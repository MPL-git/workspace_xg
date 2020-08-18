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
 
 var listConfig={
      url:"/activityArea/activityAreDateScheduleData.shtml",
//       btnItems:[{ text: '创建单品活动', icon: 'add', dtype:'win',  click: itemclick, url:'/activityArea/createSingleProductActivity.shtml', seqId:"", width : 800, height:800}],
      listGrid:{ columns: [
                        {display:'星期',name:'weekSeveral',render:function(rowdata, rowindex){
                        	if(rowdata.weekSeveral==1){
                        		if(rowdata.time==rowdata.dateTime){
                            		return "<samp style='color: red;'>星期日</samp>";
                            	}else{
	                        		return "星期日";
                            	}
                        	}
                        	if(rowdata.weekSeveral==2){
                        		if(rowdata.time==rowdata.dateTime){
                            		return "<samp style='color: red;'>星期一</samp>";
                            	}else{
                        			return "星期一";
                            	}
                        	}
                        	if(rowdata.weekSeveral==3){
                        		if(rowdata.time==rowdata.dateTime){
                            		return "<samp style='color: red;'>星期二</samp>";
                            	}else{
                        			return "星期二";
                            	}
                        	}
                        	if(rowdata.weekSeveral==4){
                        		if(rowdata.time==rowdata.dateTime){
                            		return "<samp style='color: red;'>星期三</samp>";
                            	}else{
                        			return "星期三";
                            	}
                        	}
                        	if(rowdata.weekSeveral==5){
                        		if(rowdata.time==rowdata.dateTime){
                            		return "<samp style='color: red;'>星期四</samp>";
                            	}else{
                        			return "星期四";
                            	}
                        	}
                        	if(rowdata.weekSeveral==6){
                        		if(rowdata.time==rowdata.dateTime){
                            		return "<samp style='color: red;'>星期五</samp>";
                            	}else{
                        			return "星期五";
                            	}
                        	}
                        	if(rowdata.weekSeveral==7){
                        		if(rowdata.time==rowdata.dateTime){
                            		return "<samp style='color: red;'>星期六</samp>";
                            	}else{
                        			return "星期六";
                            	}
                        	}
                        }},
                        {display:'月份',name:'month',render:function(rowdata, rowindex){
                        	if(rowdata.time==rowdata.dateTime){
                        		return "<samp style='color: red;'>"+rowdata.month+"月</samp>";
                        	}else{
	                        	return rowdata.month+"月";
                        	}
                        }},
                        {display:'日',name:'day',render:function(rowdata, rowindex){
                        	if(rowdata.time==rowdata.dateTime){
                        		return "<samp style='color: red;'>"+rowdata.day+"日</samp>";
                        	}else{
	                        	return rowdata.day+"日";
                        	}
                        }},
                        {display:'活动进行中',name:'activityNum',render:function(rowdata, rowindex){
                        	if(rowdata.time==rowdata.dateTime){
                        		if(rowdata.activityNum>0){
	                        		return "<samp style='color: red;'>"+rowdata.activityNum+"</samp>";
                        		}else{
	                        		return "<samp style='color: red;'>0</samp>";
                        		}
                        	}else{
                        		if(rowdata.activityNum>0){
	                        		return rowdata.activityNum;
                        		}else{
	                        		return "0";
                        		}
                        	}
                        }},
                        {display:'当天开始',name:'dayStart',render:function(rowdata, rowindex){
                        	if(rowdata.time==rowdata.dateTime){
                        		if(rowdata.dayStart>0){
	                        		return "<samp style='color: red;'>"+rowdata.dayStart+"</samp>";
                        		}else{
	                        		return "<samp style='color: red;'>0</samp>";
                        		}
                        	}else{
                        		if(rowdata.dayStart>0){
	                        		return rowdata.dayStart;
                        		}else{
	                        		return "0";
                        		}
                        	}
                        }},
                        {display:'当天结束',name:'endDay',render:function(rowdata, rowindex){
                        	if(rowdata.time==rowdata.dateTime){
                        		if(rowdata.endDay>0){
	                        		return "<samp style='color: red;'>"+rowdata.endDay+"</samp>";
                        		}else{
	                        		return "<samp style='color: red;'>0</samp>";
                        		}
                        	}else{
                        		if(rowdata.endDay>0){
	                        		return rowdata.endDay;
                        		}else{
	                        		return "0";
                        		}
                        	}
                        }}
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
<!-- 	<div class="l-loading" style="display: block" id="pageloading"></div> -->
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="l-panel-search-item search_right">时间：</div>
				<div class="l-panel-search-item">
					<input type="text" id="dataTime" name="dataTime" />
					<script type="text/javascript">
						$(function() {
							$("#dataTime").ligerDateEditor( {
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script> 
				</div>
					
				<!-- <div class="l-panel-search-item">至 </div>
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
				</div> -->
				<div class="search-td">
					<div class="search-td-search-btn">
						<div id="searchbtn" >搜索</div>
					</div>
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
