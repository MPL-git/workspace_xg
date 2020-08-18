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
			$("#platformPreferential").bind('keyup',function(){
				var platformpreferential = $(this).val();
				if(/^\d+(?=\.{0,1}\d+$|$)/.test(platformpreferential)){
					var arrivalprice = $("#originalPriceTd").text()-platformpreferential;
					$("#arrivalPrice").text("（到手价："+arrivalprice+"）");
				}else{
				    commUtil.alertError("请输入正确的数字！");
				}
			});
			$("input[name='type']").bind('click',function(){
				var type = $(this).val();
				if(type!="3"){
					$("#seckillTypeTr").hide();
					$.ajax({
						 type : 'POST',
						 url : "${pageContext.request.contextPath}/singleProductActivity/getSeckillTimeListBySeckillType.shtml",
						 data : {seckillType : "1"},
						 dataType : 'json',
						 success : function(data){
							 if(data == null || data.statusCode != 200){
								 commUtil.alertError(json.message);
							 } else{
								 var seckillTimeList = data.seckillTimeList;
								 var array = [];
								 array.push('<option value="">请选择...</option>');
								 for(var i=0;i<seckillTimeList.length;i++){
									 array.push('<option value="'+seckillTimeList[i].id+'">'+seckillTimeList[i].startHours+'：'+seckillTimeList[i].startMin+'</option>');
								 }
								 $("#startTime").empty();
								 $("#startTime").append(array.join(""));
							 }
						 },
						 error : function(e) {
							 commUtil.alertError("系统异常请稍后再试");
						 }
					 });
				}else{
					$("#seckillTypeTr").show();
					var seckillType = $("input[name='seckillType']:checked").val();
					$.ajax({
						 type : 'POST',
						 url : "${pageContext.request.contextPath}/singleProductActivity/getSeckillTimeListBySeckillType.shtml",
						 data : {seckillType : seckillType},
						 dataType : 'json',
						 success : function(data){
							 if(data == null || data.statusCode != 200){
								 commUtil.alertError(json.message);
							 } else{
								 var seckillTimeList = data.seckillTimeList;
								 var array = [];
								 array.push('<option value="">请选择...</option>');
								 for(var i=0;i<seckillTimeList.length;i++){
									 array.push('<option value="'+seckillTimeList[i].id+'">'+seckillTimeList[i].startHours+'：'+seckillTimeList[i].startMin+'</option>');
								 }
								 $("#startTime").empty();
								 $("#startTime").append(array.join(""));
							 }
						 },
						 error : function(e) {
							 commUtil.alertError("系统异常请稍后再试");
						 }
					 });
				}
			});
			
			$("input[name='seckillType']").bind('click',function(){
				var seckillType = $(this).val();
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/singleProductActivity/getSeckillTimeListBySeckillType.shtml",
					 data : {seckillType : seckillType},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.statusCode != 200){
							 commUtil.alertError(json.message);
						 } else{
							 var seckillTimeList = data.seckillTimeList;
							 var array = [];
							 array.push('<option value="">请选择...</option>');
							 for(var i=0;i<seckillTimeList.length;i++){
								 array.push('<option value="'+seckillTimeList[i].id+'">'+seckillTimeList[i].startHours+'：'+seckillTimeList[i].startMin+'</option>');
							 }
							 $("#startTime").empty();
							 $("#startTime").append(array.join(""));
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
			});
			
			$(".dateEditor").ligerDateEditor( {
				showTime : false,
				labelAlign : 'left'
			});
			$(".dateEd").ligerDateEditor( {
				format  : "yyyy-MM-dd hh:mm",
				showTime : true,
				labelAlign : 'left',
				width : 150
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
			var auditStatus = $("input[name='auditStatus']:checked").val();
			if(auditStatus == '4') {
				var remarks = $("#remarks").val();
				if(remarks == '') {
					commUtil.alertError("排期驳回时，原因说明不能为空！");
					return;
				}
			}
			var flag = '${flag }';
			if(flag == '1') {//秒杀
				var seckillType = $("input[name='seckillType']:checked").val();
				if(!seckillType){
					commUtil.alertError("请选择秒杀类型");
					return;
				}
				if(auditStatus == '3') {
					var beginTime = $("#beginTime").val();
					var startTime = $("#startTime option:selected").text();
					if(beginTime == '' || startTime == '请选择...') {
						commUtil.alertError("排期通过时，活动排期不能为空！");
						return;
					}
					var endDate = beginTime + " " + $.trim(startTime);
					var date = new Date();
					var startDate = date.format("yyyy-MM-dd hh:mm");
					if(!compareTime(startDate, endDate)) {
						commUtil.alertError("排期时间，必须大于现在时间！");
						return;
					}
				}
			}
			if(flag == '2' || flag == '3') {
				var beginTime = $("#beginTime2").val();
				var startTime = $("#endTime2").val();
				if(auditStatus == '3') {
					if(beginTime == '' || startTime == '') {
						commUtil.alertError("排期通过时，活动排期不能为空！");
						return;
					}
					var date = new Date();
					var startDate = date.format("yyyy-MM-dd hh:mm");
					if(!compareTime(startDate, beginTime)){
						commUtil.alertError("排期开始时间，必须大于现在时间！");
						return;
					}
					if(!compareTime(beginTime, startTime)){
						commUtil.alertError("开始时间必须小于结束时间！");
						return;
					}
					if(!differenceTime(beginTime, startTime, 30)){
						commUtil.alertError("结束时间与开始时间，相差不能超过30天！");
						return;
					}
				}
			}
			var dataJson = $("#form1").serializeArray();
			$.ajax({
				url : "${pageContext.request.contextPath}/singleProductActivity/saveScheduleAuditOrRetrial.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						$.ligerDialog.alert('提交成功！', function (yes) {
							$(window.parent.document).find("#searchbtn").click();
							frameElement.dialog.close();});
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
		<input type="hidden" name="id" value="${id }">
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="ids" value="${ids}">
		<table class="gridtable">
           	<c:if test="${flag == '1' }">
           		<tr id="seckillTypeTr">
	            	<td class="title" width="20%">
	            		秒杀类型
	            	</td>
					<td align="left" class="l-table-edit-td" >
						<span style="margin-left: 15px;"><input type="radio" name="seckillType" value="1"  >限时秒杀</span>
						<span style="margin-left: 15px;"><input type="radio" name="seckillType" value="2"  >会场秒杀</span>
					</td>
	           	</tr>
				<tr>
	            	<td class="title" width="20%">活动排期</td>
					<td align="left" class="l-table-edit-td" >
						<div>
							<input type="text" class="dateEditor" id="beginTime" name="beginTime" >
						</div>
						<div style="margin-left: 150px;">
							<div class="search-td" style="position:relative;">
								<div class="search-td-combobox-condition" style="position:absolute; top:-22px;">
									<select id="startTime" name="seckillTimeId" style="height: 23px;width: 80px;" >
										<option value="">请选择...</option>
										<c:forEach var="seckillTime" items="${seckillTimeList }">
											<option value="${seckillTime.id }">
												${seckillTime.startHours }:${seckillTime.startMin }
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</td>
	           	</tr>
           	</c:if>
           	
           	<c:if test="${flag == '2' || flag == '3'}">
           		<tr>
	            	<td class="title" width="20%">活动排期</td>
					<td align="left" class="l-table-edit-td" >
						<div  style="position: relative;">
							<div style="display: inline-block;position: absolute;">
								<input type="text" class="dateEd" id="beginTime2" name="beginTime" >
							</div>
							<span style="margin-left: 170px;position: absolute;">到</span>
							<div style="margin-left: 200px;display: inline-block;margin-bottom: 10px;">
								<input type="text" class="dateEd" id="endTime2" name="endTime" >
							</div>
						</div>
					</td>
	           	</tr>
           	</c:if>
			<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>审核结果
            	</td>
				<td align="left" class="l-table-edit-td" >
					<input type="radio" class="auditStatus" name="auditStatus" value="3" checked>排期通过	
					<input style="margin-left: 20px;" type="radio" class="auditStatus" name="auditStatus" value="4">排期驳回				
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