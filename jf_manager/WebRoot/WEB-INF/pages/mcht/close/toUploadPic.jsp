<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/move.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 13px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:20px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
.table-title-link{color: #1B17EE;}
.baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
tr.title-first td{background-color:#DCF9FF;}
tr.title-second td{background-color:#FFFB94;font-size:15px;}
tr.title-second td span{margin-right:0px;}
tr.title-third td{font-size:15px;}
.baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
.LogInfo tr td img{margin:10px 20px 0 0;}
.color01{color: #FF0000;}
.color02{color: #797979;}

*
        {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        
        #pictures
        {
            width: 570px;
            position: relative;
            margin: 10px auto;
        }
        #pictures li
        {
            width: 90px;
            height: 90px;
            float: left;
            list-style: none;
            margin: 10px;
        }
        #pictures li:hover
        {
            border-color: #9a9fa4;
            box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.85);
        }
        #pictures .active
        {
            border: 1px dashed red;
        }

</style>
<script type="text/javascript">
var viewerPictures;


function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}



//保存
function save(){
	//图片地址 
	var picPaths=[];
	$("#aaa img").each(function(){
		picPath=$(this).attr("path");
		if(picPath!=null){
		picPaths.push(picPath);
		 }
		}); 
	
	var dataJson = $("#picForm").serializeArray();
	dataJson.push({"name":"contractPics","value":JSON.stringify(picPaths)});
	
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/close/contractPicUpload.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : dataJson,
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("提交成功");
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
			
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}


//图片格式验证
function ajaxFileUploadImg(_this) {
	var file = document.getElementById(_this.id).files[0]; 
    if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg|gif|GIF)$/.test(file.type)){  
    	commUtil.alertError("图片格式不正确！");
        return;
    }
 	
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() {
        	ajaxFileUpload(_this);
        };
        image.src = e.target.result;
    }
    reader.readAsDataURL(file);  
}

function ajaxFileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "BrandInvoicePictures",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#aaa").append('<li><p><img src="${pageContext.request.contextPath}/file_servelt'+result.FILE_PATH+'" path="'+result.FILE_PATH+'"></p><a href="javascript:void(0);" class="del">删除</a></li>');
				/* $(".del").live('click',function(){
					$(this).closest("li").remove();
				});

				//对viewer进行重新赋值
					mchtBrandInvoicePic_viewer.destroy();
					mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {}); */
					
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}

window.onload = function () {
    var oUl = document.getElementById("pictures");
    
}
</script>

<html>
<body>
<form id="picForm" method="post" action="${pageContext.request.contextPath}/mchtContract/contractPicUpload.shtml">
	<input type="hidden" name="id" id="mchtCloseId" value="${id}">
	<table class="gridtable">
	 	<tr>
				<td class="title" style="width:100px;">终止合同</td>
				<td  colspan="3" align="left" class="l-table-edit-td" id="mchtBrandInvoicePic_viewer">

			    	<t:imageList name="mchtBrandInvoicePictures" list="${contractPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
			    	
			    	<ul id="aaa" name="mchtPlatformAuthPictures" class="upload_image_list">
			    	<c:forEach var="approvalApplication" items="${mchtCloseApplicationPics}">
			    	<li><p><img src="${pageContext.request.contextPath}/file_servelt${approvalApplication.pic}" path="${approvalApplication.pic }"></p><a href="javascript:void(0);" class="del">删除</a></li>
			    	</c:forEach>
			    	</ul>
					<div style="float: left;height: 105px;margin: 10px;">
						<input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandInvoicePictures" data_value="mchtBrandInvoicePic_viewer" name="file" onchange="ajaxFileUploadImg(this);" /> 
						<input type="button" style="width: 70px;" value="上传图片" />
					</div>
				</td>
			</tr>
		
		
		
		<tr>
			<td class="title" style="text-align: center;">操作</td>
			<td colspan="3" align="left" class="l-table-edit-td">
				<div id="btnDiv">
					<input name="btnSubmit" type="button" id="confirm" style="float:left;" class="l-button l-button-submit"  onclick="save()"  value="提交" />
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
				</div>
			</td>
	    </tr>
	</table>	
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
