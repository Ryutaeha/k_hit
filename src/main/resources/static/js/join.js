

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

$(".dup-btn").on("click",function(){
    const customerId = $("#cutomerId").val();
    const idReg = /^[a-zA-Z0-9]{4,15}$/;
    if(idReg.test(customerId)){
        $.ajax({
					url:"/customer/checkId",
					type : "get",
					data : {customerId : customerId},
					success : function(data){
						if(data == "0"){
							$(".checkId").text("* 중복체크 완료.")
							$(".checkId").css("color","#146C94");
						}else{
							$(".checkId").text("* 아이디를 다시 입력해 주세요.")
							$(".checkId").css("color","red");
						}
					}
				});
    }else{
    	$(".checkId").text("영어/숫자로 4~15글자를 입력해 주세요.")
    	$(".checkId").css("color","red");
    }
});

