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
<style type="text/css">
 	.radioClass {
		margin-right: 20px;
	}
 	.table-edit-activity-time div,
    .table-edit-activity-time span {
		display: inline-block;
		vertical-align: middle;
    }
    .activity-time {
    	margin-left: 50px;
    }
    .activity-hint {
    	color: #6B6B6B;
    }
</style>
<script type="text/javascript">
//查看会员
function memberView(labelGroupId) {
	$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
			title: "查看会员",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/memberLabelRelation/memberLabelRelationListManager.shtml?labelGroupId="+labelGroupId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
}

//下载方法
function downLoadUserCodeExcel() {
	$.ligerDialog.confirm('是否下载该Excel？', function(yes) {
		if(yes) {
			var filePath=$("#filePath-name").text();
			location.href="${pageContext.request.contextPath}/task/downLoadUserCodeExcel.shtml?filePath="+filePath;
		}
	});
}

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" >
	   <input type="hidden" id="id"  name="id" value="${taskCustom.id}"> 
	   	<table class="gridtable">
	   		<tr>
            	<td class="title">文章标题<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					${taskCustom.name}
				</td>				
           	</tr>
           	<tr>
				<td class="title">封面图<span class="required">*</span></td>
				<td id="industryLicense_viewer">
					<div><img id="coverPicImg" style="width: 300px;height: 100px" alt="" src="${pageContext.request.contextPath}/file_servelt${taskCustom.coverPic}"></div>
				</td>
			</tr>
			<tr>
            	<td class="title" width="20%">文章简介<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					${taskCustom.content}
				</td>				
           	</tr>
			<tr>
            	<td class="title" width="20%">发送方式<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
				<c:if test="${taskCustom.sendMode eq '0'}">
					立即发送
				</c:if>
				<c:if test="${taskCustom.sendMode eq '1'}">
					定时发送（发送时间：${sendDate}）
				</c:if>
				</td>				
           	</tr>
           	<tr>
            	<td class="title" width="20%">任务说明</td>
				<td align="left" class="l-table-edit-td" >
					${taskCustom.taskExplain}
				</td>				
           	</tr>
           	<tr>
            	<td class="title" width="20%">用户ID<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
					<table>
						<tr>
							<td>导入文件:</td>
							<td>
							<span style="margin-left: 10px;color: Blue;" id="filePath-name" onclick="downLoadUserCodeExcel();">${taskCustom.filePath }</span>
							</td>
						</tr>
						<tr>
							<td>手工输入:</td>
							<td>
							<textarea rows="10" cols="50" id="sendValues" name="sendValues" readonly>${sendValues}</textarea>
							</td>
						</tr>
						<tr>
							<td>选择分组:</td>
							<td>
							<div id="addMemberLabelGroup-div" >
								<c:forEach  var="memberLabelGroup" items="${memberLabelGroups}">
									<p style="margin: 5px 0px;">
									<a class="labelGroupId" href="javascript:void(0);" onclick="memberView(${memberLabelGroup.id});" >${memberLabelGroup.labelGroupName} <fmt:formatDate value="${memberLabelGroup.updateDate }" pattern="yyyy-MM-dd"/></a>
									</p>
								</c:forEach>
							</div>
							</td>
						</tr>
					</table>
				</td>				
           	</tr>
            <tr>
            	<td class="title" width="20%">创建人</td>
				<td align="left" class="l-table-edit-td" >
					${taskCustom.createName}
				</td>				
           	</tr>
            <tr>
            	<td class="title" width="20%">创建时间</td>
				<td align="left" class="l-table-edit-td" >
					${createDate}
				</td>				
           	</tr>
           	<c:if test="${taskCustom.status ne '0' && taskCustom.status ne '1'}">
            <tr>
            	<td class="title" width="20%">运营审核结果</td>
				<td align="left" class="l-table-edit-td" >
					<c:if test="${taskCustom.status eq '5'}">
						审核失败
					</c:if>
					<c:if test="${taskCustom.status eq '2' || taskCustom.status eq '3' || taskCustom.status eq '4'|| taskCustom.status eq '6'}">
						审核通过
					</c:if>
				</td>				
           	</tr>
           <c:if test="${taskCustom.status eq '5'}">
				<tr>
            	<td class="title" width="20%">驳回理由</td>
				<td align="left" class="l-table-edit-td" >
					${taskCustom.yyAuditRemarks}
				</td>
				</tr>				
			</c:if>
            <tr>
            	<td class="title" width="20%">运营审核人</td>
				<td align="left" class="l-table-edit-td" >
					${taskCustom.yyAuditName}
				</td>				
           	</tr>
            <tr>
            	<td class="title" width="20%">运营审核时间</td>
				<td align="left" class="l-table-edit-td" >
					${yyAuditDate}
				</td>				
           	</tr>
           	<c:if test="${taskCustom.status ne '2' && taskCustom.status ne '5'}">
            <tr>
            	<td class="title" width="20%">法务审核结果</td>
				<td align="left" class="l-table-edit-td" >
					<c:if test="${taskCustom.status eq '6'}">
						审核失败
					</c:if>
					<c:if test="${taskCustom.status eq '3' || taskCustom.status eq '4'}">
						审核通过
					</c:if>
				</td>				
           	</tr>
           	<c:if test="${taskCustom.status eq '6'}">
				<tr>
            	<td class="title" width="20%">驳回理由</td>
				<td align="left" class="l-table-edit-td" >
					${taskCustom.fwAuditRemarks}
				</td>
				</tr>				
			</c:if>
            <tr>
            	<td class="title" width="20%">法务审核人</td>
				<td align="left" class="l-table-edit-td" >
					${taskCustom.fwAuditName}
				</td>				
           	</tr>
            <tr>
            	<td class="title" width="20%">法务审核时间</td>
				<td align="left" class="l-table-edit-td" >
					${fwAuditDate}
				</td>				
           	</tr>
           	</c:if>
           	</c:if>
	   	</table>
	</form>
	</body>
</html>