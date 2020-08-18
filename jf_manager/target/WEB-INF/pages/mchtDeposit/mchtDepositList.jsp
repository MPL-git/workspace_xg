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
 		
	  function formatMoney(s, n) {
		   	n = n > 0 && n <= 20 ? n : 2;
		   	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
		   	var l = s.split(".")[0].split("").reverse(),
		   	r = s.split(".")[1];
		  	t = "";
		   	for(i = 0; i < l.length; i ++ ) {
		      		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
		   	}
		   	return t.split("").reverse().join("") + "." + r;
	  }
 
      var listConfig={
   		  btnItems:[
   	    			{text: '导出', icon: 'down', click: function() {
   	    				exportMchtDepositListExcel();
   	    			    return;
   	    			  }
   	    			}        
   	    	      ],
      	  url:"/mchtDeposit/mchtDepositList.shtml",
	      listGrid:{ columns: [
							{display:'商家序号',name:'mchtCode', align:'center', isSort:false, width:180, render: function (rowdata, rowindex) {
			                	return rowdata.mchtCode+"-"+rowdata.mchtId;
			                }},
							{display:'公司名称',name:'companyName', align:'center', isSort:false, width:180},
							{display:'店铺名称',name:'shopName', align:'center', isSort:false, width:180},
							{display: '应缴金额（元）', name:'totalAmt', align:'center', isSort:false, width:180, render: function (rowdata, rowindex) {
			                	if(rowdata.totalAmt){
									return formatMoney(rowdata.totalAmt,2);	                		
			                	}else{
			                		return "0.00";
			                	}
			                }},
							{display: '已缴金额（元）', name:'payAmt', align:'center', isSort:false, width:180, render: function (rowdata, rowindex) {
			                	if(rowdata.payAmt){
									return formatMoney(rowdata.payAmt,2);	                		
			                	}else{
			                		return "0.00";
			                	}
			                }},
							{display: '还需补缴（元）', name:'unpayAmt', align:'center', isSort:false, width:180, render: function (rowdata, rowindex) {
			                	if(rowdata.unpayAmt){
									return formatMoney(rowdata.unpayAmt,2);	                		
			                	}else{
			                		return "0.00";
			                	}
			                }},
							{display:'合作状态',name:'mchtStatusDesc', align:'center', isSort:false, width:180}
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
  
      function exportMchtDepositListExcel() {
    	  location.href="${pageContext.request.contextPath}/mchtDeposit/exportMchtDepositListExcel.shtml"
      }
      
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
