<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>缴纳记录</title>
</head>

<body>
<div class="modal-dialog" role="document" style="width:1100px;">
	<div class="modal-content">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <span class="modal-title" id="exampleModalLabel">缴纳记录</span>
      	</div>
      	<div class="modal-body">
      		<div class="container-fluid">
				<div class="search-container container-fluid"
					style="padding:10px 10px;">
		
				</div>
				<div class="clearfix"></div>
				<div class="x_content container-fluid">
						<div class="row">
							<div class="col-md-12 datatable-container">
								<table id="depositOrderDatatable"
									class="table table-striped table-bordered dataTable no-footer"
									role="grid" aria-describedby="datatable_info">
								</table>
							</div>
						</div>
				</div>
			</div>
      	</div>
	</div>
</div>
	
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script>
var table;

$(document).ready(function () {
	
	$('.datePicker').datetimepicker(
    		{
    			minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
    	    }
    );
	
    table = $('#depositOrderDatatable').dataTable({
        "ajax": function (data, callback, settings) {
            var param = [];
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mcht/contract/getDepositOrderList',
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
            {"data": "id","title":"序号","render": function (data, type, row, meta) {
            	return meta.row+1;
            }},
            {"data": "id","title":"提交时间","render": function (data, type, row, meta) {
			    return new Date(row.createDate).format("yyyy-MM-dd hh:mm:ss");
            }},
            {"data": "id","title":"缴纳金额（元）","render": function (data, type, row, meta) {
			    return row.amount;
            }},
            {"data": "id","title":"您支付账户户名","render": function (data, type, row, meta) {
			    return row.accountName;
            }},
            {"data": "id","title":"您的支付开户银行","render": function (data, type, row, meta) {
			    return row.paymentName;
            }},
            {"data": "id","title":"您的支付开户银行账号","render": function (data, type, row, meta) {
			    return row.accountNo;
            }},
            {"data": "id","title":"交易流水号","render": function (data, type, row, meta) {
			    return row.paymentNo;
            }},
            {"data": "id","title":"打款时间","render": function (data, type, row, meta) {
			    return new Date(row.payDate).format("yyyy-MM-dd");
            }},
            {"data": "id","title":"备注","render": function (data, type, row, meta) {
	            return  row.remarks;
            }}
        ]
    }).api();

});
</script>
</body>
</html>
