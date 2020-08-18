<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>查看商品页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}
.glyphicon-remove{
color:red;
margin-left: 3px;
cursor:pointer;
}
.docs-pictures{
padding: 0px;
}
.docs-pictures li{
position: relative;
margin: 3px;
}


.pic-btn-container{
	width:100%;
	position: absolute;
    top: 0px;
    background:rgba(0, 0, 0, 0.5);
    height:0px;
    z-index: 300;
    overflow: hidden;
    text-align: right;
    padding-right: 3px;
}

</style>
</head>

<body>
<!--查看品牌 -->
<div class="modal-dialog wg-xx" role="document" style="width:1000px;">
<div class="modal-content">
    <input type="hidden" id="violateOrderId" value="${violateOrder.id}">
    <input type="hidden" id="complainEndDate" value="<fmt:formatDate value="${violateOrder.complainEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>">
    <input type="hidden" id="violateComplainOrderId" value="${violateComplainOrderId}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">查看商品</span>
      </div>
	<div class="modal-body">
     	<div>
	 		<table class="panel-table">
	 			<tr>
	 				<td>退货地址*</td>
	 				<td>【${cloudProductAfterTempletCustom.name}】地址：${cloudProductAfterTempletCustom.provinceName}${cloudProductAfterTempletCustom.cityName}${cloudProductAfterTempletCustom.countyName}${cloudProductAfterTempletCustom.address}，收货人：${cloudProductAfterTempletCustom.recipient}，电话：${cloudProductAfterTempletCustom.phone}</td>
	 			</tr>
	 			<tr>
	 				<td>品牌*</td>
	 				<td>${cloudProductCustom.brandName}</td>
	 			</tr>
	 			<tr>
	 				<td>货号*</td>
	 				<td colspan="5">${cloudProductCustom.artNo}</td>
	 			</tr>
	 			<tr>
	 				<td>SKU图</td>
	 				<td colspan="5">
	 					<div class="width-60"><img src="${pageContext.request.contextPath}/file_servelt${cloudProductCustom.skuPic}"></div>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>关联商品</td>
	 				<td colspan="5">
	 					<c:forEach items="${relatedProductList}" var="relatedProduct">
	 						商品ID：${relatedProduct.code}&nbsp;&nbsp;名称：${relatedProduct.name}<a href="javascript:;" onclick="editProduct(${relatedProduct.id});">【编辑】</a><br>
	 					</c:forEach>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>经销价</td>
	 				<td colspan="5">${cloudProductCustom.sellingPrice}</td>
	 			</tr>
	 		</table>
     	</div>
     	
     	<c:if test="${not empty cloudProductItemCustoms}">
     	<div>
     		<div class="row">
     		<div class="col-md-12 datatable-container">
	 		<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
	 			<thead>
	 				<tr>
		 				<th style="height: 30px;">尺码</th>
		 				<th>库存</th>
		 				<th>云宝ID</th>
		 				<th>绑定数</th>
	 				</tr>	
	 			</thead>
	 			<tbody>
					<c:forEach var="cloudProductItemCustom" items="${cloudProductItemCustoms}">
		 			<tr>
		 				<td>${cloudProductItemCustom.propValue}</td>
		 				<td>${cloudProductItemCustom.stock}</td>
		 				<td>${cloudProductItemCustom.id}</td>
		 				<td>${cloudProductItemCustom.bindCount}</td>
		 			</tr>
			 		</c:forEach>
	 			</tbody>
	 		</table>
	 		</div>
	 		</div>
     	</div>
     	</c:if>
	</div>
</div>
</div>
<div class="modal fade" id="toComplainModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
  <script type="text/javascript">
  var picturesViewer;
  $(function(){
   	<c:if test="${not empty violateComplainOrderCustoms}">
   		picturesViewer = new Viewer(document.getElementById('main-pictures-list'), {
			shown: function () {
				var width=document.documentElement.clientWidth;
				var height=document.documentElement.clientHeight;
				$(".viewer-container").height(height);
				$(".viewer-container").width(width-20);
				$(".viewer-container").css("left",-width/4+38);
			}
		});
   	</c:if>
   	
   	$("ul[name='pictures-list']").each(function(){
   		var picnum = $(this).data("picnum");
   		if(picnum>0){
   			new Viewer(this, {
 				shown: function () {
 					var width=document.documentElement.clientWidth;
 					var height=document.documentElement.clientHeight;
 					$(".viewer-container").height(height);
 					$(".viewer-container").width(width-20);
 					$(".viewer-container").css("top",-$('.modal-dialog').offset().top);
 					$(".viewer-container").css("left",-$('.modal-dialog').offset().left);
 				}
 			});
   		}
   	});
   	
  });
  function toComplain(id){
		$.ajax({
			url: "${ctx}/violateOrder/toComplain?violateOrderId="+id, 
			type: "GET", 
			success: function(data){
				$("#toComplainModal").html(data);
				$("#toComplainModal").modal();
			},
			error:function(){
				swal('页面不存在');
			}
		});

	}
  
function editProduct(id){
	$(".modal-backdrop").hide();
	getContent("${ctx}/product/productEdit?id="+id+"&from=cloudProduct");
	$("body").parent().css("overflow-y","auto");
}
  </script>
</body>
</html>
