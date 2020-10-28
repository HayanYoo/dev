/**
 * 
 */


function go_next() {
	// '동의함' 라디오 버튼이 체크되어 있는지 확인
	if(document.formm.okon1[0].checked == true) {
		document.formm.action="join_form";
		document.formm.submit();
	} else if (document.formm.okon1[1].checked == true) {
		alert("약관에 동의하셔야 합니다.")
	}
	
}

// 아이디 중복체크 화면을 표시
function idcheck() {
	if (document.formm.id.value == "") {
		alert("아이디를 입력해 주세요!");
		document.formm.id.focus();
		return;
	}
	
	var url = "id_check_form?id=" + document.formm.id.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable = no, width=450, height=230");
}

// 회원 가입 항목 필수 입력 확인
function go_save() {

	
	if (document.formm.id.value == "") {
		alert("아이디를 입력해주세요");
		document.formm.id.focus();
	} else if (document.formm.reid.value == "") {
		alert("아이디 중복 확인을 실행해주세요");
		document.formm.id.focus();
	} else if (document.formm.pwd.value==""){
		alert ("비밀번호를 입력해주세요");
		document.formm.pwd.focus();
	} else if (document.formm.pwd.value != document.formm.pwdCheck.value) {
		alert("비밀번호가 일치하지 않습니다!");
		document.formm.pwd.focus();
	} else if (document.formm.name.value=="") {
		alert("이름을 입력해주세요");
		document.formm.name.focus();
	} else if (document.formm.email.value=="") {
		alert("이메일을 입력해주세요");
		document.formm.email.focus();
	} else {
		document.formm.action = "join";
		document.formm.submit();
	}
}



function post_zip() {
	var url = "find_zip_num";
	
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable = no, width=600, height=350");
}




