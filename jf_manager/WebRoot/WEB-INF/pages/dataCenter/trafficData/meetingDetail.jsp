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
    for(i == 0; i < l.length; i ++ )
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
	 });
	
 	var listConfig={
	      url:"/trafficData/meetingDetailList.shtml",
	      listGrid:{ columns: [                    
	                        {display:'日期', name:'statistical_date', align:'center', isSort:false},
	                        {display:'商品/特卖ID', name:'product_activity_id', align:'center', isSort:false},
	                        {display:'商品/特卖名称', name:'product_activity_name', align:'center', isSort:false},
	                        {display:'曝光（次）', name:'exposure_count', align:'center', isSort:false},
	                        {display:'访客数(会员)', name:'total_visitor_count', align:'center', isSort:false},
	                        {display:'访客数(非会员)', name:'total_visitor_count_tourist', align:'center', isSort:false},
	                        {display:'浏览量(会员)', name:'total_pv_count', align:'center', isSort:false},
	                        {display:'浏览量(非会员)', name:'total_pv_count_tourist', align:'center', isSort:false},
	                        {display:'支付金额（元）', name:'pay_amount', align:'center', isSort:false},
	                        {display:'母订单数', name:'pay_combine_order_count', align:'center', isSort:false},
	                        {display:'转化率（%）', name:'total_visitor_count_rate', align:'center', isSort:false}
			         ], 
	                 showRownumber: true //不设置默认为 true
	      } , 							
	      container:{
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }   
	  };

 	function updateTypeFun(type) {
 		if(type == '1') {
 			$(".product-activity-id").html("商品ID");
 			$(".product-activity-name").html("商品名称");
		}else {
			$(".product-activity-id").html("特卖ID");
			$(".product-activity-name").html("特卖名称");
		}
	}

</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<form id="dataForm" runat="server">
	<input type="hidden" id="activityAreaId" name="activityAreaId" value="${activityAreaId}">
	<input type="hidden" id="date" name="date" value="${date}">
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="width:230px;">
					<div class="search-td-label product-activity-id">类型</div>
					<div class="search-td-combobox-condition" >
						<select id="type" name="type" style="width: 135px;" onchange="updateTypeFun(this.value);">
							<option value="1">商品</option>
							<option value="2">特卖</option>
						</select>
					</div>
				</div>
			 	<div class="search-td" style="width:230px;">
					<div class="search-td-label product-activity-id">
						<c:if test="${type == '1'}">商品ID</c:if>
						<c:if test="${type == '2'}">特卖ID</c:if>
					</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productActivityId" name="productActivityId">
					</div>
				</div>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label product-activity-name">
						<c:if test="${type == '1'}">商品名称</c:if>
						<c:if test="${type == '2'}">特卖名称</c:if>
					</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productActivityName" name="productActivityName">
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
