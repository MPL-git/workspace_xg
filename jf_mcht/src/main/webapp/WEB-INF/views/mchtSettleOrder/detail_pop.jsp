<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>POP结算单</title>

    <style type="text/css">
        .content-body{
            padding:10px;
            line-height: 30px;
        }
        #list-content .x_panel{
            width: 100%;
            margin-bottom: 20px;
            border-radius: 0;
        }


        .nav>li>a {
            margin: 0;
            border-radius: 0;
        }
        .nav>li.active>a {
            margin-bottom: -1px;
            z-index: 1;
            border-bottom-color: #fff;
        }
        .nav-tabs {
            border: none;
        }
    </style>
</head>

<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="col-md-12">POP结算单</div>
    </div>
    <div class="row content-body">
        <div class="col-md-12">
            结算单ID：${model.id}，
            结算日期：<fmt:formatDate value='${model.beginDate}' pattern='yyyy-MM-dd'/>  到 <fmt:formatDate value='${model.endDate}' pattern='yyyy-MM-dd'/>
        </div>
        <div class="col-md-12">
            结算单金额：${model.settleAmount}元
            <a id="view1" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" data-html="true"
               title="结算单金额计算规则">【查看计算规则】</a>
        </div>
        <div class="col-md-12">
            商品数量：${model.productNum}件，
            订单客户实付金额：${model.orderAmount}元，
            直赔单金额：${model.refundAmount}元，
            技术服务费：${model.commissionAmount}元，
            平台折扣：${model.get("platformDiscount")}元，        
            开票类型：${model.get("mchtCollectTypeStr")}
        </div>
        <div class="col-md-12">
            抵缴保证金：${model.depositAmount}元，
            本次结算单平台需支付金额：${model.get("needSettleAmount")}元，
            付款状态：${model.get("payStatusStr")}<c:if test="${model.payStatus == 3 || model.payStatus == 4}">（付款金额：${model.payAmount}元）</c:if>
        </div>
        <div class="col-md-12">
            确认状态：${model.get("confirmStatusStr")}
            <c:if test="${model.confirmStatus == '1'}">
                <button type="button"  class="btn btn-info" id="btn-confirm">操作</button>
            </c:if>

            <c:if test="${not empty model.mchtConfirmDate}">
                ，商家确认日期：<fmt:formatDate value='${model.mchtConfirmDate}' pattern='yyyy-MM-dd'/>
            </c:if>
            <c:if test="${not empty model.platformConfirmDate}">
                ，平台确认日期：<fmt:formatDate value='${model.platformConfirmDate}' pattern='yyyy-MM-dd'/>
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

        //$('[data-toggle="popover"]').popover();
        $("#view1").popover({
            content:function(){
                var html = new Array();
                html.push('<div>结算单金额 =（商品金额 - 商家优惠） x （1 - 技术服务费率） - 直赔单金额<div>');
                html.push('<br/>');
                html.push('<div>技术服务费 = （商品金额 - 商家优惠）× 技术服务费率<div>');
                return html;
            }
        })

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

    function confirm() {
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
    }

</script>
</body>
</html>
