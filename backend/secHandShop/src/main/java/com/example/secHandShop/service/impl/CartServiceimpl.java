package com.example.secHandShop.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.secHandShop.constants.RtnMsg;
import com.example.secHandShop.entity.Cart;
import com.example.secHandShop.repository.CartDao;
import com.example.secHandShop.repository.UserDao;
import com.example.secHandShop.service.ifs.CartService;
import com.example.secHandShop.service.ifs.ProductService;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.CartProductVo;
import com.example.secHandShop.vo.CartRes;
import com.example.secHandShop.vo.ProductRes;
import com.example.secHandShop.vo.UpdateCartReq;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceimpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductService productService;

	// 檢查商品狀態是否符合加入購物車條件
	// 購買數量0 < quantity < 商品庫存數
	private boolean checkQuantity(int quantity, int inventory) {
		if (quantity <= 0 || quantity > inventory) {
			return true;
		}
		return false;
	}

	// state = 1(一般販售)
	private boolean checkState(int state) {
		if (state != 1) {
			return true;
		}
		return false;
	}

	@Override
	public BasicRes create(Cart cart) {
		ProductRes res = productService.getProduct(cart.getProductId());
		if (!res.rtnMsg.getMessage().equals("Successful!")) {
			return new BasicRes(res.rtnMsg);
		}

		if (!userDao.existsById(cart.getUserId())) {
			return new BasicRes(RtnMsg.ACCOUNT_NOT_FOUND);
		}

		if (cartDao.existsByUserIdAndProductId(cart.getUserId(), cart.getProductId())) {
			return new BasicRes(RtnMsg.PRODUCT_ALREADY_EXISTS);
		}

		int inventory = res.getProduct().getInventory();
		int state = res.getProduct().getState();

		if (checkQuantity(cart.getQuantity(), inventory)) {
			return new BasicRes(RtnMsg.QUANTITY_ERROR);
		}

		if (checkState(state)) {
			return new BasicRes(RtnMsg.PRODUCT_NOT_PURCHASE);
		}

		int price = res.getProduct().getPrice();

		cart.setPrice(cart.getQuantity() * price);
		cart.setAddTime(LocalDateTime.now());

		try {
			cartDao.save(cart);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.CREATE_CART_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public BasicRes delete(int cartId) {
		if (!cartDao.existsById(cartId)) {
			return new BasicRes(RtnMsg.CART_NOT_FOUND);
		}

		try {
			cartDao.deleteById(cartId);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.DELETE_CART_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public BasicRes update(UpdateCartReq req) {

		Optional<Cart> op = cartDao.findById(req.getCartId());

		if (op.isEmpty()) {
			return new BasicRes(RtnMsg.CART_NOT_FOUND);
		}

		Cart cart = op.get();

		ProductRes res = productService.getProduct(cart.getProductId());
		if (!res.rtnMsg.getMessage().equals("Successful!")) {
			return new BasicRes(res.rtnMsg);
		}

		if (!userDao.existsById(cart.getUserId())) {
			return new BasicRes(RtnMsg.ACCOUNT_NOT_FOUND);
		}

		int inventory = res.getProduct().getInventory();
		int state = res.getProduct().getState();

		if (checkQuantity(req.getQuantity(), inventory)) {
			return new BasicRes(RtnMsg.QUANTITY_ERROR);
		}

		if (checkState(state)) {
			return new BasicRes(RtnMsg.PRODUCT_NOT_PURCHASE);
		}

		int price = req.getQuantity() * res.getProduct().getPrice();

		try {
			cartDao.updateCart(req.getCartId(), req.getQuantity(), price);
		} catch (Exception e) {
			return new BasicRes(RtnMsg.UPDATE_CART_ERROR);
		}

		return new BasicRes(RtnMsg.SUCCESSFUL);
	}

	@Override
	public CartRes getCart(int userId) {
		if (!userDao.existsById(userId)) {
			return new CartRes(RtnMsg.ACCOUNT_NOT_FOUND);
		}

		List<CartProductVo> cartList = cartDao.getCart(userId);
		return new CartRes(RtnMsg.SUCCESSFUL, cartList);
	}
}
