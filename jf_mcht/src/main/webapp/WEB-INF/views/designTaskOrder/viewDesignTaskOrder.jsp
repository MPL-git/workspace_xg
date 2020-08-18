<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>任务查看</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <style>
        .td-pictures li {
            display: inline-block;
        }

        .td-pictures li img {
            width: 100px;
            height: 100px;
        }

        .glyphicon-remove {
            color: red;
            margin-left: 3px;
            cursor: pointer;
        }

        .docs-pictures {
            padding: 0px;
        }

        .docs-pictures li {
            position: relative;
            margin: 3px;
        }

        .pic-btn-container {
            width: 100%;
            position: absolute;
            top: 0px;
            background: rgba(0, 0, 0, 0.5);
            height: 0px;
            z-index: 300;
            overflow: hidden;
            text-align: right;
            padding-right: 3px;
        }

        .sku_pic_picker div {
            position: absolute;
            width: 50px;
            height: 50px;
            line-height: 50px;
            font-size: 50px;
            z-index: 1000;
            color: #ddd;
            border: solid 1px #ddd;
        }

        .sku_pic_picker input {
            position: absolute;
            width: 50px;
            height: 50px;
            opacity: 0;
            z-index: 1002;
        }

        .sku_pic_picker img {
            width: 50px;
            height: 50px;
        }

        .file {
            position: relative;
            display: inline-block;
            background: #3c9eff;
            /*border-radius: 4px;*/
            padding: 0px 12px;
            overflow: hidden;
            color: #fff;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }

        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }

        .file:hover {
            background: #AADFFD;
            border-color: #78C3F3;
            color: #004974;
            text-decoration: none;
        }

        #imgBox li {
            position: relative;
            list-style-type: none;
            border: 1px solid #ccc;
            padding: 3px;
            float: left;
            height: 140px;
            margin-left: -40px;
        }

        .toolbar {
            position: absolute;
            left: 45%;
            top: 45%;
        }

        #coverPicker div:nth-child(2) {
            width: 100% !important;
            height: 100% !important;
        }

        .webuploader-container div:nth-child(2){
            position: absolute !important;
            top: 0px !important;
            left: 0px !important;
        }
    </style>
</head>
<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="col-md-12 t-title">任务查看
            <a href="javascript:void(0);" onclick="backToDesignTaskOrderList()">返回</a>
        </div>
    </div>
    <div class="ad-form" style="padding-bottom: 0px;">
        <div class="table-responsive">
            <table class="table table-bordered ">
                <tbody>
                    <tr>
                        <td class="editfield-label-td">订单编号</td>
                        <td colspan="2" class="text-left">${designTaskOrderCustom.orderCode}</td>
                    </tr>
                    <tr>
                        <td class="editfield-label-td">任务分类</td>
                        <td colspan="2" class="text-left">${designTaskOrderCustom.taskTypeDesc}</td>
                    </tr>
                    <tr>
                        <td class="editfield-label-td">任务名称</td>
                        <td colspan="2" class="text-left">${designTaskOrderCustom.taskName}</td>
                    </tr>
                    <tr>
                        <td class="editfield-label-td">需求详情</td>
                        <td colspan="2" class="text-left">
                            ${designTaskOrderCustom.requirement}
                            <c:if test="${not empty designTaskOrderCustom.filePath}">
                                <div style="margin-top: 10px;">
                                    <a href="${ctx}/file_servelt${designTaskOrderCustom.filePath}" class="btn btn-info btn-sm">附件下载</a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="editfield-label-td">支付金额</td>
                        <td colspan="2" class="text-left">
                            <span style="color: red;">￥${designTaskOrderCustom.payAmount}</span>
                        </td>
                    </tr>
                    <tr>
                        <td class="editfield-label-td">联系方式</td>
                        <td colspan="2" class="text-left">${designTaskOrderCustom.contactWay}</td>
                    </tr>
                    <tr>
                        <td class="editfield-label-td">联系QQ</td>
                        <td colspan="2" class="text-left">${designTaskOrderCustom.qq}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="ad-form" style="padding-top: 0px;padding-bottom: 0px;">
        <h4>图片详情</h4>
        <div class="table-responsive">
            <table class="table table-bordered ">
                <tbody>
                    <c:if test="${not empty designTaskOrderCustom and designTaskOrderCustom.designStatus == '1'}">
                        <c:forEach var="designTaskOrderPic" varStatus="index" items="${designTaskOrderPicList}" >
                            <tr>
                                <td class="editfield-label-td">图片${index.count}</td>
                                <td colspan="2" class="text-left">
                                    <img src="${ctx}/file_servelt${designTaskOrderPic.pic}" style="width: 400px;height: 200px;">
                                    <a href="${ctx}/file_servelt${designTaskOrderPic.pic}" class="btn btn-info btn-sm" style="margin: 170px 0px 0px 20px;">点击下载</a>
                                    <div>
                                        <span style="color:#999;line-height: 30px;vertical-align: top;">${designTaskOrderPic.picDetail}</span>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty designTaskOrderCustom and designTaskOrderCustom.designStatus == '0'}">
                        <c:forEach var="designTaskOrderPicDetail" varStatus="index" items="${designTaskOrderPicDetailList}" >
                            <tr>
                                <td class="editfield-label-td">图片${index.count}</td>
                                <td colspan="2" class="text-left">
                                    <span style="color: #999">尺寸：${designTaskOrderPicDetail.width}x${designTaskOrderPicDetail.height}（px）图片说明：${designTaskOrderPicDetail.picDesc}</span>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="ad-form" style="padding-top: 0px;">
        <c:if test="${empty designTaskOrderCustom.commentType and designTaskOrderCustom.designStatus == '1'}">
            <form id="dataFrom">
                <div class="table-responsive">
                    <div style="margin: 0px 0px 50px 20px;">
                        <div style="margin: 5px 0px;">
                            <span>图片评价：</span>
                            <span>
                                <c:forEach var="commentType" items="${commentTypeList}">
                                    <span style="margin-left: 10px;">
                                        <label>
                                            <input type="radio" name="commentType" value="${commentType.statusValue}" <c:if test="${commentType.statusValue == 1}">checked</c:if> >
                                            ${commentType.statusDesc}
                                        </label>
                                    </span>
                                </c:forEach>
                            </span>
                        </div>
                        <div>
                            <span style="margin-left: 85px;">
                                <input placeholder="亲，写点评价吧，您的评价能够使设计师设计出令您更加满意的作品。"
                                   maxlength="200" class="ad-select" type="text" id="comment"
                                   onkeyup="$('#commentLength').text(200-($('#comment').val().length));"
                                   value="" name="comment" validate="{rangelength:[0,200]}"
                                   style="width: 400px;margin-right: 10px;">
                                <span>最多还可输入：</span>
                                <span style="color:red;margin: 0 3px;" id="commentLength">200</span><span>个字</span>
                            </span>
                        </div>
                    </div>
                    <div style="text-align:center">
                        <button type="button" class="btn btn-info" onclick="commitSave();">提交评价</button>
                    </div>
                </div>
                <input type="hidden" name="id" value="${designTaskOrderCustom.id}" >
            </form>
        </c:if>
        <c:if test="${not empty designTaskOrderCustom.commentType and designTaskOrderCustom.designStatus == '1'}">
            <div style="margin: 0px 0px 50px 20px;">
                <div style="margin: 5px 0px;">
                    <span>好评度：</span>
                    <span>${designTaskOrderCustom.commentTypeDesc}</span>
                </div>
                <div>
                    <span style="margin-left: 12px;">评语：</span>
                    <span>${designTaskOrderCustom.comment}</span>
                </div>
            </div>
        </c:if>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>

<script>

    var dataFromValidate;

    $(function() {
        $.metadata.setType("attr", "validate");
        dataFromValidate = $("#dataFrom").validate({
            highlight: function (element) {
                $(element).addClass('error');
                $(element).closest('tr').find("td").first().addClass('has-error');
            },
            success: function (label) {
                label.closest('tr').find("td").first().removeClass('has-error');
            },
            errorPlacement: function (error, element) {
                error.appendTo($(element).closest('td'));
            }
        });

        <c:if test="${empty designTaskOrderCustom.commentType}">
            initRestInput();
        </c:if>
    });

    function backToDesignTaskOrderList() {
        getContent('${ctx}/designTaskOrder/designTaskOrderManager');
    }

    function commitSave() {
        if(dataFromValidate.form()) {
            var dataJson = $("#dataFrom").serializeArray();
            $.ajax({
                url: "${ctx}/designTaskOrder/viewDesignTaskOrder",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
                        backToDesignTaskOrderList();
                    } else {
                        swal({
                            title: result.returnMsg,
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }

                },
                error: function () {
                    swal({
                        title: "提交失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            });
        }
    }

    function initRestInput() {
        <c:if test="${not empty designTaskOrderCustom and designTaskOrderCustom.designStatus == '1'}">
            $('#commentLength').text(200-($('#comment').val().length));
        </c:if>
    }

</script>
</body>
</html>