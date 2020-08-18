<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<script type="text/javascript">
    $(function(){

        $(".dateEditor").ligerDateEditor( {
            showTime : false,
            labelAlign : 'left',
            labelwidth: 120,
            width:120
        });
        getTotalRefund();
    });

    //导出
    function excel(){
        $("#dataForm").attr("action","${pageContext.request.contextPath}/desiPayment/exportDesignRefundList.shtml");
        $("#dataForm").submit();
    }

    var listConfig = {
        url:"/desiPayment/designRefundList.shtml",
        btnItems:[],
        listGrid:{ columns: [
                { display: '订单编号', name: 'orderCode', width: 180 },
                { display: '类型', name: 'taskType', width: 150, render: function (rowdata,rowindex) {
                        var html = [];
                        if(rowdata.taskType == 1){
                            html.push("品牌特卖");
                        }else if (rowdata.taskType == 2){
                            html.push("店铺装修");
                        }
                        return html.join("");
                    }
                },
                { display: '任务名称', name: 'taskName', width: 160 },
                { display: '商家序号', name: 'mchtCode', width: 160 },
                { display: '公司名称', name: 'companyName', width: 160 },
                { display: '店铺名称', name: 'shopName', width: 160 },
                { display: '退款渠道', name: '', width: 100,render:function (rowdata,rowindex) {
                        let html = [];
                        if(rowdata.refundMethod == 1){
                            html.push("支付宝APP");
                        }else{
                            html.push("现金支付/转账");
                        }
                        return html.join("");
                    }},
                { display: '实退金额(元)', name: 'refundAmount', width: 100 },
                { display: '退款状态', name: '', width: 100,render: function (rowdata,rowindex) {
                        let html = [];
                        if(rowdata.status == 0 ){
                            html.push("未退");
                        }else if (rowdata.status == 1){
                            html.push("退款中");
                        }else if (rowdata.status == 2){
                            html.push("退款成功");
                        }else if (rowdata.status == 3){
                            html.push("退款失败");
                        }
                        return html.join("");
                    }
                },
                { display: '退款时间', width: 160, render: function (rowdata, rowindex) {
                        if (rowdata.createDate != null){
                            var createDates = new Date(rowdata.createDate);
                            return createDates.format("yyyy-MM-dd hh:mm:ss");
                        }
                    }},
                { display: '支付交易号', name: 'paymentNo', width: 240 }
            ],
            showCheckbox : false,  //不设置默认为 true
            showRownumber:true //不设置默认为 true
        } ,
        container:{
            toolBarName:"toptoolbar",
            searchBtnName:"searchbtn",
            fromName:"dataForm",
            listGridName:"maingrid"
        }

    };
    function getTotalRefund() {
        $("#loadTotalRefund").html(" ");
        $.ajax({
            url: "${pageContext.request.contextPath}/desiPayment/getDesignTotalRefund.shtml",
            type: 'POST',
            dataType: 'json',
            data: $('#dataForm').serialize(),
            success: function(data){
                $("#loadTotalRefund").append("总退款金额:   "+data.totalRefund + "元");
            },
            error:function(){
                alert('数据不存在');
            }
        });

    }



</script>

<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading" ></div>
<form id="dataForm" runat="server">
    <div id ="loadTotalRefund" style="margin-left: 110px; margin-top: 10px; min-height: 1px;position: relative; " ></div>
    <input type="hidden" id="status" name="status" value="${status}">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">订单编号：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="orderCode" name="orderCode">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">商家序号：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="mchtCode" name="mchtCode">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">商家名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="mchtName" name="mchtName">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">任务名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="taskName" name="taskName">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label" style="margin-top: 5%;">支付交易号：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="paymentNo" name="paymentNo">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label" >类型：</div>
                <div class="search-td-combobox-condition">
                    <select id="taskType" name="taskType" style="width: 135px;">
                        <option value="" selected="selected" >请选择</option>
                        <option value="1" >品牌特卖</option>
                        <option value="2" >店铺装修</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="search-tr">
            <div class="search-td" style="width: 40%;">
                <div class="search-td-label" style="float: left;width: 20%;">退款日期：</div>
                <div class="l-panel-search-item" >
                    <input type="text" id="createDateBegin" name="createDateBegin" class="dateEditor" value="${pay_date_begin}" style="width: 135px;" />
                </div>
                <div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
                <div class="l-panel-search-item">
                    <input type="text" id="createDateEnd" name="createDateEnd" class="dateEditor" value="${pay_date_end}" style="width: 135px;" />
            </div>
            </div>

            <div class="search-td-search-btn" style="display: inline-flex; width: 60%;" >
                <div id="searchbtn" >
                    <input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="搜索" onclick="getTotalRefund();">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();">
                </div>
            </div>
        </div>
    </div>
    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>

</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/common/js/listModel/listModel.js">
</script>