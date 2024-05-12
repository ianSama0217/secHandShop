package com.example.secHandShop.service.ifs;

import com.example.secHandShop.entity.User;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.LoginReq;
import com.example.secHandShop.vo.LoginRes;

public interface UserService {

	public BasicRes register(User user);

	public LoginRes login(LoginReq req);

}
