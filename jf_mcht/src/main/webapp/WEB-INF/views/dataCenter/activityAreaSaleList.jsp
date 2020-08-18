<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>每天销售数据</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">品牌特卖销售数据</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">日期：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" type="text"  id="dateBegin" name="dateBegin" data-date-format="yyyy-mm-dd" value="${dateBegin}">
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="dateEnd" name="dateEnd" data-date-format="yyyy-mm-dd" value="${dateEnd}">
					</div>
				   
					<div class="col-md-3 text-center search-btn">
						             <button type="button"  class="btn btn-info" id="btn-query">查询</button>
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
	
	
<!-- 	查看信息框 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script>
var table;
//计算天数差的函数，通用  
function  DateDiff(Date1,  Date2){
    iDays  =  parseInt(Math.abs(new Date(Date1)-new Date(Date2))/1000/60/60/24);    //把相差的毫秒数转换为天数  
    return  iDays;  
}

$(document).ready(function () {
	
	$('.datePicker').datetimepicker(
			{
				minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
				language: 'zh-CN', //汉化
				autoclose:true //选择日期后自动关闭
			}
	);
	
    table = $('#datatable').dataTable({
        "ajax": function (data, callback, settings) {
            var param = $("#searchForm").serializeArray();
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/dataCenter/getActivityAreaSaleData',
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
                
                $(".datatable-container").find("div[class='row']").last().hide();
            });
        },
        

        "language": {"url": '${ctx}/static/cn.json'},
        "autoWidth": true,   // 禁用自动调整列宽
        "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
        "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
        "processing": true,  // 隐藏加载提示,自行处理
        "serverSide": false,   // 启用服务器端分页
        "searching": false,   // 禁用原生搜索
        "bSort": false,
        "bServerSide": true,
        "paging": false,
        "columns": [
            {"data": "id","title":"特卖ID","render": function (data, type, row, meta) {
            	return row.activity_id;
            }},
            {"data": "id","title":"活动名称/利益点","render": function (data, type, row, meta) {
                return row.name+'<br><p class="ellipsis f55" style="margin: 0;">'+row.benefit_point+'</p>';
            }},
            {"data": "id","title":"商品数","render": function (data, type, row, meta) {
			    return row.product_count;
            }},
            {"data": "id","title":"开始时间","render": function (data, type, row, meta) {
			    return new Date(row.activity_begin_time).format("yyyy-MM-dd hh:mm:ss");
            }},
            {"data": "id","title":"结束时间","render": function (data, type, row, meta) {
            	return new Date(row.activity_end_time).format("yyyy-MM-dd hh:mm:ss");
            }},
            {"data": "id","title":"销售数量","render": function (data, type, row, meta) {
	            return row.sale_quantity;
            }},
            {"data": "id","title":"商品金额","render": function (data, type, row, meta) {
            	return row.product_amount;
            }},
            {"data": "id","title":"商家优惠","render": function (data, type, row, meta) {
            	return row.mcht_preferential;
            }},
            {"data": "id","title":"平台优惠","render": function (data, type, row, meta) {
            	return row.platform_preferential;
            }},
            {"data": "id","title":"实付金额","render": function (data, type, row, meta) {
            	return row.pay_amount;
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
    	var dateBegin = $("#dateBegin").val();
    	var dateEnd = $("#dateEnd").val();
    	if(dateBegin && dateEnd){
		    table.ajax.reload();
    	}else{
    		swal('日期不能为空');
    	}
    });
    
});
</script>
</body>
</html>
