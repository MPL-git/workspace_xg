var ua = navigator.userAgent,
	isAdr = ua.match(/(Android);?[\s\/]+([\d.]+)?/),
	// tryTime = isAdr ? 1000 : 3500,
	parames = '',
	parame = document.location.href.split('?'),
	appUrl = isAdr ? 'xgbuy://xgapp.com/openxg?pageName=' : 'XGApp://com.XGBuy?pageName=';

for (var i = 0; i < parame.length; i++) {
	i && (parames += (i > 1 ? '?' : '') + parame[i]);
}

parames && (appUrl += parames);

var appInstall = {
	open: function() {
		/(iPhone|iPad|iPod|iOS|Android)/i.test(ua) && appInstall.tryOpen();

		// var t = Date.now();
		// setTimeout(function() {
		// 	if (Date.now() - t < tryTime + 100) {
		// 		appInstall.download();
		// 	}
		// }, tryTime);
	},
	tryOpen: function() {
		var iframe = document.createElement('iframe');
		iframe.src = appUrl;
		iframe.style.cssText = 'height: 0; border: none;';
		window.location.href = appUrl;
		document.body.appendChild(iframe);
	},
	download: function() {
		window.location.href = isAdr ? data_load.getAttribute('data-load') : 'http://itunes.apple.com/cn/app/id1250285750';
	}
}

appInstall.open();