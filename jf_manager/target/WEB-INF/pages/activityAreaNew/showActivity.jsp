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
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
 	.radioClass{
		margin-right: 20px;
	}
 
 </style>
<script type="text/javascript">
	var entryPic;
	var posterPic;
	$(function(){
		entryPic = new Viewer(document.getElementById('entryPic'), {});
		posterPic = new Viewer(document.getElementById('posterPic'), {});
	});
	
	//审核流水表
	function activityAuditLogList(activityId) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "审核进度",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityAuditLog/activityAuditLogList.shtml?activityId=" + activityId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" id="form1" enctype="multipart/form-data">
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">活动名称</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:260px;" type="text" value="${activityNewCustom.name }" disabled="disabled" >
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">类目</td>
				<td align="left" class="l-table-edit-td" >
					<select style="width: 135px;" disabled="disabled"  >
						<option value="">请选择...</option>
						<c:forEach var="productType" items="${productTypeList }">
							<option value="${productType.id }" <c:if test="${productType.id == activityNewCustom.productTypeId }">selected</c:if>  > 
								${productType.name }
							</option>
						</c:forEach>
					</select>
					<span style="margin-left: 20px;" >
						<select style="width: 135px;" id="productTypeSecondId" disabled="disabled" name="productTypeSecondId" >
							<option value="">请选择...</option>
							<c:forEach var="productTypeSecond" items="${productTypeSecondList }">
								<option value="${productTypeSecond.id }" <c:if test="${productTypeSecond.id == activityNewCustom.productTypeSecondId }">selected</c:if>  > 
									${productTypeSecond.name }
								</option>
							</c:forEach>
						</select>
					</span>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">品牌</td>
				<td align="left" class="l-table-edit-td" >
					<select style="width: 135px;" disabled="disabled"  >
						<option value=""></option>
						<c:forEach var="productBrand" items="${productBrandList }">
							<option value="${productBrand.id }" <c:if test="${productBrand.id == activityNewCustom.productBrandId }">selected</c:if>  > 
								${productBrand.name }
							</option>
						</c:forEach>
					</select>
					<span style="margin-left: 20px;">
						品牌限制：
						<span class="radioClass"><input type="radio" disabled="disabled" name="brandLimitType" value="1" <c:if test="${activityNewCustom.brandLimitType == '1' }">checked</c:if> />品牌专场</span>
						<span class="radioClass"><input type="radio" disabled="disabled" name="brandLimitType" value="2" <c:if test="${activityNewCustom.brandLimitType == '2' }">checked</c:if> />多品牌联合</span>
					</span>
				</td>
           	</tr>
           	<tr height="220px;">
            	<td class="title" width="20%">入口图</td>
				<td align="left" class="l-table-edit-td">
					<div style="width: 400px;height: 200px;border: 1px solid #6B6B6B;">
						<ul class="docs-pictures clearfix td-pictures" id="entryPic">
							<li><img src="${pageContext.request.contextPath}/file_servelt${activityNewCustom.entryPic}" style="width: 400px;height: 200px;display: block;"  ></li>
				 		</ul>
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素。</span>
				</td>
           	</tr>
           	<tr height="220px;">
            	<td class="title" width="20%">头部海报图</td>
				<td align="left" class="l-table-edit-td">
					<div style="width: 400px;height: 200px;border: 1px solid #6B6B6B;">
						<ul class="docs-pictures clearfix td-pictures" id="posterPic">
							<li><img src="${pageContext.request.contextPath}/file_servelt${activityNewCustom.posterPic}" style="width: 400px;height: 200px;display: block;" ></li>
				 		</ul>
					</div>
			 		<span style="color: #6B6B6B;">图片的格式为：.JPG，尺寸要求：800x400像素。</span>
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">状态</td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${statusAudit == '1' }">
						${activityNewCustom.operateAuditStatusDesc }
					</c:if>
					<c:if test="${statusAudit == '2' }">
						${activityNewCustom.cooAuditStatusDesc }
					</c:if>
					<span style="margin-left: 10px;">
						<a href="javascript:activityAuditLogList(${activityNewCustom.id})">【查看审核日志】</a>
					</span>
				</td>
           	</tr>
           	<c:if test="${statusAudit == '1' and activityNewCustom.operateAuditStatus == '3' }">
           		<tr>
	            	<td class="title" width="20%">驳回理由</td>
					<td align="left" class="l-table-edit-td">
						${activityNewCustom.operateAuditRemarks }
					</td>
	           	</tr>
           	</c:if>
           	<c:if test="${statusAudit == '2' and activityNewCustom.cooAuditStatus == '3' }">
           		<tr>
	            	<td class="title" width="20%">驳回理由</td>
					<td align="left" class="l-table-edit-td">
						${activityNewCustom.cooAuditRemarks }
					</td>
	           	</tr>
           	</c:if>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input style="margin-left: 20px;" class="l-button" type="button" value="关闭" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>