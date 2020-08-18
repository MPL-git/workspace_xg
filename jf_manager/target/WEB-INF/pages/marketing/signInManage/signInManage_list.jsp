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

function deleteViolatePunishStandard(id) {
	if(confirm("确定删除吗？")){
		$.ajax({
			url : "${pageContext.request.contextPath}/violateOrder/deleteViolatePunishStandard.shtml",
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
					commUtil.alertError("删除失败，请稍后重试");
				}
				setTimeout(function(){
					location.reload();
					frameElement.dialog.close();
				},1000);
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}
}

 var listConfig={
	  
     url:"/marketing/signInManage/data.shtml",
     btnItems:[{ text: '新增签到', icon: 'add', dtype:'win',  click: itemclick, url: "/marketing/signInManage/toEdit.shtml", seqId:"", width: 600, height: 350}],
     listGrid:{ columns: [
			            { display: '年', name:'year'},
			            { display: '月',name:'month'},
			            { display: '状态',name:'statusDesc'},
			            { display: '最后操作人员',name:'staffName'},
		                { display: '最后修改时间',render: function (rowdata, rowindex) {
		                	if(rowdata.updateDate){
		                		var updateDate = new Date(rowdata.updateDate);
				            	return updateDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
						}},
		                { display: '签到操作', render: function (rowdata, rowindex) {
		                	if(rowdata.statusDesc == "未开始"){
								return '<a href="javascript:;" onclick="toSignInSettingList('+rowdata.id+')">设置</a>';		                		
		                	}else{
								return '<a href="javascript:;" onclick="toSignInSettingList('+rowdata.id+')">查看</a>';		                		
		                	}
						}},
		                { display: '累签操作', render: function (rowdata, rowindex) {
		                	if(rowdata.statusDesc == "未开始"){
								return '<a href="javascript:;" onclick="toCumulativeSignInSettingList('+rowdata.id+','+rowdata.year+','+rowdata.month+')">设置</a>';		                		
		                	}else{
								return '<a href="javascript:;" onclick="toCumulativeSignInSettingList('+rowdata.id+','+rowdata.year+','+rowdata.month+')">查看</a>';		                		
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
		
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" >年：</div>
			<div class="search-td-condition">
				<input type="text" id="year" name="year">
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >月：</div>
			<div class="search-td-condition">
				<input type="text" id="month" name="month">
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