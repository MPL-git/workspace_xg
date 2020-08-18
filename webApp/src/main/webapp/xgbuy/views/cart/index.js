var cart = $('.page[data-id="cart"]:visible'),
	cart_con = cart.find('.scroll-more');

var popup_delete = cart.find('.popup-delete'),
	popup_delete_tpl = popup_delete.find('.tpl');

var popup_mcht = cart.find('.popup-mcht'),
	popup_mcht_tpl = popup_mcht.find('.tpl');

var popup_plat = cart.find('.popup-plat'),
	popup_plat_tpl = popup_plat.find('.tpl');
var cart = {
	data: {
		deleteData: {}
	},

	init: function () {
		var ts = this,
			td = ts.data;

		if (search_url) {
			for (var i = 0; i < search_url.length; i++) {
				var _a = search_url[i].split('='),
					_b = _a[1];

				switch (_a[0]) {
					case 'tab': td.tab = _b; break;
					case 'buyNow': td.buyNow = _b; break;
				}
			}
		}

		traffic.setPv(td, {
			pageType: '70'
		});

		ts.onRender();
	},

	onRender: function () {
		var ts = this,
			td = ts.data;

		td.plat = { // 平台优惠券 版本2
			tab: 0, // 切换可用与不可用优惠券列表

			availDate: [], // 日期符合, 只做桥接
			unavailDate: [], // 日期不符合

			availCoupons: [], // 符合指定优惠券
			unavailCoupons: [] // 不符合指定优惠券
		};

		td.specialCheck = getStorage('cartSpecialCheck') ? getStorage('cartSpecialCheck') : false //特殊商品显示

		new ajax(getApi.shoppingCart, {
			success: function (res) {
				var _list = res.returnData,
					_ls = _list.activityAreaList;

				if (_ls.length) {
					td.memberPlatformCoupons = _list.memberPlatformCoupons;
					td.currentTime = _list.currentTime;

					for (var i = 0; i < _ls.length; i++) {
						// 预售定金专场限制
						_ls[i].preSellAuditStatus === '1' && (td.currentTime < _ls[i].activityBeginTime || td.currentTime > _ls[i].activityEndTime) && (_ls[i].advanceLimit = 1);

						// 商家优惠券
						var _pl = _ls[i].productList;

						for (var j = 0; j < _pl.length; j++) {
							var _mn = {
								money: 0
							},
								_sl = _pl[j].salePrice - (_pl[j].quantity > _pl[j].subDepositOrderQuantity ? 0 : _pl[j].deductAmount),
								_pc = _pl[j].productCoupons;

							for (var k = 0; k < _pc.length; k++) {
								_sl > _pc[k].money && _pc[k].money > _mn.money && (!_ls[i].useMchtCoupon || _ls[i].useMchtCoupon.indexOf(_pc[k].id) === -1) && (_mn = {
									money: parseFloat(_pc[k].money),
									id: _pc[k].id
								});
							}

							if (_mn.id) {
								_ls[i].productList[j].couponMcht = _mn;
								_ls[i].useMchtCoupon ? _ls[i].useMchtCoupon.push(_mn.id) : (_ls[i].useMchtCoupon = [_mn.id]);
							}
						}
					}

					// 平台优惠券日期限制
					for (var i = 0; i < td.memberPlatformCoupons.length; i++) {
						var _mi = td.memberPlatformCoupons[i],
							_bt = _mi.expiryBeginDate,
							_et = _mi.expiryEndDate,
							n_bt = new Date(_bt),
							n_et = new Date(_et);

						_mi.time = n_bt.getFullYear() + '-'
							+ famateNumber(1 + n_bt.getMonth()) + '-'
							+ famateNumber(n_bt.getDate()) + ' '
							+ famateNumber(n_bt.getHours()) + ':'
							+ famateNumber(n_bt.getMinutes()) + ' - '
							+ n_et.getFullYear() + '-'
							+ famateNumber(1 + n_et.getMonth()) + '-'
							+ famateNumber(n_et.getDate()) + ' '
							+ famateNumber(n_et.getHours()) + ':'
							+ famateNumber(n_et.getMinutes());

						_mi.name = _mi.couponType === '1' ? ((_mi.canSuperpose === '1' ? '' : '不') + '可与品类券叠加') : ('仅' + _mi.productTypeName + '类目可使用');
						(td.currentTime < _bt || td.currentTime > _et) ? td.plat.unavailDate.push(_mi) : td.plat.availDate.push(_mi);
					}
					td.plat.couponLength = td.memberPlatformCoupons.length;

					td.pile = {};
					td.list = _ls;
					td.plat = td.plat;
					td.allowanceBalance = _list.allowanceBalance
					td.useallowance = 0

					ts.setData();

					td.buyNow && ts.getTick(td.buyNow, 1);
				}
				else if (_list.decorateInfoId) {
					cart_con.append(
						'<div class="page-empty empty-goods flex dc ac jc">' +
						'<i></i>' +
						'<p class="m3">当前购物车空空如也</p>' +
						'<a class="flex ac jc btn-red ico-btn fn" onclick="getUrl(' + "'home/index.html'" + ')">去抢购</a>' +
						'</div>'
					)
					$.ajax({
						method: 'get',
						url: lead_url + 'views/activity/templet/brand_decorate.html',
						datatype: 'html',
						success: function (res1) {
							setStorage('decorate_infoid', _list.decorateInfoId);
							setStorage('decorate_nest', 1);
							cart_con.append(res1.replace('<header class="flex', '<header class="flex hide')
								.replace('page flex dc activity-single-day decorate bgfa', 'activity-single-day decorate')
								.replace('scroll-y scroll-more', '')
								.replace(' data-id="activity_templet_brand_decorate"', ''))
								.addClass('scroll-y scroll-more').addClass('cart-decorate scroll-decorate');
							ts.setData();
						}
					});

					td.tab && cart.find('.page-empty a').addClass('hide');
				}
				else {
					td.list = [];

					ts.setData();
				}
			}
		});
	},

	setData: function () {
		var ts = this,
			td = ts.data;

		var _t = cart_con.find('.scroll-y').scrollTop() || 0;

		cart_con.html(template('cart_tpl', {
			pile: td.pile,
			list: td.list,
			plat: td.plat,
			specialCheck: td.specialCheck
		}));

		cart_con.find('.scroll-y').scrollTop(_t);
	},

	setPlat: function () {
		var ts = this,
			td = ts.data;

		var _t = popup_plat_tpl.find('.scroll-y').scrollTop() || 0;

		popup_plat_tpl.html(template('cart_tpl_plat', {
			plat: td.plat,
			specialCheck: td.specialCheck
		}));

		popup_plat_tpl.find('.scroll-y').scrollTop(_t);

		// ts.setData();
	},

	setMcht: function () {
		var ts = this,
			td = ts.data;

		popup_mcht_tpl.html(template('cart_tpl_mcht', {
			couponMcht: td.couponMcht
		}));

		// ts.setData();
	},

	getQuantity: function (_this) {
		var ts = this,
			td = ts.data,
			_d = $(_this),
			_act = _d.data('act'),
			_id = _d.data('id'),
			_type = _d.data('type'),
			_stock = _d.data('stock'),
			_limit = _d.data('limit'),
			_quantity = _d.data('quantity'),
			_control = _d.data('control'), // 预售是否要控制购物车数量
			_advanceMax = _d.data('advanceQuantity'); // 预售数量

		if (td.editShopPost) return;

		if (_type) {
			if (_quantity >= _limit && _limit) {
				delayTip('每人限购' + _limit + '件');
			} else if (_quantity >= _stock) {
				delayTip('库存不足');
			} else {
				ts.editShop({
					act: _act,
					id: _id,
					zj: 1
				});
			}
		} else if (_control && _quantity <= _advanceMax) {
			delayTip('购买数量不得低于预售数量');
			td.editShopPost = 0;
		} else if (_quantity > 1) {
			ts.editShop({
				act: _act,
				id: _id,
				zj: -1
			});
		} else {
			td.editShopPost = 0;
		}
	},

	editShop: function (obj) {
		var ts = this,
			td = ts.data;

		td.editShopPost = 1;

		!obj && (obj = td.deleteData);

		new ajax(getApi.editShoppingCart, {
			data: {
				shoppingCartId: obj.id,
				quantity: obj.zj || '',
				type: obj.type || 1
			},
			success: function () {
				for (var i = 0; i < td.list.length; i++) {
					var _l = td.list[i];
					if (_l.activityAreaId == obj.act) {
						for (var j = 0; j < _l.productList.length; j++) {
							if (_l.productList[j].shoppingCartId == obj.id) {
								if (obj.type) {
									var _ct = _l.productList[j].couponMcht;

									if (_ct && _ct.id) {
										for (var k = 0; k < _l.useMchtCoupon.length; k++) {
											if (_l.useMchtCoupon[k] == _ct.id) {
												_l.useMchtCoupon.splice(k, 1);
												break;
											}
										}
									}

									_l.productList.splice(j, 1);

									var _empty = 1;

									for (var k = 0; k < td.list.length; k++) {
										if (td.list[k].productList.length) {
											_empty = 0;
											break;
										}
									}

									closeSelf();

									if (_empty) {
										location.reload()
										// ts.onRender();
										td.editShopPost = 0;
										return;
									}
								} else {
									_l.productList[j].quantity += obj.zj;
								}

								break;
							}
						}
						
						if (_l.preferentialType == 5) {
							ts.allowanceCount(td.list)
						} else {
							ts.getCount(_l);
						}
						break;
					}
				}

				ts.getCountPlat(function () {
					td.editShopPost = 0;
				});
			},
			error: function (res) {
				td.editShopPost = 0;
			}
		});
	},

	deleteShop: function (_this) {
		var ts = this,
			td = ts.data,
			_d = $(_this),
			_act = _d.data('act'),
			_name = _d.data('name'),
			_id = _d.data('id');

		if (td.editShopPost || !_d.data('allow')) return;

		td.deleteData = {
			act: _act,
			id: _id,
			type: 2
		};

		popup_delete_tpl.html(_name);
		popupShow(popup_delete);
	},

	getTick: function (_this, _b) {
		var ts = this,
			td = ts.data,
			_qt = td.pile,
			_ls = td.list,
			_ll = _ls.length;

		var _id = _this,
			_act = undefined,
			allow = 1;

		if (!_b) {
			var _d = $(_this);

			_id = _d.data('id');
			_act = _d.data('act');
			allow = _d.data('allow');
		}

		if (allow) {
			var _n = 0, // 专场勾选数量
				_m = 0; // 专场被限制数量
			for (var i = 0; i < _ll; i++) {
				var _l = _ls[i],
					_ps = _l.productList,
					_pl = _ps.length;

				if (_l.activityAreaId == _act || _b) {
					var _x = 0, // 商品勾选数量
						_y = 0; // 商品被限制数量

					for (var j = 0; j < _pl; j++) {
						var _p = _ps[j];

						_p.shoppingCartId == _id && (_p.tick = !_p.tick);
						_p.stock > 0 && _p.quantity <= _p.stock ? _p.tick && _x++ : _y++;
					}

					_l.disTick = undefined;

					if (_x) {
						_l.tick = (_x + _y) === _pl;
					} else {
						_l.tick = 0;
						_y === _pl && (_l.disTick = 1);
					}

					if (_l.preferentialType == 5) {
						ts.allowanceCount(_ls)
					} else {
						ts.getCount(_l);
					}
				}

				if (_l.tick) {
					_n++;
				} else if (_l.disTick || _l.advanceLimit) {
					_m++;
				}
			}

			_qt.tick = (_n + _m) === _ll;

			ts.getCountPlat();
		}
	},

	getTickArea: function (_this) {
		var ts = this,
			td = ts.data,
			_qt = td.pile,
			_ls = td.list,
			_d = $(_this),
			_act = _d.data('act'),
			allow = _d.data('allow');

		if (allow) {
			var _n = 0,
				_m = 0,
				_len = _ls.length;

			for (var i = 0; i < _len; i++) {
				var _l = _ls[i];

				if (_l.activityAreaId == _act && _act !== null) {
					if (_l.tick) {
						_l.tick = 0;

						for (var j = 0; j < _l.productList.length; j++) {
							_l.productList[j].tick = 0;
						}
					} else {
						_l.tick = 1;

						for (var j = 0; j < _l.productList.length; j++) {
							var _p = _l.productList[j];

							_p.stock > 0 && _p.quantity <= _p.stock && (_p.tick = 1);
						}
					}
					if (_l.preferentialType == 5) {
						ts.allowanceCount(_ls)
					} else {
						ts.getCount(_l);
					}
				}

				if (_l.tick) {
					_n++;
				} else if (_l.advanceLimit) {
					_m++;
				}
			}

			_qt.tick = (_n + _m) === _len;

			ts.getCountPlat();
		}
	},

	getTickAll: function () {
		var ts = this,
			td = ts.data,
			_qt = td.pile,
			_ls = td.list;
		td.useallowance = 0
		if (_qt.tick) {
			_qt.tick = 0;

			for (var i = 0; i < _ls.length; i++) {
				var _l = _ls[i];

				_l.tick = 0;

				for (var j = 0; j < _l.productList.length; j++) {
					_l.productList[j].tick = 0;
				}

				ts.getCount(_l);
			}
		} else {
			_qt.tick = 1;

			for (var i = 0; i < _ls.length; i++) {
				var _l = _ls[i];

				if (!_l.advanceLimit) {
					_l.tick = 1;

					for (var j = 0; j < _l.productList.length; j++) {
						var _p = _l.productList[j];

						_p.stock > 0 && _p.quantity <= _p.stock && (_p.tick = 1);
					}

					ts.getCount(_l);
				}
			}
		}

		ts.getCountPlat();
	},

	//津贴 - 重新计算
	allowanceCount: function (ls) {
		var ts = this,
			td = ts.data;
		td.useallowance = 0
		for (var i = 0; i < ls.length; i++) {
			var _l = ls[i];
			ts.getCount(_l);
		}
	},

	getCount: function (_l) {
		var ts = this,
			td = ts.data,
			_qt = td.pile,
			_ls = td.list,
			_pt = _l.preferentialType;

		_l.saleOri = 0;			// 专场原价
		_l.saleDed = 0;			// 专场抵扣金
		_l.saleDedNum = 0;		// 专场抵扣金数量
		_l.saleSub = 0;			// 专场优惠金额
		_l.saleMcht = 0;		// 专场商品优惠
		_l.saleRes = 0;			// 专场原价减去专场优惠金额后的价格
		_l.saleNum = 0;			// 专场件数 (多买优惠, 类型1)
		_l.saleSingle = [];		// 专场单价集合 (多买优惠, 类型1)
		_l.couponId = '';		// 专场使用的优惠券ID
		_l.saleOriTxt = '';
		_l.saleSubTxt = '';
		_l.saleResTxt = '';
		_qt.countResTxt = '';
		_qt.couponPlat = '';	// 平台优惠券文字
		_l.couponLess = '';		// 凑单优惠文字
		_l.couponUser = '';		// 专场优惠文字
		_l.couponUserRight = '';		// 专场优惠右侧文字
		_l.couponTxt = '优惠';	// 专场优惠标签

		_qt.countRes = 0;		// 合计专场金额
		_qt.countSub = 0;		// 合计专场优惠
		_qt.countNum = 0;		// 合计总件数

		_qt.countResTotal = 0;	// 合计总金额
		_qt.countSubTotal = 0;	// 合计总优惠

		_qt.couponPlatId = '';	// 平台优惠券ID
		_qt.dataList = [];		// 专场数据集合

		for (var j = 0; j < _l.productList.length; j++) {
			var _p = _l.productList[j];

			if (_p.tick) {
				var _cm = 0,
					_pd = [];

				_l.saleNum += _p.quantity;
				_l.saleDed += _p.deductAmount * _p.subDepositOrderQuantity;
				_l.saleDedNum += _p.subDepositOrderQuantity;

				_p.couponMcht && _p.couponMcht.id && (_p.couponMcht.money && (_cm = parseFloat(_p.couponMcht.money)), _l.saleMcht += _cm);

				for (var k = 0; k < _p.quantity; k++) {
					_pd.push(_p.salePrice - (k < _p.subDepositOrderQuantity ? _p.deductAmount : 0));
				}

				_pd = _pd.sort(function (a, b) {
					return a - b;
				});

				_pd[_pd.length - 1] = (_pd[_pd.length - 1] * 1e3 - _cm * 1e3) / 1e3;
				_l.saleSingle = _l.saleSingle.concat(_pd);

				_l.saleSingle = _l.saleSingle.sort(function (a, b) {
					return a - b;
				});

				_l.saleOri = (_l.saleOri * 1e3 + _p.quantity * (_p.salePrice * 1e3)) / 1e3;
			}
		}

		_l.saleOri = (_l.saleOri * 1e3 - _l.saleDed * 1e3) / 1e3;
		_l.saleOri = (_l.saleOri * 1e3 - _l.saleMcht * 1e3) / 1e3;

		if (_l.saleNum) {
			if (_l.activityType === '101') {
				var _u1 = 0, // 优惠
					_u2 = 0,

					_p1 = '', // 已享
					_p2 = '',

					_z1 = '', // 标签
					_z2 = '';

				// 优惠券
				for (var i = _l.memberCoupons.length - 1; i >= 0; i--) {
					var _mc = _l.memberCoupons[i],
						s0 = parseFloat(_mc.minimum),
						s1 = parseFloat(_mc.money);

					if (_mc.preferentialType === '2') {
						var _u1s = parseInt(100 * (1e3 * _l.saleOri - 1e3 * _l.saleOri * _mc.discount) / 1e3) / 100;

						if (_mc.conditionType === '1') {
							// 无条件
							if (_u1s > _u1) {
								_u1 = _u1s;
								_l.couponId = _mc.memberCouponId;
								_mc.maxDiscountMoney && (_u1 = Math.min(_u1, _mc.maxDiscountMoney));
								_p1 = '已享' + 10 * _mc.discount + '折优惠';
							}
						} else if (_mc.conditionType === '2') {
							// 几元几折
							if (_l.saleOri >= s0 && _u1s > _u1) {
								_u1 = _u1s;
								_l.couponId = _mc.memberCouponId;
								_mc.maxDiscountMoney && (_u1 = Math.min(_u1, _mc.maxDiscountMoney));
								_p1 = '已' + 10 * _mc.discount + '折优惠';
							}
						} else if (_mc.conditionType === '3') {
							if (_l.saleNum >= _mc.minicount && _u1s > _u1) {
								// 几件几折
								_u1 = _u1s;
								_l.couponId = _mc.memberCouponId;
								_mc.maxDiscountMoney && (_u1 = Math.min(_u1, _mc.maxDiscountMoney));
								_p1 = '已享' + 10 * _mc.discount + '折优惠';
							}
						}

						_z1 = '折扣';
					} else if (_l.saleOri >= s0 && s1 > _u1) {
						_l.couponId = _mc.memberCouponId;
						_u1 = s1;
						_p1 = '已享满' + s0 + '减' + s1 + '优惠';
						_z1 = '优惠';
					}
				}

				if (_l.fullCuts &&　_l.fullCuts.length) {
					var _f = _l.fullCuts[0],
						_lad = _f.ladderFlag,
						_sum = _f.sumFlag,
						_rule = _f.rule.split('|'),
						_smax = 0,
						_smin = 0;

					for (var i = 0; i < _rule.length; i++) {
						var _s = _rule[i].split(','),
							s0 = parseFloat(_s[0]),
							s1 = parseFloat(_s[1]);

						if (_l.saleOri >= s0 && s0 > _smax) {
							var _z = '';

							_smax = s0;

							if (_lad == 1) {
								_u2 = s1;
							} else {
								if (_sum == 1) {
									_z = '每';
									_u2 = parseInt(_l.saleOri / s0) * s1;
								} else {
									_u2 = s1;
								}
							}

							_p2 = '已享' + _z + '满' + s0 + '减' + s1 + '优惠';
							_z2 = '满减';
						} else if (_l.saleOri < s0 && (!_smin || s0 < _smin)) {
							_smin = s0;
							_l.couponLess = '满' + s0 + '减' + s1;
						}
					}
				}

				if (_u1 > parseFloat(_u2)) {
					_l.couponUser = _p1;
					_l.couponTxt = _z1;
					_l.saleSub = _u1;
				} else {
					_l.couponUser = _p2;
					_l.couponTxt = _z2;
					_l.saleSub = _u2;
				}
			} else if (_pt == 1) {
				// 优惠券
				for (var i = 0; i < _l.coupons.length; i++) {
					if (_l.saleOri < _l.coupons[i].minimum) {
						_l.couponLess = '满' + _l.coupons[i].minimum + '减' + _l.coupons[i].money + '优惠';
						break;
					}
				}

				for (var i = _l.memberCoupons.length - 1; i >= 0; i--) {
					var s0 = parseFloat(_l.memberCoupons[i].minimum),
						s1 = parseFloat(_l.memberCoupons[i].money);

					if (_l.saleOri >= s0) {
						_l.couponId = _l.memberCoupons[i].memberCouponId;
						_l.saleSub = s1;
						_l.couponUser = '已享满' + s0 + '减' + s1 + '优惠';
						break;
					}
				}
			} else if (_pt == 2) {
				// 满减
				var _f = _l.fullCuts[0],
					_lad = _f.ladderFlag,
					_sum = _f.sumFlag,
					_rule = _f.rule.split('|'),
					_smax = 0,
					_smin = 0;

				for (var i = 0; i < _rule.length; i++) {
					var _s = _rule[i].split(','),
						s0 = parseFloat(_s[0]),
						s1 = parseFloat(_s[1]);

					if (_l.saleOri >= s0 && s0 > _smax) {
						var _z = '';

						_smax = s0;

						if (_lad == 1) {
							// 阶梯
							_l.saleSub = s1;
						} else {
							// 非阶梯
							if (_sum == 1) {
								// 累加
								_z = '每';
								_l.saleSub = parseInt(_l.saleOri / s0) * s1;
							} else {
								// 非累加
								_l.saleSub = s1;
							}
						}

						_l.couponUser = '已享' + _z + '满' + s0 + '减' + s1 + '优惠';
					} else if (_l.saleOri < s0 && (!_smin || s0 < _smin)) {
						_smin = s0;
						_l.couponLess = '满' + s0 + '减' + s1;
					}
				}
			} else if (_pt == 3) {
				// 满赠
				var _f = _l.fullGives;

				for (var i = 0; i < _f.length; i++) {
					if (_l.saleOri < _f[i].minimum) {
						var _s = _f[i],
							_x = '';

						if (_s.couponFlag == 1) {
							_x = _s.couponNameGroup + '优惠券';
							_l.couponId = _s.couponIdGroup;
						} else if (_s.integralFlag == 1) {
							_x = _s.integral + '积分';
						} else if (_s.productFlag == 1) {
							_x = _s.productName;
						}

						_l.couponLess = (_s.type == 1 ? ((_s.sumFlag == 1 ? '每' : '') + '满' + _s.minimum + '元赠送') : '买即赠') + _x;
						break;
					}
				}

				for (var i = _f.length - 1; i >= 0; i--) {
					if (_l.saleOri >= _f[i].minimum) {
						var _s = _f[i],
							_x = '';

						if (_s.couponFlag == 1) {
							_x = _s.couponNameGroup + '优惠券';
							_l.couponId = _s.couponIdGroup;
						} else if (_s.integralFlag == 1) {
							_x = _s.integral + '积分';
						} else if (_s.productFlag == 1) {
							_x = _s.productName;
						}

						_l.couponUser = '已享' + (_s.type == 1 ? ((_s.sumFlag == 1 ? '每' : '') + '满' + _s.minimum + '元赠送') : '买即赠') + _x;
						break;
					}
				}
			} else if (_pt == 4) {
				// 多买优惠
				var _f = _l.fullDiscounts[0],
					_rule = _f.rule.split('|'),
					_type = _f.type;

				for (var i = 0; i < _rule.length; i++) {
					var _s = _rule[i].split(','),
						s0 = parseFloat(_s[0]),
						s1 = parseFloat(_s[1]);

					if (_l.saleNum < s0) {
						var _z = '';

						if (_type == 1) {
							_z = '满' + s0 + '件减' + s1 + '件';
						} else if (_type == 2) {
							_z = s0 + '件' + s1 + '元';
						} else if (_type == 3) {
							_z = s0 + '件' + s1 + '折';
						} else if (_type == 4) {
							_z = '第' + s0 + '件' + s1 + '折';
						}

						_l.couponLess = _z;
						break;
					}
				}

				for (var i = _rule.length - 1; i >= 0; i--) {
					var _s = _rule[i].split(','),
						s0 = parseFloat(_s[0]),
						s1 = parseFloat(_s[1]);

					if (_l.saleNum >= s0) {
						var _z = '';

						if (_type == 1) {
							_z = '满' + s0 + '件减' + s1 + '件';

							for (var j = 0; j < s1; j++) {
								_l.saleSub = (1e3 * _l.saleSub + 1e3 * _l.saleSingle[j]) / 1e3;
							}
						} else if (_type == 2) {
							_z = '满' + s0 + '件' + s1 + '元';
							_l.saleSingle = _l.saleSingle.reverse();

							for (var j = 0; j < s0 * parseInt(_l.saleSingle.length / s0); j++) {
								_l.saleSub = (1e3 * _l.saleSub + 1e3 * _l.saleSingle[j]) / 1e3;
							}

							_l.saleSub = (1e3 * _l.saleSub - 1e3 * s1 * parseInt(_l.saleSingle.length / s0)) / 1e3;
						} else if (_type == 3) {
							_z = '满' + s0 + '件' + s1 + '折';
							_l.saleSub = (1e4 - 1e3 * s1) * _l.saleOri / 1e4;
						} else if (_type == 4) {
							_z = '第' + s0 + '件' + s1 + '折';
							_l.saleSingle = _l.saleSingle.reverse();
							_l.saleSub = (1e4 - 1e3 * s1) * _l.saleSingle[s0 - 1] / 1e4;
						}

						_l.couponUser = '已享' + _z + '优惠';
						break;
					}
				}
			} else if (_pt == 5) {
				//津贴

				var _f = _l.allowanceInfos[0],
					_rule = _f.rule.split(','),
					s0 = parseFloat(_rule[0]),
					s1 = parseFloat(_rule[1]);
				_l.couponLess = '满' + s0 + '减' + s1 + '优惠'
				_l.couponUser = '可用津贴额' + (td.allowanceBalance - td.useallowance) + '元'
				_l.couponTxt = '津贴'

				if (_f.usable) {
					if (_l.saleOri > s0 && td.allowanceBalance) {
						// 累加
						var maxi = parseInt((td.allowanceBalance - td.useallowance) / s1),
							i = parseInt(_l.saleOri / s0) > maxi ? maxi : parseInt(_l.saleOri / s0)
						if(parseInt(_l.saleOri / s0) > maxi){
							_l.saleSub = (td.allowanceBalance - td.useallowance)
						}else{
							_l.saleSub = parseInt(_l.saleOri / s0) * s1
						}
						td.useallowance = td.useallowance + _l.saleSub

						_l.couponUserRight = '已减' + _l.saleSub
					}
				} else {
					_l.couponUserRight = _f.useTip
				}
			}

			_l.saleOri = (_l.saleOri * 1e3 + _l.saleDed * 1e3) / 1e3;
			_l.saleOri = (_l.saleOri * 1e3 + _l.saleMcht * 1e3) / 1e3;
			_l.saleSub = parseInt(parseInt(1e3 * _l.saleSub) / 10) / 100;

			if (_l.saleSub) {
				_l.saleSub = (_l.saleSub * 1e3 + _l.saleDed * 1e3) / 1e3;
				_l.saleRes = (_l.saleOri * 1e3 - _l.saleSub * 1e3 - _l.saleMcht * 1e3) / 1e3;
			} else {
				_l.saleSub = _l.saleDed;
				_l.saleRes = (_l.saleOri * 1e3 - _l.saleDed * 1e3 - _l.saleMcht * 1e3) / 1e3;
			}

			_l.saleSub += _l.saleMcht;
			_l.saleSubTxt = _l.saleSub.toFixed(2);
			_l.saleOriTxt = _l.saleOri.toFixed(2);
			_l.saleResTxt = _l.saleRes.toFixed(2);
		} else {
			_l.saleOri = undefined;
		}
	},

	getCountPlat: function (_f) {
		var ts = this,
			td = ts.data,
			_qt = td.pile,
			_ls = td.list,
			_tp = td.plat,
			_ms = td.memberPlatformCoupons,
			_pn = [],
			_pc = [],
			_unps = [];//特殊商品

		_qt.countRes = 0;
		_qt.countSub = 0;
		_qt.unCountRes = 0;//特殊商品
		_qt.unCountSub = 0;//特殊商品

		_ls.forEach(function (_l) {
			if (_l.saleNum) {
				var _ps = _l.productList,
					_ids = [],
					_arr = [],
					_px = [];

				if (!_l.productList[0].platformCouponUsable) {
					_l.saleRes && (_qt.unCountRes = (_qt.unCountRes * 1e3 + _l.saleRes * 1e3) / 1e3);
					_l.saleSub && (_qt.unCountSub += _l.saleSub);
				}

				_l.saleRes && (_qt.countRes = (_qt.countRes * 1e3 + _l.saleRes * 1e3) / 1e3);
				_l.saleSub && (_qt.countSub += _l.saleSub);

				for (var j = 0; j < _ps.length; j++) {
					var _p = _ps[j];

					_p.tick && _px.push((_p.salePrice * _p.quantity * 1e3 - _p.deductAmount * _p.subDepositOrderQuantity * 1e3 - (_p.couponMcht && _p.couponMcht.money ? _p.couponMcht.money : 0) * 1e3) / 1e3);
				}

				_px = Math.min.apply(null, _px) || 0;
				_px === Infinity && (_px = 0);

				var _pa = [],
					_pd = 0,
					_lj = 0;

				for (var j = 0; j < _ps.length; j++) {
					var _p = _ps[j],
						_pe = _pa.length;

					if (_p.tick) {
						if (_p.productType1Id) {
							_p.divide = (_p.salePrice * _p.quantity * 1e3 - _p.deductAmount * _p.subDepositOrderQuantity * 1e3 - (_p.couponMcht && _p.couponMcht.money ? _p.couponMcht.money : 0) * 1e3) / 1e3;

							if (_pe) {
								for (var k = 0; k < _pe; k++) {
									if (_p.divide > _pa[k].divide) {
										_pa.splice(k, 0, _p);
										break;
									} else if (k === _pe - 1) {
										_pa.push(_p);
									}
								}
							} else {
								_pa.push(_p);
							}

							_pd += _p.divide;
						}

						_ids.push(_ps[j].shoppingCartId);

						_p.couponMcht && _p.couponMcht.id && _pc.push({
							productItemId: _p.productItemId,
							id: _p.couponMcht.id
						});
					}
				}

				for (var j = _pa.length - 1; j >= 0; j--) {
					var _p = _pa[j],
						_ly = parseInt(100 * (_l.saleSub - _l.saleMcht - _l.saleDed)) / 100,
						_lc = parseInt(100 * parseInt(1e3 * (1e3 * _p.divide - 1e3 * parseInt(100 * _ly * _p.divide / _pd) / 100) / 1e3) / 1e3) / 100;

					j && (_lj += _lc);

					if (_p.platformCouponUsable) { //特殊商品
						_pn.push({
							type: _p.productType1Id,
							count: _p.quantity,
							num: _lc
						});
					}
				}
				if (_pa[0].platformCouponUsable) {
					_pn[_pn.length - 1].num = (_l.saleRes * 1e3 - _lj * 1e3) / 1e3;
				}
				if (_ids.length) {
					_qt.dataList.push({
						mchtId: _l.activityType == '101' ? _l.activityAreaId : undefined,
						activityAreaId: _l.activityType == '101' ? undefined : _l.activityAreaId,
						couponId: _l.couponId,
						shopCardId: _ids.join(',')
					});

					_qt.countNum += _l.saleNum;
				}
			}
		});

		_qt.platJson = undefined;

		if (_pc.length) {
			_qt.platJson = {};

			for (var i = 0; i < _pc.length; i++) {
				_qt.platJson[_pc[i].productItemId] = _pc[i].id;
			}
		}

		_qt.countResTotal = _qt.countRes;
		_qt.countSubTotal = _qt.countSub;

		// 平台优惠券
		if (_ms && _ms.length) {
			_tp.availCoupons = [];
			_tp.unavailCoupons = [];

			var _cp1 = { // 全场优惠后
				num: 0,
				id: ''
			},
				_cp2 = { // 品类券优惠后
					num: 0,
					id: ''
				};

			for (var i = 0; i < _tp.availDate.length; i++) {
				var _aj = _tp.availDate[i],
					_ad = _aj.discount;

				_aj.minimum = parseFloat(_aj.minimum);

				if (_aj.couponType == 1) {
					if (_ad) {
						if (_aj.conditionType === '1' || (_aj.conditionType === '2' && (_qt.countRes - _qt.unCountRes) >= _aj.minimum) || (_aj.conditionType === '3' && _qt.countNum >= _aj.minicount)) {
							var _cou = parseInt(parseInt(1e3 * (_qt.countRes - _qt.unCountRes) - 1e3 * (_qt.countRes - _qt.unCountRes) * _ad / 10) / 10) / 100;

							_aj.maxDiscountMoney && (_cou = Math.min(_cou, _aj.maxDiscountMoney));

							_aj.num = (1e4 * _qt.countRes - 1e4 * _cou) / 1e4;

							//无门槛下的特殊商品
							if (_aj.num == _qt.countRes) {
								_tp.unavailCoupons.push(_aj);
								continue
							}


							(!_cp1.num || _aj.num < _cp1.num) && (_cp1 = {
								num: _aj.num,
								id: _aj.id
							});

							_tp.availCoupons.push(_aj);
						} else {
							_tp.unavailCoupons.push(_aj);
						}
					} else if ((_qt.countRes - _qt.unCountRes) >= _aj.minimum) {
						_aj.num = (1e4 * _qt.countRes - 1e4 * _aj.money) / 1e4;

						(!_cp1.num || _aj.num < _cp1.num) && (_cp1 = {
							num: _aj.num,
							id: _aj.id
						});

						_tp.availCoupons.push(_aj);
					} else {
						_tp.unavailCoupons.push(_aj);
					}
				} else if (_aj.couponType == 2) {
					var _num = 0;

					for (var j = 0; j < _pn.length; j++) {
						if (_aj.typeIds != null && _aj.typeIds.split(',').indexOf(_pn[j].type.toString()) != -1) {
							var _nums = 0, // 满足所有品类的总金额
								_count = 0; // 满足所有品类的总件数

							for (var k = 0; k < _pn.length; k++) {
								_aj.typeIds.split(',').indexOf(_pn[k].type.toString()) != -1 && (_nums = (1e3 * _nums + 1e3 * _pn[k].num) / 1e3, _count += _pn[k].count);
							}

							if (_ad) {
								if (_aj.conditionType === '1' || (_aj.conditionType === '2' && _nums >= _aj.minimum) || (_aj.conditionType === '3' && _count >= _aj.minicount)) {
									var _cou = parseInt(100 * _nums * (1e4 - 1e3 * _ad) / 1e4) / 100;

									_aj.maxDiscountMoney && (_cou = Math.min(_cou, _aj.maxDiscountMoney));

									_num = (1e4 * _qt.countRes - 1e4 * _cou) / 1e4;

									(!_cp2.num || _num < _cp2.num) && (_cp2 = {
										num: _num,
										id: _aj.id
									});
								}
							} else if (_nums >= _aj.minimum) {
								_num = (1e4 * _qt.countRes - 1e4 * _aj.money) / 1e4;

								(!_cp2.num || _num < _cp2.num) && (_cp2 = {
									num: _num,
									id: _aj.id
								});
							}
						}
					}
					_num ? (_aj.num = _num, _tp.availCoupons.push(_aj)) : _tp.unavailCoupons.push(_aj);
				}
			}

			var _cpm = Math.min(_cp1.num, _cp2.num);

			!_cpm && (_cpm = Math.max(_cp1.num, _cp2.num));

			if (_cpm) {
				var _cpi = _cp2.num == _cpm ? _cp2.id : _cp1.id;

				_qt.couponPlat = _qt.countRes - _cpm;
				_qt.couponPlat = parseFloat(_qt.couponPlat.toFixed(2));
				_qt.couponPlatId = _cpi;
				_qt.countResTotal = _cpm;
				_qt.countSubTotal = _qt.countSub + _qt.couponPlat;

				for (var j = 0; j < _tp.availCoupons.length; j++) {
					_tp.availCoupons[j].tick = 0;
				}

				for (var j = 0; j < _tp.availCoupons.length; j++) {
					if (_tp.availCoupons[j].id == _cpi) {
						_tp.availCoupons[j].tick = 1;
						break;
					}
				}
			}
		}

		!_qt.countResTotal && (_tp.availCoupons = []);

		// _qt.availCoupons = _tp.availCoupons;
		_qt.availCoupons = $.extend(true, _qt.availCoupons, _tp.availCoupons);

		for (var up = 0; up < _unps.length; up++) {
			_qt.countResTotal += _unps[up].salePrice
		}

		_qt.countResTxt = _qt.countResTotal > 0 ? _qt.countResTotal.toFixed(2) : 0;
		/\.00/.test(_qt.countResTxt) && (_qt.countResTxt = parseInt(_qt.countResTxt));

		_tp.countResTxt = _qt.countResTxt;
		_tp.couponPlat = _qt.couponPlat;

		td.list = _ls;
		td.plat = td.plat;

		ts.setData();

		_f && _f();
	},

	showCouponPlat: function () {
		var ts = this,
			td = ts.data,
			_qt = td.pile,
			_tp = td.plat;

		_tp.couponPlatId = _qt.couponPlatId;
		_tp.countResTotal = _qt.countResTotal;
		/*_tp.availCoupons = _qt.availCoupons || [];*/

		// _tp.couponPlatId = $.extend(true, _tp.couponPlatId, _qt.couponPlatId) || [];
		// _tp.countResTotal = $.extend(true, _tp.countResTotal, _qt.countResTotal) || [];
		// _tp.availCoupons = $.extend(true, _tp.availCoupons, _qt.availCoupons) || [];

		_tp.countResTxt = _qt.countResTxt || 0;
		_tp.couponPlat = _qt.couponPlat;

		if (_tp.countResTxt) {
			_tp.unavailCouponTxt = _tp.unavailCoupons.length + _tp.unavailDate.length;
		} else {
			_tp.unavailCouponTxt = _tp.couponLength;
			_tp.unavailCoupons = _tp.availDate;
		}

		_tp.unavailCouponTxt > 99 && (_tp.unavailCouponTxt = '99+');

		td.plat = _tp;

		ts.setPlat();
		popupShow(popup_plat);
	},

	changePlatTab: function (_this) {
		var ts = this,
			td = ts.data;

		td.plat.tab = $(_this).data('tab');

		ts.setPlat();
	},

	changePlatCoupon: function (_this) {
		var ts = this,
			td = ts.data,
			_qt = td.pile,
			_tp = td.plat,
			_ta = _tp.availCoupons,
			_tg = _ta[$(_this).data('index')];

		if (_tg.tick) {
			_tg.tick = 0;

			_tp.couponPlat = 0;
			_tp.countResTotal = _qt.countRes;
			_tp.couponPlatId = '';
		} else {
			for (var i = 0; i < _ta.length; i++) {
				_ta[i].tick = 0;
			}

			_tg.tick = 1;
			_tp.countResTotal = _tg.num;
			_tp.couponPlat = _qt.countRes - _tg.num;
			_tp.couponPlatId = _tg.id;
		}

		_tp.couponPlat = parseFloat(_tp.couponPlat.toFixed(2));
		_tp.countResTxt = _tp.countResTotal > 0 ? _tp.countResTotal.toFixed(2) : 0;
		/\.00/.test(_tp.countResTxt) && (_tp.countResTxt = parseInt(_tp.countResTxt));

		ts.setPlat();
	},

	// 确定勾选的平台优惠券
	confirmCoupon: function () {
		var ts = this,
			td = ts.data,
			_qt = td.pile,
			_tp = td.plat;

		/*_qt.availCoupons = _tp.availCoupons;*/
		_qt.couponPlat = _tp.couponPlat;
		_qt.couponPlatId = _tp.couponPlatId;
		_qt.countResTotal = _tp.countResTotal;

		_qt.availCoupons = $.extend(true, _qt.availCoupons, _tp.availCoupons);
		// _qt.couponPlat = $.extend(true, _qt.couponPlat, _tp.couponPlat);
		// _qt.couponPlatId = $.extend(true, _qt.couponPlatId, _tp.couponPlatId);
		// _qt.countResTotal = $.extend(true, _qt.countResTotal, _tp.countResTotal);

		_qt.countSubTotal = _qt.countSub + _qt.couponPlat;
		_qt.countResTxt = _qt.countResTotal > 0 ? _qt.countResTotal.toFixed(2) : 0;
		/\.00/.test(_qt.countResTxt) && (_qt.countResTxt = parseInt(_qt.countResTxt));

		td.pile.countResTxt = _tp.countResTxt;
		td.pile.couponPlat = _tp.couponPlat;

		ts.setData();
		closeSelf();
	},

	// 结算
	getOrderFirm: function () {
		var ts = this,
			td = ts.data,
			_qt = td.pile;

		if (!_qt.countNum) {
			delayTip('请先勾选要购买的商品');
			return;
		}

		var _sc = [];

		for (var i = 0; i < _qt.dataList.length; i++) {
			_sc = _sc.concat(_qt.dataList[i].shopCardId.split(','));
		}

		if (_sc.length > 30) {
			popupShow('.popup-tip');
			return;
		}

		var _client = '';

		isAndroid && (_client = 2);
		isiOS && (_client = 1);

		_sc = _sc.join(',');

		setStorage('cart_pay', JSON.stringify({
			payAmount: _qt.countResTotal,
			sourceClient: _client,
			mermberPlatformCouponId: _qt.couponPlatId,
			shopCardIds: _sc,
			dataList: _qt.dataList,
			productCouponJson: _qt.platJson ? encodeURIComponent(JSON.stringify(_qt.platJson)) : ''
		}));
		getUrl('order/firm/index.html', 'self');
	},

	// 显示清空
	showClean: function () {
		var ts = this,
			td = ts.data;
		td.list ? popupShow('.popup-clear') : delayTip('当前购物车空空如也~');
	},

	// 清空
	cleanCart: function () {
		var ts = this,
			td = ts.data;

		var _id = [];
		for (var i = 0; i < td.list.length; i++) {
			for (var j = 0; j < td.list[i].productList.length; j++) {
				_id.push(td.list[i].productList[j].shoppingCartId);
			}
		}

		new ajax(getApi.delShoppingCartList, {
			data: {
				shoppingCartIdList: _id
			},
			success: function (res) {

				closeSelf();
				// ts.onRender();
				location.reload()
			},
			limit: 1
		});

		traffic.setAction(cart.data, {
			type: 8
		});
	},

	// 更多商品优惠
	getCouponMcht: function (_this) {
		var ts = this,
			td = ts.data,
			_d = $(_this),
			_page = _d.data('page'),
			_index = _d.data('index'),
			_li = td.list[_page],
			_lp = _li.productList[_index],
			_lc = _lp.productCoupons,
			_sale = _lp.salePrice - (_lp.quantity > _lp.subDepositOrderQuantity ? 0 : _lp.deductAmount),
			_list = [];

		var _empty = 1,
			_text = '不使用';

		for (var i = 0; i < _lc.length; i++) {
			var _lci = _lc[i];

			parseFloat(_lc[i].money) >= _sale && (_lci.dis = 1);
			_list.push(_lci);
		}

		if (!_lp.couponMcht) {
			_lp.couponMcht = {};
			_text = '不可用';
		}

		for (var i = 0; i < _list.length; i++) {
			delete _list[i].tick;
			delete _list[i].ticked;

			if (_list[i].dis) {
				_list[i].dis = 1;
				_list[i].ticked = 1;
			} else if (_lp.couponMcht.id == _list[i].id) {
				_list[i].tick = 1;
				_empty = 0;
			} else if (_li.useMchtCoupon.indexOf(_list[i].id) != -1) {
				_list[i].ticked = 1;
			}
		}

		td.couponMcht = {
			page: _page,
			index: _index,
			list: _list,
			nouse: {
				text: _text,
				tick: _empty
			},
			obj: ''
		}

		ts.setMcht();

		popupShow(popup_mcht);
	},

	// 选择商品优惠
	getMchtTick: function (_this) {
		var ts = this,
			td = ts.data,
			_d = $(_this),
			_index = _d.data('index'),
			_ls = new Object(td.list),
			_cl = td.couponMcht,
			_clt = _cl.list,
			_cn = _cl.nouse,
			_cli = _clt[_index];

		var _tk = '';

		if (_index === undefined) {
			if (!_cn.tick) {
				for (var i = 0; i < _clt.length; i++) {
					if (_clt[i].tick) {
						_tk = _clt[i];
						delete _clt[i].tick;
						break;
					}
				}

				for (var i = 0; i < _ls[_cl.page].useMchtCoupon.length; i++) {
					if (_ls[_cl.page].useMchtCoupon[i] == _tk.id) {
						_ls[_cl.page].useMchtCoupon.splice(i, 1);
						break;
					}
				}

				_cn.tick = 1;

				_ls[_cl.page].productList[_cl.index].couponMcht.ids = _ls[_cl.page].productList[_cl.index].couponMcht.id;
				_ls[_cl.page].productList[_cl.index].couponMcht.id = '';

				for (var i = 0; i < _ls[_cl.page].productList.length; i++) {
					var _lcp = _ls[_cl.page].productList[i];

					(!_lcp.couponMcht || !_lcp.couponMcht.id) && (_lcp.couponMcht = {});
				}

				td.couponMcht.list = _clt;
				td.couponMcht.nouse = _cn;

				ts.setMcht();

				_cl.obj = _ls;
			}
		} else if (!_cli.tick && !_cli.ticked) {
			for (var i = 0; i < _clt.length; i++) {
				if (_clt[i].tick) {
					_tk = _clt[i];
					delete _clt[i].tick;
					break;
				}
			}

			for (var i = 0; i < _ls[_cl.page].useMchtCoupon.length; i++) {
				if (_ls[_cl.page].useMchtCoupon[i] == _tk.id) {
					_ls[_cl.page].useMchtCoupon.splice(i, 1);
					break;
				}
			}

			_cli.tick = 1;
			delete _cn.tick;

			!_cli.id && (_cli.id = _cli.ids);
			_ls[_cl.page].useMchtCoupon.push(_cli.id);
			_ls[_cl.page].productList[_cl.index].couponMcht = _cli;

			var _lcl = _ls[_cl.page].useMchtCoupon.length,
				_lpl = _ls[_cl.page].productCouponList.length;

			for (var i = 0; i < _ls[_cl.page].productList.length; i++) {
				var _lcp = _ls[_cl.page].productList[i];

				i != _cl.index && _lcp.couponMcht && !_lcp.couponMcht.id && _lcl === _lpl && delete _lcp.couponMcht;
			}

			td.couponMcht.list = _clt;
			td.couponMcht.nouse = _cn;

			ts.setMcht();

			_cl.obj = _ls;
		}
	},

	getMchtSubmit: function () {
		var ts = this,
			td = ts.data,
			_dc = td.couponMcht,
			_dj = _dc.obj;

		if (_dj) {
			td.list = _dj;

			ts.setData();
			

			if (td.list[_dc.page].preferentialType == 5) {
				ts.allowanceCount(td.list)
			} else {
				ts.getCount(td.list[_dc.page]);
			}
			ts.getCountPlat();
		}

		closeSelf();
	},

	//特殊商品表示显示
	specialCheck: function () {
		var ts = this,
			td = ts.data;
		td.specialCheck = !td.specialCheck
		setStorage('cartSpecialCheck', td.specialCheck)
		ts.setData()
		ts.setPlat()
	}
};

getTpl(function () {
	cart.init();
});

function onShow() {
	traffic.setPv(cart.data);
}