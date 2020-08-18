<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.radioClass{margin-right: 20px;}
td img{
width: 60px;
height: 40px;
}
</style>
<script type="text/javascript">
var dataFromValidate;
$(function(){
	$.metadata.setType("attr", "validate");
	//驳回原因必填
	$.validator.addMethod("auditRemarksRequired", function(value, element) {  
		if($("input[type=radio][name='status'][value=3]").attr("checked")&&$.trim($("#auditRemarks").val())==""){
				return false;
			}else{
				return true;
			}
	}, "请注明驳回原因");
	
	
	
	dataFromValidate = $("#form1").validate({
    	
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
        	}else if('hidden'==elementType){
        		$(element).closest('td').find("div").ligerTip({
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
        }
    });   
	
	
	
})



function submit_fun(){
	
	if(dataFromValidate.form()){
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/mchtBankAccountAuditSave.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : $("#form1").serialize(),
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					parent.location.reload();
					frameElement.dialog.close();
				}else{
					$.ligerDialog.error(data.returnMsg);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
	

}
var viewerPictures;
$(document).ready(function() {
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});


function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();
	
}


</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" name="id" value="${mchtBankAccountHis.id}">
		<table class="gridtable">
			<tr>
               <td class="title">项目</td>
               <td class="title" colspan="3" >内容</td>
			</tr> 
	        <tr>
	           	<td style="text-align: center;">商家公司名称</td> 
	           	<td colspan="3">${companyName}</td>
	        </tr>
	        <tr>
	           	<td style="text-align: center;">账户类型</td>
	           	<td colspan="3">
	           		<c:if test="${mchtBankAccountHis.accType == 1}">
	           			对私账户
	           		</c:if>
	           		<c:if test="${mchtBankAccountHis.accType == 2}">
	           			对公账户
	           		</c:if>
	           	</td>
	        </tr>
 	        <tr>
	           	<td style="text-align: center;">账户名称</td>
	            <td colspan="3">${mchtBankAccountHis.accName}</td>
	        </tr>
	        <tr>
	           	<td style="text-align: center;">开户银行</td>
	            <td colspan="3">${bankName}</td>
	        </tr>
	        <tr>
	           	<td style="text-align: center;">账户账号</td>
	            <td colspan="3">${mchtBankAccountHis.accNumber}</td>
	        </tr>
	        <tr>
	           	<td style="text-align: center;">开户支行名称</td>
	            <td colspan="3">${mchtBankAccountHis.depositBank}</td>
	        </tr>
	        <tr>
	           	<td style="text-align: center;">确认函</td>
	            <td colspan="3">
	            <img onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtBankAccountHis.confirmFile}" alt="" ></li>
	            </td>
	        </tr>
		</table>
	</form>
		<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
</html>
