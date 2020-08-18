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

 var listConfig={
	  
     url:"/marketing/memberCumulativeSignIn/data.shtml?memberId=${memberId}",
     listGrid:{ columns: [
			            { display: '会员ID', name:'memberId'},
			            { display: '领取时间',render: function (rowdata, rowindex) {
		                	if(rowdata.receiveTime){
		                		var receiveTime = new Date(rowdata.receiveTime);
				            	return receiveTime.format("yyyy-MM-dd hh:mm:ss");
		                	}
						}},
			            { display: '月份',name:'month'},
			            { display: '累计签到次数',name:'cumulativeSignInCount'},
			            { display: '积分',render: function (rowdata, rowindex) {
		                	if(rowdata.integral){
				            	return rowdata.integral+"积分";
		                	}else{
		                		return "";
		                	}
						}},
		                { display: '优惠券',name:'couponNames'},
		                { display: '实物',name:'productName'}
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">领取时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="receive_time_begin" name="receive_time_begin" />
				<script type="text/javascript">
					$(function() {
						$("#receive_time_begin").ligerDateEditor( {
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
				<input type="text" id="receive_time_end" name="receive_time_end" />
				<script type="text/javascript">
					$(function() {
						$("#receive_time_end").ligerDateEditor( {
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
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>