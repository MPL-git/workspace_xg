<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
</script>


<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}



.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}
</style>
<script type="text/javascript">





$(function ()  {
	$.metadata.setType("attr", "validate");
	            var v = $("#form1").validate({
	                errorPlacement: function (lable, element)
	                {   console.log(lable[0].innerText);
	                	if($(element).hasClass("l-text-field")){
	                		$(element).parent().ligerTip({
								content : lable.html(),width: 100
							});
	                	}else{
	                		$(element).ligerTip({
								content : lable.html(),width: 100
							});
	                	}
	                	
	                	
	                },
	                success: function (lable,element)
	                {
	                    lable.ligerHideTip();
	                    lable.remove();
	                },
	                submitHandler: function (form)
	                {   
	                	mchtProductTypeSaveSubmit();
	                }
	            });
	            
	            
	            
	  });    



function mchtProductTypeSaveSubmit(){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtProductTypeSaveSubmit.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
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

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type=hidden type="hidden" name="id" value="${mchtProductType.id}">
	<input type="hidden" name="mchtId" value="${mchtProductType.mchtId}">
		<table class="gridtable">
			<tr>
			
				<td  colspan="1" class="title">品类 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<select style="width: 160px;" id="productTypeId"  name="productTypeId" <c:if test="${!empty mchtProductType.id && empty canEdit}">disabled="true"</c:if> validate="{selectRequired:true}">
				<option value="">请选择</option>
					<c:forEach var="productType" items="${productTypes}">
						<option <c:if test="${mchtProductType.productTypeId==productType.id}">selected</c:if>
						value="${productType.id}">${productType.name}
						</option>
					</c:forEach>
					</select>
				</td>	
			</tr>
			<tr>
				<td  colspan="1" class="title">状态<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<select style="width: 160px;" id="status" name="status" validate="{selectRequired:true}">
				<option value="">请选择</option>
					<c:forEach var="statusItem" items="${mchtProductTypeStatus}">
						<option  <c:if test="${mchtProductType.status==statusItem.statusValue}">selected</c:if>
						value="${statusItem.statusValue}">${statusItem.statusDesc}
						</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			
			
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="submit" id="Button1" 
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						</c:if>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
