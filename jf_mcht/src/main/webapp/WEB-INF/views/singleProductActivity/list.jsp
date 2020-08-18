<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>单品活动管理</title>
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div class="x_panel container-fluid dd-gl">
		<div class="row content-title">
			<div class="t-title">
	            单品活动管理
	        </div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="search_code" class="col-md-1 control-label search-lable">商品id：</label>
					<div class="col-md-3 search-condition">
						<input class="form-control" type="text" data-type="number" id="search_code" name="search_code" >  
					</div>
					
					<label for="search_type" class="col-md-1 control-label search-lable">活动类型：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_type" id="search_type">
						   <option value="">请选择</option>
						   <c:forEach var="type" items="${types}">
						   		<option value="${type.statusValue}">${type.statusDesc}</option>
						   </c:forEach>
                          </select>
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">状态：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_auditStatus" id="search_auditStatus">
						   <option value="">请选择</option>
						   <c:forEach var="auditStatus" items="${auditStatusList}">
						   		<option value="${auditStatus.statusValue}">${auditStatus.statusDesc}</option>
						   </c:forEach>
                          </select>
					</div>
					<div class="col-md-3 text-center search-btn">
						             <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
					</div>
				</div>
				
			</form>

		</div>
		<div class="clearfix"></div>
		<!--   Tab panes -->
		<div class="x_content container-fluid">
			<div class="row">
				<div class="col-md-12 datatable-container">
								<table id="datatable" class="o table table-striped table-bordered dataTable no-footer"
									role="grid" aria-describedby="datatable_info">
									<thead style="display: none;">
										
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

	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
	<script>

var table;
$(document).ready(function () {
	
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
				url: '${ctx}/singleProductActivity/data',
				data: param,
				dataType: 'json'
			}).done(function (result) {
				if (result.returnCode =='0000') {
					var returnData = {};
					returnData.recordsTotal = result.returnData.Total;
					returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
					returnData.data = result.returnData.Rows;
					callback(returnData);
					
				}
			});
		},
		"language": {"url": '${ctx}/static/cn.json'},
		"autoWidth": false,   // 禁用自动调整列宽
		"order": [[ 1, 'asc' ]],          // 取消默认排序查询,否则复选框一列会出现小箭头
		"processing": true,  // 隐藏加载提示,自行处理
		"serverSide": true,   // 启用服务器端分页
		"searching": false,   // 禁用原生搜索
		"bSort": false,
		"bServerSide": true,
		
		"rowCallback": function( row, data, index ) {
			var createDate = new Date(data.createDate);
			createDate.setDate(createDate.getDate()+7);//设置天数 +7 天  
			var time = createDate.format("yyyy-MM-dd hh:mm:ss");
			var closeHtml;
			if(data.isClose == 1){
				closeHtml = '<span style="float: right;">已关闭</span>';
			}else{
				closeHtml = '';
			}
			var rejectReason;
			if(data.rejectReason){
				rejectReason = data.rejectReason;
			}else{
				rejectReason = "";
			}
			var timeEndStr = "";
			if(data.auditStatus == 1){
				if(data.endTime){
					var endTime = new Date(data.endTime);
					var now = new Date();
					if(endTime<now){
						timeEndStr = '&nbsp;&nbsp;&nbsp;&nbsp;<a style="color:red;text-decoration:none;">期望活动时间已结束</a>';
					}
				}
			}
			var rowtext=[];
			rowtext.push('<td colspan="5">');
			rowtext.push('<div class="mt-10"><div class="order info1"><span>活动类型：'+data.typeDesc+'</span><span>商品ID：'+data.productCode+'</span>');
			if(data.auditStatus == 3){//排期通过
				rowtext.push('<span>活动排期时间：'+new Date(data.beginTime).format("yyyy-MM-dd hh:mm:ss")+'—'+new Date(data.endTime).format("yyyy-MM-dd hh:mm:ss")+'</span>');
			}else{
				rowtext.push('<span>审核截止时间：'+time+''+timeEndStr+'</span>');
			}
			rowtext.push(closeHtml);
			rowtext.push('</div></div>');
			rowtext.push('<table class="order-info3">');
				rowtext.push('<tr>');
					rowtext.push('<td>');
						rowtext.push('<table>');
						rowtext.push('<tr>');
							rowtext.push('<td class="br" style="width: 50%;">');
								rowtext.push("<div class='no-check'>");
								rowtext.push("<div class='width-60'>"+"<img src='${ctx}/file_servelt"+data.pic+"'>"+"</div>");
								rowtext.push("<div class='width-226'><p class='ellipsis' title='"+data.productName+"'>"+data.productName+"</p><p>品牌:"+data.brandName+"&nbsp;&nbsp;货号:"+data.artNo+"</p><p>原价:"+data.originalPrice+"元&nbsp;&nbsp;活动价："+data.activityPrice+"元&nbsp;&nbsp;活动库存："+data.activityStock+"件</p></div>");
								rowtext.push("</div>");
							rowtext.push('</td>');
							rowtext.push('<td class="br" style="padding-left: 10px;text-align: left;">');
								rowtext.push('状态：'+data.auditStatusDesc);
								if(data.auditStatus == "3"){
									rowtext.push('<br><span style="color:red;">销量：'+data.saleCount+"件</span>");
								}
								rowtext.push('<br><span style="color:grey;">'+rejectReason+'</span>');
							rowtext.push('</td>');
							rowtext.push('</tr>');
						rowtext.push('</table>');
					rowtext.push('</td>');
				rowtext.push('</tr>');
			rowtext.push('</table>');
			rowtext.push('</td>' );
			$(row).html(rowtext.join(""));
		},
		"columns": [
			{"data": "id"},
			{"data": "id"}
		]
	}).api();
	
	$('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
	
	$("#search_code").keydown(function(e){
    	if(e.keyCode==13){
    		table.ajax.reload();
            return false;
    	}
    });
});

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
			url: '${ctx}/subOrder/subOrderDelivery',
			data: {"subOrderId":subOrderId,"expressId":expressId,"expressNo":expressNo},
			dataType: 'json'
		}).done(function (result) {
			if (result.returnCode =='0000') {
				$("#deliveryDiv").hide();
				$(".black_box").hide();
				$("#deliverySubOrderId").val("");
				swal("发货成功");
				table.ajax.reload();
			}else{
				if(result.returnMsg){
		        	swal(result.returnMsg);
	        	}else{
		        	swal("发货失败，请稍后重试");
	        	}
			}
		});
});
</script>
</body>
</html>
