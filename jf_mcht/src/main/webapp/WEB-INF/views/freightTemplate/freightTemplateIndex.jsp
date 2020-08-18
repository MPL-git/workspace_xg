<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>运费模版管理</title>
</head>

<body>
	<div class="x_panel container-fluid">
		<div class="row content-title">
			<div class="t-title">运费模版管理<a href='javascript:void(0);' onclick="editFreightTemplate('');">添加运费模板</a></div>
		</div>
		<div class="search-container container-fluid"></div>
		<div class="clearfix"></div>
		<c:forEach items="${freightTemplateCustoms}" var="freightTemplateCustom">
		<div class="row">
			<div style="padding-bottom: 5px;">
				<span style="margin-left: 10px;font-size: small;">${freightTemplateCustom.name}</span>
				<a href="javascript:;" class="btn btn-default" style="margin-left: 25px;" onclick="freightTemplateProduct(${freightTemplateCustom.id});">管理商品</a>
				<span style="margin-right: 10px;float: right;color: gray;">
					最后编辑时间：<fmt:formatDate value="${freightTemplateCustom.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
					<a href="javascript:;" class="btn btn-default" onclick="editFreightTemplate(${freightTemplateCustom.id});">修改</a>
					<a href="javascript:;" class="btn btn-default" onclick="deleteFreightTemplate(${freightTemplateCustom.id},this);">删除</a>
				</span>
			</div>
			<div class="col-md-12 datatable-container">
				<table id="datatable"
					class="table table-bordered dataTable no-footer"
					role="grid" aria-describedby="datatable_info">
					<thead>
						<tr role="row" class="active">
							<th style="height: 35px;">运送范围</th>
							<th>首件运费（元）</th>
							<th>增件运费（元/件）</th>
							<th>运费满减</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${freightTemplateCustom.provinceFreightCustoms}" var="provinceFreightCustom">
						<tr>
					      <td style="width: 610px;">${provinceFreightCustom.areaNames}</td>
					      <td style="width: 150px;">${provinceFreightCustom.firstFreight}</td>
					      <td >${provinceFreightCustom.additionalFreight}</td>
						  <c:if test="${empty freightTemplateCustom.fullReductionFreight}">
							  <td ></td>
						  </c:if>
						  <c:if test="${freightTemplateCustom.fullReductionFreight eq 0}">
							  <td >满${freightTemplateCustom.fullNumber}包邮</td>
						  </c:if>
  						</tr>
  						</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
		</c:forEach>
	</div>
	
	
<!-- 	查看信息框 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
</div>
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<script>
$(document).ready(function () {
	
   
    
});
function editFreightTemplate(id){
	$.ajax({
        url: "${ctx}/freightTemplate/editFreightTemplate?id="+id, 
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

function deleteFreightTemplate(id,_this){
	swal({
		  title: "确定要删除该模板吗?",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonText: "确定",
		  cancelButtonText: "取消",
		  closeOnConfirm: false
		},
		function(){
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/freightTemplate/deleteFreightTemplate',
		        data: {"freightTemplateId":id},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		           	swal(result.returnMsg);
		           	swal.close();
		           	$(_this).closest("div").parent().remove();
		        }else{
		        	if(result.returnMsg){
			        	swal(result.returnMsg);
		        	}else{
			        	swal("请求失败，请稍后重试");
		        	}
		        }
		    });
		});
}

function freightTemplateProduct(freightTemplateId){
	var url = "${ctx}/freightTemplate/freightTemplateProductIndex?freightTemplateId=" + freightTemplateId+"&searchTemplateType=0";
    getContent(url);
}
</script>
</body>
</html>
