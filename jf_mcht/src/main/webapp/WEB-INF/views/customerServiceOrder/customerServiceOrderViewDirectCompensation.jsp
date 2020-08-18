<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>售后单详情</title>
</head>

<body>
<!--查看售后单详情 -->
  <div class="modal-dialog" role="document" style="width:1000px;">
  <input type="hidden" id="subOrderId" value="${subOrderCustom.id}">
  <input type="hidden" id="customerServiceOrderId" value="${customerServiceOrder.id}">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">售后详情（<c:if test="${customerServiceOrder.serviceType eq 'A'}">退款单</c:if><c:if test="${customerServiceOrder.serviceType eq 'B'}">退货单</c:if><c:if test="${customerServiceOrder.serviceType eq 'C'}">换货单</c:if><c:if test="${customerServiceOrder.serviceType eq 'D'}">直赔单</c:if>）</span>
      </div>
     <div class="modal-body">
				<div class="panel panel-info">
    				<div class="bbbb lh_32"><span style="padding-left: 13px;">售后单编号：${customerServiceOrder.orderCode}</span><span class="pl_10">申请时间：<fmt:formatDate value="${customerServiceOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span><span class="pl_10">申请人联系电话:${customerServiceOrder.contactPhone}</span><span class="pl_10">售后状态：${proStatusDesc}(${serviceTypeDesc}${statusDesc})</span></div>
				    <ul class="list-group">
					        <li class="list-group-item" style="background-color: #faebcc;" id="progress">
					        <c:if test="${customerServiceOrder.serviceType eq 'D' && customerServiceOrder.proStatus eq 'D1'}">
					        	售后进度：商家同意退款>><span style="color: #f55;">等待平台退款</span>>><span style="color: gray;"> 售后完成</span> 
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'D' && customerServiceOrder.proStatus eq 'D2'}">
					        	售后进度：商家同意退款>>平台已退款>><span style="color: #f55;"> 售后完成</span> 
					        </c:if>
					        </li>
					        <li class="list-group-item">直赔原因：${reasonDesc}</li>
					        <li class="list-group-item">直赔说明：${customerServiceOrder.remarks}</li>
					        <li class="list-group-item">
					        	赔付金额:
					        	<span class="ff_yahei fs_12" style="color:red;">${customerServiceOrder.amount}</span>
					        	元
					        </li>
				    </ul>
				</div>
				
				<div class="row">
					<span class="ff_yahei fs_14 color_000000">原订单信息</span>
					<div class="panel panel-info">
    				<div class="bc_eff9ff fs_12 ff_yahei color_333333 lh_32"><span style="padding-left: 10px;">原订单编号：${subOrderCustom.subOrderCode} <a href="javascript:;" onclick="viewSubOrder(${subOrderCustom.id},${subOrderCustom.status})">【查看】</a></span><span class="pl_10">发货时间：<fmt:formatDate value="${subOrderCustom.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span><span class="pl_10">活动ID:${activityIds}</span></div>
				    <ul class="list-group">
					        <li class="list-group-item">
					        	<span>客户原收货人：${subOrderCustom.receiverName}</span><span class="pl_10">电话：${subOrderCustom.receiverPhone}</span><span class="pl_10">地址：${subOrderCustom.receiverAddress}</span><span class="pl_10"><a href="javascript:;" id="showWuliu"> 【查看物流动态】</a></span>
					        </li>
				    </ul>
					</div>
				</div>
				<div class="row">
					<h4>售后记录</h4>
					<div class="col-md-12 datatable-container" style="overflow-y: auto;height: 310px;">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead style="background-color: #bce8f1;">
								<tr>
									<th>操作者</th>
									<th>内容</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${customerServiceLogCustoms}" var="customerServiceLogCustom">
								<tr>
							      <td class="padding-10 text-left">
								      <span class="money">
								      <c:if test="${customerServiceLogCustom.operatorType eq '1'}">
								      	客户
								      </c:if>
								      <c:if test="${customerServiceLogCustom.operatorType eq '2'}">
								      	商家
								      </c:if>
								      <c:if test="${customerServiceLogCustom.operatorType eq '3'}">
								      	系统
								      </c:if>
								      </span>
								      <div class="pt_10">
								      	<fmt:formatDate value="${customerServiceLogCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
								      </div>	
							      </td>
							      <td colspan="10" class="padding-10 text-left">
							      	  <div>${customerServiceLogCustom.content}</div>
							      </td>
    							</tr>
    						</c:forEach>	
							</tbody>
						</table>
					</div>
				</div>
    </div>
  </div>
  <!-- 	订单详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

<!--弹出物流信息Div-->
<div class="video_box" style="position:fixed; width:610px; height:auto; top:30%; left:18%;border-radius: 4px;display: none;" id="wuliuDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default" style="margin-bottom:0px;">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	物流动态
				</h3>
		    </div>
		    <div class="panel-body">
				  <c:if test="${hasWuliu}">
						<c:set var="startIndex" value="${fn:length(wuliuInfos)-1 }"></c:set>  
	                    	<div class="panel-body">
	                    		<c:forEach var="wuliuInfo" items="${wuliuInfos}" varStatus="status">
		                    		<p <c:if test="${status.index == 0}">style="color: green;"</c:if>>  
		                            	${wuliuInfos[startIndex - status.index].AcceptTime}
		                            	<br>
		                            	${wuliuInfos[startIndex - status.index].AcceptStation}
		                            </p>  
	                    		</c:forEach>
	                        </div>  
					</c:if>
					<c:if test="${!hasWuliu}">
						<div class="panel-body">
							<p>暂无物流信息</p>
	                    </div>
					</c:if>
		    </div>
		 </div>
</div>

<!--弹出div End-->
<div class="black_box" style="display: none;" id="directCompensation"></div>  
  
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<!-- Bootstrap -->
<script type="text/javascript">
function viewSubOrder(id,status){
	$.ajax({
        url: "${ctx}/subOrder/subOrderView?id="+id+"&status="+status, 
        type: "GET", 
        success: function(data){
            $("#viewModal").html(data);
            $("#viewModal").modal();
        },
	    error:function(){
	    	swal('页面不存在');
	    }
	});

}
$(function(){
	$("#showWuliu").on('click',function(){
		$("#wuliuDiv").show();
		$("#directCompensation").show();
	});
	
	$(".video_close").on('click',function(){
		$("#wuliuDiv").hide();
		$("#directCompensation").hide();
	});
});
</script>
</body>
</html>
