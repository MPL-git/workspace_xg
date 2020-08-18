<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>商品管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />

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

.remarks_column_class{
text-align: left;
}
</style>
</head>

<body>
<div class="modal-dialog wg-xx" role="document" style="width:1000px;">
	<div class="modal-content" style="overflow: hidden;">
		<div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <span class="modal-title" id="exampleModalLabel">查看商品</span>
      	</div>
      	<div class="modal-body">
      		<div class="container-fluid">
				<form class="form-horizontal" id="searchProductForm">
					<input type="hidden" name="productIds" value="${productIds}">
				</form>
			</div>
			<div class="clearfix"></div>
			<div class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container">
						<table id="productDatatable"
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

<div class="modal fade" id="skuViewModal" tabindex="-1" role="dialog" aria-labelledby="skuViewModalLabel" data-backdrop="static"></div>

<div class="modal fade" id="setRemarksModal" tabindex="-1" role="dialog" aria-labelledby="setRemarksModal" data-backdrop="static" >
  <div id="test" class="modal-dialog" role="document" style="width: 400px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">商品备注</h4>
      </div>
      <div class="modal-body">
       			<div>
       			<form id="dataFrom">
				    <label for="name" class="money" style="margin-bottom: 17px;">颜色标记：</label>
				    <div style="display: inline-block" id="remarksColors">
				    		<span onclick="selectRemarks(1);" class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
				    		<span onclick="selectRemarks(2);" class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
					    	<span onclick="selectRemarks(3);" class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
					    	<span onclick="selectRemarks(4);" class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
					    	<span onclick="selectRemarks(5);" class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
					    	<span onclick="selectRemarks(6);" class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a>
				    </div>
				    <br>
				    <label for="name" class="money">备注内容：</label>
				    <textarea style="resize: vertical; height: 110px;padding: 5px;border: 1px solid #ddd;" rows="3"  id="remarks" name="remarks" validate="{maxlength:60}" placeholder="限60个字符"></textarea>
				    <br>
				    <div class="modal-footer" style="padding: 10px 0 0;">
				    <div id="selectedRemarksDiv" style="display: inline-block;">
					</div>
					<div style="display: inline-block;text-align: center;" id="remarksBtnDiv">
					    <input type="hidden" id="remarksColor" value="" name="remarksColor">
					    <input type="hidden" id="ids" value="" name="ids">
						<button type="button" class="btn btn-default" style="margin-right: 17px;" onclick="saveRemarks();">提交</button>
						<button type="button" class="btn btn-info" data-dismiss="modal">取消</button> 
					</div>
					</div>
				   </form> 
			    </div>
      </div>
    </div>
  </div>
</div>

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


	<script>
	
	function setRemarks(productId,remarksColor,remarks){
		 $("#selectedRemarksDiv").html('<span style="padding:0 10px;font-size:20px;" class="glyphicon glyphicon-flag color-'+remarksColor+'" aria-hidden="true"></span>');
		 $("#remarksColor").val(remarksColor);
		 if($.trim(remarks)!="null"){
		 $("#remarks").val($.trim(remarks));
		 }else{
			 $("#remarks").val("");
		 }
		 $("#ids").val(productId);
		 $("#setRemarksModal").modal();
	}
	
	function selectRemarks(color){
		$("#remarksColor").val(color);
		$("#selectedRemarksDiv").html('<span style="padding:0 10px;font-size:20px;" class="glyphicon glyphicon-flag color-'+color+'" aria-hidden="true"></span>');
	}
	var dataFromValidate;
	function saveRemarks(){
		if(dataFromValidate.form()){
		var dataJson = $("#dataFrom").serializeArray();
			$.ajax({
				url : "${ctx}/product/setRemarks",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				success : function(data) {
					if (data.returnCode == "0000") {
						table.ajax.reload( null, false );
						 $("#setRemarksModal").modal('hide');
					} else {
						swal({
							  title: '备注失败',
							  type: "error",
							  confirmButtonText: "确定"
							});
						return;
					}

				},
				error : function() {
					swal({
						  title: '备注失败',
						  type: "error",
						  confirmButtonText: "确定"
						});
					return;
				}
			});
		}
	}
	
	function addProduct(saleType){
// 		$.ajax({
// 	        url: "${ctx}/product/productAdd", 
// 	        type: "GET", 
// 	        success: function(data){
// 	            $("#viewModal").html(data);
// 	            $("#viewModal").modal();
// 	        },
// 		    error:function(){
// 		    	swal('页面不存在');
// 		    }
// 		});
		getContent("${ctx}/product/productAdd?saleType="+saleType);

	}
	
	function editProduct(id){
		$(".modal-backdrop").hide();
		getContent("${ctx}/product/productEdit?id="+id+"&from=cloudProduct");
		$("body").parent().css("overflow-y","auto");
	}
	function editProductSku(id){
		$.ajax({
	        url: "${ctx}/product/productSkuEdit?id="+id+"&from=cloudProduct", 
			type : 'GET',
	        success: function(data){
	            $("#skuViewModal").html(data);
	            $("#skuViewModal").modal();
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
	
	
	
	function delProduct(id){
		swal({
			  title: "确定要删除此商品?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: true
			},
			function(){
				delConfirm(id);
			});
	}

	function delConfirm(id){
		$.ajax({
			url : "${ctx}/product/delProduct?id="+id,
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

	}
	
	
var table;
$(document).ready(function () {
	$.metadata.setType("attr", "validate");
	dataFromValidate =$("#dataFrom").validate({});
	//解决select2模态框搜索问题
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
    table = $('#productDatatable').dataTable({
        "ajax": function (data, callback, settings) {
            var param = $("#searchProductForm").serializeArray();
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else {
                param.push({"name":"page","value":1});
            }
            $.ajax({
                method: 'POST',
                url: '${ctx}/cloudProduct/getProductList',
                data: param,
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode =='0000') {
                    var returnData = {};
                    returnData.recordsTotal = result.returnData.Total;
                    returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.returnData.Rows;
                    callback(returnData);
                    var shopStatus = ${shopStatus};
                    if(!shopStatus){
	                    $(".hiddenCol").css("display","none");
                    }
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
            {"data": "id",width:"240","title":"图片/名称/商品ID","render": function (data, type, row, meta) {
            	var html = [];
            	html.push('<div class="is-check">');
            	if(row.pic!=null){
	            	if(row.pic && row.pic.indexOf("http") >= 0){//网络图片
	            		html.push('<div class="width-60"><img src='+row.pic+'></div>');
	            	}else{
	            		html.push('<div class="width-60"><img src="${ctx}/file_servelt'+row.pic+'"></div>');
	            	}
            	}
            	
            	html.push('<div class="width-226"><p class="h34">'+row.name+'</p><div><span style="float: left; margin: 0;">ID：'+row.code+'</span><a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id='+row.code+'" target="_blank">预览</a></div></div>');
            	html.push("</div>");
            	return html.join("");
            }},
            {"data": "id",width:"98","title":"品牌/货号","render": function (data, type, row, meta) {
                var returnStr= row.productBrandName+"<br>"+row.artNo;
                return returnStr;
            }},
            {"data": "id",width:"78","title":"吊牌价","render": function (data, type, row, meta) {
            	var html = [];
				html.push(row.tagPriceMin);
				if(row.tagPriceMin!=row.tagPriceMax){
					html.push("-");
					html.push(row.tagPriceMax);
				};
			    return html.join("");
            }},
            {"data": "id",width:"78","title":"商城价",sClass:"hiddenCol","render": function (data, type, row, meta) {
				var html = [];
				
				html.push(row.mallPriceMin);
				
				if(row.mallPriceMin!=row.mallPriceMax){
					html.push("-");
					html.push(row.mallPriceMax);
				};
			    return html.join("");
            }},
            {"data": "id",width:"78","title":"活动价","render": function (data, type, row, meta) {
				var html = [];
				
				html.push(row.salePriceMin);
				
				if(row.salePriceMin!=row.salePriceMax){
					html.push("-");
					html.push(row.salePriceMax);
				};
			    return html.join("");
            }},
            {"data": "remarks", "width":"134", "title":"商家备注","className": "remarks_column_class","render": function (data, type, row, meta) {
            	var html = [];
            	html.push("<span onclick='setRemarks("+row.id+',"'+row.remarksColor+'"'+',"'+row.remarks+'"'+");' class='t-flag glyphicon glyphicon-flag "+"color-"+row.remarksColor+"' aria-hidden='true'></span>");
            	html.push(data);
            	return html.join("");
            }},
            {"data": "id",width:"108","title":"状态","render": function (data, type, row, meta) {
            	var html = [];
            	if(row.saleType=='1'){
          	   		if(row.activityStatusDesc=='0'||row.activityStatusDesc=='1'||row.auditStatus=='0'||row.auditStatus=='3'){
           	   			if(row.activityStatusDesc=='0'&&row.auditStatus!='0'){
           	   				html.push("可报名<br>");
            	   		}
               	   		if(row.activityStatusDesc=='1'){
            	   			html.push("报名中<br>");
               	   		}
               	   		if(row.auditStatus=='0'){
                	   		html.push("未提审<br>");
                   	   	}
               	   		if(row.auditStatus=='3'){
                	   		html.push("未通过<br>");
                   	   	}
            	   	}else{
            	   		html.push("活动中<br>");
            	   	}
    	      	   	if(row.status=='0'){
    	           	   	html.push("下架<br>");
    	       	   	}else{
    	       	   		html.push("上架");
    	       	   	}
            	}
            	
            	if(row.saleType=='2'){
          	   		if(row.activityStatusDesc=='0'||row.activityStatusDesc=='1'||row.activityStatusDesc=='3'){
           	   			if(row.activityStatusDesc=='0'){
           	   				html.push("可报名<br>");
            	   		}
               	   		if(row.activityStatusDesc=='1'){
            	   			html.push("报名中<br>");
               	   		}
               	   		if(row.activityStatusDesc=='3'){
            	   			html.push("预热中<br>");
               	   		}
               	   	
            	   	}else{
            	   		html.push("活动中<br>");
            	   	}
            	}
            	
            	

            	return html.join("");
            }},
            {"data": "id",width:"69","title":"操作","render": function (data, type, row, meta) {
            	var html = [];
            	if(row.saleType=='1'){//品牌款逻辑
                	if((row.offReason!='4'&&(row.activityStatusDesc=='0'||row.auditStatus=='0'||row.auditStatus=='3')&&row.status=='0')){
                		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProduct("+row.id+")' >编辑</a>"+"<br>");	
                	}
            	}
            	if(row.saleType=='2'){//单品款只要可报名就能编辑
                	if((row.offReason!='4'&&row.activityStatusDesc=='0')){
                		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProduct("+row.id+")' >编辑</a>"+"<br>");	
                	}
            	}
            	
        
            	
            	html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProductSku("+row.id+")' >修改SKU</a><br>");
            	
            	if(row.canDelete){
            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='delProduct("+row.id+")' >删除</a><br>");	
            	}
            	
            	if(row.saleType=='1'&&row.status=='1'&&row.activityStatusDesc=='0'){
	            	html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='changeProductStatus("+row.id+",0)' >下架</a>");
            	}
            	
            	return  html.join("");
            }}
        ]
    }).api();

});
</script>
</body>
</html>
