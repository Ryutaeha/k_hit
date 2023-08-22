$(".admin-submenu").on('click',function(){
    if($(this).next().css('display')=='none'){
        $(".sub-choice").slideUp();
        $(this).next().slideDown();
    }else{
        $(this).next().slideUp();
    }
})