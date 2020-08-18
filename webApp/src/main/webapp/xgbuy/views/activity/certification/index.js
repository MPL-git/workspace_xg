var certification = $('.page[data-id="activity_certification"]:visible'),
	certification_con = certification.find('.content .touchfix'),
	certification_header = certification.find('header');

var certification = {
	data: {
		pics: [],
		anewCommitStatus: false,
		pic: ''
	},

	init: function () {
		var _ts = this,
			_td = _ts.data;
		if (member.sys == 'android') {
			checkAppVer(67, function () {
				window.H5PluginManager.interactiveWithApp(
					JSON.stringify({
						"linkType": 3,//大学生认证
						"linkValue": {
							'sourceType': 1 //0相册和拍照 1拍照 2相册
						},
						"curturnVersion": 1,
						"extraMessage": "jsonString",
					})
				);
			}, true);
		} else if (member.sys == 'ios') {
			var _obj = {
				"linkType": 5,//调用相机
				"linkValue": {
				},
				"curturnVersion": 1,
				"extraMessage": "jsonString",
			}
			withApp(_obj)
		}

		_ts.getInfo()

	},
	getInfo: function (as) {
		var _ts = this,
			_td = _ts.data;
		new ajax(member.app ? getApi.getCollegeStudentCertificationH5 : getApi.getCollegeStudentCertification, {
			data: {
				anewCommitStatus: as ? as : _td.anewCommitStatus,
				memberId: member.id
			},
			success: function (res) {
				console.log(res)
				certification_con.html(template('cer_tpl', res.returnData))
				_ts.bindUpfile()
				$('.page:visible .scroll-fix').scrollTop(0);
			}
		})
	},
	bindUpfile: function () {
		var _ts = this,
			_td = _ts.data,
			settled_detail_file = certification_con.find('.up-btn input');

		settled_detail_file[0] && new uploadFile(settled_detail_file[0], {
			url: getApi.mchtUploadPic,
			success: function (res, result) {
				console.log(settled_detail_file[0])
				_td.pics[0] = result;
				_td.pic = res
				_ts.setImg();
			}
		});
	},
	setImg: function () {
		var _ts = this,
			_td = _ts.data,
			_s = '';
		_s += '<div><img id="img1" src="' + _td.pics[0] + '"></div>'
		certification_con.find('.up-img').html(_s);
		img1.onload = function () {
		}
	},
	submit: function () {
		var _ts = this,
			_td = _ts.data;
		if (_td.pic == "") {
			delayTip('请上传手持学生证照片！');
			return
		}
		new ajax(member.app ? getApi.addCollegeStudentCertificationH5 : getApi.addCollegeStudentCertification, {
			data: {
				pic: _td.pic,
				memberId: member.id
			},
			success: function (res) {
				console.log(res)
				delayTip('已提交审核，请耐心等待！');
				setTimeout(function(){
					var _obj = {
						"linkType": 4, //关闭webview
						"linkValue": {
						},
						"curturnVersion": 1,
						"extraMessage": "jsonString",
					}
					withApp(_obj, function () {
						back();
					})
				},1000)
				
			}
		})
	},
	fileEvent: function (e) {
		var _ts = this,
			_td = _ts.data;
		console.log(e)
		e.stopPropagation();
		var HWcamera = win_lc.getItem("HWcamera")

		if (member.sys=="ios" && HWcamera == "false") {
			console.log('请开启手机相机权限！')
			delayTip('请开启手机相机权限！');
		} else {
			$(e.currentTarget).prev().click()
		}
	}


};

getTpl(function () {
	certification.init();
});

function onShow() {
	post_id === 'activity_certification' && getUrl('', 'self');
}

