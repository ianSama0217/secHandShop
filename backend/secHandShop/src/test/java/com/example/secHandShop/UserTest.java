package com.example.secHandShop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.secHandShop.entity.User;
import com.example.secHandShop.repository.UserDao;

@SpringBootTest
public class UserTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void findAccountTest() {
		User user = new User();

		// 帳號不存在
		user = userDao.findAccount("jk666@email.com");
		if (user == null) {
			System.out.println("帳號不存在");
		}

		// 帳號存在
		user = userDao.findAccount("ian123@gmail.com");
		if (user != null) {
			System.out.println("信箱:" + user.getEmail() + "\n密碼:" + user.getPwd());
		}
	}
}
