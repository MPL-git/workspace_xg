<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>角色编辑</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/lib/zTree_v3/css/metroStyle/metroStyle.css" />

</head>

<body>

<div class="modal-dialog" role="document" style="width:900px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">角色编辑</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
                <input type="hidden" value="${model.id}" name="model.id">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">角色名称</td>
                            <td colspan="2" class="text-left">
                                <input type="text" name="model.roleName" id="roleName" value="${model.roleName}" validate="{required:true}" >
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td">角色说明</td>
                            <td colspan="2" class="text-left">
                                <input type="text" id="name" name="model.remarks" value="${model.remarks}" >
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td text-left" colspan="3">权限设置</td>
                        </tr>
                        <tr>
                            <td colspan="3" class="text-left">
                                <ul id="menuTree" class="ztree"></ul>

                            </td>
                        </tr>


                        </tbody>
                    </table>
                </div>
            </form>

            <div class="modal-footer">
                <button class="btn btn-info" onclick="commitSave();">提交</button>
                <button class="btn btn-info" data-dismiss="modal">取消</button>
            </div>

        </div>
    </div>
</div>


<script src="${ctx}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${ctx}/static/lib/zTree_v3/js/jquery.ztree.all.min.js" type="text/javascript"></script>

<script type="text/javascript">

    var setting = {
        view: {
            selectedMulti: false
        },
        check: {
            enable: true
        },
        data: {
            key:{
                checked:"checked",
                children:"subMenus",
                name:"menuName"
            },
            simpleData: {
                enable: true,
                idKey:"id",
                pIdKey: "parentId"
            }
        },
        edit: {
            enable: false
        }
    };

    var dataFromValidate;
    var zNodes;
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


        var data = [];
        var roleId = $("input[name='model.id']").val();
        if(roleId != ""){
            data.push({"name":"id", "value":roleId});
        }
        $.ajax({
            url: "${ctx}/subAccount/getRoleMenuTree",
            type: 'POST',
            dataType: 'json',
            cache: false,
            async: false,
            data: data,
            timeout: 30000,
            success: function (result) {
                if (result.success) {
                    zNodes = result.data.menuList;
                }
            }
        });

        $.fn.zTree.init($("#menuTree"), setting, zNodes);

    });


    function commitSave() {
        if (dataFromValidate.form()) {
            var dataJson = $("#dataFrom").serializeArray();
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getCheckedNodes();
            dataJson.push({"name":"menus", "value":JSON.stringify(nodes)});

            $.ajax({
                url: "${ctx}/subAccount/saveRole",
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
