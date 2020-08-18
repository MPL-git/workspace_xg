<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>打印并上传</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
    <!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
</head>
<body>

<!-- 	查看信息框 -->

<div class="modal fade" id="changeViewModal" tabindex="-1" role="dialog" aria-labelledby="changeViewModalLabel" data-backdrop="static">
</div>
<!-- 	查看信息框 -->
<div class="modal fade" id="myViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>

<div class="modal-dialog" role="document" style="width:1000px;">
    <input type="hidden" id="id" name="id" value="${id}">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">打印并上传</span>
        </div>
        <div class="modal-body">
            <div>第1步：<a href="javascript:;" onclick="toPrint(${id});">预览变更函，打印变更函</a></div><br>
            <div>第2步：将变更函盖公章<a href="javascript:;" onclick="toUpload(${id});">【扫描上传】</a></div><br>
            <div>备注：盖章要求：请将变更函打印下来加盖公章，若超过两页盖骑缝章（保证章印清楚）;<br>
                上传要求：扫描后按照页码顺序上传，保证图片清晰，内容不模糊
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    function showInfo(url){
        if(url == "")	return;
        $.ajax({
            url: url,
            type: "GET",
            success: function(data){
                $("#changeViewModal").html(data);
                $("#changeViewModal").modal();
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }

    function toPrint(id){
        showInfo('${ctx}/cooperationChangeApply/changeAgreementPreview?id='+id);
    }

    function toUpload(id){
        $.ajax({
            url: "${ctx}/cooperationChangeApply/toUpload?id="+id,
            type: "GET",
            success: function(data){
                $("#changeViewModal").html(data);
                $("#changeViewModal").modal();
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }
</script>
</body>
</html>
