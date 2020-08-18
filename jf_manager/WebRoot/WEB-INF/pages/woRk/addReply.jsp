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
	 editor1 = K.create('textarea[name="recordContent"]', {
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
			var recordContent=document.getElementById("recordContent").value;
			var staffId = $("#staffId").val();
			var orgId = $("#orgId").val();
			if($.trim(recordContent)=='' || recordContent == null){
				$.ligerDialog.alert("回复内容不能为空"); 
				return;
			}
			if(orgId == '') {	    
				$.ligerDialog.alert("指派部门不能为空"); 
				return;
	   		  }
			if(staffId == '') {	    
				$.ligerDialog.alert("指派人不能为空"); 
				return;
	   		 }	
			form.submit();
		}
	});
}); 


</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1"  action="${pageContext.request.contextPath}/work/addreplys.shtml" >
		<input type="hidden" id="id" name="id" value="${workHistory.id}"/>
		<input type="hidden" id="workid" name="workid" value="${woRk.id}"/>
		<table class="gridtable">
				<tr>
	            	<td class="title" width="20%">指派给谁<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="orgId" name="orgId" onchange="staffIdinfo();" >
							<option value="">请选择...</option>
							<c:forEach var="sysOrganizationlist" items="${sysOrganizationlist}">
								<option value="${sysOrganizationlist.orgId}" <c:if test="${sysOrganizationlist.orgId == sysStaffInfoOrgIdlist}">selected</c:if> > 
									${sysOrganizationlist.orgName}
								</option>
							</c:forEach>
						</select>
						<span style="margin-left: 20px;" >
							<select style="width: 135px;" id="staffId" name="staffId" >
								<option value="">请选择...</option>
								<c:forEach var="sysStaffInfolist" items="${sysStaffInfolist }">
									<option value="${sysStaffInfolist.staffId }" <c:if test="${sysStaffInfolist.staffId == worekrecordstaffid}">selected</c:if>  > 
										${sysStaffInfolist.staffName}
									</option>
								</c:forEach>						
							</select>
						</span>
					</td>
	           	</tr>

	           	
	         <tr>
				<td class="title">回复内容<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<textarea name="recordContent" id="recordContent" style="width:150px;height:300px;visibility:hidden;"></textarea>
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