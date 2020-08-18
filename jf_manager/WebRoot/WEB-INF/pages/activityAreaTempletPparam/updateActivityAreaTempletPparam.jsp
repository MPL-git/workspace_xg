<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
</style>
<script type="text/javascript">

	$(function (){
	    $("#form1").ligerForm();
	});	

	$(function ()  { 
	    $.metadata.setType("attr", "validate");
	            var v = $("#form1").validate({
	                errorPlacement: function (lable, element)
	                {
	                    if (element.hasClass("l-textarea"))
	                    {
	                        element.ligerTip({ content: lable.html(), target: element[0] }); 
	                    }
	                    else if (element.hasClass("l-text-field"))
	                    {
	                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
	                    }
	                    else
	                    {
	                        lable.appendTo(element.parents("td:first").next("td"));
	                    }
	                },
	                success: function (lable)
	                {
	                    lable.ligerHideTip();
	                    lable.remove();
	                },
	                submitHandler: function (form)
	                {   
	                	var code = $("#code").val();
	                	if(code == '') {
	                		$.ligerDialog.question('模板代码不能为空！');
	            			return ;
	                	} 
	            		//判断模版图片
// 	                	var pictures = [];
// 	            		var lis = $(".upload_image_list").find("li");
// 	            		lis.each(function(index, item) {
// 	            			var path = $("img", item).attr("path");
// 	            			var def = ($(".def", item).length == 1 ? "1" : "0");
// 	            			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
// 	            			pictures.push(pic);
// 	            		});
// 	            		if(pictures.length <= 0){
// 	            			$.ligerDialog.question('请上传模版图片！');
// 	            			return ;
// 	            		}
	                	form.submit();
	                }
	            });
	  });    
	
	function ajaxFileUpload() {
		$.ajaxFileUpload({
// 			url: contextPath + '/service/common/ajax_upload.shtml',  
			url: contextPath + '/service/common/ajax_upload.shtml?fileType=4',  
			secureuri: false,
			fileElementId: "photo",
			dataType: 'json',
			success: function(result, status) {
				if(result.RESULT_CODE == 0) {
					uploadImageList.addImage(contextPath + "/file_servelt", result.FILE_PATH);
				} else {
					$.ligerDialog.alert(result.RESULT_MESSAGE);
				}
			},
			error: function(result, status, e) {
				$.ligerDialog.alert("服务异常");
			}
		});
		
	}
	
  </script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/activityAreaTempletPparam/updateActivityAreaTempletPparam.shtml"  >
		<input id="id" name="id" type="hidden" value="${activityAreaTempletPparam.id}" />
		<table class="gridtable">
			<tr>
				<td class="title">模板代码 *</td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="30" cols="105" id="code" name="code" >${activityAreaTempletPparam.code}</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">模板图片</td>
				<td colspan="3">
	    			<t:imageList name="pictures" list="${photo }" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
	    			<div style="float: left;margin: 10px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="photo" name="file" onchange="ajaxFileUpload();" />
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    		</td>
			</tr>
			
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button" value="提交"  /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>
            

		</table>
	</form>
</body>
</html>
