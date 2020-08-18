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
	<div class="x_panel container-fluid">

		<div class="row content-title">
			<div class="col-md-10">商品管理</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" value="3" name="search_auditStatus">
				
				
				<div class="form-group">
							<label for="productBrand" class="col-md-1 control-label search-lable">品牌名称：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="search_brandId" id="search_brandId">
						   <option value="">--请选择--</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
						   <option value="${productBrand.productBrandId}">${productBrand.productBrandName}</option>
						   </c:forEach>
                          </select>
					</div>
				   <div class="col-md-2 search-condition" >
						   <select class="form-control" name="searchKeywrodType" id="searchKeywrodType">
						   <option value="1">商品名称</option>
						   <option value="2">商品货号</option>
						   <option value="3">商品ID</option>
						   <option value="4">商家备注</option>
						   <option value="5">活动ID</option>
                          </select>
					</div>
					<div class="col-md-2 search-condition" >
						 <input class="form-control nameWidth200" type="text"  id="searchKeywrod" name="searchKeywrod" >
					</div>

					<div class="col-md-3 text-center search-btn">
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
						</table>
					</div>
				</div>
		</div>
	</div>

	
<!-- 	查看信息框 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>

<div class="modal fade" id="setRemarksModal" tabindex="-1" role="dialog" aria-labelledby="setRemarksModal" data-backdrop="static" >
  <div id="test" class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">商品备注</h4>
      </div>
      <div class="modal-body">
       			<div>
       			<form id="dataFrom">
				    <label for="name" class="money">颜色标记：</label>
				    <div style="display: inline-block" id="remarksColors">
				    		<span onclick="selectRemarks(1);" style="padding:0 10px;font-size:20px;cursor: pointer;" class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
				    		<span onclick="selectRemarks(2);" style="padding:0 10px;font-size:20px;cursor: pointer;" class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
					    	<span onclick="selectRemarks(3);" style="padding:0 10px;font-size:20px;cursor: pointer;" class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
					    	<span onclick="selectRemarks(4);" style="padding:0 10px;font-size:20px;cursor: pointer;" class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
					    	<span onclick="selectRemarks(5);" style="padding:0 10px;font-size:20px;cursor: pointer;" class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
					    	<span onclick="selectRemarks(6);" style="padding:0 10px;font-size:20px;cursor: pointer;" class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a>
					    	
				    </div>
				    <br>
				    <label for="name" class="money">备注内容：</label>
				    <textarea style="display: inline-block;width:60%;" rows="3"  id="remarks" name="remarks" validate="{maxlength:60}" placeholder="限60个字符"></textarea>
				    <br>
				    <br>
				    <div style="margin-left: 50px;">
				    <div id="selectedRemarksDiv" style="display: inline-block;">
					</div>
					<div style="display: inline-block;text-align: center;" id="remarksBtnDiv">
					    <input type="hidden" id="remarksColor" value="" name="remarksColor">
					    <input type="hidden" id="ids" value="" name="ids">
						<button type="button" class="btn btn-default" onclick="saveRemarks();">提交</button>
						<button type="button" class="btn btn-info" data-dismiss="modal">取消</button> 
					</div>
					</div>
					<br>
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
	
	function selectSaleType(){
		var selectType=$("#search_saleType").val();
		var selectHtml;
		if(selectType==""||selectType=="1"){
			selectHtml='<option value="">--上架状态--</option><option value="1">上架</option><option value="0">下架</option>';
		}
		if(selectType=="2"){
			selectHtml='<option value="">--活动状态--</option><option value="0">可报名</option><option value="1">活动中</option>';
		}
		
		$("#search_status").html(selectHtml);
		
		
	}
	
	
	
	
	
	function addProduct(){
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

		getContent("${ctx}/product/productAdd");

	}
	
	function editProduct(id){
		
		getContent("${ctx}/product/productEdit?s_auditStatus=3&id="+id);
	}
	function editProductSku(id){
		$.ajax({
	        url: "${ctx}/product/productSkuEdit?id="+id, 
			type : 'GET',
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
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
			  closeOnConfirm: false
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


function selectAll(){
    var check = $(".checkAll").prop("checked");
    $(".checkRow").prop("checked", check);
}

function batchSetRemarks(){
	
	if($(".checkRow:checked").length<=0){
		swal('请选择行');
		return;
	}
	 var productIds="";
     $(".checkRow:checked").each(function(){
    	 productIds=productIds+","+$(this).val();
     });
  	setRemarks(productIds,1,"");
}

$(document).ready(function () {
	$.metadata.setType("attr", "validate");
	dataFromValidate =$("#dataFrom").validate({});
	//解决select2模态框搜索问题
	$.fn.modal.Constructor.prototype.enforceFocus = function () {};
	
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
                    $(".checkAll").prop("checked", false);
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
            {"data": "id","title":"图片/名称/商品ID","render": function (data, type, row, meta) {
            	var html = [];
            	html.push('<div class="no-check">');
            	html.push("<div class='width-60'>"+"<img src='${ctx}/file_servelt"+row.pic+"'>"+"</div>");
            	html.push("<div class='width-226'>"+row.name+"<br><span>ID:"+row.code+"</span></div>");
            	html.push("<div>");
            	return html.join("");
            }},
            {"data": "id",width:"98","title":"品牌/货号","render": function (data, type, row, meta) {
                var returnStr= row.productBrandName+"<br>"+row.artNo;
                return returnStr;
            }},
            {"data": "auditRemarks",width:"321","title":"驳回原因"},
            {"data": "id",width:"108","title":"类型","render": function (data, type, row, meta) {
            	if(row.saleType=='1'){
            		return "品牌款";

            	}
            	if(row.saleType=='2'){
            	 return "单品款";

            	}
            	
            }},
            {"data": "id",width:"69","title":"操作","render": function (data, type, row, meta) {
            	var html = [];
            	
            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProduct("+row.id+")' >编辑</a>"+"<br>");	
//            	if((row.offReason!='4'&&row.saleType=='2'&&row.activityStatusDesc=='0')||(row.offReason!='4'&&row.saleType=='1'&&row.status=='0')){
//            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProduct("+row.id+")' >编辑</a>"+"<br>");	
//            	}
            	
            	
            	if((row.saleType=='2'&&row.activityStatusDesc=='0')||(row.saleType=='1'&&row.status=='0')){
            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='delProduct("+row.id+")' >删除</a><br>");	
            	}
            	
            	
            	return  html.join("");
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
