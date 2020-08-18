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
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function(){

		});
		var submiting;
		function submitForm() {
			if(submiting){
				console.log(submiting)
				return false;
			}
			//调价选择
			if($("input[name='mallPrice']:checked").length == 0 &&$("input[name='salePrice']:checked").length == 0){
				commUtil.alertError("至少选择一种调价选择");
				return;
			}
			//调价内容
			if($("#changePrice").val() == ""){
				commUtil.alertError("调价内容不可为空");
				return;
			}
			//是否固定尾数
			if($("input[name='mantissa']:checked").val() == "1" && $("#mantissaPrice").val() == ""){
				commUtil.alertError("尾数内容不能为空");
				return;
			}
			//调价价格、尾数
			var reg = new RegExp("^[1-9]\\d*$");
			if(!reg.test($("#changePrice").val())){
				commUtil.alertError("调价内容须填写正整数");
				return;
			}
			if(!reg.test($("#mantissaPrice").val()) && !$("#mantissaPrice").val() == ""){
				commUtil.alertError("尾数须为正整数");
				return;
			}
			if(parseInt($("#mantissaPrice").val())>=10){
				commUtil.alertError("尾数须为小于10大于等于1的正整数");
				return;
			}
			submiting=true;
			$.ajax({
				url: "${pageContext.request.contextPath}/singleProductActivity/saveBatchChangePrice.shtml",
				type: 'POST',
				dataType: 'json',
				data: $("#form1").serialize(),
				success: function (data) {
					submiting=false;
					if ("0000" == data.returnCode) {
						$.ligerDialog.success("已成功调价"+data.returnData+"个商品。\n",function() {
							parent.location.reload();
						});
					} else {
						$.ligerDialog.error(data.returnMsg);
					}
					//submitFlag = true;
				},
				error: function () {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}




		function editPricemode(num){
			$("#editPricemode").empty();
			if (num == 1){
				$("#editPricemode").append("在原价上增加<input type=\"text\" style=\"width: 80px\" id=\"changePrice\"  name=\"changePrice\" >元")
			}
			if (num == 2){
				$("#editPricemode").append("在原价上增加<input type=\"text\" style=\"width: 80px\" id=\"changePrice\"  name=\"changePrice\">%")
			}
			if (num == 3){
				$("#editPricemode").append("在原价上减少<input type=\"text\" style=\"width: 80px\" id=\"changePrice\"  name=\"changePrice\" >元")
			}
			if (num == 4){
				$("#editPricemode").append("在原价上减少<input type=\"text\" style=\"width: 80px\" id=\"changePrice\"  name=\"changePrice\">%")
			}
			$("#pricingContent").show();
			$("#MantissaFixed").show();
			$("#operation").show();

		}
		function mantissaNum(){
			var mantissa=$("input[name='mantissa']:checked").val();
			if(mantissa == '1'){
				$("#mantissaNum").show();
			}
			if (mantissa=='2') {
				$("#mantissaNum").hide();
			}
		}


		
	
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1"  method="post" id="form1" enctype="multipart/form-data">
		<input type="hidden" id="id"  name="id" value="${id }">
		<input type="hidden" id="productIds"  name="productIds" value="${ids}">
		<table class="gridtable">

			<tr>
				<td class="title" width="20%">
					<span style="color:red;">*</span>调价选择
				</td>
				<td align="left" class="l-table-edit-td" >
					<input type="checkbox" id="mallPrice" name="mallPrice" value="1" >商城价
					<input style="margin-left: 20px;" type="checkbox" id="salePrice" name="salePrice" value="2">活动价
				</td>
			</tr>

			<tr>
				<td class="title" width="20%">
					<span style="color:red;">*</span>调价方式
				</td>
				<td align="left" class="l-table-edit-td" id="pricingWay">
					<input type="radio" name="mode" value="1"  onclick="editPricemode(1)">增加N元
					<input type="radio" name="mode" style="margin-left: 12px;" value="2" onclick="editPricemode(2)">按比例增加
					<input type="radio" name="mode" style="margin-left: 12px;"value="3" onclick="editPricemode(3)">减少N元
					<input type="radio" name="mode" style="margin-left: 12px;" value="4" onclick="editPricemode(4)">按比例减少
				</td>
			</tr>
			<tr  id="pricingContent" style="display:none;" >
				<td class="title" width="20%">
					<span style="color:red;">*</span>调价内容
				</td>
				<td align="left" class="l-table-edit-td" id="editPricemode">
					在原价上增加<input type="text" style="width: 80px"  id="changePrice" name="changePrice" >元
				</td>
			</tr>
			<tr  id="MantissaFixed" style="display:none;">
				<td class="title" width="20%">
					是否固定尾数
				</td>
				<td align="left" class="l-table-edit-td" >
						<input type="radio"    name="mantissa" value="1"onclick="mantissaNum()">是
						<input type="radio" style="margin-left: 40px;"  name="mantissa" value="2" checked="checked" onclick="mantissaNum()">否
				</td>
			</tr>
			<tr id="mantissaNum" style="display:none;">
				<td class="title" width="20%">
					<span style="color:red;">*</span>尾数为
				</td>
				<td align="left" class="l-table-edit-td" >
				<input type="text" style="width: 80px"  id="mantissaPrice" name="mantissaPrice">元 <span>(如:填写9,调整后价格360元会自动变成369元)</span>
				</td>
			</tr>

			<tr  id="operation" style="display:none;" >
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" onClick="submitForm();" class="l-button l-button-submit" value="提交"  />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>