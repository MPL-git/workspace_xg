<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 var listConfig={
     url:"/mchtDeposit/accountData.shtml",
     listGrid:{ columns: [
						{ display: 'ID', name:'id'},
			            { display: '缴纳类型', render: function (rowdata, rowindex) {
			            	if(rowdata.paymentType == "1"){
			            		return "支付宝";
			            	}else if(rowdata.paymentType == "2"){
			            		return "微信";
			            	}else if(rowdata.paymentType == "3"){
			            		if(rowdata.accountType == "1"){
			            			return "公司银行";
			            		}else if(rowdata.accountType == "2"){
			            			return "个人银行";
			            		}
			            	}
		                }},
			            { display: '银行类型',render: function (rowdata, rowindex) {
			            	if(rowdata.paymentType == "1"){
			            		return "支付宝";
			            	}else if(rowdata.paymentType == "2"){
			            		return "微信";
			            	}else if(rowdata.paymentType == "3"){
			            		return rowdata.paymentName;
			            	}
		                }},
		                { display: '账号', name:'accountNo'},
			            { display: '账户名',name:'accountName'},
		                { display: '开户银行',render: function (rowdata, rowindex) {
		                	if(rowdata.paymentType == "3"){
			            		return rowdata.paymentName;
			            	}else{
			            		return "";
			            	}			                		
		                }}
		                ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber: true //不设置默认为 true
      } ,  							
     container:{
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  };
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>