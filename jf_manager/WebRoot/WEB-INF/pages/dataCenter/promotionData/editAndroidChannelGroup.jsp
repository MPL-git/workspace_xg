<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function (){ 
		var type = $("input[name='type']:checked").val();
		if (typeof(type)=="undefined"){
			$("input[name='type']").get(0).checked=true; 
		}
		else if (type=='2'){
		 	$("#groupId").removeAttr("validate");
		 	$("#discountRate").removeAttr("validate");
			document.getElementById('groupIdTr').style.display = 'none';
		 	document.getElementById('discountRateTr').style.display = 'none';
		}
	 	$(".radioItem").change( 
 			function() {
 				var type = $("input[name='type']:checked").val(); 
 				if (type == 2)
 				{ 
 					$("#groupId").removeAttr("validate");
 					$("#discountRate").removeAttr("validate");
 					document.getElementById('groupIdTr').style.display = 'none';
 					document.getElementById('discountRateTr').style.display = 'none';

 				} 
 				else
 				{ 
 					$("#groupId").attr("validate","{required:true,maxlength:30}");
 					$("#discountRate").attr("validate","{required:true,number:true,maxlength:10}");
 					document.getElementById('groupIdTr').style.display = '';
 					document.getElementById('discountRateTr').style.display = '';
 				}
	 	});
		$.metadata.setType("attr", "validate");
		var v = $("#form1").validate({
			errorPlacement: function (lable, element) {   
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
			success: function (lable,element) {
				lable.ligerHideTip();
				lable.remove();
			},
			submitHandler: function (form) {   			
				//集合优惠率不能小于0
				var type = $("input[name='type']:checked").val();
				if (type=='1'){
       				var discountRate=document.getElementById("discountRate");
       				if ($.trim(discountRate.value)<=0){
       					$("#discountRate").ligerTip({ content: "请输入非零正数!"});
       					discountRate.focus();
       					return;
       				}
				}
		    	form.submit();
			}
		});
	})
</script>
<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/promotionData/androidChannelGroup/save.shtml"  >
		<input id="id" name="id" type="hidden" value="${androidChannelGroup.id}" />
		<table class="gridtable">
		
			<tr>
				<td class="title">渠道集合名称*</td>
				<td align="left" class="l-table-edit-td">
				<input id="groupName" 
					name="groupName" type="text" value="${androidChannelGroup.groupName}"
					style="float:left;width: 200px;" validate="{required:true,maxlength:50}" />
				</td>
			</tr>
			
			<tr>
				<td class="title">渠道包前缀*</td>
				<td align="left" class="l-table-edit-td">
				<input id="prefix" 
					name="prefix" type="text" value="${androidChannelGroup.prefix}"
					style="float:left;width: 200px;" validate="{required:true,maxlength:100}" />
				</td>
			</tr>
			
			<tr>
				<td class="title">渠道集合类型*</td>
				<td align="left" class="l-table-edit-td">
				<span class="radioClass"><input class="radioItem" type="radio" value="1" name="type" <c:if test="${androidChannelGroup.type==1}">checked="checked"</c:if> >小渠道集合</span>
				<%--<span class="radioClass"><input class="radioItem" type="radio" value="2" name="type" <c:if test="${androidChannelGroup.type==2}">checked="checked"</c:if> >大渠道集合</span>--%>
				</td>
			</tr>
			
			<tr id="groupIdTr">
				<td class="title">渠道集合ID*</td>
				<td align="left" class="l-table-edit-td">
				<input id="groupId" 
					name="groupId" type="text" value="${androidChannelGroup.groupId}"
					style="float:left;width: 200px;" validate="{required:true,maxlength:30}" />
				</td>
			</tr>
			
			<tr id="discountRateTr">
				<td class="title">渠道集合优惠率*</td>
				<td align="left" class="l-table-edit-td">
				<input id="discountRate" 
					name="discountRate" type="text" value="${androidChannelGroup.discountRate}"
					style="float:left;width: 200px;" validate="{required:true,number:true,maxlength:10}" />
				</td>
			</tr>		
			
			<tr>
               <td class="title"  style="text-align: center;" >状态*</td>  
	               <td class="statu_value" colspan="5" height="40px">
						<select name="status"  id = "status" validate="{required:true}">
						<option value="">--请选择--</option>
						<option value="1" <c:if test="${1==androidChannelGroup.status}">selected="selected"</c:if>>启用</option>
						<option value="0" <c:if test="${0==androidChannelGroup.status}">selected="selected"</c:if>>禁用</option>
						</select>
				   </td> 
            </tr>
 
            <tr>
				<td class="title">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button"  value="保存"  /></div>
	               		<div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px" class="l-button" value="取消" onclick="frameElement.dialog.close();" /></div>
					</div>
				</td>
			</tr>
            

		</table>
	</form>
</body>
</html>
