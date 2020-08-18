<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

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
 
 var listConfig={
	      url:"/activityAreaNew/choiceCouponListData.shtml?preferentialIdGroup=${preferentialIdGroup}",
	      btnItems:[],
	      listGrid:{ columns: [
	                        {display:'优惠劵ID',name:'id'},
	                        {display:'优惠券名称',name:'name'},
	                        {display:'面额/使用条件',name:'moneyMinimum',render:function(rowdata,rowindex){
	                        	return rowdata.money+"元 / 满"+rowdata.minimum+"元";
	                        }},
							{display:'状态',name:'status',render:function(rowdata,rowindex){
								if(rowdata.status==0){
									return "未启用";
								}else if(rowdata.status==1){
									return "启用";
								}else{
									return "停用";
								}
							}},
							{display:'是否失效',name:'status',render:function(rowdata,rowindex){
								if(new Date(rowdata.expiryEndDate) >= new Date()){
									return "未失效";
								}else{
									return "已失效";
								}
							}}
			                ],   
	                 showCheckbox : false,  //不设置默认为 true
	                 showRownumber: true //不设置默认为 true
	      } , 							
	     container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"couponListIdForm",
	        listGridName:"maingridCoupon"
	      }        
	  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
<!-- 	<div id="toptoolbar"></div> -->
	<form id="couponListIdForm" runat="server">
		
		<div id="maingridCoupon" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
