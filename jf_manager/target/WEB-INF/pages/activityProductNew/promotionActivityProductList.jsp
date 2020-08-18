<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript">
 	 var viewerPictures;
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
		
	 });
	 
	 function formatMoney(s, n) {
	    n = n > 0 && n <= 20 ? n : 2;
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(),
	    r = s.split(".")[1];
	    t = "";
	    for(i = 0; i < l.length; i ++ )
	    {
	       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
	    }
	    return t.split("").reverse().join("") + "." + r;
	 }
	 
	 //审核
	 function auditActivityProduct(activityProductId, audit) {
		 $.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/activityProductNew/updatePromotionActivityProduct.shtml',
			 data: {activityProductId : activityProductId, audit : audit },
			 dataType: 'json',
			 success: function(data) {
				 if(data == null || data.statusCode != 200){
				     commUtil.alertError(data.message);
				 }else {
					 if(data.productAuditStatus == '3') {
						 $("#"+activityProductId+"-a").parent("div").html("<a id='"+ activityProductId +"-a' href=\"javascript:auditActivityProduct("+ activityProductId +", 3);\">驳回</a>");
					 }else {
						 var strHtml = [];
						 strHtml.push("<a id='"+ activityProductId +"-a' href=\"javascript:auditActivityProduct("+ activityProductId +", 2);\">通过</a>");
						 strHtml.push("<span style=\"margin: 0px 5px;\">or</span>");
						 strHtml.push("<a href=\"javascript:auditActivityProduct("+ activityProductId +", 3);\">驳回</a>");
						 $("#"+activityProductId+"-a").parent("div").html(strHtml.join(""));
						 $("#"+activityProductId+"-activityPrice").html(data.activityPrice);
					 }
					 if(data.audit == '2') { //通过
						 var str = $("#"+activityProductId+"-a").parent("div").parent("td").next().find("div").html();
						 if(str == '') {
							 $("#"+activityProductId+"-a").parent("div").parent("td").next().find("div").html("<span id='"+ activityProductId +"-span' style=\"color:black;\">审核通过</span>");
						 }else{
							 $("#"+activityProductId+"-span").html("审核通过");
						 }
				 	 }else { //驳回
				 		 var str = $("#"+activityProductId+"-a").parent("div").parent("td").next().find("div").html();
						 if(str == '') {
							 $("#"+activityProductId+"-a").parent("div").parent("td").next().find("div").html("<span id='"+ activityProductId +"-span' style=\"color:black;\">已驳回</span>");
						 }else{
							 $("#"+activityProductId+"-span").html("已驳回");
						 }
				 	 }
				 }
			 },
			 error: function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }
	 
	 //PC版
	 function lawAuditProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width()*0.75,
			title: "法务审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/lawAuditProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: false,
			slide: false,
			data: null
		});
	 }
	 
	 //图片查看
	 function viewerPic(productId){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : "${pageContext.request.contextPath}/product/getPoductPic.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productId:productId},
			timeout : 30000,
			success : function(data) {
				console.log(data);
				if(data&&data.length>0){
					for (var i=0;i<data.length;i++) {
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
					}
					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
						$("#viewer-pictures").hide();
					}});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	 }
	 
 	 var listConfig={
	      url:"/activityProductNew/getActivityProductList.shtml?statusAudit=2&activityId=${activityId }&activityAreaId=${activityAreaId}",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'系统ID',name:'productId', align:'center', isSort:false, width:80},
							{display:'商品ID',name:'productCode', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								if(rowdata.isGift == 1){
									return rowdata.productCode+"<br><span style='color:red;'>赠品</span>";
								}else{
									return rowdata.productCode;
								}
							}},
	                        {display:'主图',name:'productPic', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
			                    var h = "";
			                    h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.productPic+"' width='60' height='60' onclick='viewerPic("+rowdata.productId+")'>";
			                    return h;
							}},
	                        {display:'商品名称',name:'productName', align:'left', isSort:false, width:200, render: function(rowdata, rowindex) {
	                        	var html = [];
			                    if(rowdata.productName != "" && rowdata.productName != null) {
			                    	html.push(rowdata.productName+"</br>");
			                    }
			                    html.push("<a href=\"javascript:lawAuditProduct(" + rowdata.productId + ");\">【PC版】</a>"); 
			                    html.push("<a href=\"${mUrl }"+ rowdata.productCode +"\" target=\"_blank\" >【手机预览】</a>"); 
			                    return html.join("");
							}},
							{display:'品牌',name:'productBrandName', align:'center', isSort:false, width:120},
							{display:'货号',name:'productArtNo', align:'center', isSort:false, width:120},
							{display:'动态活动价',name:'salePrice', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								html.push(rowdata.salePriceMin);
								if(rowdata.salePriceMin != rowdata.salePriceMax){
									html.push("-");
									html.push(rowdata.salePriceMax);
								};
							    return html.join("");
							}},
							{display:'静态活动价',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								var activityPrice = rowdata.activityPrice==null?"":rowdata.activityPrice;
								html.push("<span id='"+ rowdata.id +"-activityPrice' >"+activityPrice+"</span>");
							    return html.join("");
							}},
							{display:'折扣',name:'salePrice', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								html.push(rowdata.discountMin);
								if(rowdata.discountMin != rowdata.discountMax){
									html.push("-");
									html.push(rowdata.discountMax);
								};
							    return html.join("");
							}},
							{display:'商家最低活动价',name:'activityPriceMin', align:'center', isSort:false, width:120},
							{display:'SVIP折扣',name:'productSvipDiscount', align:'center', isSort:false, width:80},
							{display:'库存',name:'productStock', align:'center', isSort:false, width:80},
							{display:'审核',name:'audit', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								if(rowdata.isGift == 1){
									return "";
								}else{
									var html = [];
									if(rowdata.productAuditStatus == '3') {
										html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:auditActivityProduct("+ rowdata.id +", 3);\">驳回</a>");
									}else {
										html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:auditActivityProduct("+ rowdata.id +", 2);\">通过</a>");
										html.push("<span style=\"margin: 0px 5px;\">or</span>");
										html.push("<a href=\"javascript:auditActivityProduct("+ rowdata.id +", 3);\">驳回</a>");
									}
									return html.join("");
								}
							}},
							{display:'排期审核状态',name:'cooAuditStatus', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.cooAuditStatus == '2') {
									if(rowdata.cooAuditStatusDesc != '') {
										html.push("<span id='"+ rowdata.id +"-span' style=\"color:green;\">"+rowdata.cooAuditStatusDesc+"</span>");
									}
								}else if(rowdata.cooAuditStatus == '3'){
									if(rowdata.cooAuditStatusDesc != '') {
										html.push("<span id='"+ rowdata.id +"-span' style=\"color:red;\">"+rowdata.cooAuditStatusDesc+"</span>");
									}
								}
								return html.join("");
							}},
							{display:'排期审核人',name:'cooAuditName', align:'center', isSort:false, width:100},
							{display:'法务状态',name:'productAuditStatus', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.productAuditStatus != '2' && rowdata.productAuditStatus != '3') {
									if(rowdata.productAuditStatusDesc != '') {
										html.push("<span style=\"color:black;\">"+rowdata.productAuditStatusDesc+"</span>");
									}
								}else if(rowdata.productAuditStatus == '2') {
									if(rowdata.productAuditStatusDesc != '') {
										html.push("<span style=\"color:green;\">"+rowdata.productAuditStatusDesc+"</span>");
									}
								}else {
									if(rowdata.productAuditStatusDesc != '') {
										html.push("<span style=\"color:red;\">"+rowdata.productAuditStatusDesc+"</span>");
									}
								}
								return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      } , 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
<!-- 	<div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label" >系统 ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productId" name="productId" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商品 ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商品名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productName" name="productName" />
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" style="color: red;" >审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="cooAuditStatus" name="cooAuditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="cooAuditStatus" items="${cooAuditStatusList }">
								<option value="${cooAuditStatus.statusValue }" >
									${cooAuditStatus.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
