<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>购物津贴规则</title>
</head>

<body>
<!--协议弹框 -->
  <div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">购物津贴规则</span>
      </div>
      <div class="modal-body">
     	${content}
      </div>
    </div>
  </div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
