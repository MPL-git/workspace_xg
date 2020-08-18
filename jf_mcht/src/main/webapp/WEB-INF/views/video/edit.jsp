<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>添加视频</title>
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
        <div class="col-md-12 t-title">编辑视频
            <a href="javascript:void(0);" onclick="backToVideoListPage()">返回</a>
        </div>
    </div>
    <div class="ad-form">
        <form id="dataFrom">
            <div class="table-responsive">
                <input type="hidden" id="videoId" value="${video.id}">
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <td class="editfield-label-td">视频标题<em class="ad-em">*</em></td>
                        <td colspan="2" class="text-left">
                            <input placeholder="最多可输入20个字" maxlength="20" class="ad-select" type="text" id="title"
                                   name="title" onkeyup="$('#titleLength').text(20-($('#title').val().length));"
                                   style="width: 400px;margin-right: 10px;" value="${video.title}"
                                   validate="{required:true,rangelength:[1,20]}">
                            <span>最多还可输入：</span><span style="color:red;margin: 0 3px;"
                                                      id="titleLength">20</span><span>个</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">视频封面<em class="ad-em">*</em></td>
                        <td colspan="2" class="text-left">
                            <div id="videoCoverContainer">
                                <ul class="docs-pictures clearfix td-pictures" id="coverList">
                                    <c:if test="${not empty video.videoCover}">
                                        <li id="productPic_li_1" class="pic_li is-defaul-pic" draggable="true"
                                            ondragstart="drag(event);" pic_path="${video.videoCover}">
                                            <c:if test="${fn:contains(video.videoCover,'http')==true}">
                                                <img src="${video.videoCover}">
                                            </c:if>
                                            <c:if test="${fn:contains(video.videoCover,'http')==false}">
                                                <img src="${ctx}/file_servelt${video.videoCover}">
                                            </c:if>
                                            <div class="pic-btn-container" style="height: 0;">
                                                <span class="glyphicon glyphicon-remove pic-remove-icon"
                                                      aria-hidden="true"></span>
                                            </div>
                                        </li>
                                    </c:if>
                                </ul>
                                <div style="display: inline-block;" onclick="$('#coverErrorMsg').text('');"
                                     id="coverPicker" class="filePicker">选择图片
                                </div>
                                <span id="coverErrorMsg"
                                      style="vertical-align: top;margin-left: 10px;color: red;"></span>
                                <div><span style="color:#999;">注意：图片尺寸800*800（格式jpg,gif），且大小不超过128K</span></div>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">视频描述<em class="ad-em">*</em></td>
                        <td colspan="2" class="text-left">
                            <textarea rows="5" class="txt-area" id="description" placeholder="最多可以输入100个字"
                                      maxlength="100"
                                      onkeyup="$('#descriptionLength').text(100-($('#description').val().length));">${video.description}</textarea>
                            <span>最多还可输入：</span><span style="color:red;margin: 0 3px;" id="descriptionLength">100</span><span>个</span>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">视频内容<em class="ad-em">*</em></td>
                        <td colspan="2" class="text-left">
                            <a href="javascript:;" id="videoA" class="file" style="line-height: 30px;">
                                选择视频
                                <input type="file" onchange="uploadDescVideo(this)">

                                <input type="hidden" id="descVideoUrl" name="descVideoUrl" value="${video.videoUrl}">
                                <input type="hidden" id="fullVideoUrl" name="fullVideoUrl" value="${fullVideoUrl}">
                                <input type="hidden" id="descVideoSize" name="descVideoSize" value="${video.videoSize}">
                                <input type="hidden" id="videoHeight" name="videoHeight" value="${video.videoHeight}">
                                <input type="hidden" id="videoWidth" name="videoWidth" value="${video.videoWidth}">
                                <input type="hidden" id="videoThumbnails" name="videoThumbnails"
                                       value="${video.videoThumbnails}">
                            </a>
                            <p style="color: gray;">视频上传要求：仅限MP4、flv、f4v、mov 格式的视频；视频建议180S内；平均码率0.6Mbps; 视频大小不超过20M ；
                                画面不能抖动、画质408P到720P；</p>
                            <div style="<c:if test="${empty videoFirstFrame}">display: none;</c:if>height: 150px;">
                                <ul id="imgBox">
                                    <li>
                                        <a href="javascript:;">
                                            <img src="<c:if test="${not empty videoFirstFrame}">${ctx}/file_servelt${videoFirstFrame}</c:if>"
                                                 style="width: 200px;height: 133.33px;" id="videoCover"
                                                 onclick="toShowVideo();"/>

                                            <div class="pic-btn-container" style="height: 20px;margin-left: -3px;">
                                                <span class="glyphicon glyphicon-remove"
                                                      onclick="removeVideo();"></span>
                                            </div>
                                            <div class="toolbar">
                                                <img src="${ctx }/static/images/bf.png" border="0"
                                                     onclick="toShowVideo();">
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">相关商品<em class="ad-em">*</em></td>
                        <td colspan="2" class="text-left">
                            <a href="javascript:;" onclick="toSelectProduct()" class="file" style="line-height: 30px;">
                                选择商品
                            </a>
                            <div>
                                <table class="table table-striped table-bordered dataTable no-footer"
                                       role="grid" aria-describedby="datatable_info">
                                    <thead>
                                    <tr role="row">
                                        <th class="sorting_disabled" rowspan="1" colspan="1" style="width: 277px;">
                                            图片/名称/商品ID
                                        </th>
                                        <th class="sorting_disabled" rowspan="1" colspan="1" style="width: 111px;">
                                            品牌/货号
                                        </th>
                                        <th class="sorting_disabled hiddenCol" rowspan="1" colspan="1"
                                            style="width: 79px;">商城价
                                        </th>
                                        <th class="sorting_disabled" rowspan="1" colspan="1" style="width: 79px;">活动价
                                        </th>
                                        <th class="sorting_disabled" rowspan="1" colspan="1" style="width: 89px;">
                                            SVIP折扣
                                        </th>
                                        <th class="sorting_disabled" rowspan="1" colspan="1" style="width: 89px;">库存
                                        </th>
                                        <th class="sorting_disabled" rowspan="1" colspan="1" style="width: 124px;">状态
                                        </th>
                                        <th class="sorting_disabled" rowspan="1" colspan="1" style="width: 81px;">操作
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="selectedProductBody">
                                    <c:forEach items="${productList}" var="product">
                                        <tr role="row" class="odd">
                                            <td>
                                                <div class="td1">
                                                    <div class="width-60">
                                                        <c:if test="${fn:contains(product.pic,'http')==true}">
                                                            <img src="${product.pic}">
                                                        </c:if>
                                                        <c:if test="${fn:contains(product.pic,'http')==false}">
                                                            <img src="${ctx}/file_servelt${product.pic}">
                                                        </c:if>
                                                    </div>
                                                    <div class="width-226" style="width: calc(100% - 82px);"><p
                                                            class="h34">
                                                            ${product.name}</p>
                                                        <div><span
                                                                style="float: left; margin: 0;">ID：${product.code}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="td2">
                                                    <div style="word-wrap:break-word;">${product.productBrandName}<br>${product.artNo}
                                                    </div>
                                                </div>
                                            </td>
                                            <td class=" hiddenCol">
                                                <div class="td3">${product.mallPriceMin}<c:if
                                                        test="${product.mallPriceMin != product.mallPriceMax}">-${product.mallPriceMax}</c:if></div>
                                            </td>
                                            <td>
                                                <div class="td4">${product.salePriceMin}<c:if
                                                        test="${product.salePriceMin != product.salePriceMax}">-${product.salePriceMax}</c:if></div>
                                            </td>
                                            <td>
                                                <div class="td5">${(not empty product.svipDiscount)?"/":product.svipDiscount*10}</div>
                                            </td>
                                            <td>
                                                <div class="td6">${product.stock}</div>
                                            </td>
                                            <td>
                                                <div class="td7"><input type="hidden" class="rowProductId"
                                                                        value="${product.id}">${product.status=="0"?"下架":"上架"}
                                                </div>
                                            </td>
                                            <td>
                                                <div class="td8">
                                                    <a class="table-opr-btn red" href="javascript:void(0);"
                                                       onclick="deleteProduct(this,${product.id})">删除</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>

                                </table>
                            </div>
                        </td>
                    </tr>




                    <tr>
                        <td class="editfield-label-td">视频来源<em class="ad-em">*</em></td>
                        <td colspan="2" class="text-left">
                            <select class="ad-select"  onchange="changeSource()"   name="videoSource" id="videoSource" validate="{required:true}" style="">
                                <option value="">--请选择--</option>
                                <c:forEach var="videoSource" items="${videoSourceStatusList}">
                                    <option value="${videoSource.statusValue}"   <c:if test="${video.videoSource==videoSource.statusValue}">selected</c:if>   >${videoSource.statusDesc}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td class="editfield-label-td">授权附件<em id="identify" class="ad-em"></em></td>
                        <td colspan="2" class="text-left">


                            <div class="form-group">
                                <a href="javascript:;" class="file" style="line-height: 30px;">上传附件<input type="file" id="file" name="file" onchange="uploadFile(this)" ><input type="hidden" id="filePath"></a><span id="fileMes"></span>
                                <span style="color:#999;line-height: 30px;vertical-align: top;">如果视频中有出现人物肖像的需要上传一份肖像使用授权书</span>
                                <a href="http://p1.xgbuy.cc/download/声明书2019.10.21.docx "  ><span  style="line-height: 30px;vertical-align: top;" >肖像权声明书模板</span></a>
                            </div>
                            <!-- 	class="table table-bordered dataTable no-footer"  -->
                            <table   id="affix" role="grid" aria-describedby="datatable_info"  border="0px solid gray;" width="40%" height="30"  bordercolor="black">


                                <!--编辑视频时遍历来的上传附件  -->
                                <tbody id="updatementTbody">
                                <c:forEach var="videoAuthorizedAccessorie" items="${videoAuthorizedAccessorieList}">
                                    <tr id="">
                                        <td ><a href="javascript:;" style="color: red"  data-filename="${videoAuthorizedAccessorie.fileName}"  data-filepath="${videoAuthorizedAccessorie.filePath}" >${videoAuthorizedAccessorie.fileName}</a></td>
                                        <td class="w_h_100_36"><a href="javascript:;" style="color: red" >已上传</a></td>
                                        <td><a href="javascript:;" class="btnDelete" onclick="deleteUpdate(this)" style="color: red">[删除]</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>

                                <!--新增的肖像上传附件  -->
                                <tbody id="attachmentTbody">

                                </tbody>


                            </table>

                        </td>
                    </tr>

                    </tbody>
                </table>

                <div    style="text-align:center">
                    <input type="checkbox" id="agree" >我已知晓<a href="javascript:;" onclick="videoUploadAgreement();">《视频上传规范》</a> <br><br>

                    <button type="button" class="btn btn-info" onclick="saveVideo();">提交</button>
                </div>

            </div>
        </form>
    </div>
</div>
<!--视频播放弹窗-->
<div class="video_box" style="position:fixed; width:660px; height:440px; top:20%; left:35%; display: none;"
     id="descVideoDiv">
    <div class="modal-content">
        <div class="modal-header">
            <span class="modal-title">视频描述 </span>
            <a href="javascript:;" class="video_close" onclick="closeVideo();"><img
                    src="${ctx}/static/images/close.png"></a>
        </div>
        <div class="modal-body">
            <form>
                <video id="descVideoPre" preload width="600px" height="400px" hidden="true" controls="controls"></video>
            </form>
        </div>
    </div>
</div>
<!-- 选择商品弹窗 -->
<div class="modal fade" id="selectProductModal" tabindex="-1" role="dialog"
     aria-labelledby="selectProductModalLabel" data-backdrop="static">
</div>
<%--查看视频协议弹框--%>
<div class="modal fade" id="viewModal2" tabindex="1" role="dialog" aria-labelledby="productModalLabel"
     data-backdrop="static">
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
    var uploader;
    var selectedProductMap = new Map();

    $(function () {
        initRestInput();
        initImgBinding();
        initSelectedProductMap();

        $(".pic_li").each(function (index, element) {
            $(element).on('mouseenter', function () {
                $(element).children(".pic-btn-container").stop().animate({height: 20});
            });

            $(element).on('mouseleave', function () {
                $(element).children(".pic-btn-container").stop().animate({height: 0});
            });
        });

        $(".pic-remove-icon").each(function (index, element) {
            $(element).on('click', function () {
                $(element).parent().parent().remove();
            });

        });
    });

    function initSelectedProductMap() {
        var $trs = $("#selectedProductBody").find("tr");
        for (var i = 0; i < $trs.length; i++) {
            var $tr = $trs.eq(i);
            var productId = $tr.find(".rowProductId").val();
            var product = {};
            product.id = productId;
            product.selected = true;
            product.td1 = $tr.find(".td1").html();
            product.td2 = $tr.find(".td2").html();
            product.td3 = $tr.find(".td3").html();
            product.td4 = $tr.find(".td4").html();
            product.td5 = $tr.find(".td5").html();
            product.td6 = $tr.find(".td6").html();
            product.td7 = $tr.find(".td7").html();
            product.td8 = $tr.find(".td8").html();
            selectedProductMap.set(parseInt(productId), product);
        }
    }

    function initRestInput() {
        $("#titleLength").text(20 - $("#title").val().length);
        $("#descriptionLength").text(100 - $("#description").val().length);
    }

    //选择商品打开弹窗
    function toSelectProduct() {
        $.ajax({
            url: "${ctx}/video/toSelectProduct",
            type: "GET",
            success: function (data) {
                var $selectProductModal = $("#selectProductModal");
                $selectProductModal.html(data);
                $selectProductModal.modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    //图片上传相关绑定
    function initImgBinding() {
        uploader = WebUploader.create({
            dnd: '#videoCoverContainer',
            auto: 'true',
            paste: 'document.body',
            // swf文件路径
            swf: '${ctx}/static/images/webuploader/Uploader.swf',
            // 文件接收服务端。
            server: '${ctx}/common/fileUpload?fileType=12',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#coverPicker',
            duplicate: true,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'jpg,gif',
                mimeTypes: 'image/jpg,image/gif'
            }
        });

        uploader.on('beforeFileQueued', function (file) {
            var suffix = file.name.split(".")[0];
            if (suffix === '') {
                swal("图片文件名不能为空");
                return false;
            }

            if (file.type != 'image/jpeg'&&file.type != 'image/gif') {
                $("#coverErrorMsg").text("请上传jpg/gif格式的图片");
                return false;
            }

            if (file.size > 128 * 1024) {
                $("#coverErrorMsg").text("上传的图片不能大于128K");
                return false;
            }
        });

        uploader.onFileQueued = function (file) {
            var pickerId = file.source._refer.context.id;
            if (pickerId === 'coverPicker') {
                var oFReader = new FileReader();
                oFReader.onload = function (oFREvent) {
                    var img = new Image();
                    img.onload = function () {
                        if ($("#coverList").children("li").length >= 1) {
                            $("#coverErrorMsg").text("视频封面数量只能传1张");
                            uploader.removeFile(file, true);
                            return;
                        }
                        if (img.width !== 800 || img.height !== 800) {
                            uploader.removeFile(file, true);
                            $("#coverErrorMsg").text("视频封面不为800*800的图片！");
                            return;
                        } else {
                            var $li = $('<li class="pic_li" id="pic_li_' + file.id + '" draggable="true" ondragstart="drag(event)"><img id="pic_' + file.id + '" src="' + oFREvent.target.result + '"></li>');
                            var $btns = $('<div class="pic-btn-container"></div>').appendTo($li);
                            var $removeBtn = $('<span  class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>').appendTo($btns);
                            $('#coverList').append($li);

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

                        }
                    };
                    img.src = oFREvent.target.result
                };
                oFReader.readAsDataURL(file.source.source);
            }

        };

        uploader.on('uploadSuccess', function (file, response) {
            if (response.returnCode === '0000') {
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
            // console.log("所有文件上传结束--------");
        });
    }

    //视频提交
    function uploadDescVideo(obj) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        var rFilter = ["mp4", "MP4", "flv", "FLV", "f4v", "mov"];
        var suffix = oFile.name.substring(oFile.name.lastIndexOf(".") + 1);
        if ($.inArray(suffix, rFilter) === -1) {
            swal("文件格式不正确！");
            return;
        }
        if (oFile.size > 20 * 1024 * 1024) {
            swal("文件大小不能超过20M");
            return;
        }

        var url = URL.createObjectURL(oFile);
        $("#descVideoPre").prop("src", url);

        var size = oFile.size / 1024;
        $("#descVideoSize").val(size.toFixed(2));
        var reader = new FileReader();
        reader.onload = function (e) {
            var formData = new FormData();
            formData.append("file", oFile);
            formData.append("fileType", 12);
            formData.append("splitVideo", "1");
            $.ajax({
                url: "${ctx}/common/fileUpload",
                type: 'POST',
                data: formData,
                // async: false,
                // 告诉jQuery不要去处理发送的数据
                processData: false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType: false,
                beforeSend: function () {
                    show_waitMe();
                },
                complete: function () {

                },
                success: function (data) {
                    resetVideoFileInput(obj);
                    if (data.returnCode === "0000") {
                        $("#videoThumbnails").val(data.returnData.videoThumbnails);
                        $("#descVideoUrl").val(data.returnData.videoUrl);
                        $("#fullVideoUrl").val(data.returnData.fullVideoUrl);
                        $("#videoWidth").val(data.returnData.videoWidth);
                        $("#videoHeight").val(data.returnData.videoHeight);
                        if (hasText(data.returnData.firstFrame)) {
                            $("#videoCover").prop("src", "${ctx}/file_servelt" + data.returnData.firstFrame);
                            $("#imgBox").parent().show();
                        }
                    } else {
                        if (data.returnMsg) {
                            swal(data.returnMsg);
                        } else {
                            swalError("文件上传失败!")
                        }
                    }
                    hide_waitMe();
                },
                error: function (responseStr) {
                    swal("文件上传失败");
                    hide_waitMe();
                }
            });
        };
        reader.readAsDataURL(oFile);
    }

    //清空video file input ，便于可以再次上传video
    function resetVideoFileInput(obj) {
        $(obj).closest("a").append('<input type="file" onchange="uploadDescVideo(this)">');
        $(obj).remove();
    }

    function toShowVideo() {
        $("#descVideoDiv").show();
        var $descVideoPre = $("#descVideoPre");
        $descVideoPre.attr("hidden", false);
        $descVideoPre.prop("autoplay", true);
        $descVideoPre.prop("src", $("#fullVideoUrl").val());
        $descVideoPre.load();
        $("#productBox").show();
    }

    function closeVideo() {
        $("#descVideoDiv").hide();
        $("#descVideoPre").attr("hidden", true);
        $("#productBox").hide();
        document.getElementById("descVideoPre").pause(); //暂停
    }

    function removeVideo() {
        $("#imgBox").parent().hide();
        $("#descVideoUrl").val("");
        $("#descVideoSize").val("");
        $("#videoHeight").val("");
        $("#videoWidth").val("");
        $("#videoThumbnails").val("");
    }

    //移除商品
    function deleteProduct(obj, productId) {
        selectedProductMap.delete(productId);
        $(obj).closest("tr").remove();
    }

    //提交
    function saveVideo() {
        var data = getDataAndValidate();
        if (data == null) {
            return;
        }

        ajaxPost("${ctx}/video/mgr/save", data, function (result) {
            if (result.success) {
                swal({
                    title: "恭喜您，视频添加成功!",
                    type: "success",
                    confirmButtonText: "查看视频列表",
                    // cancelButtonText: "继续添加视频",
                    // showCancelButton: true
                }, function (isConfirm) {
                    if (isConfirm === true) {
                        getContent("${ctx}/video/mgr/index");
                    }
                });
            } else {
                swalError(result.returnMsg);
            }
        });
    }

    function getDataAndValidate() {
        var data = {};
        data.videoId = $("#videoId").val();
        data.title = $("#title").val();
        data.description = $("#description").val();
        data.videoCover = getVideoCover();
        data.videoUrl = $("#descVideoUrl").val();
        data.productIds = getProductIds();
        data.videoSource=$('#videoSource').val();
        if (data.title.length === 0) {
            swalError("请输入视频标题");
            return null;
        }
        if (data.title.length > 20) {
            swalError("视频标题不能超过20个字");
            return null;
        }
        if (data.videoCover.length === 0) {
            swalError("请上传视频封面");
            return null;
        }
        if (data.description.length === 0) {
            swalError("请输入视频描述");
            return null;
        }
        if (data.description.length > 100) {
            swalError("视频标题不能超过100个字");
            return null;
        }
        if (data.productIds.length === 0) {
            swalError("请选择相关商品");
            return null;
        }
        if (data.productIds.length > 6) {
            swalError("相关商品个数不能超过6个");
            return null;
        }
        if (data.videoUrl.length === 0) {
            swalError("请上传视频");
            return null;
        }

        if (data.videoSource.length === 0){
            swalError("请说明视频的来源");
            return null;
        }




        var filePaths=[];
        $("#affix a").each(function(){
            var affixPath={};
            affixPath=$(this).attr("data-filepath")
            if(affixPath!=null){
                filePaths.push(affixPath);
            }
        });

        var fileNames=[];
        $("#affix a").each(function(){
            var affixName={};
            affixName=$(this).attr("data-filename");
            if(affixName!=null){
                fileNames.push(affixName);
            }
        });



        var myFileList = [];
        for (var i=0;i<fileNames.length;i++){
            var myFile = {};
            myFile.fileName = fileNames[i];
            myFile.filePath = filePaths[i];
            myFileList.push(myFile);
           // console.log(myFile);
            data.videoAuthorizedAccessoriesList=myFileList;

        }


        if (data.videoSource==="1") {
            if (fileNames.length===0){
                swalError("自己拍摄需要上传一份肖像使用授权书");
                return null;
            }
        }

        var check = document.getElementById('agree');
        if (!check.checked) {
            swalError("必须”同意视频上传协议“才能提交");
            return null;
        }

        data.videoTime = $("#descVideoPre").prop("duration");
        data.videoSize = $("#descVideoSize").val();
        data.videoWidth = $("#videoWidth").val();
        data.videoHeight = $("#videoHeight").val();
        data.videoThumbnails = $("#videoThumbnails").val();
        return data;
    }

    function getVideoCover() {
        var $li = $("#coverList").find("li");
        if ($li.length > 0) {
            return $li.eq(0).attr("pic_path");
        }
        return "";
    }

    function getProductIds() {
        var ids = [];
        if (selectedProductMap.size !== 0) {
            selectedProductMap.forEach(function (value, key) {
                ids.push(key);
            });
        }
        return ids;
    }

    function backToVideoListPage() {
        getContent('${ctx}/video/mgr/index');
    }

    /* 视频上传协议*/
    function videoUploadAgreement(){
        var url = "${ctx}/video/videoUploadAgreement";
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                $("#viewModal2").html(data);
                $("#viewModal2").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }


    /*视频来源change*/
    function changeSource() {
        var videoSource = $("#videoSource").val();
        if (videoSource == "1"){
            $("#identify").text("*");
        }else {
            $("#identify").text(" ");
        }
    }


    var fileS=[];
    var deleteIds=[]
    var uploadSize=$("#filesSize").val()*1;
    function uploadFile(obj) {
        /* 	console.log("uploadSize"+uploadSize) */
        var totalSize=0*1+uploadSize*1;
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        var rFilter = ["jpg","bmp","png","gif","JPG","BMP","PNG","GIF","mp3","wav","wma","ogg","ape","acc","MP3","WAV","WMA","OGG","APE","ACC","wma","WMA","doc","docx","xls","xlsx","ppt","pptx","pdf","rar","zip","DOC","DOCX","XLS","XLSX","PPT","PPTX","PDF","RAR","ZIP"];
        var suffix = oFile.name.substring(oFile.name.lastIndexOf(".")+1);
        var fileName = oFile.name;
        var fileSize = oFile.size;
        if($.inArray(suffix,rFilter)==-1){
            swal("文件格式不正确！");
            return;
        }
        if(suffix=="MP4"||suffix=="mp4"||suffix=="avi"||suffix=="AVI"){
            if(oFile.size>100*1024*1024){
                swal("视频大小不能超过100M");
                return;
            }
        }

        /* var totalSize=$("#filesSize").val()*1; */

        for(var i=0;i<fileS.length;i++){
            totalSize += fileS[i].size*1;
        }

        if(Number(totalSize)+Number(oFile.size)>400*1024*1024){
            $("#file").attr('type','text'); //解决input文件上传不能传同一文件主要就是这两句操作
            $("#file").attr('type','file');
            swal("文件总大小不能超过400M");
            return;
        };
        fileS.push(oFile);

        $("#upFileDiv").show()
        var reader = new FileReader();
        reader.onload = function(e) {
            var formData = new FormData();
            formData.append("file",oFile);
            formData.append("fileType",5);
            $.ajax({
                url : "${ctx}/common/fileUpload",
                type : 'POST',
                data : formData,
                async: false,
                // 告诉jQuery不要去处理发送的数据
                processData : false,
                // 告诉jQuery不要去设置Content-Type请求头
                contentType : false,
                beforeSend:function(){
                    //alert("文件片上传中，请稍候");

                },
                success : function(data) {
                    if (data.returnCode=="0000") {
                        var successName="已上传";
                        var deleteName="[删除]";
                        var filePath = data.returnData;
                        $("#upFileDiv").hide();

                        var html = [];
                        html.push('<tr>');
                        html.push('<td ><a href="javascript:;" data-filename="'+fileName+'" data-filepath="'+filePath+'" style="color: red">'+fileName+'</a></td>');
                        html.push('<td class="w_h_100_36"><a href="javascript:;" style="color: red">'+successName+'</a></td>');
                        html.push('<td><a href="javascript:;" class="btnDelete" style="color: red">'+deleteName+'</a></td>');
                        html.push('</tr>');
                        $("#attachmentTbody").append(html.join(""));


                        /*  $("#file").val(""); */
                        $("#file").attr('type','text'); //解决input文件上传不能传同一文件主要就是这两句操作
                        $("#file").attr('type','file');
                    }else{
                        swal("上传失败，请稍后重试");
                    }
                },
                error : function(responseStr) {
                    swal("文件上传失败");
                }
            });
        }
        reader.readAsDataURL(oFile);
    }

    //文件删除,第一次填写举报时候的删除
    $("#attachmentTbody").on('click', '.btnDelete', function () {
        /* 	console.log(fileS);
            console.log($("#attachmentTbody").find("tr")); */
        var index = $("#attachmentTbody").find("tr").index($(this).closest('tr'));
        $(this).closest('tr').remove();
        fileS.splice(index,1);
        /*    console.log(fileS);  */

    });

    //修改的时候的,已上传文件的文件删除
    function deleteUpdate(obj){
            $(obj).closest('tr').remove();
    }




</script>
</body>
</html>