var novaplan_cash = $('.page[data-id="activity_novaplan_cash"]:visible'),
	novaplan_cash_row1 = novaplan_cash.find('.row-1'),
	novaplan_cash_row2 = novaplan_cash.find('.row-2'),
	novaplan_cash_row2_input = novaplan_cash_row2.find('.input-box'),
	novaplan_cash_row2_bank_name= novaplan_cash_row2.find('.bank-name'),
	novaplan_cash_row2_bank = novaplan_cash_row2.find('.bank'),
	novaplan_cash_row2_provice = novaplan_cash_row2.find('.province'),
	novaplan_cash_row2_city = novaplan_cash_row2.find('.city');
	
	
var novaplanCashApi = {
	data:{
		memberBalance: '',
		isWithdrawFail: ''
	},

	init: function () {
		var _ts = this;
			_td = _ts.data;

		requestAppMember(function () {
			_ts.post();
		});
	},

	post: function () {
		var _ts = this;
			_td = _ts.data;

		new ajax(getApi.getAreaByParentId, {
			data: {
				parentId: 0
			},
			success: function (e) {
				var _list = e;
				novaplan_cash_row2_provice.html(template('novaplan_cash_row2_tpl', _list));
				novaplan_cash_row2_city.html(template('novaplan_cash_row3_tpl'));
				novaplan_cash_row2_bank.html(template('novaplan_cash_row3_tpl'));
			}
		});

		new ajax(getApi.getMemberAccountInfo, {
			success: function (e) {
				var _list = e.returnData,
					_memberBalance = _list.memberBalance,
					_profitInviteBalance = _list.profitInviteBalance,
					_black = _list.isBlack,
					_bankList = _list.bankList;

				_td.memberBalance = _memberBalance;
				_td.profitInviteBalance = _profitInviteBalance;
				_td.bankList = _bankList;

				if (_black) {
					shield(_list.blackReason);
					return;
				}

				novaplan_cash_row1.html(template('novaplan_cash_tpl', _list));
				novaplan_cash_row2_input.html(template('novaplan_cash_input_tpl', _list));
				novaplan_cash_row2_bank_name.html(template('novaplan_cash_bank_name_tpl', _list));
			}
		});
	},

	draw: function () {
		var _ts = this;
			_td = _ts.data;

		var money_text = novaplan_cash.find("input[name='money-text']").val(),
			name_text = novaplan_cash.find("input[name='name-text']").val(),
			bank_text = novaplan_cash.find("input[name='bank-text']").val(),
			bank_name = novaplan_cash.find("select[name='bank'].bank-name").val(),
			bank = novaplan_cash.find('.bank option:selected').val(),
			prv_text = novaplan_cash.find('.province option:selected').val(),
			cty_text = novaplan_cash.find('.city option:selected').val();

		if (!money_text) {
			delayTip('提现金额不可为空~');
		} else if (!name_text) {
			delayTip('账户名不可为空~');
		} else if (!bank_text) {
			delayTip('银行卡号不可为空~');
		} else if (!bank_name) {
			delayTip('银行名称不可为空~');
		} else {
			novaplan_cash.find('.money').html(money_text);
			novaplan_cash.find('.name').html(name_text);
			novaplan_cash.find('.bank-num').html(bank_text);
			novaplan_cash.find('.bankName').html(bank_name);
			novaplan_cash.find('.home').html(prv_text + cty_text);
			novaplan_cash.find('.address').html(bank);

			_td.money_text = money_text;
			_td.name_text = name_text;
			_td.bank_text = bank_text;

			if (money_text < 50) {
				delayTip('50元起提，请重新输入~');
				_ts.clearPOST();
			} else if (_td.profitInviteBalance < money_text) {
				delayTip('余额不足~');
				_ts.clearPOST();
			} else {
				console.log(_td.bank)
				if (_td.bank) {
					popupShow('.popup-clear');
				} else {
					delayTip('请输入正确的银行名称');
				}
			}
		}
	},

	getBankName: function (e) {
		var _ts = this;
			_td = _ts.data;

		bank_name = novaplan_cash.find("select[name='bank'].bank-name").val();

		for (var i = 0; i < _td.bankList.length; i++) {
			if (_td.bankList[i].name == bank_name) {
				_td.bank = _td.bankList[i].id
				break;
			} else {
				_td.bank = ''
			}
		}	

		_ts.msgShow();
	},

	getProvince: function () {
		var _ts = this;
			_td = _ts.data;

		_td.province = novaplan_cash_row2_provice.find("option:selected").attr('id');

		if (_td.province) {
			new ajax(getApi.getAreaByParentId, {
				data: {
					parentId: _td.province
				},
				success: function (e) {
					novaplan_cash_row2_city.html(template('novaplan_cash_row2_tpl', e));
				}
			});
		} else {
			novaplan_cash_row2_city.html(template('novaplan_cash_row3_tpl'));
		}
	},

	getCity: function () {
		var _ts = this;
			_td = _ts.data;

		_td.city = novaplan_cash_row2_city.find("option:selected").attr('id');
		_ts.msgShow();
	},

	getBank: function () {
		var _ts = this;
			_td = _ts.data;

		_td.bankid = novaplan_cash_row2_bank.find("option:selected").attr('id');
	},

	msgShow: function () {
		var _ts = this;
			_td = _ts.data;
		console.log(_td)
		if (_td.city && _td.bank) {
			new ajax(getApi.getBankBranch, {
				data: {
					memberId: member.id,
					bankId: _td.bank,
					areaId: _td.city
				},
				success: function (e) {
					novaplan_cash_row2_bank.html(template('novaplan_cash_bank_tpl', e));
				}
			});
		} 
	},

	// 提现
	complet: function () {
		var _ts = this;
			_td = _ts.data;

		new ajax(getApi.applyCashWithdraw, {
			data: {
				alipayAccount: _td.bank_text,
				alipayName: _td.name_text,
				amount: _td.money_text,
				bankId: _td.bank,
				bankBranchId: _td.bankid
			},
			success: function () {
				delayTip('已提交审核,请耐心等待');
				_ts.post();
			},
			error: function (e) {
				delayTip(e.returnMsg);
				_ts.post();
			},
			complete: function () {
				_ts.clearPOST();
				closeSelf('.popup-clear');
			}
		});
	},
	
	// 清空提现框 
	clearPOST: function () {
		new ajax(getApi.getMemberAccountInfo, {
			success: function (e) {
				var _list = e.returnData;
				novaplan_cash_row2_input.html(template('novaplan_cash_input_tpl', _list));
			}
		});
	},

	closeSelf: function () {
		var _ts = this;
			_td = _ts.data;

		_ts.post();
		closeSelf('.popup-clear');
	},
	
	num: function (obj) {
		obj.value = obj.value.replace(/[^\d.]/g,""); // 清除"数字"和"."以外的字符  
		obj.value = obj.value.replace(/^\./g,""); // 验证第一个字符是数字而不是  
		obj.value = obj.value.replace(/\.{2,}/g,"."); // 只保留第一个. 清除多余的  
		obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); // 只能输入两个小数  
    }
};

getTpl(function () {
	novaplanCashApi.init();
});