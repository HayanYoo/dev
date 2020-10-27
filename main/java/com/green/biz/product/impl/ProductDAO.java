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
		System.out.println("getNewProductList() ���ó�� : �Ż�ǰ ��� ������");
		
		return mybatis.selectList("ProductDAO.getNewProductList");
	}
	
	public List<ProductVO> getBestProductList() {
		System.out.println("getBestProductList() ���ó�� : ����Ʈ ��ǰ ��� ������");
		
		return mybatis.selectList("ProductDAO.getBestProductList");
	}
	
	public ProductVO getProduct(ProductVO vo) {
		System.out.println("getProduct() ���ó�� : ��ǰ���� ������");
		
		return mybatis.selectOne("ProductDAO.getProduct", vo);
	}
	
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		System.out.println("getProductListByKind() ���ó�� : ��ǰ ������ ��ǰ ��� ������");
		
		return mybatis.selectList("ProductDAO.getProductListByKind", vo);
	}
	
	

}
