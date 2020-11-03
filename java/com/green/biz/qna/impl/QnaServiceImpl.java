package com.green.biz.qna.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.qna.QnaService;
import com.green.biz.qna.QnaVO;

@Service
public class QnaServiceImpl implements QnaService {
	@Autowired
	private QnaDAO qna;
	
	@Override
	public List<QnaVO> listQna(String id) {
		return qna.listQna(id);
	}

	@Override
	public QnaVO getQna(int  seq) {
		return qna.getQna(seq);
	}

	@Override
	public void insertQna(QnaVO vo) {
		qna.insertQna(vo);

	}

}
