<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>添加商品</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
	<!-- Bootstrap -->
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
<!--       <link href="${pageContext.request.contextPath}/static/css/editor.dataTables.min.css" rel="stylesheet" type="text/css" /> -->
<!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
<style type="text/css">
.ax{
}
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}

.prop-container{
padding-top: 20px;
}
.glyphicon-remove{
color:red;
margin-left: 3px;
cursor:pointer;
}
.prop-name{
font-weight: bold;
}

.docs-pictures{
padding: 0px;
}
.docs-pictures li{
position: relative;
margin: 3px;
}


.pic-btn-container{
	width:100%;
	position: absolute;
    top: 0px;
    background:rgba(0, 0, 0, 0.5);
    height:0px;
    z-index: 300;
    overflow: hidden;
    text-align: right;
    padding-right: 3px;
}
.set-default-btn{
	color:red;
	cursor: pointer;
}


.main-pic-mark {
	border-radius: 0 40px 0 0;
	width: 40px;
	height:40px;
	line-height: 40px;
	font-size:12px;
	text-align: left;
	background:rgba(0, 0, 0, 0.5);
	color: #ffffff;
	position: absolute;
	bottom: 0;
	left: 0;
	padding: 5px;
}

.sku_pic_picker {
height: 50px;
}

.sku_pic_picker div{
position:absolute;
width: 50px;
height: 50px;
line-height: 50px;
font-size: 50px;
z-index: 1000;
color: #ddd;
border: solid 1px #ddd;
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
.sweet-alert button.cancel:hover {
    background-color:#8CD4F5;
}
.sweet-alert button.cancel {
    background-color: #8CD4F5;
}

.dataTable input[type='text']{
width:70%;
}


.batch-set-icon{
color: #8CD4F5;
font-size: 16px;
cursor: pointer;
}

 .popover-content{
 width: 350px;
 }
 .popover{
 max-width: 500px;
  z-index: 200;
 }
 .webuploader-container div:nth-child(2){
 	position: absolute !important;
 	top: 0px !important;
 	left: 0px !important;
 }
</style>
<style>
        /*样式2*/
        .file {
            position: relative;
            display: inline-block;
            background: #3c9eff;
            border-radius: 4px;
            padding: 0px 12px;
            overflow: hidden;
            color: #fff;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }
        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }
        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }
        #imgBox li {position:relative;list-style-type:none;border:1px solid #ccc;padding:3px;float:left;height: 140px;margin-left: -40px;}
		.toolbar {position:absolute;left:45%; top:45%;}
    </style>



</head>


<body>
	<div class="x_panel container-fluid">
		<input type="hidden" id="mchtInfoStatus" value="${mchtInfoStatus}"/>
		<div class="row content-title">
			<div class="col-md-12 ">添加商品</div>
		</div>
	<c:if test="${productType3!=null}">
	<div style="padding-top: 20px;"><span style="color:#999;">最近添加：</span><span>${productType1.name} > ${productType2.name} > ${productType3.name}</span><span onclick="quickSelectProductType('${productType1.id}','${productType2.id}','${productType3.id}');" style="margin-left: 15px;color: #3c9eff;cursor: pointer;">快速选择</span></div>
	</c:if>
	<div class="ad-form">
 	<form id="dataFrom">
		<input type="hidden" value="${product.id}" name="id">
		<input type="hidden" value="${product.mchtId}" name="mchtId">
		<input type="hidden"  name="saleType" value="${saleType}" id="saleType">
		<input type="hidden" value="${shopProduct}" name="shopProduct" id="shopProduct">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">选择品牌<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
					      <select class="ad-select" onchange="selectBrand(this);"  name="brandId" id="brandId" validate="{required:true}" style="">
						   <option value="">--请选择--</option>
						   <c:forEach var="productBrand" items="${productBrandList}">
						   <option value="${productBrand.productBrandId}">${productBrand.productBrandName}</option>
						   </c:forEach>
                          </select>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">选择品类<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
						  <select class="ad-select" style="height: 134px;margin-right: 7px;" size="5" id="productType1"  onchange="$('#productTypeId').empty();getProductTypeList('productType2',$(this).val());">
                          </select>
						  <select class="ad-select" style="height: 134px;margin-right: 7px;" size="5" id="productType2" onchange="getProductTypeList('productTypeId',$(this).val());" >
                          </select>
						  <select class="ad-select" style="height: 134px;margin-right: 7px;" size="5" id="productTypeId" onchange="setReturn7daysDefault();" name="productTypeId"  validate="{required:true}">
                          </select>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">商品名称<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
					     <input placeholder="限制10-50字之间" maxlength="50" class="ad-select" type="text" id="name" name="name" onkeyup="$('#product_name_length').text(50-($('#name').val().length));" style="width: 530px;margin-right: 10px;" value="${product.name}" validate="{required:true,rangelength:[10,50]}">
					     <span>最多还可输入：</span><span style="color:red;margin: 0 3px;" id="product_name_length">50</span><span>个</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">推荐文案</td>
						<td colspan="2" class="text-left">
					     <input placeholder="限制0-40字之间" maxlength="40" class="ad-select" type="text" id="recommendRemarks" name="recommendRemarks" onkeyup="$('#product_recommend_remarks_length').text(40-($('#recommendRemarks').val().length));" style="width: 530px;margin-right: 10px;" value="${product.recommendRemarks}" validate="{rangelength:[0,40]}">
					     <span>最多还可输入：</span><span style="color:red;margin: 0 3px;" id="product_recommend_remarks_length">40</span><span>个</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">商品货号<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
					     <input class="ad-select" type="text" id="artNo" name="artNo"  value="${product.artNo}" validate="{required:true,maxlength:64}">
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">上市年份<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
						 <select class="ad-select"   name="yearOfListing" id="yearOfListing" validate="{required:true}" style="">
						  <option value="">--请选择--</option>
						<%--   <c:forEach var="productBrand" items="${product}">
						  <option value="${productBrand.productBrandId}">${productBrand.productBrandName}</option>
						  </c:forEach> --%>
                          </select>
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">适合性别<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
							<div class="ad-box">
								<input  type="checkbox" name="suitSexCheckbox" value="1" validate="{required:true}">男
							</div>
							<div class="ad-box">
								<input type="checkbox" name="suitSexCheckbox" value="0">女
							</div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">适合人群<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
							<div class="ad-box">
								<input type="checkbox"  name="suitGroupCheckbox"  value="1" validate="{required:true}">青年
							</div>
							<div class="ad-box">
								<input type="checkbox"  name="suitGroupCheckbox"  value="2">儿童
							</div>
							<div class="ad-box">
								<input type="checkbox"  name="suitGroupCheckbox"  value="3">老年
							</div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">适合季节<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
							<div class="ad-box">
								<input type="radio"  name="season"  value="1" validate="{required:true}">春季
							</div>
							<div class="ad-box">
						     	<input type="radio"  name="season"  value="2">夏季
						    </div>
						    <div class="ad-box">
						  	   <input type="radio"  name="season"  value="3">秋季
						  	</div>
						    <div class="ad-box">
						  	   <input type="radio"  name="season"  value="4">冬季
						  	</div>
						    <div class="ad-box">
						  	   <input type="radio"  name="season"  value="5">春夏
						  	</div>
						    <div class="ad-box">
						  	   <input type="radio"  name="season"  value="6">春秋
						  	</div>
						    <div class="ad-box">
						  	   <input type="radio"  name="season"  value="7">秋冬
						  	</div>
						    <div class="ad-box">
						  	   <input type="radio"  name="season"  value="8">四季
						  	</div>
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">商品主图<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
					     	<div id="main-pic-container">
								<ul class="docs-pictures clearfix td-pictures" id="main-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
								</ul>
								<div style="display: inline-block;" onclick="$('#mainPicErrorMsg').text('');" id="mainFilePicker" class="filePicker">选择图片</div>
								<span id="mainPicErrorMsg" style="vertical-align: top;margin-left: 10px;color: red;"></span>
								<div><span style="color:#999;">注意：图片尺寸800*800，数量：3-6张</span></div>
							</div>
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">商品视频</td>
						<td colspan="2" class="text-left">
					     	<a href="javascript:;" class="file" style="line-height: 30px;">上传文件
						     	<input type="file" id="uploadFile" onchange="uploadVideo(this)">
						     	<video id="videoPre" width="100%" height="400px" hidden="true" ></video>
						     	<input type="hidden" id="mainVideoUrl" name="mainVideoUrl">
						     	<input type="hidden" id="mainVideoSize" name="mainVideoSize">
					     	</a>
					     	<p id="videoName"></p>
					     	<p style="color: gray;">温馨提示：手动上传视频大小限制为100M</p>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<c:if test="${isShop == true}">
		<h4><label>食品安全</label></h4>
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
				<tr>
					<td class="editfield-label-td">保质期<em class="ad-em">*</em></td>
					<td colspan="2" class="text-left">
						<input class="ad-select" type="text" id="shelfLife" name="shelfLife" style="width: 145px;margin-right: 10px;" validate="{required:true}">
						<%--<span>最多还可输入：</span><span style="color:red;margin: 0 3px;" id="product_name_length">50</span><span>个</span>--%>
					</td>
				</tr>
				<tr>
					<td class="editfield-label-td">贮存条件<em class="ad-em">*</em></td>
					<td colspan="2" class="text-left">
						<input placeholder="限制0-20字之间" maxlength="20" class="ad-select" type="text" id="storageConditions" name="storageConditions" onkeyup="$('#storageConditions_name_length').text(20-($('#storageConditions').val().length));" style="width: 530px;margin-right: 10px;" validate="{required:true,rangelength:[0,20]}">
						<span>最多还可输入：</span><span style="color:red;margin: 0 3px;" id="storageConditions_name_length">20</span><span>个</span>
					</td>
				</tr>
				<tr>
					<td class="editfield-label-td">产地<em class="ad-em">*</em></td>
					<td colspan="2" class="text-left">
						<textarea rows="3" maxlength="200" id="placeOfOrigin" name="placeOfOrigin" style="width: 530px;resize:none;border: 1px solid #ccc;margin-right: 10px;" onkeyup="$('#placeOfOrigin_name_length').text(200-($('#placeOfOrigin').val().length));" validate="{required:true,rangelength:[0,200]}"></textarea>
						<span>最多还可输入：</span><span style="color:red;margin: 0 3px;" id="placeOfOrigin_name_length">200</span><span>个</span>
					</td>
				</tr>

				<tr id="foodLabelPicTr">
					<td class="editfield-label-td">食品标签<em class="ad-em">*</em></td>
					<td colspan="2" class="text-left">
						<div id="foodLabel-pic-container">
							<ul class="docs-pictures clearfix td-pictures" id="foodLabel-pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
							</ul>
							<div style="position: relative">
								<span>
									<div style="display: inline-block;" onclick="$('#foodLabelPicErrorMsg').text('');" id="foodLabelFilePicker" class="filePicker">上传照片</div>
									<div id="foodLabelPicErrorMsg" style="vertical-align: top;color: red;"></div>
								</span>
								<span style="position: absolute;top: -5px;right:300px;">
									<img src="static/images/foodLabel.png" width="50px" height="50px" onclick="viewerPic(this.src)">
									<div style="color:#999;margin-left:15px;">示例</div>
								</span>
							</div>
							<div>
								<span style="color:#999;">注意：图片宽度640-800PX，数量：1张</span>
							</div>
						</div>
					</td>
				</tr>

				<tr id="producerInformationTr">
					<td class="editfield-label-td">生产者信息<em class="ad-em">*</em></td>
					<td colspan="2" class="text-left">
						<div style="display: flex;justify-content: space-around">
							<textarea rows="3" maxlength="200" id="producerInformation" name="producerInformation" style="width: 530px;resize:none;border: 1px solid #ccc;" validate="{required:true,rangelength:[0,200]}"></textarea>
							<div style="color: #999;">示例：<br>
							厂址：福建省厦门市思明区********<br>
							公司联系方式/邮编：361000<br>
							食品生产许可证/卫生许可证：sc156218551<br>
							多个产地，分别填写以上内容；<br>
							限制200个字
						</div>
						</div>
					</td>
				</tr>
				<tr id="approvalNumberTr">
					<td class="editfield-label-td">批准文号<em class="ad-em">*</em></td>
					<td colspan="2" class="text-left">
						<input class="ad-select" maxlength="20" type="text" id="approvalNumber" name="approvalNumber" style="width: 200px;margin-right: 10px;" value="${product.recommendRemarks}" validate="{required:true,rangelength:[0,20]}">
						<span style="color: #999999;">提示批准文号为：国食健字G20191223 限制20个字</span>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		</c:if>

		<h4><label>商品详细描述</label></h4>
		<div class="container" style="width:100%;border: 1px solid #ddd;padding: 20px;">
		<p class="txt-detail">
			<strong>文字描述</strong>
			（选填项）
		</p>
		<textarea rows="5" class="txt-area" id="productDesc"></textarea>
		<div style="display: inline-block;">一行一个参数，最多18行。总字数，不超过1000汉字。<br><a href="javascript:void(0);" id="viewProductDescExample">查看实例</a>
		<script type="text/javascript">
		$(function(){
			var popoverContent='<textarea rows="5" style="width:100%;">产品名称：XTEP/特步 983119529261\n是否商场同款: 是\n颜色分类: 红色 灰色 黑色\n款号: 983119529261\n品牌: XTEP/特步\n上市时间: 16年冬季\n吊牌价: 339\n性别: 男子\n鞋底材质: 耐磨橡胶\n适用场地: 室外硬地 室内地板\n运动鞋科技: 易弯折功能\n功能: 减震 耐磨 透气 包裹性 支撑 平衡 抗冲击 轻便 增高\n鞋码: 39 40 41 42 43 44 45\n闭合方式: 系带\n运动系列: 综训鞋\n是否瑕疵: 否</textarea>';
			$('#viewProductDescExample').popover({"html" : true,"content":popoverContent,"trigger":"focus"});
		})
		</script>
		</div>
		<br>
		<br>
		<p class="txt-detail">
			<strong>视频描述</strong>
			（选填项）
			<span>（要求：最多可上传1个视频，视频大小不能超过100M。）</span>
		</p>
		<div class="form-group">
			<a href="javascript:;" class="file" style="line-height: 30px;">
				选择视频
				<input type="file" onchange="uploadDescVideo(this)">

				<input type="hidden" id="descVideoUrl" name="descVideoUrl">
				<input type="hidden" id="descVideoSize" name="descVideoSize">
			</a>
			<div style="display: none;height: 150px;">
				<ul id="imgBox">
  					<li>
  						<a href="javascript:;" >
	  						<img src="" style="width: 200px;height: 133.33px;" id="videoCover" onclick="toShowVideo();"/>
	  						<div class="pic-btn-container" style="height: 20px;margin-left: -3px;">
		  						<span class="glyphicon glyphicon-remove pic-remove-icon" onclick="removeVideo();"></span>
	  						</div>
	  						<div class="toolbar">
	  							<img src="${ctx }/static/images/bf.png" border="0" onclick="toShowVideo();">
	  						</div>
  						</a>
  					</li>
				</ul>
			</div>
		</div>
		<br>
		<br>
		<p class="txt-detail">
			<strong>图片描述</strong>
			<em>*</em>
			<span>（图片要求：单张宽度：640-800象素，所有图片总大小不能超过10M。提示：拖拽图片可以调换图片位置）</span>
		</p>
		<div id="desc-pic-container">
							<ul class="docs-pictures clearfix td-pictures" id="desc-pictures-list"
								ondrop="drop(event)" ondragover="allowDrop(event)">
							</ul>
							<div id="descFilePicker" style="display: inline-block;" onclick="$('#descPicErrorMsg').text('');" class="filePicker">选择图片</div>
							<span id="descPicErrorMsg" style="margin-left: 10px;color: red;display: inline-block;line-height: 34px;vertical-align: bottom;"></span>
		</div>
		<div style="text-align: right; width: 100%;">
		<span class="btn btn-default" onclick="preViewProduct();">预览</span>
		</div>


		</div>

		<br>
		<h4><label>设置商品销售规格与价格</label><em class="ad-em">*</em></h4>
		<div class="row">
			<div class="col-md-12">
				<div class="ad-box">
					<input type="radio" name="isSingleProp" onclick="selectPropType();" value="1">单规格商品
				</div>
				<div class="ad-box">
					<input type="radio" name="isSingleProp" onclick="selectPropType();" value="0" checked="checked">多规格商品 (创建多种颜色多种规格的商品，颜色分类为必填项 )
				</div>
			</div>
		</div>
		<div id="multiple_prop_container" class="container">

		<div class="ad-gg">
			<dl>
				<dt>规格名：</dt>
				<dd>
					<select name="productProp" id="productProp" onchange="changeProductProp()">
						<option value="">--请选择--</option>
						<c:forEach var="productProp" items="${productPropList}">
							<option value="${productProp.id}">${productProp.name}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>

			<dl>
				<dt>规格值：</dt>
				<dd>
					<textarea rows="4" cols="6" id="propValue" placeholder="一行一个值"></textarea>
					<a href='javascript:void(0);' onclick="addProp();">添加</a>
				</dd>
			</dl>

			<dl id="cy-propValue" style="display:none;">
				<dt>常用规格值：</dt>
				<dd class="ad-cy">
					<div onclick="quickSelectPropValue(this)">35<br>36<br>37<br>38<br>39<br>40</div>
					<div onclick="quickSelectPropValue(this)">39<br>40<br>41<br>42<br>43<br>44<br>45</div>
					<div onclick="quickSelectPropValue(this)">S<br>M<br>L<br>XL<br>2XL<br>3XL<br>4XL</div>
					<div onclick="quickSelectPropValue(this)">155<br>160<br>165<br>170<br>175<br>180<br>185</div>
					<div onclick="quickSelectPropValue(this)">100<br>110<br>120<br>130<br>140<br>150<br>160</div>
				</dd>
			</dl>
		</div>

		<div id="prop_container"></div>

		<div class="clearfix"></div>
		<div id="product-item-table-container" class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container">
				<div id="product-item-table">
					<div class="datatable-container">
						<table id="product-item-datatable" style="width:100%;max-width: 100%;"
							class="table table-striped table-bordered dataTable no-footer"
							role="grid" aria-describedby="datatable_info">
						</table>
					</div>
				</div>
					</div>
				</div>
		</div>

		</div>


		<div id="single_prop_container" class="container">

		<div id="single-product-item-table-container" class="x_content container-fluid">
				<div class="row">
					<div class="col-md-12 datatable-container">
				<div id="single-product-item-table">
					<div class="datatable-container">
						<table id="single-product-item-datatable" style="width:100%;max-width: 100%;" class="table table-striped table-bordered dataTable no-footer" role="grid" aria-describedby="datatable_info">
						</table>
					</div>
				</div>
					</div>
				</div>
		</div>

		</div>


		<br>
		<h4><label>商品服务承诺及其他信息</label></h4>
			<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">7天无理由退换货<em class="ad-em">*</em></td>
						<td colspan="2" class="text-left">
					     <input type="radio" name = "isReturn7days" value="1" validate="{required:true}"> 支持
					     <input type="radio" name = "isReturn7days" value="0"> 不支持
						</td>
					</tr>
					<c:if test="${isShop ne 'true'}">
					<tr>
						<td class="editfield-label-td">保质期</td>
						<td colspan="2" class="text-left">
							<input type="text" id="shelfLife" name="shelfLife" value="${product.shelfLife}">
							<%--<span>最多还可输入：</span><span style="color:red;margin: 0 3px;" id="product_name_length">50</span><span>个</span>--%>
						</td>
					</tr>
					</c:if>
					<tr>
						<td class="editfield-label-td">3C认证证书号</td>
						<td colspan="2" class="text-left">
					    <input type="text" maxlength="30" name = "cccNo" id="cccNo" validate="{rangelength:[0,30]}">
						</td>
					</tr>
				</tbody>
			</table>
		</div>



		<br>
		<h4><label>商品售后信息及运费信息</label></h4>
				<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">每人限购数量</td>
						<td colspan="2" class="text-left">
					     <input class="ad-select" type="text" id="limitBuy" name="limitBuy" validate="{digits:true,min:0}">
					     <span style="margin-left: 6px;color: red;">提示：填0表示不限制，填大于0表示每会员ID在7天内购买该商品的数量不超过此数。秒杀活动默认为1。</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">SVIP折扣</td>
						<td colspan="2" class="text-left">
							<select class="ad-select" name="svipDiscount" style="<c:if test="${sessionScope.mchtInfo.mchtType eq '1'}">background-color: #EEEEEE;</c:if>" id="svipDiscount" validate="{svipDiscount:true}" <c:if test="${sessionScope.mchtInfo.mchtType eq '1'}">disabled="disabled"</c:if>>
								<option value="">请选择</option>
								<option value="0.95" <c:if test="${sessionScope.mchtInfo.mchtType eq '1'}">selected</c:if>>9.5</option>
								<option value="0.9">9.0</option>
								<option value="0.85">8.5</option>
								<option value="0.80">8.0</option>
								<option value="0.75">7.5</option>
								<option value="0.70">7.0</option>
								<option value="0.65">6.5</option>
								<option value="0.60">6.0</option>
								<option value="0.55">5.5</option>
								<option value="0.50">5.0</option>
								<option value="0.45">4.5</option>
								<option value="0.40">4.0</option>
                            </select>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">退货地址</td>
						<td colspan="2" class="text-left">
					      <select class="ad-select" onchange="changeCstemplet();"  name="csTempletId" id="csTempletId" validate="{required:true}" disabled="true">
                          </select>
						<a id="addCsTempletInfoBtn" style="display: none;" class='table-opr-btn' href='javascript:void(0);' onclick="addTpl();">添加</a>
                          <div id="csTempletInfoContainer"></div>
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">运费</td>
						<td colspan="2" class="text-left">
					      <select class="ad-select" onchange="changeFreightTemplate(this);"  name="freightTemplateId" id="freightTemplate_Id">
                          	<option value="">全国包邮</option>
                          	<c:forEach items="${freightTemplates}" var="freightTemplate">
                          		<option value="${freightTemplate.id}">${freightTemplate.name}</option>
                          	</c:forEach>
                          </select>
                          <a id="addFreightTempletBtn"  class='table-opr-btn' href='javascript:void(0);' onclick="editFreightTemplate('');">添加运费模板</a>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">运费详情</td>
						<td colspan="2" class="text-left">
					      <table class="table table-bordered ">
								<tbody id="freightDetail">
									<tr>
										<td colspan="2" class="editfield-label-td">运送范围</td>
										<td class="text-left">首件运费（元）</td>
										<td class="text-left">增件运费（元）</td>
										<td class="text-left">运费满减</td>
									</tr>
									<tr id="defaultFreightTr">
										<td colspan="2" class="editfield-label-td">全国包邮</td>
										<td class="text-left">0</td>
										<td class="text-left">0</td>
										<td class="text-left">满一件包邮</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">商品类型</td>
						<td colspan="2" class="text-left">
						<c:if test="${saleType==1}">
						 品牌活动款 <span style="margin-left: 6px;color: red;">（可以报品牌特卖活动，显示在商城店铺中）</span>
						</c:if>
						<c:if test="${saleType==2}">
						 单品活动款 <span style="margin-left: 6px;color: red;"> （可以报单品活动，不显示在商城店铺中）</span>
						</c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<input id="commitType" type="hidden" name="commitType" value="">
		</form>

		<div class="modal-footer" style="text-align: left;">
            <c:if test="${saleType=='1'}">
				<button class="btn btn-info" onclick="$('#commitType').val('0');commitSave();">保存草稿</button>
				<button class="btn btn-info" onclick="$('#commitType').val('1');commitSave();">发布并上架</button>
				<span style="margin-left: 6px;color: red;">提示：发布后立即上架销售</span>
			</c:if>
			<c:if test="${saleType=='2'}">
				<button class="btn btn-info" onclick="$('#commitType').val('1');commitSave();">提交审核</button>
				<span style="margin-left: 6px;color: red;">提示：若审核通过后自动上架销售</span>
			</c:if>
      	</div>
    </div>


	</div>






<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal1" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel1" data-backdrop="static"></div>

<div class="modal fade" id="batchSetModal" tabindex="-1" role="dialog" aria-labelledby="batchSetModal" data-backdrop="static" >
  <div id="test" class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">批量设置数值</h4>
      </div>
      <div class="modal-body container-fluid">
      <div class="row">
       <div class="col-md-3">
       	<p>
       		<span class="bold">价格：</span>
       	</p>
      	<div id="batchSetPriceContainer"></div>
       </div>
       <div class="col-md-3">
       	<p>
       		<span class="bold">库存：</span>
       	</p>
        <div id="batchSetStockContainer">
      	</div>
       </div>
       <div class="col-md-3">
       	<p>
       		<span class="bold">SKU商家编码：</span>
       	</p>
        <div id="batchSetSkuContainer">
      	</div>
       </div>
       <div class="col-md-3">
       	<p>
       		<span class="bold">SKU图：</span>
       	</p>
        <div id="batchSetSkuPicContainer">
      	</div>
       </div>
      </div>
      </div>
      	<div class="modal-footer">
      	    <input id="selectDataId" type="hidden" value="">
			<button class="btn btn-info" onclick="batchSetConfirm();">设置</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>




<div class="modal fade" id="batchSetStockModal"  tabindex="-1" role="dialog" aria-labelledby="batchSetStockModal" data-backdrop="static" >
  <div id="test" class="modal-dialog" role="document" style="width: 800px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >批量库存设置</h4>
      </div>
      <div class="modal-body container-fluid">
      <div class="row">
      <div class="col-md-4 text-center">
      <p class="p-select">
      <span class="bold">选择规格：</span><span class="c9">（按住Ctrl、Shift键可多选）</span>
      </p>
      <select class="p1-select" size="20" multiple="multiple" id="product-item-all">
	  </select>
	  </div>
      <div class="col-md-1" style="padding: 0 3px;">
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <button onclick="productItemSelectIn();" class="btn btn-default" style="padding:2px 8px;">选入-&gt;</button>
      <br>
      <br>
      <button onclick="productItemSelectOut();" class="btn btn-default" style="padding:2px 8px;">&lt;-移出</button>
	  </div>
      <div class="col-md-4 nth-f8">
      <p class="p-select">
      <span class="bold">选中规格：</span>
      </p>
      <select class="p1-select" size="20" multiple="multiple" id="product-item-selected">

	  </select>
	  </div>

      <div class="col-md-3 nth-f8">
      <p class="p-select">
      <span class="bold">库存：</span><span class="c9">（一行一个值）</span>
	  </p>
   	  <textarea class="p1-select" id="stock-Values-Area" rows="20"></textarea>
	  </div>

      </div>
      </div>
      	<div class="modal-footer">
			<button class="btn btn-info" onclick="batchSetStockConfirm();">设置</button>
			<input id="batchSetStockUnselected" type="checkbox"><span>同时设置没选中的库存为0</span>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="batchSetSkuModal"  tabindex="-1" role="dialog" aria-labelledby="batchSetSkuModal" data-backdrop="static" >
  <div id="test" class="modal-dialog" role="document" style="width: 800px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" >批量SKU编码设置</h4>
      </div>
      <div class="modal-body container-fluid">
      <div class="row">
      <div class="col-md-4 text-center">
      <p class="p-select">
      	<span>选择规格：</span><span class="c9">（按住Ctrl、Shift键可多选）</span>
      </p>
      <select class="p1-select" size="20" multiple="multiple" id="product-item-all-sku">
	  </select>
	  </div>
      <div class="col-md-1" style="padding: 0 3px;">
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <button onclick="productItemSelectIn4SetSku();" class="btn btn-default" style="padding:2px 8px;">选入-&gt;</button>
      <br>
      <br>
      <button onclick="productItemSelectOut4SetSku();" class="btn btn-default" style="padding:2px 8px;">&lt;-移出</button>
	  </div>
      <div class="col-md-4 nth-f8">
      <p class="p-select">
      	<span>选中规格：</span>
      </p>
      <select class="p1-select" size="20" multiple="multiple" id="product-item-selected-sku">

	  </select>
	  </div>

      <div class="col-md-3 nth-f8">
      <p class="p-select">
      	<span>SKU商家编码：</span><span class="c9">（一行一个值）</span>
      </p>
   	  <textarea class="p1-select" id="sku-Values-Area" rows="20"></textarea>
	  </div>

      </div>
      </div>
      	<div class="modal-footer">
			<button class="btn btn-info" onclick="batchSetSkuConfirm();">设置</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="preViewModal"  tabindex="-1" role="dialog" aria-labelledby="preViewModal">
  <div class="modal-dialog" role="document" style="width: 600px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body container-fluid">
      	<p>
      		<span class="bold">商品参数</span>
      	</p>
      	<div>
      		<img src="${ctx }/static/images/u8.png">
      	</div>
      	<div id="productDesc-preView-container"></div>
      	<p>
      		<span class="bold">商品详情</span>
      	</p>
      	<div>
      		<img src="${ctx }/static/images/u8.png">
      	</div>
    	<div id="productImgDesc"></div>
    </div>
      <div class="modal-footer">
			<button class="btn btn-info" data-dismiss="modal">关闭</button>
      </div>
  </div>
</div>
</div>

<!--弹出视频信息Div-->
<div class="video_box" style="position:fixed; width:660px; height:440px; top:20%; left:35%; display: none;" id="descVideoDiv">
	    <div class="modal-content">
	    <div class="modal-header">
	    	<span class="modal-title" >视频描述 </span>
	    	<a href="javascript:;" class="video_close" onclick="closeVideo();"><img src="${ctx}/static/images/close.png"></a>
	    </div>
		<div class="modal-body">
			<form id="dataFrom">
				<video id="descVideoPre" width="600px" height="400px" hidden="true" controls="controls"></video>
			</form>
		</div>
		</div>
</div>
<ul class="docs-pictures clearfix td-ul" id="viewer-pictures" style="display: none;">

</ul>
<div class="black_box" style="display: none;" id="productBox"></div>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var viewerPictures;
	function viewerPic(imgPath){

		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
				$("#viewer-pictures").hide();
			}});
		viewerPictures.show();

	}

	function setReturn7daysDefault(){
		var isReturn7days = $("#productTypeId").find("option:selected").attr("return7days");

		if (isReturn7days==""){
			isReturn7days=$("#productType2").find("option:selected").attr("return7days");
		}
		if (isReturn7days==""){
			isReturn7days=$("#productType1").find("option:selected").attr("return7days");
		}

		if(isReturn7days=="0"){
			$("input:radio[name='isReturn7days'][value='0']").prop('checked',true);
			$("input:radio[name='isReturn7days']").attr("disabled", true);
		}else if(isReturn7days=="1"){
			$("input:radio[name='isReturn7days'][value='1']").prop('checked',true);
			$("input:radio[name='isReturn7days']").prop("disabled", true);
		}else{
			$("input:radio[name='isReturn7days'][value='0']").prop('checked',false);
			$("input:radio[name='isReturn7days'][value='1']").prop('checked',false);
			$("input:radio[name='isReturn7days']").prop("disabled",false);
		}
	}



	function quickSelectProductType(productType1Id,productType2Id,productType3Id){
		getProductTypeList('productType2',productType1Id);
		getProductTypeList('productTypeId',productType2Id);
		$("#productType1").val(productType1Id);
		$("#productType2").val(productType2Id);
		$("#productTypeId").val(productType3Id);
		setReturn7daysDefault();

        if(${isShop eq true}){
            var select = productType2Id;
            var name1 = '${sysParamCfg1.paramValue}';
            var name2 = '${sysParamCfg2.paramValue}';
            name1 = name1.split(",");
            name2 = name2.split(",");
            $('#approvalNumberTr').show();
            $('#approvalNumber').attr("disabled", "");
            $('#approvalNumber').attr("validate", '{required:true,rangelength:[0,20]}');
            $('#producerInformationTr').show();
            $('#producerInformation').attr("disabled", "");
            $('#producerInformation').attr("validate", '{required:true,rangelength:[0,40]}');
            $('#foodLabelPicTr').show();
            if(name1.indexOf(select) != -1) {
                $('#approvalNumberTr').hide();
                $('#approvalNumber').attr("disabled", true);
                $('#approvalNumber').attr("validate", '');
                $('#producerInformationTr').hide();
                $('#producerInformation').attr("disabled", true);
                $('#producerInformation').attr("validate", '');
                $('#foodLabelPicTr').hide();
                tag = 0;
            }else if(name2.indexOf(select) != -1){
                $('#approvalNumberTr').show();
                $('#approvalNumber').attr("disabled", false);
                $('#producerInformationTr').show();
                $('#producerInformation').attr("disabled", false);
                $('#foodLabelPicTr').show();
                tag = 1;
            } else{
                $('#approvalNumberTr').hide();
                $('#approvalNumber').attr("disabled", true);
                $('#producerInformationTr').show();
                $('#producerInformation').attr("disabled", false);
                $('#foodLabelPicTr').show();
                tag = 2;
            }
        }
	}

	function changeFreightTemplate(_this){
		var freightTemplateId = $(_this).val();
		if(freightTemplateId){
			$.ajax({
				method: 'POST',
				url: '${ctx}/freightTemplate/getProvinceFreightCustoms',
				data: {"freightTemplateId":freightTemplateId},
				cache : false,
				async : false,
				dataType: 'json'
			}).done(function (result) {
				if (result.returnCode =='0000') {
					var provinceFreightCustoms = result.returnData.provinceFreightCustoms;
					$("#defaultFreightTr").remove();
					$("tr[name='provinceFreightTr']").remove();
					var html = [];
					for(var i=0;i<provinceFreightCustoms.length;i++){
						var areaNames = provinceFreightCustoms[i].areaNames;
						var firstFreight = provinceFreightCustoms[i].firstFreight;
						var additionalFreight = provinceFreightCustoms[i].additionalFreight;
						html.push('<tr name="provinceFreightTr"><td colspan="2" class="editfield-label-td">'+areaNames+'</td><td class="text-left">'+firstFreight+'</td><td class="text-left">'+additionalFreight+'</td></tr>');
					}
					$("#freightDetail").append(html.join(""));
				}else{
					swal("设置失败，请稍后重试");
				}
			});
		}else{
			$("#defaultFreightTr").remove();
			$("tr[name='provinceFreightTr']").remove();
			$("#freightDetail").append('<tr id="defaultFreightTr"><td colspan="2" class="editfield-label-td">全国包邮</td><td class="text-left">0</td><td class="text-left">0</td></tr>');
		}
	}

	function changeProductProp(){
		var selectProp=$("#productProp").find("option:selected").text();
		if(selectProp=="尺码"){
			$("#cy-propValue").show();
		}else{
			$("#cy-propValue").hide();
		}
	}

	function selectBrand(element){
		$("#csTempletId").val("");
		$("#csTempletInfoContainer").html("");
		if($(element).val()==""){
			$("#csTempletId").attr("disabled",true);
			$("#addCsTempletInfoBtn").hide();

		}else{
			getProductAfterTempleList($(element).val());
			$("#csTempletId").attr("disabled",false);
			$("#addCsTempletInfoBtn").show();

		}
		getProductTypeList("productType1",1);
		if($(element).val() == ${smallwareBrandId}){
			$("#productType2").empty();
			$("#productTypeId").empty();
		}
	}

	function preViewProduct(){
		$("#productDesc-preView-container").empty();
		$("#productImgDesc").empty();

		var productDescArr=$.trim($("#productDesc").val()).split('\n');
		for(var i=0;i<productDescArr.length;i++){
			$("#productDesc-preView-container").append('<div>'+productDescArr[i]+'</div><br>');
		}

		$("#desc-pictures-list").find("img").each(function(index,element){
			$("#productImgDesc").append('<img src="'+$(element).attr("src")+'" width=568>');
		});
		$("#preViewModal").modal();
	}

	function selectPropType(){
		var propType=$("input[type=radio][name='isSingleProp']:checked").val();
			if(propType=="1"){
				$("#product-item-table-container").html("");
				$("#multiple_prop_container").hide();
				$("#single-product-item-table-container").html(singlePropContainerHtml);
				$("#single_prop_container").show();
				if(typeof(singleProductItemDatatable)!="undefined"){
					singleProductItemDatatable.destroy();
					 $('#single-product-item-datatable').empty();
				}
				if(typeof(productItemDatatable)!="undefined"){
					productItemDatatable.destroy();
					 $('#product-item-datatable').empty();
				}

				initSinglePropProductItemTable();
			}else{
				$("#product-item-table-container").html(multiplePropContainerHtml);
				$("#multiple_prop_container").show();
				$("#single-product-item-table-container").html("");
				$("#single_prop_container").hide();

				if(typeof(singleProductItemDatatable)!="undefined"){
					singleProductItemDatatable.destroy();
					 $('#single-product-item-datatable').empty();
				}
				if(typeof(productItemDatatable)!="undefined"){
					productItemDatatable.destroy();
					 $('#product-item-datatable').empty();
				}

				createProductItemDatatableColumns();
				initProductItemTable();
			}
	}
</script>

<script type="text/javascript">

	var productItemDatatable;
	var singleProductItemDatatable;
	var editor;
	var productItemDatatableColumns=[];
	// var productItemDatatableEditFields=[];
	var productPropIdArry=[];//属性id
	var productPropValuesArry=[];//属性数据
	var productPropValuesSelectedArry=[];//选中属性数据

	var productMainPics=[];//商品主图
	var productDescPics=[];//商品主图
	var foodLabelPics = "";//商品标签图
    var tag = 0;
	function createProductItemDatatableColumns(){
		productItemDatatableColumns=[];
		productItemDatatableColumns.push({"title": "SKU图", "width":"100", "data": "skuPic","render": function (data, type, row, meta) {
			if(row.skuPicBase64!=""){
				return '<div class="sku_pic_picker" id="sku_pic_'+row.DT_RowId+'"><input onchange="loadImageFile(this,'+"'"+row.DT_RowId+"'"+')" type="file" style="width:100px;"><img src="'+row.skuPicBase64+'"><input id="skuPicName_'+row.DT_RowId+'"  type="hidden" validate="{required:true}" value="'+row.skuPicName+'"></div>';
			}else{
			if(data!=""){
				return '<div class="sku_pic_picker" id="sku_pic_'+row.DT_RowId+'"><input onchange="loadImageFile(this,'+"'"+row.DT_RowId+"'"+')" type="file" style="width:100px;"><img src="'+data+'"><input id="skuPicName_'+row.DT_RowId+'"  type="hidden" validate="{required:true}" value="'+row.skuPicName+'"></div>';
			}else{
				return '<div class="sku_pic_picker" id="sku_pic_'+row.DT_RowId+'"><input onchange="loadImageFile(this,'+"'"+row.DT_RowId+"'"+')" type="file" style="width:100px;"><div>+</div><input id="skuPicName_'+row.DT_RowId+'"  type="hidden" validate="{required:true}" value="'+row.skuPicName+'"></div>';
			}
			}
		}});

		productItemDatatableColumns.push({"title": "规格", "width": "230", "data": "DT_RowId","render": function (data, type, row, meta) {

				if(productPropIdArry.length==1){
					return row[productPropIdArry[0]];
				}
				var propColumnValues="";
				for(var i=0;i<productPropIdArry.length;i++){
					propColumnValues=propColumnValues+"<br>"+row[productPropIdArry[i]];
				}
				return propColumnValues;
		 }});

		productItemDatatableColumns.push({"title": "吊牌价", "width":"120", "data": "tagPrice","render": function (data, type, row, meta) {
			return '<input class="ad-select" onkeyup="validatePrice(this);" id="tagPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01}"  type="text" value="'+data+'"  onblur="editProductItemTagPrice('+"'"+row.DT_RowId+"'"+',this);">';
		 }});

		if('${sessionScope.mchtInfo.shopStatus}' == '1'){
			productItemDatatableColumns.push({"title": "商城价", "width":"120", "data": "mallPrice","render": function (data, type, row, meta) {
				return '<input class="ad-select" id="mallPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,mallTagPrice:true}"  type="text" value="'+data+'"  onblur="editProductItemMallPrice('+"'"+row.DT_RowId+"'"+',this);">';
			}});
		};

		productItemDatatableColumns.push({"title": "活动价", "width":"120", "data": "salePrice","render": function (data, type, row, meta) {
			return '<input class="ad-select" id="salePrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,salePrice:true,mallPrice:true,salePriceDiscount:true}"  type="text" value="'+data+'"  onblur="editProductItemSalePrice('+"'"+row.DT_RowId+"'"+',this);">';
		}});

		if('${sessionScope.mchtInfo.mchtType}' == '1'){
			productItemDatatableColumns.push({"title": "结算价","width":"120", "data": "costPrice","render": function (data, type, row, meta) {
				return '<input class="ad-select costPrice" id="costPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,costPrice:true,costPriceRate:true}"  type="text" value="'+data+'"  onblur="editProductItemCostPrice('+"'"+row.DT_RowId+"'"+',this);">';
			}});
		}
		if(${sessionScope.mchtInfo.mchtType eq '1' and sessionScope.mchtInfo.isManageSelf eq '1'}){
			productItemDatatableColumns.push({"title": "自营运费", "width": "130", "data": "selfOperatedFreight","render": function (data, type, row, meta) {
					return '<input class="ad-select selfOperatedFreight" id="selfOperatedFreight_'+row.DT_RowId+'" type="text" validate="{number:true}" onblur="editProductItemSelfOperatedFreight('+"'"+row.DT_RowId+"'"+',this);">';
			}});
		};
		productItemDatatableColumns.push({"title": "库存 <span style='margin-left:5px;vertical-align: middle;' onclick='batchSetStock();' class='glyphicon glyphicon-cog batch-set-icon'></span>", "width":"120", "data": "stock","render": function (data, type, row, meta) {
			return '<input class="ad-select" id="stock_'+row.DT_RowId+'" validate="{required:true,digits:true,min:'+row.lockedAmount+'}"   type="text" value="'+data+'"  onblur="editProductItemStock('+"'"+row.DT_RowId+"'"+',this);">';
		}});
		productItemDatatableColumns.push({"title": "SKU商家编码<span onclick='batchSetSku();' style='margin-left:5px;vertical-align: middle;'  class='glyphicon glyphicon-cog batch-set-icon'></span>", "data": "sku", "width":"160","render": function (data, type, row, meta) {
			return '<input class="ad-select" id="sku_'+row.DT_RowId+'"  type="text" value="'+data+'"   onblur="editProductItemSku('+"'"+row.DT_RowId+"'"+',this);">';
		}});
		productItemDatatableColumns.push({"title": "批量操作", "width":"78", "data": "sku","render": function (data, type, row, meta) {
			return '<span onclick="batchSet('+"'"+row.DT_RowId+"'"+')" style="vertical-align: middle;" class="glyphicon glyphicon-cog batch-set-icon"></span>';
		}});

	}



	/* --------------批量设置sku------------------- */
	function batchSetSku(){
		$("#product-item-all-sku").empty();
		$("#product-item-selected-sku").empty();
		$("#sku-Values-Area").val("");

		for(var i=0;i<productItemData.length;i++){
			var text="";
			for(var j=0;j<productPropIdArry.length;j++){
				if(j==0){
					text=text+productItemData[i][productPropIdArry[j]];
				}else{
					text=text+"-"+productItemData[i][productPropIdArry[j]];
				}
			}
			$("#product-item-all-sku").append('<option value="'+i+'">'+text+'</option>');
		}
		$("#batchSetSkuModal").modal();
	}

	function batchSetSkuConfirm(){
		if($("#product-item-selected-sku").find('option').length==0){
			swal('请选择SKU');
			return;
		}
		if($("#sku-Values-Area").val()==""){
			swal('请输入SKU值');
			return;
		}
		var skuValues=$("#sku-Values-Area").val().split('\n');

		removeLastEmptyArrayItem(skuValues);//移除数组后面的空格

		if($("#product-item-selected-sku").find('option').length!=skuValues.length){
			swal("输入的值个数与选择的SKU个数不匹配，请检查");
			return;
		}

		$("#product-item-selected-sku").find('option').each(function(index,element){
			var productItemIndex=parseInt($(element).val());
			productItemData[productItemIndex].sku=skuValues[index];
		});

		productItemDatatable.destroy();
		$('#product-item-datatable').empty();
		initProductItemTable();
		$("#batchSetSkuModal").modal('hide');

	}


	function productItemSelectIn4SetSku(){
		$("#product-item-all-sku").find('option:selected').each(function(){
			$(this).attr("selected",false);
			$("#product-item-selected-sku").append($(this));
		});
	}
	function productItemSelectOut4SetSku(){
		$("#product-item-selected-sku").find('option:selected').each(function(){
			$("#product-item-all-sku").append($(this));
		});
	}

	/* --------------批量设置sku  end------------------- */






	/* --------------批量设置库存begin------------------- */
	function batchSetStock(){
		$("#product-item-all").empty();
		$("#product-item-selected").empty();
		$("#stock-Values-Area").val("");

		for(var i=0;i<productItemData.length;i++){
			var text="";
			for(var j=0;j<productPropIdArry.length;j++){
				if(j==0){
					text=text+productItemData[i][productPropIdArry[j]];
				}else{
					text=text+"-"+productItemData[i][productPropIdArry[j]];
				}
			}
			$("#product-item-all").append('<option value="'+i+'">'+text+'</option>');
		}

		$("#batchSetStockModal").modal();
	}

	//移除数组后面的空格元素，类是trim();
	function removeLastEmptyArrayItem(arr){
		if(arr.length>0&&arr[arr.length-1]==""){//移除最后一个空行
			arr.splice(arr.length-1,1);
			removeLastEmptyArrayItem(arr);
		}
	}

	function batchSetStockConfirm(){
		if($("#product-item-selected").find('option').length==0){
			swal('请选择SKU');
			return;
		}
		if($("#stock-Values-Area").val()==""){
			swal('请输入库存值');
			return;
		}
		var stockValues=$("#stock-Values-Area").val().split('\n');

		removeLastEmptyArrayItem(stockValues);//移除数组后面的空格

		if($("#product-item-selected").find('option').length!=stockValues.length){
			swal("输入的值个数与选择的SKU个数不匹配，请检查");
			return;
		}

		var hasErrorValue=false;
		for(var i=0;i<stockValues.length;i++){
			if($.trim(stockValues[i])==""||isNaN(parseInt($.trim(stockValues[i])))){
				hasErrorValue=true;
			}
		}
		if(hasErrorValue){
			swal("输入的值有错误，请检查");
			return;
		}
		$("#product-item-selected").find('option').each(function(index,element){
			var productItemIndex=parseInt($(element).val());
			productItemData[productItemIndex].stock=stockValues[index];
		});

		if($("#batchSetStockUnselected").prop("checked")){
			$("#product-item-all").find('option').each(function(index,element){
				var productItemIndex=parseInt($(element).val());
				productItemData[productItemIndex].stock=0;
			});
		}

		productItemDatatable.destroy();
		$('#product-item-datatable').empty();
		initProductItemTable();
		$("#batchSetStockModal").modal('hide');

	}


	function productItemSelectIn(){
		$("#product-item-all").find('option:selected').each(function(){
			$(this).attr("selected",false);
			$("#product-item-selected").append($(this));
		});
	}
	function productItemSelectOut(){
		$("#product-item-selected").find('option:selected').each(function(){
			$("#product-item-all").append($(this));
		});
	}

	/* --------------批量设置库存end------------------- */


	function batchSetConfirm(){

		var dataId=$("#selectDataId").val();
		var selectData;
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				selectData=productItemData[i];
				break;
			}
		}

		var setPiceSelect=$("input[type=checkbox][name=batchSetPriceCheckbox]:checked").val();
		var setStockSelect=$("input[type=checkbox][name=batchSetStockCheckbox]:checked").val();
		var setSkuSelect=$("input[type=checkbox][name=batchSetSkuCheckbox]:checked").val();
		var setSkuPicSelect=$("input[type=checkbox][name=batchSetSkuPicCheckbox]:checked").val();
		var sourceSkuPicItemDatas=[];
		for(var j=0;j<productItemData.length;j++){
			if(setPiceSelect!=undefined){
				if(setPiceSelect==0){
					productItemData[j].tagPrice=selectData.tagPrice;
					productItemData[j].mallPrice=selectData.mallPrice;
					productItemData[j].salePrice=selectData.salePrice;
					productItemData[j].costPrice=selectData.costPrice;
					productItemData[j].selfOperatedFreight=selectData.selfOperatedFreight;
				}else{
					if(productItemData[j][setPiceSelect]==selectData[setPiceSelect]){
						productItemData[j].tagPrice=selectData.tagPrice;
						productItemData[j].mallPrice=selectData.mallPrice;
						productItemData[j].salePrice=selectData.salePrice;
						productItemData[j].costPrice=selectData.costPrice;
						productItemData[j].selfOperatedFreight=selectData.selfOperatedFreight;
					}
				}
			}
			if(setStockSelect!=undefined){
				if(setStockSelect==0){
					productItemData[j].stock=selectData.stock;
				}else{
					if(productItemData[j][setStockSelect]==selectData[setStockSelect]){
						productItemData[j].stock=selectData.stock;
					}
				}
			}
			if(setSkuSelect!=undefined){
				if(setSkuSelect==0){
					productItemData[j].sku=selectData.sku;
				}else{
					if(productItemData[j][setSkuSelect]==selectData[setSkuSelect]){
						productItemData[j].sku=selectData.sku;
					}
				}
			}
			if(setSkuPicSelect!=undefined){

				if(setSkuPicSelect==0){
					productItemData[j].skuPic=selectData.skuPic;
					productItemData[j].skuPicBase64=selectData.skuPicBase64;
					productItemData[j].skuPicName=selectData.skuPicName;

					//如果是图片源的sku，则要重新设置新的图片源sku
					if(productItemData[j].isSourceSkuPicItem=="1"&&productItemData[j].DT_RowId!=selectData.DT_RowId){
						sourceSkuPicItemDatas.push(productItemData[j]);
					}

					if(selectData.sourceSkuPicItemId!=""){
					   productItemData[j].sourceSkuPicItemId=selectData.sourceSkuPicItemId;
					}else{
					   productItemData[j].sourceSkuPicItemId=selectData.DT_RowId;
					   selectData.isSourceSkuPicItem="1";
					   selectData.sourceSkuPicItemId=selectData.DT_RowId;
					}

				}else{
					if(productItemData[j][setSkuPicSelect]==selectData[setSkuPicSelect]){
						productItemData[j].skuPic=selectData.skuPic;
						productItemData[j].skuPicBase64=selectData.skuPicBase64;
						productItemData[j].skuPicName=selectData.skuPicName;

						//如果是图片源的sku，则要重新设置新的图片源sku
						if(productItemData[j].isSourceSkuPicItem=="1"&&productItemData[j].DT_RowId!=selectData.DT_RowId){
							sourceSkuPicItemDatas.push(productItemData[j]);
						}

						if(selectData.sourceSkuPicItemId!=""){
							   productItemData[j].sourceSkuPicItemId=selectData.sourceSkuPicItemId;
							}else{
							   productItemData[j].sourceSkuPicItemId=selectData.DT_RowId;
							   selectData.isSourceSkuPicItem="1";
							   selectData.sourceSkuPicItemId=selectData.DT_RowId;
						}
					}
				}
			}
		}

		//重新设置新的图片源sku
		if(sourceSkuPicItemDatas.length>0){
			for (var k = 0; k < sourceSkuPicItemDatas.length; k++) {
				var newSourceSkuPicImteId="";
				for (var m = 0; m < productItemData.length; m++) {
					if(productItemData[m].sourceSkuPicItemId==sourceSkuPicItemDatas[k].DT_RowId){
						if(newSourceSkuPicImteId==""){
							newSourceSkuPicImteId=productItemData[m].DT_RowId;
							productItemData[m].isSourceSkuPicItem="1";
							productItemData[m].sourceSkuPicItemId=productItemData[m].DT_RowId;
						}
						productItemData[m].sourceSkuPicItemId=newSourceSkuPicImteId;
					}
				}
				sourceSkuPicItemDatas[k].isSourceSkuPicItem="0";
			}
		}

		productItemDatatable.destroy();
		$('#product-item-datatable').empty();
		initProductItemTable();
		$("#batchSetModal").modal('hide');

	}


	function batchSet(dataId){

		$("#selectDataId").val(dataId);

		var batchSetPriceContainerHtml=[];
		batchSetPriceContainerHtml.push('<div class="ad-box"><input onclick="selectBatchSetPrice(this);" name="batchSetPriceCheckbox" type="checkbox" value="0"><span>全部</span></div>');
		for(var i=0;i<productPropIdArry.length;i++){
			batchSetPriceContainerHtml.push('<div class="ad-box"><input onclick="selectBatchSetPrice(this);" name="batchSetPriceCheckbox" type="checkbox" value="'+productPropIdArry[i]+'"><span>同'+$("#productProp").find("option[value="+productPropIdArry[i]+"]").text()+'</span></div>');
		}
		$("#batchSetPriceContainer").html(batchSetPriceContainerHtml.join(""));

		var batchSetStockContainerHtml=[];
		batchSetStockContainerHtml.push('<div class="ad-box"><input onclick="selectBatchSetStock(this);" name="batchSetStockCheckbox" type="checkbox" value="0"><span>全部</span></div>');
		for(var i=0;i<productPropIdArry.length;i++){
			batchSetStockContainerHtml.push('<div class="ad-box"><input onclick="selectBatchSetStock(this);" name="batchSetStockCheckbox" type="checkbox" value="'+productPropIdArry[i]+'"><span>同'+$("#productProp").find("option[value="+productPropIdArry[i]+"]").text()+'</span></div>');
		}
		$("#batchSetStockContainer").html(batchSetStockContainerHtml.join(""));

		var batchSetSkuContainerHtml=[];
		batchSetSkuContainerHtml.push('<div class="ad-box"><input onclick="selectBatchSetSku(this);" name="batchSetSkuCheckbox" type="checkbox" value="0"><span>全部</span></div>');
		for(var i=0;i<productPropIdArry.length;i++){
			batchSetSkuContainerHtml.push('<div class="ad-box"><input onclick="selectBatchSetSku(this);" name="batchSetSkuCheckbox" type="checkbox" value="'+productPropIdArry[i]+'"><span>同'+$("#productProp").find("option[value="+productPropIdArry[i]+"]").text()+'</span></div>');
		}
		$("#batchSetSkuContainer").html(batchSetSkuContainerHtml.join(""));

		var batchSetSkuPicContainerHtml=[];
		batchSetSkuPicContainerHtml.push('<div class="ad-box"><input onclick="selectBatchSetSkuPic(this);" name="batchSetSkuPicCheckbox" type="checkbox" value="0"><span>全部</span></div>');
		for(var i=0;i<productPropIdArry.length;i++){
			batchSetSkuPicContainerHtml.push('<div class="ad-box"><input onclick="selectBatchSetSkuPic(this);" name="batchSetSkuPicCheckbox" type="checkbox" value="'+productPropIdArry[i]+'"><span>同'+$("#productProp").find("option[value="+productPropIdArry[i]+"]").text()+'</span></div>');
		}
		$("#batchSetSkuPicContainer").html(batchSetSkuPicContainerHtml.join(""));

		$("#batchSetModal").modal();
	}

	function selectBatchSetPrice(element){
		var check = $(element).prop("checked");
		if(check){
			$("input[type=checkbox][name=batchSetPriceCheckbox]").prop("checked", false);
			$(element).prop("checked", true);
		}
	}
	function selectBatchSetStock(element){
		var check = $(element).prop("checked");
		if(check){
			$("input[type=checkbox][name=batchSetStockCheckbox]").prop("checked", false);
			$(element).prop("checked", true);
		}
	}
	function selectBatchSetSku(element){
		var check = $(element).prop("checked");
		if(check){
			$("input[type=checkbox][name=batchSetSkuCheckbox]").prop("checked", false);
			$(element).prop("checked", true);
		}
	}
	function selectBatchSetSkuPic(element){
		var check = $(element).prop("checked");
		if(check){
			$("input[type=checkbox][name=batchSetSkuPicCheckbox]").prop("checked", false);
			$(element).prop("checked", true);
		}
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
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				productItemData[i].stock=$(elemt).val();
				break;
			}
		}
	}
	function editProductItemSelfOperatedFreight(dataId,elemt){
		for(var i=0;i<productItemData.length;i++){
			if(productItemData[i].DT_RowId==dataId){
				productItemData[i].selfOperatedFreight=$(elemt).val();
				break;
			}
		}
	}

	//编辑单规格商品表格数据

	function editSingleProductItemTagPrice(dataId,elemt){
		singleProductItemData[0].tagPrice=$(elemt).val();
	}

	function editSingleProductItemSalePrice(dataId,elemt){
		singleProductItemData[0].salePrice=$(elemt).val();
	}
	function editSingleProductItemMallPrice(dataId,elemt){
		singleProductItemData[0].mallPrice=$(elemt).val();
	}
	function editSingleProductItemCostPrice(dataId,elemt){
		singleProductItemData[0].costPrice=$(elemt).val();
	}
	function editSingleProductItemSku(dataId,elemt){
		singleProductItemData[0].sku=$(elemt).val();
	}
	function editSingleProductItemStock(dataId,elemt){
		singleProductItemData[0].stock=$(elemt).val();
	}
	function editSingleProductItemSelfOperatedFreight(dataId,elemt){
		singleProductItemData[0].selfOperatedFreight=$(elemt).val();
	}

	var productItemData=[];
	var singleProductItemData=[{
		"skuPic":"",
		"skuPicName":"",
		"tagPrice":"",
		"mallPrice":"",
		"salePrice":"",
		"stock":"",
		"sku":"",
		"checked":true,
		"DT_RowId":"singleProductItem",
		"0":"均码",
		"isSkuPicUploaded":"0",
		"skuPicBase64":"",
		"costPrice":"",
		"selfOperatedFreight":""
	}];

	function initProductItemTable(){
		productItemDatatable = $('#product-item-datatable').dataTable({
			"data":productItemData,
			"language": {"url": '${ctx}/static/cn.json'},
			"autoWidth": true,   // 禁用自动调整列宽
			"stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
			"order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
			"searching": false,   // 禁用原生搜索
			"bSort": false,
			"bPaginate" : false,
			"columns": productItemDatatableColumns
		}).api();
	}

	function initSinglePropProductItemTable(){

		var singleProductItemDatatableColumns=[];
		singleProductItemDatatableColumns.push({"title": "SKU图", "width":"100", "data": "skuPic","render": function (data, type, row, meta) {
			if(row.skuPicBase64!=""){
				return '<div class="sku_pic_picker" id="sku_pic_'+row.DT_RowId+'"><input onchange="loadImageFile(this,'+"'"+row.DT_RowId+"'"+')" type="file" style="width:100px;"><img src="'+row.skuPicBase64+'"><input id="skuPicName_'+row.DT_RowId+'"  type="hidden" validate="{required:true}" value="'+row.skuPicName+'"></div>';
			}else{
			if(data!=""){
				return '<div class="sku_pic_picker" id="sku_pic_'+row.DT_RowId+'"><input onchange="loadImageFile(this,'+"'"+row.DT_RowId+"'"+')" type="file" style="width:100px;"><img src="'+data+'"><input id="skuPicName_'+row.DT_RowId+'"  type="hidden" validate="{required:true}" value="'+row.skuPicName+'"></div>';
			}else{
				return '<div class="sku_pic_picker" id="sku_pic_'+row.DT_RowId+'"><input onchange="loadImageFile(this,'+"'"+row.DT_RowId+"'"+')" type="file" style="width:100px;"><div>+</div><input id="skuPicName_'+row.DT_RowId+'"  type="hidden" validate="{required:true}" value="'+row.skuPicName+'"></div>';
			}
			}
		}});
		singleProductItemDatatableColumns.push({"title": "吊牌价", "width": "120", "data": "tagPrice","render": function (data, type, row, meta) {
			return '<input class="ad-select" onkeyup="validatePrice(this);" id="tagPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01}"  type="text" value="'+data+'"  onblur="editSingleProductItemTagPrice('+"'"+row.DT_RowId+"'"+',this);">';
		 }});

		if('${sessionScope.mchtInfo.shopStatus}' == '1'){
			singleProductItemDatatableColumns.push({"title": "商城价", "width": "120", "data": "mallPrice","render": function (data, type, row, meta) {
				return '<input class="ad-select" id="mallPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,mallTagPrice:true}"  type="text" value="'+data+'"  onblur="editSingleProductItemMallPrice('+"'"+row.DT_RowId+"'"+',this);">';
			}});
		};

		singleProductItemDatatableColumns.push({"title": "活动价", "width": "120", "data": "salePrice","render": function (data, type, row, meta) {
			return '<input class="ad-select" id="salePrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,salePrice:true,mallPrice:true,salePriceDiscount:true}"  type="text" value="'+data+'"  onblur="editSingleProductItemSalePrice('+"'"+row.DT_RowId+"'"+',this);">';
		}});
		if('${sessionScope.mchtInfo.mchtType}' == '1'){
			singleProductItemDatatableColumns.push({"title": "结算价", "width": "120", "data": "costPrice","render": function (data, type, row, meta) {
				return '<input class="ad-select costPrice" id="costPrice_'+row.DT_RowId+'" validate="{required:true,number:true,min:0.01,costPrice:true,costPriceRate:true}"  type="text" value="'+data+'"  onblur="editSingleProductItemCostPrice('+"'"+row.DT_RowId+"'"+',this);">';
			}});
		};
		if(${sessionScope.mchtInfo.mchtType eq '1' and sessionScope.mchtInfo.isManageSelf eq '1'}){
			singleProductItemDatatableColumns.push({"title": "自营运费", "width": "130", "data": "selfOperatedFreight","render": function (data, type, row, meta) {
					return '<input class="ad-select selfOperatedFreight" id="selfOperatedFreight_'+row.DT_RowId+'" type="text" value="'+data+'" validate="{number:true}" onblur="editSingleProductItemSelfOperatedFreight('+"'"+row.DT_RowId+"'"+',this);">';
				}});
		};
		singleProductItemDatatableColumns.push({"title": "库存", "width": "120", "data": "stock","render": function (data, type, row, meta) {
			return '<input class="ad-select" id="stock_'+row.DT_RowId+'" validate="{required:true,digits:true,min:0}"   type="text" value="'+data+'"  onblur="editSingleProductItemStock('+"'"+row.DT_RowId+"'"+',this);">';
		}});
		singleProductItemDatatableColumns.push({"title": "SKU商家编码", "width": "160", "data": "sku","render": function (data, type, row, meta) {
			return '<input class="ad-select" id="sku_'+row.DT_RowId+'"    type="text" value="'+data+'"   onblur="editSingleProductItemSku('+"'"+row.DT_RowId+"'"+',this);">';
		}});

		singleProductItemDatatable = $('#single-product-item-datatable').dataTable({
			"data":singleProductItemData,
			"language": {"url": '${ctx}/static/cn.json'},
			"autoWidth": true,   // 禁用自动调整列宽
			"stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
			"order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
			"searching": false,   // 禁用原生搜索
			"bSort": false,
			"bPaginate" : false,
			"columns": singleProductItemDatatableColumns
		}).api();
	}

	//数组去重
	Array.prototype.unique2 = function(){
		var res = [this[0]];
	 	for(var i = 1; i < this.length; i++){
			var repeat = false;
		  	for(var j = 0; j < res.length; j++){
		   		if(this[i] == res[j]){
		    		repeat = true;
		    		break;
		   		}
		  	}
		  	if(!repeat){
		   		res.push(this[i]);
		  	}
	 	}
	 	return res;
	};

	//重新创建商品sku
	function createProductItems(){
		//组织数据
		productItemData=[];
		if(productPropIdArry.length==0){
			return;
		}
		var res=[];
	 	cb(res,[],productPropValuesSelectedArry,0);
		var timestamp = new Date().getTime();
		for(var n=0;n<res.length;n++){
			var itemData={
				"skuPic":"",
				"skuPicName":"",
				"tagPrice":"",
				"mallPrice":"",
				"salePrice":"",
				"stock":"",
				"sku":"",
				"checked":true,
				"isSkuPicUploaded":"0",
				"skuPicBase64":"",
				"costPrice":"",
				"sourceSkuPicItemId":"",
				"isSourceSkuPicItem":"0",
				"selfOperatedFreight":""
			};
			var d=res[n];
			for(var k=0;k<d.length;k++){
				itemData[productPropIdArry[k]]=d[k];
			}
			itemData["DT_RowId"]="newItemData_"+timestamp+"_"+n;
			productItemData.push(itemData);
		}
	}


	//添加创建商品sku
	function addProductItems(productPropId,productPropValues2Add){
		//组织数据
		if(productPropIdArry.length==0){
			return;
		}
		var productPropValuesAddArry=[];
		for(var k=0;k<productPropValuesSelectedArry.length;k++){
			productPropValuesAddArry[k]=productPropValuesSelectedArry[k];
		}

		for(var k=0;k<productPropIdArry.length;k++){
			if(productPropIdArry[k]==productPropId){
				productPropValuesAddArry[k]=productPropValues2Add;
			}
		}

		var res =[];
		cb(res,[],productPropValuesAddArry,0);
		var timestamp = new Date().getTime();
		for(var n=0;n<res.length;n++){
			var itemData={
				"skuPic":"",
				"skuPicName":"",
				"tagPrice":"",
				"mallPrice":"",
				"salePrice":"",
				"stock":"",
				"sku":"",
				"productItemId":"",
				"checked":true,
				"isSkuPicUploaded":"0",
				"skuPicBase64":"",
				"costPrice":"",
				"sourceSkuPicItemId":"",
				"isSourceSkuPicItem":"0"
			};
			var d=res[n];
			for(var k=0;k<d.length;k++){
				itemData[productPropIdArry[k]]=d[k];
			}
			itemData["DT_RowId"]="newItemData_"+timestamp+"_"+n;
			productItemData.push(itemData);
		}
	}

	function cb(result,item,arr,index){

		if(arr.length==index){
			return;
		}
		for (var i = 0; i < arr[index].length; i++) {
			if((arr.length-1)==index){
				result.push(item.concat(arr[index][i]));
			}
			cb(result,item.concat(arr[index][i]),arr,index+1);
		}
	}

	function combine(arr) {
        var r = [];
        (function f(t, a, n) {
            if (n == 0) return r.push(t);
            for (var i = 0; i < a[n-1].length; i++) {
                f(t.concat(a[n-1][i]), a, n - 1);
            }
        })([], arr, arr.length);
        return r;
    }


	function addProp(){

		var productPropId=$("#productProp").val();

		if($.trim(productPropId)==""){
			swal("请选择规格名");
			return;
		}

		var productTypeName=$("#productType1 option:selected").text();

		if($.trim(productPropId)!="1"&&$("#prop_container_1").length<=0&&productTypeName!="生活"){
			swal("对不起，请先添加颜色分类");
			return;
		}

		if($.trim($("#propValue").val())==""){
			swal("请输入规格值");
			return;
		}

		var hasViolateWord=false;
		//校验违禁词
		$.ajax({
			url : "${ctx}/common/checkViolateWord",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {word:$.trim($("#propValue").val())},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="4004") {
					hasViolateWord=true;
					swal(data.returnMsg);
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

		if(hasViolateWord){
			return;
		}

		var productPropValues=$("#propValue").val().toUpperCase().split('\n');
		productPropValues=productPropValues.unique2();//去重

		//限制每一行字数
		var wordTag = true;
		for (var i=0;i<productPropValues.length;i++){
			if($.trim(productPropValues[i])!="" && $.trim(productPropValues[i]).length>64){
				wordTag = false;
				break;
			}
		}
		if(!wordTag){
			swal("每个规格值长度不可超过64");
			return;
		}

		if($("#prop_container_"+productPropId).length<=0){//规格还未添加过

			productPropIdArry.push(productPropId);
			//去除空串
			var productPropValuesTmp=[];
			for (var i=0;i<productPropValues.length;i++){
				if($.trim(productPropValues[i])!=""){
					productPropValuesTmp.push($.trim(productPropValues[i]));
				}
			}
			productPropValues=productPropValuesTmp;

			productPropValuesArry.push(productPropValues);
			productPropValuesSelectedArry.push(productPropValues);

			$("#prop_container").append("<div id='prop_container_"+productPropId+"' class='prop-container'></div>");
			$("#prop_container_"+productPropId).append(
		  		"<div class='col-md-12'><span class='prop-name'>"+$("#productProp").find("option:selected").text()+"</span><span id='prop_remove_icon_"+productPropId+"' onclick='removeProp(this)' class='glyphicon glyphicon-remove' aria-hidden='true'></span></div>"
			);
			$("#prop_container_"+productPropId).append(
				"<div class='row' style='margin: 0;' id='prop_value_container_"+productPropId+"'></div>"
			);

			for (var i=0;i<productPropValues.length;i++){
				if($.trim(productPropValues[i])!=""){
					$("#prop_value_container_"+productPropId).append(
						"<div class='ad-box'><input type='checkbox' onclick='selectPropValue(this);' checked='true'  name='checkbox_"+productPropId+"'  value='"+$.trim(productPropValues[i])+"'><span class='prop-value-"+productPropId+"'>"+productPropValues[i]+"</span></div>"
					);
				}
			}
			createProductItemDatatableColumns();
			createProductItems();
			productItemDatatable.destroy();
			$('#product-item-datatable').empty();
			initProductItemTable();

		}else{
			var existValues=[];
			$(".prop-value-"+productPropId).each(function(){
				existValues.push($(this).text());
			});

			var productPropValues2Add=[];

			for (var i=0;i<productPropValues.length;i++){
				if($.trim(productPropValues[i])!=""){
					var hasExist=false;
					for(var j=0;j<existValues.length;j++){
						if($.trim(productPropValues[i])==$.trim(existValues[j])){
							hasExist=true;
						}
					}
					if(!hasExist){//去重，如果已经存在的就不在添加
						$("#prop_value_container_"+productPropId).append(
							"<div class='ad-box'><input type='checkbox' checked='true' onclick='selectPropValue(this);'  name='checkbox_"+productPropId+"'  value='"+$.trim(productPropValues[i])+"'><span class='prop-value-"+productPropId+"'>"+$.trim(productPropValues[i])+"</span></div>"
						);
						productPropValues2Add.push($.trim(productPropValues[i]));//用于添加sku数据
						productPropValuesSelectedArry[$.inArray(productPropId,productPropIdArry)].push($.trim(productPropValues[i]));
					}
				}
			}
			addProductItems(productPropId,productPropValues2Add);
			productItemDatatable.destroy();
			$('#product-item-datatable').empty();
			initProductItemTable();
		}

		$("#propValue").val("");

	}

	function selectPropValue(elment){
		var propId=$(elment).attr("name").substring(9);
		var propValue=$(elment).attr("value");

		var productItemDataTmp=[];
		var productPropValues2Add=[];
		var temArry=[];
		if($(elment).prop('checked')){//选中时添加商品sku数据
			productPropValues2Add.push(propValue);//用于添加sku数据
			productPropValuesSelectedArry[$.inArray(propId,productPropIdArry)].push(propValue);
			addProductItems(propId,productPropValues2Add);
		}else{//取消选中，删除商品sku数据
			var sourceSkuPicItemDatas=[];
			for(var i=0;i<productItemData.length;i++){
				if(productItemData[i][propId]!=propValue){
					productItemDataTmp.push(productItemData[i]);
				}else{
					temArry=productPropValuesSelectedArry[$.inArray(propId,productPropIdArry)];
					for(var j=0;j<temArry.length;j++){
						if(temArry[j]==propValue){
							temArry.splice(j,1);
						break;
						}
					}

					//如果是图片源的sku，则要重新设置新的图片源sku
					if(productItemData[i].isSourceSkuPicItem=="1"){
						sourceSkuPicItemDatas.push(productItemData[i]);
					}

				}
			}
			productItemData=productItemDataTmp;

			//重新设置新的图片源sku
			if(sourceSkuPicItemDatas.length>0){
				for (var k = 0; k < sourceSkuPicItemDatas.length; k++) {
					var newSourceSkuPicImteId="";
					for (var m = 0; m < productItemData.length; m++) {
						if(productItemData[m].sourceSkuPicItemId==sourceSkuPicItemDatas[k].DT_RowId){
							if(newSourceSkuPicImteId==""){
								newSourceSkuPicImteId=productItemData[m].DT_RowId;
								productItemData[m].isSourceSkuPicItem="1";
								productItemData[m].sourceSkuPicItemId=productItemData[m].DT_RowId;

							}
							productItemData[m].sourceSkuPicItemId=newSourceSkuPicImteId;
						}
					}
				}
			}

		}
		if(productPropValuesSelectedArry[$.inArray(propId,productPropIdArry)].length==0){
			$("#prop_remove_icon_"+propId).click();
		}else{
			productItemDatatable.destroy();
			$('#product-item-datatable').empty();
			initProductItemTable();
		}

	}


	function removeProp(element){

		$(element).parent().parent().remove();
		productPropValuesArry.splice($.inArray($(element).parent().parent().attr("id").substr(15),productPropIdArry),1);
		productPropValuesSelectedArry.splice($.inArray($(element).parent().parent().attr("id").substr(15),productPropIdArry),1);
		productPropIdArry.splice($.inArray($(element).parent().parent().attr("id").substr(15),productPropIdArry),1);
		createProductItemDatatableColumns();
		createProductItems();
		productItemDatatable.destroy();
		$('#product-item-datatable').empty();
		initProductItemTable();
	}

	function getProductTypeList(selectId,parentId){
		var sel = $("#"+selectId);
		if(parentId==""){
			sel.empty();
			return;
		}

		var productBrandId = $("#brandId").val();
		$.ajax({
			url : "${ctx}/productType/getMchtProductTypes",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {parentId:parentId,productBrandId:productBrandId},
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					sel.empty();
					$.each(data.returnData, function(index, item) {
						sel.append("<option return7days='"+(item.isReturn7days==null?"":item.isReturn7days)+"' value=\"" + item.id + "\">" + item.name + "</option>");
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
				  title: "请求失败！",
				  type: "error",
				  confirmButtonText: "确定"
				});
			}
		});

	}


	function commitSave(){
		var mchtInfoStatus = $("#mchtInfoStatus").val();
		if(mchtInfoStatus == "2"){
			swal("对不起，合作状态暂停，不能添加商品");
			return false;
		}
		dataFromValidate =$("#dataFrom").validate({
			errorPlacement: function(error, element) {
				error.appendTo($(element).closest('td'));
			}
		});

		if(dataFromValidate.form()){
			var propType=$("input[type=radio][name='isSingleProp']:checked").val();
			if("0"==propType&&productItemData.length==0){
				swal({
				  title: '请输入商品规格',
				  type: "error",
				  confirmButtonText: "确定"
				});
				return;
			}

			var productTypeName=$("#productType1 option:selected").text();

			if("0"==propType&&$("#prop_container_1").length<=0&&productTypeName!="生活"){//颜色分类为必填
				swal("对不起，请先添加颜色分类");
				return;
			}
			if("1"==propType&&singleProductItemData.length==0){
				swal({
				  title: '请输入商品规格',
				  type: "error",
				  confirmButtonText: "确定"
				});
				return;
			}
			show_waitMe();
			uploader.upload();
		}else{
			swal("您输入的信息存在错误，请检查！");
			return;
		}
	}

	function commit2Server(){

		productMainPics=[];
		productDescPics=[];
		$("#main-pictures-list").children("li").each(function(index,element){
			var pic={};
			if($(element).hasClass("is-defaul-pic")){
				pic.isDefault='1';
			}else{
				pic.isDefault='0';
			}
			pic.pic=$(element).attr("pic_path");
			pic.seqNo=index;
			productMainPics.push(pic);
		});

		if(productMainPics.length==0){
			hide_waitMe();
			swal({
		  		title: '请上传商品主图',
			  	type: "error",
			  	confirmButtonText: "确定"
			});
			return;
		}

		if(productMainPics.length<3||productMainPics.length>6){
			hide_waitMe();
			swal({
			  	title: '商品主图只能3-6张',
			  	type: "error",
			  	confirmButtonText: "确定"
			});
			return;
		}


		$("#desc-pictures-list").children("li").each(function(index,element){
			var pic={};
			pic.pic=$(element).attr("pic_path");
			pic.seqNo=index;
            productDescPics.push(pic);
		});

		if(productDescPics.length==0){
			hide_waitMe();
			swal({
			  	title: '请上传详情图',
			  	type: "error",
			  	confirmButtonText: "确定"
			});
			return;
		}

		$("#foodLabel-pictures-list").children("li").each(function(index,element){
			foodLabelPics = $(element).attr("pic_path");
		});

		if(tag != 0){
            if(foodLabelPics == "" || foodLabelPics == null || foodLabelPics == "null"){
                hide_waitMe();
                swal({
                    title: '请上传食品标签图',
                    type: "error",
                    confirmButtonText: "确定"
                });
                return;
            }
        }
		var dataJson = $("#dataFrom").serializeArray();
		if(typeof($("input:radio[name='isReturn7days']").attr("disabled"))!="undefined"){
			 dataJson.push({"name":"isReturn7days","value":$("input:radio[name='isReturn7days']:checked").val()});
		}
		dataJson.push({"name":"productMainPics","value":JSON.stringify(productMainPics)});
		dataJson.push({"name":"productDescPics","value":JSON.stringify(productDescPics)});
		dataJson.push({"name":"foodLabelPics","value":foodLabelPics});
		$('#producerInformation').val()
		//判断适合性别
		var suitSex="";
		if($("input[type=checkbox][name=suitSexCheckbox][value=1]").prop('checked')){
			suitSex=suitSex+"1";
		}else{
			suitSex=suitSex+"0";
		}

		if($("input[type=checkbox][name=suitSexCheckbox][value=0]").prop('checked')){
			suitSex=suitSex+"1";
		}else{
			suitSex=suitSex+"0";
		}

		dataJson.push({"name":"suitSex","value":suitSex});

		//判断适合人群
		var suitGroup="";
		if($("input[type=checkbox][name=suitGroupCheckbox][value=1]").prop('checked')){
			suitGroup=suitGroup+"1";
		}else{
			suitGroup=suitGroup+"0";
		}

		if($("input[type=checkbox][name=suitGroupCheckbox][value=2]").prop('checked')){
			suitGroup=suitGroup+"1";
		}else{
			suitGroup=suitGroup+"0";
		}

		if($("input[type=checkbox][name=suitGroupCheckbox][value=3]").prop('checked')){
			suitGroup=suitGroup+"1";
		}else{
			suitGroup=suitGroup+"0";
		}

		dataJson.push({"name":"suitGroup","value":suitGroup});

		//组织属性规格数据
	  	var propType=$("input[type=radio][name='isSingleProp']:checked").val();
	  	var skuDatas;
	   	var propValues=[];
		if(propType=="0"){//多规格
			for(var i=0;i<productPropIdArry.length;i++){
				propValues.push({"propId":productPropIdArry[i],"propValues":productPropValuesSelectedArry[i]});
			}
			skuDatas= jQuery.extend(true, [], productItemData);
		}else{
			propValues.push({"propId":0,"propValues":"均码"});
			skuDatas = jQuery.extend(true, [], singleProductItemData);
		}

		for(var i=0;i<skuDatas.length;i++){
			skuDatas[i].skuPicBase64="";
		}
		dataJson.push({"name":"propValueList","value":JSON.stringify(propValues)});
		dataJson.push({"name":"productItems","value":JSON.stringify(skuDatas)});

		if($.trim($("#productDesc").val())!=""){
		dataJson.push({"name":"productDesc","value":$.trim($("#productDesc").val()).replace(/\n/g,"&&&")});
		}
		$.ajax({
			url : "${ctx}/product/productAddCommit",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					swal({
					  title: "恭喜您，商品添加成功!",
					  type: "success",
					  confirmButtonText: "查看商品列表",
					  cancelButtonText:"继续添加商品",
					  showCancelButton: true,
					},function(isConfirm){
						if(isConfirm==true){
							if($("#shopProduct").val()=="1"){
								getContent("${ctx}/shopProduct/productIndex");
							}else{
								if($("#saleType").val()=="1"){
								 getContent("${ctx}/product/productIndex?saleType=1");
								}else{
								 getContent("${ctx}/product/productIndex?saleType=2");
								}
							}
						}else{
							getContent("${ctx}/product/productAdd?saleType="+$("#saleType").val()+"&shopProduct="+$("#shopProduct").val());
						}
					});
				} else {
					swal({
						  title: data.returnMsg,
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
				hide_waitMe();
			},
			error : function() {
				hide_waitMe();
				swal({
				  	title: "提交失败！",
				  	type: "error",
				  	confirmButtonText: "确定"
				});
			}
		});
	}


	var dataFromValidate;
	var productPicturesViewer;
	var productDescPictruresViewer;
	var uploader;
	var descUploader;

	var multiplePropContainerHtml;
	var singlePropContainerHtml;
	var descPicsArray=[];

$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
	multiplePropContainerHtml=$("#product-item-table-container").html();
	singlePropContainerHtml=$("#single-product-item-table-container").html();
	$("#single_prop_container").hide();


	$('#productType2').change(function(){
		if(${isShop eq true}){
			var select = $(this).val();
			var name1 = '${sysParamCfg1.paramValue}';
			var name2 = '${sysParamCfg2.paramValue}';
			name1 = name1.split(",");
			name2 = name2.split(",");
            $('#approvalNumberTr').show();
            $('#approvalNumber').attr("disabled", "");
            $('#approvalNumber').attr("validate", '{required:true,rangelength:[0,20]}');
            $('#producerInformationTr').show();
            $('#producerInformation').attr("disabled", "");
            $('#producerInformation').attr("validate", '{required:true,rangelength:[0,200]}');
            $('#foodLabelPicTr').show();
            if(name1.indexOf(select) != -1) {
                $('#approvalNumberTr').hide();
                $('#approvalNumber').attr("disabled", true);
                $('#approvalNumber').attr("validate", '');
                $('#producerInformationTr').hide();
                $('#producerInformation').attr("disabled", true);
                $('#producerInformation').attr("validate", '');
                $('#foodLabelPicTr').hide();
                tag = 0;
            }else if(name2.indexOf(select) != -1){
                $('#approvalNumberTr').show();
                $('#approvalNumber').attr("disabled", false);
                $('#producerInformationTr').show();
                $('#producerInformation').attr("disabled", false);
                $('#foodLabelPicTr').show();
                tag = 1;
            } else{
                $('#approvalNumberTr').hide();
                $('#approvalNumber').attr("disabled", true);
                $('#producerInformationTr').show();
                $('#producerInformation').attr("disabled", false);
                $('#foodLabelPicTr').show();
                tag = 2;
            }
        }
	})

	uploader= WebUploader.create({

        dnd: '#main-pic-container',
        paste: 'document.body',
        // swf文件路径
        swf: '${ctx}/static/images/webuploader/Uploader.swf',

        // 文件接收服务端。
        server: '${ctx}/common/fileUpload?fileType=2',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#mainFilePicker',
        duplicate:true,
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
        }

    });

	uploader.addButton({
	    id: '#descFilePicker',
	});
	uploader.addButton({
		id: '#foodLabelFilePicker',
	});


	uploader.on( 'beforeFileQueued', function( file ) {
		var pickerId=file.source._refer.context.id;
		var suffix = file.name.split(".")[0];
	 	if(suffix ==''){
			swal("图片文件名不能为空");
		    return false;
		};

		if(file.type!='image/jpeg'){
			if(pickerId=='descFilePicker'){//主图的图片列表
				$("#descPicErrorMsg").text("请上传jpg，jpeg格式的图片");
			    return false;
	    	}
			if(pickerId=='mainFilePicker'){//主图的图片列表
				$("#mainPicErrorMsg").text("请上传jpg，jpeg格式的图片");
			    return false;
	    	}
			if(pickerId=='foodLabelFilePicker'){//食品标签的图片
				$("#foodLabelPicErrorMsg").text("请上传jpg，jpeg格式的图片");
				return false;
			}
		}

			if(pickerId=='descFilePicker'){//详情图的图片列表
				if(getTotalDescImageSize()+file.size>10*1024*1024){
					$("#descPicErrorMsg").text("上传的图片总大小已超过10M");
					return false;
				}

	    	}
			if(pickerId=='descFilePicker'&&file.size>400*1024){//图片描述
				$("#descPicErrorMsg").text("您选择了包含大于400K的图片！");
				return false;
			}
			if(pickerId=='mainFilePicker'&&file.size>400*1024){//主图的图片列表
				$("#mainPicErrorMsg").text("您选择了包含大于400K的图片！");
			    return false;
	    	}
			if(pickerId=='foodLabelFilePicker'&&file.size>400*1024){//食品标签的图片
				$("#foodLabelPicErrorMsg").text("您选择了包含大于400K的图片！");
				return false;
			}
	});
	uploader.on( 'fileDequeued', function( file ) {
		var pickerId=file.source._refer.context.id;
			if(pickerId=='descFilePicker'){//详情图的图片列表
				if(getTotalDescImageSize()<=10*1024*1024){
					var errorMsg = $("#descPicErrorMsg").text();
					if(errorMsg.indexOf("上传的图片总大小已超过") >= 0){
						$("#descPicErrorMsg").text("");
					}
				}

	    	}
	});


    uploader.onFileQueued = function( file ) {
    	var pickerId=file.source._refer.context.id;
    	if(pickerId=='mainFilePicker'){//主图的图片列表
			 var oFReader = new FileReader();
			 oFReader.onload = function(oFREvent) {
				 var img=new Image();
				 img.onload=function(){
						 if($("#main-pictures-list").children("li").length>=6){
							 $("#mainPicErrorMsg").text("主图数量只能3-6张");
							 uploader.removeFile( file,true );
							 return;
						 }
						if(img.width!=800||img.height!=800){
							uploader.removeFile( file,true );
							$("#mainPicErrorMsg").text("您选择了包含尺寸不为800*800的图片！");
							return;
						}else{
							var $li = $('<li class="pic_li" id="pic_li_'+file.id+'" draggable="true" ondragstart="drag(event)"><img id="pic_'+file.id+'" src="'+oFREvent.target.result+'"></li>');
							var $btns = $('<div class="pic-btn-container"></div>').appendTo( $li );
							var $setDefaultBtn=$('<span class="glyphicon glyphicon-cog set-default-btn"></span>').appendTo($btns);
							var $removeBtn=$('<span  class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>').appendTo($btns);
							$('#main-pictures-list').append( $li );

							$li.on( 'mouseenter', function() {
								$btns.stop().animate({height: 20});
							});

							$li.on( 'mouseleave', function() {
								$btns.stop().animate({height: 0});
							});

							$removeBtn.on( 'click', function() {
										$li.remove();
										uploader.removeFile( file ,true);
								}
							);

							$setDefaultBtn.on( 'click', function() {
								$(".pic_li").each(function(index,element){
									$(element).children(".main-pic-mark").remove();
									$(element).removeClass("is-defaul-pic");
								});
								var $mainPicMark=$('<div class="main-pic-mark">主图</div>').appendTo($li);
								$li.addClass("is-defaul-pic");
								}
							);
						}

					};
				 img.src=oFREvent.target.result
			};
			oFReader.readAsDataURL(file.source.source);
	    }


    	if(pickerId=='descFilePicker'){//详情图的图片列表
	    	 var oFReader = new FileReader();
			 oFReader.onload = function(oFREvent) {
				 var img=new Image();
				 img.onload=function(){
					 if(img.width<640||img.width>800){
						 uploader.removeFile( file,true );
				    		$("#descPicErrorMsg").text("您选择了包含宽度不为640-800像素的图片!");
				    		return;
					 }else{
							var descPic={'fileId':file.id,'fileName':file.name};
							var returnObject=addDescPic2Array(descPic);

							var $li = $('<li class="pic_li" id="pic_li_'+file.id+'" draggable="true" ondragstart="drag(event)"><img  id="pic_'+file.id+'" src="'+oFREvent.target.result+'"></li>');
							var $btns = $('<div class="pic-btn-container"></div>').appendTo( $li );
							var $removeBtn=$('<span  class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>').appendTo($btns);

							if(returnObject.position=='-1'){
								$('#desc-pictures-list').append( $li );
							}else{
								if($('#pic_li_'+returnObject.fileId).length>0){
									if(returnObject.position=='0'){//position 为0在元素前面插入
										$('#pic_li_'+returnObject.fileId).before( $li );
									}else{//position 不为0 在元素后面插入
										$('#pic_li_'+returnObject.fileId).after( $li );
									}
								}else{
									$('#desc-pictures-list').append( $li );
								}
							}

							$li.on( 'mouseenter', function() {
								$btns.stop().animate({height: 20});
							});

							$li.on( 'mouseleave', function() {
								$btns.stop().animate({height: 0});
							});

							$removeBtn.on( 'click', function() {
										$li.remove();
										delDescPic2Array(descPic);
										uploader.removeFile( file ,true);
								}
							);
					 }
				 }

				 img.src=oFREvent.target.result
			};
			oFReader.readAsDataURL(file.source.source);
      }

		if(pickerId=='foodLabelFilePicker'){//食品标签的图片
			var oFReader = new FileReader();
			oFReader.onload = function(oFREvent) {
				var img=new Image();
				img.onload=function(){
					if($("#foodLabel-pictures-list").children("li").length>=1){
						$("#foodLabelPicErrorMsg ").text("主图数量只能1张");
						uploader.removeFile( file,true );
						return;
					}
					if(img.width!=640||img.height!=800){
						uploader.removeFile( file,true );
						$("#foodLabelPicErrorMsg ").text("您选择了包含尺寸不为640*800的图片！");
						return;
					}else{
						var $li = $('<li class="pic_li" id="pic_li_'+file.id+'" draggable="true" ondragstart="drag(event)"><img id="pic_'+file.id+'" src="'+oFREvent.target.result+'"></li>');
						var $btns = $('<div class="pic-btn-container"></div>').appendTo( $li );
						var $removeBtn=$('<span  class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>').appendTo($btns);
						$('#foodLabel-pictures-list').append( $li );
						$li.on( 'mouseenter', function() {
							$btns.stop().animate({height: 20});
						});

						$li.on( 'mouseleave', function() {
							$btns.stop().animate({height: 0});
						});

						$removeBtn.on( 'click', function() {
									$li.remove();
									uploader.removeFile( file ,true);
								}
						);
					}

				};
				img.src=oFREvent.target.result
			};
			oFReader.readAsDataURL(file.source.source);
		}
    };

    uploader.on( 'uploadSuccess', function( file,response ) {
        if(response.returnCode=='0000'){
        	$("#pic_li_"+file.id).attr("pic_path",response.returnData);
        }else{
			uploader.removeFile( file,true );
			$("#pic_li_"+file.id).remove();
		}
    });

	uploader.on( 'uploadError', function( file,response ) {
		uploader.removeFile( file,true );
		$("#pic_li_"+file.id).remove();
		console.log("uploadError-------"+file.name);
		console.log(response);
	});

	uploader.on('error', function(type){
		console.log("error-------"+type);
	});

    //当所有文件上传结束时触发
    uploader.on("uploadFinished",function(){
    	console.log("所有文件上传结束--------");
    	uploadSkuImage();
    });


    //初始化多规格表格
	createProductItemDatatableColumns();
	initProductItemTable();

	$("#brandId").select2({
	  language: "zh-CN",
	  placeholder: "--请选择--",
	  allowClear: true
	});


	$.metadata.setType("attr", "validate");

	<c:if test="${mchtInfoStatus eq '2'}">
		swal("对不起，合作状态暂停，不能添加商品");
		return false;
	</c:if>

});




	function getTotalDescImageSize() {
		var totalSize = 0;
		var files = uploader.getFiles();

		for (var i = 0; i < files.length; i++) {
			if (files[i].source._refer) {
				var pickerId = files[i].source._refer.context.id;
				if (pickerId == 'descFilePicker'
						&& files[i].getStatus() != 'invalid'
						&& files[i].getStatus() != 'cancelled') {
					totalSize = totalSize + files[i].size;
				}
			}
		}
		return totalSize;

	}

	/**
	 * 详情图添加都数组里面，主要用于图片排序,返回position为0时在fileId元素前插入 ，1在元素后面插入，-1 在最后插入
	 */
	function addDescPic2Array(descPic) {

		var targetPic;
		if (descPicsArray.length > 0) {
			for (var i = 0; i < descPicsArray.length; i++) {
				targetPic = descPicsArray[i];
				var compare = strCompare(descPic.fileName, targetPic.fileName);
				if (compare <= 0) {
					descPicsArray.splice(i, 0, descPic);
					return {
						'position' : '0',
						'fileId' : targetPic.fileId
					};
				}

				if (compare > 0 && i + 1 == descPicsArray.length) {
					descPicsArray.splice(i + 1, 0, descPic);
					return {
						'position' : '1',
						'fileId' : targetPic.fileId
					};
				}

			}
		} else {
			descPicsArray.push(descPic);
			return {
				'position' : '-1'
			};
		}

	}

	function delDescPic2Array(descPic) {
		if (descPicsArray.length > 0) {
			for (var i = 0; i < descPicsArray.length; i++) {
				if (descPicsArray[i].fileId = descPic.fileId) {
					descPicsArray.splice(i, 1);
					return;
				}

			}
		}

	}

	function drag(ev) {
		ev.dataTransfer.setData("Text", $(ev.target).parent().attr("id"));
	}
	function drop(ev) {
		ev.preventDefault();
		var data = ev.dataTransfer.getData("Text");
		if ($(ev.target).prop("tagName") == "UL") {
			$(ev.target).append(document.getElementById(data));
		}
		if ($(ev.target).prop("tagName") == "IMG") {
			$(ev.target).parent().before(document.getElementById(data));
		}

	}

	function allowDrop(ev) {
		ev.preventDefault();
	}

	function getProductAfterTempleList(brandId) {
		var path = '${pageContext.request.contextPath}/productAfterTemplet/getProductAfterTemplets?brandId='+ brandId;
		jQuery.ajax({
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
				$.each(
					json,
					function(index, item) {
						sel.append("<option  recipient=\""+item.recipient+"\"  tel=\""+item.phone+"\" address=\""+item.areaName+item.address+"\" value=\"" + item.id + "\">"
									+ item.name+ "</option>");
				});

			},
			error : function() {
				swal('操作超时，请稍后再试！');
			}
		});
	}

	function changeCstemplet() {
		var selectElemt = $("#csTempletId option:selected");
		var csTempletinfo;
		if ($(selectElemt).val() != "") {
			csTempletinfo = "收货人：" + $(selectElemt).attr("recipient")
					+ "&nbsp&nbsp&nbsp&nbsp" + "电话："
					+ $(selectElemt).attr("tel") + "<br>" + "地址："
					+ $(selectElemt).attr("address");
		} else {
			csTempletinfo = "";
		}
		$("#csTempletInfoContainer").html(csTempletinfo);
	}

	function addTpl() {
		var brandId = $("#brandId").val();
		$.ajax({
			url : "${ctx}/product/toProductAfterTempletAdd?brandId=" + brandId,
			type : "GET",
			success : function(data) {
				$("#viewModal1").html(data);
				$("#viewModal1").modal();
			},
			error : function() {
				swal('页面不存在');
			}
		});

	}

	function editFreightTemplate(id){
		$.ajax({
	        url: "${ctx}/freightTemplate/editFreightTemplate?id="+id,
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

	function loadImageFile(obj, dataId) {
		if (obj.files.length === 0) {
			return;
		}
		var oFile = obj.files[0];

		var suffix = oFile.name.split(".")[0];
	 	if(suffix ==''){
			swal("图片文件名不能为空");
		    return false;
		};

		if (oFile.size > 400 * 1024) {
			swal("图片大小不能超过400K");
			return;
		}

		var rFilter = /^(?:image\/jpeg)$/i;
		if (!rFilter.test(oFile.type)) {
			swal("请选择jpg，jpeg格式的图片文件");
			return;
		}
		if ($(obj).parent().children("img").length <= 0) {
			$('<img>').appendTo($(obj).parent());
		}

		var oFReader = new FileReader();
		oFReader.onload = function(oFREvent) {
			var img = new Image();

			img.onload = function() {
				if (img.width != 800 || img.height != 800) {
					swal("请选择尺寸为800*800的图片");
					return;
				} else {
					$(obj).parent().children("img").attr("src",oFREvent.target.result);
					$(obj).parent().children("div").remove();

					var propType = $("input[type=radio][name='isSingleProp']:checked").val();

					if (propType == '0') {
						for (var i = 0; i < productItemData.length; i++) {
							if (productItemData[i].DT_RowId == dataId) {
								productItemData[i].skuPic = oFile;
								productItemData[i].skuPicName = oFile.name;
								productItemData[i].skuPicBase64 = oFREvent.target.result;
								productItemData[i].isSourceSkuPicItem = "1";
								productItemData[i].sourceSkuPicItemId = productItemData[i].DT_RowId;
								//重新设置复制的图片数据源
								var newSourceSkuPicImteId = "";
								for (var m = 0; m < productItemData.length; m++) {
									if (productItemData[m].sourceSkuPicItemId == dataId
											&& productItemData[m].DT_RowId != dataId) {
										if (newSourceSkuPicImteId == "") {
											newSourceSkuPicImteId = productItemData[m].DT_RowId;
											productItemData[m].isSourceSkuPicItem = "1";
										}
										productItemData[m].sourceSkuPicItemId = newSourceSkuPicImteId;
									}
								}
								break;
							}
						}
					} else {
						singleProductItemData[0].skuPic = oFile;
						singleProductItemData[0].skuPicName = oFile.name;
						singleProductItemData[0].skuPicBase64 = oFREvent.target.result;
					}

					$("#skuPicName_" + dataId).val(oFile.name);
					if ($(obj).parent().parent().children("label").length > 0) {
						$(obj).parent().parent().children("label").remove();
					}
				}
			}
			img.src = oFREvent.target.result;
		};
		oFReader.readAsDataURL(oFile);
	}

	function uploadSkuImage() {
		debugger;
		var formData = new FormData();
		var hasNoUploaded = false;
		var itemDatas;
		var propType = $("input[type=radio][name='isSingleProp']:checked").val();
		if (propType == "0") {//多规格
			itemDatas = productItemData;
		} else {
			itemDatas = singleProductItemData;
		}

		for (var i = 0; i < itemDatas.length; i++) {
			if (typeof (itemDatas[i].skuPic) == "object"
					&& (itemDatas[i].sourceSkuPicItemId == ""
							|| itemDatas[i].sourceSkuPicItemId == itemDatas[i].DT_RowId || propType == "1")) {
				hasNoUploaded = true;
				formData.append(i, itemDatas[i].skuPic);
			}
		}
		if (hasNoUploaded == false) {
			commit2Server();
			return;
		}

		$.ajax({
			url : "${ctx}/common/batchFileUpload?fileType=2",
			type : 'POST',
			data : formData,
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			beforeSend : function() {},
			success : function(data) {
				if (data.returnCode == "0000") {
					var returnData = data.returnData;
					if (propType == "0") {//多规格
						for (var j = 0; j < returnData.length; j++) {
							if (returnData[j].isSuccess == "1") {
								productItemData[parseInt(returnData[j].fileKey)].skuPic = returnData[j].filePath;
								productItemData[parseInt(returnData[j].fileKey)].isSkuPicUploaded = "1";

								//批量设置图片的sku
								for (var k = 0; k < productItemData.length; k++) {
									if (productItemData[k].sourceSkuPicItemId == productItemData[parseInt(returnData[j].fileKey)].DT_RowId) {
										productItemData[k].skuPic = returnData[j].filePath;
										productItemData[k].isSkuPicUploaded = "1";
									}
								}
							}
						}
					} else {
						for (var j = 0; j < returnData.length; j++) {
							if (returnData[j].isSuccess == "1") {
								singleProductItemData[parseInt(returnData[j].fileKey)].skuPic = returnData[j].filePath;
								singleProductItemData[parseInt(returnData[j].fileKey)].isSkuPicUploaded = "1";
							}
						}
					}

					commit2Server();
				} else {
					hide_waitMe();
					swal({
						title : "图片上传失败",
						type : "error",
						confirmButtonText : "确定"
					});
				}
			},
			error : function(responseStr) {
				hide_waitMe();
				swal("图片上传失败");
			}
		});
	}

	function quickSelectPropValue(element) {
		var value = $(element).html().replace(/<br>/g, "\n");
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
		var elementId = $(element).attr("id");
		var tagPriceId = "tagPrice_" + elementId.substr(10);
		if (Number(value) >= Number($("#" + tagPriceId).val())) {
			return false;
		} else {
			return true;
		}
	}, "价格必须小于吊牌价");

	$.validator.addMethod("salePriceDiscount", function(value, element) {
		var elementId = $(element).attr("id");
		var tagPriceId = "tagPrice_" + elementId.substr(10);
		if ((Number(value)/Number($("#" + tagPriceId).val()))<=0.1) {
			return false;
		} else {
			return true;
		}
	}, "折扣不可低于一折");

	$.validator.addMethod("mallPrice", function(value, element) {
		var elementId=$(element).attr("id");
		var mallPriceId="mallPrice_"+elementId.substr(10);
		if(Number(value)>=Number($("#"+mallPriceId).val())){
			return false;
		}else{
			return true;
		}
	}, "活动价必须小于商城价");


	$.validator.addMethod("mallPriceDiscount", function(value, element) {
		var elementId=$(element).attr("id");
		var mallPriceId="mallPrice_"+elementId.substr(10);
		if(Number(value)>Number($("#"+mallPriceId).val())){
			return false;
		}else{
			return true;
		}
	}, "活动价必须小于等于商城价");


	$.validator.addMethod("costPrice", function(value, element) {
		var elementId = $(element).attr("id");
		var salePriceId = "salePrice_" + elementId.substr(10);
		if (Number(value) >= Number($("#" + salePriceId).val())) {
			return false;
		} else {
			return true;
		}
	}, "结算价必须小于活动价");

	$.validator.addMethod("costPriceRate", function(value, element) {
		var elementId = $(element).attr("id");
		var salePriceId = "salePrice_" + elementId.substr(10);
		var priceArray = new Array();
		var price = {"salePrice":$("#" + salePriceId).val(), "costPrice":value};
		priceArray.push(price);
		var svipDiscount = $("#svipDiscount").val()==""?0.95:$("#svipDiscount").val();
		var costPriceRate = {"priceArray":JSON.stringify(priceArray), "svipDiscount":svipDiscount,"brandId":$('#brandId option:selected') .val()};
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
			var costPriceRate = {"priceArray":JSON.stringify(priceArray), "svipDiscount":svipDiscount,"brandId":$('#brandId option:selected') .val()};
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

	$(function(){
		var myDate = new Date();
		var year=myDate.getFullYear();
		var startYear=myDate.getFullYear()+1;//起始年份
        var endYear=myDate.getFullYear()-41;//结束年份

        for (var i=startYear;i>=endYear;i--){
        	$("#yearOfListing").append('<option>'+i+'</option>')
        }
	});

	function delMainVideo(_this){
		$(_this).parent().hide();
		$("#mainVideoSize").val("");
		$("#mainVideoUrl").val("");
	}

	function uploadVideo(obj) {
		if (obj.files.length === 0) {
			return;
		}
		var oFile = obj.files[0];
		var rFilter = ["avi","mp4","AVI","MP4","flv","FLV"];
		var suffix = oFile.name.substring(oFile.name.lastIndexOf(".")+1);
		var fileName = oFile.name;
		if($.inArray(suffix,rFilter)==-1){
			swal("文件格式不正确！");
			return;
		}
		if(oFile.size>100*1024*1024){
			swal("文件大小不能超过100M");
			return;
		}
		var url = URL.createObjectURL(oFile);
		$("#videoPre").prop("src", url);
		var loaded;
		if(suffix == "mp4" || suffix == "MP4"){
			$("#videoPre")[0].addEventListener("loadedmetadata", function(){
				/*if(this.videoWidth<this.videoHeight){
					loaded = true;
					swal("视频尺寸错误，视频高度不得大于宽度！");
					hide_waitMe();
					return;
				}else{*/
					if(loaded){
						return;
					}
					var size = oFile.size/(1024*1024);
					$("#mainVideoSize").val(size.toFixed(2));
					$("#videoName").text(fileName+"("+size.toFixed(2)+"M)");
					$("#videoName").append('<a href="javascript:;" onclick="delMainVideo(this);">     删除</a>');
					$("#videoName").show();
					var reader = new FileReader();
					reader.onload = function(e) {
						var formData = new FormData();
						formData.append("file",oFile);
						formData.append("fileType",11);
						$.ajax({
							url : "${ctx}/common/fileUpload",
							type : 'POST',
							data : formData,
							// async: false,
							// 告诉jQuery不要去处理发送的数据
							processData : false,
							// 告诉jQuery不要去设置Content-Type请求头
							contentType : false,
							beforeSend:function(){
								show_waitMe();
							},
							success : function(data) {
								if (data.returnCode=="0000") {
									loaded = true;
									var filePath = data.returnData;
									$("#mainVideoUrl").val(filePath);
								} else {
									swal({
										title: "文件上传失败！",
										type: "error",
										confirmButtonText: "确定"
									});
								}
								hide_waitMe();
							},
							error : function(responseStr) {
								swal("文件上传失败");
								hide_waitMe();
							}
						});
					}
					reader.readAsDataURL(oFile);
				// }
			});
		}else{
			show_waitMe();
			var size = oFile.size/(1024*1024);
			$("#mainVideoSize").val(size.toFixed(2));
			$("#videoName").text(fileName+"("+size.toFixed(2)+"M)");
			$("#videoName").append('<a href="javascript:;" onclick="delMainVideo(this);">     删除</a>');
			$("#videoName").show();
			var reader = new FileReader();
			reader.onload = function(e) {
				var formData = new FormData();
				formData.append("file",oFile);
				formData.append("fileType",11);
				$.ajax({
					url : "${ctx}/common/fileUpload",
					type : 'POST',
					data : formData,
					async: false,
					// 告诉jQuery不要去处理发送的数据
					processData : false,
					// 告诉jQuery不要去设置Content-Type请求头
					contentType : false,
					beforeSend:function(){
						console.log("文件片上传中，请稍候");
					},
					success : function(data) {
						if (data.returnCode=="0000") {
							hide_waitMe();
							loaded = true;
							var filePath = data.returnData;
							$("#mainVideoUrl").val(filePath);
						} else {
							hide_waitMe();
							if(data.returnMsg){
								loaded = true;
								swal(data.returnMsg);
								return;
							}else{
								swal({
									title: "文件上传失败！",
									type: "error",
									confirmButtonText: "确定"
								});
							}
						}
					},
					error : function(responseStr) {
						swal("文件上传失败");
					}
				});
			}
			reader.readAsDataURL(oFile);
		}
	}

	function uploadDescVideo(obj) {
		if (obj.files.length === 0) {
			return;
		}
		var oFile = obj.files[0];
		var rFilter = ["avi","mp4","AVI","MP4","flv","FLV"];
		var suffix = oFile.name.substring(oFile.name.lastIndexOf(".")+1);
		var fileName = oFile.name;
		if($.inArray(suffix,rFilter)==-1){
			swal("文件格式不正确！");
			return;
		}
		if(oFile.size>100*1024*1024){
			swal("文件大小不能超过100M");
			return;
		}
		var url = URL.createObjectURL(oFile);
		$("#descVideoPre").prop("src", url);
		var loaded;
		if(suffix == "mp4" || suffix == "MP4"){
			$("#descVideoPre")[0].addEventListener("loadedmetadata", function(){
				/*if(this.videoWidth<this.videoHeight){
					loaded = true;
					swal("视频尺寸错误，视频高度不得大于宽度！");
					hide_waitMe();
					return;
				}else{*/
					if(loaded){
						return;
					}
					var size = oFile.size/(1024*1024);
					$("#descVideoSize").val(size.toFixed(2));
					var reader = new FileReader();
					reader.onload = function(e) {
						var formData = new FormData();
						formData.append("file",oFile);
						formData.append("fileType",11);
						$.ajax({
							url : "${ctx}/common/fileUpload",
							type : 'POST',
							data : formData,
							// async: false,
							// 告诉jQuery不要去处理发送的数据
							processData : false,
							// 告诉jQuery不要去设置Content-Type请求头
							contentType : false,
							beforeSend:function(){
								show_waitMe();
							},
							success : function(data) {
								if (data.returnCode=="0000") {
									loaded = true;
									var filePath = data.returnData;
									$("#descVideoUrl").val(filePath);
									$("#descVideoPre").prop("src","${ctx}/file_servelt"+filePath);
									var video;
									var scale = 0.8;
									var initialize = function() {
										video = document.getElementById("descVideoPre");
										video.addEventListener('loadeddata',captureImage);
									};
									var captureImage = function() {
										var src = $("#descVideoPre").attr("src");
										if(src.indexOf("/video")!=-1){
											var canvas = document.createElement("canvas");
											canvas.width = video.videoWidth * scale;
											canvas.height = video.videoHeight * scale;
											canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
											var src = canvas.toDataURL("image/png");
											$("#videoCover").prop("src",src);
											$("#imgBox").parent().show();
										}
									};
									initialize();
								} else {
									swal({
										title: "文件上传失败！",
										type: "error",
										confirmButtonText: "确定"
									});
								}
								hide_waitMe();
							},
							error : function(responseStr) {
								swal("文件上传失败");
								hide_waitMe();
							}
						});
					}
					reader.readAsDataURL(oFile);
				// }
			});
		}else{
			show_waitMe();
			var size = oFile.size/(1024*1024);
			$("#descVideoSize").val(size.toFixed(2));
			var reader = new FileReader();
			reader.onload = function(e) {
				var formData = new FormData();
				formData.append("file",oFile);
				formData.append("fileType",11);
				$.ajax({
					url : "${ctx}/common/fileUpload",
					type : 'POST',
					data : formData,
					async: false,
					// 告诉jQuery不要去处理发送的数据
					processData : false,
					// 告诉jQuery不要去设置Content-Type请求头
					contentType : false,
					beforeSend:function(){
						console.log("文件片上传中，请稍候");
					},
					success : function(data) {
						if (data.returnCode=="0000") {
							hide_waitMe();
							loaded = true;
							var filePath = data.returnData;
							$("#descVideoUrl").val(filePath);
							$("#descVideoPre").prop("src","${ctx}/file_servelt"+filePath);
							var video;
							var scale = 0.8;
							var initialize = function() {
								video = document.getElementById("descVideoPre");
								video.addEventListener('loadeddata',captureImage);
							};
							var captureImage = function() {
								var src = $("#descVideoPre").attr("src");
								if(src.indexOf("/video")!=-1){
									var canvas = document.createElement("canvas");
									canvas.width = video.videoWidth * scale;
									canvas.height = video.videoHeight * scale;
									canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
									var src = canvas.toDataURL("image/png");
									$("#videoCover").prop("src",src);
									$("#imgBox").parent().show();
								}
							};
							initialize();
							$("#imgBox").parent().hide();
						} else {
							hide_waitMe();
							if(data.returnMsg){
								loaded = true;
								swal(data.returnMsg);
								return;
							}else{
								swal({
									title: "文件上传失败！",
									type: "error",
									confirmButtonText: "确定"
								});
							}
						}
					},
					error : function(responseStr) {
						swal("文件上传失败");
					}
				});
			}
			reader.readAsDataURL(oFile);
		}
	}

	function toShowVideo(){
		$("#descVideoDiv").show();
		$("#descVideoPre").attr("hidden",false);
		$("#descVideoPre").prop("autoplay",true);
		var descVideoUrl = $("#descVideoUrl").val();
		$("#descVideoPre").prop("src","${ctx}/file_servelt"+descVideoUrl);
		$("#descVideoPre").load();
		$("#productBox").show();
	}

	function closeVideo(){
		$("#descVideoDiv").hide();
		$("#descVideoPre").attr("hidden",true);
		$("#productBox").hide();
	}

	function removeVideo(){
		$("#imgBox").parent().hide();
		$("#descVideoUrl").val("");
		$("#descVideoSize").val("");
	}
</script>

</body>

</html>