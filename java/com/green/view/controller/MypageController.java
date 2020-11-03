package com.green.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.member.MemberVO;
import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;

@Controller
public class MypageController {

	static final String INCOMPLETE_ORDER = "1";
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/cart_insert", method = RequestMethod.POST)
	public String cartInsert(CartVO vo, HttpSession session) {

		// ����ڰ� �α��� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId()); // ����� ID�� īƮ������ ����
			cartService.insertCart(vo);

			return "redirect:cart_list"; // controller�� "cart_list" �� ��û�ϴ� ���ڿ� ��ȯ
		}
	}

	@RequestMapping(value = "/cart_list", method = RequestMethod.GET)
	public String cartList(HttpSession session, CartVO vo, Model model) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId()); // ����� ID�� īƮ������ ����
			List<CartVO> cartList = cartService.listCart(vo);

			int totalPrice = 0;
			for (CartVO cartVO : cartList) {
				totalPrice += cartVO.getPrice2() * cartVO.getQuantity();
			}

			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalPrice);

			return "mypage/cartList";
		}
	}

	@RequestMapping(value = "/cart_delete", method = RequestMethod.POST)
	public String cartDelete(@RequestParam(value = "cseq") int[] cseq) {
		for (int c : cseq) {
			cartService.deleteCart(c);
		}

		return "redirect:/cart_list";
	}

	@RequestMapping(value = "order_insert", method = RequestMethod.POST)
	public String order_insert(OrderVO order, HttpSession session, Model model) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			order.setId(loginUser.getId());
			int oseq = orderService.insertOrder(order);
			model.addAttribute("oseq", oseq);
			return "redirect:/order_list";
		}
	}

	@RequestMapping(value = "order_list")
	public String orderList(OrderVO vo, HttpSession session, Model model) {

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		int totalPrice = 0;

		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());
			vo.setResult("1");

			List<OrderVO> orderList = orderService.listOrderById(vo);
			for (OrderVO order : orderList) {
				totalPrice += order.getPrice2() * order.getQuantity();
			}

			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalPrice);

			return "mypage/orderList";
		}
	}

	@RequestMapping(value = "mypage")
	public String mypageView(OrderVO orderVO, HttpSession session, Model model) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			orderVO.setId(loginUser.getId());
			orderVO.setResult("1");
				
			List<Integer> oseqList = orderService.selectSeqOrdering(orderVO);
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			for (int oseq : oseqList) {

				OrderVO order = new OrderVO();

				order.setId(loginUser.getId());
				order.setOseq(oseq);
				order.setResult(INCOMPLETE_ORDER);

				// �ϳ��� �ֹ���ȣ�� ���� �ֹ� ���� ��ȸ
				List<OrderVO> orderBySeq = orderService.listOrderById(order);

				OrderVO vo = new OrderVO(); // �� �ֹ��� �ֹ� ���� ��� �ؼ� ����.
				vo.setIndate(orderBySeq.get(0).getIndate());
				vo.setOseq(orderBySeq.get(0).getOseq());

				if (orderBySeq.size() > 1) {
					vo.setPname(orderBySeq.get(0).getPname() + "�� " + (orderBySeq.size() - 1) + "��");
				} else
					vo.setPname(orderBySeq.get(0).getPname());

				int priceSum = 0;
				for (int i = 0; i < orderBySeq.size(); i++) {
					priceSum += orderBySeq.get(i).getPrice2() * orderBySeq.get(i).getQuantity();
				}
				vo.setPrice2(priceSum);
				orderList.add(vo);
			}

			model.addAttribute("title", "���� ���� �ֹ� ����");
			model.addAttribute("orderList", orderList);
			return "mypage/mypage";
		}

	}

	@RequestMapping(value = "order_detail")
	public String orderDetailView(OrderVO vo, HttpSession session, Model model) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		vo.setId(loginUser.getId());
		vo.setResult("");

		List<OrderVO> orderList = orderService.listOrderById(vo);
		OrderVO orderDetail = new OrderVO();

		orderDetail.setIndate(orderList.get(0).getIndate());
		orderDetail.setOseq(orderList.get(0).getOseq());
		orderDetail.setMname(orderList.get(0).getMname());

		int totalPrice = 0;

		for (OrderVO order : orderList) {
			totalPrice += order.getPrice2() * order.getQuantity();
		}

		model.addAttribute("orderList", orderList);
		model.addAttribute("orderDetail", orderDetail);
		model.addAttribute("totalPrice", totalPrice);

		return "mypage/orderDetail";

	}

	@RequestMapping(value = "order_all")
	public String orderAll(HttpSession session, Model model) {
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "member/login";
		} else {
			OrderVO orderVO = new OrderVO();
			orderVO.setId(loginUser.getId());
			orderVO.setResult("");
			
			List<Integer> oseqList = orderService.selectSeqOrdering(orderVO);
			List<OrderVO> orderList = new ArrayList<>();
			
			for (int oseq : oseqList) {
				OrderVO order = new OrderVO();
				
				order.setId(loginUser.getId());
				order.setOseq(oseq);
				order.setResult("");
				
				List<OrderVO> orderBySeq = orderService.listOrderById(order);
				
				
				OrderVO vo = new OrderVO();
				vo.setIndate(orderBySeq.get(0).getIndate());
				vo.setOseq(orderBySeq.get(0).getOseq());
				vo.setPname(orderBySeq.get(0).getPname() + "�� " + (orderBySeq.size()-1) + "��");
				
				
				
				int totalPrice = 0;
				for ( OrderVO price : orderBySeq) {
					
					totalPrice += price.getPrice2() * price.getQuantity();
				}
				vo.setPrice2(totalPrice);
				
				// �ֹ� ��� ������ list�� ����
				orderList.add(vo);
			}
			
			model.addAttribute("title", "�� �ֹ� ����");
			model.addAttribute("orderList", orderList);
			
			return "mypage/mypage";
		}

	}

}
