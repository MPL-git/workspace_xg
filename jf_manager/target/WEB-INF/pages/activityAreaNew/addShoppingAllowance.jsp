<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<style type="text/css">

    table.gridtable {
        font-family: verdana, arial, sans-serif;
        border-width: 1px;
        border-color: #666666;
        border-collapse: collapse;
        color: #333333;
        font-size: 12px;
        width: 100%;
    }

    table.gridtable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #ffffff;
    }

    table.gridtable td.title {
        background-color: #dedede;
        font-weight: bold;
        text-align: center;
    }

    table.gridtable span.required {
        color: #FF0000;
        font-size: 100%;
        text-align: center;
        text-align: center;
        text-align: center;
    }
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

    .file input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
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

    #coverPicker div:nth-child(2) {
        width: 100% !important;
        height: 100% !important;
    }

    .webuploader-container div:nth-child(2) {
        position: absolute !important;
        top: 0px !important;
        left: 0px !important;
    }
</style>
<script type="text/javascript">
    var viewerPictures;
    $(function () {

        $("#exchangeBeginDate").ligerDateEditor({
            showTime : true,
            width: 158,
            format: "yyyy-MM-dd hh:mm"
        });
        $("#exchangeEndDate").ligerDateEditor({
            showTime : true,
            width: 158,
            format: "yyyy-MM-dd hh:mm"
        });


        var pic = $("#pic").val();
        if (pic != "") {
            $("#imgShow").show();
        }

    })


    function ajaxPicFileUploadImg(statusImg) {
        var file = document.getElementById(statusImg).files[0];
        if(!/image\/(GIF|gif)$/.test(file.type)){
            commUtil.alertError("图片格式不正确！");
            return;
        }
        var size = file.size;
        if(size > 1024*1024 ) {
            commUtil.alertError("图片不能大于1024Kb，请重新上传！");
            return;
        }
        var reader = new FileReader();
        reader.onload = function(e) {
            var image = new Image();
            image.onload = function() {
                if(this.width != '198' && this.height != '198') {
                    commUtil.alertError("图片像素不是198x198像素！");
                }else{
                    ajaxFileUploadLogo(statusImg);
                }
            };
            image.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }


    function ajaxFileUploadLogo() {
        $.ajaxFileUpload({
            url: contextPath + '/service/common/ajax_upload.shtml',
            secureuri: false,
            fileElementId: "logoPicFile",
            dataType: 'json',
            success: function(result, status) {
                if(result.RESULT_CODE == 0) {
                    $("#imgShow").show();
                    $("#logoPic").attr("src",contextPath + "/file_servelt"+result.FILE_PATH);
                    $("#pic").val(result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error: function(result, status, e) {
                alert("服务异常");
            }
        });

    }


    function commitSave(){

        var name = $("#name").val();
        if(name == '') {
            commUtil.alertError("津贴名称不能为空！");
            return;
        }

        var integral1 = $("#integral1").val();
        var integral2 = $("#integral2").val();
        var exchangeAmountMin1 = $("#exchangeAmountMin1").val();
        var exchangeAmountMin2 = $("#exchangeAmountMin2").val();
        var exchangeAmountMax1 = $("#exchangeAmountMax1").val();
        var exchangeAmountMax2 = $("#exchangeAmountMax2").val();
        if(integral1 == ''||integral2 == ''||exchangeAmountMin1 == ''||exchangeAmountMin2 == ''||exchangeAmountMax1 == ''||exchangeAmountMax2 == '') {
            commUtil.alertError("积分兑换设置框内数据都不能为空！");
            return;
        }
        var testNumber =  /^([0]|[1-9][0-9]*)$/;
        if(!testNumber.test(integral1) ||!testNumber.test(integral2)){
            commUtil.alertError("积分兑换只能输入整数");
            return false;
        }

        if ((parseFloat(exchangeAmountMin1) >parseFloat(exchangeAmountMax1))|| (parseFloat(exchangeAmountMin2) >parseFloat(exchangeAmountMax2))){
            commUtil.alertError("积分兑换津贴填写错误");
            return false;
        }

        var popupCount = $("#popupCount").val();
        if(popupCount == '') {
            commUtil.alertError("请选择兑换弹窗设置！");
            return;
        }
        if (popupCount=='3'){
            var day = $("#day").val();
            if(day == '') {
                commUtil.alertError("请填写自定义天数！");
                return;
            }
        }

        var exchangeBeginDate = $("#exchangeBeginDate").val();
        if(exchangeBeginDate == '') {
            commUtil.alertError("兑换开始时间不能为空！");
            return;
        }
        var exchangeEndDate = $("#exchangeEndDate").val();
        if(exchangeEndDate == '') {
            commUtil.alertError("兑换结束时间不能为空！");
            return;
        }
        var dataJson = $("#dataForm").serializeArray();
        $.ajax({
            method: 'POST',
            url: '${pageContext.request.contextPath}/shopping/saveAllowanceSetting.shtml',
            data: dataJson,
            dataType: 'json',
            cache : false,
            async : false,
            timeout : 30000,
            success:function(data){
                if(data.returnCode == '0000'){
                    commUtil.alertSuccess("保存成功");
                    setTimeout(function(){
                        parent.location.reload();
                        frameElement.dialog.close();
                    },1000);
                }else{
                    commUtil.alertError(data.returnMsg);
                }
            },
            error:function(){
                commUtil.alertError("请求失败");
            }
        });
    };




</script>

<body style="padding: 0px; overflow: hidden;">
<form id="dataForm">
    <input type="hidden" id="id" name="id" value="${allowanceSetting.id}"/>
    <table class="gridtable">
        <tr>
            <td class="title">津贴名称<span class="required">*</span></td>
            <td colspan="3">
                <input type="text" style="width: 240px" id="name" name="name" value="${allowanceSetting.name}">
            </td>
        </tr>
        <tr>
            <td class="title">积分兑换设置<span class="required">*</span></td>
            <td colspan="3">
                积分<input type="text" style="width: 50px" id="integral1" name="integral1" value="${integralExchange1.integral}">可兑换补贴
                <input type="text" style="width: 50px" id="exchangeAmountMin1" name="exchangeAmountMin1" value="${integralExchange1.exchangeAmountMin}">
                <input type="text" style="width: 50px" id="exchangeAmountMax1" name="exchangeAmountMax1" value="${integralExchange1.exchangeAmountMax}">元;

                积分<input type="text" style="width: 50px" id="integral2" name="integral2" value="${integralExchange2.integral}">可兑换补贴
                <input type="text" style="width: 50px" id="exchangeAmountMin2" name="exchangeAmountMin2" value="${integralExchange2.exchangeAmountMin}">
                <input type="text" style="width: 50px" id="exchangeAmountMax2" name="exchangeAmountMax2" value="${integralExchange2.exchangeAmountMax}">元
            </td>
        </tr>
        <tr>
            <td class="title">兑换弹窗设置<span class="required">*</span></td>
            <td colspan="3">
                <input class="radioItem" id="popupCount" name="popupCount" type="radio" value="1" checked="checked" <c:if test="${allowanceSetting.popupCount==1}">checked="checked"</c:if> >每日一次
                <input class="radioItem" id="popupCount" name="popupCount" type="radio" value="2" <c:if test="${allowanceSetting.popupCount==2}">checked="checked"</c:if>>终生一次
                <input class="radioItem" id="popupCount" name="popupCount" type="radio" value="3" <c:if test="${allowanceSetting.popupCount==3}">checked="checked"</c:if>>自定义
                <input type="text" style="width: 50px" id="day" name="day" <c:if test="${allowanceSetting.popupCount==3}"> value="${allowanceSetting.day}" </c:if> >天一次<br><br>
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">兑换开始时间<span class="required">*</span></td>
            <td colspan="5" align="left" class="l-table-edit-td">
                <input type="text" id="exchangeBeginDate" name="exchangeBeginDate"   value="<fmt:formatDate value="${allowanceSetting.exchangeBeginDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
            </td>
        </tr>

        <tr>
            <td colspan="1" class="title">兑换结束时间<span class="required">*</span></td>
            <td colspan="5" align="left" class="l-table-edit-td">
                <input type="text" id="exchangeEndDate" name="exchangeEndDate" value="<fmt:formatDate value="${allowanceSetting.exchangeEndDate}" pattern="yyyy-MM-dd HH:mm"/>"/>
            </td>
        </tr>
        <tr>
            <td  class="title" width="20%">浮窗图片<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td" >
                <div style="display: none" id="imgShow"><img id="logoPic" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${allowanceSetting.pic}" ></div>
                <div style="float: left;height: 30px;">
                    <input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxPicFileUploadImg('logoPicFile');" />
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                    <span style="color: gray;">(图片尺寸不超过198*198px，大小不超过1024kb)</span>
                </div>
                <input id="pic" name="pic" type="hidden" value="${allowanceSetting.pic}">
            </td>
        </tr>

        <tr>
            <td class="title">操作</td>
            <td colspan="3">
                <input type="button" class="l-button" onclick="commitSave()" value="保存">
            </td>
        </tr>
    </table>
</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
</ul>
</body>