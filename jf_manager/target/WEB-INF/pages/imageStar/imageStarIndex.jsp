<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<link href="${pageContext.request.contextPath}/css/search_form.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	var viewerPictures;
	$(function() {
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'),
				{
					hide : function() {
						$("#viewer-pictures").hide();
					}
				});
	});
	//编辑
	function editorder(paramId) {
		$.ligerDialog
				.open({
					height : $(window).height() - 40,
					width : $(window).width() - 40,
					title : "启动图修改",
					name : "INSERT_WINDOW",
					url : "${pageContext.request.contextPath}/service/imageStar/imageStarEdit.shtml?paramId="
							+ paramId,
					showMax : true,
					showToggle : false,
					showMin : false,
					isResize : true,
					slide : false,
					data : null
				});
	}

	function viewerPic(imgPath) {

		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$("#viewer-pictures")
				.append(
						'<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'),
				{
					hide : function() {
						$("#viewer-pictures").hide();
					}
				});
		$("#viewer-pictures").show();
		viewerPictures.show();

	}
	var listConfig = {
		url : "/service/imageStar/imageStarData.shtml",
		listGrid : {
			columns : [
					{
						display : '图片ID',
						name : 'paramId',
						width : 60
					},
					{
						display : '图片编号',
						name : 'paramCode',
						width : 180
					},
					{
						display : '图片名称',
						name : 'paramName',
						width : 180
					},
					{
						display : '图片logo',
						width : 120,
						render : function(rowdata, rowindex) {
							var html = [];
							html
									.push("<img src='${pageContext.request.contextPath}/file_servelt/"
											+ rowdata.paramValue
											+ "' width='60' height='60' onclick='viewerPic(this.src)'>");
							return html.join("");
						}
					},
					{
						display : '图片序号',
						name : 'paramOrder',
						width : 180
					},
					{
						display : '图片描述',
						name : 'paramDesc',
						width : 180
					},
					{
						display : '操作',
						name : '',
						width : 150,
						align : 'center',
						render : function(rowdata, rowindex) {
							var html = [];
							html.push("<a href=\"javascript:editorder("
									+ rowdata.paramId
									+ ");\">【修改】</a>&nbsp;&nbsp;");
							return html.join("");
						}
					}, ],
			showCheckbox : false, //不设置默认为 true
			showRownumber : true
		//不设置默认为 true
		},
		
		container : {
			toolBarName : "toptoolbar",
			searchBtnName : "searchbtn",
			fromName : "dataForm",
			listGridName : "maingrid"
		}

	}
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures"
		style="display: none;">

	</ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
