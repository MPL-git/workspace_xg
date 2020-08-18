<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>
<head>
<title>特卖分组</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript">
var viewerPictures;
$(function(){
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

function editsale(id) {
	$.ligerDialog.open({
	height: 850,
	width: 650,
	title: "特卖修改",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/activityNew/editsale.shtml?ID=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function activityManager(id) {
	$.ligerDialog.open({
	height: $(window).height() - 40,
	width: $(window).width() - 40,
	title: "查看特卖",
	name: "INSERT_WINDOW",
	url: "${pageContext.request.contextPath}/activityNew/activityManager.shtml?activityGroupId=" +id,
	showMax: true,
	showToggle: false,
	showMin: false,
	isResize: true,
	slide: false,
	data: null
});
}

function deleditsale(id) {
	$.ligerDialog.confirm("是否删除？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/activityNew/editsaledel.shtml?id=" +id,
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

     //创建特卖
     var myurl = "/activityNew/editsale.shtml?ID=";
     
	 var listConfig={
			  
		      url:"/activityNew/SaleGroupingListdata.shtml",
		   
		      btnItems:[{ text: '创建', icon: 'add', dtype:'win', url:myurl, click: itemclick, seqId:"", width : 650, height:850}],
		      
		      listGrid:{ columns: [  { display: '分组ID', name: 'id',width:60 }, 
				                { display: ' 分组名称', name: 'groupName',width:100},
				                { display: '入口图彩标', width: 180 ,render: function (rowdata, rowindex) {
				                	var html = [];
				                	if(rowdata.signPic != null && rowdata.signPic != '' ) {
				                		html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.signPic+"' style='margin: 5px;' width='160' height='80' onclick='viewerPic(this.src)'>");
				                	}
				                	return html.join("");
				                }}, 
				                { display: '商品彩标（详情页）', width: 120 ,render: function (rowdata, rowindex) {
				                	var html = [];
				                	if(rowdata.productWkPic != null && rowdata.productWkPic != '') {
				                		html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.productWkPic+"' style='margin: 5px;' width='80' height='80' onclick='viewerPic(this.src)'>");
				                	}
				                	return html.join("");
				                }}, 
				                { display: '商品彩标（列表页）', width: 120 ,render: function (rowdata, rowindex) {
				                	var html = [];
				                	if(rowdata.productWkPicM != null && rowdata.productWkPicM != '') {
				                		html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.productWkPicM+"' style='margin: 5px;' width='80' height='80' onclick='viewerPic(this.src)'>");
				                	}
				                	return html.join("");
				                }}, 
				                { display: '价格彩标', width: 180 ,render: function (rowdata, rowindex) {
				                	var html = [];
				                	if(rowdata.priceWkPic != null && rowdata.priceWkPic != '') {
				                		html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.priceWkPic+"' style='margin: 5px;' width='160' height='80' onclick='viewerPic(this.src)'>");
				                	}
				                	return html.join("");
				                }}, 
				                { display: '字体色号', name: 'priceFontColor' ,width: 120 }, 
				                { display: '主会场ID', name: 'productWkLinkId' ,width: 120 }, 
				                { display: '对齐方式', width: 120 ,render: function (rowdata, rowindex) {
				                	 var html = [];
				                	 if(rowdata.productWkPosition == '1') {
				                	 	html.push("左上角");
				                	 }else if(rowdata.productWkPosition == '2') {
				                	 	html.push("右上角");	
				                	 }else if(rowdata.productWkPosition == '3') {
				                	 	html.push("右下角"); 
				                	 }else if(rowdata.productWkPosition == '4') {
				                		html.push("左下角");
				                	 }
				                	 return html.join("");
				                }}, 
				                { display: '已关联几个特卖', name: 'quantity' ,width: 120 }, 
				                { display: "操作", name: "id", width: 150, align: "center", render: function(rowdata, rowindex) {
									var html = [];
									if(rowdata.quantity !="" && rowdata.quantity!=0){			
										html.push("<a href=\"javascript:editsale(" +rowdata.id+ ");\">【修改】</a>&nbsp;&nbsp;"); 
										html.push("<a href=\"javascript:activityManager(" +rowdata.id+ ");\">【查看特卖】</a>&nbsp;&nbsp;"); 
										return html.join("");
									}
									html.push("<a href=\"javascript:editsale(" +rowdata.id+ ");\">【修改】</a>&nbsp;&nbsp;");
									html.push("<a href=\"javascript:deleditsale(" +rowdata.id+ ");\">【删除】</a>&nbsp;&nbsp;");
				                    return html.join("");
							 }
			             }
				                ],   
		                 showCheckbox : false,  //不设置默认为 true
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  >
		 <div id="searchbtn" style="display:none">搜索</div>	
		</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
