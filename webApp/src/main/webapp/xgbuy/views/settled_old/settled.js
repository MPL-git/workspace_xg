var page_settled = $('.page[data-id="settled"]:visible');

var settledApi = {
	submit: function (_this) {
		var $ts = $(_this);
		defSelf();

		var _serialize = JSON.parse(decodeURIComponent(formName($ts.serialize()), true));
		_serialize.sourceType = 7;

		if (!_serialize.companyName) {
			delayTip('公司名称 不能为空');
			return;
		} else if (!_serialize.contactName) {
			delayTip('姓名 不能为空');
			return;
		} else if (!_serialize.contactJob) {
			delayTip('职务 不能为空');
			return;
		} else if (!_serialize.phone) {
			delayTip('电话 不能为空');
			return;
		} else if (!_serialize.email) {
			delayTip('邮箱 不能为空');
			return;
		} else if (!_serialize.qq) {
			delayTip('QQ 不能为空');
			return;
		} else if (!_serialize.productTypeMain) {
			delayTip('品类 不能为空');
			return;
		} else if (!_serialize.productBrandMain) {
			delayTip('品牌名称 不能为空');
			return;
		} else if (!_serialize.otherChannelLink) {
			delayTip('其他渠道链接 不能为空');
			return;
		}

		if (_serialize.otherChannelLink.length > 512) {
			delayTip('其他渠道链接 字数不能超过512');
			return;
		}

		$.ajax({
			type: 'post',
			url: '../../applySubmit.shtml',
			data: _serialize,
			success: function (data) {
				if (data.returnCode == '0000') {
					_this.reset();
					alertTip('提交入驻成功，招商人员将会与您联系洽谈入驻事宜。');
				} else {
					delayTip(data.returnMsg);
				}
			},
			error: function (data) {
				delayTip(data.returnMsg);
			}
		});
	}
};