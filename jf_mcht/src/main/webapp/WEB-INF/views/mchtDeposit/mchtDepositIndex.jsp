<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>保证金管理</title>
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">保证金管理</div>
		</div>
		<div class="search-container container-fluid bzj-gl">
				<div class="panel panel-default">
				    <div class="panel-body">
				    	<div class="info-row ye"><span>保证金余额&nbsp;：</span><strong>${mchtDeposit.payAmt}</strong>元</div>
				    	<c:if test="${mchtDeposit.unpayAmt gt 0}">
						    <div class="info-row"> <span>还需补缴&nbsp;：</span><span  class="info-cotent">${mchtDeposit.unpayAmt}元</span></div>
				    	</c:if>
					    <!-- <div class="info-row"> <button class="btn btn-info" onclick="addDepositOrder(${mchtDeposit.id})" >补缴</button></div> -->
					    <div class="info-row info-title"><span>提示：醒购平台不会以任何形式主动给您发送收款账号。平台有权从货款结算单扣减用于抵缴保证金。</span></div>
				    </div>
				</div>
			
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" id="depositId" name="depositId" value="${mchtDeposit.id}">
				<div class="form-group">
					<label for="" class="col-md-1 control-label search-lable">时间：</label>
					<div class="col-md-2 search-condition">
						 <input class="form-control datePicker" type="text"  id="createTimeBegin" name="createTimeBegin" data-date-format="yyyy-mm-dd">
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="createTimeEnd" name="createTimeEnd" data-date-format="yyyy-mm-dd">
					</div>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_txnType" id="search_txnType">
						   		<option value="">不限</option>
						   		<option value="B">现金缴纳</option>
						   		<option value="C">货款抵缴</option>
						   		<option value="D">退保证金</option>
						   		<option value="E">违规扣款</option>
						   		<option value="F">申诉成功</option>
						   		<option value="G">售后扣款</option>
                           </select>
					</div>
					<div class="col-md-3 text-center search-btn">
						<button type="button" class="btn btn-info" id="btn-query">搜索</button>
                        <button type="button" class="btn btn-info" id="btn-out">导出</button>
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
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
	
	<script>
var table;

$(document).ready(function () {
	
	$("#btn-out").on('click',function(){
		var depositId = $("#depositId").val();
		var search_bizType = $("#search_bizType").val();
		location.href="${ctx}/mchtDeposit/export?depositId="+depositId+"&search_bizType="+search_bizType;
	});
	
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
            
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mchtDeposit/getMchtDepositDtlList',
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
            {"data": "id","title":"变更时间","render": function (data, type, row, meta) {
            	return new Date(row.createDate).format("yyyy-MM-dd hh:mm:ss");
            }},
            {"data": "id","title":"变更明细","render": function (data, type, row, meta) {
			    return row.changeDetail;
            }},
            {"data": "id","title":"类型","render": function (data, type, row, meta) {
			    return row.bizTypeDesc;
            }},
            {"data": "id","title":"金额(元)","render": function (data, type, row, meta) {
            	if(row.txnType == "E"){
		            return "<span style='color:red;'>"+formatMoney(row.txnAmt,2)+"</span>";
            	}else{
            		
            	}
	            return "<span style='color:green;'>"+formatMoney(row.txnAmt,2)+"</span>";
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
});
function addDepositOrder(depositId){
	$.ajax({
        url: "${ctx}/mchtDeposit/addDepositOrder?depositId="+depositId, 
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
