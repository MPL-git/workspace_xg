var cup_points = $('.page[data-id="cup_points"]:visible'),
	cup_points_con = cup_points.find('.content');

var cupPointsApi = {
	init: function () {
		new ajax(member.app ? getApi.getIntegralTaskH5 : getApi.getIntegralTask, {
			data: {
				memberId: member.id
			},
			success: function (res) {
				cup_points_con.html(template('cup_points_tpl', res));
			}
		});
	},

	getMobileInter: function (_mobile) {
		var _this = this;

		if (_mobile == 'false') {
			getUrl('register/mobile/index.html');
		} else {
			new ajax(member.app ? getApi.addMemberIntegralTaskH5 : getApi.addMemberIntegralTask, {
				data: {
					memberId: member.id
				},
				success: function () {
					delayTip('领取成功');
					getUrl('', true);
				}
			});
		}
	},

	getSetSelf: function () {
		getUrl('set/self.html', 'self');
	}
};

getTpl(function () {
	cupPointsApi.init();
});