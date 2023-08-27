var swiper = new Swiper(".mySwiper", {
  spaceBetween: 30,
  effect:"fade",
  loopAdditionalSlides: 1,
  autoplay: {
    delay: 4000,
    disableOnInteraction: false,
  },
  pagination: {
    el: ".swiper-pagination",
    type: "fraction",
  },
  navigation: {
    nextEl: ".swiper-button-next",
    prevEl: ".swiper-button-prev",
  },
  
});