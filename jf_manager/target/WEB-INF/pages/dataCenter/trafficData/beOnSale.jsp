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

	 $(function() {
	  $(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
	  });
	  
	   $("#searchbtn").bind('click',function(){
			if($('#createDateBegin').val() == '' || $('#createDateEnd').val() == ''){
				commUtil.alertError("请选择统计日期!");
				return;
			}
			$("#search").click();
	   });
	 
	 });
	 
 	var listConfig={
	      url:"/trafficData/beOnSaleList.shtml",
	      listGrid:{ columns: [
	           			 		{display:'特卖ID', name:'activityId', align:'center', isSort:false},
		                        {display:'特卖名称', name:'name', align:'center', isSort:false},
		                        {display:'一级类目', name:'productTypeName', align:'center', isSort:false},
		                        {display:'特卖状态', name:'', align:'center', isSort:false, render: function (rowdata, rowindex) {
									if(rowdata.preheatTime != null && rowdata.activityBeginTime != null
											&& new Date(rowdata.preheatTime) <= new Date() && new Date(rowdata.activityBeginTime) > new Date() ) {
										return "预热中";
									}else if(rowdata.activityBeginTime != null && rowdata.activityEndTime != null
											&& new Date(rowdata.activityBeginTime) <= new Date() && new Date(rowdata.activityEndTime) >= new Date() ) {
										return "活动中";
									}else {
										return "已结束";
									}
								}},
		                        {display:'首页位置', name:'homePagePosition', align:'center', isSort:false},
		                        {display:'分类位置', name:'classifyPagePosition', align:'center', isSort:false},
		                        {display:'首页曝光（次）', name:'homePageExposureCount', align:'center', isSort:false},
		                        {display:'分类曝光（次）', name:'classifyPageExposureCount', align:'center', isSort:false},
		                        {display:'总曝光（次）', name:'totalExposure', align:'center', isSort:false},
		                        {display:'访客数(会员)', name:'totalVisitorCount', align:'center', isSort:false},
		                        {display:'访客数(非会员)', name:'totalVisitorCountTourist', align:'center', isSort:false},
		                        {display:'浏览量(会员)', name:'totalPvCount', align:'center', isSort:false},
		                        {display:'浏览量(非会员)', name:'totalPvCountTourist', align:'center', isSort:false},
		                        {display:'支付金额（元）', name:'payAmount', align:'center', isSort:false},
		                        {display:'母订单数', name:'payCombineOrderCount', align:'center', isSort:false},
		                        {display:'转化率（%）', name:'inversionRate', align:'center', isSort:false, render: function (rowdata, rowindex) {
		                        	if(rowdata.inversionRate){
			 		                	return formatMoney(rowdata.inversionRate,2);
		 		                	}else{
		 		                		return "0.00";
		 		                	}
		                        }},
				  				{display:'曝光价值（元/次）', name:'', align:'exposureAmount', isSort:false, render: function (rowdata, rowindex) {
									if(rowdata.exposureAmount){
										return formatMoney(rowdata.exposureAmount,2);
									}else{
										return "0.00";
									}
								}}
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      } , 							
	      container:{
	        searchBtnName:"search",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<form id="dataForm" runat="server">
		<c:if test="${status == '1'}">
			<input type="hidden" id="createDateBegin" name="createDateBegin" value="${beginDate}">
			<input type="hidden" id="createDateEnd" name="createDateEnd" value="${endDate}">
			<input type="hidden" id="activityId" name="activityId" value="${activityId}">
		</c:if>
		<div class="search-pannel">
			<c:if test="${status == '1'}">
				<div class="search-tr"  >
					<div class="search-td" style="width:230px;">
						<div class="search-td-label"  >特卖名称</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="name" name="name">
						</div>
					</div>
					<div class="search-td" style="width:230px;">
						<div class="search-td-label">一级类目</div>
						<div class="search-td-combobox-condition" >
							<select id="productTypeName" name="productTypeName" style="width: 135px;">
								<option value="">请选择</option>
								<c:forEach var="productTypeItem" items="${productTypeList}">
									<option value="${productTypeItem.id}">${productTypeItem.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="search-td-search-btn" >
						<div id="searchbtn" class="l-button">搜索</div>
						<div id="search" style="display: none;">搜索</div>
					</div>
				</div>
			</c:if>
			<c:if test="${status != '1'}">
				<div class="search-tr"  >
					<div class="search-td">
						<div class="search-td-label"  >活动状态</div>
						<div class="search-td-combobox-condition" >
							<select id="activityStatus" name="activityStatus" style="width: 135px;">
								<option value="">全部</option>
								<option value="1">预热中</option>
								<option value="2">活动中</option>
								<option value="3">已结束</option>
							</select>
						</div>
					</div>
					<div class="search-td" id="temai">
						<div class="search-td-label">特卖ID</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="activityId" name="activityId" >
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label"  >特卖名称</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="name" name="name">
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label">一级类目</div>
						<div class="search-td-combobox-condition" >
							<select id="productTypeName" name="productTypeName" style="width: 135px;">
								<option value="">请选择</option>
									<c:forEach var="productTypeItem" items="${productTypeList}">
										<option value="${productTypeItem.id}">${productTypeItem.name}</option>
									</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="search-tr"  >
					<div class="search-td" id="temai">
						<div class="search-td-label">商家序号</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="mchtCode" name="mchtCode" >
						</div>
					</div>
					<div class="search-td">
						<div class="search-td-label"  >商家名称</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="mchtName" name="mchtName">
						</div>
					</div>
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">统计日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" style="width: 135px;" value="${beginDate}" placeholder="请选择"/>
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
						<div class="l-panel-search-item">
							<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" style="width: 135px;"  value="${endDate}" placeholder="请选择" />
						</div>
					</div>
					<div class="search-td-search-btn" >
						<div id="searchbtn" class="l-button">搜索</div>
						<div id="search" style="display: none;">搜索</div>
					</div>
				</div>
			</c:if>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
