<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
});
	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		
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
 
    //管理特卖
	function activitybrandgroupactivity(activitybrandgroupid) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title : "管理特卖信息",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/activityNew/activityBrandGroupActivity.shtml?activitybrandgroupid="+activitybrandgroupid,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
 function delBrandGroup(id) {
	$.ligerDialog.confirm("是否删除？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/activityNew/delBrandGroup.shtml?id=" +id,
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


function updateStatus(id,status) {
	var title;
	if (status=='1'){
		title="启用";
	}else{
		title="停用";
	}
	$.ligerDialog.confirm("是否"+title+"？", function (yes) 
	{ 
		if(yes){
			$.ajax({
				url : "${pageContext.request.contextPath}/activityNew/updateStatus.shtml?id=" + id + "&status=" + status,
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
 
  function brandGroup(activitybrandgroupid) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 400,
			title: "编辑",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityBrandGroupnew/addActivityBrandGroup.shtml?activitybrandgroupid="+activitybrandgroupid,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }

	var listConfig = {
		url : "/activityNew/activityBrandingdata.shtml",
		btnItems:[{ text: '添加',icon: 'add',dtype:'win',click: itemclick,url:'/activityBrandGroupnew/addActivityBrandGroup.shtml?activitybrandgroupid=', seqId:"",width: 900, height:700}],
		listGrid : {
			columns : [{display:'名称', name:'name', align:'center', isSort:false, width:180},
					  { display: '入口背景图', width: 180 ,render: function (rowdata, rowindex) {
				         var html = [];
				          if(rowdata.entryPic != null && rowdata.entryPic != '' ) {
				           html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.entryPic+"' style='margin: 5px;' width='160' height='80' onclick='viewerPic(this.src)'>");
				         }
				           return html.join("");
				    }},					
					{ display: '海报图', width: 180 ,render: function (rowdata, rowindex) {
				         var html = [];
				          if(rowdata.posterPic != null && rowdata.posterPic != '' ) {
				           html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.posterPic+"' style='margin: 5px;' width='160' height='80' onclick='viewerPic(this.src)'>");
				        }
				           return html.join("");
				    }},
					{ display: '分类栏目',width: 120,render:function(rowdata, rowindex){
					    if (rowdata.appCatalog=='0') {
						   return "预告";
						} 
					       return rowdata.productTypeName;
					}}, 
					{ display: '展示位置', name: 'position',width: 120 },
					{ display: '品牌团状态',width: 120,render:function(rowdata, rowindex){
					   if (rowdata.status=='0') {
						  return "停用";
					   }else if (rowdata.status=='1') {
						  return "启用";
					}		
					}},
					{display:'创建时间',align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
						if (rowdata.createDate != null && rowdata.createDate != '') {
							var createDate = new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						} else {
							return "";
						}
					}},
					{display:'操作',align:'center', isSort:false, width:250, render:function(rowdata, rowindex) {
						var html = []; 
						if (rowdata.status != '1') {
						    html.push("<a href=\"javascript:brandGroup(" + rowdata.id + ");\">【编辑】</a>");
							html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '1');\">【启用】</a>");
							html.push("<a href=\"javascript:activitybrandgroupactivity(" + rowdata.id + ");\">【管理特卖】</a>");
							html.push("<a href=\"javascript:delBrandGroup(" + rowdata.id + ");\">【删除】</a>");
						} else {
						    html.push("<a href=\"javascript:brandGroup(" + rowdata.id + ");\">【编辑】</a>");
							html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '0');\">【停用】</a>");
							html.push("<a href=\"javascript:activitybrandgroupactivity(" + rowdata.id + ");\">【管理特卖】</a>");
						}
						return html.join("");
				    }}
				],
			showCheckbox : false, //不设置默认为 true
			showRownumber : true  //不设置默认为 true
		},
		container : {
			toolBarName : "toptoolbar",
			searchBtnName : "searchbtn",
			fromName : "dataForm",
			listGridName : "maingrid"
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
					<div class="search-td-label"  >品牌团名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="name" name="name" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >分类栏目：</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeId" name="productTypeId" style="width: 135px;">
						        <option value=''>请选择...</option>
								<c:forEach var="productType" items="${productTypeList }">
									<option value="${productType.id }">${productType.name }</option>
								</c:forEach>
								<option value='0'>预告</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
			  <div class="search-td">
					<div class="search-td-label"  >品牌团状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="status" name="status" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="0">停用</option>
							<option value="1">启用</option>
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
