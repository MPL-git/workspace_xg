<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>发布任务</title>
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
        <div class="col-md-12 t-title"><c:if test="${empty designTaskOrderCustom}">发布任务</c:if><c:if test="${not empty designTaskOrderCustom}">修改任务</c:if>
            <a href="javascript:void(0);" onclick="backToDesignTaskOrderList()">返回</a>
        </div>
    </div>
    <div class="ad-form">
        <form id="dataFrom">
            <div class="table-responsive">
                <table class="table table-bordered ">
                    <tbody>
                        <tr>
                            <td class="editfield-label-td">任务分类<em class="ad-em">*</em></td>
                            <td colspan="2" class="text-left">
                                <select class="ad-select" <c:if test="${not empty designTaskOrderCustom.taskType}">disabled</c:if> onchange="changeTaskType(this.value)" readonly="true" name="taskType" id="taskType" validate="{required:true}" style="">
                                    <c:forEach var="taskType" items="${taskTypeList}">
                                        <option value="${taskType.statusValue}" <c:if test="${designTaskOrderCustom.taskType == taskType.statusValue}">selected</c:if> >${taskType.statusDesc}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td">任务名称<em class="ad-em">*</em></td>
                            <td colspan="2" class="text-left">
                                <input placeholder="最多可输入50个字" maxlength="50" class="ad-select" type="text" id="taskName"
                                       name="taskName" onkeyup="$('#taskNameLength').text(50-($('#taskName').val().length));"
                                       style="width: 400px;margin-right: 10px;" value="${designTaskOrderCustom.taskName}" <c:if test="${not empty designTaskOrderCustom}">disabled</c:if>
                                       validate="{required:true,rangelength:[1,50]}">
                                <span>最多还可输入：</span>
                                <span style="color:red;margin: 0 3px;" id="taskNameLength">50</span><span>个字</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td" style="border-bottom-width: 0px;">需求详情<em class="ad-em">*</em></td>
                            <td colspan="2" class="text-left" style="border-bottom-width: 0px;">
                                <textarea rows="5" class="txt-area" id="requirement" name="requirement"
                                      maxlength="1000" onkeyup="$('#requirementLength').text(1000-($('#requirement').val().length));"
                                      validate="{required:true,rangelength:[1,1000]}"><c:if test="${empty designTaskOrderCustom}">品牌：
主文案：
副文案：
主推款商品ID：
利益点：
</c:if><c:if test="${not empty designTaskOrderCustom}">${designTaskOrderCustom.requirement}</c:if></textarea>
                                <span>最多还可输入：</span><span style="color:red;margin: 0 3px;" id="requirementLength">1000</span><span>个字</span>
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td" style="border-top-width: 0px;"></td>
                            <td colspan="2" class="text-left" style="border-top-width: 0px;">
                                <div class="form-group" <c:if test="${not empty designTaskOrderCustom.filePath}">style="display: none;"</c:if>>
                                    <a href="javascript:;" class="file" style="line-height: 30px;">
                                        上传附件<input type="file" id="file" name="file" onchange="uploadFile(this)">
                                        <input type="hidden" id="filePath" name="filePath" >
                                    </a>
                                    <span id="fileMes"></span>
                                    <span style="color:#999;line-height: 30px;vertical-align: top;">&nbsp;&nbsp;上传zip,rar压缩包，大小不超过50M</span>
                                </div>
                                <table d="affix" role="grid" aria-describedby="datatable_info"  border="0px solid gray;" width="40%" height="30"  bordercolor="black">
                                    <tbody id="attachmentTbody">
                                        <c:if test="${not empty designTaskOrderCustom.filePath}">
                                            <td ><span style="color: red"  data-filename="${designTaskOrderCustom.remarks}"  data-filepath="${designTaskOrderCustom.filePath}" >${designTaskOrderCustom.remarks}</span></td>
                                            <td class="w_h_100_36"><span style="color: red" >已上传</span></td>
                                            <td><button type="button" class="btn btn-danger btn-xs btnDelete" >删除</button></td>
                                        </c:if>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td">图片详情<em class="ad-em">*</em></td>
                            <td colspan="2" class="text-left" >
                                <c:forEach var="designTaskOrderPicDetail" items="${designTaskOrderPicDetailList}">
                                    <div class="task-type task-type-${designTaskOrderPicDetail.taskType}"
                                        <c:if test="${empty designTaskOrderCustom and designTaskOrderPicDetail.taskType != '1'}" >style="display: none;"</c:if>
                                        <c:if test="${not empty designTaskOrderCustom and designTaskOrderCustom.taskType != designTaskOrderPicDetail.taskType}" >style="display: none;"</c:if>
                                        >
                                        <span style="margin-left: 10px;">尺寸</span>
                                        <input style="width: 60px;margin-left: 5px;" disabled value="${designTaskOrderPicDetail.width}" />
                                        <span style="margin-left: 5px;">px&nbsp;*</span>
                                        <input style="width: 60px;margin-left: 5px;" disabled value="${designTaskOrderPicDetail.height}" />
                                        <span style="margin-left: 20px;">图片说明</span>
                                        <input style="width: 300px;margin-left: 5px;" disabled value="${designTaskOrderPicDetail.picDesc}" />
                                    </div>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td">支付金额</td>
                            <td colspan="2" class="text-left">
                                <span style="color: red;" id="pay-amount" >
                                    <c:if test="${empty designTaskOrderCustom}">
                                        ￥60
                                    </c:if>
                                    <c:if test="${not empty designTaskOrderCustom}">
                                        ￥${designTaskOrderCustom.payAmount}
                                    </c:if>
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td">联系方式</td>
                            <td colspan="2" class="text-left">
                                <input placeholder="最多可输入32个字" maxlength="32" class="ad-select" type="text" id="contactWay"
                                       value="${designTaskOrderCustom.contactWay}" name="contactWay" validate="{rangelength:[0,32]}"
                                       style="width: 200px;margin-right: 10px;">
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td">联系QQ</td>
                            <td colspan="2" class="text-left">
                                <input placeholder="最多可输入16个字" maxlength="16" class="ad-select" type="text" id="qq"
                                       value="${designTaskOrderCustom.qq}" name="qq" validate="{rangelength:[0,16],digits:true}"
                                       style="width: 200px;margin-right: 10px;">
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div style="text-align:center">
                    <button type="button" class="btn btn-info" onclick="commitSave();">提交</button>
                </div>
            </div>
            <input type="hidden" name="id" value="${designTaskOrderCustom.id}" >
        </form>
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
        initRestInput();
    });

    function backToDesignTaskOrderList() {
        getContent('${ctx}/designTaskOrder/designTaskOrderManager');
    }

    function changeTaskType(taskType) {
        $(".task-type").hide();
        $(".task-type-"+taskType).show();
        if(taskType == '1') {
            $("#pay-amount").html("￥60");
        }else {
            $("#pay-amount").html("￥150");
        }
    }

    function commitSave() {
        if(dataFromValidate.form()) {
            var dataJson = $("#dataFrom").serializeArray();
            $.ajax({
                url: "${ctx}/designTaskOrder/editDesignTaskOrder",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
                        swal({
                           title: "上传成功",
                           type: "success",
                           confirmButtonText: "确定"
                        });
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

    function uploadFile(obj) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        var rFilter = ["zip","ZIP","rar","RAR"];
        var suffix = oFile.name.substring(oFile.name.lastIndexOf(".")+1);
        if($.inArray(suffix,rFilter) == -1) {
            swal("文件格式不正确！");
            //解决input文件上传不能传同一文件主要就是这两句操作
            $("#file").attr('type','text');
            $("#file").attr('type','file');
            return;
        }
        if(oFile.size>50*1024*1024){
            swal("文件大小不能超过50M");
            //解决input文件上传不能传同一文件主要就是这两句操作
            $("#file").attr('type','text');
            $("#file").attr('type','file');
            return;
        }
        var reader = new FileReader();
        reader.onload = function(e) {
            var formData = new FormData();
            formData.append("file", oFile);
            formData.append("fileType", 13);
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
                        var deleteName="删除";
                        var filePath = data.returnData;
                        var fileName = filePath.substring(filePath.lastIndexOf("/")+1);
                        var html = [];
                        html.push('<tr>');
                        html.push('<td ><span data-filename="'+fileName+'" data-filepath="'+filePath+'" style="color: red">'+fileName+'</span></td>');
                        html.push('<td class="w_h_100_36"><span style="color: red">'+successName+'</span></td>');
                        html.push('<td><button type="button" class="btn btn-danger btn-xs btnDelete" >'+deleteName+'</button></td>');
                        html.push('</tr>');
                        $("#attachmentTbody").append(html.join(""));
                        $(".form-group").hide();
                        $("#filePath").val(filePath);
                        //解决input文件上传不能传同一文件主要就是这两句操作
                        $("#file").attr('type','text');
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
        $(this).closest('tr').remove();
        $(".form-group").show();
        $("#filePath").val("");
    });

    function initRestInput() {
        $('#taskNameLength').text(50-($('#taskName').val().length));
        $('#requirementLength').text(1000-($('#requirement').val().length));
    }

</script>
</body>
</html>