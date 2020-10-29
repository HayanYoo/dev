package com.green.view.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.green.biz.member.AddressVO;
import com.green.biz.member.MemberService;
import com.green.biz.member.MemberVO;

@Controller
@SessionAttributes("loginUser")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	//로그인 화면 표시
	@RequestMapping(value="/login_form" , method=RequestMethod.GET)
	public String loginView() {
		
		return "member/login";
	}
	
	// 일반사용자 로그인 처리
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginAction(MemberVO vo, Model model) {
		
		MemberVO loginUser = memberService.getMember(vo);
		
		if(loginUser != null) { 	// 사용자가 존재
			model.addAttribute("loginUser", loginUser);	// request 영역에 사용자 정보 저장
			
			return "redirect:/index";
		} else {
			return "member/login_fail";
		}
	}
	
	
	// 로그아웃 처리
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logoutAction(SessionStatus status) {
		status.setComplete();
		
		return "redirect:/login_form";
	}
	
	
	// 약관 화면 표시
	@RequestMapping(value="/contract", method=RequestMethod.GET)
	public String contractView() {

		return "member/contract";
	}
	
	// 회원가입 화면 표시
	@RequestMapping(value="/join_form", method=RequestMethod.POST)
	public String joinView() {
			
		return "member/join";
	}
	
	// 로그인 화면의 "Join us" 서브메뉴 처리
		@RequestMapping(value="/join_form", method=RequestMethod.GET)
		public String joinForm() {
				
			return "member/contract";
		}
	
	// 아이디 중복 체크 화면 표시
	@RequestMapping(value="/id_check_form", method=RequestMethod.GET)
	public String idCheckView(@RequestParam(value="id", defaultValue="", required=false) String id, Model model) {
		
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	// 중복된 아이디인지 member 테이블을 조회하여 확인
	@RequestMapping(value="/id_check_form", method=RequestMethod.POST)
	public String idCheckAction(@RequestParam(value="id", defaultValue="", required=false) String id, Model model) {
		
		MemberVO user = memberService.findMember(id);
		if (user != null) {	// 사용자가 이미 존재
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
	
	// 주소찾기 화면 표시
	@RequestMapping(value="/find_zip_num", method=RequestMethod.GET)
	public String findZipNumView() {
		return "member/findZipNum";
	}
	
	// 동이름으로 주소를 조회하여 주소찾기 화면에 표시
	@RequestMapping(value="/find_zip_num", method=RequestMethod.POST)
	public String findZipNumAction(AddressVO vo, Model model) {
			
		List<AddressVO> addrList = memberService.selectAddressByDong(vo);
		model.addAttribute("addressList", addrList);
		
		return "member/findZipNum";
	}
	
	// 아이디, 비밀번호 찾기 화면 표시
	@RequestMapping(value="find_id_form", method=RequestMethod.GET)
	public String findIdView() {
		return "member/findIdAndPassword";
	}
	
	@RequestMapping(value="find_id", method = RequestMethod.GET)
	public String findIdAction(MemberVO vo, Model model) {
		MemberVO member = memberService.getMemberByNameAndEmail(vo);
		
		if (member != null) {		// 아이디가 존재하는 경우
			model.addAttribute("message", "1");
			model.addAttribute("id", member.getId());
		} else {
			model.addAttribute("message", "-1");
			
		}
		
		return "member/findResult";
	}
	
	@RequestMapping(value="find_pw", method = RequestMethod.GET)
	public String findPwAction(MemberVO vo, Model model) {
		MemberVO member = memberService.getMemberByIdAndNameAndEmail(vo);
		model.addAttribute("id", vo.getId());
		if (member != null) {		// 아이디가 존재하는 경우
			model.addAttribute("message", "1");
			
		} else {
			model.addAttribute("message", "-1");
			
		}
		
		return "member/findPwdResult";
	}
	
	@RequestMapping(value="change_password", method = RequestMethod.POST)
	public String changepassword(MemberVO vo) {
		
		memberService.changePassword(vo);
		System.out.println("암호가 변경되었습니다.");
		
		
		return "member/close";
	}
}
