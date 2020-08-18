<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>店铺资质信息</title>
	<style type="text/css">
		body{
			font-size: 14px;
		}
		.hidden{
			display:none;
		}
		.row-body{
			padding:8px;
		}
		.col-left{
			text-align: right;
		}
		.color-red{
			color:#f00;
		}
		.reject{
			padding-top:10px;
		}
	</style>
</head>

<body>
<div class="x_panel container-fluid">
	<div class="row content-title">
		<div class="col-md-12">入驻及合同管理</div>
	</div>

	<div class="x_content container-fluid">
		<div class="row" style="padding:20px;">
			<div class="col-md-3 color-red">第一步：完善店铺资质</div>
			<div class="col-md-3">第二步：签署入驻合同</div>
		</div>
		<c:if test="${mchtInfo.totalAuditStatus == 3}">
		<div class="row" style="padding:20px;">
			<div class="col-md-9 color-red">
				对不起，您提交的入驻资料被驳回，请认真检查资料并修改，重新提交。
				<br/>驳回原因：${mchtInfo.totalAuditRemarks}
			</div>
		</div>
		</c:if>
		<div class="row row-body">
			<div class="col-md-3 col-left">工商主体信息：</div>
			<div class="col-md-6">
				<c:if test="${mchtInfo.isCompanyInfPerfectDesc.equals('待审核')}">已填写</c:if>
				<c:if test="${!mchtInfo.isCompanyInfPerfectDesc.equals('待审核')}">${mchtInfo.isCompanyInfPerfectDesc}</c:if>
				<a href="javascript:showInfo('${ctx}/mcht/mchtInfoPerfect?mchtId=${mchtInfo.id}')">【查看详情】</a>
				<c:if test="${mchtInfo.isCompanyInfPerfect == 4}">
				<div class="reject color-red">原因：${mchtInfo.companyInfAuditRemarks}</div>
				</c:if>
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-3 col-left">店铺信息：</div>
			<div class="col-md-6">
				${shopNamePerfectDesc}
				<a href="javascript:showInfo('${ctx}/mchtUser/changeShopInfo')">【查看详情】</a>
				<c:if test="${shopNamePerfectDesc.equals('驳回')}">
					<div class="reject color-red">原因：${mchtInfo.shopNameAuditRemarks}</div>
				</c:if>
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-3 col-left">结算银行信息：</div>
			<div class="col-md-6">
				${mchtBankAccountPerfectDesc}
				<a href="javascript:showInfo('${ctx}/mchtBankAccount/mchtBankAccountEdit?id=${mchtBankAccount.id}&isReload=1')">【查看详情】</a>
				<c:if test="${mchtBankAccountPerfectDesc.equals('驳回')}">
					<div class="reject color-red">原因：${mchtBankAccount.auditRemarks}</div>
				</c:if>
			</div>
		</div>
<!-- 	
		<div class="row row-body">
			<div class="col-md-3 col-left">税务开票信息：</div>
			<div class="col-md-6">
				${mchtTaxInvoiceInfoPerfectDesc}
				<a href="javascript:showInfo('${ctx}/mchtTaxInvoice/mchtTaxInvoiceEdit')">【查看详情】</a>
				<c:if test="${mchtTaxInvoiceInfoPerfectDesc.equals('驳回')}">
					<div class="reject color-red">原因：${mchtTaxInvoiceInfo.auditRemarks}</div>
				</c:if>
			</div>
		</div>
-->		<c:set var="isOk" value="${isPerfect && ( (empty mchtInfo.totalAuditStatus) || mchtInfo.totalAuditStatus == 3|| mchtInfo.totalAuditStatus == 4 || mchtInfo.totalAuditStatus == 0)}"/>
		<div class="row row-body">
			<div class="col-md-3 col-left">经营品牌：</div>
			<div class="col-md-6">
				<c:if test="${empty mchtInfo.zsTotalAuditStatus || mchtInfo.zsTotalAuditStatus == 4 || mchtInfo.zsTotalAuditStatus == 3}">
					<a href="javascript:showInfo('${ctx}/mcht/addMchtBrand?brandSource=1')">【添加品牌】</a>
				</c:if>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>品牌名称</th>
							<th>招商审核状态</th>
							<th>资料审核状态</th>
							<th>运营状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="productBrand" items="${productBrandList}" varStatus="ind" >
							<tr>
								<td>${productBrand.productBrandName}</td>
								<td>${productBrand.get("zsAuditStatusDesc")}</td>
								<td>${productBrand.get("auditStatusDesc")}</td>
								<td>
									<c:if test="${productBrand.status == 0}">
										申请中
									</c:if>
									<c:if test="${productBrand.status == 1}">
										正常
									</c:if>
									<c:if test="${productBrand.status == 2}">
										暂停
									</c:if>
									<c:if test="${productBrand.status == 3}">
										关闭
									</c:if>
									<c:if test="${productBrand.status == 4}">
										驳回申请
									</c:if>
								</td>
								<td>
									<c:if test="${productBrand.zsAuditStatus == 0 || productBrand.zsAuditStatus == 4 || productBrand.auditStatus == 4}">
										<a href="javascript:showInfo('${ctx}/mcht/mchtBrandPerfect?mchtBrandId=${productBrand.id}')">【修改】</a>
									</c:if>
									<c:if test="${productBrand.zsAuditStatus != 4 && productBrand.zsAuditStatus != 5 && productBrand.zsAuditStatus != 6 && productBrand.auditStatus != 4 && productBrand.auditStatus != 5 && productBrand.auditStatus != 6}">
										<a href="javascript:showInfo('${ctx}/mcht/mchtBrandView?mchtBrandId=${productBrand.id}')">【查看详情】</a>
									</c:if>
									<c:if test="${productBrand.zsAuditStatus == 5 || productBrand.zsAuditStatus == 6 || productBrand.auditStatus == 5 || productBrand.auditStatus == 6}">
										请与招商联系
									</c:if>
									<c:if test="${productBrand.zsAuditStatus == 4}">
										<a href="javascript:;" onclick="deleteProductBrand(${productBrand.id});">【删除】</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-3 col-left">店铺负责人：</div>
			<div class="col-md-6">
				<c:if test="${empty contactAll}">未填写
				<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactAdd?contactType=1&writeType=1')">【查看详情】</a>
				</c:if>
				<c:if test="${not empty contactAll}">已填写
				<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactEdit?contactType=1&id=${contactAll.id}&writeType=1')">【查看详情】</a>
				</c:if>
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-3 col-left">商家运营：</div>
			<div class="col-md-6">
				<c:if test="${empty contactOperation}">未填写
				<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactAdd?contactType=2&writeType=1')">【查看详情】</a>
				</c:if>
				<c:if test="${not empty contactOperation}">已填写
				<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactEdit?contactType=2&id=${contactOperation.id}&writeType=1')">【查看详情】</a>
				</c:if>
			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-3 col-left">订单对接人：</div>
			<div class="col-md-6">
				<c:if test="${empty contactOrder}">
					未填写
					<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactAdd?contactType=3&writeType=1')">【查看详情】</a>
				</c:if>
				<c:if test="${not empty contactOrder}">
					已填写
					<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactEdit?contactType=3&id=${contactOrder.id}&writeType=1')">【查看详情】</a>
				</c:if>

			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-3 col-left">售后对接人：</div>
			<div class="col-md-6">
				<c:if test="${empty contactCustomerService}">
					未填写
					<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactAdd?contactType=4&writeType=1')">【查看详情】</a>
				</c:if>
				<c:if test="${not empty contactCustomerService}">
					已填写
					<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactEdit?contactType=4&id=${contactCustomerService.id}&writeType=1')">【查看详情】</a>
				</c:if>

			</div>
		</div>
		<div class="row row-body">
			<div class="col-md-3 col-left">客服对接人：</div>
			<div class="col-md-6">
				<c:if test="${empty contactCustomer}">
					未填写
					<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactAdd?contactType=6&writeType=1')">【查看详情】</a>
				</c:if>
				<c:if test="${not empty contactCustomer}">
					已填写
					<a href="javascript:showInfo('${ctx}/mchtContact/mchtContactEdit?contactType=6&id=${contactCustomer.id}&writeType=1')">【查看详情】</a>
				</c:if>

			</div>
		</div>
		<div class="row" style="padding:20px; text-align: center;">
			<c:if test="${!isOk}">
				<c:if test="${empty mchtInfo.zsTotalAuditStatus || mchtInfo.zsTotalAuditStatus == 4} ">
					<div style="color: red;">上述项目全部填写完成后，才能提交申请</div>
				</c:if>
				<button class="btn btn-default" disabled="disabled" onclick="javascript:void(0);" style="background-color: gray;">提交审核</button>
			</c:if>
			<c:if test="${isOk && (mchtInfo.zsTotalAuditStatus == 0 || mchtInfo.totalAuditStatus == 0)}">
				<div style="color: red;">您的资料已提交审核，请您耐心等待，如有审核结果，会第一时间通知,请保持手机畅通。手机号<span class="phonehide">${linkMobile}</span></div>
			</c:if>
			<c:if test="${mchtInfo.zsTotalAuditStatus == 3 }">
				<div style="color: red;">${mchtInfo.zsTotalAuditRemarks}</div>
			</c:if>
			<c:if test="${mchtInfo.totalAuditStatus == 3 }">
				<div style="color: red;">${mchtInfo.totalAuditRemarks}</div>
			</c:if>
			<c:if test="${isOk}">
				<c:if test="${empty mchtInfo.zsTotalAuditStatus || mchtInfo.zsTotalAuditStatus == 4 || mchtInfo.zsTotalAuditStatus == 3 || mchtInfo.totalAuditStatus == 3 }">
					<button class="btn btn-info" onclick="commit();">提交审核</button>
				</c:if>
				<c:if test="${not empty mchtInfo.zsTotalAuditStatus && mchtInfo.zsTotalAuditStatus != 4 && mchtInfo.zsTotalAuditStatus != 3 && mchtInfo.zsTotalAuditStatus != 5 && mchtInfo.zsTotalAuditStatus != 6 && mchtInfo.totalAuditStatus != 3 && mchtInfo.totalAuditStatus != 5 && mchtInfo.totalAuditStatus != 6}">
					<button class="btn btn-info" disabled="disabled" onclick="javascript:void(0);" style="background-color: gray;">提交审核</button>
				</c:if>
			</c:if>
			<c:if test="${mchtInfo.zsTotalAuditStatus == 5 || mchtInfo.zsTotalAuditStatus == 6 || mchtInfo.totalAuditStatus == 5 || mchtInfo.totalAuditStatus == 6}">
				<div style="color: red;">当前入驻异常，请您与招商联系</div>
			</c:if>
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
<script>

	$(document).ready(function () {
		$('#btn-query').on('click', function (event) {
			table.ajax.reload();
		});

		phonehide();
	});

	function phonehide(){
			$(".phonehide").each(function(i){
					var tel = "";
					var phone = "";
					if($(this).attr("type")){
						phone = $(this).val();
					}else{
						phone = $(this).html();
					}
					if(phone.length == 11){
						tel = phone.substr(0, 3) + '****' + phone.substr(7);
					}
					if(phone.length > 3 && phone.length <= 7){
						tel = phone.substr(0, 3) + '****';
					}
					if(phone.length == 12){
						tel = phone.substr(0, 6) + '****' + phone.substr(9);
					}
					if(phone.length == 13){
						tel = phone.substr(0, 6) + '****' + phone.substr(10);
					}
					if(phone.length == 14){
						tel = phone.substr(0, 6) + '****' + phone.substr(11);
					}
					if(phone.length == 15){
						tel = phone.substr(0, 7) + '****' + phone.substr(12);
					}
	                if(phone.length == 16){
						tel = phone.substr(0, 8) + '****' + phone.substr(13);
					}
					if($(this).attr("type")){
						$(this).val(tel);
					}else{
						$(this).html(tel);
					}
			})
	}

	var isReload=false;
	$(function(){
		$('#viewModal').on('hidden.bs.modal', function (e) {
			if(isReload){
				$.ajax({
			        url: "${ctx}/mcht/contract", 
			        type: "GET", 
			        success: function(data){
			            $("#main-content").html(data);
			            isReload=false;
			        },
				    error:function(){
				    	swal('页面不存在');
				    }
				});
			}
			});
	});
	
	
	
	
	function showInfo(url){
		if(url == "")	return;
		$.ajax({
			url: url, 
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

	function commit() {
		var dataJson= {};
		//dataJson.push({"name":"json", "value":"xxx"});
		$.ajax({
			url: "${ctx}/mcht/contract/mchtInfoCommit",
			type: 'POST',
			//ontentType : 'application/json;charset=utf-8', //设置请求头信息
			dataType: 'json',
			cache: false,
			async: false,
			data: dataJson,
			timeout: 30000,
			success: function (result) {
				if (result.success) {
					swal({
						title: "提交成功!",
						type: "success",
						confirmButtonText: "确定"
					});
					getContent("${ctx}/mcht/contract");
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
					title: "提交失败！",
					type: "error",
					confirmButtonText: "确定"
				});
			}
		});

	}
	
	function deleteMchtProductType(productTypeId,mchtId){
		$.ajax({
			url: "${ctx}/mcht/contract/deleteMchtProductType",
			type: 'POST',
			dataType: 'json',
			cache: false,
			async: false,
			data: {productTypeId:productTypeId,mchtId:mchtId},
			timeout: 30000,
			success: function (result) {
				if (result.success) {
					swal({
						title: "删除成功!",
						type: "success",
						confirmButtonText: "确定"
					});
					getContent("${ctx}/mcht/contract");
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

	function deleteProductBrand(productBrandId) {
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
					getContent("${ctx}/mcht/contract");
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
</script>
</body>
</html>
