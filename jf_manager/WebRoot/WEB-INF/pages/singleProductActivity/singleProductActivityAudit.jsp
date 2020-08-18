<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		
		$(function(){
			updateActivityPrice();
		});
		
		function validateNum(className) {
			var patrn = /^[1-9]\d*(\.\d+)?$|^0(\.\d+){1}$/;
			var result = true;
			$("." + className).each(function () {
                if (!patrn.exec(this.value)) {
                	$("." + className).val("");
                	commUtil.alertError("请输入正确的价格！");
                	result = false;
                }
            })
            return result;
		}
		
		function updateActivityPrice() {
			var activityPrice = $("#activityPrice").val();
			if(activityPrice == '') {
				$("#oPrice").hide();
				return;
			}else{
				if(!validateNum('activityPrice')) {
					$("#oPrice").hide();
					return;
				}
			}
			$("#oPrice").show();
			var tagPriceMin = '${singleProductActivity.tagPriceMin }';
			var discount = parseFloat(activityPrice/tagPriceMin*10);
			discount = Math.round(discount*100)/100;
			if(isNaN(discount)) {
				$("#price").text("格式不正确");
			}else {
	 			$("#price").text(discount+"折");
			}
		}
	
		function submitForm() {
			var auditStatus = $("input[name='auditStatus']:checked").val();
			if(auditStatus == '2') {
				var remarks = $("#remarks").val();
				if(remarks == '') {
					commUtil.alertError("初审驳回时，原因说明不能为空！");
					return;
				}
			}
			<c:if test="${singleProductActivity.type == '3'}">
				var seckillType = $("input[name='seckillType']:checked").val();
				if(!seckillType){
					commUtil.alertError("请选择秒杀类型");
					return;
				}
			</c:if>
			$("#form1").submit();
		}
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" action="${pageContext.request.contextPath}/singleProductActivity/saveSingleProductActivityAuditLog.shtml" method="post" id="form1" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${singleProductActivity.id }">
		<table class="gridtable">
			<tr height="110px">
            	<td class="title" width="20%">商家优势说明</td>
				<td align="left" class="l-table-edit-td" style="color:red;" >${singleProductActivity.remarks }</td>
           	</tr>
           	<c:if test="${singleProductActivity.type == '3'}">
           	<tr height="110px">
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>秒杀类型
            	</td>
				<td align="left" class="l-table-edit-td" style="color:red;" >
					<input type="radio" name="seckillType" value="1" <c:if test="${singleProductActivity.seckillType =='1'}">checked="checked"</c:if>>限时秒杀
					<input type="radio" name="seckillType" value="2" <c:if test="${singleProductActivity.seckillType =='2'}">checked="checked"</c:if>>会场秒杀
				</td>
           	</tr>
           	</c:if>
			<tr>
            	<td class="title" width="20%">当前状态</td>
				<td align="left" class="l-table-edit-td" >
					<c:if test="${singleProductActivity.auditStatus == '0' }">待审</c:if>
					<c:if test="${singleProductActivity.auditStatus == '1' }">初审通过</c:if>
					<c:if test="${singleProductActivity.auditStatus == '2' }">初审驳回</c:if>
					<c:if test="${singleProductActivity.auditStatus == '3' }">排期通过</c:if>
					<c:if test="${singleProductActivity.auditStatus == '4' }">排期驳回</c:if>
					<c:if test="${singleProductActivity.auditStatus == '5' }">复审驳回</c:if>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">竞品价格</td>
				<td align="left" class="l-table-edit-td" >
					<input type="text" style="width:100px;" id="comparePrice" class="comparePrice" name="comparePrice" value="${singleProductActivity.comparePrice }" onChange="validateNum('comparePrice');">元
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>审核结果
            	</td>
				<td align="left" class="l-table-edit-td" >
					<c:if test="${singleProductActivity.auditStatus != '0' }">
						<input type="radio" checked class="auditStatus" name="auditStatus" value="1" checked>初审通过
						<input style="margin-left: 20px;" type="radio" class="auditStatus" name="auditStatus" value="${singleProductActivity.auditStatus }">关闭报名
					</c:if>
					<c:if test="${singleProductActivity.auditStatus == '0' }">
						<input type="radio" class="auditStatus" name="auditStatus" value="1" checked>初审通过		
						<input style="margin-left: 20px;" type="radio" class="auditStatus" name="auditStatus" value="2">初审驳回				
					</c:if>		
				</td>
           	</tr>
			<tr height="100px">
            	<td class="title" width="20%">原因说明</td>
				<td align="left" class="l-table-edit-td" >
					<textarea rows="5" style="width: 100%;height: 100%;" id="remarks" name="remarks" ></textarea>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="button" onClick="submitForm();" class="l-button l-button-submit" value="保存" /> 
					<input style="margin-left: 20px;" class="l-button l-button-submit" type="button" value="取消" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>