var page_evalua = $('.page[data-id="evalua"]:visible'),
	evalua_con = page_evalua.find('.content'),
	evalua_tbody = page_evalua.find('.tab-body'),
	evalua_form = page_evalua.find('form'),

	evalua_file_box = '';

var evaluaApi = {
	data: {
		api: getApi.getCanCommentOrderDtl,
		type: 1,
		productScore: 5,
		mchtScore: 5,
		wuliuScore: 5,
		subOrderId: undefined,
		orderDtlId: undefined,
		picMax: 6,
		picNum: 0,
		rules: 1
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		for (var i = 0; i < search_url.length; i++) {
			var _a = search_url[i].split('='),
				_b = _a[1];

			switch (_a[0]) {
				case 'type': _td.type = _b; break;
				case 'subOrderId': _td.subOrderId = _b; break;
				case 'orderDtlId': _td.orderDtlId = _b; break;
			}
		}

		traffic.setPv();

		if (_td.type == 2) {
			setTitle('发表追评');
			_td.api = getApi.getAppendCommentProduct;
		}

		_td.subOrderId && _ts.main();
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(_td.api, {
			data: {
				subOrderId: _td.subOrderId,
				orderDtlId: _td.orderDtlId
			},
			success: function (e) {
				var _list = e.returnData;

				if (_td.type == 1 && _list.dataList.length) {
					for (var i = 0; i < _list.dataList.length; i++) {
						_list.dataList[i].productScore = _td.productScore;
						_list.dataList[i].picMax = _td.picMax;
						_list.dataList[i].picNum = _td.picNum;
					}

					_list.mchtScore = _td.mchtScore;
					_list.wuliuScore = _td.wuliuScore;
				}

				if (_td.type == 2) {
					_td.mchtScore = undefined;
					_td.wuliuScore = undefined;

					_list.append = true;
					_list.picMax = _td.picMax;
					_list.picNum = _td.picNum;
					_list.dataList = [];
					_list.dataList.push(_list);
				}

				_list.type = _td.type;
				evalua_con.html(template('evalua_tpl', _list));

				// 评分
				if (_td.type == 1) {
					$.each(evalua_con.find('.evalua-star'), function () {
						$(this).click(function (e) {
							if (e.target !== this) {
								var _ix = 1 + $(e.target).index(),
									_cl = 'star-' + _ix;

								if ($(this).hasClass(_cl)) {
									_ix--;
									_cl = 'star-' + _ix;
								}

								$(this).removeClass('star-1')
									.removeClass('star-2')
									.removeClass('star-3')
									.removeClass('star-4')
									.removeClass('star-5')
									.addClass(_cl);

								$(this).hasClass('mchtScore') && (_td.mchtScore = _ix);
								$(this).hasClass('wuliuScore') && (_td.wuliuScore = _ix);
								$(this).hasClass('productScore') && ($(this).attr('data-val', _ix));
								if(_ix == 0) {
									_ix = 1;
									$(this).addClass('star-1');
								}

								if (_ix == 5) {
									$(this).next().text('非常好')
								} else if (_ix == 4) {
									$(this).next().text('好')
								} else if (_ix == 3) {
									$(this).next().text('一般')
								} else if (_ix == 2) {
									$(this).next().text('差')
								} else if (_ix == 1) {
									$(this).next().text('非常差')
								} 
							}
						});
					});
				}

				// textare文字输入	
				evalua_con.find('textarea').on('input', function(){
					$(this).val().length >= 200 && delayTip('您最多输入200个字~');
				});

				// 上传
				$.each(page_evalua.find('input[type=file]'), function () {
					var $width = $(this).width(),
						$ts = $(this);

					!evalua_file_box && (evalua_file_box = $ts.parent().siblings());

					upload.init(this, {
						success: function (_obj) {
							new ajax(getApi.mchtUploadPic, {
								data: {
									fileName: _obj.name,
									pic: _obj.src
								},
								success: function (e) {
									closeSelf();

									_ts.change($ts, function () {
										evalua_file_box.append('<div><span class="flex ac jc"><img src="' + e.returnData + '" style="width: 100%; height: 100%"></span><a class="fn" onclick="evaluaApi.deletes(this)"></a></div>');
									});
								},
								limit: 1
							});
						}
					});
				});

				// 提交
				evalua_form.find('input[type=submit]').removeAttr('disabled');

				evalua_form.submit(function (e) {
					e.preventDefault();
					var _arr = [];

					$.each(evalua_con.find('.main'), function () {
						_arr.push({
							productScore: $(this).find('.productScore').data('val'),
							content: $(this).find('textarea').val(),
							orderDtlId: $(this).data('id'),
							pics: $(this).find('.image').data('pic')
						});
					});

					new ajax(getApi.addMemberComment, {
						data: {
							type: _td.type,
							subOrderId: _td.subOrderId,
							orderDtlId: _td.orderDtlId,
							mchtScore: _td.mchtScore,
							wuliuScore: _td.wuliuScore,
							dataList: _arr
						},
						success: function (e) {
							var _list = e.returnData;

							if (_td.type == 1 && _list.isHasActivity) {
								new ajax(getApi.addMemberCommentDraw, {
									data: {
										subOrderId: _td.subOrderId
									},
									success: function () {
										page_evalua.find('.evalua_integral').html(_list.integral);
										popupShow('.popup-evalua-integral');
									}
								});
							} else {
								delayTip('提交成功', 1e3, function () {
									back('', true);
								}, true);
							}
						},
						limit: 1
					});
				});
			}
		});
	},

	change: function (_this, fn) {
		var _ts = this,
			_td = _ts.data,
			_par = _this.parent(),
			_box = _par.siblings(),
			_len = _box.children().length,
			_pic = [];

		_len < _td.picMax && fn && fn();
		_len = _box.children().length;
		_len < _td.picMax ? _par.removeClass('hide') : _par.addClass('hide');
		_this.siblings().html(_len + '/' + _td.picMax);

		if (_len) {
			$.each(_box.children(), function () {
				_pic.push($(this).find('img').attr('src'));
			});

			_pic.join(',');
		} else {
			_pic = '';
		}

		_box.parent().attr('data-pic', _pic);
	},

	//活动规则
	rules: function () {
		var _ts = this,
			_td = _ts.data;

		if (_td.rules) {
			new ajax(getApi.getLuckDrawRule, {
				success: function (e) {
					var _list = e;

					_td.rules = 0;
					evalua_tbody.html(template('evalua_tpl_rules', _list));
					popupShow('.popup-evalua-rule');
				}
			});
		} else {
			popupShow('.popup-evalua-rule');
		}
	},

	deletes: function (_this) {
		var _ts = this,
			$ts = $(_this),
			_file = $ts.closest('.image').find('input[type=file]');

		$ts.parent().remove();
		_ts.change(_file);
	}
};

getTpl(function () {
	evaluaApi.init();
});