<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

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
<style>
.seqClass{width:50px;height:23px;text-align:center; border:1px solid red;box-sizing: border-box;border-width: 1px; border-style: solid; border-color: rgba(121, 121, 121, 1);border-radius: 0px; }
</style>
<script type="text/javascript">
	var viewerPictures;
	var linkValueList;
	$(function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			success : function(data) {
					linkValueList = data.linkValueList;
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
		
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

	
	function chgIndexPopupAd(id, status) {
		var title;
		if (status=='1'){
			title="上架";
		}else{
			title="下架";
		}
		$.ligerDialog.confirm("是否"+title+"？", function (yes) { 
			if(yes){
				$.ajax({
					url : "${pageContext.request.contextPath}/homepagePopup/changeIndexPopupAd.shtml?id=" + id + "&status=" + status,
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

	function delIndexPopupAd(id) {
		$.ligerDialog.confirm("是否删除？", function (yes) { 
			if(yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/homepagePopup/delIndexPopupAd.shtml?id=" + id,
					dataType : 'json',
					type : 'POST',
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

	function editIndexPopupAd(id) {
			var url = "";
			var title ="";
		if (id!=null){
			url = "${pageContext.request.contextPath}/homepagePopup/editIndexPopupAd.shtml?id="+id;
			title = "编辑广告";
		} else {
			url="${pageContext.request.contextPath}/homepagePopup/editIndexPopupAd.shtml";
			title = "新增广告";
		}
		$.ligerDialog.open({
			height: $(window).height()-50,
			width: $(window).width()-250,
			title: title,
			name: "INSERT_WINDOW",
			url: url,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	var listConfig={
	btnItems:[
			  // { text: '新增广告', icon: 'add', dtype:'win',  click: itemclick, url: "/homepagePopup/editIndexPopupAd.shtml", seqId:"", width: 1480, height: 800},
			  { text: '新增广告', icon: 'add', dtype:'win',  click: function() {
					  editIndexPopupAd();}
			  }
			 ],
      url:"/homepagePopup/indexPopupAddata.shtml",
      listGrid:{columns: [
                { display: '弹窗说明', width: 200, name: 'popupDesc' },
                { display: '图片', width: 120, render: function (rowdata, rowindex){
                    var h = "";
                       h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
                    return h;
                }}, 
                { display: '链接类型', width: 100, name: 'linkTypeDesc' },

                { display: '详情', width: 200, name: 'linkContent',render: function(rowdata, rowindex) {
	            if( rowdata.linkType=='3'){
            		return rowdata.pCode;
            	}else if(rowdata.linkType=='28'){
            		return rowdata.mallName;
            	}else if (rowdata.linkType=='11') {
            		if (rowdata.productTypeName) {
            			return rowdata.productTypeName;
					}
				}else if (rowdata.linkType=='10') {
					if (rowdata.mchtCode) {
						return rowdata.mchtCode;
					}
				}else if (rowdata.linkType=='9') {
					  if (rowdata.brandTeamTypeName) {
						  return rowdata.brandTeamTypeName;
					}
				}else if (rowdata.linkType=='6') {
					   for (var i= 0; i < linkValueList.length; i++) {
						    if (linkValueList[i].linkValue==rowdata.linkContent) {
						    	return linkValueList[i].linkValueName;
							}
					  	}
				}else{
            	  return rowdata.linkContent;
				}
         	}},
                { display: '弹窗次数', width: 150, name: 'popupCount',render:function(rowdata, rowindex){
                	if (rowdata.popupCount=='1') {
						return "终生一次";
					}else if (rowdata.popupCount=='2') {
						return "启用即弹";
					}else if (rowdata.popupCount=='3') {
						return "每天一次";
					}else if (rowdata.popupCount=='4') {
						return rowdata.day+"天一次";
					}else if (rowdata.popupCount=='5') {
						return "领取停止（启用即弹）";
					} 	
                	
                }},
	            { display: '上架时间', width: 150, render: function(rowdata, rowindex) {
	            	if (rowdata.upDate!=null){
		            	var upDate=new Date(rowdata.upDate);
		            	return upDate.format("yyyy-MM-dd hh:mm");	
	            	}
	         	}},
	            { display: '下架时间', width: 150, render: function(rowdata, rowindex) {
	            	if (rowdata.downDate){
		            	var downDate=new Date(rowdata.downDate);
		            	return downDate.format("yyyy-MM-dd hh:mm");
	            	}
	         	}},
	         	{ display: '上架状态', name:'',width: 100,render: function (rowdata, rowindex) {
	            	if(rowdata.status == 0){
	            		return "下架";
	            	}else{
	            		return "上架";
	            	}
				}},
                { display: "操作", name: "OPER", align: "center", width: 150, render: function(rowdata, rowindex) {
					var html = [];
					if (rowdata.status=='0'){
					    html.push("<a href=\"javascript:chgIndexPopupAd(" + rowdata.id + ",'1');\">上架</a>"); 
					    html.push("<a href=\"javascript:delIndexPopupAd(" + rowdata.id + ");\">删除</a>");
					    html.push("<a href=\"javascript:editIndexPopupAd(" + rowdata.id + ");\">编辑</a>");
					}else{
						html.push("<a href=\"javascript:chgIndexPopupAd(" + rowdata.id + ",'0');\">下架</a>");
					}
				    return html.join("&nbsp;&nbsp;");
				}}
                ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true//不设置默认为 true
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
	<form id="dataForm" runat="server" >
		<div id="searchbtn" style="display:none;">搜索</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
