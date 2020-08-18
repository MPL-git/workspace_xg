<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 <script type="text/javascript">
var viewerPictures; 
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
}); 

function toDesign(decorateInfoId){
	$.ligerDialog.open({
		height: $(window).height()-100,
		width: $(window).width()-100,
		title: "装修",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/wetaoPage/toDesign.shtml?decorateInfoId=" + decorateInfoId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toAdInfoList(wetaoPageId){
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.9,
		title: "管理广告",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/wetaoPage/wetaoPageAdInfo/list.shtml?wetaoPageId=" + wetaoPageId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function toEditAdInfo(wetaoPageId){
	$.ligerDialog.open({
		height: 600,
		width: 900,
		title: "添加",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/wetaoPage/toEditWetaoPageAdInfo.shtml?wetaoPageId=" + wetaoPageId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function del(id) {
	$.ligerDialog.confirm("确定删除吗？", function (yes) {
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/marketing/indexTopStyle/delete.shtml?id=" + id,
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

function saveSeqNo(id,_this){
	var seqNo = $(_this).val();
	if(!seqNo){
		return;
	}
	if(seqNo && !testNumber(seqNo)){
		return;		
	}
	$.ajax({
		url : "${pageContext.request.contextPath}/wetaoPage/saveSeqNo.shtml?id=" + id+"&seqNo="+seqNo,
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

function changeStatus(id,status) {
	var title="";
	if(status == 0){
		title = "确定要上架吗？";
	}else{
		title = "确定要下架吗？";
	}
	$.ligerDialog.confirm(title, function (yes) {
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/wetaoPage/changeStatus.shtml?id=" + id,
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

function formatMoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(),
    r = s.split(".")[1];
    t = "";
    for(i = 0; i < l.length; i ++ )
    {
       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
    }
    return t.split("").reverse().join("") + "." + r;
 }

function toEdit(id) {
	$.ligerDialog.open({
		height: 250,
		width: 600,
		title: "编辑",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/wetaoPage/toEdit.shtml?id=" + id,
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
	           { text: '添加', icon: 'add', dtype:'win',  click: itemclick, url: "/wetaoPage/toEdit.shtml", seqId:"", width: 600, height: 250},
	          ], 
     url:"/wetaoPage/data.shtml",
     listGrid:{ columns: [
			            { display: '类型', name:'productTypeName',render: function (rowdata, rowindex) {
			            	if(rowdata.productTypeName){
				            	return rowdata.productTypeName;
			            	}else{
								return "全部";			            		
			            	}
						}},
			            { display: '名称', name:'name',render: function (rowdata, rowindex) {
			            	return rowdata.name;
						}},
			            { display: '信息操作', name:'infoOp',render: function (rowdata, rowindex) {
			            	return '<a href="javascript:;" onclick="toEdit('+rowdata.id+')">编辑</a>';
						}},
			            { display: '上架状态', name:'statusDesc',render: function (rowdata, rowindex) {
			            	if(rowdata.status == 0){
			            		return "下架";
			            	}else{
			            		return "上架";
			            	}
						}},
						{ display: '广告位装修', name:'adOp',render: function (rowdata, rowindex) {
			            	return '<a href="javascript:;" onclick="toAdInfoList('+rowdata.id+')">管理</a>|<a href="javascript:;" onclick="toEditAdInfo('+rowdata.id+')">添加</a>';
						}},
			            { display: '操作',name:'op',render: function (rowdata, rowindex) {
		            		var html=[];
		            		html.push('<a href="javascript:;" onclick="toDesign('+rowdata.decorateInfoId+');">装修&nbsp;&nbsp;</a>');
			            	if(rowdata.status==0){
			            		html.push('<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.status+');">上架&nbsp;&nbsp;</a>');
		                	}else{
			            		html.push('<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.status+');">下架&nbsp;&nbsp;</a>');
		                	}
			            	html.push('<a href="'+rowdata.previewUrl+'" target="_blank">预览</a>');
	                		return html.join("");
						}},
						{ display: '排序',name:'seqNo',render: function (rowdata, rowindex) {
							if(rowdata.seqNo){
				            	return '<input name="seqNo" value="'+rowdata.seqNo+'" onblur="saveSeqNo('+rowdata.id+',this)" maxlength="9">';
							}else{
								return '<input name="seqNo" value="" onblur="saveSeqNo('+rowdata.id+',this)" maxlength="9">';						
							}
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
  
 function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
			viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	}
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label">类型:</div>
			<div class="search-td-condition" >
				<select id="productTypeId" name="productTypeId">
					<option value="-1">请选择</option>
					<option value="">全部</option>
					<c:forEach items="${productTypeList}" var="productType">
						<option value="${productType.id}">${productType.name}</option>
					</c:forEach>
				</select>
		 	</div>
			</div>
				
			<div class="search-td">
			<div class="search-td-label" >名称：</div>
			<div class="search-td-condition">
				<input type="text" id="name" name="name" >
			</div>
			</div>
				
			<div class="search-td">
			<div class="search-td-label">上架状态:</div>
			<div class="search-td-condition" >
				<select id="status" name="status">
					<option value="">请选择</option>
					<option value="0">下架</option>
					<option value="1">上架</option>
				</select>
		 	</div>
			</div>
		
			<div class="search-td-search-btn">
				<div id="searchbtn" >搜索</div>
			</div>
		</div>
		
		</div>
		
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>