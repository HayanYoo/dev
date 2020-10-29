/**
 * 장바구니에 담기
 */

function go_cart() {
	if(document.formm.quantity.value == "" || document.formm.quantity.value == 0) {
		alert("수량을 입력해주세요!");
		document.formm.quantity.focus();
	} else {
		document.formm.action="cart_insert";
		document.formm.submit();
	}
}
