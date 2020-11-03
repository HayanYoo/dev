package com.green.biz.product;

import java.util.List;

import com.green.biz.product.dto.ProductVO;
import com.green.biz.utils.Criteria;

public interface ProductService {
	public List<ProductVO> getNewProductList();
	
	public List<ProductVO> getBestProductList();
	
	public ProductVO getProduct(ProductVO vo);
	
	public List<ProductVO> getProductListByKind(ProductVO vo);
	
	public int countProductList(String name) ;
	
	public List<ProductVO> getlistProduct(String name);
	
	public void insertProduct(ProductVO vo);
	
	public void updateProduct(ProductVO vo);
	
	public List<ProductVO> getListProductPaging(String name, Criteria cri);

}
