<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>供应商商品SKU池</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />

</head>

<body>
	<div class="x_panel container-fluid">

		<div class="row content-title">
			<div class="t-title">
				供应商商品SKU池
				<a href='javascript:void(0);' onclick="toBind();" >绑定供应商</a>
			</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="productBrand" class="col-md-1 control-label search-lable">品牌：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="productBrandId" id="productBrandId">
						   <option value="">全部</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
						   <option value="${productBrand.productBrandId}">${productBrand.productBrandName}</option>
						   </c:forEach>
                          </select>
					</div>
					
					<label for="activityStatus" class="col-md-1 control-label search-lable">货号：</label>
					<div class="col-md-2 search-condition" >
						 <input class="form-control nameWidth200" type="text"  id="artNo" name="artNo" >
					</div>
					
					<label for="activityStatus" class="col-md-1 control-label search-lable">状态：</label>
					<div class="col-md-2 search-condition">
                          <select  class="form-control" name="status" id="status">
						   <option value="">全部</option>
						   <option value="1">销售中</option>
						   <option value="0">已下架</option>
                          </select>
					</div>
					
					<label for="activityStatus" class="col-md-1 control-label search-lable">供应商：</label>
					<div class="col-md-2 search-condition">
                          <select  class="form-control" name="supplierUserId" id="supplierUserId">
						   <option value="">请选择</option>
						   <c:forEach var="mchtSupplierUserCustom" items="${mchtSupplierUserCustoms}">
							   <option value="${mchtSupplierUserCustom.spOfficeId}">${mchtSupplierUserCustom.companyName}</option>
						   </c:forEach>
                          </select>
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
				<div class="col-md-12 datatable-container">
					<table id="datatable"
						class="table table-striped table-bordered dataTable no-footer"
						role="grid" aria-describedby="datatable_info">
					</table>
				</div>
			</div>
		</div>
	</div>

	
<!-- 	查看信息框 -->

<div class="modal fade" id="cloudProductViewModal" tabindex="-1" role="dialog" aria-labelledby="cloudProductViewModalLabel" data-backdrop="static"></div>


	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
		<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
		<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script>
	function toViewCloudProduct(id){
		$.ajax({
 	        url: "${ctx}/cloudProduct/toViewCloudProduct?id="+id, 
 	        type: "GET", 
 	        success: function(data){
 	            $("#cloudProductViewModal").html(data);
 	            $("#cloudProductViewModal").modal();
 	        },
 		    error:function(){
 		    	swal('页面不存在');
 		    }
 		});
	}
	
	function toProductList(productIds){
		$.ajax({
 	        url: "${ctx}/cloudProduct/toProductList?productIds="+productIds, 
 	        type: "GET", 
 	        success: function(data){
 	            $("#cloudProductViewModal").html(data);
 	            $("#cloudProductViewModal").modal();
 	        },
 		    error:function(){
 		    	swal('页面不存在');
 		    }
 		});
	}
	
	var dataFromValidate;
	function toBind(){
 		$.ajax({
 	        url: "${ctx}/cloudProduct/toBind", 
 	        type: "GET", 
 	        success: function(data){
 	            $("#cloudProductViewModal").html(data);
 	            $("#cloudProductViewModal").modal();
 	        },
 		    error:function(){
 		    	swal('页面不存在');
 		    }
 		});

	}
	
	function editProduct(id){
		var pageNumber = $("li[class='paginate_button active']").find("a").first().text();
		getContent("${ctx}/product/productEdit?id="+id+"&pageNumber="+pageNumber);
	}
	function editProductSku(id){
		$.ajax({
	        url: "${ctx}/product/productSkuEdit?id="+id, 
			type : 'GET',
	        success: function(data){
	            $("#cloudProductViewModal").html(data);
	            $("#cloudProductViewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});

	}
	
	
	function changeProductStatus(id,status){
		var title;
		if(status==0){
			title="确定要下架此商品？";
		}else{
			title="确定要上架此商品？";
		}
		swal({
			  title: title,
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: true
			},
			function(){
				$.ajax({
					url : "${ctx}/product/changeProductStatus?id="+id,
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
					data : {id:id},
					timeout : 30000,
					success : function(data) {
						if (data.returnCode=="0000") {
							table.ajax.reload( null, false );
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
			});
	}
	
var table;
$(document).ready(function () {
	$.metadata.setType("attr", "validate");
	dataFromValidate =$("#dataFrom").validate({});
	//解决select2模态框搜索问题
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};

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
                url: '${ctx}/cloudProduct/getCloudProductList',
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
            {"data": "id",width:"80","title":"供应商SKU图","render": function (data, type, row, meta) {
            	var html = [];
            	html.push('<div class="is-check">');
            	if(row.skuPic){
	            	if(row.skuPic.indexOf("http") >= 0){//网络图片
	            		html.push('<div class="width-60"><img src='+row.skuPic+'></div>');
	            	}else{
	            		html.push('<div class="width-60"><img src="${ctx}/file_servelt'+row.skuPic+'"></div>');
	            	}
            	}
            	html.push("<div>");
            	return html.join("");
            }},
            {"data": "id",width:"98","title":"品牌/货号","render": function (data, type, row, meta) {
                var returnStr= row.brandName+"<br>"+row.artNo;
                return returnStr;
            }},
            {"data": "id",width:"98","title":"尺码","render": function (data, type, row, meta) {
                var propValues = row.propValues;
                if(propValues){
	                var propValuesArray = propValues.split(",");
	                var length = propValuesArray.length;
	                if(length>1){
	                	return propValuesArray[0]+"到"+propValuesArray[length-1];
	                }else{
	                	return propValuesArray[0];
	                }
                }else{
                	return "";
                }
            }},
            {"data": "id",width:"78","title":"经销价","render": function (data, type, row, meta) {
            	var html = [];
				html.push(row.sellingPrice);
			    return html.join("");
            }},
            {"data": "id",width:"78","title":"商城价/活动价",sClass:"hiddenCol","render": function (data, type, row, meta) {
				var html = [];
				html.push(row.mallPriceMin);
				if(row.mallPriceMin!=row.mallPriceMax){
					html.push("-");
					html.push(row.mallPriceMax);
				};
				html.push('<br>');
				html.push(row.salePriceMin);
				if(row.salePriceMin!=row.salePriceMax){
					html.push("-");
					html.push(row.salePriceMax);
				};
			    return html.join("");
            }},
            {"data": "id",width:"78","title":"库存","render": function (data, type, row, meta) {
            	var html = [];
				html.push(row.stock);
			    return html.join("");
            }},
            {"data": "id",width:"108","title":"供应商商品状态","render": function (data, type, row, meta) {
            	var html = [];
   	      	   	if(row.status=='0'){
   	           	   	html.push("已下架<br>");
   	       	   	}else{
   	       	   		html.push("销售中<br>");
   	       	   	}
   	      		html.push('<a href="javascript:;" onclick="toViewCloudProduct('+row.id+');">查看商品</a>');
            	return html.join("");
            }},
            {"data": "id",width:"69","title":"操作","render": function (data, type, row, meta) {
            	var html = [];
            	if(row.productIds){
	            	html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="toProductList('+"'"+row.productIds+"'"+')" >查看关联商品</a><br>');
            	}else{
            		html.push("未绑定");        		
            	}
            	return  html.join("");
            }},
            {"data": "id",width:"108","title":"供应商创建日期","render": function (data, type, row, meta) {
            	var createDate = new Date(row.createDate);
            	return createDate.format("yyyy-MM-dd");
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
