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
})





$(".admin-submenu").on('click',function(){
    if($(this).next().css('display')=='none'){
        $(".sub-choice").slideUp();
        $(this).next().slideDown();
    }else{
        $(this).next().slideUp();
    }
});

$(".pSelect").on('click',function(){
	const pNo= $(this).children().eq(0).text();
	$(".modal-tbl").children().children().children().empty();
	$(".modal-inpo").children('div').remove();
	$.ajax({
		url : "/admin/pContent",
		type : "post",
		data : {pNo : pNo},
		dataType : "json",
		success: function(data){
			$(".mImg").append($("<h2>").append(data[0].productImg));
			$(".mName").append($("<h2>").append(data[0].productName));
			$(".mSellerId").append($("<h3>").append(data[0].sellerId));
			$(".mRegDate").append($("<h4>").append(data[0].productRegDate));
			$(".mPrice").append($("<h2>").append(data[0].productPrice));
			$(".mContent").append($("<h3>").append(data[0].productContent));
			$(".mContentDetail").append($("<h3>").append(data[0].productContentDetails));
	    	if(data[0].productCheck==4){
	   			$(".modal-inpo").append($("<div></div>").append($("<h2>").append("현재 판매가 중지 된 상품입니다")));
	   		}else{
	   			var checkP = $("<div>");
	   			var select = $("<select></select>");
	   			select.attr("id","pCheck");
	   			select.append($("<option value=1>승인</option>"));
	   			select.append($("<option value=2>검수</option>"));
	   			select.append($("<option value=3>반려</option>"));
	   			checkP.append(select);
	   			checkP.append("<input type=button value=변경 class = pCheckChange>");
	   			$(".modal-inpo").append(checkP);
	    	}
	    }
    })
	$(".modal-wrap").css("display","flex");
})
$(document).on('click',".pCheckChange",function(){
	console.log($("#pCheck").val());
})

$(".delModal").on('click',function(){
	$(".modal-wrap").css("display","none");
})
$(".sMenu").on('click',function(){
	console.log($(this).text());
    event.stopPropagation();
})