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
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
	 });
	 
	//商品信息
	function viewProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1200,
			title: "商品信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	 
	//排序
	function updateArtNo(id) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNo = $("#seqNo" + id).val();
		var flag = seqNo.match(/^[1-9]\d*$/);
		if(seqNo != '' && flag != null) {
			$.ajax({
				 type : 'POST',
				 url : "${pageContext.request.contextPath}/cutPriceProduct/updateSingleProductActivity.shtml",
				 data : {id : id, seqNo : seqNo},
				 dataType : 'json',
				 success : function(data){
					 if(data == null || data.statusCode != 200)
						 commUtil.alertError(data.message);
					 else{
						 $("#seqNo" + id).parent().append("<span style='color:#009999;'>更改成功</span>");
						 $("#seqNo" + id).attr("seqNo", seqNo);
					 }
				 },
				 error : function(e) {
					 commUtil.alertError("系统异常请稍后再试");
				 }
			 });
		}else{
			$("#seqNo" + id).val($("#seqNo" + id).attr("seqNo"));
			$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}
	
	//审核
	function updateAuditStatusManager(singleProductActivityId) {
		$.ligerDialog.open({
			height: $(window).height() - 300,
			width: $(window).width() - 1100,
			title: "审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/assistCutPriceProduct/updateAuditStatusManager.shtml?singleProductActivityId="+singleProductActivityId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//上架或下架
	function updateStatus(singleProductActivityId, status) {
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/cutPriceProduct/updateStatus.shtml",
			 data : {id : singleProductActivityId, status : status},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.statusCode != 200)
					 commUtil.alertError(json.message);
				 else{
					 $("#searchbtn").click();
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	}
	 
 	var listConfig={
	      url:"/assistCutPriceProduct/getAssistCutPriceProductList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'排序值',name:'seqNo', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
	                        	return "<input type='text' style='width:70px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >";
	                        }},
							{display:'主图',name:'productPic', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
								return "<div style='padding:3px;'><img style='width:50px;height:50px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.productPic+"'></div>";
							}},
							{display:'品牌 / 货号/ 商品ID',name:'brandName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	return (rowdata.brandName==null?'':rowdata.brandName)
	                        		   + "<br/>" + 
	                        		   (rowdata.artNo==null?'':rowdata.artNo)
	                        		   + "<br/>" + 
	                        		   (rowdata.code==null?'':rowdata.code);
	                        }},
	                        {display:'店铺名 / 商品名',name:'shopName', align:'center', isSort:false, width:260, render:function(rowdata, rowindex) {
	                        	return (rowdata.shopName==null?'':rowdata.shopName) 
	                        			+ "<br/><a href=\"javascript:viewProduct(" + rowdata.productId + ");\">"
	                        			+ (rowdata.productName==null?'':rowdata.productName) + "</a>";
	                        }},
	                        {display:'吊牌价',name:'tagPriceMax', align:'center', isSort:false, width:80},
	                        {display:'活动价',name:'activityPrice', align:'center', isSort:false, width:80},
	                        {display:'助力一人立减',name:'fixedAmount', align:'center', isSort:false, width:80},
	                        {display:'上限人数',name:'maxInviteTimes', align:'center', isSort:false, width:80},
	                        {display:'最低成交价',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	return (rowdata.activityPrice - (rowdata.fixedAmount * rowdata.maxInviteTimes)).toFixed(2);
                    		}},
                    		{display:'近三个月最低活动价',name:'productlogminsalePrice', align:'center', isSort:false, width:150},
	                        {display:'最新库存数',name:'stockSum', align:'center', isSort:false, width:80},
	                        {display:'状态',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								if(rowdata.auditStatus == '0' ) {
									return "等待审核";
								}else if(rowdata.auditStatus == '1' ) {
									return "审核中";
								}else if(rowdata.auditStatus == '3' ) {
									return "审核通过";
								}else if(rowdata.auditStatus == '4' ) {
									return "审核驳回";
								}
							    return "";
							}},
							{display:'购买数量',name:'singleProductSum', align:'center', isSort:false, width:80},
	                        {display:'成交笔数',name:'singleProductCount', align:'center', isSort:false, width:80},
	                        {display:'操作',name:'', align:'center', isSort:false, width:200, render: function(rowdata, rowindex) {
								var html = [];
									if(rowdata.auditStatus != '4' && rowdata.auditStatus != '5' ) {
										if(rowdata.auditStatus == '3' ) {
											if(rowdata.status == '0' ) {
												html.push("<a href=\"javascript:updateAuditStatusManager(" + rowdata.id + ");\">【审核】</a>");
												html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '1');\">【上架】</a>");	
											}else {
												html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '0');\">【下架】</a>");	
											}
										}else {
											html.push("<a href=\"javascript:updateAuditStatusManager(" + rowdata.id + ");\">【审核】</a>");
										}
									}
							    return html.join("");
							}},
	                        {display:'审核人员',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
	                        	if(rowdata.firstAuditName != null && rowdata.firstAuditName != '') 
	                        		return rowdata.firstAuditName;
							}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >店铺名：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="shopName" name="shopName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="brandName" name="brandName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >货号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="artNo" name="artNo" >
					</div>
				</div>
			</div>
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode" >
					</div>
				</div>
				<div class="search-td">
			 	<div class="search-td-label" >类目：</div>
			 	<div class="search-td-condition" >
				<select id="productTypes" name="productTypes" class="querysel" style="width: 135px;" >
					<option value="">全部</option>
					<c:forEach var="lisProductTypes" items="${lisProductTypes}">
					   <option value="${lisProductTypes.id}">${lisProductTypes.name}</option>
				   </c:forEach>					
				</select>
				</div>
		 	  </div>
		 	  <div class="search-td">
			 	<div class="search-td-label" >审核状态：</div>
			 	<div class="search-td-condition" >
				<select id="auditStatus" name="auditStatus" class="querysel" style="width: 135px;" >
					<option value="">全部</option>
					<option value="0">等待审核</option>
					<option value="1">审核中</option>
					<option value="3">审核通过</option>
					<option value="4">审核驳回</option>
				</select>
				</div>
		 	  </div>
		 	  <div class="search-td">
			 	<div class="search-td-label" >上架状态：</div>
			 	<div class="search-td-condition" >
				<select id="status" name="status" class="querysel" style="width: 135px;" >
					<option value="">全部</option>
					<option value="0">下架</option>
					<option value="1">上架</option>
				</select>
				</div>
		 	  </div>
			</div>
			<div class="search-tr"  > 
		 	  <div class="search-td">
			 	<div class="search-td-label" >按排序值排序</div>
			 	<div class="search-td-condition" style="text-align: left;width: 5%;">
					<input type="checkbox" value="1" name="seqNo" id="seqNo"  checked="checked">
				</div>
			 </div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
