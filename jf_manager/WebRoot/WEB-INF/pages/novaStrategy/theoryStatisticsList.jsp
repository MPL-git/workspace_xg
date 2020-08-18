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
	 });
	 
	//拉新记录
	function laNewRecord(id) {
		$.ligerDialog.open({
			height: $(window).height()-40,
			width:  $(window).width()-40,
			title: "拉新记录",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/theoryStatistics/laNewRecord.shtml?Id=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
		
	//分润记录
	function fenRunRecord(id) {
		$.ligerDialog.open({
			height: $(window).height()-40,
			width:  $(window).width()-40,
			title: "分润记录",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/theoryStatistics/fenRunRecord.shtml?Id=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});				
	}
	
 	var listConfig={
	      url:"/theoryStatistics/getTheoryStatisticsList.shtml",
	      listGrid:{ columns: [
	                        {display:'会员ID', name:'id', align:'center', isSort:false},
	                        {display:'会员昵称', name:'nick', align:'center', isSort:false},
	                        {display:'拉新用户', name:'invitationCount', align:'center', isSort:false},
	                        {display:'获得分润', name:'fenrunTotal', align:'center', isSort:false, render: function (rowdata, rowindex) {
	 		                	if(rowdata.fenrunTotal){
		 		                	return formatMoney(rowdata.fenrunTotal,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
	                        }},
	                        {display:'提现次数', name:'withdrawAcount', align:'center', isSort:false},
	                        {display:'现金余额(含提现中)', name:'cashBalance', align:'center', isSort:false, render: function (rowdata, rowindex) {
	 		                	if(rowdata.cashBalance){
		 		                	return formatMoney(rowdata.cashBalance,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
	                        }},
	                        {display:'提现中金额', name:'withdrawalAmount', align:'center', isSort:false, render: function (rowdata, rowindex) {
	 		                	if(rowdata.withdrawalAmount){
		 		                	return formatMoney(rowdata.withdrawalAmount,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
	                        }},
	                        {display:'已提现金额', name:'withdrawalsAmount', align:'center', isSort:false, render: function (rowdata, rowindex) {
	 		                	if(rowdata.withdrawalsAmount){
		 		                	return formatMoney(rowdata.withdrawalsAmount,2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
	                        }},
	                        {display:'首次加入新星计划时间', name:'firstJoinDate', align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.firstJoinDate != null && rowdata.firstJoinDate != '') {
									var createDate = new Date(rowdata.firstJoinDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
	                        {display:'最后拉新时间', name:'endLachineDate', align:'center', isSort:false, render: function(rowdata, rowindex) {
	                        	var html = [];
	                        	if (rowdata.endLachineDate != null && rowdata.endLachineDate != '') {
									var createDate = new Date(rowdata.endLachineDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
								
							}},
	                        {display:'当前新星计划状态', name:'currentStatus', align:'center', isSort:false}, 
	                        {display:'操作', name:'', align:'center', isSort:false, render: function(rowdata, rowindex) {
								var html = [];
									html.push("<a href=\"javascript:laNewRecord(" + rowdata.id + ");\">拉新记录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");	
									html.push("<a href=\"javascript:fenRunRecord(" + rowdata.id + ");\">分润记录</a>");
							    return html.join("");
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会员昵称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="nick" name="nick">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">当前新星计划状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="currentStatus" name="currentStatus" style="width: 135px;">
							<option value="">请选择</option>
							<option value="1">开通中</option>
							<option value="2">已过期</option>
							<%-- <c:forEach var="list" items="${sprChnls}">
									<option value="${list.statusValue}">${list.statusDesc}</option>
							</c:forEach> --%>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr">
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">首次加入新星计划日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="firstJoinDateBegin" name="firstJoinDateBegin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="firstJoinDateEnd" name="firstJoinDateEnd" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td" style="width: 40%;">
						<div class="search-td-label" style="float: left;width: 20%;">最后拉新日期</div>
						<div class="l-panel-search-item" >
							<input type="text" id="laNewDateBegin" name="laNewDateBegin" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
						<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >—</div>
						<div class="l-panel-search-item">
							<input type="text" id="laNewDateEnd" name="laNewDateEnd" class="dateEditor" placeholder="请选择" style="width: 135px;" />
						</div>
					</div>
					<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" class="l-button">搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
