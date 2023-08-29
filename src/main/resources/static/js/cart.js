const allCheck = $(".allCheck");
const use = $(".check");
//전체 선택
allCheck.on("change", function () {
    const status = allCheck.is(":checked");
    console.log("dddd");
    use.prop("checked", status);
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
    
});


//쇼핑계속하기
$(".keepShopping").on("click",function() {
	history.back();
});

//계속구매하기
$(".payment").on("click",function() {

});