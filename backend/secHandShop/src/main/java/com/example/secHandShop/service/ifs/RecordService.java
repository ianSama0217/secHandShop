package com.example.secHandShop.service.ifs;

import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.RecordReq;

public interface RecordService {

	public BasicRes create(RecordReq req);

	// 取消訂單:state => -1
	public BasicRes cancel(int recordId);

	// 賣家出貨訂單:state => 2
	public BasicRes sellerShip(int recordId);

	// 買家收到商品:state => 3
	public BasicRes receivedProduct(int recordId);
}
