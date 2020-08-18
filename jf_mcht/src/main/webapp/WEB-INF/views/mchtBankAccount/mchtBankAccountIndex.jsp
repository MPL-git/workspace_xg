<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>品牌管理</title>
</head>

<body>
	<div class="x_panel container-fluid">

		<div class="row content-title">
			<div class="t-title">银行账号管理</div>
		</div>
		<div class="search-container container-fluid yh-zh">
				<div class="form-group">
					<span class="col-md-12">银行账号用于平台与贵公司之间的资金往来收款付款。<a href="javascript:mchtBankAccountEdit('');">添加银行帐号</a></span>
				</div>

		</div>
		<div class="clearfix"></div>
		<div class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container at-table">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>账户名称</th>
									<th>银行名称</th>
									<th>开户支行名称</th>
									<th>账号</th>
									<th>是否默认</th>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->


	<script>
	
function mchtBankAccountEdit(id){
	
	$.ajax({
        url: "${ctx}/mchtBankAccount/mchtBankAccountEdit?id="+id, 
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
	
	function setDefault(id){
		
		
		swal({
			  title: "确定要此帐号设置为默认？",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: false
			},
			function(){
				$.ajax({
					url : "${ctx}/mchtBankAccount/setDefault",
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {id:id},
					timeout : 30000,
					success : function(data) {
						if (data.returnCode=="0000") {
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
							  title: "提交失败！",
							  type: "error",
							  confirmButtonText: "确定"
							});
					}
				});
			});
		
		
		
		

	}
	
	
var table;

$(document).ready(function () {
    
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
                url: '${ctx}/mchtBankAccount/getMchtBankAccountList',
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
            {"data": "accName"},
            {"data": "bankName"},
            {"data": "depositBank"},
            {"data": "accNumber"},
            {"data": "isDefault","render": function (data, type, row, meta) {
            	
            	if(data=="1"){
            		return "是";
            	}else{
            		return "<a class='table-opr-btn' href='javascript:void(0);' onclick='setDefault("+row.id+")'>否</a>";;
            	}
            }},
       		{"data": "id","render": function (data, type, row, meta) {
       			return "<a class='table-opr-btn' href='javascript:void(0);' onclick='mchtBankAccountEdit("+row.id+")' >详情</a>";
            }}
        ]
    }).api();
    
});
</script>
</body>
</html>
