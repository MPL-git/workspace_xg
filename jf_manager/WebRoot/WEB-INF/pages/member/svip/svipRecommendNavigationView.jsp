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

		function chgSvipRecommendNavigation(id, status) {
			var title;
			if (status=='1'){
				title="上架";
			}else{
				title="下架";
			}
			$.ligerDialog.confirm("是否"+title+"？", function (yes) {
				if(yes){
					$.ajax({
						url : "${pageContext.request.contextPath}/svipOrder/changeSvipRecommendNavigation.shtml?id=" + id + "&status=" + status,
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

		function delSvipRecommendNavigation(id) {
			$.ligerDialog.confirm("是否删除？", function (yes) {
				if(yes) {
					$.ajax({
						url : "${pageContext.request.contextPath}/svipOrder/delSvipRecommendNavigation.shtml?id=" + id,
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

		function editSvipRecommendNavigation(id) {
			var url = "";
			var title ="";
			if (id==null){
				url="${pageContext.request.contextPath}/svipOrder/editSvipRecommendNavigationView.shtml";
				title = "新增";
			} else {
				url = "${pageContext.request.contextPath}/svipOrder/editSvipRecommendNavigationView.shtml?id="+id;
				title = "编辑";
			}
			$.ligerDialog.open({
				height: $(window).height()-420,
				width: $(window).width()-300,
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

		//排序
		function updateArtNo(id,seqNo) {
			$("#seqNo" + id).parent().find("span").remove();
			var seqNo = $("#seqNo" + id).val();
			if(seqNo <0){
				commUtil.alertError("请输入正整数");
				return;
			}

			var flag = seqNo.match(/^[1-9]\d*$/);
			if(seqNo != '' && flag != null || seqNo == '') {
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/svipOrder/updateSvipRecommendNavigationSeqNo.shtml",
					data : {id : id, seqNo : seqNo},
					dataType : 'json',
					success : function(data){
						if(data == null || data.code != 200)
							commUtil.alertError(data.msg);
						else{
							$("#seqNo" + id).parent().append("<span style='color:#009999;'>更改成功</span>");
							$("#seqNo" + id).attr("seqNo", seqNo);
							$("#searchbtn").click();
						}
					},
					error : function(e) {
						commUtil.alertError("系统异常请稍后再试");
					}
				});
			}else{
				$("#seqNo" + id).val($("#seqNo" + hotSearchWordId).attr("seqNo"));
				$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
			}
		}

		var listConfig={
			btnItems:[
				{ text: '新增', icon: 'add', dtype:'win',  click: function() {
						editSvipRecommendNavigation();}
				}
			],
			url:"/svipOrder/svipRecommendNavigationList.shtml",
			listGrid:{columns: [
					{ display: '排序', name: 'seqNo', align:'center', width: 150, render: function(rowdata, rowindex) {
							var html = [];
							var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
							html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id +","+seqNo+")' value='" + seqNo + "' >");
							return html.join("");
						}},
					{ display: '导航名称', width: 200, name: 'name' },
					{ display: '备注', width: 200, name: 'remarks' },
					{ display: '上架状态', width: 150, name: 'status',render:function(rowdata, rowindex){
							if (rowdata.status=='1') {
								return "上架中";
							}else{
								return "下架";
							}
						}},
					{ display: '关键字', width: 200, name: 'keyword',render:function(rowdata, rowindex){
							if (rowdata.id == '1') {
								return "/";
							}else{
								return rowdata.keyword;
							}
						}},
					{ display: "操作", name: "OPER", align: "center", width: 150, render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.status=='0'){
								html.push("<a href=\"javascript:editSvipRecommendNavigation(" + rowdata.id + ");\">编辑</a>");
								html.push("<a href=\"javascript:chgSvipRecommendNavigation(" + rowdata.id + ",'1');\">上架</a>");
								if(rowdata.id != 1){
									html.push("<a href=\"javascript:delSvipRecommendNavigation(" + rowdata.id + ");\">删除</a>");
								}
							}else{
								html.push("<a href=\"javascript:chgSvipRecommendNavigation(" + rowdata.id + ",'0');\">下架</a>");
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
