<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.8.3.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        var submitFlag = true;
        $(function () {
            $.metadata.setType("attr", "validate");
            $("#form1").validate({
                errorPlacement: function (lable, element) {
                    var elementType = $(element).attr("type");
                    if ('radio' == elementType) {
                        var radioName = $(element).attr("name");
                        $("input[type=radio][name=" + radioName + "]:last").parent("span").ligerTip({
                            content: lable.html(), width: 100
                        });
                    } else {
                        $(element).ligerTip({
                            content: lable.html(), width: 100
                        });
                    }
                    $(".l-verify-tip-corner").css("z-index", "1001");
                    $(".l-verify-tip-content").css("z-index", "1000");
                },
                success: function (lable, element) {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function (form) {
                    if (submitFlag) {
                        submitFlag = false;
                        formSubmit();
                    }
                }
            });

        });

        function formSubmit() {
            $.ajax({
                url: "${pageContext.request.contextPath}/couPon/addSpecialMcht.shtml",
                type: 'POST',
                dataType: 'json',
                data:  $("#form1").serialize(),
                success: function (data) {
                    if ("0000" == data.returnCode) {
                        parent.location.reload();
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                    submitFlag = true;
                },
                error: function () {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function getMchtInfo(){
            var mchtCode = $("#mchtCode").val();
            if(mchtCode == ""||mchtCode.length == 0){
                commUtil.alertError("请先输入商家序号");
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/couPon/getMchtInfo.shtml",
                type: 'GET',
                dataType: 'json',
                data: $("#form1").serialize(),
                success: function (data) {
                    if ("0000" == data.returnCode ) {
                        if (data.mchtInfoCustom!=null ) {
                            if (data.mchtInfoCustom.id != null) {
                                $("#mchtId").attr("value", data.mchtInfoCustom.id)
                            }
                            if (data.mchtInfoCustom.companyName != null) {
                                $("#companyName").attr("value", data.mchtInfoCustom.companyName)
                            } else {
                                $("#companyName").attr("value", "(无数据)")
                            }
                            if (data.mchtInfoCustom.shopName != null) {
                                $("#shopName").attr("value", data.mchtInfoCustom.shopName)
                            } else {
                                $("#shopName").attr("value", "(无数据)")
                            }
                            if (data.mchtInfoCustom.productType != null) {
                                $("#productType").attr("value", data.mchtInfoCustom.productType)
                            } else {
                                $("#productType").attr("value", "(无数据)")
                            }
                        }else {
                            $.ligerDialog.error("此商家序号不存在或者商家业务状态不正常");
                        }
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error: function () {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }
    </script>

</head>
<body style="margin: 10px; ">
<form name="form1" class="form1" method="post" id="form1" action="">
    <table class="gridtable">
    <input type="hidden" id="mchtId" name="mchtId" value="">
        <tr>
            <td class="title" width="30%">商家序号</td>
            <td align="left" class="l-table-edit-td">
                <input style="width:200px;" type="text" id="mchtCode" name="mchtCode" value=""
                       validate="{required:true}"/>
                <input type="button" class="l-button l-button-submit" onclick="getMchtInfo()" value="查询">
            </td>
        </tr>
        <tr>
            <td class="title" width="30%">公司名称<span class="required">
            <td align="left" class="l-table-edit-td">
                <input style="width:300px;" type="text" id="companyName" name="companyName"
                       value="" validate="{required:true}"/>
            </td>
        </tr>
        <tr>
            <td class="title" width="30%">商家名称<span class="required">
            <td align="left" class="l-table-edit-td">
                <input style="width:300px;" type="text" id="shopName" name="shopName"
                       value="" validate="{required:true}"/>
            </td>
        </tr>
        <tr>
            <td class="title" width="30%">主营类目<span class="required">
            <td align="left" class="l-table-edit-td">
                <input style="width:300px;" type="text" id="productType" name="productType"
                       value="" validate="{required:true}"/>
            </td>
        </tr>
        <tr>
            <td class="title" width="30%">操作</td>
            <td align="left" class="l-table-edit-td">
                <input type="submit" class="l-button l-button-submit" value="提交"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>