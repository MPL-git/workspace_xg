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
 	var viewerFlag = true;
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
		
	 });
 
	 
	 //放大图片
	 function viewerPic(feedbackcontentid, src) {
		var url = "${pageContext.request.contextPath}/staffOpinionFeedback/staffopinionfeedbackpicPicList.shtml";
		viewerFlag = true;
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {feedbackcontentid : feedbackcontentid},
			timeout : 30000,
			success : function(data) {
				if(data && data.length > 0){
					var ind = 0;
					for (var i=0;i<data.length;i++) {
						if(data[i].feedbackContentPic == src) {
							ind = i;
						}
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].feedbackContentPic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].feedbackContentPic+'" alt=""></li>');
					}
					viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {
						url: 'data-original',
						hide: function(){
							$("#viewer-pictures").hide();
						},
						viewed: function() {
							if(viewerFlag) {
								viewerPictures.view(ind);
								viewerFlag = false;
							}
						}
					});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
	 }
	 
	 
	 //详情
	 function viewDetail(id) {
		 $.ligerDialog.open({
				height: 600,
				width: 950,
				title: "详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/staffOpinionFeedback/view.shtml?id="+id,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
 	 var listConfig={ 
	      url:"/staffOpinionFeedback/data.shtml",
	      btnItems:[{ text: '添加', icon: 'add', id:'add', dtype:'win', click: itemclick, url:"/staffOpinionFeedback/edit.shtml", seqId:"", width: 950, height: 600 }],
	      listGrid:{ columns: [
							{display:'ID',name:'id', align:'center', isSort:false, width:100},   															        
			                {display:'反馈内容',name:'', align:'left', isSort:false, width:410,render:function(rowdata, rowindex) {
								var html = [];
								var str = rowdata.feedbackContent;
								if(str) {							
									html.push("<div style='margin-left: 5px;'>"+str+"</div>");
								}
								if(rowdata.staffFeedbackContentPic) {
									var staffFeedbackContentPic = rowdata.staffFeedbackContentPic.split(",");
									for(var i=0;i<staffFeedbackContentPic.length;i++) {
										html.push("<img style='margin-left: 5px;margin-bottom: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+staffFeedbackContentPic[i]+"' width='60' height='60' onclick='viewerPic("+rowdata.id+", \""+staffFeedbackContentPic[i]+"\")'>");
									}
								}
																																					
								return html.join("");
							}},
						   {display:'所属部门',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    if (rowdata.organizationId=='1') {
								return "总经办";
							}
							if (rowdata.organizationId=='2') {
								return "人事部";
							}
						    if (rowdata.organizationId=='3') {
								return "财务部";
							}
							if (rowdata.organizationId=='4') {
								return "法务部";
							}
							if (rowdata.organizationId=='5') {
								return "产品部";
							}
							if (rowdata.organizationId=='6') {
								return "技术部";
							}
							if (rowdata.organizationId=='7') {
								return "商品部";
							}
							if (rowdata.organizationId=='8') {
								return "招商部";
							}
							if (rowdata.organizationId=='9') {
								return "客服部";
							}
							if (rowdata.organizationId=='10') {
								return "策划部";
							}
							if (rowdata.organizationId=='11') {
								return "设计部";
							}
							if (rowdata.organizationId=='12') {
								return "文案部";
							}
							if (rowdata.organizationId=='13') {
								return "推广部";
							}
							if (rowdata.organizationId=='14') {
								return "其他";
							}
						   
						   }},
						   {display:'状态',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    if (rowdata.sridNum>0) {
								return "已回复"+"("+rowdata.sridNum+")";
							}
							if (rowdata.sridNum<=0) {
								return "未回复";
							}
						   
						   }},
			               {display:'创建时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	var html = [];
								if(rowdata.createDate != null && rowdata.createDate != '' ) {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
			                }},	
							{display:'操作',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='viewDetail(" + rowdata.id + ");'>详情</a></br>");												
							    return html.join("");
							}}
							
			         ], 			    
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 						
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
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id" >
					</div>
				</div>
			   <div class="search-td">
			 	<div class="search-td-label" >发送给谁：</div>
			 	<div class="search-td-condition" >
				<select id="organizationId" name="organizationId" class="querysel">
					<option value=''>请选择...</option>
				    <option value='1'>总经办</option>
				    <option value='2'>人事部</option>
					<option value='3'>财务部</option>
					<option value='4'>法务部</option>
					<option value='5'>产品部</option>
					<option value='6'>技术部</option>
					<option value='7'>商品部</option>
					<option value='8'>招商部</option>
					<option value='9'>客服部</option>
					<option value='10'>策划部</option>
					<option value='11'>设计部</option>
					<option value='12'>文案部</option>
					<option value='13'>推广部</option>
					<option value='14'>其他</option>
					<%-- <c:forEach var="list" items="${sysOrganizationList}">
					   <option value="${list.orgId}">${list.orgName}</option>						
					</c:forEach> --%>
				</select>
				</div>
		 	   </div>
		 	  <div class="search-td">
			 	<div class="search-td-label" >状态：</div>
			 	<div class="search-td-condition" >
				<select id="reply" name="reply" class="querysel">
					<option value="">请选择...</option>
					<option value="1">已回复</option>
					<option value="2">未回复</option>					
				</select>
				</div>
		 	  </div>	 	   	 	  	 	   					 
			</div>
			
			<div class="search-tr"  >
			 <div class="search-td">
				<div class="search-td-label" style="float:left;"  >创建日期：</div>
				<div class="l-panel-search-item" >
					 <input type="text" class="dateEditor" id="startCreateDate" name="startCreateDate" >
				 </div>
				 <div class="l-panel-search-item" >&nbsp;&nbsp;&nbsp;&nbsp;至：</div>
			  </div>
			  <div class="search-td">
				   <div class="l-panel-search-item" >
						<input type="text" class="dateEditor" id="endCreateDate" name="endCreateDate" >
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
