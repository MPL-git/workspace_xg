<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript">
$(function(){
	$("input[name='editDecorateModule']").live('click',function(){
		var decorateInfoId = $("#decorateInfoId").val();
		var decorateAreaId = $("#decorateAreaId").val();
		var moduleType = $(this).attr("moduleType");
		var decorateModuleId = $(this).attr("decorateModuleId");
		var index = $("#decorateModuleDiv div[name='moduleDiv']").index($(this).closest("div[name='moduleDiv']"));
		var seqNo = Number(index)+1;
		var title;
		var closeRefresh=false;
		if(moduleType == "1"){
			title = "编辑图片模块";
		}else if(moduleType == "2"){
			title = "编辑商品模块";
			closeRefresh=true;
		}else if(moduleType == "3"){
			title = "编辑特卖模块";
		}else if(moduleType == "4"){
			title = "编辑商品列表";
		}else if(moduleType == "5"){
			title = "编辑特卖列表";
		}else if(moduleType == "13"){
			title = "编辑视频模块";
			closeRefresh=true;
		}
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/customPage/editDecorateModule.shtml?decorateAreaId=" + decorateAreaId+"&moduleType="+moduleType+"&seqNo="+seqNo+"&decorateModuleId="+decorateModuleId+"&decorateInfoId="+decorateInfoId+"&pageType="+$("#pageType").val(),
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null,
			closeRefresh:closeRefresh
		});
		
	});
	
	$("input[name='moveUp']").bind('click',function(){
		var prevId = $(this).attr("decorateModuleId");
		var nextId = $(this).closest("div[name='moduleDiv']").prev().prev().attr("decorateModuleId");
		var nextIndex = $("#decorateModuleDiv div[name='moduleDiv']").index($(this).closest("div[name='moduleDiv']"));
		var nextSeqNo = Number(nextIndex)+1;
		var prevIndex = $("#decorateModuleDiv div[name='moduleDiv']").index($(this).closest("div[name='moduleDiv']").prev().prev());
		var prevSeqNo = Number(prevIndex)+1;
		if(prevId && nextId){
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/moveDecorateModule.shtml?prevId="+prevId+"&nextId="+nextId+"&prevSeqNo="+prevSeqNo+"&nextSeqNo="+nextSeqNo,
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {prevId:prevId,nextId:nextId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("操作成功");
						location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			
		}
	});
	
	$("input[name='moveDown']").bind('click',function(){
		var nextId = $(this).attr("decorateModuleId");
		var prevId = $(this).closest("div[name='moduleDiv']").next().next().attr("decorateModuleId");
		var prevIndex = $("#decorateModuleDiv div[name='moduleDiv']").index($(this).closest("div[name='moduleDiv']"));
		var prevSeqNo = Number(prevIndex)+1;
		var nextIndex = $("#decorateModuleDiv div[name='moduleDiv']").index($(this).closest("div[name='moduleDiv']").next().next());
		var nextSeqNo = Number(nextIndex)+1;
		if(prevId && nextId){
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/moveDecorateModule.shtml?prevId="+prevId+"&nextId="+nextId+"&prevSeqNo="+prevSeqNo+"&nextSeqNo="+nextSeqNo,
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {prevId:prevId,nextId:nextId},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("操作成功");
						location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			
		}
	});
}); 

function edit(id) {
	$.ligerDialog.open({
		height: $(window).height()*0.5,
		width: $(window).width()*0.5,
		title: "编辑",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/editCustomPage.shtml?id=" + id,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function changeStatus(id,status) {
	var title;
	if(status == "0"){
		title = "确认上架吗？";
	}else{
		title = "确认下架吗？";
	}
	if(confirm(title)){
		$.ajax({
			url : "${pageContext.request.contextPath}/customPage/changeStatus.shtml",
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
}

function previewDecorateInfo(decorateInfoId) {
	$.ligerDialog.open({
		height: $(window).height()*0.9,
		width: $(window).width()*0.7,
		title: "预览",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/previewDecorateInfo.shtml?decorateInfoId=" + decorateInfoId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function del(id,obj){
	if(confirm("确定删除吗？")){
		if(id && id>0){
			$.ajax({
				url : "${pageContext.request.contextPath}/customPage/deleteDecorateModule.shtml?id="+id,
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : $("#form").serialize(),
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("删除成功");
						location.reload();
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			var $moduleDiv = $(obj).closest("div[name='moduleDiv']");
			var $br = $moduleDiv.next();
			$moduleDiv.remove();
			$br.remove();
		}
	}
}

function insertPicModule(){
	var picModuleDiv = '<div style="position: relative;width: 750px;height:500px;border:1px solid #000" name="moduleDiv" decorateModuleId = "">'+
								'<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
									'<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:blue;color:white;">图片模块</div>'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(0,this);">'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="1" decorateModuleId="">'+
										'<div style="margin-top: 50px;margin-left: 10px;">'+
											'自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽为750像素，高度不限，大小：200Kb以内'+
										'</div>'+
								'</div>'+
								'<img src="" style="width: 100%;">'+
						'</div>'+
						'<br>';
	if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
		$("#decorateModuleDiv").find("div[listModule='list']").before(picModuleDiv);
	}else{
		$("#decorateModuleDiv").append(picModuleDiv);
	}
}

function insertHDPicModule(){
	var picModuleDiv = '<div style="position: relative;width: 750px;height:500px;border:1px solid #000" name="moduleDiv" decorateModuleId = "">'+
								'<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
									'<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:blue;color:white;">图片模块</div>'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(0,this);">'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="1" decorateModuleId="">'+
										'<div style="margin-top: 50px;margin-left: 10px;">'+
											'自定义图片，仅支持.jpg .png。&nbsp;&nbsp;尺寸：宽为1242像素，高度不限，大小：200Kb以内'+
										'</div>'+
								'</div>'+
								'<img src="" style="width: 100%;">'+
						'</div>'+
						'<br>';
	if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
		$("#decorateModuleDiv").find("div[listModule='list']").before(picModuleDiv);
	}else{
		$("#decorateModuleDiv").append(picModuleDiv);
	}
}

function insertVedioModule(){
	var vedioModuleDiv = '<div style="position: relative;width: 750px;height:500px;border:1px solid #000" name="moduleDiv" decorateModuleId = "">'+
								'<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
									'<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:blue;color:white;">视频模块</div>'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(0,this);">'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="13" decorateModuleId="">'+
										'<div style="margin-top: 50px;margin-left: 10px;">'+
											'视频封面仅支持.jpg .png，宽高比与视频一致；视频文件格式支持mp4大小：100M以内。'+
										'</div>'+
								'</div>'+
								'<img src="" style="width: 100%;">'+
						'</div>'+
						'<br>';
	if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
		$("#decorateModuleDiv").find("div[listModule='list']").before(vedioModuleDiv);
	}else{
		$("#decorateModuleDiv").append(vedioModuleDiv);
	}
}

function insertProductModule(){
	var decorateAreaId = $("#decorateAreaId").val();
	var moduleType = 2;
	var showNum = 2;
	$.ajax({
		url : "${pageContext.request.contextPath}/customPage/insertProductModule.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {decorateAreaId:decorateAreaId,moduleType:moduleType,showNum:showNum},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				var decorateModuleId = data.decorateModuleId;
				var productModuleDiv = '<div style="position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv" decorateModuleId = "'+decorateModuleId+'">'+
											'<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
												'<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:green;color:white;">商品模块</div>'+
													'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del('+decorateModuleId+',this);">'+
													'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="2" decorateModuleId="'+decorateModuleId+'">'+
													'<div style="margin-top: 50px;margin-left: 10px;">'+
														'共选0个商品，要求显示0个商品'+
													'</div>'+
											'</div>'+
											'<img src="" style="width: 100%;">'+
										'</div>'+
										'<br>';
				if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
					$("#decorateModuleDiv").find("div[listModule='list']").before(productModuleDiv);
				}else{
					$("#decorateModuleDiv").append(productModuleDiv);
				}						
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function insertActivityAreaModule(){
	var activityAreaModule = '<div style="position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv" decorateModuleId = "">'+
								'<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
									'<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:#5500FF;color:white;">特卖模块</div>'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(0,this);">'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="3" decorateModuleId="">'+
										'<div style="margin-top: 50px;margin-left: 10px;">'+
											'共选0个特卖，要求显示0个特卖'+
									'</div>'+
								'</div>'+
								'<img src="" style="width: 100%;">'+
							  '</div>'+
							  '<br>';
	if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
		$("#decorateModuleDiv").find("div[listModule='list']").before(activityAreaModule);
	}else{
		$("#decorateModuleDiv").append(activityAreaModule);
	}						  
}

function insertProductListModule(){
	if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
		commUtil.alertError("商品列表、特卖列，只能插入其中的一个，而且只能放在最后");
		return false;
	}
	var decorateInfoId = $("#decorateInfoId").val();
	var decorateAreaId = $("#decorateAreaId").val();
	var moduleType = 4;
	$.ajax({
		url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml?decorateInfoId="+decorateInfoId+"&decorateAreaId="+decorateAreaId+"&moduleType="+moduleType,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				var decorateModuleId = data.decorateModuleId;
				var productListModule = '<div style="position: relative;width: 750px;height: 200px;border:1px solid #000" listModule="list" name="moduleDiv" decorateModuleId = "'+decorateModuleId+'">'+
				'<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
					'<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:orange;color:white;">商品列表</div>'+
						'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del('+decorateModuleId+',this);">'+
						'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="4" decorateModuleId="'+decorateModuleId+'">'+
						'<div style="margin-top: 50px;margin-left: 10px;">'+
							'商品筛选条件：<br>'+
							'一级分类 = <br>'+
							'二级分类 = <br>'+
							'品牌 = '+
					'</div>'+
				'</div>'+
				'<img src="" style="width: 100%;">'+
				 '</div>'+
				 '<br>';
				$("#decorateModuleDiv").append(productListModule);				
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function insertActivityListModule(){
	if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
		commUtil.alertError("商品列表、特卖列，只能插入其中的一个，而且只能放在最后");
		return false;
	}
	var decorateInfoId = $("#decorateInfoId").val();
	var decorateAreaId = $("#decorateAreaId").val();
	var moduleType = 5;
	$.ajax({
		url : "${pageContext.request.contextPath}/customPage/saveDecorateModule.shtml?decorateInfoId="+decorateInfoId+"&decorateAreaId="+decorateAreaId+"&moduleType="+moduleType,
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				var decorateModuleId = data.decorateModuleId;
				var activityListModule = '<div style="position: relative;width: 750px;height: 200px;border:1px solid #000" listModule="list" name="moduleDiv" decorateModuleId = "'+decorateModuleId+'">'+
				'<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
					'<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:#FF34B3;color:white;">特卖列表</div>'+
						'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del('+decorateModuleId+',this);">'+
						'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="5" decorateModuleId="'+decorateModuleId+'">'+
						'<div style="margin-top: 50px;margin-left: 10px;">'+
							'商品筛选条件：<br>'+
							'一级分类 = <br>'+
							'二级分类 = <br>'+
							'品牌 = '+
					'</div>'+
				'</div>'+
				'<img src="" style="width: 100%;">'+
				 '</div>'+
				 '<br>';
				$("#decorateModuleDiv").append(activityListModule);				
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}

function editDecorateProductPool(){
	var decorateInfoId = $("#decorateInfoId").val();
	$.ligerDialog.open({
		height: $(window).height()*0.8,
		width: $(window).width()*0.8,
		title: "本装修商品池",
		name: "INSERT_WINDOW",
		url: "${pageContext.request.contextPath}/customPage/editDecorateProductPool.shtml?decorateInfoId=" + decorateInfoId,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}
</script>
</head>
<body style="padding: 0px;">
	<form id="dataForm" runat="server">
		<input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}">
		<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateAreaId}">
		<input type="hidden" id="pageType" name="pageType" value="${pageType}">
		<div class="search-pannel" style="overflow-y: auto;">
			<div class="search-tr"  > 
				<div style="display: inline-flex;">
				<c:if test="${pageType ne 21 && pageType ne 23}">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入图片模块" onclick="insertPicModule();">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入商品模块" onclick="insertProductModule();">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入特卖模块" onclick="insertActivityAreaModule();">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入商品列表" onclick="insertProductListModule();">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入特卖列表" onclick="insertActivityListModule();">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 70px;height: 30px;cursor: pointer;" value="编辑商品池" onclick="editDecorateProductPool();">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="预览" onclick="previewDecorateInfo(${decorateInfoId});">
					</div>
				</c:if>
				<c:if test="${pageType eq 21}">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入图片模块" onclick="insertHDPicModule();">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入视频模板" onclick="insertVedioModule();">
					</div>
				</c:if>
				<c:if test="${pageType eq 23}">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入图片模块" onclick="insertPicModule();">
					</div>
				</c:if>
				</div>
			</div>
			<div class="search-tr" id="decorateModuleDiv">
			<c:forEach items="${decorateModuleCustoms}" var="decorateModuleCustom" varStatus="index">
				<c:if test="${decorateModuleCustom.moduleType == 1}">
					<div style="position: relative;width: 750px;min-height:180px;border:1px solid #000" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: blue;color: white;">
								图片模块
							</div>
							<c:if test="${index.index!=0}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
							<c:if test="${!index.last}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="1" decorateModuleId="${decorateModuleCustom.id}">
							<div style="margin-top: 50px;margin-left: 10px;">自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽为750像素，高度不限，大小：200Kb以内</div>
						</div>
						<img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="width: 100%;height: auto;">
					</div>
					<br>
				</c:if>
				<c:if test="${decorateModuleCustom.moduleType == 2}">
					<div style="position: relative;width: 750px;min-height:180px;border:1px solid #000" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: green;color: white;">商品模块</div>
							<c:if test="${index.index!=0}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
							<c:if test="${!index.last}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="2" decorateModuleId="${decorateModuleCustom.id}">
							<div style="margin-top: 50px;margin-left: 10px;">
								共选${decorateModuleCustom.count}个商品，要求显示${decorateModuleCustom.showNum}个商品
							</div>
						</div>
					</div>
					<br>
				</c:if>
				<c:if test="${decorateModuleCustom.moduleType == 3}">
					<div style="position: relative;width: 750px;min-height:180px;border:1px solid #000" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: #5500FF;color: white;">特卖模块</div>
							<c:if test="${index.index!=0}">	
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
							<c:if test="${!index.last}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="3" decorateModuleId="${decorateModuleCustom.id}">
							<div style="margin-top: 50px;margin-left: 10px;">
								共选${decorateModuleCustom.count}个特卖，要求显示${decorateModuleCustom.showNum}个特卖
							</div>
						</div>
					</div>
					<br>
				</c:if>
				<c:if test="${decorateModuleCustom.moduleType == 4}">
					<div style="position: relative;width: 750px;min-height:180px;border:1px solid #000" name="moduleDiv" listModule="list" decorateModuleId = "${decorateModuleCustom.id}">
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: orange;color: white;">商品列表</div>
							<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
							<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="4" decorateModuleId="${decorateModuleCustom.id}">
							<div style="margin-top: 50px;margin-left: 10px;">
								商品筛选条件：<br>
								一级分类 = ${decorateModuleCustom.firstLevelName}<br>
								二级分类 = ${decorateModuleCustom.secondLevelName}<br>
								品牌 = ${decorateModuleCustom.brandNames}
							</div>
						</div>
					</div>
					<br>
				</c:if>
				<c:if test="${decorateModuleCustom.moduleType == 5}">
					<div style="position: relative;width: 750px;min-height:180px;border:1px solid #000" name="moduleDiv" listModule="list" decorateModuleId = "${decorateModuleCustom.id}">
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: #FF34B3;color: white;">特卖列表</div>
							<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
							<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="5" decorateModuleId="${decorateModuleCustom.id}">
							<div style="margin-top: 50px;margin-left: 10px;">
								特卖筛选条件：<br>
								一级分类 = ${decorateModuleCustom.firstLevelName}<br>
								二级分类 = ${decorateModuleCustom.secondLevelName}<br>
								品牌 = ${decorateModuleCustom.brandNames}
							</div>
						</div>
					</div>
					<br>
				</c:if>
				<c:if test="${decorateModuleCustom.moduleType == 13}">
					<div style="position: relative;width: 750px;min-height:180px;border:1px solid #000" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
		
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: blue;color: white;">
								视频模块
							</div>
							<c:if test="${index.index!=0}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
							<c:if test="${!index.last}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="13" decorateModuleId="${decorateModuleCustom.id}">
							<div style="margin-top: 50px;margin-left: 10px;">视频封面仅支持.jpg .png，宽高比与视频一致；视频文件格式支持mp4大小：100M以内。</div>
						</div>
						<img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="width: 100%;height: auto;margin-top:30px;">&nbsp;
						<video id="video" src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.videoPath}" style="width: 100%;height: auto;" preload="auto" controls="controls">
							您的浏览器不支持该视频,推荐使用火狐浏览器
						</video>
					</div>
					<br>
				</c:if>
			</c:forEach>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>