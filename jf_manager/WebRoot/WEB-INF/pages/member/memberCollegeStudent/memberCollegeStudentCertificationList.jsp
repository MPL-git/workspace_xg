<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>

<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/star.css" rel="stylesheet" type="text/css" />

 <script type="text/javascript">

 var viewerPictures;
 
 $(function() {	
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
 		$("#viewer-pictures").hide();
 	  }});
		
 });
 
 function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		$("#viewer-pictures").show();
		viewerPictures.show();
	}
 
 
 function viewDetail(id) {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 100,
			title: "会员资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/member/info/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 
 	 var listConfig={ 
	      url:"/member/memberCollegeStudentCertificationdata.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
					
						    {display:'会员ID',name:'memberId', align:'center', isSort:false, width:150},
						    {display:'昵称',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	var html = []; 
						    	if (rowdata.memberNick!=null) {
								    html.push("<a href=\"javascript:viewDetail(" + rowdata.memberId + ");\">"+rowdata.memberNick+"</a>");			
								}
						    	return html.join("");
						    }},
						    {display:'手机号码',name:'', align:'center', isSort:false, width:180,render:function(rowdata, rowindex){
						    	 var html=[];
						    	 if (rowdata.memberMobile!=null) {
									html.push(rowdata.memberMobile.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2'));
								}
						    	 return html.join("");
						    }},
						    {display:'会员状态',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	var html=[];
						    	if (rowdata.memberStatus=='A') {
									html.push("已激活");
								}else if (rowdata.memberStatus=='N') {
									html.push("待激活");
								}else if (rowdata.memberStatus=='P') {
									html.push("黑名单");
								}else if (rowdata.memberStatus=='C') {
									html.push("注销");
								}
						    	return html.join("");
						    }},
						    { display: '注册时间', width: 150, render: function(rowdata, rowindex) {
				            	if (rowdata.memberCreateDate!=null){
					            	var memberCreateDate=new Date(rowdata.memberCreateDate);
					            	return memberCreateDate.format("yyyy-MM-dd hh:mm");	
				            	}
				         	}},
						    { display: '认证图片', width: 180, render: function (rowdata, rowindex){
			                    var h = "";
			                    if (rowdata.pic!=null && rowdata.pic!="") {
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
								}
			                    return h;
			                }},
						    {display:'审核状态',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	var html=[];
						    	if (rowdata.status!=null) {
									if (rowdata.status=='1') {
										html.push("待审核");
									}else if (rowdata.status=='2') {
										html.push("通过");
									}else if (rowdata.status=='3') {
										html.push("不通过");
									}
								}
						    	return html.join("");
						    }},
						    { display: '提交审核时间', width: 150, render: function(rowdata, rowindex) {
				            	if (rowdata.commitDate!=null){
					            	var commitDate=new Date(rowdata.commitDate);
					            	return commitDate.format("yyyy-MM-dd hh:mm");	
				            	}
				         	}},
				         	{display:'审核信息',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
				         		 var html=[];
						    	 if (rowdata.auditBy!=null && rowdata.auditDate!=null) {	
									 var auditDate=new Date(rowdata.auditDate);
									 html.push(rowdata.memberName+"<br>");
									 html.push(auditDate.format("yyyy-MM-dd hh:mm"));				 									 
								}
						    	 return html.join("");
						    }},
				         				         							    							
			         ], 			    
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 						
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      },
	  };
 	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
			<div class="search-td">
					<div class="search-td-label" >会员ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberId" name="memberId" >
					</div>
			 </div>
		     <div class="search-td">
					<div class="search-td-label" >会员昵称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberNick" name="memberNick" >
					</div>
			 </div>
			 <div class="search-td">
					<div class="search-td-label" >手机号码：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberMobile" name="memberMobile" >
					</div>
			 </div>
			 <div class="search-td">
					<div class="search-td-label">审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="1">待审核</option>
							<option value="2">通过</option>
							<option value="3">不通过</option>
						</select>
				  </div>
			 </div>
			
			<div class="search-td-search-btn" >
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
