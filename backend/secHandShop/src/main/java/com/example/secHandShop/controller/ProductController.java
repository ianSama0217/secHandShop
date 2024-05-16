package com.example.secHandShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.secHandShop.entity.Product;
import com.example.secHandShop.service.ifs.ProductService;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.ProductRes;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	// 查詢商品
	@GetMapping(value = "/product")
	public ProductRes search(@RequestParam(name = "userId", required = false) Integer userId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "type", required = false) List<Integer> type,
			@RequestParam(name = "lowPrice", required = false) Integer lowPrice,
			@RequestParam(name = "highPrice", required = false) Integer highPrice,
			@RequestParam(name = "sort", required = false) String sort) {
		return productService.search(userId, name, type, lowPrice, highPrice, sort);
	}

	// 取得指定商品資訊(單筆)
	@GetMapping(value = "/product/{id}")
	public ProductRes getProduct(@PathVariable(name = "id") int productId) {
		return productService.getProduct(productId);
	}

	// 新增商品
	@PostMapping(value = "/product/create")
	public BasicRes create(@RequestBody Product product) {
		return productService.create(product);
	}

	// 刪除商品(將state改為-1,不刪除)
	@PatchMapping(value = "/product/delete")
	public BasicRes delete(@RequestParam(name = "id") int productId) {
		return productService.delete(productId);
	}

	// 更新商品資訊
	@PatchMapping(value = "/product/update")
	public BasicRes update(@RequestBody Product product) {
		return productService.update(product);
	}

	// 查詢使用者的所有商品
	@GetMapping(value = "/store/{id}")
	public ProductRes getStore(@PathVariable(name = "id") int userId) {
		return productService.getStore(userId);
	}
}
