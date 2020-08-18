var checkin_yichang = $('.page[data-id="activity_newsign_yichang"]:visible'),
	yichang_body = checkin_yichang.find(".main-body");

var checkinYichangApi = {
	data: {
		withdrawCashId: ''
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
						case 'withdrawCashId': _td.withdrawCashId = _b; break;
					}
				}
			}

			requestAppMember(function () {}, function () {
				getApi.addMemberWithdrawCash = getApi.addMemberWithdrawCashH5;
			});
		});

		yichang_body.append(template('checkin_yichang_tpl'));
	},

	submit: function () {
		var _ts = this;
		_td = _ts.data;

		var name = document.getElementById("namepass").value;
		var zhanghao = document.getElementById("zhanghaopass").value;

		if (name && zhanghao) {
			new ajax(getApi.addMemberWithdrawCash, {
				data: {
					memberId: member.id,
					withdrawCashId: _td.withdrawCashId,
					realName: name,
					alipayAccount: zhanghao
				},
				success: function (e) {
					delayTip('提交成功');

					setTimeout(function () {
						back('activity/checkin/draw/index.html', true);
					}, 2000);
				},
				limit: 1
			});
		} else {
			if (!name) {
				if (zhanghao) {
					delayTip("请输入姓名");
				} else {
					delayTip("请输入姓名和支付宝账号");
				}
			} else {
				if (zhanghao) {
					new ajax(getApi.addMemberWithdrawCash, {
						data: {
							memberId: member.id,
							withdrawCashId: _td.withdrawCashId,
							realName: name,
							alipayAccount: zhanghao
						},
						success: function (e) {
							delayTip('提交成功');

							setTimeout(function () {
								back('activity/checkin/draw/index.html', true);
							}, 2000);
						},
						limit: 1
					});
				} else {
					delayTip("请输入支付宝账号");
				}
			}
		}
	}
};

getTpl(function () {
	checkinYichangApi.init();
});