package com.example.secHandShop.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.secHandShop.constants.RtnMsg;
import com.example.secHandShop.entity.User;
import com.example.secHandShop.repository.UserDao;
import com.example.secHandShop.service.ifs.UserService;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.LoginReq;
import com.example.secHandShop.vo.UserRes;

@Service
public class UserServiceimpl implements UserService {

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserDao userDao;

	private boolean checkAccount(String email, String pwd) {
		if (!StringUtils.hasText(email) || !StringUtils.hasText(pwd)) {
			return true;
		}

		return false;
	}

	private boolean checkUserInfo(String name, String phone) {
		if (!StringUtils.hasText(name) || !StringUtils.hasText(phone)) {
			return true;
		}

		return false;
	}

	@Override
	public BasicRes register(User user) {
		if (checkUserInfo(user.getName(), user.getPhone())) {
			return new BasicRes(RtnMsg.PARAM_ERROR);
		}

		if (userDao.existsByEmail(user.getEmail())) {
			return new BasicRes(RtnMsg.EMAIL_IS_EXISTED);
		}

		String phonePttern = "^(\\\\+[0-9]{1,3})?[0-9]{8,14}$";
		String emailPattern = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
		String pwdPattern = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";

		if (!user.getPhone().matches(phonePttern)) {
			return new BasicRes(RtnMsg.PHONE_FORMAT_ERROR);
		}

		if (!user.getEmail().matches(emailPattern)) {
			return new BasicRes(RtnMsg.EMAIL_FORMAT_ERROR);
		}

		if (!user.getPwd().matches(pwdPattern)) {
			return new BasicRes(RtnMsg.PASSWORD_FORMAT_ERROR);
		}

		user.setPwd(encoder.encode(user.getPwd()));

		try {
			userDao.save(user);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.REGISTER_ERROR);
		}
		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public UserRes login(LoginReq req) {
		if (checkAccount(req.getEmail(), req.getPwd())) {
			return new UserRes(RtnMsg.PARAM_ERROR);
		}

		User user = new User();

		try {
			user = userDao.findAccount(req.getEmail());
		} catch (Exception e) {
			return new UserRes(RtnMsg.LOGIN_ERROR);
		}

		if (user == null) {
			return new UserRes(RtnMsg.ACCOUNT_NOT_FOUND);
		}

		if (!encoder.matches(req.getPwd(), user.getPwd())) {
			return new UserRes(RtnMsg.PASSWORD_ERROR);
		}

		user.setPwd("");
		return new UserRes(RtnMsg.SUCCESSFUL, user);
	}

	@Override
	public BasicRes update(User user) {
		if (checkUserInfo(user.getName(), user.getPhone())) {
			return new BasicRes(RtnMsg.PARAM_ERROR);
		}

		try {
			userDao.updateAccount(user.getUserId(), user.getName(), user.getMimeType(), user.getPhoto(),
					user.getPhone());
		} catch (Exception e) {
			return new BasicRes(RtnMsg.UPDATE_ACCOUNT_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public UserRes getUser(int userId) {
		Optional<User> op = userDao.findById(userId);

		if (op.isEmpty()) {
			return new UserRes(RtnMsg.ACCOUNT_NOT_FOUND);
		}

		User user = op.get();
		user.setPwd("");
		return new UserRes(RtnMsg.SUCCESSFUL, user);
	}

}
