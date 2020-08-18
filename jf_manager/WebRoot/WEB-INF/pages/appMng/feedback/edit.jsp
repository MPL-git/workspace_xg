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
$(function (){
    $("#form1").ligerForm();
});
//验证
$(function(){
     var v = $("#form1").validate({
     submitHandler: function (form)
          {   
          	var isValidateOk=true;
      		var dealopinion = document.getElementById("dealopinion");
      		var relatedorder = document.getElementById("relatedorder");
      		var mchtCode=document.getElementById("mchtCode");
      		var mchtcompanyName=document.getElementById("mchtcompanyName");
      		if (dealopinion=="" && relatedorder=="" && mchtCode=="" && mchtcompanyName=="") {
          		if($.trim(dealopinion.value)==""){
          			$("#dealopinion").ligerTip({ content: "该字段不能为空。"});
          			isValidateOk=false;
          		}
          		
          		if($.trim(relatedorder.value)==""){
          			$("#relatedorder").ligerTip({ content: "该字段不能为空。"});
          			isValidateOk=false;
          		}
          		if($.trim(mchtCode.value)==""){
          			$("#mchtCode").ligerTip({ content: "该字段不能为空。"});
          			isValidateOk=false;
          		}
          		if($.trim(mchtcompanyName.value)==""){
          			$("#mchtcompanyName").ligerTip({ content: "该字段不能为空。"});
          			isValidateOk=false;
          		} 
				
			}
      		        	
          	if(isValidateOk){
          		form.submit();
          	}
          }
      });           
});

//不需要处理部分隐藏
function setNoVisibility(){  
    document.getElementById("deal").style.display = "none";  
    document.getElementById("related").style.display = "none";
    document.getElementById("mchtcode").style.display = "none";  
    document.getElementById("mchtcompanyname").style.display = "none"; 
} 
function setVisibility(){  
    document.getElementById("deal").style.display = "";  
    document.getElementById("related").style.display = ""; 
    document.getElementById("mchtcode").style.display = "";  
    document.getElementById("mchtcompanyname").style.display = ""; 
}  


</script>

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/appMng/editsales.shtml"  >
		<input id="id" name="id" type="hidden" value="${memberFeedback.id}" />
		<table class="gridtable">						
			<tr>
				<td class="title">处理跟进*</td>
				<td align="left" class="l-table-edit-td">
				<input type="radio" id="" name="dealstatus" value="0"  onclick="setVisibility()"<c:if test="${memberFeedback.dealstatus=='0'}">checked="checked"</c:if>>待处理
                <input type="radio" id="" name="dealstatus" value="1"  onclick="setVisibility"<c:if test="${memberFeedback.dealstatus=='1'}">checked="checked"</c:if>>已处理
                <input type="radio" id="" name="dealstatus" value="2" onclick="setNoVisibility()"<c:if test="${memberFeedback.dealstatus=='2'}">checked="checked"</c:if>>不需要处理
                </td>
			</tr>
	
			 <tr id="deal">
				<td class="title">处理意见(必填)*</td>
				<td align="left" class="l-table-edit-td">
					<textarea id="dealopinion" name="dealopinion" rows="4" cols="45" class="text" >${memberFeedback.dealopinion}</textarea>
				</td>
			</tr>
			<tr id="related">
				<td class="title">相关订单(必填)*</td>
				<td align="left" class="l-table-edit-td">
					<input id="relatedorder" name="relatedorder" value="${memberFeedback.relatedorder}"  type="text" style="width:200px;"/>
			</tr>
			<tr id="mchtcode">
				<td class="title">商家序号(必填)*</td>
				<td align="left" class="l-table-edit-td">
					<input id="mchtCode" name="mchtCode" value="${memberFeedback.mchtCode}"  type="text" style="width:200px;"/>
			</tr>
			<tr id="mchtcompanyname">
				<td class="title">商家公司名称(必填)*</td>
				<td align="left" class="l-table-edit-td">
					<input id="mchtcompanyName" name="mchtcompanyName" value="${memberFeedback.mchtcompanyName}"  type="text" style="width:200px;"/>
			</tr>
		  
            <tr>
				<td class="title">操作*</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<div><input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button" value="提交" /></div>
					</div>
				</td>
			</tr>           
		</table>
	</form>
</body>
</html>