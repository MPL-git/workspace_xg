<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">

function searchbtnFunction() {
	  var Verification=true;
      var memberId = $("[name='memberId']").val();
	  /* var nick = $("[name='nick']").val(); */
	  var mchtCode=$("[name='mchtCode']").val();
	  var activityId=$("[name='activityId']").val();
	  var mobile=$("[name='mobile']").val();
	  var updateDate_begin = $("#updateDate_begin").val();
	  var updateDate_end = $("#updateDate_end").val();
	  var startDay = new Date(updateDate_begin);
	  var endDay = new Date(updateDate_end);
	  var diffDays = endDay - startDay;
	  var time = 60*24*60*60*1000;
	   if ($.trim(memberId)=='' && $.trim(mchtCode)=='' && $.trim(mobile)=='' && $.trim(activityId)=='') {
		   commUtil.alertError("会员ID、商家序号、手机号码、特卖ID至少输入1个搜索条件！");
		   return Verification=false;
	   }
	   if(diffDays > time){
			commUtil.alertError("浏览日期范围不能超过60天,请重新选择!");
			$("[name='updateDate_begin']").ligerDateEditor().setValue("");
			$("[name='updateDate_end']").ligerDateEditor().setValue("");
			return Verification=false;
	   }
	   if (Verification==true) {
          listModel.ligerGrid.url = '${pageContext.request.contextPath}/member/footprint/specialSalelistdatalist.shtml';
          listModel.initdataPage();		 
		
	   }
			
 } 

//查看商品
function showActivityProduct(statusPage, activityId) {
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: "查看商品",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activityProductNew/activityProductManager.shtml?statusPage="+statusPage+"&activityId="+activityId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

//查看活动、排期审核活动
function cooAuditActivity(activityId, statusPage, statusAudit) {
	var titleStr ;
	if(statusPage == '1') {
		titleStr = "查看活动";
	}else if(statusPage == '2') {
		titleStr = "活动排期审核";
	} 
	$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 400,
		title: titleStr,
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activityNew/showOrAuditActivity.shtml?activityId="+activityId+"&statusPage="+statusPage+"&statusAudit="+statusAudit,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

	  var listConfig={
		      listGrid:{ columns: [
			                    { display: '足迹ID', name: 'id'},
			                    { display: '会员ID', name: 'memberId'},
				                { display: '会员昵称',  name: 'nick', render: function(rowdata, rowindex) {
				                	if (rowdata.nick==""){
		    	  						return "醒购会员";
		    	  					}else{
		    	  						return rowdata.nick;
		    	  					}
				                }},
				                { display: '特卖ID',  name: 'activityId'},
				                { display: '商家序号',  name: 'activityMchtCode'},
				                { display: '店铺名称',  name: 'activityMchtShopName'},
				                { display: '一级类目',  name: 'activityProductTypename'},
				                { display: '品牌',  name: 'activityProductBrandname'}, 
				                { display: '浏览特卖名称',  name: 'activityName'},
				                { display: '利益点',  name: 'benefitPoint'},
				                { display: '促销方式',  name: 'preferentialTypeDesc'},
				                { display: '通过商品',  name: 'sumCooPass'},
				                {display:'活动状态',name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
									var html = [];
									if(rowdata.activityStatus != '6') {
									   if (rowdata.activityStatus=='1') {
										   html.push("待提报");
									   }
									   if (rowdata.activityStatus=='2') {
										   html.push("待审核");
									   }
									   if (rowdata.activityStatus=='3') {
										   html.push("审核中"); 
									   }
									   if (rowdata.activityStatus=='4') {
										   html.push("驳回");
									   }
									   if (rowdata.activityStatus=='5') {
										   html.push("暂停");
									   }
										
									}else if(rowdata.activityStatus == '6') {
										if(new Date(rowdata.preheatTime) > new Date()) {
											html.push("待开始");
										}else if(new Date(rowdata.preheatTime) <= new Date() && new Date(rowdata.activityBeginTime) > new Date()) {
											html.push("预热中");
										}else if(new Date(rowdata.activityBeginTime) <= new Date() && new Date(rowdata.activityEndTime) >= new Date()) {
											html.push("活动中");
										}else if(new Date(rowdata.activityEndTime) < new Date()) {
											html.push("已结束");
										}
									}
								    return html.join("");
								}},
				                { display: '访问时间', name: 'updateDate', render: function(rowdata, rowindex) {
		    	  					var date=new Date(rowdata.updateDate);
		    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);
		      					}},
		      					{display:'操作',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
								 var html = [];
								 html.push("<a href=\"javascript:cooAuditActivity(" + rowdata.activityId + ", '1', '2');\">【查看活动】</a>");
									html.push("<a href=\"javascript:showActivityProduct('', " + rowdata.activityId + ");\">【查看商品】</a>");
									 return html.join("");
								}},

				               ],   
		                 showCheckbox : false,  //不设置默认为 true
		                 showRownumber:true, dataAction: 'server' 
		      } ,     						
		     container:{
		        toolBarName:"toptoolbar",
		        searchBtnName:"searchbtn",
		        fromName:"dataForm",
		        listGridName:"maingrid"
		      } 
		       
		  };
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
	<div class="search-pannel">
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label">会员ID：</div>
			<div class="search-td-condition" >
				<input type="text" id ="memberId" name="memberId" >
			</div>
			</div>
			 	
			<div class="search-td">
			<div class="search-td-label">会员昵称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "nick" name="nick" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label">商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mchtCode" name="mchtCode" >
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label">手机号码：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mobile" name="mobile" >
			</div>
			</div>
					
		</div>
		
		<div class="search-tr"  >
		<div class="search-td">
			<div class="search-td-label" style="float:left;">浏览日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="updateDate_begin" name="updateDate_begin" value="${updateDate_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#updateDate_begin").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;至：</div>
			</div>
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="updateDate_end" name="updateDate_end" value="${updateDate_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#updateDate_end").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label">特卖ID：</div>
			<div class="search-td-condition" >
				<input type="text" id ="activityId" name="activityId" >
			</div>
			</div>
		    <div class="search-td-search-btn">		  
				 <div class="l-button" onclick="searchbtnFunction();" >搜索</div>						
			</div>
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</body>


