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
        var statisticsType = '1';
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
                statisticsType = $(this).val();
            })

            $("#searchbtn1").bind("click",function(){
                if(statisticsType == '2'){
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
            url:"/resourceLocationManagement/onlineProductDataStatisticsList.shtml?pagetype=${pagetype}",
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
                            var upDate;
                            if (rowdata.upDate!=null){
                                upDate=new Date(rowdata.upDate);
                                /*  return upDate.format("yyyy-MM-dd"); */
                                html.push("<span>"+upDate.format("yyyy-MM-dd")+"</span>");
                            }
                            /*   	html.push(upDate) */ //updateWetaoChannel
                            return html.join("");
                        }},

                    {display:'商品', name:'seqNo', align:'center', isSort:false, width:330, render:function(rowdata, rowindex) {
                            var html=[];
                            var h = "";
                            if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
                                h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='"+rowdata.pic+"' width='100' height='100' onclick='viewerPic("+rowdata.linkId+")'>";
                            }else{
                                h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='100' onclick='viewerPic("+rowdata.linkId+")'>";
                            }
                            html.push(h);
                            html.push("<div class='width-226'><p class='ellipsis'>"+rowdata.productName+"</p><p>商品ID:"+rowdata.productCode+"</p><p>"+rowdata.artbrandGroupEasy+"&nbsp|&nbsp;"+rowdata.producttype1Name+"&nbsp;&nbsp;</p></div>")
                            html.push("<div>")

                            return html.join("");
                        }},

                    { display: '吊牌价/活动价/折扣', width: 180, render: function (rowdata, rowindex) {
                            var html = [];
                            //吊牌价
                            html.push(rowdata.tagPriceMin);
                            if(rowdata.tagPriceMin!=rowdata.tagPriceMax){
                                html.push("-");
                                html.push(rowdata.tagPriceMax);
                            };
                            if (rowdata.tagPriceMin!=null && rowdata.tagPriceMin!='') {
                                html.push("<br>");
                            }

                            //活动价
                            html.push("<font color='red'> ")
                            html.push(rowdata.salePriceMin);
                            if(rowdata.salePriceMin!=rowdata.salePriceMax){
                                html.push("-");
                                html.push(rowdata.salePriceMax);
                            };
                            html.push("</font>")
                            if (rowdata.salePriceMin!=null && rowdata.salePriceMin!='') {
                                html.push("<br>");
                            }
                            //折扣
                            html.push(rowdata.discountMin);
                            if(rowdata.discountMin!=rowdata.discountMax){
                                html.push("-");
                                html.push(rowdata.discountMax);
                            };
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
            <div class="search-td" style="margin-right:-20px;">
                <div class="search-td-label">商品ID：</div>
                <div class="search-td-condition">
                    <input name="productId">
                </div>
            </div>

            <div class="search-td" style="margin-right:-20px;">
                <div class="search-td-label">商品名称：</div>
                <div class="search-td-condition">
                    <input name="productName">
                </div>
            </div>

            <c:if test="${pagetype ne '1101' and pagetype ne '1201'}">
                <div class="search-td" style="margin-right:-20px;">
                    <div class="search-td-label">商家序号：</div>
                    <div class="search-td-condition">
                        <input name="mchtCode">
                    </div>
                </div>

                <div class="search-td" style="margin-right:-20px;">
                    <div class="search-td-label">店铺名称：</div>
                    <div class="search-td-condition">
                        <input name="mchtName">
                    </div>
                </div>
            </c:if>
            <div class="search-td" style="margin-right:-20px;">
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
            <div class="search-td-label" style="float: left;width: 20%;margin-top:3px;">付款日期：</div>
            <div class="l-panel-search-item" >
                <input type="text" id="nowBeginDate" name="nowBeginDate" value="${today}" readonly="readonly"/>
            </div>

            <div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >-</div>
            <div class="l-panel-search-item">
                <input type="text" id="nowEndDate" name="nowEndDate" value="${today}" readonly="readonly"/>
            </div>
        </div>

        <div class="search-td" id="beforePeriod" style="width: 40%;display:none;">
            <div class="search-td-label" style="float: left;width: 20%;margin-top:3px;">付款日期：</div>
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
