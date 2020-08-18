<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>品牌库</title>
</head>

<body>
	<div class="x_panel container-fluid">

		<div class="row content-title">
			<div class="col-md-12 ">品牌库</div>
		</div>
		
		
		<div class="search-container container-fluid"
			style="padding:10px 10px;">
			<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<span class="col-md-12">醒购网已与众多品牌商达成战略合作，如果没找到您的品牌，您还可以申请<a href="javascript:applyNewBrand();">添加品牌</a>，查看<a href="javascript:getContent('${ctx }/productBrand/productBrandTmpIndex')">已添加品牌</a></span>
				</div>
				<div class="form-group">
					<label for="productType" class="col-md-1 control-label search-lable">品类</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="productType" id="productType">
						   <option value="">--请选择--</option>
						   <c:forEach var="productType" items="${productTypes }">
						   <option value="${productType.id}">${productType.name}</option>
						   </c:forEach>
                          </select>
					</div>
					
					<label for="tmkType" class="col-md-1 control-label search-lable">类别</label>
					<div class="col-md-2 search-condition">
							 <select class="form-control" name="tmkType" id="tmkType">
						   <option value="25">第25类</option>
						   <option value="14">第14类</option>
						   <option value="18">第18类</option>
						   <option value="24">第24类</option>
						   <option value="">--请选择--</option>
                          </select>
					</div>
					
					<label for="searchKeyWord" class="col-md-1 control-label search-lable">关键词</label>
					<div class="col-md-2 search-condition">
						<input type="text" class="form-control" id="searchKeyWord" name="searchKeyWord"
					>
					</div>
					<div class="col-md-3 text-center search-btn">
						             <button type="button"  class="btn btn-info" id="btn-query">
                                    <i class="glyphicon glyphicon-search"></i>查询</button>
					</div>
					
				</div>
				
				
				
				
				
			</form>

		</div>
		<div class="clearfix"></div>
		<div class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container">
						<table id="datatable"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
							<thead>
								<tr role="row">
									<th>品牌LOGO</th>
									<th>品牌</th>
									<th>中文</th>
									<th>英文</th>
									<th>品类</th>
									<th>商标类别</th>
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
	function applyNewBrand(){
		$.ajax({
	        url: "${ctx }/productBrand/productBrandTmpIndex", 
	        type: "GET", 
	        success: function(data){
	            $("#main-content").html(data);
	        	$.ajax({
	                url: "${ctx}/productBrand/productBrandTmpEdit", 
	                type: "GET", 
	                success: function(data){
	                    $("#viewModal").html(data);
	                    $("#viewModal").modal();
	                },
	        	    error:function(){
	        	    	swal('页面不存在');
	        	    }
	        	});
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
                url: '${ctx}/productBrand/getProductBrandList',
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
            {"data": "logo","render": function (data, type, row, meta) {
                return "<img style='width:80px;height:40px;' src='${ctx}/file_servelt"+data+"'>";
            }},
            {"data": "name"},
            {"data": "nameZh"},
            {"data": "nameEn"},
            {"data": "productTypes","render": function (data, type, row, meta) {
            	var html = [];
		    	for (var i=0;i<data.length;i++)
		    	{
		    		html.push(data[i].name+"<br>");
		    	}
			    
			    return html.join("");
            }},
            {"data": "tmkTypeGroup"}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
});
</script>
</body>
</html>
