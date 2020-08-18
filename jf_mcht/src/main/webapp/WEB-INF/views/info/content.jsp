<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>信息内容</title>
    <style type="text/css">
        .hidden{
            display:none;
        }

        #info_content img{
            max-width: 95%;
            max-height: 95%;
        }
    </style>
</head>

<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="t-title"><a href="javascript:void(0);" onclick="backList();">上一级</a></div>
    </div>
    <div class="clearfix"></div>
    <div class="x_content container-fluid pt">
        <div class="row" style="text-align: center; font-size: 18px; font-weight: bold; color:#333333; padding-bottom:10px;">
            ${info.title}
        </div>
        <div class="row" style="text-align: right; font-size: 12px; color:#666666; padding: 0px 20px 20px 20px;">
            发布时间：<fmt:formatDate value='${info.releaseTime}' pattern='yyyy-MM-dd'/>
        </div>
        <div class="row" style="font-size: 12px; color:#666666; padding: 10px;">
            <p id="info_content">${info.content}</p>
        </div>
    </div>
</div>

<script type="text/javascript">
    function backList() {
        getContent('/info/listPage?catalogId='+${info.catalogId});
    }
</script>
</body>
</html>
