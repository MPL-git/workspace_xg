var my_opinion = $('.page[data-id="my_opinion"]:visible'),
	my_opinion_add = my_opinion.find('.img-add'),
	evalua_file_box = '';

var myOpinionApi = {
	data: {
		maxPic: 5, // 最多上传图片数量
		tel: '',
		txt: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '49'
		});

		_ts.main();

		new ajax(getApi.userInfo, {
			success: function (e) {
				var _list = e.returnData;

				_ts.data.tel = _list.customerServiceMobile;
				_ts.data.txt = _list.customerServiceWorkTime;
			}
		});
		
		clipHnad(my_opinion.find('.con-tip')[0]);
	},
	
	call: function () {
		this.data.tel && confirmPhone(this.data.tel, this.data.txt);
	},

	tab: function (_this) {
		var $ts = $(_this);
		!$ts.hasClass('ticked') && $ts.find('.tick').addClass('ticked').end().siblings().find('.tick').removeClass('ticked');
	},

	main: function () {
		my_opinion.find('form').submit(function (e) {
			defSelf(e);

			var _serialize = JSON.parse(formName(decodeURIComponent($(this).serialize(), true)).replace(/\r|\n|\\s/g, '')),
				_pic_arr = [];

			_serialize.memberId = member.id;
			_serialize.type = $(this).find('.ticked').data('type');
			_serialize.phoneSystem = '';
			_serialize.pic = '';

			isiOS && (_serialize.phoneSystem = 1);
			isAndroid && (_serialize.phoneSystem = 2);

			$.each($(this).find('.img-box div'), function () {
				var _src = $(this).find('img').attr('src');
				_pic_arr.push(_src);
			});

			_pic_arr.length && (_serialize.pic = _pic_arr.join(','));

			new ajax(getApi.addMemberFeedback, {
				data: _serialize,
				success: function () {
					delayTip('提交成功');
					setTimeout(back, 1e3);
				},
				limit: 1
			});
		});

		$.each(my_opinion.find('input[type=file]'), function () {
			var _td = myOpinionApi.data,
				$ts = $(this);

			!evalua_file_box && (evalua_file_box = $ts.parent().siblings());

				if (!$ts.hasClass('update') && $ts.parent().siblings().find('div').length == _td.maxPic) {
					delayTip('最多上传' + _td.maxPic + '张图片');
					return;
				}

			upload.init(this, {
				success: function (_obj) {
					new ajax(getApi.uploadPic, {
						data: {
							fileName: _obj.name,
							pic: _obj.src
						},
						success: function (e) {
							var _src = e.returnData;
	
							if ($ts.hasClass('update')) {
								$ts.siblings('img').attr('src', _src);
							} else {
								$ts.parent().siblings().append('<div class="flex ac jc fn">'
									+ '<img src="' + _src + '">'
									+ '<a class="close ico-btn" onclick="myOpinionApi.deletes(this)"></a>'
									+ '</div>');
	
								$ts.parent().siblings().find('div').length == _td.maxPic ? my_opinion_add.addClass('hide') : my_opinion_add.removeClass('hide');
							}

							$('input[type=file]').val('');
						},
						limit: 1
					});
				}
			});
		});
	},
	
	deletes: function (_this) {
		$(_this).parent().remove();
		my_opinion_add.removeClass('hide');
	}
};

myOpinionApi.init();