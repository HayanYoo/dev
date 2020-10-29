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
	
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable = no, width=550, height=350");
}


// 아이디, 비밀번호 찾기 화면 윈도우 생성
function find_id_form() {
	var url = "find_id_form";
	
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=no, resizable = no, width=400, height=350");

}

function findMemberId() {
	if (document.findId.name.value=="") {
		alert("이름을 입력해주세요!");
		document.findId.name.focus();
	} else if (document.findId.email.value=="") {
		alert("이메일을 입력해주세요!");
		document.findId.email.focus();
	} else {
		document.findId.action="find_id";
		document.findId.submit();
	}
}

function findPassword(){
	if (document.findPW.id.value ==""){
		alert("이름을 입력해주세요!");
		document.findPW.id.focus();
	}else if (document.findPW.name.value=="") {
		alert("이름을 입력해주세요!");
		document.findPW.name.focus();
	} else if (document.findPW.email.value=="") {
		alert("이메일을 입력해주세요!");
		document.findPW.email.focus();
	} else {
		document.findPW.action="find_pw";
		document.findPW.submit();
	}
	
}


function changePassword() {
	if (document.formm.pwd.value==""){
		alert ("비밀번호를 입력해주세요");
		document.formm.pwd.focus();
	} else if (document.formm.pwd.value != document.formm.pwdCheck.value) {
		alert("비밀번호가 일치하지 않습니다!");
		document.formm.pwd.focus();
	} else {	
		document.formm.action = "change_password";
		document.formm.submit();	
	}
	

}
