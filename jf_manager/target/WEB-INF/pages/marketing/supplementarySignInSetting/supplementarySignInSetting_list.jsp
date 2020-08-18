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

function toSignInSettingList(signInManageId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "签到设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/signInSetting/list.shtml?signInManageId=" + signInManageId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toCumulativeSignInSettingList(signInManageId,year,month) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: year+"-"+month+"累签设置",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/cumulativeSignInSetting/list.shtml?signInManageId=" + signInManageId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function changeStatus(id,status) {
	var title="";
	if(status == 0){
		title = "确定要启用吗？";
	}else{
		title = "确定要关闭吗？";
	}
	if(confirm(title)){
		$.ajax({
			url : "${pageContext.request.contextPath}/marketing/supplementarySignInSetting/changeStatus.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess(data.returnMsg);
				}else{
					commUtil.alertError("操作失败，请稍后重试");
				}
				setTimeout(function(){
					location.reload();
				},1000);
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
}

 var listConfig={
	  
     url:"/marketing/supplementarySignInSetting/data.shtml",
     btnItems:[{ text: '新增补签卡设置', icon: 'add', dtype:'win',  click: itemclick, url: "/marketing/supplementarySignInSetting/toEdit.shtml", seqId:"", width: 600, height: 350}],
     listGrid:{ columns: [
			            { display: '邀请人数', name:'invitationCount'},
			            { display: '获得补签卡数量',name:'supplementaryCardCount'},
			            { display: '状态',render: function (rowdata, rowindex) {
		                	if(rowdata.status == 0){
		                		return "关闭";
		                	}else{
		                		return "启用";
		                	}
						}},
			            { display: '最后操作人员',name:'staffName'},
		                { display: '最后修改时间',render: function (rowdata, rowindex) {
		                	if(rowdata.updateDate){
		                		var updateDate = new Date(rowdata.updateDate);
				            	return updateDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
						}},
		                { display: '操作', render: function (rowdata, rowindex) {
		                	if(rowdata.status == 0){
								return '<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.status+')">启用</a>';		                		
		                	}else{
								return '<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.status+')">关闭</a>';		                		
		                	}
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
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>