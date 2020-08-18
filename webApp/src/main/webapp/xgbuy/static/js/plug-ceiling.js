$.fn.ceiling=function(a,b){!a&&(a=$(window));b=b||0;var position=function(e){var c=e.offset().top + a.scrollTop(),d=e.width(),f=e.css('position'),t=e.css('top');a.scroll(function(){var g=$(this).scrollTop();
if(g>c-b){
    if(window.XMLHttpRequest){
    d = '100%'
    e.css({position:'fixed',top:b-1,width:d}).addClass('fixed')
    $(".page:visible .scroll-decorate>.box-list").css("paddingTop",".8rem")
    e.find(".decorate-header-btn").css("background",e.find(".decorate-header-btn").attr("data-bg"))
    }else{    
    e.css({top:g}).addClass('fixed')
    $(".page:visible .scroll-decorate>.box-list").css("paddingTop",".8rem")
    e.find(".decorate-header-btn").css("background",e.find(".decorate-header-btn").attr("data-bg"))
    }
}else{
e.css({position:f,top:t}).removeClass('fixed')
$(".scroll-decorate>.box-list").css("paddingTop","0")
e.find(".decorate-header-btn").css("background","none")

}})};return $(this).each(function(){position($(this))})};