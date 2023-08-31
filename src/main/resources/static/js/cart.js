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


$("#updateDeliverFrm").on("click",function(){
	$(".searchBtn").show();
	$(".new-deliver-info input").prop("readonly",false);
	$(this).hide();
	$(this).next().show();
	$(".updateInputTitle").text("배송지 수정");
});


function updateDeliver(obj, customerNo){
	const addressName = $("#updateName").val();
	const addressPhone = $("#updatePhone").val();
	const addressPostalCode = $("#updatePostalCode").val();
	const addressSimple = $("#updateAddress").val();
	const addressDetail = $("#updateDetail").val();
	$.ajax({
		url : "/customer/updateDeliver",
		type : "post",
		data : {customerNo : customerNo, addressName : addressName, addressPhone : addressPhone, addressPostalCode : addressPostalCode, addressSimple : addressSimple, addressDetail : addressDetail},
		success : function(data){
			$(".searchBtn").hide();
			$(".new-deliver-info input").prop("readonly",true);
			$(obj).hide();
			$(obj).prev().show();
			$(".updateInputTitle").text("기본배송지");
		}
	});
}

$("#buyBtn").on("click",function(){
	$("#cartForm").submit();	
	/*
	const priceStr = $("#paymentPrice").text();
	const price = Number(priceStr);
	const d = new Date();
	const date = d.getFullYear()+""+(d.getMonth()+1)+""+d.getDate()
				+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
	const customerName = $("input[name=addressName]").val();
	const customerPhone = $("input[name=addressPhone]").val();
	const address = $("input[name=addressSimple]").val();
	const post = $("input[name=addressPostalCode]").val();
	
	IMP.init("imp57311522");
	
	IMP.request_pay({
		pg : "html5_inicis",
		pay_method : "card",	
		name : "KHIT",
		amount : price,
		buyer_name : customerName,
		buyer_tel : customerPhone,
		buyer_addr : address,
		buyer_post : post
	},function(rsp){
		alert(rsp);
		if(rsp.success){
			$("#cartForm").submit();	
		}else{
			Swal.fire({
						title : "결제 실패",
						text : "결제 실패하였습니다. 다시 시도해 주세요.",
						icon : "error",
				        confirmButtonColor: '#61677A',
				        confirmButtonText: '확인',
				        
				    });	
		}
		
	});
	*/
	
});