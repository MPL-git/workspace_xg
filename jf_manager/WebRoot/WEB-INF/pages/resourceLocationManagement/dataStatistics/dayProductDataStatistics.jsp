<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style>
</style>
<script type="text/javascript">
    function formatMoney(s, n)
    {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for(i = 0; i < l.length; i ++ )
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
        }
        return t.split("").reverse().join("") + "." + r;
    }

    $(function(){
        // 禁止分页
        $("#maingrid").ligerGrid({
            usePager:false
        });
    });


    var listConfig={
        url:"/resourceLocationManagement/dayProductDataStatisticsList.shtml",
        listGrid:{ columns: [
                { display: '统计时间',width:180, name:'statisticalDate'},
                { display: '访客数(会员)',width:180, render: function (rowdata, rowindex) {
                    if(rowdata.totalVisitorCount==""||rowdata.totalVisitorCount==null){
                        return "0";
                    }else {
                        return rowdata.totalVisitorCount;
                    }
                    }},
                { display: '访客数(非会员)',width:100,name:'totalVisitorCountTourist', render: function (rowdata, rowindex) {
                        if(rowdata.totalVisitorCountTourist==""||rowdata.totalVisitorCountTourist==null){
                            return "0";
                        }else {
                            return rowdata.totalVisitorCountTourist;
                        }
                    }},
                { display: '浏览量(会员)',width:100, name:'totalPvCount', render: function (rowdata, rowindex) {
                        if(rowdata.totalPvCount==""||rowdata.totalPvCount==null){
                            return "0";
                        }else {
                            return rowdata.totalPvCount;
                        }
                    }},
                { display: '浏览量(非会员)',width:100, name:'totalPvCountTourist', render: function (rowdata, rowindex) {
                        if(rowdata.totalPvCountTourist==""||rowdata.totalPvCountTourist==null){
                            return "0";
                        }else {
                            return rowdata.totalPvCountTourist;
                        }
                    }},
                { display: '加购件数',width:100, name:'shoppingCartCount', render: function (rowdata, rowindex) {
                        if(rowdata.shoppingCartCount==""||rowdata.shoppingCartCount==null){
                            return "0";
                        }else {
                            return rowdata.shoppingCartCount;
                        }
                    }},
                { display: '加购转化',width:100, render: function (rowdata, rowindex) {
                        return formatMoney(rowdata.addConversion,2)+"%";
                    }},
                { display: '提交订单件数',width:100,  name: 'subProductCount', render: function (rowdata, rowindex) {
                        if(rowdata.subProductCount==""||rowdata.subProductCount==null){
                            return "0";
                        }else {
                            return rowdata.subProductCount;
                        }
                    }},
                { display: '下单转化',width:100, render: function (rowdata, rowindex) {
                        return formatMoney(rowdata.orderConversion,2)+"%";
                    }},
                { display: '付款件数',width:100,  name: 'payProductCount', render: function (rowdata, rowindex) {
                        if(rowdata.payProductCount==""||rowdata.payProductCount==null){
                            return "0";
                        }else {
                            return rowdata.payProductCount;
                        }
                    }},
                { display: '付款转化',width:100, render: function (rowdata, rowindex) {
                        return formatMoney(rowdata.paymentConversion,2)+"%";
                    }}
            ],
            showCheckbox : false,  //不设置默认为 true
            showRownumber: true //不设置默认为 true
        } ,
        container:{
            toolBarName:"toptoolbar",
            searchBtnName:"searchbtn",
            fromName:"dataForm",
            listGridName:"maingrid"
        }
    };
</script>

<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<c:if test="${roleType!=1}">
    <div id="toptoolbar"></div>
</c:if>
<form id="dataForm" runat="server">
    <input type="hidden" id="pagetype"  name="pagetype" value="${pagetype}">
    <div class="search-pannel">

        <div class="search-tr"  >

            <div class="search-td">
                <div class="search-td-label" style="float:left;">统计日期：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="statisticalBeginDate" name="statisticalBeginDate" value="${eightDayAgo}" />
                    <script type="text/javascript">
                        $(function() {
                            $("#statisticalBeginDate").ligerDateEditor({
                                showTime : false,
                                labelWidth : 150,
                                labelAlign : 'left'
                            });
                        });
                    </script>
                </div>
            </div>

            <div class="search-td">
                <div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="statisticalEndDate" name="statisticalEndDate" value="${yesterday}" />
                    <script type="text/javascript">
                        $(function() {
                            $("#statisticalEndDate").ligerDateEditor({
                                showTime : false,
                                labelWidth : 150,
                                labelAlign : 'left'
                            });
                        });
                    </script>
                </div>
            </div>

            <div class="search-td-search-btn">
                <div id="searchbtn" >搜索</div>
            </div>

        </div>

    </div>

    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

</ul>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>