package com.example.secHandShop;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.secHandShop.entity.Product;
import com.example.secHandShop.repository.ProductDao;

@SpringBootTest
public class ProductTest {

	@Autowired
	private ProductDao dao;

	@Test
	public void updateStateTest() {
		int res = dao.updateState(99);
		System.out.println("res: " + res);
	}

	@Test
	public void updateProductTest() {
		int res = dao.updateProduct(1, "葡萄", 1, null, 299, 50, 1);
		System.out.println("res: " + res);
	}

	@Test
	public void getStoreProductTest() {
		List<Product> res = dao.getStoreProduct(3);
		for (Product product : res) {
			System.out.println(product.getName());
		}
	}
}
