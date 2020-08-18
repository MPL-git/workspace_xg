<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>驳回原因</title>
<style type="text/css">
img{
	max-width: 50%;
	max-height: 50%;
}
</style>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">驳回原因</span>
      </div>
     <div class="modal-body">
     <div>
     	${fwInnerRemarks}
     </div>
    </div>
    </div>
  </div>
  
  <script type="text/javascript">
	
	
  </script>
</body>
</html>
