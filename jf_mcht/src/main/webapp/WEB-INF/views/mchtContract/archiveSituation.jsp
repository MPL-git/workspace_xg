<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>资料归档情况</title>
</head>

<body>
<div class="x_panel container-fluid pp-gl">
    <div class="row content-title">
        <div class="t-title">资料归档情况</div>
    </div>
    <div class="search-container container-fluid">
    </div>
    <div class="clearfix"></div>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <table id="depositOrderDatatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>

<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript">
    var table;

    $(document).ready(function () {
        table = $('#depositOrderDatatable').dataTable({
            "ajax": function (data, callback, settings) {
                $.ajax({
                    method: 'POST',
                    url: '${ctx}/mcht/contract/archiveSituationData',
                    data: {},
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode == '0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);
                        $(".datatable-container").find("div[class='row']").last().hide();
                    }
                });
            },
            "language": {"url": '${ctx}/static/cn.json'},
            "autoWidth": true,   // 禁用自动调整列宽
            "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
            "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
            "processing": true,  // 隐藏加载提示,自行处理
            "serverSide": false,   // 启用服务器端分页
            "searching": false,   // 禁用原生搜索
            "bSort": false,
            "paging": false,
            "bServerSide": true,
            "columns": [
                {"data": "proName", "title": "项目"},
                {"data": "sendStatusDesc", "title": "寄件状态"},
                {"data": "archiveStatusDesc", "title": "归档状态"},
                {
                    "data": "id", "title": "资料详情", "render": function (data, type, row, meta) {
                        if (!row.type || row.type === '') {
                            return '';
                        }
                        if (row.type === 1) {
                            // 预览打印最新合同
                            return '<a href="javascript:void(0);" onclick="viewContract(' + row.contractId + ');">查看</a>';
                        } else if (row.type === 2) {
                            // 查看公司详情
                            return '<a href="javascript:void(0);" onclick="viewMchtDetail();">查看</a>';
                        }
                        return '<a href="javascript:void(0);" onclick="viewBrandDetail(' + row.brandId + ');">查看</a>';
                    }
                },
                {
                    "data": "id", "title": "操作", "render": function (data, type, row, meta) {
                        var html = [];
                        if (row.type === 1) {
                            // 预览打印最新合同
                            html.push('<a href="javascript:void(0);" onclick="showInfo(' + row.contractId + ');">预览打印</a><br>');
                        }
                        html.push('<a href="javascript:;" onclick="viewAddr(' + row.contractId + ');">查看寄件地址</a>');
                        return html.join("");
                    }
                }
            ]
        }).api();

    });

    // 查看合同详情
    function viewContract(id) {
        $.ajax({
            url: "${ctx}/mcht/contract/contractDetail?contractId=" + id,
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

    // 预览打印
    function showInfo(id) {
        $.ajax({
            url: "${ctx}/mcht/contract/contractPreview?id=" + id,
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

    // 查看寄件地址
    function viewAddr() {
        var str = '<div class="modal-dialog" role="document" style="width:600px;">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
            '<span aria-hidden="true">&times;</span>' +
            '</button>' +
            '<span class="modal-title" id="exampleModalLabel2">查看寄件地址</span>' +
            '</div><div class="modal-body" style="padding-left: 30px;">' +
            '<p style="line-height: 30px;">' +
            '收件人：招商' +
            '<br/>联系电话：4008088227' +
            '<br/>收件地址：福建省厦门市思明区观音山国际商务区5号楼二楼整层' +
            '</p></div></div></div>';
        $("#viewModal").html(str);
        $("#viewModal").modal();
    }

    // 查看公司详情
    function viewMchtDetail() {
        $.ajax({
            url: "${ctx}/mcht/contract/mchtDetail",
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

    // 查看品牌详情
    function viewBrandDetail(brandId) {
        $.ajax({
            url: "${ctx}/mcht/contract/mchtBrandDetail?mchtBrandId=" + brandId,
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
