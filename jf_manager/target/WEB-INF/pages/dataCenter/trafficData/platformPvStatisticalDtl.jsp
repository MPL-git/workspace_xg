<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.table-title{font-size: 14px;color: #333333;font-weight: 600;}
.title-top{padding-top:10px;padding-bottom:10px;}
.marR10{margin-right:10px;}
.marT10{margin-top:10px;}
.marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
	
	var statisticsBeginDate = '${statisticsBeginDate }';
	var statisticsEndDate = '${statisticsEndDate }';

	function showActivity(activityId) {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
			title: "特卖转化详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/trafficData/beOnSale.shtml?status=1&activityId="+activityId
					+"&statisticsBeginDate="+statisticsBeginDate+"&statisticsEndDate="+statisticsEndDate,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	function showActivityArea(activityAreaId) {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
			title: "会场转化详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/trafficData/hall.shtml?status=1&activityAreaId="+activityAreaId
					+"&statisticsBeginDate="+statisticsBeginDate+"&statisticsEndDate="+statisticsEndDate,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	var listConfig={
	      url:"/platformPvStatistical/platformPvStatisticalDtlList.shtml",
	      listGrid:{ columns: [                    
						{display:'页面类型',name:'page_classify_desc', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
							var html = [];
							if(rowdata.page_type == '27' ) { //特卖
								html.push("<a href=\"javascript:showActivity(" + rowdata.value_id + ");\">" + rowdata.page_type_desc + "</a>");
							}else if(rowdata.page_type == '30' ) { //会场
								html.push("<a href=\"javascript:showActivityArea(" + rowdata.value_id + ");\">" + rowdata.page_type_desc + "</a>");
							}else {
								html.push(rowdata.page_type_desc);
							}
						    return html.join("");
			            }},
						{display:'访问用户数(会员)',name:'total_visitor_count', align:'center', isSort:false, width:160},
						{display:'访问用户数(非会员)',name:'total_visitor_count_tourist', align:'center', isSort:false, width:160},
						{display:'购买用户数',name:'total_pay_member_count', align:'center', isSort:false, width:160},
						{display:'访问次数(会员)',name:'total_pv_count', align:'center', isSort:false, width:160},
						{display:'访问次数(非会员)',name:'total_pv_count_tourist', align:'center', isSort:false, width:160},
						{display:'贡献下游',name:'member_pv_count', align:'center', isSort:false, width:160},
						{display:'平均停留时长（秒）',name:'stay_time_avg', align:'center', isSort:false, width:160},
						{display:'访问次数占比',name:'total_pv_rate', align:'center', isSort:false, width:160}
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
<html>
<body style="padding: 0px; overflow: hidden;">
	<form id="dataForm" runat="server" style="display: none;">
		<div class="search-pannel">
			<div class="search-tr" > 
				<input type="hidden" name="statisticsBeginDate" value="${statisticsBeginDate }" />
				<input type="hidden" name="statisticsEndDate" value="${statisticsEndDate }" />
				<input type="hidden" name="pageClassify" value="${pageClassify }" />
				<input type="hidden" name="totalPvCount" value="${totalPvCount }" />
				<input type="hidden" name="totalPvCountTourist" value="${totalPvCountTourist }" />
				<div class="search-td-search-btn" style="display: inline-flex;margin-right:140px;">
					<div id="search">搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
