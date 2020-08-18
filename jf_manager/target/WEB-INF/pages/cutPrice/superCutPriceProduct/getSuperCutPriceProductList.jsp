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
				 url : "${pageContext.request.contextPath}/superCutPriceProduct/updateSingleProductActivity.shtml",
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
	
	//砍价设置
	function cutPriceCnfManager(singleProductActivityId) {
		$.ligerDialog.open({
			height: $(window).height() - 400,
			width: $(window).width() - 1200,
			title: "邀请设置",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/superCutPriceProduct/addOrUpdateSuperCutPriceCnfManager.shtml?singleProductActivityId="+singleProductActivityId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	//审核
	function updateAuditStatusManager(singleProductActivityId) {
		$.ligerDialog.open({
			height: $(window).height() - 300,
			width: $(window).width() - 1100,
			title: "审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/superCutPriceProduct/updateAuditStatusManager.shtml?singleProductActivityId="+singleProductActivityId,
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
			 url : "${pageContext.request.contextPath}/superCutPriceProduct/updateStatus.shtml",
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
	      url:"/superCutPriceProduct/getSuperCutPriceProductList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'邀请ID',name:'id', align:'center', isSort:false, width:80},
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
	                        {display:'报名价格',name:'originalPrice', align:'center', isSort:false, width:80},
	                        {display:'吊牌价',name:'tagPriceMax', align:'center', isSort:false, width:80},
	                        {display:'最新库存数',name:'stockSum', align:'center', isSort:false, width:80},
	                        {display:'虚拟领取人数',name:'unrealityNum', align:'center', isSort:false, width:80},
	                        {display:'实际领取人数',name:'yqCutPriceOrderCount', align:'center', isSort:false, width:80},
	                        {display:'邀请人数',name:'superCutPriceOrderDtlCount', align:'center', isSort:false, width:80},
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
	                        {display:'操作',name:'', align:'center', isSort:false, width:200, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href=\"javascript:cutPriceCnfManager(" + rowdata.id + ");\">【邀请设置】</a>");
								if(rowdata.cutPriceCnfCreateName != null && rowdata.cutPriceCnfCreateName != '') {
									if(rowdata.auditStatus == '3' ) {
										if(rowdata.status == '0' ) {
											html.push("<a href=\"javascript:updateAuditStatusManager(" + rowdata.id + ");\">【审核】</a>");
											html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '1');\">【上架】</a>");	
										}else {
											html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '0');\">【下架】</a>");	
										}
									}else if(rowdata.auditStatus == '1' ) {
										html.push("<a href=\"javascript:updateAuditStatusManager(" + rowdata.id + ");\">【审核】</a>");
									}
								}
							    return html.join("");
							}},
	                        {display:'审核人员',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
	                        	if(rowdata.firstAuditName != null && rowdata.firstAuditName != '') 
	                        		return rowdata.firstAuditName;
							}},
	                        {display:'砍价设置人员',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
	                        	if(rowdata.cutPriceCnfCreateName != null && rowdata.cutPriceCnfCreateName != '') {
	                        		return rowdata.cutPriceCnfCreateName;
	                        	}
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
					<div class="search-td-label"  >砍价ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="singleProductActivityId" name="singleProductActivityId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >店铺名：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="shopName" name="shopName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商品名：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productName" name="productName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >审核人员：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="firstAuditName" name="firstAuditName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="auditStatus" name="auditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="0">等待审核</option>
							<option value="1">审核中</option>
							<option value="3">审核通过</option>
							<option value="4">审核驳回</option>
						</select>
				 	 </div>
				</div>
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
