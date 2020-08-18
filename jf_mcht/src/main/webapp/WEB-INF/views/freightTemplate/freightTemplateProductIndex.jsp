<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>${freightTemplateName}--商品管理</title>
<link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
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
	<div class="x_panel container-fluid sh-gl">
		<div class="row content-title">
			<div class="t-title">
				${freightTemplateName}--商品管理
				<a href="javascript:void(0);" onclick="getContent('${ctx}/freightTemplate/freightTemplateIndex')">返回</a>
			</div>
		</div>
		<div class="search-container container-fluid">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" name="searchTemplateType" id="searchTemplateType" value="${searchTemplateType}">
				<input type="hidden" name="freightTemplateId" id="freightTemplateId" value="${freightTemplateId}">
				<div class="form-group">
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
					
					<div class="modal fade" id="popDivs" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            </div>
                            <div class="modal-body">
                                <p>请输入货号：<span class="c9">(一行一个)</span></p>
                                <div>
                                    <textarea id="artNos" name="artNos" rows="8" style="width: 100%;resize: none;"></textarea>
                                </div>
                                <div class="modal-footer" style="padding: 10px 0 0">
                                    <button type="button"  class="btn btn-info" id="btn-query-arts">查询</button>
                                </div>
                            </div>
                        </div>
                    </div>
                	</div>
					
					<div class="col-md-3 text-center search-btn">
						<button type="button"  class="btn btn-info" id="btn-query">搜索</button>
						<button type="button"  class="btn btn-default" id="btn-query-pop">多货号批量搜索</button>
					</div>
				</div>
			</form>
		</div>
  <!-- Nav tabs -->
  <ul class="nav nav-tabs q" role="tablist">
    <li role="presentation" <c:if test="${searchTemplateType =='0'}">class="active"</c:if>><a href="#" style="white-space: nowrap;width: 145px;" role="tab" data-toggle="tab" onclick="list('0');" id="currentTemplateCount">当前运费模板商品</a></li>
    <li role="presentation" <c:if test="${searchTemplateType =='1'}">class="active"</c:if>><a href="#" style="white-space: nowrap;width: 145px;" role="tab" data-toggle="tab" onclick="list('1');" id="noTemplateCount">未分配商品</a></li>
    <li role="presentation" <c:if test="${searchTemplateType =='2'}">class="active"</c:if>><a href="#" style="white-space: nowrap;width: 145px;" role="tab" data-toggle="tab" onclick="list('2');" id="otherTemplateCount">其他模板商品</a></li>
  </ul>

<!--   Tab panes -->
<div class="clearfix"></div>
<div class="x_content container-fluid">
	<div class="row">
		<div class="col-md-12 datatable-container">
			<div class="datatable-caption">
				<span class="mr">
					<input type='checkbox' class='checkAll' onclick='selectAll();' value='ccc'>全选
				</span>
				<c:if test="${searchTemplateType == 0}">
					<button class="btn btn-all" onclick="batchOutFreightTemplate();">退出模板</button>
				</c:if>
				<c:if test="${searchTemplateType == 1 || searchTemplateType == 2}">
					<button class="btn btn-all" onclick="batchSelectFreightTemplate();">选择模板</button>
				</c:if>
			</div>
			<table id="datatable"
				class="table table-striped table-bordered dataTable no-footer"
				role="grid" aria-describedby="datatable_info">
			</table>
		</div>
	</div>
</div>

</div>
		
<!-- 	售后详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

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
<!-- 	订单详情 -->

<div class="modal fade" id="subOrderViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>

	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script>
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
                url: '${ctx}/freightTemplate/getFreightTemplateProductList',
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
                    
                    if(result.returnData.currentTemplateCount>0){
	                    $("#currentTemplateCount").text("当前运费模板商品("+result.returnData.currentTemplateCount+")");
                    }
                    if(result.returnData.noTemplateCount>0){
	                    $("#noTemplateCount").text("未分配商品("+result.returnData.noTemplateCount+")");
                    }
                    if(result.returnData.otherTemplateCount>0){
	                    $("#otherTemplateCount").text("其他模板商品("+result.returnData.otherTemplateCount+")");
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
            {"data": "id","title":"商品信息","render": function (data, type, row, meta) {
            	var html = [];
            	html.push('<div class="is-check">');
            	html.push('<div class="width-33"><input type="checkbox" class="checkRow"  value="' + data + '" onclick="checkSelectAll(this);"/></div>');
            	html.push('<div class="width-60"><img src="${ctx}/file_servelt'+row.pic+'"></div>');
            	html.push('<div class="width-226"><p class="h34" style="height: 20px;">'+row.name+'</p><div><span style="float: left; margin: 0;">货号：'+row.artNo+'</span><br><span style="float: left; margin: 0;">ID：'+row.code+'</span></div></div>');
            	html.push('<div>');
            	return html.join("");
            }},
            <c:if test="${searchTemplateType == 2}">
            {"data": "id",width:"108","title":"运费模板名称","render": function (data, type, row, meta) {
            	return row.freightTempletName;
            }},
            </c:if>
            {"data": "remarks", "width":"134", "title":"商家备注","className": "remarks_column_class","render": function (data, type, row, meta) {
            	var html = [];
            	html.push("<span onclick='setRemarks("+row.id+',"'+row.remarksColor+'"'+',"'+row.remarks+'"'+");' class='t-flag glyphicon glyphicon-flag "+"color-"+row.remarksColor+"' aria-hidden='true'></span>");
            	html.push(data);
            	return html.join("");
            }},
            {"data": "id",width:"88","title":"商城价","render": function (data, type, row, meta) {
				var html = [];
				
				html.push(row.mallPriceMin);
				
				if(row.mallPriceMin!=row.mallPriceMax){
					html.push("-");
					html.push(row.mallPriceMax);
				};
			    return html.join("");
            }},
           
            {"data": "id",width:"108","title":"库存","render": function (data, type, row, meta) {
            	return ""+row.stock;
            }},
            {"data": "id",width:"108","title":"状态","render": function (data, type, row, meta) {
            	var html = [];
            	if(row.status == "0"){
            		html.push("下架<br>");
            	}else{
            		html.push("上架");
            	}
            	return html.join("");
            }},
            {"data": "id",width:"69","title":"操作","render": function (data, type, row, meta) {
            	var html = [];
            	if(!row.freightTemplateId){
            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='selectFreightTemplate("+row.id+")' >选择模板</a>");	
            	}else{
            		<c:if test="${searchTemplateType == 0}">
	            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='outFreightTemplate("+row.id+")' >退出模板</a>");	
            		</c:if>
            		<c:if test="${searchTemplateType == 1 || searchTemplateType == 2}">
	            		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='selectFreightTemplate("+row.id+")' >选择模板</a>");	
            		</c:if>
            	}
            	return  html.join("");
            }}
        ]
    }).api();
	
	$('#btn-query-pop').on('click', function (event) {
        // $("#popDiv").removeClass("hidden");
        $("#popDivs").modal();
    });

    $("#closeDiv").click(function(){
        $("#popDiv").addClass("hidden");
    });
    
    $('#btn-query-arts').on('click', function (event) {
        $("#searchKeywrod").val("");
        table.ajax.reload();
    });
    
});

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

function list(searchTemplateType) {
    var url = "${ctx}/freightTemplate/freightTemplateProductIndex?searchTemplateType=" + searchTemplateType+"&freightTemplateId=${freightTemplateId}";
    getContent(url);
}

function selectAll(){
    var check = $(".checkAll").prop("checked");
    $(".checkRow").prop("checked", check);
}

function batchOutFreightTemplate(){
	var length = $("input[class='checkRow']:checked").length;
	if(length == 0){
		swal("请先选中要退出模板的商品");
		return;
	}
	var productIds = "";
	$("input[class='checkRow']:checked").each(function(index){
		if(index!=length-1){
			productIds+=$(this).val()+",";
		}else{
			productIds+=$(this).val();
		}
	});
	var freightTemplateId = $("#freightTemplateId").val();
	$.ajax({
        method: 'POST',
        url: '${ctx}/freightTemplate/outFreightTemplate',
        data: {"productIds":productIds,"freightTemplateId":freightTemplateId},
        dataType: 'json'
    }).done(function (result) {
        if (result.returnCode =='0000') {
        	swal("操作成功");
        	table.ajax.reload(null, false);
        	if(result.returnData.currentTemplateCount>=0){
        		$("#currentTemplateCount").text("当前运费模板商品("+result.returnData.currentTemplateCount+")");
        	}
        }else{
        	if(result.returnMsg){
	        	swal(result.returnMsg);
        	}else{
        		swal("操作失败，请稍后再试");
        	}
        }
    });
}

function outFreightTemplate(productIds){
	var freightTemplateId = $("#freightTemplateId").val();
	$.ajax({
        method: 'POST',
        url: '${ctx}/freightTemplate/outFreightTemplate',
        data: {"productIds":productIds,"freightTemplateId":freightTemplateId},
        dataType: 'json'
    }).done(function (result) {
        if (result.returnCode =='0000') {
        	swal("操作成功");
        	table.ajax.reload(null, false);
        }else{
        	if(result.returnMsg){
	        	swal(result.returnMsg);
        	}else{
        		swal("操作失败，请稍后再试");
        	}
        }
    });
}

function batchSelectFreightTemplate(){
	var length = $("input[class='checkRow']:checked").length;
	if(length == 0){
		swal("请先选中要选择模板的商品");
		return;
	}
	var productIds = "";
	$("input[class='checkRow']:checked").each(function(index){
		if(index!=length-1){
			productIds+=$(this).val()+",";
		}else{
			productIds+=$(this).val();
		}
	});
	var freightTemplateId = $("#freightTemplateId").val();
	var searchTemplateType = $("#searchTemplateType").val();
	if(searchTemplateType == 2){
		swal({
			  title: "确认将选中商品替换为运费模板${freightTemplateName}?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: false
			},
			function(){
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/freightTemplate/selectFreightTemplate',
			        data: {"productIds":productIds,"freightTemplateId":freightTemplateId},
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	swal("操作成功");
			        	table.ajax.reload(null, false);
			        }else{
			        	if(result.returnMsg){
				        	swal(result.returnMsg);
			        	}else{
			        		swal("操作失败，请稍后再试");
			        	}
			        }
			    });
			});
	}else{
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/freightTemplate/selectFreightTemplate',
	        data: {"productIds":productIds,"freightTemplateId":freightTemplateId},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	swal("操作成功");
	        	table.ajax.reload(null, false);
	        }else{
	        	if(result.returnMsg){
		        	swal(result.returnMsg);
	        	}else{
	        		swal("操作失败，请稍后再试");
	        	}
	        }
	    });
	}
}

function selectFreightTemplate(productIds){
	var freightTemplateId = $("#freightTemplateId").val();
	var searchTemplateType = $("#searchTemplateType").val();
	if(searchTemplateType == 2){
		swal({
			  title: "确认将选中商品替换为运费模板${freightTemplateName}?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonText: "确定",
			  cancelButtonText: "取消",
			  closeOnConfirm: false
			},
			function(){
				$.ajax({
			        method: 'POST',
			        url: '${ctx}/freightTemplate/selectFreightTemplate',
			        data: {"productIds":productIds,"freightTemplateId":freightTemplateId},
			        dataType: 'json'
			    }).done(function (result) {
			        if (result.returnCode =='0000') {
			        	swal("操作成功");
			        	table.ajax.reload(null, false);
			        }else{
			        	if(result.returnMsg){
				        	swal(result.returnMsg);
			        	}else{
			        		swal("操作失败，请稍后再试");
			        	}
			        }
			    });
			});
	}else{
		$.ajax({
	        method: 'POST',
	        url: '${ctx}/freightTemplate/selectFreightTemplate',
	        data: {"productIds":productIds,"freightTemplateId":freightTemplateId},
	        dataType: 'json'
	    }).done(function (result) {
	        if (result.returnCode =='0000') {
	        	swal("操作成功");
	        	table.ajax.reload(null, false);
	        }else{
	        	if(result.returnMsg){
		        	swal(result.returnMsg);
	        	}else{
	        		swal("操作失败，请稍后再试");
	        	}
	        }
	    });
	}
	
}

function checkSelectAll(_this){
	var checked = $(_this).prop("checked");
	var length = $("input[class='checkRow']").length;
	var checkedLength = $("input[class='checkRow']:checked").length;
	if(!checked){
		$(".checkAll").prop("checked",false);
	}else{
		if(length == checkedLength){
			$(".checkAll").prop("checked",true);
		}else{
			$(".checkAll").prop("checked",false);
		}
	}
}

$('#btn-query').on('click', function (event) {
    table.ajax.reload();
});
</script>
</body>
</html>