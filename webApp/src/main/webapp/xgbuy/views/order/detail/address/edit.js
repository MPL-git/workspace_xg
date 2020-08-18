var address_edit = $('.page[data-id="order_detail_address_edit"]:visible'),
	address_edit_province = address_edit.find('select[name=province]'),
	address_edit_city = address_edit.find('select[name=city]'),
	address_edit_county = address_edit.find('select[name=county]');

var orderAddressEditApi = {
	data: {
		edit: '',
		province: '',
		city: '',
		county: '',
		combineOrderId: '',
		subOrderId: '',
		usedAddressId: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'combineOrderId': _td.combineOrderId = _b; break;
					case 'subOrderId': _td.subOrderId = _b; break;
					case 'addressId': _td.usedAddressId = _b; break;
				}
			}
		}

		traffic.setPv();

		_td.edit = getStorage('address_add');

		if (_td.edit) {
			_td.edit = JSON.parse(_td.edit);
			setTitle(_td.edit.title);

			address_edit.find('input[name=recipient]').val(_td.edit.recipient);
			address_edit.find('input[name=phone]').val(_td.edit.phone);
			address_edit.find('input[name=address]').val(_td.edit.address);
			address_edit.find('input[name=id]').val(_td.edit.id);

			_td.province = _td.edit.province;
			_td.city = _td.edit.city;
			_td.county = _td.edit.county;

			_ts.initProvince(function () {
				address_edit_province.find('option[value=' + _td.province + ']').prop('selected', true);

				_ts.initCity(function () {
					address_edit_city.find('option[value=' + _td.city + ']').prop('selected', 'selected');

					_ts.initCounty(function () {
						address_edit_county.find('option[value=' + _td.county + ']').prop('selected', 'selected');
					});
				});
			});
		} else {
			_ts.initProvince();
		}
	},

	initProvince: function (_f) {
		this.post(0, function (res) {
			var _s = '<option value="">请选择</option>';

			for (var i = 0; i < res.length; i++) {
				_s += '<option value="' + res[i].areaId + '">' + res[i].areaName + '</option>';
			}
			address_edit_province.html(_s);
			_f && _f();
		});
	},

	getProvince: function (ts) {
		this.data.province = ts.value;
		this.initCity();
	},

	initCity: function (_f) {
		this.post(this.data.province, function (res) {
			var _s = '<option value="">请选择</option>';

			for (var i = 0; i < res.length; i++) {
				_s += '<option value="' + res[i].areaId + '">' + res[i].areaName + '</option>';
			}
			address_edit_city.html(_s);
			_f && _f();
		});
	},

	getCity: function (ts) {
		if (!this.data.province) return;
		this.data.city = ts.value;
		this.initCounty();
	},

	initCounty: function (_f) {
		this.post(this.data.city, function (res) {
			var _s = '<option value="">请选择</option>';

			for (var i = 0; i < res.length; i++) {
				_s += '<option value="' + res[i].areaId + '">' + res[i].areaName + '</option>';
			}
			address_edit_county.html(_s);
			_f && _f();
		});
	},

	getCounty: function (ts) {
		if (!this.data.city) return;
		this.data.county = ts.value;
	},

	post: function (e, _f) {
		if (e === '') return;

		new ajax(getApi.getAreaByParentId, {
			data: {
				parentId: e
			},
			success: function (res) {
				_f(res.returnData);
			}
		});
	}
};

orderAddressEditApi.init();

address_edit.find('form').submit(function (e) {
	e.preventDefault();

	var _ts = this,
		_j = JSON.parse(formName($(this).serialize()));

	_j.recipient = decodeURI(_j.recipient);
	_j.address = decodeURI(_j.address);
	var phoneReg = /^1[3456789]\d{9}$/;
	if (!phoneReg.test(_j.phone)) {
		return delayTip('联系电话格式有误');
	}

	if (orderAddressEditApi.data.edit) {
		new ajax(getApi.updateAddress, {
			data: _j,
			success: function () {
				delayTip('保存成功', 1e3, function () {
					_ts.reset();
					getUrl(`order/detail/address/list.html?combineOrderId=${orderAddressEditApi.data.combineOrderId}&subOrderId=${orderAddressEditApi.data.subOrderId}&addressId=${orderAddressEditApi.data.usedAddressId}`, 'self');
				}, true);
			},
			limit: 1
		});
	} else {
		new ajax(getApi.addAddress, {
			data: _j,
			success: function () {
				delayTip('保存成功', 1e3, function () {
					_ts.reset();
					getUrl(`order/detail/address/list.html?combineOrderId=${orderAddressEditApi.data.combineOrderId}&subOrderId=${orderAddressEditApi.data.subOrderId}&addressId=${orderAddressEditApi.data.usedAddressId}`, 'self');
				}, true);
			},
			limit: 1
		});
	}
});