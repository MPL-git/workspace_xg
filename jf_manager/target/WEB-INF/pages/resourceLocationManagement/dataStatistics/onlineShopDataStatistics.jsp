<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
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

        var viewerPictures;
        $(function(){
            $("#agoods").hide();

            viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
                    $("#viewer-pictures").hide();
                }});

            $(".l-dialog-close").live("click", function(){
                $("#searchbtn").click();
            });

            $("#statisticsType").change(function(){
                if($(this).val() == '1'){//实时
                    $("#nowPeriod").show();
                    $("#beforePeriod").hide();
                }else{//昨天之后
                    $("#nowPeriod").hide();
                    $("#beforePeriod").show();
                }
                $("#statisticsType").val($(this).val());
            })

            $("#searchbtn1").bind("click",function(){
                if($("#statisticsType").val() == '2'){
                    var date = new Date().getFullYear()+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate();
                    if($("#beforeBeginDate").val() == "" || $("#beforeEndDate").val() == ""){
                        $.ligerDialog.error('开始时间或结束时间不可为空');
                        return;
                    }else if(new Date($("#beforeBeginDate").val()).getTime() > new Date($("#beforeEndDate").val()).getTime()){//开始时间不可大于结束时间
                        $.ligerDialog.error('开始时间不可大于结束时间');
                        return;
                    }else if(new Date($("#beforeBeginDate").val()).getTime() > new Date(date).getTime() || new Date($("#beforeEndDate").val()).getTime() > new Date(date).getTime()){//开始时间与结束时间不可大于当前时间
                        $.ligerDialog.error('开始时间或结束时间不可大于当前时间');
                        return;
                    }
                }
                $("#searchbtn").click();
            })
        });


        function viewProduct(id) {
            $.ligerDialog.open({
                height: $(window).height() - 40,
                width: 1200,
                title: "商品信息",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        //查看店铺
        function showMchtShop(mchtId, mchtType) {
            $.ligerDialog.open({
                height: $(window).height() - 50,
                width: $(window).width() - 100,
                title: "商品列表",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/mcht/showMchtShopManager.shtml?mchtId="+mchtId+"&mchtType="+mchtType,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        function viewerPic(productId){
            $("#viewer-pictures").empty();
            viewerPictures.destroy();
            $.ajax({
                url : "${pageContext.request.contextPath}/product/getPoductPic.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {productId:productId},
                timeout : 30000,
                success : function(data) {

                    if(data&&data.length>0){
                        for (var i=0;i<data.length;i++)
                        {	if(data[i].pic.indexOf("http") >= 0){
                            $("#viewer-pictures").append('<li><img data-original="'+data[i].pic+'" src="'+data[i].pic+'" alt=""></li>');
                        }else{
                            $("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
                        }
                        }
                        viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
                                $("#viewer-pictures").hide();
                            }});
                        $("#viewer-pictures").show();
                        viewerPictures.show();
                    }


                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });

        }

        var listConfig={
            url:"/resourceLocationManagement/onlineShopDataStatisticsList.shtml?pagetype=${pagetype}",
            listGrid:{ columns: [
                    {display: '权重', name: 'weight', width: 80, render: function(rowdata, rowindex) {
                            var html = [];
                            if(rowdata.weight==null|| rowdata.weight==0){
                                html.push("0");
                            }else{
                                html.push(rowdata.weight);
                            }
                            /*html.push("<br><a href=\"javascript:weightDetail(" + rowdata.linkId + ");\">明细</a>");*/
                            return html.join("");
                        }},

                    { display: '上线日期', name: "upDate", width: 100, align: "center", render: function(rowdata, rowindex) {
                            var html = [];
                            var onlineDate;
                            if (rowdata.upDate!=null){
                                onlineDate=new Date(rowdata.upDate);
                                html.push("<span>"+onlineDate.format("yyyy-MM-dd")+"</span>");
                            }
                            return html.join("");
                        }},
                    { display: '商家序号', name: 'mchtCode', width: 100},
                    { display: '开通日期', name: 'cooperate_begin_date', width: 120 ,render: function(rowdata, rowindex) {
                            if(rowdata.cooperateBeginDate != null && rowdata.cooperateBeginDate != ""){
                                var cooperateBeginDate = new Date(rowdata.cooperateBeginDate);
                                return cooperateBeginDate.format("yyyy-MM-dd");
                            }else{
                                return "";
                            }
                        }},
                    { display: '类目/商家名称', width: 200, render: function(rowdata, rowindex){
                            var html = [];
                            html.push(rowdata.productTypeName);
                            html.push("<br/>"+rowdata.companyName);
                            if(rowdata.shopStatus == '1') {
                                html.push('<br/><a href="${previewUrl}'+rowdata.linkId+'&preview=1" target="_blank">'+rowdata.shopName+'</a>');

                            }else {
                                html.push("<br/><a href=\"javascript:showMchtShop("+ rowdata.linkId +", "+ rowdata.mchtType +");\">"+ rowdata.shopName +"</a>");
                            }
                            return html.join("");
                        }},
                    { display: '品牌', name: '', width: 180 ,render: function(rowdata, rowindex) {
                            var mchtProductBrandName = rowdata.mchtProductBrandName;
                            var html = [];
                            html.push(mchtProductBrandName);
                            return html.join("");
                        }},

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
                                return rowdata.unmemberPageView;
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
                            if(rowdata.addProductRate==""||rowdata.addProductRate==null){
                                return "00.00%"
                            }else {
                                return formatMoney(rowdata.addProductRate, 2) + "%";
                            }
                        }},
                    { display: '提交订单件数',width:100,  name: 'subProductCount', render: function (rowdata, rowindex) {
                            if(rowdata.subProductCount==""||rowdata.subProductCount==null){
                                return "0";
                            }else {
                                return rowdata.subProductCount;
                            }
                        }},
                    { display: '下单转化',width:100, render: function (rowdata, rowindex) {
                            if(rowdata.submitOrderRate==""||rowdata.submitOrderRate==null){
                                return "00.00%"
                            }else{
                                return formatMoney(rowdata.submitOrderRate,2)+"%";
                            }

                        }},
                    { display: '付款件数',width:100,  name: 'payProductCount', render: function (rowdata, rowindex) {
                            if(rowdata.payProductCount==""||rowdata.payProductCount==null){
                                return "0";
                            }else {
                                return rowdata.payProductCount;
                            }
                        }},
                    { display: '付款转化',width:100, render: function (rowdata, rowindex) {
                            if(rowdata.paymentRate==""||rowdata.paymentRate==null){
                                return "00.00%"
                            }else {
                                return formatMoney(rowdata.paymentRate, 2) + "%";
                            }
                        }}



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

    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <input type="hidden" id="pagetype" name="pagetype" value="${pagetype}">
    <div id="topmenu"></div>
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">商家序号：</div>
                <div class="search-td-condition">
                    <input name="mchtCode">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">店铺名称：</div>
                <div class="search-td-condition">
                    <input name="mchtName">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label" >一级类目：</div>
                <div class="search-td-condition" >
                    <select id="productTypeId" name="productTypeId">
                        <option value="">请选择</option>
                        <c:forEach var="productType" items="${productTypes}">
                            <option value="${productType.id}">${productType.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

        </div>

        <div class="search-tr">
            <div class="search-td" style="margin-right:-20px;">
                <div class="search-td-label" >统计类型：</div>
                <div class="search-td-condition" >
                    <select id="statisticsType" name="statisticsType">
                        <option value="1">实时数据</option>
                        <option value="2">历史记录</option>
                    </select>
                </div>
            </div>

            <div class="search-td" id="nowPeriod" style="width: 40%;">
                <div class="search-td-label" style="float: left;width: 24%;margin-top:3px;">付款日期：</div>
                <div class="l-panel-search-item" >
                    <input type="text" id="nowBeginDate" name="nowBeginDate" value="${today}" readonly="readonly"/>
                </div>

                <div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >-</div>
                <div class="l-panel-search-item">
                    <input type="text" id="nowEndDate" name="nowEndDate" value="${today}" readonly="readonly"/>
                </div>
            </div>

            <div class="search-td" id="beforePeriod" style="width: 40%;display:none;">
                <div class="search-td-label" style="float: left;width: 24%;margin-top:3px;">付款日期：</div>
                <div class="l-panel-search-item" >
                    <input type="text" id="beforeBeginDate" name="beforeBeginDate" value="${yesterday}"/>
                    <script type="text/javascript">
                        $(function() {
                            $("#beforeBeginDate").ligerDateEditor({
                                showTime : false,
                                labelWidth : 150,
                                width : 150,
                                labelAlign : 'left'
                            });
                        });
                    </script>
                </div>

                <div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >-</div>
                <div class="l-panel-search-item">
                    <input type="text" id="beforeEndDate" name="beforeEndDate" value="${yesterday}"/>
                    <script type="text/javascript">
                        $(function() {
                            $("#beforeEndDate").ligerDateEditor({
                                showTime : false,
                                labelWidth : 150,
                                width : 150,
                                labelAlign : 'left'
                            });
                        });
                    </script>
                </div>
            </div>

            <div class="search-td-search-btn">
                <div class="l-button" id="searchbtn1">搜索</div>
            </div>

            <div class="search-td-search-btn">
                <div id="searchbtn" style="display:none;">搜索</div>
            </div>
        </div>
    </div>
</form>
<div id="maingrid" style="margin: 0; padding: 0"></div>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
