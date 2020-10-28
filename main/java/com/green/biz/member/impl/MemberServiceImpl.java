package com.green.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.member.AddressVO;
import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;


@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO mDao;
	
	@Override
	public MemberVO findMember(String id) {
		return mDao.findMember(id);
	}
	
	
	@Override
	public MemberVO getMember(MemberVO vo) {
		
		return mDao.getMember(vo);
	}

	@Override
	public void insertMember(MemberVO vo) {
		mDao.insertMember(vo);

	}

	@Override
	public List<MemberVO> getMemberList() {
		return mDao.getMemberList();
	}
	
	@Override
	public List<AddressVO> selectAddressByDong(AddressVO vo) {

		return mDao.selectAddressByDong(vo);
	}



}
