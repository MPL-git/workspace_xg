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
	 
	 //设置推送
	 function signSendMsgCnfManager() {
		 $.ligerDialog.open({
				height: $(window).height() - 100,
				width: $(window).width() - 300,
				title: "推送列表",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/memberSignIn/signSendMsgCnfManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //拉黑
	 function addBlackList(memberId) { 
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "拉黑",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/memberSignIn/addblackListManager.shtml?memberId="+memberId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	
	 //签到记录
	 function showMemberAccountDtl(memberAccountId) {
		 $.ligerDialog.open({
				height: $(window).height() - 50,
				width: $(window).width() - 300,
				title: "签到记录",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/memberSignIn/memberAccountDtlManager.shtml?accId="+memberAccountId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 
 	 var listConfig={
	      url:"/memberSignIn/getMemberSignInList.shtml",
	      btnItems:[
		      { text: '推送设置', icon:'modify', id:'modify', dtype:'win', click:signSendMsgCnfManager }
	      ],
	      listGrid:{ columns: [
							{display:'会员ID',name:'memberId', align:'center', isSort:false, width:80},
							{display:'会员名称',name:'memberNick', align:'center', isSort:false, width:180},
							{display:'电话号码',name:'memberMobile', align:'center', isSort:false, width:180},
							{display:'剩余可提现金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var memberBalance = "0.00";
								if(rowdata.memberBalance){
									memberBalance = formatMoney(rowdata.memberBalance, 2);
	 		                	}
								return memberBalance;
			                }},
							{display:'已提现金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var twoAmountSum = "0.00";
								if(rowdata.twoAmountSum){
									twoAmountSum = formatMoney(rowdata.twoAmountSum, 2);
	 		                	}
								return twoAmountSum;
			                }},
			                {display:'最近签到时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if (rowdata.lastSignInDate != null && rowdata.lastSignInDate != '') {
									var lastSignInDate = new Date(rowdata.lastSignInDate);
									return lastSignInDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'最高连签天数',name:'mostContinuity', align:'center', isSort:false, width:100},
							{display:'累计签到金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var amountSum = "0.00";
								if(rowdata.amountSum){
									amountSum = formatMoney(rowdata.amountSum, 2);
	 		                	}
								return amountSum;
			                }},
							{display:'累计邀请好友金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	var fiveAmountSum = "0.00";
								if(rowdata.fiveAmountSum){
									fiveAmountSum = formatMoney(rowdata.fiveAmountSum, 2);
	 		                	}
								return fiveAmountSum;
			                }},
							{display:'是否有消费',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								if(rowdata.expenseCount > 0){
									return "是";
	 		                	}else {
									return "否";
	 		                	}
			                }},
							{display:'是否开启提醒',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
								if(rowdata.isSendWarn == '1'){
									return "是";
	 		                	}else {
									return "否";
	 		                	}
			                }},
			                {display:'提现次数',name:'twoAmountCount', align:'center', isSort:false, width:100},
			                {display:'推送次数',name:'sendCount', align:'center', isSort:false, width:100},
							{display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='addBlackList("+rowdata.memberId+");'>【拉黑】</a>");
								html.push("<a href='javascript:;' onclick='showMemberAccountDtl("+rowdata.memberAccountId+");'>【签到记录】</a>");
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >会员名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberNick" name="memberNick" >
					</div>
				</div>
			 	<div class="search-td">
					<div class="search-td-label" >未签到时间：</div>
					<div class="search-td-combobox-condition" >
						<select id="lastSignInDay" name="lastSignInDay" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="3">3天未签到</option>
							<option value="7">7天未签到</option>
							<option value="15">15天未签到</option>
							<option value="30">30天未签到</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">最后签到时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginLastSignInDate" name="beginLastSignInDate" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endLastSignInDate" name="endLastSignInDate" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >会员电话：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberMobile" name="memberMobile" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
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
