<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>侵权维权</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/imageUploader.css"/>

    <style type="text/css">
        .td-pictures li {
            display: inline-block;
        }

        .td-pictures li img {
            width: 40px;
            height: 40px;
        }

        .td_title {
            font-weight: normal;
            background-color: #cccccc;
            height: 22px;
            border-bottom: 0 !important;
        }

        .text-right {
            width: 150px;
        }
    </style>
</head>

<body>
<div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 910px;">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">侵权维权</span>
        </div>
        <div class="modal-body">
            <input type="hidden" value="${detail.rightAppealCustom.id}" id="appealId">
            <input type="hidden" value="${detail.rightAppealCustom.complainId}" id="complainId">
            <div class="table-responsive">
                <h2>投诉信息</h2>
                <table class="table table-bordered ">
                    <tbody>
                    <tr>
                        <td class="editfield-label-td">投诉理由</td>
                        <td colspan="2" class="text-left">
                            <span>${detail.rightAppealCustom.appealReasonDesc}</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">投诉类型</td>
                        <td colspan="2" class="text-left">
                            <span>${detail.rightAppealCustom.appealTypeDesc}</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">相关商品ID/店铺名称</td>
                        <td colspan="2" class="text-left">
                            <span>${detail.rightAppealCustom.relevantValue}</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">理由说明</td>
                        <td colspan="2" class="text-left">
                            <span>${detail.rightAppealCustom.reasonDesc}</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">举证材料</td>
                        <td colspan="2" class="text-left">
                            <div class="pic-container">
                                <ul class="docs-pictures clearfix td-pictures pictures-list">
                                    <c:forEach var="appealPic" items="${detail.appealPicList}">
                                        <li class="pic_li">
                                            <img src="${ctx}/file_servelt${appealPic.pic}">
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
            <c:if test="${detail.showFlag != null && detail.showFlag != 0}">
                <div class="table-responsive">
                    <h2>申诉信息</h2>
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">申诉理由</td>
                            <td colspan="2" class="text-left">
                                <c:if test="${detail.showFlag != null && detail.showFlag == 1}">
                                <textarea class="form-control" rows="3"
                                          id="complainReason">${detail.rightAppealCustom.complainReason}</textarea>
                                </c:if>
                                <c:if test="${detail.showFlag != null && detail.showFlag == 2}">
                                    ${detail.rightAppealCustom.complainReason}
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${detail.showFlag != null && detail.showFlag != 1}">
                            <tr>
                                <td class="editfield-label-td">申诉材料</td>
                                <td colspan="2" class="text-left">
                                    <div class="pic-container">
                                        <ul class="docs-pictures clearfix td-pictures pictures-list">
                                            <c:forEach var="complainPic" items="${detail.complainPicList}">
                                                <li class="pic_li">
                                                    <img src="${ctx}/file_servelt${complainPic.pic}">
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${detail.showFlag != null && detail.showFlag == 1}">
                            <tr>
                                <td class="editfield-label-td">申诉材料</td>
                                <td colspan="2" class="text-left">
                                    <div id="main-pic-container">
                                        <ul class="docs-pictures clearfix td-pictures" id="main-pictures-list"
                                            ondrop="drop(event);" ondragover="allowDrop(event)">
                                            <c:forEach var="complainPic" items="${detail.complainPicList}"
                                                       varStatus="varStatus">
                                                <li id="productPic_li_${varStatus.index }" class="pic_li pic_li_for_up"
                                                    draggable="true"
                                                    ondragstart="drag(event);" pic_path="${complainPic.pic}">
                                                    <img src="${ctx}/file_servelt${complainPic.pic}">
                                                    <div class="pic-btn-container" style="height: 0px;">
                                                <span class="glyphicon glyphicon-remove pic-remove-icon"
                                                      aria-hidden="true"></span>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                        <div onclick="$('#mainPicErrorMsg').text('');" id="mainFilePicker"
                                             class="filePicker">
                                            上传材料
                                        </div>
                                        <span id="mainPicErrorMsg"
                                              style="vertical-align: top;margin-left: 10px;color: red;"></span>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${detail.showFlag != null && detail.showFlag == 1}">
                            <tr>
                                <td class="editfield-label-td">操作</td>
                                <td>
                                    <button class="btn btn-info" onclick="submit();">提交</button>
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script type="text/javascript">
    var uploader;
    var picUrlArr = [];
    $(function () {
        <c:if test="${detail.showFlag != null && detail.showFlag == 1}">
        uploader = WebUploader.create({

            dnd: '#main-pic-container',
            paste: 'document.body',
            // swf文件路径
            swf: '${ctx}/static/images/webuploader/Uploader.swf',

            // 文件接收服务端。
            server: '${ctx}/common/fileUpload?fileType=2',

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#mainFilePicker',
            duplicate: true,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'jpg,jpeg',
                mimeTypes: 'image/jpg,image/jpeg'
            }
        });

        uploader.on('beforeFileQueued', function (file) {
            var pickerId = file.source._refer.context.id;


            if (file.type != 'image/jpeg') {
                if (pickerId == 'mainFilePicker') {//主图的图片列表
                    $("#mainPicErrorMsg").text("请上传jpg，jpeg格式的图片");
                    return false;
                }
            }

            if (pickerId == 'mainFilePicker' && file.size > 5 * 1024 * 1024) {//主图的图片列表
                $("#mainPicErrorMsg").text("您选择了包含大于5M的图片！");
                return false;
            }


        });

        uploader.onFileQueued = function (file) {
            var pickerId = file.source._refer.context.id;
            if (pickerId == 'mainFilePicker') {

                var oFReader = new FileReader();
                oFReader.onload = function (oFREvent) {

                    var img = new Image();

                    img.onload = function () {

                        if ($("#main-pictures-list").children("li").length > 10) {
                            $("#mainPicErrorMsg").text("申诉材料最多只能10张");
                            uploader.removeFile(file, true);
                            return;
                        }

                        // if(img.width!=800||img.height!=800){
                        //     uploader.removeFile( file,true );
                        //     $("#mainPicErrorMsg").text("您选择了包含尺寸不为800*800的图片！");
                        //     return;
                        // }else{
                        var $li = $('<li class="pic_li pic_li_for_up" id="pic_li_' + file.id + '" draggable="true" ondragstart="drag(event)"><img id="pic_' + file.id + '" src="' + oFREvent.target.result + '"></li>');
                        var $btns = $('<div class="pic-btn-container"></div>').appendTo($li);
                        var $removeBtn = $('<span  class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>').appendTo($btns);
                        $('#main-pictures-list').append($li);
//                              productPicturesViewer.destroy();
//                              productPicturesViewer = new Viewer(document.getElementById('main-pictures-list'), {

//                              });

                        $li.on('mouseenter', function () {
                            $btns.stop().animate({height: 20});
                        });

                        $li.on('mouseleave', function () {
                            $btns.stop().animate({height: 0});
                        });

                        $removeBtn.on('click', function () {
                                $li.remove();
                                uploader.removeFile(file, true);
                            }
                        );
                    };
                    img.src = oFREvent.target.result
                };

                oFReader.readAsDataURL(file.source.source);
            }
        };

        uploader.on('uploadSuccess', function (file, response) {
            if (response.returnCode == '0000') {
                $("#pic_li_" + file.id).attr("pic_path", response.returnData);
            } else {
                uploader.removeFile(file, true);
                $("#pic_li_" + file.id).remove();
            }
        });

        uploader.on('uploadError', function (file, response) {
            uploader.removeFile(file, true);
            $("#pic_li_" + file.id).remove();
            console.log("uploadError-------" + file.name);
            console.log(response);
        });

        uploader.on('error', function (type) {
            console.log("error-------" + type);
        });

        //当所有文件上传结束时触发
        uploader.on("uploadFinished", function () {
            console.log("所有文件上传结束--------");
            uploadImage();
        });
        </c:if>
    });

    // 上传图片后触发事件
    function uploadImage() {
        $(".pic_li_for_up").each(function (index, element) {
            var picUrl = $(element).attr('pic_path');
            picUrlArr.push(picUrl);
        });

        var complainId = $("#complainId").val();
        var complainReason = $("#complainReason").val();
        var appealId = $("#appealId").val();

        $.ajax({
            url: "${ctx}/propertyRightAppeal/saveComplain",
            type: 'POST',
            dataType: 'json',
            cache: false,
            async: false,
            data: {
                id: appealId,
                complainId: complainId,
                complainReason: complainReason,
                picUrls: picUrlArr.toString()
            },
            timeout: 30000,
            success: function (data) {
                if (data.returnCode === "0000") {
                    swal({
                        title: "保存成功！",
                        type: "success",
                        confirmButtonText: "确定"
                    }, function (e) {
                        $('#viewDiv').on('hidden.bs.modal');
                        $("#viewDiv").modal('hide');
                        parent.$("#btn-query").click();
                    });
                } else {
                    swal({
                        title: "保存失败！",
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
        })
    }

    // 点击提交
    function submit() {
        if ($("#main-pictures-list").children("li").length > 10) {
            $("#mainPicErrorMsg").text("申诉材料最多只能10张");
            return;
        }
        // 触发上传图片
        uploader.upload();
    }
</script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/imageUpload.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
</body>
</html>
