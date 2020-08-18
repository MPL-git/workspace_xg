<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>我的违规</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">我的违规</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
				  	<label for="productBrand" class="col-md-1 control-label search-lable">违规时间：</label>
					<div class="col-md-5 search-condition">
						 <input class="form-control datePicker" type="text"  id="violateDateBegin" name="violateDateBegin" data-date-format="yyyy-mm-dd">
						 <i class="picker-split">-</i>
						 <input class="form-control datePicker" type="text"  id="violateDateEnd" name="violateDateEnd" data-date-format="yyyy-mm-dd">
					</div>
				  	
				  	<label for="productBrand" class="col-md-1 control-label search-lable">状态：</label>
					<div class="col-md-2 search-condition">
						 <select class="form-control" name="search_status" id="search_status">
						   <option value="">--请选择--</option>
						   <c:forEach var="violateOrderStatus" items="${violateOrderStatusList }">
						   		<option value="${violateOrderStatus.statusValue }">${violateOrderStatus.statusDesc }</option>
						   </c:forEach>
						   <c:forEach var="againAuditStatus" items="${againAuditStatusList }">
						   		<option value="999${againAuditStatus.statusValue }">${againAuditStatus.statusDesc }</option>
						   </c:forEach>
                         </select>
					</div>
				  	
				  	<label for="productBrand" class="col-md-1 control-label search-lable">违规单号：</label>
				  	<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text" id="search_orderCode" name="search_orderCode" >
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
            
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/violateOrder/getViolateOrderList',
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
			{"data": "id","title":"违规时间","render": function (data, type, row, meta) {
				var violateDate = new Date(row.violateDate);
			    return violateDate.format("yyyy-MM-dd hh:mm:ss");
			}},
			{"data": "id","title":"违规单号","render": function (data, type, row, meta) {
			    return '<a href="javascript:;" onclick="viewViolateOrder('+row.id+');">'+row.orderCode+'</a>';
			}},
            {"data": "id","title":"违规行为","render": function (data, type, row, meta) {
                return "【"+row.violateTypeDesc+"】"+row.violateActionDesc;
            }},
            {"data": "id","title":"处罚金额","render": function (data, type, row, meta) {
            	if(row.money){
	            	return row.money+"元";
            	}else{
            		return "0元";
            	}
            }},
            {"data": "id","title":"状态","render": function (data, type, row, meta) {
            	if(row.againAuditStatus){
					if(row.againAuditStatus == "0" || row.againAuditStatus == "1"){
		            	return row.statusDesc;
					}else if(row.againAuditStatus == "2"){//复审失败
						if(row.status == "6"){//6.申诉成功
							return "复审失败";
						}else{
							return row.statusDesc;
						}
					}else if(row.againAuditStatus == "3"){//线下申诉成功
						if(row.status == "4" || row.status == "5"){//4.超期未申诉 5.申诉失败
							return "线下申诉成功";
						}else{
							return row.statusDesc;
						}
					}            		
            	}else{
	            	return row.statusDesc;
            	}
            }},
            {"data": "id","title":"操作","render": function (data, type, row, meta) {
	            return  "<a href='javascript:;' onclick='viewViolateOrder("+row.id+")'>查看</a>";
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
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
