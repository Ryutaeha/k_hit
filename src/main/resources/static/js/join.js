

const allAgreement = $("#allAgreement");
const use = $("#useAgreement")
const privacy = $("#privacyAgreement");
const optional = $("#optionalAgreement");
allAgreement.on("change",function(){
    const status = allAgreement.is(":checked");
    use.prop("checked",status);
    privacy.prop("checked",status);
    optional.prop("checked",status);
});

$(".agreeCheck").on("click",function(){
    const agreeCheck = $(".agreeCheck").length;
    const checked = $(".agreeCheck:checked").length;
    if(agreeCheck != checked){
        allAgreement.prop("checked",false);
    }else{
        allAgreement.prop("checked",true);
    }
});

const nextBtn = $(".next-btn");
nextBtn.on("click",function(){
    const useStatus = use.is(":checked");
    const privacyStatus = privacy.is(":checked");
    if(useStatus==false || privacyStatus==false){
        alert("이용약관에 동의해 주세요.")
        event.preventDefault();
    }else{
        $(".agree-content").hide();
        $(".signinfo-content").show();
    }
});

const emailSelect = $(".email-choice");
emailSelect.on("change",function(){
    const selectValue = emailSelect.val();
    const email2 = $("[name=customerEmail2]");
    email2.val(selectValue);
});

$("#sel-re").on("click",function(){
    confirm("[판매자]회원가입이 맞으신가요?")
  
});
$("#cus-re").on("click",function(){
    confirm("[고객]회원가입이 맞으신가요?")    
});

// 판매자 회원가입 이미지 미리보기 ajax
$("[name=imgFile]").on("change",function(){
    if(this.files.length != 0 && this.files[0] !=0){
        const reader = new FileReader();//파일정보를 얻어올 수 있는 객체
        //선택한 파일 정보를 읽어옴(비동기요청)
        reader.readAsDataURL(this.files[0]);
        //파일리더가 정보를 다 읽어오면 동작할 함수
        reader.onload = function(e){
            $("#img-view").attr("src",e.target.result);
        }
    }else{
        $("#img-view").attr("src","");
    }
});