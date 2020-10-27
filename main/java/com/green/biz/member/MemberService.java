package com.green.biz.member;

import java.util.List;

import com.green.biz.member.dto.MemberVO;

public interface MemberService {
	
	public MemberVO findMember(String id);
	
	public MemberVO getMember(MemberVO vo);
	
	public void insertMember(MemberVO vo);
	
	public List<MemberVO> getMemberList();

}
