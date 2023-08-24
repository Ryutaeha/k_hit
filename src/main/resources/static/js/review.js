
const stars = $(".star-wrap1>span");
stars.on("click",function(){
    stars.css("color","lightgray");
    const index = stars.index(this);
    const starPoint = index+1;
    for(let i=0;i<starPoint;i++){
        stars.eq(i).css("color","gold");
    }
    $("#star-result").val(starPoint);
});