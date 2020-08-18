var evalua_update = $('.page[data-id="evalua_update"]:visible'),
	evalua_con = evalua_update.find('.content'),
	evalua_tbody = evalua_update.find('.tab-body'),
	evalua_form = evalua_update.find('form'),

	evalua_file_box = '';

var evaluaApi = {
	data: {
		mchtScore: 5,
		wuliuScore: 5,
		subOrderId: undefined,
		picMax: 6,
		picNum: 0,
		list: getStorage('evalua_update_data')
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv();

		if (_td.list) {
			var _list = JSON.parse(_td.list);

			_td.subOrderId = _list.subOrderId;

			require(['template'], function (template) {
				if (_list.dataList.length) {
					for (var i = 0; i < _list.dataList.length; i++) {
						_list.dataList[i].picMax = _td.picMax;
						_list.dataList[i].picNum = _list.dataList[i].commentPics.length;
						_list.dataList[i].pics = _list.dataList[i].commentPics.join(',');
					}
				}

				_td.mchtScore = _list.mchtScore;
				_td.wuliuScore = _list.wuliuScore;

				evalua_con.html(template('evalua_tpl', _list));

				// 评分
				$.each(evalua_con.find('.evalua-star'), function () {
					$(this).click(function (e) {
						if (e.target !== this) {
							var _ix = 1 + $(e.target).index(),
								_cl = 'star-' + _ix,
								_tt = '';

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

							switch (_ix) {
								case 1: _tt = '非常差'; break;
								case 2: _tt = '差'; break;
								case 3: _tt = '一般'; break;
								case 4: _tt = '好'; break;
								case 5: _tt = '非常好'; break;
							}

							$(this).next().text(_tt);
						}
					});
				});

				// 上传
				$.each(evalua_update.find('input[type=file]'), function () {
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
										evalua_file_box.append('<div><span class="flex ac jc"><img src="' + e.returnData + '"></span><a class="fn" onclick="evaluaApi.deletes(this)"></a></div>');
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

					new ajax(getApi.updateOrderComment, {
						data: {
							subOrderId: _td.subOrderId,
							mchtScore: _td.mchtScore,
							wuliuScore: _td.wuliuScore,
							dataList: _arr
						},
						success: function (e) {
							delayTip('保存成功', 1e3, function () {
								back('', true);
							}, true);
						},
						limit: 1
					});
				});

				// textare文字输入	
				evalua_update.find('textarea').on('input', function() {
					$(this).val().length >= 200 && delayTip('您最多输入200个字~');
				});
			});
		}
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

	deletes: function (_this) {
		var _ts = this,
			$ts = $(_this),
			_file = $ts.closest('.image').find('input[type=file]');

		$ts.parent().remove();
		_ts.change(_file);
	}
};

evaluaApi.init();