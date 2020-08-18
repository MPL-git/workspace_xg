<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>订单结算管理</title>
</head>

<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="t-title">订单结算管理</div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <div class="form-group">
                <label for="confirmStatus" class="col-md-1 control-label search-lable">确认状态：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="confirmStatus" id="confirmStatus">
                        <option value="">--请选择--</option>
                        <option value="1">待商家确认</option>
                        <option value="2">待平台确认</option>
                        <option value="3">已确认</option>
                    </select>
                </div>
                <div class="col-md-3 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">查询</button>
                </div>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container at-table">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>结算日期（订单完成日期）</th>
                        <th>结算ID</th>
                        <th>结算单金额</th>
                        <th>抵缴保证金</th>
                        <th>应结金额</th>
                        <th>确认状态</th>
                        <th>付款状态</th>
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

    <style>
        .pop-notice {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1111;
            width: 100%;
            height: 100%;
        }
        .pop-notice-con {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 665px;
            height: 275px;
            margin: -137.5px 0 0 -332.5px;
            background: #fff;
            border: 1px solid #ddd;
            border-radius: 2px;
            box-shadow: 0 0 10px #ccc;
            font-size: 14px;
        }
        .pop-notice-article {
            height: 160px;
            overflow: auto;
            padding: 0 27px;
            color: #333;
            line-height: 20px;
        }
        .pop-notice-article h2 {
            line-height: 34px;
            margin-bottom: 15px;
            font-size: 18px;
            font-weight: bold;
            color: #333;
            text-align: center;
        }
        .pop-notice-article p {
            position: relative;
            padding-left: 22px;
            word-spacing: 3px;
        }
        .pop-notice-article p:before {
            position: absolute;
            top: 0;
            left: 0;
            width: 22px;
            content: attr(data-order);
        }
        .pop-notice-header {
            position: relative;
            height: 30px;
        }
        .pop-notice-header a {
            position: absolute;
            top: 0;
            right: 0;
            width: 30px;
            height: 30px;
            background: url("${pageContext.request.contextPath}/static/images/pop-notice-close.png") no-repeat center center;
        }
        .pop-notice-footer a {
            display: block;
            width: 90px;
            height: 32px;
            line-height: 32px;
            margin: 0 auto;
            background: #3c9eff;
            color: #fff;
            text-align: center;
            cursor: pointer;
        }
    </style>
    <div class="pop-notice">
        <div class="pop-notice-con">
            <div class="pop-notice-header">
                <a onclick="popNoticeClose()"></a>
            </div>
            
            <div class="pop-notice-article">
                <h2>关于结算货款方式一律使用公对公账户结算的通知</h2>
                <div>
                    <p data-order="1、">根据有关部门的相关规定，结合平台实际，将在 2018年3月31日停止私户结算货款，如有使用对私户结算的商家，请及时改为公对公的结算方式，具体详细事情可咨询醒购招商部门。</p>
                </div>
            </div>
            <div class="pop-notice-footer">
            <a onclick="popNoticeClose()">知道了</a>
        </div>
    </div>

<!-- Bootstrap -->
<script src="${ctx}/static/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/dateUtil.js"></script>
<script>

    function detail(id) {
        var url = "${ctx}/mchtSettleOrder/detail?id=" + id;
        getContent(url);
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
                    url: '${ctx}/mchtSettleOrder/list',
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
                {"data": "id", "render":function(data,type, row, meta){
                    var html = [];
                    html.push(row.beginDate.toString().substr(0, 10));
                    html.push(' 到 ');
                    html.push(row.endDate.toString().substr(0, 10));
                    return html.join("");
                }},
                {"data": "id"},
                {"data": "settleAmount"},
                {"data": "depositAmount"},
                {"data": "needSettleAmount"},
                {"data": "confirmStatus", "render":function(data,type, row, meta){
                    if(data == "1"){
                        return "待商家确认"
                    }else if(data == "2"){
                        return "待平台确认";
                    }else if(data == "3"){
                        return "已确认";
                    }else{
                        return "未知状态";
                    }
                }},
                {"data": "payStatus", "render":function(data,type, row, meta){
                    if(data == "1" || data == "2"){
                        return "未付款"
                    }else if(data == "3" || data == "4"){
                        return "已结清";
                    }else{
                        return "未知状态";
                    }
                }},
                {"data": "id", "render": function (data, type, row, meta) {
                    var html = [];
                    html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="detail(', "'"+row.id+"'", ');" >【详情】</a>');
                    return html.join("");
                }}
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });

    });

        // 公告
        function popNotice() {
            var win_ls = window.sessionStorage;
            console.log(win_ls.getItem('pop-notice'))

            if (!win_ls.getItem('pop_notice_order')) {
                win_ls.setItem('pop_notice_order', 1);
                $('.pop-notice').fadeIn();
            }
        }

        function popNoticeClose() {
            $('.pop-notice').fadeOut();
        }

        // popNotice();
</script>
</body>
</html>