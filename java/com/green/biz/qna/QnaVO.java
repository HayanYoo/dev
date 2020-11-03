package com.green.biz.qna;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QnaVO {
	private  int qseq; 
	private String subject; 
	private String content; 
	private String id; 
	private String rep; 
	private String reply;
	private Timestamp indate;

	
}
