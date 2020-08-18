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
	<div class="x_panel container-fluid pp-gl">
		<div class="row content-title">
			<div class="t-title">税务信息申请更新记录
			<c:if test="${applyCount<=0}">
			<a id="infoChgApply" href='javascript:void(0);' onclick="addMchtTaxInvoiceChg();" >申请更新</a>
			</c:if>
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
									<th>创建日期</th>
									<th>纳税类型</th>
									<th>银行帐号</th>
									<th>状态</th>
									<th>通过日期</th>
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
	
function viewMchtBrand(id){
	
	$.ajax({
        url: "${ctx}/mcht/mchtBrandView?mchtBrandId="+id, 
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


function addMchtTaxInvoiceChg(){
	
		$.ajax({
	        url: "${ctx}/mchtTaxInvoice/addMchtTaxInvoiceChg", 
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
function mchtTaxInvoiceChgEdit(id){
	
		$.ajax({
	        url: "${ctx}/mchtTaxInvoice/mchtTaxInvoiceChgEdit?id="+id, 
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
            
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/mchtTaxInvoice/mchtTaxInvoiceChgList',
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
            {"data": "id","render": function (data, type, row, meta) {
             	if(row.createDate){
	            	return new Date(row.createDate).format("yyyy-MM-dd");
            	}else{
            		return "";
            	}
            }},  
            {"data": "taxTypeDesc"},
            {"data": "taxNumber"},
   
            {"data": "auditStatusDesc"},
            {"data": "updateDate","render": function (data, type, row, meta) {
            	if(row.auditStatus=="3"){
	           	   var updateDate=new Date(data);
	         	   return updateDate.format("yyyy-MM-dd");
            	}else{
            	   return "";
            	}
             }},
            {"data": "id","render": function (data, type, row, meta) {
            	if(row.auditStatus=='0'||row.auditStatus=='4'){
            	 return "<a class='table-opr-btn' href='javascript:void(0);' onclick='mchtTaxInvoiceChgEdit("+row.id+")'>【修改】</a>"+"<a class='table-opr-btn' href='javascript:void(0);' onclick='mchtTaxInvoiceChgDel("+row.id+")'>【删除】</a>";	
            	}else{
                 return "<a class='table-opr-btn' href='javascript:void(0);' onclick='mchtTaxInvoiceChgEdit("+row.id+")'>查看</a>";
            	}            	
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
});


function mchtTaxInvoiceChgDel(id){
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
		url : "${ctx}/mchtTaxInvoice/delMchtTaxInvoiceChg?id="+id,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {id:id},
		timeout : 30000,
		success : function(data) {
			if (data.returnCode=="0000") {
				getContent("${ctx}/mchtTaxInvoice/mchtTaxInvoiceChgIndex");
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

</script>
</body>
</html>
