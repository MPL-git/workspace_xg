<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>修改SkU</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<!--       <link href="${pageContext.request.contextPath}/static/css/editor.dataTables.min.css" rel="stylesheet" type="text/css" /> -->
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
<style type="text/css">

a {
	cursor: pointer;
}

a:focus{
  color:#3C9EFF;
}


.sku_pic_picker {
height: 50px;
margin: 0 auto;
}

.sku_pic_picker div{
position:absolute;
width: 50px;
height: 50px;
line-height: 50px;
font-size: 50px;
z-index: 1000;
color: #ddd;
}
.sku_pic_picker input{
position: absolute;
width: 50px;
height: 50px;
opacity: 0;
z-index: 1002;
}

.sku_pic_picker img{
width: 50px;
height: 50px;
}
.dataTables_info{
display: none;
}

.datatable-container{
padding: 0 5px;
}

.sweet-alert button.cancel:hover {
    background-color:#8CD4F5;
}
.sweet-alert button.cancel {
    background-color: #8CD4F5;
}

.dataTable input[type='text']{
width:70%;
}

</style>


</head>

<body>
  <div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">修改SkU</span>
      </div>
			<div class="modal-body">
		<form id="dataFrom">
		<input type="hidden" value="${product.id}" name="id">
		<input type="hidden" value="${activityStatusDesc}" id="activityStatusDesc">
		
		
		<div id="prop_container" style="display:none;">
		<c:forEach var="prop" items="${props}">
		<span id='prop_container_${prop.key}' class='prop-container'>${prop.value.propName}</span>
		</c:forEach>
		</div>
		
		<div class="clearfix"></div>
		<div id="product-item-table-container" class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container">
				<div id="product-item-table">
					<div class="col-md-12 datatable-container">
						<table id="product-item-datatable" style="width:100%;max-width: 100%;"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
						</table>
					</div>
				</div>
					</div>
				</div>
		</div>
		
		
		
		
		<br>
		<h5><label>其它信息</label></h5>
				<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">SVIP折扣</td>
						<td colspan="2" class="text-left">
					      <select name="svipDiscount" id="svipDiscount" validate="{svipDiscount:true}" style="width:25%;<c:if test="${sessionScope.mchtInfo.mchtType eq '1'}">background-color: #EEEEEE;</c:if>" <c:if test="${sessionScope.mchtInfo.mchtType eq '1'}">disabled="disabled"</c:if> <c:if test="${productActivityStatus ne 0}">disabled="disabled"</c:if>>
					      	<option <c:if test="${empty product.svipDiscount}">selected="selected"</c:if> value="">请选择</option>
							  <option <c:if test="${svipDiscount == 0.95}">selected="selected"</c:if><c:if test="${empty product.svipDiscount && sessionScope.mchtInfo.mchtType eq '1'}">selected="selected"</c:if> value="0.95">9.5</option>
					      	<option <c:if test="${svipDiscount == 0.90}">selected="selected"</c:if> value="0.90">9.0</option>
					      	<option <c:if test="${svipDiscount == 0.85}">selected="selected"</c:if> value="0.85">8.5</option>
					      	<option <c:if test="${svipDiscount == 0.80}">selected="selected"</c:if> value="0.80">8.0</option>
					      	<option <c:if test="${svipDiscount == 0.75}">selected="selected"</c:if> value="0.75">7.5</option>
					      	<option <c:if test="${svipDiscount == 0.70}">selected="selected"</c:if> value="0.70">7.0</option>
					      	<option <c:if test="${svipDiscount == 0.65}">selected="selected"</c:if> value="0.65">6.5</option>
					      	<option <c:if test="${svipDiscount == 0.60}">selected="selected"</c:if> value="0.60">6.0</option>
					      	<option <c:if test="${svipDiscount == 0.55}">selected="selected"</c:if> value="0.55">5.5</option>
					      	<option <c:if test="${svipDiscount == 0.50}">selected="selected"</c:if> value="0.50">5.0</option>
					      	<option <c:if test="${svipDiscount == 0.45}">selected="selected"</c:if> value="0.45">4.5</option>
					      	<option <c:if test="${svipDiscount == 0.40}">selected="selected"</c:if> value="0.40">4.0</option>
                          </select>
						</td>
					</tr>					
					<tr>
						<td class="editfield-label-td">每人限购数量</td>
						<td colspan="2" class="text-left">
					     <input type="text" id="limitBuy" name="limitBuy" value="${product.limitBuy }" validate="{digits:true,min:0}">
						 <span style="margin-left: 6px;color: #999;">提示：填0表示不限制，大于0表示每个用户在7天内购买该商品的数量不超过此数</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">售后信息</td>
						<td colspan="2" class="text-left">
					      <select  onchange="changeCstemplet();"  name="csTempletId" id="csTempletId"  style="width:25%;" validate="{required:true}">
                          </select>
						<a style="font-size: 14px;font-weight:normal;" class='table-opr-btn' href='javascript:void(0);' onclick="addTpl();" >【添加】</a>
                          <div id="csTempletInfoContainer" style="display:inline-block;margin-left: 20px;"></div>
						</td>
					</tr>
					<c:if test="${activityStatusDesc=='0'}">
					<tr>
						<td class="editfield-label-td">商品类型</td>
						<td colspan="2" class="text-left">
						  <select class="ad-select" onchange="selectSaleType();"  name="saleType" id="saleType" style="width:25%;" validate="{required:true}">
						  <option value="1" <c:if test="${product.saleType=='1'}">selected="selected"</c:if>>品牌活动款</option>
						  <option value="2" <c:if test="${product.saleType=='2'}">selected="selected"</c:if>>单品活动款</option>
						  </select>
						  <span id="saleTypeDesc" style="color:red;">
						  <c:if test="${product.saleType=='1'}">（可以报品牌特卖活动，显示在商城店铺中）</c:if>
						  <c:if test="${product.saleType=='2'}">（可以报单品活动，不显示在商城店铺中）</c:if>
						  </span>
						</td>
					</tr>
					</c:if>

				</tbody>
			</table>
		</div>
	     <input id="commitType" type="hidden" name="commitType" value="">
		</form>
		
	<div class="modal-footer">
			<button class="btn btn-info" onclick="$('#commitType').val('2');commitSave();">保存</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>
		
			</div>
	</div>
  </div>

<div class="modal fade" id="viewModal1" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel1" data-backdrop="static">
</div>


	
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	




<script type="text/javascript">

	var productItemDatatable;
	var editor;
	var productItemDatatableColumns=[];
	var productPropIdArry=[];//属性id
	var productPropValuesArry=[];//属性数据
	var productPropValuesSelectedArry=[];//选中属性数据


	//批量设置价格，将所有商城价都置为第一行的值
	function batchChangeTagPrice(){
		var tagPrice=productItemData[0].tagPrice;
		for(var i=0;i<productItemData.length;i++){
			productItemData[i].tagPrice=tagPrice;
			$("#tagPrice_"+productItemData[i].DT_RowId).val(tagPrice);
		}
	}
	function batchChangeMallPrice(){
		var mallPrice=productItemData[0].mallPrice;
		for(var i=0;i<productItemData.length;i++){
			productItemData[i].mallPrice=mallPrice;
			$("#mallPrice_"+productItemData[i].DT_RowId).val(mallPrice);
		}
	}
	function batchChangeSalePrice(){
		var salePrice=productItemData[0].salePrice;
		for(var i=0;i<productItemData.length;i++){
			productItemData[i].salePrice=salePrice;
			$("#salePrice_"+productItemData[i].DT_RowId).val(salePrice);
		}
	}
	function batchChangeCostPrice(){
		var costPrice=productItemData[0].costPrice;
		for(var i=0;i<productItemData.length;i++){
			productItemData[i].costPrice=costPrice;
			$("#costPrice_"+productItemData[i].DT_RowId).val(costPrice);
		}
	}


	function createProductItemDatatableColumns(){
		productItemDatatableColumns=[];
		productItemDatatableColumns.push({"title": "SKU图", "width":"100", "data": "skuPic","render": function (data, type, row, meta) {
			return '<div class="sku_pic_picker" id="sku_pic_'+row.DT_RowId+'"><img src="'+data+'"><input id="skuPicName_'+row.DT_RowId+'"  type="hidden" validate="{required:true}" value="'+row.skuPicName+'"></div>';
		}});
		if("0"==${product.isSingleProp}){
			productItemDatatableColumns.push({"title": "规格", "width":"230", "data": "DT_RowId","render": function (data, type, row, meta) {
				if(productPropIdArry.length==1){
					return row[productPropIdArry[0]];
				}
				var propColumnValues="";
				for(var i=0;i<productPropIdArry.length;i++){
					propColumnValues=propColumnValues+"<br>"+row[productPropIdArry[i]];
				}
				return propColumnValues;
			}});
		}

		var as = $("#activityStatusDesc").val();
		var tagPriceTitle="吊牌价";
		var mallPriceTitle="商城价";
		var salePriceTitle="活动价";
		var costPriceTitle="结算价";
		if(as == "0"||as == "6"||as == "7"){
			tagPriceTitle='<a href="javascript:void(0);"  onclick="batchChangeTagPrice();">吊牌价</a>';
			mallPriceTitle='<a href="javascript:void(0);"  onclick="batchChangeMallPrice();">商城价</a>';
			salePriceTitle='<a href="javascript:void(0);"  onclick="batchChangeSalePrice();">活动价</a>';
			costPriceTitle='<a href="javascript:void(0);"  onclick="batchChangeCostPrice();">结算价</a>';
		}

		productItemDatatableColumns.push({"title": tagPriceTitle, "width":"120", "data": "tagPrice","render": function (data, type, row, meta) {
			var activityStatus = $("#activityStatusDesc").val();
			var productActivityStatus = "0";//可报名
			<c:if test="${not empty from}">
				productActivityStatus = '${productActivityStatus}';//来自供应商那边跳转过来的编辑页面
			</c:if>
			var readOnlyHtml = "";
			if(productActivityStatus!="0"){//不是可报名，只能是只读
				readOnlyHtml="readonly";
			}
			if((activityStatus == "0"||activityStatus == "6"||activityStatus == "7") && ${marketTag eq 0}){
				return '<input class="priceInput" '+readOnlyHtml+' onkeyup="validatePrice(this);" id="tagPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01}"  type="text" value="'+data+'"  onblur="editProductItemTagPrice('+"'"+row.DT_RowId+"'"+',this);">';
			}else{
				return '<input disabled="disabled" '+readOnlyHtml+' class="priceInput" onkeyup="validatePrice(this);" id="tagPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01}"  type="text" value="'+data+'"  onblur="editProductItemTagPrice('+"'"+row.DT_RowId+"'"+',this);">';
			}
		 }});
		productItemDatatableColumns.push({"title": mallPriceTitle, "width":"120", "data": "mallPrice","render": function (data, type, row, meta) {
			var activityStatus = $("#activityStatusDesc").val();
			var productActivityStatus = "0";//可报名
			<c:if test="${not empty from}">
				productActivityStatus = '${productActivityStatus}';//来自供应商那边跳转过来的编辑页面
			</c:if>
			var readOnlyHtml = "";
			if(productActivityStatus!="0"){//不是可报名，只能是只读
				readOnlyHtml="readonly";
			}
			if((activityStatus == "0"||activityStatus == "6"||activityStatus == "7") && ${marketTag eq 0}){
				return '<input class="priceInput" '+readOnlyHtml+' id="mallPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,mallTagPrice:true}"  type="text" value="'+data+'"  onblur="editProductItemMallPrice('+"'"+row.DT_RowId+"'"+',this);">';
			}else{
				return '<input disabled="disabled" '+readOnlyHtml+' class="priceInput" id="mallPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,mallTagPrice:true}"  type="text" value="'+data+'"  onblur="editProductItemMallPrice('+"'"+row.DT_RowId+"'"+',this);">';
			}
		}});
		productItemDatatableColumns.push({"title": salePriceTitle, "width":"120", "data": "salePrice","render": function (data, type, row, meta) {
			var activityStatus = $("#activityStatusDesc").val();
			var productActivityStatus = "0";//可报名
			<c:if test="${not empty from}">
				productActivityStatus = '${productActivityStatus}';//来自供应商那边跳转过来的编辑页面
			</c:if>
			var readOnlyHtml = "";
			if(productActivityStatus!="0"){//不是可报名，只能是只读
				readOnlyHtml="readonly";
			}
			if((activityStatus == "0"||activityStatus == "6"||activityStatus == "7") && ${marketTag eq 0}){
				 return '<input  class="priceInput" '+readOnlyHtml+' id="salePrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,salePrice:true,mallPrice:true,salePriceDiscount:true}"  type="text" value="'+data+'"  onblur="editProductItemSalePrice('+"'"+row.DT_RowId+"'"+',this);">';
			}else{
				return '<input disabled="disabled" '+readOnlyHtml+' class="priceInput" id="salePrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,salePrice:true,mallPrice:true,salePriceDiscount:true}"  type="text" value="'+data+'"  onblur="editProductItemSalePrice('+"'"+row.DT_RowId+"'"+',this);">';
			}
		}});

		if('${sessionScope.mchtInfo.mchtType}' == '1' ) {
			var activityStatus = $("#activityStatusDesc").val();
			productItemDatatableColumns.push({"title": costPriceTitle, "width":"120", "data": "costPrice","render": function (data, type, row, meta) {
					if(activityStatus == '0' && ${marketTag eq 0} ) {
						return '<input class="costPrice" id="costPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,costPrice:true,costPriceRate:true}"  type="text" value="'+data+'"  onblur="editProductItemCostPrice('+"'"+row.DT_RowId+"'"+',this);">';
					}else {
						return '<input class="costPrice" disabled="disabled" id="costPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,costPrice:true,costPriceRate:true}"  type="text" value="'+data+'"  onblur="editProductItemCostPrice('+"'"+row.DT_RowId+"'"+',this);">';
					}
				}});
		};
		productItemDatatableColumns.push({"title": "库存", "width":"120", "data": "stock","render": function (data, type, row, meta) {
			var readOnlyHtml = "";
			if(row.cloudProductItemId && row.cloudProductItemId>0){
				if(row.readOnly == "1"){
					readOnlyHtml="readonly";
				}
			}
			var returnHtml=	'<input id="stock_'+row.DT_RowId+'" '+readOnlyHtml+' validate="{required:true,digits:true}"   type="text" value="'+data+'" defaultValue="'+data+'" onblur="editProductItemStock('+"'"+row.DT_RowId+"'"+',this);">';
			if(row.lockedAmount>0){
				returnHtml=returnHtml+"<div class='lockNumDiv' style='position: absolute;left: 40px;'><span style='color:#999999'>冻结："+row.lockedAmount+"</span><div>";
			}
			return returnHtml;
		}});
		productItemDatatableColumns.push({"title": "SKU商家编码", "width": "160", "data": "sku","render": function (data, type, row, meta) {
			return '<input id="sku_'+row.DT_RowId+'"  type="text" value="'+data+'"  onblur="editProductItemSku('+"'"+row.DT_RowId+"'"+',this);">';
		}});
	}

	function editProductItemTagPrice(dataId,elemt){
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				productItemData[i].tagPrice=$(elemt).val();
				break;
			}
		}
	}
	function editProductItemSalePrice(dataId,elemt){
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				productItemData[i].salePrice=$(elemt).val();
				break;
			}
		}
	}
	function editProductItemMallPrice(dataId,elemt){
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				productItemData[i].mallPrice=$(elemt).val();
				break;
			}
		}
	}

	function editProductItemCostPrice(dataId,elemt){
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				productItemData[i].costPrice=$(elemt).val();
				break;
			}
		}
	}

	function editProductItemSku(dataId,elemt){
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				productItemData[i].sku=$(elemt).val();
				break;
			}
		}
	}
	function editProductItemStock(dataId,elemt){
		var nowValue = $(elemt).val();
		var productItemId="";
		var lastStock = "";
		var itemIndex;
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				lastStock=productItemData[i].stock
				productItemData[i].stock= nowValue;
				productItemId=productItemData[i].productItemId;
				itemIndex = i;
				break;
			}
		}
		if(productItemId!=""&&nowValue!=""&&nowValue!=lastStock){
			var $parent_td = $(elemt).parent();
			var $msg_label;
			if( $parent_td.children("label").length > 0){
				$msg_label = $parent_td.children("label");
			}else{
				$msg_label=$('<label class="error"></label>');
				$parent_td.append($msg_label);
			}
			$parent_td.children(".lockNumDiv").remove();
			$.ajax({
				url : "${ctx}/product/changeStockCommit",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {productItemId:productItemId,stock:nowValue},
				timeout : 30000,
				success : function(data) {
					if (data.returnCode=="0000") {
						$msg_label.text("修改成功");
					} else if (data.returnCode=="4001"){
						productItemData[itemIndex].stock=data.returnData;
						$(elemt).val(data.returnData);
						$msg_label.html("修改失败<br>还有"+data.returnData+"件未付款");
					}else{
						$msg_label.text(data.returnMsg);
					}
				},
				error : function() {
						$msg_label.text("修改失败");
				}
			});
		}
	}

	var productItemData=[];

	function initProductItemTable(){
		productItemDatatable = $('#product-item-datatable').dataTable({
			"data":productItemData,
			"language": {"url": '${ctx}/static/cn.json'},
			"autoWidth": false,   // 禁用自动调整列宽
			"stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
			"order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
			"searching": false,   // 禁用原生搜索
			"bSort": false,
			"bPaginate" : false,
			"columns": productItemDatatableColumns
		}).api();
	}

	function commitSave(){
		dataFromValidate =$("#dataFrom").validate({
			errorPlacement: function(error, element) {
				error.appendTo($(element).closest('td'));
			}
		});

		if(dataFromValidate.form()){
			if(productItemData.length==0){
				swal({
					  title: '请输入商品规格',
					  type: "error",
					  confirmButtonText: "确定"
					});
				return;
			}
			commit2Server();
		}else{
			swal("您输入的信息存在错误，请检查！");
			return;
		}
	}

	function commit2Server(){
		var dataJson = $("#dataFrom").serializeArray();
		dataJson.push({"name":"productItems","value":JSON.stringify(productItemData)});
		var disabled = $("#svipDiscount").prop("disabled");
		if(disabled){
			dataJson.push({"name":"svipDiscount","value":$("#svipDiscount").val()});//BUG #7137
		}
		$.ajax({
			url : "${ctx}/product/productEditSkuCommit",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					$("#skuViewModal").modal('hide');
					$("#skuModal").modal('hide');
					$("#viewModal").modal('hide');
					var productCustom = data.returnData.productCustom;
					var salePriceMin = productCustom.salePriceMin;
					var salePriceMax = productCustom.salePriceMax;
					var salePriceDesc;
					if(salePriceMin == salePriceMax){
						salePriceDesc = salePriceMin;
					}else{
						salePriceDesc = salePriceMin+"-"+salePriceMax;
					}
					var discountMin = productCustom.discountMin;
					var discountMax = productCustom.discountMax;
					var discountDesc;
					if(discountMin == discountMax){
						if(discountMin){
							discountDesc=discountMin;
						}else{
							discountDesc=0;
						}
					}else{
						if(!discountMin){
							discountMin=0;
						}
						if(!discountMax){
							discountMax=0;
						}
						discountDesc=discountMin+"-"+discountMax;
					}
					if($("#activityPriceDesc")){
						$("#activityPriceDesc").html(salePriceDesc+"元("+discountDesc+"折)&nbsp;&nbsp;<a class='table-opr-btn' href='javascript:void(0);' onclick='editProductSku("+productCustom.id+")'>[修改SKU价格]</a><div id='activityPriceDiv' style='display:none;'>"+discountDesc+"</div>");
						$("#originalPrice").val(salePriceDesc);
						$("input[name='product']:checked").parent().parent().find("td").eq(3).text(salePriceDesc);
						$("input[name='product']:checked").parent().parent().find("td").eq(4).text(productCustom.stock);
					}
					swal({
						  title: "提交成功!",
						  type: "success",
						  confirmButtonText: "确定"

						});
					if(!$("#activityPriceDesc") || $("#skuViewModal").length>0 || table){
						table.ajax.reload( null, false );
					}
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
					  title: "提交失败！",
					  type: "error",
					  confirmButtonText: "确定"
					});
			}
		});
	}


	var dataFromValidate;
	<c:forEach items="${propIds}"  var="propId">
		productPropIdArry.push("${propId}");
	</c:forEach>
	<c:forEach items="${propValues}" var="propValue" varStatus="varStatus">
		productPropValuesArry[${varStatus.index}]=[];
		productPropValuesSelectedArry[${varStatus.index}]=[];
		<c:forEach items="${propValue}"  var="pValue">
			productPropValuesArry[${varStatus.index}].push("${pValue}");
			productPropValuesSelectedArry[${varStatus.index}].push("${pValue}");
		</c:forEach>
	</c:forEach>

	$(function(){
		//加载sku数据
		$.ajax({
			url : "${ctx}/product/getProductItemDatas",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {id:"${product.id}"},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					productItemData=data.returnData;
					createProductItemDatatableColumns();
					initProductItemTable();
				}

			},
			error : function() {
				swal({
					  title: "请求失败",
					  type: "error",
					  confirmButtonText: "确定"
					});
			}
		});
		getProductAfterTempleList("${product.brandId}");
		$("#csTempletId").val(${product.csTempletId});
		changeCstemplet();
		$.metadata.setType("attr", "validate");
	});


	function getProductAfterTempleList() {
		var path = '${pageContext.request.contextPath}/productAfterTemplet/getProductAfterTemplets';
		jQuery.ajax( {
			url : path,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			timeout : 30000,
			success : function(json) {
				var sel = $("#csTempletId");
				sel.empty();
				sel.append("<option recipient=\"\" tel=\"\" address=\"\" value=\"" + "\">--请选择--</option>");

				$.each(json, function(index, item) {
					sel.append("<option  recipient=\""+item.recipient+"\"  tel=\""+item.phone+"\" address=\""+item.areaName+item.address+"\" value=\"" + item.id + "\">" + item.name + "</option>");
				});
			},
			error : function()
			{
				swal('操作超时，请稍后再试！');
			}
		});
	}

	function changeCstemplet(){
		var selectElemt=$("#csTempletId option:selected");
		var csTempletinfo;
		if($(selectElemt).val()!=""){
			csTempletinfo="收货人："+$(selectElemt).attr("recipient")+"&nbsp&nbsp&nbsp&nbsp"+"电话："+$(selectElemt).attr("tel")+"<br>"+"地址："+$(selectElemt).attr("address");
		}else{
			csTempletinfo="";
		}
		$("#csTempletInfoContainer").html(csTempletinfo);
	}


	function addTpl(){
		$.ajax({
			url: "${ctx}/product/toProductAfterTempletAdd?brandId="+"${product.brandId}",
			type: "GET",
			success: function(data){
				$("#viewModal1").html(data);
				$("#viewModal1").modal();
			},
			error:function(){
				swal('页面不存在');
			}
		});
	}

	function loadImageFile(obj,dataId) {
		if (obj.files.length === 0) {
			return;
		}
		var oFile = obj.files[0];
		if(oFile.size>400*1024){
			swal("图片大小不能超过400K");
			return;
		}
		var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
		if (!rFilter.test(oFile.type)) {
			swal("请选择图片文件");
			return;
		}
		if($(obj).parent().children("img").length<=0){
			$('<img>').appendTo( $(obj).parent() );
		}
		if(oFile.size>400*1024){
			swal("图片大小不能超过400K");
			return;
		}
		var oFReader = new FileReader();
		oFReader.onload = function(oFREvent) {
			 var img=new Image();
			 img.onload=function(){
				 if(img.width!=800||img.height!=800){
						swal("请选择尺寸为800*800的图片");
						return;
				 }else{
					$(obj).parent().children("img").attr("src",oFREvent.target.result);
					$(obj).parent().children("div").remove();
					for(var i=0;i<productItemData.length;i++){
						if(productItemData[i].DT_RowId==dataId){
							productItemData[i].skuPic=oFile;
							productItemData[i].skuPicName=oFile.name;
							productItemData[i].skuPicBase64=oFREvent.target.result;
							break;
						}
					}
					$("#skuPicName_"+dataId).val(oFile.name);
					if($(obj).parent().parent().children("label").length>0){
						$(obj).parent().parent().children("label").remove();
					}
				 }
			 }
			img.src=oFREvent.target.result;
		};
		oFReader.readAsDataURL(oFile);
	}

	function quickSelectPropValue(element){
		var value=$(element).html().replace(/<br>/g,"\n");
		$("#propValue").val(value);
	}


	function validatePrice(element){
		var $element=$(element);
			var elementId=$element.attr("id");
			var salePriceId="salePrice_"+elementId.substr(9);
			if($("#"+salePriceId).val()!=""){
				if((Number($(element).val()) > Number($("#"+salePriceId).val()))&&(Number($("#"+salePriceId).val())/Number($(element).val()))>0.1){
					$("#"+salePriceId).removeClass("error");
					$("#"+salePriceId).next("label").remove();
				}
			}
	}

	$.validator.addMethod("mallTagPrice", function(value, element) {
		var elementId=$(element).attr("id");
		var tagPriceId="tagPrice_"+elementId.substr(10);
		if(Number(value) > Number($("#"+tagPriceId).val())){
			return false;
		}else{
			return true;
		};
	}, "商城价必须小于吊牌价");

	$.validator.addMethod("salePrice", function(value, element) {
		var elementId=$(element).attr("id");
		var tagPriceId="tagPrice_"+elementId.substr(10);
		if(Number(value) >=Number($("#"+tagPriceId).val())){
			return false;
		}else{
			return true;
		};
	}, "价格必须小于吊牌价");

	$.validator.addMethod("salePriceDiscount", function(value, element) {
		var elementId = $(element).attr("id");
		var tagPriceId = "tagPrice_" + elementId.substr(10);
		if ((Number(value)/Number($("#" + tagPriceId).val()))<=0.1) {
			return false;
		} else {
			return true;
		}
		;
	}, "折扣不可低于一折");

	$.validator.addMethod("mallPrice", function(value, element) {
		var elementId=$(element).attr("id");
		var mallPriceId="mallPrice_"+elementId.substr(10);
		if(Number(value)>Number($("#"+mallPriceId).val())){
			return false;
		}else{
			return true;
		};
	}, "活动价必须小于等于商城价");

	$.validator.addMethod("costPrice", function(value, element) {
		var elementId=$(element).attr("id");
		var salePriceId="salePrice_"+elementId.substr(10);
		if(Number(value) >=Number($("#"+salePriceId).val())){
			return false;
		}else{
			return true;
		};
	}, "结算价必须小于活动价");

	$.validator.addMethod("costPriceRate", function(value, element) {
		var elementId = $(element).attr("id");
		var salePriceId = "salePrice_" + elementId.substr(10);
		var priceArray = new Array();
		var price = {"salePrice":$("#" + salePriceId).val(), "costPrice":value};
		priceArray.push(price);
		var svipDiscount;
		var costPriceRate;
		<c:if test="${sessionScope.mchtInfo.mchtType == '2'}" >
			svipDiscount = $("#svipDiscount").val()==""?1:$("#svipDiscount").val();
			costPriceRate = {"priceArray":JSON.stringify(priceArray), "svipDiscount":svipDiscount};
		</c:if>
		<c:if test="${sessionScope.mchtInfo.mchtType == '1'}" >
			svipDiscount = $("#svipDiscount").val()==""?0.95:$("#svipDiscount").val();
			costPriceRate = {"priceArray":JSON.stringify(priceArray), "svipDiscount":svipDiscount,"brandId":${product.brandId}};
		</c:if>
		var flag = false;
		$.ajax({
			type: 'post',
			url: '${ctx}/product/costPriceRate',
			dataType: 'json',
			data: costPriceRate,
			async: false,
			success: function(data) {
				if(data.returnCode == '0000' && data.returnData) {
					flag = true;
				}
			},
			error: function(e) {
				return false;
			}
		});
		return flag;
	}, "结算价设置过高");

	$.validator.addMethod("svipDiscount", function(value, element) {
		var flag = false;
		<c:if test="${sessionScope.mchtInfo.mchtType == '2'}" >
			flag = true;
		</c:if>
		<c:if test="${sessionScope.mchtInfo.mchtType == '1'}" >
			var priceArray = new Array();
			$(".costPrice").each(function(){
				var elementId = $(this).attr("id");
				var salePriceId = "salePrice_" + elementId.substr(10);
				var price = {"salePrice":$("#" + salePriceId).val(), "costPrice":$(this).val()};
				priceArray.push(price);
			});
			var svipDiscount = $("#svipDiscount").val()==""?0.95:$("#svipDiscount").val();
			var costPriceRate = {"priceArray":JSON.stringify(priceArray), "svipDiscount":svipDiscount,"brandId":${product.brandId}};
			$.ajax({
				type: 'post',
				url: '${ctx}/product/costPriceRate',
				dataType: 'json',
				data: costPriceRate,
				async: false,
				success: function(data) {
					if(data.returnCode == '0000' && data.returnData) {
						flag = true;
					}
				},
				error: function(e) {
					return false;
				}
			});
		</c:if>
		return flag;
	}, "SVIP折扣设置过低");

	function selectSaleType(){
		var saleType = $("#saleType").val();
		if(saleType=='1'){
			$("#saleTypeDesc").text("（可以报品牌特卖活动，显示在商城店铺中）");
		}else{
			$("#saleTypeDesc").text("（可以报单品活动，不显示在商城店铺中）");
		}
	}

// $.validator.addMethod("stockCheck", function(value, element) {
// 	var oldStock=$(element).attr("oldStock");
// 	if(Number(value) < Number(oldStock)){
// 		$.validator.messages.customFun = "库存不能小当前库存"+oldStock;
// 		return false;
// 	}else{
// 		return true;
// 	};
// }, stockCheckmsg);

	$(function() {
		if ($.validator) {
			//fix: when several input elements shares the same name, but has different id-ies....
			$.validator.prototype.elements = function() {
				var validator = this, rulesCache = {};
				return $([])
					.add(this.currentForm.elements)
					.filter(":input")
					.not(":submit, :reset, :image, [disabled]")
					.not(this.settings.ignore)
					.filter(
						function() {
							var elementIdentification = this.id
									|| this.name;
							!elementIdentification
									&& validator.settings.debug
									&& window.console
									&& console
											.error(
													"%o has no id nor name assigned",
													this);
							if (elementIdentification in rulesCache
									|| !validator.objectLength($(this)
											.rules()))
								return false;
							rulesCache[elementIdentification] = true;
							return true;
						});
			};
		}
	});
</script>

</body>
</html>
