const allCheck = $(".allCheck");
const use = $(".check");
//전체 선택
allCheck.on("change", function () {
    const status = allCheck.is(":checked");
    use.prop("checked", status);
    calcTotalPrice();
});

//선택
$(".check").on("click",function(){
	const check = $(".check").length;
    const checked = $(".check:checked").length;
    if(check != checked){
        allCheck.prop("checked",false);
    } else{
        allCheck.prop("checked",true);
    }
	calcTotalPrice();
});

function calcTotalPrice(){
	const checks = $(".check:checked");
	let totalPrice = 0;
	for(let i=0; i<checks.length;i++){
		const check = checks.eq(i);
		const priceStr = check.parent().next().next().next().text();
		const price = parseInt(priceStr.slice(0, -1));
		totalPrice += price;
	}
	$(".paymentPrice").text(totalPrice);
	
}

//쇼핑계속하기
$(".keepShopping").on("click",function() {
	history.back();
});

//계속구매하기
$(".payment").on("click",function() {

});

function cartDelete(obj, cartNo){
	const deleteBtn = $(obj);
	$.ajax({
		url : "/customer/cartDelete",
		type : "post",
		data : {cartNo : cartNo},
		success : function(data){
			deleteBtn.parent().parent().hide();		
		}
	});
}

function searchAddress(){
	
	new daum.Postcode({
		oncomplete: function(data){

			$("#postalCode").val(data.zonecode);
			$("#address").val(data.roadAddress);
			$("#detail").focus();
		}
	}).open();
}

function inputDeliver(obj, customerNo){
	const addressName = $("#name").val();
	const addressPhone = $("#phone").val();
	const addressPostalCode = $("#postalCode").val();
	const addressSimple = $("#address").val();
	const addressDetail = $("#detail").val();
	console.log(addressName);
	$.ajax({
		url : "/customer/inputDeliver",
		type : "post",
		data : {customerNo : customerNo, addressName : addressName, addressPhone : addressPhone, addressPostalCode : addressPostalCode, addressSimple : addressSimple, addressDetail : addressDetail},
		success : function(data){
			
		}
	});
}
