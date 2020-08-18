<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript">
 function gridRefresh() {
	    if (listModel.gridManager) {
	        var gridparms = [];
	        gridparms.push({ name: "staff", value: "self" });
	        listModel.gridManager.loadServerData(gridparms);
	    }
	}
 
  var listConfig={
	  
      url:"/resourceLocationManagement/SpecialSaleCouponsListData.shtml",
    
      listGrid:{ columns: [  
 		                { display: '店铺名称/商家序号',width: 200, render: function (rowdata, rowindex) {
 		                	var html = [];
 		                	html.push(rowdata.shortName);
 		                	html.push("<br>");
 		                	html.push(rowdata.mchtCode);
 		              	 	return html.join("");
		                }},
		                { display: '商品数', width: 150, name : 'adoptActivityProductNum'},

		                { display: '活动时间', width: 150, render: function (rowdata, rowindex) {
		                	var html = [];
		                    if (rowdata.activityBeginTime!=null){
		                    	var beginDate=new Date(rowdata.activityBeginTime);
		                    	html.push("起 "+ beginDate.format("yyyy-MM-dd hh:mm"));
		                    }
		                    if (rowdata.activityEndTime!=null){
		                    	var endDate=new Date(rowdata.activityEndTime);
		                    	html.push("止 "+endDate.format("yyyy-MM-dd hh:mm"));
		                    }
							return html.join("<br>");
		                }},
		                { display: '优惠信息',  name: '', width: 200, render:function(rowdata,rowindex){
		                	var html = [];
		                	if(rowdata.couponList!=null){
		                	for(var i=0;i < rowdata.couponList.length;i++){
		                		var minimum = rowdata.couponList[i].minimum;
		                		var money = rowdata.couponList[i].money;
		                		html.push("满"+minimum+"减"+money+"<br>");
		                	 }
		                	}
		                	return html.join("");
		                }},
		                
		                { display: '发行量',  name: '',render:function(rowdata,rowindex){
		                	var html = [];
		                	var couponList = rowdata.couponList;
		                	for(var i = 0 ; i < couponList.length;i++){
		                		html.push(couponList[i].grantQuantity+"<br>");
		                	}
		                	return html.join("");
		                }},
		                { display: '领取量',  name: '',render:function(rowdata,rowindex){
		                	var html = [];
		                	var couponList = rowdata.couponList;
		                	for(var i = 0 ; i < couponList.length;i++){
		                		html.push(couponList[i].recQuantity+"<br>");
		                	}
		                	return html.join("");
		                }},
		                { display: '使用量',  name: '',render:function(rowdata,rowindex){
		                	var html = [];
		                	var couponList = rowdata.couponList;
		                	for(var i = 0 ; i < couponList.length;i++){
		                		html.push(couponList[i].useQuantity+"<br>");
		                	}
		                	return html.join("");
		                }}, 
		                { display: '状态', render: function(rowdata, rowindex) {
		                	var html = [];
		                	var now = new Date();
		                	var beginTime = rowdata.activityBeginTime;
		                	var endTime = rowdata.activityEndTime;
	                  		if(now>endTime){
	                  			 html.push("已结束");
	                  		}
	                  		if(now>=beginTime && now<=endTime){
	                  			 html.push("活动中");
	                  		}
	                  		if(now<beginTime){
	                  			 html.push("未开始")
	                  		}
	                  		return html.join("")
		                }},
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
}
</script>

<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<c:if test="${roleType!=1}">
	<div id="toptoolbar"></div>
	</c:if>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		
		<div class="search-tr"  > 
		
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="recBeginDate" name="recBeginDate" />
				<script type="text/javascript">
					$(function() {
						$("#recBeginDate").ligerDateEditor( {
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
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			<div class="l-panel-search-item">
				<input type="text" id="recEndDate" name="recEndDate" />
				<script type="text/javascript">
					$(function() {
						$("#recEndDate").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>

			 
			 
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>