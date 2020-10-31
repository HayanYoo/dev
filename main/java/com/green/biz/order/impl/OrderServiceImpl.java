package com.green.biz.order.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.order.CartService;
import com.green.biz.order.CartVO;
import com.green.biz.order.OrderService;
import com.green.biz.order.OrderVO;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private CartService cartService;
	
	@Override
	public int selectMaxOseq() {		
		return orderDao.selectMaxOseq();
	}

	@Override
	public int insertOrder(OrderVO vo) {
		// �ֹ���ȣ�� �����Ѵ�.
		int oseq = selectMaxOseq();
		
		// �ֹ���ȣ�� vo ��ü�� ����
		vo.setOseq(oseq);
		
		// �ֹ����̺��� �ֹ���ȣ�� ����
		orderDao.insertOrder(vo);
		
		// ��ٱ��Ͽ��� �ֹ��󼼸���� �о�´�.
		CartVO cartVO = new CartVO();
		cartVO.setId(vo.getId());
		List<CartVO> listCart = cartService.listCart(cartVO);
		
		// ��ٱ����� ����� �ֹ� �� ���̺��� �����Ѵ�.
		for(CartVO item : listCart) {
			OrderVO order = new OrderVO();
			order.setOseq(oseq);
			order.setPseq(item.getPseq());
			order.setQuantity(item.getQuantity());
			
			insertOrderDetail(order);
			
			// cart ���̺��� �ֹ�ó�� ����� �����Ѵ�.
			cartService.updateCart(item);
		}
		return oseq;

	}

	@Override
	public void insertOrderDetail(OrderVO vo) {
		orderDao.insertOrderDetail(vo);

	}

	@Override
	public List<OrderVO> listOrderById(OrderVO vo) {
		return orderDao.listOrderById(vo);
	}

	@Override
	public List<Integer> selectSeqOrdering(String id) {
		return orderDao.selectSeqOrdering(id);
	}

}