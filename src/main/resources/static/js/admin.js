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
	$(".pModal>table").children().children().children('td').empty();
	$(".pModal").children('form').remove();
	pModal($(this).children().eq(0).text());
	pModalO($(this).children().eq(0).text());	
    $(".modal-inpo").css("display","none");
    $(".pModal").css("display","block");
	$(".modal-wrap").css("display","flex");

})

function pModal(pNo){

	$.ajax({
		url : "/admin/pContent",
		type : "post",
		data : {pNo : pNo},
		dataType : "json",
		success: function(data){
			console.log(data)
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
	   			var checkP = $("<form action=/admin/productCheckChange method=post></form>");
	   			var select = $("<select name=productCheck></select>");
	   			select.attr("id","pCheck");
	   			select.append($("<option value=1>승인</option>"));
	   			select.append($("<option value=2>검수</option>"));
	   			select.append($("<option value=3>반려</option>"));
	   			checkP.append(select);
	   			checkP.append("<input type=submit value=변경 class = pCheckChange>");
	   			checkP.append("<input type=hidden value="+data[0].productNo+" class = pNo name=productNo>");
	   			$(".pModal").append(checkP);
	   			$("#pCheck").val(data[0].productCheck);
	   			
	    	}
	    }
    })

}
function pModalO(pNo){
	$(".pModal>table").children().children().children('td').empty();
	$(".pModal").children('form').remove();
	$.ajax({
		url : "/admin/pContentO",
		type : "post",
		data : {pNo : pNo},
		dataType : "json",
		success: function(data){
			console.log(data)
			if(data.length==0){
				$(".mProductO").append($("<h2>").append("옵션이 없습니다"));
			}else{
				for(var i =0;i<data.length;i++){
					const div = $("<div style= 'padding: 10px 0; border: 1px solid black'>");
					div.append($("<span style='width: 35%; display: inline-block;'>").append(data[i].optionColor));
					div.append($("<span style='width: 35%; display: inline-block;'>").append(data[i].optionSize));
					div.append($("<span style='width: 15%; display: inline-block;'>").append(data[i].optionStock));
						if(data[i].outOfStock==0){
							div.append($("<span style='width: 15%; display: inline-block;'>").append('판매'));
						}else{
							div.append($("<span style='width: 15%; display: inline-block;'>").append('중지'));
						}
					$(".mProductO").append(div);
				}
	    	}
	    }
	    
    })
}

$(".sMenu").on('click',function(){
	$(".sModal>table").children().children().children('td').empty();
	sModal($(this).text());
	sModalP($(this).text());	
	$(".modal-inpo").css("display","none");
    $(".sModal").css("display","block");
	$(".modal-wrap").css("display","flex");	
    event.stopPropagation();
})

$(".sMenuSel").on('click',function(){
	$(".sModal>table").children().children().children('td').empty();
	sModal($(this).children().eq(1).text());
	sModalP($(this).children().eq(1).text());
	$(".modal-inpo").css("display","none");
    $(".sModal").css("display","block");
	$(".modal-wrap").css("display","flex");	
})

function sModal(sId){
	$.ajax({
		url : "/admin/sContent",
		type : "post",
		data : {sId : sId},
		dataType : "json",
		success: function(data){
			$(".mImgS").append($("<span>").append(data.sellerImg));
			$(".mIdS").append($("<span>").append(data.sellerId));
			$(".mRegDateS").append($("<span>").append(data.sellerEnrollDate));
			$(".mEmailS").append($("<span>").append(data.sellerEmail));
			$(".mNameS").append($("<span>").append(data.sellerName));
			$(".mPhoneS").append($("<span>").append(data.sellerPhone));
			$(".mIntroduceS").append($("<span>").append(data.sellerIntroduce));
		}
	})
}

function sModalP(sId){
console.log(sId)
		$.ajax({
		url : "/admin/sContentP",
		type : "post",
		data : {sId : sId},
		dataType : "json",
		success: function(data){
			console.log(data.isEmpty)
			if(data.length==0){
				$(".mProductS").append($("<h2>").append("등록한 상품이 없습니다"));
			}else{
				for(var i =0;i<data.length;i++){
					const div = $("<div style= 'padding: 10px 0; border: 1px solid black'>");
					div.append($("<span style='width: 10%; display: inline-block;'>").append(data[i].productNo));
					div.append($("<span style='width: 60%; display: inline-block;'>").append(data[i].productName));
					div.append($("<span style='width: 30%; display: inline-block;'>").append(data[i].productPrice));
					$(".mProductS").append(div);
				}
			}
		}
	})
}

$(".cMenu").on('click',function(){
	$(".cModal>table").children().children().children('td').empty();	
	cModal($(this).text());
	$(".modal-inpo").css("display","none");
    $(".cModal").css("display","block");
	$(".modal-wrap").css("display","flex");
    event.stopPropagation();
})

$(".cMenuSel").on('click',function(){
	$(".cModal>table").children().children().children('td').empty();
	cModal($(this).children().eq(1).text());
	cModalB($(this).children().eq(1).text());
	$(".modal-inpo").css("display","none");
    $(".cModal").css("display","block");
	$(".modal-wrap").css("display","flex");
})

function cModal(cId){

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
	
}

function cModalB(cId){

	$.ajax({
		url : "/admin/cContentB",
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
	
}

$(".nSelect").on('click',function(){
	nModal($(this).children().eq(0).text());
})

$(document).on('click',".fix",function(){
	const fix = $(this).val();
	const nNo = $(".mNoN").val()
	$.ajax({
		url : "/admin/fix",
		type : "post",
		data : {fix : fix, nNo : nNo},
		dataType : "json",
		success: function(data){
			alert("수정 성공");
			 nModal(nNo);
		}
	})
	/*
	*/
})

function nModal(nNo){
	$(".nModal>table").children().children().children().empty();
	$(".fix").remove()
	$.ajax({
		url : "/admin/nContent",
		type : "post",
		data : {nNo : nNo},
		dataType : "json",
		success: function(data){
			console.log(data)
			$(".mTitleN").append($("<h2>").append(data.noticeTitle));
			$(".mDateN").append($("<h3>").append(data.noticeDate));
			$(".mContentN").append($("<h3>").append(data.noticeContent));
			if(data.noticeFix==0){
				$(".modifyNoticeFrm").append($("<button value=1 class = fix>").append("고정"));
			}else{
				$(".modifyNoticeFrm").append($("<button value=0 class = fix>").append("고정해제"));
			}
		}
	})
	$(".modal-inpo").css("display","none");
    $(".nModal").css("display","block");
	$(".modal-wrap").css("display","flex");
}

$(".modifyNoticeFrm>button:first-child").on('click',function(){
	$(".modifyNoticeFrm").css('display','none');	
	$(".modifyNotice").css('display','block');
	
})

$(".qMenuSel").on('click',function(){
	qModal($(this).children().eq(0).text());
})

function qModal(qNo){
	$(".qModal>table").children().children().children().empty();
	$(".qnaAnswer").empty()
	console.log(qNo)
	$.ajax({
		url : "/admin/qContent",
		type : "post",
		data : {qNo : qNo},
		dataType : "json",
		success: function(data){
			console.log(data)
			/*
			*/
			$(".mTitleQ").append($("<h2>").append(data[0].questionContent));
			$(".mDateQ").append($("<h3>").append(data[0].questionDate));
			if(data.questionSellWriter==null){
			$(".mWriterQ").append($("<h3>").append(data[0].questionCusWriter));
			}
			if(data.questionCusWriter==null){
			$(".mWriterQ").append($("<h3>").append(data[0].questionSellWriter));
			}
			$(".mContentQ").append($("<h3>").append(data[0].questionContent));
			$(".qnaAnswer").append($("<input type = text name = qnaAnswerComment>"));
			$(".qnaAnswer").append($("<input type = hidden name = qnaNo value= "+data[0].questionNo+">"));
			$(".qnaAnswer").append($("<input type = submit value= 작성>"));
			}
		})
	$(".modal-inpo").css("display","none");
    $(".qModal").css("display","block");
	$(".modal-wrap").css("display","flex");
}
	