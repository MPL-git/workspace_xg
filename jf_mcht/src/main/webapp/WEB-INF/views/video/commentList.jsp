<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="zh-CN">
<head>
    <title>视频评论</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
</head>
<body>
<div class="x_panel container-fluid py-tm">
    <input type="hidden" id="mchtInfoStatus">
    <div class="row content-title">
        <div class="t-title">
            视频评论
            <a href="javascript:void(0);" onclick="backToVideoListPage()">返回</a>
        </div>
    </div>

    <!-- 查询区域 -->
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <input type="hidden" id="videoId" name="videoId" value="${videoId}">
            <div class="form-group">
                <label class="col-md-1 control-label search-lable">评论时间：</label>
                <div class="col-md-5 search-condition">
                    <input class="form-control datePicker" type="text" id="dateBegin" name="dateBegin"
                           data-date-format="yyyy-mm-dd">
                    <i class="picker-split"> - </i>
                    <input class="form-control datePicker" type="text" id="dateEnd" name="dateEnd"
                           data-date-format="yyyy-mm-dd">
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
                <table id="datatable" class="table table-striped table-bordered dataTable no-footer" role="grid"
                       aria-describedby="datatable_info">

                </table>
            </div>
        </div>
    </div>
</div>

<%--回复评论弹窗--%>
<div class="modal fade" id="commentReplyModal" tabindex="-1" role="dialog" aria-labelledby="commentReplyLabel"
     data-backdrop="static">
    <div class="modal-dialog" role="document" style="width:440px;">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <span class="modal-title" id="exampleModalLabel">编辑评论回复</span>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <input type="hidden" id="replyId">
                    <input type="hidden" id="commentId">
                    <textarea rows="5" class="txt-area" id="replyContent" maxlength="500"></textarea>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-info" id="confirm" onclick="commitReply()">保存</button>
                    <button class="btn btn-info" data-dismiss="modal">取消</button>
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
        initDate();
        initTable();
    });

    function initTable() {
        table = $('#datatable').dataTable({
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
                    url: '${ctx}/video/mgr/comment/list',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.success) {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.totalCount;
                        returnData.recordsFiltered = result.returnData.totalCount; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.list;
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
                    width: "160", "title": "评论日期", "render": function (data, type, row, meta) {
                        var createDate = new Date(row.createDate);
                        return createDate.format("yyyy-MM-dd hh:mm");
                    }
                },
                {
                    width: "98", "title": "用户昵称", "render": function (data, type, row, meta) {
                        return "<div>" + row.memberNick + "</div>";
                    }
                },
                {
                    width: "200", "title": "评论内容", "render": function (data, type, row, meta) {
                        return "<div>" + row.content + "</div>";
                    }
                },
                {
                    width: "200", "title": "回复内容", "render": function (data, type, row, meta) {
                        return "<div>" + hasText(row.replyContent) ? row.replyContent : "" + "</div>";
                    }
                },
                {
                    "data": "id", width: "100", "title": "操作", "render": function (data, type, row, meta) {
                        var html = [];
                        if (row.status === "0") {
                            html.push("已隐藏");
                        } else {
                            if (hasText(row.replyId)) {
                                html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='replyComment(" + row.id + "," + row.replyId + ",\"" + row.replyContent + "\")' >修改回复</a><br/>");
                            } else {
                                html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='replyComment(" + row.id + ",\"\"" + ",null)' >回复评价</a><br/>");
                            }
                            html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='hideComment(" + row.id + ")' >隐藏评价</a>");
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

    //打开回复弹窗
    function replyComment(commentId, replyId, replyContent) {
        $("#replyId").val(replyId);
        $("#commentId").val(commentId);
        $("#replyContent").val(replyContent);
        $("#commentReplyModal").modal();
    }

    function commitReply() {
        var data = {};
        data.replyId = $("#replyId").val();
        data.commentId = $("#commentId").val();
        data.content = $("#replyContent").val();
        if (!hasText(data.content)) {
            swalError("回复内容不能为空");
            return;
        }

        ajaxPost("${ctx}/video/mgr/comment/reply", data, function (result) {
            if (result.success) {
                swalSuccess("保存成功");
                table.ajax.reload();
                $("#commentReplyModal").modal("hide");
            } else {
                if (result.returnMsg) {
                    swal(result.returnMsg);
                } else {
                    swalError("操作失败!")
                }
            }
        });
    }

    //隐藏评论
    function hideComment(commentId) {
        swal({
                title: "确定要隐藏该评论?",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            },
            function () {
                ajaxPost("${ctx}/video/mgr/comment/" + commentId + "/hide", {}, function (result) {
                    if (result.success) {
                        swalSuccess("隐藏成功");
                        table.ajax.reload();
                    } else {
                        if (result.returnMsg) {
                            swal(result.returnMsg);
                        } else {
                            swalError("操作失败!")
                        }
                    }
                });
            });
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

    function backToVideoListPage() {
        getContent('${ctx}/video/mgr/index');
    }
</script>
</body>
</html>
