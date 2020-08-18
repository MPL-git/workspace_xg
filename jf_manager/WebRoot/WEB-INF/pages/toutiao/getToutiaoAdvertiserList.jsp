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
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
	 });
	 
	 // 删除广告主
	 function delAdvertiser(id) {
		 $.ligerDialog.confirm('删除广告主时，<span style="color:red;">此广告主下的广告组与广告计划一并删除</span>，确认删除吗?', function (status){  
             if(status==true){
            	 $.ajax({
        			 type: 'post',
        			 url: '${pageContext.request.contextPath }/toutiaoAdvertiser/delAdvertiser.shtml',
        			 data: {id : id},
        			 dataType: 'json',
        			 success: function(data) {
        				 if(data == null || data.statusCode != 200){
        				     commUtil.alertError(data.message);
        				 }else {
        					 $("#searchbtn").click();
        				 }
        			 },
        			 error: function(e) {
        				 commUtil.alertError("系统异常请稍后再试");
        			 }
        		 });
             }
         });
	 }
	 
	 // 广告主更新
	 function updatedAdvertiser(id) {
		 $.ligerDialog.waitting('正在更新广告主信息中,请稍候...');
		 $.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/toutiaoAdvertiser/updatedAdvertiser.shtml',
			 data: {id : id},
			 dataType: 'json',
			 success: function(data) {
				 $.ligerDialog.closeWaitting();
				 if(data == null || data.statusCode != 200){
				     commUtil.alertError(data.message);
				 }else {
					 $("#searchbtn").click();
				 }
			 },
			 error: function(e) {
				 $.ligerDialog.closeWaitting();
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }
	 
	 // 广告组更新
	 function updatedCampaignList(id) {
		 $.ligerDialog.waitting('正在更新广告组信息中,请稍候...');
		 $.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/toutiaoAdvertiser/updatedCampaignList.shtml',
			 data: {id : id},
			 dataType: 'json',
			 success: function(data) {
				 $.ligerDialog.closeWaitting();
				 if(data == null || data.statusCode != 200){
				     commUtil.alertError(data.message);
				 }else {
					 $("#searchbtn").click();
				 }
			 },
			 error: function(e) {
				 $.ligerDialog.closeWaitting();
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }
	 
	 // 广告计划更新
	 function updateAdList(id) {
		 $.ligerDialog.waitting('正在更新广告计划信息中,请稍候...');
		 $.ajax({
			 type: 'post',
			 url: '${pageContext.request.contextPath }/toutiaoAdvertiser/updateAdList.shtml',
			 data: {id : id},
			 dataType: 'json',
			 success: function(data) {
				 $.ligerDialog.closeWaitting();
				 if(data == null || data.statusCode != 200){
				     commUtil.alertError(data.message);
				 }else {
					 $("#searchbtn").click();
				 }
			 },
			 error: function(e) {
				 $.ligerDialog.closeWaitting();
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	 }
	 
 	 var listConfig={
	      url:"/toutiaoAdvertiser/getToutiaoAdvertiserList.shtml",
	      btnItems:[{ text: '添加广告主', icon: 'add', dtype:'win',  click: itemclick, url:'/toutiaoAdvertiser/createToutiaoAdvertiserManager.shtml', seqId:"", width : 500, height:400}],
	      listGrid:{ columns: [
							{display:'广告主ID',name:'advertiserId', align:'center', isSort:false, width:120},
							{display:'账户名称',name:'name', align:'center', isSort:false, width:120},
							{display:'联系人',name:'contacter', align:'center', isSort:false, width:100},
							{display:'角色',name:'roleDesc', align:'center', isSort:false, width:100},
							{display:'状态',name:'statusDesc', align:'center', isSort:false, width:100},
							{display:'审核拒绝原因',name:'reason', align:'center', isSort:false, width:200},
							{display:'广告主上次更新时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata,rowindex){
								var html = [];
								if(rowdata.updateDate != null && rowdata.updateDate != "" ){
									var updateDate = new Date(rowdata.updateDate);
									html.push(updateDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'广告主更新',name:'', align:'center', isSort:false, width:100, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:updatedAdvertiser("+ rowdata.id +");\">【广告主更新】</a>");
								return html.join("");
							}},
							{display:'广告组上次更新时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata,rowindex){
								var html = [];
								if(rowdata.campaignUpdateDate != null && rowdata.campaignUpdateDate != "" ){
									var campaignUpdateDate = new Date(rowdata.campaignUpdateDate);
									html.push(campaignUpdateDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'广告组更新',name:'', align:'center', isSort:false, width:100, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:updatedCampaignList("+ rowdata.id +");\">【广告组更新】</a>");
								return html.join("");
							}},
							{display:'广告计划上次更新时间',name:'', align:'center', isSort:false, width:160, render:function(rowdata,rowindex){
								var html = [];
								if(rowdata.adUpdateDate != null && rowdata.adUpdateDate != "" ){
									var adUpdateDate = new Date(rowdata.adUpdateDate);
									html.push(adUpdateDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
							}},
							{display:'广告计划更新',name:'', align:'center', isSort:false, width:120, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:updateAdList(" + rowdata.id + ");\">【广告计划更新】</a>");
								return html.join("");
							}},
							{display:'操作',name:'', align:'center', isSort:false, width:80, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:delAdvertiser(" + rowdata.id + ");\">【删除】</a>");
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >广告主体：</div>
					<div class="search-td-combobox-condition" >
						<select id="tokenId" name="tokenId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${toutiaoTokenInfoList }" var="toutiaoTokenInfo">
								<option value="${toutiaoTokenInfo.id }">${toutiaoTokenInfo.mainName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >广告主ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="advertiserId" name="advertiserId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >账户名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="advertiserName" name="advertiserName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >角色：</div>
					<div class="search-td-combobox-condition" >
						<select id="role" name="role" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${roleList }" var="role">
								<option value="${role.statusValue }">${role.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${statusList }" var="status">
								<option value="${status.statusValue }">${status.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
