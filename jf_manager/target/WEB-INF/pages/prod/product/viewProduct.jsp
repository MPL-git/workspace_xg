<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript">
</script>

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js"
type="text/javascript">
</script>


<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}

.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}


.table-title{
font-size: 17px;font-weight: 600;
}
.center-align{
	text-align: center;
}
.table-title-link{
	color: #1B17EE;
	font-size: 15px;
	text-decoration: none;
}

.video-js{
	width: 300px;
    height: 200px;
	
}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});

	$("#maingridProductItem").ligerGrid({
        columns: [
			{ display: 'SKU图', name: 'pic',render: function(rowdata, rowindex) {
				var h = "";
                if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
                 h += "<img src='"+rowdata.pic+"' width='150' height='150' onclick='viewerPic(this.src)'>";
                }else{
                 h += "<img src='${pageContext.request.contextPath}/file_servelt"+rowdata.pic+"' width='150' height='150' onclick='viewerPic(this.src)'>";
                }
              	return h;
		 	}},
		 	<c:forEach var="productProp" items="${productProps}">
	        	{ display: '${productProp.name}', name: 'logo',render: function(rowdata, rowindex) {
	        		var productPropId = ${productProp.id};
					return rowdata.propValuesMap[productPropId];
			 	} },
			</c:forEach>
        	{ display: '吊牌价', name: 'tagPrice' ,render: function(rowdata, rowindex) {
        	  	if(rowdata.tagPrice){
	       	  		return rowdata.tagPrice;
	       	  	}else{
	       	  		return "";
	       	  	}
		 	} },
        	{ display: '商城价', name: 'mallPrice' ,render: function(rowdata, rowindex) {
        	  	if(rowdata.mallPrice){
	       	  		return rowdata.mallPrice;
	       	  	}else{
	       	  		return "";
	       	  	}
		 	} },
        	{ display: '活动价', name: 'salePrice' ,render: function(rowdata, rowindex) {
        	  	if(rowdata.salePrice){
	       	  		return rowdata.salePrice;
	       	  	}else{
	       	  		return "";
	       	  	}
		 	} },
		 	{ display: '结算价', name: 'year', width: 100, hide:${productCustom.mchtType == 1}, render: function(rowdata, rowindex) {
		 		if(rowdata.costPrice){
	       	  		return rowdata.costPrice;
	       	  	}else{
	       	  		return "";
	       	  	}
            }},
        	{ display: '冻结数量', name: 'lockedAmount', render: function(rowdata, rowindex) {
        		if(rowdata.lockedAmount){
	       	  		return rowdata.lockedAmount;
	       	  	}else{
	       	  		return 0;
	       	  	}
		 	}},
	        { display: '库存数量', name: 'stock', render: function(rowdata, rowindex) {
	    		if(rowdata.stock){
	       	  		return rowdata.stock;
	       	  	}else{
	       	  		return 0;
	       	  	}
		 	}},
	        { display: 'SKU商家编码', name: 'sku',render: function(rowdata, rowindex){
	        	if(rowdata.sku){
	       	  		return rowdata.sku;
	       	  	}else{
	       	  		return "";
	       	  	}
	        }}
        ],
        pageSize:5,
        pageSizeOptions:[5,10,20,50,100],
        url:'${pageContext.request.contextPath}/product/productItemData.shtml?productId=${productCustom.id}',
        width: '100%',
        onAfterShowData: function() {
			$(".l-grid-row-cell-inner").css("height", "auto");
		}
    });

	$("#productUpperLowerLogItem").ligerGrid({
		columns: [
			{ display: '时间', name: 'createDate' ,render: function(rowdata, rowindex) {
					if(rowdata.createDate!=null){
						var createDate=new Date(rowdata.createDate);
						return createDate.format("yyyy-MM-dd");
					}
				} },
			{ display: '商品上下架状态', name: 'status' ,render: function(rowdata, rowindex) {
					if(rowdata.status == 0){
						return "下架";
					}else{
						return "上架";
					}
				} },
			{ display: '操作人', name: 'type', render: function(rowdata, rowindex) {
					if(rowdata.type == 0){
						return "平台";
					}else{
						return "商家";
					}
				}},
			{ display: '下架原因', name: 'offReason'}
		],
		showRownumber:true,
		pageSize:5,
		pageSizeOptions:[5,10,20,50,100],
		url:'${pageContext.request.contextPath}/product/upperLowerLogItemData.shtml?productId=${productCustom.id}',
		width: '100%',
		onAfterShowData: function() {
			$(".l-grid-row-cell-inner").css("height", "auto");
		}
	});
});

function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
	imgPath=imgPath.replace('_M', '');
	imgPath=imgPath.replace('_S', '');
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();
}

/*
function productAuditLogList(productId) {
	$.ligerDialog.open({
		height: $(window).height() - 40,
		width: $(window).width() - 40,
		title: "审核记录",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/product/productAuditLogList.shtml?productId=" + productId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
*/

function toEditManualWeight(productId){
	$.ligerDialog.open({
		height: $(window).height()*0.35,
		width: $(window).width()*0.35,
		title: "操作",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/product/toEditManualWeight.shtml?productId=" + productId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
</script>
<html>
<body>
		<div><span class="table-title" >商品查看</span></div>
		<table class="gridtable">
			<tr>
				<td class="title" width="140px;">供应商</td>
				<td align="left" class="l-table-edit-td">${productCustom.companyName}&nbsp;&nbsp;${productCustom.shopName}&nbsp;&nbsp;${productCustom.mchtCode}</td>
			</tr>
			<tr>
				<td class="title">
				<c:if test='${productCustom.mchtType=="1"}'>
				结算方式
				</c:if>
				<c:if test='${productCustom.mchtType=="2"}'>
				佣金
				</c:if>
				
				</td>
				<td align="left" class="l-table-edit-td">
				<c:if test='${productCustom.mchtType=="1"}'>
				${productCustom.priceModel}
				<div>
				<c:if test='${productCustom.priceModelDesc!=""}'>
				<textarea style="width: 30%;height:110px;" readonly="readonly">${productCustom.priceModelDesc}</textarea>
				</c:if>
				</div>
				</c:if>
				<c:if test='${productCustom.mchtType=="2"}'>
					<c:if test="${productCustom.brandId==0}">
						${thirdFeeRate*100}%
					</c:if>
					<c:if test="${productCustom.brandId!=0}">
						${productCustom.popCommissionRate*100}%
					</c:if>
				</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">选择品类</td>
				<td align="left" class="l-table-edit-td">
				${productTypeStr }
				</td>
			</tr>
			<tr>
				<td class="title">选择品牌<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				${productCustom.productBrandName}
				</td>
			</tr>
			<tr>
				<td class="title">商品名称<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				${productCustom.name}
				</td>
			</tr>
			<tr>
				<td class="title">推荐文案</td>
				<td align="left" class="l-table-edit-td">
					${productCustom.recommendRemarks}
				</td>
			</tr>
			<tr>
				<td class="title">商品货号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				${productCustom.artNo}
				</td>
			</tr>
		  	<tr>
    		<td class="title">适合性别 <span class="required">*</span></td>
    		<td align="left" class="l-table-edit-td">${suiteSexStr}</td>
    		</tr>
			<tr>
				<td class="title">适合人群<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">${suiteGroupStr}</td>
			</tr>
			<tr>
				<td class="title">年份季节<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
				<c:if test="${productCustom.year!='' && productCustom.year!=null}">${productCustom.year}年</c:if>${productCustom.seasonDesc}
				</td>
			</tr>
			<tr>
				<td class="title">每人限购数量</td>
				<td align="left" class="l-table-edit-td">
				${productCustom.limitBuy }
				</td>
			</tr>
			<tr>
				<td class="title">售后信息</td>
				<td align="left" class="l-table-edit-td">

                                         收货人：${productAfterTemplet.recipient}<br>电话：${productAfterTemplet.phone} <br>地址：${productAfterTemplet.address}
				
				</td>
			</tr>
			<tr>
				<td class="title">7天无理由退换货</td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${productCustom.isReturn7days eq 0}">
						不支持
					</c:if>
					<c:if test="${productCustom.isReturn7days eq 1}">
						支持
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">保质期<c:if test="${productCustom.productType1Id==364}"> <span class="required">*</span></c:if> </td>
				<td align="left" class="l-table-edit-td">
					${productCustom.shelfLife}
				</td>
			</tr>

			<c:if test="${productCustom.productType1Id==364}">
			<tr>
				<td class="title">贮存条件<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					${productExtend.storageConditions}
				</td>
			</tr>
			<tr>
				<td class="title">产地<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					${productExtend.placeOfOrigin}
				</td>
			</tr>
				<c:if test="${flag ne '1'}">
			<tr>
				<td class="title">食品标签<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${productExtend.foodLabelPic != null && productExtend.foodLabelPic != ''}">
					<img src='${pageContext.request.contextPath}/file_servelt${productExtend.foodLabelPic}' width='150' height='150' onclick='viewerPic(this.src)'>
					</c:if>
					<c:if test="${productExtend.foodLabelPic == null || productExtend.foodLabelPic == '' }">
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">生产者信息<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					${productExtend.producerInformation}
				</td>
			</tr>
				</c:if>
				<c:if test="${flag eq '2'}">
			<tr>
				<td class="title">批准文号<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td">
					${productExtend.approvalNumber}
				</td>
			</tr>
				</c:if>
			</c:if>


			<tr>
				<td class="title">3C认证证书号</td>
				<td align="left" class="l-table-edit-td">
					${productCustom.cccNo}
				</td>
			</tr>
			<tr>
				<td class="title">排序值</td>
				<td align="left" class="l-table-edit-td">
					总值：${totalWeight}，季节：${productWeight.seasonWeight}，销量：${productWeight.saleWeight}，销售额：${productWeight.saleAmountWeight}，商家等级：${productWeight.mchtGradeWeight}，品牌等级：${productWeight.brandGradeWeight}，评价分值：${productWeight.commentWeight }，人工分值：${productWeight.manualWeight}
					<c:if test="${sessionScope.USER_SESSION.staffID==1}">
						<a href="javascript:;" onclick="toEditManualWeight(${productCustom.id})">【修改人工分】</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">商品主图</td>
				<td align="left" class="l-table-edit-td">
				 <c:forEach var="pic" items="${pics}">
				 <c:if test="${productCustom.source eq 0}">
					 <img src="${pageContext.request.contextPath}/file_servelt${pic.pic}" onclick='viewerPic(this.src)' style="max-width: 180px;max-height: 200px;">
				 </c:if> 
				 <c:if test="${productCustom.source ne 0}">
					 <img src="${pic.pic}" onclick='viewerPic(this.src)' style="max-width: 180px;max-height: 200px;">
				 </c:if> 
				 </c:forEach>
				</td>
			</tr>
			<tr>
				<td class="title">商品视频</td>
				<td align="left" class="l-table-edit-td">
				<c:if test="${empty videoMap.mainVideo}">
				无
				</c:if>
				<c:if test="${not empty videoMap.mainVideo}">
					<video width="420" height="260" controls="controls">
    				 	<source src="${pageContext.request.contextPath}/file_servelt${videoMap.mainVideo}" type="video/mp4">
  					</video>
				</c:if>			 
				</td>
			</tr>
		</table>
		<br>
		<div><span class="table-title" >商品SKU信息</span></div>
		<br>
		<form id="dataFormBrand" runat="server">
			<div id="maingridProductItem" style="margin: 0; padding: 0"></div>
		</form>
		<%-- <table class="gridtable">
			<tr>

				<td class="title" width="140px;">SKU图</td>
				<c:forEach var="productProp" items="${productProps}">
				<td class="title">${productProp.name}</td>
				</c:forEach>
				<td class="title">吊牌价</td>
				<td class="title">商城价</td>
				<td class="title">活动价</td>
				<c:if test="${productCustom.mchtType == 1}">
				<td class="title">结算价</td>
				</c:if>
				<td class="title">冻结数量</td>
				<td class="title">库存数量</td>
				<td class="title">SKU商家编码</td>
			</tr>
			
			<c:forEach var="productItem" items="${productItems}">
			<tr>
				<td class="l-table-edit-td center-align">
				<img alt="" src="${pageContext.request.contextPath}/file_servelt${productItem.pic}" onclick='viewerPic(this.src)' style="max-width: 150px;max-height: 150px;">
				</td>
				<c:forEach var="productProp" items="${productProps}">
				<td class="l-table-edit-td center-align">${productItem.propValuesMap[productProp.id]}</td>
				</c:forEach>
				<td class="l-table-edit-td center-align">${productItem.tagPrice}</td>
				<td class="l-table-edit-td center-align">${productItem.mallPrice}</td>
				<td class="l-table-edit-td center-align">${productItem.salePrice}</td>
				<c:if test="${productCustom.mchtType == 1}">
				<td class="l-table-edit-td center-align">${productItem.costPrice}</td>
				</c:if>
				<td class="l-table-edit-td center-align">${productItem.lockedAmount}</td>
				<td class="l-table-edit-td center-align">${productItem.stock}</td>
				<td class="l-table-edit-td center-align">${productItem.sku}</td>
			</tr>
			
			</c:forEach>
			

		</table> --%>
		<br>
		<div><span class="table-title" >商品详情描述信息</span></div>
		<table class="gridtable" style="width:60%;">
			<tr>
			<c:forEach var="desc" items="${productDesc}" varStatus="varStatus">
			<td class="l-table-edit-td">${desc}</td>
			 <c:if test="${varStatus.index % 2 != 0}">
			  </tr>
			  <tr>
			 </c:if>
			</c:forEach>
			</tr>

		</table>
		
		<br>
		<div><span class="table-title">商品详情图</span></div>
		<div style="width: 100%;">
		<br>
		<c:if test="${not empty videoMap.descVideo}">
			<div style="margin-left:150px;">
			<video width="800" height="290" controls="controls">
    			<source src="${pageContext.request.contextPath}/file_servelt${videoMap.descVideo}" type="video/mp4">
  			</video>
			</div>	
			<br>
		</c:if>
		
		<c:forEach var="descPic" items="${productDescPics }">
			<img style="max-width: 300px;max-height: 300px;" alt="" src="${pageContext.request.contextPath}/file_servelt${descPic.pic}" onclick="viewerPic(this.src)"><br>
		</c:forEach>
		</div>
		
		<table class="gridtable" style="margin-top:20px;">
			<tr>
				<td class="title" width="140px;">销售类型</td>
				<td align="left" class="l-table-edit-td">
				${productCustom.saleTypeDesc}
				</td>
			</tr>
			<tr>
				<td class="title">上架状态</td>
				<td align="left" class="l-table-edit-td">
				<c:if test="${productCustom.status == 1}">
					${productCustom.statusDesc}
				</c:if>
				<c:if test="${productCustom.status == 0}">
					${productCustom.statusDesc}<%--:<c:if test="${productCustom.offReason == null}">无</c:if>${productCustom.offReasonDesc}--%>
				</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">法务审核状态</td>
				<td align="left" class="l-table-edit-td">
				${productCustom.auditStatusDesc}
				</td>
			</tr>
			<tr>
				<td class="title">商品的活动状态</td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${productCustom.productActivityStatus eq '0'}">闲置</c:if>
					<c:if test="${productCustom.productActivityStatus eq '1'}">报名中</c:if>
					<c:if test="${productCustom.productActivityStatus eq '2'}">待开始</c:if>
					<c:if test="${productCustom.productActivityStatus eq '3'}">预热中</c:if>
					<c:if test="${productCustom.productActivityStatus eq '4'}">活动中</c:if>
					<c:if test="${productCustom.productActivityStatus eq '5'}">活动暂停</c:if>
					<c:if test="${productCustom.productActivityStatus eq '6'}">未提审</c:if>
					<c:if test="${productCustom.productActivityStatus eq '7'}">未通过</c:if>
					
				</td>
			</tr>
			<tr>
				<td class="title">所在活动ID/专区</td>
				<td align="left" class="l-table-edit-td">
					<c:if test="${productCustom.productActivityStatus ne '0'}">
						${lastActivityIdStr}
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="title">往期品牌特卖ID</td>
				<td align="left" class="l-table-edit-td">
					${activityIds}
				</td>
			</tr>
			<c:if test="${productCustom.auditStatus=='3'}">
			<tr>
				<td class="title">驳回原因</td>
				<td align="left" class="l-table-edit-td">
				${productCustom.auditRemarks}
				</td>
			</tr>
			</c:if>
		</table>
		
		<br/>
		<%--<div>
			<span class="table-title" >审核记录</span>
			<a class="table-title-link" href="javascript:productAuditLogList(${productId })" >【查看更多】</a>
		</div>
		<table class="gridtable">
			<tr align="center">
				<td class="title">时间</td>
				<td class="title">审核人</td>
				<td class="title">审核状态</td>
				<td class="title">驳回原因</td>
			</tr>
			<c:forEach items="${productAuditLogList}" var="productAuditLog">
				<tr align="center">
				<td><fmt:formatDate value="${productAuditLog.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${productAuditLog.createStaffName }</td>
				<td>
					<c:forEach var="productAuditStatus" items="${productAuditStatusList}">
						<c:if test="${productAuditStatus.statusValue == productAuditLog.status }">
							${productAuditStatus.statusDesc }
						</c:if>
					</c:forEach>
				</td>
				<td>${productAuditLog.remarks }</td>
			</tr>
			</c:forEach>
		</table>--%>

		<div>
			<span class="table-title" >商品状态记录</span>
		</div>
		<br/>
		<form id="dataFormProductUpperLowerLog" runat="server">
			<div id="productUpperLowerLogItem" style="margin: 0; padding: 0"></div>
		</form>

		<c:if test="${productCustom.mchtType == 1}">

		<br/>
		<div>
			<span class="table-title" >SPOP调价记录</span>
		</div>
		<table class="gridtable">
			<tr align="center">
				<td class="title">时间</td>
				<td class="title">操作人</td>
				<td class="title">内容</td>
			</tr>
			<c:forEach items="${productPriceChangeLogList}" var="productPriceChangeLog">
				<tr align="center">
					<td><fmt:formatDate value="${productPriceChangeLog.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${productPriceChangeLog.staffName }</td>
					<td>${productPriceChangeLog.remarks }</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
		
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
</html>
