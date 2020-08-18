<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet"
      type="text/css"/>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>

<style type="text/css">
    body {
        font-size: 12px;
        padding: 10px;
    }

    table.gridtable td.title {
        text-align: left;
    }

    table.gridtable .title {
        border-style: none;
    }

    .ant-btn {
        line-height: 1.499;
        position: relative;
        display: inline-block;
        font-weight: 400;
        white-space: nowrap;
        text-align: center;
        background-image: none;
        border: 1px solid transparent;
        -webkit-box-shadow: 0 2px 0 rgba(0, 0, 0, 0.015);
        box-shadow: 0 2px 0 rgba(0, 0, 0, 0.015);
        cursor: pointer;
        -webkit-transition: all .3s cubic-bezier(.645, .045, .355, 1);
        transition: all .3s cubic-bezier(.645, .045, .355, 1);
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        -ms-touch-action: manipulation;
        touch-action: manipulation;
        height: 32px;
        padding: 0 15px;
        font-size: 14px;
        border-radius: 4px;
        color: rgba(0, 0, 0, 0.65);
        background-color: #fff;
        border-color: #d9d9d9;
    }

    .ant-btn-red {
        color: #fff;
        background-color: #01AAEE;
        border-color: #2D93F6;
        text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.12);
        -webkit-box-shadow: 0 2px 0 rgba(0, 0, 0, 0.045);
        box-shadow: 0 2px 0 rgba(0, 0, 0, 0.045);
    }
</style>

<script type="text/javascript">
    function commit() {
        var id = $("#id").val();
        var pay = parseFloat($("#pay").val());
        var number1 = $("#unpayAmt").val();
        var number2 = $("#needPayAmount").val();
        var unpayAmt = parseFloat(number1);
        var needPayAmount = parseFloat(number2);
        if (pay <= 0) {
            commUtil.alert("要提交的金额必须大于0");
            return;
        }
        if (pay > unpayAmt) {
            // commUtil.alert("要提交的金额必须小于保证金还需补缴金额,unpayAmt:"+ unpayAmt +",pay:"+ pay);
            commUtil.alert("要提交的金额必须小于保证金还需补缴金额");
            return;
        }
        if (pay > needPayAmount) {
            // commUtil.alert("要提交的金额必须小于本结算单还需支付金额,needPayAmount:"+ needPayAmount +",pay:"+ pay);
            commUtil.alert("要提交的金额必须小于本结算单还需支付金额");
            return;
        }
        $.ajax({ //ajax提交
            type: 'POST',
            url: '${pageContext.request.contextPath}' + "/mchtSettleOrder/planPayCommit.shtml",
            dataType: "json",
            data: {id: id, pay: pay},
            cache: false,
            success: function (data) {
                if ("0000" == data.returnCode) {
                    commUtil.alertSuccess("确认成功");
                    setTimeout(function () {
                        // location.reload();
                        // parent.parent.location.reload();
                        parent.location.reload();
                        frameElement.dialog.close();
                    }, 1000);
                } else {
                    $.ligerDialog.error(data.returnMsg);
                }
            },
            error: function (e) {
                commUtil.alertError("系统异常请稍后再试");
            }
        });
    }
</script>

<html>
<body>
<form name="form1" class="form1" method="post" id="form1">
    <input type="hidden" id="id" name="id" value="${id}"/>
    <input type="hidden" id="unpayAmt" name="unpayAmt" value="${unpayAmt}"/>
    <input type="hidden" id="needPayAmount" name="needPayAmount" value="${needPayAmount}"/>
    <div style="text-align: center;">
        <input type="text" id="pay" name="pay" value="${pay}" style="width: 160px;height: 25px;cursor: pointer;"> 元
    </div>
    <br>
    <br>
    <div style="text-align: center;">
        <input type="button" class="ant-btn ant-btn-red" onclick="commit()" value="确认">
    </div>
</form>
</body>
</html>
