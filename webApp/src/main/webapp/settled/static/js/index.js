require.config({
	paths: {
		'jquery': 'jquery-3.2.1.min',
		'events': 'jquery.mobile-events.min',
		'swiper': 'swiper.min',
	}
});

require(['jquery'], function ($, np) {
	require(['swiper'], function () {
		var card_swiper = new Swiper('.swiper-container', {
			direction: 'vertical',
			onInit: slideActive,
			onTransitionEnd: slideActive
		});

		require(['events'], function () {
			$('.download').tap(function () {
				location.href = isiOS ? 'http://itunes.apple.com/us/app/id1250285750' : 'http://xgbuy.cc/app/xgbuyMM.apk';
			});
		});
	});
});

function slideActive() {
	var _a = $('.swiper-container').find('.swiper-slide-active');
	_a.addClass('active').siblings().removeClass('active');
}