<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
var activityAreaId="${activityAreaId}";
 /* $(function(){
	<c:if test="${not empty activityAreaId}">
	  activityAreaId=${activityAreaId};
	</c:if>
 }); */
 /* 查看活动 */
 function seeVenueActivityInfo(activityId) {
		$.ligerDialog.open({
		height: $(window).height() - 100,
		width: $(window).width() - 10,
		title: "官方会场商家报名审核",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activityArea/venueActivityInfo.shtml?activityId=" + activityId+"&type=${type}",
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 var listConfig={
	  
      url:"/activityArea/seeActivityMchtListData.shtml?activityAreaId="+activityAreaId+"&type=${type}",
      btnItems:[],
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
                        {display:'特卖ID',name:'id'},
                        {display:'会场名称',name:'activityAreaName'},
                        {display:'活动类型',name:'activityAreaTypeDesc'},
                        {display:'活动名称',name:'name'},
		                {display:'商家名称', name:'shortName'},
		                {display:'商家序号',name:'mchtCode'},
		                {display:'对接运营',name:'contactName'},
/* 		                {display:'销量',name:'thisQuantity'},
		                {display:'销售额',name:'thisSalePrice'}, */
		                {display:'技术服务费',name:'feeRate'},
		                { display: "操作", name: "OPER",align: "center",width: 150, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:seeVenueActivityInfo(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						    return html.join("");
					 }
	             }
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
  };
  
</script>
<style type="text/css">
/* .l-selected{height: 64px;} */
/* l-grid-row l-grid-row-alt l-grid-row-over l-selected */
</style>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label"  >商家：</div>
			<div class="search-td-combobox-condition" >
				<input type="text" id = "shortName" name="shortName" >
			</div>
			</div>
			
			<div class="search-td">
				<div class="search-td-label"  >商家序号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "mchtCode" name="mchtCode" >
				</div>
			</div>
			<div class="search-td">
			<div class="search-td-label"  >活动状态：</div>
			<div class="search-td-combobox-condition" >
				<select id="actStatus" name="actStatus">
					<option value="">不限</option>
					<option value="1">待开始</option>
					<option value="2">预热中</option>
					<option value="3">活动中</option>
					<option value="4">已结束</option>
				</select>
			</div>
			</div>
			<div class="search-td" style="width: 50px;">
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
			<div class="l-panel-search-item search_right">活动时间：</div>
				<div class="l-panel-search-item">
					<input type="text" id="activityBeginTime" name="activityBeginTime" />
					<script type="text/javascript">
						$(function() {
							$("#activityBeginTime").ligerDateEditor( {
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
					<input type="text" id="activityEndTime" name="activityEndTime" />
					<script type="text/javascript">
						$(function() {
							$("#activityEndTime").ligerDateEditor( {
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>	
				</div>
		</div>
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
