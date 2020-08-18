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
	$(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		
	});

	//图片信息
	function showInterventionOrder(seckillBrandGroupId) {
		$.ligerDialog.open({
			height : 700,
			width : 900,
			title : "图片信息",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/seckillBrandGroup/saveSeckillBrandGroupManager.shtml?seckillBrandGroupId="+seckillBrandGroupId,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	//管理商品
	function seckillBrandGroupProductManager(seckillBrandGroupId) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: $(window).width() - 200,
			title : "管理商品信息",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/seckillBrandGroup/seckillBrandGroupProductManager.shtml?seckillBrandGroupId="+seckillBrandGroupId,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	
	//启用状态
	function updateStatus(seckillBrandGroupId, status) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/seckillBrandGroup/updateSeckillBrandGroup.shtml',
			data: {id : seckillBrandGroupId, status : status},
			dataType: 'json',
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}
	
	//删除
	function delSeckillBrandGroup(seckillBrandGroupId) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/seckillBrandGroup/delSeckillBrandGroup.shtml',
			data: {seckillBrandGroupId : seckillBrandGroupId},
			dataType: 'json',
			success: function(data) {
				if(data.code != null && data.code == 200) {
					$("#searchbtn").click();
				}else {
					commUtil.alertError(data.msg);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}

	var listConfig = {
		url : "/seckillBrandGroup/seckillBrandGroupList.shtml",
		btnItems:[{ text: '创建品牌团',icon: 'add',dtype:'win',click: itemclick,url:'/seckillBrandGroup/saveSeckillBrandGroupManager.shtml', seqId:"",width: 900, height:700}],
		listGrid : {
			columns : [{display:'名称', name:'name', align:'center', isSort:false, width:180},
					{display:'图片', name:'sumOperateAudit', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
						return "<a href=\"javascript:showInterventionOrder("+ rowdata.id + ");\">【图片信息】</a>";
					}},
					{display:'时间点', name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
						if (rowdata.beginTime != null && rowdata.beginTime != '') {
							var beginTime = new Date(rowdata.beginTime);
							return beginTime.format("yyyy-MM-dd hh:mm:ss");
						} else {
							return "";
						}
					}},
					{display:'操作', name:'', align:'center', isSort:false, width:220, render:function(rowdata, rowindex) {
						var html = []; 
						if (rowdata.status != '1') {
							html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '1');\">【启用】</a>");
							html.push("<a href=\"javascript:delSeckillBrandGroup(" + rowdata.id + ");\">【删除】</a>");
						} else {
							html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '0');\">【停用】</a>");
						}
						html.push("<a href=\"javascript:seckillBrandGroupProductManager(" + rowdata.id + ");\">【管理商品】</a>");
						return html.join("");
					}}
				],
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
						<input type="text" id="seckillBrandGroupName" name="seckillBrandGroupName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >品牌团状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="seckillBrandGroupStatus" name="seckillBrandGroupStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="0">未启用</option>
							<option value="1">已启用</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">品牌团开始时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="startBeginTime" name="startBeginTime" class="dateEditor" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endBeginTime" name="endBeginTime" class="dateEditor" style="width: 135px;" />
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
