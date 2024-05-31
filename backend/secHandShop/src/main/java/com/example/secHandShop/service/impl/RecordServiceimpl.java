package com.example.secHandShop.service.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.secHandShop.constants.RtnMsg;
import com.example.secHandShop.entity.Record;
import com.example.secHandShop.entity.RecordItem;
import com.example.secHandShop.entity.RecordState;
import com.example.secHandShop.repository.RecordDao;
import com.example.secHandShop.repository.RecordItemDao;
import com.example.secHandShop.repository.RecordStateDao;
import com.example.secHandShop.repository.UserDao;
import com.example.secHandShop.service.ifs.ProductService;
import com.example.secHandShop.service.ifs.RecordService;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.ProductRes;
import com.example.secHandShop.vo.RecordReq;

@Service
public class RecordServiceimpl implements RecordService {

	@Autowired
	private RecordDao recordDao;

	@Autowired
	private RecordItemDao itemDao;

	@Autowired
	private RecordStateDao stateDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductService productService;

	@Transactional
	@Override
	public BasicRes create(RecordReq req) {
		if (req.getRecord() == null || req.getItemList() == null) {
			return new BasicRes(RtnMsg.PARAM_ERROR);
		}

		// 檢查使用者帳號
		if (!userDao.existsById(req.getRecord().getBuyerId())) {
			return new BasicRes(RtnMsg.ACCOUNT_NOT_FOUND);
		}

		// 檢查商品數量 & 狀態
		for (RecordItem item : req.getItemList()) {
			ProductRes res = productService.getProduct(item.getProductId());
			if (!res.rtnMsg.getMessage().equals("Successful!")) {
				return new BasicRes(res.rtnMsg);
			}

			int inventory = res.getProduct().getInventory();
			int state = res.getProduct().getState();

			if (item.getQuantity() <= 0 || item.getQuantity() > inventory) {
				return new BasicRes(RtnMsg.QUANTITY_ERROR);
			}

			if (state != 1) {
				return new BasicRes(RtnMsg.PRODUCT_NOT_PURCHASE);
			}

			int price = res.getProduct().getPrice();
			item.setPrice(item.getQuantity() * price);
		}

		req.getState().setState(1);
		req.getState().setStateTime(LocalDateTime.now());

		try {
			Record res = recordDao.save(req.getRecord());
			// 設定recordId
			for (RecordItem item : req.getItemList()) {
				item.setRecordId(res.getRecordId());
			}

			req.getState().setRecordId(res.getRecordId());
			itemDao.saveAll(req.getItemList());
			stateDao.save(req.getState());
		} catch (Exception e) {
			return new BasicRes(RtnMsg.CREATE_RECORD_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public BasicRes cancel(int recordId) {
		if (!recordDao.existsById(recordId)) {
			return new BasicRes(RtnMsg.RECORD_NOT_FOUND);
		}

		RecordState state = new RecordState();
		state.setState(-1);
		state.setStateTime(LocalDateTime.now());
		state.setRecordId(recordId);

		try {
			stateDao.save(state);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.CANCEL_RECORD_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public BasicRes sellerShip(int recordId) {
		if (!recordDao.existsById(recordId)) {
			return new BasicRes(RtnMsg.RECORD_NOT_FOUND);
		}

		RecordState state = new RecordState();
		state.setState(2);
		state.setStateTime(LocalDateTime.now());
		state.setRecordId(recordId);

		try {
			stateDao.save(state);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.SHIP_RECORD_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public BasicRes receivedProduct(int recordId) {
		if (!recordDao.existsById(recordId)) {
			return new BasicRes(RtnMsg.RECORD_NOT_FOUND);
		}

		RecordState state = new RecordState();
		state.setState(3);
		state.setStateTime(LocalDateTime.now());
		state.setRecordId(recordId);

		try {
			stateDao.save(state);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.RECEIVED_RECORD_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

}
