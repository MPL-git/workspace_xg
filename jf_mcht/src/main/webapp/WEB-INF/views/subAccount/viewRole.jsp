<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>角色编辑</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/lib/zTree_v3/css/metroStyle/metroStyle.css" />

</head>

<body>

<div class="modal-dialog" role="document" style="width:900px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">角色信息</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">角色名称</td>
                            <td colspan="2" class="text-left">
                                ${model.roleName}
                            </td>
                        </tr>
                        <tr>
                            <td class="editfield-label-td">角色说明</td>
                            <td colspan="2" class="text-left">
                                ${model.remarks}
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
                <button class="btn btn-info" data-dismiss="modal">关闭</button>
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

    var zNodes;
    $(function () {

        var data = [];
        data.push({"name":"id", "value":${model.id}});
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

</script>
</body>
</html>
