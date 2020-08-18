<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>动态详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.td-ul {
margin: 0;
padding: 0;
}
.td-ul li{
display: inline-block;
}
.img-td img{
display: inline-block;
width: 60px;
height: 40px;
margin:10px 10px 10px 0;
}
.td-ul li img{
width: 240px;
height: 80px;
}
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}

.video_box {
	z-index: 2222;
	display: block;
}

.black_box {
	background: #000;
	opacity: 0.6;
	left: 0;
	top: 0;
	z-index: 1111;
	position: fixed;
	height: 100%;
	width: 100%;
}
.video_close {
	position: absolute;
	top: -14px;
	right: -12px;
}

.form-group img{
	width: 100px;
	height: 100px;
}
.lineFeed {
  width: 776px;
  word-wrap: break-word;
}
</style>


</head>

<body>

      <div class="x_panel container-fluid py-tm">
        <div class="row content-title">
      <div class="modal-header">
        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" class="goBack">&times;</span></button> -->
       <c:if test="${ShopDynamicCustom != null}">
        <span class="modal-title" id="exampleModalLabel">动态详情</span>
        </c:if>
      </div>
		<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" value="${ShopDynamicCustom.id}" id="shopDynamicId">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
				
				<tr>
						<td class="editfield-label-td">动态顶部封面<span class="required">*</span></td>
						<td colspan="2" class="text-left">					
									<ul class="td-ul" id="idCard-list">
										<li><img
											src="${ctx}/file_servelt${ShopDynamicCustom.topCover}">
										</li>
									</ul>								
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">动态内容<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<c:if test='${ShopDynamicCustom.content!=null && ShopDynamicCustom.content!=""}'>
						    	<div class="lineFeed">${ShopDynamicCustom.content}</div>
						    </c:if>  
						</td>
						
					</tr>
						
					<tr>
						<td class="editfield-label-td">绑定商品</td>
						<td colspan="2" class="text-left">
						<c:if test="${productList!= null}">
							<table id="checkedList" class="checkedList" border="1" width="700px">
								<thead id="tableHead">
									<tr>
									<td>商品信息</td>
									<td>商城价</td>
									<td>活动价</td>
									<td>库存</td>
									<td>状态</td>
									<!-- <td>操作</td> -->
									</tr>
								</thead>
								<tbody id="tableBody">
									
										<c:forEach var="row" items="${productList}">
											<tr>
												<td>
													<div class="is-check">
														<div class="width-60"><img src="${ctx}/file_servelt${row.pic}"></div>
														<div class="width-226">
														<p class="h34">${row.name}</p>
														<div>
														<span style="float: left; margin: 0;">ID：${row.code}
														</span>
														<a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id=${row.id}" target="_blank">预览</a>
														</div>
														</div>
													</div>
												</td>
												<td class=" hiddenCol">${row.mallPriceMax}</td>
												<td>${row.salePriceMin}</td>
												<td>${row.stock}</td>
												<td>
													<c:if test="${row.saleType =='1'}">
														<!-- 品牌款<br> -->
														
														<c:if test="${row.status =='0'}">
														 	下架<br>
														</c:if>
														<c:if test="${row.status =='1'}">
														 	上架<br>
														</c:if>
													</c:if>
													<%-- <c:if test="${row.saleType =='2'}">
														单品款<br>
													</c:if> --%>
												</td>
											</tr>
										</c:forEach>
									
								</tbody>
							</table>
							</c:if>
						</td>
					</tr>	
					<tr>
						<td class="editfield-label-td">审核人<span class="required"></span></td>
						<td colspan="2" class="text-left">
							 <div>${ShopDynamicCustom.staffName}</div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">审核时间<span class="required"></span></td>
						<td colspan="2" class="text-left">
							 ${auditDate}
						</td>
					</tr>
		
					<tr>
						<td class="editfield-label-td">审核结果<span class="required"></span></td>
						<td colspan="2" class="text-left">
							  <c:if test="${ShopDynamicCustom.auditStatus=='0'}">
					          		待审核
					          		</td>
					</tr>
					          </c:if>
					          <c:if test="${ShopDynamicCustom.auditStatus=='1'}">
					          		审核通过
					          		</td>
					</tr>
					          </c:if>
					          <c:if test="${ShopDynamicCustom.auditStatus=='2'}">
					          		审核失败
					          		</td>
					</tr>
					<tr>
						<td class="editfield-label-td">驳回理由<span class="required"></span></td>
						<td colspan="2" class="text-left">
							 <div class="lineFeed">${rejectionReason}</div>
						</td>
					</tr>
					          </c:if>
						
					
				</tbody>
			</table>
		</div>
		</form>
		
		
			</div>
	</div>
  </div>

<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
	<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
  	<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>		
<script type="text/javascript">
var idCardViewer;
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
		idCardViewer = new Viewer(document.getElementById('idCard-list'), {
	});
});

</script>
</body>
</html>
