<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>违规详细页</title>
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
        <span class="modal-title" id="exampleModalLabel">违规详细页</span>
      </div>
	<div class="modal-body">
     	<div>
     		<h3>商家违规信息：</h3>
	 		<table class="panel-table">
	 			<tr>
	 				<td>违规编号</td>
	 				<td>${violateOrder.orderCode}</td>
	 				<td>订单编号</td>
	 				<td>${subOrderCode}</td>
	 				<td>违规时间</td>
	 				<td><fmt:formatDate value="${violateOrder.violateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	 			</tr>
	 			<tr>
	 				<td>处罚金额</td>
	 				<td>${violateOrder.money}元</td>
	 				<td>申诉状态</td>
	 				<td>
	 					<c:if test="${empty violateOrder.againAuditStatus}">
	 						<span style="color: red;">${statusDesc}</span>
	 					</c:if>
	 					<c:if test="${not empty violateOrder.againAuditStatus}">
	 						<c:if test="${violateOrder.againAuditStatus == 0 || violateOrder.againAuditStatus == 1}">
		 						<span style="color: red;">${statusDesc}</span>
	 						</c:if>
	 						<c:if test="${violateOrder.againAuditStatus == 2}">
	 							<c:if test="${violateOrder.status == 6}">
	 								复审失败
	 							</c:if>
	 							<c:if test="${violateOrder.status ne 6}">
		 							<span style="color: red;">${statusDesc}</span>
	 							</c:if>
	 						</c:if>
	 						<c:if test="${violateOrder.againAuditStatus == 3}">
	 							<c:if test="${violateOrder.status == 4 || violateOrder.status == 5}">
	 								线下申诉成功
	 							</c:if>
	 							<c:if test="${violateOrder.status ne 4 && violateOrder.status ne 5}">
	 								<span style="color: red;">${statusDesc}</span>
	 							</c:if>
	 						</c:if>
	 					</c:if>
	 				</td>
	 				<td>申诉截止时间</td>
	 				<td>
	 				<c:if test="${violateOrder.status ne '2'}">
	 				<fmt:formatDate value="${violateOrder.complainEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	 				</c:if>
	 				</td>
	 			</tr>
	 			<tr>
	 				<td>违规行为</td>
	 				<td colspan="5">【${violateTypeDesc}】${violateActionDesc}</td>
	 			</tr>
	 			<tr>
	 				<td>违规详情</td>
	 				<td colspan="5">${violateOrder.content}</td>
	 			</tr>
	 			<tr>
	 				<td>其它处罚方式</td>
	 				<td colspan="5">${violateOrder.punishMeans}</td>
	 			</tr>
	 			<tr>
	 				<td>附件</td>
	 				<td colspan="5"><a href="${pageContext.request.contextPath}/file_servelt${violateOrder.enclosure}">${enclosureName}</a></td>
	 			</tr>
	 			<c:if test="${allowComplain}">
	 			<tr>
	 				<td>操作</td>
	 				<td colspan="5"><a href="javascript:;" onclick="toComplain(${violateOrder.id});">填写申诉资料 </a>（超过申诉截止时间未填写视为放弃申诉）</td>
	 			</tr>
	 			</c:if>
	 		</table>
     	</div>
     	
     	<c:if test="${showComplain}">
     	<c:if test="${not empty violateComplainOrderCustoms}">
     	<div>
     		<h3>商家申诉：</h3>
			<c:forEach var="violateComplainOrderCustom" items="${violateComplainOrderCustoms}">
	 		<table class="panel-table">
	 			<tr>
	 				<td>申诉时间</td>
	 				<td><fmt:formatDate value="${violateComplainOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	 			</tr>
	 			<tr>
	 				<td>联系方式</td>
	 				<td>电话：${violateComplainOrderCustom.phone} 邮箱：${violateComplainOrderCustom.email}</td>
	 			</tr>
	 			<tr>
	 				<td>申诉内容</td>
	 				<td style="word-break: break-all;">${violateComplainOrderCustom.content}</td>
	 			</tr>
	 			<tr>
	 				<td>上传资料</td>
	 				<td colspan="2" class="text-left">
	 					<div id="main-pic-container">
	 					<ul class="docs-pictures clearfix td-pictures" id="main-pictures-list" ondrop="drop(event);" ondragover="allowDrop(event)">
							<c:forEach items="${violateComplainOrderCustom.complainOrderPics}" var="complainOrderPic">
								<img src="${ctx}/file_servelt${complainOrderPic.pic}" style="width: 60px;height: 60px;">
							</c:forEach>
						</ul>	
						</div>
	 				</td>
	 			</tr>
	 			<c:if test="${violateComplainOrderCustom.status ne '0'}">
	 			<tr>
	 				<td>申诉状态</td>
	 				<td>
	 					<c:if test="${violateComplainOrderCustom.status eq '1'}">通过</c:if>
	 					<c:if test="${violateComplainOrderCustom.status eq '2'}">驳回</c:if>
	 					(审核时间：<fmt:formatDate value="${violateComplainOrderCustom.statusDate}" pattern="yyyy-MM-dd"/>)
	 				</td>
	 			</tr>
	 			</c:if>
	 			<c:if test="${violateComplainOrderCustom.status eq '2'}">
	 			<tr>
	 				<td>驳回原因</td>
	 				<td colspan="2" class="text-left">
	 					<div>
	 					<ul class="docs-pictures clearfix td-pictures" name="pictures-list" data-picnum = "${violateComplainOrderCustom.platformPics.size()}" ondrop="drop(event);" ondragover="allowDrop(event)">
		 					${violateComplainOrderCustom.remarks}<br>
		 					<c:forEach items="${violateComplainOrderCustom.platformPics}" var="platformPic">
								<img src="${ctx}/file_servelt${platformPic.pic}" style="width: 60px;height: 60px;">
							</c:forEach>
						</ul>
						</div>
	 				</td>
	 			</tr>
	 			</c:if>
	 		</table>
	 		</c:forEach>
     	</div>
     	</c:if>
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
	<c:if test="${showComplain}">
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
  
  </script>
</body>
</html>
