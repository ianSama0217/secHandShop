package com.example.secHandShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.secHandShop.service.ifs.RecordService;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.RecordReq;

@CrossOrigin
@RestController
public class RecordController {

	@Autowired
	private RecordService service;

	@PostMapping(value = "/record/create")
	public BasicRes create(@RequestBody RecordReq req) {
		return service.create(req);
	}

}
