<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>编辑模板</title>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>

    <style type="text/css">
        html {
            font-size: 100px;
        }

        @media (min-width: 750px) {
            html {
                width: 750px;
                margin: 0 auto;
            }
        }

        body {
            margin: 0;
            font-size: .24rem;
            text-rendering: optimizeLegibility;
            -webkit-font-smoothing: antialiased;
            -webkit-tap-highlight-color: transparent;
        }

        .flex {
            display: -webkit-flex;
            display: flex;
            box-sizing: border-box;
        }

        .jc {
            -webkit-justify-content: center;
            justify-content: center;
        }

        .jb {
            -webkit-justify-content: space-between;
            justify-content: space-between;
        }

        .je {
            -webkit-justify-content: flex-end;
            justify-content: flex-end;
        }

        .ac {
            -webkit-align-items: center;
            align-items: center;
        }

        .box-header {
            height: .6rem;
        }

        .box-btn {
            width: .8rem;
            height: .4rem;
            font-size: inherit;
            border-radius: 0;
            background: #f05050;
            color: #fff;
            border: none;
        }

        .box-file {
            overflow: hidden;
            position: relative;
        }

        .box-file input[type=file] {
            position: absolute;
            top: 0;
            z-index: 1;
            left: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
            cursor: default;
        }

        .box-con {
            position: relative;
            width: 7.5rem;
            height: 4rem;
            margin: auto;
            overflow-x: hidden;
        }

        .box-svg {
            overflow: hidden;
            position: absolute;
            top: 0;
            left: 0;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;

            cursor: crosshair;
        }

        .box-svg img {
            display: block;
            pointer-events: none;
        }

        .box-frame {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 1;
            width: 0;
            height: 0;
            background: rgba(0, 0, 0, .5);
            border: 1px dashed #000;
            cursor: move;
        }

        .box-frame div {
            position: absolute;
            top: 0;
            left: -1px;
            z-index: 1;
            width: calc(100% + 2px);
            height: .2rem;
        }

        .mask-close a,
        .box-frame .box-delete,
        .box-frame .box-popup {
            float: right;
            position: relative;
            width: .2rem;
            height: .2rem;
            border-radius: .1rem;
            background: #fff;
            text-align: center;
            font-size: .14rem;
            font-weight: bold;
            box-sizing: border-box;
            box-shadow: 0 0 .06rem #000;
            cursor: pointer;
            opacity: 0;
            pointer-events: none;

            -webkit-transition: .3s ease-out;
            transition: .3s ease-out;
        }

        .box-frame:hover .box-delete,
        .box-frame:hover .box-popup {
            opacity: 1;
            pointer-events: auto;
        }

        .box-frame .box-delete:active,
        .box-frame .box-popup:active {
            background: #ccc;
        }

        .box-frame .box-delete:after,
        .box-frame .box-popup:after {
            content: '!';
        }

        .box-frame .box-delete {
            margin: 0 .1rem;
        }

        .mask-close a:before,
        .mask-close a:after,
        .box-frame .box-delete:before,
        .box-frame .box-delete:after {
            position: absolute;
            top: 50%;
            left: 50%;
            content: '';
            width: .14rem;
            height: .02rem;
            margin: -.01rem 0 0 -.07rem;
            background: #000;
            border-radius: .01rem;
            -webkit-transform: rotate(45deg);
            transform: rotate(45deg);
        }

        .mask-close a:after,
        .box-frame .box-delete:after {
            -webkit-transform: rotate(-45deg);
            transform: rotate(-45deg);
        }

        .box-mask {
            position: fixed;
            top: 0;
            left: 0;
            z-index: 11;
            width: 1000px;
            height: 100vh;
            background: rgba(0, 0, 0, .5);
            opacity: 0;
            pointer-events: none;

            -webkit-transition: .3s ease-out;
            transition: .3s ease-out;
        }

        .box-mask.show {
            opacity: 1;
            pointer-events: auto;
        }

        #test_load p {
            position: absolute;
            top: 50%;
            left: 0;
            width: 100%;
            text-align: center;
            color: #fff;
        }

        .pop-con h3 {
            margin: .2rem 0 0;
            text-align: center;
        }

        label{
            display: inline-block;
            max-width: 100%;
            width: 300px;
            font-weight: 700;
            text-align: right;
        }

        .pop-input {
            height: .6rem;
            padding: .2rem .2rem 0;
            clear: both;
        }

        .pop-input label {
            width: 1.1rem;
            margin-right: .1rem;
        }

        .pop-input input {
            height: .24rem;
            padding-left: .1rem;
        }

        .box-article p {
            margin: .2rem 0 0;
        }

        .p-index {
            position: relative;
            padding-left: 1.36rem;
            word-break: break-all;
        }

        .p-index:before {
            position: absolute;
            top: 0;
            left: 0;
            width: 1.34rem;
            content: attr(data-t);
        }

        .modal-content {
            position: absolute;
            z-index: 55;

        }

        .box-dog {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 2;
            width: .1rem;
            height: .1rem;
        }

        .box-dog:nth-child(2) {
            left: auto;
            right: 0;

            -webkit-transform: rotate(90deg);
            transform: rotate(90deg);
        }

        .box-dog:nth-child(3) {
            left: auto;
            right: 0;
            top: auto;
            bottom: 0;

            -webkit-transform: rotate(180deg);
            transform: rotate(180deg);
        }

        .box-dog:nth-child(4) {
            top: auto;
            bottom: 0;

            -webkit-transform: rotate(-90deg);
            transform: rotate(-90deg);
        }

        .box-dog:after,
        .box-dog:before {
            position: absolute;
            top: 0;
            left: 0;
            content: '';
            width: .04rem;
            height: .1rem;
            background: rgba(255, 255, 255, .6);
        }

        .box-dog:after {
            width: .1rem;
            height: .04rem;
        }

        .box-dog[data-dog="0"] {
            cursor: nw-resize;
        }

        .box-dog[data-dog="1"] {
            cursor: ne-resize;
        }

        .box-dog[data-dog="2"] {
            cursor: se-resize;
        }

        .box-dog[data-dog="3"] {
            cursor: sw-resize;
        }

        .mask-close a {
            margin: .1rem .2rem 0 0;
            opacity: 1;
            pointer-events: auto;
        }

        .imgs-span {
            text-align: center;
        }

        .textSpan {
            display: inline-block;
            margin-top: 10px;
            margin-bottom: 100px;
        }

        .Scbtn {
            width: 80px;
            height: 35px;
            line-height: 35px;
            text-align: center;
            font-size: 18px;
        }
        .pop-con {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 5rem;
            height: 2.6rem;
            margin: -1rem 0 0 -2.5rem;
            background: #fff;
        }
    </style>
</head>
<body>
<div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">编辑模板</span>
        </div>
        <div class="modal-body">
            <div id="main-pic-container">
                <form method="post" id="test_box" name="form">
                    <input type="hidden" id="moduleId" name="moduleId" value="${moduleData.moduleId}"/>
                    <input type="hidden" id="shopDecorateAreaId" name="shopDecorateAreaId"
                           value="${shopDecorateAreaId}"/>
                    <input id="pic" name="pic" type="hidden" value="${moduleData.modulePic}">
                    <div>
                        <div style="float: left;height: 30px;">
                            <input type="file" id="picFile" name="file" onchange="loadImageFile(this);"
                                   style="position:absolute; opacity:0;width: 80px;height: 30px;cursor: pointer;">
                            <button class="btn-info Scbtn" type="button">上传图片</button>
                        </div>
                        <input type="button" class="btn-info Scbtn"
                               style="float:right;width: 80px;height: 35px;cursor: pointer;" value="保存"
                               onclick="commitSave()">
                        <br>
                        <br>
                        <div class="box-con">
                            <div class="box-svg">
                                <img src="${ctx}/file_servelt${moduleData.modulePic}"
                                     style="width: 750px; <c:if test="${empty moduleData.modulePic}">display: none;</c:if>"
                                     id="modulePic">
                                <c:forEach items="${moduleData.moduleMapJSONArray}" var="moduleMapJsonObject">
                                    <div class="box-frame" data-x1="${moduleMapJsonObject.x1}"
                                         data-y1="${moduleMapJsonObject.y1}" data-x2="${moduleMapJsonObject.x2}"
                                         data-y2="${moduleMapJsonObject.y2}"
                                         data-linktype="${moduleMapJsonObject.linkType}"
                                         data-linkvalue="${moduleMapJsonObject.linkValue}"
                                         style="left: ${moduleMapJsonObject.x1}%; top: ${moduleMapJsonObject.y1}%; width: ${moduleMapJsonObject.width}%; height: ${moduleMapJsonObject.height}%;">
                                        <a class="box-dog" data-dog="0"></a>
                                        <a class="box-dog" data-dog="1"></a>
                                        <a class="box-dog" data-dog="2"></a>
                                        <a class="box-dog" data-dog="3"></a>
                                        <div><a class="box-delete"></a><a class="box-popup"></a></div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                    </div>
                </form>
                <span class="imgs-span textSpan">
                    自定义图片，可以加超链接。尺寸：宽为750像素，高度上限1500，大小：800以内
                </span>
                <br>
                <span style="color: red" class="imgs-span">备注：图片按住鼠标可拖拉区域设置链接</span>
            </div>
            <div class="box-mask" id="boxMask">
                <div class="pop-con" style="display: none" id="popDialog">
                    <h3>设置链接</h3>
                    <div class="mask-close" style="margin-top:-42px;"><a onclick="closeMapInfo()"></a></div>
                    <div class="pop-input flex ac">
                        <label>链接类型: </label>
                        <select name="linkType" id="linkType" onchange="setLinkValueHtml(this)">
                            <option value="">请选择</option>
                            <option value="1">单品详情</option>
                            <option value="2">商品关键字</option>
                            <option value="3">店铺链接</option>
                        </select>
                    </div>
                    <div class="pop-input flex ac">
                        <label id="popTitle">详情: </label>
                        <input id="linkValue" name="linkValue" type="text" style="height:35px;" value="" placeholder="">
                    </div>

                    <div class="pop-input flex ac jc">
                        <button class="box-btn flex jc ac" style="cursor:pointer;" onclick="saveMapInfo(this)">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<div class="modal fade" id="viewModal1" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel1"
     data-backdrop="static">
</div>

<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript">


    var box_obj = {
            x1: 0, // 距左%
            y1: 0, // 距上%
            x2: 0,
            y2: 0,
            src: '',
            href: '',
            title: ''
        },
        box_min = 40,

        box_img = '',
        box_frame = '',
        box_dbframe = '',
        box_dog = '',

        box_down = false,
        box_move = false,
        box_edit = false,
        box_data = false,
        box_dont = false,
        box_drag = false,

        box_dx = 0,
        box_dy = 0,

        box_body = document.body,
        box_con = test_box.querySelector('.box-con'),
        box_svg = test_box.querySelector('.box-svg'),
        box_file = test_box.querySelector('input[type=file]'),

        box_bw = getStyle(box_con, 'width'),
        box_bh = getStyle(box_con, 'height');

    var winSession = window.sessionStorage,
        box_header = test_box.querySelector('.box-header'),
        box_article = box_body.querySelector('.box-article'),
        box_objs = winSession.getItem('box_objs');
    <c:if test="${not empty moduleData.modulePic}">
    box_img = $("#modulePic")[0];
    box_down = true;
    </c:if>

    function getStyle(elem, style) {
        return parseInt(elem.currentStyle ? elem.currentStyle[style] : getComputedStyle(elem)[style]);
    }

    function closest(e, s) {
        var matchesSelector = e.matches || e.webkitMatchesSelector || e.mozMatchesSelector || e.msMatchesSelector;

        while (e) {
            if (matchesSelector.call(e, s)) {
                break;
            }
            e = e.parentElement;
        }
        return e;
    }

    //图片格式验证
    function loadImageFile(obj) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        if (oFile.size > 800 * 1024) {
            swal("图片大小不能超过800K");
            return;
        }
        if (!/image\/(PNG|png|JPG|jpg|JPEG|jpeg)$/.test(oFile.type)) {
            swal("请选择图片文件");
            return;
        }
        if ($(obj).parent().children("img").length <= 0) {
            $('<img>').appendTo($(obj).parent());
        }

        var reader = new FileReader();
        reader.onload = function (e) {
            var image = new Image();
            image.src = e.target.result;
            image.onload = function () {
                if (image.width != 750 || image.height > 1500) {
                    swal("请选择尺寸宽为750像素，高度上限1500像素的图片");
                    return false;
                } else {
                    ajaxFileUpload(oFile);
                }
            }
        };
        reader.readAsDataURL(oFile);
    }

    function ajaxFileUpload(file) {
        var formData = new FormData();
        formData.append("file", file);
        $.ajax({
            url: "${ctx}/common/fileUpload?fileType=10",
            type: 'POST',
            data: formData,
            // 同步
            async: false,
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            beforeSend: function () {
                console.log("图片上传中，请稍候");
            },
            success: function (data) {
                if (data.returnCode === "0000") {
                    $("#modulePic").css("display", "block");
                    $(".box-svg").find("img").attr("src", "${ctx}/file_servelt" + data.returnData);
                    <c:if test="${not empty moduleMapJSONArray}">
                    var moduleMapJSONArray = ${moduleMapJSONArray};
                    var moduleMapDivHtml = [];
                    for (var i = 0; i < moduleMapJSONArray.length; i++) {
                        var moduleMapJsonObject = moduleMapJSONArray[i];
                        var tmpStr = '<div class="box-frame" data-x1="' + moduleMapJsonObject.x1 + '" data-y1="' + moduleMapJsonObject.y1 + '" data-x2="' + moduleMapJsonObject.x2 + '" data-y2="' + moduleMapJsonObject.y2 + '" data-linktype="' + moduleMapJsonObject.linkType + '" data-linkvalue="' + moduleMapJsonObject.linkValue + '" style="left: ' + moduleMapJsonObject.x1 + '%; top: ' + moduleMapJsonObject.y1 + '%; width: ' + moduleMapJsonObject.width + '%; height: ' + moduleMapJsonObject.height + '%;">' +
                            '<a class="box-dog" data-dog="0"></a>' +
                            '<a class="box-dog" data-dog="1"></a>' +
                            '<a class="box-dog" data-dog="2"></a>' +
                            '<a class="box-dog" data-dog="3"></a>' +
                            '<div><a class="box-delete"></a><a class="box-popup"></a></div>' +
                            '</div>';
                        moduleMapDivHtml.push(tmpStr);
                    }
                    $(".box-svg").find(".box-frame").each(function () {
                        $(this).remove();
                    });
                    $(".box-svg").append(moduleMapDivHtml.join(""));
                    </c:if>
                    $("#modulePic").load(function () {
                        box_img = $("#modulePic")[0];
                        box_down = true;
                    });
                    $("#pic").val(data.returnData);
                } else {
                    swal({
                        title: "图片上传失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            },
            error: function (responseStr) {
                swal("图片上传失败");
            }
        });
    }

    // 鼠标按下
    box_svg.onmousedown = function (e) {
        if (box_down) {
            if (e.target.className == 'box-dog') {
                box_dx = e.x;
                box_dy = e.y;

                box_dog = e.target;
                box_frame = closest(box_dog, '.box-frame');

                box_edit = true;
            } else if (e.target.className == 'box-svg') {
                box_obj.x1 = 100 * (e.x - box_svg.getBoundingClientRect().left) / box_img.width;
                box_obj.y1 = 100 * (e.y - box_svg.getBoundingClientRect().top) / box_img.height;

                box_frame = document.createElement('div');
                box_frame.className = 'box-frame';
                box_svg.appendChild(box_frame);

                box_move = true;
            } else if ((e.target.className == 'box-popup' || e.target.className == 'box-delete') && e.which == 1) {
                box_dont = true;
            } else {
                box_dx = e.x;
                box_dy = e.y;
                box_frame = e.target.className == 'box-frame' ? e.target : closest(e.target, '.box-frame');

                box_drag = true;
            }
        }
    };

    // 鼠标移动
    box_svg.onmousemove = function (e) {
        if (box_down && box_frame) {
            if (box_move) {
                // 创建
                box_obj.x2 = 100 * (e.x - box_svg.getBoundingClientRect().left) / box_img.width;
                box_obj.y2 = 100 * (e.y - box_svg.getBoundingClientRect().top) / box_img.height;
            } else if (box_drag) {
                // 拖动
                var _x = 100 * (e.x - box_dx) / box_img.width,
                    _y = 100 * (e.y - box_dy) / box_img.height,
                    _x1 = parseInt(box_frame.getAttribute('data-x1')),
                    _y1 = parseInt(box_frame.getAttribute('data-y1')),
                    _x2 = parseInt(box_frame.getAttribute('data-x2')),
                    _y2 = parseInt(box_frame.getAttribute('data-y2'));

                box_obj.x1 = _x1 + _x;
                box_obj.x2 = _x2 + _x;
                box_obj.y1 = _y1 + _y;
                box_obj.y2 = _y2 + _y;

                box_obj.x1 < 0 && (box_obj.x1 = 0);
                box_obj.x2 < 0 && (box_obj.x2 = 0);
                box_obj.y1 < 0 && (box_obj.y1 = 0);
                box_obj.y2 < 0 && (box_obj.y2 = 0);

                box_obj.x1 > 100 && (box_obj.x1 = 100);
                box_obj.x2 > 100 && (box_obj.x2 = 100);
                box_obj.y1 > 100 && (box_obj.y1 = 100);
                box_obj.y2 > 100 && (box_obj.y2 = 100);
            } else if (box_edit) {
                // 编辑
                var _x = 100 * (e.x - box_dx) / box_img.width,
                    _y = 100 * (e.y - box_dy) / box_img.height,
                    _x1 = parseInt(box_frame.getAttribute('data-x1')),
                    _y1 = parseInt(box_frame.getAttribute('data-y1')),
                    _x2 = parseInt(box_frame.getAttribute('data-x2')),
                    _y2 = parseInt(box_frame.getAttribute('data-y2')),
                    _dog = parseInt(box_dog.getAttribute('data-dog'));

                if (_dog == 0) {
                    if (_x1 > _x2) {
                        box_obj.x1 = _x1;
                        box_obj.y1 = _y1;
                        box_obj.x2 = _x2 + _x;
                        box_obj.y2 = _y2 + _y;
                    } else {
                        box_obj.x1 = _x1 + _x;
                        box_obj.y1 = _y1 + _y;
                        box_obj.x2 = _x2;
                        box_obj.y2 = _y2;
                    }
                } else if (_dog == 1) {
                    if (_x1 > _x2) {
                        box_obj.x1 = _x1 + _x;
                        box_obj.y1 = _y1;
                        box_obj.x2 = _x2;
                        box_obj.y2 = _y2 + _y;
                    } else {
                        box_obj.x1 = _x1;
                        box_obj.y1 = _y1 + _y;
                        box_obj.x2 = _x2 + _x;
                        box_obj.y2 = _y2;
                    }
                } else if (_dog == 2) {
                    if (_x1 > _x2) {
                        box_obj.x1 = _x1 + _x;
                        box_obj.y1 = _y1 + _y;
                        box_obj.x2 = _x2;
                        box_obj.y2 = _y2;
                    } else {
                        box_obj.x1 = _x1;
                        box_obj.y1 = _y1;
                        box_obj.x2 = _x2 + _x;
                        box_obj.y2 = _y2 + _y;
                    }
                } else if (_dog == 3) {
                    if (_x1 > _x2) {
                        box_obj.x1 = _x1;
                        box_obj.y1 = _y1 + _y;
                        box_obj.x2 = _x2 + _x;
                        box_obj.y2 = _y2;
                    } else {
                        box_obj.x1 = _x1 + _x;
                        box_obj.y1 = _y1;
                        box_obj.x2 = _x2;
                        box_obj.y2 = _y2 + _y;
                    }
                }

                box_obj.x1 < 0 && (box_obj.x1 = 0);
                box_obj.x2 < 0 && (box_obj.x2 = 0);
                box_obj.y1 < 0 && (box_obj.y1 = 0);
                box_obj.y2 < 0 && (box_obj.y2 = 0);

                box_obj.x1 > 100 && (box_obj.x1 = 100);
                box_obj.x2 > 100 && (box_obj.x2 = 100);
                box_obj.y1 > 100 && (box_obj.y1 = 100);
                box_obj.y2 > 100 && (box_obj.y2 = 100);
            }

            if (box_move || box_drag || box_edit) {
                box_frame.style.cssText = 'left: ' + Math.min(box_obj.x1, box_obj.x2) + '%; top: ' + Math.min(box_obj.y1, box_obj.y2) + '%; width: ' + Math.abs(box_obj.x2 - box_obj.x1) + '%; height: ' + Math.abs(box_obj.y2 - box_obj.y1) + '%;';

                box_data = true;
            }
        }
    };

    // 鼠标松开
    document.onmouseup = function (e) {
        if (!box_data && e.target.className == 'box-popup' && box_dont) {
            box_dbframe = e.target.parentNode.parentNode;
            openMapInfo();
        } else if (!box_data && e.target.className == 'box-delete' && box_dont) {
            var _parents = e.target.parentNode.parentNode;
            _parents.parentNode.removeChild(_parents);
        } else if (box_down && (box_move || box_drag || box_edit)) {
            if (Math.min(getStyle(box_frame, 'width'), getStyle(box_frame, 'height')) < box_min) {
                box_frame.parentNode.removeChild(box_frame);
            } else if (box_data) {
                box_frame.setAttribute('data-x1', box_obj.x1);
                box_frame.setAttribute('data-y1', box_obj.y1);
                box_frame.setAttribute('data-x2', box_obj.x2);
                box_frame.setAttribute('data-y2', box_obj.y2);

                box_move && (box_frame.innerHTML = '<a class="box-dog" data-dog="0"></a><a class="box-dog" data-dog="1"></a><a class="box-dog" data-dog="2"></a><a class="box-dog" data-dog="3"></a><div><a class="box-delete"></a><a class="box-popup"></a></div>');
            }
        }

        box_move = false;
        box_drag = false;
        box_edit = false;
        box_data = false;
        box_dont = false;
        box_dog = '';
        box_frame = '';
    };

    // 禁止拖动
    document.ondragstart = function () {
        return false;
    };

    // 禁止右键弹出菜单
    box_svg.oncontextmenu = function () {
        return false;
    };

    // 打开map信息窗口
    function openMapInfo(_this) {
        if (_this) {
            box_dbframe = _this.parentNode.parentNode;
        }
        var linkType = box_dbframe.getAttribute('data-linkType') || '',
            linkValue = box_dbframe.getAttribute('data-linkValue') || '';
        $("#linkType").val(linkType);
        $("#linkValue").val(linkValue);
        if (!linkType) {
            $("#popTitle").html('详情:');
            $("#linkValue").attr('placeholder', '');
        }

        $("#boxMask").addClass('show');
        $("#popDialog").show();
    }

    // 关闭map信息窗口
    function closeMapInfo() {
        $("#boxMask").removeClass('show');
        $("#popDialog").hide();
    }

    // 保存map信息
    function saveMapInfo() {
        var linkType = $('#linkType option:selected').val();
        var linkValue = $('#linkValue').val();

        if (!linkType) {
            swal("请选择类型");
            return false;
        }
        if (!linkValue) {
            swal("详细不能为空");
            return false;
        }
        var submitFlag = true;
        if (!submitFlag) {
            swal("请不要重复提交");
            return false;
        }
        submitFlag = false;

        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/checkLink",
            type: 'POST',
            dataType: 'json',
            cache: false,
            async: false,
            data: {linkType: linkType, linkValue: linkValue},
            timeout: 30000,
            success: function (data) {
                if ("0000" === data.returnCode) {
                    // 保存相关设置 (对保存项[data-]修改时, 注意同时修改保存所有map信息中的相关项)
                    box_dbframe.setAttribute('data-linkType', linkType);
                    box_dbframe.setAttribute('data-linkValue', linkValue);
                    swal({
                        title: "提交成功！",
                        type: "success",
                        confirmButtonText: "确定"
                    }, function (e) {
                        closeMapInfo();
                    });
                } else {
                    swal(data.returnMsg);
                    return false;
                }
                submitFlag = true;
            },
            error: function () {
                swal('操作超时，请稍后再试！');
            }
        });
    }

    // 保存
    function commitSave() {
        var moduleId = $("#moduleId").val();
        var modulePic = $("#pic").val();
        var shopDecorateAreaId = $("#shopDecorateAreaId").val();
        if (!modulePic) {
            swal("请上传图片");
            return false;
        }
        var box_map = [],
            box_frames = box_svg.querySelectorAll('.box-frame');
        var linkTypeFlag = false;
        for (var i = 0; i < box_frames.length; i++) {
            var linkType = box_frames[i].getAttribute('data-linkType');
            var linkValue = box_frames[i].getAttribute('data-linkValue');
            if (!linkType) {
                linkTypeFlag = true;
                break;
            }
            box_map.push({
                x1: box_frames[i].getAttribute('data-x1'),
                y1: box_frames[i].getAttribute('data-y1'),
                x2: box_frames[i].getAttribute('data-x2'),
                y2: box_frames[i].getAttribute('data-y2'),
                linkType: box_frames[i].getAttribute('data-linkType'),
                linkValue: box_frames[i].getAttribute('data-linkValue')
            });
        }

        if (linkTypeFlag) {
            swal("请设置链接");
            return false;
        }

        var data = {
            'id': moduleId,
            'shopDecorateAreaId': shopDecorateAreaId,
            'pic': modulePic,
            'moduleMapJsonStr': JSON.stringify(box_map)
        };

        var submitFlag = true;
        if (!submitFlag) {
            swal("请不要重复提交");
            submitFlag = false;
            return false;
        }

        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/saveModuleDraft",
            type: 'POST',
            dataType: 'json',
            data: data,
            timeout: 30000,
            success: function (data) {
                if (data.returnCode === "0000") {
                    // 点击确认跳转至装修主页
                    swal({
                        title: "保存成功！",
                        type: "success",
                        confirmButtonText: "确定"
                    }, function (e) {
                        $('#viewModal').on('hidden.bs.modal', function (e) {
                            getContent("${ctx}/shopDecoratePageDraft/decorateIndex")
                        });
                        $("#viewModal").modal('hide');
                    });
                } else {
                    swal({
                        title: "保存失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
                submitFlag = true;
            },
            error: function () {
                swal({
                    title: "保存失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
        })
    }

    //下拉框选择
    function setLinkValueHtml(_this) {
        var linkType = $(_this).val();
        var _linkTitle;
        var _linkPlaceholder;
        if (linkType == "1") {
            _linkTitle = "单品详情:";
            _linkPlaceholder = "输入商品ID";
        } else if (linkType == "2") {
            _linkTitle = "关键字:";
            _linkPlaceholder = "输入关键字";
        } else if (linkType == "3") {
            _linkTitle = "店铺链接:";
            _linkPlaceholder = "输入商家序号";
        } else {
            _linkTitle = "详情:";
            _linkPlaceholder = "";
        }
        $("#popTitle").html(_linkTitle);
        $("#linkValue").val('');
        $("#linkValue").attr('placeholder', _linkPlaceholder);
    }

</script>
</body>
</html>
