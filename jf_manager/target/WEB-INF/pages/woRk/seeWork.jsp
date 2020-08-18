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

//查看介入单详情
function interventionOrder(interventionOrderId, statusPage) {
	$.ligerDialog.open({
		height : $(window).height() - 100,
		width : $(window).width() - 200,
		title : "查看详情",
		name : "INSERT_WINDOW",
		url : "${pageContext.request.contextPath}/interventionOrder/showInterventionOrderManager.shtml?interventionOrderId="
				+ interventionOrderId + "&statusPage=" + statusPage,
		showMax : true,
		showToggle : false,
		showMin : false,
		isResize : true,
		slide : false,
		data : null
	});
}

//查看投诉单详情	
function appealOrder(id) {
		$.ligerDialog.open({
			height : $(window).height() - 50,
			width : $(window).width() - 100,
			title : "投诉详情页",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/appealOrder/view.shtml?id="
					+ id,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
//查看售后单详情
function customerServiceOrder(id) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
	 		title: "售后详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/afterService/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
//查看子订单详情
function subOrder(id) {
	$.ligerDialog.open({
 		height: $(window).height(),
		width: $(window).width()-50,
		title: "子订单详情",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
} 

//下载附件
$(function(){
	 $("#download").bind('click',function(){
		 var attachmentPath = $(this).attr("attachmentpath");
		 var attachmentName = $(this).attr("attachmentname");
		 $.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/work/checkFileExists.shtml',
				data: {"attachmentPath":attachmentPath},
				dataType: 'json',
				success: function(data){
					if(data == null || data.code != 200){
				    	commUtil.alertError(data.msg);
				  	}else{
				  		location.href="${pageContext.request.contextPath}/wrok/downLoadAttachment.shtml?fileName="+attachmentName+"&filePath="+attachmentPath;
				  	}
				},
				error: function(e){
				 	commUtil.alertError("系统异常请稍后再试！");
				}
			});
	 });

});
 
 
</script>

</head>
	<body style="margin: 10px;">
	<form method="post" id="form1" name="form1"  action="" >
		<input type="hidden" id="id" name="id" value="${workHistorylist.id}" />
		<span class="marR10" style="font-weight: bold;">工单内容</span>
		<table class="gridtable">
				<tr>
	            	<td class="title" width="20%">指派给谁<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="orgId" name="orgId" onchange="staffIdinfo();" >
							<option value="">请选择...</option>
							<c:forEach var="sysOrganizationlist" items="${sysOrganizationlist}">
								<option value="${sysOrganizationlist.orgId}" <c:if test="${sysOrganizationlist.orgId == workHistorylist.orgId}">selected</c:if> > 
									${sysOrganizationlist.orgName}
								</option>
							</c:forEach>
						</select>
						<span style="margin-left: 20px;" >
							<select style="width: 135px;" id="staffId" name="staffId" >
								<option value="">请选择...</option>
								<c:forEach var="sysStaffInfolist" items="${sysStaffInfolist }">
									<option value="${sysStaffInfolist.staffId }" <c:if test="${sysStaffInfolist.staffId == workHistorylist.staffId}">selected</c:if>  > 
										${sysStaffInfolist.staffName}
									</option>
								</c:forEach>						
							</select>
						</span>
					</td>
	           	</tr>
	           	<tr>
	            	<td class="title" width="20%" >所属类型<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="workType" name="workType" >
							<option value="">请选择...</option>							
				            <option value='1' <c:if test="${workHistorylist.workType eq '1' }">selected</c:if>>售后咨询</option>
				            <option value='2' <c:if test="${workHistorylist.workType eq '2' }">selected</c:if>>售前咨询</option>
					        <option value='3' <c:if test="${workHistorylist.workType eq '3' }">selected</c:if>>入驻咨询</option>
					        <option value='4' <c:if test="${workHistorylist.workType eq '4' }">selected</c:if>>合同咨询</option>
					        <option value='5' <c:if test="${workHistorylist.workType eq '5' }">selected</c:if>>工商投诉</option>
					        <option value='6' <c:if test="${workHistorylist.workType eq '6' }">selected</c:if>>其他</option>
						</select>
					</td>	
	           	</tr>
	           	
	           	<tr>	      
					<td class="title" width="20%" style="margin-left: 20px;">紧急程度<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="urgentDegree" name="urgentDegree" >
							<option value="">请选择...</option>							
				            <option value='1' <c:if test="${workHistorylist.urgentDegree eq '1' }">selected</c:if>>普通</option>
				            <option value='2' <c:if test="${workHistorylist.urgentDegree eq '2' }">selected</c:if>>重要</option>
					        <option value='3' <c:if test="${workHistorylist.urgentDegree eq '3' }">selected</c:if>>紧急</option>
					        <option value='4' <c:if test="${workHistorylist.urgentDegree eq '4' }">selected</c:if>>重要紧急</option>					
						</select>
					</td>
	           	</tr>
	           	
	           	<tr>
	            	<td class="title" width="20%">相关类型</td>
					<td align="left" class="l-table-edit-td" >
						<select style="width: 135px;" id="relevantType" name="relevantType" >
							<option value=''>请选择...</option>
				            <option value='1' <c:if test="${workHistorylist.relevantType eq '1' }">selected</c:if>>介入单</option>
				            <option value='2' <c:if test="${workHistorylist.relevantType eq '2' }">selected</c:if>>投诉单</option>
					        <option value='3' <c:if test="${workHistorylist.relevantType eq '3' }">selected</c:if>>意见反馈</option>
					        <option value='4' <c:if test="${workHistorylist.relevantType eq '4' }">selected</c:if>>子订单</option>
					        <option value='5' <c:if test="${workHistorylist.relevantType eq '5' }">selected</c:if>>售后单</option>
					        <option value='6' <c:if test="${workHistorylist.relevantType eq '6' }">selected</c:if>>其他</option>					
						</select>
					</td>
	           	</tr>
	           	
	           	<tr>	       
					<td class="title" width="20%" style="margin-left: 20px;">相关单号</td>
					<td align="left" class="l-table-edit-td" >
					 <c:if test="${not empty workHistorylist.relevantCode}">	    
					   <c:if test="${workHistorylist.relevantType eq '1'}">
						 <a href="javascript:interventionOrder(${workHistorylist.relevantId},1);">${workHistorylist.relevantCode}</a>
					  </c:if>
					  <c:if test="${workHistorylist.relevantType eq '2'}">
						 <a href="javascript:appealOrder(${workHistorylist.relevantId});">${workHistorylist.relevantCode}</a>
					  </c:if>
					  <c:if test="${workHistorylist.relevantType eq '4'}">
						 <a href="javascript:subOrder(${workHistorylist.relevantId});">${workHistorylist.relevantCode}</a>
					  </c:if>
					  <c:if test="${workHistorylist.relevantType eq '5'}">
						 <a href="javascript:customerServiceOrder(${workHistorylist.relevantId});">${workHistorylist.relevantCode}</a>
					  </c:if>
					 </c:if>
					 <c:if test="${empty workHistorylist.relevantCode}">
					                   暂无相关单号~~~
					 </c:if>							
					</td>
	           	</tr>
	           	
	            <tr>
	            	<td class="title" width="20%">标题<span class="required">*</span></td>
					<td align="left" class="l-table-edit-td" >
						<input style="width:360px;" type="text" id="titleContent" name="titleContent" value="${workHistorylist.titleContent}" />
					</td>
	           	</tr>
	           	
	         <tr>
				<td class="title">具体描述<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<textarea name="describeContent" id="describeContent" style="width:150px;height:300px;visibility:hidden;">${workHistorylist.describeContent}</textarea>
              	</td>  
            </tr>
	           	
	        <tr>
				<td class="title">附件</td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${not empty attachmentPath}">
						${attachmentName}&nbsp;&nbsp;<a href="javascript:;" id="download" attachmentname="${attachmentName}" attachmentpath="${attachmentPath}">【下载】</a>
					</c:if>
					<c:if test="${empty attachmentPath}">
						 暂无附件~~~
					</c:if>						
				</td>
			</tr>
         			
		</table>
	</form>
	</body>
</html>