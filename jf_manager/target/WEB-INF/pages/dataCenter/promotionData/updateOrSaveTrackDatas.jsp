<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		
		function submitForm() {
			var fileStatus = $("#fileStatus").val();
			if(fileStatus != 'true') {
				commUtil.alertError("导入文件不能为空！");
				return;
			}
			$.ligerDialog.waitting('正在导入中,请稍候...');
 			$("#form1").submit();
		}
	
		function updateExcelFile(fileName) {
			var fileext = fileName.substring(fileName.lastIndexOf("."), fileName.length);  
		    fileext = fileext.toLowerCase();  
			if(fileext != '.xls' && fileext != '.xlsx') {  
	        	$("#excelFileName").css("color","red");
	        	$("#excelFileName").html("对不起，导入数据格式必须是xls或xlsx格式文件"); 
	        	$("#fileStatus").val("false");
	            return;
	        }
			$("#excelFileName").html(fileName);
			$("#fileStatus").val("true");
		}
		
		
	</script>
	
</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" id="form1" action="${pageContext.request.contextPath}/promotionData/orderCount/updateOrSaveTrackDatas.shtml" method="post" enctype="multipart/form-data">
		<input type="hidden" name="status" value="${status }">
		<table class="gridtable">
			 <tr>
            	<td class="title" width="20%" style="color:red;">注意</td>
				<td align="left" class="l-table-edit-td" style="color:red;" >
					导入文件不能超过五千行
				</td>
           	 </tr>
			 <tr>
               <td  class="title" width="20%">导入文件<span class="required">*</span></td>
               <td align="left" class="l-table-edit-td" >
	    			<div style="float: left;position: relative;">
	    				<input style="position: absolute;opacity:0;width:100%;" type="file" id="excelFile" name="excelFile" onchange="updateExcelFile(this.value);" />
	    				<a href="javascript:void(0);">导入文件</a>
	    			</div>
	    			<div id="excelFileName" style="margin-left: 80px;" ></div>
	    			<input type="hidden" id="fileStatus" value="false">
	    			
               </td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" onClick="submitForm();" class="l-button l-button-submit" value="保存" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="取消" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>