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

</style>
<script type="text/javascript">

</script>

</head>
	<body style="margin: 10px; ">
	<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/mcht/updateMchtOptimizeGrade.shtml" >
		<input type="hidden" id="mchtId" name="mchtId" value="${mchtInfoCustom.id }" />
		<input type="hidden" id="gradeType" name="gradeType" value="${gradeType }" />
		<table class="gridtable">
           	<tr>
           	    <td class="title" width="20%">
           	    	<c:if test="${gradeType == '1' }">法务风险等级</c:if>
           	    	<c:if test="${gradeType == '2' }">配合度</c:if>
           	    	<c:if test="${gradeType == '3' }">站内资源等级</c:if>
           	    </td>
				<td align="left" class="l-table-edit-td" >
					<c:if test="${gradeType == '1' }">
						<select id="auditRisk" name="auditRisk" style="width: 135px;">
							<c:forEach var="auditRisk" items="${auditRiskList }">
								<option value="${auditRisk.statusValue }" <c:if test="${mchtInfoCustom.auditRiskDesc == auditRisk.statusDesc }">selected</c:if> >${auditRisk.statusDesc }</option>
							</c:forEach>
						</select>
					</c:if>
           	    	<c:if test="${gradeType == '2' }">
           	    		<select id="degreeAdaptability" name="degreeAdaptability" style="width: 135px;">
							<c:forEach var="degreeAdaptability" items="${degreeAdaptabilityList }">
								<option value="${degreeAdaptability.statusValue }" <c:if test="${mchtInfoCustom.degreeAdaptabilityDesc == degreeAdaptability.statusDesc }">selected</c:if> >${degreeAdaptability.statusDesc }</option>
							</c:forEach>
						</select>
           	    	</c:if>
           	    	<c:if test="${gradeType == '3' }">
           	    		<select id="resourceGrade" name="resourceGrade" style="width: 135px;">
							<c:forEach var="resourceGrade" items="${resourceGradeList }">
								<option value="${resourceGrade.statusValue }" <c:if test="${mchtInfoCustom.resourceGradeDesc == resourceGrade.statusDesc }">selected</c:if> >${resourceGrade.statusDesc }</option>
							</c:forEach>
						</select>
           	    	</c:if>
            	</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="保存" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="取消" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	</body>
</html>