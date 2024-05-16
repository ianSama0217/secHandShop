package com.example.secHandShop.service.ifs;

import java.util.List;

import com.example.secHandShop.entity.Product;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.ProductRes;

public interface ProductService {

	public ProductRes search(Integer userId, String name, List<Integer> type, Integer lowPrice, Integer highPrice,
			String sort);

	public ProductRes getProduct(int productId);

	public BasicRes create(Product product);

	// 將state改為-1,不刪除
	public BasicRes delete(int productId);

	public BasicRes update(Product product);

	public ProductRes getStore(int userId);
}
