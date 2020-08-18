<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>
<script type="text/javascript">
function ajaxFileUploadLogo() {//上传图片
	$.ajaxFileUpload({
		url : contextPath + '/service/common/ajax_upload.shtml',
		secureuri : false,
		fileElementId : "signPicFile",
		dataType : 'json',
		success : function(result, status) {
			if (result.RESULT_CODE == 0) {
				$("#signpiclogo").attr("src",
						contextPath + "/file_servelt" + result.FILE_PATH);
				$("#pic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error : function(result, status, e) {
			alert("服务异常");
		}
	});

}

//限制上传图片
function ajaxFileUploadLogoAstrict(){
	 var file = document.getElementById("signPicFile").files[0];  
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var image = new Image();
    	image.onload = function() { 
    				var imgWidth = '240';
					var imgHeight = '320';
        			if(this.width != imgWidth || this.height != imgHeight ) {
            			$("#pic").val("");
            			commUtil.alertError("图片像素不是"+imgWidth+"*"+imgHeight+"px"); 
                	}else{
                		
                		ajaxFileUploadLogo();
                			                		
                	}       		
        };
        image.src = e.target.result;
    },
    reader.readAsDataURL(file);
    }
    
     //验证
      $(function(){   
           var v = $("#form1").validate({
           submitHandler: function (form)
                {   
                	var isValidateOk=true;
            		var name = document.getElementById("name");
            		var pic = document.getElementById("pic");
            		
	            		if($.trim(name.value)==""){
	            			$("#name").ligerTip({ content: "该字段不能为空。"});
	            			isValidateOk=false;
	            		}
	            		
	            		if($.trim(name.value).length>6){
	            			$("#name").ligerTip({ content: "风格名称最大6个字。"});
	            			isValidateOk=false;
	            		} 
	            	
	            		if($.trim(pic.value)==""){
            			$.ligerDialog.question('请上传图片！');
            			isValidateOk=false;
            		}
	            		
                	if(isValidateOk){
                		form.submit();
                	}
                }
            });           
  }); 
     
    $(function (){
	    $("#form1").ligerForm();
	});	
</script>

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/styleAdministration/editsales.shtml"  >
		<input id="id" name="id" type="hidden" value="${stylesegroup.id}" />
		<table class="gridtable">
			<tr>
				<td class="title"> 风格名称*</td>
				<td align="left" class="l-table-edit-td"><input id="name" 
					name="name" type="text" value="${stylesegroup.name }"
					style="float:left;width: 200px;" validate="{required:true,nameUnique:true}" />
			</tr>
			<tr>
				<td class="title">图片*</td>
				<td>
	    			<div><img id="signpiclogo" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${stylesegroup.pic}"></div>
	    			<div style="float: left;margin: 10px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="signPicFile" name="file" onchange="ajaxFileUploadLogoAstrict();"/>
						<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="${stylesegroup.pic}">
	    		</td>
			</tr>
			<tr>
				<td class="title">性别*</td>
				<td align="left" class="l-table-edit-td">
				<input type="radio" name="sex" value="1"  <c:if test="${stylesegroup.sex==1}">checked="checked"</c:if>>男
                <input type="radio" name="sex" value="2" <c:if test="${stylesegroup.sex==2}">checked="checked"</c:if>>女
                </td>
			</tr>
			     
            <tr>
				<td class="title">操作*</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button" value="提交" /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>           
		</table>
	</form>
</body>
</html>