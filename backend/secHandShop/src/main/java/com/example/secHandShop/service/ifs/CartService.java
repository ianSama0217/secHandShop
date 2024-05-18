package com.example.secHandShop.service.ifs;

import com.example.secHandShop.entity.Cart;
import com.example.secHandShop.vo.BasicRes;
import com.example.secHandShop.vo.CartRes;
import com.example.secHandShop.vo.UpdateCartReq;

public interface CartService {

	public BasicRes create(Cart cart);

	public BasicRes delete(int cartId);

	// 變更購買數量
	public BasicRes update(UpdateCartReq req);

	public CartRes getCart(int userId);
}
