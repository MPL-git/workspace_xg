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
		$("#date_begin").ligerDateEditor( {
			showTime : false,
			labelWidth : 150,
			labelAlign : 'left'
		});
	});
	
	$(function() {
		$("#date_end").ligerDateEditor( {			
			showTime : false,
			labelWidth : 150,
			labelAlign : 'left'
		});
	});
	
	
	 function MchtInfoAuditList(eachDay) {
			$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "全部商家审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/getMchtListManager.shtml?statusPage=1&EachDay="+eachDay,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	
		
	 var listConfig={
		     url:"/mchtData/BusinessStatisticsdata.shtml",
		     listGrid:{ columns: [
								{ display: '日期', name:'eachDay'},
								{ display: '创建商家数', name:'mchtcount'},
								{ display: '入驻资质未提交',name:'settledunsubmitted'},
					            { display: '入驻资质待审/审核中', name:'settledaudited'},
					            { display: '入驻资质通过',name:'settledadopt'},
				                { display: '店铺开通数',name:'shopopen'},
					            { display: '合同归档数',name:'contractfile'},
					            { display: '操作', name:"OPER1" , align: "center", render: function(rowdata, rowindex){
					               var html = [];
					               if(rowdata.eachDay !=null ){					
					                var eachDay = new Date(rowdata.eachDay);					                
					               html.push("<a href=\"javascript:MchtInfoAuditList('"+eachDay.format("yyyy-MM-dd")+ "');\">&nbsp;&nbsp;查看</a>&nbsp;&nbsp;");	
					               
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
				<div id="searchbtn">查看</div> 
			</div>
			</div>
			</div>
		 <div id="maingrid" style="margin: 0; padding: 0"></div>
   </form>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
  </body>
</html>
