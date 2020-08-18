<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>

<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">
$(function ()  {
	$.metadata.setType("attr", "validate");
	
	var v = $("#form1").validate({
	            	
		errorPlacement: function (lable, element)
		{   
        	var elementType=$(element).attr("type");

        	if($(element).hasClass("l-text-field")){
        		$(element).parent().ligerTip({
					content : lable.html(),width: 100
				});
        	}
        	else if('radio'==elementType){
        		var radioName=$(element).attr("name");
        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
					content : lable.html(),width: 100
				});
        	}
        	else{
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
			$.ajax({
				url : "${pageContext.request.contextPath}/order/complain/addSubmit.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form1").serialize(),
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$.ligerDialog.success('成功发起投诉!');
 						parent.location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	});
});
</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
		<input id="orderDtlId" name="orderDtlId" type="hidden" value="${orderDtlCustom.id }"/>
		<input id="mchtId" name="mchtId" type="hidden" value="${orderDtlCustom.mchtId }"/>
		<input id="subOrderId" name="subOrderId" type="hidden" value="${orderDtlCustom.subOrderId }"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">子订单编号</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					${orderDtlCustom.subOrderCode }
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">商品名称 </td>
				<td colspan="5" align="left" class="l-table-edit-td">
					${orderDtlCustom.productName }
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">投诉类型</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select style="width: 200px;" id="appealType" name="appealType">
					<c:forEach var="list" items="${sysStatusLst}">
						<option value="${list.statusValue}">${list.statusDesc}及以上
						</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">投诉内容<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea id="content" name="content" rows="10" cols="50" class="text" validate="{required:true}">${mchtSettledApplyCustom.remarks}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">备注</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea id="platformRemarks" name="platformRemarks" rows="6" cols="50" class="text" >${mchtSettledApplyCustom.remarks}</textarea>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
