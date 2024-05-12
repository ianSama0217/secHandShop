package com.example.secHandShop.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.secHandShop.constants.RtnMsg;
import com.example.secHandShop.entity.Product;
import com.example.secHandShop.repository.ProductDao;
import com.example.secHandShop.service.ifs.ProductService;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.ProductRes;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	private boolean checkProduct(String name, String description, int type, int price, int inventory, int state) {
		if (!StringUtils.hasText(name) || !StringUtils.hasText(description) || type == -1 || price < 0 || inventory < 0
				|| state == -1) {
			return true;
		}

		return false;
	}

	@Override
	public ProductRes search(String name, Integer type, Integer lowPrice, Integer highPrice, String sort) {
		List<Product> productList = productDao.search(name, type, lowPrice, highPrice, sort);
		return new ProductRes(RtnMsg.SUCCESSFUL, productList);
	}

	@Override
	public ProductRes getProduct(int productId) {
		Optional<Product> op = productDao.findById(productId);

		if (op.isEmpty()) {
			return new ProductRes(RtnMsg.PRODUCT_NOT_FOUND);
		}

		Product product = op.get();
		return new ProductRes(RtnMsg.SUCCESSFUL, product);
	}

	@Override
	public BasicRes create(Product product) {
		if (checkProduct(product.getName(), product.getDescription(), product.getType(), product.getPrice(),
				product.getInventory(), product.getState())) {
			return new BasicRes(RtnMsg.PARAM_ERROR);
		}

		product.setUpdateTime(LocalDateTime.now());

		try {
			productDao.save(product);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.CREATE_PRODUCT_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public BasicRes delete(int productId) {
		// TODO 若商品訂單位未完成無法刪除
		try {
			productDao.updateState(productId);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.DELETE_PRODUCT_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public BasicRes update(Product product) {
		// TODO 若商品訂單位未完成無法更改狀態
		try {
			productDao.updateProduct(product.getProductId(), product.getName(), product.getDescription(),
					product.getType(), product.getPhoto(), product.getPrice(), product.getInventory(),
					product.getState());
		} catch (Exception e) {
			return new BasicRes(RtnMsg.UPDATE_PRODUCT_ERROR);
		}
		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

}