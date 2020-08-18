require.config({
	paths: {
		'jquery': 'jquery-3.2.1.min',
		'distpicker': 'distpicker.min'
	}
});

require(['jquery', 'distpicker'], function () {
	$('.distpicker').distpicker({
		province: '省',
		city: '市',
		district: '区'
	});
	
	$('.distpicker select').change(function () {
		$.each($('.distpicker select'), function () {
			$(this).attr('data-val', $(this).find('option:selected').data('code'));
		});
	});
	
	postApi('../../api/n/getProductTypeByParentId', {
		productTypeId: ''
	}, function (data) {
		var _s = '<option value="">请选择要经营的品类</option>',
		list = data.returnData;
		console.log(data);
		for (var i = 0; i < list.length; i++) {
			_s += '<option value="' + list[i].id + '">' + list[i].name + '</option>';
		}
		$("#productTypeId").html(_s);
	});
	
	$('#form_apply').submit(function (e) {
		e.preventDefault();

		var _this = $(this),
			_pics = [],
			f_data = _this.find('.f-data'),
			_serialize = JSON.parse(formName(decodeURIComponent($(this).serialize(), true)));

		_serialize.province = $('.distpicker select[name=province]').attr('data-val');
		_serialize.city = $('.distpicker select[name=city]').attr('data-val');
		_serialize.county = $('.distpicker select[name=county]').attr('data-val');
		_serialize.sourceType = document.location.search.replace('?source=', '') || 7;
		if(document.location.search.indexOf("?source")!=-1 && document.location.search.indexOf("&platformContactId")!=-1){
			var array = document.location.search.split("&");
			_serialize.sourceType = array[0].replace('?source=', '');
			_serialize.platformContactId = array[1].replace('platformContactId=', '');
		}
		$.each($('.zj .f-data'), function () {
			_pics.push($(this).data('val'));
		});

		_serialize.pics =  _pics.join(',');

		if (!_serialize.companyName) {
			alertTip('公司名称 不能为空');
			return;
		} else if (!_serialize.regCapital) {
			alertTip('公司资本 不能为空');
			return;
		} else if (!_pics[0]) {
			alertTip('营业执照 不能为空');
			return;
		} else if (!_pics[1]) {
			alertTip('银行开户许可证 不能为空');
			return;
		} else if (!_serialize.corporationName) {
			alertTip('公司法人 不能为空');
			return;
		} else if (!_serialize.address) {
			alertTip('通讯地址 不能为空');
			return;
		} else if (!_serialize.contactName) {
			alertTip('联系人 不能为空');
			return;
		} else if (!_serialize.qq) {
			alertTip('QQ 不能为空');
			return;
		} else if (!_serialize.phone) {
			alertTip('联系人手机 不能为空');
			return;
		} else if (!_serialize.email) {
			alertTip('联系邮箱 不能为空');
			return;
		} else if (!_serialize.productTypeId) {
			alertTip('主营类目 不能为空');
			return;
		} else if (!_serialize.productBrandMain) {
			alertTip('运营品牌 不能为空');
			return;
		}

		$.ajax({
			type: 'post',
			url: '../../applySubmit.shtml',
			data: _serialize,
			success: function (data) {
				if (data.returnCode == '0000') {
					f_data.val('').attr('data-val', '');
					$('.zb select').removeAttr('data-val');
					$('.zj img').attr('src', '');

					alertTip('提交入驻成功，招商人员将会与您联系洽谈入驻事宜。', true);
				} else {
					alert(data.returnMsg);
				}
			},
			error: function (data) {
				alert(data.returnMsg);
			}
		});
	});

	// 上传图片
	$.each($('.no-border input[type=file]'), function () {
		(function (_this) {
			fileReader(_this[0], 400, false, function (_src, _name, _c) {
				postApi('../../api/n/mchtUploadPic', {
					fileName: _name,
					pic: _src
				}, function (data) {
					var _w = '100%',
						_h = 'auto';

					if (_c.width < _c.height) {
						_w = 'auto';
						_h = '100%';
					}

					_this.attr('data-val', data.returnData).closest('dt').find('img').attr('src', data.returnData).width(_w).height(_h);
				});
			});
		})($(this));
	});

	// 百度
	require(['https://hm.baidu.com/hm.js?6d2fd36d9cf86bab33982f624c157f83']);
});

// 弹窗
function alertTip(e, _b) {
	$('body').find('.confirm-tip').remove();
	$('body').append('<div class="confirm confirm-tip"><div class="confirm-con"><div><h3 class="flex ac jc">提示</h3><p class="flex ac jc">' + e + '</p></div><a class="flex ac jc" onclick="closeTicket(' + (_b ? 1 : '') + ')">确认</a></div></div>');
}

// 关闭弹窗(改为回退)
function closeTicket(e) {
	$('.confirm-tip').hide();
	e && history.back();
}

// 转json
function formName(e) {
	return "{\"" + e.replace(/&/g,"\",\"").replace(/=/g,"\":\"") + "\"}";
}

// 上传
function postApi(_url, data, fn) {
	$.ajax({
		type: 'post',
		url: _url,
		data: JSON.stringify({
			reqData: data,
			system: 3,
			token: '',
			timeStamp: (new Date).getTime(),
			nonceStr: "OV5QOVX21A0446K8",
			signature: "7f8c4ad7f6d349cf28879670b9fe79b9"
		}),
		success: function (data) {
			fn(data);
		}
	});
}

// 上传图片
function fileReader(e, w, q, b) {
	if (typeof FileReader === 'undefined') {
		alert('Not support FileReader!');
		e.setAttribute('disabled', 'disabled');
	} else{
		e.addEventListener('change', function () {
			var arr_yes = [],
				arr_not = [],
				index = 0;

			for (var i = 0; i < this.files.length; i++) {
				if (!/image\/\w+/.test(this.files[i].type)) {
					arr_not.push(this.files[i].name);
				} else{
					arr_yes.push(this.files[i].name);

					var reader = new FileReader();
					reader.readAsDataURL(this.files[i]);

					reader.onload = function (e) {
						index++;
						
						var c = document.createElement('canvas'),
							x = c.getContext('2d'),
							m = new Image();

						m.src = this.result;

						m.onload = function () {
							var _w = this.width,
								h = this.height;

							w = w ? Math.min(w, _w) : _w;
							h = w * h / _w;
							c.width = w;
							c.height = h;

							x.drawImage(m, 0, 0, w, h);

							q ? b(c.toDataURL('image/jpeg', q), arr_yes[0], this) : b(c.toDataURL(), arr_yes[0], this);
						}
					}
				}
			}
		});
	}
}