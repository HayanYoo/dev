package com.green.biz.member.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.member.dto.MemberVO;


@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	// id�� �����ϴ��� Ȯ��
		public MemberVO findMember(String id) {
			System.out.println("findMember() ���ó�� : ȸ�� ���� ��ȸ");
			return mybatis.selectOne("MemberDAO.findMember", id);
			
	}
	
	// id�� pw�� �̿��� �α���
	public MemberVO getMember(MemberVO vo) {
		System.out.println("getMember() ���ó�� : ȸ������ ������");
		
		return mybatis.selectOne("MemberDAO.getMember", vo);		
	}
	
	public void insertMember(MemberVO vo) {
		
		System.out.println("insertMember() ���ó�� : ȸ������");
		
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	public List<MemberVO> getMemberList() {
		System.out.println("getMemberList() ���ó�� : ��� ����Ʈ");
		
		return mybatis.selectList("MemberDAO.getMemberList");
	}
	
	
}
