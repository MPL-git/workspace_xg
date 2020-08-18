<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>查看品牌</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/imageUploader.css"/>
    <style type="text/css">

        .td-pictures li{
            display: inline-block;
        }
        .td-pictures li img{
            width: 40px;
            height: 40px;
        }
    </style>
</head>
<body>
<!--查看品牌 -->
<div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 910px;">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">查看品牌</span>
        </div>
        <div class="modal-body">

            <div class="table-responsive">
                <table class="table table-bordered ">
                    <tbody>
                    <tr>
                        <td class="editfield-label-td">品牌名称</td>
                        <td colspan="2" class="text-left">
                            <span>${mchtProductBrand.productBrandName }</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">商标注册证或受理通知书 <span class="required">*</span></td>
                        <td colspan="2" class="text-left">
                            <div id="mchtBrandAptitudeDiv">
                                <c:forEach var="mchtBrandAptitudeCustom" items="${mchtBrandAptitudeCustoms}"
                                           varStatus="status">
                                    <table class="table table-bordered"
                                           mchtBrandAptitudeId="${mchtBrandAptitudeCustom.id}">
                                        <tbody>
                                        <tr>
                                            <td class="editfield-label-td">商标注册证号<span class="required">*</span></td>
                                            <td colspan="2" class="text-left">
                                                    ${mchtBrandAptitudeCustom.certificateNo}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="editfield-label-td">本商标注册证相关文件<span class="required">*</span>
                                            </td>
                                            <td colspan="2" class="text-left">
                                                <div class="pic-container">
                                                    <ul class="docs-pictures clearfix td-pictures pictures-list">
                                                        <c:forEach var="mchtBrandAptitudePic"
                                                                   items="${mchtBrandAptitudeCustom.mchtBrandAptitudePics}">
                                                            <li class="pic_li">
                                                                <img src="${ctx}/file_servelt${mchtBrandAptitudePic.pic}">
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </c:forEach>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">销售授权书</td>
                        <td colspan="2" class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:forEach var="mchtPlatformAuthPic" items="${mchtPlatformAuthPics}">
                                        <li class="pic_li">
                                            <img src="${ctx}/file_servelt${mchtPlatformAuthPic.pic}">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">进货发票</td>
                        <td colspan="2" class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:forEach var="mchtBrandInvoicePic" items="${mchtBrandInvoicePics}">
                                        <li class="pic_li">
                                            <img src="${ctx}/file_servelt${mchtBrandInvoicePic.pic}">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">质检报告/检疫报告</td>
                        <td colspan="2" class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:forEach var="mchtBrandInspectionPic" items="${mchtBrandInspectionPics}">
                                        <li class="pic_li">
                                            <img src="${ctx}/file_servelt${mchtBrandInspectionPic.pic}">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/imageUpload.js" ></script>
</body>
</html>
