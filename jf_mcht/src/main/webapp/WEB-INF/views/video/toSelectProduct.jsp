<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>选择商品</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>
</head>

<body>
<div class="modal-dialog wg-xx" role="document" style="width:1010px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">选择商品</span>
        </div>
        <!-- modal body begin -->
        <div class="modal-body">

            <div class="x_panel container-fluid">
                <div class="search-container container-fluid">
                    <form class="form-horizontal" id="searchForm">
                        <div class="form-group">
                            <label for="searchProductBrandId" class="col-md-1 control-label search-lable">品牌名称：</label>
                            <div class="col-md-2 search-condition">
                                <select class="form-control" name="searchProductBrandId" id="searchProductBrandId">
                                    <option value="">--请选择--</option>
                                    <c:forEach var="productBrand" items="${productBrandList}">
                                        <option value="${productBrand.productBrandId}">${productBrand.productBrandName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label class="col-md-1 control-label search-lable">分类：</label>
                            <div class="col-md-2 search-condition">
                                <select onchange="onchangeProductType1();" class="form-control productType1-select"
                                        name="productType1" id="productType1-select">
                                </select>
                            </div>
                            <div class="col-md-2 search-condition">
                                <select onchange="onchangeProductType2();" class="form-control productType2-select"
                                        name="productType2" id="productType2-select">
                                </select>
                            </div>
                            <div class="col-md-2 search-condition">
                                <select class="form-control productType3-select" name="productType3"
                                        id="productType3-select">
                                </select>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-md-1 control-label search-lable">商品搜索：</label>
                            <div class="col-md-2 search-condition">
                                <select class="form-control" name="searchShopProductCustomTypeId"
                                        id="searchShopProductCustomTypeId">
                                    <option value="">--所属分类--</option>
                                    <c:forEach items="${shopProductCustomTypeList}" var="shopProductCustomType">
                                        <option value="${shopProductCustomType.id}">${shopProductCustomType.name}</option>
                                    </c:forEach>
                                    <option value="-1">未分类</option>
                                </select>
                            </div>

                            <div class="col-md-2 search-condition">
                                <select class="form-control" name="searchKeywrodType" id="searchKeywrodType">
                                    <option value="1">商品名称</option>
                                    <option value="2">商品货号</option>
                                    <option value="3">商品ID</option>
                                    <option value="4">商家备注</option>
                                    <option value="5">活动ID</option>
                                </select>
                            </div>
                            <div class="col-md-2 search-condition">
                                <input class="form-control nameWidth200" type="text" id="searchKeywrod"
                                       name="searchKeywrod">
                            </div>

                            <div class="col-md-3 text-center search-btn">
                                <button type="button" class="btn btn-info" id="btn-query">搜索</button>
                            </div>
                        </div>
                    </form>


                </div>
                <div class="clearfix"></div>
                <div class="x_content container-fluid">
                    <div class="row">
                        <div class="col-md-12 datatable-container">
                            <div class="datatable-caption">
                                <span class="mr" style="color:red; margin-left: 5px;">已选商品数量 <span id="selectedCount">0</span>/6</span>
                            </div>
                            <table id="datatable" class="table table-striped table-bordered dataTable no-footer"
                                   role="grid" aria-describedby="datatable_info">
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div><!-- modal body end -->

    </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script>
    var table;
    $(function () {
        reloadSelectedCount();
        initProductTypeSelect();
        initDate();
        initTable();
    });

    function initProductTypeSelect() {
        //解决select2模态框搜索问题
        $.fn.modal.Constructor.prototype.enforceFocus = function () {
        };

        $("#productType1-select").select2({
            language: "zh-CN",
            placeholder: "--请选择--",
            allowClear: true
        });
        $("#productType2-select").select2({
            language: "zh-CN",
            placeholder: "--请选择--",
            allowClear: true
        });
        $("#productType3-select").select2({
            language: "zh-CN",
            placeholder: "--请选择--",
            allowClear: true
        });
        getProductType1List(1);
    }

    function initDate() {
        $('.datePicker').datetimepicker(
            {
                minView: "month", //选择日期后，不会再跳转去选择时分秒
                format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose: true //选择日期后自动关闭
            }
        );
    }

    function initTable() {
        table = $('#datatable').dataTable({
            "aLengthMenu": [[5, 10, 20, 50], ["5", "10", "20", "50"]],
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm").serializeArray();
                param.push({"name": "pageSize", "value": data.length});
                if (data.start > 0) {
                    param.push({"name": "page", "value": data.start / data.length + 1});
                } else {
                    param.push({"name": "page", "value": 1});
                }
                $.ajax({
                    method: 'POST',
                    url: '${ctx}/video/toSelectProduct/list',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.success) {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);

                        reloadOperate(); //刷新列表操作项
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
                    "data": "id", "title": "图片/名称/商品ID", width: 260, "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<div class='td1' style='white-space:normal;word-break: break-all;'>");
                        if (row.pic != null) {
                            if (row.pic && row.pic.indexOf("http") >= 0) {//网络图片
                                html.push('<div class="width-60"><img src=' + row.pic + '></div>');
                            } else {
                                html.push('<div class="width-60"><img src="${ctx}/file_servelt' + row.pic + '"></div>');
                            }
                        }
                        html.push('<div class="width-226" style="width: 160px"><p class="h34">' + row.name + '</p><div><span style="float: left; margin: 0;">ID：' + row.code + '</span></div>');
                        html.push("</div>");
                        return html.join("");
                    }
                },
                {
                    "data": "id", width: "200", "title": "品牌/货号", "render": function (data, type, row, meta) {
                        return "<div class='td2'><div style='word-wrap:break-word;'>" + row.productBrandName + "<br>" + row.artNo + "</div></div>";
                    }
                },
                {
                    "data": "id",
                    width: "80",
                    "title": "商城价",
                    sClass: "hiddenCol",
                    "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<div class='td3'>");
                        html.push(row.mallPriceMin);
                        if (row.mallPriceMin != row.mallPriceMax) {
                            html.push("-");
                            html.push(row.mallPriceMax);
                        }
                        html.push("</div>");
                        return html.join("");
                    }
                },
                {
                    "data": "id", width: "80", "title": "活动价", "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<div class='td4'>");
                        html.push(row.salePriceMin);
                        if (row.salePriceMin != row.salePriceMax) {
                            html.push("-");
                            html.push(row.salePriceMax);
                        }
                        html.push("</div>");
                        return html.join("");
                    }
                },
                {
                    "data": "id", width: "80", "title": "SVIP折扣", "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<div class='td5'>");
                        html.push(!row.svipDiscount ? "/" : row.svipDiscount * 10);
                        html.push("</div>");
                        return html.join("");
                    }
                },
                {
                    "data": "id", width: "80", "title": "库存", "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<div class='td6'>");
                        html.push(row.stock);
                        html.push("</div>");
                        return html.join("");
                    }
                },
                {
                    "data": "id", width: "100", "title": "状态", "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<div class='td7'>");
                        html.push("<input type='hidden' class='rowProductId' value='" + row.id + "' >");
                        if (row.status === '0') {
                            html.push("下架");
                        } else {
                            html.push("上架");
                        }
                        html.push("</div>");
                        return html.join("");
                    }
                },
                {
                    "data": "id", width: "70", "title": "操作", "render": function (data, type, row, meta) {
                        var html = [];
                        if (row.selected) {
                            html.push("<a class='table-opr-btn red' href='javascript:void(0);' onclick='removeProduct(this," + row.id + ")' >删除</a>");
                        } else {
                            html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='addProduct(this," + row.id + ")' >添加</a>");
                        }
                        return html.join("");
                    }
                }
            ]
        }).api();

        $('#btn-query').on('click', function () {
            table.ajax.reload();
        });
    }

    //添加商品
    function addProduct(obj, productId) {
        if (selectedProductMap.size >= 6) {
            swal("每个视频最多添加6个商品");
            return;
        }

        var $tr = $(obj).closest("tr");
        $(obj).closest("td").html("<a class='table-opr-btn red' href='javascript:void(0);' onclick='removeProduct(this," + productId + ")' >删除</a>");

        var product = {};
        product.id = productId;
        product.selected = true;
        product.td1 = $tr.find(".td1").html();
        product.td2 = $tr.find(".td2").html();
        product.td3 = $tr.find(".td3").html();
        product.td4 = $tr.find(".td4").html();
        product.td5 = $tr.find(".td5").html();
        product.td6 = $tr.find(".td6").html();
        product.td7 = $tr.find(".td7").html();
        product.td8 = '<a class="table-opr-btn red" href="javascript:void(0);" onclick="deleteProduct(this,' + productId + ')">删除</a>';
        selectedProductMap.set(productId, product);
        reloadSelectedCount();
        reloadSelectedTable();
    }

    //移除商品
    function removeProduct(obj, productId) {
        selectedProductMap.delete(productId);
        $(obj).closest("td").html("<a class='table-opr-btn' href='javascript:void(0);' onclick='addProduct(this," + productId + ")' >添加</a>");
        reloadSelectedCount();
        reloadSelectedTable();
    }

    //刷新列表操作项 及 已选商品数
    function reloadOperate() {
        var $trs = $("#datatable tbody").find("tr");
        for (var i = 0; i < $trs.length; i++) {
            var productId = $trs.eq(i).find(".rowProductId").val();
            var $operate = $trs.eq(i).find(".table-opr-btn").closest("td");
            if (selectedProductMap.has(parseInt(productId))) { //已添加
                $operate.html("<a class='table-opr-btn red' href='javascript:void(0);' onclick='removeProduct(this," + productId + ")' >删除</a>");
            } else {
                $operate.html("<a class='table-opr-btn' href='javascript:void(0);' onclick='addProduct(this," + productId + ")' >添加</a>")
            }
        }

        reloadSelectedCount();
    }

    //刷新已选中的列表
    function reloadSelectedTable() {
        var html = [];
        selectedProductMap.forEach(function (value, key) {
            html.push("<tr role='row'>");
            html.push("<td>" + value.td1 + "</td>");
            html.push("<td>" + value.td2 + "</td>");
            html.push("<td>" + value.td3 + "</td>");
            html.push("<td>" + value.td4 + "</td>");
            html.push("<td>" + value.td5 + "</td>");
            html.push("<td>" + value.td6 + "</td>");
            html.push("<td>" + value.td7 + "</td>");
            html.push("<td>" + value.td8 + "</td>");
            html.push("</tr>");
        });
        $("#selectedProductBody").html(html.join(""));
    }

    //刷新已选商品数
    function reloadSelectedCount() {
        $("#selectedCount").text(selectedProductMap.size);
    }
</script>
</body>
</html>
