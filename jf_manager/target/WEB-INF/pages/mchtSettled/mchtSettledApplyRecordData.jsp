<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">

function excel(){ //导出
	$("#form").attr("action","${pageContext.request.contextPath}/mchtSettled/mchtSettledApplyRecordDatat.shtml");
	$("#form").submit();
} 

$(function ()  {
	$("#save").bind('click',function(){
		var platformcontactId = $.trim($("#platformcontactId").val());
		if(!platformcontactId){
			commUtil.alertError("请选择招商对接人!");
			return false;
		}
		var excelFilePath = $.trim($("#excelFilePath").val());
		
		var fileext=excelFilePath.substring(excelFilePath.lastIndexOf("."),excelFilePath.length);
		
		if(!excelFilePath || fileext != '.xls'){
			commUtil.alertError("请先导入EXCEL且导入格式必须是.xls格式文件!");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtSettled/mchtSettledApplyRecordDataList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"platformcontactId":platformcontactId,"excelFilePath":excelFilePath},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					$.ligerDialog.confirm("共"+data.total+"条,成功导入："+data.successTotal+"条,异常："+data.errorTotal+"条", function (yes) 
					{ 
						if(yes){
							parent.location.reload();
							frameElement.dialog.close();	
						}
					});
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});
});

/* function fileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "file",
		dataType: 'json',
		success: function(result, status) {
			console.log(result);
			if(result.RESULT_CODE == 0) {
				$("#excelFilePath").val(result.FILE_PATH);
				$("#fileInfoDesc").text("已导入EXCEL文件："+result.FILE_PATH.substring(result.FILE_PATH.lastIndexOf("/")+1));
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
} */

function ajaxFileUpload(obj) {
    var file = obj.files[0];
	var fileName = file.name;
	$("#fileInfoDesc").text(fileName);
    var reader = new FileReader();
    reader.onload = function(e) {
    	$.ajaxFileUpload({
    		url: '${pageContext.request.contextPath}/service/common/fileUpload.shtml',
    		secureuri: false,
    		fileElementId: "file",
    		dataType: 'json',
    		success: function(result, status) {
    			if(result.RESULT_CODE == 0){
    			   var filePath = result.FILE_PATH;
	               $("#excelFilePath").val(filePath);
	               console.log(result);
	               commUtil.alertSuccess("已导入成功Excel文档");
   			  	}else{
   			  		alert(result.RESULT_MESSAGE);
   			  	}
    		},
    		error: function(e) {
    			alert("服务异常");
    		}
    	});
    };
    reader.readAsDataURL(file);  
}

</script>

<html>
<body>
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/mchtSettled/mchtSettledApplyRecordDataList.shtml">
		<table class="gridtable">
			
			<tr>
				<td colspan="1" class="title">所属招商</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					<select style="width: 120px;" id="platformcontactId" name="platformcontactId">
				    	<option value="">请选择...</option>
							<c:forEach var="list" items="${platformContact}">
							   <option value="${list.id}">${list.contactName}</option>
						    </c:forEach>					
					</select>
	    		</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">导入EXCEL</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div style="float: left;height: 35px;margin: 10px;">
	    				<input style="position:absolute; opacity:0;" type="file" id="file" name="file" onchange="ajaxFileUpload(this);" />
						 <input type="button" style="width: 80px; height: 30px;" value="导入">
						<br>
						<span id="fileInfoDesc"></span>
	    			</div>
	    			<input id="excelFilePath" name="excelFilePath" type="hidden">
	    			 	    	    						 
					<span style="float: left;margin-top:30px;margin-left:50px;"><a href="javascript:void(0);" style="width:30%;" onclick="return excel();">下载开发模板</a></span>						               
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
					  <c:if test="${sessionScope.USER_SESSION.staffID=='1'}">
						<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
					  </c:if>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
