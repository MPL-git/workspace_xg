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

function addmchtSettledApplyRecord(id) {
	$.ligerDialog.open({
		height: $(window).height() - 360,
		width: $(window).width() - 900,
		title: "开发记录",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/addmchtSettledApplyRecord.shtml?settledApplyId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function mchtSettledApplyRecordselect(id) {
	$.ligerDialog.open({
		height: $(window).height() - 360,
		width: $(window).width() - 900,
		title: "查看更多",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/mchtSettled/mchtSettledApplyRecordselect.shtml?settledApplyId=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}


 var listConfig={
	  
     url:"/mchtSettled/mchtSettledAPPlyRecord/data.shtml",
   
     btnItems:[{ text: '添加商家', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/mchtSettled/editmchtSettledAPPlyRecord.shtml", seqId:"", width: 800, height: 700 }],
     			     			   
     listGrid:{ columns: [
                         { display: '创建日期', width: 120, render: function(rowdata, rowindex) {
			            	var createDate=new Date(rowdata.createDate);
			            	return createDate.format("yyyy-MM-dd");
			         	}},
						{ display: '招商专员', name:'platformContactName',width: 120},		       
			            { display: '客户来源', width: 120,render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.customerSource == "1"){
			            		html.push("主管分配");
			            	}else if(rowdata.customerSource == "2"){
			            		html.push("个人开发");
			            	}
			            	return html.join("");
			         	}},
			         	{ display: '入驻类型', width: 80,render: function(rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.settledType == "1"){
			            		html.push("企业公司");
			            	}else if(rowdata.settledType == "2"){
			            		html.push("个体商户");
			            	}
			            	return html.join("");
			         	}},
		                { display: '公司名称', name: 'companyName',width: 160},
		                { display: '经营品类', name: 'productTypeMain',width: 100},
		                { display: '品牌名称', name: 'productBrandMain',width: 100},
		                { display: '在做渠道店铺链接', width:230,render:function(rowdata, rowindex){
		                 var html=[];
		                 if (rowdata.tmallShop !=null && rowdata.tmallShop !='') {
							 html.push("天猫："+rowdata.tmallShop+"<br>");
						 }
						 if (rowdata.taobaoShop !=null && rowdata.taobaoShop !='') {
							 html.push("淘宝："+rowdata.taobaoShop+"<br>");
						 }
						 if (rowdata.jdShop !=null && rowdata.jdShop !='') {
							 html.push("京东："+rowdata.jdShop+"<br>");
						 }
						 if (rowdata.vipsShop !=null && rowdata.vipsShop !='') {
							 html.push("唯品会："+rowdata.vipsShop+"<br>");
						 }
						 if (rowdata.otherShop !=null && rowdata.otherShop !='') {
							 html.push("其他："+rowdata.otherShop+"<br>");
						 }
		                  return html.join("");
		                }},
		                { display: '联系人', name: 'contactName',width: 120},
		                { display: '职务', name: 'contactJob',width: 120},		   
		                { display: '手机',  name: 'phone',width: 120},
		                { display: 'QQ',  name: 'qq',width: 120},
		                { display: '微信号',  name: 'wechat',width:130},
		                { display: '公司座机',  name: 'companyTel',width:120},
		                { display: '企业邮箱',  name: 'email',width:160},
		                { display: '开发情况记录', width:200,render:function(rowdata, rowinde){
		                  var html=[];
		                  if (rowdata.createDateMax !=null && rowdata.createDateMax !='') {
		                  var createDateMax=new Date(rowdata.createDateMax);       
		                  html.push("["+createDateMax.format("yyyy-MM-dd hh:mm")+"]"+rowdata.recoRd+"<br>");
		                  html.push("<span style='text-align:center'><a href=\"javascript:mchtSettledApplyRecordselect(" + rowdata.id + ");\">查看更多</a></span>");												
						 }else{
						   html.push("");
						 }
						  
		                  return html.join("");
		                }},
		                { display: '操作', name: "OPER", align: "center", width:120,render: function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:addmchtSettledApplyRecord(" + rowdata.id + ");\">【添加记录】</a>");											
						    return html.join("");
						}}
		                ],
                 showCheckbox : true,  //不设置默认为 true
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
			<div class="search-td-label" style="float:left;">创建日期：</div>
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
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
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
			<div class="search-td-label" style="float:left;">客户来源：</div>
			<div class="search-td-condition" >
				<select id="customerSource" name="customerSource" style="width: 150px;">
					<option value="">请选择...</option>
					<option value="1">主管分配</option>
					<option value="2">个人开发</option>
				</select>
		 	 </div>
			 </div>
			<div class="search-td">
			<div class="search-td-label" >公司名称：</div>
			<div class="search-td-condition" >
				<input type="text" id="companyName" name="companyName" >
			</div>
			</div>
		</div>
		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" style="float:left;">入驻类型：</div>
				<div class="search-td-condition" >
					<select id="settledType" name="settledType" style="width: 150px;">
						<option value="">请选择</option>
						<option value="1">企业公司</option>
						<option value="2">个体商户</option>
					</select>
			 	 </div>
			 </div>
			 <div class="search-td-search-btn" style="display: inline-flex;">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>	
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
		
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>