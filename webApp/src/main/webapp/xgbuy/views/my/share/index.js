var myShare = {
	data: {

	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '52'
		});

		shareHand.update();
	}
};

myShare.init();