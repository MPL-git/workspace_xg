var novaplan_create = $('.page[data-id="activity_novaplan_pages_create"]:visible'),
	novaplan_create_checkbox = novaplan_create.find('.checkbox');

var novaplanCreateApi = {
	data:{
		read: 1
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
						case 'type': _td.type = _b; break;
						case 'id': _td.id = _b; break;
					}
				}
			}

			requestAppMember(function () {
				_ts.post();
			}, function () {
				getApi.addMemberOpenNovaPlan = getApi.addMemberOpenNovaPlanH5;
			});
		});
	},

	post: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getInformation, {
			data: {
				memberId: member.id,
				id:  _td.id,
				type: _td.type
			},
			success: function (e) {
				var _list = e.returnData,
					_openType = _list.openType,
					_mVerfiyFlag = _list.mVerfiyFlag,
					_black = _list.isBlack;
					
				setTitle(_list.title);

				_list.endDate = "(" + new Date(_list.endDate).getFullYear() + "-" + (new Date(_list.endDate).getMonth() + 1) + "-" + new Date(_list.endDate).getDate() + '日到期)'
				_td.openType = _openType;
				_td.mVerfiyFlag = _mVerfiyFlag;

				if (_black) {
					shield(_list.blackReason);
					return;
				}

				// novaplan_create_checkbox.html(template('novaplan_create_tpl', _list));
				novaplan_create.find('.content').html(_list.content);
			}
		});
	},

	open: function () {
		var _ts = this,
			_td = _ts.data;

		if (_td.read) {
			if (_td.mVerfiyFlag == 1) {
				new ajax(getApi.addMemberOpenNovaPlan, {
					data: {
						memberId: member.id
					},
					success: function (e) {
						var _list = e.returnData;

						if (!_list.isSuccess) {
							novaplan_create.find('.tab-body').html(_list.msg);
							popupShow('.popup-clear');
						} else {
							openNovaPlanResult({
								isSuccess: _list.isSuccess,
								msg: _list.msg
							}, function () {
								delayTip(_list.msg, null, null, 1);
								closeSelf('.popup-clear');
		
								setTimeout(function () {
									back('activity/novaplan/index.html', true);
								}, 2e3);
							});
						}
					},
					error: function (e) {
						openNovaPlanResult({
							isSuccess: false,
							msg: e.returnMsg
						}, function () {
							delayTip(e.returnMsg);
							closeSelf('.popup-clear');
						});
					},
					limit: novaplan_create.find('.footer')
				});
			} else {
				appBindPhone();
			}
		} else {
			delayTip('请先阅读并同意《醒购新星计划协议》');
		}
	},

	change: function (_this) {
		var _ts = this,
			_td = _ts.data,
			_that = $(_this).find('a');

		if (_that.hasClass('tick')) {
			_td.read = 1;
			_that.addClass('ticked');
			_that.removeClass('tick');
		} else if (_that.hasClass('ticked')) {
			_td.read = '';
			_that.addClass('tick');
			_that.removeClass('ticked');
		}
	}
};

getTpl(function () {
	novaplanCreateApi.init();
});