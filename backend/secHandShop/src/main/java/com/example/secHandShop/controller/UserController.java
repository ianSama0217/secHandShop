package com.example.secHandShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.secHandShop.entity.User;
import com.example.secHandShop.service.ifs.UserService;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.LoginReq;
import com.example.secHandShop.vo.UserRes;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/user/register")
	public BasicRes register(@RequestBody User user) {
		return userService.register(user);
	}

	@PostMapping(value = "/user/login")
	public UserRes login(@RequestBody LoginReq req) {
		return userService.login(req);
	}

	@PatchMapping(value = "/user/update")
	public BasicRes update(@RequestBody User user) {
		return userService.update(user);
	}

	@GetMapping(value = "/user/{id}")
	public UserRes getUser(@PathVariable(name = "id") int userId) {
		return userService.getUser(userId);
	}
}
