package com.example.secHandShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.secHandShop.entity.Cart;
import com.example.secHandShop.service.ifs.CartService;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.CartRes;
import com.example.secHandShop.vo.UpdateCartReq;

@CrossOrigin
@RestController
public class CartController {

	@Autowired
	private CartService service;

	// (商品)加入購物車
	@PostMapping(value = "/cart/create")
	public BasicRes create(@RequestBody Cart cart) {
		return service.create(cart);
	}

	// 刪除購物車
	@DeleteMapping(value = "/cart/delete/{id}")
	public BasicRes delete(@PathVariable(name = "id") int cartId) {
		return service.delete(cartId);
	}

	// 更新購物車商品購買數量
	@PatchMapping(value = "/cart/update")
	public BasicRes update(@RequestBody UpdateCartReq req) {
		return service.update(req);
	}

	// 取得指定使用者的購物車
	@GetMapping(value = "/cart/{id}")
	public CartRes getCart(@PathVariable(name = "id") int userId) {
		return service.getCart(userId);
	}

}
