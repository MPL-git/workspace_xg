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
				format  : "yyyy-MM-dd hh:mm:ss",
				showTime : true,
				labelAlign : 'left',
				width : 150
			});

			$(".l-dialog-close").live("click", function(){
				window.location.href = window.location.href;
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
			//debugger;
			var status = '${singleProductActivity.auditStatus }';
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
				if(auditStatus == '3') {
					var beginTime = $("#beginTime").val();
					var startTime = $("#startTime option:selected").text();
					if(beginTime == '' || startTime == '请选择...') {
						commUtil.alertError("排期通过时，活动排期不能为空！");
						return;
					}
					if(status != '3' && status != '4') {
						var endDate = beginTime + " " + $.trim(startTime);
						var date = new Date();
						var startDate = date.format("yyyy-MM-dd hh:mm");
						if(!compareTime(startDate, endDate)) {
							commUtil.alertError("排期时间，必须大于现在时间！");
							return;
						}
					}
				}
			}
			if(flag == '2') {
				var beginTime = $("#beginTime2").val();
				var startTime = $("#endTime2").val();
				if(status == '3' || status == '4') {
					if(auditStatus == '3') {
						if(beginTime == '' || startTime == '') {
							commUtil.alertError("排期通过时，活动排期不能为空！");
							return;
						}
						if(!compareTime(beginTime, startTime)){
							commUtil.alertError("开始时间必须大于结束时间！");
							return;
						}
						if(!differenceTime(beginTime, startTime, 30)){
							commUtil.alertError("结束时间与开始时间，相差不能超过30天！");
							return;
						}
					}
				}else{
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
							commUtil.alertError("开始时间必须大于结束时间！");
							return;
						}

                        var startDateTime = new Date(beginTime).getTime();
                        var endDateTime = new Date(startTime).getTime();
                        var datesTime = 30*24*60*60*1000;
                        var differenceTime1 = endDateTime - startDateTime;

                        if(differenceTime1 > datesTime) {
                            commUtil.alertError("结束时间与开始时间，相差不能超过30天！");
                            return;
                        }

						/*if(!differenceTime(beginTime, startTime, 30)){
							commUtil.alertError("结束时间与开始时间，相差不能超过30天！");
							return;
						}*/
					}
				}
			}
// 			if(flag != '3') {
// 				var type = $("input[name='type']:checked").val();
// 				if(auditStatus == '3' && type == undefined) {
// 					commUtil.alertError("请选择活动类型！");
// 					return;
// 				}
// 			}
			if(flag == '3') {
				var remarks = $("#remarks").val();
				if(remarks == '') {
					commUtil.alertError("复审驳回时，原因说明不能为空！");
					return;
				}
			}
			var platformPreferential = $("#platformPreferential").val();
			if(platformPreferential){
				if(Number(platformPreferential)<0){
					commUtil.alertError("平台补贴价格应大于等于0");
					return;
				}
				var originalPrice = $("#currentPriceTd").text();
				if(originalPrice){
					var priceMin = originalPrice.split("-")[0];
					if(Number(platformPreferential)>=Number(priceMin)){
						commUtil.alertError("平台补贴不能大于等于最小活动价格");
						return;
					}
				}
			}
			if($("input[name='seckillStatus']").attr("checked")==true) {
				var seckillPrice = $("#seckillPrice").val()*100;
				var acPrice = $("#originalPriceContrast").text().split("-")[0]*100;
			
				if(seckillPrice>=acPrice||seckillPrice==null||seckillPrice==""||seckillPrice<=0){
					commUtil.alertError("秒杀价设置有误，请重新设置。");
					return;
				}
			}
			
 			/* $("#form1").submit(); */
			 formSubmit();
		}

		function updateProductItem(id, flag) {
			$.ligerDialog.open({
				height: $(window).height() - 40,
				width: $(window).width()*0.75,
				title: "法务审核",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/product/lawAuditProduct.shtml?id="+id+"&flag="+flag,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: false,
				slide: false,
				data: null
			});
		}
		
		
		
		function formSubmit() {
			$.ajax({
				url : "${pageContext.request.contextPath}/singleProductActivity/saveScheduleAuditOrRetrial.shtml",
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
		<input type="hidden" name="flag" value="${flag }">
		<input type="hidden" name="aPrice" value="${singleProductActivity.activityPrice }">
		<table class="gridtable">
			<tr height="110px">
            	<td class="title" width="20%">商家优势说明</td>
				<td align="left" class="l-table-edit-td" style="color:red;" >${singleProductActivity.remarks }</td>
           	</tr>
           	
           	<c:if test="${flag == '1' }">
           		<tr>
	            	<td class="title" width="20%">
	            		活动类型
	            	</td>
					<td align="left" class="l-table-edit-td" >
						<c:forEach items="${typeList }" var="type" >
							<c:if test="${type.statusValue == singleProductActivity.type}">
								<span style="margin-left: 15px;"><input type="radio" name="type" value="${type.statusValue }" <c:if test="${type.statusValue == singleProductActivity.type }">checked</c:if> >${type.statusDesc }</span>
							</c:if>
						</c:forEach>
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
	            	<td class="title" width="20%">活动排期</td>
					<td align="left" class="l-table-edit-td" >
						<div>
							<c:if test="${singleProductActivity.auditStatus eq 1}">
								<input type="text" class="dateEditor" id="beginTime" name="beginTime" value="<fmt:formatDate value='${singleProductActivity.expectedDate}' pattern='yyyy-MM-dd'/>">
							</c:if>
							<c:if test="${singleProductActivity.auditStatus ne 1}">
								<input type="text" class="dateEditor" id="beginTime" name="beginTime" value="<fmt:formatDate value='${singleProductActivity.beginTime}' pattern='yyyy-MM-dd'/>">
							</c:if>
						</div>
						<div style="margin-left: 150px;">
							<div class="search-td" style="position:relative;">
								<div class="search-td-combobox-condition" style="position:absolute; top:-22px;">
									<select id="startTime" name="seckillTimeId" style="height: 23px;width: 80px;" >
										<option value="">请选择...</option>
										<c:forEach var="seckillTime" items="${seckillTimeList }">
											<option value="${seckillTime.id }" <c:if test="${seckillTime.id eq singleProductActivity.seckillTimeId}">selected="selected"</c:if>>
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
           	
           	<c:if test="${flag == '2' }">
           		<tr>
	            	<td class="title" width="20%">
	            		活动类型
	            	</td>
					<td align="left" class="l-table-edit-td" >
						<c:choose>
							<c:when test="${not empty zbFlag and singleProductActivity.type == 2 }">
								<c:forEach items="${typeList }" var="type" >
									<c:if test="${type.statusValue == 2 && type.statusValue == singleProductActivity.type}">
										<span style="margin-left: 15px;"><input type="radio" name="type" value="${type.statusValue }" <c:if test="${type.statusValue == singleProductActivity.type }">checked</c:if> >${type.statusDesc }</span>
									</c:if>
								</c:forEach>
							</c:when>
							<c:when test="${not empty zbFlag and singleProductActivity.type == 6 }">
								<c:forEach items="${typeList }" var="type" >
									<c:if test="${type.statusValue == 6 && type.statusValue == singleProductActivity.type}">
										<span style="margin-left: 15px;"><input type="radio" name="type" value="${type.statusValue }" <c:if test="${type.statusValue == singleProductActivity.type }">checked</c:if> >${type.statusDesc }</span>
									</c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach items="${typeList }" var="type" >
									<c:if test="${type.statusValue != '3' && type.statusValue == singleProductActivity.type}">
										<span style="margin-left: 15px;"><input type="radio" name="type" value="${type.statusValue }" <c:if test="${type.statusValue == singleProductActivity.type }">checked</c:if> >${type.statusDesc }</span>
									</c:if>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</td>
	           	</tr>
           		<tr>
	            	<td class="title" width="20%">活动排期</td>
					<td align="left" class="l-table-edit-td" >
						<div  style="position: relative;">
							<div style="display: inline-block;position: absolute;">
								<input type="text" class="dateEd" id="beginTime2" name="beginTime" value="<fmt:formatDate value='${singleProductActivity.beginTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
							</div>
							<span style="margin-left: 170px;position: absolute;">到</span>
							<div style="margin-left: 200px;display: inline-block;margin-bottom: 10px;">
								<input type="text" class="dateEd" id="endTime2" name="endTime" value="<fmt:formatDate value='${singleProductActivity.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
							</div>
						</div>
					</td>
	           	</tr>
           	</c:if>



				<tr>
					<td class="title" width="20%">报名价格</td>
					<td align="left" class="l-table-edit-td" id="originalPriceTd">
						${singleProductActivity.activityPrice}
					</td>
				</tr>
				<tr>
					<td class="title" width="20%">最新价格</td>
					<td align="left" class="l-table-edit-td" id=" currentPriceTd">
						<original id=originalPriceContrast>${currentPrice}</original>
						<c:if test="${singleProductActivity.mchtType == '1'}">
							<a href="javascript:void(0);" onclick="updateProductItem(${singleProductActivity.productId},1);">【修改】</a>
						</c:if>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<c:if test="${singleProductActivity.type=='3'||singleProductActivity.type=='4'}">
							<input type="checkbox" class="seckillStatus" name="seckillStatus" value="1">是否低价秒杀
							<input type="text" id="seckillPrice" name="seckillPrice" value="" style="width:75px;">
						</c:if>
					</td>
				</tr>

           		<tr>
	            	<td class="title" width="20%">活动库存</td>
					<td align="left" class="l-table-edit-td" >
						商品当前库存：${stock}
					</td>
	           	</tr>
	           	
           	<c:if test="${singleProductActivity.type eq 4}">
           		<tr>
	            	<td class="title" width="20%">平台补贴</td>
					<td align="left" class="l-table-edit-td" >
						<input type="text" id="platformPreferential" name="platformPreferential" value="${singleProductActivity.platformPreferential}"><span id="arrivalPrice">(到手价：${arrivalPrice})</span>
					</td>
	           	</tr>
           	</c:if>
           	
           	<c:if test="${flag == '3' }">
           		<tr>
	            	<td class="title" width="20%">竞品价格</td>
					<td align="left" class="l-table-edit-td" >
						<input type="text" style="width:100px;" disabled="disabled" value="${singleProductActivity.comparePrice }">元
					</td>
	           	</tr>
	           	<tr>
	            	<td class="title" width="20%">活动排期</td>
					<td align="left" class="l-table-edit-td" >
						<input value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${singleProductActivity.beginTime }' />" disabled="disabled" >
						<span style="margin: 0px 10px;">到</span>
						<input value="<fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${singleProductActivity.endTime }' />" disabled="disabled" >
					</td>
	           	</tr>
	           	<tr>
	            	<td class="title" width="20%">
	            		<span style="color:red;">*</span>审核结果
	            	</td>
					<td align="left" class="l-table-edit-td" >
						<input type="radio" class="auditStatus" name="auditStatus" value="5" checked>复审驳回
					</td>
	           	</tr>
           	</c:if>
           	
           	<c:if test="${flag != '3' }">
				<tr>
	            	<td class="title" width="20%">
	            		<span style="color:red;">*</span>审核结果
	            	</td>
					<td align="left" class="l-table-edit-td" >
						<input type="radio" class="auditStatus" name="auditStatus" value="3" checked>排期通过	
						<c:if test="${singleProductActivity.auditStatus != '4' and singleProductActivity.auditStatus != '3' }">
							<input style="margin-left: 20px;" type="radio" class="auditStatus" name="auditStatus" value="4">排期驳回				
						</c:if>			
					</td>
	           	</tr>
	        </c:if>
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