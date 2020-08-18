<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>投诉管理</title>
<style type="text/css">


</style>
</head>

<body>
	<div class="x_panel container-fluid py-tm">

		<div class="row content-title">
			<div class="t-title">我收到的投诉</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" name="search_remarksColor" id="search_remarksColor" >
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">投诉编号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_orderCode" name="search_orderCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">订单号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text"  id="search_subOrderCode" name="search_subOrderCode" >
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">投诉类型：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_appealType" id="search_appealType">
						   <option value="">--请选择--</option>
						   <c:forEach var="appealOrderAppealType" items="${appealOrderAppealTypeList}">
						   		<option value="${appealOrderAppealType.statusValue}">${appealOrderAppealType.statusDesc}</option>
						   </c:forEach>
                          </select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable mwa">状态：</label>
					<div class="col-md-2 search-condition">
						  <select class="form-control" name="search_status" id="search_status">
						   <option value="-1" <c:if test="${status == '-1'}">selected="selected"</c:if>>不限状态</option>
						   <c:forEach var="appealOrderStatus" items="${appealOrderStatusList}">
						   		<option value="${appealOrderStatus.statusValue}">${appealOrderStatus.statusDesc}</option>
						   </c:forEach>
                          </select>
					</div>
					
					<label for="productBrand" class="col-md-1 control-label search-lable">投诉时间：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" type="text"  id="createTimeBegin" name="createTimeBegin" data-date-format="yyyy-mm-dd">
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="createTimeEnd" name="createTimeEnd" data-date-format="yyyy-mm-dd">
					</div>
					<div class="col-md-3 text-center search-btn">
						             <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
					</div>
				</div>
			</form>
		</div>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs q" role="tablist">
    <li role="presentation" <c:if test="${status=='-1'}">class="active"</c:if>><a href="#"  role="tab" data-toggle="tab" onclick="list('-1');">全部</a></li>
    <li role="presentation" <c:if test="${status=='2'}">class="active"</c:if>><a href="#" role="tab" data-toggle="tab" onclick="list('2');">待处理</a></li>
  </ul>

<!--   Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane row active" id="contact_1">
    				<div class="col-md-12 datatable-container">
						<table id="dataTableId" class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							
						</table>
					</div>
	</div>
  </div>

</div>
		
	</div>
	
	
<!-- 	查看信息框 -->

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
	
	table =	$('#dataTableId').dataTable({
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
                url: '${ctx}/appealOrder/getAppealOrderList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
                    
                    $("#dataTableId").find("tbody").find("tr").each(function(){
                    	var $this = $(this);
                    	if($this.find("td").eq(3).text()=="待商家回复"){
                    		var updateDate = $this.find("td").eq(6).text();
                			var endDate = new Date(updateDate.replace(/-/g,"/"));
                			var endTime = endDate.getTime()+2*24*3600*1000;
                			leftTimer($this.find("td").eq(8),endTime);
                    	}
                    });
                    
                }
            });
        },
        "language": {"url": '${ctx}/static/cn.json'},
        "bAutoWidth":false,
        "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
        "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
        "processing": true,  // 隐藏加载提示,自行处理
        "serverSide": true,   // 启用服务器端分页
        "searching": false,   // 禁用原生搜索
        "bSort": false,
        "bServerSide": true,
        "columns": [
            {"data": "id","title":"投诉编号","width":"15%","render": function (data, type, row, meta) {
            	return row.orderCode;
            }},
            {"data": "id","title":"订单号","width":"15%","render": function (data, type, row, meta) {
            	return row.subOrderCode;
            }},
            {"data": "id","title":"投诉类型","width":"11%","render": function (data, type, row, meta) {
            	return row.appealTypeDesc;
            }},
            {"data": "id","title":"状态","width":"10%","render": function (data, type, row, meta) {
            	return row.statusDesc;
            }},
            {"data": "id","title":"责任方","width":"6%","render": function (data, type, row, meta) {
            	return row.liabilityDesc;
            }},
            {"data": "id","title":"投诉时间","width":"10%","render": function (data, type, row, meta) {
            	return new Date(row.createDate).format("yyyy-MM-dd hh:mm:ss");
            }},
            {"data": "id","title":"最后更新时间","width":"10%","render": function (data, type, row, meta) {
            	if(row.updateDate){
	            	return new Date(row.updateDate).format("yyyy-MM-dd hh:mm:ss");
            	}else{
            		return "";
            	}
            }},
            {"data": "id","title":"投诉人","width":"6%","render": function (data, type, row, meta) {
            	return row.userTypeDesc;
            }},
            {"data": "id","title":"剩余处理时间","width":"11%","render": function (data, type, row, meta) {
            	return "";
            }},
            {"data": "id","title":"操作","width":"6%","render": function (data, type, row, meta) {
            	return "<a class='table-opr-btn' href='javascript:void(0);' onclick='viewAppealOrder("+row.id+")'>查看</a>";
            }}
        ]
    }).api();	
	
	$('#btn-query').on('click', function (event) {
	    table.ajax.reload();
	});
});

function leftTimer(object,endTime){
	 var nowDate = new Date();
	 var nowTime = nowDate.getTime();
	 if(endTime > nowTime){
		var leftTime = endTime - nowTime;//计算剩余的毫秒数 
		var days = parseInt(leftTime / 1000 / 60 / 60 / 24 , 10); //计算剩余的天数 
		var hours = parseInt(leftTime / 1000 / 60 / 60 % 24 , 10); //计算剩余的小时 
		var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟 
		days = checkTime(days); 
		hours = checkTime(hours); 
		minutes = checkTime(minutes);
		setTimeout(function() { 
			leftTimer(object,endTime); 
		},1000)
	    object.html('<span style="color: red;">'+days+'</span> 天 <span style="color: red;">'+hours+'</span> 时<span style="color: red;">'+minutes+'</span>分钟<br>');
	 }else{
		object.html('<span style="color: red;">已超时</span>');
	 }
}

function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
	 if(i<10){ 
	  	i = "0" + i;
	  } 
	 	return i; 
}

function viewAppealOrder(id){
		$.ajax({
	        url: "${ctx}/appealOrder/appealOrderView?id="+id, 
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

function list(status) {
	var url = "${ctx}/appealOrder/appealOrderIndex?status=" + status;
	getContent(url);
}

</script>
</body>
</html>
