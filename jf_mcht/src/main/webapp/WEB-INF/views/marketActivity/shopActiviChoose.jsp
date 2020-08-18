<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>品牌特卖活动管理</title>

	<style type="text/css">
		.color-red{
			color: #FC0000;
		}
		.color-green{
			color: #00FC28;
		}
		.color-orange{
			color: orange;
		}
	</style>

<!-- 预览活动列表 -->
<style>
	.modal-preview p,
	.modal-preview ul {
		margin: 0;
	}
	.modal-preview ul {
		overflow: hidden;
		padding: 0;
	}
	.modal-preview li {
		list-style: none;
	}
	.modal-preview {
		display: none;
		position: absolute;
		top: 50%;
		left: 50%;
		z-index: 1051;
		width: 401px;
		height: 804px;
		margin: -402px 0 0 190px;
		background: url(${pageContext.request.contextPath}/static/images/active-preview/preview-bg.png) no-repeat;
		cursor: move;
	}
	.scroll-preview {
		overflow: hidden;
		position: relative;
		width: 334px;
		height: 538px;
		margin: 158px auto 0;
		cursor: default;
	}
	.close-preview {
		position: absolute;
		top: 0;
		right: 8px;
		z-index: 3;
		width: 37px;
		height: 37px;
		background: #333 url(${pageContext.request.contextPath}/static/images/active-preview/preview-close.png) no-repeat center;
		cursor: pointer;
		border-radius: 100%;

		transition: .6s ease-out;
	}
	.close-preview:hover {
		transform: rotate(360deg);
	}

	.header-preview {
		position: relative;
		height: 167px;
	}
	.header-preview img {
		display: block;
		border: none;
		width: 100%;
	}
	.header-preview p {
		position: absolute;
		bottom: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 24px;
		line-height: 24px;
		color: #fff;
		font-size: 14px;
		padding-left: 6px;
		box-sizing: border-box;
		background: #737779;
	}

	.con-preview {
		overflow: hidden;
		padding: 8px 6px 0;
		background: #fafafa;
	}
	.con-preview ul {
		width: 328px;
	}
	.con-preview li {
		float: left;
		width: 154px;
		height: 226px;
		margin: 0 6px 6px 0;
		background: #fff;
		border: 1px solid #ddd;
		box-sizing: border-box;
	}

	.con-preview-top {
		position: relative;
		height: 152px;
		background: #f1f1f1;
	}
	.con-preview-top div {
		position: absolute;
		top: 7px;
		left: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 22px;
		padding-right: 6px;
		box-sizing: border-box;
	}
	.con-preview-top span {
		display: none;
		float: right;
		width: 22px;
		height: 100%;
		margin-left: 8px;
		border-radius: 100%;
		background: #333 url(${pageContext.request.contextPath}/static/images/active-preview/preview-edit.png) no-repeat;
		cursor: pointer;

		transition: transform .3s ease-out;
	}
	.con-preview-top span:active {
		transform: scale(1.2);
	}
	.con-preview-top span.by-bot {
		background-position: 0 -66px;
	}
	.con-preview-top span.to-bot {
		background-position: 0 -44px;
	}
	.con-preview-top span.to-top {
		background-position: 0 -22px;
	}
	.con-preview-top span.by-top {
		background-position: 0 0;
	}
	.con-preview-top span.ban {
		background-color: #999;
	}
	.con-preview-top img {
		display: block;
		width: 100%;
		border: none;
	}

	.con-preview-bot {
		padding: 8px 5px 0;
	}
	.con-preview-bot p {
		overflow: hidden;
		height: 30px;
		font: 12px/14px '微软雅黑';
		color: #333;
	}
	.con-preview-bot div {
		padding-top: 6px;
		line-height: 22px;
	}
	.con-preview-bot strong,
	.con-preview-bot s {
		float: left;
		max-width: 50px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	.con-preview-bot strong {
		font-size: 14px;
		font-weight: bold;
		margin-right: 4px;
		color: #f55;
	}
	.con-preview-bot s {
		font-size: 12px;
		color: #999;
	}
	.con-preview-bot em {
		float: right;
		font-style: normal;
		height: 18px;
		line-height: 18px;
		padding: 0 2px;
		font-size: 12px;
		color: #f55;
		border: 1px solid #f55;
	}

	.footer-preview {
		padding-top: 20px;
		text-align: center;
	}
	.footer-preview span {
		display: inline-block;
		vertical-align: top;
		width: 100px;
		height: 34px;
		line-height: 34px;
		margin: 0 17px;
		text-align: center;
		background: #ff5050;
		font-size: 14px;
		color: #fff;
		cursor: pointer;
	}
	.footer-preview span.hide {
		display: none;
	}

	.iScrollLoneScrollbar {
		background: rgba(0, 0, 0, .3);
	}
	.iScrollIndicator {
		border: none !important;
	}
</style>
</head>

<body>
<input type="hidden" id="txtType" value="${txtType}">
<div class="modal-dialog wg-xx" role="document" style="width:1000px;">
<div class="x_panel container-fluid py-tm">
 	  <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">商品选择</span>
      </div>
	<div class="search-container container-fluid">
		<form class="form-horizontal" id="searchForms">
			<div class="form-group">
				<label for="name" class="col-md-1 control-label search-lable">品牌：</label>
				<div class="col-md-2 search-condition">
					<select class="form-control" name="brandId" id="brandId">
						<option value="">--请选择--</option>
						<c:forEach var="productBrand" items="${productBrandList}">
							<option value="${productBrand.id}">${productBrand.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2 search-condition" style="margin-left:20px;">
                    <select class="form-control" name="productIDorNameorArtNo" id="productIDorNameorArtNo">
                        <option value="0">商品ID</option>
                        <option value="1">商品名称</option>
                        <option value="2">商品货号</option>
                    </select>
              	<textarea cols="" id="productIDorCodeData" name="productIDorNameorArtNoData" placeholder="多个商品ID查询请换行" style="height: 28px; width: 192px;resize:none;padding:0px;"></textarea>                  
            	</div>
				<label for="status" class="col-md-1 control-label search-lable" style="margin-left:13px;">活动价：</label>
				<div class="col-md-5 search-condition" >
              	<input class="form-control"  style="width:50px;" type="text" id="priceMin" name="priceMin">
              	<i class="picker-split">~</i>
              	<input class="form-control" style="width:50px;" type="text" id="priceMax" name="priceMax">
                </div>
				<div class="col-md-3 text-center search-btn" style="margin-left:40px;">
					<button type="button"  class="btn btn-info" id="btn-querys">搜索</button>
				</div>
			</div>
		</form>
	</div>
	<div class="clearfix"></div>
	<div class="x_content container-fluid">
		<div class="row">
			<div class="col-md-12 datatable-container at-table">
				<table id="datatables" class="table table-striped table-bordered dataTable no-footer" role="grid" aria-describedby="datatable_info">
				</table>
			</div>
		</div>
	</div>
</div>
</div>

<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
	 data-backdrop="static">
</div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/static/js/iscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/tmpl.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/draggabilly.pkgd.min.js"></script>

<script>
	var tables,
	status_val;
	$(document).ready(function () {
		tables = $('#datatables').dataTable({
			"ajax": function (data, callback, settings) {
				var param = $("#searchForms").serializeArray();
                param.push({"name": "pageSize", "value": data.length});
                if (data.start > 0) {
                    param.push({"name": "pageNumber", "value": data.start / data.length + 1});
                } else {
                    param.push({"name": "pageNumber", "value": 1});
                }

				$.ajax({
					method: 'POST',
					url: '${ctx}/market/shopProductList?activityType='+${activityType},
					data: param,
					dataType: 'json'
				}).done(function (result) {
					if (result.returnCode == '0000') {
						var returnData = {};
						returnData.recordsTotal = result.returnData.Total;
						returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
						returnData.data = result.returnData.Rows;
						callback(returnData);
						$(".dataTables_empty").attr("colspan",6);//BUG #9514
					}else{
						 swal(result.returnMsg);
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
              	{"data": "id",width:80,"title":"商品信息","render": function (data, type, row, meta) {
                  	var html = [];
                	html.push('<div class="is-check">');
                	if(row.pic){
    	            	if(row.pic && row.pic.indexOf("http") >= 0){//网络图片
    	            		html.push('<div class="width-60"><img src='+row.pic+'></div>');
    	            	}else{
    	            		html.push('<div class="width-60"><img src="${ctx}/file_servelt'+row.pic+'"></div>');
    	            	}
                	}         	
                	html.push('<div class="width-226"><p class="h34">'+row.name+'</p><div></div>货号：'+row.artNo+'<div><span style="float: left; margin: 0;">ID：'+row.code+'</span><a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id='+row.code+'" target="_blank">预览</a></div></div>');
                	html.push("<div>");
                	return html.join("");
                }},
                {"data": "id",width:70,"title":"商城价","render": function (data, type, row, meta) {
    				var html = [];
    				
    				html.push(row.mallPriceMin);
    				
    				if(row.mallPriceMin!=row.mallPriceMax){
    					html.push("-");
    					html.push(row.mallPriceMax);
    				};
    			    return html.join("");
                }},
				{"data": "id",width:70,"title":"活动价","render": function (data, type, row, meta) {
					var html = [];
					
					html.push(row.salePriceMin);
					
					if(row.salePriceMin!=row.salePriceMax){
						html.push("-");
						html.push(row.salePriceMax);
					};
				    return html.join("");
	            }},
				{"data": "stock","title":"库存",width:70},	
				{"data": "id",width:"60","title":"状态","render": function (data, type, row, meta) {
					if(row.status == "0"){
						return "下架";
					}else{
						return "上架";
					}
	            }},
				{
					"data": "activity.id","width":60,"title":"操作","render": function (data, type, row, meta) {
						//判断之前是否已经有操作过,并赋相应值
							for(var i = 0;i<addId.length;i++){	
								if(addId[i] == row.id){
									return '已添加';
								}
							}
							for(var i = 0;i<notAdd.length;i++){
								if(notAdd[i] == row.id){
									return '不可报名';
								}
							}			
						var html = [];
						html.push('<div id="'+row.id+'"><a class="table-opr-btn" href="javascript:void(0);" onclick="add('+row.id+');" >添加</a></div>');
						return html.join("");
				}}
			]
		}).api();
		
        $('#btn-querys').on('click', function (event) {
            tables.ajax.reload();
        });
	});
	
	function add(productId){
		 //不可大于最大报名数
		 if(parseInt($('#counted').val())>=parseInt($('#count').val())){
			 swal({
				  title: "推荐商品数不能超过"+$('#count').val()+"个",
				  type: "error",
				  timer: 3000,
				  confirmButtonText: "确定"
				});
		 }else{	
			 $.ajax({
		    	    url: "${ctx}/market/addProduct?productId="+productId+"&activityType="+${activityType},
		    	    success: function(data){ 	    	
		    	    	if (data.returnData == true) {
		    	    		$('#'+productId).html('已添加');
		    	    		//动态改变主页面的表格
		    	    		 addTable(productId);
		    	    		 addId.push(productId);
		    	    		 //改变商品选择的数量
		    	    		 $('#counted').val(parseInt($('#counted').val())+1);

		    	    		 $('#productChoose').text("商品选择("+$('#counted').val()+"/"+$('#count').val()+")");
		    	    		 $('#defaultImg').hide();
						}else if(data.returnData == false){
							 $('#'+productId).html('不可报名');
			    	    	 notAdd.push(productId);
						}else if(data.returnCode="4004"){
							swal(data.returnMsg);
						}
		    	    },
		    		error:function(){
		    		   	  swal('请求失败');
		    		}
		    	});
		 }
	}
	
	function addTable(productId){
		$('#content').show();
		$.ajax({
    	    url: "${ctx}/market/selectProduct?productId="+productId,
    	    success: function(data){
    	    	var html = "";
    	    	html+='<tr><td><div class="no-check">';
                if(data.pic){
                	var pic=data.pic.substring(0, data.pic.lastIndexOf("."))+"_M"+data.pic.substring(data.pic.lastIndexOf("."));
                	html+='<div class="width-60"><img src="${ctx}/file_servelt'+pic+'"></div>';
                }else{
                	html+='<div class="width-60"><img src="${ctx}/file_servelt"></div>';
                }
                html+='<div class="width-226"><p>'+data.name+'</p><br><span style="color: #999;margin: 5px 0 0;">货号：'+data.artNo+'&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="float: left; margin: 0;">ID：'+data.code+'</span><a style="float: right; width:45px;" href="https://m.xgbuy.cc/share_buy.html?id='+data.id+'" target="_blank">预览</a></div>';
                html+='</div></td>';
         /*        
                if(data.recommendRemarks==null){
                	html+='<td><textarea rows="3" cols="20" style="width:200px;" maxlength="40" id="remarks" placeholder="最多输入40个字"></textarea></td>';
                }else{
                	html+='<td><textarea rows="3" cols="20" style="width:200px;" maxlength="40" id="remarks" placeholder="最多输入40个字">'+data.recommendRemarks+'</textarea></td>';	
                } */
                
                
               	//商城价
            	html+='<td>'+data.mallPriceMin;
               	if(data.mallPriceMin!=data.mallPriceMax){
               		html+='-'+data.mallPriceMax+'</td>';
               	}else{
               		html+='</td>';
               	}
                //活动价
				html+='<td>'+data.salePriceMin;
               	if(data.salePriceMin!=data.salePriceMax){
               		html+='-'+data.salePriceMax+'</td>';
               	}else{
               		html+='</td>';
               	}
            	
             /*   	if(data.svipDiscount==null){
               		html+='<td>/</td>';
               	}else{
               	 	html+='<td>'+data.svipDiscount+'</td>';
               	} */
                
               	//库存
               	if(data.stock==undefined){
               		var temNum="0";
               		html+='<td>'+temNum+'</td>';
               	}else{
             		html+='<td>'+data.stock+'</td>';
               	}
               
               	//状态
               	if(data.status==1){
               		var temSta = "上线"
               		html+='<td>'+temSta+'</td>';
               	}else{
               		var temSta="下线";
               		html+='<td>'+temSta+'</td>';
               	}
               	//操作
               	html+='<td><div><a id="del" class="table-opr-btn" href="javascript:void(0);" data_value="'+data.id+'">删除</a></div></td>'
               	
               	$('#content').show();
                $('#content tr').eq(0).after(html);
    			
    	    },
    		error:function(){
    		   	  swal('请求失败');
    		}
    	});
	}
	
	$('#productIDorNameorArtNo').change(function(){
    	if($(this).children('option:selected').val() == '0'){
    		$('#productIDorCodeData').attr('placeholder','多个商品ID查询请换行');
    	}else if($(this).children('option:selected').val() == '1'){
    		$('#productIDorCodeData').attr('placeholder','多个商品名称查询请换行');
    	}else if($(this).children('option:selected').val() == '2'){
    		$('#productIDorCodeData').attr('placeholder','多个商品货号查询请换行');
    	}
    });
</script>
</body>
</html>
