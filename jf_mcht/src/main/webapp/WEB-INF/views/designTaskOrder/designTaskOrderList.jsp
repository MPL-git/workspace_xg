<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>设计任务付费</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">
				设计任务付费
				<a href='javascript:void(0);' onclick="addDesignTaskOrder();">发布任务</a>
			</div>
		</div>
		<div class="search-container container-fluid">
			<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label class="col-md-1 control-label search-lable">付款状态：</label>
					<div class="col-md-2 search-condition">
						<select class="form-control" name="payStatus" id="payStatus">
							<option value="">--请选择--</option>
							<c:forEach var="payStatus" items="${payStatusList }">
								<option value="${payStatus.statusValue }">${payStatus.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-md-1 control-label search-lable">类型：</label>
					<div class="col-md-2 search-condition">
						<select class="form-control" name="taskType" id="taskType">
							<option value="">--请选择--</option>
							<c:forEach var="taskType" items="${taskTypeList }">
								<option value="${taskType.statusValue }">${taskType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				  	<label class="col-md-1 control-label search-lable">订单编号：</label>
				  	<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text" id="orderCode" name="orderCode" />
					</div>
					<label class="col-md-1 control-label search-lable">任务名称：</label>
				  	<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text" id="taskName" name="taskName" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label search-lable">设计进度：</label>
					<div class="col-md-2 search-condition">
						<select class="form-control" name="designStatus" id="designStatus">
							<option value="">--请选择--</option>
							<c:forEach var="designStatus" items="${designStatusList }">
								<option value="${designStatus.statusValue }">${designStatus.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
					<label class="col-md-1 control-label search-lable"></label>
					<div class="col-md-2 search-condition">
						<label class="col-md-1 control-label search-lable">
							<input type="checkbox" id="status" name="status" value="1" />显示已取消任务
						</label>
					</div>
					<div class="col-md-3 text-center search-btn">
						<button type="button"  class="btn btn-info" id="btn-query">搜索</button>
					</div>
				</div>
				
			</form>
		</div>
		<div class="clearfix"></div>
		<div class="x_content container-fluid">
			<div class="row">
				<div class="col-md-12 datatable-container at-table">
					<table id="datatable"
						class="table table-striped table-bordered dataTable no-footer"
						role="grid" aria-describedby="datatable_info">

					</table>
				</div>
			</div>
		</div>
	</div>

<!-- 查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>

<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>

<script>

	var table;

	$(function () {
		initDatePicker();
		initTable();
		initBindEvents();
	});

	function initBindEvents() {
		$('#btn-query').on('click', function () {
			table.ajax.reload();
		});
	}

	function initDatePicker() {
		$('.datePicker').datetimepicker(
			{
				minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
				language: 'zh-CN', //汉化
				autoclose: true //选择日期后自动关闭
			}
		);
	}

	//列表初始化
	function initTable() {
		table = $('#datatable').dataTable({
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
					url: '${ctx}/designTaskOrder/getDesignTaskOrderListList',
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
			"autoWidth": true,   // 禁用自动调整列宽
			"stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
			"order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
			"processing": true,  // 隐藏加载提示,自行处理
			"serverSide": true,   // 启用服务器端分页
			"searching": false,   // 禁用原生搜索
			"bSort": false,
			"bServerSide": true,
			"columns": [
				{"data": "id","title":"订单编号","render": function (data, type, row, meta) {
						return row.orderCode;
					}},
				{"data": "id","title":"类型","render": function (data, type, row, meta) {
						return row.taskTypeDesc;
					}},
				{"data": "id","title":"任务名称","render": function (data, type, row, meta) {
						return row.taskName;
					}},
				{"data": "id","title":"金额（元）","render": function (data, type, row, meta) {
						return row.payAmount;
					}},
				{"data": "id","title":"付款状态","render": function (data, type, row, meta) {
						if(row.designTaskRefundOrderStatus != null) {
							if(row.designTaskRefundOrderStatus == '2') {
								return "退款成功";
							}else if(row.designTaskRefundOrderStatus == '3') {
								return "退款失败";
							}else {
								return "退款中";
							}
						}else {
							return row.payStatusDesc;
						}
					}},
				{"data": "id","title":"设计进度","render": function (data, type, row, meta) {
						return row.designStatusDesc;
					}},
				{"data": "id","title":"取消状态","render": function (data, type, row, meta) {
						return row.statusDesc;
					}},
				{"data": "id","title":"操作","render": function (data, type, row, meta) {
						var html = [];
						if(row.payStatus == '0' && row.status == '0') {
							html.push("<a href='javascript:;' onclick='updateDesignTaskOrder("+row.id+")'>修改</a>");
							html.push("<a href='javascript:;' onclick='payDesignTaskOrder("+row.id+")'>去付款</a>");
							if(row.status == '0') {
								html.push("<a href='javascript:;' onclick='cancelDesignTaskOrder("+row.id+")'>取消任务</a>");
							}
						}else {
							html.push("<a href='javascript:;' onclick='viewDesignTaskOrder("+row.id+")'>查看</a>");
						}
						return html.join("<br/>");
					}}
			]
		}).api();
	}

	function cancelDesignTaskOrder(designTaskOrderId) {
		swal({
			title: "是否要取消任务？",
			type: "warning",
			showCancelButton: true,
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			closeOnConfirm: true
		},
		function(){
			$.ajax({
				url: '${ctx}/designTaskOrder/cancelDesignTaskOrder',
				type: 'POST',
				dataType: 'json',
				cache: false,
				async: false,
				data: {designTaskOrderId : designTaskOrderId},
				timeout: 30000,
				success: function (result) {
					if (result.success) {
						table.ajax.reload(null, false);
					} else {
						swal({
							title: result.returnMsg,
							type: "error",
							confirmButtonText: "确定"
						});
					}
				},
				error: function () {
					swal({
						title: "取消任务！",
						type: "error",
						confirmButtonText: "确定"
					});
				}
			});
		});
	}

	function addDesignTaskOrder(){
		getContent("${ctx}/designTaskOrder/editDesignTaskOrderManager");
	}

	function updateDesignTaskOrder(designTaskOrderId) {
		getContent("${ctx}/designTaskOrder/editDesignTaskOrderManager?designTaskOrderId="+designTaskOrderId);
	}

	function viewDesignTaskOrder(designTaskOrderId) {
		getContent("${ctx}/designTaskOrder/viewDesignTaskOrderManager?designTaskOrderId="+designTaskOrderId);
	}

	function payDesignTaskOrder(designTaskOrderId) {
		getContent("${ctx}/designTaskOrder/payDesignTaskOrderManager?designTaskOrderId="+designTaskOrderId);
	}

</script>
</body>
</html>
