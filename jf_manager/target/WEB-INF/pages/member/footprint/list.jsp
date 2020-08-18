<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

function searchbtnFunction() {
	  var Verification=true;
      var memberId = $("[name='memberId']").val();
	  var nick = $("[name='nick']").val();
	  var productCode=$("[name='productCode']").val();	
	  var mobile=$("[name='mobile']").val();
	  var updateDate_begin = $("#updateDate_begin").val();
	  var updateDate_end = $("#updateDate_end").val();
	  var startDay = new Date(updateDate_begin);
	  var endDay = new Date(updateDate_end);
	  var diffDays = endDay - startDay;
	  var time = 60*24*60*60*1000;
	   if ($.trim(memberId)=='' && $.trim(nick)=='' && $.trim(productCode)=='' && $.trim(mobile)=='') {
		   commUtil.alertError("会员ID、会员昵称、商品ID、手机号码至少输入1个搜索条件！");
		   return Verification=false;
	   }
	   if(diffDays > time){
			commUtil.alertError("浏览日期范围不能超过60天,请重新选择!");
			$("[name='updateDate_begin']").ligerDateEditor().setValue("");
			$("[name='updateDate_end']").ligerDateEditor().setValue("");
			return Verification=false;
	   }
	   if (Verification==true) {
          listModel.ligerGrid.url = '${pageContext.request.contextPath}/member/footprint/datalist.shtml';
          listModel.initdataPage();		 
		
	   }
			
 }  


	  var listConfig={
		     /*  url:"/member/footprint/datalist.shtml", */
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
				                { display: '商品ID',  name: 'productCode'},
				                { display: '浏览商品名称',  name: 'productName'},
				                { display: '货号',  name: 'artNo'},
				                { display: '醒购价',  name: 'salePrice'},
				                { display: '一级类目',  name: 'productTypeName1'},
				                { display: '二级类目',  name: 'productTypeName2'},
				                { display: '三级类目',  name: 'productTypeName3'},
				                { display: '品牌',  name: 'productBrandName'}, 
				                { display: '浏览时间', name: 'updateDate', render: function(rowdata, rowindex) {
		    	  					var date=new Date(rowdata.updateDate);
		    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);
		      					}},
		      					{ display: '是否清除',  name: 'delFlag', render: function(rowdata, rowindex) {
		    	  					if (rowdata.delFlag==1){
		    	  						return "是";
		    	  					}else{
		    	  						return "否";
		    	  					}
		      					}}
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
			<div class="search-td-label">商品ID：</div>
			<div class="search-td-condition" >
				<input type="text" id = "productCode" name="productCode" >
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


