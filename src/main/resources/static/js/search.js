/*
$(".main-menu").children().children().hover(function(){
    $(".sub-menu").css("display","block")
});
*/

//검색창
$(".search").click(function() {
    $(".search-box").slideToggle();
});
$(".clear").click(function(){
    $(".search-box").slideUp();
});

