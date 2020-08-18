<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/css/sweetalert.css" rel="stylesheet">
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
        .sweet-alert button {
            padding: 0px 20px;
            height: 38px;
        }
    </style>
</head>
<body>
<div class="x_panel container-fluid">
    <div class="ad-form" style="padding-bottom: 0px;">
        <div class="table-responsive">
            <div>
                <span style="margin-left: 50px;">支付订单：${designTaskOrderCustom.orderCode}</span>
                <a href="javascript:void(0);" onclick="problemGuideline();" style="float: right;margin-right: 50px;">
                    <span class="glyphicon glyphicon-question-sign"></span>问题指引
                </a>
            </div>
            <div style="margin-top: 5px;">
                <div style="margin-left: 50px;width: 200px;display: inline-block;vertical-align: top;">
                    支付金额：<span style="color: #008200;font-weight: bold;">￥${designTaskOrderCustom.payAmount}</span>
                </div>
                <div style="margin-left: 50px;color: #999;display: inline-block;">
                    <div class="triangle-top" style="display: none;">
                        <p onclick="viewTriangle(false);" >订单详情<span class="glyphicon glyphicon-triangle-top"></span></p>
                        <p style="width: 460px;"><span>任务名称：${designTaskOrderCustom.taskName}</span></p>
                        <div style="width: 500px;">
                            <table>
                                <tr>
                                    <td style="vertical-align: top;min-height: 28px;height: 28px;">需求详情：</td>
                                    <td style="width: 400px;text-align: left;vertical-align: top;min-height: 28px;height: 28px;white-space: pre-line;">${designTaskOrderCustom.requirement}</td>
                                </tr>
                            </table>
                        </div>
                        <p><span>支付金额：￥${designTaskOrderCustom.payAmount}</span></p>
                        <p><span>下单时间：${designTaskOrderCustom.remarks}</span></p>
                    </div>
                    <div class="triangle-bottom">
                        <p onclick="viewTriangle(true);" >订单详情<span class="glyphicon glyphicon-triangle-bottom"></span></p>
                    </div>
                </div>
            </div>
            <hr/>
            <div>
                <div>
                    <p>支付方式：</p>
                    <p>
                        <img src="${pageContext.request.contextPath}/static/images/ali-btn.png" />
                    </p>
                    <div style="width: 200px;display: inline-block;">
                        <div id="qrcode" style="margin-bottom: 10px;margin-left: 5px;"></div>
                        <span style="color: white;background-color: #008200;">请打开手机支付宝，扫一扫完成支付</span>
                    </div>
                    <div style="margin-left: 50px;width: 200px;display: inline-block;vertical-align: top;">
                        <span>
                            <img src="${pageContext.request.contextPath}/static/images/ali-view.png" />
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="viewModal" tabindex="1" role="dialog" aria-labelledby="productModalLabel" data-backdrop="static">
    
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
<script src="${pageContext.request.contextPath}/static/js/qrcode.min.js" type="text/javascript"></script>

<script>
    $(function(){
        <c:if test="${not empty qrCode}">
            var qrcode = new QRCode(document.getElementById("qrcode"), {
                width : 180,
                height : 180
            });
            qrcode.makeCode('${qrCode}');
        </c:if>
        <c:if test="${empty qrCode}">
            swal('获取支付码失败，请刷新页面！');
        </c:if>
        intervalId = setInterval("aliPayStatus()",5000);
    });


    function aliPayStatus() {
        $.ajax({
            url: "${ctx}/designTaskOrder/payStatus",
            type: 'POST',
            dataType: 'json',
            data: {"designTaskOrderId" : ${designTaskOrderCustom.id}},
            success: function(result){
                if (result.success) {
                    if(result.flag) {
                        clearInterval(intervalId);
                        swal({
                            title: "支付成功",
                            text: "2秒后自动跳转至设计任务页面",
                            type: "success",
                            timer: 2000,
                            showConfirmButton: true,
                            confirmButtonText: "立即跳转"
                        },
                        function(){
                            swal.close();
                            getContent('${ctx}/designTaskOrder/designTaskOrderManager');
                        });
                    }
                }
            },
            error: function() {
                swal('页面不存在');
            }
        });
    }

    function viewTriangle(flag) {
        if(flag) {
            $(".triangle-bottom").hide();
            $(".triangle-top").show();
        }else {
            $(".triangle-bottom").show();
            $(".triangle-top").hide();
        }
    }

    function problemGuideline() {
        $.ajax({
            url: "${ctx}/designTaskOrder/problemGuidelineManager",
            type: "GET",
            success: function(data){
                $("#viewModal").html(data);
                $("#viewModal").modal();
            },
            error: function() {
                swal('页面不存在');
            }
        });
    }

</script>
</body>
</html>