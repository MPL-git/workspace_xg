<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style type="text/css">
.color-s0,.color-fs1{color: #0000FF;}
.color-s1,.color-fs2{color: #008000;}
.color-s4{color: #333333;}
.color-fs0{color: #000000;}
</style>
<script type="text/javascript">
$(function() {
	// 禁止分页
	 $("#maingrid").ligerGrid({
        usePager:false
    });
}); 

function seeFunction() {
    listModel.ligerGrid.url = '${pageContext.request.contextPath}/membership/memberDailyreportsdata.shtml';
    listModel.initdataPage();		 
}

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
		  
	  url:"/membership/memberDailyreportsdata.shtml",
      
      listGrid:{ columns: [
                        { display: '统计时间', name:'eachDay'},
		                { display: '新增会员数', name:'memberEverydayAdded'},
		                { display: '新增会员消费人数', name:'memberEverydayNewlyaddedConsumption'},
		                { display: '活跃会员数', name:'memberEverydayActive'},
		                { display: '消费会员数', name:'memberEverydayConsumption'},
		                { display: '新增SVIP会员数', name:'memberEverydaySvipcount'}	               	            	                
		               ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true//不设置默认为 true
                 
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">统计日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="date_begin" name="date_begin" value="${date_begin}"/>
				<script type="text/javascript">
					$(function() {
						$("#date_begin").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="date_end" name="date_end" value="${date_end}"/>
				<script type="text/javascript">
					$(function() {
						$("#date_end").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			
			<div style="display: inline-flex;">
				<div id="search">
				   <input type="button" class="l-button" onclick="seeFunction();" style="height: 26px;"  value="搜索">
				</div>
				<div style="padding-left: 10px;">
					<input type="button" style="width: 70px;height: 28px;cursor: pointer;" value="查看规则" onclick="memberExplain()">
				</div>
			</div>
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>