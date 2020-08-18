<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<style type="text/css">
    body {font-size: 12px;padding: 10px;}
    td input,td select{border:1px solid #AECAF0;}
    .table-title{font-size: 14px;color: #333333;font-weight: 600;}
    .title-top{padding-top:10px;padding-bottom:10px;}
    .marR10{margin-right:10px;}
    .marT10{margin-top:10px;}
    .marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">
    var viewerPictures;

    $(function () {

        viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
                $("#viewer-pictures").hide();
            }});

        $("#confirm").bind('click',function(){
            var id = $("#id").val();
            var pictures = [];
            var lis = $(".upload_image_list").find("li");
            lis.each(function(index, item) {
                var path = $("img", item).attr("path");
                var def = ($(".def", item).length == 1 ? "1" : "0");
                var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
                pictures.push(pic);
            });
            // if(pictures.length > 0){
            //     if(pictures.length>5){
            //         commUtil.alertError("最多只能上传5个凭证");
            //         return false;
            //     }else{
            //         $("#cooperationChangeUploadPics").val(JSON.stringify(pictures));
            //     }
            // }
            var param = $("#form").serializeArray();
            param.push({"name":"cooperationChangeUploadPics", "value":JSON.stringify(pictures)});
            // $.ligerDialog.alert(param);
            // var cooperationChangeUploadPic = $("#cooperationChangeUploadPic").val();
            $.ajax({
                url : "${pageContext.request.contextPath}/cooperationChangeApply/toUploadPicture.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : param,
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        commUtil.alertSuccess("确认成功");
                        setTimeout(function(){
                            parent.location.reload();
                            frameElement.dialog.close();
                        },1000);
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        });
    });

    function viewerPic(imgPath){
        $("#viewer-pictures").empty();
        viewerPictures.destroy();
        $("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
        viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
                $("#viewer-pictures").hide();
            }});
        viewerPictures.show();
    }

    function ajaxFileUpload() {
        $.ajaxFileUpload({
            url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
            secureuri: false,
            fileElementId: "cooperationChangeUploadPic",
            dataType: 'json',
            success: function(result, status) {
                if(result.RESULT_CODE == 0) {
                    // $("#cooperationChangeUploadPics").val($("#cooperationChangeUploadPic").val());
                    // alert( $("#cooperationChangeUploadPics").val());
                    // alert( $("#cooperationChangeUploadPic"));
                    uploadImageList.addImage("${pageContext.request.contextPath}/file_servelt", result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error: function(result, status, e) {
                alert("服务异常");
            }
        });

    }
</script>
<html>
<body>

<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/cooperationChangeApply/toUploadPicture.shtml">
    <input type="hidden" id="id" name="id" value="${id}">
    <table class="gridtable">
        <tr id="picsTr">
            <td class="title">上传</td>
            <td colspan="2" align="left" class="l-table-edit-td">
                <t:imageList name="pictures" list="${cooperationChangeUploadPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
                <div style="float: left;height: 105px;margin: 10px;">
                    <input style="position:absolute; opacity:0;" type="file" id="cooperationChangeUploadPic" name="file" onchange="ajaxFileUpload();" />
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                </div>
<%--                <input id="cooperationChangeUploadPics" name="cooperationChangeUploadPics" type="hidden" value="">--%>
            </td>
        </tr>
        <tr>
            <td class="title">操作</td>
            <td colspan="2" align="left" class="l-table-edit-td">
                <div id="btnDiv">
                    <input id="confirm" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
                </div>
            </td>
        </tr>
    </table>

</form>

<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
