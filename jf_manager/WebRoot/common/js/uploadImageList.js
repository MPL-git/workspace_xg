var uploadImageList = {
	init: function() {
		uploadImageList.bindEvent();
	},
	
	bindElementEvent: function(obj) {
		var li = $(obj);
//		$("img", li).click(function() {
//			uploadImageList.setDefault(this);
//		});
		$(".del", li).click(function() {
			uploadImageList.removeImage(this);
		});
	},
	
	bindEvent: function() {
		var ul = $(".upload_image_list");
		$("li", ul).each(function(index, item) {
			uploadImageList.bindElementEvent(item);
		});
	},
	
	setDefault: function(obj) {
		var ul = $(".upload_image_list");
		$(".def", ul).remove();
		
		var _self = $(obj);
		_self.parents("p").append("<a class=\"def\">默认图</a>");
	},
	
	addImage: function(prefixPath, path,listId) {
		var ul;
		if(listId){
			ul= $("#"+listId);
		}else{
			ul= $(".upload_image_list");
		}

		var html = [];
		html.push("<li>");
		html.push("<p>");
		html.push("<img src=\"" + prefixPath + path + "\" path=\"" + path + "\"/>");
//		if($("li", ul).length == 0) {
//			html.push("<a class=\"def\">默认图</a>");
//		}
		html.push("</p>");
		html.push("<a href=\"javascript:void(0);\" class=\"del\">删除</a>");
		html.push("</li>");
		ul.append(html.join(""));
		uploadImageList.bindElementEvent($("li:last", ul));
	},
	
	removeImage: function(obj) {
		$(obj).parents("li").remove();
	}
};

$(function() {
	uploadImageList.init();
	//uploadImageList.addImage("/suyuan_manager/file_servelt/b.jpg");
});