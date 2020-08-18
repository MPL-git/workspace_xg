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
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		var subFlag = true;
		$(function(){
			$(".dateEditor").ligerDateEditor( {
				showTime : false,
				labelAlign : 'left'
			});
			$(".dateEd").ligerDateEditor( {
				format  : "yyyy-MM-dd hh:mm:ss",
				showTime : true,
				labelAlign : 'left',
				width : 160
			});
		});
	
		function compareTime(startDate, endDate) {   
			if (startDate.length > 0 && endDate.length > 0) {   
				var startDateTime = new Date(startDate).getTime();
				var endDateTime = new Date(endDate).getTime();
				if (startDateTime >= endDateTime) {   
			        return false;   
				} else {   
			    	return true;   
			    }   
			} else {   
			    return false;   
			}   
		}   
		
		function differenceTime(startDate, endDate, dates) {
			if (startDate.length > 0 && endDate.length > 0 ) {  
				var startDateTime = new Date(startDate.replace(/-/g,"/")).getTime();
				var endDateTime = new Date(endDate.replace(/-/g,"/")).getTime();
				var datesTime = dates*24*60*60*1000;
				var differenceTime = endDateTime - startDateTime;
				if(differenceTime <= datesTime) {
					return true;
				}else {
					return false; 
				}
			}else {
				return false;   
			}
		}
	
		function submitForm() {
			var date = new Date();
			var startDate = date.format("yyyy-MM-dd hh:mm");
			var auditStatus = $("input[name='auditStatus']:checked").val();
			var beginTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			if(auditStatus == '3') {
				if(endTime == '' || beginTime == '') {
					commUtil.alertError("排期通过时，活动排期不能为空！");
					return;
				}
				if(!compareTime(startDate, beginTime)){
					commUtil.alertError("排期开始时间，必须大于现在时间！");
					return;
				}
				if(!compareTime(beginTime, endTime)){
					commUtil.alertError("开始时间必须大于结束时间！");
					return;
				}
				if(!differenceTime(beginTime, endTime, 30)){
					commUtil.alertError("结束时间与开始时间，相差不能超过30天！");
					return;
				}
			}else if(auditStatus == '4') {
				var remarks = $("#remarks").val();
				if(remarks == '') {
					commUtil.alertError("排期驳回时，原因说明不能为空！");
					return;
				}
			}
			if(subFlag) {
				subFlag = false;
				formSubmit();
			}
		}
		
		
		function formSubmit() {
			$.ajax({
				url : "${pageContext.request.contextPath}/singleProductActivity/saveScheduleAuditOrRetrialCustom.shtml",
				type : 'POST',
				dataType : 'json',
				data : $("#form1").serialize(),
				success : function(data) {
					if ("0000"==data.returnCode) {
						  if (data.auditStatus!=null && data.auditStatus!='') {						  
						     $.ligerDialog.success("操作成功",function() {
							 window.parent.schedulingStatus(data.auditStatus,data.id);//子页面调用父页面方法;
							frameElement.dialog.close();
						   });		
						}
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
	<form name="form1" class="form1" action="" method="post" id="form1" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${singleProductActivity.id }">
		<input type="hidden" name="productId" value="${singleProductActivity.productId }">
		<input type="hidden" name="aPrice" value="${singleProductActivity.activityPrice }">
		<table class="gridtable">
			<tr height="110px">
            	<td class="title" width="20%">商家优势说明</td>
				<td align="left" class="l-table-edit-td" style="color:red;" >${singleProductActivity.remarks }</td>
           	</tr>
          	<tr>
            	<td class="title" width="20%">
            		活动类型
            	</td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 15px;">
						<label><input type="radio" name="type" value="3" checked="checked" >限时抢购</label>
					</span>
				</td>
           	</tr>
           	<tr id="seckillTypeTr">
	            <td class="title" width="20%">
	            		秒杀类型
	            </td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 15px;"><input type="radio" name="seckillType" value="1" <c:if test="${singleProductActivity.seckillType == '1'}">checked</c:if> >限时秒杀</span>
					<span style="margin-left: 15px;"><input type="radio" name="seckillType" value="2" <c:if test="${singleProductActivity.seckillType == '2'}">checked</c:if> >会场秒杀</span>
				</td>
	        </tr>
          		<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>活动排期
            	</td>
				<td align="left" class="l-table-edit-td" >
					<div  style="position: relative;">
						<div style="display: inline-block;position: absolute;">
							<input type="text" class="dateEd" id="beginTime" name="beginTime" value="<fmt:formatDate value='${singleProductActivity.beginTime }' pattern='yyyy-MM-dd HH:mm:ss' />" >
						</div>
						<span style="margin-left: 170px;position: absolute;">到</span>
						<div style="margin-left: 200px;display: inline-block;margin-bottom: 10px;">
							<input type="text" class="dateEd" id="endTime" name="endTime" value="<fmt:formatDate value='${singleProductActivity.endTime }' pattern='yyyy-MM-dd HH:mm:ss' />" >
						</div>
					</div>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>审核结果
            	</td>
				<td align="left" class="l-table-edit-td" >
					<span style="margin-left: 15px;">
						<label><input type="radio" class="auditStatus" name="auditStatus" value="3" checked="checked" >排期通过</label>
					</span>
					<span style="margin-left: 25px;">
						<label><input type="radio" class="auditStatus" name="auditStatus" value="4">排期驳回</label>
					</span>
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
					<input style="margin-left: 20px;" class="l-button" type="button" value="取消" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>