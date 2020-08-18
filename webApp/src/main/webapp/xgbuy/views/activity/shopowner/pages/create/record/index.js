var create_record = $('.page[data-id="activity_shopowner_pages_create_record"]:visible'),
	create_record_scroll = create_record.find('.scroll-more');

var createRecordApi = {
	data: {
		pag: 10
	},

	init: function () {
		this.post();
	},

	post: function (_scroll) {
		var _ts = this;
			_td = _ts.data;

		require(['template'], function (template) {
			new ajax(getApi.getMemberRenewalProgressLog, {
				data: {
					memberId: member.id,
					currentPage: create_record_scroll.attr('data-page') || 0,
				},
				success: function (e) {
					var _pag = e.returnSize.dataList,
						_list = e.returnData.dataList,
						_len = e.returnData.dataList.length;

					if (_scroll) {
						if (_len) {
							var _cur = create_record_scroll.attr('data-page');
							_cur++;
							create_record_scroll.attr('data-page', _cur);
							create_record_scroll.find('.con-brand').append(template('novaplan_create_list', e));
						} else {
							delayTip('已经全部加载完毕');
						}
					} else {
						create_record_scroll.html(template('novaplan_create', e));
						_pag && (_ts.data.pag = _pag);

						_len == _ts.data.pag && scrollHand.init(create_record_scroll, function () {
							_ts.post(true);
						}, true);
					}
				}
			});
		});
	}
};

createRecordApi.init();