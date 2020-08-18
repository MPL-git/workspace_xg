<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>订单管理</title>
    <link href="${pageContext.request.contextPath}/static/css/subOrder.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
    <style type="text/css">
        .color-1 {
            color: #9D999D;
        }

        .color-2 {
            color: #FC0000;
        }

        .color-3 {
            color: #EFD104;
        }

        .color-4 {
            color: #00FC28;
        }

        .color-5 {
            color: #0351F7;
        }

        .color-6 {
            color: #DF00FB;
        }

        .video_box {
            z-index: 2222;
            display: block;
        }

        .black_box {
            background: #000;
            opacity: 0.6;
            left: 0;
            top: 0;
            z-index: 1111;
            position: fixed;
            height: 100%;
            width: 100%;
        }

        .video_close {
            position: absolute;
            top: -14px;
            right: -12px;
        }

        .test-box .more {
            position: relative;
            color: blue;
            cursor: pointer;
        }

        .test-box .more:hover:after {
            z-index: 999;
            color: white;
            position: absolute;
            top: calc(100% + 10px);
            left: 50%;
            content: attr(data-more);
            padding: 2px 10px;
            background: rgba(0, 0, 0, .5);
            border-radius: 5px;
            word-break: keep-all;
            transform: translateX(-50%);
        }

        .test-box .more:before {
            z-index: 999;
            display: none;
            position: absolute;
            top: 100%;
            left: 0;
            right: 0;
            content: '';
            width: 0;
            margin: 0 auto;
            border-bottom: 10px solid rgba(0, 0, 0, .5);
            border-left: 5px solid transparent;
            border-right: 5px solid transparent;
        }

        .test-box .more:hover:before {
            display: block;
        }

    </style>
</head>

<body>
<div class="x_panel container-fluid dd-gl">
    <div class="row content-title">
        <div class="t-title">
            订单管理
        </div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <input type="hidden" name="search_remarksColor" id="search_remarksColor">
            <input type="hidden" name="status" value="${status}" id="status"/>
            <div class="form-group">
                <label for="productBrand" class="col-md-1 control-label search-lable">商品ID：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text" id="search_productId" name="search_productId">
                </div>

                <label for="productBrand" class="col-md-1 control-label search-lable">品牌名称：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="search_brandId" id="search_brandId">
                        <option value="">--请选择--</option>
                        <c:forEach var="productBrand" items="${productBrandList}">
                            <option value="${productBrand.id}">${productBrand.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <label for="productBrand" class="col-md-1 control-label search-lable">订单状态：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="search_status" id="search_status">
                        <option value="-1">不限状态</option>
                        <c:forEach var="subOrderStatus" items="${subOrderStatusList}">
                            <option value="${subOrderStatus.statusValue}">${subOrderStatus.statusDesc}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="productBrand" class="col-md-1 control-label search-lable">活动ID：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text" id="search_activityId" name="search_activityId">
                </div>

                <label for="productBrand" class="col-md-1 control-label search-lable">快递单号：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text" id="search_expressNo" name="search_expressNo">
                </div>

                <label for="productBrand" class="col-md-1 control-label search-lable">审核时间：</label>
                <div class="col-md-5 search-condition">
                    <input class="form-control datePicker" type="text" id="payDateBegin" name="payDateBegin"
                           data-date-format="yyyy-mm-dd HH:mm:ss">
                    <i class="picker-split">-</i>
                    <input class="form-control datePicker" type="text" id="payDateEnd" name="payDateEnd"
                           data-date-format="yyyy-mm-dd HH:mm:ss">
                </div>
            </div>

            <div class="form-group">
                <label for="activityStatus" class="col-md-1 control-label search-lable">收货人：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text" id="search_receiverName" name="search_receiverName">
                </div>

                <label class="col-md-1 control-label search-lable">联系电话：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text" id="search_receiverPhone" name="search_receiverPhone">
                </div>

                <label class="col-md-1 control-label search-lable">订单编号：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text" id="search_subOrderCode" name="search_subOrderCode">
                </div>

            </div>

            <div class="form-group">
                <label class="col-md-1 control-label search-lable">商品名称：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text" id="search_productName" name="search_productName">
                </div>

                <label class="col-md-1 control-label search-lable">商品货号：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control" type="text" id="search_productArtNo" name="search_productArtNo">
                </div>

                <div class="col-md-3 text-center search-btn">
                    <button type="button" class="btn btn-info" id="btn-query">搜索</button>
                    <button type="button" class="btn btn-info" id="btn-out">导出</button>
                </div>
            </div>

        </form>
    </div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation" style="width: 105px;" <c:if test="${status=='-2'}">class="active"</c:if>><a href="#"
                                                                                                            role="tab"
                                                                                                            data-toggle="tab"
                                                                                                            onclick="list('-2');">近一个月订单</a>
        </li>
        <li role="presentation" style="width: 105px;" <c:if test="${status=='0'}">class="active"</c:if>><a href="#"
                                                                                                           role="tab"
                                                                                                           data-toggle="tab"
                                                                                                           onclick="list('0');">待客户付款</a>
        </li>
        <li role="presentation" style="width: 105px;" <c:if test="${status=='1'}">class="active"</c:if>><a href="#"
                                                                                                           role="tab"
                                                                                                           data-toggle="tab"
                                                                                                           onclick="list('1');">待商家发货</a>
        </li>
        <li role="presentation" style="width: 105px;" <c:if test="${status=='2'}">class="active"</c:if>><a href="#"
                                                                                                           role="tab"
                                                                                                           data-toggle="tab"
                                                                                                           onclick="list('2');">商家已发货</a>
        </li>
        <li role="presentation" style="width: 105px;" <c:if test="${status=='6'}">class="active"</c:if>><a href="#"
                                                                                                           role="tab"
                                                                                                           data-toggle="tab"
                                                                                                           onclick="list('6');">买家已收货</a>
        </li>
        <li role="presentation" style="width: 105px;" <c:if test="${status=='3'}">class="active"</c:if>><a href="#"
                                                                                                           role="tab"
                                                                                                           data-toggle="tab"
                                                                                                           onclick="list('3');">订单完成</a>
        </li>
        <li role="presentation" style="width: 105px;" <c:if test="${status=='-3'}">class="active"</c:if>><a href="#"
                                                                                                            role="tab"
                                                                                                            data-toggle="tab"
                                                                                                            onclick="list('-3');">关闭的订单</a>
        </li>
        <li role="presentation" style="width: 105px;" id="allOrder" <c:if test="${status=='-1'}">class="active"</c:if>>
            <a href="#" role="tab" data-toggle="tab" onclick="list('-1');">全部订单</a></li>
    </ul>

    <!--   Tab panes -->
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <div class="datatable-caption" id="remarksBtnDiv">
                    <c:if test="${status=='1'}">
                        <div style="padding:0px 10px;">
                            <span style="color:red;">订单超过72小时未发货，系统将自动判断为缺货违规</span>
                        </div>
                        <br>
                    </c:if>
                    <span class="mr">
								<input type="checkbox" class="checkAll" onclick="checkAll(this,1);" value="ccc">全选
							</span>
                    <button class="btn btn-all" id="showRemarksDiv">批量备注</button>
                </div>
                <table id="datatable" class="o table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>商品</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>是否售后</th>
                        <th>订单状态</th>
                        <th>实付金额</th>
                        <th class="p-dropdown">
                            操作
                            <div class="dropdown">
                                <span data-toggle="dropdown" class="dropdown-toggle glyphicon glyphicon-flag color-1"
                                      aria-hidden="true"></span>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:;" name="flagColor" data-remarkscolor="1"><span
                                            class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a></li>
                                    <li><a href="javascript:;" name="flagColor" data-remarkscolor="2"><span
                                            class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a></li>
                                    <li><a href="javascript:;" name="flagColor" data-remarkscolor="3"><span
                                            class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a></li>
                                    <li><a href="javascript:;" name="flagColor" data-remarkscolor="4"><span
                                            class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a></li>
                                    <li><a href="javascript:;" name="flagColor" data-remarkscolor="5"><span
                                            class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a></li>
                                    <li><a href="javascript:;" name="flagColor" data-remarkscolor="6"><span
                                            class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a></li>
                                </ul>
                            </div>
                        </th>
                    </tr>
                    </thead>
                    <tbody id="tbody1">

                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<!-- 	订单详情 -->

<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>

<!-- 	订单备注 -->

<div class="modal fade" id="remarksModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>

<!--弹出div-->
<div class="video_box"
     style="position:fixed; width:600px; height:180px; top:50%; left:50%;margin: -90px 0 0 -300px; display: none;"
     id="subOrderRemarksDiv">
    <a href="javascript:;" id="closeVideo" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                订单备注
                <span style="display: inline-block;float: right;" id="remarksColors">
							<a href="javascript:;" name="remarksColor" data-remarkscolor="1"><span
                                    class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="2"><span
                                    class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="3"><span
                                    class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="4"><span
                                    class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="5"><span
                                    class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a>
							<a href="javascript:;" name="remarksColor" data-remarkscolor="6"><span
                                    class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a>
					</span>
            </h3>

        </div>
        <div class="panel-body">
            <textarea class="form-control" rows="3" id="remarks"
                      style="width: 100%;resize: vertical;margin-bottom: 10px;">${remarks}</textarea>
            <span id="selectedRemark">
				旗帜设置为:
				<c:if test="${remarksColor eq '1'}">
                    <span class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
                </c:if>
				<c:if test="${remarksColor eq '2'}">
                    <span class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
                </c:if>
				<c:if test="${remarksColor eq '3'}">
                    <span class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
                </c:if>
				<c:if test="${remarksColor eq '4'}">
                    <span class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
                </c:if>
				<c:if test="${remarksColor eq '5'}">
                    <span class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
                </c:if>
				<c:if test="${remarksColor eq '6'}">
                    <span class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span>
                </c:if>
			</span>
            <div style="float:right;" id="remarksBtnDiv">
                <input type="hidden" id="subOrderIds" value="${ids}">
                <input type="hidden" id="remarksColor" value="${remarksColor}">
                <button type="button" class="btn btn-default" id="batchSaveRemarks">提交备注</button>
            </div>
        </div>
    </div>

</div>
<!--弹出Div End-->

<!--弹出立即发货Div-->
<div class="video_box" style="position:fixed; width:800px; max-height:400px; top:35%; left:35%; display: none;"
     id="deliveryDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                立即发货
            </h3>
        </div>

        <form class="form-horizontal" role="form" style="margin-top: 10px;overflow-y: auto;" onsubmit="return false;">
            <input type="hidden" id="deliverySubOrderId">
            <div class="form-group" style="padding-left: 30px;">
                <label class="col-md-3 control-label">快递名称<span style="color: red;">*</span>：</label>
                <div class="col-md-7">
                    <select class="form-control" id="expressId" name="expressId">
                        <option value="">--请选择--</option>
                        <c:forEach var="express" items="${expressList}">
                            <option value="${express.id}">${express.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group" style="padding-left: 30px;">
                <label class="col-md-3 control-label">快递单号<span style="color: red;">*</span>：</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="expressNo" name="expressNo">
                </div>
            </div>
            <div class="form-group" style="padding-left: 30px;">
                <label class="col-md-3 control-label">选择发货商品<span style="color: red;">*</span>：</label>
                <div class="col-md-7">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <input type="checkbox">耐克 测试单如有下单概不发货 705474<br>蓝色705474-402_40 （未发货）
                        </li>
                        <li class="list-group-item">免费 Window 空间托管</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">图像的数量</li>
                        <li class="list-group-item">24*7 支持</li>
                    </ul>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-md-7">
                    <button type="button" class="btn btn-default" id="delivery">立即发货</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!--弹出div End-->

<!--弹出修改快递单号Div-->
<div class="video_box" style="position:fixed; width:320px; height:170px; top:35%; left:45%; display: none;"
     id="editExpressDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                修改快递单号
            </h3>
        </div>

        <form class="form-horizontal" role="form" style="margin-top: 10px;">
            <input type="hidden" id="editExpressSubOrderId">
            <div class="form-group" style="padding-left: 30px;">
                <label class="col-md-3 control-label">快递名称<span style="color: red;">*</span>：</label>
                <div class="col-md-7">
                    <select class="form-control" id="editExpressId" name="editExpressId">
                        <option value="">--请选择--</option>
                        <c:forEach var="express" items="${expressList}">
                            <option value="${express.id}">${express.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group" style="padding-left: 30px;">
                <label class="col-md-3 control-label">快递单号<span style="color: red;">*</span>：</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" id="editExpressNo" name="editExpressNo">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-4 col-md-7">
                    <button type="button" class="btn btn-default" id="commit">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!--弹出div End-->
<div class="black_box" style="display: none;"></div>

<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/clipboard.min.js"></script>

<script>
    function settime(countdown) {
        if (countdown > 0) {
            countdown--;
        }
        setTimeout(function () {
            settime(countdown);
        }, 1000);
    }

    function checkAll(input, index) {
        if ($(input).prop("checked")) {
            $("#tbody" + index).find("input[name='subOrderId']").each(function () {
                $(this).prop("checked", true);
            });
        } else {
            $("#tbody" + index).find("input[name='subOrderId']").each(function () {
                $(this).prop("checked", false);
            });
        }
    }

    function expressNoFun(expressNo) {
        if (expressNo.length > 14) {
            return expressNo.substring(0, 13) + "..." + expressNo.substring(expressNo.length - 1, expressNo.length);
        } else {
            return expressNo;
        }
    }

    function receiverAddressFun(receiverAddress) {
        if (receiverAddress.length > 7) {
            return receiverAddress.substring(0, 6) + "..." + receiverAddress.substring(receiverAddress.length - 1, receiverAddress.length);
        } else {
            return receiverAddress;
        }
    }

    var table;
    $(document).ready(function () {
        <c:if test="${not empty status}">
        var status = ${status};
        if (status == -1 || status == -2) {
            $("#search_status").find("option[value='-1']").attr("selected", true);
        } else if (status == -3) {
            $("#search_status").find("option[value='5']").attr("selected", true);
        } else {
            $("#search_status").find("option[value='" + status + "']").attr("selected", true);
        }
        </c:if>

        $('.datePicker').datetimepicker(
            {
                format: 'yyyy-mm-dd hh:ii:ss',
                minView: 0,
                minuteStep: 1,
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
                    url: '${ctx}/subOrder/getSubOrderList',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode == '0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);

                        $("input[name='subOrderId']").on('click', function () {
                            if (!$(this).prop("checked")) {
                                $(this).closest("table").find("input[name='checkAll']").prop("checked", false);
                            } else {
                                var checkedLength = $(this).closest("tbody").find("input[name='subOrderId']:checked").length;
                                var totalLength = $(this).closest("tbody").find("input[name='subOrderId']").length;
                                if (checkedLength == totalLength) {
                                    $(this).closest("table").find("input[name='checkAll']").prop("checked", true);
                                }
                            }
                        });

                        $("#showRemarksDiv").unbind('click').on('click', function () {
                            if ($("input[name='subOrderId']:checked").length == 0) {
                                swal("请先选中要备注的订单");
                                return false;
                            }
                            var idsArray = [];
                            $("input[name='subOrderId']:checked").each(function () {
                                idsArray.push($(this).val());
                            });
                            var ids = idsArray.join(",");
                            viewSubOrderRemarks(ids);
                        });

                        $("td[name='operation1']").each(function () {
                            var flag;
                            $(this).parent().find("td").first().find("div[name='customerServiceOrderTypeAndStatus']").each(function () {
                                if ($(this).text() == "退款单进行中") {
                                    flag = true;
                                    return false;
                                }
                            });
                            if (!flag) {
                                var nowDate = new Date();
                                var nowTime = nowDate.getTime();
                                var date = $(this).data("statusdate");
                                var subOrderId = $(this).data("suborderid");
                                var endSendTime = date;
                                var isOver = false;
                                if (endSendTime > nowTime) {
                                    var diff = endSendTime - nowTime;//时间差的毫秒数
                                    countdown = diff / 1000;//秒数
                                } else {
                                    isOver = true;
                                    var diff = nowTime - endSendTime;//时间差的毫秒数
                                    countdown = diff / 1000;//秒数
                                }
                                settime(countdown);
                                var hour = Math.round(countdown / 3600);
                                if (!isOver) {
                                    if (countdown <= 3600) {
                                        countdownDesc = "不足1小时";
                                    } else {
                                        countdownDesc = "倒计时：" + hour + "小时";
                                    }
                                } else {
                                    if (countdown <= 3600) {
                                        countdownDesc = "超时1小时";
                                    } else {
                                        countdownDesc = "超时：" + hour + "小时";
                                    }
                                }
                                var swalClass = "";
                                if (isOver) {
                                    swalClass = "color:red;";
                                }
                                var html = [];
                                html.push('<div class="row"><div><div><a href="javascript:;" onclick="showDeliveryDiv(' + subOrderId + ');">立即发货</a></div><div><span style="' + swalClass + '">' + countdownDesc + '</span></div></div></div>');
                                html.push('<div class="row"><a href="javascript:;" onclick="toMarkedOutOfStock(' + subOrderId + ');">标记缺货</a></div>');
                                $(this).html(html.join(""));
                            }
                        });

                        $("td[name='operation2']").each(function () {
                            var subOrderId = $(this).data("suborderid");
                            var supplyAgain = $(this).data("supplyagain");
                            if (supplyAgain == "1") {
                                var html = [];
                                html.push('<div class="row"><a href="javascript:;" onclick="toSupplyAgain(' + subOrderId + ');">补发</a></div>');
                                $(this).html(html.join(""));
                            }
                        });

                        $("a[name='viewDirectCompensationOrder']").on('click', function () {
                            var subOrderId = $(this).data("suborderid");
                            $.ajax({
                                url: "${ctx}/customerServiceOrder/customerServiceOrderView?subOrderId=" + subOrderId + "&serviceType=D",
                                type: "GET",
                                success: function (data) {
                                    $("#viewModal").html(data);
                                    $("#viewModal").modal();
                                },
                                error: function () {
                                    swal('页面不存在');
                                }
                            });
                        });

                        $("a[name='editExpress']").on('click', function () {
                            $("#editExpressId").val("");
                            $("#editExpressNo").val("");
                            $("#editExpressDiv").show();
                            $(".black_box").show();
                            var subOrderId = $(this).data("id");
                            $("#editExpressSubOrderId").val(subOrderId);
                        });


                    }
                });
            },
            "language": {"url": '${ctx}/static/cn.json'},
            "autoWidth": false,   // 禁用自动调整列宽
            "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
            "processing": true,  // 隐藏加载提示,自行处理
            "serverSide": true,   // 启用服务器端分页
            "searching": false,   // 禁用原生搜索
            "bSort": false,
            "bServerSide": true,

            "rowCallback": function (row, data, index) {
                var subOrder = data;
                var createDate = new Date(subOrder.createDate).format("yyyy-MM-dd hh:mm:ss");
                var activityIds = subOrder.activityIds;
                if (!activityIds) {
                    activityIds = "";
                }
                var receiverPhone = subOrder.receiverPhone;
                var status = subOrder.status;
                var statusDesc;
                if (status == "0") {
                    statusDesc = "待付款";
                } else if (status == "1") {
                    statusDesc = "待发货";
                } else if (status == "2") {
                    statusDesc = "待收货";
                } else if (status == "3") {
                    statusDesc = "完成";
                } else if (status == "4") {
                    statusDesc = "订单已取消";
                } else if (status == "5") {
                    statusDesc = "关闭";
                } else if (status == "6") {
                    statusDesc = "已收货";
                }
                var paymentStr = "";
                if (subOrder.paymentId == 1 || subOrder.paymentId == 6) {//支付宝
                    paymentStr = "支付宝支付";
                } else if (subOrder.paymentId == 2 || subOrder.paymentId == 4 || subOrder.paymentId == 5) {//微信
                    paymentStr = "微信支付";
                } else if (subOrder.paymentId == 3) {//银联
                    paymentStr = "银联";
                }
                if (status != "1" && receiverPhone) {
                    receiverPhone = receiverPhone.replace(/^(\d{3})\d{5}(\d+)/, "$1****$2");
                }
                if (!receiverPhone) {
                    receiverPhone = "";
                }
                var receiverName = subOrder.receiverName;
                if (!receiverName) {
                    receiverName = "";
                }
                var receiverAddress = subOrder.receiverAddress;
                if (!receiverAddress) {
                    receiverAddress = "";
                }
                var expressNo = subOrder.expressNo;
                if (!expressNo) {
                    expressNo = "无";
                }
                var expressName = subOrder.expressName;
                if (!expressName) {
                    expressName = "无";
                }
                var remarksColor = subOrder.remarksColor;
                var remarksColorHtml = "";
                if (remarksColor == "1") {
                    remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks(' + subOrder.id + ');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span></a>';
                } else if (remarksColor == "2") {
                    remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks(' + subOrder.id + ');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span></a>';
                } else if (remarksColor == "3") {
                    remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks(' + subOrder.id + ');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span></a>';
                } else if (remarksColor == "4") {
                    remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks(' + subOrder.id + ');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span></a>';
                } else if (remarksColor == "5") {
                    remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks(' + subOrder.id + ');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span></a>';
                } else if (remarksColor == "6") {
                    remarksColorHtml = '<a href="javascript:;" onclick="viewSubOrderRemarks(' + subOrder.id + ');"><span style="float:right;margin: 8px 5px 0 0;" class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span>';
                }

                var rowtext = [];
                rowtext.push('<td colspan="7">');
                var memberRemindDesc = "";
                var memberRemindCount = subOrder.memberRemindCount;
                if (memberRemindCount && memberRemindCount > 0) {
                    memberRemindDesc = '<span style="color:red;">（买家发货已提醒：' + memberRemindCount + '次）</span>';
                }

                var blackMemberRemark = "";
                //黑名单订单
                if (subOrder.memberStatus == 'P') {
                    blackMemberRemark = '<span style="color:red;">（订单异常，请谨慎发货）</span>';
                }

                rowtext.push('<div class="mt-10"><div class="order info1"><input type="checkbox" name="subOrderId" value="' + subOrder.id + '"><span>订单编号：' + subOrder.subOrderCode + '</span><span>支付方式：' + paymentStr + '</span><span>下单时间：' + createDate + '</span>' + blackMemberRemark + memberRemindDesc + '' + remarksColorHtml + '</div></div>');
                var deliveryDate = new Date(subOrder.deliveryDate);//订单发货时间
                var yesterday = new Date(subOrder.yesterday);//昨天时间

                var deliveryDateAfter = new Date(subOrder.deliveryDateAfter);
                var deliveryLastDateAfter = new Date(subOrder.deliveryLastDateAfter);
                var nowDate = new Date();

                if(deliveryLastDateAfter > deliveryDateAfter){
                    if( status == "2" && nowDate <= deliveryLastDateAfter){
                        rowtext.push('<div class="order info2 test-box"><span class="more" data-more="' + receiverName + '">收货人：' + receiverAddressFun(receiverName) + '</span><span>电话：' + receiverPhone + '</span>地址：<span class="more" data-more="' + receiverAddress + '">' + receiverAddressFun(receiverAddress) + '<a href="javascript:void(0);" class="copyAddress" data-clipboard-action="copy" data-clipboard-text="' + receiverAddress + '" >【复制】</a></span><span>快递名称：' + expressName + '</span><span class="more" data-more="' + expressNo + '">单号：' + expressNoFun(expressNo) + '</span><a href="javascript:;" onclick="toEditDelivery(' + subOrder.id + ');">【修改】</a></div>');
                    } else {
                        rowtext.push('<div class="order info2 test-box"><span class="more" data-more="' + receiverName + '">收货人：' + receiverAddressFun(receiverName) + '</span><span>电话：' + receiverPhone + '</span>地址：<span class="more" data-more="' + receiverAddress + '">' + receiverAddressFun(receiverAddress) + '<a href="javascript:void(0);" class="copyAddress" data-clipboard-action="copy" data-clipboard-text="' + receiverAddress + '" >【复制】</a></span><span>快递名称：' + expressName + '</span><span class="more" data-more="' + expressNo + '">单号：' + expressNoFun(expressNo) + '</span></div>');
                    }
                }else if(deliveryLastDateAfter < deliveryDateAfter){
                    if( status == "2" && nowDate <= deliveryDateAfter){
                        rowtext.push('<div class="order info2 test-box"><span class="more" data-more="' + receiverName + '">收货人：' + receiverAddressFun(receiverName) + '</span><span>电话：' + receiverPhone + '</span>地址：<span class="more" data-more="' + receiverAddress + '">' + receiverAddressFun(receiverAddress) + '<a href="javascript:void(0);" class="copyAddress" data-clipboard-action="copy" data-clipboard-text="' + receiverAddress + '" >【复制】</a></span><span>快递名称：' + expressName + '</span><span class="more" data-more="' + expressNo + '">单号：' + expressNoFun(expressNo) + '</span><a href="javascript:;" onclick="toEditDelivery(' + subOrder.id + ');">【修改】</a></div>');
                    } else {
                        rowtext.push('<div class="order info2 test-box"><span class="more" data-more="' + receiverName + '">收货人：' + receiverAddressFun(receiverName) + '</span><span>电话：' + receiverPhone + '</span>地址：<span class="more" data-more="' + receiverAddress + '">' + receiverAddressFun(receiverAddress) + '<a href="javascript:void(0);" class="copyAddress" data-clipboard-action="copy" data-clipboard-text="' + receiverAddress + '" >【复制】</a></span><span>快递名称：' + expressName + '</span><span class="more" data-more="' + expressNo + '">单号：' + expressNoFun(expressNo) + '</span></div>');
                    }
                }

      /*          if (status == "2" && deliveryDate > yesterday) {
                    rowtext.push('<div class="order info2 test-box"><span class="more" data-more="' + receiverName + '">收货人111：' + receiverAddressFun(receiverName) + '</span><span>电话：' + receiverPhone + '</span>地址：<span class="more" data-more="' + receiverAddress + '">' + receiverAddressFun(receiverAddress) + '<a href="javascript:void(0);" class="copyAddress" data-clipboard-action="copy" data-clipboard-text="' + receiverAddress + '" >【复制】</a></span><span>快递名称：' + expressName + '</span><span class="more" data-more="' + expressNo + '">单号：' + expressNoFun(expressNo) + '</span><a href="javascript:;" onclick="toEditDelivery(' + subOrder.id + ');">【修改】</a></div>');
                } else {
                    rowtext.push('<div class="order info2 test-box"><span class="more" data-more="' + receiverName + '">收货人：' + receiverAddressFun(receiverName) + '</span><span>电话：' + receiverPhone + '</span>地址：<span class="more" data-more="' + receiverAddress + '">' + receiverAddressFun(receiverAddress) + '<a href="javascript:void(0);" class="copyAddress" data-clipboard-action="copy" data-clipboard-text="' + receiverAddress + '" >【复制】</a></span><span>快递名称：' + expressName + '</span><span class="more" data-more="' + expressNo + '">单号：' + expressNoFun(expressNo) + '</span></div>');
                }*/

                rowtext.push('<table class="order-info3">');
                rowtext.push('<tr>');
                rowtext.push('<td>');
                rowtext.push('<table>');
                var orderDtlCustoms = subOrder.orderDtlCustoms;
                for (var i = 0; i < orderDtlCustoms.length; i++) {
                    rowtext.push('<tr>');

                    var orderDtlCustom = orderDtlCustoms[i];
                    var customerServiceType = orderDtlCustom.customerServiceType;
                    var customerServiceStatusDesc = orderDtlCustom.customerServiceStatusDesc;
                    var activityAreaSource = orderDtlCustom.activityAreaSource;
                    var activityAreaType = orderDtlCustom.activityAreaType;
                    var singleProductActivityType = orderDtlCustom.singleProductActivityType;
                    var preferentialType = orderDtlCustom.preferentialType;
                    var activityTypeDesc = "";
                    if (singleProductActivityType) {
                        activityTypeDesc = "活动：" + orderDtlCustom.singleProductActivityTypeDesc;
                    } else if (activityAreaSource && activityAreaType) {
                        if (activityAreaSource == "1" && activityAreaType == "1") {
                            activityTypeDesc = "活动：官方会场（ID：" + orderDtlCustom.activityId + "）";
                        } else if (activityAreaSource == "2") {
                            activityTypeDesc = "活动：品牌特卖（ID：" + orderDtlCustom.activityId + "）";
                        }
                        if (activityAreaType == "3") {
                            activityTypeDesc = "活动：推广会场（ID：" + orderDtlCustom.activityId + "）";
                        } else if (activityAreaType == "6") {
                            activityTypeDesc = "活动：单品爆款";
                        } else if (activityAreaType == "7") {
                            activityTypeDesc = "活动：新用户专享";
                        } else if (activityAreaType == "8") {
                            activityTypeDesc = "活动：秒杀";
                        }
                    } else if(preferentialType == '12'){
                        activityTypeDesc = "活动：积分转盘";
                    }else {
                        activityTypeDesc = "活动：商城";
                    }
                    if (!customerServiceType) {
                        customerServiceType = "";
                    }
                    if (!customerServiceStatusDesc) {
                        customerServiceStatusDesc = "";
                    }

                    rowtext.push('<td width="342" class="br">');
                    rowtext.push("<div class='no-check'>");
                    if (orderDtlCustom.cloudProductItemId) {
                        rowtext.push("<div class='width-60' style='height: 80px;'>" + "<img src='${ctx}/file_servelt" + orderDtlCustom.skuPic + "'>" + "<br><span style='color:green'>供应商发货</span></div>");
                    } else {
                        rowtext.push("<div class='width-60'>" + "<img src='${ctx}/file_servelt" + orderDtlCustom.skuPic + "'>" + "</div>");
                    }
                    if (!orderDtlCustom.sku) {
                        orderDtlCustom.sku = "";
                    }
                    if (orderDtlCustom.sku) {
                        rowtext.push("<div class='width-226' style='float: initial;'><p class='ellipsis' title='" + orderDtlCustom.brandName + "&nbsp;&nbsp;" + orderDtlCustom.productName + "&nbsp;&nbsp;" + orderDtlCustom.artNo + "'>" + orderDtlCustom.brandName + "&nbsp;&nbsp;" + orderDtlCustom.productName + "&nbsp;&nbsp;" + orderDtlCustom.artNo + "</p><p><a href='javascript:;'>" + orderDtlCustom.productPropDesc + "</a>  SKU:" + orderDtlCustom.sku + "</p><p style='color:red;'>" + activityTypeDesc + "</p></div>");
                    } else {
                        rowtext.push("<div class='width-226' style='float: initial;'><p class='ellipsis' title='" + orderDtlCustom.brandName + "&nbsp;&nbsp;" + orderDtlCustom.productName + "&nbsp;&nbsp;" + orderDtlCustom.artNo + "'>" + orderDtlCustom.brandName + "&nbsp;&nbsp;" + orderDtlCustom.productName + "&nbsp;&nbsp;" + orderDtlCustom.artNo + "</p><p><a href='javascript:;'>" + orderDtlCustom.productPropDesc + "</a></p><p style='color:red;'>" + activityTypeDesc + "</p></div>");
                    }
                    rowtext.push("</div>");
                    rowtext.push('</td>');

                    rowtext.push('<td width="77" class="br">');
                    rowtext.push(formatMoney(orderDtlCustom.salePrice, 2));
                    if (orderDtlCustom.subDepositOrderId) {
                        rowtext.push('<br><span style="color:red;">预售单</span>');
                    }
                    if (orderDtlCustom.svipDiscount) {
                        rowtext.push('<br><span style="color:red;">SVIP价</span>');
                    }
                    rowtext.push('</td>');

                    rowtext.push('<td width="77" class="br">');
                    rowtext.push(orderDtlCustom.quantity);
                    rowtext.push('</td>');

                    rowtext.push('<td width="87" class="br">');
                    rowtext.push('<div name="customerServiceOrderTypeAndStatus">' + customerServiceType + '' + customerServiceStatusDesc + '</div>');

                    if (customerServiceType == "直赔单") {
                        rowtext.push('<div><a href="javascript:;" name="viewDirectCompensationOrder" data-suborderid="' + subOrder.id + '">查看</a></div>');
                    } else if (customerServiceType && customerServiceStatusDesc) {
                        if (orderDtlCustom.customerServiceStatus != '3') {
                            rowtext.push('<div><a href="javascript:;" onclick="viewCustomerServiceOrder(' + orderDtlCustom.id + ');">查看</a></div>');
                        }
                    }
                    rowtext.push('</td>');
                    rowtext.push('</tr>');
                }
                rowtext.push('</table>');
                rowtext.push('</td>');

                rowtext.push('<td width="88" class="br"><div>' + statusDesc + '</div><div><a href="javascript:;" onclick="viewSubOrder(' + subOrder.id + ',' + status + ')">详情</a><div></td>');


                rowtext.push('<td width="164" class="br"><span class="money">' + formatMoney(subOrder.payAmount, 2) + '</span>');
                if (subOrder.mchtPreferential) {
                    rowtext.push('<div class="preferential pt_6">商家优惠：' + formatMoney(subOrder.mchtPreferential, 2) + '</div>');
                }
                if (subOrder.platformPreferential) {
                    rowtext.push('<div class="preferential pt_6">平台优惠：' + formatMoney(subOrder.platformPreferential, 2) + '</div>');
                }
                if (subOrder.integralPreferential) {
                    rowtext.push('<div class="preferential pt_6">积分优惠：' + formatMoney(subOrder.integralPreferential, 2) + '</div>');
                }
                if (subOrder.freight) {
                    rowtext.push('<div class="preferential pt_6">商品运费：' + formatMoney(subOrder.freight, 2) + '</div>');
                }
                rowtext.push('</td>');
                var supplyAgain = "0";
                for (var i = 0; i < orderDtlCustoms.length; i++) {
                    var orderDtlCustom = orderDtlCustoms[i];
                    if (orderDtlCustom.deliveryStatus == 0) {
                        if (orderDtlCustom.customerServiceStatusDesc == "进行中" || orderDtlCustom.customerServiceStatusDesc == "已完成") {
                            supplyAgain = "0";
                        } else {
                            supplyAgain = "1";
                            break;
                        }
                    }
                    if (orderDtlCustom.markedOutOfStock == 1) {
                        supplyAgain = "1";
                        break;
                    }
                }
                rowtext.push('<td width="97" name="operation' + status + '" data-supplyagain="' + supplyAgain + '" data-suborderid="' + subOrder.id + '" data-statusdate="' + subOrder.deliveryLastDate + '"></td>');
                rowtext.push('</tr>');
                rowtext.push('</table>');
                rowtext.push('</td>');
                $(row).html(rowtext.join(""));
            },
            "columns": [
                {"data": "id", "width": "342"},
                {"data": "id", "width": "78"},
                {"data": "id", "width": "78"},
                {"data": "id", "width": "88"},
                {"data": "id", "width": "88"},
                {"data": "id", "width": "164"},
                {"data": "id", "width": "98"}
            ]
        }).api();


        $("a[name='flagColor']").on('click', function () {
            var flagClass = $(this).find("span").prop("class");
            $(this).closest("th").find("span").first().prop("class", flagClass);
            $("#search_remarksColor").val($(this).data("remarkscolor"));
            table.ajax.reload(null, false);
        });

        // 复制地址
        var clipboard = new Clipboard('.copyAddress');
        clipboard.on('success', function (e) {
            swal("复制成功！");
        });


    });

    function viewSubOrder(id, status) {
        $.ajax({
            url: "${ctx}/subOrder/subOrderView?id=" + id + "&status=" + status,
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

    function viewSubOrderRemarks(ids) {
        $.ajax({
            url: "${ctx}/subOrder/subOrderRemarks?ids=" + ids,
            type: "GET",
            success: function (data) {
                $("#remarksModal").html(data);
                $("#remarksModal").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });

    }

    function viewCustomerServiceOrder(id) {
        $.ajax({
            url: "${ctx}/customerServiceOrder/customerServiceOrderView?orderDtlId=" + id,
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

    function showDeliveryDiv(id) {
        $.ajax({
            url: "${ctx}/subOrder/toDelivery?id=" + id,
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

    function toMarkedOutOfStock(id) {
        $.ajax({
            method: 'POST',
            url: '${ctx}/subOrder/checkMarkedOutOfStock',
            data: {"id": id},
            dataType: 'json'
        }).done(function (result) {
            if (result.returnCode == '0000') {
                $.ajax({
                    url: "${ctx}/subOrder/toMarkedOutOfStock?id=" + id,
                    type: "GET",
                    success: function (data) {
                        $("#viewModal").html(data);
                        $("#viewModal").modal();
                    },
                    error: function () {
                        swal('页面不存在');
                    }
                });
            } else {
                if (result.returnMsg) {
                    swal(result.returnMsg);
                } else {
                    swal("操作失败，请稍后重试");
                }
            }
        });
    }

    function toSupplyAgain(id) {
        $.ajax({
            url: "${ctx}/subOrder/toSupplyAgain?id=" + id,
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

    function toEditDelivery(id) {
        $.ajax({
            url: "${ctx}/subOrder/toEditDelivery?id=" + id,
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

    $("#delivery").on('click', function () {
        var subOrderId = $("#deliverySubOrderId").val();
        var expressId = $("#expressId").val();
        var expressNo = $("#expressNo").val();
        if ($.trim(expressId) == "") {
            swal("快递名称必选");
            return false;
        }
        if ($.trim(expressNo) == "") {
            swal("快递单号不能为空");
            return false;
        }
        $.ajax({
            method: 'POST',
            url: '${ctx}/subOrder/subOrderDelivery',
            data: {"subOrderId": subOrderId, "expressId": expressId, "expressNo": expressNo},
            dataType: 'json'
        }).done(function (result) {
            if (result.returnCode == '0000') {
                $("#deliveryDiv").hide();
                $(".black_box").hide();
                $("#deliverySubOrderId").val("");
                swal("发货成功");
                table.ajax.reload(null, false);
            } else {
                if (result.returnMsg) {
                    swal(result.returnMsg);
                } else {
                    swal("发货失败，请稍后重试");
                }
            }
        });
    });

    $("#expressNo").keydown(function (e) {
        if (e.keyCode == 13) {
            var subOrderId = $("#deliverySubOrderId").val();
            var expressId = $("#expressId").val();
            var expressNo = $("#expressNo").val();
            if ($.trim(expressId) == "") {
                swal("快递名称必选");
                return false;
            }
            if ($.trim(expressNo) == "") {
                swal("快递单号不能为空");
                return false;
            }
            $.ajax({
                method: 'POST',
                url: '${ctx}/subOrder/subOrderDelivery',
                data: {"subOrderId": subOrderId, "expressId": expressId, "expressNo": expressNo},
                dataType: 'json'
            }).done(function (result) {
                if (result.returnCode == '0000') {
                    $("#deliveryDiv").hide();
                    $(".black_box").hide();
                    $("#deliverySubOrderId").val("");
                    swal("发货成功");
                    table.ajax.reload(null, false);
                } else {
                    if (result.returnMsg) {
                        swal(result.returnMsg);
                    } else {
                        swal("发货失败，请稍后重试");
                    }
                }
            });
        }
    });

    $("#commit").on('click', function () {
        var subOrderId = $("#editExpressSubOrderId").val();
        var expressId = $("#editExpressId").val();
        var expressNo = $("#editExpressNo").val();
        if ($.trim(expressId) == "") {
            swal("快递名称必选");
            return false;
        }
        if ($.trim(expressNo) == "") {
            swal("快递单号不能为空");
            return false;
        }
        $.ajax({
            method: 'POST',
            url: '${ctx}/subOrder/editExpress',
            data: {"subOrderId": subOrderId, "expressId": expressId, "expressNo": expressNo},
            dataType: 'json'
        }).done(function (result) {
            if (result.returnCode == '0000') {
                $("#editExpressDiv").hide();
                $(".black_box").hide();
                $("#editExpressSubOrderId").val("");
                swal("修改成功");
                table.ajax.reload(null, false);
            } else {
                if (result.returnMsg) {
                    swal(result.returnMsg);
                } else {
                    swal("修改失败，请稍后重试");
                }
            }
        });
    });

    $(".video_close").on('click', function () {
        $("#subOrderRemarksDiv").hide();
        $("#deliveryDiv").hide();
        $("#editExpressDiv").hide();
        $(".black_box").hide();
    });


    $('#btn-query').on('click', function (event) {
        $("#status").val("-1");
        table.ajax.reload();
        $("#allOrder").parent().find("li").each(function () {
            $(this).prop("class", "");
        });
        $("#allOrder").prop("class", "active");
    });

    function list(status) {
        var url = "${ctx}/subOrder/subOrderIndex?status=" + status;
        getContent(url);
    }

    $("#btn-out").on('click', function () {
        var param = $("#searchForm").serializeArray();
        var search_productId = $("#search_productId").val();
        var search_brandId = $("#search_brandId").val();
        var search_status = $("#search_status").val();
        var search_activityId = $("#search_activityId").val();
        var search_expressNo = $("#search_expressNo").val();
        var payDateBegin = $("#payDateBegin").val();
        var payDateEnd = $("#payDateEnd").val();
        var search_receiverName = $("#search_receiverName").val();
        var search_receiverPhone = $("#search_receiverPhone").val();
        var search_subOrderCode = $("#search_subOrderCode").val();
        if ((!payDateBegin || !payDateEnd ) && !search_subOrderCode) { //订单编号不为空时无需填写审核时间
            swal("请填写审核时间");
            return false;
        }
        var startTime = new Date(Date.parse(payDateBegin)).getTime();
        var endTime = new Date(Date.parse(payDateEnd)).getTime();
        var dates = Math.abs((startTime - endTime)) / (1000 * 60 * 60 * 24);
        if (dates > 31 && !search_subOrderCode) {
            swal("审核时间间隔不能超过31天");
            return false;
        }
        $.ajax({
            method: 'POST',
            url: '${ctx}/subOrder/checkOrderCount',
            data: param,
            dataType: 'json'
        }).done(function (result) {
            if (result.returnCode == '0000') {
                location.href = "${ctx}/subOrder/exportSubOrder?search_productId=" + search_productId + "&search_brandId=" + search_brandId + "&search_status=" + search_status + "&search_activityId=" + search_activityId + "&search_expressNo=" + search_expressNo + "&payDateBegin=" + payDateBegin + "&payDateEnd=" + payDateEnd + "&search_receiverName=" + search_receiverName + "&search_receiverPhone=" + search_receiverPhone + "&search_subOrderCode=" + search_subOrderCode + "&status=" + $("#status").val();
            } else {
                swal(result.returnMsg);
            }
        });
    });
</script>
</body>
</html>
