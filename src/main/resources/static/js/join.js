

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

// 로그인창에서 되묻기 컬럼


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


//회원가입 유효성 검사
let idResult = false;
let pwResult = false;
let phoneResult = false;
let nameResult = false;

//아이디중복검사
$(".dup-btn").on("click",function(){
	const customerId = $("#customerId").val();
	const idReg = /^[a-z0-9]{4,12}$/;
	if(idReg.test(customerId)){
		$.ajax({
			url:"/customer/checkId",
			type : "get",
			data : {customerId : customerId},
			success : function(data){
				if(data == "0"){						
					Swal.fire({
						title : "아이디 중복체크",
						text : "[ "+customerId+" ]는 사용 가능한 아이디입니다.",
						icon : "success",
				        confirmButtonColor: '#61677A',
				        confirmButtonText: '확인',
				        
				    });
					$(".checkId").text("* 중복체크 완료.")
					$(".checkId").css("color","#146C94");
					idResult = true;
				}else{
					Swal.fire({
						title : "아이디 중복체크",
						text : "[ "+customerId+" ]는 이미 사용중인 아이디입니다.",
						icon : "error",
						confirmButtonColor: '#61677A',
						confirmButtonText: '확인',
				    });
					$(".checkId").text("* 아이디를 다시 입력해 주세요.")
					$(".checkId").css("color","red");
					idResult = false;
					return false;
				}
			}
		});
	}else{
		Swal.fire({
			title : "아이디 사용 불가능",
			text : "입력값을 다시 확인해 주세요.",
			icon : "error",
	        confirmButtonColor: '#61677A',
		    confirmButtonText: '확인', 
		});
		$(".checkId").text("영어 소문자/숫자로 4~12글자를 입력해 주세요.")
		$(".checkId").css("color","red");
		idResult = false;
	}
});

//비밀번호, 비밀번호 확인
const inputPw = $("#customerPw");
const inputPwRe = $("#customerPwRe");
inputPw.on("change",function(){
    const pwValue = inputPw.val();
    const pwReValue = inputPwRe.val();
    const pwRegArr = [
        /^.{8,16}$/,
        /[A-Z]/,
        /[a-z]/,
        /[0-9]/,
        /[!@#$%]/
    ];
    let count = 0;
    for(let i=0;i<pwRegArr.length;i++){
    	const check = pwRegArr[i].test(pwValue);
    	if(check){
    		count++;
    	}
    }
    if(count == pwRegArr.length){
		$(".checkPw").text("");
		if(pwReValue != ""){
			pwCheck();
		}
	}else{
		$(".checkPw").text("* 영어 대/소문자 /숫자 /특수문자(!,@,#,$,%)를 포함하여 8~16글자");
    	$(".checkPw").css("color","red");
    	$(".checkPwRe").text("");
    	$(".checkPwRe").css("color","red");
    	pwResult = false;
	}
});
inputPwRe.on("change",function(){
    const pwValue = inputPw.val();
    const pwReValue = inputPwRe.val();
    const pwRegArr = [
        /^.{8,16}$/,
        /[A-Z]/,
        /[a-z]/,
        /[0-9]/,
        /[!@#$%]/
    ];
    let count = 0;
    for(let i=0;i<pwRegArr.length;i++){
    	const check = pwRegArr[i].test(pwValue);
    	if(check){
    		count++;
    	}
    }
    if(pwValue==""&&pwReValue==""){
        pwBlank();
    }else if(pwValue==""){
    	$(".checkPwRe").text("* 비밀번호를 먼저 입력해 주세요.");
    	$(".checkPwRe").css("color","red");
    	pwResult = false;

    }else if(count == pwRegArr.length){
    	$(".checkPw").text("");
        pwCheck();
    }
});
function pwCheck(){
	const pwValue = inputPw.val();
    const pwReValue = inputPwRe.val();
    if(pwValue == pwReValue){
    	$(".checkPw").text("");
    	$(".checkPwRe").text("* 비밀번호가 일치합니다.");
    	$(".checkPwRe").css("color","#146C94");
    	pwResult = true;
    }else{
    	$(".checkPw").text("");
    	$(".checkPwRe").text("* 비밀번호가 일치하지 않습니다.");
    	$(".checkPwRe").css("color","red");
    	pwResult = false;

    }
};
function pwBlank(){
	$(".checkPwRe").text("");
};

//이름
const inputName = $("#customerName");
inputName.on("change",function(){
	const nameValue = inputName.val();
	const nameReg = /^[가-힣]{1,}$/;
	if(nameReg.test(nameValue)){
		$(".checkName").text("");
    	nameResult = true;
	}else{
		$(".checkName").text("* 최소 1글자 이상 / 한글만 입력해 주세요.");
    	$(".checkName").css("color","red");
    	nameResult = false;

	}
});

//전화번호
const inputPhone = $("#customerPhone");
inputPhone.on("change",function(){
	const phoneValue = inputPhone.val();
	const phoneReg = /^[0-9]{11}$/;
	if(phoneReg.test(phoneValue)){
		$(".checkPhone").text("");
    	phoneResult = true;
	}else{
		$(".checkPhone").text("*  '-' 를 제외한 번호(10자리)만 입력해주세요");
    	$(".checkPhone").css("color","red");
    	phoneResult = false;

	}
});

//회원가입

$(".signupBtn").on("click",function(event){
	const emailValue1 = $("#customerEmail").val();
	const emailValue2 = $("#customerEmail2").val();
	
	if(pwResult && phoneResult && nameResult && emailValue1!="" && emailValue2!=""){
		if(!idResult){
			Swal.fire({
						title : "회원가입",
						text : "아이디 중복을 확인해 주세요.",
						icon : "error",
						confirmButtonColor: '#61677A',
						confirmButtonText: '확인',
				    });
			event.preventDefault();	
		}
	}else{
	
		Swal.fire({
						title : "회원가입",
						text : "입력값을 확인해 주세요.",
						icon : "error",
						confirmButtonColor: '#61677A',
						confirmButtonText: '확인',
				    });
		event.preventDefault();		
	}
});