var settled_detail = $('.page[data-id="settled_detail"]:visible'),
	settled_detail_main = settled_detail.find('.main'),
	settled_detail_success = settled_detail.find('.popup-success'),
	settled_detail_file = '';

var settledDetailApi = {
	data: {
		type: 1, // 类型 1, 企业公司; 2, 个体工商
		pics: [], // 上传的图片集合
		flag:true
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'type': _td.type = _b; break;
					case 'platformContactId': _td.platformContactId = _b; break;
					case 'memberId': _td.memberId = _b; break;
				}
			}
		}
		_td.memberId = member.id

		setTitle(_td.type == 2 ? '个体工商入驻申请' : '企业入驻申请');

		settled_detail_main.append(template('settled_detail_tpl', {type: _td.type,platformContactId:_td.platformContactId}));
		

		settled_detail_file = settled_detail_main.find('.up-btn input');

		new ajax(getApi.getProductTypeByParentId, {
			data: {
				productTypeId: ''
			},
			success: function (res) {
				var _s = '<option value="">请选择要经营的品类</option>',
					list = res.returnData;

				for (var i = 0; i < list.length; i++) {
					_s += '<option value="' + list[i].id + '">' + list[i].name + '</option>';
				}
				settled_detail_main.find('select').html(_s);
			}
		});

		new uploadFile(settled_detail_file[0], {
			url: getApi.mchtUploadPic,
			length: 3,
			success: function (res) {
				_td.pics.push(res);
				_ts.setImg();
			}
		});
	},

	setImg: function () {
		var _ts = this,
			_td = _ts.data,
			_s = '';

		for (var i = 0; i < _td.pics.length; i++) {
			_s += '<div><img src="' + _td.pics[i] + '"><span onclick="settledDetailApi.popImg(' + i + ')"></span></div>'
		}

		_td.pics.length < 2 ? settled_detail_main.find('.up-tip').removeClass('hide') : settled_detail_main.find('.up-tip').addClass('hide');
		_td.pics.length < 3 ? settled_detail_main.find('.up-btn').removeClass('hide') : settled_detail_main.find('.up-btn').addClass('hide');

		settled_detail_file.attr('data-length', _td.pics.length);
		settled_detail_main.find('.up-img').html(_s);
		settled_detail_main.find('input[name=productPic]').val(_td.pics.join(','));
	},

	popImg: function (_i) {
		var _ts = this,
			_td = _ts.data;

		_td.pics.splice(_i, 1);
		_ts.setImg();
	},

	select: function (_this) {
		$(_this).siblings('input').val($(_this).find('option[value=' + _this.value + ']').text());
	},

	submit: function (event, _this) {
		var _ts = this,
			_td = _ts.data,
			$ts = $(_this);

		defSelf(event);

		var _jp = JSON.parse(decodeURIComponent(formName($ts.serialize()), true));
		_jp.settledType = _td.type;
		_jp.clientType = member.psys == 3 ? 1 : 2;
		_jp.memberId = _td.memberId;
		if (_jp.companyName == '') {
			delayTip('公司名称 不能为空');
			return;
		} else if (_jp.companyName.indexOf("公司")==-1) {
			delayTip('公司名称填写有误！');
			return;
		} else if (_jp.contactName == '') {
			delayTip('姓名 不能为空');
			return;
		} else if (_jp.contactJob == '') {
			delayTip('职务 不能为空');
			return;
		} else if (_jp.phone == '') {
			delayTip('手机 不能为空');
			return;
		} else if (_jp.email == '') {
			delayTip('邮箱 不能为空');
			return;
		} else if (_jp.qq == '') {
			delayTip('QQ 不能为空');
			return;
		} else if (_jp.productTypeId == '') {
			delayTip('品类 不能为空');
			return;
		} else if (_jp.otherShop == '') {
			delayTip('其他渠道链接 不能为空');
			return;
		} else if (_jp.productBrandMain == '') {
			delayTip('品牌名称 不能为空');
			return;
		} else if (_jp.productName == '') {
			delayTip('商品名称 不能为空');
			return;
		} else if (_jp.productPic == '') {
			delayTip('商品主图 不能为空');
			return;
		}
		if(_td.flag){
			$.ajax({
				type: 'post',
				url: '../../applySubmit.shtml',
				data: _jp,
				success: function (res) {
					_ts.data.flag = false;
					if (res.returnCode == '0000') {
						_this.reset();
						settled_detail_success.find('i').html(_jp.phone);
						popupShow(settled_detail_success);
					} else {
						delayTip(res.returnMsg);
					}
				},
				error: function (res) {
					delayTip(res.returnMsg);
				}
			});
		}

	}
};

getTpl(function () {
	settledDetailApi.init();
});