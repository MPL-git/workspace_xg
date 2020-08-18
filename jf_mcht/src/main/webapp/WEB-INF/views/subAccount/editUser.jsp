<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>账号编辑</title>
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
            <span class="modal-title" id="exampleModalLabel">账号编辑</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
                <input type="hidden" value="${model.id}" name="model.id">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">用户名</td>
                            <td colspan="2" class="text-left">
                                <input type="text" value="${primaryUser.userCode}_" style="text-align: right;padding-right: 8px;margin-right: 7px;" readonly="readonly" >
                                <input type="text" name="model.userCode" value="${model.userCode}" validate="{required:true}" >
                                <c:if test="${not empty model}">
                                    <input class="vm" style="margin-right: 3px;" type="checkbox" name="model.status" value="1" <c:if test="${model.status=='1'}">checked="checked"</c:if> />禁用
                                </c:if>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">密码</td>
                            <td colspan="2" class="text-left">
                                <c:if test="${empty model}">
                                    <input type="text" name="model.password" value="" validate="{required:true}" >
                                </c:if>
                                <c:if test="${not empty model}">
                                    <input type="text" name="model.password" value="" >
                                </c:if>

                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">角色</td>
                            <td colspan="2" class="text-left">
                                <select name="roleId" validate="{required:true}">
                                    <option value="">--请选择--</option>
                                    <c:forEach var="role" items="${roleList}">
                                        <option value="${role.id}"
                                                <c:if test="${model.get('roleId')==role.id}">selected</c:if>>${role.roleName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">姓名</td>
                            <td colspan="2" class="text-left">
                                <input type="text" name="model.userName" value="${model.userName}" validate="{required:true}" >
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">手机</td>
                            <td colspan="2" class="text-left">
                                <input type="text" name="model.mobile" value="${model.mobile}" >
                            </td>
                        </tr>


                        </tbody>
                    </table>
                </div>
            </form>

            <div class="modal-footer">
                <c:if test="${empty model}">
                    <button class="btn btn-info" onclick="commitSave();">保存</button>
                </c:if>
                <c:if test="${not empty model}">
                    <button class="btn btn-info" onclick="commitSave();">修改</button>
                </c:if>
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
                url: "${ctx}/subAccount/saveUser",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
                        $("#viewModal").modal('hide');
                        table.ajax.reload();
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
