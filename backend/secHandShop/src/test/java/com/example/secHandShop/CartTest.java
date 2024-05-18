package com.example.secHandShop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.secHandShop.repository.CartDao;

@SpringBootTest
public class CartTest {

	@Autowired
	private CartDao dao;

	@Test
	public void updateCartTest() {
		int res = dao.updateCart(1, 5);
		System.out.println("res:" + res);
	}
}
