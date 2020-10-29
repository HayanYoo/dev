package com.green.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDAO cart;
	
	@Override
	public void insertCart(CartVO vo) {
		cart.insertCart(vo);

	}

	@Override
	public List<CartVO> listCart(CartVO vo) {
		return cart.listCart(vo);
	}

	@Override
	public void deleteCart(CartVO vo) {
		cart.deleteCart(vo);

	}

	@Override
	public void updateCart(CartVO vo) {
		cart.updateCart(vo);
		
	}

	@Override
	public int totalPrice(CartVO vo) {
		return cart.totalPrice(vo);
	}

}
