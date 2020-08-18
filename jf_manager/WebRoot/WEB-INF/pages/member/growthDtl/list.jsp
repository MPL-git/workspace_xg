<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
   var ctx = "${pageContext.request.contextPath}";
</script>

<script type="text/javascript">
	  var listConfig={
			  url:"/member/growthDtl/datalist.shtml",
		    
		      listGrid:{ columns: [  
			                    { display: '日志ID', name: 'id'},
			                    { display: '会员ID', name: 'memberId'},
				                { display: '会员昵称',  name: 'nick'},
				                { display: '获得成长值',  name: 'gValue'},
				                { display: '结余成长值',  name: 'balance'},
				                { display: '订单号',  name: 'subOrderCode'},
				                { display: '时间', name: 'createDate', render: function(rowdata, rowindex) {
		    	  					var date=new Date(rowdata.createDate);
		    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);
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
				<input type="text" id = "memberId" name="memberId" >
			</div>
			</div>
			 	
			<div class="search-td">
			<div class="search-td-label">会员昵称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "nick" name="nick" >
			</div>
			</div>
			 
			<div class="search-td">
			<div class="search-td-label">手机号码：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mobile" name="mobile" >
			</div>
			</div>
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
			
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</body>


