package com.green.biz.order;

import java.util.List;

public interface CartService {
	
	public void insertCart(CartVO vo);
	
	public List<CartVO> listCart(CartVO vo);
	
	public void deleteCart(int cseq) ;
	
	public void updateCart(CartVO vo);

}
