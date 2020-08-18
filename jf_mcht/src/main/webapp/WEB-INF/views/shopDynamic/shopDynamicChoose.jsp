<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>动态管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
<style type="text/css">
.x_content {
	float: inherit;
}
</style>
</head>

<body>
<div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
    	<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><a href='javascript:void(0);'>&times;</a></span></button>
	        <span class="modal-title" id="exampleModalLabel">添加商品列表</span>
        </div>
		<div class="modal-body">
			<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" id="ids" value="${ids}">
				<div class="form-group">
				  	
				  	<label for="productBrand" class="col-md-1 control-label search-lable">商品名称：</label>
				  	<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text" id="search_name" name="search_name" >
					</div>
				  	
				  	<label for="productBrand" class="col-md-1 control-label search-lable">商品货号：</label>
				  	<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text" id="search_number" name="search_number" >
					</div>
				  	
				  	<label for="productBrand" class="col-md-1 control-label search-lable">商品ID：</label>				  	
				  	<div class="col-md-2 search-condition" >
						 <input class="form-control" type="text" id="search_id" name="search_id" >
					</div>
				  	
					
					<div class="col-md-3 text-center search-btn">
						 <button type="button"  class="btn btn-info" id="btn-query" >搜索</button>
					</div>
					
				</div>
				
			</form>
			
			<font color="red">当前已绑定的商品数:<span id="idsum"></span></font>
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
    </div>
		
</div>
	
	
<!-- 	查看信息框 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
	
	<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>
var table;


$(document).ready(function () {
	
	$("#idsum").html(arrayId.length);
	
	$('.datePicker').datetimepicker(
			{
				minView: "month", //选择日期后，不会再跳转去选择时分秒
				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
				language: 'zh-CN', //汉化
				autoclose:true //选择日期后自动关闭
			}
	);
	
	var table1 = $('#datatable').dataTable({
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
                url: '${ctx}/shopDynamic/getProductList',
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
			{"data": "id",width:"240","title":"商品信息","render": function (data, type, row, meta) {
		var html = [];
		html.push('<div class="is-check">');
		if(row.pic!=null){
	    	if(row.pic.indexOf("http") >= 0){//网络图片
	    		html.push('<div class="width-60"><img src='+row.pic+'></div>');
	    	}else{
	    		html.push('<div class="width-60"><img src="${ctx}/file_servelt'+row.pic+'"></div>');
	    	}
		}
		
		html.push('<div class="width-226"><p class="h34">'+row.name+'</p><div><span style="float: left; margin: 0;">货号：'+row.artNo+'</span></div><br><div><span style="float: left; margin: 0;">ID：'+row.code+'</span><a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id='+row.code+'" target="_blank">预览</a></div></div>');
		html.push("<div>");
		return html.join("");
	}},
	
	{"data": "id",width:"70","title":"商城价",sClass:"hiddenCol","render": function (data, type, row, meta) {
		var html = [];
		
		html.push(row.mallPriceMin);
		
		if(row.mallPriceMin!=row.mallPriceMax){
			html.push("-");
			html.push(row.mallPriceMax);
		};
	    return html.join("");
	}},
	{"data": "id",width:"70","title":"活动价","render": function (data, type, row, meta) {
		var html = [];
		
		html.push(row.salePriceMin);
		
		if(row.salePriceMin!=row.salePriceMax){
			html.push("-");
			html.push(row.salePriceMax);
		};
	    return html.join("");
	}},
	{"data": "id",width:"70","title":"库存","render": function (data, type, row, meta) {

			 return row.stock;

	}},
	
	{"data": "id",width:"150","title":"状态","render": function (data, type, row, meta) {
		var html = [];
		if(row.saleType=='1'){
			/* html.push("品牌款<br>"); */
	  	   	if(row.status=='0'){
	       	   	html.push("下架");
	   	   	}else{
	   	   		html.push("上架");
	   	   	}
		}
		/* if(row.saleType=='2'){
			html.push("单品款<br>");	
			if(row.status=='0'){
	       	   	html.push("下架<br>");
	   	   	}else{
	   	   		html.push("上架");
	   	   	}
		} */		
	
		return html.join("");
	}},
	{"data": "id",width:"100","title":"操作","render": function (data, type, row, meta) {
		var idsStr = "${ids}";
		var index = $.inArray(row.id+"", idsStr.split(","));		
		if(index>=0){
	        return  "已添加";
		}else{
	        return  "<a href='javascript:;' id='check"+row.id+"' name='opName' class='check"+row.id+"' onclick='saveId("+row.id+")'>添加</a>";			
		}
    }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {   	
        table1.ajax.reload();
    });
});





function addDynamic(saleType){
		/* $.ajax({
	        url: "${ctx}/shopDynamic/shopDynamicAdd", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		}); */
	getContent("${ctx}/shopDynamic/shopDynamicAdd");

}

function del(id){
	swal({
		  title: "确定要删除此动态?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: false
		},
		function(){
			delDynamic(id);
		});
}
function delDynamic(id){
	$.ajax({
		url : "${ctx}/shopDynamic/deleteDynamic",
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
