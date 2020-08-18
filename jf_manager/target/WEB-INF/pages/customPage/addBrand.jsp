<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<html>
<body>
<form method="post" id="form" name="form" action="">
	<input type="hidden" id="decorateModuleId" name="decorateModuleId" value="${decorateModuleId}">
	<table class="gridtable">
           	<tr>
               <td class="title">一级分类</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="productType1Ids" name="productType1Ids" disabled="disabled">
               		<c:if test="${empty productType1}">
	               		<option value="">请选择</option>
               		</c:if>
               		<c:if test="${not empty productType1}">
		               	<option value="${productType1.id}" selected="selected">${productType1.name}</option>
               		</c:if>
               	</select>
               </td>
			</tr>
			<tr>
               <td class="title">二级分类</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="productType2Ids" name="productType2Ids" multiple="multiple" style="width: 200px;height: 200px;" disabled="disabled">
               		<c:if test="${empty productTypes}">
	               		<option value="">请选择</option>
               		</c:if>
               		<c:if test="${not empty productTypes}">
	               		<c:forEach items="${productTypes}" var="productType">
	               			<option value="${productType.id}" selected="selected">${productType.name}</option>
	               		</c:forEach>
               		</c:if>
               	</select>
               	<br>
               	<br>
               	<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="搜索品牌" onclick="searchBrand();">
               </td>
			</tr>
			<tr>
               <td class="title">品牌</td>
               <td colspan="2" align="left" class="l-table-edit-td" id="brandList">
               		
               </td>
			</tr>
	 </table>
</form>
<script type="text/javascript">
$(function(){
	$("a[name='add']").live('click',function(){
		var $this = $(this);
		var text = $this.text();
		if(text == "已添加"){
			return false;
		}
		$this.text("已添加");
	});
});
	function searchBrand(){
		var decorateModuleId = $("#decorateModuleId").val();
		var productType1Ids = $("#productType1Ids").val();
		var productType2Ids = "";
		$("#productType2Ids").find("option:selected").each(function(index){
			if(index!=$("#productType2Ids").find("option:selected").length-1){
				productType2Ids+=$(this).val()+",";
			}else{
				productType2Ids+=$(this).val();
			}
		});
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/searchBrand.shtml?decorateModuleId="+decorateModuleId+"&productType1Ids="+productType1Ids+"&productType2Ids="+productType2Ids,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					var brandList = data.brandList;
					var html=[];
					for(var i=0;i<brandList.length;i++){
						var id = brandList[i].id;
						var name = brandList[i].name;
						html.push('<span>'+name+'</span>&nbsp;<a href="javascript:;" name="add" brandId="'+id+'" brandName="'+name+'">添加</a>&nbsp;&nbsp;&nbsp;');
					}
					$("#brandList").html(html.join());
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
</body>
</html>
