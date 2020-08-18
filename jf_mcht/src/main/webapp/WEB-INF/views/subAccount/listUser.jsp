<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>子账号管理</title>
</head>

<body>
<div class="x_panel container-fluid zzh-gl">
    <div class="row content-title">
        <div class="t-title">
            子账号管理
            <a href='javascript:void(0);' onclick="edit();">添加子账号</a>
        </div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <div class="form-group">
                <label for="userName" class="col-md-1 control-label search-lable">角色名称：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text"  id="userName" name="userName">
                </div>
                <div class="col-md-3 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">查询</button>
                </div>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation"> <a href="#" role="tab" data-toggle="tab" onclick="listRole();">角色</a></li>
        <li role="presentation" class="active"> <a href="#" role="tab" data-toggle="tab">子账号</a></li>
    </ul>
    <div class="x_content container-fluid at-table">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>姓名</th>
                        <th>用户名</th>
                        <th>角色</th>
                        <th>联系电话</th>
                        <th>最后登录时间</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>

<!-- Bootstrap -->
<script src="${ctx}/static/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/dateUtil.js"></script>
<script>

    function listRole() {
        var url = "${ctx}/subAccount/listRolePage";
        getContent(url);
    }

    function edit(id) {
        var url = "${ctx}/subAccount/editUser";
        if(id){
            url += "?id=" + id;
        }
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                $("#viewModal").html(data);
                $("#viewModal").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    function viewRole(roleId){
        var url = "${ctx}/subAccount/viewRole?id=" + roleId;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                $("#viewModal").html(data);
                $("#viewModal").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    var table;
    $(document).ready(function () {
        table = $('#datatable').dataTable({
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm").serializeArray();
                param.push({"name": "pageSize", "value": data.length});
                if (data.start > 0) {
                    param.push({"name": "pageNumber", "value": data.start / data.length + 1});
                } else {
                    param.push({"name": "pageNumber", "value": 1});
                }

                $.ajax({
                    method: 'POST',
                    url: '${ctx}/subAccount/listUser',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.success) {
                        var returnData = {};
                        returnData.recordsTotal = result.data.page.totalRow;
                        returnData.recordsFiltered = result.data.page.totalRow; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.data.page.list;
                        callback(returnData);
                    }
                });
            },
            "language": {"url": '${ctx}/static/cn.json'},
            "autoWidth": true,   // 禁用自动调整列宽
            "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
            "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
            "processing": true,  // 隐藏加载提示,自行处理
            "serverSide": true,   // 启用服务器端分页
            "searching": false,   // 禁用原生搜索
            "bSort": false,
            "bServerSide": true,
            "columns": [
                {"data": "userName"},
                {"data": "userCode"},
                {"data": "roleName"},
                {"data": "mobile"},
                {"data": "lastLoginTime"},
                {"data": "status", render:function(data, type, row, meta){
                    if(data == "1"){
                        return "禁用"
                    }else{
                        return "正常";
                    }
                }},
                {"data": "id", "render": function (data, type, row, meta) {
                    var html = [];
                    html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="edit(', "'"+row.id+"'", ');">修改账号</a>');
                    html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="viewRole(', "'"+row.roleId+"'", ');">查看角色</a>');
                    return html.join("");
                }}
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });

    });

</script>
</body>
</html>
