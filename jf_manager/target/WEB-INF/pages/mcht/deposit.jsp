<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript">
 function mchtDepositDtl(depositId) {
		$.ligerDialog.open({
		height: $(window).height()-200,
		width: $(window).width()-400,
		title: "保证金明细",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mcht/mchtDepositDtl.shtml?depositId=" + depositId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 
      var listConfig={
	  
      url:"/mcht/depositList.shtml",
      listGrid:{ columns: [
   		                { display: '商家序号', name: 'mchtCode',width:150 },
		                { display: '公司名称',  name: 'companyName',width:300}, 
		                { display: '应缴保证金', name: 'totalAmt' ,width: 150 },
		                { display: '已缴保证金', name: 'payAmt' ,width: 150 },
		                { display: '未缴保证金', name: 'unpayAmt' ,width: 150 },
		                { display: '明细', name: 'OPER', width:150, render: function(rowdata, rowindex) {
		                	var html = [];
		                	html.push("<a href=\"javascript:mchtDepositDtl(" + rowdata.id + ");\">查看明细</a>"); 
							return html.join("");
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
       
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="l-panel-search">
			<div class="l-panel-search-item search_right">公司名称：</div>
			<div class="l-panel-search-item">
				<input type="text" id ="companyName" name="companyName" >
			</div>
			
			<div  class="l-panel-search-item" style="margin-left: 20px;">
				<div id="searchbtn" style="width: 100px; ">搜索</div> 
			</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
