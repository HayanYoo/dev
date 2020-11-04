package com.green.biz.order.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.order.OrderVO;
import com.green.biz.product.dto.ProductVO;
import com.green.biz.utils.Criteria;

@Repository
public class OrderDAO  {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int selectMaxOseq() {
		return mybatis.selectOne("OrderDAO.selectMaxOseq");
	}
	
	public void insertOrder(OrderVO vo) {
		mybatis.insert("OrderDAO.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		mybatis.insert("OrderDAO.insertOrderDetail", vo);
	}
	
	public List<OrderVO> listOrderById(OrderVO vo){
		return mybatis.selectList("OrderDAO.listOrderById", vo);
	}
	
	public List<Integer> selectSeqOrdering(OrderVO vo) {
		return mybatis.selectList("OrderDAO.selectSeqOrdering", vo);
	}
	
	public List<OrderVO> listOrder(String key) {
		return mybatis.selectList("OrderDAO.listOrder", key);
	}
	
	public void updateOrderResult(int odseq) {
		mybatis.update("OrderDAO.updateOrderResult", odseq);
	}
	
	// 페이지별 주문 목록 조회
	public List<OrderVO> getListOrderPaging(String name, Criteria cri) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("criteria", cri);
		return mybatis.selectList("OrderDAO.listWithPaging", map);
	}
	
	public int countOrderList(String name) {
		return mybatis.selectOne("OrderDAO.countOrderList", name);
	}
	
}
