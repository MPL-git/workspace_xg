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
 function formatMoney(s, n)
 {
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
 
 	var statisticsBeginDate = '${beginDate }';
	var statisticsEndDate = '${endDate }';
 	var comparisonBeginDate = '${beginDate }';
	var comparisonEndDate = '${endDate }';
 

	 $(function() {
	  $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
	  });
	  
	  $("#searchbtn").bind('click',function(){
		if($('#createDateBegin').val() == '' || $('#createDateEnd').val() == ''){
			commUtil.alertError("日期不能为空");
			return;
		}
		statisticsBeginDate = $('#createDateBegin').val();
		statisticsEndDate = $('#createDateEnd').val();
	 	comparisonBeginDate = $('#createDateBegin').val();
		comparisonEndDate = $('#createDateEnd').val();
		$("#search").click();
	  });
	   
	 });
	
	//品牌
	function showMchtBand(mchtType, mchtCode, productBrandId ) {
		$.ligerDialog.open({
			 height: $(window).height() - 50,
			width: $(window).width() - 50,
			title: "商家品牌",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/productIndex.shtml?mchtType="+mchtType+"&mchtCode="+mchtCode+"&productBrandId="+productBrandId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
			});
		}
	
 	var listConfig={
	      url:"/trafficData/merchantTrafficList.shtml",
	      listGrid:{ columns: [
	                        {display:'日期', name:'statisticalDate', align:'center', isSort:false},
	                        {display:'商家序号', name:'mchtCode', align:'center', isSort:false},
	                        {display:'公司名称', name:'companyName', align:'center', isSort:false},
	                        {display:'店铺名称', name:'shopName', align:'center', isSort:false},
	                        { display: '主营类目', name:'productName', width: 120, align:'center', isSort:false},
	                        {display:'品牌', name:'mchtProductBrandName', align:'center', isSort:false, render: function (rowdata, rowindex) {
	                        	var mchtProductBrandName = rowdata.mchtProductBrandName;
			                	var html = [];
			                	if(mchtProductBrandName != null && mchtProductBrandName.length > 0){
			                		var mchtProductBrandNames = mchtProductBrandName.split(",");
									for (var i = 0; i < mchtProductBrandNames.length; i++) {
										var brandName = mchtProductBrandNames[i].substring(0, mchtProductBrandNames[i].lastIndexOf("）")+1);
										var productBrandId = mchtProductBrandNames[i].substring(mchtProductBrandNames[i].lastIndexOf("）")+1);
										if(i != 0) {
											html.push("<br/>");
										}
										if(productBrandId!=0){
											html.push("<a href=\"javascript:showMchtBand("+ rowdata.mchtType +", '"+ rowdata.mchtCode +"', "+ productBrandId +");\">"+ brandName +"</a>");
										}else{//无品牌
											html.push("无品牌");
										}
									}
			                	}
								return html.join("");
	                        }},
	                        {display:'访客数(会员)', name:'totalVisitorCount', align:'center', isSort:false},
				  			{display:'访客数(非会员)', name:'totalVisitorCountTourist', align:'center', isSort:false},
	                        {display:'浏览量(会员)', name:'totalPvCount', align:'center', isSort:false, render: function (rowdata, rowindex) {
								return rowdata.totalPvCount+"<br/>（PV："+rowdata.browseQuantity+"）";
	                        }},
				  			{display:'浏览量(非会员)', name:'totalPvCountTourist', align:'center', isSort:false, render: function (rowdata, rowindex) {
								return rowdata.totalPvCountTourist+"<br/>（PV："+rowdata.browseQuantityTourist+"）";
	                        }},
	                        {display:'支付金额', name:'payAmount', align:'center', isSort:false},
	                        {display:'支付母订单数', name:'payCombineOrderCount', align:'center', isSort:false},
	                        {display:'支付商品销量', name:'payProductCount', align:'center', isSort:false},
	                        {display:'支付买家数', name:'payMemberCount', align:'center', isSort:false, render: function (rowdata, rowindex) {
								return rowdata.payMemberCount+"<br/>（CR："+rowdata.paymentOfBuyers+"%）";
	                        }},
	                        {display:'操作', name:'', align:'center', isSort:false, render: function (rowdata, rowindex) {
	                        	var html = [];
								html.push("<a href=\"javascript:overview(" + rowdata.mchtId + ");\">概况总览</a></br>");
								html.push("<a href=\"javascript:flowKanban(" + rowdata.mchtId + ");\">流量看板</a></br>");
								html.push("<a href=\"javascript:commodityProfile(" + rowdata.mchtId + ");\">商品概况</a></br>");
						    	return html.join("");
	                        }}
			         ],
	                 showRownumber: true //不设置默认为 true
	      } , 							
	      container:{
	        searchBtnName:"search",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }   
	  };
 	
 	 //概况总览
	 function overview(mchtId) {
		 $.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 50,
				title: "概况总览",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtPvStatistical/mchtPvStatisticalSumUp.shtml?mchtId="+mchtId
						+"&statisticsBeginDate="+statisticsBeginDate+"&statisticsEndDate="+statisticsEndDate
						+"&comparisonBeginDate="+comparisonBeginDate+"&comparisonEndDate="+comparisonEndDate,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
 	 
 	 //流量看板
	 function flowKanban(mchtId) {
		 $.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 50,
				title: "流量看板",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtPvStatistical/mchtPvStatisticalShow.shtml?mchtId="+mchtId
						+"&statisticsBeginDate="+statisticsBeginDate+"&statisticsEndDate="+statisticsEndDate
						+"&comparisonBeginDate="+comparisonBeginDate+"&comparisonEndDate="+comparisonEndDate,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
 	 
 	 //商品概况
	 function commodityProfile(mchtId) {
		 $.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 50,
				title: "详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/mchtPvStatistical/mchtPvStatisticalProduct.shtml?mchtId="+mchtId
						+"&statisticsBeginDate="+statisticsBeginDate+"&statisticsEndDate="+statisticsEndDate
						+"&comparisonBeginDate="+comparisonBeginDate+"&comparisonEndDate="+comparisonEndDate,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<input type="hidden" id="pageSize" value="${pageSize}">
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">商家序号</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				 </div>
				 <div class="search-td" style="width:230px;">
					<div class="search-td-label">商家名称</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="shortName" name="shortName">
					</div>
				 </div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label" >主营类目</div>
					<div class="search-td-condition" >
						<select id="productTypeId" name="productTypeId" class="querysel">
							<option value="">请选择</option>
							<c:forEach var="productType" items="${productTypeList }">
								<option value="${productType.id }">${productType.name }</option>
							</c:forEach>
						</select>
					</div>
				 </div>
					<div class="search-td" style="width: 30%;">
						<div class="search-td-label" style="float: left;width: 20%;">统计日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor"  value="${beginDate}" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" value="${endDate}" placeholder="请选择" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td-search-btn" style="display: inline-flex;margin-right:200px;">
					<div id="searchbtn" class="l-button">搜索</div>
				    <div id="search" style="display: none;">搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
