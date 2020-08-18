<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .table-edit-activity-time div,
        .table-edit-activity-time span {
            display: inline-block;
            vertical-align: middle;
        }
    </style>
    <script type="text/javascript">
        //判断字符是否为空
        function isEmpty(obj){
            return (typeof obj === 'undefined' || obj === null || obj === "");
        }


        //提交规则
        function saveRule(){
            var activityPriceLimit=$("#activityPriceLimit").val();
            var depositLimit=$("#depositLimit").val();
            var minRate=$("#minRate").val();
            var maxRate=$("#maxRate").val();
            var offsetMultiple=$("#offsetMultiple").val();
            if(isEmpty(activityPriceLimit) ||  isEmpty(depositLimit) || isEmpty(minRate) || isEmpty(maxRate) || isEmpty(offsetMultiple)){
                commUtil.alertError("规则均不能为空！");
                return;
            }
            if(Number(depositLimit) >= Number(activityPriceLimit)){
                commUtil.alertError("定金不可大于等于预付商品金额！");
                return;
            }
            if(Number(depositLimit) > 99999999 || Number(activityPriceLimit) > 99999999 || Number(offsetMultiple) > 99999999){
                commUtil.alertError("定金,定金抵扣倍数或商品金额数值过大！");
                return;
            }
            if(!/^[1-9]\d*$/.test(minRate) || !/^[1-9]\d*$/.test(maxRate)){
                commUtil.alertError("预付金额百分比要为正整数");
                return;
            }
            if(Number(maxRate) <= Number(minRate)){
                commUtil.alertError("预付金额百分比设置错误！");
                return;
            }
            if( Number(minRate) > 100  || Number(maxRate) > 100 ){
                commUtil.alertError("预付金额百分比设置错误！");
                return;
            }
            if(Number(offsetMultiple) <= 0){
                commUtil.alertError("定金抵扣倍数必须大于零！");
                return;
            }
            $(window.parent.document).find("#activityPriceLimit").val(Number(activityPriceLimit).toFixed(2));
            $(window.parent.document).find("#depositLimit").val(Number(depositLimit).toFixed(2));
            $(window.parent.document).find("#minRate").val(minRate);
            $(window.parent.document).find("#maxRate").val(maxRate);
            $(window.parent.document).find("#offsetMultiple").val(Number(offsetMultiple).toFixed(2));
            frameElement.dialog.close();
        }

    </script>

</head>
<body style="margin: 10px; margin-bottom: 200px;">
<form name="form1" class="form1" method="post" id="form1" action="${pageContext.request.contextPath}/activityAreaNew/addOrUpdateActivityArea.shtml" >
    <table class="gridtable">
        <tr>
            <td align="left" class="l-table-edit-td" >
                <div>预付金额不可低于<input style="width:70px;" <c:if test="${type == '0' }">readonly="readonly"</c:if> id="activityPriceLimit" name="activityPriceLimit" type="text" value="${activityPriceLimit }" />元 , 定金金额不可低于<input style="width:70px;" <c:if test="${type == '0' }">readonly="readonly"</c:if> id="depositLimit" name="depositLimit" type="text" value="${depositLimit }" />元</div>
            </td>
        </tr>
        <tr>
            <td align="left" class="l-table-edit-td" >
                <div>定金在预付商品价格<input style="width:70px;" <c:if test="${type == '0' }">readonly="readonly"</c:if> id="minRate" name="minRate" type="text" value="${minRate }" />% 至 <input style="width:70px;" <c:if test="${type == '0' }">readonly="readonly"</c:if> id="maxRate" name="maxRate" type="text" value="${maxRate }" />% 之间</div>
            </td>
        </tr>
        <tr>
            <td align="left" class="l-table-edit-td" >
                <div>定金抵扣倍数高于等于<input style="width:70px;" <c:if test="${type == '0' }">readonly="readonly"</c:if> id="offsetMultiple" name="offsetMultiple" type="text" value="${offsetMultiple }" />倍</div>
            </td>
        </tr>
        <tr>
            <td align="left" class="l-table-edit-td" >
                <input style="margin-left: 20px;" class="l-button" type="button" value="提交" onclick="saveRule()" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>