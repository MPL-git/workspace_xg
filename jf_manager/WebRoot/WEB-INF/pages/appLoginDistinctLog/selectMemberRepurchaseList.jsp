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
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
	<%-- 自定义JS --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
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
	      url:"/appLoginDistinctLog/selectMemberRepurchaseList.shtml",
	      listGrid:{ columns: [
					{display:'用户ID',name:'member_id', align:'center', isSort:false, width:120},
					{display:'用户昵称',name:'member_nick', align:'center', isSort:false, width:180},
					{display:'手机号',name:'member_mobile', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
						if(rowdata.member_mobile == null || rowdata.member_mobile == '' ) {
							return "";
						}else if(rowdata.member_mobile.length > 7){
							return rowdata.member_mobile.substring(0, 3)+"****"+rowdata.member_mobile.substring(7, 11);
						}
						return rowdata.member_mobile;
					}},
					{display:'性别',name:'sex_type_desc', align:'center', isSort:false, width:100},
					{display:'年龄',name:'member_age', align:'center', isSort:false, width:100},
					{display:'最近下单时间',name:'', align:'center', width:180, isSort:false, render:function(rowdata, rowindex) {
						if(rowdata.last_buy_time == null || rowdata.last_buy_time == '' ) {
							return "";
						}else{
							var orderDate = new Date(rowdata.last_buy_time);
							return orderDate.format("yyyy-MM-dd hh:mm:ss");
						}
					}},
				  	{display:'最近访问时间',name:'', align:'center', width:180, isSort:false, render:function(rowdata, rowindex) {
						if(rowdata.last_login_time == null || rowdata.last_login_time == '' ) {
							return "";
						}else{
							var loginDate = new Date(rowdata.last_login_time);
							return loginDate.format("yyyy-MM-dd hh:mm:ss");
						}
					}},
				  	{display:'注册时间',name:'', align:'center', width:180, isSort:false, render:function(rowdata, rowindex) {
						if(rowdata.create_date == null || rowdata.create_date == '' ) {
							return "";
						}else{
							var createDate = new Date(rowdata.create_date);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}
					}}
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
 
	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<%--<div id="toptoolbar"></div>--%>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<input type="hidden" name="dateStatus" value="${dateStatus}" />
			<input type="hidden" name="payDate" value="${payDate}" />
			<input type="hidden" name="sexType" value="${sexType}" />
			<input type="hidden" name="status" value="${status}" />
			<input type="hidden" name="resultNum" value="${resultNum}" />
			<input type="hidden" name="totalNum" value="${totalNum}" />
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >用户ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >用户昵称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberNick" name="memberNick" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">年龄：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="yearEnd" name="yearEnd" >
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="yearBegin" name="yearBegin" >
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">最近下单日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="orderDateBegin" name="orderDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="orderDateEnd" name="orderDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">最近访问日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="loginDateBegin" name="loginDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="loginDateEnd" name="loginDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >性别：</div>
					<div class="search-td-combobox-condition" >
						<select id="memberSexType" name="memberSexType" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">男</option>
							<option value="0">女</option>
							<option value="2">未知</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">注册日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
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
