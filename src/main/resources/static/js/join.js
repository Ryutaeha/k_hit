

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


const emailSelect = $(".email-choice");
emailSelect.on("change",function(){
    const selectValue = emailSelect.val();
    const email2 = $("[name=customerEmail2]");
    email2.val(selectValue);
});