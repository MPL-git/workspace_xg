<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />


<script type="text/javascript">
    //提交操作
    $(function() {

      //  var linkTypeNum = ${activityAreaModule.linkType};
        var linkTypeNum = $("#linkTypeNum").val()
        if(linkTypeNum =='35'|| linkTypeNum =='36'){
            $("#selectTr").hide();
        }

        $("#Button1").bind('click',function(){

            var name = $("#name").val();
            var defaultModulePic = $("#defaultModulePic").val();
            var selectedModulePic = $("#selectedModulePic").val();
            var linkType = $("#linkType").val();
            var linkValue = $("#linkValue").val();

            if(!name){
                commUtil.alertError("请填写模块名字");
                return false;
            }
            if(name.length>15){
                commUtil.alertError("模块名字请控制在15字以内");
                return false;
            }
            if(!defaultModulePic){
                commUtil.alertError("请选择默认模块图片");
                return false;
            }
            if(!selectedModulePic){
                commUtil.alertError("请选择选中模块图片");
                return false;
            }
            if(!linkType){
                commUtil.alertError("请选择连接类型");
                return false;
            }
            if(linkType!="35" && linkType!="36"&& linkValue==""){
                commUtil.alertError("请填写连接详情");
                return false;
            }
            var testNumber = /^[0-9]*[1-9][0-9]*$/;
            console.log(linkType);
            console.log(linkValue);
            if(!testNumber.test(linkValue) && linkType =='1' ){
                commUtil.alertError("请填写正确的会场id");
                return false;
            }
            if(!testNumber.test(linkValue) && linkType =='4' ){
                commUtil.alertError("请填写正确的自建页面id");
                return false;
            }
            if(linkValue.length>9){
                commUtil.alertError("请最多输入九位数");
                return false;
            }

            var param = $("#form1").serialize();
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/saveEditModule.shtml",
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
                var widthStr = '750';
                if(this.width != widthStr) {
                    commUtil.alertError("图片宽度不是"+widthStr+"像素！");
                }else{
                    if("defaultPic"==statusImg){
                        ajaxFileUploadDefault();
                 }else if("selectedPic"==statusImg){
                        ajaxFileUploadSelected();
                 }

                }
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(file);
    }



    //默认模块图片上传
    function ajaxFileUploadDefault() {
        $.ajaxFileUpload({
            url : contextPath + '/service/common/ajax_upload.shtml',
            secureuri : false,
            fileElementId : "defaultPic",
            dataType : 'json',
            success : function(result, status) {
                if (result.RESULT_CODE == 0) {
                    $("#default").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
                    $("#defaultModulePic").val(result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error : function(result, status, e) {
                alert("服务异常");
            }
        });
    }

    //选中模块图片上传
    function ajaxFileUploadSelected() {
        $.ajaxFileUpload({
            url : contextPath + '/service/common/ajax_upload.shtml',
            secureuri : false,
            fileElementId : "selectedPic",
            dataType : 'json',
            success : function(result, status) {
                if (result.RESULT_CODE == 0) {
                    $("#selected").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
                    $("#selectedModulePic").val(result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error : function(result, status, e) {
                alert("服务异常");
            }
        });
    }

    //当选择我的优惠券的时候,隐藏详细信息栏
    function hideLinkValue(){
        var linkType = $("#linkType").val()
        if(linkType == '35' || linkType=='36'){
            $("#selectTr").hide();
            $("#linkValue").val("")
        }else{
            $("#selectTr").show();
        }
    }

</script>

<html>
<body>
<form method="post" id="form1" name="form1" >
    <input id="id" name="id" type="hidden"  value="${activityAreaModule.id}" />
    <input id="linkTypeNum" name="linkTypeNum" type="hidden"  value="${activityAreaModule.linkType}" />
    <table class="gridtable">

        <tr>
            <td class="title">模块名称<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input id="name" name="name" type="text"  value="${activityAreaModule.name}" style="float:left;width: 200px;" validate="{paramNameUnique:true}" /></td>
        </tr>
        <tr>
            <td class="title">默认模块图片 <span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <div>
                    <img id="default" style="width: 100px;height: 50px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityAreaModule.defaultModulePic}">
                </div>
                <div style="float: left;margin: 10px;">
                    <input style="position:absolute; opacity:0;" type="file" id="defaultPic" name="file" onchange="ajaxFileUploadImg('defaultPic');" />
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                    <br>
                    <span style="color: red;">提示：图片宽为750PX；支持.png.jpg</span>
                </div> <input id="defaultModulePic" name="defaultModulePic" type="hidden" value="${activityAreaModule.defaultModulePic}">
            </td>
        </tr>

        <tr>
            <td class="title">选中模块图片 <span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <div>
                    <img id="selected" style="width: 100px;height: 50px" alt="" src="${pageContext.request.contextPath}/file_servelt${activityAreaModule.selectedModulePic}">
                </div>
                <div style="float: left;margin: 10px;">
                    <input style="position:absolute; opacity:0;" type="file" id="selectedPic" name="file" onchange="ajaxFileUploadImg('selectedPic');" />
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                    <br>
                    <span style="color: red;">提示：图片宽为750PX；支持.png.jpg</span>
                </div> <input id="selectedModulePic" name="selectedModulePic" type="hidden" value="${activityAreaModule.selectedModulePic}">
            </td>
        </tr>

        <tr>
            <td class="title">链接类型 <span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <select id = "linkType" name = "linkType"  style="width:180px;" onchange="hideLinkValue()">
                    <option value=""  >请选择</option>
                    <option value="1" <c:if test="${activityAreaModule.linkType==1}">selected="selected"</c:if> >会场id</option>
                    <option value="4" <c:if test="${activityAreaModule.linkType==4}">selected="selected"</c:if>>自建页面id</option>
                    <option value="35" <c:if test="${activityAreaModule.linkType==35}">selected="selected"</c:if>>我的优惠券</option>
                    <option value="36" <c:if test="${activityAreaModule.linkType==36}">selected="selected"</c:if>>购物津贴</option>
                </select>
            </td>
        </tr>

        <tr id="selectTr">
            <td class="title">链接详情 <span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input id = "linkValue" name = "linkValue" value="${activityAreaModule.linkValue}"/>
            </td>
        </tr>



        <tr>
            <td class="title">操作</td>
            <td align="left" class="l-table-edit-td">
                <div id="btnDiv">
                    <div>
                        <input name="btnSubmit" type="text" id="Button1" style="float:left;" class="l-button" value="提交" />
                    </div>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
