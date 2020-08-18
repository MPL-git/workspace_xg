<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>我的违规</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
</head>

<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="t-title">
            合作变更管理
            <c:if test="${empty noAuth}">
                <a href='javascript:void(0);' onclick="editCooperationChangeApply('');">申请</a>
            </c:if>
        </div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <div class="form-group">
                <label for="productBrand" class="col-md-1 control-label search-lable">申请时间：</label>
                <div class="col-md-5 search-condition">
                    <input class="form-control datePicker" type="text"  id="createDateBegin" name="createDateBegin" data-date-format="yyyy-mm-dd">
                    <i class="picker-split">-</i>
                    <input class="form-control datePicker" type="text"  id="createDateEnd" name="createDateEnd" data-date-format="yyyy-mm-dd">
                </div>

                <label for="productBrand" class="col-md-1 control-label search-lable">变更类型：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="changeApplyType" id="changeApplyType">
                        <option value="">--请选择--</option>
                        <option value="1">店铺名称变更</option>
                        <option value="2">店铺主营类目变更</option>
                        <option value="3">品牌技术服务费变更</option>
                        <option value="4">保证金变更</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="productBrand" class="col-md-1 control-label search-lable">审核状态：</label>
                <div class="col-md-2 search-condition">
                    <select class="form-control" name="auditStatus" id="auditStatus">
                        <option value="">--请选择--</option>
                        <option value="0">待审</option>
                        <option value="1">审核中</option>
                        <option value="2">通过</option>
                        <option value="3">驳回</option>
                    </select>
                </div>

                <label for="productBrand" class="col-md-1 control-label search-lable">归档状态：</label>
                <div class="col-md-2 search-condition" >
                    <select class="form-control" name="archiveStatus" id="archiveStatus">
                        <option value="">--请选择--</option>
                        <c:forEach items="${archiveStatusList}" var="archiveStatus">
                            <option value="${archiveStatus.statusValue}">${archiveStatus.statusDesc}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">搜索</button>
                </div>
            </div>

        </form>

    </div>
    <div class="clearfix"></div>
    <span style="color: red;">盖章提醒：文件两页及两页已上请加盖骑缝章</span>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container at-table">
                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">

                </table>
            </div>
        </div>
    </div>
</div>


<!-- 	查看信息框 -->

<div class="modal fade" id="changeViewModal" tabindex="-1" role="dialog" aria-labelledby="changeViewModalLabel" data-backdrop="static">
</div>
<!-- 	查看信息框 -->
<div class="modal fade" id="myViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>
<script
        src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/producttype-select.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>
    var table;

    $(document).ready(function () {

        $('.datePicker').datetimepicker(
            {
                minView: "month", //选择日期后，不会再跳转去选择时分秒
                format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
                language: 'zh-CN', //汉化
                autoclose:true //选择日期后自动关闭
            }
        );

        table = $('#datatable').dataTable({
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm").serializeArray();

                param.push({"name":"pagesize","value":data.length});
                if (data.start > 0) {
                    param.push({"name":"page","value":data.start/data.length+1});
                } else {
                    param.push({"name":"page","value":1});
                }


                $.ajax({
                    method: 'POST',
                    url: '${ctx}/cooperationChangeApply/getCooperationChangeApplyList',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode =='0000') {
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
            "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支  持CSS伪类的场合
            "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
            "processing": true,  // 隐藏加载提示,自行处理
            "serverSide": true,   // 启用服务器端分页
            "searching": false,   // 禁用原生搜索
            "bSort": false,
            "bServerSide": true,
            "columns": [
                {"data": "id","title":"申请时间","render": function (data, type, row, meta) {
                        var createDate = new Date(row.createDate);
                        return createDate.format("yyyy-MM-dd hh:mm:ss");
                    }},
                {"data": "id","title":"变更类型","render": function (data, type, row, meta) {
                        var html = [];
                        var changeApplyType = row.changeApplyType;
                        var array = changeApplyType.split(",");
                        for(var i=0;i<array.length;i++){
                            if(array[i] == 1){
                                html.push("店铺名称变更<br>");
                            }else if(array[i] == 2){
                                html.push("店铺主营类目变更<br>");
                            }else if(array[i] == 3){
                                html.push("品牌技术服务费变更<br>");
                            }else if(array[i] == 4){
                                html.push("保证金变更<br>");
                            }
                        }
                        return html.join("");
                    }},
                {"data": "id","title":"招商审核状态","render": function (data, type, row, meta) {
                        if(row.zsAuditStatus == 0){
                            return "待审";
                        }else if(row.zsAuditStatus == 2){
                            return "驳回<br><a href='javascript:;' onclick='toAuditRemarks("+row.id+");'>【查看原因】</a>";
                        }else if(row.zsAuditStatus == 1){
                            return "通过";
                        }
                    }},
                {"data": "id","title":"资料审核状态","render": function (data, type, row, meta) {
                        if(row.fwAuditStatus == 1){
                            return "通过";
                        }else if(row.fwAuditStatus == 2){
                            return "驳回<br><a href='javascript:;' onclick='toAuditRemarks("+row.id+");'>【查看原因】</a>";
                        }else if(row.uploadStatus != 1 ){
                            return "未上传";
                        }else if(row.uploadStatus == 1){
                            return "已上传";
                        }
                        return "";
                    }},
                {"data": "id","title":"寄件及归档状态","render": function (data, type, row, meta) {
                        if(row.sendStatus != 1 && row.archiveStatus == 0 || row.archiveStatus == null){
                            return "未寄件";
                        }else if (row.sendStatus == 1 && row.archiveStatus == 0 || row.archiveStatus == null){
                            return "已寄件";
                        }
                        if(row.archiveStatus == 2){
                            return row.archiveStatusDesc+"<br><a href='javascript:;' onclick='toArchiveRemarks("+row.id+");'>【查看原因】</a>";
                        }else if(row.archiveStatus == 1){
                            return row.archiveStatusDesc;
                        }
                        return "";
                    }},
                 {"data": "id","title":"操作","render": function (data, type, row, meta) {
                        var html = [];
                        html.push('<a href="javascript:;" onclick="viewCooperationChangeApply('+row.id+')">查看</a><br>');
                        if(row.zsAuditStatus == 2 ){
                            html.push('<a href="javascript:;" onclick="editCooperationChangeApply('+row.id+')">修改</a><br>');
                        }
                        if(row.sendStatus != 1 && row.fwAuditStatus == 1 && row.archiveStatus != 1){
                            html.push('<a href="javascript:;" onclick="toSend('+row.id+')">立即寄件</a><br>');
                        }
                        // if(row.archiveStatus && row.archiveStatus != 1){
                        // 	html.push('<a href="javascript:" onclick="toPrint('+row.id+');">【打印变更函】</a>');
                        // }
                        if(row.zsAuditStatus == 1 && row.fwAuditStatus != 1  && row.uploadStatus != 1){
                            html.push('<a href="javascript:;" onclick="toPrintAndUpload('+row.id+');">打印并上传</a>');
                        }
                        return html.join("");
                    }}
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });

    });
    function viewCooperationChangeApply(id){
        $.ajax({
            url: "${ctx}/cooperationChangeApply/viewCooperationChangeApply?id="+id,
            type: "GET",
            success: function(data){
                $("#changeViewModal").html(data);
                $("#changeViewModal").modal();
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }

    function editCooperationChangeApply(id){
        $.ajax({
            url: "${ctx}/cooperationChangeApply/editCooperationChangeApply?id="+id,
            type: "GET",
            success: function(data){
                $("#changeViewModal").html(data);
                $("#changeViewModal").modal();
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }

    function toSend(id){
        $.ajax({
            url: "${ctx}/cooperationChangeApply/toSend?id="+id,
            type: "GET",
            success: function(data){
                $("#changeViewModal").html(data);
                $("#changeViewModal").modal();
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }

    function toArchiveRemarks(id){
        $.ajax({
            url: "${ctx}/cooperationChangeApply/toArchiveRemarks?id="+id,
            type: "GET",
            success: function(data){
                $("#changeViewModal").html(data);
                $("#changeViewModal").modal();
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }

    function toAuditRemarks(id){
        $.ajax({
            url: "${ctx}/cooperationChangeApply/toAuditRemarks?id="+id,
            type: "GET",
            success: function(data){
                $("#changeViewModal").html(data);
                $("#changeViewModal").modal();
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }

    <%--function showInfo(url){--%>
        <%--if(url == "")	return;--%>
        <%--$.ajax({--%>
            <%--url: url,--%>
            <%--type: "GET",--%>
            <%--success: function(data){--%>
                <%--$("#changeViewModal").html(data);--%>
                <%--$("#changeViewModal").modal();--%>
            <%--},--%>
            <%--error:function(){--%>
                <%--swal('页面不存在');--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

    <%--function toPrint(id){--%>
        <%--showInfo('${ctx}/cooperationChangeApply/changeAgreementPreview?id='+id);--%>
    <%--}--%>

    function toPrintAndUpload(id){
        $.ajax({
            url: "${ctx}/cooperationChangeApply/toPrintAndUpload?id="+id,
            type: "GET",
            success: function(data){
                $("#changeViewModal").html(data);
                $("#changeViewModal").modal();
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }
</script>
</body>
</html>
