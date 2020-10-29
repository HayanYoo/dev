package com.green.biz.order.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.order.CartVO;

@Repository
public class CartDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertCart(CartVO vo) {
		mybatis.insert("CartDAO.insertCart", vo);
	}
	
	public List<CartVO> listCart(CartVO vo) {
		return mybatis.selectList("CartDAO.listCart", vo);
	}
	
	public void deleteCart(CartVO vo) {
		mybatis.delete("CartDAO.deleteCart", vo);
	}
	
	public void updateCart(CartVO vo) {
		mybatis.update("CartDAO.updateCart", vo);
	}
	
	public int totalPrice(CartVO vo) {
		return mybatis.selectOne("CartDAO.totalPrice", vo);
	}
}
