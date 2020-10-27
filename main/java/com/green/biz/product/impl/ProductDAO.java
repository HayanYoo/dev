package com.green.biz.product.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.product.dto.ProductVO;

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
	
	

}
