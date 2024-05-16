package com.example.secHandShop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.secHandShop.entity.User;
import com.example.secHandShop.repository.UserDao;

@SpringBootTest
public class UserTest {

	@Autowired
	private UserDao dao;

	@Test
	public void findAccountTest() {
		User user = new User();

		// 帳號不存在
		user = dao.findAccount("jk666@email.com");
		if (user == null) {
			System.out.println("帳號不存在");
		}

		// 帳號存在
		user = dao.findAccount("ian123@gmail.com");
		if (user != null) {
			System.out.println("帳號:" + user.getEmail() + "\n密碼:" + user.getPwd());
		}
	}

	@Test
	public void updateAccountTest() {
		int res = dao.updateAccount(1, "阿扁巴巴大俠", null, null, "0966667777");
		System.out.println("res:" + res);
	}
}
