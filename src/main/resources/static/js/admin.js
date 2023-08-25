$(".admin-submenu").on('click',function(){
    if($(this).next().css('display')=='none'){
        $(".sub-choice").slideUp();
        $(this).next().slideDown();
    }else{
        $(this).next().slideUp();
    }
});

$(".pSelect").on('click',function(){
	console.log("히히");
})

$(".sMenu").on('click',function(){
	console.log("zlzl");
    event.stopPropagation();
})