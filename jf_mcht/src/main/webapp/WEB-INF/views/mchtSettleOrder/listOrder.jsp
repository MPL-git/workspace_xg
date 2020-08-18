<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>结算单-订单明细</title>
</head>

<body>
<div class="x_panel container-fluid">
    <div class="search-container container-fluid" style="padding:20px 0 0;">
        <form class="form-horizontal" id="searchForm" action="mchtSettleOrder/export" method="post">
            <input type="hidden" name="mchtSettleOrderId" value="${mchtSettleOrderId}"/>
            <input type="hidden" name="cmd" value=""/>
            <div class="form-group">
                <label for="subOrderCode" class="col-md-1 control-label search-lable">订单号：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text"  id="subOrderCode" name="subOrderCode" >
                </div>
                <label for="productName" class="col-md-1 control-label search-lable">商品名称：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text"  id="productName" name="productName" >
                </div>
                <div class="col-md-3 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">查询</button>
                    <button type="button"  class="btn btn-info" id="btn-export">导出</button>
                </div>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>订单完成日期</th>
                        <th>订单号</th>
                        <th>商品名称</th>
                        <th>数量</th>
                        <%--SPOP--%>
                        <c:if test="${mchtSettleOrder.mchtType == 1}">
                        <th>商品结算单价</th>
                        <th>商家优惠</th>
                        </c:if>
                        <%--POP--%>
                        <c:if test="${mchtSettleOrder.mchtType == 2}">
                        <th>客户实付金额</th>
                        </c:if>
                        <th>结算金额</th>
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

    function orderDetail(id) {
        var url = "${ctx}/subOrder/subOrderView?id=" + id;
        //getContent(url);
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
                    url: '${ctx}/mchtSettleOrder/listOrder',
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
                {"data": "subOrder.completeDate", "render":function(data,type, row, meta){
                    return data.toString().substr(0, 10);
                }},
                {"data": "subOrder.subOrderCode", "render":function(data,type, row, meta){
                    var html = [];
                    html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="orderDetail(', "'"+row.subOrderId+"'", ');" >', data ,'</a>');
                    return html.join("");
                }},
                {"data": "productName"},
                {"data": "quantity"},
                <%--SPOP--%>
                <c:if test="${mchtSettleOrder.mchtType == 1}">
                {"data": "salePrice"},
                {"data": "mchtPreferential"},
                </c:if>
                <%--POP--%>
                <c:if test="${mchtSettleOrder.mchtType == 2}">
                {"data": "payAmount"},
                </c:if>
                {"data": "settleAmount"}
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            $("input[name='cmd']").val("list");
            table.ajax.reload();
        });

        $('#btn-export').on('click', function (event) {
            $("input[name='cmd']").val("export");

            $("#searchForm").submit();
        });

    });

</script>
</body>
</html>
