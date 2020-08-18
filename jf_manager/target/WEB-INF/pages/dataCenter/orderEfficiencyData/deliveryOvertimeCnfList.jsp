<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
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
 
 function del(id) {
	 $.ligerDialog.confirm('确定删除吗？',  function (yes){
       	 if(yes == true){
       		$.ajax({
				url : "${pageContext.request.contextPath}/orderEfficiencyData/deliveryOvertimeCnf/delete.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"id":id},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess(data.returnMsg);
					}else{
						commUtil.alertError("删除失败，请稍后重试");
					}
					setTimeout(function(){
						location.reload();
						frameElement.dialog.close();
					},1000);
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
       	 }
     }); 
 }

 function toEdit(id){
	$.ligerDialog.open({
		height: 700,
		width: 700,
		title: "修改",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/orderEfficiencyData/deliveryOvertimeCnf/toEdit.shtml?id="+id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
 }
 
 
 var listConfig={
     url:"/orderEfficiencyData/deliveryOvertimeCnf/data.shtml",
     btnItems:[{ text: '添加', icon: 'add', dtype:'win',  click: itemclick, url: "/orderEfficiencyData/deliveryOvertimeCnf/toEdit.shtml", seqId:"", width: 700, height: 700}],
     listGrid:{ columns: [
						{ display: '开始时间',width: 200, render: function (rowdata, rowindex) {
							return new Date(rowdata.beginDate).format("yyyy-MM-dd hh:mm:ss");	                		
		                }},
			            { display: '结束时间',width: 190,render: function (rowdata, rowindex) {
			            	return new Date(rowdata.endDate).format("yyyy-MM-dd hh:mm:ss");	                		
		                }},
		                { display: '内部备注',width: 190, render: function (rowdata, rowindex) {
							return rowdata.remarks;	                		
		                }},
		                { display: '类型',width: 190, render: function (rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.timeType == '0') {
		                		html.push("相对值");
							}else if(rowdata.timeType == '1') {
								html.push("绝对值");
							}
		                	return html.join("");
		                }},
			            { display: '发货承诺时间', width: 190,render: function (rowdata, rowindex) {
			            	var html = [];
			            	if(rowdata.timeType == '0' && rowdata.overtime != '') {
								html.push(rowdata.overtime+"小时");
							}else if(rowdata.timeType == '1' && rowdata.deliveryDate != '') {
								html.push(new Date(rowdata.deliveryDate).format("yyyy-MM-dd hh:mm:ss"));
							}		      
			            	return html.join("");
		                }},
			            { display: '商品公告', width: 190,render: function (rowdata, rowindex) {
			            	return "<img src='${pageContext.request.contextPath}/file_servelt"+rowdata.productNoticePic+"'  width='120' height='60' onclick='viewerPic(this.src)'>";
		                }},
		                { display: '操作', width: 190,render: function (rowdata, rowindex) {
							return '<a href="javascript:;" onclick="toEdit('+rowdata.id+');">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="del('+rowdata.id+');">删除</a>';	                		
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr"  > 
			<div class="search-td">
				<div class="search-td-label" style="float:left;">支付日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="date" name="date" value="${date}"/>
					<script type="text/javascript">
						$(function() {
							$("#date").ligerDateEditor( {
								showTime : false,
								labelWidth : 150,
								labelAlign : 'left'
							});
						});
					</script>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
			<div class="search-td">
				<span style="margin-left: 50px;color: red;">备注：默认值为48小时</span>
			</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>