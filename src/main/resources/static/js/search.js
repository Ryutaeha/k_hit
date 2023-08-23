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

//회원가입 모달
const modal = $("#join-modalWrap")
const closeBtn = $(".modal-close>span");
const customerBtn = $("#customer-btn");
const sellerBtn = $("#seller-btn");
const join = $(".join");

join.on("click", function(){
	modal.show();
});

closeBtn.on("click", function() {
    modal.hide();
});

customerBtn.on("click", function(){
    location.href = "/customer/join";
});

sellerBtn.on("click", function(){
    location.href = "/seller/join";
});
