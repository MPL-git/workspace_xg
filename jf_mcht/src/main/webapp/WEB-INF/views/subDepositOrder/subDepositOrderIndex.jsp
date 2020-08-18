<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>定金管理</title>
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
</head>

<body>
	<div class="x_panel container-fluid dd-gl">
		<div class="row content-title">
			<div class="t-title">
	            	定金管理
	        </div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">商品ID：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_productCode" name="search_productCode" >
					</div>
					<label for="productBrand" class="col-md-1 control-label search-lable">品牌名称：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_brandName" id="search_brandName">
						   <option value="">--请选择--</option>
						   <c:forEach var="brandName" items="${brandNames}">
								<option value="${brandName}">${brandName}</option>
						   </c:forEach>
						  </select>
					</div>
					<label for="productBrand" class="col-md-1 control-label search-lable">付款时间：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" type="text"  id="payDateBegin" name="payDateBegin" data-date-format="yyyy-mm-dd HH:mm:ss">
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="payDateEnd" name="payDateEnd" data-date-format="yyyy-mm-dd HH:mm:ss">
					</div>
				</div>
				
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">定金状态：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_status" id="search_status">
						   <option value="">--不限状态--</option>
						   <c:forEach var="status" items="${statusList}">
								<option value="${status.statusValue}">${status.statusDesc}</option>
						   </c:forEach>
						  </select>
					</div>
					<label for="productBrand" class="col-md-1 control-label search-lable">预售订单编号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_subDepositOrderCode" name="search_subDepositOrderCode" >
					</div>
					<div class="col-md-3 text-center search-btn">
						<button type="button"  class="btn btn-info" id="btn-query">搜索</button>
						<button type="button"  class="btn btn-info" id="btn-out">导出</button>
					</div>
				</div>
			</form>
		</div>

<!--   Tab panes -->
<div class="x_content container-fluid">
	<div class="row">
		<div class="col-md-12 datatable-container">
			<table id="datatable" class="o table table-striped table-bordered dataTable no-footer"
				role="grid" aria-describedby="datatable_info">
				<thead>
					<tr role="row">
						<th>商品</th>
						<th>预售价</th>
						<th>数量</th>
						<th>定金</th>
						<th>抵用金额</th>
						<th>定金状态</th>
						<th>实付金额</th>
						<th class="p-dropdown">操作</th>
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

<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/clipboard.min.js"></script>
<script type="text/javascript">
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
				url: '${ctx}/subDepositOrder/getSubDepositOrderList',
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
			var subDepositOrder = data;
			var createDate = new Date(subDepositOrder.createDate).format("yyyy-MM-dd hh:mm:ss");
			var payDate = "";
			if(subDepositOrder.payDate){
				payDate = new Date(subDepositOrder.payDate).format("yyyy-MM-dd hh:mm:ss");
			}
			var activityId = subDepositOrder.activityId;
			var statusDesc = subDepositOrder.statusDesc;
			var paymentStr="";
			if(subDepositOrder.paymentId == 1 || subDepositOrder.paymentId == 6){//支付宝
				paymentStr = "支付宝支付";
			}else if(subDepositOrder.paymentId == 2 || subDepositOrder.paymentId == 4 || subDepositOrder.paymentId == 5){//微信
				paymentStr = "微信支付";
			}else if(subDepositOrder.paymentId == 3){//银联
				paymentStr = "银联";
			}
			var rowtext=[];
			rowtext.push('<td colspan="8">');
			rowtext.push('<div class="mt-10"><div class="order info1"><span>预售订单编号：'+subDepositOrder.subDepositOrderCode+'</span><span>支付方式：'+paymentStr+'</span><span>下单时间：'+createDate+'</span><span>付款时间：'+payDate+'</span></div></div>');
			rowtext.push('<table class="order-info3">');
				rowtext.push('<tr>');
					rowtext.push('<td>');
						rowtext.push('<table>');
							rowtext.push('<tr>');
							rowtext.push('<td width="346" class="br">');
							rowtext.push("<div class='no-check'>");
							rowtext.push("<div class='width-60'>"+"<img src='${ctx}/file_servelt"+subDepositOrder.skuPic+"'>"+"</div>");
							if(subDepositOrder.sku){
								rowtext.push("<div class='width-226'><p class='ellipsis' title='"+subDepositOrder.brandName+"&nbsp;&nbsp;"+subDepositOrder.productName+"&nbsp;&nbsp;"+subDepositOrder.artNo+"'>"+subDepositOrder.brandName+"&nbsp;&nbsp;"+subDepositOrder.productName+"&nbsp;&nbsp;"+subDepositOrder.artNo+"</p><p><a href='javascript:;'>"+subDepositOrder.productPropDesc+"</a>  SKU:"+subDepositOrder.sku+"</p><p style='color:red;'>活动ID:"+subDepositOrder.activityId+"</p></div>");
							}else{
								rowtext.push("<div class='width-226'><p class='ellipsis' title='"+subDepositOrder.brandName+"&nbsp;&nbsp;"+subDepositOrder.productName+"&nbsp;&nbsp;"+subDepositOrder.artNo+"'>"+subDepositOrder.brandName+"&nbsp;&nbsp;"+subDepositOrder.productName+"&nbsp;&nbsp;"+subDepositOrder.artNo+"</p><p><a href='javascript:;'>"+subDepositOrder.productPropDesc+"</a></p><p style='color:red;'>活动ID:"+subDepositOrder.activityId+"</p></div>");
							}
							rowtext.push("</div>");
							rowtext.push('</td>');

							rowtext.push('<td width="65" class="br">');
							rowtext.push(formatMoney(subDepositOrder.salePrice,2));
							rowtext.push('</td>');

							rowtext.push('<td width="66" class="br">');
							rowtext.push(subDepositOrder.quantity);
							rowtext.push('</td>');

							rowtext.push('<td width="76" class="br">');
							rowtext.push('<div>'+formatMoney(subDepositOrder.deposit,2)+'</div>');
							
							rowtext.push('</td>');
							rowtext.push('</tr>');
						rowtext.push('</table>');
					rowtext.push('</td>');
					var completeDateStr="";
					if(subDepositOrder.completeDate){
						var completeDate = new Date(subDepositOrder.completeDate).format("yyyy-MM-dd hh:mm:ss");
						completeDateStr=completeDate;
					}
					rowtext.push('<td width="87" class="br"><div>'+formatMoney(subDepositOrder.deductAmount,2)+'</div></td>');
					rowtext.push('<td width="88" class="br"><div>'+statusDesc+'</div><div style="color:red;">'+completeDateStr+'<div></td>');
					
					rowtext.push('<td width="101" class="br"><span class="money">'+formatMoney(subDepositOrder.payAmount,2)+'</span>');
					rowtext.push('</td>');
					if((subDepositOrder.status == '3' || subDepositOrder.status == '4' || subDepositOrder.status == '5' || subDepositOrder.status == '6' || subDepositOrder.status == '8' || subDepositOrder.status == '9') && subDepositOrder.subOrderId){
						rowtext.push('<td width="98" name="operation"><a href="javascript:;" onclick="viewSubOrder('+subDepositOrder.subOrderId+');">查看尾款</a></td>');
					}else{
						rowtext.push('<td width="98" name="operation" style="color:#999999;">查看尾款</td>');
					}
				rowtext.push('</tr>');
			rowtext.push('</table>');
			rowtext.push('</td>' );
			$(row).html(rowtext.join(""));
		},
		"columns": [
			{"data": "id","width":"345"},
			{"data": "id","width":"67"},
			{"data": "id","width":"67"},
			{"data": "id","width":"76"},
			{"data": "id","width":"86"},
			{"data": "id","width":"88"},
			{"data": "id","width":"100"},
			{"data": "id","width":"97"}
		]
	}).api();
	
	$('#btn-query').on('click', function (event) {
		table.ajax.reload();
	});

	$("#btn-out").on('click',function(){
		var search_productCode = $("#search_productCode").val();
		var search_brandName = $("#search_brandName").val();
		var search_status = $("#search_status").val();
		var search_subDepositOrderCode = $("#search_subDepositOrderCode").val();
		var payDateBegin = $("#payDateBegin").val();
		var payDateEnd = $("#payDateEnd").val();
		if(!payDateBegin || !payDateEnd){
			swal("请填写付款时间");
			return false;
		}
		var startTime =  new Date(Date.parse(payDateBegin)).getTime();
		var endTime =  new Date(Date.parse(payDateEnd)).getTime();
		var dates = Math.abs((startTime - endTime))/(1000*60*60*24);   
	    if(dates>31){
	   	 	swal("付款时间间隔不能超过31天");
	   	 	return false;
	    }
		location.href="${ctx}/subDepositOrder/exportSubDepositOrder?search_productCode="+search_productCode+"&search_brandName="+search_brandName+"&search_status="+search_status+"&search_subDepositOrderCode="+search_subDepositOrderCode+"&payDateBegin="+payDateBegin+"&payDateEnd="+payDateEnd;
	});
});

function viewSubOrder(id){
		$.ajax({
			url: "${ctx}/subOrder/subOrderView?id="+id, 
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
