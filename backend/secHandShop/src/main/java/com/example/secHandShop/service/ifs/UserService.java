package com.example.secHandShop.service.ifs;

import org.springframework.stereotype.Service;

import com.example.secHandShop.entity.User;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.LoginReq;
import com.example.secHandShop.vo.LoginRes;

@Service
public interface UserService {

	public BasicRes register(User user);

	public LoginRes login(LoginReq req);

}
