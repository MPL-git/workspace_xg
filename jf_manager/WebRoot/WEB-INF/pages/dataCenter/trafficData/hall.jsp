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
				 commUtil.alertError("日期不能为空");
				 return;
			 }
			 $("#search").click();
		 });
		 
	 });
	 
	//查看详情
	function meetingDetail(activityAreaId,date,title) {
		$.ligerDialog.open({
			height: $(window).height()-40,
			width:  $(window).width()-300,
			title: "会场统计详情("+title+")",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/trafficData/meetingDetail.shtml?activityAreaId=" + activityAreaId+"&date="+date,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});				
	}
	
 	var listConfig={
	      url:"/trafficData/hallList.shtml",
	      listGrid:{ columns: [                    
	                        {display:'日期', name:'statisticalDate', align:'center', isSort:false},
	                        {display:'会场ID', name:'activityAreaId', align:'center', isSort:false},
	                        {display:'会场名称', name:'name', align:'center', isSort:false, render: function(rowdata, rowindex) {
								var html = [];
								var title = rowdata.activityAreaId+rowdata.name+"-"+rowdata.statisticalDate;
								html.push("<a href=\"javascript:meetingDetail(" + rowdata.activityAreaId + ",'"+rowdata.statisticalDate+"','"+title+"');\">"+rowdata.name+"</a>");
							    return html.join("");
							}},
	                        {display:'会场类型', name:'type', align:'center', isSort:false, render: function (rowdata, rowindex) {
	 		                	if(rowdata.type == "1"){
		 		                	return "品牌活动";
	 		                	}else if(rowdata.type == "2"){
	 		                		return "单品活动";
	 		                	}else if(rowdata.type == "3"){
	 		                		return "推广会场";
	 		                	}
	                        }},
	                        {display:'曝光（次）', name:'totalExposure', align:'center', isSort:false},
	                        {display:'访客数(会员)', name:'totalVisitorCount', align:'center', isSort:false},
	                        {display:'访客数(非会员)', name:'totalVisitorCountTourist', align:'center', isSort:false},
	                        {display:'浏览量(会员)', name:'totalPvCount', align:'center', isSort:false},
	                        {display:'浏览量(非会员)', name:'totalPvCountTourist', align:'center', isSort:false},
	                        {display:'支付金额', name:'payAmount', align:'center', isSort:false},
	                        {display:'母订单数', name:'payCombineOrderCount', align:'center', isSort:false},
	                        {display:'转化率（%）', name:'inversionRate', align:'center', isSort:false, render: function (rowdata, rowindex) {
	                        	if(rowdata.inversionRate){
		 		                	return formatMoney(rowdata.inversionRate,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
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
 
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<input type="hidden" id="pageSize" value="${pageSize}">
	<input type="hidden" id="pageNumber" value="${pageNumber}">
	<form id="dataForm" runat="server">
		<c:if test="${status == '1'}">
			<input type="hidden" id="createDateBegin" name="createDateBegin" value="${beginDate}">
			<input type="hidden" id="createDateEnd" name="createDateEnd" value="${endDate}">
			<input type="hidden" id="activityAreaId" name="activityAreaId" value="${activityAreaId}">
		</c:if>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td"  style="width:230px;">
					<div class="search-td-label">会场类型</div>
					<div class="search-td-combobox-condition" >
						<select id="type" name="type" style="width:135px;">
							<option value="">请选择</option>
							<option value="2">单品活动</option>
							<option value="1">品牌活动</option>
							<option value="3">推广会场</option>
						</select>
					</div>
				</div>
				<c:if test="${status != '1'}">
					<div class="search-td" style="width:230px;">
						<div class="search-td-label">会场ID</div>
						<div class="search-td-combobox-condition" >
							<input type="text" id="activityAreaId" name="activityAreaId" value="${activityAreaId}">
						</div>
					</div>
				</c:if>
				<div class="search-td" style="width:230px;">
					<div class="search-td-label">会场名称</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name">
					</div>
				 </div>
				 <c:if test="${status != '1'}">
					 <div class="search-td" style="width: 30%;">
						<div class="search-td-label" style="float: left;width: 20%;">统计日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" value="${beginDate}" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" value="${endDate}" placeholder="请选择" style="width: 135px;" />
						</div>
					 </div>
				 </c:if>
				 <div class="search-td-search-btn" style="display: inline-flex;margin-right:140px;">
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
