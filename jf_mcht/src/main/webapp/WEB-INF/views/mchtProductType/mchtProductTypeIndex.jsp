<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>品牌库</title>
<style type="text/css">
.dataTables_info{
display: none;
}
</style>
</head>

<body>
	<div class="x_panel container-fluid">

		<div class="row content-title">
			<div class="t-title">类目管理
				<!-- <a href='javascript:void(0);' onclick="addMchtProductType();">添加</a>-->
			</div>
		</div>
		<br>
		<br>
		<div class="clearfix"></div>
		<div class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>一级类目</th>
									<th>二级类目</th>
									<th>三级类目</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
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
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<!-- Bootstrap -->

	<script>
	
	
	function delMchtProductType(id){
		swal({
			  title: "确定要删除此信息?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: false
			},
			function(){
				delConfirm(id);
			});
	}

	function delConfirm(id){
		$.ajax({
			url : "${ctx}/mchtProductType/delMchtProductType?id="+id,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {id:id},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					swal.close();
					table.ajax.reload();
				} else {
					swal({
						  title: data.returnMsg,
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
				
			},
			error : function() {
				swal({
					  title: "处理失败！",
					  type: "error",
					  timer: 1500,
					  confirmButtonText: "确定"
					});
			}
		});

	}
	
	
	
	
	
	
	function addMchtProductType(){
		$.ajax({
	        url: "${ctx}/mchtProductType/addMchtProductType", 
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
	
var table;

$(document).ready(function () {
	
    
	
    
    table = $('#datatable').dataTable({
        "ajax": function (data, callback, settings) {
            var param = $("#searchForm").serializeArray();
            
//             param.push({"name":"pagesize","value":data.length});
//             if (data.start > 0) {
//                 param.push({"name":"page","value":data.start/data.length+1});
//             } else {
//                 param.push({"name":"page","value":1});
//             }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mchtProductType/getMchtProductTypeList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
//                     returnData.recordsTotal = result.returnData.Total;
//                     returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
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
        "searching": false,   // 禁用原生搜索
        "bSort": false,
        "bServerSide": true,
        "bPaginate" : false,
        "paging": false, // 禁止分页
        "columns": [
            {"data": "id","render": function (data, type, row, meta) {
            	if(row.isMain == "0"){
	      			return row.productTypeName;
            	}else{
            		return row.productTypeName+"<span style='color:red;'>【主营】</span>";            		
            	}
            }},
            {"data": "id","render": function (data, type, row, meta) {
      			return "不限";
            }},
            {"data": "id","render": function (data, type, row, meta) {
      			return "不限";
            }},
            {"data": "statusDesc"},
            {"data": "status","render": function (data, type, row, meta) {
				if(data=='0'||data=='2'){
					return "<a class='table-opr-btn' href='javascript:void(0);' onclick='delMchtProductType("+row.id+")'>【删除】</a>";
				}else{
					return "";
				}
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
});
</script>
</body>
</html>
