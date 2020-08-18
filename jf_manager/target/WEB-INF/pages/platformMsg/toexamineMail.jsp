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

<script type="text/javascript">
//审核站内信
$(function(){
	 var listConfig = $("#maingrid").ligerGrid({
	     columns: [
        {display:'商家序号',name:'mchtCode', align:'center'},
	     {display:'公司名称',name:'companyName', align:'center'},
		 {display:'店铺名称',name:'shopName', align:'center'},
		 {display:'主营类目',name:'productTypeName', align:'center'},
		 {display:'商家运营',name:'yyContactName', align:'center'}
        ],pageSize:5,pageSizeOptions:[5,10,20,50,100],
        url:'${pageContext.request.contextPath}/platformMsg/mchtInfodata.shtml?mchtIdGroup=${mchtidGroup}',
        width: '100%',height:'300'
    }); 
	 
	 $("#download").bind('click',function(){
		 var attachmentPath = $(this).attr("attachmentpath");
		 var attachmentName = $(this).attr("attachmentname");
		 $.ajax({
				type: 'post',
				url: '${pageContext.request.contextPath}/platformMsg/checkFileExists.shtml',
				data: {"attachmentPath":attachmentPath},
				dataType: 'json',
				success: function(data){
					if(data == null || data.code != 200){
				    	commUtil.alertError(data.msg);
				  	}else{
				  		location.href="${pageContext.request.contextPath}/platformMsg/downLoadAttachment.shtml?fileName="+attachmentName+"&filePath="+attachmentPath;
				  	}
				},
				error: function(e){
				 	commUtil.alertError("系统异常请稍后再试！");
				}
			});
	 }); 
	 
});

function setVisibility(){  
    document.getElementById("auditRemark").style.display = "";   
}
function setNoVisibility(){  
    document.getElementById("auditRemark").style.display = "none";   
}

</script>

</head>
	<body style="margin: 10px; margin-bottom: 200px;">
	<form name="form" class="form" method="post" id="form" action="${pageContext.request.contextPath}/platformMsg/updatetoexamineMail.shtml" >
		<input type="hidden" id="id" name="id" value="${platformsgexamine.id}" />
		<table class="gridtable">
		    <tr>
            	<td class="title" width="20%">类型<span class="required">*</span></td>
            	<td>
				<select id="msgType" name="msgType" style="width:100px;" disabled="disabled">
 					    <option value="">请选择...</option>
 					    <option value="A1"<c:if test="${platformsgexamine.msgType eq 'A1' }">selected</c:if>>退款</option>
                        <option value="A2"<c:if test="${platformsgexamine.msgType eq 'A2' }">selected</c:if>>退货</option>
                        <option value="A3"<c:if test="${platformsgexamine.msgType eq 'A3' }">selected</c:if>>换货</option>
                        <option value="A4"<c:if test="${platformsgexamine.msgType eq 'A4' }">selected</c:if>>投诉</option>
                        <option value="A5"<c:if test="${platformsgexamine.msgType eq 'A5' }">selected</c:if>>违规</option>
                        <option value="A6"<c:if test="${platformsgexamine.msgType eq 'A6' }">selected</c:if>>活动</option>
                        <option value="TZ"<c:if test="${platformsgexamine.msgType eq 'TZ' }">selected</c:if>>通知</option>
				</select>
			  </td>
           	</tr>
			<tr>
            	<td class="title" width="20%">标题<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:335px;" disabled="disabled" type="text" id="title" name="title" value="${platformsgexamine.title}" validate="{required:true}" />
				</td>
           	</tr>
			<tr>
				<td class="title">推送商家<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					 <div id="maingrid" style="margin: 0; padding: 0"></div>
				</td>
			</tr>
			<tr>
				<td class="title">编辑站内信<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="content" name="content" disabled="disabled" rows="4" cols="50" class="text" >${platformsgexamine.content}</textarea>
				</td>
			</tr>
			<tr>
				<td class="title">附件</td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${not empty platformsgexamine.attachmentPath}">
						${platformsgexamine.attachmentName}&nbsp;&nbsp;<a href="javascript:;" id="download" attachmentname="${platformsgexamine.attachmentName}" attachmentpath="${platformsgexamine.attachmentPath}">【下载】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">审核结果<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<input type="radio" name="status" value="2" onclick="setNoVisibility()"<c:if test="${platformsgexamine.status=='2'}">checked="checked"</c:if>>通过
                <input type="radio" name="status" value="3" onclick="setVisibility()" <c:if test="${platformsgexamine.status=='3'}">checked="checked"</c:if>>驳回
                </td>
			</tr>
			<tr id="auditRemark">
				<td class="title">驳回理由<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea id="auditRemarks" name="auditRemarks" rows="4" cols="50" class="text" >${platformsgexamine.auditRemarks}</textarea>
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">操作<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>