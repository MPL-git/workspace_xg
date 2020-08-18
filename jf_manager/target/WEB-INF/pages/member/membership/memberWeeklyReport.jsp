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
 
$(function(){
	// 禁止分页
	 $("#maingrid").ligerGrid({
       usePager:false
   });
}); 

function memberExplain(){
	$.ligerDialog.open({
		height: 300,
		width: 500,
		title: "名词解释",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/membership/memberExplain.shtml",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
 }

 var listConfig={
     url:"/membership/memberWeeklyReportdata.shtml",
     listGrid:{ columns: [
                    { display: '统计时间', name:'weeklyDay'},
                    { display: '新增会员数', name:'memberWeeklyAdded'},
                    { display: '新增会员消费人数', name:'memberWeeklyNewlyaddedConsumption'},
                    { display: '活跃会员数', name:'memberWeeklyActive'},
                    { display: '消费会员数', name:'memberWeeklyConsumption'},
                    { display: '新增SVIP会员数', name:'memberWeeklySvipcount'}	
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
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">统计截止日期：</div>
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name=date_end value="${date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>	
			<div class="search-td">
			<div class="search-td-label" style="float:left;">周数：</div>
			<div class="l-panel-search-item" >
				<select id="week" name="week">
					<option value="">请选择</option>
					<option value="1" <c:if test="${week == 1}">selected="selected"</c:if>>1周</option>
					<option value="2" <c:if test="${week == 2}">selected="selected"</c:if>>2周</option>
					<option value="3" <c:if test="${week == 3}">selected="selected"</c:if>>3周</option>
					<option value="4" <c:if test="${week == 4}">selected="selected"</c:if>>4周</option>
					<option value="5" <c:if test="${week == 5}">selected="selected"</c:if>>5周</option>
					<option value="6" <c:if test="${week == 6}">selected="selected"</c:if>>6周</option>
					<option value="7" <c:if test="${week == 7}">selected="selected"</c:if>>7周</option>
					<option value="8" <c:if test="${week == 8}">selected="selected"</c:if>>8周</option>
					<option value="9" <c:if test="${week == 9}">selected="selected"</c:if>>9周</option>
					<option value="10" <c:if test="${week == 10}">selected="selected"</c:if>>10周</option>
				</select>
		 	 </div>
			 </div>
			<div class="search-td-search-btn" style="display: inline-flex;">
					<div id="searchbtn" >搜索</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 66px;height: 26px;cursor: pointer;" value="查看规则" onclick="memberExplain()">
					</div>
				 </div>	
			</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>