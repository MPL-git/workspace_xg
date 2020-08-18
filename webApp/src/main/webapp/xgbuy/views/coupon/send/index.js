var coupon_send = $('.page[data-id="coupon_send"]:visible'),
	coupon_send_body = coupon_send.find('.tab-body');

var couponsendApi = {
	data: {
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		$(function () {
			if (search_url) {
				for (var i = 0; i < search_url.length; i++) {
					var _a = search_url[i].split('='),
						_b = _a[1];

					switch (_a[0]) {
						case 'preferentialInfo': _td.preferentialInfo = _b; break;
						case 'id': _td.id = _b; break;
					}
				}
			}
		});
	},

	draw: function() {
		var _ts = this,
			_td = _ts.data,
			phone = coupon_send.find("input[name='phone']").val();
			
		if (phone) {
			coupon_send_body.empty();
			coupon_send_body.html('即将赠送优惠卷'+ '"' + _td.preferentialInfo + '"' + '给会员' + '"' + phone + '"' + '是否赠送？');
			popupShow('.popup-clear');
		} else {
			delayTip('手机号码不能为空');
		}
	},
	
	send: function() {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.giveMemberCouponBySvip, {
			data: {
				memberId: member.id,
				memberCouponId: _td.id,
				mobile: coupon_send.find("input[name='phone']").val()
			},
			success: function() {
				delayTip('赠送成功');
				closeSelf('.popup-clear');
			},
			error: function(e) {
				delayTip(e.returnMsg);
				closeSelf('.popup-clear');
			}
		});
	}
};

getTpl(function () {
	couponsendApi.init();
});