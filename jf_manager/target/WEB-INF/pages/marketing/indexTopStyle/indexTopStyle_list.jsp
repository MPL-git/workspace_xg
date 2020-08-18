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
 <script type="text/javascript">
var viewerPictures; 
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
}); 
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
				url : "${pageContext.request.contextPath}/marketing/indexTopStyle/changeStatus.shtml?id=" + id,
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
		height: 450,
		width: 600,
		title: "编辑",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/marketing/indexTopStyle/toEdit.shtml?id=" + id+"&type=${type}",
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
	           { text: '添加', icon: 'add', dtype:'win',  click: itemclick, url: "/marketing/indexTopStyle/toEdit.shtml?type=${type}", seqId:"", width: 600, height: 450},
	          ], 
     url:"/marketing/indexTopStyle/data.shtml",
     listGrid:{ columns: [
			            { display: '活动时间', name:'activityTime',render: function (rowdata, rowindex) {
			            	return new Date(rowdata.activityBeginDate).format("yyyy-MM-dd hh:mm:ss")+"~"+new Date(rowdata.activityEndDate).format("yyyy-MM-dd hh:mm:ss");
						}},
			            { display: '背景图', name:'bgPic',render: function (rowdata, rowindex) {
			            	return "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.bgPic+"' width='60' height='60' onclick='viewerPic(this.src)'>";
						}},
			            { display: '上架状态', name:'statusDesc',render: function (rowdata, rowindex) {
			            	if(rowdata.status == 0){
			            		return "下架";
			            	}else{
			            		return "上架";
			            	}
						}},
			            { display: '创建时间',name:'createDate',render: function (rowdata, rowindex) {
			            	return new Date(rowdata.createDate).format("yyyy-MM-dd hh:mm:ss");
						}},
			            { display: '操作',name:'op',render: function (rowdata, rowindex) {
		            		var html=[];
			            	if(rowdata.status==0){
			            		html.push('<a href="javascript:;" onclick="toEdit('+rowdata.id+');">编辑&nbsp;&nbsp;</a>');
			            		html.push('<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.status+');">上架&nbsp;&nbsp;</a>');
			            		html.push('<a href="javascript:;" onclick="del('+rowdata.id+');">删除</a>');
		                	}else{
			            		html.push('<a href="javascript:;" onclick="toEdit('+rowdata.id+');">编辑&nbsp;&nbsp;</a>');
			            		html.push('<a href="javascript:;" onclick="changeStatus('+rowdata.id+','+rowdata.status+');">下架</a>');
		                	}
	                		return html.join("");
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
		<input type="hidden" id="type" name="type" value="${type}">
		<div class="search-pannel">
		<div class="search-tr">
			<div class="search-td">
			<div class="search-td-label">上下架状态:</div>
			<div class="search-td-condition" >
				<select id="status" name="status">
					<option value="">请选择</option>
					<option value="0">下架</option>
					<option value="1">上架</option>
				</select>
		 	</div>
			</div>
		
			<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="createDateBegin" name="createDateBegin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#createDateBegin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd" value=""  />
						<script type="text/javascript">
							$(function() {
								$("#createDateEnd").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
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