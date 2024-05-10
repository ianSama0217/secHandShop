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

		// �b�����s�b
		user = userDao.findAccount("jk666@email.com");
		if (user == null) {
			System.out.println("�b�����s�b");
		}

		// �b���s�b
		user = userDao.findAccount("ian123@gmail.com");
		if (user != null) {
			System.out.println("�H�c:" + user.getEmail() + "\n�K�X:" + user.getPwd());
		}
	}
}
