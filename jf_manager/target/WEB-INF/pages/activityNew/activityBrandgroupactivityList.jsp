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
		
		$(".l-dialog-close").live("click", function(){
			$("#searchbtn").click();
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
 
	//添加特卖
	function addactivityarealist() {
		$.ligerDialog.open({
			height: $(window).height() - 50,
			width: $(window).width() - 50,
			title : "添加特卖",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/activityNew/addactivityarealist.shtml?activitybrandgroupid=${activitybrandgroupid}",
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	
	//删除
	function delactivitybrandgroupactivity(activitybrandgroupactivityids) {
	    var title="退出";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/activityNew/delactivityBrandGroupActivity.shtml',
			data: {activitybrandgroupactivityids : activitybrandgroupactivityids},
			dataType: 'json',
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	   }
	  });
	 }
 	var listConfig={
	      url:"/activityNew/activitybrandgroupactivitylist.shtml",
	      btnItems:[{text: '添加特卖',icon: 'add',click: function() {
                addactivityarealist();
            }},
			{line:true},
          	{text: '批量退出',icon: 'delete',click: function() {
  			  	var data = listModel.gridManager.getSelectedRows();
               	if (data.length == 0) {
               		commUtil.alertError('请选择行！');
               	}else {
                    var str = "";                         
                   	$(data).each(function() {    
                 	  	if(str=='') {
                 		  str = this.id ;
                 	     }else {
                 		  		str += ","+ this.id ;
                 	  	}
                   	});
                   	delactivitybrandgroupactivity(str); 
               	}
  			}}
			],
	      listGrid:{ columns: [
							{display:'特卖ID',name:'activityId', align:'center',isSort:false, width:80},
							{display:'入口图',name:'activityEntryPic', align:'center',isSort:false,width:180,render:function(rowdata, rowindex){
							    var html = [];
				                if(rowdata.activityEntryPic != null && rowdata.activityEntryPic != '' ) {
				                 html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.activityEntryPic+"' style='margin: 5px;' width='160' height='80' onclick='viewerPic(this.src)'>");
				                }
				                return html.join("");			
							}},
							{display:'活动名称',name:'activityName', align:'center',isSort:false,width:180},
	                        {display:'利益点',name:'activityareaBenefitPoint', align:'center',isSort:false,width:160},
	                        {display:'促销方式',name:'activityareaPreferentialType', align:'center',isSort:false,width:100},
	                        {display:'店铺名称',name:'activityShopName', align:'center',isSort:false,width:180},
	                        {display:'一级类目',name:'activityProductType', align:'center',isSort:false,width:100},
	                      	{display:'活动开始时间',name:'activityareaBeginTtime', align:'center',isSort:false,width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityareaBeginTtime == null || rowdata.activityareaBeginTtime == '' ) {
									return "";
								}else{
									var activityareaBeginTtime = new Date(rowdata.activityareaBeginTtime);
									return activityareaBeginTtime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }},
	                        {display:'活动结束时间',name:'activityareaEndTime', align:'center',isSort:false,width:160, render:function(rowdata, rowindex) {
	                        	if(rowdata.activityareaEndTime == null || rowdata.activityareaEndTime == '' ) {
									return "";
								}else{
									var activityareaEndTime = new Date(rowdata.activityareaEndTime);
									return activityareaEndTime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }},          	                 
	                        {display:'活动状态',name:'activityStatusDesc', align:'center',isSort:false,width:100,render:function(rowdata, rowindex){
	                            var html = [];
							         if(new Date(rowdata.activityareaPreheatTime) <= new Date() && new Date(rowdata.activityareaBeginTtime) > new Date()) {
										html.push("预热中");
									}else if(new Date(rowdata.activityareaBeginTtime) <= new Date() && new Date(rowdata.activityareaEndTime) >= new Date()) {
										html.push("活动中");
									}else if(new Date(rowdata.activityareaEndTime) < new Date()) {
										html.push("已结束");
									}										
							        return html.join("");
	                        
	                        }},
							{display:'操作', name:'', align:'center',isSort:false,width:100, render:function(rowdata, rowindex) {
					            var html = []; 
					           html.push("<a href=\"javascript:delactivitybrandgroupactivity(" + rowdata.id + ");\">【退出】</a>");
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
	<input type="hidden" name="activitybrandgroupid" value="${activitybrandgroupid}">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >特卖ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityId" name="activityId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityName" name="activityName" >
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
						<select id="activityProductType" name="activityProductType" style="width: 135px;" >
							<option value="">请选择...</option>
							    <c:forEach var="producttypeList" items="${producttypeList}">
									<option value="${producttypeList.id}">${producttypeList.name}</option>
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
						<input type="text" id="activityareaBeginTtime" name="activityareaBeginTtime" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="activityareaBeginTtimes" name="activityareaBeginTtimes" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">活动结束日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="activityareaEndTime" name="activityareaEndTime" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="activityareaEndTimes" name="activityareaEndTimes" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			</div>	
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >活动状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="activityStatusDesc" name="activityStatusDesc" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="12">预热中</option>
							<option value="13">活动中</option>
							<option value="14">已结束</option>
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
