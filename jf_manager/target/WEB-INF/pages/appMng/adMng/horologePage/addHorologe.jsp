<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">
$(function () {
	$("input[name='linkType']").eq(0).attr("checked",'true');
	$("#show1-1").html('活动ID<span class="required">*</span>');
	$("#show1-2").html('<input type="text" id="linkId" name="linkId" validate="{digits:true,maxlength:9}"/>');
 	$(".radioItem").change( function() { 			
		var linkType = $("input[name='linkType']:checked").val();
		switch (linkType) {
			case '2':
				$("#show1-1").html('活动ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkId" name="linkId" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '3':
				$("#show1-1").html('商品ID<span class="required">*</span>');
				$("#show1-2").html('<input type="text" id="linkId" name="linkId" validate="{digits:true,maxlength:9}"/>');
			  	break;
			case '5':
				$("#show1-1").html('URL');
				$("#show1-2").html('<input type="text" id="linkUrl" name="linkUrl" />');
				break;
		}
	});
	
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
			//判断链接类型
			var linkType = $("input[name='linkType']:checked").val();
			var linkId = document.getElementById("linkId");
			var linkUrl = document.getElementById("linkUrl");
			var pic = document.getElementById("pic");
			var autoUpDate = document.getElementById("autoUpDate");
			var autoDownDate = document.getElementById("autoDownDate");
			if(linkType != '4' && linkType != '5' && linkType != '6' && $.trim(linkId.value)==""){
    			$("#linkId").ligerTip({ content: "该字段不能为空。"});
    			linkId.focus();
    			return;
			}else if(linkType == '4' && $.trim(linkUrl.value)==""){
    			$("#linkUrl").ligerTip({ content: "该字段不能为空。"});
    			linkUrl.focus();
    			return;
			}
			
			if($.trim(pic.value)==""){
				commUtil.alertError("请上传图片。");
				return;
			}
			
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

function ajaxFileUploadLogo() {
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "logoPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				$("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
				$("#pic").val(result.FILE_PATH);
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/appMng/adMng/saveSubmit.shtml">
		<input type="hidden" id="type" name="type" value="1"/>
		<input type="hidden" id="catalog" name="catalog" value="${catalog }"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">广告类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<c:forEach var="typeItem" items="${linkTypeList }">
						<c:if test="${typeItem.statusValue != 1 and typeItem.statusValue != 4 and typeItem.statusValue != 6  }">
							<span class="radioClass"><input class="radioItem" type="radio" value="${typeItem.statusValue }" name="linkType">${typeItem.statusDesc}</span>
						</c:if>
					</c:forEach>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">所属广告位<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select style="width: 160px;" id="position" name="position">
					<c:forEach var="positionItem" items="${positionList }">
						<c:if test="${positionItem.statusValue==1 || positionItem.statusValue==2 || positionItem.statusValue==3}">
							<option value="${positionItem.statusValue}">${positionItem.statusDesc}</option>
						</c:if>
					</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr id="show1">
				<td id="show1-1" colspan="1" class="title"></td>
				<td id="show1-2" colspan="5" align="left" class="l-table-edit-td" ></td>
			</tr>
			<tr>
               <td  class="title" width="20%">广告图片：</td>
               <td align="left" class="l-table-edit-td" >
               		<div><img id="logoPic" style="width: 300px;height: 150px" alt="" src=""></div>
	    			<div style="float: left;height: 30px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadLogo();" />
	    				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    			</div>
	    			<input id="pic" name="pic" type="hidden" value="">
               </td>
           </tr>
			
			<tr>
				<td colspan="1" class="title">自动上架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="autoUpDate" name="autoUpDate"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">自动下架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="autoDownDate" name="autoDownDate"/>
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
