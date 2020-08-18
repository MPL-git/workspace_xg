<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>扫描上传</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <!--    <link href="${pageContext.request.contextPath}/static/css/editor.dataTables.min.css" rel="stylesheet" type="text/css" /> -->
    <!--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/fileuploader-style.css" /> -->
    <style type="text/css">
        .ax{
        }
        .td-pictures li{
            display: inline-block;
        }
        .td-pictures li img{
            width: 100px;
            height: 100px;
        }

        .prop-container{
            padding-top: 20px;
        }
        .glyphicon-remove{
            color:red;
            margin-left: 3px;
            cursor:pointer;
        }
        .prop-name{
            font-weight: bold;
        }

        .docs-pictures{
            padding: 0px;
        }
        .docs-pictures li{
            position: relative;
            margin: 3px;
        }
        .pic-btn-container{
            width:100%;
            position: absolute;
            top: 0px;
            background:rgba(0, 0, 0, 0.5);
            height:0px;
            z-index: 300;
            overflow: hidden;
            text-align: right;
            padding-right: 3px;
        }
        .set-default-btn{
            color:red;
            cursor: pointer;
        }
        .main-pic-mark {
            border-radius: 0 40px 0 0;
            width: 40px;
            height:40px;
            line-height: 40px;
            font-size:12px;
            text-align: left;
            background:rgba(0, 0, 0, 0.5);
            color: #ffffff;
            position: absolute;
            bottom: 0;
            left: 0;
            padding: 5px;
        }
        .sku_pic_picker {
            height: 50px;
        }
        .sku_pic_picker div{
            position:absolute;
            width: 50px;
            height: 50px;
            line-height: 50px;
            font-size: 50px;
            z-index: 1000;
            color: #ddd;
            border: solid 1px #ddd;
        }
        .sku_pic_picker input{
            position: absolute;
            width: 50px;
            height: 50px;
            opacity: 0;
            z-index: 1002;
        }
        .sku_pic_picker img{
            width: 50px;
            height: 50px;
        }
        .dataTables_info{
            display: none;
        }
        .sweet-alert button.cancel:hover {
            background-color:#8CD4F5;
        }
        .sweet-alert button.cancel {
            background-color: #8CD4F5;
        }
        .dataTable input[type='text']{
            width:70%;
        }
        .batch-set-icon{
            color: #8CD4F5;
            font-size: 16px;
            cursor: pointer;
        }
        .popover-content{
            width: 350px;
        }
        .popover{
            max-width: 500px;
            z-index: 200;
        }
        .btn_disable{
            background-color: #d4d4d4;
            background-color: #d4d4d4;
            color: #fff;
        }
    </style>

</head>
<body>
<div class="modal-dialog" role="document" >

    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">扫描上传</span>
        </div>
    <div class="ad-form">
        <form id="dataFrom" method="post">
            <input type="hidden" id="id" name="id" value="${id}">
            <div class="table-responsive">
                <table class="table table-bordered ">
                    <tbody>
                    <tr>
                        <td class="editfield-label-td">上传</td>
                        <td colspan="2" class="text-left">
                            <div id="mcht-feedback-pic">
                                <ul class="docs-pictures clearfix td-pictures" id="mcht-feedback-pic-list" ondrop="drop(event)" ondragover="allowDrop(event)">
                                </ul>
                                <div style="display: inline-block;" onclick="$('#cooperationChangeUploadPicErrorMsg').text('');" id="cooperationChangeUploadPic" class="filePicker">选择图片</div>
                                <span id="cooperationChangeUploadPicErrorMsg" style="vertical-align: top;margin-left: 10px;color: red;"></span>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="editfield-label-td">操作</td>
                        <td colspan="2" class="text-left">
                            <button id="btn" type="button" style="float:left;width: 70px;" class="l-button l-button-test" onclick="commitSave();">提交</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/static/js/webuploader.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>

<script type="text/javascript">
    var dataFromValidate;
    var uploader;
    var multiplePropContainerHtml;
    var singlePropContainerHtml;
    var cooperationChangeUploadPics = []; //图片


    $(function(){
        multiplePropContainerHtml = $("#product-item-table-container").html();
        singlePropContainerHtml = $("#single-product-item-table-container").html();
        $("#single_prop_container").hide();

        uploader = WebUploader.create({
            dnd: '#mcht-feedback-pic',
            paste: 'document.body',
            // swf文件路径
            swf: '${ctx}/static/images/webuploader/Uploader.swf',
            // 文件接收服务端。
            server: '${ctx}/common/fileUpload?fileType=4',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#cooperationChangeUploadPic',
            duplicate:true,
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
            }
        });

        uploader.on('beforeFileQueued', function(file) {
            var pickerId = file.source._refer.context.id;

            if(pickerId=='cooperationChangeUploadPic' && file.size > 200*1024) {//图片列表
                $("#cooperationChangeUploadPicErrorMsg").text("您选择了包含大于200kb的图片！");
                return false;
            }
        });

        uploader.onFileQueued = function(file) {
            var pickerId = file.source._refer.context.id;
            if(pickerId == 'cooperationChangeUploadPic') {//图片列表
                var oFReader = new FileReader();
                oFReader.onload = function(oFREvent) {
                    var img = new Image();
                    img.onload = function() {
                        if($("#mcht-feedback-pic-list").children("li").length >= 9) {
                            $("#cooperationChangeUploadPicErrorMsg").text("图片数量最多9张");
                            uploader.removeFile(file, true );
                            return;
                        }
                        var $li = $('<li class="pic_li" id="pic_li_'+file.id+'" draggable="true" ondragstart="drag(event)"><img id="pic_'+file.id+'" src="'+oFREvent.target.result+'"></li>');
                        var $btns = $('<div class="pic-btn-container"></div>').appendTo($li);
                        var $removeBtn = $('<span  class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>').appendTo($btns);
                        $('#mcht-feedback-pic-list').append($li);
                        $li.on('mouseenter', function() {
                            $btns.stop().animate({height: 20});
                        });
                        $li.on('mouseleave', function() {
                            $btns.stop().animate({height: 0});
                        });
                        $removeBtn.on('click', function() {
                                $li.remove();
                                uploader.removeFile(file);
                            }
                        );
                    };
                    img.src = oFREvent.target.result;
                };
                oFReader.readAsDataURL(file.source.source);
            }

        };

        uploader.on('uploadSuccess', function( file,response ) {
            if(response.returnCode == '0000') {
                $("#pic_li_"+file.id).attr("pic_path",response.returnData);
            }
        });

        //当所有文件上传结束时触发
        uploader.on("uploadFinished",function(){
            commitServer();
        });

        $.metadata.setType("attr", "validate");

    });

    function commitSave(){
        var picList = $("#mcht-feedback-pic-list").children("li");
        if (!picList){
            hide_waitMe();
            swal({
                title:"图片不能为空",
                type:"error",
                confirmButtonText:"确定",
            });
            return;
        }
        dataFromValidate = $("#dataFrom").validate({
            errorPlacement: function(error, element) {
                console.log($(element).closest('td'));
                error.appendTo($(element).closest('td'));
            }
        });
        if(dataFromValidate.form()) {
            show_waitMe();
            uploader.upload();
        }

        document.getElementById('btn').disabled=true;
    }

    function commitServer() {
        var dataJson = $("#dataFrom").serializeArray();

        $("#mcht-feedback-pic-list").children("li").each(function(index,element) {
            var pic = {};
            pic.pic = $(element).attr("pic_path");
            pic.seqNo = index;
            cooperationChangeUploadPics.push(pic);
        });


        dataJson.push({"name":"cooperationChangeUploadPics", "value":JSON.stringify(cooperationChangeUploadPics)});
        $.ajax({
            url : "${pageContext.request.contextPath}/cooperationChangeApply/toUploadPicture",
            type : 'POST',
            dataType : 'json',
            cache : false,
            async : false,
            data : dataJson,
            timeout : 30000,
            success : function(data) {
                if ("0000" == data.returnCode) {
                    swal({
                        title:"提交成功",
                        type:"success",
                        confirmButtonText: "确定",
                    });
                    setTimeout(function(){
                        parent.location.href=parent.location.href;
                        frameElement.dialog.close();
                    },1000);
                }else{
                    swal({
                        title: "提交失败",
                        type: "error",
                        confirmButtonText:"确定"
                    });
                }
                hide_waitMe();

            },
            error : function() {
                hide_waitMe();
                swal({
                    title: "提交失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
        });
    }

</script>
</body>
</html>
