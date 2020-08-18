<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style type="text/css">
    .color-s0,.color-fs1{color: #0000FF;}
    .color-s1,.color-fs2{color: #008000;}
    .color-s4{color: #333333;}
    .color-fs0{color: #000000;}
</style>
<script type="text/javascript">

    $(function(){

        $(".dateEditor").ligerDateEditor( {
            showTime : false,
            labelAlign : 'left',
            labelwidth: 120,
            width:120
        });

    });

    var totalCount = "";
    var combineAmount = "";
    var wxAmount = "";
    var zfbAmount = "";
    var gzhAmount = "";
    var wxCount = "";
    var zfbCount = "";
    var gzhCount = "";

    function formatMoney(s, n) {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for(i = 0; i < l.length; i ++ ) {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
        }
        return t.split("").reverse().join("") + "." + r;
    }

    function combineList(refundDate,paymentId) {
        $.ligerDialog.open({
            height: $(window).height()-100,
            width: $(window).width()-100,
            title: "年费退款明细",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/svipOrder/refundSvipOrderManager.shtml?paymentId=" + paymentId+"&refundDate="+refundDate,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }

    function checkSearchCondition(){
        totalCount = "";
        combineAmount = "";
        wxAmount = "";
        zfbAmount = "";
        gzhAmount = "";
        wxCount = "";
        zfbCount = "";
        gzhCount = "";
        return true;
    }

    //导出
    function excel(){
        $("#dataForm").attr("action","${pageContext.request.contextPath}/svipOrder/exportRefCountSvipOrderList.shtml");
        $("#dataForm").submit();
    }

    var listConfig={
        url:"/svipOrder/refCountSvipOrderList.shtml",
        listGrid:{ columns: [{ display: "退款日期", name:'eachDay'},
                { display: '笔数', render: function (rowdata, rowindex) {
                        var html = [];
                        var count = Number(rowdata.wxCount) + Number(rowdata.zfbCount) + Number(rowdata.gzhCount);
                        if(count != rowdata.totalCount){
                            html.push(rowdata.totalCount+"<span style='color:red;'>[异常]</span>");
                        }else if(totalCount != ''){
                            if(Number(totalCount) > Number(rowdata.totalCount) ) {
                                html.push("<span style='color:green;'>"+ rowdata.totalCount +"</span>");
                            }else if(Number(totalCount) < Number(rowdata.totalCount) ) {
                                html.push("<span style='color:red;'>"+ rowdata.totalCount +"</span>");
                            }else {
                                html.push(rowdata.totalCount);
                            }
                        }else {
                            html.push(rowdata.totalCount);
                        }
                        return html.join("");
                    }},
                { display: '退款金额', render: function (rowdata, rowindex) {
                        var html = [];
                        var amount = Number(rowdata.wxAmount) + Number(rowdata.zfbAmount) + Number(rowdata.gzhAmount);
                        if(parseFloat(amount).toFixed(2) != rowdata.combineAmount){
                            html.push(formatMoney(rowdata.combineAmount, 2)+"<span style='color:red;'>[异常]</span>");
                        }else if(combineAmount != ''){
                            if(Number(combineAmount) > Number(formatMoney(rowdata.combineAmount, 2)) ) {
                                html.push("<span style='color:green;'>"+ formatMoney(rowdata.combineAmount, 2) +"</span>");
                            }else if(Number(combineAmount) < Number(formatMoney(rowdata.combineAmount, 2)) ) {
                                html.push("<span style='color:red;'>"+ formatMoney(rowdata.combineAmount, 2) +"</span>");
                            }else {
                                html.push(formatMoney(rowdata.combineAmount, 2));
                            }
                        }else {
                            html.push(formatMoney(rowdata.combineAmount, 2));
                        }
                        return html.join("");
                    }},
                { display: '支付宝',render: function (rowdata, rowindex) {
                        var html = [];
                        if(zfbAmount != ''){
                            if(Number(zfbAmount) > Number(formatMoney(rowdata.zfbAmount, 2)) ) {
                                html.push("<a href='javascript:;' style='color:green;' onclick='combineList(\""+ rowdata.eachDay +"\", 2);'>"+ formatMoney(rowdata.zfbAmount, 2) +"</a>");
                            }else if(Number(zfbAmount) < Number(formatMoney(rowdata.zfbAmount, 2)) ) {
                                html.push("<a href='javascript:;' style='color:red;' onclick='combineList(\""+ rowdata.eachDay +"\", 2);'>"+ formatMoney(rowdata.zfbAmount, 2) +"</a>");
                            }else {
                                html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.eachDay +"\", 2);'>"+ formatMoney(rowdata.zfbAmount, 2) +"</a>");
                            }
                        }else {
                            html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.eachDay +"\", 2);'>"+ formatMoney(rowdata.zfbAmount, 2) +"</a>");
                        }
                        return html.join("");
                    }},
                { display: '微信', render: function (rowdata, rowindex) {
                        var html = [];
                        if(wxAmount != ''){
                            if(Number(wxAmount) > Number(formatMoney(rowdata.wxAmount, 2)) ) {
                                html.push("<a href='javascript:;' style='color:green;' onclick='combineList(\""+ rowdata.eachDay +"\",1);'>"+ formatMoney(rowdata.wxAmount, 2) +"</a>");
                            }else if(Number(wxAmount) < Number(formatMoney(rowdata.wxAmount, 2)) ) {
                                html.push("<a href='javascript:;' style='color:red;' onclick='combineList(\""+ rowdata.eachDay +"\",1);'>"+ formatMoney(rowdata.wxAmount, 2) +"</a>");
                            }else {
                                html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.eachDay +"\",1);'>"+ formatMoney(rowdata.wxAmount, 2) +"</a>");
                            }
                        }else {
                            html.push("<a href='javascript:;' onclick='combineList(\""+ rowdata.eachDay +"\",1);'>"+ formatMoney(rowdata.wxAmount, 2) +"</a>");
                        }
                        return html.join("");
                    }}
            ],
            showCheckbox : false,  //不设置默认为 true
            showRownumber:true, //不设置默认为 true
            beforeSearch: checkSearchCondition
        } ,
        container:{
            toolBarName:"toptoolbar",
            searchBtnName:"searchbtn",
            fromName:"dataForm",
            listGridName:"maingrid"
        }
    }
</script>

<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td" style="width: 40%;">
                <div class="search-td-label" style="float: left;width: 20%;">退款日期：</div>
                <div class="l-panel-search-item" style="width: 135px" >
                    <input type="text" id="refund_date_begin" name="refund_date_begin" class="dateEditor" value="${refund_date_begin}" style="width: 135px;" />
                </div>
                <div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
                <div class="l-panel-search-item" style="width: 135px">
                    <input type="text" id="refund_date_end" name="refund_date_end" class="dateEditor" value="${refund_date_end}" style="width: 135px;" />
                </div>
            </div>
            <div class="search-td-search-btn" style="display: inline-flex;" >
                <div id="searchbtn" >搜索</div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();">
                </div>
            </div>
        </div>
    </div>
    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
<script type="text/javascript">
    $(function() {
        // 禁止分页
        $("#maingrid").ligerGrid({
            usePager: false
        });
    });
</script>
</body>