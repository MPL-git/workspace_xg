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
	 
	 function updateOrSaveTrackDatas(flag) {
		 var titleStr = "";
		 var status = "";
		 if(flag == '1') {
			 titleStr = "导入IOS推广";
			 status = "IOS";
		 }else if(flag == '2') {
			 titleStr = "导入Android推广";
			 status = "ANDROID";
		 }
		$.ligerDialog.open({
			height: 500,
			width: 700,
			title: titleStr,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/promotionData/orderCount/trackDataManager.shtml?flag=2&status=" + status,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
 var listConfig={
	      url:"/promotionData/orderCount/getTrackDataList.shtml",
	      btnItems:[
   			{text: '导入IOS推广', icon: 'modify', click: function() {
   				updateOrSaveTrackDatas('1');
   			    return;
   			  }
   			}, 
   			{text: '导入Android推广', icon: 'add', click: function() {
   				updateOrSaveTrackDatas('2');
   			    return;
   			  }
   			}
   	      ],
	      listGrid:{ columns: [
	                        {display:'推广活动URL',name:'spreadurl', align:'center', isSort:false, width:100 },
	                        {display:'推广渠道',name:'channel', align:'center', isSort:false, width:80 },
	                        {display:'推广活动名称',name:'spreadname', align:'center', isSort:false, width:180 },
	                        {display:'活动组',name:'activityname', align:'center', isSort:false, width:100 },
	                        {display:'点击广告发生时间',name:'clicktime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.clicktime == null || rowdata.clicktime == "" || rowdata.beginTime == undefined) {
									return "";
								}else{
									var clicktime = new Date(rowdata.clicktime);
									return clicktime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }},
	                        {display:'Ios设备id',name:'idfa', align:'center', isSort:false, width:180 },
	                        {display:'Ios6以下',name:'mac', align:'center', isSort:false, width:180 },
	                        {display:'Android设备id',name:'imei', align:'center', isSort:false, width:180 },
	                        {display:'点击广告设备UA信息',name:'ua', align:'center', isSort:false, width:180 },
	                        {display:'点击广告设备IP',name:'uip', align:'center', isSort:false, width:120 },
	                        {display:'产品appkey',name:'appkey', align:'center', isSort:false, width:100 },
	                        {display:'应用激活时间',name:'activetime', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	                        	if(rowdata.activetime == null || rowdata.activetime == "" || rowdata.activetime == undefined) {
									return "";
								}else{
									var activetime = new Date(rowdata.activetime);
									return activetime.format("yyyy-MM-dd hh:mm:ss");
								}
	                        }},
	                        {display:'激活设备系统版本',name:'osversion', align:'center', isSort:false, width:100 },
	                        {display:'激活设备类型',name:'devicetype', align:'center', isSort:false, width:80 },
	                        {display:'安全参数',name:'skey', align:'center', isSort:false, width:80 }
			         ], 
	                 showCheckbox : false,  //不设置默认为 true
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >推广渠道：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="channel" name="channel" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="spreadname" name="spreadname" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动组：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityname" name="activityname" >
					</div>
				</div>
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
