package com.green.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.member.AddressVO;
import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	// ��� ȭ�� ǥ��
	@RequestMapping(value="/contract", method=RequestMethod.GET)
	public String contractView() {

		return "member/contract";
	}
	
	// ȸ������ ȭ�� ǥ��
	@RequestMapping(value="/join_form", method=RequestMethod.POST)
	public String joinView() {
			
		return "member/join";
	}
	
	// ���̵� �ߺ� üũ ȭ�� ǥ��
	@RequestMapping(value="/id_check_form", method=RequestMethod.GET)
	public String idCheckView(@RequestParam(value="id", defaultValue="", required=false) String id, Model model) {
		
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	// �ߺ��� ���̵����� member ���̺��� ��ȸ�Ͽ� Ȯ��
	@RequestMapping(value="/id_check_form", method=RequestMethod.POST)
	public String idCheckAction(@RequestParam(value="id", defaultValue="", required=false) String id, Model model) {
		
		MemberVO user = memberService.findMember(id);
		if (user != null) {	// ����ڰ� �̹� ����
			model.addAttribute("message", 1);
		} else model.addAttribute("message", -1);
	
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinAction(@RequestParam(value="addr1") String addr1,
							 @RequestParam(value="addr2") String addr2,
							 MemberVO vo) {
		vo.setAddress(addr1 + " " + addr2);
		memberService.insertMember(vo);

		return "member/login";
	}
	
	// �ּ�ã�� ȭ�� ǥ��
	@RequestMapping(value="/find_zip_num", method=RequestMethod.GET)
	public String findZipNumView() {
		return "member/findZipNum";
	}
	
	// ���̸����� �ּҸ� ��ȸ�Ͽ� �ּ�ã�� ȭ�鿡 ǥ��
	@RequestMapping(value="/find_zip_num", method=RequestMethod.POST)
	public String findZipNumAction(AddressVO vo, Model model) {
			
		List<AddressVO> addrList = memberService.selectAddressByDong(vo);
		model.addAttribute("addressList", addrList);
		
		return "member/findZipNum";
	}
}
