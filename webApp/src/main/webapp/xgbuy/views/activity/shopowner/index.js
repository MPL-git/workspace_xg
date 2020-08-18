var nova_plan = $('.page[data-id="activity_shopowner"]:visible'),
	nova_plan_con = nova_plan.find('.content .touchfix'),
	nova_plan_header = nova_plan.find('header');

var novaPlan = {
	data: {
		about: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getNovaPlanIndex, {
			success: function (res) {
				var _list = res.returnData;
				
				_td.about = _list.novaPlanUrl;
				nova_plan_con.html(template('novaplan_tpl', _list));

				scrollFix();
				shareHand.update();
			}
		});

		nova_plan.find('.scroll-y').scroll(function () {
			$(this).scrollTop() > nova_plan_header.height() ? nova_plan_header.removeClass('bg-none') : nova_plan_header.addClass('bg-none');
		});
	},

	about: function () {
		var _ts = this,
			_td = _ts.data;

		_td.about && getUrl(_td.about);
	}
};

getTpl(function () {
	novaPlan.init();
});

function onShow() {
	post_id === 'activity_shopowner' && getUrl('', 'self');
}