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
    
   <title></title>
   <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript">	

	$(function() {
		 $(".dateEditor").ligerDateEditor({
				showTime : true,
				format : "yyyy-MM-dd",
				labelAlign : 'left',
				width : 135
			 });
	});
	
	var labelGroupIds = new Array();
	$(function() {
		var labelGroupIdsStr=$(window.parent.document).find("#labelGroupIds").val();
		if(labelGroupIdsStr){
			labelGroupIds = labelGroupIdsStr.split(",");
		}
	});
	
	 //添加
	 function add(memberLabelId){
		var labelGroupName=$("#labelGroupNameDiv"+memberLabelId).text();
		var html = [];
		html.push('<p style="margin: 5px 0px;">');
		html.push('<a class="labelGroupId" href="javascript:void(0);" onclick="memberView('+memberLabelId+');" >'+labelGroupName+'</a>');
		html.push('<input type="button" style="margin-left:15px;color: #FFFFFF;background-color: #D9534F;border: none;width: 25px;height: 20px;border-radius: 3px;" onclick="delMemberLabelGroup(this);" placeholder="'+memberLabelId+'" value="-">');
		html.push('</p>');
		$(window.parent.document).find("#addMemberLabelGroup-div").append(html.join(""));
		if(!$(window.parent.document).find("#labelGroupIds").val()){
			$(window.parent.document).find("#labelGroupIds").val(memberLabelId);
		}else{
			$(window.parent.document).find("#labelGroupIds").val($(window.parent.document).find("#labelGroupIds").val()+","+memberLabelId);
		}
		$("#labelGroupNameDiv"+memberLabelId).parent().html("已添加");
	 }
	
	 var listConfig={
			 url:"/memberLabelGroup/getMemberLabelGroupList.shtml?memberLabelGroupIds=0&status=1",
		     listGrid:{ columns: [
								{ display: '分组名称', name:'labelGroupName',width: 230},
								{ display: '分组包含用户数', name:'memberCount',width:130},
								{ display: '分组条件',name:'labelTypeName',width: 230},
								{ display: '备注',name:'remarks',width: 230},
								{ display: '创建人',name:'staffName',width:100},
					            { display: '创建时间', name: '',width: 200, render: function(rowdata, rowindex) {
		    	  					if (rowdata.createDate!=null && rowdata.createDate!="") {
		    	  						var createDate=new Date(rowdata.createDate);
		    			            	return createDate.format("yyyy-MM-dd hh:mm:ss");
								   }
		      					}},
		      					{display:'操作',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
		      						if(labelGroupIds.indexOf (rowdata.id+"") != -1){
		      							return "已添加";
		      						}else{
										return "<a href=\"javascript:add("+ rowdata.id +");\">添加</a>&nbsp&nbsp<div id='labelGroupNameDiv"+rowdata.id+"' style = 'display:none;'>"+rowdata.labelGroupName+" "+new Date(rowdata.updateDate).format("yyyy-MM-dd")+"</div>";
		      						}
								}},
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
	<div id="labelGroupIdsDiv" style = "display:none;">${labelGroupIds}</div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr" > 
			<div class="search-td">
				<div class="search-td-label"  >分组名称：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id="labelGroupName" name="labelGroupName" >
				</div>
			</div>
			<div class="search-td" style="width: 40%;">
				<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" style="width: 135px;" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" style="width: 135px;" />
				</div>
				 <div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>
		</div>
		</div>
		 <div id="maingrid" style="margin: 0; padding: 0"></div>
   </form>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
  </body>
</html>
