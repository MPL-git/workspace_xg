var my_set = $('.page[data-id="my_set"]:visible'),
	my_set_con = my_set.find('.content');

isWeiXin && my_set.find('.set input').hide();


var setSelf = {
	data: {
		bir: 1,
		sex: 1,
		areadata:[]
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		traffic.setPv(_td, {
			pageType: '53'
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

				my_set_con.html(template('my_set_list', e));

				isAndroid && $('#my_set_bir').addClass('set-self-bir-anz');
				isiOS && $('#my_set_bir').addClass('set-self-bir-ios');

				// 性别
				my_set.find('.popup-sex div[data-type=' + my_set_con.find('.set-self-sex b').data('type') + ']').addClass('active');

				my_set_con.find('.set-self-sex').click(function (e) {
					e.preventDefault();
					$('.popup-sex').addClass('show');
				});

				// 生日
				my_set_con.find('.set-self-bir input').blur(function () {
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
						_sex = my_set_con.find('.set-self-sex b');

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
				
				_ts.post(0,function(pro){
					console.log(pro)
					_td.areadata = pro
					console.log(e.returnData.province)
					for(var i=0;i<pro.length;i++){
						if(pro[i].areaId == e.returnData.province){
							_td.tbindex0 = i
							break
						}
					}
					//初始化 未知城市
					if(_td.tbindex0==undefined){
						_td.tbindex0 = 0
						_td.tbindex1 = 0
						_ts.areaPicker()
						return
					}
					_ts.post(e.returnData.province,function(city){
						console.log(city)
						_td.areadata[_td.tbindex0].child = city
						for(var j=0;j<city.length;j++){
							if(city[j].areaId == e.returnData.city){
								_td.tbindex1 = j
								break
							}
						}
						if(_td.tbindex1==undefined){
							_td.tbindex1 = 0
						}
						_ts.areaPicker()
					});
				});
			}
		});
		
		
	},
	areaPicker: function(){
		var _ts = this,
			_td = _ts.data;
			require(['multiPicker'],function(){
			new MultiPicker({
				input: 'multiPickerInput2',//点击触发插件的input框的id
				container: 'targetContainer2',//插件插入的容器id
				jsonData: _td.areadata,
				int:[_td.tbindex0, _td.tbindex1],
				success: function (arr) {
					console.log(JSON.stringify(arr));
					if(arr.length>2){
						arr = arr.splice((arr.length-2),2)
					}

					new ajax(getApi.saveMemberAreaInfo,{
					data:{
						memberId:member.id,
						province:arr[0].id,
						city:arr[1].id,
					},
					success:function(){
						$("#multiPickerInput2").parent().find("a>div>b").text(arr[0].value + arr[1].value)
					}
					})
				},//回调
				checkRangepost: function(id,fn){
					_ts.post(id,function(city){
						fn(city)
					})
				}
			});
			$("#multi-picker-targetContainer2-0").css("transform","translate3d(0px, -" + 40 * _td.tbindex0 + "px, 0px)")
			$("#multi-picker-targetContainer2-1").css("transform","translate3d(0px, -" + 40 * _td.tbindex1 + "px, 0px)")
		  })
	},
	post: function (e, _f) {
		if (e === '') return;
		
		
		new ajax(getApi.getAreaByParentId, {
			data: {
				parentId: e
			},
			async: false,
			success: function (res) {
				_f(res.returnData);
			}
		},false);
	},
};


getTpl(function () {
	setSelf.init();
	
});

// 设置密码
function registerRevise(e) {
	getUrl('register/revise/index.html');
	setStorage('haspwd', e);
}

function selfPicChange(_this) {
	var _files = _this.files[0],
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

			selfPicPost(c.toDataURL('image/jpeg', 1), _files.name);
		}
	}
}

function selfPicPost(_src, _name) {
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
						my_set_con.find('figure img').attr('src', res1.returnData.pic);
						$('.page[data-page="my"').remove();
					}
				}
			});
		}
	});
}

// 绑定微信
function reBindingWx(e) {
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
}

// 退出
function outLogin() {
	new ajax(getApi.loginout, {
		success: function (res) {
			member.id = '';
			member.token = '';
			win_lc.removeItem('member');
			delayTip('退出成功');
			getUrl('home', true);
		}
	});
}