package com.green.biz.qna;

import java.util.List;

public interface QnaService {

	List<QnaVO> listQna(String id);

	QnaVO getQna(int seq);

	void insertQna(QnaVO vo);

}