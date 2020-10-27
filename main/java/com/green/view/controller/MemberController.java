package com.green.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.member.MemberService;
import com.green.biz.member.dto.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/contract")
	public String contractView() {

		return "member/contract";
	}
	
	@RequestMapping(value="/join_form", method=RequestMethod.POST)
	public String joinView() {
			
		return "member/join";
	}
	
	@RequestMapping(value="/id_check_form", method=RequestMethod.POST)
	public String idCheckView(MemberVO vo) {
		memberService.getMember(vo);	
		return "member/idcheck";
	}

}
