<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
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
	var submitting;
	$("#next").bind('click',function(){
		if(submitting){
			return false;
		}
		var mchtCode = $.trim($("#mchtCode").val());
		if(!mchtCode){
			commUtil.alertError("请输入商家序号");
			return false;
		}
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtShopManager/getMchtInfoByMchtCode.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtCode":mchtCode,"type":1},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var mchtId = data.id;
					$("#mchtId").val(mchtId);
					$("#mchtCodeTr").hide();
					$("#selectedMchtCode").text(mchtCode);
					var companyName = data.companyName;
					$("#companyName").text(companyName);
					var shopName = data.shopName;
					$("#shopName").text(shopName);
					var mchtProductType = data.mchtProductType;
					$("#mchtProductType").text(mchtProductType);
					$("tr[name='mchtInfo']").show();
					$("#next").hide();
					$("#confirmOpen").show();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				submitting = false;
			},
			error : function() {
				submitting = false;
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
		$("#confirmOpen").bind('click',function(){
			if(submitting){
				return false;
			}
			var mchtId = $("#mchtId").val();
			if(!mchtId){
				commUtil.alertError("请先输入商家序号");
				return false;
			}
			submitting = true;
			$.ajax({
				url : "${pageContext.request.contextPath}/mchtShopManager/changeMchtShopStatus.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"mchtId":mchtId,"type":1},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("确认成功");
						setTimeout(function(){
							parent.location.reload();
							frameElement.dialog.close();
						},1000);
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
					submitting = false;
				},
				error : function() {
					submitting = false;
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		});
	});
});

</script>

<html>
<body>
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/payToMchtLog/updateConfirmStatus.shtml">
		<input type="hidden" name="mchtId" id="mchtId" >
		<table class="gridtable">
			<tr id="mchtCodeTr">
				<td colspan="1" class="title">商家序号：</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="mchtCode" name="mchtCode" />
				</td>
			</tr>
			
			<tr name="mchtInfo" style="display: none;">
				<td colspan="1" class="title">商家序号：</td>
				<td colspan="5" align="left" class="l-table-edit-td" id="selectedMchtCode">
	    		</td>
			</tr>
			
			<tr name="mchtInfo" style="display: none;">
				<td colspan="1" class="title">公司名称：</td>
				<td colspan="5" align="left" class="l-table-edit-td" id="companyName">
	    		</td>
			</tr>
			
			<tr name="mchtInfo" style="display: none;">
				<td colspan="1" class="title">店铺名称：</td>
				<td colspan="5" align="left" class="l-table-edit-td" id="shopName">
				</td>
			</tr>
			
			<tr name="mchtInfo" style="display: none;">
				<td colspan="1" class="title">主营类目：</td>
				<td colspan="5" align="left" class="l-table-edit-td" id="mchtProductType">
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input type="button" id="next" style="float:left;" class="l-button l-button-submit" value="下一步"/>
						<input type="button" id="confirmOpen" value="确认开通" class="l-button l-button-test" style="float:left;display: none;" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
