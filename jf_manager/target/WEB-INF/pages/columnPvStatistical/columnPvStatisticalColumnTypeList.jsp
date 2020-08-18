<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
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
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150,
			cancelable : false
		});

		// 禁止分页
		$("#maingrid").ligerGrid({
			usePager:false
		});
		
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
	 
 	 var listConfig={
	      url:"/columnPvStatistical/columnPvStatisticalColumnTypeList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'栏目',name:'column_type', align:'center', isSort:false, width:120},
							{display:'访客数(会员)',name:'total_visitor_count', align:'center', isSort:false, width:120},
							{display:'访客数(非会员)',name:'total_visitor_count_tourist', align:'center', isSort:false, width:120},
							{display:'浏览量(会员)',name:'total_pv_count', align:'center', isSort:false, width:120},
							{display:'浏览量(非会员)',name:'total_pv_count_tourist', align:'center', isSort:false, width:120},
							{display:'加购件数',name:'shopping_cart_count', align:'center', isSort:false, width:120},
							{display:'加购转化',name:'shopping_cart_rate', align:'center', isSort:false, width:120},
							{display:'提交订单件数',name:'sub_product_count', align:'center', isSort:false, width:120},
							{display:'下单转化',name:'sub_product_rate', align:'center', isSort:false, width:120},
							{display:'付款件数',name:'pay_product_count', align:'center', isSort:false, width:120},
							{display:'付款转化',name:'pay_product_rate', align:'center', isSort:false, width:120},
							{display:'销售金额(实收)',name:'pay_amount_count', align:'center', isSort:false, width:120}
			         ],
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	 };

	 function searchFun() {
	 	if($("#statisticalDateStart").val() == '' || $("#statisticalDateEnd").val() == '' ) {
			commUtil.alertError("付款日期不能为空！");
		}else{
	 		$("#searchbtn").click();
		}
	 }
	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<%--<div id="toptoolbar"></div>--%>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="statisticalDateStart" name="statisticalDateStart" class="dateEditor" value="${statisticalDateStart}" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="statisticalDateEnd" name="statisticalDateEnd" class="dateEditor" value="${statisticalDateEnd}" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" style="display: inline-flex;">
					<input type="button" class="l-button" onclick="searchFun();" value="搜索">
					<div id="searchbtn" style="display: none;"></div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
