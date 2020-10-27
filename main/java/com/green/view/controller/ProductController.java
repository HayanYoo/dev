package com.green.view.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.product.ProductService;
import com.green.biz.product.dto.ProductVO;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/product_detail", method=RequestMethod.GET)
	public String productDetailAction(ProductVO vo, Model model) {
		
		ProductVO pVo = productService.getProduct(vo);
		model.addAttribute("productVO", pVo);
		
		return "product/productDetail";	
		
	}
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String productKindAction(ProductVO vo, Model model) {
		
		System.out.println("카테고리번호 : " + vo.getKind());
		List<ProductVO> productKindList = productService.getProductListByKind(vo);
		model.addAttribute("productKindList", productKindList);
		
		return "product/productKind";
		
	}

}
