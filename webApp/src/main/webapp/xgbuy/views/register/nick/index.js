var register_nick = $('.page[data-id="register_nick"]:visible');

$(function () {
	register_nick.find('form').submit(function (e) {
		e.preventDefault();
		var _serialize = JSON.parse(formName($(this).serialize())),
			_nick = decodeURI(_serialize.nick);

		new ajax(getApi.updateUserInfo, {
			data: {
				memberId: member.id,
				nick: _nick
			},
			success: function (res) {
				member.nick = _nick;
				getUrl('set/self', true);

				$('.page[data-page="my"]').remove();
			},
			limit: 1
		});
	});
});