<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    <title>每日商家漏斗统计</title>
   <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
   <script type="text/javascript">
	$(function(){
		$("#maingrid").ligerGrid({
			usePager:false
		});
	});
	
	$(function() {
		$("#date_begin").ligerDateEditor({
			format: 'yyyy-MM',
			showTime : false,
			labelWidth : 150,
			labelAlign : 'left'
		});
	});
	
	$(function() {
		$("#date_end").ligerDateEditor({
			format: 'yyyy-MM', 
			showTime : false,
			labelWidth : 150,
			labelAlign : 'left'
		});
	});
	
	
	 function mchtInfoAuditList(eachMonth) {
			$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "全部商家审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/getMchtListManager.shtml?statusPage=3&eachMonth="+eachMonth,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	
	 
     function searchbtnFunction() {
		  var Verification=true;
		  var date_begin = $("#date_begin").val();
		  var date_end = $("#date_end").val();
		  var startDay = new Date(date_begin);
		  var endDay = new Date(date_end);
		  var diffDays = endDay - startDay;
		  var time = 180*24*60*60*1000;
		   if(diffDays > time){
				commUtil.alertError("日期跨度不能超过6个月,请重新选择!");
				return Verification=false;
		   }
		   if (Verification==true) {
	          listModel.ligerGrid.url = '${pageContext.request.contextPath}/mchtData/monthlyBusinessStatisticsdata.shtml';
	          listModel.initdataPage();		 
			
		   }
				
	 }  	 
	 
		
	 var listConfig={
		     url:"/mchtData/monthlyBusinessStatisticsdata.shtml",
		     listGrid:{ columns: [
								{ display: '日期', name:'eachMonth'},
								{ display: '创建商家数', name:'mchtCount'},
								{ display: '入驻资质未提交',name:'settledunSubmitted'},
					            { display: '入驻资质待审/审核中', name:'settleDaudited'},
					            { display: '入驻资质通过',name:'settleDadopt'},
				                { display: '店铺开通数',name:'shopOpen'},
					            { display: '合同归档数',name:'contractFile'},
					            { display: '申请关店数',name:'mchtcloseApplication'},
					            { display: '已关店数',name:'mchtcloseCount'},
					            { display: '操作', name:"OPER1" , align: "center", render: function(rowdata, rowindex){
					               var html = [];
					               if(rowdata.eachMonth !=null ){					
					                var eachMonth = new Date(rowdata.eachMonth);					                
					               html.push("<a href=\"javascript:mchtInfoAuditList('"+eachMonth.format("yyyy-MM-dd")+ "');\">&nbsp;&nbsp;查看</a>&nbsp;&nbsp;");	
					               
					               }
					            	
					               return html.join("");
					            }}
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr" > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="date_begin" name="date_begin" value="${date_begin}"/>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
			</div>
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name="date_end" value="${date_end}"/>
			</div>
			</div>
			<div class="search-td-search-btn">		  
				 <div class="l-button" onclick="searchbtnFunction();" >查看</div>						
			</div>
			</div>
			</div>
		 <div id="maingrid" style="margin: 0; padding: 0"></div>
   </form>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
  </body>
</html>
