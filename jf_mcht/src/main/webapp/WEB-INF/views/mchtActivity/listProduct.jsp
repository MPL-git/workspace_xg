<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>活动商品管理</title>

    <style type="text/css">
        .color-1{
            color: #9D999D;
        }
        .color-2{
            color: #FC0000;
        }
        .color-3{
            color: #EFD104;
        }
        .color-4{
            color: #00FC28;
        }
        .color-5{
            color: #0351F7;
        }
        .color-6{
            color: #DF00FB;
        }
        .hidden{
            display:none;
        }
        #popDiv {
            width:446px;
            height:294px;
            position:absolute;
            left:238px;
            top:90px;
            z-index:1;
            border:4px solid #7A7A7A;
            background-color: #f2f2f2;
        }
        .remarks_column_class{
            text-align: left;
        }
    </style>
</head>

<body>
<div class="x_panel container-fluid">
    <input type="hidden" id="mchtInfoStatus">
    <div class="row content-title">
        <div class="t-title">
            活动商品管理
            <a href="javascript:void(0);" onclick="getContent('${ctx}/mcht/activity/view?id=${activityAreaId}')">返回</a>
        </div>
    </div>
    <div class="search-container container-fluid">
        <form class="form-horizontal" id="searchForm">
            <input type="hidden" name="activityId" value="${activityId}"/>
            <input type="hidden" name="status" value="${status}"/>
            <div class="form-group">
                <div class="col-md-2 search-condition" >
                    <select class="form-control" name="searchKeywrodType" id="searchKeywrodType">
                        <option value="1">商品名称</option>
                        <option value="2">商品货号</option>
                        <option value="4">商品ID</option>
                        <option value="5">特卖ID</option>
                        <option value="3">商家备注</option>
                    </select>
                </div>
                <div class="col-md-3 search-condition" >
                    <input class="form-control nameWidth200" type="text"  id="searchKeywrod" name="searchKeywrod" >
                </div>

                <!--  <div id="popDiv" class="hidden">
                     <div><span id="closeDiv">关闭</span></div>
                     <div>
                         <span>请输入货号：一行一个</span>
                         <textarea name="artNos" rows="8"></textarea>
                     </div>
                     <div>
                         <button type="button"  class="btn btn-info" id="btn-query-arts">查询</button>
                     </div>
                 </div> -->

                <div class="modal fade" id="popDivs" tabindex="-1" role="dialog">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            </div>
                            <div class="modal-body">
                                <p>请输入货号：<span class="c9">(一行一个)</span></p>
                                <div>
                                    <textarea id="artNos" name="artNos" rows="8" style="width: 100%;resize: none;"></textarea>
                                </div>
                                <div class="modal-footer" style="padding: 10px 0 0">
                                    <button type="button"  class="btn btn-info" id="btn-query-arts">查询</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-md-5 text-center search-btn">
                    <button type="button"  class="btn btn-info" id="btn-query">查询
                    </button>
                    <button type="button"  class="btn btn-default" id="btn-query-pop">多货号批量搜索
                    </button>
                </div>
            </div>
        </form>

    </div>
    <div class="clearfix"></div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation" <c:if test="${status==2}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="listProduct(2);">已报名商品（${normalCount}）</a></li>
        <li role="presentation" <c:if test="${status==1}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="listProduct(1);">可报名商品（${waitCount}）</a></li>
        <li role="presentation" <c:if test="${status==3}">class="active"</c:if>> <a href="#" role="tab" data-toggle="tab" onclick="listProduct(3);">已驳回商品（${rejectCount}）</a></li>
    </ul>
    <div class="x_content container-fluid">
        <div class="row">
            <div class="col-md-12 datatable-container">
                <c:if test="${status==1}">
                    <div class="datatable-caption">
                        <c:if test="${status==1}">
                            <span class="mr">
                                <input type='checkbox' class='selectAll'>全选
                            </span>
                        </c:if>
                        <button type="button" class="btn btn-all" id="btn-batch-add">批量报名</button>
                    </div>
                </c:if>
                <!--
				<c:if test="${status==1 && (activity.status==1 || activity.status==4)}">
                    <div class="datatable-caption">
                        <c:if test="${status==1}">
                            <span class="mr">
                                <input type='checkbox' class='selectAll'>全选
                            </span>
                        </c:if>
                        <button type="button" class="btn btn-all" id="btn-batch-add">批量报名</button>
                    </div>
                </c:if>
				-->
                <!--
            	<c:if test="${status==2 && (activity.status==1 || activity.status==4)}">
                    <div class="datatable-caption">
                        <button type="button" class="btn btn-all" id="btn-commit" style="margin-left: 10px;">提交审核</button>
                    </div>
                </c:if>
                -->

                <table id="datatable"
                       class="table table-striped table-bordered dataTable no-footer"
                       role="grid" aria-describedby="datatable_info">
                    <thead>
                    <tr role="row">
                        <th>商品信息</th>
                        <th>商品备注</th>
                        <th width="88">活动价（元）</th>
                        <c:if test="${mchtInfo.mchtType==1}">
                            <th width="88">结算价(元)</th>
                        </c:if>
                        <th width="88">SVIP折扣</th>
                        <th width="68">库存</th>
                        <th width="88">销量</th>
                        <th width="68">操作</th>
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
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document" style="width: 1010px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body"></div>
        </div>
    </div>
</div>

<div class="modal fade" id="setRemarksModal" tabindex="-1" role="dialog" aria-labelledby="setRemarksModal" data-backdrop="static" >
    <div id="test" class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">商品备注</h4>
            </div>
            <div class="modal-body">
                <div>
                    <form id="dataFrom">
                        <label for="name" class="money">颜色标记：</label>
                        <div style="display: inline-block" id="remarksColors">
                            <span onclick="selectRemarks(1);" class="glyphicon glyphicon-flag color-1" aria-hidden="true"></span>
                            <span onclick="selectRemarks(2);" class="glyphicon glyphicon-flag color-2" aria-hidden="true"></span>
                            <span onclick="selectRemarks(3);" class="glyphicon glyphicon-flag color-3" aria-hidden="true"></span>
                            <span onclick="selectRemarks(4);" class="glyphicon glyphicon-flag color-4" aria-hidden="true"></span>
                            <span onclick="selectRemarks(5);" class="glyphicon glyphicon-flag color-5" aria-hidden="true"></span>
                            <span onclick="selectRemarks(6);" class="glyphicon glyphicon-flag color-6" aria-hidden="true"></span></a>

                        </div>
                        <br>
                        <label for="name" class="money">备注内容：</label>
                        <textarea style="display: inline-block;width:60%;" rows="3"  id="remarks" name="remarks" validate="{maxlength:60}" placeholder="限60个字符"></textarea>
                        <br>
                        <br>
                        <div style="margin-left: 53px;">
                            <div id="selectedRemarksDiv" style="display: inline-block;">
                            </div>
                            <div style="display: inline-block;text-align: center;" id="remarksBtnDiv">
                                <input type="hidden" id="remarksColor" value="" name="remarksColor">
                                <input type="hidden" id="ids" value="" name="ids">
                                <button type="button" class="btn btn-default" style="margin-right: 17px;" onclick="saveRemarks();">提交</button>
                                <button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                        <br>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<script>
    function setRemarks(productId,remarksColor,remarks){
        $("#selectedRemarksDiv").html('<span style="padding:0 10px;font-size:20px;" class="glyphicon glyphicon-flag color-'+remarksColor+'" aria-hidden="true"></span>');
        $("#remarksColor").val(remarksColor);
        if($.trim(remarks)!="null"){
            $("#remarks").val($.trim(remarks));
        }else{
            $("#remarks").val("");
        }
        $("#ids").val(productId);
        $("#setRemarksModal").modal();
    }

    var table;
    $(document).ready(function () {

        $.metadata.setType("attr", "validate");
        dataFromValidate =$("#dataFrom").validate({});

        $(".selectAll").change(function(){
            if($(this).is(':checked')) {
                $("input[name='productId']").prop('checked',true);
            }else{
                $("input[name='productId']").prop('checked',false);
            }
        });

        $("#btn-batch-add").click(function(){
            var productIds=[];
            $("input[name='productId']:checked").each(function(){
                productIds.push($(this).val());
            });
            if(productIds.length == 0){
                if($(".selectAll").is(':checked')) {
                    swal("商品没有设置SVIP折扣无法报名");
                    return false;
                }else{
                    swal("请选择要报名的商品");
                    return false;
                }
            }
            addProducts(productIds);
        });

        $("#btn-commit").click(function(){
            var mchtInfoStatus = $("#mchtInfoStatus").val();
            if(mchtInfoStatus == "2"){
                swal("对不起，合作状态暂停，不能提报活动");
                return false;
            }
            var postData= {};
            postData.activityId = ${activityId};
            var url = "${ctx}/mcht/activity/commitActivity";
            $.ajax({
                url: url,
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : postData,
                timeout : 30000,
                success: function (data) {
                    if (data.success) {
                        swal({
                            title: "提审成功!",
                            type: "success",
                            confirmButtonText: "确定"
                        });
                        getContent("${ctx}/mcht/activity");
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
                        title: "提交失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            });

        });


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
                    url: '${ctx}/mcht/activity/listProduct',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.success) {
                        var returnData = {};
                        returnData.recordsTotal = result.data.page.totalRow;
                        returnData.recordsFiltered = result.data.page.totalRow; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.data.page.list;
                        callback(returnData);
                        var mchtInfoStatus = result.data.mchtInfoStatus;
                        $("#mchtInfoStatus").val(mchtInfoStatus);
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
                // <c:if test="${status==1}">
                // {
                //     "data": "id", "render": function (data, type, row, meta) {
                //     var html = [];
                //     html.push('<input type="checkbox" name="productId" value="', data ,'">');
                //     return html.join("");
                // }},
                // </c:if>
                {"data": "id","render": function (data, type, row, meta) {
                        var html = [];

                        <c:if test="${status==1}">
                        html.push('<div class="is-check">');
                        // if(row.svipDiscount){
                            html.push('<div class="width-33"><input type="checkbox" name="productId" value="' + data + '" /></div>');
                        // }
                        </c:if>

                        <c:if test="${status!=1}">
                        html.push('<div class="no-check">');
                        </c:if>
                        if(row.pic){
                            var pic=row.pic.substring(0, row.pic.lastIndexOf("."))+"_M"+row.pic.substring(row.pic.lastIndexOf("."));
                            html.push('<div class="width-60"><img src="${ctx}/file_servelt'+pic+'"></div>');
                        }else{
                            html.push('<div class="width-60"><img src="${ctx}/file_servelt"></div>');
                        }
                        html.push('<div class="width-226"><a href="https://m.xgbuy.cc/share_buy.html?id='+row.code+'" target="_blank">'+row.name+'</a><br><p style="color: #999;margin: 5px 0 0;">货号：'+row.artNo+'&nbsp;&nbsp;&nbsp;&nbsp;</p><p style="color: #999;margin: 0;">ID：'+row.code+'</p></div>');
                        html.push("</div>");

                        <c:if test="${status==3}">
                        if(row.activityProductRemarks){
                            html.push('<div class="f55 active-bh">驳回原因：'+row.activityProductRemarks+'</div>');
                        }
                        </c:if>

                        return html.join("");
                    }},
                {"data": "remarks", "width":"134", "title":"商品备注","className": "remarks_column_class","render": function (data, type, row, meta) {
                        var html = [];
                        html.push("<span onclick='setRemarks("+row.id+',"'+row.remarksColor+'"'+',"'+row.remarks+'"'+");' class='t-flag glyphicon glyphicon-flag "+"color-"+row.remarksColor+"' aria-hidden='true'></span>");
                        html.push(data);
                        return html.join("");
                    }},
                {"data": "id","render": function (data, type, row, meta) {
                        var html = [];
                        html.push(row.minSalePrice);
                        if(row.minSalePrice!=row.maxSalePrice){
                            html.push("-", row.maxSalePrice);
                        };
                        return html.join("");
                    }},
                //根据商家类型spop进行选择性展示
                <c:if test="${mchtInfo.mchtType==1}">
                {"data": "id","render": function (data, type, row, meta) {
                        var html = [];
                        html.push(row.minSettlePrice);
                        if(row.minSettlePrice!=row.maxSettlePrice){
                            html.push("~", row.maxSettlePrice);
                        };
                        return html.join("");
                    }},
                </c:if>
                {"data": "id",width:"78","title":"SVIP折扣","render": function (data, type, row, meta) {
                        if(!row.svipDiscount){
                            return "/";
                        }else{
                            return row.svipDiscount*10;
                        }
                    }},
                {"data": "stock"},
                {"data": "saleQuantity"},
                {
                    "data": "id", "render": function (data, type, row, meta) {
                        var html = [];
                        <c:if test="${status==2 && (activity.status==1 || activity.status==4)}">
                        html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="cancelProduct(', "'"+row.id+"'", ');" >退出活动</a>');
                        html.push('<br/>');
                        </c:if>
                        <c:if test="${status==1}">
                        html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="editProduct(', "'"+row.id+"'", ');" >修改商品</a>');
                        html.push('<br/>');
                        if(row.svipDiscount){
                            html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="addProduct(', "'"+row.id+"'", ');" >立即报名</a>');
                        }
                        html.push('<br/>');
                        </c:if>
                        <c:if test="${status==3}">
                        html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="editProduct(', "'"+row.id+"'", ');" >修改商品</a>');
                        html.push('<br/>');
                        html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="cancelProduct(', "'"+row.id+"'", ');" >退出活动</a>');
                        html.push('<br/>');
                        if(row.svipDiscount){
                            html.push('<a class="table-opr-btn" href="javascript:void(0);" onclick="addProduct(', "'"+row.id+"'", ');" >重新报名</a>');
                        }
                        html.push('<br/>');
                        </c:if>
                        return html.join("");
                    }
                }
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            $("#artNos").val("");
            table.ajax.reload();
        });

        $("#searchKeywrod").keydown(function(e){
            if(e.keyCode==13){
                table.ajax.reload();
                return false;
            }
        });

        $('#btn-query-arts').on('click', function (event) {
            $("#searchKeywrod").val("");
            table.ajax.reload();
        });

        $('#btn-query-pop').on('click', function (event) {
            // $("#popDiv").removeClass("hidden");
            $("#popDivs").modal();
        });

        $("#closeDiv").click(function(){
            $("#popDiv").addClass("hidden");
        })
    });

    function listProduct(status) {
        var url = "${ctx}/mcht/activity/listProductPage?activityId=${activityId}&status=" + status;
        getContent(url);
    }

    function editProduct(productId) {
        getContent("${ctx}/product/productEdit?id="+productId);

        <%--var url = "${ctx}/product/productEdit?id=" + productId;--%>
        <%--$.ajax({--%>
        <%--url: url,--%>
        <%--type: "GET",--%>
        <%--success: function (data) {--%>
        <%--$("#viewModal").html(data);--%>
        <%--$("#viewModal").modal();--%>
        <%--},--%>
        <%--error: function () {--%>
        <%--swal('页面不存在');--%>
        <%--}--%>
        <%--});--%>
    }

    function addProduct(productId) {
        var productIds=[];
        productIds.push(productId);
        addProducts(productIds);
    }

    function addProducts(productIds) {
        var postData= {};
        postData.activityId = ${activityId};
        postData.productIds = JSON.stringify(productIds);
        var url = "${ctx}/mcht/activity/addProduct";
        $.ajax({
            url: url,
            type : 'POST',
            dataType : 'json',
            cache : false,
            async : false,
            data : postData,
            timeout : 30000,
            success: function (data) {
                if (data.success) {
                    //table.ajax.reload();
                    listProduct(${status});
//                    swal({
//                        title: "报名成功!",
//                        type: "success",
//                        confirmButtonText: "确定"
//                    });
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
                    title: "提交失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
        });
    }

    function cancelProduct(productId) {
        var productIds=[];
        productIds.push(productId);
        var postData= {};
        postData.activityId = ${activityId};
        postData.productIds = JSON.stringify(productIds);

        var url = "${ctx}/mcht/activity/cancelProduct";
        $.ajax({
            url: url,
            type : 'POST',
            dataType : 'json',
            cache : false,
            async : false,
            data : postData,
            timeout : 30000,
            success: function (data) {
                if (data.success) {
                    //table.ajax.reload();
                    listProduct(${status});
                    //swal({
                    //    title: "退出活动成功!",
                    //    type: "success",
                    //    confirmButtonText: "确定"
                    //});
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
                    title: "提交失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
        });
    }


    // 商品备注
    function setRemarks(productId,remarksColor,remarks){
        $("#selectedRemarksDiv").html('<span style="padding:0 10px;font-size:20px;" class="glyphicon glyphicon-flag color-'+remarksColor+'" aria-hidden="true"></span>');
        $("#remarksColor").val(remarksColor);
        if($.trim(remarks)!="null"){
            $("#remarks").val($.trim(remarks));
        }else{
            $("#remarks").val("");
        }
        $("#ids").val(productId);
        $("#setRemarksModal").modal();
    }

    function selectRemarks(color){
        $("#remarksColor").val(color);
        $("#selectedRemarksDiv").html('<span style="padding:0 10px;font-size:20px;" class="glyphicon glyphicon-flag color-'+color+'" aria-hidden="true"></span>');
    }
    var dataFromValidate;
    function saveRemarks(){
        if(dataFromValidate.form()){
            var dataJson = $("#dataFrom").serializeArray();
            $.ajax({
                url : "${ctx}/product/setRemarks",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : dataJson,
                timeout : 30000,
                success : function(data) {
                    if (data.returnCode == "0000") {
                        table.ajax.reload( null, false );
                        $("#setRemarksModal").modal('hide');
                    } else {
                        swal({
                            title: '备注失败',
                            type: "error",
                            confirmButtonText: "确定"
                        });
                        return;
                    }

                },
                error : function() {
                    swal({
                        title: '备注失败',
                        type: "error",
                        confirmButtonText: "确定"
                    });
                    return;
                }
            });
        }
    }

</script>
</body>
</html>
