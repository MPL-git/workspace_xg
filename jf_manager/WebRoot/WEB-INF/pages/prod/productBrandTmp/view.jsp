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
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript">
	
</script>

<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
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
var contextPath = "${pageContext.request.contextPath}";
	$(function() {
		$("#form1").ligerForm();
	});
</script>

<script type="text/javascript">

	$(function() {
		$("#listbox1").ligerListBox({
        isShowCheckBox: false,
        isMultiSelect: true,
        height: 100,
        width:200,
        url:"${pageContext.request.contextPath}/productType/getProductType4ListBox.shtml?productTypeIds="+"${productBrandTmp.productTypeGroup}"
    });
		
	 	var dataGrid = [
	 			        { id: 1, name: '第1类' },{ id: 2, name: '第2类' },{ id: 3, name: '第3类' },{ id: 4, name: '第4类' },{ id: 5, name: '第5类' },
	 			        { id: 6, name: '第6类' },{ id: 7, name: '第7类' },{ id: 8, name: '第8类' },{ id: 9, name: '第9类' },{ id: 10, name: '第10类' },
	 			        { id: 11, name: '第11类' },{ id: 12, name: '第12类' },{ id: 13, name: '第13类' },{ id: 14, name: '第14类' },{ id: 15, name: '第15类' },
	 			        { id: 16, name: '第16类' },{ id: 17, name: '第17类' },{ id: 18, name: '第18类' },{ id:19, name: '第19类' },{ id: 20, name: '第20类' },
	 			        { id: 21, name: '第21类' },{ id: 22, name: '第22类' },{ id: 23, name: '第23类' },{ id: 24, name: '第24类' },{ id: 25, name: '第25类' },
	 			        { id: 26, name: '第26类' },{ id: 27, name: '第27类' },{ id: 28, name: '第28类' },{ id: 29, name: '第29类' },{ id: 30, name: '第30类' },
	 			        { id: 31, name: '第31类' },{ id: 32, name: '第32类' },{ id: 33, name: '第33类' },{ id: 34, name: '第34类' },{ id: 35, name: '第35类' },
	 			        { id: 36, name: '第36类' },{ id: 37, name: '第37类' },{ id: 38, name: '第38类' },{ id: 39, name: '第39类' },{ id: 40, name: '第类' },
	 			        { id: 41, name: '第41类' },{ id: 42, name: '第42类' },{ id: 43, name: '第43类' },{ id: 44, name: '第44类' },{ id: 45, name: '第45类' }
	 		         ]; 

	$("#tmkTypeGroup").ligerCheckBoxList({
			data : dataGrid,
			textField : 'name',
			valueField : 'id',
			rowSize : 6

		});
	
	//设置品类选中
		var tmkTypeGroupIdsStr = "${productBrandTmp.tmkTypeGroup}";
		if (tmkTypeGroupIdsStr != "") {
			var tmkTypeGroupIds = tmkTypeGroupIdsStr.split(",");
			for (var i = 0; i < tmkTypeGroupIds.length; i++) {
				$("input[type=checkbox][name='tmkTypeGroup'][value="+tmkTypeGroupIds[i]+"]").attr("checked",'true');  
			}
			
		};

	});

	
</script>

<html>
<body>
	<form method="post" id="form1" name="form1"
		action="${pageContext.request.contextPath}/productBrandTmp/auditingSave.shtml">
		<input id="id" name="id" type="hidden" value="${productBrandTmp.id}" />
		<table class="gridtable">
			<tr>

				<td class="title">品牌名称</td>
				<td align="left" class="l-table-edit-td">${productBrandTmp.name}</td>
			</tr>
			<tr>
				<td class="title">中文</td>
				<td align="left" class="l-table-edit-td">${productBrandTmp.nameZh}</td>
			</tr>
			<tr>
				<td class="title">英文</td>
				<td align="left" class="l-table-edit-td">${productBrandTmp.nameEn}</td>
			</tr>
			<tr>
				<td class="title">LOGO图片</td>
				<td align="left" class="l-table-edit-td">
				<div><img style="width: 100px;height: 100px;" id="logoPic" alt="" src="${pageContext.request.contextPath}/file_servelt${productBrandTmp.logo}"></div>
				</td>
			</tr>
			<tr>
				<td class="title">适用品类</td>
				<td align="left" class="l-table-edit-td">
					<input type="hidden" id="productTypeGroup" name="productTypeGroup" value="${productBrandTmp.productTypeGroup}">
		           <div style="margin:4px;float:left;">
					        <div id="listbox1" class="l-listbox">
					        </div>  
					    </div>
					   
				</td>
			</tr>
			<tr>
				<td class="title">商标类别</td>
				<td align="left" class="l-table-edit-td">
				<div id="tmkTypeGroup"></div>
				</td>
			</tr>
		  	<tr>
    		<td class="title">商标证图片</td>
    		<td colspan="3">
    			<t:imageList name="pictures" list="${brandTmkPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
    		</td>
    	</tr>
			<tr>
				<td class="title">商标类型</td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" value="1" name="tmkType" <c:if test="${productBrandTmp.tmkType==1}">checked="checked"</c:if> >R标已发商标证</span>
					<span><input  type="radio" value="2" name="tmkType" <c:if test="${productBrandTmp.tmkType==2}">checked="checked"</c:if>>TM标受理中</span>
					
				</td>
			</tr>
			<tr>
				<td class="title">审核状态</td>
				<td align="left" class="l-table-edit-td">
					<span class="radioClass"><input type="radio" value="2" name="status" <c:if test="${productBrandTmp.status==2}">checked="checked"</c:if> >通过</span>
					<span class="radioClass"><input  type="radio" value="1" name="status" <c:if test="${productBrandTmp.status==1}">checked="checked"</c:if>>待定</span>
					<span><input  type="radio" value="3" name="status" <c:if test="${productBrandTmp.status==3}">checked="checked"</c:if>>驳回</span>
					
				</td>
			</tr>
			<tr>
				<td class="title">驳回原因</td>
				<td align="left" class="l-table-edit-td">${productBrandTmp.auditRemarks}
				</td>
			</tr>
			<tr>
				<td class="title">品牌目录</td>
				<td align="left" class="l-table-edit-td">
				${productBrandTmp.catalog}
				</td>
				
			</tr>

		</table>
	</form>
</body>
</html>
