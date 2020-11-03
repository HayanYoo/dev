package com.green.biz.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.product.ProductService;
import com.green.biz.product.dto.ProductVO;
import com.green.biz.utils.Criteria;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public List<ProductVO> getNewProductList() {
		
		return productDAO.getNewProductList();
	}

	@Override
	public List<ProductVO> getBestProductList() {
	
		return productDAO.getBestProductList();
	}

	@Override
	public ProductVO getProduct(ProductVO vo) {
	
		return productDAO.getProduct(vo);
	}

	@Override
	public List<ProductVO> getProductListByKind(ProductVO vo) {
		return productDAO.getProductListByKind(vo);
	}

	@Override
	public int countProductList(String name) {
		
		return productDAO.countProductList(name);
	}

	@Override
	public List<ProductVO> getlistProduct(String name) {
		return productDAO.listProduct(name);
	}

	@Override
	public void insertProduct(ProductVO vo) {
		productDAO.insertProduct(vo);
		
	}

	@Override
	public void updateProduct(ProductVO vo) {
		productDAO.updateProduct(vo);
		
	}

	@Override
	public List<ProductVO> getListProductPaging(String name, Criteria cri) {
		return productDAO.getListProductPaging(name, cri);
	}

}
