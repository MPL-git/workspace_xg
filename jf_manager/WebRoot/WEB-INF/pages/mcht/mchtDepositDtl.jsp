<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript">
      var listConfig={
	  
      url:"/mcht/mchtDepositDtlList.shtml?depositId=${depositId}",
      listGrid:{ columns: [  { display: '日期', name: 'createDate',width:120, render: function(rowdata, rowindex) {
    	  					var date=new Date(rowdata.createDate);
    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2);
      					}},
		                { display: ' 商家序号', name: 'mchtCode',width:100 },
		                { display: '公司名称',  name: 'companyName', width: 150 }, 
		                { display: '摘要', name: 'remarks' ,width: 200 },
		                { display: '应缴保证金变化', name: 'txnAmt', width:120 ,render: function(rowdata, rowindex) {				    
						    if(rowdata.txnType=='A')
						    {
						    	if (rowdata.txnAmt>0){
						    		return "+"+rowdata.txnAmt;
						    	}
						    	else{
						    		return rowdata.txnAmt;
						    	}
						    }						    
		                }},
		                { display: '已缴保证金变化', name: 'txnAmt', width:100, render: function(rowdata, rowindex) {					    
						    if(rowdata.txnType=='B')
						    {
						    	if (rowdata.txnAmt>0){
						    		return "+"+rowdata.txnAmt;
						    	}
						    	else{
						    		return rowdata.txnAmt;
						    	}
						    }						    
		                }},
		                { display: '变化后金额', name: 'payAmt' ,width: 200 }
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
		<input name="depositId" type="hidden" value="${depositId}" />
		<div class="l-panel-search"  > 
			<div class="l-panel-search-item search_right">日期：</div>
			<div class="l-panel-search-item">
				<input type="text" id="date_begin" name="date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script> 
			</div>
				
			<div class="l-panel-search-item">至 </div>
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name="date_end" />
				<script type="text/javascript">
					$(function() {
						$("#date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
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
