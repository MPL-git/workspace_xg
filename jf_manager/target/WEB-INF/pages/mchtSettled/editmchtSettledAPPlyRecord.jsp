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
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript"> 

$(function(){

		$("#form1").validate({//表单验证
	        submitHandler: function(form) {
	            var companyName=$("#companyName").val();
	        	var productTypeMain =  $("#productTypeMain").val();
	        	var productBrandMain =  $("#productBrandMain").val();
	        	var tmallShop=$("#tmallShop").val();
	        	var taobaoShop=$("#taobaoShop").val();
	        	var vipsShop=$("#vipsShop").val();
	        	var otherShop=$("#otherShop").val();
	        	var contactName=$("#contactName").val();
	        	var phone=$("#phone").val();
	        	var qq=$("#qq").val();
	        	var wechat=$("#wechat").val();
	        	var email=$("#email").val();
	        	if(companyName == '') {
	        		commUtil.alertError("公司名称不能为空！");
	   		    	return;
	        	}
	        	if(productTypeMain == '') {
	        		commUtil.alertError("品类不能为空！");
	   		    	return;
	        	}
	        	if(productBrandMain == '') {
	        		commUtil.alertError("品牌名称不能为空");
	   		    	return;
	        	}
				if (tmallShop=='' && taobaoShop == '' && vipsShop == '' && otherShop == '' && otherShop == '') {
					commUtil.alertError("该链接最少有一个不能为空!");
					return;
				}
				if(contactName == '') {
	        		commUtil.alertError("联系人不能为空");
	   		    	return;
	        	}
	            if(phone == '' || !/^[1][3,4,5,7,8][0-9]{9}$/.test(phone)) {
	        		commUtil.alertError("手机号码不能为空或手机号码不是11位");
	   		    	return;
	        	}
	        	if(qq == '') {
	        		commUtil.alertError("QQ号不能为空");
	   		    	return;
	        	}
	        	if(wechat == '') {
	        		commUtil.alertError("微信号不能为空");
	   		    	return;
	        	}
	        	if(email == '' || !/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email)) {
	        		commUtil.alertError("邮箱不能为空或邮箱格式不正确");
	   		    	return;
	        	}	   
	        	    
	        	form.submit();
	        }
	    }); 
		
	});
</script>

<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/mchtSettled/addmchtSettledAPPlyRecord.shtml">
		<input id="id" name="id" type="hidden" value=""/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">公司名称 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="companyName" name="companyName" value="" type="text" style="width:400px;"/>
				</td>
			</tr>				
			
			<tr>
				<td colspan="1" class="title">主营品类 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="productTypeMain" name="productTypeMain" value="" type="text" style="width:400px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">品牌名称<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea id="productBrandMain" name="productBrandMain" rows="3" cols="53" class="text"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">在做渠道店铺链接<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">天猫</span><input id="tmallShop" name="tmallShop" type="text" style="width:500px;"/></div>
					<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">淘宝</span><input id="taobaoShop" name="taobaoShop" type="text" style="width:500px;"/></div>
					<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">京东</span><input id="jdShop" name="jdShop" type="text" style="width:500px;"/></div>
					<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">唯品会</span><input id="vipsShop" name="vipsShop" type="text" style="width:500px;"/></div>
					<div style="margin-bottom:10px;"><span style="width:50px;float:left;text-align:center;">其他</span><input id="otherShop" name="otherShop" type="text" style="width:500px;"/></div>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">联系人 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="contactName" name="contactName" value="" type="text" style="width:400px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">联系人职务</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="contactJob" name="contactJob" value="" type="text" style="width:400px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">手机<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="phone" name="phone" value="" type="text" style="width:400px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">QQ<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="qq" name="qq" type="text" value="" style="width:400px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">微信<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="wechat" name="wechat" value="" type="text" style="width:400px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">公司座机</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="companyTel" name="companyTel" value="" type="text" style="width:400px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">企业邮箱<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="email" name="email" value="" type="text" style="width:400px;"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
					  <c:if test="${isZs}">
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/>
					 </c:if>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
