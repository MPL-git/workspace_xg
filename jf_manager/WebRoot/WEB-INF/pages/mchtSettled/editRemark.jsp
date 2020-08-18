<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
.upload_image_list li a{display:none !important}
</style>
<script type="text/javascript">

var viewerPictures;

$(function (){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {});
});

</script>
<html>
<body>
	<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/mchtSettled/editRemarkSave.shtml">
		<input id="id" name="id" type="hidden" value="${mchtSettledApplyCustom.id }"/>
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">获取入驻信息来源 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<c:if test="${mchtSettledApplyCustom.sourceType!=9}">${mchtSettledApplyCustom.sourceTypeDesc }</c:if>
					<c:if test="${mchtSettledApplyCustom.sourceType==9}">${mchtSettledApplyCustom.sourceRemark }</c:if>
				</td>
			</tr>
		
			<tr>
				<td colspan="1" class="title">公司名称 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">${mchtSettledApplyCustom.companyName }</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">公司证件 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
<!-- 	    			<t:imageList name="pictures" list="${mchtSettledApplyPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" /> -->
	    		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures">
	    		<c:forEach items="${mchtSettledApplyPics}" var="pic">
	    		<li>
					<img src="${pageContext.request.contextPath}/file_servelt${pic.PICTURE_PATH}" width='80' height='80'>
					</li>
				</c:forEach>
				</ul>
	    		</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">注册资本<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<fmt:formatNumber value="${mchtSettledApplyCustom.regCapital }" pattern="#.####"/>万${mchtSettledApplyCustom.regFeeTypeDesc }
					</select>
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">法人姓名 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">${mchtSettledApplyCustom.corporationName }</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">通讯地址 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					${mchtSettledApplyCustom.areaName }&nbsp;${mchtSettledApplyCustom.address }
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">联系人 <span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">${mchtSettledApplyCustom.contactName }</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">QQ<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">${mchtSettledApplyCustom.qq }</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">联系人手机<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">${mchtSettledApplyCustom.phone }</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">联系人邮箱<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">${mchtSettledApplyCustom.email }</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">其它店地址</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div style="margin-bottom:10px;"><span style="width:60px;float:left;text-align:center;">天猫：</span>${mchtSettledApplyCustom.tmallShop }<br></div>
					<div style="margin-bottom:10px;"><span style="width:60px;float:left;text-align:center;">淘宝：</span>${mchtSettledApplyCustom.taobaoShop }<br></div>
					<div style="margin-bottom:10px;"><span style="width:60px;float:left;text-align:center;">京东：</span>${mchtSettledApplyCustom.jdShop }<br></div>
					<div style="margin-bottom:10px;"><span style="width:60px;float:left;text-align:center;">唯品会：</span>${mchtSettledApplyCustom.vipsShop }<br></div>
					<div style="margin-bottom:10px;"><span style="width:60px;float:left;text-align:center;">其他：</span>${mchtSettledApplyCustom.otherShop }<br></div>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">主营类目</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;border-style:none;" rows="6" cols="60" readonly="readonly">${mchtSettledApplyCustom.productTypeMain}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">运营品牌</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;border-style:none;" rows="6" cols="60" readonly="readonly">${mchtSettledApplyCustom.productBrandMain}</textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">备注</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea id="remarks" name="remarks" rows="6" cols="60" class="text" >${mchtSettledApplyCustom.remarks}</textarea>
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
