var isIos = navigator.userAgent.indexOf('iPhone') > -1,
	_download = document.querySelectorAll('.download');

if (isIos) {
	for (var i = 0; i < _download.length; i++) {
		_download[i].innerHTML = '打开商城';
	}
}

document.querySelector('.swiper-container') && new Swiper('.swiper-container', {
	loop: true,
	slidesPerView: 2,
	pagination: {
		el: '.pagination'
	}
});

(function() {
	var hm = document.createElement('script');
	hm.src = 'https://hm.baidu.com/hm.js?6d2fd36d9cf86bab33982f624c157f83';
	document.body.appendChild(hm);
})();

function spreadDownload(e, p) {
	if (navigator.userAgent.indexOf('iPhone') > -1) {
		location.href = p ? p : 'http://itunes.apple.com/cn/app/id1250285750';
	} else if (e) {
		location.href = e;
	}
}