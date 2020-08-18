var novaplan_strategy = $('.page[data-id="activity_shopowner_pages_strategy"]:visible'),
	novaplan_strategy_con = novaplan_strategy.find('.scroll-more'),
	novaplan_strategy_footer = novaplan_strategy.find('.footer');

var novaplanStrategy = {
	data: {
		post: 0
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(member.app ? getApi.getNovaPlanHelpCenterH5 : getApi.getNovaPlanHelpCenter, {
			data: {
				memberId: member.id,
				type: 1
			},
			success: function (e) {
				var _list = e.returnData,
					_data = _list.dataList;

				if (_data.length) {
					for (var i = 0; i < _data.length; i++) {
						_data[i].content && (_list.dataList[i].content = _data[i].content.replace(/<br>/g, ''));
					}
				}

				novaplan_strategy_con.html(template('novaplan_strategy_tpl', _list));

				!_list.isNoviceIntegral && require(['qpTime'], function () {
					var _b = novaplan_strategy_footer.find('b');
					novaplan_strategy_footer.removeClass('hide');

					$.qpTime({
						s: 0,
						e: 10e3 - 1
					}, function (pt) {
						if (pt.t) {
							_b.html(pt.s);
						} else {
							_td.post = 1;
							_b.parent().html('领取');
						}
					});
				});
			}
		});
	},

	getReward: function () {
		const ts = this,
			td = ts.data;

		td.post && new ajax(member.app ? getApi.addReadStrategyIntegralH5 : getApi.addReadStrategyIntegral, {
			data: {
				memberId: member.id
			},
			success: function () {
				delayTip('领取成功');
				novaplan_strategy_footer.addClass('hide');
			},
			limit: 1
		});
	}
};

getTpl(function () {
	requestAppMember(function () {
		novaplanStrategy.init();
	});
});