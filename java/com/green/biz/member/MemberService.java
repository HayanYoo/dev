package com.green.biz.member;

import java.util.List;



public interface MemberService {
	
	public MemberVO findMember(String id);
	
	public MemberVO getMember(MemberVO vo);
	
	public void insertMember(MemberVO vo);
	
	public List<MemberVO> getMemberList();

	public List<AddressVO> selectAddressByDong(AddressVO vo) ;
	
	public MemberVO getMemberByNameAndEmail(MemberVO vo) ;
	
	public MemberVO getMemberByIdAndNameAndEmail(MemberVO vo);
	
	public void changePassword(MemberVO vo);
}
