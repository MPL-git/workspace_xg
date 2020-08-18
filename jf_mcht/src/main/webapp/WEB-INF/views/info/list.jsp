<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>信息列表</title>
    <style type="text/css">
        .hidden{
            display:none;
        }
        thead .table_head{
            font-size: 14px;
            font-weight:bold;
            color:#333333;
        }
        tbody .col_title{
            text-align: left;
            font-size: 14px;
            font-weight:normal;
            color:#228af1;"
        }
        tbody .col_date{
            font-size: 14px;
            font-weight:normal;
            color:#333333;"
        }
        .inline-block{
            display:inline-block;
            width:10px;
        }
    </style>
</head>

<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="t-title">${catalog.frontName}</div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <input type="hidden" name="pageStatus" value="${pageStatus}"/>
            <input type="hidden" name="catalogId" value="${catalog.id}"/>
            <div class="form-group">
                <label for="title" class="col-md-1 control-label search-lable mwa">标题：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text"  id="title" name="title" >
                </div>
                <div class="col-md-3 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">查询</button>
                </div>
                <div class="col-md-6"></div>
            </div>
        </form>

    </div>
    <div class="clearfix"></div>
    <div class="x_content container-fluid pt">
        <div class="row">
            <div class="col-md-12 datatable-container at-table">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th style="text-align: left;padding-left: 10px;">标题</th>
                        <th width="178">时间</th>
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
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>

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
                    url: '${ctx}/info/list',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.success) {
                        var returnData = {};
                        returnData.recordsTotal = result.data.page.totalRow;
                        returnData.recordsFiltered = result.data.page.totalRow; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.data.page.list;
                        console.log(returnData);
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
                { "data": "title", className: "col_title", "render": function (data, type, row, meta) {
                    var html = [];
                    html.push('<div class="inline-block"></div><a class="table-opr-btn" href="javascript:void(0);" onclick="viewContent(', "'" + row.id + "'", ');" >');
                    if(row.isRead == null && row.tag ==null){
                        html.push(row.title + '</a>');
                    }else{
                        html.push(row.title+'<img src="${ctx}/static/images/smallCircle.png" style="margin-top: -16px;width: 5px;"/></a>');
                    }
                    return html.join("");
                }},
                {"data": "releaseTime", className: "col_date", "render": function (data, type, row, meta) {
                	if(data!=null){
                    	return data.toString().substr(0, 10);
                	}else{
                		return "";
                	}
                }}
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });
		
        $("#title").keydown(function(e){
        	if(e.keyCode==13){
        		table.ajax.reload();
                return false;
        	}
        });
    });

    function viewContent(id) {
        var url = "${ctx}/info/content?id=" + id;
        getContent(url);
    }

    function viewContent2(id) {
        var url = "${ctx}/info/content?id=" + id;
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

</script>
</body>
</html>
