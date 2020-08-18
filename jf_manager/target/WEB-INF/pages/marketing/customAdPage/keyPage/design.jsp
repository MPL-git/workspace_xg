<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript">
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("img").bind('click',function(){
		viewerPic($(this).attr("src"));
	});
	
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
		$("#viewer-pictures").hide();
		viewerPictures.show();

	}
	
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
		}else if(moduleType == "9"){
			title = "编辑滑动栏目模块";
		}else if(moduleType == "12"){
			title = "编辑";
			closeRefresh=true;
		}
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/adMng/editDecorateModule.shtml?decorateAreaId=" + decorateAreaId+"&moduleType="+moduleType+"&seqNo="+seqNo+"&decorateModuleId="+decorateModuleId+"&decorateInfoId="+decorateInfoId,
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
				url : "${pageContext.request.contextPath}/appMng/adMng/moveDecorateModule.shtml?prevId="+prevId+"&nextId="+nextId+"&prevSeqNo="+prevSeqNo+"&nextSeqNo="+nextSeqNo,
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
				url : "${pageContext.request.contextPath}/appMng/adMng/moveDecorateModule.shtml?prevId="+prevId+"&nextId="+nextId+"&prevSeqNo="+prevSeqNo+"&nextSeqNo="+nextSeqNo,
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

function del(id,obj){
	if(confirm("是否删除模块，删除后不可恢复")){
		if(id && id>0){
			$.ajax({
				url : "${pageContext.request.contextPath}/appMng/adMng/deleteDecorateModule.shtml?id="+id,
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
										'<div style="margin-top: 100px;margin-left: 70px;">'+
											'自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽为1242像素，高度不限，大小：200Kb以内'+
										'</div>'+
								'</div>'+
								'<img src="" style="width: 100%;margin-top: 30px;">'+
						'</div>'+
						'<br>';
	if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
		$("#decorateModuleDiv").find("div[listModule='list']").before(picModuleDiv);
	}else{
		$("#decorateModuleDiv").append(picModuleDiv);
	}
}

function insertSlideColumnModule(){
	var picModuleDiv = '<div style="position: relative;width: 750px;height:500px;border:1px solid #000" name="moduleDiv" decorateModuleId = "">'+
								'<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
									'<div style="float: left;padding-top:7px;padding-left:12px;width:80px;height:23px;background-color:blue;color:white;">滑动栏目模块</div>'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(0,this);">'+
										'<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="9" decorateModuleId="">'+
										'<div style="margin-top: 100px;margin-left: 70px;">'+
											'自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽>1242像素，高为576像素，大小：200Kb以内'+
										'</div>'+
								'</div>'+
								'<img src="" style="width: 100%;margin-top: 30px;">'+
						'</div>'+
						'<br>';
	if($("#decorateModuleDiv").find("div[listModule='list']").length>0){
		$("#decorateModuleDiv").find("div[listModule='list']").before(picModuleDiv);
	}else{
		$("#decorateModuleDiv").append(picModuleDiv);
	}
}

function insertFiveGranuleNevigateModule(){
	var decorateInfoId = $("#decorateInfoId").val();
	var decorateAreaId = $("#decorateAreaId").val();
	var moduleType = "12";	
	$.ajax({
		url : "${pageContext.request.contextPath}/activityAreaNew/getModuleCount.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				if(data.count>0){
					commUtil.alertError("已经存在五粒导航模块,不可重复添加");
				}else{
					$.ajax({
						  url : "${pageContext.request.contextPath}/customAdPage/keyPage/insertFiveGranuleNevigateModule.shtml",
						  type : 'POST',
						  dataType : 'json',
						  cache : false,
						  async : false,
						  data : {decorateAreaId:decorateAreaId,moduleType:moduleType},
						  timeout : 30000,
						  success : function(data) {
						   if ("0000" == data.returnCode) {
						    location.reload();
						   }else{
						    $.ligerDialog.error(data.returnMsg);
						   }
						  },
						  error : function() {
						   $.ligerDialog.error('操作超时，请稍后再试！');
						  }
						 });
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

function insertXgSeckillModule(){
	var decorateInfoId = $("#decorateInfoId").val();
	var decorateAreaId = $("#decorateAreaId").val();
	var moduleType = "11";
	$.ajax({
		url : "${pageContext.request.contextPath}/activityAreaNew/getModuleCount.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				if(data.count>0){
					commUtil.alertError("已经存在秒杀模块,不可重复添加");
				}else{
					$.ajax({
						url : "${pageContext.request.contextPath}/customAdPage/keyPage/insertXgSeckillModule.shtml",
						type : 'POST',
						dataType : 'json',
						cache : false,
						async : false,
						data : {decorateAreaId:decorateAreaId,moduleType:moduleType},
						timeout : 30000,
						success : function(data) {
							if ("0000" == data.returnCode) {
								location.reload();
							}else{
								$.ligerDialog.error(data.returnMsg);
							}
						},
						error : function() {
							$.ligerDialog.error('操作超时，请稍后再试！');
						}
					});
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
</script>
</head>
<body style="padding: 0px;">
	<form id="dataForm" runat="server">
		<input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}">
		<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateAreaId}">
		<div class="search-pannel" style="overflow-y: hidden;">
			<div class="search-tr"  > 
				<div style="display: inline-flex;" id = "topButtons">
					<c:if test="${pageType ne 20}">
						<div style="padding-left: 10px;">
							<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入图片模块" onclick="insertPicModule();">
						</div>
						<!-- <div style="padding-left: 10px;">
							<input type="button" style="width: 100px;height: 30px;cursor: pointer;" value="插入滑动栏目模块" onclick="insertSlideColumnModule();">
						</div> -->
						<div style="padding-left: 10px;">
							<input type="button" style="width: 100px;height: 30px;cursor: pointer;" value="插入醒购秒杀" onclick="insertXgSeckillModule();">
						</div>
						<div style="padding-left: 10px;">
							<input type="button" style="width: 100px;height: 30px;cursor: pointer;" value="插入五粒导航" onclick="insertFiveGranuleNevigateModule();">
						</div>
					</c:if>
					<c:if test="${pageType eq 20}">
						<div style="padding-left: 10px;">
							<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入图片模块" onclick="insertPicModule();">
						</div>
					</c:if>
					<div style="padding-left: 10px;">
						<a href="${previewUrl}" style="font-size: 20px;" target="_blank">预览</a>
					</div>
				</div>
			</div>
			<div class="search-tr" id="decorateModuleDiv">
			<c:forEach items="${decorateModuleCustoms}" var="decorateModuleCustom" varStatus="index">
				<c:if test="${decorateModuleCustom.moduleType == 1}">
					<div style="position: relative;width: 750px;" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
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
							<div style="margin-top: 100px;margin-left: 70px;">自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽为1242像素，高度不限，大小：200Kb以内</div>
						</div>
						<img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="width: 100%;height: auto;margin-top: 30px;">
					</div>
					<br>
				</c:if>
				<c:if test="${decorateModuleCustom.moduleType == 9}">
					<div style="position: relative;width: 750px;" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 80px;height: 23px;background-color: blue;color: white;">
								滑动栏目模块
							</div>
							<c:if test="${index.index!=0}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
							<c:if test="${!index.last}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}">
							</c:if>
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="9" decorateModuleId="${decorateModuleCustom.id}">
							<div style="margin-top: 100px;margin-left: 70px;">自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽>1242像素，高为576像素，大小：200Kb以内</div>
						</div>
						<img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="width: 100%;height: auto;margin-top: 30px;">
					</div>
					<br>
				</c:if>
				<c:if test="${decorateModuleCustom.moduleType == 11}">
					<div style="margin-left: 4px;position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 80px;height: 23px;background-color: red;color: white;">醒购秒杀模块</div>
							<c:if test="${index.index!=0}">	
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
							</c:if>
							<c:if test="${!index.last}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
							</c:if>
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
							<div style="margin-top: 50px;margin-left: 20px;">
								<c:forEach items="${decorateModuleCustom.seckillTimes}" var="seckillTime">
									<input type="button" style="width: 70px;height: 30px;cursor: default;" value="${seckillTime.startHours}:${seckillTime.startMin}">&nbsp;&nbsp;
								</c:forEach>
							</div>
						</div>
					</div>
					<br>
				</c:if>
				<c:if test="${decorateModuleCustom.moduleType == 12}">
					<div style="margin-left: 4px;position: relative;width: 750px;height: 285px;border:1px solid #000" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
						<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
							<div style="float: left;padding-top:7px;padding-left:12px;width: 80px;height: 23px;background-color: orange;color: white;">五粒导航模块</div>
							<c:if test="${index.index!=0}">	
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
							</c:if>
							<c:if test="${!index.last}">
								<input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
							</c:if>
								<font size="4px">&nbsp;&nbsp;APP展示位置同下方缩略图</font>
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
								<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="12" decorateModuleId="${decorateModuleCustom.id}">
							<div style="margin-top: 35px;margin-left: 3px;">
								<table id="moduleNavigationTable" border = "0" cellpadding = "0" cellspacing = "0">
									<tr>
										<c:forEach items="${decorateModuleCustom.moduleNavigationFirstLine}" var="moduleNavigation">
											<td>
												<img id="1pic${moduleNavigation.col}" style="width: 62px;height: 62px" alt="" src="${pageContext.request.contextPath}/file_servelt${moduleNavigation.pic}">
											</td>
										</c:forEach>
									</tr>
									
									<tr>
										<c:forEach items="${decorateModuleCustom.moduleNavigationSecondLine}" var="moduleNavigation">
											<td>
												<img id="2pic${moduleNavigation.col}" style="width: 62px;height: 62px" alt="" src="${pageContext.request.contextPath}/file_servelt${moduleNavigation.pic}">
											</td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${decorateModuleCustom.moduleNavigationThirdLine}" var="moduleNavigation">
											<td>
												<img id="3pic${moduleNavigation.col}" style="width: 62px;height: 62px" alt="" src="${pageContext.request.contextPath}/file_servelt${moduleNavigation.pic}">
											</td>
										</c:forEach>
									</tr>
								</table>
							</div>
						</div>
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