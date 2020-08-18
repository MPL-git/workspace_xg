<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>店铺LOGO</title>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>
    <style type="text/css">
        .single_pic_picker.x1 {
            width: 400px;
            height: 400px;
            margin: 0 auto;
        }

        .single_pic_picker img {
            position: absolute;
            top: 0;
            /*left: calc(50% - 375px);*/
        }

        .imgs-span {
            display: block;
            margin: auto;
            font-size: 19px;
            color: #999;
            text-align: center;
        }

        #main-pic-container {
            position: relative;
        }

        .addClick {
            position: absolute;
            left: calc(50% - 30px);
            top: calc(50% - 30px);
            font-size: 60px;
            color: #ddd;
        }

        .single_pic_picker input {
            position: absolute;
            z-index: 55;
            width: 100%;
            height: 100%;
            opacity: 0;
        }

        #changeshopLogoFile {
            position: absolute;
            left: 0;
        }

        .btn-baocun {
            position: absolute;
            left: calc(50% - 40px);
            width: 60px;
        }

    </style>
</head>
<body>
<div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">店铺LOGO</span>
        </div>
        <div class="modal-body">
            <div id="main-pic-container">
                <input type="hidden" id="decoratePageId" value="${decoratePage.id}"/>
                <div class="single_pic_picker x1">
                    <input type="hidden" name="shopLogo" id="shopLogoImg" value="${decoratePage.shopLogo}"/>
                    <input id="changeshopLogoFile" onchange="loadImageFile(this)" type="file">
                    <c:if test='${decoratePage.shopLogo != null && decoratePage.shopLogo != ""}'>
                        <img src="${ctx}/file_servelt${decoratePage.shopLogo}" id="preview_header">
                    </c:if>
                    <c:if test='${decoratePage.shopLogo==null||decoratePage.shopLogo==""}'>
                        <div class="addClick">+</div>
                    </c:if>
                </div>
                <span class="imgs-span">图片尺寸宽400*400px，图片大小为100KB以内</span>
            </div>

            <div class="modal-footer">
                <button class="btn btn-info btn-baocun" onclick="commitSave();">保存</button>
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
    // 上传临时文件
    var upFile;
    // 点击上传
    function loadImageFile(obj) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        if (oFile.size > 100 * 1024) {
            swal("图片大小不能超过100K");
            return;
        }
        var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
        if (!rFilter.test(oFile.type)) {
            swal("请选择图片文件");
            return;
        }
        if ($(obj).parent().children("img").length <= 0) {
            $('<img>').appendTo($(obj).parent());
        }

        var oFReader = new FileReader();
        oFReader.onload = function (oFREvent) {
            var img = new Image();
            img.src = oFREvent.target.result;
            img.onload = function () {
                if (img.width != 400 || img.height != 400) {
                    swal("请选择尺寸为400*400的图片");
                    return false;
                } else {
                    $(obj).parent().children("img").attr("src", oFREvent.target.result);
                    $(obj).parent().children("div").remove();
                    upFile = obj.files[0];
                }
            }

        };
        oFReader.readAsDataURL(oFile);
    }

    // 上传图片
    function uploadImg($img, $valueFiled) {
        var formData = new FormData();
        formData.append("file", upFile);
        $.ajax({
            url: "${ctx}/common/fileUpload?fileType=10",
            type: 'POST',
            data: formData,
            async: false,
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            beforeSend: function () {
                console.log("图片片上传中，请稍候");
            },
            success: function (data) {
                if (data.returnCode === "0000") {
                    $valueFiled.val(data.returnData);
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

    // 保存
    function commitSave() {
        var entryPic = $("#shopLogoImg");
        // var oFile = document.getElementById("changeshopLogoFile").files[0];
        // 当没有文件改变时则不需要上传
        if (upFile) {
            uploadImg("changeshopLogoFile", entryPic);
        }

        var decoratePageId = $("#decoratePageId").val();
        if (!decoratePageId) {
            swal("请求数据错误，请刷新页面");
            return false;
        }
        var shopLogoPic = $("#shopLogoImg").val();
        if (!shopLogoPic) {
            swal("请上传图片");
            return false;
        }
        var data = {
            'shopLogo': shopLogoPic,
            'id': decoratePageId
        };
        var submitFlag = true;
        if (!submitFlag) {
            swal("请不要重复提交");
            submitFlag = false;
            return false;
        }

        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/saveShopLogo",
            type: 'POST',
            dataType: 'json',
            data: data,
            timeout: 30000,
            success: function (data) {
                if (data.returnCode === "0000") {
                    // 更改装修页面的logo
                    $("img[name='shopLogoImg']").attr('src', '${ctx}/file_servelt' + shopLogoPic);
                    swal({
                        title: "保存成功！",
                        type: "success",
                        confirmButtonText: "确定"
                    }, function (e) {
                        $('#viewModal').on('hidden.bs.modal');
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
</script>
</body>
</html>
