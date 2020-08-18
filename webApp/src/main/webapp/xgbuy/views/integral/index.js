var integral = $('.page[data-id="integral"]:visible'),
	integral_scroll = integral.find('.scroll-more'),
	integral_con = integral.find('.content');

var integralApi = {
	data: {
		size: 10
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '39'
		});

		_ts.getPagingList();
	},

	getPagingList: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getIntegralDtlList, {
			data: {
				currentPage: integral_scroll.attr('data-page') || 0,
				pageSize: _td.size
			},
			success: function (res) {
				var _list = res.returnData,
					_len = _list.integralDtlList.length,
					_size = res.returnSize;

				if (_scroll) {
					if (_len) {
						var _cur = integral_scroll.attr('data-page');
						_cur++;
						integral_scroll.attr('data-page', _cur);
						integral_con.append(template('integral_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					_size && (_td.size = _size);
					integral_con.html(template('integral_tpl', _list));

					new ajax(getApi.getSingleIntegralMallActivityList, {
						data: {
							memberId: member.id,
							type: 5,
							currentPage: 0
						},
						success: function (res1) {
							var _lists = res1.returnData;
							_lists.data.length > 0 && integral_scroll.prepend(template('integral_cjdk_tpl', _lists));
						}
					});

					_len == _td.size && scrollHand.init(integral_scroll, function () {
						_ts.getPagingList(true);
					}, true);
				}
			}
		});
	}
};

getTpl(function () {
	integralApi.init();
});

function onShow() {
	traffic.setPv(integralApi.data);
}