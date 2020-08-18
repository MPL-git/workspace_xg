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
	 
 	var listConfig={
	      url:"/singleProductActivity/getSingleProductActivityList.shtml?flag=4",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'单品活动ID',name:'id', align:'center', isSort:false, width:80},
	                        {display:'报名时间',name:'createDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.createDate == null || rowdata.createDate == "" || rowdata.createDate == undefined) {
									return "";
								}else{
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm");
								}
	                        }},
	                        {display:'活动类型',name:'typeDesc', align:'center', isSort:false, width:80},
	                        {display:'商品ID',name:'code', align:'center', isSort:false, width:180},
	                        {display:'主图',name:'productPic', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	return "<div style='padding:3px;'><img style='width:50px;height:50px;' src='${pageContext.request.contextPath}/file_servelt"+rowdata.productPic+"'></div>";
	                        }},
	                        {display:'品牌 / 货号',name:'brandName', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	return (rowdata.brandName==null?'':rowdata.brandName) + "<br/>" + (rowdata.artNo==null?'':rowdata.artNo);
	                        }},
	                        {display:'店铺名 / 商品名',name:'shopName', align:'center', isSort:false, width:260, render:function(rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.mchtGradeDesc) {
				                    html.push("<span style='color:red;margin-right:5px;'>"+rowdata.mchtGradeDesc+"</span>"); 
 	                        	}
	                        	html.push((rowdata.shopName==null?'':rowdata.shopName) 
	                        			+ "<br/><a href=\"javascript:viewProduct(" + rowdata.productId + ");\">"
	                        			+ (rowdata.productName==null?'':rowdata.productName) + "</a>");
	                        	return html.join("");
	                        }},
	                        {display:'报名价格',name:'originalPrice', align:'center', isSort:false, width:80},
	                        {display:'吊牌价',name:'tagPriceMin', align:'center', isSort:false, width:80},
	                        {display:'折扣',name:'discount', align:'center', isSort:false, width:80},
	                        {display:'商家最低活动价',name:'activityPriceMin', align:'center', isSort:false, width:120},
	                        {display:'活动库存',name:'stockSum', align:'center', isSort:false, width:80},
	                        {display:'期望日期',name:'expectedDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.expectedDate == null || rowdata.expectedDate == "" || rowdata.expectedDate == undefined) {
									return "";
								}else{
									var expectedDate = new Date(rowdata.expectedDate);
									return expectedDate.format("yyyy-MM-dd");
								}
	                        }},
	                        {display:'审核状态',name:'auditStatus', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	if(rowdata.auditStatus == null || rowdata.auditStatus == "" || rowdata.auditStatus == undefined) {
									return "";
								}else{
									if(rowdata.auditStatus == '3') 
										return "排期通过";
									if(rowdata.auditStatus == '5') 
										return "<span style='color:red;'>复审驳回</span>";
								}
	                        }},
	                        {display:'是否关闭',name:'isClose', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	                        	if(rowdata.isClose == '0') {
									return "否";
								}else{
									return "是";
								}
	                        }},
	                        {display:'活动开始时间',name:'beginTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.beginTime == null || rowdata.beginTime == "" || rowdata.beginTime == undefined) {
									return "";
								}else{
									var beginTime = new Date(rowdata.beginTime);
									return beginTime.format("yyyy-MM-dd hh:mm");
								}
	                        }},
	                        {display:'活动结束时间',name:'endTime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.endTime == null || rowdata.endTime == "" || rowdata.endTime == undefined) {
									return "";
								}else{
									var endTime = new Date(rowdata.endTime);
									return endTime.format("yyyy-MM-dd hh:mm");
								}
	                        }},
	                        {display:'初审专员',name:'firstAuditName', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
	                        	if(rowdata.firstAuditName != null && rowdata.firstAuditName != '') 
	                        		return rowdata.firstAuditName;
	                        	else
	                        		return "";
							}},
	                        {display:'排期专员',name:'scheduleAuditName', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
	                        	if(rowdata.scheduleAuditName != null && rowdata.scheduleAuditName != '') 
	                        		return rowdata.scheduleAuditName;
	                        	else
	                        		return "";
							}},
	                        {display:'复审专员',name:'againAuditName', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
	                        	if(rowdata.againAuditName != null && rowdata.againAuditName != '') 
	                        		return rowdata.againAuditName;
	                        	else
	                        		return "";
							}}
			         ], 
	                 showCheckbox : true,  //不设置默认为 true
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >活动类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="type" name="type" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="type" items="${typeList }">
								<option value="${type.statusValue }">
									${type.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="code" name="code" >
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
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >店铺名：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="shopName" name="shopName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >期望日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="expectedDate" name="expectedDate" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动开始日期：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" class="dateEditor" id="beginTime" name="beginTime" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="auditStatus" name="auditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="auditStatus" items="${auditStatusList }">
								<option value="${auditStatus.statusValue }">
									${auditStatus.statusDesc }
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
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
