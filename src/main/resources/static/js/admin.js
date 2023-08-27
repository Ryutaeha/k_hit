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
	console.log($(".modal-tbl").children().children().children().empty());
	$.ajax({
		url : "/admin/pContent",
		type : "post",
		data : {pNo : pNo},
		dataType : "json",
		success: function(){
			console.log(pNo);
	    }
    })
	$(".modal-wrap").css("display","flex");
})


$(".delModal").on('click',function(){
	$(".modal-wrap").css("display","none");
})
$(".sMenu").on('click',function(){
	console.log($(this).text());
    event.stopPropagation();
})