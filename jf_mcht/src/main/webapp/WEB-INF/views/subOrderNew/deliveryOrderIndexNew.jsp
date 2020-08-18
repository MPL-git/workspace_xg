<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>批量发货管理</title>
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
<style type="text/css">
.color-1{
color: #9D999D;
}
.color-2{
color: #FC0000;
}
.color-3{
color: #EFD104;
}
.color-4{
color: #00FC28;
}
.color-5{
color: #0351F7;
}
.color-6{
color: #DF00FB;
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

.test-box .more {
	position: relative;
	color: blue;
	cursor: pointer;
}
.test-box .more:hover:after {
	z-index: 999;
	color: white;
	position: absolute;
	top: calc(100% + 10px);
	left: 50%;
	content: attr(data-more);
	padding: 2px 10px;
	background: rgba(0, 0, 0, .5);
	border-radius: 5px;
	word-break: keep-all;
	transform: translateX(-50%);
}
.test-box .more:before {
	z-index: 999;
	display: none;
	position: absolute;
	top: 100%;
	left: 0;
	right: 0;
	content: '';
	width: 0;
	margin: 0 auto;
	border-bottom: 10px solid rgba(0, 0, 0, .5);
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
}
.test-box .more:hover:before {
	display: block;
}
.more {
	position: relative;
	color: blue;
	cursor: pointer;
}
.more:hover:after {
	z-index: 999;
	color: white;
	position: absolute;
	top: calc(100% + 10px);
	left: 50%;
	content: attr(data-more);
	padding: 2px 10px;
	background: rgba(0, 0, 0, .5);
	border-radius: 5px;
	word-break: keep-all;
	transform: translateX(-50%);
}
.more:before {
	z-index: 999;
	display: none;
	position: absolute;
	top: 100%;
	left: 0;
	right: 0;
	content: '';
	width: 0;
	margin: 0 auto;
	border-bottom: 10px solid rgba(0, 0, 0, .5);
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
}
.more:hover:before {
	display: block;
}
</style>
</head>

<body>
	<div class="x_panel container-fluid dd-gl sh-gl pl-gl">
		<div class="row content-title">
			<div class="t-title">批量发货管理</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" name="search_remarksColor" id="search_remarksColor" >
				<input type="hidden" name="searchStatus" value="${searchStatus}" id="searchStatus"/>
				<input type="hidden" id="waitDeliveryCount"/>
				<input type="hidden" id="confirmDeliveryCount"/>
				<input type="hidden" id="hasDeliveryCount"/>
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">商品ID：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_productId" name="search_productId" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">快递名称：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_expressId" id="search_expressId">
						   <option value="">--请选择--</option>
						   <c:forEach var="express" items="${expressList}">
						   		<option value="${express.id}">${express.name}</option>
						   </c:forEach>
                          </select>
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">品牌名称：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_brandId" id="search_brandId">
						   <option value="">--请选择--</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
						   		<option value="${productBrand.id}">${productBrand.name}</option>
						   </c:forEach>
                          </select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">活动ID：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_activityId" name="search_activityId" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">快递单号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_expressNo" name="search_expressNo" >
					</div>
				
					<label for="productBrand" class="col-md-1 control-label search-lable">审核时间：</label>
					<div class="col-md-5 search-condition">
						<input class="form-control datePicker" type="text"  id="payDateBegin" name="payDateBegin" data-date-format="yyyy-mm-dd">
						<i class="picker-split">-</i>
						<input class="form-control datePicker" type="text"  id="payDateEnd" name="payDateEnd" data-date-format="yyyy-mm-dd">
					</div>
				</div>
				
				<div class="form-group">
				   <label for="activityStatus" class="col-md-1 control-label search-lable">收货人：</label>
				   <div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_receiverName" name="search_receiverName" >
				   </div>
					
				   <label class="col-md-1 control-label search-lable">联系电话：</label>
				   <div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_receiverPhone" name="search_receiverPhone" >
				   </div>
				   
				   <label class="col-md-1 control-label search-lable">订单编号：</label>
				   <div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_subOrderCode" name="search_subOrderCode" >
				   </div>

					<div class="col-md-3 text-center search-btn">
			             <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
					</div>
					
				</div>
			</form>
		</div>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs q" role="tablist">
    <li role="presentation" <c:if test="${searchStatus=='0'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('0');" id="waitDelivery">待发货</a></li>
    <li role="presentation" <c:if test="${searchStatus=='1'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('1');" id="confirmDelivery">发货待确认</a></li>
    <li role="presentation" <c:if test="${searchStatus=='2'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('2');" id="hasDelivery">今天已发货</a></li>
  </ul>

<!--   Tab panes -->
<div class="x_content container-fluid">
	<div class="row">
		
		<div class="col-md-12 datatable-container">
			<div class="pl-gl-tab clearfix" id="buttonDiv">
				<div name="searchStatus" <c:if test="${searchStatus ne '0'}">style="display: none;"</c:if>>
	          		<button type="button" class="btn btn-default" name="exportSelected">导出：选中的</button>
	          		<button type="button" class="btn btn-default" name="exportAll">导出：全部</button>
	          		<button type="button" class="btn btn-default" id="batchExpress">批量设置快递</button>
	          		<!--  
	          		<button type="button" class="btn btn-default" id="batchPrintExpress">批量打印快递单</button>
	          		<button type="button" class="btn btn-default" name="batchPrintDelivery">批量打印发货单</button>
	          		-->
	          		<button type="button" class="btn btn-default" name="importExpressNo">导入快递单号</button>
	          	</div>
	          	<div name="searchStatus" <c:if test="${searchStatus ne '1'}">style="display: none;"</c:if>>
	          		<button type="button" class="btn btn-default" name="exportSelected">导出：选中的</button>
	          		<button type="button" class="btn btn-default" name="exportAll">导出：全部</button>
	          		<!--
	          		<button type="button" class="btn btn-default" name="batchPrintDelivery">批量打印发货单</button>
	          		-->
	          		<button type="button" class="btn btn-default" name="importExpressNo">导入快递单号</button>
	          		<button type="button" class="btn btn-default" id="confirmSelected">确认：选中的</button>
	          		<button type="button" class="btn btn-default" id="confirmAll">确认：全部</button>
	          	</div>
	          	<div name="searchStatus" <c:if test="${searchStatus ne '2'}">style="display: none;"</c:if>>
	          		<button type="button" class="btn btn-default" name="exportSelected">导出：选中的</button>
	          		<button type="button" class="btn btn-default" name="exportAll">导出：全部</button>
	          		<button type="button" class="btn btn-default" name="batchUpdate">批量更新</button>
	          		<!--
	          		<button type="button" class="btn btn-default" name="batchPrintDelivery">批量打印发货单</button>
	          		-->
	          	</div>
			</div>

			<div>
				<c:if test="${searchStatus eq '0'}">
					<div class="panel panel" id="progressBar">
			    		<div class="panel-body">
			    			导出待发货，常用的做法：每天固定几个时间点导出，选择好付款时间，本次时间是上次时间的延续，不要重叠，也不要跳跃。
			    		</div>
					</div>
		    	</c:if>
			</div>

						<table id="datatable" class="o table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th><span class="fl" style="float: left;padding-left: 10px;"><input style="vertical-align: middle;margin-right: 5px;" type="checkbox" name="checkAll" onclick="checkAll(this,1);">全选</span>商品</th>
									<th width="78">数量</th>
									<th width="118">是否售后</th>
									<th width="118">快递信息</th>
									<th width="118" class="p-dropdown">操作<li class="dropdown">
											<span data-toggle="dropdown" class="dropdown-toggle glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
											<ul class="dropdown-menu">
												<li><a href="javascript:;" name="flagColor" data-remarkscolor="1"><span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a></li>
												<li><a href="javascript:;" name="flagColor" data-remarkscolor="2"><span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a></li>
												<li><a href="javascript:;" name="flagColor" data-remarkscolor="3"><span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a></li>
												<li><a href="javascript:;" name="flagColor" data-remarkscolor="4"><span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a></li>
												<li><a href="javascript:;" name="flagColor" data-remarkscolor="5"><span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a></li>
												<li><a href="javascript:;" name="flagColor" data-remarkscolor="6"><span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a></li>
											</ul>
										</li>
									</th>
								</tr>
							</thead>
							<tbody id="tbody1">
								
							</tbody>
						</table>
					</div>
	</div>
</div>

</div>
		
<!-- 	订单详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

<!-- 	订单备注 -->

<div class="modal fade" id="remarksModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

<!--弹出div-->
<div class="video_box" style="position:fixed; width:600px; height:180px; top:25%; left:0;right:0;margin:0 auto; display: none;" id="subOrderRemarksDiv">
    <a href="javascript:;" id="closeVideo" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	订单备注
		        	<span style="display: inline-block;float: right;" id="remarksColors">
				    		<a href="javascript:;" name="remarksColor" data-remarkscolor="1"><span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a>
				    		<a href="javascript:;" name="remarksColor" data-remarkscolor="2"><span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor" data-remarkscolor="3"><span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor" data-remarkscolor="4"><span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor" data-remarkscolor="5"><span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a>
					    	<a href="javascript:;" name="remarksColor" data-remarkscolor="6"><span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a>
					</span>
				</h3>
		        
		    </div>
		    <div class="panel-body">
		        <textarea class="form-control" rows="3" id="remarks">${remarks}</textarea>
		        <span id="selectedRemark">
		    	旗帜设置为:
		    	<c:if test="${remarksColor eq '1'}">
		    		<span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '2'}">
		    		<span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '3'}">
		    		<span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '4'}">
		    		<span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '5'}">
		    		<span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
		    	</c:if>
		    	<c:if test="${remarksColor eq '6'}">
		    		<span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span>
		    	</c:if>
		    </span>
			<div class="modal-footer" id="remarksBtnDiv">
			   	<input type="hidden" id="subOrderIds" value="${ids}">
			   	<input type="hidden" id="remarksColor" value="${remarksColor}">
			    <button type="button" class="btn btn-default" id="batchSaveRemarks">提交备注</button>
			</div>
		    </div>
		</div>
    	
</div>
<!--弹出Div End-->

<!--弹出立即发货Div-->
<div class="video_box" style="position:fixed; width:320px; height:170px; top:35%; left:0;right:0;margin:0 auto; display: none;" id="deliveryDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	立即发货
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 10px;" onsubmit="return false;">
		    	  <input type="hidden" id="deliverySubOrderId">
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">快递名称<span style="color: red;">*</span>：</label>
				    <div class="col-md-7">
				    	<select class="form-control" id="expressId" name="expressId">
							<option value="">--请选择--</option>
							<c:forEach var="express" items="${expressList}">
								<option value="${express.id}">${express.name}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">快递单号<span style="color: red;">*</span>：</label>
				    <div class="col-md-7">
				      <input type="text" class="form-control" id="expressNo" name="expressNo">
				    </div>
				  </div>
 
				  <div class="form-group">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="delivery">立即发货</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出批量设置快递Div-->
<div class="video_box" style="position:fixed; width:320px; height:187px; top:35%; left:0;right:0;margin:0 auto; display: none;border-radius: 2px;" id="batchExpressDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default" style="height: 187px;">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	将选中的订单快递名称批量设置
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 25px;" >
				  <div class="form-group" style="padding-left: 30px;">
				    <label class="col-md-3 control-label">快递名称<span style="color: red;">*</span>：</label>
				    <div class="col-md-7">
				    	<select class="form-control" id="batchExpressId" name="batchExpressId">
							<option value="">--请选择--</option>
							<c:forEach var="express" items="${expressList}">
								<option value="${express.id}">${express.name}</option>
							</c:forEach>
				        </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="batchSet">设置</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>

<!--弹出批量发货Div-->
<div class="video_box" style="position:fixed; width:400px; height:265px; top:35%; left:0;right:0;margin:0 auto; display: none;border-radius: 2px;" id="batchDeliveryDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default" style="height: 265px;">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	批量发货
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" id="uploadForm" style="margin-top: 25px;" >
				  <div style="padding-left: 20px;">
				    	请使用本系统导出订单的表格（格式：.xls或.xlsx）填写好快递单号再导入。<br>
						确保表格的前三列分别为：订单号、快递名称、快递单号。<br>
						导入成功后，需要您再次确认，才完成发货。
				  </div>
				  <div style="padding-left: 20px;padding-top: 20px;">
				  		<input id="file" name="file" type="file" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
				  		<input type="hidden"  id="filePath" name="filePath" value="">
				  </div>
				  <div class="form-group" style="margin-top: 40px;">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="import">下一步</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>
<!--弹出div End-->

<!--弹出批量更新Div-->
<div class="video_box" style="position:fixed; width:400px; height:265px; top:35%; left:0;right:0;margin:0 auto; display: none;border-radius: 2px;" id="batchUpdateDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    	<div class="panel panel-default" style="height: 265px;">
		    <div class="panel-heading">
		        <h3 class="panel-title">
		        	批量更新
				</h3>
		    </div>
		    
		    <form class="form-horizontal" role="form" style="margin-top: 25px;" >
				  <div style="padding-left: 20px;">
				    	请使用本系统导出订单的表格（格式：.xls或.xlsx）填写好快递单号再导入。<br>
						确保表格的前三列分别为：订单号、快递名称、快递单号。<br>
						导入后会更新订单的相关物流信息
				  </div>
				  <div style="padding-left: 20px;padding-top: 20px;">
				  		<input id="updateFile" name="updateFile" type="file" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
				  		<input type="hidden"  id="updateFilePath" name="updateFilePath" value="">
				  </div>
				  <div class="form-group" style="margin-top: 40px;">
				    <div class="modal-footer">
				      <button type="button" class="btn btn-default" id="toUpdate">下一步</button>
				    </div>
				  </div>
			 </form>
		 </div>
</div>
<!--弹出div End-->
<div class="black_box" style="display: none;" id="deliveryOrderBlackBox"></div>

	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/clipboard.min.js"></script>
	<script>
function settime(countdown) {
	if (countdown > 0) {
		countdown--; 
	} 
	setTimeout(function() { 
		settime(countdown); 
	},1000) 
}

function checkAll(input,index){
	if($(input).prop("checked")){
		$("#tbody"+index).find("input[name='subOrderId']").each(function(){
			$(this).prop("checked",true);
		});
	}else{
		$("#tbody"+index).find("input[name='subOrderId']").each(function(){
			$(this).prop("checked",false);
		});
	}
}

function receiverAddressFun(receiverAddress) {
	if(receiverAddress.length > 7) {
		return receiverAddress.substring(0,6)+"..."+receiverAddress.substring(receiverAddress.length-1,receiverAddress.length);
	}else {
		return receiverAddress;
	}
}

var table;
$(document).ready(function () {
	
	$('.datePicker').datetimepicker(
			{
    			format: 'yyyy-mm-dd hh:ii:ss',
    		    minView: 0,
    		    minuteStep:1,
    		    autoclose: true,
    		    language: 'zh-CN' //汉化
    	    }
    );
	
	table =	$('#datatable').dataTable({
        "ajax": function (data, callback, settings) {
			var param = $("#searchForm").serializeArray();
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            $.ajax({
                method: 'POST',
                url: '${ctx}/subOrderNew/getDeliveryOrderListNew',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
                    
                    if(result.returnData.waitDeliveryCount>0){
	                    $("#waitDelivery").text("待发货("+result.returnData.waitDeliveryCount+")");
	                    $("#waitDeliveryCount").val(result.returnData.waitDeliveryCount);
                    }
                    if(result.returnData.confirmDeliveryCount>0){
	                    $("#confirmDelivery").text("发货待确认("+result.returnData.confirmDeliveryCount+")");
	                    $("#confirmDeliveryCount").val(result.returnData.confirmDeliveryCount);
                    }
                    if(result.returnData.hasDeliveryCount>0){
	                    $("#hasDelivery").text("今天已发货("+result.returnData.hasDeliveryCount+")");
	                    $("#hasDeliveryCount").val(result.returnData.hasDeliveryCount);
                    }
                    
                    $("input[name='subOrderId']").on('click',function(){
                    	if(!$(this).prop("checked")){
                    		$(this).closest("table").find("input[name='checkAll']").prop("checked",false);
                    	}else{
                    		var checkedLength = $(this).closest("tbody").find("input[name='subOrderId']:checked").length;
                    		var totalLength = $(this).closest("tbody").find("input[name='subOrderId']").length;
                    		if(checkedLength == totalLength){
                    			$(this).closest("table").find("input[name='checkAll']").prop("checked",true);
                    		}
                    	}
                    });
                    
                    $("#showRemarksDiv").unbind('click').on('click',function(){
                		if($("input[name='subOrderId']:checked").length==0){
                			swal("请先选中要备注的订单");
                			return false;
                		}
                		var idsArray = [];
                		$("input[name='subOrderId']:checked").each(function(){
                			idsArray.push($(this).val());
                		});
                		var ids = idsArray.join(",");
                		viewSubOrderRemarks(ids);
                	});
                    
                    $("td[name='operation0']").each(function(){
                    	var flag=false;
                    	$(this).parent().find("td").first().find("div[name='customerServiceOrderTypeAndStatus']").each(function(){
                    		if($(this).text().substring(3,6) == "进行中"){
                    			flag = true;
	                    		return false;
                    		}
                    	});
                    	if(!flag){
	                		var nowDate = new Date();
	                		var nowTime = nowDate.getTime();
	                		var date = $(this).data("statusdate");
	                		var payDate = $(this).data("paydate");
	                		var orderPayDate = (new Date(payDate)).format("yyyy-MM-dd hh:mm:ss");
	                		var subOrderId = $(this).data("suborderid");
	                		
	                		
	                		var endSendTime = date;
	                		var isOver = false;
	                		if(endSendTime>nowTime){
	                			var diff = endSendTime - nowTime;//时间差的毫秒数  
	                			countdown = diff/1000;//秒数
	                		}else{
	                			isOver = true;
	                			var diff = nowTime - endSendTime;//时间差的毫秒数  
	                			countdown = diff/1000;//秒数
	                		}
	                		settime(countdown);
	                		var hour = Math.round(countdown/3600);
	                		if(!isOver){
	                			if(countdown<=3600){
		                			countdownDesc="不足1小时";
		                		}else{
		                			countdownDesc="倒计时："+hour+"小时";
		                		}
	                		}else{
                                if(countdown<=3600){
                                    countdownDesc="超时1小时";
                                }else{
                                    countdownDesc="超时："+hour+"小时";
                                }
	                		}
	                		
	                		var swalClass="";
	                		if(isOver){
	                			swalClass="color:red;";
	                		}
	                		var html = [];
	                		
							html.push('<div class="row"><div><div><a href="javascript:;" onclick="showDeliveryDiv('+subOrderId+');">立即发货</a></div><div>付款时间:'+orderPayDate+'</div><div><span style="'+swalClass+'">'+countdownDesc+'</span></div></div></div>');
							$(this).html(html.join(""));
                    	}
                	});
                    
                    $("td[name='operationFinish']").each(function(){
                        var flag=true;
                        $(this).parent().find("td").first().find("div[name='customerServiceOrderTypeAndStatus']").each(function(){
                            if($(this).text().substring(3,6) == "进行中"){
                                flag = false;
                            }
                        });
                    	var suborderid = $(this).data("suborderid");
                    	var deliverydate = $(this).data("deliverydate")+24*60*60*1000;
                    	var now = new Date().getTime();
                        if(!flag){
                            $(this).text("");
                        }else if(now<deliverydate){
                    		$(this).html('<a href="javascript:;" onclick="toEditDelivery('+suborderid+');">修改快递信息</a>');
                    	}else{
                    		$(this).text("已发货"); 
                    	}
	                	           	
                	});
                    $("td[name='operationReady']").each(function(){
                    	var flag=false;
                    	$(this).parent().find("td").first().find("div[name='customerServiceOrderTypeAndStatus']").each(function(){
                    		var afterState = $(this).text().slice(3,6);
                    		if(afterState == "进行中"){
                    			flag = true;
	                    		return false;
                    		}
                    	});
                    	
	                		var nowDate = new Date();
	                		var nowTime = nowDate.getTime();
	                		var date = $(this).data("statusdate");
	                		var payDate = $(this).data("paydate");
	                		var orderPayDate = (new Date(payDate)).format("yyyy-MM-dd hh:mm:ss");
	                		var orderDtlId = $(this).data("suborderid");
	                		
	                		
	                		var endSendTime = date;
	                		var isOver = false;
	                		if(endSendTime>nowTime){
	                			var diff = endSendTime - nowTime;//时间差的毫秒数  
	                			countdown = diff/1000;//秒数
	                		}else{
	                			isOver = true;
	                			var diff = nowTime - endSendTime;//时间差的毫秒数  
	                			countdown = diff/1000;//秒数
	                		}
	                		settime(countdown);
	                		var hour = Math.round(countdown/3600);
	                		if(!isOver){
	                			if(countdown<=3600){
		                			countdownDesc="不足1小时";
		                		}else{
		                			countdownDesc="倒计时："+hour+"小时";
		                		}
	                		}else{
	                			if(countdown<=3600){
		                			countdownDesc="超时1小时";
		                		}else{
									countdownDesc="超时："+hour+"小时";
		                		}
	                		}
	                		
	                		var swalClass="";
	                		if(isOver){
	                			swalClass="color:red;";
	                		}
	                		var html = [];
	                		if(!flag){
								html.push('<div class="row"><div><div><a href="javascript:;" onclick="toEditDelivery('+orderDtlId+');">修改快递信息</a></div><div>付款时间:'+orderPayDate+'</div><div><span style="'+swalClass+'">'+countdownDesc+'</span></div></div></div>');
	                		}else{
	                			html.push('<div class="row"><div><div>付款时间:'+orderPayDate+'</div></div></div>');
	                		}
							$(this).html(html.join(""));
                    	
                	});
                    
                    
                    $("a[name='viewDirectCompensationOrder']").on('click',function(){
                    	var subOrderId = $(this).data("suborderid");
                    	$.ajax({
                            url: "${ctx}/customerServiceOrder/customerServiceOrderView?subOrderId="+subOrderId+"&serviceType=D", 
                            type: "GET", 
                            success: function(data){
                                $("#viewModal").html(data);
                                $("#viewModal").modal();
                            },
                    	    error:function(){
                    	    	swal('页面不存在');
                    	    }
                    	});
                    });
                    
                }
            });
        },
        "language": {"url": '${ctx}/static/cn.json'},
        "autoWidth": true,   // 禁用自动调整列宽
        "order": [[ 1, 'asc' ]],          // 取消默认排序查询,否则复选框一列会出现小箭头
        "processing": true,  // 隐藏加载提示,自行处理
        "serverSide": true,   // 启用服务器端分页
        "searching": false,   // 禁用原生搜索
        "bSort": false,
        "bServerSide": true,
        
        "rowCallback": function( row, data, index ) {
        	var orderDtl = data;
        	var createDate = new Date(orderDtl.createDate).format("yyyy-MM-dd hh:mm:ss");
        	var activityIds = orderDtl.activityId;
        	if(!activityIds){
        		activityIds = "";
        	}
        	var receiverPhone = orderDtl.receiverPhone;
        	if(!receiverPhone){
        		receiverPhone="";
        	}
        	var receiverName = orderDtl.receiverName;
        	if(!receiverName){
        		receiverName="";
        	}
        	var receiverAddress = orderDtl.receiverAddress;
        	if(!receiverAddress){
        		receiverAddress="";
        	}
        	var expressNo = orderDtl.expressNo;
        	if(!expressNo){
        		expressNo = "无";
        	}
        	var expressName = orderDtl.expressName;
        	if(!expressName){
        		expressName = "无";
        	}
        	var paymentStr;
			if(orderDtl.paymentId == 1 || orderDtl.paymentId == 6){//支付宝
				paymentStr = "支付宝支付";
			}else if(orderDtl.paymentId == 2 || orderDtl.paymentId == 4 || orderDtl.paymentId == 5){//微信
				paymentStr = "微信支付";
			}else if(orderDtl.paymentId == 3){//银联
				paymentStr = "银联";
			}
			var remarksColor = orderDtl.remarksColor;
        	var remarksColorHtml="";
        	if(remarksColor == "1"){
        		remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks('+orderDtl.subOrderId+');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a>';
        	}else if(remarksColor == "2"){
        		remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks('+orderDtl.subOrderId+');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a>';
        	}else if(remarksColor == "3"){
        		remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks('+orderDtl.subOrderId+');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a>';
        	}else if(remarksColor == "4"){
        		remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks('+orderDtl.subOrderId+');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a>';
        	}else if(remarksColor == "5"){
        		remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks('+orderDtl.subOrderId+');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a>';
        	}else if(remarksColor == "6"){
        		remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks('+orderDtl.subOrderId+');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span>';
        	}
          	var rowtext=[];

          	rowtext.push('<td colspan="7">');

			var blackMemberRemark="";
			//黑名单订单
			if(orderDtl.memberStatus=='P'){
				blackMemberRemark = '<span style="color:red;">订单异常，谨慎发货</span>';
			}
          	var orderDtlId = orderDtl.id;
          	
          	rowtext.push('<div class="order info1"><input type="checkbox" name="subOrderId" value="'+orderDtl.id+'"><span>订单编号：'+orderDtl.subOrderCode+'-'+orderDtl.id+'</span><span class="more" data-more="'+receiverName+'">收货人：'+receiverAddressFun(receiverName)+'</span><span>电话：'+receiverPhone+'</span><span class="more" data-more="'+receiverAddress+'">地址：'+receiverAddressFun(receiverAddress)+'<a href="javascript:void(0);" class="copyAddress" data-clipboard-action="copy" data-clipboard-text="'+receiverAddress+'" >【复制】</a></span>'+blackMemberRemark+remarksColorHtml+'</div>');
//          rowtext.push('<div class="order info1"><input type="checkbox" name="subOrderId" value="'+subOrder.id+'"><span>订单编号：'+subOrder.subOrderCode+'</span><span>支付方式：'+paymentStr+'</span><span>下单时间：'+createDate+'</span><span>活动ID：'+activityIds+'</span>'+blackMemberRemark+remarksColorHtml+'</div>');
//          rowtext.push('<div class="order info2 test-box"><span>收货人：'+receiverName+'</span><span>电话：'+receiverPhone+'</span>地址：<span class="more" data-more="'+receiverAddress+'">'+receiverAddressFun(receiverAddress)+'<a href="javascript:void(0);" class="copyAddress" data-clipboard-action="copy" data-clipboard-text="'+receiverAddress+'" >【复制】</a></span><span>快递名称：'+expressName+'</span><span>单号：'+expressNo+'</span></div>');

          	rowtext.push('<table class="order-info3">');
          	rowtext.push('<tr>');
          	rowtext.push('<td class="br">');
           	rowtext.push('<table>');
           	rowtext.push('<tr style="border-bottom: 1px solid #ddd;">');
           	var orderDtlCustom = orderDtl;
           	var customerServiceType = orderDtlCustom.customerServiceType;
           	var customerServiceStatusDesc = orderDtlCustom.customerServiceStatusDesc;
           	if(!customerServiceType){
           		customerServiceType = "";
           	}
           	if(!customerServiceStatusDesc){
           		customerServiceStatusDesc = "";
           	}
           	rowtext.push('<td class="br">');
      		rowtext.push("<div class='no-check'>");
      		if(orderDtlCustom.cloudProductItemId){
				rowtext.push("<div class='width-60' style='height: 80px;'>"+"<img src='${ctx}/file_servelt"+orderDtlCustom.skuPic+"'>"+"<br><span style='color:green'>供应商发货</span></div>");
			}else{
				rowtext.push("<div class='width-60'>"+"<img src='${ctx}/file_servelt"+orderDtlCustom.skuPic+"'>"+"</div>");
			}

      		rowtext.push("<div class='width-226'><p class='ellipsis'>"+orderDtlCustom.brandName+"&nbsp;&nbsp;"+orderDtlCustom.productName+"&nbsp;&nbsp;"+orderDtlCustom.artNo+"</p><p>规格:"+orderDtlCustom.productPropDesc+"</p><p>SKU码:"+orderDtlCustom.sku+"</p></div>");
      		
      		rowtext.push("<div>");
           	
           	rowtext.push('</td>');
           	rowtext.push('<td width="78" class="br">');
        	rowtext.push(orderDtlCustom.quantity);
           	rowtext.push('</td>');
           	rowtext.push('<td width="118">');
           	rowtext.push('<div name="customerServiceOrderTypeAndStatus">'+customerServiceType+''+customerServiceStatusDesc+'</div>');
        	if(customerServiceType == "直赔单"){
        		rowtext.push('<div><a href="javascript:;" name="viewDirectCompensationOrder" data-suborderid="'+orderDtlCustom.subOrderId+'">查看</a></div>');
        	}else if(customerServiceType && customerServiceStatusDesc){
        		if(orderDtlCustom.customerServiceStatus!='3'){
	           		rowtext.push('<div><a href="javascript:;" onclick="viewCustomerServiceOrder('+orderDtlCustom.id+');">查看</a></div>');
        		}
           	}
           	rowtext.push('</td>');
           	rowtext.push('</tr>');
        
           	rowtext.push('</table>');
          	rowtext.push('</td>');
          	if(orderDtl.merchantCode!=null && orderDtl.merchantCode!=""){
          		rowtext.push('<td width="118" class="br"><div>快递公司：'+orderDtl.dtlExpressName+'<br>快递单号：'+orderDtl.dtlExpressNo+'<br>快递商户编号：'+orderDtl.merchantCode+'</div></td>');
          	}else if(orderDtl.dtlExpressNo!=null){
          		rowtext.push('<td width="118" class="br"><div>快递公司：'+orderDtl.dtlExpressName+'<br>快递单号：'+orderDtl.dtlExpressNo+'</div></td>');
          	}else{
          		rowtext.push('<td width="118" class="br"></br>');
          	}
          	if(orderDtl.dtlExpressNo!=null){
          		if(orderDtl.deliveryStatus==0){
          			rowtext.push('<td width="118" name="operationReady" data-suborderid="'+orderDtl.id+'" data-statusdate="'+orderDtl.deliveryLastDate+'" data-paydate="'+orderDtl.paymentDate+'" data-deliverydate="'+orderDtl.deliveryDate+'"></td>');
          		}else if(orderDtl.deliveryStatus==1){
          			rowtext.push('<td width="118" name="operationFinish" data-suborderid="'+orderDtl.id+'" data-statusdate="'+orderDtl.deliveryLastDate+'" data-paydate="'+orderDtl.paymentDate+'" data-deliverydate="'+orderDtl.deliveryDate+'"></td>');
          		}
          	}else{
          		rowtext.push('<td width="118" name="operation'+orderDtl.deliveryStatus+'" data-suborderid="'+orderDtl.id+'" data-statusdate="'+orderDtl.deliveryLastDate+'" data-paydate="'+orderDtl.paymentDate+'" data-deliverydate="'+orderDtl.deliveryDate+'"></td>');
          	}
          	rowtext.push('</tr>');
          	rowtext.push('</table>');
          	
          	rowtext.push('</td>' );
            $(row).html(rowtext.join(""));
            },
        
        
        "columns": [
			{"data": "id"},
			{"data": "id","width":"78"},
			{"data": "id","width":"118"},
			{"data": "id","width":"118"},
			{"data": "id","width":"118"}
        ]
    }).api();
	
	
	$("a[name='flagColor']").on('click',function(){
		var flagClass = $(this).find("span").prop("class");
		$(this).closest("th").find("span").first().prop("class",flagClass);
		$("#search_remarksColor").val($(this).data("remarkscolor"));
		table.ajax.reload();
	});
	
	$(".video_close").on('click',function(){
		$("#subOrderRemarksDiv").hide();
		$("#deliveryDiv").hide();
		$("#batchExpressDiv").hide();
		$("#batchDeliveryDiv").hide();
		$("#batchUpdateDiv").hide();
		$(".black_box").hide();
	});

	$('#btn-query').on('click', function (event) {
		if(Number($("#search_activityId").val()) >= Number(2147483647)){
			swal("活动ID输入有误");
			return false;
		}
	    table.ajax.reload();
	});
	
	$("#delivery").on('click',function(){
		var subOrderId = $("#deliverySubOrderId").val();
		var expressId = $("#expressId").val();
		var expressNo = $("#expressNo").val();
		if($.trim(expressId)==""){
			swal("快递名称必选");
			return false;
		}
		if($.trim(expressNo)==""){
			swal("快递单号不能为空");
			return false;
		}
		$.ajax({
		        method: 'POST',
		        url: '${ctx}/subOrderNew/subOrderDeliveryNew',
		        data: {"subOrderId":subOrderId,"expressId":expressId,"expressNo":expressNo},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		        	$("#deliveryDiv").hide();
		        	$(".black_box").hide();
		        	$("#deliverySubOrderId").val("");
		           	swal("发货成功");
		            table.ajax.reload(null, false);
		        }else{
		        	if(result.returnMsg){
			        	swal(result.returnMsg);
		        	}else{
			        	swal("发货失败，请稍后重试");
		        	}
		        }
		    });
	});
	
	$("#expressNo").keydown(function(e){
		if(e.keyCode==13){
			var subOrderId = $("#deliverySubOrderId").val();
			var expressId = $("#expressId").val();
			var expressNo = $("#expressNo").val();
			if($.trim(expressId)==""){
				swal("快递名称必选");
				return false;
			}
			if($.trim(expressNo)==""){
				swal("快递单号不能为空");
				return false;
			}
			$.ajax({
					method: 'POST',
					url: '${ctx}/subOrderNew/subOrderDeliveryNew',
					data: {"subOrderId":subOrderId,"expressId":expressId,"expressNo":expressNo},
					dataType: 'json'
				}).done(function (result) {
					if (result.returnCode =='0000') {
						$("#deliveryDiv").hide();
						$(".black_box").hide();
						$("#deliverySubOrderId").val("");
						swal("发货成功");
						table.ajax.reload(null, false);
					}else{
						if(result.returnMsg){
				        	swal(result.returnMsg);
			        	}else{
				        	swal("发货失败，请稍后重试");
			        	}
					}
				});
		}
	});
	
	$("button[name='exportSelected']").on('click',function(){
		var orderDtlIdArray = [];
		$("input[name='subOrderId']:checked").each(function(){
			orderDtlIdArray.push($(this).val());
		});
		if(orderDtlIdArray.length == 0){
			swal("请先选中要导出的订单");
			return false;
		}
		var orderDtlIds = orderDtlIdArray.join(",");
		location.href="${ctx}/subOrderNew/exportSelectedDeliverySubOrderNew?orderDtlIds="+orderDtlIds;
	});
	
	$("button[name='exportAll']").on('click',function(){
		var searchStatus = $("#searchStatus").val();
		var search_productId = $("#search_productId").val();
		var search_expressId = $("#search_expressId").val();
		var search_brandId = $("#search_brandId").val();
		var search_activityId = $("#search_activityId").val();
		var search_expressNo = $("#search_expressNo").val();
		var payDateBegin = $("#payDateBegin").val();
		var payDateEnd = $("#payDateEnd").val();
		var search_receiverName = $("#search_receiverName").val();
		var search_receiverPhone = $("#search_receiverPhone").val();
		var search_subOrderCode = $("#search_subOrderCode").val();
		location.href="${ctx}/subOrderNew/exportDeliverySubOrderNew?search_productId="+search_productId+"&search_expressId="+search_expressId+"&search_brandId="+search_brandId+"&search_activityId="+search_activityId+"&search_expressNo="+search_expressNo+"&payDateBegin="+payDateBegin+"&payDateEnd="+payDateEnd+"&search_receiverName="+search_receiverName+"&search_receiverPhone="+search_receiverPhone+"&search_subOrderCode="+search_subOrderCode+"&searchStatus="+searchStatus;
	});
	
	$("#batchExpress").on('click',function(){
		var subOrderIdArray = [];
		$("input[name='subOrderId']:checked").each(function(){
			subOrderIdArray.push($(this).val());
		});
		if(subOrderIdArray.length == 0){
			swal("请先选中要设置的订单");
			return false;
		}
		$("#batchExpressDiv").show();
		$("#deliveryOrderBlackBox").show();
	});
	
	$("#batchSet").on('click',function(){
		var subOrderIdArray = [];
		$("input[name='subOrderId']:checked").each(function(){
			subOrderIdArray.push($(this).val());
		});
		var orderDtlIds = subOrderIdArray.join(",");
		var expressId = $("#batchExpressId").val();
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/subOrderNew/batchExpressNew',
	        data: {"orderDtlIds":orderDtlIds,"expressId":expressId},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	$("#batchExpressDiv").hide();
	        	$(".black_box").hide();
	           	swal("设置成功");
	            table.ajax.reload();
	        }else{
	        	swal("设置失败，请稍后重试");
	        }
	    });
	});
	
	$("#confirmSelected").on('click',function(){
		var orderDtlIdArray = [];
		$("input[name='subOrderId']:checked").each(function(){
			orderDtlIdArray.push($(this).val());
		});
		if(orderDtlIdArray.length == 0){
			swal("请先选中要确认的订单");
			return false;
		}
		var orderDtlIds = orderDtlIdArray.join(",");
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/subOrderNew/confirmSelectedNew',
	        data: {"orderDtlIds":orderDtlIds},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	           	swal(result.returnMsg);
	           	var successCount = result.returnData.successCount;
	           	var confirmDeliveryCount = $("#confirmDeliveryCount").val();
	           	var lastCount = parseInt(confirmDeliveryCount)-parseInt(successCount);
	           	$("#confirmDeliveryCount").val(lastCount);
	           	$("#confirmDelivery").text("发货待确认("+lastCount+")");
	           	$("#confirmDeliveryCount").val(lastCount);
	           	$("#buttonDiv").find("div[name='searchStatus']").hide();
	           	$("#buttonDiv").find("div[name='searchStatus']").eq(2).show();
	           	$("#searchStatus").val("2");
	        	$("#confirmDelivery").parent().prop("class","");
	           	$("#hasDelivery").parent().prop("class","active");
	            table.ajax.reload();
	        }else{
	        	if(result.returnMsg){
	        		swal(result.returnMsg);
	        	}else{
		        	swal("确认失败，请稍后重试");
	        	}
	        }
	    });
	});
	
	$("#confirmAll").on('click',function(){
		var subOrderIdArray = [];
		$("input[name='subOrderId']").each(function(){
			subOrderIdArray.push($(this).val());
		});
		if(subOrderIdArray.length == 0){
			swal("没有需要确认的订单");
			return false;
		}
		var param = $("#searchForm").serializeArray();
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/subOrderNew/confirmAllNew',
	        data: param,
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	           	swal(result.returnMsg);
	           	var successCount = result.returnData.successCount;
	           	var confirmDeliveryCount = $("#confirmDeliveryCount").val();
	           	var lastCount = parseInt(confirmDeliveryCount)-parseInt(successCount);
	           	$("#confirmDeliveryCount").val(lastCount);
	           	$("#confirmDelivery").text("发货待确认("+lastCount+")");
	           	$("#confirmDeliveryCount").val(lastCount);
	           	$("#buttonDiv").find("div[name='searchStatus']").hide();
	           	$("#buttonDiv").find("div[name='searchStatus']").eq(2).show();
	           	$("#searchStatus").val("2");
	        	$("#confirmDelivery").parent().prop("class","");
	           	$("#hasDelivery").parent().prop("class","active");
	            table.ajax.reload();
	        }else{
	        	if(result.returnMsg){
	        		swal(result.returnMsg);
	        	}else{
		        	swal("确认失败，请稍后重试");
	        	}
	        }
	    });
	});
	
	$("button[name='importExpressNo']").on('click',function(){
		$("#batchDeliveryDiv").show();
		$("#deliveryOrderBlackBox").show();
	});

	$("#import").on('click',function(){
		if(isEmpty(document.getElementById("file").files[0])){
            swal("请选择文件");
            return;
		}
		var formData = new FormData();
        formData.append("file", document.getElementById("file").files[0]);
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/subOrderNew/importExpressNoNew',
	        data: formData,
	        dataType: 'json',
	        cache: false,  
            processData: false,  
            contentType: false 
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	$("#batchDeliveryDiv").hide();
	        	$(".black_box").hide();
	           	swal("成功导入"+result.returnData.importSuccess+"单");
	           	$("#buttonDiv").find("div[name='searchStatus']").hide();
	           	$("#buttonDiv").find("div[name='searchStatus']").eq(1).show();
	           	$("#searchStatus").val("1");
	           	$("#waitDelivery").parent().prop("class","");
	           	$("#confirmDelivery").parent().prop("class","active");
	           	table.ajax.reload();
	        }else{
	        	if(result.returnMsg){
	        		swal(result.returnMsg);
	        	}else{
		        	swal("设置失败，请稍后重试");
	        	}
	        	$("#batchDeliveryDiv").hide();
	        	$(".black_box").hide();
	           	table.ajax.reload();
	        }
	    });
	});
	
	$("button[name='batchUpdate']").on('click',function(){
		$("#batchUpdateDiv").show();
		$("#deliveryOrderBlackBox").show();
	});
	
	$("#toUpdate").on('click',function(){
		var formData = new FormData();
        formData.append("updateFile", document.getElementById("updateFile").files[0]);
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/subOrderNew/batchUpdateExpressNew',
	        data: formData,
	        dataType: 'json',
	        cache: false,  
            processData: false,  
            contentType: false 
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	$("#batchUpdateDiv").hide();
	        	$(".black_box").hide();
	           	swal("批量更新成功"+result.returnData.importSuccess+"单");
	           	table.ajax.reload();
	        }else{
	        	if(result.returnMsg){
	        		swal(result.returnMsg);
	        	}else{
		        	swal("批量更新失败，请稍后重试");
	        	}
	        	$("#batchUpdateDiv").hide();
	        	$(".black_box").hide();
	           	table.ajax.reload();
	        }
	    });
	});
	
	// 复制地址
	var clipboard = new Clipboard('.copyAddress');
	clipboard.on('success', function (e) {
		swal("复制成功！");
	});
	
});

//判断字符是否为空
function isEmpty(obj){
    return (typeof obj === 'undefined' || obj === null || obj === "");
}

function viewSubOrder(id,status){
		$.ajax({
	        url: "${ctx}/subOrderNew/subOrderViewNew?id="+id+"&status="+status, 
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

function viewSubOrderRemarks(ids){
	$.ajax({
        url: "${ctx}/subOrder/subOrderRemarks?ids="+ids, 
        type: "GET", 
        success: function(data){
            $("#remarksModal").html(data);
            $("#remarksModal").modal();
        },
	    error:function(){
	    	swal('页面不存在');
	    }
	});

}

function viewCustomerServiceOrder(id){
	$.ajax({
        url: "${ctx}/customerServiceOrder/customerServiceOrderView?orderDtlId="+id, 
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

function showDeliveryDiv(id){
	$.ajax({
		url: "${ctx}/subOrderNew/toDeliveryNew?id="+id, 
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

function list(searchStatus) {
    var url = "${ctx}/subOrderNew/deliveryOrderIndexNew?searchStatus=" + searchStatus;
    getContent(url);
}

function toEditDelivery(id){
	$.ajax({
		url: "${ctx}/subOrderNew/toEditDeliveryNew?id="+id, 
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
</script>
</body>
</html>
