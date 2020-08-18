<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<style type="text/css">
body{ font-size:12px;padding:10px;}
.l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin-right: 20px;}
.l-verify-tip-corner{left:25px!important;}
.l-verify-tip-content{left:32px!important;} 
</style>
<html>
<head>
<script type="text/javascript">
$(function ()  {
	var id = document.getElementById("id").value;
	if (id==""){
		$("input[name='intType']:first").attr("checked",'checked');
		$("input[name='growType']:first").attr("checked",'checked');
		$("input[name='status']:last").attr("checked",'checked');
		$("#integral_1").hide();
		$("#integral_2").hide();
		$("#grow_1").hide();
		$("#grow_2").hide();
	}else{
		changeIntType();
		changeGrowType();
	}
	
 	$(".radioIntItem").change(function() {
 		changeIntType();
	});
 	$(".radioGrowItem").change(function() { 
 		changeGrowType();
 	});
	
	$.metadata.setType("attr", "validate");
	
	var v = $("#form1").validate({
	            	
		errorPlacement: function (lable, element)
		{   
        	var elementType=$(element).attr("type");

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
			var isValidateOk=true;
			var tallyMode = $("input[name='tallyMode']:checked").val();
			if(tallyMode==undefined){
				$("input[type=radio][name='tallyMode']:last").ligerTip({ content: "请选择进出账类型。",width: 150});
    			isValidateOk=false;
			}
    
	    	if(isValidateOk){
	    		form.submit();
	    	}else{
				$("html,body").animate({scrollTop:$("body").offset().top},0);
	    	}
		}
	});
});

function changeIntType() { 
	var intType = $("input[name='intType']:checked").val(); 
	switch (intType)
	{
		case '1':
		  $("#integral_1").show();
		  $("#integral_2").hide();
		  break;
		case '2':
	      $("#integral_1").hide();
		  $("#integral_2").show();
		  break;
		default:
		  $("#integral_1").hide();
		  $("#integral_2").hide();
		  break;
	}
}
	
function changeGrowType() { 
	var growType = $("input[name='growType']:checked").val(); 
	switch (growType)
	{
		case '1':
		  $("#grow_1").show();
		  $("#grow_2").hide();
		  break;
		case '2':
		     $("#grow_1").hide();
		  $("#grow_2").show();
		  break;
		default:
		  $("#grow_1").hide();
		  $("#grow_2").hide();
		  break;
	}
}	
</script>
</head>
<body>
		<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/member/integralType/success.shtml" enctype="multipart/form-data">
		<input id="id" name="id" type="hidden" style="float:left;" value="${integralType.id}" /> 
			<table class="gridtable" >
			    <tr >
					<td class="title" width="20%">积分类型名称</td>  
					<td align="left" class="l-table-edit-td">
						<input id="name" name="name" type="text" style="float:left;width: 200px" value="${integralType.name}" validate="{required:true}"/>
					</td> 
				</tr>
				<tr>
					<td class="title"  width="30%">进出帐类型</td>
					<td align="left" class="l-table-edit-td">
					<c:forEach var="modeItem" items="${tallyModes }">
						<span class="radioClass"><input class="radioItem" type="radio" value="${modeItem.statusValue }" name="tallyMode" <c:if test="${integralType.tallyMode==modeItem.statusValue}">checked="checked"</c:if> >${modeItem.statusDesc}</span>
					</c:forEach>
					</td> 
				</tr>
				
				<tr>
					<td class="title"  width="30%">金币配置方式</td>
					<td align="left" class="l-table-edit-td">
					<c:forEach var="intItem" items="${intTypes }">
						<span class="radioClass"><input class="radioIntItem" type="radio" value="${intItem.statusValue }" name="intType" <c:if test="${integralType.intType==intItem.statusValue}">checked="checked"</c:if> >${intItem.statusDesc}</span>
					</c:forEach>
					</td> 
				</tr>

				<tr id="integral_1">
					<td class="title"  width="30%">定额金币</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" id="integral" name="integral" value="${integralType.integral}" style="float:left;width: 100px" validate="{digits:true}"/>
					</td> 
				</tr>
				<tr id="integral_2">
					<td class="title"  width="30%">金币/元</td>
					<td align="left" class="l-table-edit-td">
					 	<input type="text" id="iCharge" name="iCharge" value="${integralType.iCharge}" style="float:left;width: 100px" validate="{digits:true}"/>
					</td> 
				</tr>
				
				<tr>
					<td class="title"  width="30%">成长值配置方式</td>
					<td align="left" class="l-table-edit-td">
					<c:forEach var="growItem" items="${growTypes }">
						<span class="radioClass"><input class="radioGrowItem" type="radio" value="${growItem.statusValue }" name="growType" <c:if test="${integralType.growType==growItem.statusValue}">checked="checked"</c:if> >${growItem.statusDesc}</span>
					</c:forEach>
					</td> 
				</tr>
				
				<tr id="grow_1">
					<td class="title"  width="30%">定额成长值</td>
					<td align="left" class="l-table-edit-td">
					 	<input type="text" id="gValue" name="gValue" value="${integralType.gValue}" style="float:left;width: 100px" validate="{digits:true}"/>
					</td> 
				</tr>
				<tr id="grow_2">
					<td class="title">成长值/元</td>
					<td align="left" class="l-table-edit-td">
						<input type="text" id="gCharge" name="gCharge" value="${integralType.gCharge}" style="float:left;width: 100px" validate="{digits:true}"/>   
					</td>  
				</tr>
				<tr>
					<td class="title"  width="30%">状态</td>
					<td align="left" class="l-table-edit-td">
					<c:forEach var="statusItem" items="${status }">
						<span class="radioClass"><input class="radioItem" type="radio" value="${statusItem.statusValue }" name="status" <c:if test="${integralType.status==statusItem.statusValue}">checked="checked"</c:if> >${statusItem.statusDesc}</span>
					</c:forEach>
					</td> 
				</tr>
				<tr>
					<td class="title"  width="30%">备注</td>
					<td align="left" class="l-table-edit-td">
						<textarea rows=3 id="remarks" name="remarks" cols="45" class="text" >${integralType.remarks}</textarea>
					</td> 
				</tr>
				<tr>
				<td class="title"  width="30%">操作</td> 
				<td align="left" class="l-table-edit-td">
					<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="保存" />
                	<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" /> 	
				</td>
				</tr>
			</table> 
			 
		</form>
	</body>
</html>
