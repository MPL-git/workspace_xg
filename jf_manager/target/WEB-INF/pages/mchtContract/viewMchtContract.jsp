<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
</style>
<script type="text/javascript">

</script>

<html>
<body>
<table class="gridtable">
	<tr>
		<td class="title">对方公司名称</td>
		<td colspan="3">${mchtInfo.companyName}</td>
		<td class="title">商家序号</td>
		<td>${mchtInfo.mchtCode}</td>
	</tr>
	<tr>
		<td class="title">合同类型</td>
		<td>${mchtInfo.get('mchtTypeStr')}</td>
		<td class="title">本合同编号</td>
		<td>${mchtContract.contractCode}</td>
		<td class="title">签约分类</td>
		<td>${mchtContract.get('contractTypeStr')}</td>
	</tr>
	<tr>
		<td class="title">合同开始日期</td>
		<td><fmt:formatDate value="${mchtContract.beginDate}" pattern="yyyy-MM-dd"/></td>
		<td class="title">合同结束日期</td>
		<td><fmt:formatDate value="${mchtContract.endDate}" pattern="yyyy-MM-dd"/></td>
		<td class="title">上份合同编号</td>
		<td>${lastMchtContract.contractCode}</td>
	</tr>
	<tr>
		<td class="title">生成时间</td>
		<td><fmt:formatDate value='${mchtContract.createDate}' pattern='yyyy-MM-dd'/></td>
		<td class="title">商家上传日期</td>
		<td><fmt:formatDate value='${mchtContract.uploadDate}' pattern='yyyy-MM-dd'/></td>
		<td class="title">上传扫描文件</td>
		<td>
			<a href="javascript:viewContractPic2(${mchtContract.id})" class="table-title-link">【查看】</a>
			<c:if test="${sessionScope.USER_SESSION.staffID==1 || isFw == 1}">
				<a href="javascript:toUpload(${mchtContract.id})" class="table-title-link">【上传】</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td class="title">线上审核状态</td>
		<td>${mchtContract.get('auditStatusStr')}</td>
		<td class="title">线上审核日期</td>
		<td><fmt:formatDate value='${mchtContract.auditDate}' pattern='yyyy-MM-dd'/></td>
		<td class="title">商家是否寄出</td>
		<td>${mchtContract.get('isMchtSendStr')}</td>
	</tr>
	<tr>
		<td class="title">商家寄件快递</td>
		<td>${mchtContract.get('mchtExpressName')}</td>
		<td class="title">商家寄件单号</td>
		<td>${mchtContract.mchtExpressNo}</td>
		<td class="title">商家寄出日期</td>
		<td><fmt:formatDate value='${mchtContract.mchtSendDate}' pattern='yyyy-MM-dd'/></td>
	</tr>
	<tr>
		<td class="title">合同归档状态</td>
		<td>${mchtContract.get('archiveStatusStr')}</td>
		<td class="title">归档时间</td>
		<td><fmt:formatDate value='${mchtContract.archiveDate}' pattern='yyyy-MM-dd'/></td>
		<td class="title">归档编号</td>
		<td>${mchtContract.archiveNo}</td>
	</tr>
	<tr>
		<td class="title">归档人员</td>
		<td>${mchtContract.get('archiveStaffName')}</td>
		<td class="title">是否寄回商家</td>
		<td>${mchtContract.get('isPlatformSendStr')}</td>
		<td class="title">寄回时间</td>
		<td><fmt:formatDate value='${mchtContract.platformSendDate}' pattern='yyyy-MM-dd'/></td>
	</tr>
	<tr>
		<td class="title">寄回快递名称</td>
		<td>${mchtContract.get('platformExpressName')}</td>
		<td class="title">寄回快递单号</td>
		<td>${mchtContract.platformExpressNo}</td>
		<td class="title">PDF文件</td>
		<td>
			<c:if test="${empty mchtContract.filePath || mchtContract.filePath.length() == 0}">
				未生成
			</c:if>
			<c:if test="${not empty mchtContract.filePath && mchtContract.filePath.length() > 0}">
				<a href="${pageContext.request.contextPath}/file_servelt/${mchtContract.filePath}" class="table-title-link">【下载】</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td class="title">续签状态</td>
		<td>${mchtContract.get('renewStatusStr')}</td>
		<td class="title">续签合同编号</td>
		<c:if test="${isNew eq 1 || isNew eq undefined || mchtContract.archiveStatus ne 0}">
			<td>${renewMchtContract.contractCode}</td>
		</c:if>
		<c:if test="${mchtContract.archiveStatus eq 0 && isNew eq 0}">
			<td><a href="javascript:renewalContract(${mchtContract.id});">【重新生成】</a></td>
		</c:if>
		<td class="title">续签合同归档状态</td>
		<td>${renewMchtContract.get('archiveStatusStr')}</td>
	</tr>
	<tr>
		<td class="title">归档备注/驳回原因</td>
		<td colspan="5">
			<textarea rows=3 name="mchtContract.remarks" cols="90" class="text" ><%=htmlspecialchars(htmlData)%>${mchtContract.remarks}</textarea>
		</td>
	</tr>
	<tr>
		<td class="title">续签状态说明</td>
		<td colspan="5">
			<textarea rows=3 name="mchtContract.renewRemarks" cols="90" class="text" >${mchtContract.renewRemarks}</textarea>
		</td>
	</tr>
</table>


<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
<script type="text/javascript">
	var editor1;
	var viewerPictures;
	$(function() {
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		
		KindEditor.ready(function(K) {
			 editor1 = K.create('textarea[name="mchtContract.remarks"]', {
				width:'85%',
				height : '650px',
				cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
				uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
				fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
				}
				
			});
			prettyPrint();
		});
	});


	function viewContractPic(mchtContractId){
		var url = "${pageContext.request.contextPath}/mchtContract/viewContractPic.shtml";
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {id:mchtContractId},
			timeout : 30000,
			success : function(result) {
				if(result.success){
					var picList = result.data.picList;
					console.log(picList);
					if(picList.length == 0){
						$.ligerDialog.error('暂无上传文件');
						return;
					}
					var pic;
					for (var i=0; i<picList.length; i++){
						pic = "${pageContext.request.contextPath}/file_servelt" + picList[i].pic;
						$("#viewer-pictures").append('<li><img data-original="' + pic + '" src="' + pic + '" alt=""></li>');
					}
					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
						$("#viewer-pictures").hide();
					}});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	}

	// 查看合同扫描件
	function viewContractPic2(mchtContractId) {
		$.ligerDialog.open({
			height: 700,
			width: 800,
			title: "查看合同",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/viewContractPicPage.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	function toUpload(mchtContractId){
		$.ligerDialog.open({
			height: 600,
			width: 800,
			title: "合同",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/toUpload.shtml?id=" + mchtContractId+"&isSpecial=1",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	
	// 查看商家合同详情
	function renewalContract(mchtContractId) {
		$.ligerDialog.open({
			height: $(window).height() - 300,
			width: $(window).width() - 700,
			title: "重新生成合同",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mchtContract/renewalContract.shtml?id=" + mchtContractId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
</script>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>