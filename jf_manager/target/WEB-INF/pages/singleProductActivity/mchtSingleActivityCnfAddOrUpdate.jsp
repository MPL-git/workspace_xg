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
	
		$(function(){
			
			var activityType = '${mchtSingleActivityCnfCustom.activityType }';
			var limitMchtQuality = '${mchtSingleActivityCnfCustom.limitMchtQuality }';
			if(limitMchtQuality != '') {
				$("#defaultNum").html(limitMchtQuality);
			}else {
				if(activityType == '1') 
					$("#defaultNum").html(5);
				if(activityType == '2') 
					$("#defaultNum").html(5);
				if(activityType == '3') 
					$("#defaultNum").html(3);
				if(activityType == '4') 
					$("#defaultNum").html(0);
				if(activityType == '5') 
					$("#defaultNum").html(3);
				if(activityType == '6') 
					$("#defaultNum").html(100);
				if(activityType == '7') 
					$("#defaultNum").html(0);
				if(activityType == '8') 
					$("#defaultNum").html(0);
				if(activityType == '10') 
					$("#defaultNum").html(0);
			}
			
			$("#form1").validate({
				rules: {
					mchtCode: {
						mchtCodeFlag: ""
					},
					activityType: "required",
					limitQuality: {
						limitQualityFlag: ""
					}
				},
				messages: {
					activityType: "单品活动名称不能为空"
				},
				errorPlacement: function (lable, element) {   
		        	var elementType = $(element).attr("type");
		        	if($(element).hasClass("l-text-field")){
		        		$(element).parent().ligerTip({
							content : lable.html(),width: 180
						});
		        	}else if('radio'==elementType){
		        		var radioName=$(element).attr("name");
		        		$("input[type=radio][name="+radioName+"]:last").ligerTip({
							content : lable.html(),width: 180
						});
		        	}else{
		        		$(element).ligerTip({
							content : lable.html(),width: 180
						});
		        	}
				},
				success: function (lable,element){
					lable.ligerHideTip();
					lable.remove();
				},
				submitHandler: function (form){
					var flag = true;
					var id = $("#id").val();
					if(!id) {
						var mchtId = $("#mchtId").val();
						var activityType = $("#activityType").val();
						$.ajax({
							 type : 'POST',
							 url : "${pageContext.request.contextPath}/singleProductActivity/getMchtSingleActivityCnf.shtml",
							 data : {mchtId : mchtId, activityType : activityType},
							 dataType : 'json',
							 async : false,
							 success : function(data){
								 if(data.code == 200) {
									 commUtil.alertError("对不起，该商家已添加过，请不要重复添加！");
									 flag = false;
								 }
							 },
							 error : function(e) {
								 commUtil.alertError("系统异常请稍后再试");
							 }
						 });
					}
					if(flag) {
				    	form.submit();
					}
				}
			}); 
			
			
			$.validator.addMethod("mchtCodeFlag", function(value,element,params){
				var flag = true;
				$.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/singleProductActivity/getMchtInfo.shtml",
					 data : {mchtCode : value},
					 dataType : 'json',
					 async : false,
					 success : function(data){
						 if(data == null || data.code != 200) {
							 flag = false;
						 }else{
							 $("#mchtId").val(data.data.id);
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试");
					 }
				 });
				return flag;
			}, "此商家序号不存在");
			
			$.validator.addMethod("limitQualityFlag", function(value,element,params){
				if(value.match(/^[0-9]\d*$/) != null) {
					return true;
				}else {
					return false;
				}
			}, "大于等于零的整数");
			
		});
		
		function updateActivityType() {
			var activityType = $("#activityType").val();
			if(activityType != '') {
				var limitMchtQuality = $("#"+activityType).attr("limitMchtQuality");
				if(limitMchtQuality != '' && limitMchtQuality != undefined) {
					$("#defaultNum").html(limitMchtQuality);
				}else {
					if(activityType == '1') 
						$("#defaultNum").html(5);
					if(activityType == '2') 
						$("#defaultNum").html(5);
					if(activityType == '3') 
						$("#defaultNum").html(3);
					if(activityType == '4') 
						$("#defaultNum").html(0);
					if(activityType == '5') 
						$("#defaultNum").html(3);
					if(activityType == '6') 
						$("#defaultNum").html(100);
					if(activityType == '7') 
						$("#defaultNum").html(0);
					if(activityType == '8') 
						$("#defaultNum").html(0);
					if(activityType == '10') 
						$("#defaultNum").html(0);
				}
			}else {
				$("#defaultNum").html('');
			}
		}
		
		
</script>

</head>
	<body style="margin: 10px;">
	<form name="form1" class="form1" action="${pageContext.request.contextPath}/singleProductActivity/saveOrUpdateMchtSingleActivityCnf.shtml" method="post" id="form1" enctype="multipart/form-data">
		<input type="hidden" id="id" name="id" value="${mchtSingleActivityCnfCustom.id }">
		<input type="hidden" id="mchtId" name="mchtId" value="">
		<table class="gridtable">
			<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>商家序号
            	</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:135px;" type="text" id="mchtCode" name="mchtCode" value="${mchtSingleActivityCnfCustom.mchtCode }" >
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>单品活动名称
            	</td>
				<td align="left" class="l-table-edit-td" >
					<select id="activityType" name="activityType" style="width: 135px;" onchange="updateActivityType();" >
						<option value="">请选择...</option>
						<c:forEach var="activityType" items="${activityTypeList }">
							<option value="${activityType.statusValue }" id="${activityType.statusValue }" <c:forEach var="singleProductActivityCnf" items="${singleProductActivityCnfList}"> <c:if test="${singleProductActivityCnf.activityType == activityType.statusValue}">limitMchtQuality="${singleProductActivityCnf.limitMchtQuality}"</c:if></c:forEach><c:if test="${mchtSingleActivityCnfCustom.activityType == activityType.statusValue }">selected</c:if> >
								${activityType.statusDesc }
							</option>
						</c:forEach>
					</select>
				</td>
           	</tr>
			<tr>
            	<td class="title" width="20%">默认报名数</td>
				<td align="left" class="l-table-edit-td" id="defaultNum" ></td>
				
           	</tr>
           	<tr>
            	<td class="title" width="20%">
            		<span style="color:red;">*</span>报名数改为
            	</td>
				<td align="left" class="l-table-edit-td" >
					<input style="width:135px;" type="text" id="limitQuality" name="limitQuality" value="${mchtSingleActivityCnfCustom.limitQuality }" >
				</td>
           	</tr>
           	<tr>
            	<td class="title" width="20%">
            		开放特殊报名
            	</td>
				<td align="left" class="l-table-edit-td" >
					<input type="radio" name="isSpecial" value="1" <c:if test="${mchtSingleActivityCnfCustom.isSpecial eq 1}">checked="checked"</c:if>>开放
					<input type="radio" name="isSpecial" value="0" <c:if test="${empty mchtSingleActivityCnfCustom.isSpecial || mchtSingleActivityCnfCustom.isSpecial eq 0}">checked="checked"</c:if>>关闭
				</td>
           	</tr>
			
			<tr>
            	<td class="title" width="20%">操作</td>
				<td align="left" class="l-table-edit-td" >
					<input type="submit" class="l-button l-button-submit" value="提交" /> 
					<input style="margin-left: 20px;" class="l-button" type="button" value="取消" onclick="frameElement.dialog.close()" />
				</td>
           	</tr>
		</table> 
	</form>
	</body>
</html>