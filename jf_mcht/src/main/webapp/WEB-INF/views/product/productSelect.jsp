<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>商品列表</title>

    <style>
        #datatable2{
            margin-top:20px !important;
            width:750px !important;
        }
    </style>
</head>

<body>
<div class="modal-dialog" role="document" style="width:800px;">
    <div class="modal-content">
        <div class="modal-header">
            <span class="modal-title" id="exampleModalLabel">选择商品</span>
        </div>
        <div class="modal-body">
            <div class="search-container container-fluid" style="padding:10px 10px;">
                <form class="form-horizontal" id="searchForm2">
                    <div class="form-group">
                        <label for="name2" class="col-md-1 control-label search-lable">商品名称：</label>
                        <div class="col-md-2 search-condition">
                            <input class="form-control" type="text" id="name2" name="name" >
                        </div>
                        <label for="artNo2" class="col-md-1 control-label search-lable">货号：</label>
                        <div class="col-md-2 search-condition">
                            <input class="form-control" type="text" id="artNo2" name="artNo" >
                        </div>

                        <div class="col-md-3 text-center search-btn">
                            <button type="button"  class="btn btn-info" id="btn-query2">查询</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="clearfix"></div>
            <div class="x_content container-fluid">
                <div class="row">
                    <div class="col-md-12 datatable-container">
                        <table id="datatable2"
                               class="table table-striped table-bordered dataTable no-footer"
                               role="grid" aria-describedby="datatable_info2" style="width:700px !important;">
                            <thead>
                            <tr role="row">
                                <th>选择</th>
                                <th>商品信息</th>
                                <th>醒购价</th>
                                <th>库存</th>
                                <th>历史总销量</th>
                                <th>30天销量</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>

        <div class="modal-footer">

            <button class="btn btn-info" onclick="chooseProduct();">选择</button>
            <button class="btn btn-info" data-dismiss="modal2" onclick="closeViewModal2();">取消</button>
        </div>
    </div>
</div>



<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script>

    function chooseProduct() {
        var id = $("input[name='id']:checked").val();
        var name = $("#name_" + id).text();
        var code = $("#name_" + id).data("code");
        var pic = $("#name_" + id).data("pic");
        chooseProductAndClose(id, name,code,pic);
    }

    var table;
    $(document).ready(function () {

        table = $('#datatable2').dataTable({
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm2").serializeArray();
                param.push({"name": "pageSize", "value": data.length});
                if (data.start > 0) {
                    param.push({"name": "pageNumber", "value": data.start / data.length + 1});
                } else {
                    param.push({"name": "pageNumber", "value": 1});
                }

                $.ajax({
                    method: 'POST',
                    url: '${ctx}/product/list',
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
                {
                    "data": "id", "render": function (data, type, row, meta) {
                    var html = [];
                    html.push('<input style="height: auto; vertical-align: middle;margin:0;" type="radio" name="id" value="', data ,'">');
                    return html.join("");
                }
                },
                {"data": "name", "render": function (data, type, row) {
                    var html = [];
                    html.push('<span id="name_', row.id, '" data-code="'+row.code+'" data-pic="'+row.pic+'">', data, '</span>');
                    return html.join("");
                }
                },
                {"data": "id","render": function (data, type, row, meta) {
                    var html = [];
                    html.push(row.minSalePrice);
                    if(row.minSalePrice!=row.maxSalePrice){
                        html.push("-", row.maxSalePrice);
                    };
                    return html.join("");
                }},
                {"data": "stock"},
                {"data": "saleQuantity"},
                {"data": "saleQuantityIn30Day"}
            ]
        }).api();

        $('#btn-query2').on('click', function (event) {
            table.ajax.reload();
        });

    });
</script>
</body>
</html>
