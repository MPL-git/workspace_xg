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
   
	function edit(id) {
		$.ligerDialog.open({
			height: 300,
			width: 600,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/coupon/addcouponcombinereclimit.shtml?Id=" + id ,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	
	function delFavorites(id) {
		$.ligerDialog.confirm("是否删除？", function (yes) 
		{ 
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/coupon/CouponCombinereclimitdata.shtml?id=" + id,
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
	
	
	 var listConfig={
		     url:"/coupon/couponcombinereclimitdata.shtml",
		     btnItems:[{ text: '创建优惠劵组', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/coupon/addcouponcombinereclimit.shtml", seqId:"", width: 600, height: 300}],
		     listGrid:{ columns: [
								{ display: '优惠劵组名称', name:'name',width: 200},
								{ display: '优惠劵组ID', name:'couponIdgroup',width: 200},
								{ display: '限领',name:'',width: 200,render:function(rowdata, rowindex){
									if (rowdata.reclimittypeDesc=='每人每天限领1张') {
										return rowdata.reclimittypeDesc;
									}else if (rowdata.reclimittypeDesc=='每人每月限领1张') {
										return rowdata.reclimittypeDesc;
									}else {
									   return rowdata.reclimittypeDesc+rowdata.recEach;
										
									}
								}},
					            { display: '创建时间', name: 'createDate',width: 200, render: function(rowdata, rowindex) {
		    	  					var date=new Date(rowdata.createDate);
		    	  					return date.getFullYear()+"-"+("0" + (date.getMonth() + 1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2)+" "+("0"+date.getHours()).slice(-2)+":"+("0"+date.getMinutes()).slice(-2)+":"+("0"+date.getSeconds()).slice(-2);
		      					}},
		      					{ display: '创建人',name:'staffName',width: 200},
		      					{display:'管理',name:'', align:'center', isSort:false, width:180, render: function(rowdata, rowindex) {
									 var html = [];
										 html.push("<a href=\"javascript:edit("+ rowdata.id +");\">编辑</a>&nbsp&nbsp");
										 html.push("<a href=\"javascript:delFavorites(" + rowdata.id + ");\">删除</a>");
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
			<div class="search-td-label" style="float:left;">优惠劵组名称：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="name" name="name">
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label" style="float:left;">优惠劵ID：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="couponId" name="couponId">
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
