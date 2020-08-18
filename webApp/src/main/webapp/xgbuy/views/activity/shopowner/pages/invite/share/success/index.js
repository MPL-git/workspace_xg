var a_invite_success_page = $('.page[data-id="activity_shopowner_pages_invite_share_success"]:visible');

var aInviteSuccessApi = {
	data: {
			
	},

	url: isiOS ? 'XGApp://com.XGBuy?pageName=' : 'xgbuy://xgapp.com/openxg?pageName=',

	init: function () {
		var _ts = this;

		$(function () {
			var _u = real_url.split('pageName=')[1];

			if (_u) {
				_ts.url += _u;
			} else {
				_ts.url += 'homePage';
			}

			(isiOS || isAndroid) && _ts.open();
		});

		shareHand.update();
	},

	open: function () {
		var _i = document.createElement('iframe');
		_i.src = this.url;
		_i.style.cssText = 'height: 0; border: none;';
		document.body.appendChild(_i);
		location.href = this.url;
	},

	down: function () {
		(isWeiXin || isWeibo) ? this.tip() : (location.href = 'http://xgbuy.cc/app/downloadIndex.shtml');
	},

	tip: function () {
		var _tip = $('.page:visible .load-tip');

		if (_tip.length) {
			_tip.addClass('show');
		} else {
			$('.page:visible').append('<div class="popup load-tip" onclick="closeSelf(this)"></div>');
			$('.page:visible .load-tip').addClass('show');
		}
	}
}

aInviteSuccessApi.init();