<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>SPOP结算单</title>

    <style type="text/css">
        .content-body{
            padding:10px;
            line-height: 30px;
        }
        #list-content .x_panel{
            width:920px;
        }
    </style>
</head>

<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="col-md-12">SPOP结算单</div>
    </div>
    <div class="row content-body">
        <div class="col-md-12">
            结算单ID：${model.id}，
            结算日期：<fmt:formatDate value='${model.beginDate}' pattern='yyyy-MM-dd'/>  到 <fmt:formatDate value='${model.endDate}' pattern='yyyy-MM-dd'/>
        </div>
        <div class="col-md-12">
            商品数量：${model.productNum}件，
            订单商品货款：${model.settlePriceTotal}元，
            订单商家优惠：${model.mchtPreferentialTotal}元，
            直赔单金额：${model.refundAmount}元
        </div>
        <div class="col-md-12">
            结算单金额：${model.settleAmount}元，
<%--            开票状态：${model.get("mchtInvoiceStatusStr")}--%>
<%--            <c:if test="${model.mchtInvoiceStatus == '1'}">--%>
<%--                <button type="button"  class="btn btn-info" id="btn-invoice">操作</button>--%>
<%--            </c:if>--%>
        </div>
        <div class="col-md-12">
            抵缴保证金：${model.depositAmount}元，
            本次结算单平台需支付金额：${model.needPayAmount}元，
            付款状态：${model.get("payStatusStr")}
        </div>
        <div class="col-md-12">
            确认状态：${model.get("confirmStatusStr")}
            <c:if test="${model.confirmStatus == '1'}">
                <button type="button"  class="btn btn-info" id="btn-confirm">操作</button>
            </c:if>
            <c:if test="${not empty model.mchtConfirmDate}">
                商家确认日期：<fmt:formatDate value='${model.mchtConfirmDate}' pattern='yyyy-MM-dd'/>
            </c:if>
            <c:if test="${not empty model.platformConfirmDate}">
                平台确认日期：<fmt:formatDate value='${model.platformConfirmDate}' pattern='yyyy-MM-dd'/>
            </c:if>
        </div>
        <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="clearfix"></div>
    <ul class="nav nav-tabs q" role="tablist">
        <li role="presentation" class="active"> <a href="#" role="tab" data-toggle="tab" onclick="listOrder(this);">订单明细</a></li>
        <li role="presentation"> <a href="#" role="tab" data-toggle="tab" onclick="listRefundOrder(this);">直赔单</a></li>
    </ul>
    <div id="list-content"></div>
</div>
<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel" data-backdrop="static"></div>

<!-- Bootstrap -->
<script type="text/javascript" src="${ctx}/static/js/dateUtil.js"></script>
<script>

    $(document).ready(function () {

        listOrder();

        // $('#btn-invoice').on('click', function (event) {
        //     invoice();
        // });

        $('#btn-confirm').on('click', function (event) {
            confirm();
        });
    });

    function listOrder(obj){
        if($(obj).parent().hasClass("active"))  return;

        list("${ctx}/mchtSettleOrder/listOrderPage?mchtSettleOrderId=${model.id}");
    }

    function listRefundOrder(obj){
        if($(obj).parent().hasClass("active"))  return;

        list("${ctx}/mchtSettleOrder/listRefundOrderPage?mchtSettleOrderId=${model.id}");
    }

    function list(url){
        $.ajax({
            url: url,
            type: "GET",
            async: false,
            success: function(data){
                $("#list-content").html(data);
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }

    function invoice() {
        var url = "${ctx}/mchtSettleOrder/invoice?id=${model.id}";
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

    function confirm(){
        swal({
            title: "确认结算单 ",
            text: "在确认前请您检查商家税票信息、商家银行账号是否有误。 请您对此结算单的金额进行确认。<span style='color:#f00;'>若您的保证金缴纳不足，在您确认之后，平台可能会将部分金额抵缴到保证金。</span>确认之后，请开具增值税专用发票给平台，平台收到票后会排款给商家。",
            html:true,
            type: "warning",
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnConfirm: true
        },
       function(){
           var url = "${ctx}/mchtSettleOrder/confirm?id=${model.id}";
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
            <%--/*var postData= {"id":${model.id}};--%>
            <%--$.ajax({--%>
            <%--    url : "${ctx}/mchtSettleOrder/confirmCommit",--%>
            <%--    type : 'POST',--%>
            <%--    dataType : 'json',--%>
            <%--    cache : false,--%>
            <%--    async : false,--%>
            <%--    data : postData,--%>
            <%--    timeout : 30000,--%>
            <%--    success : function(result) {--%>
            <%--        if (result.success) {--%>
            <%--            getContent("${ctx}/mchtSettleOrder/detail?id=${model.id}")--%>
            <%--        } else {--%>
            <%--            swal({--%>
            <%--                title: result.returnMsg,--%>
            <%--                type: "error",--%>
            <%--                confirmButtonText: "确定",--%>
            <%--                closeOnConfirm: true--%>
            <%--            });--%>
            <%--        }--%>

            <%--    },--%>
            <%--    error : function() {--%>
            <%--        swal({--%>
            <%--            title: "处理失败！",--%>
            <%--            type: "error",--%>
            <%--            timer: 1500,--%>
            <%--            confirmButtonText: "确定"--%>
            <%--        });--%>
            <%--    }--%>
            <%--});*/--%>
        });
    }

</script>
</body>
</html>
