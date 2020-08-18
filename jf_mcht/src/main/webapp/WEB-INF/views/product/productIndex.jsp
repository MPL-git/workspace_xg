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
.conBox{
	float: left;
}
.conBox input{
	float: left;
}
.remarks_column_class{
text-align: left;
}
</style>
</head>

<body>
	<div class="x_panel container-fluid">

		<div class="row content-title">
			<div class="t-title">
				商品管理
				<a href='javascript:void(0);' onclick="addProduct(${saleType});" >添加</a>
			</div>
		</div>
		<div class="search-container container-fluid">
				<input type="hidden" name="pageNumber" id="pageNumber" value="${pageNumber}">
				<form class="form-horizontal" id="searchForm">
				<input type="hidden" name="search_saleType" value="${saleType}" id="search_saleType">
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


<!-- 					<label for="productBrand" class="col-md-1 control-label search-lable">更新时间</label> -->
<!-- 					<div class="col-md-2 search-condition" style="width:13.583325%"> -->
<!-- 						 <input class="form-control datePicker" type="text"  id="updateTimeBegin" name="updateTimeBegin" data-date-format="yyyy-mm-dd"> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-1 search-condition text-center" style="width:2%;padding:5px 0;"> -->
<!-- 						<span>--</span> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-2 search-condition" style="width:13.583325%"> -->
<!-- 						 <input class="form-control datePicker" type="text"  id="updateTimeEnd" name="updateTimeEnd" data-date-format="yyyy-mm-dd"> -->
<!-- 					</div> -->


					
<!-- 				</div> -->
				
				<div class="form-group">
				   <label for="activityStatus" class="col-md-1 control-label search-lable">商品搜索：</label>
				   <c:if test="${saleType==1}">
				   <div class="col-md-2 search-condition">
                          <select  class="form-control" name="search_status" id="search_status">
						   <option value="">--上架状态--</option>
						   <option value="1">上架</option>
						   <option value="0">下架</option>
                          </select>
					</div>
					<div class="col-md-2 search-condition">
                          <select  class="form-control" name="search_productActivityStatus" id="search_productActivityStatus">
						   <option value="">--活动状态--</option>
						   <option value="0">可报名</option>
						   <option value="1">报名中</option>
						   <option value="2">待开始</option>
						   <option value="3">预热中</option>
						   <option value="4">活动中</option>
						   <option value="5">活动暂停</option>
						   <option value="6">未提审</option>
						   <option value="7">未通过</option>
                          </select>
					</div>
				   </c:if>
				   <c:if test="${saleType==2}">
				   	<div class="col-md-2 search-condition">
                          <select  class="form-control" name="search_productActivityStatus" id="search_productActivityStatus">
						   <option value="">--活动状态--</option>
						   <option value="0">可报名</option>
						   <option value="1">报名中</option>
						   <option value="3">预热中</option>
						   <option value="4">活动中</option>
                          </select>
					</div>
				   </c:if>

					
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
						             <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
						             <button type="button"  class="btn btn-info" id="btn-export" style="width: 80px;padding-left: 5px;">导出商品列表</button>
						             <button type="button"  class="btn btn-info" id="btn-exportsku" style="width: 86px;padding-left: 6px;">导出商品SKU</button>
                                    <!-- <i class="glyphicon glyphicon-search"></i>查询</button> -->
					</div>
					
				</div>
				
				
				
				
				
			</form>
			
			
			
			
			
			

		</div>
		<div class="clearfix"></div>
		<div class="x_content container-fluid">
			<div class="row">
				<div class="col-md-12 datatable-container">
					<div class="datatable-caption">
						<span class="mr">
							<input type='checkbox' class='checkAll' onclick='selectAll();' value='ccc'>全选
						</span>
						<button class="btn btn-all" onclick="batchSetRemarks();">批量备注</button>
						<c:if test="${sessionScope.mchtInfo.mchtType != '1'}">
							<button class="btn btn-all" onclick="batchSetSvipDiscount();">批量SVIP折扣</button>
						</c:if>
						<c:if test="${saleType eq '1'}">
						<button class="btn btn-all" onclick="batchUndercarriage();">批量下架</button>
						</c:if>
						<button class="btn btn-all" onclick="batchDelete();">批量删除</button>
						<c:if test="${isMainProductType eq '316' or isMainProductType eq '317' or isMainProductType eq '318'}">
							<button class="btn btn-all" onclick="batchModifyStock();">批量修改库存</button>
						</c:if>
						<button class="btn btn-all" onclick="batchAdjustPrice();">批量调价</button>
					</div>
					<table id="datatable"
						class="table table-striped table-bordered dataTable no-footer"
						role="grid" aria-describedby="datatable_info">
					</table>
				</div>
			</div>
		</div>
	</div>

	
<!-- 	查看信息框 -->

<div class="modal fade" data-width="1400px;" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>
<div class="modal fade" id="batchViewModal" tabindex="-1" role="dialog" aria-labelledby="batchViewModal" data-backdrop="static"></div>
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

<div class="modal fade" id="setSvipDiscountModal" tabindex="-1" role="dialog" aria-labelledby="setSvipDiscountModal" data-backdrop="static" >
  <div id="test" class="modal-dialog" role="document" style="width: 400px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">批量SVIP折扣</h4>
      </div>
      <div class="modal-body">
     	<div>
	     	<form id="dataFrom">
			    <label for="name" class="money">SVIP折扣：</label>
			    <select class="ad-select" name="indexSvipDiscount" id="indexSvipDiscount">
					<option value="">请选择</option>
					<option value="0.95" >9.5</option>
					<option value="0.90" >9.0</option>
					<option value="0.85" >8.5</option>
					<option value="0.80" >8.0</option>
					<option value="0.75" >7.5</option>
					<option value="0.70" >7.0</option>
					<option value="0.65" >6.5</option>
					<option value="0.60" >6.0</option>
					<option value="0.55" >5.5</option>
					<option value="0.50" >5.0</option>
					<option value="0.45" >4.5</option>
					<option value="0.40" >4.0</option>
                </select>
			    <br>
			    <div class="modal-footer" style="padding: 10px 0 0;">
				<div style="display: inline-block;text-align: center;">
				    <input type="hidden" id="productIds" value="" name="productIds">
					<button type="button" class="btn btn-default" style="margin-right: 17px;" onclick="saveSvipDiscount();">提交</button>
					<button type="button" class="btn btn-info" data-dismiss="modal">取消</button> 
				</div>
				</div>
			</form> 
	    </div>
      </div>
    </div>
  </div>
</div>

	<!-- 	批量调价框 -->
	<div class="modal fade" id="batchAdjustPriceModal" class="batchAdjustPriceModal" tabindex="-1" role="dialog" aria-labelledby="batchAdjustPriceModal" data-backdrop="static" >
		<div id="batchAdjustPrice" class="modal-dialog" role="document" style="width: 800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">批量调价</h4>
				</div>
				<div class="modal-body">
					<div>
						<form id="myDataFrom">
						  <table class="table table-striped table-bordered" cellspacing="0">
							<tbody>
							<tr>
								<td class="editfield-label-td">调价选择<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<div class="conBox" style="margin-right: 20px;"><input type="checkbox" name="choice" value="1" ><span>商城价</span></div>
									<div class="conBox"><input type="checkbox" name="choice" value="2" ><span>活动价</span></div>
								</td>
							</tr>
							<tr>
								<td class="editfield-label-td">调价方式<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<div class="conBox"><input type="radio" name="mode" value="1"  onclick="editPricemode(1)"><span>增加N元</span></div>
									<div class="conBox"><input type="radio" name="mode" style="margin-left: 12px;" value="2" onclick="editPricemode(2)"><span>按比例增加</span></div>
									<c:if test="${sessionScope.mchtInfo.mchtType != '1'}">
									<div class="conBox"><input type="radio" name="mode" style="margin-left: 12px;" value="3" onclick="editPricemode(3)"><span>减少N元</span></div>
									<div class="conBox"><input type="radio" name="mode" style="margin-left: 12px;" value="4" onclick="editPricemode(4)"><span>按比例减少</span></div>
									</c:if>
								</td>
							</tr>
							<tr id="pricingContent" style="display:none;">
								<td class="editfield-label-td">调价内容<span class="required">*</span></td>
								<td colspan="2" class="text-left" id="editPricemode">
									在原价上增加<input type="text" style="width: 80px" id="changePrice" name="changePrice" >元
								</td>
							</tr>
							<tr id="MantissaFixed" style="display:none;">
								<td class="editfield-label-td">是否固定尾数<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<div class="conBox"><input type="radio" name="mantissa" value="1" onclick="mantissaNum()"><span>是</span></div>
									<div class="conBox"><input type="radio" style="margin-left: 40px;"  id="mantissa" name="mantissa" value="2" checked="checked" onclick="mantissaNum()"><span>否</span></div>
								</td>
							</tr>
							<tr id="mantissaNum" style="display:none;">
								<td class="editfield-label-td">尾数为<span class="required">*</span></td>
								<td colspan="2" class="text-left">
									<input type="text" style="width: 80px"  id="mantissaPrice" name="mantissaPrice">元 <span>(如:填写9,调整后价格360元会自动变成369元)</span>
								</td>
							</tr>
							<tr id="operation" style="display:none;">
								<td class="editfield-label-td">操作</td>
								<td colspan="2" class="text-left">
									<a class="btn btn-info" href="javascript:;" onclick="submitForm();">提交</a>
								</td>
							</tr>
							</tbody>
						  </table>
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

	//批量调价
	function batchAdjustPrice(){
		if($(".checkRow:checked").length<=0){
			swal('请选择行');
			return;
		}
		$("input[name='choice']:checked").each(function(i,item){
			$(item).attr("checked",false);
		});
		$("input[name='mode']:checked").attr("checked",false);
		$("input[name='mantissa']:checked").attr("checked",false);
		$("input:radio[name='mantissa']").eq(1).prop("checked","checked");
		$("#changePrice").val("");
		$("#mantissaPrice").val("");
		$("#pricingContent").hide();
		$("#MantissaFixed").hide();
		$("#operation").hide();
		$("#mantissaNum").hide();
		$("#batchAdjustPriceModal").modal();
	}

	function submitForm(){
			var productIds;
			var choiceStr;
			$(".checkRow:checked").each(function(){
				if(!productIds){
					productIds = $(this).val();
				}else{
					productIds = productIds+","+$(this).val();
				}

			});
			//调价选择
			if($("input[name='choice']:checked").length == 0){
				swal("至少选择一种调价选择");
				return;
			}
			$("input[name='choice']:checked").each(function(i,item){
				if(i == 0){
					choiceStr = $(item).val();
				}else{
					choiceStr = choiceStr+","+$(item).val();
				}
			});
			//调价内容
			if($("#changePrice").val() == ""){
				swal("调价内容不可为空");
				return;
			}
			if($("#changePrice").val()>10000000){
				swal("调价内容不可大于一千万");
				return;
			}
			//是否固定尾数
			if($("input[name='mantissa']:checked").val() == "1" && $("#mantissaPrice").val() == ""){
				swal("尾数不可为空");
				return;
			}
			//调价价格、尾数
			var reg = new RegExp("^[1-9]\\d*$");
			if(!reg.test($("#changePrice").val())){
				swal("调价价格须为正整数");
				return;
			}
			if(!reg.test($("#mantissaPrice").val()) && !$("#mantissaPrice").val() == ""){
				swal("尾数须为正整数");
				return;
			}
			if(parseInt($("#mantissaPrice").val())>=10){
				swal("尾数须为小于10大于等于1的正整数");
				return;
			}

			var dataJson = $("#myDataFrom").serializeArray();

			$.ajax({
				url : "${ctx}/product/batchAdjustPrice.shtml?productIds="+productIds+"&choiceStr="+choiceStr+"&saleType="+${saleType},
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				success : function(data) {
					if (data.returnCode=="0000") {
                        table.ajax.reload( null, false );
                        $("#batchAdjustPriceModal").modal('hide');
						swal({
							title: data.returnMsg,
							type: "success",
							confirmButtonText: "确定"
						},function(){
							table.ajax.reload( null, false );
						});
					} else {
						swal({
							title: data.returnMsg,
							type: "error",
							confirmButtonText: "确定"
						},function(){
							$("#batchAdjustPriceModal").on("hide.bs.modal",function() {
								table.ajax.reload( null, false );
							});
						});
					}
				},
				error : function() {
					swal({
						title: "提交失败！",
						type: "error",
						timer: 1500,
						confirmButtonText: "确定"
					},function(){
					});
				}
			});
	}

	function editPricemode(num){
		$("#editPricemode").empty();
		if (num == 1){
			$("#editPricemode").append("在原价上增加<input id=\"changePrice\" name=\"changePrice\" type=\"text\" style=\"width: 80px\">元")
		}
		if (num == 2){
			$("#editPricemode").append("在原价上增加<input id=\"changePrice\" name=\"changePrice\" type=\"text\" style=\"width: 80px\">%")
		}
		if (num == 3){
			$("#editPricemode").append("在原价上减少<input id=\"changePrice\" name=\"changePrice\" type=\"text\" style=\"width: 80px\">元")
		}
		if (num == 4){
			$("#editPricemode").append("在原价上减少<input id=\"changePrice\" name=\"changePrice\" type=\"text\" style=\"width: 80px\">%")
		}
		$("#pricingContent").show();
		$("#MantissaFixed").show();
		$("#operation").show();
	}

	function mantissaNum(){
		var mantissa=$("input[name='mantissa']:checked").val();
		if(mantissa == '1'){
			$("#mantissaNum").show();
		}
		if (mantissa=='2') {
			$("#mantissaNum").hide();
		}
	}

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

	function setSvipDiscount(productIds){
		 $("#indexSvipDiscount").val("");
		 $("#productIds").val(productIds);
		 $("#setSvipDiscountModal").modal();
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
	
	function saveSvipDiscount(){
		var indexSvipDiscount = $("#indexSvipDiscount").val();
		if(!indexSvipDiscount){
			swal("请选择SVIP折扣");
			return;
		}
		$.ajax({
			url : "${ctx}/product/setSvipDiscount",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"productIds":$("#productIds").val(),"svipDiscount":indexSvipDiscount},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode == "0000") {
					table.ajax.reload( null, false );
					 $("#setSvipDiscountModal").modal('hide');
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
	
	function selectSaleType(){
		var selectType=$("#search_saleType").val();
		var selectHtml;
		if(selectType==""||selectType=="1"){
			selectHtml='<option value="">--上架状态--</option><option value="1">上架</option><option value="0">下架</option>';
		}
		if(selectType=="2"){
			selectHtml='<option value="">--活动状态--</option><option value="0">可报名</option><option value="1">报名中</option><option value="2">待开始</option><option value="3">预热中</option><option value="4">活动中</option><option value="5">活动暂停</option><option value="6">未提审</option><option value="7">未通过</option>';
		}
		
		$("#search_status").html(selectHtml);
		
		
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
		var pageNumber = $("li[class='paginate_button active']").find("a").first().text();
		getContent("${ctx}/product/productEdit?id="+id+"&pageNumber="+pageNumber);
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

	//批量下架
	function batchUndercarriage(){
		if($(".checkRow:checked").length<=0){
			swal('请选择行');
			return;
		}
		var productIds="";
		$(".checkRow:checked").each(function(){
			if(!productIds){
				productIds = $(this).val();
			}else{
				productIds = productIds+","+$(this).val();
			}

		});

		$.ajax({
			url : "${ctx}/product/batchUndercarriage",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productIds:productIds},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					swal({
						title: data.returnData,
						type: "success",
						confirmButtonText: "确定"
					},function(){
						table.ajax.reload( null, false );
					});
				} else {
					if(data.returnMsg == "1"){
						swal({
							title: "调价失败，只有活动状态为可报名的才能调价",
							type: "error",
							confirmButtonText: "确定"
						});
					}
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

	//批量删除
	function batchDelete(){
		if($(".checkRow:checked").length<=0){
			swal('请选择行');
			return;
		}
		var productIds="";
		$(".checkRow:checked").each(function(){
			if(!productIds){
				productIds = $(this).val();
			}else{
				productIds = productIds+","+$(this).val();
			}

		});

		$.ajax({
			url : "${ctx}/product/batchDelete",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productIds:productIds},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					swal({
						title: data.returnData,
						type: "success",
						confirmButtonText: "确定"
					},function(){
						table.ajax.reload( null, false );
					});
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

	//批量修改库存
	function batchModifyStock() {
		if($(".checkRow:checked").length<=0){
			swal('请选择行');
			return;
		}
		var productIds="";
		$(".checkRow:checked").each(function(){
			if(!productIds){
				productIds = $(this).val();
			}else{
				productIds = productIds+","+$(this).val();
			}

		});

		$.ajax({
			url : "${ctx}/product/batchModifyStock?productIds="+productIds,
			success : function(data) {
				$("#batchViewModal").html(data);
				$("#batchViewModal").modal();
			},
			error : function() {
				swal('页面不存在');
			}
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

function batchSetSvipDiscount(){
	if($(".checkRow:checked").length<=0){
		swal('请选择行');
		return;
	}
	 var productIds="";
     $(".checkRow:checked").each(function(){
    	 if(!productIds){
    		 productIds = $(this).val();
    	 }else{
	    	 productIds = productIds+","+$(this).val();
    	 }
    		 
     });
     setSvipDiscount(productIds);
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
    var isInit = true;
	var pageNumber;
    table = $('#datatable').dataTable({
        "ajax": function (data, callback, settings) {
        	pageNumber = $("#pageNumber").val();
            var param = $("#searchForm").serializeArray();
            param.push({"name":"pagesize","value":data.length});
            if (data.start > 0) {
                param.push({"name":"page","value":data.start/data.length+1});
            } else if(isInit && pageNumber && parseInt(pageNumber)>0){
	            data.start = (parseInt(pageNumber)-1)*data.length;
				param.push({"name": "pageNumber", "value": pageNumber});
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
                    var shopStatus = '${shopStatus}';
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
        	// {"data": "id","title":"<input type='checkbox' class='checkAll' onclick='selectAll();' value='ccc'/>","render": function (data, type, row, meta) {
         //    	return '<input type="checkbox" class="checkRow"  value="' + data + '" />';
         //    }},
            {"data": "id",width:"240","title":"图片/名称/商品ID","render": function (data, type, row, meta) {
            	var html = [];
            	html.push('<div class="is-check">');
            	html.push('<div class="width-33"><input type="checkbox" class="checkRow"  value="' + data + '" /></div>');
            	if(row.pic!=null){
	            	if(row.pic && row.pic.indexOf("http") >= 0){//网络图片
	            		html.push('<div class="width-60"><img src='+row.pic+'></div>');
	            	}else{
	            		html.push('<div class="width-60"><img src="${ctx}/file_servelt'+row.pic+'"></div>');
	            	}
            	}
            	
            	html.push('<div class="width-226"><p class="h34">'+row.name+'</p><div><span style="float: left; margin: 0;">ID：'+row.code+'</span><a style="float: right;" href="https://m.xgbuy.cc/share_buy.html?id='+row.code+'" target="_blank">预览</a></div></div>');
            	html.push("<div>");
            	return html.join("");
            }},
            {"data": "id",width:"98","title":"品牌/货号","render": function (data, type, row, meta) {
                var returnStr= "<div style='width:90px;word-wrap:break-word;'>"+row.productBrandName+"<br>"+row.artNo+"</div>";
                return returnStr;
            }},
            {"data": "id",width:"70","title":"吊牌价","render": function (data, type, row, meta) {
            	var html = [];
				html.push(row.tagPriceMin);
				if(row.tagPriceMin!=row.tagPriceMax){
					html.push("-");
					html.push(row.tagPriceMax);
				};
			    return html.join("");
            }},
            {"data": "id",width:"70","title":"商城价",sClass:"hiddenCol","render": function (data, type, row, meta) {
				var html = [];
				
				html.push(row.mallPriceMin);
				
				if(row.mallPriceMin!=row.mallPriceMax){
					html.push("-");
					html.push(row.mallPriceMax);
				};
				if(row.mallPriceMin>row.tagPriceMin){
					html.push("<br><br><font color='red'>须小于吊牌价</font>")
				}
			    return html.join("");
            }},
            {"data": "id",width:"70","title":"活动价","render": function (data, type, row, meta) {
				var html = [];
				
				html.push(row.salePriceMin);
				
				if(row.salePriceMin!=row.salePriceMax){
					html.push("-");
					html.push(row.salePriceMax);
				};
				if(row.salePriceMin>row.tagPriceMin){
					html.push("<br><br><font color='red'>须小于吊牌价</font>")
				}
				if(row.salePriceMin>row.mallPriceMin){
					html.push("<br><br><font color='red'>须小于商城价</font>")
				}
			    return html.join("");
            }},
			<c:if test="${sessionScope.mchtInfo.mchtType == '1'}">
				{"data": "id",width:"88","title":"结算价","render": function (data, type, row, meta) {
					var html = [];
					html.push(row.costPriceMin);
					if(row.costPriceMin!=row.costPriceMax){
						html.push("-");
						html.push(row.costPriceMax);
					};
					return html.join("");
				}},
			</c:if>
            {"data": "id",width:"78","title":"SVIP折扣","render": function (data, type, row, meta) {
            	if(!row.svipDiscount){
            		return "/";
            	}else{
            		return row.svipDiscount*10;
            	}
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
            	
//             	if((row.offReason!='4'&&row.saleType=='2'&&(row.activityStatusDesc=='0'||row.activityStatusDesc=='6'||row.activityStatusDesc=='7'))||(row.offReason!='4'&&row.saleType=='1'&&row.status=='0')){
//             		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProduct("+row.id+")' >编辑</a>"+"<br>");	
//             	}
            	
//             	html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editProductSku("+row.id+")' >修改SKU</a><br>");
            	
//             	if((row.saleType=='2'&&row.activityStatusDesc=='0')||(row.saleType=='1'&&row.status=='0')){
//             		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='delProduct("+row.id+")' >删除</a><br>");	
//             	}
            	
            	
            	
            	if(row.saleType=='1'&&row.status=='1'&&row.activityStatusDesc=='0'){
	            	   		html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='changeProductStatus("+row.id+",0)' >下架</a>");
	            	
// 	            	else{
// 	            	   		if(row.auditStatus=='2'&&row.offReason!='4'){
// 	            	   			html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='changeProductStatus("+row.id+",1)' >上架</a>");
// 	            	   		}
	            	   		
// 	            	   	}
            	}
            	
            	return  html.join("");
            }}
        ],
	      
	       "initComplete": function(settings, json) {
	           //这里在初始化完成后修改一下显示的页数
	           if(isInit&& parseInt(pageNumber)!=0){
	    		   isInit = false;
	    		   table.page(parseInt(pageNumber)-1).draw(false);
	    	   }
	       }
    }).api();

    $('#btn-query').on('click', function (event) {
        table.ajax.reload();
    });
    
    $('#btn-export').on('click', function (event) {
        var search_brandId = $("#search_brandId").val();
        var productType1 = "";
        if($("#productType1-select").find("option:selected").length>0){
        	productType1 = $("#productType1-select").find("option:selected").val();
        }
        var productType2 = "";
        if($("#productType2-select").find("option:selected").length>0){
        	productType2 = $("#productType2-select").find("option:selected").val();
        }
        var productType3 = "";
        if($("#productType3-select").find("option:selected").length>0){
        	productType3 = $("#productType3-select").find("option:selected").val();
        }
        var search_status ="";
        if($("#search_status").val()){
        	search_status=$("#search_status").val();
        }
        var search_productActivityStatus=""; 
        if($("#search_productActivityStatus").val()){
        	search_productActivityStatus = $("#search_productActivityStatus").val();
        }
        
        var search_saleType="";
        if($("#search_saleType").val()){
        	search_saleType = $("#search_saleType").val();
        }
        
        var searchKeywrodType = $("#searchKeywrodType").val();
        var searchKeywrod = $("#searchKeywrod").val();
        location.href="${ctx}/product/exportProduct?search_brandId="+search_brandId+"&productType1="+productType1+"&productType2="+productType2+"&productType3="+productType3+"&search_status="+search_status+"&search_productActivityStatus="+search_productActivityStatus+"&searchKeywrodType="+searchKeywrodType+"&searchKeywrod="+searchKeywrod+"&search_saleType="+search_saleType;
    });
    
    
    $('#btn-exportsku').on('click', function (event) {
        var search_brandId = $("#search_brandId").val();
        var productType1 = "";
        if($("#productType1-select").find("option:selected").length>0){
        	productType1 = $("#productType1-select").find("option:selected").val();
        }
        var productType2 = "";
        if($("#productType2-select").find("option:selected").length>0){
        	productType2 = $("#productType2-select").find("option:selected").val();
        }
        var productType3 = "";
        if($("#productType3-select").find("option:selected").length>0){
        	productType3 = $("#productType3-select").find("option:selected").val();
        }
        var search_status ="";
        if($("#search_status").val()){
        	search_status=$("#search_status").val();
        }
        var search_productActivityStatus=""; 
        if($("#search_productActivityStatus").val()){
        	search_productActivityStatus = $("#search_productActivityStatus").val();
        }
        
        var search_saleType="";
        if($("#search_saleType").val()){
        	search_saleType = $("#search_saleType").val();
        }
        
        var searchKeywrodType = $("#searchKeywrodType").val();
        var searchKeywrod = $("#searchKeywrod").val();
        location.href="${ctx}/product/exportProductSku?search_brandId="+search_brandId+"&productType1="+productType1+"&productType2="+productType2+"&productType3="+productType3+"&search_status="+search_status+"&search_productActivityStatus="+search_productActivityStatus+"&searchKeywrodType="+searchKeywrodType+"&searchKeywrod="+searchKeywrod+"&search_saleType="+search_saleType;
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
