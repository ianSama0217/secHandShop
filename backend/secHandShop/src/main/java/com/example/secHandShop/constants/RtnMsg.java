package com.example.secHandShop.constants;

import java.lang.String;

public enum RtnMsg {

	SUCCESSFUL(200, "Successful!"), //
	PARAM_ERROR(400, "Param error!"), //
	// 註冊
	EMAIL_IS_EXISTED(400, "Email is existed!"), //
	EMAIL_FORMAT_ERROR(400, "Email format error!"), //
	PASSWORD_FORMAT_ERROR(400, "Password format error!"), //
	PHONE_FORMAT_ERROR(400, "Phone format error!"), //
	REGISTER_ERROR(500, "Register error!"), //
	// 登入
	ACCOUNT_NOT_FOUND(400, "Account not found!"), //
	PASSWORD_ERROR(400, "Password error!"), //
	LOGIN_ERROR(500, "Login error!"), //
	// 修改使用者帳號資料
	UPDATE_ACCOUNT_ERROR(500, "Update account error!"), //
	// 搜尋商品
	// 取得商品資料
	PRODUCT_NOT_FOUND(400, "Product not found!"), //
	// 創建商品
	CREATE_PRODUCT_ERROR(500, "Create product error!"), //
	// 刪除商品
	DELETE_PRODUCT_ERROR(500, "Delete product error!"), //
	// 更新商品
	UPDATE_PRODUCT_ERROR(500, "Update product error!"), //
	// 取得店家商品資料
	STORE_NOT_FOUND(400, "Store not found!"), //
	// 新增購物車
	PRODUCT_ALREADY_EXISTS(400, "Product already exists!"), //
	QUANTITY_ERROR(400, "Quantity error!"), //
	PRODUCT_NOT_PURCHASE(400, "Product not purchase!"), //
	CREATE_CART_ERROR(500, "Create cart error!"), //
	// 刪除購物車
	CART_NOT_FOUND(400, "Cart not found!"), //
	DELETE_CART_ERROR(500, "Delete cart error!"), //
	// 更新購物車
	UPDATE_CART_ERROR(500, "Update cart error!"), //
	// 新增訂單
	CREATE_RECORD_ERROR(500, "Create record error!"), //
	// 更新訂單狀態
	RECORD_NOT_FOUND(400, "Record not found!"), //
	CANCEL_RECORD_ERROR(500, "Cancel record error!"), //
	SHIP_RECORD_ERROR(500, "Ship record error!"), //
	RECEIVED_RECORD_ERROR(500, "Received record error!"),//
	;

	private int stateCode;

	private String message;

	private RtnMsg(int stateCode, String message) {
		this.stateCode = stateCode;
		this.message = message;
	}

	public int getStateCode() {
		return stateCode;
	}

	public String getMessage() {
		return message;
	}

}
