package com.example.secHandShop.service.ifs;

import com.example.secHandShop.entity.Product;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.ProductRes;

public interface ProductService {

	public ProductRes search(String name, Integer type, Integer lowPrice, Integer highPrice, String sort);

	public ProductRes getProduct(int productId);

	public BasicRes create(Product product);

	// 將state改為-1,不刪除
	public BasicRes delete(int productId);

	public BasicRes update(Product product);
}
