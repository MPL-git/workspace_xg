<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
	});	
	
   var viewerPictures;
  $(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
  });
	
  function viewerPic(imgPath){//点击放大图片
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();	
 }
 
 
    var post_flag = false;//添加重复提交时设置一个变量来控制是否进入AJAX过程
 
    //添加
	function addactivityareaid(activityareaid) {
	    if(post_flag) return; //如果正在提交则直接返回，停止执行
	       post_flag = true;//标记当前状态为正在提交状态
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/addactivityBrandgroupactivitylist.shtml',
			data: {activityareaid : activityareaid, activitybrandgroupid : ${activitybrandgroupid}},
			dataType: 'json',
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
				     post_flag =false;//在提交成功之后将标志标记为可提交状态
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
			  commUtil.alertError("系统异常请稍后再试");
			}
		});
	} 
	

 	var listConfig={
	      url:"/activityNew/activityareatdata.shtml",
	      btnItems:[{text: '批量添加',icon: 'add',click: function() {
				var data = listModel.gridManager.getSelectedRows();
	           	if (data.length == 0) {
	           		commUtil.alertError('请选择行！');
	           	}else {
	              		var str = "";                         
	               	$(data).each(function() {    
	             	  		if(str=='') {
	             		  		str = this.activityAreaId;
	             	  		}else {
	             		  		str += ","+ this.activityAreaId;
	             	  		}
	               	});
	               	addactivityareaid(str); 
	           	}
            }}
			],
	      listGrid:{ columns: [
				            {display:'特卖ID',name:'id', align:'center', isSort:false, width:80},
				            {display:'入口图',align:'center',isSort:false,width:180,render:function(rowdata, rowindex){
							     var html = [];
							     if (rowdata.entryPic !=null && rowdata.entryPic !='' ) {
									 html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.entryPic+"' style='margin: 5px;' width='160' height='80' onclick='viewerPic(this.src)'>");									
								}
				                 return html.join("");   	
							}},
							{display:'活动名称',name:'name', align:'center', isSort:false, width:180},
	                        {display:'利益点',name:'benefitPoint', align:'center', isSort:false, width:160},
	                        {display:'促销方式',name:'preferentialTypeDesc', align:'center', isSort:false, width:100},
	                        {display:'店铺名称',name:'shopName', align:'center', isSort:false, width:180},
	                        {display:'一级类目',name:'productTypeName', align:'center',isSort:false,width:100},                 
	                        {display:'活动开始时间',name:'activityBeginTime', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityBeginTime == null || rowdata.activityBeginTime == '' ) {
									return "";
								}else{
									var activityBeginTime = new Date(rowdata.activityBeginTime);
									return activityBeginTime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }},
	                        {display:'活动结束时间',name:'activityEndTime', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityEndTime == null || rowdata.activityEndTime == '' ) {
									return "";
								}else{
									var activityEndTime = new Date(rowdata.activityEndTime);
									return activityEndTime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }}, 
	                        {display:'活动状态',name:'activityStatusDesc', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								  if(new Date(rowdata.preheatTime) <= new Date() && new Date(rowdata.activityBeginTime) > new Date()) {
										html.push("预热中");
									}else if(new Date(rowdata.activityBeginTime) <= new Date() && new Date(rowdata.activityEndTime) >= new Date()) {
										html.push("活动中");
									}
							    return html.join("");
							}},
							{display:'操作',align:'center',isSort:false,width:100, render:function(rowdata, rowindex) {
					           var html = []; 
					           html.push("<a href=\"javascript:addactivityareaid(" + rowdata.activityAreaId + ");\">【添加】</a>");						     
					           return html.join("");
				            }}                  
			         ], 
	                 showCheckbox :true,  //不设置默认为 true
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
	<form id="dataForm" runat="server">
	<div id="toptoolbar"></div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >特卖ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="id" name="id" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >一级类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeId" name="productTypeId" style="width: 135px;" >
							<option value="">请选择...</option>
							    <c:forEach var="productTypeList" items="${productTypeList}">
									<option value="${productTypeList.id}">${productTypeList.name}</option>
								</c:forEach>
						</select>
				 	 </div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">活动开始日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="activityBeginTime" name="activityBeginTime" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="activityBeginTimes" name="activityBeginTimes" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">活动结束日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="activityEndTime" name="activityEndTime" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="activityEndTimes" name="activityEndTimes" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			</div>	
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >活动状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="12">预热中</option>
							<option value="13">活动中</option>
						</select>
				 	</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
