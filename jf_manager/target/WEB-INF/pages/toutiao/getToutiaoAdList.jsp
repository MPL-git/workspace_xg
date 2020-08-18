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
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
	 });
	 
	 // 复制
	 function copyToutiaoAd(adInfoId) {
		 $.ligerDialog.confirm('复制前请先在广告主列表<span style="color:red;">【广告计划更新】</span>，是否已经更新?', function (status){  
             if(status==true){
            	 $.ligerDialog.open({
         			height: 500,
         			width: 600,
         			title: "复制",
         			name: "INSERT_WINDOW",
         			url: "${pageContext.request.contextPath}/toutiaoAd/copyToutiaoAdManager.shtml?adInfoId=" + adInfoId,
         			showMax: true,
         			showToggle: false,
         			showMin: false,
         			isResize: true,
         			slide: false,
         			data: null
         		});
             }
         });
	 }
	 
 	 var listConfig={
	      url:"/toutiaoAd/getToutiaoAdList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'广告计划ID',name:'adId', align:'center', isSort:false, width:180},
							{display:'广告计划名称',name:'name', align:'center', isSort:false, width:180},
							{display:'已选流量',name:'', align:'center', isSort:false, width:180,render:function(rowdata, rowindex){
	                        	var html = [];
	                        	var inventoryType = rowdata.inventoryType;
								if(inventoryType != null && inventoryType != '') {
	                        		var inventoryTypes = inventoryType.split(",");
	                        		for(var i=0;i<inventoryTypes.length;i++) {
	                        			if(html.length != 0) {
		                        			html.push("</br>");
	                        			}
	                        			html.push(inventoryTypes[i]);
	                        		}
	                        	}
	                        	return html.join("");
	                        }},
							{display:'账户名称',name:'advertiserName', align:'center', isSort:false, width:180},
							{display:'广告组名称',name:'campaignName', align:'center', isSort:false, width:180},
							{display:'投放时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata,rowindex){
								var html = [];
								if(rowdata.startTime != null && rowdata.startTime != "" ){
									var startTime = new Date(rowdata.startTime);
									html.push(startTime.format("yyyy-MM-dd"));
								}
								html.push("~");
								if(rowdata.endTime != null && rowdata.endTime != "" ){
									var endTime = new Date(rowdata.endTime);
									html.push(endTime.format("yyyy-MM-dd"));
								}
								return html.join("");
							}},
							{display:'目标转化出价（元）',name:'', align:'center', isSort:false, width:120, render:function(rowdata,rowindex){
								var html = [];
								html.push(rowdata.bid+"&nbsp;&nbsp;");
								html.push(rowdata.pricingDesc);
								return html.join("");
							}},
							{display:'预算（元）',name:'', align:'center', isSort:false, width:100, render:function(rowdata,rowindex){
								var html = [];
								html.push(rowdata.budget);
								html.push("</br>");
								html.push(rowdata.budgetModeDesc);
								return html.join("");
							}},
							{display:'状态',name:'statusDesc', align:'center', isSort:false, width:100},
							{display:'创建时间',name:'adCreateTime', align:'center', isSort:false, width:180},
							{display:'操作',name:'adCreateTime', align:'center', isSort:false, width:100, render:function(rowdata,rowindex){
								var html = [];
								html.push("<a href=\"javascript:copyToutiaoAd(" + rowdata.id + ");\">【复制】</a>");
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
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >广告计划ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="adId" name="adId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >广告计划名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="adName" name="adName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >所属账户：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="advertiserName" name="advertiserName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >广告组名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="campaignName" name="campaignName" >
					</div>
				</div>
			</div>
		</div>
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td">
					<div class="search-td-label"  >推广目的：</div>
					<div class="search-td-combobox-condition" >
						<select id="landingType" name="landingType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${landingTypeList }" var="landingType">
								<option value="${landingType.statusValue }">${landingType.statusDesc }</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${statusList }" var="status">
								<option value="${status.statusValue }">${status.statusDesc }</option>
							</c:forEach>
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
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
