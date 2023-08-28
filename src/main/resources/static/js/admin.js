$.urlParam = function(name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null) {
        return null;
    } else {
        return results[1] || 0;
    }
}
$(function(){
	$("#categoryNo").val($.urlParam('categoryNo'));
	
	const urlCheck = $(location).attr('pathname').split('/')[2];
	
	if(urlCheck=='member'||urlCheck=='banner'){
		$(".sub-choice").eq(0).css('display','block');
	}else if(urlCheck=='product'){
		$(".sub-choice").eq(1).css('display','block');
	}else if(urlCheck=='sales'||urlCheck=='income'||urlCheck=='consumption'){
		$(".sub-choice").eq(2).css('display','block');
	}else if(urlCheck=='noticeFrm'||urlCheck=='notice'){
		$(".sub-choice").eq(3).css('display','block');
	}else if(urlCheck=='question'){
		$(".sub-choice").eq(4).css('display','block');
	}
})


$(".admin-submenu").on('click',function(){
    if($(this).next().css('display')=='none'){
        $(".sub-choice").slideUp();
        $(this).next().slideDown();
    }else{
        $(this).next().slideUp();
    }
});

$(".delModal").on('click',function(){
	$(".modal-wrap").css("display","none");
})
$(".pSelect").on('click',function(){
	pModal($(this).children().eq(0).text());
})

function pModal(pNo){
	$(".pModal>table").children().children().children().empty();
	$(".pModal").children('div').remove();
	$.ajax({
		url : "/admin/pContent",
		type : "post",
		data : {pNo : pNo},
		dataType : "json",
		success: function(data){
			$(".mImgP").append($("<h2>").append(data[0].productImg));
			$(".mNameP").append($("<h2>").append(data[0].productName));
			$(".mSellerIdP").append($("<h3>").append(data[0].sellerId));
			$(".mRegDateP").append($("<h4>").append(data[0].productRegDate));
			$(".mPriceP").append($("<h2>").append(data[0].productPrice));
			$(".mContentP").append($("<h3>").append(data[0].productContent));
			$(".mContentDetailP").append($("<h3>").append(data[0].productContentDetails));
	    	if(data[0].productCheck==4){
	   			$(".pModal").append($("<div></div>").append($("<h2>").append("현재 판매가 중지 된 상품입니다")));
	   		}else{
	   			var checkP = $("<div>");
	   			var select = $("<select></select>");
	   			select.attr("id","pCheck");
	   			select.append($("<option value=1>승인</option>"));
	   			select.append($("<option value=2>검수</option>"));
	   			select.append($("<option value=3>반려</option>"));
	   			checkP.append(select);
	   			checkP.append("<input type=button value=변경 class = pCheckChange>");
	   			$(".pModal").append(checkP);
	    	}
	    }
    })
    $(".modal-inpo").css("display","none");
    $(".pModal").css("display","block");
	$(".modal-wrap").css("display","flex");
}


$(".sMenu").on('click',function(){
	sModal($(this).text());
    event.stopPropagation();
})

$(".sMenuSel").on('click',function(){
	sModal($(this).children().eq(1).text());
})

function sModal(sId){
	$(".sModal>table").children().children().children().empty();
	$.ajax({
		url : "/admin/sContent",
		type : "post",
		data : {sId : sId},
		dataType : "json",
		success: function(data){
			$(".mImgS").append($("<h2>").append(data.sellerImg));
			$(".mIdS").append($("<h2>").append(data.sellerId));
			$(".mRegDateS").append($("<h3>").append(data.sellerEnrollDate));
			$(".mEmailS").append($("<h4>").append(data.sellerEmail));
			$(".mNameS").append($("<h2>").append(data.sellerName));
			$(".mPhoneS").append($("<h3>").append(data.sellerPhone));
			$(".mIntroduceS").append($("<h3>").append(data.sellerIntroduce));
		}
	})
	$(".modal-inpo").css("display","none");
    $(".sModal").css("display","block");
	$(".modal-wrap").css("display","flex");
}

$(".cMenuSel").on('click',function(){
	cModal($(this).children().eq(1).text());
})

function cModal(cId){
	$(".cModal>table").children().children().children().empty();
	$.ajax({
		url : "/admin/cContent",
		type : "post",
		data : {cId : cId},
		dataType : "json",
		success: function(data){
			console.log(data)
			$(".mIdC").append($("<h2>").append(data.customerId));
			$(".mNameC").append($("<h2>").append(data.customerName));
			$(".mRegDateC").append($("<h3>").append(data.customerEnrolldate));
			$(".mEmailC").append($("<h4>").append(data.customerEmail));
			$(".mPhoneC").append($("<h3>").append(data.customerPhone));
		}
	})
	$(".modal-inpo").css("display","none");
    $(".cModal").css("display","block");
	$(".modal-wrap").css("display","flex");
}

$(document).on('click',".pCheckChange",function(){
	console.log($("#pCheck").val());
})