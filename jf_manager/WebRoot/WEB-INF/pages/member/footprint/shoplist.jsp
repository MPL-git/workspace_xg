<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">

function searchbtnFunction() {
	  var Verification=true;
      var memberId = $("[name='memberId']").val();
	  var nick = $("[name='nick']").val();
	  var mchtCode=$("[name='mchtCode']").val();	
	  var mobile=$("[name='mobile']").val();
	  var updateDate_begin = $("#updateDate_begin").val();
	  var updateDate_end = $("#updateDate_end").val();
	  var startDay = new Date(updateDate_begin);
	  var endDay = new Date(updateDate_end);
	  var diffDays = endDay - startDay;
	  var time = 60*24*60*60*1000;
	   if ($.trim(memberId)=='' && $.trim(nick)=='' && $.trim(mchtCode)=='' && $.trim(mobile)=='') {
		   commUtil.alertError("会员ID、会员昵称、商家序号、手机号码至少输入1个搜索条件！");
		   return Verification=false;
	   }
	   if(diffDays > time){
			commUtil.alertError("浏览日期范围不能超过60天,请重新选择!");
			$("[name='updateDate_begin']").ligerDateEditor().setValue("");
			$("[name='updateDate_end']").ligerDateEditor().setValue("");
			return Verification=false;
	   }
	   if (Verification==true) {
          listModel.ligerGrid.url = '${pageContext.request.contextPath}/member/footprint/shopdatalist.shtml';
          listModel.initdataPage();		 
		
	   }
			
 }  


	  var listConfig={
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
				                { display: '商家序号',  name: 'mchtCode'},
				                { display: '浏览店铺名称',  name: 'shopName'},
				                { display: '一级类目',  name: 'mchtproductTypeName'},
				                { display: '品牌',  name: 'mchtproductBandName',render:function(rowdata, rowindex){
				                	var mchtProductBrandName = rowdata.mchtproductBandName;
				                	var html = [];
				                	if(mchtProductBrandName != null && mchtProductBrandName.length > 0){
				                		var mchtProductBrandNames = mchtProductBrandName.split(",");
										for (var i = 0; i < mchtProductBrandNames.length; i++) {
											var brandName = mchtProductBrandNames[i].substring(0, mchtProductBrandNames[i].indexOf("）")+1);
											var productBrandId = mchtProductBrandNames[i].substring(mchtProductBrandNames[i].indexOf("）")+1);
											if(i != 0) {
												html.push("<br/>");
											}
											html.push(brandName);
										}
				                	}
									return html.join("");
				                }}, 
				                { display: '商家开通日期',  name: '',render:function(rowdata, rowindex){			       
				                	if (rowdata.cooperateBeginDate!=null && rowdata.cooperateBeginDate !='') {
				                	var cooperateBeginDate = new Date(rowdata.cooperateBeginDate);				                
				                	    return cooperateBeginDate.format("yyyy-MM-dd");            	   
									}else{
										return "";
									}
				                }},
				                { display: '访问时间', name: 'updateDate', render: function(rowdata, rowindex) {
		    	  					var date=new Date(rowdata.updateDate);
		    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);
		      					}},

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
			<div class="search-td-label">商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mchtCode" name="mchtCode" >
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
						$("#updateDate_begin").ligerDateEditor( {
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
						$("#updateDate_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
		    <div class="search-td-search-btn">		  
				 <div class="l-button" id="" onclick="searchbtnFunction();" >搜索</div>						
			</div>
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</body>


