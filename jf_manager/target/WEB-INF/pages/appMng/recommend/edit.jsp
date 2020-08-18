<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
.toAddAct{display:block;margin:10px 0 15px 0;}
#hasAddId{margin:10px 0 5px 0;}
#hasAddId span{margin:0 10px 6px 0;display:inline-block;font-size: 13px;}
</style>

<script type="text/javascript">
$(function ()  {
	$("#autoUpDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
	});
	$("#autoDownDate").ligerDateEditor( {
		showTime : true,
		width: 158,
		format: "yyyy-MM-dd hh:mm"
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
			var autoUpDate = document.getElementById("autoUpDate");
			var autoDownDate = document.getElementById("autoDownDate");
			if($.trim(autoUpDate.value)==""){
				commUtil.alertError("自动上架时间不能为空。");
				return;
			}
			
			if($.trim(autoDownDate.value)==""){
				commUtil.alertError("自动下架时间不能为空。");
				return;
			}
			
			if($.trim(autoUpDate.value)>=$.trim(autoDownDate.value)){
				commUtil.alertError("自动上架时间必须小于自动下架时间");
				return;
			}
			
			var autoDownDate=new Date(autoDownDate.value);
			var nowDate=new Date();
			var dateDiff=autoDownDate.getTime()-nowDate.getTime();
			if (dateDiff<=0){
				commUtil.alertError("自动下架时间必须大于当前时间");
				return;
			}
			
	    	form.submit();
		}
	});
});
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/appMng/recommend/saveSubmit.shtml">
		<input type="hidden" id="id" name="id" value="${adInfoCustom.id}"/>
		<table class="gridtable">			
			<tr>
				<td colspan="1" class="title"><c:if test="${adInfoCustom.linkType==1}">会场</c:if><c:if test="${adInfoCustom.linkType==2}">特卖</c:if>ID<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="linkId" name="linkId" value="${adInfoCustom.linkId}" disabled="disabled"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">自动上架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="autoUpDate" name="autoUpDate" value="<fmt:formatDate value='${adInfoCustom.autoUpDate}' pattern='yyyy-MM-dd HH:mm'/>" validate="{required:true}"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">自动下架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="autoDownDate" name="autoDownDate" value="<fmt:formatDate value='${adInfoCustom.autoDownDate}' pattern='yyyy-MM-dd HH:mm'/>" validate="{required:true}"/>
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
