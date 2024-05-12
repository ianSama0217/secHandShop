package com.example.secHandShop.vo;

import java.util.List;

import com.example.secHandShop.constants.RtnMsg;
import com.example.secHandShop.entity.Product;

public class ProductRes extends BasicRes {

	private Product product;

	private List<Product> productList;

	public ProductRes(RtnMsg rtnMsg) {
		super(rtnMsg);
	}

	public ProductRes(RtnMsg rtnMsg, Product product) {
		super(rtnMsg);
		this.product = product;
	}

	public ProductRes(RtnMsg rtnMsg, List<Product> productList) {
		super(rtnMsg);
		this.productList = productList;
	}

	public Product getProduct() {
		return product;
	}

	public List<Product> getProductList() {
		return productList;
	}

}
