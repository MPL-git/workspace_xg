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

(function ($) {
    var applicationPath = ctx;
    function initWebUpload(item, options) {
        if (!WebUploader.Uploader.support()) {
            var error = "上传控件不支持您的浏览器！请尝试升级flash版本或者使用Chrome引擎的浏览器。<a target='_blank' href='http://se.360.cn'>下载页面</a>";
            if (window.console) {
                window.console.log(error);
            }
            $(item).text(error);
            return;
        }
        var target = $(item);//容器
        var divId = target.prop("id");
        var html = [];
        html.push('<ul class="docs-pictures clearfix td-pictures" style="margin-left:'+options.ulMarginLeft+'" id="'+divId+'_pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">');
        var imgPaths = options.imgPaths||[];
        if(imgPaths.length>0){
        	var imgPathArray = imgPaths.split(",");
        	if(imgPathArray.length>0){
        		for(var i=0;i<imgPathArray.length;i++){
        			html.push('<li id="'+divId+'_pic_li_'+i+'" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="'+imgPathArray[i]+'">'+
	        					'<img  src="'+ctx+'/file_servelt'+imgPathArray[i]+'">'+
	        					'<div class="pic-btn-container" style="height: 0px;">'+
	        						'<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>'+
	        					'</div>'+
        					 '</li>');
        		}
        	}
        }
        html.push('</ul>');
        html.push('<input type="hidden" id="'+divId+'_imgPaths" value="'+imgPaths+'">');
        if(options.divMarginLeft){
        	html.push('<div id="'+divId+'_filePicker" class="filePicker" style="margin-left: '+options.divMarginLeft+';">选择图片</div>');
        }else{
        	html.push('<div id="'+divId+'_filePicker" class="filePicker" style="margin-left: 165px;">选择图片</div>');
        }
        html.push('<span id="'+divId+'ErrorMsg" style="vertical-align: top;margin-left: 10px;color: red;"></span>');
        target.append(html.join(""));
        
        $("#"+divId+"_filePicker").on('click',function(){
        	$("#"+divId+"ErrorMsg").text("");
        });
        
        target.find("li").on('mouseenter', function() {
            $(this).find("div").first().stop().animate({height: 20});
        });

        target.find("li").on('mouseleave', function() {
        	$(this).find("div").first().stop().animate({height: 0});
        });
	    	
        target.find("span").on('click', function() {
  		   $(this).closest("li").remove();
        });
        
        var picturesViewer = new Viewer(document.getElementById(divId+'_pictures-list'), {});
        //创建默认参数
        var defaults = {
            auto:true,
            hiddenInputId: "uploadifyHiddenInputId", // input hidden id
            onAllComplete: function (event) { }, // 当所有file都上传后执行的回调函数
            onComplete: function (event) { },// 每上传一个file的回调函数
            innerOptions: {},
            fileNumLimit: undefined,//验证文件总数量, 超出则不允许加入队列
            fileSizeLimit: undefined,//验证文件总大小是否超出限制, 超出则不允许加入队列。
            fileSingleSizeLimit: undefined,//验证单个文件大小是否超出限制, 超出则不允许加入队列
            PostbackHold: false
        };
        var opts = $.extend(defaults, options);
        var pickerid = divId+"_filePicker";
        var webuploaderoptions = $.extend({
            // swf文件路径
            swf: applicationPath + '/static/images/webuploader/Uploader.swf',
            // 文件接收服务端。
            server:  applicationPath+'/common/fileUpload',
//          deleteServer:'/Home/DeleteFile',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#' + pickerid,
            //不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
            resize: false,
            fileNumLimit: opts.fileNumLimit,
            fileSizeLimit: opts.fileSizeLimit,
            fileSingleSizeLimit: opts.fileSingleSizeLimit
        },opts.innerOptions);
        var uploader = WebUploader.create(webuploaderoptions);
        uploader.onFileQueued = function( file ) {
	    	 var oFReader = new FileReader();
			 oFReader.onload = function(oFREvent) {
				var $li = $('<li class="pic_li" id="pic_li_'+file.id+'" draggable="true" ondragstart="drag(event)"><img id="pic_'+file.id+'" src="'+oFREvent.target.result+'"></li>');
				var $btns = $('<div class="pic-btn-container"></div>').appendTo( $li );
				var $removeBtn=$('<span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>').appendTo($btns);
				$('#'+divId+'_pictures-list').append( $li );
				if(options.preview){
					picturesViewer.destroy();
					picturesViewer = new Viewer(document.getElementById(divId+'_pictures-list'), {
						shown: function () {
							var width=document.documentElement.clientWidth;
							var height=document.documentElement.clientHeight;
							$(".viewer-container").height(height);
							$(".viewer-container").width(width-20);
							if(options.viewerTop){
								$(".viewer-container").css("top",-$('.modal-dialog').offset().top+options.viewerTop);
							}else{
								$(".viewer-container").css("top",-$('.modal-dialog').offset().top);
							}
							if(options.viewerLeft){
								$(".viewer-container").css("left",-$('.modal-dialog').offset().left+options.viewerLeft);
							}else{
								$(".viewer-container").css("left",-$('.modal-dialog').offset().left);
							}
						}
					});
				}
		    	
		    	$li.on('mouseenter', function() {
		            $btns.stop().animate({height: 20});
		        });
	
		    	$li.on('mouseleave', function() {
		            $btns.stop().animate({height: 0});
		        });
			    	
	            $removeBtn.on('click', function() {
          		   $li.remove();
                   uploader.removeFile( file );
                });
			    	
			};
			oFReader.readAsDataURL(file.source.source);
			
	    };
		
	    uploader.on('uploadSuccess', function(file,response ) {
	        if(response.returnCode=='0000'){
	        	$("#pic_li_"+file.id).attr("pic_path",response.returnData);
	        }
	    });
	    
	  	//当所有文件上传结束时触发
	    uploader.on('uploadFinished',function(){
	    	uploader.reset();
	    });
        return uploader;
    }
    
    $.fn.getImgPaths = function (options) {
        var ele = $(this);
        var filesdata = ele.find("li.pic_li");
        var filesAddress = [];
        filesdata.each(function () {
            filesAddress.push($(this).attr("pic_path"));
        });
        return filesAddress.join(",");
    }

    $.fn.myWebUploader = function (options) {
        var uploader = initWebUpload(this, options);
        return uploader;
    }
})(jQuery);