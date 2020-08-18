<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css"/>
    <title>优惠管理</title>
</head>
<style>
    .line-limit-length {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .rows {
        position: relative;
        width: 300px;
        margin: 5px;
        display: inline-block;
        background: rgb(255, 255, 224);
    }

    .tap-title {
        text-align: center;
        position: absolute;
        top: 0;
        right: 0;
        width: 60px;
        height: 20px;
        color: #fff;
        background: red;
    }

    .tap-title2 {
        text-align: center;
        position: absolute;
        top: 0;
        right: 0;
        width: 60px;
        height: 20px;
        color: #fff;
        background: DeepSkyBlue;
    }

    .no-check {
        width: 180px;
    }

    .width-180 {
        width: 180px;
        position: absolute;
        width: 200px;
        height: 7px;
        left: 83px;
        top: 5px;
        text-align: left;
    }
</style>


<body>
<div class="row content-title">
    <div class="t-title">
        优惠管理
    </div>
</div>

<div class="clearfix"></div>
<br>
<br>

<div type="1" class="panel panel-default home-content-panner company-info-container" style="height: auto;">
    <div class="rows">
        <div class="col-md-12">
            <div><span>  </span></div>
        </div>
        <div><p style="color:black;font-size:16px;">店铺满减活动</p></div>
        <div class="col-md-9">
            <p style="color: gray;">
                消费者店铺内消费达到一定金额，即可使用（活动商品除外）
            </p>
            <p style="color: red;"><a href="javascript:;" class="btn btn-warning" onclick="toAddShop('');">立即创建</a>
        </div>
        <div class="tap-title">店铺活动</div>
    </div>

    <!-- <div class="row" style="padding-top: 15px;font-size: 14px; line-height: 28px;"></div> -->
    <div class="rows">
        <div class="col-md-12">
            <div><span>  </span></div>
        </div>
        <div><p style="color:black;font-size:16px;">店铺满减券</p></div>
        <div class="col-md-9">
            <p style="color: gray;">
                消费者店铺内消费达到一定金额，即可使用（活动商品除外）
            </p>
            <p style="color: red;"><a href="javascript:;" class="btn btn-warning"
                                      onclick="toAddShopReduction(1);">立即创建</a>
        </div>
        <div class="tap-title">店铺券</div>
    </div>

    <div class="rows">
        <div class="col-md-12">
            <div><span>  </span></div>
        </div>
        <div><p style="color:black;font-size:16px;">商品券</p></div>
        <div class="col-md-9">
            <p style="color: gray;">
                针对指定商品使用的无门槛优惠券（可与其他优惠活动叠加使用）
            </p>
            <p style="color: red;"><a href="javascript:;" class="btn btn-warning"
                                      onclick="toAddproductCertificates(1);">立即创建</a>
        </div>
        <div class="tap-title2">商品券</div>
    </div>

    <div class="rows">
        <div class="col-md-12">
            <div><span>  </span></div>
        </div>
        <div><p style="color:black;font-size:16px;">店铺折扣券</p></div>
        <div class="col-md-9">
            <p style="color: gray;">
                消费者店铺内购买商品达到一定数量，即可使用（活动商品除外）
            </p>
            <p style="color: red;"><a href="javascript:;" class="btn btn-warning"
                                      onclick="toAddShopReduction(2);">立即创建</a>
        </div>
        <div class="tap-title">店铺券</div>
    </div>
</div>

<!--=================================================================================================================  -->

<div class="x_panel container-fluid gf-hd">


    <div class="clearfix"></div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <input type="hidden" id="searchOrderType" name="searchOrderType" value="${searchOrderType}"/>
            <div class="form-group">


                <div id="searchStyle" style="display:none;">
                    <label for="productBrand" class="col-md-1 control-label search-lable mwa">优惠类型：</label>
                    <div class="col-md-2 search-condition">
                        <select class="form-control" name="search_type" id="search_type">
                            <option value="">请选择</option>
                            <option value="1">店铺满减券</option>
                            <option value="2">店铺折扣券</option>
                        </select>
                    </div>
                </div>


                <label for="productBrand" class="col-md-1 control-label search-lable mwa">状态：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="search_status" id="search_status">
                        <option value="">请选择</option>
                        <option value="0">未开始</option>
                        <option value="1">活动中</option>
                        <option value="2">已结束</option>

                    </select>
                </div>


                <div class="col-md-3 text-center search-btn" id="searchOne">
                    <button type="button" class="btn btn-in fo" id="btn-query">搜索</button>
                </div>

                <div class="col-md-3 text-center search-btn" id="searchTwo" style="display:none;">
                    <button type="button" class="btn btn-info" id="btn-query2">搜索</button>
                </div>


                <div class="col-md-3 text-center search-btn" id="searchThree" style="display:none;">
                    <button type="button" class="btn btn-info" id="btn-query3">搜索</button>
                </div>

                <div class="col-md-3 text-center search-btn" id="searchFour" style="display:none;">
                    <button type="button" class="btn btn-info" id="btn-query4">搜索</button>
                </div>

            </div>
        </form>
    </div>
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation" <c:if test="${searchOrderType==1}">class="active"</c:if>><a href="#" role="tab"
                                                                                            data-toggle="tab"
                                                                                            onclick="list(1);">店铺活动</a>
        </li>
        <li role="presentation" <c:if test="${searchOrderType==2}">class="active"</c:if>><a href="#" role="tab"
                                                                                            data-toggle="tab"
                                                                                            onclick="list(2);">店铺券</a>
        </li>
        <li role="presentation" <c:if test="${searchOrderType==3}">class="active"</c:if>><a href="#" role="tab"
                                                                                            data-toggle="tab"
                                                                                            onclick="list(3);">商品券</a>
        </li>
        <li role="presentation" <c:if test="${searchOrderType==4}">class="active"</c:if>><a href="#" role="tab"
                                                                                            data-toggle="tab"
                                                                                            onclick="list(4);">特卖优惠券</a>
        </li>
    </ul>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container" id="tab1">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>店铺优惠名称</th>
                        <th>促销方式</th>
                        <th>活动时间</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>

            <div class="col-md-12 datatable-container" id="tab2">
                <table id="datatable2"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">

                        <th>优惠名称/类型</th>
                        <th>使用条件</th>
                        <th>使用时间</th>
                        <th>发行量</th>
                        <th>领取量</th>
                        <th>使用量</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>

            <div class="col-md-12 datatable-container" id="tab3">
                <table id="datatable3"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th class="sorting_disabled" rowspan="1" colspan="1" style="width: 250px;">商品</th>
                        <th>优惠名称/类型</th>
                        <th>面额</th>
                        <th>使用时间</th>
                        <th>发行量</th>
                        <th>领取量</th>
                        <th>使用量</th>
                        <th>是否参加积分转盘</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>

            <div class="col-md-12 datatable-container" id="tab4">
                <table id="datatable4"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <%--<th class="sorting_disabled" rowspan="1" colspan="1" style="width: 250px;">商品</th>--%>
                        <th>特卖名称</th>
                        <th>促销方式</th>
                        <th>发行量/领取量/可领取</th>
                        <th>活动时间</th>
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

<div class="modal fade" id="viewModal" tabindex="-1" role="dialogA" aria-labelledby="viewModalLabelA"
     data-backdrop="static"></div>
<script
        src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script
        src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/myWebUploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>
<script type="text/javascript">
    var table;
    var table2;
    var table3;
    var table4;
    $(document).ready(function () {


        $("#tab2").hide();
        $("#tab3").hide();
        $("#tab4").hide();

        table4 = $('#datatable4').dataTable({
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
                    url: '${ctx}/shopPreferentialInfo/getShopPreferentialInfoList?searchOrderType=4',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode == '0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);
                        $("span[name='timeStatus2']").each(function () {
                            var $this = $(this);
                            var id = $this.data("id");
                            var timer = "timer" + id;
                            var status = $this.data("status");
                            var beginDate = $this.data("begindate");
                            var endDate = $this.data("enddate");
                            var preferentialType = $this.data("preferentialtype");
                            var now = new Date();
                            var beginTime = new Date(beginDate);
                            var endTime = new Date(endDate);


                           /* if (now > endTime) {
                                $this.text("已结束");
                            }
                            if (now >= beginTime && now <= endTime) {
                                $this.text("活动中");
                                $this.closest("td").next().html('<a href="javascript:;" onclick="endInAdvanceCoupon(' + id + ',2);">追加</a>');
                            }
                            if (now < beginTime) {
                                $this.text("未开始");
                                var html = [];
                                html.push('<a href="javascript:;" onclick="toEdit(2,' + id + ',2);">编辑</a><br>');
                                html.push('<a href="javascript:;" onclick="remove(' + id + ',2);">删除</a><br>');
                                $this.closest("td").next().html(html.join(""));
                            }*/
                        });
                    }
                });
            },
            "language": {"url": '${ctx}/static/cn.json'},
            "autoWidth": true,   // 禁用自动调整列宽
            "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
            "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
            "processing": false,  // 隐藏加载提示,自行处理
            "serverSide": true,   // 启用服务器端分页
            "searching": false,   // 禁用原生搜索
            "bSort": false,
            "bServerSide": true,

            "columns": [

                {"data": "name", "width": "180"},
                {"data": "rules", "width": "180"},
                {
                    "data": "quantitys", "width": "150", "render": function (data, type, row, meta) {//发行量
                        var html = [];
                        html.push(row.quantitys)
                        return html.join("")
                    }
                },
                {
                    "data": "date", "render": function (data, type, row, meta) {//使用时间
                        return "起:" + new Date(row.activityBeginTime).format("yyyy-MM-dd hh:mm") + "<br>止:" + new Date(row.activityEndTime).format("yyyy-MM-dd hh:mm");
                    }
                },
                // {"data": "recQuantity","width":"80","render": function (data, type, row, meta) {//领取量
                //         var html= [];
                //         var temDate = new Date();
                //         html.push(row.recQuantity);
                //         if(row.recQuantity>=row.grantQuantity){
                //             html.push("<br>已领光")
                //         }
                //         if(temDate>=row.recEndDate){
                //             html.push("<br>领取结束")
                //         }
                //         return html.join("");
                //     }},
                // {"data": "useNum","width":"80","render": function (data, type, row, meta) {
                //         return row.useNum;
                //     }},

                {
                    "data": "status", "width": "80", "render": function (data, type, row, meta) {
                        var html = [];
                        var now = new Date();
                        var beginTime = row.activityBeginTime;
                        var endTime = row.activityEndTime;
                        if(now<beginTime){
                            html.push("未开始")
                        }else if(now<endTime){
                            html.push("活动中")
                        }else {
                            html.push("已结束")
                        }
                        return html.join("")
                    }
                },
                {
                    "data": "op", "width": "100", "render": function (data, type, row, meta) {
                        var html = [];
                        var now = new Date();
                        var beginTime = row.activityBeginTime;
                        var endTime = row.activityEndTime;
                        if(beginTime<now && now<endTime){
                        	html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editMchtContact("+row.areaId+",4)' >追加</a>")
                        }else {
                            html.push("<a class='table-opr-btn' href='javascript:void(0);'>-</a>")
                        }
                        return html.join("")
                    }
                }
            ]
        }).api();

        $('#btn-query4').on('click', function (event) {
            table4.ajax.reload();
        });

        table3 = $('#datatable3').dataTable({
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
                    url: '${ctx}/shopPreferentialInfo/getShopPreferentialInfoList?searchOrderType=3',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode == '0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);
                        $("span[name='timeStatus3']").each(function () {
                            var $this = $(this);
                            var id = $this.data("id");
                            var timer = "timer" + id;
                            var status = $this.data("status");
                            var beginDate = $this.data("begindate");
                            var endDate = $this.data("enddate");
                            var preferentialType = $this.data("preferentialtype");
                            var now = new Date();
                            var beginTime = new Date(beginDate);
                            var endTime = new Date(endDate);

                            if (now > endTime) {
                                $this.text("已结束");
                            }
                            if (now >= beginTime && now <= endTime) {
                                $this.text("活动中");
                                $this.closest("td").next().html('<a href="javascript:;" onclick="endInAdvanceCoupon(' + id + ',3);">【提前结束】</a>');
                            }
                            if (now < beginTime) {
                                $this.text("未开始");
                                var html = [];
                                html.push('<a href="javascript:;" onclick="toEdit(3,' + id + ',);">编辑</a><br>');
                                html.push('<a href="javascript:;" onclick="remove(' + id + ',3);">删除</a><br>');
                                $this.closest("td").next().html(html.join(""));
                            }


                        });
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
                    "data": "", "width": "180", "render": function (data, type, row, meta) {
                        var tempNo = row.productArtNo
                        var tempName = row.productName;
                        var ellipsisNo = "";
                        var ellipsisName = "";
                        if (tempName && tempName.length > 10) {
                            ellipsisName = tempName.substring(0, 10) + "..."
                        } else {
                            ellipsisName = tempName
                        }

                        if (tempNo && tempNo.length > 10) {
                            ellipsisNo = tempNo.substring(0, 10) + "..."
                        } else {
                            ellipsisNo = tempNo
                        }
                        var h = [];
                        var html = "";
                        html += '<div class="no-check">';
                        if (row.pic) {
                            var pic = row.pic.substring(0, row.pic.lastIndexOf(".")) + "_M" + row.pic.substring(row.pic.lastIndexOf("."));
                            html += '<div class="width-60"><img src="${ctx}/file_servelt' + pic + '"></div>';
                        } else {
                            html += '<div class="width-60"><img src="${ctx}/file_servelt"></div>';
                        }
                        html += '<div class="width-180"><p  style="color: #999;margin: 5px 0 0;">' + ellipsisName + '</p><br><p  style="color: #999;margin: 5px 0 0;">货号：' + ellipsisNo + '&nbsp;&nbsp;&nbsp;&nbsp;</p><p style="color: #999;margin: 0;">ID：' + row.productCode + '</p></div>';
                        html += '</div>';
                        h.push(html);
                        return h.join("");

                    }
                },

                {
                    "data": "name", "width": "80", "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='watchView(" + row.id + ")' >" + row.name + "</a>");
                        if (row.couponType == 1 && row.preferentialType == 1) {
                            html.push("<p>店铺满减券</p>")
                        }
                        if (row.couponType == 1 && row.preferentialType == 2) {
                            html.push("<p>店铺折扣券</p>")
                        }
                        if (row.couponType == 4) {
                            html.push("<p>商品券</p>")
                        }
                        return html.join("");

                    }
                },
                {
                    "data": "money", "width": "50", "render": function (data, type, row, meta) {
                        return row.money;
                    }
                },
                {
                    "data": "date", "width": "120", "render": function (data, type, row, meta) {//使用时间
                        return "起:" + new Date(row.expiryBeginDate).format("yyyy-MM-dd hh:mm") + "<br>止:" + new Date(row.expiryEndDate).format("yyyy-MM-dd hh:mm");
                    }
                },
                {
                    "data": "grantQuantity", "width": "80", "render": function (data, type, row, meta) {//发行量
                        var html = [];
                        var now = new Date();
                        var endTime = row.recEndDate;
                        html.push(row.grantQuantity + "<br>")
                        if (now < endTime) {
                            html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editMchtContact(" + row.id + ",3)' >追加</a>")
                        }
                        return html.join("")
                    }
                },
                {
                    "data": "recQuantity", "width": "80", "render": function (data, type, row, meta) {//领取量
                        var html = [];
                        var temDate = new Date();
                        if (row.recQuantity == "" || row.recQuantity == null || row.recQuantity == 0) {
                            html.push("0");
                        } else {
                            html.push(row.recQuantity);
                        }

                        if (row.recQuantity >= row.grantQuantity) {
                            html.push("<br>已领光")
                        }
                        if (row.recEndDate <= temDate) {
                            html.push("<br>领取结束")
                        }
                        return html.join("");
                    }
                },
                {
                    "data": "useNum", "width": "80", "render": function (data, type, row, meta) {
                        return row.useNum;
                    }
                },
                {
                    "data": "isIntegralTurntableDesc", "width": "80", "render": function (data, type, row, meta) {
                        return row.isIntegralTurntableDesc;
                    }
                },
                {
                    "data": "status", "width": "80", "render": function (data, type, row, meta) {
                        /*    var statusDesc="";
                           if(row.status==0){
                               statusDesc="未启用"
                           }
                           if(row.status==1){
                               statusDesc="启用"
                           }
                           if(row.status==2){
                               statusDesc="停用"
                           } */

                        return '<span name="timeStatus3" data-preferentialtype="' + row.preferentialType + '"  data-id="' + row.id + '" data-status="' + row.status + '" data-begindate="' + row.recBeginDate + '" data-enddate="' + row.recEndDate + '"></span>';
                    }
                },
                {
                    "data": "op", "width": "100", "render": function (data, type, row, meta) {
                        return "";
                    }
                }
            ]
        }).api();
        $('#btn-query3').on('click', function (event) {
            table3.ajax.reload();
        });


        /* ================================================================================ */

        table2 = $('#datatable2').dataTable({
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
                    url: '${ctx}/shopPreferentialInfo/getShopPreferentialInfoList?searchOrderType=2',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode == '0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);
                        $("span[name='timeStatus2']").each(function () {
                            var $this = $(this);
                            var id = $this.data("id");
                            var timer = "timer" + id;
                            var status = $this.data("status");
                            var beginDate = $this.data("begindate");
                            var endDate = $this.data("enddate");
                            var preferentialType = $this.data("preferentialtype");
                            var now = new Date();
                            var beginTime = new Date(beginDate);
                            var endTime = new Date(endDate);


                            if (now > endTime) {
                                $this.text("已结束");
                            }
                            if (now >= beginTime && now <= endTime) {
                                $this.text("活动中");
                                $this.closest("td").next().html('<a href="javascript:;" onclick="endInAdvanceCoupon(' + id + ',2);">【提前结束】</a>');
                            }
                            if (now < beginTime) {
                                $this.text("未开始");
                                var html = [];
                                html.push('<a href="javascript:;" onclick="toEdit(2,' + id + ',2);">编辑</a><br>');
                                html.push('<a href="javascript:;" onclick="remove(' + id + ',2);">删除</a><br>');
                                $this.closest("td").next().html(html.join(""));
                            }
                        });
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
                    "data": "name", "width": "180", "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='watchView(" + row.id + ")' >" + row.name + "</a>");
                        if (row.couponType == 1 && row.preferentialType == 1) {
                            html.push("<p>店铺满减券</p>")
                        }
                        if (row.couponType == 1 && row.preferentialType == 2) {
                            html.push("<p>店铺折扣券</p>")
                        }
                        if (row.couponType == 4) {
                            html.push("<p>商品券</p>")
                        }
                        return html.join("");

                    }
                },
                {
                    "data": "money", "render": function (data, type, row, meta) {
                        var html = [];
                        if (row.preferentialType == 1) {
                            html.push("满" + row.minimum + "减" + row.money + "<br>");
                        } else {
                            var discount = row.discount * 10
                            html.push("满" + row.minicount + "件" + discount.toFixed(1) + "折<br>");
                        }
                        return html.join("");

                    }
                },
                {
                    "data": "date", "render": function (data, type, row, meta) {//使用时间
                        return "起:" + new Date(row.expiryBeginDate).format("yyyy-MM-dd hh:mm") + "<br>止:" + new Date(row.expiryEndDate).format("yyyy-MM-dd hh:mm");
                    }
                },
                {
                    "data": "grantQuantity", "width": "80", "render": function (data, type, row, meta) {//发行量
                        var html = [];
                        var now = new Date();
                        var beginTime = row.recBeginDate;
                        var endTime = row.recEndDate;
                        html.push(row.grantQuantity + "<br>")
                        if (now < endTime) {
                            html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editMchtContact(" + row.id + ",2)' >追加</a>")
                        }
                        return html.join("")
                    }
                },
                {
                    "data": "recQuantity", "width": "80", "render": function (data, type, row, meta) {//领取量
                        var html = [];
                        var temDate = new Date();
                        html.push(row.recQuantity);
                        if (row.recQuantity >= row.grantQuantity) {
                            html.push("<br>已领光")
                        }
                        if (temDate >= row.recEndDate) {
                            html.push("<br>领取结束")
                        }
                        return html.join("");
                    }
                },
                {
                    "data": "useNum", "width": "80", "render": function (data, type, row, meta) {
                        return row.useNum;
                    }
                },

                {
                    "data": "status", "width": "80", "render": function (data, type, row, meta) {
                        /*    var statusDesc="";
                           if(row.status==0){
                               statusDesc="未启用"
                           }
                           if(row.status==1){
                               statusDesc="启用"
                           }
                           if(row.status==2){
                               statusDesc="停用"
                           } */

                        return '<span name="timeStatus2" data-preferentialtype="' + row.preferentialType + '"  data-id="' + row.id + '" data-status="' + row.status + '" data-begindate="' + row.recBeginDate + '" data-enddate="' + row.recEndDate + '"></span>';
                    }
                },
                {
                    "data": "op", "width": "100", "render": function (data, type, row, meta) {
                        return "";
                    }
                }
            ]
        }).api();

        $('#btn-query2').on('click', function (event) {
            table2.ajax.reload();
        });
        /*================================================================================================================  */
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
                    url: '${ctx}/shopPreferentialInfo/getShopPreferentialInfoList?searchOrderType=1',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode == '0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);
                        $("span[name='timeStatus1']").each(function () {
                            var $this = $(this);
                            var id = $this.data("id");
                            var timer = "timer" + id;
                            var status = $this.data("status");
                            var beginDate = $this.data("begindate");
                            var endDate = $this.data("enddate");
                            var now = new Date();
                            var beginTime = new Date(beginDate);
                            var endTime = new Date(endDate);

                            if (now > endTime) {
                                $this.text("已结束");
                            }
                            if (now >= beginTime && now <= endTime) {

                                $this.text("活动中");
                                $this.closest("td").next().html('<a href="javascript:;" onclick="endInAdvance(' + id + ');">【提前结束】</a>');
                            }
                            if (now < beginTime) {
                                $this.text("未开始");
                                var html = [];
                                html.push('<a href="javascript:;" onclick="toEdit(1,' + id + ',-1);">编辑</a><br>');
                                html.push('<a href="javascript:;" onclick="del(' + id + ');">删除</a><br>');
                                $this.closest("td").next().html(html.join(""));
                            }
                        });
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
                    "data": "name", "width": "150", "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='watchShopView(" + row.id + ")' >" + row.name + "</a>");
                        html.push("<p>店铺满减活动</p>")

                        return html.join("");
                    }
                },
                {
                    "data": "rules", "width": "150", "render": function (data, type, row, meta) {
                        if (row.rules) {
                            var rulesArray = row.rules.split("|");
                            var html = [];
                            for (var i = 0; i < rulesArray.length; i++) {
                                var eachRuleArray = rulesArray[i].split(",");
                                if (row.sumFlag == "0") {
                                    html.push("满" + eachRuleArray[0] + "减" + eachRuleArray[1] + "<br>");
                                } else {
                                    html.push("满" + eachRuleArray[0] + "减" + eachRuleArray[1] + "【上不封顶】");
                                }
                            }
                            return html.join("");
                        } else {
                            return "";
                        }
                    }
                },
                {
                    "data": "date", "width": "180", "render": function (data, type, row, meta) {
                        return "起:" + new Date(row.beginDate).format("yyyy-MM-dd hh:mm") + "<br>止:" + new Date(row.endDate).format("yyyy-MM-dd hh:mm");
                    }
                },
                {
                    "data": "statusDesc", "width": "80", "render": function (data, type, row, meta) {
                        return '<span name="timeStatus1" data-id="' + row.id + '" data-status="' + row.status + '" data-begindate="' + row.beginDate + '" data-enddate="' + row.endDate + '"></span>';
                    }
                },
                {
                    "data": "op", "width": "100", "render": function (data, type, row, meta) {
                        return "";
                    }
                }
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });

        /*     $("#searchKeyWord").keydown(function(e){
                if(e.keyCode==13){
                    table.ajax.reload();
                    return false;
                }
            }); */


        var searchIndex = "${searchOrderType}"
        list(searchIndex);
    });


    function list(searchOrderType) {
        if (searchOrderType == 1) {
            $("#searchOrderType").val("1")
            $("#searchStyle").hide();

            $("#searchOne").show();
            $("#searchTwo").hide();
            $("#searchThree").hide();
            $("#searchFour").hide();

            $("#tab2").hide();
            $("#tab3").hide();
            $("#tab1").show();
            $("#tab4").hide();
            table.ajax.reload();
        }
        if (searchOrderType == 2) {
            $("#searchOrderType").val("2");
            $("#searchStyle").show();

            $("#searchOne").hide();
            $("#searchTwo").show();
            $("#searchThree").hide();
            $("#searchFour").hide();

            $("#tab1").hide();
            $("#tab3").hide();
            $("#tab2").show();
            $("#tab4").hide();
            table2.ajax.reload();
        }
        if (searchOrderType == 3) {
            $("#searchOrderType").val("3")
            $("#searchStyle").hide();

            $("#searchOne").hide();
            $("#searchTwo").hide();
            $("#searchThree").show();
            $("#searchFour").hide();

            $("#tab1").hide();
            $("#tab2").hide();
            $("#tab3").show();
            $("#tab4").hide();
            table3.ajax.reload();
        }
        if (searchOrderType == 4) {
            $("#searchOrderType").val("4")
            $("#searchStyle").hide();

            $("#searchOne").hide();
            $("#searchTwo").hide();
            $("#searchThree").hide();
            $("#searchFour").show();

            $("#tab1").hide();
            $("#tab2").hide();
            $("#tab3").hide();
            $("#tab4").show();
            table4.ajax.reload();
        }

        /*  // var url = "
        ${ctx}/shopPreferentialInfo/shopPreferentialInfoIndex?searchOrderType=" + searchOrderType;
    table2.ajax.reload(); */
    }

    //店铺满减活动
    function toEdit(searchOrderType, id, preferentialType) {
        /* var type ="
        ${searchOrderType}" */
        ;
        /*   console.log("t"+searchOrderType);
          console.log("s"+preferentialType);
          console.log("id"+id); */

        if (searchOrderType == 1) {
            $("#searchOrderType").val("1")
            $.ajax({
                url: "${ctx}/shopPreferentialInfo/toEditShopPreferentialInfo?id=" + id,
                type: "GET",
                success: function (data) {
                    $("#viewModal").html(data);
                    $("#viewModal").modal();
                    table.ajax.reload();
                },
                error: function () {
                    swal('页面不存在');
                }
            });
        }
        if (searchOrderType == 2) {
            $("#searchOrderType").val("2")
            $.ajax({
                url: "${ctx}/shopPreferentialInfo/shopFullReduction?id=" + id + "&preferentialType=" + preferentialType,
                type: "GET",
                success: function (data) {
                    $("#viewModal").html(data);
                    $("#viewModal").modal();
                    table2.ajax.reload();
                },
                error: function () {
                    swal('页面不存在');
                }
            });
        }

        if (searchOrderType == 3) {
            $("#searchOrderType").val("3")
            $.ajax({
                url: "${ctx}/shopPreferentialInfo/productCertificates?id=" + id + "&isHide=" + 1,
                type: "GET",
                success: function (data) {
                    $("#viewModal").html(data);
                    $("#viewModal").modal();
                    table3.ajax.reload();
                },
                error: function () {
                    swal('页面不存在');
                }
            });
        }

    }


    //创建店铺满减活动
    function toAddShop() {
        $.ajax({
            url: "${ctx}/shopPreferentialInfo/checkShopPreferentialInfoNum",
            type: "GET",
            success: function (data) {
                if (data.returnDate == "4004") {
                    swal("对不起,最多只能创建20个同类型活动");
                } else {
                    $.ajax({
                        url: "${ctx}/shopPreferentialInfo/toEditShopPreferentialInfo",
                        type: "GET",
                        success: function (data) {
                            $("#viewModal").html(data);
                            $("#viewModal").modal();
                            table.ajax.reload();
                        },
                        error: function () {
                            swal('页面不存在');
                        }
                    });
                }
            },
            error: function () {
                swal('页面不存在');
            }
        });


    }


    //创建店铺满减券和折扣券
    function toAddShopReduction(preferentialType) {

        $.ajax({
            url: "${ctx}/shopPreferentialInfo/checkCouponNum?preferentialType=" + preferentialType,
            type: "GET",
            success: function (data) {
                if (data.returnDate == "4004") {
                    swal("对不起,最多只能创建20个同类型活动");
                } else {
                    $.ajax({
                        url: "${ctx}/shopPreferentialInfo/shopFullReduction?preferentialType=" + preferentialType,
                        type: "GET",
                        success: function (data) {
                            $("#viewModal").html(data);
                            $("#viewModal").modal();
                            table2.ajax.reload();
                        },
                        error: function () {
                            swal('页面不存在');
                        }
                    });
                }

            },
            error: function () {
                swal('页面不存在');
            }
        });

    }

    //创建商品券
    function toAddproductCertificates() {

        $.ajax({
            url: "${ctx}/shopPreferentialInfo/checkCouponNum?preferentialType=" + 3,
            type: "GET",
            success: function (data) {
                if (data.returnDate == "4004") {
                    swal("对不起,最多只能创建20个同类型活动");
                } else {
                    $.ajax({
                        url: "${ctx}/shopPreferentialInfo/productCertificates",
                        type: "GET",
                        success: function (data) {
                            $("#viewModal").html(data);
                            $("#viewModal").modal();
                            table3.ajax.reload();
                        },
                        error: function () {
                            swal('页面不存在');
                        }
                    });
                }
            },
            error: function () {
                swal('页面不存在');
            }
        });


    }


    //查看优惠券详情
    function watchView(id) {
        $.ajax({
            url: "${ctx}/shopPreferentialInfo/watchView?id=" + id,
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

    //查看店铺满减活动
    function watchShopView(id) {
        $.ajax({
            url: "${ctx}/shopPreferentialInfo/watchShopView?id=" + id,
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


    //追加
    function editMchtContact(id,type){

        if(type==2){
            $.ajax({
                url: "${ctx}/shopPreferentialInfo/appendQuantity?id="+id+"&type=2",
                type: "GET",
                success: function(data){
                    $("#viewModal").html(data);
                    $("#viewModal").modal();

                },
                error:function(){
                    swal('页面不存在');
                }
            });
        }
        if(type==3){
            $.ajax({
                url: "${ctx}/shopPreferentialInfo/appendQuantity?id="+id+"&type=3",
                type: "GET",
                success: function(data){
                    $("#viewModal").html(data);
                    $("#viewModal").modal();

                },
                error:function(){
                    swal('页面不存在');
                }
            });
        }

        if(type==4){
            $.ajax({
                url: "${ctx}/shopPreferentialInfo/addQuantity?id="+id+"&type=4",
                type: "GET",
                success: function(data){
                    $("#viewModal").html(data);
                    $("#viewModal").modal();

                },
                error:function(){
                    swal('页面不存在');
                }
            });
        }

    }


    function loadImageFile(obj) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        var rFilter = /^(?:image\/jpeg|image\/png)$/i;
        if (!rFilter.test(oFile.type)) {
            swal("图片格式不正确！");
            return;
        }
        if (oFile.size > 100 * 1024) {
            swal("图片大小不能超过100K");
            return;
        }
        var reader = new FileReader();
        reader.onload = function (e) {
            var image = new Image();
            image.onload = function () {
                var imgWidth = '400';
                var imgHeight = '400';
                if (this.width != imgWidth || this.height != imgHeight) {
                    swal("图片像素不是" + imgWidth + "*" + imgHeight + "px");
                    return;
                } else {
                    uploadImage("logoFile", $("#shopLogo"));
                    if ($(obj).parent().children("img").length <= 0) {
                        $('<img style="left: -6px;">').appendTo($(obj).parent());
                        ;
                    }
                    $(obj).parent().children("img").attr("src", e.target.result);
                    $(obj).parent().children("div").remove();
                }
            };
            image.src = e.target.result;
        }
        reader.readAsDataURL(oFile);
    }

    //上传图片
    function uploadImage(fileElementId, $valueFiled) {
        var oFile = document.getElementById(fileElementId).files[0];
        var formData = new FormData();
        formData.append("file", oFile);
        $.ajax({
            url: "${ctx}/common/fileUpload",
            type: 'POST',
            data: formData,
            async: false,
            // 告诉jQuery不要去处理发送的数据
            processData: false,
            // 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            beforeSend: function () {
                console.log("图片片上传中，请稍候");
            },
            success: function (data) {
                if (data.returnCode == "0000") {
                    $valueFiled.val(data.returnData);
                    var shopLogo = $("#shopLogo").val();
                    var oldShopLogo = "";
                    <c:if test="${not empty shopLogo}">
                    oldShopLogo = "${shopLogo}";
                    </c:if>
                    if (shopLogo != oldShopLogo) {
                        $.ajax({
                            method: 'POST',
                            url: '${ctx}/shopPreferentialInfo/updateShopLogo',
                            data: {"shopLogo": shopLogo},
                            dataType: 'json'
                        }).done(function (result) {
                            submited = false;
                            if (result.returnCode == '0000') {
                                swal("上传成功");
                            } else {
                                swal("上传失败，请稍后重试");
                            }
                        });
                    }
                } else {
                    swal({
                        title: "图片上传失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            },
            error: function (responseStr) {
                swal("图片上传失败");
            }
        });


    }

    //店铺活动的启用
    /* var submited;
    function changeStatus(id,status){

            $.ajax({
                      method: 'POST',
                        url: '${ctx}/shopPreferentialInfo/changeStatus',
			        data: {"id":id},
			        dataType: 'json'
			    }).done(function (result) {
			    	submited = false;
			        if (result.returnCode =='0000') {
			           	swal("操作成功");
			           	table.ajax.reload();
			        }else{
			        	swal("操作失败，请稍后重试");
			        }
			    });

} */

    //优惠券的启用
    /* function changeCouponStatus(id,type){
            if(type==2){
                $.ajax({
                    method: 'POST',
                    url: '${ctx}/shopPreferentialInfo/changeCouponStatus',
		        data: {"id":id},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		           	 swal("操作成功"); 
		           	table2.ajax.reload();
		        }else{
		        	swal("操作失败，请稍后重试");
		        }
		    });
		}
	
		
		if(type==3){
			$.ajax({
		        method: 'POST',
		        url: '${ctx}/shopPreferentialInfo/changeCouponStatus',
		        data: {"id":id},
		        dataType: 'json'
		    }).done(function (result) {
		        if (result.returnCode =='0000') {
		           	 swal("操作成功"); 
		           	table3.ajax.reload();
		        }else{
		        	swal("操作失败，请稍后重试");
		        }
		    });
		}

		
}
 */





    //店铺活动的删除
    function del(id) {
        swal({
                title: "您确定要删除该优惠信息吗?",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false
            },
            function () {
                delConfirm(id);
            });
    }


    function delConfirm(id) {

        $.ajax({
            url: "${ctx}/shopPreferentialInfo/delete",
            type: 'POST',
            dataType: 'json',
            cache: false,
            async: false,
            data: {id: id},
            timeout: 30000,
            success: function (data) {
                if (data.returnCode == "0000") {
                    swal.close();
                    table.ajax.reload();
                } else {
                    swal({
                        title: data.returnMsg,
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }

            },
            error: function () {
                swal({
                    title: "处理失败！",
                    type: "error",
                    timer: 1500,
                    confirmButtonText: "确定"
                });
            }
        });

    }

    //优惠券的删除
    function remove(id, type) {
        swal({
                title: "您确定要删除该优惠信息吗?",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false
            },
            function () {
                removeCoupon(id, type);
            });
    }


    function removeCoupon(id, type) {
        if (type == 2) {
            $.ajax({
                url: "${ctx}/shopPreferentialInfo/removeCoupon",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: {id: id},
                timeout: 30000,
                success: function (data) {
                    if (data.returnCode == "0000") {
                        swal.close();
                        table2.ajax.reload();
                    } else {
                        swal({
                            title: data.returnMsg,
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }

                },
                error: function () {
                    swal({
                        title: "处理失败！",
                        type: "error",
                        timer: 1500,
                        confirmButtonText: "确定"
                    });
                }
            });
        }
        if (type == 3) {
            $.ajax({
                url: "${ctx}/shopPreferentialInfo/removeCoupon",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: {id: id},
                timeout: 30000,
                success: function (data) {
                    if (data.returnCode == "0000") {
                        swal.close();
                        table3.ajax.reload();
                    } else {
                        swal({
                            title: data.returnMsg,
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }

                },
                error: function () {
                    swal({
                        title: "处理失败！",
                        type: "error",
                        timer: 1500,
                        confirmButtonText: "确定"
                    });
                }
            });
        }


    }

    //店铺活动的提前结束
    function endInAdvance(id) {
        swal({
                title: "您确定要提前结束该优惠信息吗？",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        method: 'POST',
                        url: '${ctx}/shopPreferentialInfo/endInAdvance',
                        data: {"id": id},
                        dataType: 'json'
                    }).done(function (result) {
                        if (result.returnCode == '0000') {
                            table.ajax.reload(null, false);
                            swal("操作成功");
                        } else {
                            if (result.returnMsg) {
                                swal(result.returnMsg);
                            } else {
                                swal("操作失败，请稍后重试");
                            }
                        }
                    });
                }
            });
    }


    //优惠券的提前结束
    function endInAdvanceCoupon(id, type) {
        if (type == 2) {
            swal({
                    title: "您确定要提前结束该优惠信息吗？",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $.ajax({
                            method: 'POST',
                            url: '${ctx}/shopPreferentialInfo/endInAdvanceCoupon',
                            data: {"id": id},
                            dataType: 'json'
                        }).done(function (result) {
                            if (result.returnCode == '0000') {
                                table2.ajax.reload(null, false);
                                swal("操作成功");
                            } else {
                                if (result.returnMsg) {
                                    swal(result.returnMsg);
                                } else {
                                    swal("操作失败，请稍后重试");
                                }
                            }
                        });
                    }
                });
        }
        if (type == 3) {
            swal({
                    title: "您确定要提前结束该优惠信息吗？",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $.ajax({
                            method: 'POST',
                            url: '${ctx}/shopPreferentialInfo/endInAdvanceCoupon',
                            data: {"id": id},
                            dataType: 'json'
                        }).done(function (result) {
                            if (result.returnCode == '0000') {
                                table3.ajax.reload(null, false);
                                swal("操作成功");
                            } else {
                                if (result.returnMsg) {
                                    swal(result.returnMsg);
                                } else {
                                    swal("操作失败，请稍后重试");
                                }
                            }
                        });
                    }
                });
        }

    }


</script>
</body>
</html>
