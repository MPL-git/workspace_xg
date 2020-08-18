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
		if(moduleType == "1"){
			title = "编辑图片模块";
		}
		$.ligerDialog.open({
			height: $(window).height()*0.9,
			width: $(window).width()*0.9,
			title: title,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/appMng/adMng/editBrandTeamDecorateModule.shtml?decorateAreaId=" + decorateAreaId+"&moduleType="+moduleType+"&seqNo="+seqNo+"&decorateModuleId="+decorateModuleId+"&decorateInfoId="+decorateInfoId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
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
	if(confirm("是否删除图片模块，删除后不可恢复")){
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

</script>
</head>
<body style="padding: 0px;">
	<form id="dataForm" runat="server">
		<input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}">
		<input type="hidden" id="decorateAreaId" name="decorateAreaId" value="${decorateAreaId}">
		<div class="search-pannel" style="overflow-y: auto;">
			<div class="search-tr"  > 
				<div style="display: inline-flex;">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入图片模块" onclick="insertPicModule();">
					</div>
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
				
			</c:forEach>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>