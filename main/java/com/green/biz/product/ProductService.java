package com.green.biz.product;

import java.util.List;

import com.green.biz.product.dto.ProductVO;

public interface ProductService {
	public List<ProductVO> getNewProductList();
	
	public List<ProductVO> getBestProductList();
	
	public ProductVO getProduct(ProductVO vo);
	
	public List<ProductVO> getProductListByKind(ProductVO vo);

}
