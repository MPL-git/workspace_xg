<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>扣款记录（详情）</title>
</head>
<body>

<div class="modal-dialog wg-xx" role="document" style="width:1000px;">
	<div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">扣款记录（详情）</span>
      </div>
     <div class="modal-body">
		<div class="panel-default">
    		商家序号：${mchtInfo.mchtCode}&nbsp;&nbsp;&nbsp;
    		商家：${mchtInfo.companyName}&nbsp;&nbsp;&nbsp;
    		<span class="btn btn-danger">${activityName}</span><br>
    		<c:if test="${not empty totalDeductions}">
    			<strong>总扣款：<em>${totalDeductions}</em>元</strong>
			</c:if>
			<c:if test="${empty totalDeductions}">
    			<strong>总扣款：<em>0</em>元</strong>
			</c:if>
		</div>

		<div>
			<div>
					<form id="searchFormView">
						<input type="hidden" id="activityId" name="activityId" value="${activityId}">
					</form>
			</div>

			<div class="clearfix"></div>

			<div class="container-fluid">
				<div class="row">
					<div class="row">
						<table id="datatableView"
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
	
<!-- 	查看信息框 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script>
var tableView;

$(document).ready(function () {
	
	tableView = $('#datatableView').dataTable({
        "ajax": function (data, callback, settings) {
            var param = $("#searchFormView").serializeArray();
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            $.ajax({
                method: 'POST',
                url: '${ctx}/violateOrder/getDebitRecordViewData',
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
        "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
        "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
        "processing": true,  // 隐藏加载提示,自行处理
        "serverSide": true,   // 启用服务器端分页
        "searching": false,   // 禁用原生搜索
        "bSort": false,
        "bServerSide": true,
        "columns": [
				{"data": "id","title":"违规行为","render": function (data, type, row, meta) {
					return row.violateTypeDesc;
				}},
				{"data": "id","title":"涉及订单","render": function (data, type, row, meta) {
				    return "<a href='javascript:;' onclick='viewSubOrder("+row.subOrderId+")'>"+row.subOrderCode+"</a>";
				}},
				{"data": "id","title":"违规编号","render": function (data, type, row, meta) {
				    return "<a href='javascript:;' onclick='viewViolateOrder("+row.id+")'>"+row.orderCode+"</a>";
				}},
				{"data": "id","title":"违规时间","render": function (data, type, row, meta) {
				    return new Date(row.violateDate).format("yyyy-MM-dd hh:mm:ss");
				}},
				{"data": "id","title":"扣款时间","render": function (data, type, row, meta) {
				    return new Date(row.auditDate).format("yyyy-MM-dd hh:mm:ss");
				}},
				{"data": "id","title":"扣款额度","render": function (data, type, row, meta) {
					if(row.money){
					    return row.money+"元";
					}else{
						return "0元";
					}
				}}
        ]
    }).api();

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
