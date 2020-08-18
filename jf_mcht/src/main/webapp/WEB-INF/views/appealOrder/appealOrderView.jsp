<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>投诉详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
<link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/imageUploader.css"/>
<style type="text/css">
.td-pictures li {
	display: inline-block;
}

.td-pictures li img {
    width: 100px;
    height: 100px;
}

.docs-pictures li {
    position: relative;
    margin: 3px;
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
</head>

<body>
<!--查看投诉详情 -->
  <div class="modal-dialog sh-xq ts-xq" role="document" style="width:1000px;">
    <div class="modal-content">
    <input type="hidden" id="appealOrderId" value="${appealOrder.id}">
    <input type="hidden" id="mchtShortName" value="${mchtShortName}">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">投诉详情页</span>
      </div>
     <div class="modal-body">
     			<div class="list-group-item" id="progress" style="margin-bottom: 10px;">
	     			<c:if test="${appealOrder.status eq '2'}">
	    				当前进度：<span style="color:#f55;"> 客户投诉 </span>>>投诉处理中 >>投诉关闭
	     			</c:if>
	     			<c:if test="${appealOrder.status eq '1'}">
	    				当前进度：客户投诉 >><span style="color:#f55;"> 投诉处理中  </span>>>投诉关闭
	     			</c:if>
	     			<c:if test="${appealOrder.status eq '3' || appealOrder.status eq '4'|| appealOrder.status eq '5'}">
	    				当前进度：客户投诉 >>投诉处理中>><span style="color:#f55;"> 投诉关闭  </span>
	     			</c:if>
    			</div>

				<div class="row">
    				<h4>商品信息</h4>
				    <div class="col-md-12 datatable-container">
						<table class="table table-striped table-bordered no-footer"
							role="grid" aria-describedby="datatable_info">
						    <tr>
						       <th colspan="7">商品信息</th>
						       <th>货号</th>
						       <th>商品价格</th>
						       <th>优惠金额</th>
						       <th>实际付款金额</th>
						    </tr>   
					        <tr>
					        	<td colspan="7">
					        		<div class="no-check">
					        			<div class="width-60">
					        				<img src="${ctx}/file_servelt${orderDtl.skuPic}">
					        			</div>
					        			<div class="width-226 c9" style="width: 270px;">
					        				<p class="ellipsis" style="color: #333">${orderDtl.brandName}&nbsp;${orderDtl.artNo}&nbsp;${orderDtl.productName}</p>
					        				<br>${orderDtl.sku}
					        			</div>
					        		</div>
					        	</td>
					        	<td>${orderDtl.artNo}</td>
					        	<td>${orderDtl.salePrice}</td>
					        	<td>
					        		<c:if test="${not empty totalPreferentialInfoDesc}">
						        		${totalPreferentialInfoDesc}
					        		</c:if>
					        		<c:if test="${empty totalPreferentialInfoDesc}">
						        		无
					        		</c:if>
					        	</td>
					        	<td>${orderDtl.payAmount}</td>
					        </tr>
	    				</table>
	    			</div>
				</div>

				<div class="row">
					<h4>投诉信息：</h4>
					<div class="col-md-12 datatable-container">
						<table class="table table-striped table-bordered no-footer"
							role="grid" aria-describedby="datatable_info">
							<tbody>
								<tr>
							      <td>投诉单号</td>
							      <td>${appealOrder.orderCode}</td>
							      <td>订单编号</td>
							      <td>${subOrderCode}</td>
							      <td>投诉类型</td>
							      <td>${appealTypeDesc}</td>
							      <td>投诉状态</td>
							      <td>${statusDesc}</td>
    							<tr>
								<tr>
							      <td>投诉人</td>
							      <td>${appealOrder.userName}</td>
							      <td>联系人</td>
							      <td>${appealOrder.contactName}</td>
							      <td>联系电话</td>
							      <td>${appealOrder.contactPhone}</td>
							      <td>责任判定</td>
							      <td>
							      <c:if test="${not empty liabilityDesc}">
							      	${liabilityDesc}
							      </c:if>
							      </td>
    							<tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<h4>处理过程：</h4>
					<div class="col-md-12 datatable-container ts-xq-cl">
						<table class="table table-striped table-bordered no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row" class="active">
									<th>发起人</th>
									<th>内容</th>
								</tr>
							</thead>
							<tbody id="process">
							<c:forEach items="${appealLogCustoms}" var="appealLogCustom">
								<tr style="background: white;">
							      <td style="width: 25%;text-align: left;">
							      <c:if test="${appealLogCustom.userType eq '1'}">
							      	<span style="color: #4FC6DD;">买家</span>
							      </c:if>
							      <c:if test="${appealLogCustom.userType eq '2'}">
							      	<span style="color: #c9302c;">醒购平台客服</span>
							      </c:if>
							      <c:if test="${appealLogCustom.userType eq '3'}">
							      	<span style="color: #f0ad4e;">商家</span>
							      </c:if>
							      	<br>
							      	<fmt:formatDate value="${appealLogCustom.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
							      </td>
							      <td style="text-align: left;">
							      	${appealLogCustom.content}<br>
							      	<div class="pic-container">
							      	<ul class="docs-pictures clearfix td-pictures pictures-list">
							      	<c:forEach items="${appealLogCustom.pics}" var="pic">
								      	<li class="pic_li">
								      		<img src="${ctx}/file_servelt${pic}" style="width: 60px;height: 60px;">
								      	</li>	
							      	</c:forEach>
							      	</ul>
							      	</div>
							      </td>
    							<tr>
    						</c:forEach>	
							</tbody>
						</table>
					</div>
				</div>
				<div id="appealLogDiv" <c:if test="${not empty appealOrder.serviceSponsorType && appealOrder.status ne '2'}">style="display: none;"</c:if>>
					<div class="panel panel-default">
					    <div class="panel-body">
					        <form role="form" id="appealLogForm">
								  <div class="form-group">
									    <label for="" class="col-sm-2">留言：</label>
									    <div>
									    	<textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="content"></textarea>
									    </div>
								  </div>
								  <div class="form-group">
				  				  		<label for="" class="col-sm-2">举证：</label>
									    <div id="pic-container">
									    	
								    	</div>
								    	
			  				  	  </div>
							</form>
					    </div>
				    	<div class="panel-footer" style="height: 50px;background-color: white;text-align: center;">
						      <button type="submit" class="btn btn-info" id="comfirm">提交</button>
						      <c:if test="${empty appealOrder.serviceSponsorType}">
							      <a href="javascript:;" id="showApplyDiv">申请客服介入</a>
						      </c:if>
						      <c:if test="${not empty appealOrder.serviceSponsorType}">
							      <c:if test="${appealOrder.serviceStatus eq '0'}">
							      	等待客服介入
							      </c:if>
							      <c:if test="${appealOrder.serviceStatus eq '1'}">
							      	处理中
							      </c:if>
							      <c:if test="${appealOrder.serviceStatus eq '2'}">
							      	已结束
							      </c:if>
						      </c:if>
				    	</div>
					</div>	
	    		</div>
    			
	    		<div id="serviceStatusDiv" <c:if test="${empty appealOrder.serviceSponsorType}">style="display: none;"</c:if>>
    				<div class="list-group-item">
    				   		已申请客服介入，当前客服介入状态：
    				   		<span style="color:#f55;" id="serviceStatusDesc">
	    				   		<c:if test="${appealOrder.serviceStatus eq '0'}">待介入</c:if>
	    				   		<c:if test="${appealOrder.serviceStatus eq '1'}">处理中</c:if>
	    				   		<c:if test="${appealOrder.serviceStatus eq '2'}">已结束</c:if>
    				   		</span>
    				</div>
	    		</div>
    			
	    			
</div>
				
				
    </div>
  </div>
  <!--申请客服介入Div-->
<div class="video_box" style="position:fixed; width:450px; height:170px; top:35%; left:0; right: 0;margin: 0 auto; display: none;" id="applyDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	给客服留言：
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="padding: 10px 10px 0;" >
				  <div class="form-group">
						<textarea style="box-shadow: none;border-radius: 1px;width:100%;" rows="5" placeholder="此留言只有客服能看到，客户看不到。" id="mchtRemarks"></textarea>
				  </div>
				  <div class="modal-footer">
				    	<div class="">
				      		<button type="button" class="btn btn-info" id="apply">申请客服介入</button>
				    	</div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出div End-->
<div class="black_box" style="display: none;" id="viewBlackBox"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
<script type="text/javascript" src="${ctx}/static/js/imageUpload.js" ></script>
<script type="text/javascript">
var imageUploader;
	$(function(){
		var imgPaths = $("#pics").val();
		imageUploader = $("#pic-container").myWebUploader({
			fileNumLimit:5,
	        fileSizeLimit:1024*1024*6,
	        fileSingleSizeLimit:1024*1024*2,
	        imgPaths:imgPaths
	    });
		$("#showApplyDiv").on('click',function(){
			$("#applyDiv").show();
			$("#viewBlackBox").show();
		});
	
		var submitting;
		$("#comfirm").on('click',function(){
			if(submitting){
				return false;
			}
			var appealOrderId = $("#appealOrderId").val();
			var userType = "3";//处理人类型 3.商家
			var content = $.trim($("#content").val());
			if(content == ""){
				swal("请输入留言");
				return false;
			}
			imageUploader.upload();
			imageUploader.on('uploadFinished',function(){
				var imgs = $("#pic-container").getImgPaths();
				submitting = true;
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/appealOrder/saveAppealLog',
			        data: {"appealOrderId":appealOrderId,"userType":userType,"content":content,"imgs":imgs},
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			           	var mchtShortName = $("#mchtShortName").val();
			           	var appealLogCreateDate = result.returnData.appealLogCreateDate;
			           	var html = [];
			           	html.push('<tr>');
			           	html.push('<td style="width: 25%;text-align: left;">商家 <br><span style="color: #f0ad4e;">'+appealLogCreateDate+'</span></td>');
			           	html.push('<td style="text-align: left;">'+content+'<br>');
			           	if(result.returnData.imgs){
			           		var imgsArray = result.returnData.imgs.split(",");
			           		for(var i=0;i<imgsArray.length;i++){
			           			html.push('<img src="${ctx}/file_servelt/'+imgsArray[i]+'" style="width: 60px;height: 60px;">');
			           		}
			           	}
			           	html.push('</td>');
			           	html.push('</tr>');
			        	$("#process").append(html.join(""));
			        	$("#appealLogForm")[0].reset();
			        	$("#progress").html('当前进度：客户投诉 >><span style="color:#f55;"> 投诉处理中  </span>>>投诉关闭');
			           	swal("提交成功");
			           	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	swal("提交失败，请稍后重试");
			        }
			        submitting = false;
			    });
		    });
		});
		
		$("#apply").on('click',function(){
			if(submitting){
				return false;
			}
			var appealOrderId = $("#appealOrderId").val();
			var serviceSponsorType = "1";//申请介入发起人类型 1.商家 2.用户
			var serviceStatus="0";//客服介入状态 0.待处理
			var mchtRemarks = $("#mchtRemarks").val();
			submitting = true;
			$.ajax({
			        method: 'POST',
			        url: '${ctx}/appealOrder/applyIntervention',
			        data: {"appealOrderId":appealOrderId,"serviceSponsorType":serviceSponsorType,"serviceStatus":serviceStatus,"mchtRemarks":mchtRemarks},
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			           	swal("申请介入成功");
			        	$("#applyDiv").hide();
			        	$("#viewBlackBox").hide();
			        	$("#showApplyDiv").hide();
			        	$("#appealLogDiv").hide();
			        	$("#serviceStatusDesc").text("待介入");
			        	$("#serviceStatusDiv").show();
			        	$("#viewModal").modal('hide');
			           	table.ajax.reload(null, false);
			        }else{
			        	swal("申请介入失败，请稍后重试");
			        }
			        submitting = false;
			    });
		});

		$(".video_close").on('click',function(){
			$("#applyDiv").hide();
			$("#viewBlackBox").hide();
		});
		
	});
	
  </script>
</body>
</html>
