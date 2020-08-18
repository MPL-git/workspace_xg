<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>知识侵权管理</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
</head>

<body>
<div class="x_panel container-fluid pp-gl">
    <div class="row content-title">
        <div class="t-title">知识侵权管理
        </div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <div class="form-group">
                <label class="col-md-1 control-label search-lable">投诉日期：</label>
                <div class="col-md-5 search-condition">
                    <input class="form-control datePicker" type="text" id="createDateBegin" name="createDateBegin"
                           data-date-format="yyyy-mm-dd HH:mm:ss">
                    <i class="picker-split">至</i>
                    <input class="form-control datePicker" type="text" id="createDateEnd" name="createDateEnd"
                           data-date-format="yyyy-mm-dd HH:mm:ss">
                </div>

                <label for="appealType" class="col-md-1 control-label search-lable">投诉类型：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="appealType" id="appealType">
                        <option value="">--请选择--</option>
                        <option value="1">商品</option>
                        <option value="2">店铺</option>
                    </select>
                </div>

                <label for="complainStatus" class="col-md-1 control-label search-lable">申诉状态：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="complainStatus" id="complainStatus">
                        <option value="">--请选择--</option>
                        <option value="-1">待申诉</option>
                        <option value="1">待审核</option>
                        <option value="2">待修改</option>
                        <option value="3">多次未按要求整改撤销</option>
                        <option value="4">已声明</option>
                        <option value="99">超期未声明</option>
                    </select>
                </div>
                <div class="col-md-3 text-center search-btn col-md-offset-6">
                    <button type="button" class="btn btn-info" id="btn-query">查询</button>
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
                        <th>投诉日期</th>
                        <th>投诉类型</th>
                        <th>权利人联系电话</th>
                        <th>投诉单状态</th>
                        <th>申诉状态</th>
                        <th>申诉截止剩余时间</th>
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
<div class="modal fade" id="viewDiv" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>

<div class="modal fade" id="setRemarksModal" tabindex="-1" role="dialog" aria-labelledby="setRemarksModal" data-backdrop="static" >
    <div id="test" class="modal-dialog" role="document" style="width: 400px;margin-top: 320px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">驳回原因</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form id="dataFrom">
                        <label class="money">原因：</label>
                        <span id="remarks"></span>
                        <br>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<!-- Bootstrap -->

<script type="text/javascript">
    function view(id) {
        $.ajax({
            url: "${ctx}/propertyRightAppeal/rightAppealDetail?id=" + id,
            type: "GET",
            success: function (data) {
                $("#viewDiv").html(data);
                $("#viewDiv").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    // 查看修改原因
    function viewReason(reason) {
        if (reason === 'null') {
            reason = '无';
        }
        $("#remarks").html(reason);
        $("#setRemarksModal").modal();
    }

    var table;

    $(document).ready(function () {

        $('.datePicker').datetimepicker(
            {
                format: 'yyyy-mm-dd',
                minView: 2,
                autoclose: true,
                language: 'zh-CN' //汉化
            }
        );

        table = $('#datatable').dataTable({
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm").serializeArray();

                param.push({"name": "pagesize", "value": data.length});
                if (data.start > 0) {
                    param.push({"name": "page", "value": data.start / data.length + 1});
                } else {
                    param.push({"name": "page", "value": 1});
                }

                $.ajax({
                    method: 'POST',
                    url: '${ctx}/propertyRightAppeal/rightAppealData',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode === '0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
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
                    "data": "createDate", "render": function (data, type, row, meta) {
                        var aptitudeExpDate = new Date(data);
                        return aptitudeExpDate.format("yyyy-MM-dd");
                    }
                },
                {"data": "appealTypeDesc"},
                {
                    "data": "mobile", "render": function (data, type, row, meta) {
                        var str = data;
                        if (row.allowShowMobile === "0") {
                            str = data.substr(0, 3) + '*****' + data.substr(8);
                        }
                        return str;
                    }
                },
                {"data": "statusDesc"},
                {
                    "data": "complainStatus", "render": function (data, type, row, meta) {
                        var str = '';
                        if (row.acceptStatus === '3') {
                            return str;
                        }
                        var resultHtml = [];
                        // 判断是否已申诉过
                        if (!row.complainId) {
                            str = row.complainEndDate > new Date().getTime() ? '待申诉' : '超期未声明';
                        } else {
                            str = row.complainEndDate > new Date().getTime() ? row.complainStatusDesc : '超期未声明';
                        }
                        resultHtml.push(str);
                        if (row.complainStatus === '2') {
                            resultHtml.push("<a href=\"javascript:viewReason(\'" + row.complainRemarks + "\');\">【查看原因】</a>");
                        }
                        return resultHtml.join("");
                    }
                },
                {
                    "data": "complainEndDate", "render": function (data, type, row, meta) {
                        var resultHtml = [];
                        if (row.acceptStatus === '3') {
                            return "";
                        }
                        if (!row.complainId || row.complainStatus === '1' || row.complainStatus === '2') {
                            var desc = countdown(data);
                            resultHtml.push('<div class="complainEndDateDiv" data-value="' + data + '">' + desc + '</div>');
                        }
                        return resultHtml.join("");
                    }
                },
                {
                    "data": "", "render": function (data, type, row, meta) {
                        var str = '';
                        if (row.acceptStatus === '3') {
                            return str;
                        }
                        var returnHtml = [];
                        // 判断是否已申诉过
                        if (!row.complainId) {
                            str = row.complainEndDate > new Date().getTime() ? '申诉' : '查看';
                        } else {
                            str = row.complainStatus === '2' && row.complainEndDate > new Date().getTime() ? '修改' : '查看';
                        }
                        returnHtml.push('<a href="javascript:view(' + row.id + ');">' + str + '</a><br>');
                        return returnHtml.join("");
                    }
                }
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });

        $("#searchKeyWord").keydown(function (e) {
            if (e.keyCode === 13) {
                table.ajax.reload();
                return false;
            }
        });

        // 倒计时
        setInterval(function () {
            $(".complainEndDateDiv").each(function () {
                var limitDate = parseInt($(this).attr("data-value"));
                var result = countdown(limitDate);
                $(this).html(result);
            });
        }, 60000);
    });

    // 倒计时
    function countdown(limitDate) {
        var nowDate = new Date().getTime();
        if (limitDate <= nowDate) {
            return "00天00时00分";
        }
        var times = (limitDate - nowDate) / 1000;
        var day = 0,
            hour = 0,
            minute = 0,
            second = 0;//时间默认值
        if (times > 0) {
            day = Math.floor(times / (60 * 60 * 24));
            hour = Math.floor(times / (60 * 60)) - (day * 24);
            minute = Math.floor(times / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(times) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        }
        if (day <= 9) day = '0' + day;
        if (hour <= 9) hour = '0' + hour;
        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        return day + "天" + hour + "小时" + minute + "分";
    }
</script>
</body>
</html>
