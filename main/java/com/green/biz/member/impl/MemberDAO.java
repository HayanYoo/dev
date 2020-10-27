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
	
	
	// id가 존재하는지 확인
		public MemberVO findMember(String id) {
			System.out.println("findMember() 기능처리 : 회원 여부 조회");
			return mybatis.selectOne("MemberDAO.findMember", id);
			
	}
	
	// id와 pw를 이용한 로그인
	public MemberVO getMember(MemberVO vo) {
		System.out.println("getMember() 기능처리 : 회원정보 얻어오기");
		
		return mybatis.selectOne("MemberDAO.getMember", vo);		
	}
	
	public void insertMember(MemberVO vo) {
		
		System.out.println("insertMember() 기능처리 : 회원가입");
		
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	public List<MemberVO> getMemberList() {
		System.out.println("getMemberList() 기능처리 : 멤버 리스트");
		
		return mybatis.selectList("MemberDAO.getMemberList");
	}
	
	
}
