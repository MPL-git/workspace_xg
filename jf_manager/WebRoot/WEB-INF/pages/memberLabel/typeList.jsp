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
		$("#update_begin").ligerDateEditor({
			showTime : false,
			labelWidth : 150,
			labelAlign : 'left'
		});
	});
	
	$(function() {
		$("#update_end").ligerDateEditor({			
			showTime : false,
			labelWidth : 150,
			labelAlign : 'left'
		});
	});
	
	
	function edit(id) {
		$.ligerDialog.open({
			height: 450,
			width: 750,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/memberLabel/typeAdd.shtml?Id=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	
	function typeStatus(id,status) {
		if (status=='0') {
		$.ligerDialog.confirm("是否作废？", function (yes) 
		{ 
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/memberLabel/updatememberLabelType.shtml?id="+id+"&status="+status,
					secureuri : false,
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if ("0000" == data.returnCode) {
							listModel.gridManager.reload();
						}else{
							$.ligerDialog.error(data.returnMsg);
						}
						
					},
					error : function() {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		});
			
	  }else if (status=='1') {
		  $.ligerDialog.confirm("是否恢复？", function (yes) 
					{ 
						if(yes){
							$.ajax({
								url : "${pageContext.request.contextPath}/memberLabel/updatememberLabelType.shtml?id="+id+"&status="+status,
								secureuri : false,
								dataType : 'json',
								cache : false,
								async : false,
								success : function(data) {
									if ("0000" == data.returnCode) {
										listModel.gridManager.reload();
									}else{
										$.ligerDialog.error(data.returnMsg);
									}
									
								},
								error : function() {
									$.ligerDialog.error('操作超时，请稍后再试！');
								}
							});
						}
					});
	      }
	}
	
	
	 var listConfig={
		     url:"/memberLabel/typeListdata.shtml",
		     btnItems:[{ text: '添加标签类型', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/memberLabel/typeAdd.shtml", seqId:"", width: 750, height: 450}],
		     listGrid:{ columns: [
								{ display: '标签类型', name:'labelTypeName',width: 180},
								{ display: '标签选项', name:'labelName',width: 230},
								{ display: '备注',name:'remarks',width: 230},
								{ display: '状态',name:'',width:100,render:function(rowdata, rowindex){
									if (rowdata.status=='0') {
										return "作废";
									}else if (rowdata.status=='1') {
										return "正常";
									}
								}},
								{ display: '创建人',name:'staffName',width:100},
					            { display: '更新时间', name: '',width: 200, render: function(rowdata, rowindex) {
		    	  					if (rowdata.updateDate!=null) {
		    	  						var updateDate=new Date(rowdata.updateDate);
		    			            	return updateDate.format("yyyy-MM-dd hh:mm:ss");
								   }
		      					}},
		      					{display:'操作',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
									 var html = [];
									 if (rowdata.status=='0') {
									     html.push("<a href=\"javascript:typeStatus("+ rowdata.id +",'1');\">恢复</a>&nbsp&nbsp");
								     }else {
										 html.push("<a href=\"javascript:edit("+ rowdata.id +");\">编辑</a>&nbsp&nbsp");
										 html.push("<a href=\"javascript:typeStatus(" + rowdata.id + ",'0');\">作废</a>");
											
									 }
									  return html.join("");
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
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr" > 
			<div class="search-td">
			<div class="search-td-label" style="float:left;">标签类型：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="labelTypeName" name="labelTypeName">
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label" >状态：</div>
			<div class="search-td-condition" >
			   <select id="status" name="status">
				<option value="">请选择</option>
				<option value="1">正常</option>
				<option value="0">已作废</option>
			  </select>
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label" style="float:left;">创建日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="update_begin" name="update_begin" value=""/>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
			</div>
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="update_end" name="update_end" value=""/>
			</div>
			</div>
			 <div class="search-td-search-btn">
				<div id="searchbtn">搜索</div>
			</div>
			</div>
			</div>
		 <div id="maingrid" style="margin: 0; padding: 0"></div>
   </form>
   <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
  </body>
</html>
