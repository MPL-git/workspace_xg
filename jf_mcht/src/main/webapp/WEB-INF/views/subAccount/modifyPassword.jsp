<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>修改密码</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />

</head>

<body>

<div class="modal-dialog" role="document" style="width:700px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">修改密码</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">旧密码</td>
                            <td colspan="2" class="text-left">
                                <input type="text" name="oldPassword" validate="{required:true}" >
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">新密码</td>
                            <td colspan="2" class="text-left">
                                <input type="text" name="newPassword" validate="{required:true}" >
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">确认密码</td>
                            <td colspan="2" class="text-left">
                                <input type="text" name="confirmPassword" validate="{required:true}" >
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </form>

            <div class="modal-footer">
                <button class="btn btn-info" onclick="commitSave();">确认修改</button>
                <button class="btn btn-info" data-dismiss="modal">取消</button>
            </div>

        </div>
    </div>
</div>


<script src="${ctx}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript">

    var dataFromValidate;
    $(function () {

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


    });


    function commitSave() {
        if (dataFromValidate.form()) {
            var dataJson = $("#dataFrom").serializeArray();

            $.ajax({
                url: "${ctx}/subAccount/modifyPasswordCommit",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
                        $("#viewModal").modal('hide');
                        swal({
                            title: "提交成功!",
                            type: "success",
                            confirmButtonText: "确定"
                        });
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


</script>
</body>
</html>
