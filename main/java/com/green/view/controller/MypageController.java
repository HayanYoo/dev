package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.member.MemberVO;
import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;

@Controller
public class MypageController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping(value="/cart_insert", method=RequestMethod.POST)
	public String cartInsert(CartVO vo, HttpSession session) {
		
		// ����ڰ� �α��� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());	// ����� ID�� īƮ������ ����
			cartService.insertCart(vo);
			
			return "redirect:cart_list";	// controller�� "cart_list" �� ��û�ϴ� ���ڿ� ��ȯ
		}
	}
	
	@RequestMapping(value="/cart_list" , method=RequestMethod.GET)
	public String cartList(HttpSession session, CartVO vo, Model model) {
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if(loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());	// ����� ID�� īƮ������ ����
			List<CartVO> cartList = cartService.listCart(vo);
			model.addAttribute("cartList", cartList);
			
			return "member/cartList";	
		}
		
		
		
		
		/*
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		vo.setId(loginUser.getId());
		
		List<CartVO> cart = cartService.listCart(vo);
		int totalPrice = cartService.totalPrice(vo);
		
		model.addAttribute("cartList", cart);
		model.addAttribute("totalPrice", totalPrice);
		
		return "member/cartList";
		*/
	}
}
