<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />	


<script type="text/javascript">
	
	//修改五粒导航
	function editModuleNavigation(_this){
		var ModuleNavigationId = _this;
		$.ligerDialog.open({
			   height: $(window).height()*0.9,
			   width: $(window).width()*0.5,
			   title: "编辑",
			   name: "INSERT_WINDOW",
			   url: "${pageContext.request.contextPath}/appMng/adMng/editModuleNavigation.shtml?ModuleNavigationId="+ModuleNavigationId+"&decorateModuleId="+$("#decorateModuleId").val(),
			   showMax: true,
			   showToggle: false,
			   showMin: false,
			   isResize: true,
			   slide: false,
			   data: null
			  });
	}
	//删除五粒导航
	function delModuleNavigation(_this){
		var moduleNavigationId = _this;
		$.ligerDialog.confirm("是否删除？", function (yes) {
			if(yes) {
				$.ajax({
					url : "${pageContext.request.contextPath}/appMng/adMng/delModuleNavigation.shtml",
					data : {moduleNavigationId : moduleNavigationId},
					dataType : 'json',
					success : function(data) {
						if('0000' == data.returnCode) {
							location.reload();	
							//listModel.gridManager.reload();
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
	//保存五粒导航(返回上级页面)
	function goBackRefresh(){
		parent.location.reload();	
	}

</script>

<html>
<body>
		<input type="hidden" id="decorateModuleId" value="${decorateModuleId}">
		<input type="button" style="float:left;width: 80px;height: 35px;cursor: pointer;" value="添加" id="editModuleNavigation" onclick="editModuleNavigation('')">
		<!-- <input type="button" style="float:left;width: 80px;height: 35px;cursor: pointer;" value="保存" id="goBackRefresh" onclick="goBackRefresh()"> -->
		<font size="4px">&nbsp;&nbsp;当前内容已保存</font>
		<br>
		<br>
		<br>
		<table class="gridtable" style="width:840px;height: 260px">
		<c:if test="${not empty ModuleNavigationListOne}">
			<tr id="row1" style="width:840px;height: 85px;">
					<c:forEach items="${ModuleNavigationListOne}" var="ModuleNavigation">
						<c:if test="${ModuleNavigation ne null}">
						<td style="width: 70px;padding: 0px;">
						<img id="pic1"+${ModuleNavigation.col} style="margin-left: 2px;width: 67px;height: 67px" alt="" src="${pageContext.request.contextPath}/file_servelt${ModuleNavigation.pic}">
						<br>
						<div style="width: 70px;text-align:center">
						<a href="javascript:void(0);" id="editPic" onclick='editModuleNavigation("${ModuleNavigation.id}")'>编辑</a>
						<a href="javascript:void(0);" id="delPic" onclick='delModuleNavigation("${ModuleNavigation.id}")'>删除</a>
						</div>
						</td>
						</c:if>
						<c:if test="${ModuleNavigation  eq  null}">
						<td style="width: 70px;padding: 0px;">
						<div><img id="pic1" style="margin-left: 2px;width: 67px;height: 67px" alt="" src="${pageContext.request.contextPath}/file_servelt${ModuleNavigation.pic}"></div>
						</td>
						</c:if>
					</c:forEach>
			</tr>
		</c:if>
		<c:if test="${not empty ModuleNavigationListTwo}">
			<tr id="row2" style="height: 85px;">
					<c:forEach items="${ModuleNavigationListTwo}" var="ModuleNavigation">
						<c:if test="${ModuleNavigation ne null}">
						<td style="width: 70px;padding: 0px;">
						<img id="pic1"+${ModuleNavigation.col} style="margin-left: 2px;width: 67px;height: 67px" alt="" src="${pageContext.request.contextPath}/file_servelt${ModuleNavigation.pic}">
						<br>
						<div style="width: 70px;text-align:center">
						<a href="javascript:void(0);" id="editPic" onclick='editModuleNavigation("${ModuleNavigation.id}")'>编辑</a>
						<a href="javascript:void(0);" id="delPic" onclick='delModuleNavigation("${ModuleNavigation.id}")'>删除</a>
						</div>
						</td>
						</c:if>
						<c:if test="${ModuleNavigation  eq  null}">
						<td style="width: 70px;padding: 0px;">
						<div><img id="pic2" style="margin-left: 2px;width: 67px;height: 67px" alt="" src="${pageContext.request.contextPath}/file_servelt${ModuleNavigation.pic}"></div>
						</td>
						</c:if>
					</c:forEach>
			</tr>
		</c:if>
		<c:if test="${not empty ModuleNavigationListThree}">
			<tr id="row3" style="height: 85px;">
					<c:forEach items="${ModuleNavigationListThree}" var="ModuleNavigation">
						<c:if test="${ModuleNavigation ne null}">
						<td style="width: 70px;padding: 0px;">
						<img id="pic1"+${ModuleNavigation.col} style="margin-left: 2px;width: 67px;height: 67px" alt="" src="${pageContext.request.contextPath}/file_servelt${ModuleNavigation.pic}">
						<br>
						<div style="width: 70px;text-align:center">
						<a href="javascript:void(0);" onclick='editModuleNavigation("${ModuleNavigation.id}")'>编辑</a>
						<a href="javascript:void(0);" onclick='delModuleNavigation("${ModuleNavigation.id}")'>删除</a>
						</div>
						</td>
						</c:if>
						<c:if test="${ModuleNavigation  eq  null}">
						<td style="width: 70px;padding: 0px;">
						<div><img id="pic3" style="margin-left: 2px;width: 67px;height: 67px" alt="" src="${pageContext.request.contextPath}/file_servelt${ModuleNavigation.pic}"></div>
						</td>
						</c:if>
					</c:forEach>
			</tr>
		</c:if>	
		</table>

	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
		
</body>
</html>
