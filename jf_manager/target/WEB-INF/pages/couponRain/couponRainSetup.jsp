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
 
 function edItcouponRainSetup(id) {
		$.ligerDialog.open({
			height: $(window).height()-200,
			width: $(window).width()-600,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/couponRain/addCouponRainSetup.shtml?id="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
 
 
 	 var listConfig={ 
	      url:"/couponRain/couponRainSetupData.shtml",
	      btnItems:[{ text: '新增', icon: 'add', dtype:'win',  click: itemclick, url: "/couponRain/addCouponRainSetup.shtml", seqId:"", width: 700, height: 560},],
	      listGrid:{ columns: [
					
						    {display:'红包雨名称',name:'title', align:'center', isSort:false, width:200},
						    {display:'有效优惠券个数',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
						    	if (rowdata.effectiveCouponCount!=null && rowdata.effectiveCouponCount!="") {
						    		return rowdata.effectiveCouponCount;
								}else {
									return "-";
								}
						    }},
						    {display:'平台可领取数量',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	if (rowdata.platformSum!=null && rowdata.platformSum!="") {
						    		return rowdata.platformSum;
								}else {
									return "-";
								}
						    }},
						    {display:'商品可领取数量',name:'commoditySum', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	if (rowdata.commoditySum!=null && rowdata.commoditySum!="") {
						    		return rowdata.commoditySum;
								}else {
									return "-";
								}
						    }},
						    { display: '弹窗图片', width: 120, render: function (rowdata, rowindex){
			                    var h = "";
			                    if (rowdata.pic!=null && rowdata.pic!="") {
			                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
								}
			                    return h;
			                }},
						    {display:'是否支持商品劵',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	if (rowdata.includeProductCoupon=='1') {
									return "支持"+"("+Math.round(rowdata.includeProductCouponPercent* 10000)/100+"%"+")";
								}else if (rowdata.includeProductCoupon=='0') {
									return "不支持";
								}
						    }},
						    {display:'是否支持赠送积分',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	if (rowdata.includeIntegral=='1') {
									return "支持"+"("+rowdata.includeIntegralMin+"~"+rowdata.includeIntegralMax+")";
								}else if (rowdata.includeIntegral=='0') {
									return "不支持";
								}
						    }},
						    {display:'是否支持特卖劵',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	if (rowdata.includeSale=='1') {
									return "支持"+"("+Math.round(rowdata.includeSalePercent* 10000)/100+"%"+")";
								}else if (rowdata.includeSale=='0') {
									return "不支持";
								}
						    }},
						    {display:'是否支持平台劵',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	if (rowdata.includePlatformCoupon=='1') {
									return "支持"+"("+Math.round(rowdata.includePlatformCouponPercent* 10000)/100+"%"+")";
								}else if (rowdata.includePlatformCoupon=='0') {
									return "不支持";
								}
						    }},
						    {display:'每次红包上限个数',name:'limitRecCount', align:'center', isSort:false, width:150},
						    {display:'红包类型',name:'', align:'center', isSort:false, width:150,render:function(rowdata, rowindex){
						    	if (rowdata.redEnvelopeType=='P') {
									return "普通红包";
								}else if (rowdata.redEnvelopeType=='S') {
									return "圣诞红包";
								}
						    }},
						    { display: '有效开始时间', width: 150, render: function(rowdata, rowindex) {
				            	if (rowdata.recBeginDate!=null){
					            	var recBeginDate=new Date(rowdata.recBeginDate);
					            	return recBeginDate.format("yyyy-MM-dd hh:mm");	
				            	}
				         	}},
				         	{ display: '有效结束时间', width: 150, render: function(rowdata, rowindex) {
				            	if (rowdata.recEndDate!=null){
					            	var recEndDate=new Date(rowdata.recEndDate);
					            	return recEndDate.format("yyyy-MM-dd hh:mm");	
				            	}
				         	}},
				         	{ display: '创建时间', width: 150, render: function(rowdata, rowindex) {
				            	if (rowdata.createDate!=null){
					            	var createDate=new Date(rowdata.createDate);
					            	return createDate.format("yyyy-MM-dd hh:mm");	
				            	}
				         	}},
						    {display:'操作',name:'', align:'center', isSort:false, width:100,render:function(rowdata, rowindex){
						    	var html=[];
							    html.push("<a href='javascript:;' onclick='edItcouponRainSetup(" + rowdata.id + ");'>编辑</a></br>");	    
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
	      pageSize:10
	  };
 
	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
		      <div class="search-td">
					<div class="search-td-label" >名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="title" name="title" >
					</div>
			 </div>
			<div class="search-td">
			 	<div class="search-td-label" >是否支持商家劵：</div>
			 	<div class="search-td-condition" >
				<select id="includeProductCoupon" name="includeProductCoupon" class="querysel">
					<option value="">请选择...</option>
					<option value="0">不支持</option>
					<option value="1">支持</option>	
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
