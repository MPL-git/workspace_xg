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
            <a href='javascript:void(0);' onclick="edit();">添加角色</a>
        </div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <div class="form-group">
                <label for="roleName" class="col-md-1 control-label search-lable">角色名称：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text"  id="roleName" name="roleName" >
                </div>
                <div class="col-md-3 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">查询</button>
                </div>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation" class="active"> <a href="#" role="tab" data-toggle="tab">角色</a></li>
        <li role="presentation"> <a href="#" role="tab" data-toggle="tab" onclick="listUser();">子账号</a></li>
    </ul>
    <div class="x_content container-fluid at-table">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>角色</th>
                        <th>用户</th>
                        <th>创建时间</th>
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

    function listUser() {
        var url = "${ctx}/subAccount/listUserPage";
        getContent(url);
    }

    function edit(id) {
        var url = "${ctx}/subAccount/editRole";
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

    function del(id){
        swal({
            title: "确定要删除该角色吗?",
            type: "warning",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false
        },
        function(){
            var ids=[];
            ids.push(id);
            console.log(JSON.stringify(ids));
            var postData= {"ids":JSON.stringify(ids)};
            $.ajax({
                url : "${ctx}/subAccount/deleteRole",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : postData,
                timeout : 30000,
                success : function(result) {
                    if (result.success) {
                        table.ajax.reload();
                    } else {
                        swal({
                            title: result.returnMsg,
                            type: "error",
                            confirmButtonText: "确定",
                            closeOnConfirm: true
                        });
                    }

                },
                error : function() {
                    swal({
                        title: "处理失败！",
                        type: "error",
                        timer: 1500,
                        confirmButtonText: "确定"
                    });
                }
            });
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
                    url: '${ctx}/subAccount/listRole',
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
                {"data": "roleName"},
                {"data": "userNames"},
                {"data": "createDate"},
                {"data": "id", "render": function (data, type, row, meta) {
                    var html = [];
                    html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="edit(', "'"+row.id+"'", ');">修改</a>');
                    html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="del(', "'"+row.id+"'", ');">删除</a>');
                    return html.join("");
                }}
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });
        
        $("#roleName").keydown(function(e){
        	if(e.keyCode==13){
        		table.ajax.reload();
                return false;
        	}
        });
    });

</script>
</body>
</html>
