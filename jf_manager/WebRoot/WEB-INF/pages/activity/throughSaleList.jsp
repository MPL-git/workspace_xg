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
 
 var type=${type};

 /* 查看活动 */
 function seeActivity(activityId) {
		$.ligerDialog.open({
		height: $(window).height()-100,
		width: $(window).width()-400,
		title: "品牌特卖活动专题",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/activity/allSeeActivityInfo.shtml?activityId=" + activityId+"&type="+type,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
	}
 var listConfig={
	  
      url:"/activity/allOperateAuditListData.shtml?type="+type,
   
      btnItems:[],
      listGrid:{ columns: [  /* { display: 'ID', name: 'id'}, */ 
                        {display:'特卖ID',name:'id'},
                        {display:'活动名称',name:'name'},
                        {display:'利益点',name:'benefitPoint'},
		                {display:'商家名称', name:'shortName'},
		                {display:'商家序号',name:'mchtCode'},
		                {display:'类目',name:'productTypeName'},
		                {display:'品牌',name:'productBrandName'},
		                {display:'活动状态',render:function(rowdata,rowindex){
		                	var myDate = new Date();
	                		if(myDate<rowdata.preheatTime){
	                			return "待开始";
	                		}
	                		if(myDate<rowdata.activityBeginTime && myDate>=rowdata.preheatTime){
	                			return "预热中";
	                		}
	                		if(myDate>=rowdata.activityBeginTime && myDate<=rowdata.activityEndTime || rowdata.activityEndTime==null){
	                			return "<samp style='color:#006600;'>活动中</samp>";
	                		}
	                		if(myDate>rowdata.activityEndTime){
	                			return "已结束";
	                		}
		                }},
		                {display:'是否暂停',render:function(rowdata,rowindex){
		                	if (rowdata.status=='5'){
		                		return "<span style='color:#CC0000;'>是</span>";
		                	}else{
		                		return "否";
		                	}
		                }},
/* 		                {display:'销量',name:'thisQuantity'},
		                {display:'销售额',name:'thisSalePrice'}, */
						{display:'活动预热时间',name:'preheatTime',width: 150,render:function(rowdata,rowindex){
							if(rowdata.expectedStartTime!=null){
								var preheatTime=new Date(rowdata.preheatTime);
				            	return preheatTime.format("yyyy-MM-dd hh:mm:ss");
							}else{return "";}
						}},
						{display:'活动开始时间',name:'activityBeginTime',width: 150,render:function(rowdata,rowindex){
							if(rowdata.activityBeginTime!=null){
								var activityBeginTime=new Date(rowdata.activityBeginTime);
				            	 return activityBeginTime.format("yyyy-MM-dd hh:mm:ss");
							}else{return "";} 
						}},
						{display:'活动结束时间',name:'activityEndTime',width: 150,render:function(rowdata,rowindex){
							if(rowdata.activityEndTime!=null){
								var activityEndTime=new Date(rowdata.activityEndTime);
				            	 return activityEndTime.format("yyyy-MM-dd hh:mm:ss");
							}else{return "";} 
						}},
						{display:'技术服务费',name:'feeRate'},
		                { display: "操作", name: "OPER",align: "center",width: 150, render: function(rowdata, rowindex) {
							var html = [];
						    html.push("<a href=\"javascript:seeActivity(" + rowdata.id + ");\">查看活动</a>&nbsp;&nbsp;"); 
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
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label"  >特卖ID：</div>
			<div class="search-td-condition" >
				<input type="text" id = "activityId" name="activityId" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >活动名称：</div>
			<div class="search-td-condition" >
				<input type="text" id = "name" name="name" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >商家：</div>
			<div class="search-td-condition" >
				<input type="text" id = "shortName" name="shortName" >
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >商家序号：</div>
			<div class="search-td-condition" >
				<input type="text" id = "mchtCode" name="mchtCode" >
			</div>
			</div>
		</div>
			
		<div class="search-tr"  > 
 			<div class="search-td">
			<div class="search-td-label">活动状态：</div>
			<div class="search-td-condition" >
				<select id="actStatus" name="actStatus">
					<option value="">不限</option>
					<option value="1">待开始</option>
					<option value="2">预热中</option>
					<option value="3">活动中</option>
					<option value="4">已结束</option>
				</select>
			</div>
			</div>
			
			<div class="search-td">
			<div class="search-td-label" style="float:left;">日期：</div>
			<div class="l-panel-search-item" >
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
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			</div>
			
			<div class="search-td">
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
			</div>
			
			<div class="search-td">
			<div class="search-td-label"  >运营专员：</div>
			<div class="search-td-combobox-condition" >
				<select id="platformContactId" name="platformContactId">
					<option value="">请选择</option>
					<c:forEach items="${platformContacts}" var="list">
						<option value="${list.id}">${list.contactName}</option>
					</c:forEach>
				</select>
			</div>
			</div>
		</div>
		
		<div class="search-tr"  > 			
			<div class="search-td">
			<div class="search-td-label">类目：</div>
			<div class="search-td-combobox-condition" >
				<select id="productTypeId" name="productTypeId">
					<option value="">请选择</option>
					<c:forEach items="${productTypes}" var="list">
						<option value="${list.id}">${list.name}</option>
					</c:forEach>
				</select>
			</div>
			</div>
			
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
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
