<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>商品回收站</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />

<style type="text/css">
.color-1{
color: #9D999D;
}
.color-2{
color: #FC0000;
}
.color-3{
color: #EFD104;
}
.color-4{
color: #00FC28;
}
.color-5{
color: #0351F7;
}
.color-6{
color: #DF00FB;
}
</style>
</head>

<body>
	<div class="x_panel container-fluid">

		<div class="row content-title">
			<div class="col-md-12 ">商品回收站</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">品牌名称：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="brandId" id="brandId">
						   <option value="">--请选择--</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
						   <option value="${productBrand.id}">${productBrand.name}</option>
						   </c:forEach>
                          </select>
					</div>
					<label for="productType" class="col-md-1 control-label search-lable">分类：</label>
					<div class="col-md-2 search-condition">
						   <select onchange="onchangeProductType1();" class="form-control productType1-select" name="productType1" id="productType1-select">
                          </select>
					</div>
					<div class="col-md-2 search-condition">
						   <select onchange="onchangeProductType2();" class="form-control productType2-select" name="productType2" id="productType2-select">
                          </select>
					</div>
					<div class="col-md-2 search-condition">
						   <select class="form-control productType3-select" name="productType3" id="productType3-select">
                          </select>
					</div>
				
					
				</div>
				
<!-- 				<div class="form-group"> -->


<!-- 					<label for="productBrand" class="col-md-1 control-label search-lable">添加时间</label> -->
<!-- 					<div class="col-md-2 search-condition" style="width:13.583325%"> -->
<!-- 						 <input class="form-control datePicker" type="text"  id="createTimeBegin" name="createTimeBegin" data-date-format="yyyy-mm-dd"> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-1 search-condition text-center"  style="width:2%;padding:5px 0;" > -->
<!-- 						<span>--</span>  -->
<!-- 					</div> -->
<!-- 					<div class="col-md-2 search-condition" style="width:13.583325%"> -->
<!-- 						 <input class="form-control datePicker" type="text"  id="createTimeEnd" name="createTimeEnd" data-date-format="yyyy-mm-dd"> -->
<!-- 					</div> -->


<!-- 					<label for="productBrand" class="col-md-1 control-label search-lable">删除时间</label> -->
<!-- 					<div class="col-md-2 search-condition" style="width:13.583325%"> -->
<!-- 						 <input class="form-control datePicker" type="text"  id="delTimeBegin" name="delTimeBegin" data-date-format="yyyy-mm-dd"> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-1 search-condition text-center" style="width:2%;padding:5px 0;"> -->
<!-- 						<span>--</span> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-2 search-condition" style="width:13.583325%"> -->
<!-- 						 <input class="form-control datePicker" type="text"  id="delTimeEnd" name="delTimeEnd" data-date-format="yyyy-mm-dd"> -->
<!-- 					</div> -->


					
<!-- 				</div> -->
				
				<div class="form-group">
				   <label class="col-md-1 control-label search-lable">商品搜索：</label>
				   <div class="col-md-2 search-condition" >
						   <select class="form-control" name="searchKeywrodType" id="searchKeywrodType">
						   <option value="1">商品名称</option>
						   <option value="2">商品货号</option>
						   <option value="3">商品ID</option>
						   <option value="4">商家备注</option>
                          </select>
					</div>
					<div class="col-md-3 search-condition" >
						 <input class="form-control nameWidth200" type="text"  id="searchKeywrod" name="searchKeywrod" >
					</div>

					<div class="col-md-3 text-center search-btn col-md-offset-3">
						 <button type="button"  class="btn btn-info" id="btn-query"><!-- <i class="glyphicon glyphicon-search"></i> -->搜索</button>
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
									<th>图片/名称/商品ID</th>
									<th>品牌/货号</th>
									<th>吊牌价</th>
									<th>醒购价</th>
									<th>商家备注</th>
									<th>类型</th>
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
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>


	<script>

function recover(id){
	swal({
		  title: "确定要还原此商品?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: true
		},
		function(){
			recoverConfirm(id);
		});
}

function recoverConfirm(id){
	$.ajax({
		url : "${ctx}/product/productRecover?id="+id,
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
				  title: "处理失败！",
				  type: "error",
				  timer: 1500,
				  confirmButtonText: "确定"
				});
		}
	});

}
	
	
var table;

$(document).ready(function () {
	
	
	$("#brandId").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	
	
	
	$("#productType1-select").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	$("#productType2-select").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	$("#productType3-select").select2({
		  language: "zh-CN",
		  placeholder: "--请选择--",
		  allowClear: true
		});
	
	getProductType1List(1);
	
	
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
            
            param.push({"name":"delFlag","value":"1"});
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            
            
            $.ajax({
                method: 'POST',
                url: '${ctx}/product/getProductList',
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
            	var html = [];
            	html.push('<div class="no-check">');
            	html.push("<div class='width-60'>"+"<img src='${ctx}/file_servelt"+row.pic+"'>"+"</div>");
            	html.push("<div class='width-226'><p class='h34'>"+row.name+"</p><span>ID:"+row.code+"</span></div>");
            	html.push("<div>");
            	return html.join("");
            }},
            {"data": "id",width:"98","render": function (data, type, row, meta) {
                var returnStr= row.productBrandName+"<br>"+row.artNo;
                return returnStr;
            }},
            {"data": "id",width:"98","render": function (data, type, row, meta) {
            	var html = [];
				html.push(row.tagPriceMin);
				if(row.tagPriceMin!=row.tagPriceMax){
					html.push("-");
					html.push(row.tagPriceMax);
				};
			    return html.join("");
            }},
            {"data": "id",width:"88","render": function (data, type, row, meta) {
				var html = [];
				
				html.push(row.salePriceMin);
				
				if(row.salePriceMin!=row.salePriceMax){
					html.push("-");
					html.push(row.salePriceMax);
				};
			    return html.join("");
            }},
            {"data": "remarks",width:"134","render": function (data, type, row, meta) {
            	var html = [];
            	html.push("<span class='t-flag glyphicon glyphicon-flag "+"color-"+row.remarksColor+"' aria-hidden='true'></span>");
            	html.push(data);
            	return html.join("");
            }},
            {"data": "id",width:"108","render": function (data, type, row, meta) {
            	if(row.saleType=="1"){
            		return "品牌款";
            	}
            	if(row.saleType=="2"){
            		return "单品款";
            	}
            }},
            {"data": "id",width:"69","render": function (data, type, row, meta) {
            	return  "<a class='table-opr-btn' href='javascript:void(0);' onclick='recover("+row.id+")' >还原</a>";
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
    $("#searchKeywrod").keydown(function(e){
    	if(e.keyCode==13){
    		table.ajax.reload();
            return false;
    	}
    });
});
</script>
</body>
</html>
