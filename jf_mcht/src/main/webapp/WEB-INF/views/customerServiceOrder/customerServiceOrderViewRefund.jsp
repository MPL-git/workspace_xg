<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
<style type="text/css">
.video_box {
    background: #fff;
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
<title>售后单详情</title>
</head>

<body>
<!--查看售后单详情 -->
  <div class="modal-dialog sh-xq" role="document" style="width:1000px;">
  <input type="hidden" id="subOrderId" value="${subOrderCustom.id}">
  <input type="hidden" id="customerServiceOrderId" value="${customerServiceOrder.id}">
  <input type="hidden" id="proStatus" value="${customerServiceOrder.proStatus}">
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
					        <c:if test="${customerServiceOrder.serviceType eq 'A' && customerServiceOrder.proStatus eq 'A1'}">
					        	售后进度：客户申请退款 >><span style="color: #f55;">等待商家同意退款 </span>>><span style="color: gray;">平台退款 </span> >><span style="color: gray;"> 售后完成</span> 
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'A' && customerServiceOrder.proStatus eq 'A2'}">
					        	售后进度：客户申请退款 >>商家同意退款>><span style="color: #f55;">等待平台退款</span> >><span style="color: gray;"> 售后完成</span> 
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'A' && customerServiceOrder.proStatus eq 'A4'}">
					        	售后进度：客户申请退款 >>商家同意退款>>平台退款>><span style="color: #f55;"> 售后完成</span> 
					        </c:if>
					        <c:if test="${customerServiceOrder.serviceType eq 'A' && customerServiceOrder.proStatus eq 'A3'}">
					        	售后进度：客户申请退款 >>商家不同意退款>><span style="color: #f55;"> 售后已拒绝</span> 
					        </c:if>
					         
					        </li>
					        <li class="list-group-item">退款原因：${reasonDesc}<c:if test="${not empty customerServiceOrder.depositAmount && customerServiceOrder.depositAmount gt 0}"><span style="color: red;">（预售订单，退款会将定金一起返还，退款原因如果是“商家责任”，需要额外赔偿定金等值的积分给用户，从商家保证金扣除）</span></c:if></li>
					        <li class="list-group-item">退款说明：${customerServiceOrder.remarks}</li>
					        <li class="list-group-item">
					        	退款金额:
					        	<span class="ff_yahei fs_12" style="color: red;">${customerServiceOrder.amount}</span>
					        	元
					        </li>
				    </ul>
				    <c:if test="${customerServiceOrder.status == 0 && customerServiceOrder.serviceType eq 'A' && customerServiceOrder.proStatus eq 'A1'}">
				    <div class="panel-body" style="display: inline-block;">
						<div style="display: inherit;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_3c9eff w_h_130_36" id="agreeRefund">同意退款</button><br>
				       		<span class="preferential">请确保商品未发货，并确认同意买家退款请求。</span>
				        </p>
						</div>
						<span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>				    
				        <div style="float: right;">
				        <p>
				        	<button type="button" class="btn b_r_2 bc_ff5555 w_h_130_36" id="disAgreeRefund">不同意退款</button><br>
				       		<span class="preferential"> 如已与买家沟通，并达成协商一致，商家可主动拒绝售后。</span>
				        </p>
						</div>
				    </div>
				    </c:if>
				</div>
				
				<div class="row">
					<h4>退款商品信息</h4>

					<div class="col-md-12">
						<div class="panel panel-info">
	    				<div class="bc_eff9ff fs_12 ff_yahei color_333333 lh_32"><span style="padding-left: 10px;">原订单编号：${subOrderCustom.subOrderCode}</span><span class="pl_10"><a href="javascript:;" onclick="viewSubOrder(${subOrderCustom.id},${subOrderCustom.status})">【查看】</a></span><span class="pl_10">下单时间：<fmt:formatDate value="${subOrderCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span><span class="pl_10">活动ID:${orderDtl.activityId}</span></div>
					    <ul class="list-group">
						        <li class="list-group-item">
						        	<span>客户原收货人：${subOrderCustom.receiverName}</span><span class="pl_10">电话：${subOrderCustom.receiverPhone}</span><span class="pl_10">地址：${subOrderCustom.receiverAddress}</span><span class="pl_10"><a href="javascript:;" id="showWuliu"> 【查看物流动态】</a></span>
						        </li>
					    </ul>
						</div>
					</div>
					
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead style="background-color: #bce8f1;">
								<tr role="row" class="bbbb">
									<th colspan="5">商品</th>
									<th>单价</th>
									<th>数量</th>
									<th>商家优惠</th>
									<th>运费</th>
									<th>平台优惠</th>
									<th>积分优惠</th>
									<th>实付金额</th>
								</tr>
							</thead>
							<tbody>
								<tr>
							      <td colspan="5">
							      	<div class="no-check order-info3">
							      		<div class="width-60"><img src="${ctx}/file_servelt${orderDtl.skuPic}"></div>
							      		<div class="width-226" style="width: 300px;"><p class="ellipsis">${orderDtl.brandName}${orderDtl.productName}&nbsp;&nbsp;${orderDtl.artNo}</p><p>规格:${orderDtl.productPropDesc}</p><p>SKU码:${orderDtl.sku}</p></div>
							      	</div>
							      </td>
							      <td>${orderDtl.salePrice}</td>
							      <td>${orderDtl.quantity}</td>
							      <td>${orderDtl.mchtPreferential}</td>
							      <td>${orderDtl.freight}</td>
							      <td>${orderDtl.platformPreferential}</td>
							      <td>${orderDtl.integralPreferential}</td>
							      <td>${orderDtl.payAmount}</td>
    							<tr>
							</tbody>
						</table>
					</div>	
				</div>
				<c:if test="${not empty subDepositOrder}">
				<div class="row">
					<h4>预售定金</h4>
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead style="background-color: #bce8f1;">
								<tr>
									<th>定金金额</th>
									<th>抵扣金额</th>
								</tr>
							</thead>
							<tbody>
								<tr>
							      <td class="padding-10 text-left">${subDepositOrder.deposit}</td>
							      <td class="padding-10 text-left">${subDepositOrder.deductAmount}</td>
    							</tr>
							</tbody>
						</table>
					</div>
				</div>
				</c:if>
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
								      <span class="money" style="font-weight: bold;">
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
							      <td class="padding-10 text-left">
							      	<div>${customerServiceLogCustom.content}</div>
							      	<c:if test="${not empty customerServiceLogCustom.serviceLogPics}">
							      	<div style="display: inline-block;">
								      	<c:forEach items="${customerServiceLogCustom.serviceLogPics}" var="serviceLogPic">
									      	<c:if test="${not empty serviceLogPic.pic}">
									      		<img class="w_h_60_60" src="${ctx}/file_servelt${serviceLogPic.pic}" onclick="viewerPic(this.src,this)" name="logPic">
									      	</c:if>
								      	</c:forEach>
							      	</div>
							      	</c:if>
							      </td>
    							</tr>
    						</c:forEach>	
							</tbody>
						</table>
					</div>
				</div>
				
    </div>
  </div>
</div>  
  <!-- 	订单详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

<!--弹出退款Div-->
<div class="video_box" style="position:fixed; width:400px; height:155px; top:30%; left:30%; display: none;border-radius: 2px;" id="refundDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	同意退款确认
				</h3>
		    </div>
		    <div class="panel-body">
				  <div class="form-group">
					    	您确认同意退款给客户：<span style="color: red;">${customerServiceOrder.amount}</span>元？
				  </div>
				  <div class="form-group">
				   	 	<p style="color: gray;">同意之后，金额由平台操作退款给客户，请不要重复支付。</p>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-md-7">
				      <span><button type="button" class="btn btn-default" id="confirm" style="font-size: large;">确认</button></span>
				      <span style="float: right;"><button type="button" class="btn btn-default" name="cancle" >取消</button></span>
				    </div>
				  </div>
		    </div>
		 </div>
</div>

<!--弹出不同意退款Div-->
<div class="video_box" style="position:fixed; width:600px; height:290px; top:35%; left:20%; display: none;border-radius: 2px;" id="disAgreeRefundDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	不同意退款确认
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" >
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">拒绝原因<span style="color: red;">*</span>：</label>
				    <div class="col-md-7" style="padding-left: 40px;">
				    	<select class="form-control" id="reason" style="width: 180px;">
							<option value="">--请选择--</option>
							<c:forEach items="${refundSysParamCfgList}" var="refundSysParamCfg">
								<option value="${refundSysParamCfg.paramValue}">${refundSysParamCfg.paramValue}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">请填写原因说明：</label>
				    <div class="col-md-7">
				      <textarea class="form-control" style="width: 420px;" rows="3" id="reasonDescription"></textarea>
				    </div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-14 control-label" style="padding-left:80px;">上传凭证&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				    <span style="color: gray;margin-left: 30px;">每张图片大小不超过400K，最多3张，支持GIF、JPG、PNG格式</span>
				    <div id="pic-container" style="margin-left: 105px;">
					</div>
				  </div>
				  
				  <div class="form-group" style="padding-left: 60px;">
				    <div class="col-sm-offset-3 col-md-7">
				      <button type="button" class="btn btn-default" id="confirmDisAgree">确认不同意</button>
				      <button type="button" class="btn btn-default" name="cancle">取消</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出物流信息Div-->
<div class="video_box" style="position:fixed; width:610px; height:auto; top:30%; left:32.5%;border-radius: 4px;display: none;border-radius: 2px;" id="wuliuDiv">
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
<div class="black_box" style="display: none;" id="refundBlackBox"></div>
<ul  class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;"></ul>
<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
<script type="text/javascript">
function viewerPic(imgPath,_this){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	var name = $(_this).attr("name");
	$("img[name='"+name+"']").each(function(){
		var eachImgPath = $(this).prop("src");
		if(imgPath!=eachImgPath){
			$("#viewer-pictures").append('<li><img data-original="'+eachImgPath+'" src="'+eachImgPath+'" alt=""></li>');
		}
	});
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
	var width=window.parent.document.documentElement.clientWidth;
	var height=window.parent.document.documentElement.clientHeight;
	$(".viewer-container").height(height);
	$(".viewer-container").width(width-20);
}

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
  var imageUploader;
  var viewerPictures;
	$(function(){
		imageUploader = $("#pic-container").myWebUploader({
			fileNumLimit:3,
	        fileSizeLimit:1024*400*3,
	        fileSingleSizeLimit:1024*400,
	        ulMarginLeft:"-3px"
	    });
	    
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
		$("#showWuliu").on('click',function(){
			$("#wuliuDiv").show();
			$("#refundBlackBox").show();
		});
		
		$("#agreeRefund").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus == "A1"){
				$("#refundDiv").show();
				$("#refundBlackBox").show();
			}else{
				return false;
			}
		});
		
		$(".video_close").on('click',function(){
			$("#refundDiv").hide();
			$("#disAgreeRefundDiv").hide();
			$("#wuliuDiv").hide();
			$("#refundBlackBox").hide();
		});
		
		$("button[name='cancle']").on('click',function(){
			$("#refundDiv").hide();
			$("#disAgreeRefundDiv").hide();
			$("#refundBlackBox").hide();
		});
		var submiting;
		$("#confirm").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "A2";
			var content = "<span>同意退款，请耐心等待。</span>";
			var isRefund = "1";
			submiting=true;
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/customerServiceOrder/customerServiceOrderAgree',
		        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"isRefund":isRefund},
		        dataType: 'json',
		        cache : false,
				async : false,
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		        	$("#proStatus").val(proStatus);
		           	swal(result.returnMsg);
					$("#agreeRefund").parent().parent().parent().remove();
					$("#progress").html('售后进度：客户申请退货 >>商家同意退款 >><span style="color: #f55;">等待平台退款 </span> >><span style="color: gray;"> 售后完成</span>');
					$("#refundDiv").hide();
		        	$("#refundBlackBox").hide();
		        	$("#viewModal").modal('hide');
		           	table.ajax.reload(null, false);
		    		submiting = false;
		        }else{
		        	if(result.returnMsg){
		        		swal(result.returnMsg);
		        	}else{
			        	swal("操作失败，请稍后重试");
		        	}
		    		submiting = false;
		        }
		    });
		});
		
		$("#disAgreeRefund").on('click',function(){
			//添加判断，已同意退款的，再次点击不显示弹出层
			var proStatus = $("#proStatus").val();
			if(proStatus == "A1"){
				$("#disAgreeRefundDiv").show();
				$("#refundBlackBox").show();
			}else{
				return false;
			}
		});
		
		$("#confirmDisAgree").on('click',function(){
			if(submiting){
				return false;
			}
			var customerServiceOrderId = $("#customerServiceOrderId").val();
			var proStatus = "A3";
			var reason = $.trim($("#reason").val());
			if(reason == ""){
				swal("拒绝原因必选，其他选填");
				return false;
			}
			var remarks = $("#reasonDescription").val();
			if(remarks.length >= 400){
				swal("拒绝原因说明需小于400字");
				return false;
			}
			var content = "<span>不同意退款，同时关闭售后</span><br><span>拒绝原因："+reason+"<br>原因说明："+remarks+"</span>";
			submiting = true;
			imageUploader.upload();
			imageUploader.on('uploadFinished',function(){
				var imgPaths = $("#pic-container").getImgPaths();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/customerServiceOrder/customerServiceOrderDisAgree',
			        data: {"customerServiceOrderId":customerServiceOrderId,"proStatus":proStatus,"content":content,"reason":reason,"remarks":remarks,"imgPaths":imgPaths},
			        dataType: 'json',
			        cache : false,
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	submiting = false;
			        	$("#proStatus").val(proStatus);
			           	swal("操作成功");
			           	$("#disAgreeRefund").text("已拒绝退款");
			           	$("#disAgreeRefund").siblings().remove();
			           	$("#disAgreeRefund").prop("readonly",true);
						$("#disAgreeRefund").parent().parent().siblings().remove();
						$("#progress").html('售后进度：客户申请退款 >>商家不同意退款 >><span style="color: #f55;">售后已拒绝 </span>');
						$("#disAgreeRefundDiv").hide();
			        	$("#refundBlackBox").hide();
			        	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	if(result.returnMsg){
			        		swal(result.returnMsg);
			        	}else{
				        	swal("操作失败，请稍后重试");
			        	}
			        }
			    });
			});
		});
		
	});
	
	
  </script>
  
  
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
