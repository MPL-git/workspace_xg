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
			<div class="t-title">每天销售数据</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">商品ID：</label>
					<div class="col-md-3 search-condition" >
						<input class="form-control" type="text"  id="search_productCode" name="search_productCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">品牌：</label>
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
					<label for="productBrand" class="col-md-1 control-label search-lable">日期：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" type="text"  id="timeBegin" name="timeBegin" data-date-format="yyyy-mm-dd" value="${payDateBegin}">
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="timeEnd" name="timeEnd" data-date-format="yyyy-mm-dd" value="${payDateEnd}">
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
                url: '${ctx}/dataCenter/getEachDaySaleData',
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
            {"data": "id","title":"日期","render": function (data, type, row, meta) {
            	return row.eachDay;
            }},
            {"data": "id","title":"商品数","render": function (data, type, row, meta) {
                return row.productCount;
            }},
            {"data": "id","title":"商品金额","render": function (data, type, row, meta) {
			    return row.productAmount;
            }},
            {"data": "id","title":"商家优惠","render": function (data, type, row, meta) {
			    return row.mchtPreferential;
            }},
            {"data": "id","title":"平台优惠","render": function (data, type, row, meta) {
            	return row.platformPreferential;
            }},
            {"data": "id","title":"实收金额","render": function (data, type, row, meta) {
            	if(row.payAmount){
	            	return row.payAmount+"元";
            	}else{
            		return "0元";
            	}
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
    	var timeBegin = $("#timeBegin").val();
    	var timeEnd = $("#timeEnd").val();
    	if(timeBegin && timeEnd){
	    	if(DateDiff(timeBegin,timeEnd)>31){
	    		swal('日期跨度不能超过31天');
	    		return false;
	    	}else{
		        table.ajax.reload();
	    	}
    	}else{
    		swal('日期不能为空');
    	}
    });
    
});
function viewViolateOrder(id){
	$.ajax({
        url: "${ctx}/violateOrder/violateOrderView?id="+id, 
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
