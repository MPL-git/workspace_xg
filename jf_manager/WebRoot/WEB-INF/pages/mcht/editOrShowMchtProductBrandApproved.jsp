<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
$(function(){
	$("input[name='isSpeciallyApproved']").bind('click',function(){
		var isSpeciallyApproved = $(this).val();
		if(isSpeciallyApproved == 0){
			$("#sourceTr").hide();
			$("#dateTr").hide();
		}else{
			$("#sourceTr").show();
			$("#dateTr").show();
		}
	});
});

	function submit_fun(){
		var isSpeciallyApproved = $("input[name='isSpeciallyApproved']:checked").val();
		if(isSpeciallyApproved == 1){
			var speciallyApprovedSource = $("input[name='speciallyApprovedSource']:checked").val();
			if(!speciallyApprovedSource){
				commUtil.alertError("请选择来源");
				return;
			}
			var speciallyApprovedDate = $("#speciallyApprovedDate").val();
			if(!speciallyApprovedDate){
				commUtil.alertError("特批有效期不能为空");
				return;
			}
			var speciallyApprovedRemarks = $("#speciallyApprovedRemarks").val();
			if(!speciallyApprovedRemarks){
				commUtil.alertError("备注不能为空");
				return;
			}
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/updateMchtProductBrandApproved.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : $("#form1").serialize(),
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("操作成功");
					parent.location.reload();
					frameElement.dialog.close();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" method="post" id="form1" >
		<c:if test="${statusPage == '1' }">
			<input type="hidden" id="mchtProductBrandId" name="mchtProductBrandId" value="${mchtProductBrand.id }" />
		</c:if>
		<table class="gridtable">
			<tr>
				<td class="title"  width="30%">是否标记<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass">
						<input name="isSpeciallyApproved" <c:if test="${mchtProductBrand.isSpeciallyApproved == 1}">checked</c:if> type="radio" value="1" style="width: 30px;" <c:if test="${statusPage == '2' }">disabled="disabled"</c:if>>是
						<input name="isSpeciallyApproved" <c:if test="${mchtProductBrand.isSpeciallyApproved == 0}">checked</c:if> type="radio" value="0" style="width: 30px;" <c:if test="${statusPage == '2' }">disabled="disabled"</c:if>>否
					</span>
				</td> 
			</tr>
			
			<tr id="sourceTr" <c:if test="${mchtProductBrand.isSpeciallyApproved == 0}">style="display: none;"</c:if>>
				<td class="title"  width="30%">来源<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass">
						<input name="speciallyApprovedSource"  type="radio" value="1" style="width: 30px;" <c:if test="${mchtProductBrand.speciallyApprovedSource == 1}">checked="checked"</c:if>>总经理
						<input name="speciallyApprovedSource"  type="radio" value="2" style="width: 30px;" <c:if test="${mchtProductBrand.speciallyApprovedSource == 2}">checked="checked"</c:if>>副总经理
						<input name="speciallyApprovedSource"  type="radio" value="3" style="width: 30px;" <c:if test="${mchtProductBrand.speciallyApprovedSource == 3}">checked="checked"</c:if>>其他人员
					</span>
				</td> 
			</tr>
			<tr id="dateTr" <c:if test="${mchtProductBrand.isSpeciallyApproved == 0}">style="display: none;"</c:if>>
               <td class="title" width="30%">有效期<span class="required">*</span></td>
               <td colspan="2" align="left" class="l-table-edit-td">
               		<input type="text" id="speciallyApprovedDate" name="speciallyApprovedDate" value='<fmt:formatDate value="${mchtProductBrand.speciallyApprovedDate}"/>' />
	               	<script type="text/javascript">
						$(function(){
							$("#speciallyApprovedDate").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
	        </tr>
			<tr>
				<td class="title"  width="30%">备注<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<textarea rows="5" id="speciallyApprovedRemarks" name="speciallyApprovedRemarks" cols="45" class="text" >${mchtProductBrand.speciallyApprovedRemarks }</textarea>
				</td> 
			</tr>
			<c:if test="${statusPage == '1' }">
				<tr>
					<td class="title"  width="30%">操作</td> 
					<td align="left" class="l-table-edit-td" >
						<input type="button" onclick="submit_fun();" class="l-button l-button-submit" value="提交" /> 
						<input style="margin-left: 20px;" class="l-button" type="button" value="取消" onclick="frameElement.dialog.close();" />
					</td>
				</tr>
			</c:if>
		</table> 
	</form>
	</body>
</html>