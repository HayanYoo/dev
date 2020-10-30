package com.green.biz.order;

import java.util.List;

public interface OrderService {

	int selectMaxOseq();

	int insertOrder(OrderVO vo);

	void insertOrderDetail(OrderVO vo);

	List<OrderVO> listOrderById(OrderVO vo);

	List<Integer> selectSeqOrdering(String id);

}