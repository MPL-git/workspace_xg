<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link
        href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
        rel="stylesheet" type="text/css" />
<script
        src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
        type="text/javascript"></script>

<script
        src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/common/js/uploadImageList.js"
        type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
      rel="stylesheet" type="text/css" />
<link
        href="${pageContext.request.contextPath}/css/upload_image_list.css"
        rel="stylesheet" type="text/css" />

<style type="text/css">
    body {
        font-size: 12px;
        padding: 10px;
    }

    .radioClass {
        margin-right: 20px;
    }

    .l-table-edit {

    }

    .l-table-edit-td {
        padding: 4px;
    }

    .l-button-submit,.l-button-test {
        width: 80px;
        float: left;
        margin-left: 10px;
        padding-bottom: 2px;
    }

    .l-verify-tip {
        left: 230px;
        top: 120px;
    }
</style>
<style type="text/css">
    .middle input {
        display: block;
        width: 30px;
        margin: 2px;
    }

    table.l-checkboxlist-table td {
        border-style: none;
    }

    table.l-listbox-table td {
        border-style: none;
    }
</style>
<script type="text/javascript">
    //提交操作
    $(function() {
        $("#Button1").bind('click',function(){

            var name = $("#name").val();
            var bottomBgPic = $("#bottomBgPic").val();

            if(!name){
                commUtil.alertError("请填写会场名字");
                return false;
            }
            if(name.length>15){
                commUtil.alertError("会场名字请控制在15字以内");
                return false;
            }

            if(!bottomBgPic){
                commUtil.alertError("请上传底部背景图片");
                return false;
            }

            var param = $("#form1").serialize();
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/SaveUpVenue.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : param,
                timeout : 30000,
                success : function(data) {
                    if (data.returnCode=='0000') {
                        commUtil.alertSuccess("保存成功");
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


    //图片格式验证
    function ajaxFileUploadImg(statusImg) {
        var file = document.getElementById(statusImg).files[0];
        if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg)$/.test(file.type)){
            commUtil.alertError("图片格式不正确！");
            return;
        }
        var reader = new FileReader();
        reader.onload = function(e) {
            var image = new Image();
            image.onload = function() {
               // var highStr = '110';
                if(this.width != '750' || this.height != '110') {
                    commUtil.alertError("图片不是750*110像素！");
                }else{
                    ajaxFileUploadLogo();
                }
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);
    }

    //文件上传
    function ajaxFileUploadLogo() {
        $.ajaxFileUpload({
            url : contextPath + '/service/common/ajax_upload.shtml',
            secureuri : false,
            fileElementId : "logoPicFile",
            dataType : 'json',
            success : function(result, status) {
                if (result.RESULT_CODE == 0) {
                    $("#logPic").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
                    $("#bottomBgPic").val(result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error : function(result, status, e) {
                alert("服务异常");
            }
        });
    }
</script>

<html>
<body>
<form method="post" id="form1" name="form1" >
    <input id="id" name="id" type="hidden"  value="${activityAreaSetting.id}" />
    <table class="gridtable">

        <tr>
            <td class="title">会场名称<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input id="name" name="name" type="text"  value="${activityAreaSetting.name}" style="float:left;width: 200px;" validate="{paramNameUnique:true}" /></td>
        </tr>
        <tr>
            <td class="title">底部背景图 <span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <div>
                    <img id="logPic" style="width: 100px;height: 50px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityAreaSetting.bottomBgPic}">
                </div>
                <div style="float: left;margin: 10px;">
                    <input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxFileUploadImg('logoPicFile');" />
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                    <br>
                    <span style="color: red;">提示：图片宽为750PX,高为110PX；支持.png.jpg</span>
                </div> <input id="bottomBgPic" name="bottomBgPic" type="hidden" value="${activityAreaSetting.bottomBgPic}">
            </td>
        </tr>

        <tr>
            <td class="title">操作</td>
            <td align="left" class="l-table-edit-td">
                <div id="btnDiv">
                    <div>
                        <input name="btnSubmit" type="text" id="Button1"
                               style="float:left;" class="l-button" value="提交" />
                    </div>
<%--                    <div>
                        <input name="btnCancle" type="button" id="Button2"
                               style="float:left; margin-left: 40px" class="l-button"
                               value="取消" onclick="frameElement.dialog.close();" />
                    </div>--%>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
