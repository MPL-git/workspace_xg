var novaplanCode = {
	formSubmit: function (event, ts) {
		defSelf(event);

		var _d = JSON.parse(formName($(ts).serialize()));

		if (!_d.invitationCode) {
			delayTip('邀请码不存在');
			return;
		}

		new ajax(getApi.updateMemberInvitationCode, {
			data: _d,
			success: function (res) {
				var _list = res.returnData;

				delayTip(_list.msg);
				_list.isSuccess && setTimeout(function () {
					back(true);
				}, 1e3);
			}
		});
	}
};