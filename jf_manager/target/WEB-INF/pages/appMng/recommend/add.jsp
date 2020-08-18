<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
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
var actName="会场";
$(function ()  {
	$("input[name='linkType']").eq(0).attr("checked",'true');
 	$(".radioItem").change( function() { 
		var linkType = $("input[name='linkType']:checked").val();
		if (linkType=='1'){
			actName="会场";
			$("#hasAddText").text("当前已添加的会场ID：");
			$("#addText").text("会场");
		}else{
			actName="特卖";
			$("#hasAddText").text("当前已添加的特卖ID：");
			$("#addText").text("特卖");
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
			var autoUpDate = document.getElementById("autoUpDate");
			var autoDownDate = document.getElementById("autoDownDate");
			$("#linkUrlGroup").ligerHideTip();
			var array = [];
			$("#hasAddId >span").each(function(){
				array.push($(this).text());
			});
			var linkIdStr=array.join(",");
			if (linkIdStr==""){
				$("#linkUrlGroup").ligerTip({ content: "请添加"+actName+"ID。"});
				linkUrlGroup.focus();
				return;
			}else{
				$("#linkIdStr").val(linkIdStr);
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

function addAct(){
	var linkUrlGroup = document.getElementById("linkUrlGroup").value;
	var hasAddId = document.getElementById("hasAddId");
	var type=$('input:radio[name="linkType"]:checked').val();
	linkUrlGroup=linkUrlGroup.replace(/\s+/g," ");
	var temp = linkUrlGroup.split(/[\r\n\t\s,]/g);
	var array = [];
	$("#hasAddId >span").each(function(){
		array.push($(this).text());
	});
	var arrayTmp = [];
	var newStr=null;
	var temp2=null;
	for(var i =0; i<temp.length; i++){
		var number= /^[1-9]+[0-9]*]*$/;
		if(number.test(temp[i]) && array.indexOf(temp[i])==-1){
			arrayTmp.push(temp[i]);
		}
    }
	if (arrayTmp.length!=0){
		var arrayIdStr=arrayTmp.join(",");
		$.ajax({
			type:'POST',
			url : "${pageContext.request.contextPath}/appMng/recommend/filterId.shtml",
			data: {type:type, arrayIdStr:arrayIdStr, catalog:$("#catalog").val()},
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
				if ("0000" == data.returnCode) {
					newStr=data.returnMsg;
				}	
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	
	if (newStr!=null&&newStr!=""){
		var temp2=newStr.split(",");
		for(var i =0; i<temp2.length; i++){
			var span = document.createElement("span");
			span.innerHTML = temp2[i]+'<img src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteAct(this);"/>';
			hasAddId.appendChild(span);
	    }
	}
};

function deleteAct(obj){
	$(obj).parent().remove();
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/appMng/recommend/saveSubmit.shtml">
		<input type="hidden" id="type" name="type" value="2"/>
		<input type="hidden" id="catalog" name="catalog" value="${catalog}"/>
		<input type="hidden" id="position" name="position" value="9"/>
		<input type="hidden" id="linkIdStr" name="linkIdStr" value=""/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title" style="min-width:100px;">推荐类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<span class="radioClass"><input class="radioItem" type="radio" value="1" name="linkType">会场</span>
					<span class="radioClass"><input class="radioItem" type="radio" value="2" name="linkType">特卖</span>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">添加<span id="addText">会场</span><span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea rows="7" cols="40" id="linkUrlGroup" name="linkUrlGroup" class="text" validate="{maxlength:500}"></textarea><br>
					<input type="button" value="添加" class="l-button toAddAct" onclick="addAct();"/>
					<span id="hasAddText">当前已添加的会场ID：</span>
					<div id="hasAddId"></div>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">自动上架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="autoUpDate" name="autoUpDate" validate="{required:true}"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">自动下架<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" id="autoDownDate" name="autoDownDate" validate="{required:true}"/>
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
