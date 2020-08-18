<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	
</script>

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

td input,td select{
border:1px solid #AECAF0;
}
.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}
</style>
<script type="text/javascript">
$(function ()  {
	
	Date.prototype.format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} ;
	

	
	var cooperateBeginDate=new Date("${mchtInfo.cooperateBeginDate }");
	var agreementBeginDate=new Date("${mchtInfo.agreementBeginDate }");
	var agreementEndDate=new Date("${mchtInfo.agreementEndDate }");
	$("#cooperateBeginDate").ligerDateEditor( {
		showTime : false,
		width:158,
		initValue: cooperateBeginDate.format("yyyy-MM-dd")
	});
	$("#agreementBeginDate").ligerDateEditor( {
		showTime : false,
		width:158,
		initValue: agreementBeginDate.format("yyyy-MM-dd")
	});
	$("#agreementEndDate").ligerDateEditor( {
		showTime : false,
		width:158,
		initValue: agreementEndDate.format("yyyy-MM-dd")
	});
	$.metadata.setType("attr", "validate");
	
	            var v = $("#form1").validate({
	            	
	                errorPlacement: function (lable, element)
	                {   
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
	                	mchtBaseInfoChangeSubmit();
	                }
	            });
	            
	            
	            
	  });    

function mchtBaseInfoChangeSubmit(){
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtBaseInfoChangeSubmit.shtml",
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

</script>

<html>
<body>
	<form method="post" id="form1" name="form1">
	<input type="hidden" value="${mchtInfo.id }" name="id">
		<table class="gridtable">
			<tr>
			<td  colspan="1" class="title">商家序号</td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.mchtCode}
				</td>
				<td  colspan="1" class="title">店铺目录<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="shopCatalog" validate="{required:true}"
					name="shopCatalog" type="text" value="${mchtInfo.shopCatalog }"
					style="float:left;" /></td>
				</tr>
				
				
				
				<tr>
				<td  colspan="1" class="title">商家类型 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"> 
				<select style="width: 160px;" id="mchtType" name="mchtType" validate="{selectRequired:true}" disabled="disabled">
					<c:forEach var="statusItem" items="${mchtTypeStatus}">
						<option <c:if test="${mchtInfo.mchtType==statusItem.statusValue}">selected</c:if>
						value="${statusItem.statusValue}">${statusItem.statusDesc}
						</option>
					</c:forEach>
				</select>
				</td>
						<td  colspan="1" class="title">店铺名 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="shopName" validate="{required:true}"
					name="shopName" type="text" value="${mchtInfo.shopName }"
					style="float:left;" /></td>
				
			</tr>
				
				
				
				
				<tr>
				<td  colspan="1" class="title">商家简称 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="shortName" validate="{required:true}"
					name="shortName" type="text" value="${mchtInfo.shortName }"
					/></td>
				
				
				<td  colspan="1" class="title">合作开始日期<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="cooperateBeginDate" name="cooperateBeginDate"  value="" style="float:left;" validate="{required:true}"/>
				</td>
				
			</tr>
			<tr>
			
			<td  colspan="1" class="title">公司名称<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td"><input id="companyName" validate="{required:true}"
					name="companyName" type="text" value="${mchtInfo.companyName }"
					/></td>
			<td  colspan="1" class="title">企业资质公司名<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				${mchtInfo.companyRegName }
				</td>
			</tr>
					<tr>
			<td  colspan="1" class="title">最新合同开始 <span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="agreementBeginDate" name="agreementBeginDate" value=""  style="float:left;" validate="{required:true}"/>
				</td>
					
				<td  colspan="1" class="title">最新合同到期<span class="required">*</span></td>
				<td  colspan="3" align="left" class="l-table-edit-td">
				<input type="text" id="agreementEndDate" name="agreementEndDate" value=""  style="float:left;" validate="{required:true}"/>
				</td>
			</tr>
			<tr>
				<td  class="title">操作</td>
				<td colspan="7" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<c:if test="${STAFF_ID!=1}">
							<input name="btnSubmit" type="submit" id="Button1"
								style="float:left;" class="l-button l-button-submit" value="提交"
								 />
						</c:if>
						<input type="button" value="取消" class="l-button l-button-test"
							style="float:left;" onclick="frameElement.dialog.close();" />

					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
