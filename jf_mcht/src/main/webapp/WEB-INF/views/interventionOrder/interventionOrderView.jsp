<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
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
</style>
<title>介入单详情</title>
</head>

<body>
<!--查看订单详情 -->
  <div class="modal-dialog dd-xq" role="document" style="width:1000px;">
  <input type="hidden" id="interventionOrderId" value="${interventionOrder.id}">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">介入单详情</span>
      </div>
     <div class="modal-body">
				<div class="row">
					<h4>介入单信息</h4>
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row" class="active">
									<th>介入单号</th>
									<th>售后单号</th>
									<th>凭证</th>
									<th>介入原因</th>
									<th>介入留言</th>
									<th>联系人</th>
									<th>联系电话</th>
									<th>申请介入时间</th>
								</tr>
							</thead>
							<tbody>
								<tr>
							      <td>${interventionOrder.interventionCode}</td>
							      <td><a href="javascript:;" onclick="viewCustomerServiceOrder(${interventionOrder.serviceOrderId})">${customerServiceOrderCode}</a></td>
							      <td>
							      	<c:if test="${not empty customerInterventionProcessPicList}">
							      		<a href="javascript:;" onclick="showProof(${interventionOrder.id},1);">查看</a>
							      	</c:if>
							      </td>
							      <td>
							      	<c:if test="${interventionOrder.reason eq '1'}">
							      		商家拒绝7天无理由退货
							      	</c:if>
							      	<c:if test="${interventionOrder.reason eq '2'}">
							      		快递未送达
							      	</c:if>
							      	<c:if test="${interventionOrder.reason eq '3'}">
							      		商品质量问题
							      	</c:if>
							      	<c:if test="${interventionOrder.reason eq '4'}">
							      		商家少发/漏发
							      	</c:if>
							      	<c:if test="${interventionOrder.reason eq '5'}">
							      		商家缺货
							      	</c:if>
							      	<c:if test="${interventionOrder.reason eq '6'}">
							      		发票问题
							      	</c:if>
							      	<c:if test="${interventionOrder.reason eq '7'}">
							      		运费问题
							      	</c:if>
							      	<c:if test="${interventionOrder.reason eq '8'}">
							      		商家违背承诺
							      	</c:if>
							      	<c:if test="${interventionOrder.reason eq '9'}">
							      		其他
							      	</c:if>
							      </td>
							      <td>${interventionOrder.message}</td>
							      <td>${interventionOrder.contacts}</td>
							      <td>${interventionOrder.tel}</td>
							      <td><fmt:formatDate value="${interventionOrder.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
    							<tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<h4>相关商品信息</h4>
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row" class="active">
									<th>商品ID</th>
									<th>商品名称</th>
									<th>商品吊牌价</th>
									<th>醒购价</th>
									<th>售后单进度状态</th>
									<th>退款金额</th>
									<th>支付金额</th>
								</tr>
							</thead>
							<tbody>
								<tr>
							      <td class="w_h_100_36">${productCode}</td>
							      <td class="w_h_100_36">${orderDtl.productName}</td>
							      <td class="w_h_280_36">${orderDtl.tagPrice}</td>
							      <td class="w_h_90_36">${orderDtl.salePrice}</td>
							      <td class="w_h_374_36 f_f_c_l">${proStatusDesc}</td>
							      <td>${refundAmount}</td>
							      <td>${orderDtl.payAmount}</td>
    							<tr>
							</tbody>
						</table>
					</div>
				</div>
				<c:if test="${not empty mchtContent}">
				<div class="row">
					<h4>商家申诉</h4>
					<div>
						<table class="panel-table">
				 			<tr>
				 				<td>申诉内容</td>
				 				<td>${mchtContent}</td>
				 			</tr>
				 			<tr>
				 				<td>申诉凭证</td>
				 				<td>
				 					<c:forEach items="${mchtInterventionProcessPicList}" var="mchtInterventionProcessPic">
				 						<img src="${ctx}/file_servelt${mchtInterventionProcessPic.pic}" style="width: 60px;height: 60px;">
				 					</c:forEach>
				 				</td>
				 			</tr>
			 			</table>
					</div>
				</div>
				</c:if>
				<c:if test="${not empty interventionProcessCustoms}">
				<div class="row">
					<div style="width: 100%;display:inline-flex;">
						<h4>协商内容</h4>
						<a href="javascript:;" style="margin-left: 87%;" onclick="showMore(${interventionOrder.id},2);">查看更多</a>
					</div>
					<div>
						<table class="panel-table">
						<c:forEach items="${interventionProcessCustoms}" var="interventionProcessCustom">
				 			<tr>
				 				<td>商家<br><fmt:formatDate value="${interventionProcessCustom.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				 				<td>
				 					${interventionProcessCustom.content}<br>
				 					<c:forEach items="${interventionProcessCustom.interventionProcessPics}" var="interventionProcessPic">
				 						<img src="${ctx}/file_servelt${interventionProcessPic.pic}" style="width: 60px;height: 60px;">
				 					</c:forEach>
				 				</td>
				 			</tr>
						</c:forEach>
			 			</table>
					</div>
				</div>
				</c:if>
				<c:if test="${not empty interventionOrder.winType}">
				<div class="row">
					<h4>平台判定</h4>
					<div>
						<table class="panel-table">
				 			<tr>
				 				<td>商家</td>
				 				<td><span style="color: red;">${mchtResultReasonDesc}</span></td>
				 			</tr>
			 			</table>
					</div>
				</div>
				</c:if>
				<c:if test="${empty interventionOrder.winType && interventionOrder.status eq '8'}">
				<div class="row">
					<h4>介入单已结案</h4>
					<div>
						<table class="panel-table">
					 		<tr>
					 			<td>客户</td>
					 			<td>客户主动撤销介入单</td>
					 		</tr>
				 		</table>
					</div>
				</div>
				</c:if>
				<c:if test="${not empty proofInterventionProcessCustoms}">
				<div class="row">
					<div style="width: 100%;display:inline-flex;">
						<h4>处理凭证记录</h4>
						<a href="javascript:;" style="margin-left: 84%;" onclick="showMore(${interventionOrder.id},3);">查看更多</a>
					</div>
					<div>
						<table class="panel-table">
						<c:forEach items="${proofInterventionProcessCustoms}" var="proofInterventionProcessCustom">
				 			<tr>
				 				<td>
				 					<c:if test="${proofInterventionProcessCustom.operatorType eq '2'}">
				 						商家
				 					</c:if>
				 					<c:if test="${proofInterventionProcessCustom.operatorType eq '3'}">
				 						平台
				 					</c:if>
				 					<br>
				 					<fmt:formatDate value="${proofInterventionProcessCustom.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
				 				<td>
				 					${proofInterventionProcessCustom.content}<br>
				 					<c:forEach items="${proofInterventionProcessCustom.interventionProcessPics}" var="proofInterventionProcessPic">
				 						<img src="${ctx}/file_servelt${proofInterventionProcessPic.pic}" style="width: 60px;height: 60px;">
				 					</c:forEach>
				 				</td>
				 			</tr>
						</c:forEach>
			 			</table>
					</div>
				</div>
				</c:if>
				<c:if test="${interventionOrder.status eq '4'}">
				<div class="row">
					<h4>商家申诉：</h4>
					<div class="form-group">
						<input type="radio" name="accept" value="1">同意买家介入申请
						<input type="radio" name="accept" value="0" checked="checked">不同意申请
					</div>
					<div class="panel panel-default" id="notAcceptDiv">
					    <div class="panel-body">
					        <form role="form" id="notAcceptForm">
								  <div class="form-group">
									    <label for="content" class="col-sm-2">申诉内容：</label>
									    <div>
									    	<textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="content"></textarea>
									    </div>
								  </div>
								  <div class="form-group">
				  				  		<label for="" class="col-sm-2">申诉凭证（最多三张）：</label>
									    <div id="pic-container">
									    	
								    	</div>
			  				  	  </div>
							</form>
					    </div>
				    	<div class="panel-footer" style="height: 50px;background-color: white;text-align: center;">
						      <button  class="btn btn-info" id="notAcceptConfirm">提交</button>
				    	</div>
					</div>
					<div class="panel" id="acceptDiv" style="display: none;">
						<div class="panel-footer" style="height: 50px;background-color: white;text-align: center;">
						      <button  class="btn btn-info" id="acceptConfirm">提交</button>
				    	</div>
					</div>	
	    		</div>	
				</c:if>
				<c:if test="${interventionOrder.status eq '5'}">
				<div class="row">
					<h4>留言</h4>	
					<div class="panel panel-default">
					    <div class="panel-body">
					        <form role="form">
								  <div class="form-group">
									    <label for="" class="col-sm-2">留言：</label>
									    <div>
									    	<textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="content"></textarea>
									    </div>
								  </div>
								  <div class="form-group">
				  				  		<label for="" class="col-sm-2">上传凭证（最多三张）：</label>
									    <div id="message-pic-container">
									    	
								    	</div>
			  				  	  </div>
							</form>
					    </div>
				    	<div class="panel-footer" style="height: 50px;background-color: white;text-align: center;">
						      <button  class="btn btn-info" id="messageConfirm">提交</button>
				    	</div>
					</div>
				</div>		
				</c:if>
				<c:if test="${interventionOrder.status eq '6'}">
				<div class="row">
					<h4>上传处理凭证</h4>	
					<div class="panel panel-default">
					    <div class="panel-body">
					        <form role="form">
								  <div class="form-group">
									    <label for="" class="col-sm-2">处理说明：</label>
									    <div>
									    	<textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="content"></textarea>
									    </div>
								  </div>
								  <div class="form-group">
				  				  		<label for="" class="col-sm-2">上传凭证（最多三张）：</label>
									    <div id="proof-pic-container">
									    	
								    	</div>
			  				  	  </div>
							</form>
					    </div>
				    	<div class="panel-footer" style="height: 50px;background-color: white;text-align: center;">
						      <button  class="btn btn-info" id="proofConfirm">提交</button>
				    	</div>
					</div>
				</div>		
				</c:if>
    </div>
  </div>
<div class="video_box" style="position:fixed; width:905px;top:14%; left:4%; display: none;" id="showMoreDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title" id="showMoreTitle">
					协商内容记录
				</h3>
			</div>
			<div class="row" style="max-width:905px;max-height:750px;overflow-x:hidden;overflow-y: auto;">
				<div style="padding-left: 11px;">
					<table class="panel-table" id="rowData" style="width: 900px;">
			 		</table>
				</div>
			</div>
		 </div>
</div>
<div class="video_box" style="position:fixed; width:905px;height:500px;top:25%; left:4%; display: none;" id="showProofDiv">
	<a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">
					查看凭证
				</h3>
			</div>
			<div class="row" style="max-width:905px;">
				<div style="padding-left: 11px;" id="proofData">
					
				</div>
			</div>
		 </div>
</div>		
<!--弹出div End-->
<div class="black_box" style="display: none;" id="interventionOrderBlackBox"></div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
  <script type="text/javascript">
  function showMore(interventionOrderId,processType){
	  $.ajax({
	        method: 'POST',
	        url: '${ctx}/interventionOrder/showMore',
	        data: {"interventionOrderId":interventionOrderId,"processType":processType},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	if(processType == "2"){
	        		$("#showMoreTitle").text("协商内容记录");
	        	}else if(processType == "3"){
	        		$("#showMoreTitle").text("处理凭证记录");
	        	}
	        	$("#rowData").empty();
	        	var html = [];
	        	var interventionProcessCustoms = result.returnData.interventionProcessCustoms;
	        	for(var i=0;i<interventionProcessCustoms.length;i++){
	        		var createDate = new Date(interventionProcessCustoms[i].createDate).format("yyyy-MM-dd hh:mm");
	        		html.push('<tr>');
	        		html.push('<td>商家<br>'+createDate+'</td>');
	        		html.push('<td>');
	        		html.push(interventionProcessCustoms[i].content+'<br>');
	        		var picHtml = [];
	 				for(var j=0;j<interventionProcessCustoms[i].interventionProcessPics.length;j++){
	 					picHtml.push('<img src="${ctx}/file_servelt'+interventionProcessCustoms[i].interventionProcessPics[j].pic+'" style="width: 60px;height: 60px;">');
	 				}
	 				html.push(picHtml.join(""));
	 				html.push("</td>");	
	 				html.push("</tr>");	
	        	}
	        	$("#rowData").html(html.join(""));
	        	$("#showMoreDiv").show();
	            $("#interventionOrderBlackBox").show();
	        }else{
	        	swal("提交失败，请稍后重试");
	        }
	    });
	}
  
  function showProof(interventionOrderId,processType){
	  $.ajax({
	        method: 'POST',
	        url: '${ctx}/interventionOrder/showProof',
	        data: {"interventionOrderId":interventionOrderId,"processType":processType},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	var picHtml = [];
	        	var interventionProcessPics = result.returnData.interventionProcessPics;
 				for(var i=0;i<interventionProcessPics.length;i++){
 					picHtml.push('<img src="${ctx}/file_servelt'+interventionProcessPics[i].pic+'" style="width: 280px;height: 280px;">');
 				}
 				$("#proofData").html(picHtml.join(""));
	        	$("#showProofDiv").show();
	            $("#interventionOrderBlackBox").show();
	        }else{
	        	swal("提交失败，请稍后重试");
	        }
	    });
	}
  
  	var imageUploader;
	$(function(){
		<c:if test="${interventionOrder.status eq '4'}">
			imageUploader = $("#pic-container").myWebUploader({
				fileNumLimit:3,
		        fileSizeLimit:1024*1024*6,
		        fileSingleSizeLimit:1024*1024*2
		    });
		</c:if>
		<c:if test="${interventionOrder.status eq '5'}">
			imageUploader = $("#message-pic-container").myWebUploader({
				fileNumLimit:3,
		        fileSizeLimit:1024*1024*6,
		        fileSingleSizeLimit:1024*1024*2,
		        divMarginLeft:"10px"
		    });
		</c:if>
		<c:if test="${interventionOrder.status eq '6'}">
			imageUploader = $("#proof-pic-container").myWebUploader({
				fileNumLimit:3,
		        fileSizeLimit:1024*1024*6,
		        fileSingleSizeLimit:1024*1024*2,
		        divMarginLeft:"10px"
		    });
		</c:if>
		
		$("input[name='accept']").bind('click',function(){
			var accept = $(this).val();
			if(accept == "1"){//同意
				$("#notAcceptDiv").hide();
				$("#acceptDiv").show();
			}else if(accept == "0"){//不同意
				$("#notAcceptDiv").show();
				$("#acceptDiv").hide();
			}
		});
		
		var submitting;
		$("#notAcceptConfirm").on('click',function(){
			if(submitting){
				return false;
			}
			var interventionOrderId = $("#interventionOrderId").val();
			var operatorType = "2";//操作人类型 2.商家
			var processType = "1";//申诉
			var content = $.trim($("#content").val());
			if(content == ""){
				swal("请输入申诉内容");
				return false;
			}
			var status = "5";//商家申诉中（平台待评判）
			submitting = true;
			imageUploader.upload();
			imageUploader.on('uploadFinished',function(){
				var imgs = $("#pic-container").getImgPaths();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/interventionOrder/saveInterventionProcess',
			        data: {"interventionOrderId":interventionOrderId,"operatorType":operatorType,"processType":processType,"content":content,"imgs":imgs,"status":status},
			        dataType: 'json'
			    }).done(function (result) {
			        submitting = false;
			        if (result.returnCode =='0000') {
			           	swal("提交成功");
			           	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	swal("提交失败，请稍后重试");
			        }
			    });
		    });
		});
		
		var acceptSubmitting;
		$("#acceptConfirm").on('click',function(){
			swal({ 
				  title: "您同意买家介入申请时，将放弃该介入单的申诉",
				  showCancelButton: true, 
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "确定", 
				  cancelButtonText: "取消",
				  closeOnConfirm: false
				},
				function(isConfirm){ 
				  if(isConfirm) { 
					  if(acceptSubmitting){
							return false;
						}
						var interventionOrderId = $("#interventionOrderId").val();
						var status = "6";//待商家上传凭证
						var winType = "1";//买家
						acceptSubmitting = true;
						$.ajax({
					        method: 'POST',
					        url: '${ctx}/interventionOrder/saveInterventionOrder',
					        data: {"interventionOrderId":interventionOrderId,"status":status,"winType":winType},
					        dataType: 'json'
					    }).done(function (result) {
					    	acceptSubmitting = false;
					        if (result.returnCode =='0000') {
					           	swal("提交成功");
					           	$("#viewModal").modal('hide');
					           	table.ajax.reload(null, false);
					        }else{
					        	swal("提交失败，请稍后重试");
					        }
					    });
				  }
				});
		});
		
		var messageSubmitting;
		$("#messageConfirm").on('click',function(){
			if(messageSubmitting){
				return false;
			}
			var interventionOrderId = $("#interventionOrderId").val();
			var operatorType = "2";//操作人类型 2.商家
			var processType = "2";//留言
			var content = $.trim($("#content").val());
			if(content == ""){
				swal("请输入留言");
				return false;
			}
			messageSubmitting = true;
			imageUploader.upload();
			imageUploader.on('uploadFinished',function(){
				var imgs = $("#message-pic-container").getImgPaths();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/interventionOrder/saveInterventionProcess',
			        data: {"interventionOrderId":interventionOrderId,"operatorType":operatorType,"processType":processType,"content":content,"imgs":imgs},
			        dataType: 'json'
			    }).done(function (result) {
			    	messageSubmitting = false;
			        if (result.returnCode =='0000') {
			           	swal("提交成功");
			           	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	swal("提交失败，请稍后重试");
			        }
			    });
		    });
		});
		
		var proofSubmitting;
		$("#proofConfirm").on('click',function(){
			if(proofSubmitting){
				return false;
			}
			var interventionOrderId = $("#interventionOrderId").val();
			var operatorType = "2";//操作人类型 2.商家
			var processType = "3";//处理凭证
			var content = $.trim($("#content").val());
			if(content == ""){
				swal("请输入处理说明");
				return false;
			}
			var status = "7";//待结案
			proofSubmitting = true;
			imageUploader.upload();
			imageUploader.on('uploadFinished',function(){
				var imgs = $("#proof-pic-container").getImgPaths();
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/interventionOrder/saveInterventionProcess',
			        data: {"interventionOrderId":interventionOrderId,"operatorType":operatorType,"processType":processType,"content":content,"imgs":imgs,"status":status},
			        dataType: 'json'
			    }).done(function (result) {
			    	proofSubmitting = false;
			        if (result.returnCode =='0000') {
			           	swal("提交成功");
			           	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	swal("提交失败，请稍后重试");
			        }
			    });
		    });
		});
		
		$(".video_close").on('click',function(){
			$("#showMoreDiv").hide();
			$("#showProofDiv").hide();
			$(".black_box").hide();
		});
	});
  </script>
</body>
</html>
