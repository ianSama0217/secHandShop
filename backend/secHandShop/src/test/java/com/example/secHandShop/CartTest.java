package com.example.secHandShop;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.secHandShop.repository.CartDao;
import com.example.secHandShop.vo.CartProductVo;

@SpringBootTest
public class CartTest {

	@Autowired
	private CartDao dao;
	
	@Test
	public void checkCartTest() {
		boolean res = dao.existsByUserIdAndProductId(4, 1);
		System.out.println("res:" + res);
	}

	@Test
	public void updateCartTest() {
		int res = dao.updateCart(1, 5, 999);
		System.out.println("res:" + res);
	}

	@Test
	public void getCartTest() {
		List<CartProductVo> res = dao.getCart(3);
		if (res.isEmpty()) {
			System.out.println("購物車是空的");
			return;
		}
		for (CartProductVo cart : res) {
			System.out.println("商品名稱:" + cart.getName());
			System.out.println("商品單價:" + cart.getPrice());
			System.out.println("購買數量:" + cart.getQuantity());
			System.out.println("===========================");
		}
	}
}
