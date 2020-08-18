<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>合同详情</title>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/imageUploader.css"/>
    <style type="text/css">
        .td-pictures li {
            display: inline-block;
        }

        .td-pictures li img {
            width: 100px;
            height: 100px;
        }

        .docs-pictures li {
            position: relative;
            margin: 3px;
        }

    </style>
</head>
<body>

<div class="modal-dialog" role="document" style="width:800px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">合同详情</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">合同详情</td>
                            <td colspan="2" class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                        <c:if test="${not empty contractPicList}">
                                            <c:forEach var="contractPic" items="${contractPicList}">
                                                <li class="pic_li">
                                                    <img src="${ctx}/file_servelt${contractPic.pic}">
                                                </li>
                                            </c:forEach>
                                        </c:if>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/imageUpload.js" ></script>
</body>
</html>
