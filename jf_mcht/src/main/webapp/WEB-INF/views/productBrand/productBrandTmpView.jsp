<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	
<title>查看申请品牌</title>
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}
</style>
<script type="text/javascript">
var pictuerViews;
$(function(){
	pictuerViews= new Viewer($("#brandTmkPic-pictures-list").get(0), {
		shown: function () {
			var width=document.documentElement.clientWidth;
			var height=document.documentElement.clientHeight;
			$(".viewer-container").height(height);
			$(".viewer-container").width(width-20);
			$(".viewer-container").css("top",-$('.modal-dialog').offset().top);
			$(".viewer-container").css("left",-$('.modal-dialog').offset().left);
		  }
		
	});
	
})

</script>

</head>

<body>

  <div class="modal-dialog" role="document" style="width:800px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">查看申请品牌</span>
      </div>
			<div class="modal-body">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">品牌名称</td>
						<td colspan="2" class="text-left">
						<span>${productBrandTmp.name}</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">中文</td>
						<td colspan="2" class="text-left">
						<span>${productBrandTmp.nameZh}</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">英文</td>
						<td colspan="2" class="text-left">
						<span>${productBrandTmp.nameEn}</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">LOGO图片</td>
						<td colspan="2" class="text-left">
						<span>
						 <img style="max-width: 100px;"  src="${ctx}/file_servelt${productBrandTmp.logo}"></div>
						</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">适用品类</td>
						<td colspan="2" class="text-left">
						<span>
						<c:forEach var="productType" varStatus="varstatus" items="${productBrandTmp.productTypes}">
						<c:if test="${varstatus.first }">
						${productType.name}
						</c:if>
						
						<c:if test="${!varstatus.first }">
						 ，${productType.name}
						</c:if>
						</c:forEach>
                        </span>
						</td>
					</tr>
					
					
					<tr>
						<td class="editfield-label-td">商标图片</td>
						<td colspan="2" class="text-left">
								<ul class="docs-pictures clearfix td-pictures pictures-list" id="brandTmkPic-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
								<c:forEach var="brandTmkPicTmp" items="${brandTmkPicTmps}" varStatus="varStatus">
								<li id="brandTmkPicTmp_li_${varStatus.index }" >
								<img  src="${ctx}/file_servelt${brandTmkPicTmp.pic}">
								</li>
								</c:forEach>
								</ul>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">商标类别</td>
						<td colspan="2" class="text-left">
						<span>${productBrandTmp.tmkTypeGroup}</span>
						</td>
					</tr>
					
					
				    <tr>
						<td class="editfield-label-td">状态</td>
						<td colspan="2" class="text-left">
						<span>${productBrandTmp.statusName}</span>
						</td>
					</tr>
					
					<c:if test="${productBrandTmp.status=='3'}">
					
				    <tr>
						<td class="editfield-label-td">驳回理由</td>
						<td colspan="2" class="text-left">
						<span>${productBrandTmp.auditRemarks}</span>
						</td>
					</tr>
					
					</c:if>
					
					
				</tbody>
			</table>
		</div>
		
			</div>
	</div>
  </div>
  <div>
</div>
  
  
  
  
</body>
</html>
