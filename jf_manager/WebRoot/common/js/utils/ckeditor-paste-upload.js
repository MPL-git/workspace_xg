/************************************************
 *    ckeditor 黏贴图片上传服务端控件
 *    
 ***********************************************/

//上传控件的命名空间
var UPLOAD_PASTE = function(editor,url){
	this.uploadUrl = url;
	this.editor = editor;
	this.extArray = new Array(".gif", ".jpg", ".png");
};

/**
 * 初始化上传控件
 * @param editor ck编辑器对象
 * @param url 上传服务地址
 */
UPLOAD_PASTE.prototype.init = function(editor,url){
	this.uploadUrl = url;
	this.editor = editor;
	
};

/**
 * 绑定ck编辑器的黏贴事件
 * 功能：1/ 在黏贴的时候，对黏贴内容进行分析，有图片的进行上传；
 *       2/ 上传完成之后，将图片的标签进行替换
 * @param editor 要绑定的的编辑器
 * @param url 上传服务地址
 */
UPLOAD_PASTE.prototype.bandPaste = function(){
	var parent = this;
	
	//绑定黏贴事件
	this.editor.on("paste",function(env){
		//黏贴数据
		var pasteData = env.data.dataValue;
		var newPasteData = env.data.dataValue;
		
		var startIndex = 0;
		for(var i=0;i<999;i++){
			startIndex = pasteData.indexOf("<img",startIndex);
			if(startIndex > 0){
				var endIndex = pasteData.indexOf(">",startIndex)+1;
				var paseteImg = pasteData.substring(startIndex,endIndex);
				//执行一个文件上传,并且返回新的图片标签
				var newImg = parent.uploadImage(paseteImg);
				newPasteData = newPasteData.replace(paseteImg,newImg);
				startIndex = endIndex;
			}else{
				break;
			}
		}
		env.data.dataValue = newPasteData;
	});
};

/**
 * 上传文件
 */
UPLOAD_PASTE.prototype.uploadImage = function(imgTagStr){
	if(this.uploadUrl != "" && this.uploadUrl != null){
		if(imgTagStr == null || imgTagStr == ""){
			return "";
		}else{
			var src = $(imgTagStr).attr("src");
			var divObj = document.createElement("div");
			divObj.style.display = "none";
			var uploadForm = "<form action='" + this.uploadUrl +"' method='post' id='ckeditor_paste_upload_form' enctype='multipart/form-data'>" +
							  "<input type='file' id='ckeditor_paste_upload_input' name='ckeditor_paste_image' value='" + src + "'>" +
							  "</form>";
			divObj.innerHTML = uploadForm;
			document.body.appendChild(divObj);
			
			//异步上传文件
			var result = "";
			$.ajaxFileUpload({
				url : this.uploadUrl,							//上传地址
				secureuri:false,
				fileElementId : 'ckeditor_paste_upload_input',  //文件选择框id
				dataType:'json',								//服务器返回的格式，可以是json
				async: false,
				success :function(data, status){				//成功执行的方法
					var filePath = data.filePath;
					result = "<img src='" + filePath + "'>";
				},
				error :function(data, status, e){				//失败后执行的方法
					alert("文件上传失败！");
				}
			});
			return result;
		}
	}else{
		alert("你还没有设置上传文件地址，无法上传文件！");
		return "";
	}
};


