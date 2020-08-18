<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>合作协议终止预览</title>
	<!-- Bootstrap -->
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
	<style type="text/css">
		.hidden{
			display:none;
		}
	</style>
</head>

<body>

<div class="modal-dialog" role="document" style="width:1200px;">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
					aria-hidden="true">&times;</span></button>
			<span class="modal-title" id="exampleModalLabel">终止合作协议书预览</span>
		</div>
		<div class="modal-body">
			<div class="ie hidden" style="background-color:#FAE220">IE浏览器暂不支持在线预览PDF，请下载合同再打印，或使用现代浏览器如谷歌、opera等预览打印</div>
			<div class="media" style="background-color: rgb(255, 255, 255); width: 1100px;">
				<iframe id="printIframe" width="1100" height="700"></iframe>
			</div>
		</div>

		<div class="modal-footer">
			<button class="btn btn-info" data-dismiss="modal">关闭</button>
			<button class="btn btn-info not-ie" onclick="printContract()">打印合同</button>
			<br>
			<span style="color: red;">备注：无法打印合同，请用谷歌浏览器或点击右上角的<img src="${ctx}/static/images/print.png" title="打印">打印图片</span>
		</div>
	</div>
</div>


<script src="${ctx}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript">

	$(function () {
		if(isIE()){
			$(".ie").removeClass("hidden");
			$(".not-ie").addClass("hidden");
		}else{
			$("#printIframe").attr("src", "${ctx}/mchtCloseApplication/endAgreementPDF?id=${mchtCloseApplication.id}");
		}
	});

	function isIE() {
		if (!!window.ActiveXObject || "ActiveXObject" in window)
			return true;
		else
			return false;
	}


	/**
	 * 打印合同
	 */
	function printContract(){
		$("#printIframe")[0].contentWindow.print();
	}

	function downloadContract(){
		window.location.href = "${ctx}/file_servelt/${mchtCloseApplication.filePath}";
	}

</script>
</body>
</html>