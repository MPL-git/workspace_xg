var pictuerViews={};
function ImageUploader(){
	this.initUploader=function initUploader(){
		
	    var uploader= WebUploader.create({

	    paste: 'document.body',
	    // swf文件路径
	    swf: ctx+'/static/images/webuploader/Uploader.swf',

	    // 文件接收服务端。
	    server: ctx+'/common/fileUpload',

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '.filePicker',
	    duplicate:true,
	    // 只允许选择图片文件。
	    accept: {
	        title: 'Images',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
	    },
	    
	});

	uploader.onFileQueued = function( file ) {
	    	 var oFReader = new FileReader();
	    	 var $picturesList=$(file.source._refer).parent().children('.title-top');
			 oFReader.onload = function(oFREvent) {
				var $li = $('<li class="pic_li" id="pic_li_'+file.id+'" draggable="true" ondragstart="drag(event)"><img  id="pic_'+file.id+'" src="'+oFREvent.target.result+'"></li>');
				var $btns = $('<div class="pic-btn-container"></div>').appendTo( $li );
				var $removeBtn=$('<span  class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>').appendTo($btns);
				$picturesList.append( $li );
				pictuerViews["picturesViewer_"+$picturesList.attr("id")].destroy();
				pictuerViews["picturesViewer_"+$picturesList.attr("id")] = new Viewer($picturesList.get(0), {
		    		shown: function () {
		    			var width=document.documentElement.clientWidth;
		    			var height=document.documentElement.clientHeight;
		    			$(".viewer-container").height(height);
		    			$(".viewer-container").width(width-20);
		    			$(".viewer-container").css("top",-$('.modal-dialog').offset().top);
		    			$(".viewer-container").css("left",-$('.modal-dialog').offset().left);
		    		  }
		    		
		    	});
		    	
		    	$li.on( 'mouseenter', function() {
		            $btns.stop().animate({height: 20});
		        });

		    	$li.on( 'mouseleave', function() {
		            $btns.stop().animate({height: 0});
		        });
		    	
	            $removeBtn.on( 'click', function() {
	            			$li.remove();
	                        uploader.removeFile( file );
	                }
	            );

		    	
			};
			
			oFReader.readAsDataURL(file.source.source);
		
		
		

	};

	uploader.on( 'uploadSuccess', function( file,response ) {
	    if(response.returnCode=='0000'){
	    	$("#pic_li_"+file.id).attr("pic_path",response.returnData);
	    }
	});

	return uploader;

	};
	
	this.uploader=this.initUploader();
	this.upload=function(uploadFinishedCallback){
		this.uploader.on("uploadFinished",function(){
			uploadFinishedCallback();
		});
		this.uploader.upload();
	};

}

function drag(ev) {
	ev.dataTransfer.setData("Text", $(ev.target).parent().attr("id"));
}
function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("Text");
	if ($(ev.target).prop("tagName") == "UL") {
		$(ev.target).append(document.getElementById(data));
	}
	if ($(ev.target).prop("tagName") == "IMG") {
		$(ev.target).parent().before(document.getElementById(data));
	}

}

function allowDrop(ev) {
	ev.preventDefault();
}

$(function(){
	// $(".pic_li").each(function(index,element){
	// 	$(element).on( 'mouseenter', function() {
	// 		$(element).children(".pic-btn-container").stop().animate({height: 20});
	// 	});
	//
	// 	$(element).on( 'mouseleave', function() {
	// 		$(element).children(".pic-btn-container").stop().animate({height: 0});
	// 	});
	// });
	//
	// $(".pic-remove-icon").each(function(index,element){
	// 	$(element).on( 'click', function() {
	// 		$(element).parent().parent().remove();
	// 	});
	//
	// });
	
	    $(".title-top").each(function(index,element){
	    	pictuerViews["picturesViewer_"+$(element).attr("id")] = new Viewer($(element).get(0), {
	    		shown: function () {
	    			// var width=document.documentElement.clientWidth;
	    			// var height=document.documentElement.clientHeight;
	    			// $(".viewer-container").height(height);
	    			// $(".viewer-container").width(width-20);
	    			$(".viewer-container").css("top",-$('.modal-dialog').offset().top);
	    			$(".viewer-container").css("left",-$('.modal-dialog').offset().left);
	    		  }
	    		
	    	});
	    });
	
});
