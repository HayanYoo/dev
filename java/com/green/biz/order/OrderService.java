package com.green.biz.order;

import java.util.List;

import com.green.biz.product.dto.ProductVO;
import com.green.biz.utils.Criteria;

public interface OrderService {

	int selectMaxOseq();

	int insertOrder(OrderVO vo);

	void insertOrderDetail(OrderVO vo);

	List<OrderVO> listOrderById(OrderVO vo);

	List<Integer> selectSeqOrdering(OrderVO vo);

	public List<OrderVO> listOrder(String member_name);
	
	public void updateOrderResult(int odseq);
	
	public List<OrderVO> getListOrderPaging(String name, Criteria cri);
	
	public int countOrderList(String name) ;
	
}