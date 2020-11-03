package com.green.biz.order;

import java.sql.Timestamp;


import lombok.Data;

// 장바구니 정보
@Data
public class CartVO {
	private int cseq;
	private String id;
	private int pseq;
	private String mname;
	private String pname;
	private int quantity;
	private int price2;
	private Timestamp indate;
}
