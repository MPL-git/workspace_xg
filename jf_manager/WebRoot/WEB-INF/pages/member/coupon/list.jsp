<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript">

$(function(){
	$("#search").bind('click',function(){
	    var memberId = $("[name='memberId']").val();
	    var activityareaId=$("[name='activityareaId']").val();
	    if ($.trim(memberId)=='' && $.trim(activityareaId)=='') {
	    	 commUtil.alertError("会员ID或活动ID必填写其中一个才能进行搜索");
			 return;
		} 
		 $("#searchbtn").click();
		 searchbtnFunction();

	});
});


function searchbtnFunction(){
	     listModel.ligerGrid.url = '${pageContext.request.contextPath}/member/coupon/dataList.shtml';
	     listModel.initdataPage();		 
			
}


 var listConfig={
     /*  url:"/member/coupon/dataList.shtml", */
     
      listGrid:{ columns: [   { display: 'ID', name: 'id' },
                        { display: '会员ID', name: 'memberId' },
    	                { display: '会员昵称', name: 'nick', render: function(rowdata, rowindex) {
    	                	if(rowdata.nick==null){
    	                		return "醒购会员";
    	                	}else{
    	                		return rowdata.nick;
    	                	}
    	                }},
    	                { display: '优惠劵类型', name: 'rangName' },
    	                { display: '使用范围', render: function(rowdata, rowindex) {
    	                	return "等待活动模块完成补充";
    	                }},
    	                { display: '优惠劵', name: 'couponName' },
    	                { display: '过期状态', width: 150, render: function(rowdata, rowindex) {
			            	if (rowdata.expiryEndDate!=null){
			            		var date1=new Date();
			            		var expiryEndDate=new Date(rowdata.expiryEndDate);
			            		if (date1<=expiryEndDate) {
			            			return "未过期";
								}else if (date1>expiryEndDate) {
									return "已过期";
								}
				            	
			            	}
			         	}},
    	                { display: '过期时间', width: 150, render: function(rowdata, rowindex) {
			            	if (rowdata.expiryEndDate!=null){
				            	var expiryEndDate=new Date(rowdata.expiryEndDate);
				            	return expiryEndDate.format("yyyy-MM-dd hh:mm:ss");
			            	}
			         	}},
		                { display: '使用状态', name: 'statusDesc' },
		                { display: '使用订单', name: 'orderId' },
			            { display: '领取时间', width: 150, render: function(rowdata, rowindex) {
			            	if (rowdata.recDate!=null){
				            	var recDate=new Date(rowdata.recDate);
				            	return recDate.format("yyyy-MM-dd hh:mm:ss");
			            	}
			         	}},
			            { display: '使用时间', width: 150, render: function(rowdata, rowindex) {
			            	if (rowdata.useDate!=null){
			            		var useDate=new Date(rowdata.useDate);
				            	return useDate.format("yyyy-MM-dd hh:mm:ss");
			            	}	
			         	}}
		                ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
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
		<div class="search-tr"> 	
			<div class="search-td">
			<div class="search-td-label">会员ID：</div>
			<div class="search-td-condition" >
				<input type="text" id = "memberId" name="memberId" >
			</div>
			</div>
			 	
		<!-- 	<div class="search-td">
			<div class="search-td-label">会员昵称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "nick" name="nick" >
			</div>
			</div> -->
			
			 
			<div class="search-td">
			<div class="search-td-label">活动ID：</div>
			<div class="search-td-condition" >
				<input type="text" id ="activityareaId" name="activityareaId" >
			</div>
			</div>
		
			<div class="search-td">
			<div class="search-td-label" >状态：</div>
			<div class="search-td-condition" >
				<select id="status" name="status">
				<option value="">请选择</option>
				<c:forEach var="statusItem" items="${memberCouponStatus}">
				<option value="${statusItem.statusValue}">${statusItem.statusDesc}</option>
				</c:forEach>
				</select>
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" >过期状态：</div>
			<div class="search-td-condition" >
			   <select id="overdue" name="overdue">
				<option value="">请选择</option>
				<option value="1">未过期</option>
				<option value="2">已过期</option>
			  </select>
			</div>
			</div>
			<div class="search-td-search-btn" style="display: inline-flex;">
				 <div id="search">
					  <input type="button" class="l-button" onclick="seeFunction();" value="搜索">
				 </div>
				 <div id="searchbtn" style="display: none;"></div>
			</div>
			<!-- <div class="search-td-search-btn">		  
				 <div class="l-button" onclick="searchbtnFunction();" >搜索</div>						
			</div> -->
			
		</div>
					
	</div>
				
	<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js">
</script>