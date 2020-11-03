package com.green.biz.product.impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.product.dto.ProductVO;
import com.green.biz.utils.Criteria;

@Repository
public class ProductDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<ProductVO> getNewProductList() {
		System.out.println("getNewProductList() 기능처리 : 신상품 목록 얻어오기");
		
		return mybatis.selectList("ProductDAO.getNewProductList");
	}
	
	public List<ProductVO> getBestProductList() {
		System.out.println("getBestProductList() 기능처리 : 베스트 상품 목록 얻어오기");
		
		return mybatis.selectList("ProductDAO.getBestProductList");
	}
	
	public ProductVO getProduct(ProductVO vo) {
		System.out.println("getProduct() 기능처리 : 상품정보 얻어오기");
		
		return mybatis.selectOne("ProductDAO.getProduct", vo);
	}
	
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		System.out.println("getProductListByKind() 기능처리 : 상품 종류별 상품 목록 얻어오기");
		
		return mybatis.selectList("ProductDAO.getProductListByKind", vo);
	}
	
	public int countProductList(String name) {
		return mybatis.selectOne("ProductDAO.countProductList", name);
	}
	
	public List<ProductVO> listProduct(String name) {
		return mybatis.selectList("ProductDAO.listProduct", name);
	}
	
	// 페이지별 상품 목록 조회
	public List<ProductVO> getListProductPaging(String name, Criteria cri) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("criteria", cri);
		return mybatis.selectList("ProductDAO.listWithPaging", map);
	}
	
	public void insertProduct(ProductVO vo) {
		mybatis.insert("ProductDAO.insertProduct", vo);
	}
	
	public void updateProduct(ProductVO vo) {
		mybatis.update("ProductDAO.updateProduct", vo);
	}
	
	

}
