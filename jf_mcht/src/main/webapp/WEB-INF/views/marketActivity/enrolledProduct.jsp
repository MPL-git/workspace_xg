<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>活动商品管理</title>
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
        .hidden{
            display:none;
        }
        #popDiv {
            width:446px;
            height:294px;
            position:absolute;
            left:238px;
            top:90px;
            z-index:1;
            border:4px solid #7A7A7A;
            background-color: #f2f2f2;
        }
         .change {
            background: white;
            color: #131313;
            border-top: 3px solid red;
        }
     	.color-7{
		color: #0351F7;
		}
.more {
	position: relative;
	color: #bbb;
	cursor: pointer;
}
.more:hover:after {
	z-index: 999;
	color: white;
	position: absolute;
	top: calc(100% + 10px);
	left: 50%;
	content: attr(data-more);
	padding: 2px 10px;
	background: rgba(0, 0, 0, .5);
	border-radius: 5px;
	word-break: keep-all;
	transform: translateX(-50%);
}
.more:before {
	z-index: 999;
	display: none;
	position: absolute;
	top: 100%;
	left: 0;
	right: 0;
	content: '';
	width: 0;
	margin: 0 auto;
	border-bottom: 10px solid rgba(0, 0, 0, .5);
	border-left: 5px solid transparent;
	border-right: 5px solid transparent;
}
.more:hover:before {
	display: block;
}
.more:hover:after {
    position: absoulte;
	display: block;
	opacity: 0.7;
	z-index:999;
}
.lines{
    height: 1px;
   	background: rgba(221,221,221,1);
    width: 100%;
}
.fts13 {
	font-size:13px;
}
</style>
</head>

<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="t-title">
       		 	营销活动
        </div>
    </div>
    <div class="clearfix"></div>
    <ul class="nav nav-tabs q" role="tablist" style="margin-top:10px;">
        <li> <a href="#" role="tab" data-toggle="tab" onclick="signUpActivity()">活动报名</a></li>
        <li id="tab2" <c:if test="${pageStatus eq 1}">class="change"</c:if>> <a href="#" role="tab" data-toggle="tab">已报名商品</a></li>
    </ul>
    <div class="lines"></div>
    <div class="clearfix"></div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
           	<div class="form-group">
            	<label for="productBrand" class="col-md-1 control-label search-lable">活动类型：</label>
                <div class="col-md-2 search-condition" >
                    <select class="form-control" name="type" id="type">
                    	<option value="">请选择</option>
                        <option value="1">有好货</option>
                        <option value="4">潮鞋上新</option>
                        <option value="5">潮流男装</option>
                        <option value="6">断码特惠</option>
                        <option value="7">运动鞋服</option>
                        <option value="8">潮流美妆</option>
                        <option value="9">食品超市</option>
                        <option value="13">积分转盘</option>
                    </select>
                </div>
                
            	<label for="productBrand" class="col-md-1 control-label search-lable">商品名称：</label>
             	<div class="col-md-2 search-condition" >
              	<input class="form-control nameWidth200" type="text" id="name" name="name">
                </div>
            
            	<div class="col-md-2 search-condition" >
                    <select class="form-control" name="productIDorCode" id="productIDorCode">
                        <option value="0">商品ID</option>
                        <option value="1">商品货号</option>
                    </select>
              	<textarea cols="" id="productIDorCodeData" name="productIDorCodeData" placeholder="多个商品ID查询请换行" style="height: 28px; width: 192px;resize:none;padding:0px;"></textarea>                  
            	</div>
            </div>

            <div class="form-group">
             	<label for="productBrand" class="col-md-1 control-label search-lable">报名状态：</label>
                <div class="col-md-2 search-condition" >
                    <select class="form-control" name="auditStatus" id="auditStatus">
                    	<option value="">请选择</option>
                        <option value="0">待审核</option>
                        <option value="1">审核通过</option>
                        <option value="2">驳回</option>
                    </select>
                </div>
				<label for="productBrand" class="col-md-1 control-label search-lable">上线日期：</label>
				<div class="col-md-5 search-condition">
						<input class="form-control datePicker" type="text" id="upDateBegin" name="upDateBegin" data-date-format="yyyy-mm-dd">
						<i class="picker-split">-</i>
						<input class="form-control datePicker" type="text" id="upDateEnd" name="upDateEnd" data-date-format="yyyy-mm-dd">
				</div>                           
           	 	<div class="col-md-5 text-center search-btn">
                	<button type="button"  class="btn btn-info" id="btn-query">查询</button>
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
                        <th width="88">活动类型</th>
                        <th>商品</th>
                        <th width="88">活动价</th>
                        <th width="68">SVP折扣</th>
                        <th width="88">库存</th>
                        <th width="68">近30天销量</th>
                        <th width="88">上线日期</th>
                        <th width="88">报名状态</th>
                        <th width="68">操作</th>
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
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>
    var table;
    $(document).ready(function () {
    	$('.datePicker').datetimepicker(
    			{
    				minView: "month", //选择日期后，不会再跳转去选择时分秒
    				format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
    				language: 'zh-CN', //汉化
    				autoclose:true //选择日期后自动关闭
    			}
    	);
    	
        $.metadata.setType("attr", "validate");
        dataFromValidate =$("#dataFrom").validate({});

        table = $('#datatable').dataTable({
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm").serializeArray();
                param.push({"name": "pageSize", "value": data.length});
                if (data.start > 0) {
                    param.push({"name": "pageNumber", "value": data.start / data.length + 1});
                } else {
                    param.push({"name": "pageNumber", "value": 1});
                }

                $.ajax({
                    method: 'POST',
                    url: '${ctx}/market/listProduct',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode == "0000") {
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
            "lengthMenu":[10,20,30,40,50],
            "iDisplayLength" : 20, //默认显示的记录数 
            "bServerSide": true,
            "columns": [
                {"data": "id","render": function (data, type, row, meta) {
                    var html = [];
                    if(row.type == "1"){
                    	return "有好货";
                    }
                    if(row.type == "4"){
                    	return "潮鞋上新";
                    }
                    if(row.type == "5"){
                    	return "潮流男装";
                    }
                    if(row.type == "6"){
                    	return "断码特惠";
                    }
                    if(row.type == "7"){
                    	return "运动鞋服";
                    }
                    if(row.type == "8"){
                    	return "潮流美妆";
                    }
                    if(row.type == "9"){
                    	return "食品超市";
                    }
                    if(row.type == "13"){
                        return "积分转盘";
                    }
                }},
                {"data": "id","render": function (data, type, row, meta) {
                    var html = [];
                    html.push('<div class="no-check">');
                    if(row.pic){
						var pic=row.pic.substring(0, row.pic.lastIndexOf("."))+"_M"+row.pic.substring(row.pic.lastIndexOf("."));
	                    html.push('<div class="width-60"><img src="${ctx}/file_servelt'+pic+'"></div>');
                    }else{
	                    html.push('<div class="width-60"><img src="${ctx}/file_servelt"></div>');
                    }
                    html.push('<div class="width-226"><a href="https://m.xgbuy.cc/share_buy.html?id='+row.code+'" target="_blank">'+row.name+'</a><br><p style="color: #999;margin: 5px 0 0;">货号：'+row.artNo+'&nbsp;&nbsp;&nbsp;&nbsp;</p><p style="color: #999;margin: 0;">商品ID：'+row.code+'</p></div>');
                    html.push("</div>");
                    return html.join("");
                }},
                {"data": "id","render": function (data, type, row, meta) {
					var html = [];
					
					html.push(row.salePriceMin);
					
					if(row.salePriceMin!=row.salePriceMax){
						html.push("-");
						html.push(row.salePriceMax);
					};
				    return html.join("");
	            }},
                {"data": "svipDiscount"},
                {"data": "productItemStock"},
                {"data": "productCount"},
                {"data": "upDate","render": function (data, type, row, meta) {
                	var html = [];
                	if (row.upDate != null && row.upDate != '') {
						var upDate = new Date(row.upDate);
						html.push(upDate.format("yyyy-MM-dd"));
					}
					return html.join("");           	
                }},
                {"data": "auditStatus","render": function (data, type, row, meta) {
                	if(row.auditStatus == "0"){
                		return "待审核";
                	}
                	if(row.auditStatus == "1"){
                		return "审核通过";
                	}
                	if(row.auditStatus == "2"){
                		return "驳回";
                	}
					return html.join("");           	
                }},
                {"data": "operation","render": function (data, type, row, meta) {
                	if(row.auditStatus == "0" || row.auditStatus == "1"){
                		return "<a href=\"javascript:editProductSku("+row.linkId+");\">修改SKU</a>";
                	}
                	if(row.auditStatus == "2"){
                		return "<a href=\"javascript:quit("+row.id+");\">退出</a>";
                	}
					return html.join("");           	
                }},
            ]
        }).api();
        
        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });
    });
    
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
	
	function quit(id){
		swal({
			  title: "是否将商品从报名活动中移除",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: true
			},
			function(){
				$.ajax({
					url : "${ctx}/market/quitActivity?id="+id,
					type : 'POST',
					dataType : 'json',
					cache : false,
					async : false,
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
	
    function signUpActivity() {
        getContent("${ctx}/market/signUpActivity");
    }
    
    $('#productIDorCode').change(function(){
    	if($(this).children('option:selected').val() == '1'){
    		$('#productIDorCodeData').attr('placeholder','多个商品货号查询请换行');
    	}else{
    		$('#productIDorCodeData').attr('placeholder','多个商品ID查询请换行');
    	}
    });
</script>
</body>
</html>
