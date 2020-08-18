<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="zh-CN">
<head>
    <title>视频专区</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
</head>
<body>
<div class="x_panel container-fluid py-tm">
    <input type="hidden" id="mchtInfoStatus">
    <div class="row content-title">
        <div class="t-title">
            视频专区
            <a href='javascript:void(0);' onclick="videoAdd();">上传</a>
        </div>
    </div>

    <!-- 查询区域 -->
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <input type="hidden" id="searchStatus" name="searchStatus" value="1">
            <div class="form-group">
                <label for="createDateMoreOrEquals" class="col-md-1 control-label search-lable">申请日期：</label>
                <div class="col-md-5 search-condition">
                    <input class="form-control datePicker" type="text" id="createDateMoreOrEquals"
                           name="createDateMoreOrEquals" data-date-format="yyyy-mm-dd">
                    <i class="picker-split"> - </i>
                    <input class="form-control datePicker" type="text" id="createDateLessOrEquals"
                           name="createDateLessOrEquals" data-date-format="yyyy-mm-dd">
                </div>

                <label for="title" class="col-md-1 control-label search-lable">视频标题：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control nameWidth200" type="text" id="title" name="title">
                </div>

                <label for="productCode" class="col-md-1 control-label search-lable">商品ID：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control nameWidth200" type="text" id="productCode" name="productCode">
                </div>
            </div>

            <div class="form-group">
                <label for="productName" class="col-md-1 control-label search-lable">商品名称：</label>
                <div class="col-md-2 search-condition">
                    <input class="form-control nameWidth200" type="text" id="productName" name="productName">
                </div>

                <label for="auditStatus" class="col-md-1 control-label search-lable">审核状态：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="auditStatus" id="auditStatus">
                        <option value="">--请选择--</option>
                        <option value="1">待提审</option>
                        <option value="2">待审核</option>
                        <option value="3">审核中</option>
                        <option value="5">通过</option>
                        <option value="99">驳回</option>
                    </select>
                </div>

                <div class="col-md-3 text-center search-btn">
                    <button type="button" class="btn btn-info" id="btn-query">搜索</button>
                </div>
            </div>
        </form>
    </div>

    <div class="clearfix"></div>
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation" class="active"><a href="javascript:;" role="tab" data-toggle="tab"
                                                  onclick="changeTab(this,1);">全部</a></li>
        <li role="presentation"><a href="javascript:;" role="tab" data-toggle="tab" onclick="changeTab(this,2);">待修改</a>
        </li>
    </ul>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <table id="datatable" class="table table-striped table-bordered dataTable no-footer" role="grid"
                       aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>申请日期</th>
                        <th>视频标题/视频文字描述</th>
                       <%-- <th>视频相关图片</th>--%>
                        <th>涉及商品</th>
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

<%--图片预览弹窗--%>
<div class="modal fade" id="previewImgModal" tabindex="-1" role="dialog" aria-labelledby="previewImgLabel"
     data-backdrop="static">
    <div class="modal-dialog" role="document" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <span class="modal-title" id="exampleModalLabel">图片预览</span>
            </div>
            <div class="modal-body">
                <div class="table-responsive" style="text-align: center">
                    <img src="" id="previewImg">
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/static/js/utils.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    var table;

    $(function () {
        initDatePicker();
        initTable();
        initBindEvents();
    });

    function initBindEvents() {
        $('#btn-query').on('click', function () {
            table.ajax.reload();
        });
    }

    function initDatePicker() {
        $('.datePicker').datetimepicker(
            {
                minView: "month", //选择日期后，不会再跳转去选择时分秒
                format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose: true //选择日期后自动关闭
            }
        );
    }

    //列表初始化
    function initTable() {
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
                    url: '${ctx}/video/mgr/list',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.success) {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.page.totalRow;
                        returnData.recordsFiltered = result.returnData.page.totalRow; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.page.list;
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
                    "title": "申请日期", width: "120", "render": function (data, type, row, meta) {
                        var createDate = new Date(row.createDate);
                        return createDate.format("yyyy-MM-dd hh:mm");
                    }
                },
                {
                    "title": "视频标题/视频文字描述", width: "200", "render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<div style='white-space:normal;word-break: break-all;'>");
                        if (row.videoCover != null) {
                            if (row.videoCover && row.videoCover.indexOf("http") >= 0) {//网络图片
                                html.push('<div style="text-align: center;"><img style="width: 80px;height: 80px" src=' + row.videoCover + '></div>');
                            } else {
                                html.push('<div><img style="width: 80px;height: 80px" src="${ctx}/file_servelt' + row.videoCover + '"></div>');
                            }
                        }
                        html.push("<br/>");
                        html.push("标题：" + row.title);
                        html.push("<br/>");
                        html.push("<span>描述：" + row.description + "</span>");
                        html.push("</div>")
                        return html.join("");
                    }
                },
              /*  {
                    "title": "视频相关图片", width: "220", "render": function (data, type, row, meta) {
                        var html = [];
                        if (row.imgList.length > 0) {
                            for (var i = 0; i < row.imgList.length; i++) {
                                if (row.imgList[i] && row.imgList[i].indexOf("http") >= 0) {//网络图片
                                    html.push('<div style="margin: 2px" class="width-60"><a href=' + row.imgList[i] + '><img src=' + row.imgList[i] + '></a></div>');
                                } else {
                                    html.push('<div style="margin: 2px" class="width-60"><img onclick="openImg(this)" src="${ctx}/file_servelt' + row.imgList[i] + '"></div>');
                                }
                            }
                        }
                        return html.join("");
                    }
                },*/
                {
                    "title": "涉及商品", width: "360", "render": function (data, type, row, meta) {
                        var html = [];
                        if (row.productList.length > 0) {
                            for (var i = 0; i < row.productList.length; i++) {
                                html.push("<div style='overflow: hidden;padding: 5px 0;'>");
                                var product = row.productList[i];
                                if (product.mainImg && product.mainImg.indexOf("http") >= 0) {//网络图片
                                    html.push('<div class="width-60"><img src=' + product.mainImg + '></div>');
                                } else {
                                    html.push('<div class="width-60"><img src="${ctx}/file_servelt' + product.mainImg + '"></div>');
                                }
                                html.push('<div class="width-226" style="width: calc(100% - 82px);"><p class="h34">' + product.name + '</p><div><span style="float: left; margin: 0;">ID：' + product.productCode + '</span></div></div>');
                                html.push("</div>");
                            }
                        }
                        return html.join("");
                    }
                },
                {
                    "title": "状态", "render": function (data, type, row, meta) {
                        var html = [];
                        if (row.auditStatus === "1") {
                            html.push("待提审");
                        } else if (row.auditStatus === "2") {
                            html.push("待审核");
                        } else if (row.auditStatus === "3") {
                            html.push("审核中");
                        } else if (row.auditStatus === "5") {
                            html.push("通过");
                            html.push("<br/>");
                            html.push(row.status === "1" ? "上架" : "下架");
                        } else if (row.auditStatus === "4"|| row.auditStatus=="6") {
                            html.push("驳回");
                            html.push("<br/>");
                            html.push("<a href='javascript:;' data-content='" + row.auditRemark + "' onclick='showAuditRemark(this)'>【查看原因】</a>")
                        }
                        if (row.tipOff) {
                            html.push("<br/>");
                            html.push("<span style='color: red;'>已被举报</span>");
                        }
                        return html.join("");
                    }
                },
                {
                    width: "90", "title": "操作", "render": function (data, type, row, meta) {
                        var html = [];
                        if (row.auditStatus === "1") {
                            html.push("<a href='javascript:;' onclick='submitAudit(" + row.id + ")'>提交审核</a><br/>");
                            html.push("<a href='javascript:;' onclick='toEditPage(" + row.id + ")'>修改</a><br/>");
                            html.push("<a href='javascript:;' onclick='deleteVideo(" + row.id + ")'>删除</a><br/>");
                        }
                        if (row.auditStatus === "4" || row.auditStatus=="6") {
                            html.push("<a href='javascript:;' onclick='toEditPage(" + row.id + ")'>修改</a><br/>");
                            html.push("<a href='javascript:;' onclick='deleteVideo(" + row.id + ")'>删除</a><br/>");
                        }
                        if (row.auditStatus === "5") {
                            if (!row.tipOff) {
                                html.push("<a href='javascript:;' onclick='toCommentPage(" + row.id + ")'>评论管理</a><br/>");
                            }
                            if (row.status === "1") {
                                html.push("<a href='javascript:;' onclick='videoOffline(" + row.id + ")'>下架</a><br/>");
                            }
                            if (row.status === "0" && !row.tipOff) {
                                html.push("<a href='javascript:;' onclick='toEditPage(" + row.id + ")'>修改</a><br/>");
                                html.push("<a href='javascript:;' onclick='deleteVideo(" + row.id + ")'>删除</a><br/>");
                            }
                            if (row.status === "0" && row.tipOff) {
                                html.push("<a href='javascript:;' onclick='deleteVideo(" + row.id + ")'>删除</a><br/>");
                            }
                        }
                        return html.join("");
                    }
                }
            ]
        }).api();
    }

    function showAuditRemark(obj) {
        swal($(obj).data("content").toString());
    }

    //预览图片
    function openImg(obj) {
        var url = $(obj).attr("src");
        $("#previewImg").attr("src", url);
        $("#previewImgModal").modal();
    }

    function toEditPage(videoId) {
        getContent("${ctx}/video/mgr/" + videoId + "/edit");
        window.scrollTo(0, 0)
    }

    function toCommentPage(videoId) {
        getContent("${ctx}/video/mgr/comment/index?videoId=" + videoId);
        window.scrollTo(0, 0)
    }

    //提交评审
    function submitAudit(videoId) {
        ajaxPost("${ctx}/video/" + videoId + "/mgr/submitAudit", {}, function (result) {
            if (result.success) {
                swalSuccess("提交成功");
                table.ajax.reload();
            } else {
                if (result.returnMsg) {
                    swal(result.returnMsg);
                } else {
                    swalError("提交失败!")
                }
            }
        });
    }

    //删除视频
    function deleteVideo(videoId) {
        swal({
                title: "确定要删除此视频?",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            },
            function () {
                ajaxPost("${ctx}/video/" + videoId + "/mgr/delete", {}, function (result) {
                    if (result.success) {
                        swalSuccess("删除成功");
                        table.ajax.reload();
                    } else {
                        if (result.returnMsg) {
                            swal(result.returnMsg);
                        } else {
                            swalError("删除失败!")
                        }
                    }
                });
            });
    }

    //视频下架
    function videoOffline(videoId) {
        ajaxPost("${ctx}/video/" + videoId + "/mgr/offline", {}, function (result) {
            if (result.success) {
                swalSuccess("操作成功");
                table.ajax.reload();
            } else {
                if (result.returnMsg) {
                    swal(result.returnMsg);
                } else {
                    swalError("操作失败!")
                }
            }
        });
    }

    function videoOnline(videoId) {
        ajaxPost("${ctx}/video/" + videoId + "/mgr/online", {}, function (result) {
            if (result.success) {
                swalSuccess("操作成功");
                table.ajax.reload();
            } else {
                if (result.returnMsg) {
                    swal(result.returnMsg);
                } else {
                    swalError("操作失败!")
                }
            }
        });
    }

    function videoAdd() {
        getContent("${ctx}/video/mgr/add");
    }

    function changeTab(obj, type) {
        $(obj).closest("ul").find("li").removeClass("active");
        $(obj).closest("li").addClass("active");
        $("#searchStatus").val(type);
        if (type === 1) {
            $("#auditStatus").prop("disabled", false);
        } else {
            $("#auditStatus").val("");
            $("#auditStatus").prop("disabled", true);
        }
        table.ajax.reload();
    }

</script>
</body>
</html>
