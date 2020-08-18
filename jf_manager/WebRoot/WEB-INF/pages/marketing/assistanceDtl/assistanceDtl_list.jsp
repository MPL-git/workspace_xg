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
	
}); 
 var listConfig={
	  
     url:"/marketing/assistanceDtl/data.shtml?memberSupplementarySignInId=${memberSupplementarySignInId}",
     listGrid:{ columns: [
			            { display: '微信头像',render: function (rowdata, rowindex) {
		                	return '<img src="${pageContext.request.contextPath}/file_servelt/'+rowdata.weixinHead+'" width="60" height="60">';
						}},
			            { display: '会员ID',name:'memberId'},
			            { display: '会员名称',name:'nick'},
			            { display: '助力情况',name:'proStatusDesc'},
		                { display: '助力完成时间',render: function (rowdata, rowindex) {
		                	if(rowdata.completeDate){
		                		var completeDate = new Date(rowdata.completeDate);
				            	return completeDate.format("yyyy-MM-dd hh:mm:ss");
		                	}
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
			<div class="search-td-label" >会员ID：</div>
			<div class="search-td-condition">
				<input type="text" id="memberId" name="memberId">
			</div>
			</div>
			
			<div class="search-td" style="width: 40%;">
			<div class="search-td-label" style="float:left;">助力时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="complete_date_begin" name="complete_date_begin" />
				<script type="text/javascript">
					$(function() {
						$("#complete_date_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
			<div class="l-panel-search-item">
				<input type="text" id="complete_date_end" name="complete_date_end" />
				<script type="text/javascript">
					$(function() {
						$("#complete_date_end").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>	
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>