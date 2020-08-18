<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<style type="text/css">
		.lineFeed {
			width: 395px;
			word-wrap: break-word;
		}
	</style>
<title>品牌管理</title>
</head>

<body>
	<div class="x_panel container-fluid pp-gl">
		<div class="row content-title">
			<div class="t-title">品牌管理
			<a href='javascript:void(0);' onclick="addMchtBrand();">添加</a>
			</div>
		</div>
		<div class="search-container container-fluid">
			<form class="form-horizontal" id="searchForm">
				<div class="form-group">
					<label for="productType" class="col-md-1 control-label search-lable">品类：</label>
					<div class="col-md-2 search-condition">
						   <select class="form-control" name="productType" id="productType">
						   <option value="">--请选择--</option>
						   <c:forEach var="productType" items="${productTypes }">
						   <option value="${productType.productTypeId}">${productType.productTypeName}</option>
						   </c:forEach>
                          </select>
					</div>
					
					<label for="zsAuditStatus" class="col-md-1 control-label search-lable">招商审核状态：</label>
					<div class="col-md-2 search-condition">
						<select class="form-control" name="zsAuditStatus" id="zsAuditStatus">
						   <option value="">--请选择--</option>
						   <option value="0">未提交</option>
						   <option value="01">首次提审</option>
						   <option value="11">重新提审</option>
						   <option value="2">通过</option>
						   <option value="4">驳回</option>
						   <option value="56">异常</option>
                         </select>
					</div>
					
					<label for="auditStatus" class="col-md-1 control-label search-lable">资料审核状态：</label>
					<div class="col-md-2 search-condition">
						<select class="form-control" name="auditStatus" id="auditStatus">
						   <option value="">--请选择--</option>
						   <option value="0">未提交</option>
						   <option value="1">待审</option>
						   <option value="3">通过</option>
						   <option value="4">驳回</option>
						   <option value="56">异常</option>
                        </select>
					</div>
					
					<label for="status" class="col-md-1 control-label search-lable">运营状态：</label>
					<div class="col-md-2 search-condition">
						 <select class="form-control" name="status" id="status">
						   <option value="">--请选择--</option>
						   <c:forEach var="mchtProductBrandStatus" items="${mchtProductBrandStatusList }">
						   <option value="${mchtProductBrandStatus.statusValue}">${mchtProductBrandStatus.statusDesc}</option>
						   </c:forEach>
                          </select>
					</div>
				</div>
				<div class="form-group">	
					<label for="status" class="col-md-1 control-label search-lable">资料归档状态：</label>
					<div class="col-md-2 search-condition">
						 <select class="form-control" name="archiveStatus" id="archiveStatus">
						   <option value="">--请选择--</option>
						   <option value="0">未寄出</option>
						   <option value="1">待处理</option>
						   <option value="2">未齐全</option>
						   <option value="3">已齐全</option>
                         </select>
					</div>

					<label for="searchKeyWord" class="col-md-1 control-label search-lable">关键词：</label>
					<div class="col-md-2 search-condition">
						<input type="text" class="form-control" id="searchKeyWord" name="searchKeyWord">
					</div>

					<div class="col-md-3 text-center search-btn col-md-offset-6">
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
									<th>品牌LOGO</th>
									<th>品牌</th>
									
									<c:if test="${sessionScope.mchtInfo.mchtType=='1'}">
									 <th>定价方式</th> 
									</c:if>
									
									<c:if test="${sessionScope.mchtInfo.mchtType=='2'}">
									 <th>佣金比例</th> 
									</c:if>
									
									
									<th>资质类型</th>
									<th>授权期限</th>
									<th>招商审核状态</th>
									<th>资料审核状态</th>
									<th>资料归档状态</th>
									<th>运营状态</th>
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
<!-- 	查看信息框 -->
	<div class="modal fade" id="myViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
	 data-backdrop="static">
	</div>

<script type="text/javascript">
function viewBrandAptitude(id){
	$.ajax({
        url: "${ctx}/mcht/mchtBrand/viewBrandAptitude?mchtProductBrandId="+id, 
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

function toSend(id){
	$.ajax({
	       url: "${ctx}/mcht/mchtBrand/toSend?id="+id, 
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
	
	
function addMchtBrand(){
	
	$.ajax({
        url: "${ctx}/mcht/addMchtBrand?brandSource=2", 
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
function editMchtBrand(id){
	
	$.ajax({
        url: "${ctx}/mcht/mchtBrandPerfect?mchtBrandId="+id, 
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
	
function applayMchtBrand(id){
	getContent("${ctx}/mcht/mchtBrandChgApplyIndex?mchtBrandId="+id);
}
	
function delMchtBrand(productBrandId) {
	$.ajax({
		url: "${ctx}/mcht/contract/deleteProductBrand",
		type: 'POST',
		dataType: 'json',
		cache: false,
		async: false,
		data: {productBrandId : productBrandId},
		timeout: 30000,
		success: function (result) {
			if (result.success) {
				swal({
					title: "删除成功!",
					type: "success",
					confirmButtonText: "确定"
				});
				table.ajax.reload(null, false);
			} else {
				swal({
					title: result.returnMsg,
					type: "error",
					confirmButtonText: "确定"
				});
			}
		},
		error: function () {
			swal({
				title: "删除失败！",
				type: "error",
				confirmButtonText: "确定"
			});
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
                url: '${ctx}/mcht/getMchtBrandList',
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
            {"data": "logo", "width": "178", "class":"pp-img", "render": function (data, type, row, meta) {
            	if(row.productBrandLogo){
	                return "<img style='width:158px;height:68px;' src='${ctx}/file_servelt"+row.productBrandLogo+"'>";
            	}else{
	                return "<img style='width:158px;height:68px;' src='${ctx}/file_servelt"+row.logo+"'>";
            	}
            }},
            {"data": "brandName","render": function (data, type, row, meta) {
               var html = [];	
               if(row.brandName){
            	   html.push("<div class='lineFeed'>"+row.brandName+"</div>");
               }else{
            	   html.push("<div class='lineFeed'>"+row.productBrandName+"</div>");
               }
               if(status == 1 && auditStatus!=3){
            	   html.push('<br><span style="color:red;">资料更新中</span>');
               }
               return html.join("");
            }},
            {"data": "priceModel","render": function (data, type, row, meta) {
               if(${sessionScope.mchtInfo.mchtType}=='1'){
            	   return row.priceModelStatusDesc;
               }else if(${sessionScope.mchtInfo.mchtType}=='2'){
            	   if(row.popCommissionRate){
	            	   return row.popCommissionRate;
            	   }else{
            		   return "";
            	   }
               }
            }},
            {"data": "aptitudeTypeDesc"},
            {"data": "platformAuthExpDate","render": function (data, type, row, meta) {
            	if(data){
	          	   var platformAuthExpDate=new Date(data);
	        	   return platformAuthExpDate.format("yyyy-MM-dd");
            	}else{
				   return "";            		
            	}
            }},
            {"data": "zsAuditStatus","render": function (data, type, row, meta) {
          	   if(!row.zsAuditStatus || row.zsAuditStatus == 0){
          		   return "未提交";
          	   }else if(row.zsAuditStatus == 1){
          		   if(row.isZsAuditRecommit == 0){
          			   return "首次提审";
          		   }else if(row.isZsAuditRecommit == 1){
          			   return "重新提审";
          		   }else{
          			 return "首次提审";
          		   }
          	   }else if(row.zsAuditStatus == 2){
          		 return "通过";
          	   }else if(row.zsAuditStatus == 4){
          		 return "驳回";
          	   }else if(row.zsAuditStatus == 5 || row.zsAuditStatus == 6){
          		 return "异常";
          	   }else{
          		 return "";
          	   }
            }},
            {"data": "auditStatusDesc","render": function (data, type, row, meta) {
          	   if(!row.auditStatus || row.auditStatus == 0){
          		   return "未提交";
          	   }else if(row.auditStatus == 1){
          		 	return "待审";
          	   }else if(row.auditStatus == 3){
          		 	return "通过";
          	   }else if(row.auditStatus == 4){
          		 	return "驳回";
          	   }else if(row.auditStatus == 5 || row.auditStatus == 6){
          		 	return "异常";
          	   }else{
          		 	return "未提交";
          	   }
            }},
            {"data": "archiveStatus","render": function (data, type, row, meta) {
          	   if(!row.archiveStatus || row.archiveStatus == 0){
          		   return "未寄出";
          	   }else if(row.archiveStatus == 1){
          		 	return "待处理";
          	   }else if(row.archiveStatus == 2){
          		 	return "未齐全";
          	   }else if(row.archiveStatus == 3){
          		 	return "已齐全";
          	   }
            }},
            {"data": "statusDesc"},
            {"data": "auditStatus","render": function (data, type, row, meta) {
            	if(row.zsAuditStatus == 5 || row.zsAuditStatus == 6 || row.auditStatus == 5 || row.auditStatus == 6 || row.status == 4){
            		return "请与招商联系";
            	}else{
            		var returnHtml = [];
            		if(row.zsAuditStatus!=4 && row.auditStatus!=4){//查看：招商状态！=驳回OR招商状态！=异常 OR 法务状态！=驳回 OR法务审核状态！=异常
            			returnHtml.push('<a href="javascript:;" onclick="viewMchtBrand('+row.id+')">查看</a><br>');
            		}
            		
            		if(row.status == 1 && row.auditStatus!=3){//查看更新:运营状态=正常且 申请更新后 法务审核状态！=通过
            			returnHtml.push('<a href="javascript:;" onclick="applayMchtBrand('+row.id+')">查看更新</a><br>');
            		}
            		if(row.archiveStatus == 0 && row.auditStatus==3){//立即寄件：法务审核状态（资料审核状态）=通过and（资料归档状态）=未寄出
            			returnHtml.push('<a href="javascript:;" onclick="toSend('+row.id+')">立即寄件</a><br>');
            		}
            		
            		if(row.auditStatus==3 && row.archiveStatus!=3){//查看品牌资质内容：资料审核状态（法务审核状态）=通过and 资料归档状态！=已齐全
            			returnHtml.push('<a href="javascript:;" onclick="viewBrandAptitude('+row.id+')">查看品牌资质内容</a><br>');
            		}
            		
            		if(row.auditStatus == 3 && (row.status == 1 || row.status == 2)){//申请更新：运营状态=正常OR运营状态=暂停 且 不存在更新
            			returnHtml.push('<a href="javascript:;" onclick="applayMchtBrand('+row.id+')">申请更新</a><br>');
            		}
            		
            		if(row.status == 3 || row.zsAuditStatus == 4){//删除：运营状态=关闭,招商驳回
            			returnHtml.push('<a href="javascript:;" onclick="delMchtBrand('+row.id+')">删除</a><br>');
            		}
            		
            		if(row.zsAuditStatus==4 || row.auditStatus==4){//修改：招商状态=驳回OR 资料审核状态=驳回（法务审核状态）
            			returnHtml.push('<a href="javascript:;" onclick="editMchtBrand('+row.id+')">修改</a><br>');
            		}
	                return returnHtml.join("");
            	}
            }}
        ]
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
    $("#searchKeyWord").keydown(function(e){
    	if(e.keyCode==13){
    		table.ajax.reload();
            return false;
    	}
    });
});
</script>
</body>
</html>
