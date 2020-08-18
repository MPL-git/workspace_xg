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
   <style type="text/css">
 	.line{
 		text-decoration: line-through;
 	}
 </style>
 <script type="text/javascript">
 	 var viewerPictures;
 	 if( $("#operateAuditStatus option:selected").val() !="1"){
 		$(".search-td-search-btn").children("div").eq(1).hide();
	 }
	 $(function() {
	 	 //监听审核状态(当值不为待审核时，隐藏全部通过按钮)
	 	 $("#operateAuditStatus").change(function(){
	 	 	if($(this).children('option:selected').val()!="1"){
	 	 		$(".search-td-search-btn").children("div").eq(1).hide();
	 	 	}
	 	 });
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
		
		//品牌
		$("#productBrandName").ligerComboBox({
	     	 width: 135,
	         slide: false,
	         selectBoxWidth: 450,
	         selectBoxHeight: 300,
	         valueField: 'id',
	         textField: 'name',
	         valueFieldID:'productBrandId',
	         grid: getGridOptions(false),
	         condition:{ fields: [{ name:'name', label:'品牌名', width:90, type:'text' } ]}
	    });
		
		function getGridOptions(checkbox){
		     var options = {
		         columns: [
					{display:'ID',name:'id', align:'center', isSort:false, width:100},
					{display:'品牌',name:'name', align:'center', isSort:false, width:100}
		         ], 
		         switchPageSizeApplyComboBox: false,
			     url: '${pageContext.request.contextPath}/activityNew/getProductBrandList.shtml',
		         pageSize: 10, 
		         checkbox: checkbox
		     };
		     return options;
		 }
		
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
	 function auditActivityProduct(activityProductId, statusAudit, audit) {
		 $.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/activityProductNew/updateActivityProduct.shtml',
			 data: {activityProductId : activityProductId, statusAudit : statusAudit, audit : audit },
			 dataType: 'json',
			 success: function(data) {
				 if(data == null || data.statusCode != 200){
				     commUtil.alertError(data.message);
				 }else {
					 if(data.statusAudit == '1') { //1.专员审核	
						 if(data.productAuditStatus == '3') {
							 $("#"+activityProductId+"-a").parent("div").html("<a id='"+ activityProductId +"-a' href=\"javascript:auditActivityProduct("+ activityProductId +", 1, 3);\">驳回</a>");
						 }else if(data.operateAuditStatus == '2' && data.cooAuditStatus != '3' && data.cooAuditStatus != '2') {
							 $("#"+activityProductId+"-a").parent("div").html("<a id='"+ activityProductId +"-a' href=\"javascript:auditActivityProduct("+ activityProductId +", 1, 3);\">驳回</a>");
						 }else if(data.operateAuditStatus != '3' && data.operateAuditStatus != '2' && data.cooAuditStatus != '3' && data.cooAuditStatus != '2' && data.productAuditStatus != '3'){
							 var strHtml = [];
							 strHtml.push("<a id='"+ activityProductId +"-a' href=\"javascript:auditActivityProduct("+ activityProductId +", 1, 2);\">通过</a>");
							 strHtml.push("<span style=\"margin: 0px 5px;\">or</span>");
							 strHtml.push("<a href=\"javascript:auditActivityProduct("+ activityProductId +", 1, 3);\">驳回</a>");
							 $("#"+activityProductId+"-a").parent("div").html(strHtml.join(""));
						 }else{
							 $("#"+activityProductId+"-a").parent("div").html("<span id='"+ activityProductId +"-a'></span>");
						 }
					 }else if(data.statusAudit == '2') { //2.排期审核
						 if(data.productAuditStatus == '3') {
							 $("#"+activityProductId+"-a").parent("div").html("<a id='"+ activityProductId +"-a' href=\"javascript:auditActivityProduct("+ activityProductId +", 2, 3);\">驳回</a>");
						 }else {
							 var strHtml = [];
							 strHtml.push("<a id='"+ activityProductId +"-a' href=\"javascript:auditActivityProduct("+ activityProductId +", 2, 2);\">通过</a>");
							 strHtml.push("<span style=\"margin: 0px 5px;\">or</span>");
							 strHtml.push("<a href=\"javascript:auditActivityProduct("+ activityProductId +", 2, 3);\">驳回</a>");
							 $("#"+activityProductId+"-a").parent("div").html(strHtml.join(""));
						 }
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
	      url:"/activityProductNew/getActivityProductList.shtml?activityId=${activityId }&activityAreaId=${activityAreaId}",
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
							{display:'商城价',name:'salePrice', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								html.push(rowdata.mallPriceMin);
								if(rowdata.mallPriceMin != rowdata.mallPriceMax){
									html.push("-");
									html.push(rowdata.mallPriceMax);
								};
							    return html.join("");
							}},
							{display:'活动价',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								html.push(rowdata.salePriceMin);
								if(rowdata.salePriceMin != rowdata.salePriceMax){
									html.push("-");
									html.push(rowdata.salePriceMax);
								};
								if(rowdata.activityPrice!=null && (rowdata.salePriceMin != rowdata.activityPrice || rowdata.salePriceMax != rowdata.activityPrice)){//审核通过
									html.push("<br><span class='line' id='"+ rowdata.id +"-activityPrice' >"+rowdata.activityPrice+"</span>");							
								}
							    return html.join("");
							}},
							{display:'结算价',name:'', align:'center', isSort:false, width:120,render:function(rowdata, rowindex){
								var html = [];
								if (rowdata.mchtType=='1') {
								html.push(rowdata.costPriceMin);
								if(rowdata.costPriceMin != rowdata.costPriceMax){
									html.push("-");
									html.push(rowdata.costPriceMax);
								};		
							   }else if (rowdata.mchtType=='2') {
								   html.push("-");
							 }
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
							{display:'定金',name:'deposit', align:'center', isSort:false, width:120},
							{display:'抵扣金额',name:'deductAmount', align:'center', isSort:false, width:120,render: function(rowdata, rowindex) {
								if(rowdata.deductAmount != null && rowdata.deposit != null){
									var num = parseFloat(rowdata.deductAmount/rowdata.deposit).toFixed(0);
									return rowdata.deductAmount+"<br><font color='red'>("+num+"倍)</font>";
								}else{
									return rowdata.deductAmount;
								}
							}},
							{display:'销量',name:'salesVolume', align:'center', isSort:false, width:80},
							{display:'商品好评率',name:'favorableRate', align:'center', isSort:false, width:80,render: function(rowdata, rowindex) {
								if(rowdata.favorableRate != null){
									var num = parseFloat(rowdata.favorableRate).toFixed(2)*100;
									return num+"%";
								}else{
									return "";
								}
							}},
							{display:'库存',name:'productStock', align:'center', isSort:false, width:80},
							{display:'审核',name:'audit', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								if(rowdata.isGift == 1){
									return "";
								}else{
									var html = [];
									if(rowdata.productAuditStatus == '3') {
										html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:auditActivityProduct("+ rowdata.id +", 1, 3);\">驳回</a>");
									}
									if(rowdata.cooAuditStatus != '2' && rowdata.cooAuditStatus != '3') {
										if(rowdata.operateAuditStatus == '2'){
											html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:auditActivityProduct("+ rowdata.id +", 1, 3);\">驳回</a>");
										}else if(rowdata.operateAuditStatus != '3' && rowdata.productAuditStatus != '3'){
											html.push("<a id='"+ rowdata.id +"-a' href=\"javascript:auditActivityProduct("+ rowdata.id +", 1, 2);\">通过</a>");
											html.push("<span style=\"margin: 0px 5px;\">or</span>");
											html.push("<a href=\"javascript:auditActivityProduct("+ rowdata.id +", 1, 3);\">驳回</a>");
										}
									}
									return html.join("");
								}
							}},
							{display:'专员审核状态',name:'operateAuditStatus', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.operateAuditStatus == '2') {
									if(rowdata.operateAuditStatusDesc != '') {
										html.push("<span id='"+ rowdata.id +"-span' style=\"color:green;\">"+rowdata.operateAuditStatusDesc+"</span>");
									}
								}else if(rowdata.operateAuditStatus == '3'){
									if(rowdata.operateAuditStatusDesc != '') {
										html.push("<span id='"+ rowdata.id +"-span' style=\"color:red;\">"+rowdata.operateAuditStatusDesc+"</span>");
									}
								}
								return html.join("");
							}},
							{display:'排期审核状态',name:'cooAuditStatus', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.cooAuditStatus == '2') {
									if(rowdata.cooAuditStatusDesc != '') {
										html.push("<span style=\"color:green;\">"+rowdata.cooAuditStatusDesc+"</span>");
									}
								}else if(rowdata.cooAuditStatus == '3'){
									if(rowdata.cooAuditStatusDesc != '') {
										html.push("<span style=\"color:red;\">"+rowdata.cooAuditStatusDesc+"</span>");
									}
								}
								return html.join("");
							}},
							{display:'专员审核人',name:'operateAuditName', align:'center', isSort:false, width:100},
							{display:'法务状态',name:'productAuditStatus', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
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
	      },
	      pageSize:"100"
	  };
 	 
 	 //点击搜索当不是待审核时，隐藏全部通过按钮
 	 function searchList(){
 		 if( $("#operateAuditStatus option:selected").val() !="1"){
 			$(".search-td-search-btn").children("div").eq(1).hide();
		 }else{
			 $(".search-td-search-btn").children("div").eq(1).show();
		 }
	 }
 	 
 	 //批量审核操作
 	function passAll(){
 		$.ligerDialog.confirm('是否全部通过？', function (yes) {
 			if(yes){
 				 $.ajax({
 		 			 type:"POST",
 		 			 dataType:"json",
 		 			 url:"${pageContext.request.contextPath}//activityProductNew/batchAuditOperation.shtml?activityId=${activityId }&activityAreaId=${activityAreaId}&type=0",
 		 			 data:$("#dataForm").serialize(),
 		 			 success:function(data){
 		 				 if(data.statusCode =="200"){
 		 					$.ligerDialog.success(data.message);
 		 					window.location.reload();
 		 				 }else if(data.statusCode=="999"){
 		 					$.ligerDialog.error(data.message); 
 		 				 }
 		 			 },
 		 			 error:function(data){
 		 				$.ligerDialog.error('系统异常，请稍后再试');
 		 			 }
 		 		 });
 			}
 		});
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
						<select id="operateAuditStatus" name="operateAuditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="operateAuditStatus" items="${operateAuditStatusList }">
								<option value="${operateAuditStatus.statusValue }" <c:if test="${operateAuditStatus.statusValue == '1' }">selected</c:if> >
									${operateAuditStatus.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商品法务状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="lawAuditStatus" name="lawAuditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="lawAuditStatus" items="${lawAuditStatusList }">
								<option value="${lawAuditStatus.statusValue }" >
									${lawAuditStatus.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<c:if test="${statusFlag == '1' }">
					<div class="search-td">
						<div class="search-td-label">对接人：</div>
						<div class="search-td-condition">
							<select id="platContactStaffId" name="platContactStaffId" style="width: 135px;" >
								<c:if test="${isManagement == 1}">
									<option value="" selected="selected">全部商家</option>
								</c:if>
								<option value="${staffID }" selected="selected" >我自己</option>
								<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
									<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
								</c:forEach>
							</select>
						</div>
					</div> 
				</c:if>
				<div class="search-td">
					<div class="search-td-label"  >类目：</div>
					<div class="search-td-combobox-condition" >
						<c:if test="${isCwOrgStatus == 1 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" disabled="disabled" >
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
						<c:if test="${isCwOrgStatus == 0 }">
							<select id="productTypeId" name="productTypeId" style="width: 135px;" >
								<option value="">请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
							</select>
						</c:if>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productBrandName" name="productBrandName"  />
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" class="l-button" onClick="searchList();">搜索</div>
					<c:if test="${statusFlag != '1' }">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 52px;height: 25px;cursor: pointer;" value="全部通过" onClick="passAll();">
					</div>
					</c:if>
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
