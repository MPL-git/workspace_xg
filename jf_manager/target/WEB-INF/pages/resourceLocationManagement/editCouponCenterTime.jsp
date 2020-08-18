<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
</style>
<script type="text/javascript">

$(function ()  {
    var startHoursDiv=$("#startHoursDiv").text();
    var startMinDiv=$("#startMinDiv").text();
    var continueHoursDiv=$("#continueHoursDiv").text();
    var continueMinDiv=$("#continueMinDiv").text();

    var startHoursHtml = [];
    for(var j=0 ; j<24; j++){
        if(j<10){
            if(startHoursDiv && j == startHoursDiv ){
                startHoursHtml.push("<option selected value=0"+j+">0"+j+"</option>");
			}else{
                startHoursHtml.push("<option value=0"+j+">0"+j+"</option>");
			}
		}else {
            if(startHoursDiv && j == startHoursDiv ){
                startHoursHtml.push("<option selected value="+j+">"+j+"</option>");
            }else {
                startHoursHtml.push("<option value=" + j + ">" + j + "</option>");
            }
		}
    }
    $("#startHours").html(startHoursHtml.join(""));
    var continueHoursHtml = [];
    for(var j=0 ; j<24; j++){
        if(j<10){
            if(continueHoursDiv && j == continueHoursDiv ){
                continueHoursHtml.push("<option selected value=0"+j+">0"+j+"</option>");
            }else{
                continueHoursHtml.push("<option value=0"+j+">0"+j+"</option>");
            }
        }else {
            if(continueHoursDiv && j == continueHoursDiv ){
                continueHoursHtml.push("<option selected value="+j+">"+j+"</option>");
            }else {
                continueHoursHtml.push("<option value=" + j + ">" + j + "</option>");
            }
        }
    }
    $("#continueHours").html(continueHoursHtml.join(""));

    var startMinHtml = [];
    for(var j=0 ; j<60; j++){
        if(j<10){
            if(startMinDiv && j == startMinDiv ){
                startMinHtml.push("<option selected value=0"+j+">0"+j+"</option>");
            }else {
                startMinHtml.push("<option value=0" + j + ">0" + j + "</option>");
            }
        }else {
            if(startMinDiv && j == startMinDiv ){
                startMinHtml.push("<option selected value="+j+">"+j+"</option>");
            }else {
                startMinHtml.push("<option value=" + j + ">" + j + "</option>");
            }
		}
    }
    $("#startMin").html(startMinHtml.join(""));

    var continueMinHtml = [];
    for(var j=0 ; j<60; j++){
        if(j<10){
            if(continueMinDiv && j == continueMinDiv ){
                continueMinHtml.push("<option selected value=0"+j+">0"+j+"</option>");
            }else {
                continueMinHtml.push("<option value=0" + j + ">0" + j + "</option>");
            }
        }else {
            if(continueMinDiv && j == continueMinDiv ){
                continueMinHtml.push("<option selected value="+j+">"+j+"</option>");
            }else {
                continueMinHtml.push("<option value=" + j + ">" + j + "</option>");
            }
        }
    }
    $("#continueMin").html(continueMinHtml.join(""));
});

function commitSave(){

    $.ajax({
        url : "${pageContext.request.contextPath}/resourceLocationManagement/saveEditCouponCenterTime.shtml",
        type : 'POST',
        dataType : 'json',
        cache : false,
        async : false,
        data : {"id":$("#id").val(),
            "startHours":$("#startHours").val(),
            "startMin":$("#startMin").val(),
            "continueHours":$("#continueHours").val(),
            "continueMin":$("#continueMin").val()
        },
        timeout : 30000,
        success : function(data) {
            if ("0000" == data.returnCode) {
                parent.location.reload();
            }else{
                commUtil.alertError(data.returnMsg);
                return false;
            }
        },
        error : function() {
            $.ligerDialog.error('操作超时，请稍后再试！');
        }
    });
}

</script>

<html>
<body>
	<form method="post" id="form1" name="form1" >
		<input type="hidden" id="id" name="id" value="${couponCenterTime.id}"/>
		<div id="startHoursDiv" style="display: none">${couponCenterTime.startHours}</div>
		<div id="startMinDiv" style="display: none">${couponCenterTime.startMin}</div>
		<div id="continueHoursDiv" style="display: none">${couponCenterTime.continueHours}</div>
		<div id="continueMinDiv" style="display: none">${couponCenterTime.continueMin}</div>
		<table class="gridtable">

			<tr>
				<td colspan="1" class="title">开始时间<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select id="startHours" name="startHours"  style="width: 80px;">
						<option value="">请选择</option>
					</select>
					&nbsp;:&nbsp;
					<select id="startMin" name="startMin"  style="width: 80px;">
						<option value="">请选择</option>
					</select>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">活动时长<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select id="continueHours" name="continueHours"  style="width: 80px;">
						<option value="">请选择</option>
					</select>
					小时
					<select id="continueMin" name="continueMin"  style="width: 80px;">
						<option value="">请选择</option>
					</select>
					分钟
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit"  id="Button1" style="float:left;" class="l-button l-button-submit" onclick="commitSave();" value="提交"/>
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
