var a_invite_page = $('.page[data-id="activity_novaplan_pages_invite"]:visible'),
	a_invite_page_scroll = a_invite_page.find('.scroll-y');

var invite_share_share = {
	title: '送你500元大礼包，快来领取吧',
	content: '注册即送500元大礼包，更有爆款商品新人秒杀价，点击领取！',
	pictureUrl: '',
	url: ''
};

var aInviteApi = {
	data: {

	},

	init: function () {
		var _ts = this,
			_td = _ts.data;

		_ts.main();
	},

	main: function () {
		var _ts = this,
			_td = _ts.data;

		new ajax(getApi.getInvitationFriendPage, {
			success: function (res) {
				var _list = res.returnData,
					_text;

				_text = _list.invitationRuelH5.split('1')[1];
				_text = _text.split('</BODY>')[0];
				_text = '1' + _text;
				_list.rule = _text.split('</br>');
				_list.isWeiXin = isWeiXin;
				_list.isPC = isPC()
				console.log(_list.isPC)

				_td.imageArrayByte = 'data:image/png;base64,' + _list.imageArrayByte
				a_invite_page_scroll.html(template('a_invite_list', _list));

				var copy_bt = a_invite_page.find('.copy-bt');

				for (var i = 0; i < copy_bt.length; i++) {
					clipHnad(copy_bt[i]);
				}

				invite_share_share.url = _list.qrCodeUrl;
				invite_share_share.content = _list.shareContent;
				invite_share_share.title = _list.shareTitle;

				shareHand.update(invite_share_share);
			}
		});
	},

	share: function () {
		shareHand.show({
			wx: 1
		});
	},
	
	getDownload: function(url){
				var img = document.createElement('img');
				img.src = url
			$("body .page:visible ").append(img); 
			img.click();
			delayTip("长按保存图片")
	}
};

getTpl(function () {
	aInviteApi.init();
});