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
 
 $(function(){
	 parent.mchtInfoListId('${mchtIdGroup}', '${mchtIdGroupCode}');
 });
 
 var listConfig={
      url:"/activityAreaNew/memberInfoList.shtml?mchtIdGroup=${mchtIdGroup}",
      btnItems:[],
      listGrid:{ columns: [ 
						{display:'商家序号',name:'mchtCode', align:'center', isSort:false, width:300},
						{display:'店铺名称',name:'shopName', align:'center', isSort:false, width:300},
						{display:'公司名称',name:'companyName', align:'center', isSort:false, width:300}
// 						{display:'商家名称',name:'shortName', align:'center', isSort:false, width:150},
// 						{display:'入驻类型',name:'mchtTypeDesc', align:'center', isSort:false, width:100},
// 						{display:'入驻时间',name:'createDate', align:'center', isSort:false, width:150, render:function(rowdata,rowindex){
// 							if(rowdata.createDate==null||rowdata.createDate==""||rowdata.createDate==undefined){
// 								return "";
// 							}else{
// 								var createDate=new Date(rowdata.createDate);
// 								return createDate.format("yyyy-MM-dd hh:mm:ss");								
// 							}
// 						}}
		                ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"operateAuditForm",
        listGridName:"maingridOperate"
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
<!-- 	<div id="toptoolbar"></div> -->
	<form id="operateAuditForm" runat="server">
		
		<div id="maingridOperate" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
