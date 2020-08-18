<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript">

 function toestablishMailSee(id){
		$.ligerDialog.open({
			height: 800,
			width: 800,
			title: "审核站内信",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/platformMsg/toestablishMailSee.shtml?establishmailId="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		}); 
	 }
 
 function seeToexamineMail(id){
		$.ligerDialog.open({
			height: 800,
			width: 800,
			title: "查看站内信",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/platformMsg/seeToexamineMail.shtml?establishmailIdsee="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		}); 
	 }
 
 var listConfig={
     url:"/platformMsg/examineMaildata.shtml",
      
     btnItems:[],
     listGrid:{ columns: [
                       { display: '序号', name:'id', width: 100},
                       { display: '更新日期', name:'updateDate',width:150,render:function(rowdata, rowindex){
                    	   var updateDate=new Date(rowdata.updateDate);
		            	   return updateDate.format("yyyy-MM-dd hh:mm:ss");
                       }},
                       { display: '站内信类型', name:'msgType', width:100,render: function(rowdata,roindex){
                    	 if (rowdata.msgType=='A1') {
							return "退款";
						}else if (rowdata.msgType=='A2') {
							return "退货";
						}else if (rowdata.msgType=='A3') {
							return "换货";
						}else if (rowdata.msgType=='A4') {
							return "投诉";
						}else if (rowdata.msgType=='A5') {
							return "违规";
						}else if (rowdata.msgType=='A6') {
							return "活动";
						}else if (rowdata.msgType=='TZ') {
							return "通知";
						}
                       }},
                       { display: '标题',name:'title', width:200},
                       { display: '内容', width:100,render: function(rowdata, rowindex) {
                    	   var html = [];
						   html.push("<a href=\"javascript:seeToexamineMail(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;"); 
						   return html.join("");
		               }},
                       { display: '驳回原因',name:'auditRemarks', width:200},
                       { display: '状态',name:'status', width:100,render:function(rowdata,roindex){
                    	 if (rowdata.status=='1') {
							 return "已提审";
						}else if (rowdata.status=='2') {
							 return "审核通过";
						}else if (rowdata.status=='3') {
							 return "审核未通过";
						}else if (rowdata.status=='0') {
							 return "未提审";
						}else if (rowdata.status=='4') {
							 return "审核通过";
						}
                       }},
                       { display: '操作',name:"OPER", width:100,render:function(rowdata, rowindex){
                    	   var html = []; 
						    if(rowdata.status=='1'){
							  html.push("<a href=\"javascript:toestablishMailSee(" + rowdata.id +");\">审核</a>&nbsp;&nbsp;");
						    }
						    return html.join("");
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label" style="float:left;">创建时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="updateBegin" name="updateBegin" value="${updateBegin}"/>
				<script type="text/javascript">
					$(function() {
						$("#updateBegin").ligerDateEditor({
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
			<div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至</div>
			</div>		
			<div class="search-td">
			<div class="l-panel-search-item">
				<input type="text" id="updateEnd" name="updateEnd" value="${updateEnd}"/>
				<script type="text/javascript">
					$(function() {
						$("#updateEnd").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							labelAlign : 'left'
						});
					});
				</script>	
			</div>
			</div>
			<div class="search-td">
			<div class="search-td-label">商家序号：</div>
			       <div class="search-td-combobox-condition">
						<input type="text" id="mcht_code" name="mchtCode">
					</div>
			</div>
			 <div class="search-td">
			 <div class="search-td-label" >状态：</div>
			 <div class="search-td-condition" >
 				<select id="examineMailstatus" name="examineMailstatus" style="width:100px;">
 					    <option value="">请选择...</option>
 					    <option value="0">未提审</option>
                        <option value="1">已提审</option>
                        <option value="2">审核通过</option>
                        <option value="3">审核未通过</option>
				</select>
		 	 </div>
			 </div>
		</div>	
	
		<div class="search-tr">
			<div class="search-td">
			 <div class="search-td-label" >站内信类型：</div>
			 <div class="search-td-condition" >
 				<select id="examineMailType" name="examineMailType" style="width:100px;">
 					    <option value="">请选择...</option>
 					    <option value="A1">退款</option>
                        <option value="A2">退货</option>
                        <option value="A3">换货</option>
                        <option value="A4">投诉</option>
                        <option value="A5">违规</option>
                        <option value="A6">活动</option>
                        <option value="TZ">通知</option>
				</select>
		 	 </div>
			 </div>	
			
			<div class="search-td-search-btn">
				<div id="searchbtn">搜索</div>
			</div>			
		</div>		
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>