var my_info = $('.page[data-id="my_info"]:visible'),
	my_info_con = my_info.find('.content');

isWeiXin && my_info.find('.set input').hide();

var infoSelf = {
	data: {
		bir: 1,
		sex: 1
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '37'
		});

		new ajax(getApi.userInfo, {
			data: {
				memberId: member.id
			},
			success: function (e) {
				var _s = parseInt(e.returnData.sexType);
				_s.toString() == 'NaN' && (_s = 1);
				e.returnData.sexType = _s;

				getStorage('member_app') && (member.app = 1);
				member.app && setStorage('member_app', 1);
				e.returnData.isApp = member.app;

				isWeiXin && (e.returnData.isWeiXin = 1);

				my_info_con.html(template('my_info_list', e));

				isAndroid && $('#my_info_bir').addClass('set-self-bir-anz');
				isiOS && $('#my_info_bir').addClass('set-self-bir-ios');

				// 性别
				my_info.find('.popup-sex div[data-type=' + my_info_con.find('.set-self-sex b').data('type') + ']').addClass('active');

				my_info_con.find('.set-self-sex').click(function (e) {
					e.preventDefault();
					$('.popup-sex').addClass('show');
				});

				// 生日
				my_info_con.find('.set-self-bir input').blur(function () {
					if (!_td.bir) return;
					_td.bir = 0;

					var that = $(this),
						_val = that.val();

					if (_val != that.data('val')) {
						new ajax(getApi.updateUserInfo, {
							data: {
								memberId: member.id,
								birthday: _val
							},
							success: function () {
								that.attr('data-val', _val);
								that.removeClass('opc0');
							},
							complete: function () {
								_td.bir = 1;
							}
						});
					} else {
						_td.bir = 1;
					}
				});

				$('.popup-sex .popup-center div').click(function (e) {
					e.preventDefault();

					if (!_td.sex) return;
					_td.sex = 0;

					$('.popup-sex').removeClass('show');
					var that = $(this),
						_sex = my_info_con.find('.set-self-sex b');

					new ajax(getApi.updateUserInfo, {
						data: {
							memberId: member.id,
							sexType: that.data('type')
						},
						success: function () {
							that.addClass('active').siblings().removeClass('active');
							_sex.html(that.text()).attr('data-type', that.data('type'));
						},
						complete: function () {
							_td.sex = 1;
						}
					});
				});
			}
		});
	},

	reBindingWx: function (e) {
		e && new ajax(getApi.bindingWeChat, {
			data: {
				memberId: member.id,
				weixinUnionId: e
			},
			success: function (res) {
				res.returnCode == '0000' && getUrl('', 'self');
				delayTip(res.returnMsg);
			}
		});
	},

	selfPicChange: function (_this) {
		var _ts = this,
			_files = _this.files[0],
			reader = new FileReader();

		reader.readAsDataURL(_files);

		reader.onload = function (e) {
			var c = document.createElement('canvas'),
				x = c.getContext('2d'),
				m = new Image();

			m.src = this.result;

			m.onload = function () {
				var _w = this.width,
					_h = this.height,
					_w0 = _w,
					_h0 = _h;

				if (_w < _h) {
					_h = Math.min(360, _h);
					_w = _w * _h / _h0;
				} else {
					_w = Math.min(360, _w);
					_h = _w * _h / _w0;
				}

				c.width = _w;
				c.height = _h;

				x.drawImage(m, 0, 0, _w, _h);

				_ts.selfPicPost(c.toDataURL('image/jpeg', 1), _files.name);
			}
		}
	},

	registerRevise: function (e) {
		getUrl('register/revise/index.html');
		setStorage('haspwd', e);
	},

	selfPicPost: function (_src, _name) {
		new ajax(getApi.uploadPic, {
			data: {
				memberId: member.id,
				fileName: _name,
				pic: _src
			},
			success: function (res) {
				new ajax(getApi.uploadUserPic, {
					data: {
						memberId: member.id,
						fileName: _name,
						pic: _pic.returnData
					},
					success: function (res1) {
						if (member.app) {
							getUrl('', true);
						} else {
							my_info_con.find('figure img').attr('src', res1.returnData.pic);
							$('.page[data-page="my"').remove();
						}
					}
				});
			}
		});
	}
};

getTpl(function () {
	infoSelf.init();
});