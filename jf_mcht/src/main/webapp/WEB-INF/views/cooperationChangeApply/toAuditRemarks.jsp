<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>查看原因</title>
</head>

<body>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title">查看原因</span>
      </div>
     <div class="modal-body">
     <div>
     	<textarea class="form-control" style="width: 100%;resize: vertical;margin-bottom: 10px;" rows="5">${auditRemarks}</textarea>
     </div>
    </div>
    </div>
  </div>
</body>
</html>
