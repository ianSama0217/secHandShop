package com.example.secHandShop.service.ifs;

import com.example.secHandShop.entity.User;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.LoginReq;
import com.example.secHandShop.vo.UserRes;

public interface UserService {

	public BasicRes register(User user);

	public UserRes login(LoginReq req);

	public BasicRes update(User user);

	public UserRes getUser(int userId);
}
