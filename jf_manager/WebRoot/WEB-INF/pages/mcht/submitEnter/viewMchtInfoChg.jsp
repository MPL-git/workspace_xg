<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
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
	
		<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	
	
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>

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

.td-pictures li{
display: inline-block;
}
td img{
width: 60px;
height: 40px;
}
.infoChange{
	color: red;
}
</style>
<script type="text/javascript">

var corporationNewViewer;
var blPicNewViewer;
var occPicNewViewer;
var atqPicNewViewer;
var boaalPicNewViewer;
$(function(){
	corporationNewViewer = new Viewer(document.getElementById('corporation-new'), {});
	blPicNewViewer = new Viewer(document.getElementById('blPic-new'), {});
	occPicNewViewer = new Viewer(document.getElementById('occPic-new'), {});
	atqPicNewViewer = new Viewer(document.getElementById('atqPic-new'), {});
	boaalPicNewViewer = new Viewer(document.getElementById('boaalPic-new'), {});
	
});



$(function ()  {
	$.metadata.setType("attr", "validate");
    //驳回原因必填
    $.validator.addMethod("auditRemarksRequired", function(value, element) {  
    	if($("input[type=radio][name='status'][value=4]").attr("checked")&&$.trim($("#auditRemarks").val())==""){
 			return false;
 		}else{
 			return true;
 		}
    }, "请注明驳回原因");
	            var v = $("#form1").validate({
	            	
	                errorPlacement: function (lable, element)
	                {   console.log(lable[0].innerText);
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
	                	form.submit();
// 	                	auditSubmit();
	                }
	            });
	            
	  });    

function auditSubmit(){
	
	$.ajax({
		url : "${pageContext.request.contextPath}/mcht/mchtInfoChgApplyAuditSave.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : $("#form1").serialize(),
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
			      $.ligerDialog.alert(message, '提示', 'success', function() {
			    	  parent.parent.location.reload();
					  parent.frameElement.dialog.close();
				 });
			}else{
		        $.ligerDialog.alert(message, '提示', 'error',function() {
		        	parent.parent.location.reload();
					  parent.frameElement.dialog.close();
					 });
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
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/mcht/mchtInfoChgApplyAuditSave.shtml">
	<input type="hidden" value="${mchtInfoChg.id }" name="id">
		<table class="gridtable">
		
			<tr>
				<td  colspan="1" class="title"></td>
				<td  colspan="1" class="title">新内容</td>
			</tr>
		
			<tr>
			<td  colspan="1" class="title">公司名称</td>
			<td  colspan="1" align="left" class="l-table-edit-td">
				<span>${mchtInfoChg.companyName}</span>	
			</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">公司类型</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfoChg.companyType}</span>	
				</td>
			</tr>
			<tr>
			
				<td  colspan="1" class="title">营业执照注册号</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfoChg.uscc}</span>	
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">注册资本</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfoChg.regCapital}（${mchtInfoChg.regFeeType}）</span>	
				</td>
			</tr>
				
			<tr>
				<td  colspan="1" class="title">公司住所</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfoChg.companyAddress}</span>	
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">成立日期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span><fmt:formatDate value="${mchtInfoChg.foundedDate }" pattern="yyyy-MM-dd"/></span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">工商年检有效期</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span><fmt:formatDate value="${mchtInfoChg.yearlyInspectionDate }" pattern="yyyy-MM-dd"/></span>	
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">法人姓名</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfoChg.corporationName }</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">法人身份证号</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfoChg.corporationIdcardNo }</span>	
				</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">法人身份证</td>
			<td  colspan="1" align="left" class="l-table-edit-td">
				<ul class="docs-pictures clearfix td-pictures" id="corporation-new">
					<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.corporationIdcardImg1}" alt=""></li>
			 		<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.corporationIdcardImg2}" alt=""></li>
			 	</ul>
			</td>	
			</tr>
			<tr>
				<td colspan="1" class="title">法人身份证有效期</td>
				<td colspan="1" align="left" class="l-table-edit-td">
					<fmt:formatDate value="${mchtInfoChg.corporationIdcardDate}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td  colspan="1" class="title">法人手机</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfoChg.corporationMobile }</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">公司总机</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>${mchtInfoChg.companyTel }</span>	
				</td>
			</tr>
			
			<tr>
				<td  colspan="1" class="title">公司通讯地址</td>
				<td  colspan="1" align="left" class="l-table-edit-td">
					<span>
						${mchtInfoChg.contactProvinceName}${mchtInfoChg.contactCityName}${mchtInfoChg.contactCountyName}${mchtInfoChg.contactAddress}
					</span>
				</td>
			</tr>
			
			<tr>
			<td  colspan="1" class="title">营业执照副本</td>
			<td  colspan="1" align="left" class="l-table-edit-td">
				<ul class="docs-pictures clearfix td-pictures" id="blPic-new">
						<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.blPic}" alt=""></li>
				</ul>
			</td>	
			</tr>
			<tr>
					<td colspan="1" class="title">主要经营品牌类型</td>
					<td colspan="1" align="left" class="l-table-edit-td">
                    <select style="width:210px;" name="brandType" id="brandType" validate="{required:true}" disabled>
                    <option value="">请选择</option>
                    <option value="0" <c:if test="${mchtInfoChg.brandType eq 0}">  selected = "selected" </c:if>>品牌官方</option>
                    <option value="1" <c:if test="${mchtInfoChg.brandType eq 1}">  selected = "selected" </c:if>>品牌总代</option>
                    <option value="2" <c:if test="${mchtInfoChg.brandType eq 2}">  selected = "selected" </c:if>>品牌代理商</option>
                    </select>
                    <br><br>
                    <textarea class="form-control" disabled rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfoChg.brandTypeDesc}</textarea>
					</td>
			</tr>
			<tr>
			<td  colspan="1" class="title">组织机构代码证</td>
			<td  colspan="1" align="left" class="l-table-edit-td">
				<ul class="docs-pictures clearfix td-pictures" id="occPic-new">
					<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.occPic}" alt=""></li>
				</ul>
			</td>	
			</tr>
			<tr>
			<td  colspan="1" class="title">一般纳税人资格证</td>
			<td  colspan="1" align="left" class="l-table-edit-td">
				<ul class="docs-pictures clearfix td-pictures" id="atqPic-new">
					<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.atqPic}" alt=""></li>
				</ul>
			</td>	
			</tr>
			<tr>
			<td  colspan="1" class="title">银行开户许可证</td>
			<td  colspan="1" align="left" class="l-table-edit-td">
				<ul class="docs-pictures clearfix td-pictures" id="boaalPic-new">
					<li><img  src="${pageContext.request.contextPath}/file_servelt${mchtInfoChg.boaalPic}" alt=""></li>
				</ul>
			</td>	
			</tr>
		</table>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
</html>
