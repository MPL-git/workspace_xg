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
			format : "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 150
		});
		
	 });
	 
	 //拉黑
	 function addBlackList(memberId) { 
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "拉黑",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/cutPriceOrder/addblackListManager.shtml?memberId="+memberId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
 
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
	      url:"/cutPriceOrder/getCutPriceOrderDtlList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:80},
							{display:'会员名称',name:'memberNick', align:'center', isSort:false, width:160},
							{display:'砍价金额',name:'', align:'center', isSort:false, width:80, render:function(rowdata, rowindex) {
	 		                	var amount = "0.00";
								if(rowdata.amount){
									if(rowdata.tallyMode == '2') {
										amount = "<span style='color:red;'>"+formatMoney(rowdata.amount, 2)+"</span>";
									}else {
										amount = formatMoney(rowdata.amount, 2);
									}
	 		                	}
								return amount;
			                }},
							{display:'是否新用户',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								if(rowdata.isNewMember == '0'){
									return "否";
								}else if(rowdata.isNewMember == '1'){
									return "是";
								}
								return "";
			                }},
							{display:'是否消费',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								if(rowdata.xfCount == 0){
									return "否";
								}else {
									return "是";
								}
			                }},
			                {display:'今日参与砍价次数',name:'cutPriceOrderDtlCount', align:'center', isSort:false, width:120},
			                {display:'累计参与砍价次数',name:'ljkjCount', align:'center', isSort:false, width:120},
			                {display:'发起砍价次数',name:'fqkjCount', align:'center', isSort:false, width:100},
			                {display:'成功砍价次数',name:'cgkjCount', align:'center', isSort:false, width:100},
			                {display:'砍价时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
								if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'累计砍价金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var balance = "<span style='color:green;'>0.00</span>";
								if(rowdata.amountSum){
									balance = "<span style='color:green;'>"+formatMoney(rowdata.amountSum, 2)+"</span>";
	 		                	}
								return balance;
			                }},
			                {display:'操作',name:'', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='addBlackList("+rowdata.memberId+");'>【拉黑】</a>");
							    return html.join("");
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server" >
		<input type="hidden" id="cutPriceOrderId" name="cutPriceOrderId" value="${cutPriceOrderId }" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">砍价时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td" style="width: 60%;" >
					<div class="search-td-label" style="float: left;width: 13%;" >会员名称：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="memberNick" name="memberNick" >
					</div>
					<c:if test="${not empty returnDate }">
						<div class="l-panel-search-item">
							本次砍价历时<span style="color:red;font-weight: bold;" >${returnDate }</span>共砍<span style="color:red;font-weight: bold;" >${totalCount }</span>刀
						</div>
					</c:if>
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
