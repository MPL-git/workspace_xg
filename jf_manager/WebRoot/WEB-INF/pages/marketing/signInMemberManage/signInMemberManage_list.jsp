<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
$(function(){
	
}); 

//拉黑
function addBlackList(memberId) { 
	 $.ligerDialog.open({
			height: 500,
			width: 600,
			title: "拉黑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/memberSignIn/addblackListManager.shtml?memberId="+memberId+"&newSignIn=1",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	});
}

function toMemberCumulativeSignInList(memberId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "累签记录",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/memberCumulativeSignIn/list.shtml?memberId=" + memberId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toMemberSignInSettingList(memberId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "签到记录",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/memberSignInSetting/list.shtml?memberId=" + memberId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
	  
     url:"/marketing/signInMemberManage/data.shtml",
     listGrid:{ columns: [
			            { display: '会员ID', name:'member_id'},
			            { display: '会员名称',name:'nick'},
			            { display: '最后签到时间',render: function (rowdata, rowindex) {
			            	if(rowdata.last_sign_in_time){
		                		var last_sign_in_time = new Date(rowdata.last_sign_in_time);
				            	return last_sign_in_time.format("yyyy-MM-dd hh:mm:ss");
		                	}
						}},
			            { display: '获得红包',name:'amount'},
			            { display: '提现次数',name:'withdraw_count'},
		                { display: '获得积分',name:'integral'},
		                { display: '获得优惠券（张）',name:'coupon_count'},
		                { display: '实物奖励（个）',name:'product_count'},
		                { display: '是否有消费',render: function (rowdata, rowindex) {
			            	if(rowdata.total_pay_amount>0){
		                		return "是";
		                	}else{
		                		return "否";
		                	}
						}},
		                { display: '操作',width:250,render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.black_id){
				                html.push("【已拉黑】");
		                	}else{
				                html.push("<a href='javascript:;' onclick='addBlackList("+rowdata.member_id+");'>【拉黑】</a>");
		                	}
		                	html.push('<a href="javascript:;" onclick="toMemberSignInSettingList('+rowdata.member_id+');">【签到记录】</a>');
		                	html.push('<a href="javascript:;" onclick="toMemberCumulativeSignInList('+rowdata.member_id+');">【累签记录】</a>');
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label" >会员ID：</div>
			<div class="search-td-condition">
				<input type="text" id="memberId" name="memberId">
			</div>
			</div>
		 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">最后签到时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="sign_in_time_begin" name="sign_in_time_begin" />
				<script type="text/javascript">
					$(function() {
						$("#sign_in_time_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="sign_in_time_end" name="sign_in_time_end" />
				<script type="text/javascript">
					$(function() {
						$("#sign_in_time_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			<div class="search-td">
					<div class="search-td-label">累计签到次数：</div>
					<div class="search-td-condition">
						<input type="text" id="signinCount" name="signinCount">
					</div>
			</div>
		</div>
		<div class="search-tr">
			<div class="search-td">
					<div class="search-td-label">领取累签到次数：</div>
					<div class="search-td-condition">
						<input type="text" id="cumulativesigninCount" name="cumulativesigninCount">
					</div>
			</div>
			<div class="search-td">
					<div class="search-td-label">签到年月：</div>
					<div class="search-td-condition">
						<input name="endYear" style="width:43%"> <span
							style="width:10%">--</span> <input name="endMonth"
							style="width:43%">
					</div>
			</div>
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>