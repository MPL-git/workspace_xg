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

<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin-right: 20px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
</style>
<script type="text/javascript">
var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
function closeDialog(){
	dialog.close();//关闭dialog 
}

$(function ()  {
 	$(".radioItem").change( function() { 			
		var txnType = $("input[name='txnType']:checked").val();
	   	$("#typeSub").empty();
	   	getTypeSub(txnType); 
 	});
});

function submit_fun(){
	$("#labelRemarks").text("");
 	$("#labelTxnType").text(""); 
	$("#labelTxnAmt").text("");
    
	if (($("#remarks").val())=='')
	{
		$("#labelRemarks").text("请填写摘要");
		return;
	}
	
    var val1=$('input:radio[name="txnType"]:checked').val();
    if(val1==null){
    	$("#labelTxnType").text("请填选择类型");
        return;
    }
    
    var val2=$("#typeSub").val();
    if(val2==''){
    	$("#labelTypeSub").text("请填选择子类");
        return;
    }
	
    var txnAmt=$("#txnAmt").val();
	if (txnAmt==''|| txnAmt==0)
	{
		$("#labelTxnAmt").text("变化金额不能为0或空");
		return;
	}
	
	if (isNaN(txnAmt))
	{
		$("#labelTxnAmt").text("请填写正确的金额格式");
		return;
	}
	//现金缴纳B、货款抵缴C、申诉成功F只能填正数	
	if ((val1=='B'||val1=='C'||val1=='F') && txnAmt<=0 ){
		alert("对不起，金额只能大于0");
		return;
	}
	//退保证金D、违规扣款E只能填负数
	if ((val1=='D'||val1=='E'||val1=='G') && txnAmt>=0 ){
		alert("对不起，金额只能小于0");
		return;
	}
	
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/addMchtDepositDtlSubmit.shtml",
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

function getTypeSub(txnType)
{
	if(typeof(txnType)!="undefined"){
		var path = '${pageContext.request.contextPath}/mcht/getMchtDepositDtlTypeSubList.shtml';
		jQuery.ajax( {
	  		url : path,
	  		type : 'POST',
	  		dataType : 'json',
	  		cache : false,
	  		async : false,
	  		data : { txnType : txnType},
	  		timeout : 30000,
	  		success : function(json) 
	  		{  
	  			var sel = $("#typeSub");
				sel.empty();
				sel.append("<option value=\"" + "\">  请选择 </option>");			
				$.each(json, function(index, item) {
					sel.append("<option value=\"" + item.statusValue + "\">" + item.statusDesc + "</option>");
				});
	  		},
			error : function() 
			{
				$.ligerDialog.warn('操作超时，请稍后再试！');
			}
	  	});
	}
	
 
}
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" enctype="multipart/form-data">
	<input type="hidden" name="depositId" value="${depositId}">
		<table class="gridtable">
		    <tr>
               <td class="title" style="text-align: center; width=20%;" >&nbsp;</td>  
               <td class="title" style="text-align: center; width=20%;" >&nbsp;</td>
			</tr>
			
			<tr>
				<td style="text-align: center;">摘要</td>
				<td align="left" class="l-table-edit-td">
					<input id="remarks" name="remarks" type="text" value="" style="float:left;width:50%;" />
					<label id="labelRemarks" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
				</td>
			</tr>
			
			<tr>
				<td style="text-align: center;">类型</td>
				<td align="left" class="l-table-edit-td">
				<c:forEach var="txnTypeItem" items="${mchtDepositDtlTxnType }">
					<span class="radioClass"><input class="radioItem" type="radio" value="${txnTypeItem.statusValue }" name="txnType" >${txnTypeItem.statusDesc}</span>
				</c:forEach>
				<label id="labelTxnType" style="margin:0 0 0 10px;color: #FF0000"></label></td>
				</td>
			</tr>
			
			<tr>
				<td style="text-align: center;">子类</td>
				<td align="left" class="l-table-edit-td">
					<select id="typeSub" name="typeSub">
					<option value="">请选择</option>
					</select>
					<label id="labelTypeSub" style="margin:0 0 0 10px;color: #FF0000"></label></td>
				</td>
			</tr>
			
			<tr>
				<td style="text-align: center;">变化金额</td> 
				<td align="left" class="l-table-edit-td">
					<input id="txnAmt" name="txnAmt" type="text" value="" style="float:left;" />
					<label id="labelTxnAmt" style="float:left;margin:0 0 0 10px;color: #FF0000"></label>
				</td>
			</tr>

	        <tr>
				<td style="text-align: center;">操作</td>
				<td align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="submit_fun();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	        </tr>
		</table>
	</form>
</body>
</html>
