<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>公司信息</title>
    <!-- Bootstrap -->
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
            <span class="modal-title" id="exampleModalLabel">公司信息</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">法人身份证</td>
                            <td colspan="2" class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                        <c:if test="${mchtInfo.corporationIdcardImg1 != null && mchtInfo.corporationIdcardImg1 != ''}">
                                            <li class="pic_li">
                                                <img src="${ctx}/file_servelt${mchtInfo.corporationIdcardImg1}">
                                            </li>
                                        </c:if>
                                        <c:if test="${mchtInfo.corporationIdcardImg2 != null && mchtInfo.corporationIdcardImg2 != ''}">
                                            <li class="pic_li">
                                                <img src="${ctx}/file_servelt${mchtInfo.corporationIdcardImg2}">
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">营业执照副本</td>
                            <td colspan="2" class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:if test="${mchtInfo.blPic != null && mchtInfo.blPic != ''}">
                                        <li class="pic_li">
                                            <img src="${ctx}/file_servelt${mchtInfo.blPic}">
                                        </li>
                                    </c:if>
                                    <c:if test="${not empty mchtBlPics}">
                                        <c:forEach var="mchtBlPic" items="${mchtBlPics}">
                                            <li class="pic_li">
                                                <img src="${ctx}/file_servelt${mchtBlPic.pic}">
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                    </ul>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">组织机构代码证</td>
                            <td colspan="2" class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                        <c:if test="${mchtInfo.occPic != null && mchtInfo.occPic != ''}">
                                            <li class="pic_li">
                                                <img src="${ctx}/file_servelt${mchtInfo.occPic}">
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">税务登记证</td>
                            <td colspan="2" class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                        <c:if test="${mchtInfo.trcPic != null && mchtInfo.trcPic != ''}">
                                            <li class="pic_li">
                                                <img src="${ctx}/file_servelt${mchtInfo.trcPic}">
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">一般纳税人资格证</td>
                            <td colspan="2" class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                        <c:if test="${mchtInfo.atqPic != null && mchtInfo.atqPic != ''}">
                                            <li class="pic_li">
                                                <img src="${ctx}/file_servelt${mchtInfo.atqPic}">
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">银行开户许可证</td>
                            <td colspan="2" class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                        <c:if test="${mchtInfo.boaalPic != null && mchtInfo.boaalPic != ''}">
                                            <li class="pic_li">
                                                <img src="${ctx}/file_servelt${mchtInfo.boaalPic}">
                                            </li>
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
