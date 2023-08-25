var swiper = new Swiper(".mySwiper", {
    spaceBetween: 30,    // 슬라이드 사이 여백

    slidesPerView : 'auto', // 한 슬라이드에 보여줄 갯수
    centeredSlides: true,    //센터모드
    autoplay: {     //자동슬라이드 (false-비활성화)
      delay: 2500, // 시간 설정
      disableOnInteraction: false, // false-스와이프 후 자동 재생
    },

    loop : false,   // 슬라이드 반복 여부

    loopAdditionalSlides : 1,

// 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
    pagination: { // 호출(pager) 여부
      el: ".swiper-pagination", //버튼을 담을 태그 설정
      clickable: true, // 버튼 클릭 여부
    },
    navigation: {   // 버튼
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
  });

  // 매개 변수로 사용시 swiper가 초기화 될 때 동작

var mySwiper = new Swiper('.swiper-container', {

    on : {
  
      init : function () {
  
        console.log('swiper 초기화 될때 실행');
  
      },
  
      imagesReady : function () { // 모든 내부 이미지 로드후 이벤트 시작
  
        console.log('슬라이드 이미지 로드 후 실행');
  
      },
  
    },
  
});
  
  // swiper가 초기화 된 후 동작
  
  var mySwiper = new Swiper('.swiper-container', {
  
    // ...
  
  });
  
  mySwiper.on('init', function () {
  
    console.log('slide가 초기화 설정을 마친 후 실행');
  
  });