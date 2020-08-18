var novaplan_task = $('.page[data-id="activity_novaplan_pages_task"]:visible'),
	novaplan_task_con = novaplan_task.find('.scroll-more');

var novaplanTask = {
	data: {
		pag: 10,
	},

	init: function (_scroll) {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getMemberRenewalTaskProgress, {
			data: {
				memberId: member.id,
				pageSize: _td.pag,
				currentPage: novaplan_task_con.attr('data-page') || 0
			},
			success: function (e) {
				var _list = e.returnData,
					_size = e.returnSize,
					_len = _list.renewalTaskList.length;

				if (_scroll) {
					if (_len) {
						var _cur = novaplan_task_con.attr('data-page');
						_cur++;
						novaplan_task_con.attr('data-page', _cur);
						novaplan_task_con.append(template('novaplan_task_tpl', _list));
					} else {
						delayTip('已经全部加载完毕');
					}
				} else {
					novaplan_task_con.html(template('novaplan_task_tpl', _list));

					_size && (_td.pag = _size);

					_len == _td.pag && scrollHand.init(novaplan_task_con, function () {
						_ts.init(_tab, true);
					}, true);
				}
			}
		});
	}
};

getTpl(function () {
	novaplanTask.init();
});

function onShow() {
	post_id === 'activity_novaplan_pages_task' && getUrl('', 'self');
}