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
			url: "${pageContext.request.contextPath}/memberSignIn/addblackListManager.shtml?memberId="+memberId+"&supplementaryCard=3",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	});
}

function toAssistanceDtlList(memberSupplementarySignInId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "邀请记录",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/assistanceDtl/list.shtml?memberSupplementarySignInId=" +memberSupplementarySignInId ,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

 var listConfig={
	  
     url:"/marketing/memberSupplementarySignIn/data.shtml",
     listGrid:{ columns: [
			            { display: '会员ID', name:'memberId'},
			            { display: '邀请人数', name:'supplementaryInvitationCount'},
			            { display: '获得补签卡数量',name:'supplementaryCardCount'},
			            { display: '发起时间',render: function (rowdata, rowindex) {
		                	if(rowdata.beginTime){
		                		var beginTime = new Date(rowdata.beginTime);
		                		return beginTime.format("yyyy-MM-dd hh:mm");
		                	}
						}},
			            { display: '已邀请人数',name:'invitationCount'},
		                { display: '邀请状态',render: function (rowdata, rowindex) {
		                	if(rowdata.supplementaryInvitationCount <= rowdata.invitationCount){
		                		return "邀请成功";
		                	}else{
		                		var endTime = rowdata.endTime;
		                		var now = new Date();
		                		if(now<endTime){
		                			return "邀请中";
		                		}else{
		                			return "邀请失败";
		                		}
		                	}
						}},
		                { display: '当前补签卡数量', name:'supplementaryCard'},
		                { display: '操作',render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(!rowdata.blackListId){
			                	html.push("<a href='javascript:;' onclick='addBlackList("+rowdata.memberId+");'>【拉黑】</a>");
		                	}
		                	html.push('<a href="javascript:;" onclick="toAssistanceDtlList('+rowdata.id+');">【邀请记录】</a>');
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
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" >会员ID：</div>
			<div class="search-td-condition">
				<input type="text" id="memberId" name="memberId">
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