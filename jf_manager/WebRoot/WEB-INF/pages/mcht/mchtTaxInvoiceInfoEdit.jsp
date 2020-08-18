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
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.radioClass{margin-right: 20px;}
</style>
<script type="text/javascript">
function submit_fun(){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtTaxInvoiceInfoSubmit.shtml",
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

$(document).ready(function() {
	if (${mchtTaxInvoiceInfo.auditStatus} == 4)
	{
		document.getElementById('ARemark').style.display = '';
	}else
	{
		document.getElementById('ARemark').style.display = 'none';
	}
	$(".radioItem").change( 
		function() { 
			var $selectedvalue = $("input[name='auditStatus']:checked").val(); 
			if ($selectedvalue == 4)
			{ 
				document.getElementById('ARemark').style.display = '';
			} 
			else
			{ 
				document.getElementById('ARemark').style.display = 'none';
			}
	});
}); 

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
	<input type="hidden" name="id" value="${mchtTaxInvoiceInfo.id}">
		<table class="gridtable">
		    <tr>
               <td class="title" style="text-align: center;" >纳税类型</td>
               <td colspan="3">
               <select style="width: 200px;" id="taxType" name="taxType">
				<option value="">请选择</option>
					<c:forEach var="typeItem" items="${taxType}">
						<option  <c:if test="${mchtTaxInvoiceInfo.taxType==typeItem.statusValue}">selected</c:if>
						value="${typeItem.statusValue}">${typeItem.statusDesc}
						</option>
					</c:forEach>
				</select>
			   </td>
			</tr> 
	        <tr>
	           	<td class="title" style="text-align: center;">公司名称</td> 
	           	<td colspan="3">
	           		<input type="text" id="companyName" name="companyName"  value="${companyName}" style="width: 300px;" disabled="true"　readOnly="true"/>
	           	</td>
	        </tr>
	        <tr>
	           	<td class="title" style="text-align: center;">纳税人识别号</td> 
	           	<td colspan="3">
					<input type="text" id="taxNumber" name="taxNumber"  value="${mchtTaxInvoiceInfo.taxNumber}" style="width: 300px;" />
				</td>
	        </tr>
	        <tr>
	           	<td class="title" style="text-align: center;">地址</td> 
	           	<td colspan="3">
					<input type="text" id="address" name="address"  value="${mchtTaxInvoiceInfo.address}" style="width: 300px;" />
				</td> 
	        </tr>
	        <tr>
	           	<td class="title" style="text-align: center;">电话</td> 
	           	<td colspan="3">
					<input type="text" id="tel" name="tel"  value="${mchtTaxInvoiceInfo.tel}" style="width: 300px;" />
				</td> 
	        </tr>
	        <tr>
	           	<td class="title" style="text-align: center;">开户行</td> 
	           	<td colspan="3">
					<input type="text" id="depositBank" name="depositBank"  value="${mchtTaxInvoiceInfo.depositBank}" style="width: 300px;" />
				</td> 
	        </tr>
	        <tr>
	           	<td class="title" style="text-align: center;">账号</td> 
	           	<td colspan="3">
					<input type="text" id="accountNumber" name="accountNumber"  value="${mchtTaxInvoiceInfo.accountNumber}" style="width: 300px;" />
				</td> 
	        </tr>
	        <tr>
	           	<td class="title" style="text-align: center;">确认函</td> 
	           	<td colspan="3">
					<img style="width:60px;height:40px;" onclick="viewerPic(this.src)" src="${pageContext.request.contextPath}/file_servelt${mchtTaxInvoiceInfo.confirmFile}">
				</td> 
	        </tr>
	        <tr>
	           	<td class="title" style="text-align: center;">状态</td> 
	           	<td colspan="3">
				<c:forEach var="statusItem" items="${auditStatus }">
					<c:if test="${statusItem.statusValue!=0}">
					<span class="radioClass"><input class="radioItem" type="radio" value="${statusItem.statusValue }" name="auditStatus" <c:if test="${mchtTaxInvoiceInfo.auditStatus==statusItem.statusValue}">checked="checked"</c:if> >${statusItem.statusDesc}</span>
					</c:if>
				</c:forEach>
				</td>
	        </tr>
	        <tr id="ARemark">
	           	<td class="title" style="text-align: center;">驳回原因</td> 
	           	<td colspan="3">
					<textarea rows=3 id="auditRemarks" name="auditRemarks" cols="45" class="text" >${mchtTaxInvoiceInfo.auditRemarks}</textarea>
				</td>
	        </tr>
	        <tr>
				<td class="title" style="text-align: center;">操作</td>
				<td colspan="3" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交" onclick="submit_fun();"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
	        </tr>
		</table>
	</form>
	
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	
</body>
</html>
