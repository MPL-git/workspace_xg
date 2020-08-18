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
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
    
<script type="text/javascript">
var editor1;
KindEditor.ready(function(K) {
	 editor1 = K.create('textarea[name="describeContent"]', {
		cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
		uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
		fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
		allowFileManager : true,
		afterCreate : function() {
		}
		
	});
	prettyPrint();
});



//二级联动获取指派人
function staffIdinfo() {
	 var orgId = $("#orgId").val();
	 if(orgId == '') {
	 	var option = [];
		option.push('<option value="">请选择...</option>');
		$("#staffId").html(option.join(''));
	 }else {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/work/staffIdinfolist.shtml',
			dataType : 'json',
			data: {orgId:orgId},
			success: function(data) {
				if(data.code == 200) {
					var option = [];
					option.push('<option value="">请选择...</option>');
					for(var i=0;i<data.sysStaffInfolist.length;i++) {
						option.push('<option value="'+data.sysStaffInfolist[i].staffId+'">'+data.sysStaffInfolist[i].staffName+'</option>');
					}
					$("#staffId").html(option.join(''));				
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError('操作超时，请稍后再试！');
			}
		});
	}	
}


//限制上传附件大小
function ajaxFileUploadattachment(obj) {
	var file = obj.files[0];
    var reader = new FileReader();  
    reader.onload = function(e) { 
    	var filesize = file.size;
    		if(filesize>1024*1024*50) {
    			commUtil.alertError("附件总大小不超过50M!");
        	}else {
    			ajaxFileUpload(obj);
    		}
      
        image.src = e.target.result;
    },
    reader.readAsDataURL(file);
}


function ajaxFileUpload(obj) {
    var file = obj.files[0];
	var fileName = file.name;
	$("#attachmentName").val(fileName);
	$("#attachmentNameDesc").text(fileName);
    var reader = new FileReader();
    $("#uploading").val(1);
    reader.onload = function(e) {
    	$.ajaxFileUpload({
    		url: '${pageContext.request.contextPath}/service/common/fileUpload.shtml?fileType=8',
    		secureuri: false,
    		fileElementId: "uploadFile",
    		dataType: 'json',
    		success: function(result, status) {
    			if(result.RESULT_CODE == 0){
    			   var filePath = result.FILE_PATH;
	               $("#attachmentPath").val(filePath);
	               $("#uploading").val(0);
	               commUtil.alertSuccess("文件上传成功");
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

$(function ()  { 
	 $.metadata.setType("attr", "validate");
   $("#form1").validate({
		errorPlacement : function(lable, element) {
      	$(element).ligerTip({recordContent : lable.html()});
		},
		success : function(lable) {
			lable.ligerHideTip();
			lable.remove();
		},
		submitHandler : function(form) {
			editor1.sync();
			var orgId = $("#orgId").val();
			var staffId = $("#staffId").val();
    		var workType = $("#workType").val();
    		var urgentDegree = $("#urgentDegree").val();
    		var titleContent=$("#titleContent").val();
    		var describeContent=document.getElementById("describeContent").value;
    		var relevantType=$("#relevantType").val();
    		var relevantCode=$("#relevantCode").val();
    		if(orgId == '') {
    		   $.ligerDialog.alert("指派部门不能为空！"); 
     		   return;
     		}
    		if(staffId == '') {
    			$.ligerDialog.alert("指派人不能为空！");
        		return;
        	}
        	if(workType == '') {
        		$.ligerDialog.alert("所属类型不能为空！");
        		return;
        	}
        	if(urgentDegree=='') {
        		$.ligerDialog.alert("紧急程度不能为空！");
    			return;
        	}
        	if ((relevantType=='1' || relevantType=='2' || relevantType=='4' || relevantType=='5') && relevantCode=='') {
        		$.ligerDialog.alert("有筛选相关类型时,请必填相关单号！");
        		return;
			}
        	if (titleContent=='') {
        		$.ligerDialog.alert("标题不能为空！");
        		return;
			}
        	if ($.trim(describeContent)=='' || describeContent==null) {
        		$.ligerDialog.alert("具体描述内容不能为空！");
        		return;
			}
        	var uploading = $("#uploading").val();
        	if(uploading == 1){
        		$.ligerDialog.alert("附近上传中，请稍后再提交！");
    			return;
        	}
		
			form.submit();
		}
	});
});

</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1"  action="${pageContext.request.contextPath}/order/sub/addOrderWorklist.shtml" >
		<input type="hidden" id="attachmentName" name="attachmentName" value=""/>
		<input type="hidden" id="attachmentPath" name="attachmentPath" value=""/>
		<input type="hidden" id="uploading" value="0"/>
		<input type="hidden" id="id" name="id" value="${subOrderCustom.id}"/>
		<table class="gridtable">
				<tr>
	            	<td class="title" width="20%">指派给谁<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="orgId" name="orgId" onchange="staffIdinfo();" >
							<option value="">请选择...</option>
							<c:forEach var="sysOrganizationlist" items="${sysOrganizationlist}">
								<option value="${sysOrganizationlist.orgId}"> 
									${sysOrganizationlist.orgName}
								</option>
							</c:forEach>
						</select>
						<span style="margin-left: 20px;" >
							<select style="width: 135px;" id="staffId" name="staffId" >
								<option value="">请选择...</option>						
							</select>
						</span>
					</td>
	           	</tr>
	           	<tr>
	            	<td class="title" width="20%" >所属类型<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="workType" name="workType" >
							<option value="">请选择...</option>							
				            <option value='1' selected>售后咨询</option>
				            <option value='2'>售前咨询</option>
					        <option value='3'>入驻咨询</option>
					        <option value='4'>合同咨询</option>
					        <option value='5'>工商投诉</option>
					        <option value='6'>其他</option>
						</select>
					</td>	
	           	</tr>
	           	
	           	<tr>	      
					<td class="title" width="20%" style="margin-left: 20px;">紧急程度<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="urgentDegree" name="urgentDegree" >
							<option value="">请选择...</option>							
				            <option value='1'>普通</option>
				            <option value='2'>重要</option>
					        <option value='3'>紧急</option>
					        <option value='4'>重要紧急</option>					
						</select>
					</td>
	           	</tr>
	           	
	           	<tr>
	            	<td class="title" width="20%">相关类型</td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="relevantType" name="relevantType" >
							<option value=''>请选择...</option>
				            <option value='1'>介入单</option>
				            <option value='2'>投诉单</option>
					        <option value='3'>意见反馈</option>
					        <option value='4' selected>子订单</option>
					        <option value='5'>售后单</option>
					        <option value='6'>其他</option>					
						</select>
					</td>
	           	</tr>
	           	
	           	<tr>	       
					<td class="title" width="20%" style="margin-left: 20px;">相关单号</td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:360px;" type="text" id="relevantCode" name="relevantCode" value="${subOrderCustom.subOrderCode}" />
					</td>
	           	</tr>
	           	
	            <tr>
	            	<td class="title" width="20%">标题<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:360px;" type="text" id="titleContent" name="titleContent" value=""  />
					</td>
	           	</tr>
	           	
	         <tr>
				<td class="title">具体描述<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<textarea name="describeContent" id="describeContent" style="width:150px;height:300px;visibility:hidden;"></textarea>
              	</td>  
            </tr>
	           	
	         <tr>
				<td class="title">附件</td>
				<td align="left" class="l-table-edit-td">
					<input style="position:absolute; opacity:0;" type="file" id="uploadFile" name="uploadFile" onchange="ajaxFileUploadattachment(this);" />
					<input type="button" style="width: 45px;" value="上传">
					<span id="attachmentNameDesc"></span>
					<span style="color:#CC0000;">(提示：附件总大小不超过50M)</span>
				</td>
			</tr>
         
			 <tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>