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
        var viewerPictures;
        $(function(){
            viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
                    $("#viewer-pictures").hide();
                }});

            /*         $(".l-dialog-close").live("click", function(){
                         $("#searchbtn").click();
                     });*/

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
                    console.log(data);
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

        //品牌查看商品
        function showProduct(id,name) {
            var reportColumnId = $("#reportColumnId").val();
            var payBegin = $("#payBegin").val();
            var payEnd = $("#payEnd").val();
            $.ajax({
                url : "${pageContext.request.contextPath}/flowReport/getTitleName.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {reportColumnId:reportColumnId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        var title ="";
                        if(data.colName && data.colName != " "){
                            title = "商品详情("+name+"-"+data.colName+")";
                        }else {
                            title = "商品详情("+name+")";
                        }
                        var reportColumnId = data.reportColumnId;
                        $.ligerDialog.open({
                            height: $(window).height() - 40,
                            width: $(window).width() - 40,
                            title: title,
                            name: "INSERT_WINDOW",
                            url: "${pageContext.request.contextPath}/flowReport/shopProductShow.shtml?id="+id+"&reportColumnId="+reportColumnId+"&type=2"+"&payBegin="+payBegin+"&payEnd="+payEnd,
                            showMax: true,
                            showToggle: false,
                            showMin: false,
                            isResize: true,
                            slide: false,
                            data: null
                        });
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        //品牌查看店铺
        function showShop(id,name) {
            var reportColumnId = $("#reportColumnId").val();
            var payBegin = $("#payBegin").val();
            var payEnd = $("#payEnd").val();
            $.ajax({
                url : "${pageContext.request.contextPath}/flowReport/getTitleName.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {reportColumnId:reportColumnId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        var title ="";
                        if(data.colName && data.colName != " "){
                            title = "店铺详情("+name+"-"+data.colName+")";
                        }else {
                            title = "店铺详情("+name+")";
                        }
                        var reportColumnId = data.reportColumnId;
                        $.ligerDialog.open({
                            height: $(window).height() - 40,
                            width: $(window).width() - 40,
                            title: title,
                            name: "INSERT_WINDOW",
                            url: "${pageContext.request.contextPath}/flowReport/shopShow.shtml?id="+id+"&reportColumnId="+reportColumnId+"&type=2"+"&payBegin="+payBegin+"&payEnd="+payEnd,
                            showMax: true,
                            showToggle: false,
                            showMin: false,
                            isResize: true,
                            slide: false,
                            data: null
                        });
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        };

        //获取品牌会员访客数
        function getVisitorCount(flagId, payBegin,payEnd) {
            var reportColumnId = $("#reportColumnId").val();//栏目
            var productTypeId = $("#productTypeId").val();//类目
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath }/flowReport/getVisitorCount.shtml',
                data: {type:"productBrand",reportColumnId : reportColumnId, productTypeId : productTypeId, flagId : flagId, payBegin : payBegin,payEnd:payEnd},
                dataType: 'json',
                success: function(data) {
                    if(data == null || data.code != 200){
                        commUtil.alertError(data.msg);
                    }else {
                        $("#"+flagId+"allVisitorsNum").html(data.allVisitorsNum);
                        $("#"+flagId+"allVisitorTouristNum").html(data.allVisitorTouristNum);
                    }
                },
                error: function(e) {
                    commUtil.alertError("系统异常请稍后再试");
                }
            });
        };


        var listConfig={
            url:"/flowReport/brandFlowReportData.shtml",
            listGrid:{ columns: [
                    { display: '品牌名称',name:'name'},
                    { display: '访客数（会员）',name:'totalVisitorCount', render: function (rowdata, rowindex) {
                            var html = [];
                            var payBegin = $("#payBegin").val();
                            var payEnd = $("#payEnd").val();
                            var flagId = rowdata.brandId;
                            html.push("<span id='"+ flagId +"allVisitorsNum'>")
                            html.push("<a href=\"javascript:getVisitorCount('"+flagId+"', '"+payBegin+"', '"+payEnd+"');\">查看</a>")
                            html.push("</span>");
                            return html.join("");

                        }},
                    { display: '访客数（非会员）',name:'totalVisitorCount', render: function (rowdata, rowindex) {
                            var html = [];
                            var payBegin = $("#payBegin").val();
                            var payEnd = $("#payEnd").val();
                            var flagId = rowdata.brandId;
                            html.push("<span id='"+ flagId +"allVisitorTouristNum'>")
                            html.push("<a href=\"javascript:getVisitorCount('"+flagId+"', '"+payBegin+"', '"+payEnd+"');\">查看</a>")
                            html.push("</span>");
                            return html.join("");

                        }},
                    { display: '浏览量（会员）', name:'totalPvCount'},
                    { display: '浏览量（非会员）', name:'totalPvCountTourist'},
                    { display: '加购件数', name:'shoppingCartCount'},
                    { display: '加购转化',width:100, render: function (rowdata, rowindex) {
                            if(rowdata.addProductRate==""||rowdata.addProductRate==null){
                                return "00.00%"
                            }else {
                                return rowdata.addProductRate;
                            }
                        }},
                    { display: '提交订单件数',name:'subProductCount'},
                    { display: '下单转化', name:'submitOrderRate',render: function (rowdata, rowindex) {
                            if(rowdata.submitOrderRate==""||rowdata.submitOrderRate==null){
                                return "00.00%"
                            }else {
                                return rowdata.submitOrderRate;
                            }
                        }},
                    { display: '付款件数', name:'payProductCount'},
                    { display: '付款转化', name:'paymentRate',render: function (rowdata, rowindex) {
                            if(rowdata.paymentRate==""||rowdata.paymentRate==null){
                                return "00.00%"
                            }else {
                                return rowdata.paymentRate;
                            }
                        }},
                    { display: '销售金额(实收)', name:'payAmountCount'},
                    {display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
                            var html = [];
                            html.push("<a href='javascript:;' onclick='showProduct("+rowdata.brandId+",\""+rowdata.name+"\");'>查看商品</a>&nbsp&nbsp");
                            html.push("<a href='javascript:;' onclick='showShop("+rowdata.brandId+",\""+rowdata.name+"\")'>查看店铺</a>");
                            return html.join("");
                        }},

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

        function chaneGoods(){
            var temp = $("#whichProduct").val();
            if(temp==1){
                $("#artnos").hide();
                $("#goodIds").show();
            }
            if(temp==2){
                $("#artnos").show();
                $("#goodIds").hide();
            }

        }

    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <div id="topmenu"></div>
    <div class="search-pannel">
        <div class="search-tr">

            <div class="search-td">
                <div class="search-td-label">品牌名称：</div>
                <div class="search-td-condition">
                    <input id="brandName" name="brandName">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label" >报表栏目：</div>
                <div class="search-td-condition">
                    <select  class="ad-select" name="reportColumnId" id="reportColumnId" >
                        <option value="">--请选择--</option>
                        <c:forEach var="reportColumn" items="${reportColumnList}">
                            <option value="${reportColumn.statusValue}" >${reportColumn.statusDesc}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">一级类目：</div>
                <div class="search-td-condition">
                    <select  class="ad-select" name="productTypeId" id="productTypeId" >
                        <option value="">--请选择--</option>
                        <c:forEach var="productType" items="${productTypes}">
                            <option value="${productType.id}">${productType.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>


        </div>

        <div class="search-tr">
            <div class="search-td" id="period" style="width: 40%;">
                <div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
                <div class="l-panel-search-item" >
                    <input type="text" id="payBegin" name="payBegin" value="${yesterday}" />
                    <script type="text/javascript">
                        $(function() {
                            $("#payBegin").ligerDateEditor({
                                showTime : false,
                                labelWidth : 150,
                                width : 150,
                                labelAlign : 'left'
                            });
                        });
                    </script>
                </div>
                <div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >~</div>
                <div class="l-panel-search-item">
                    <input type="text" id="payEnd" name="payEnd" value="${yesterday}"  />
                    <script type="text/javascript">
                        $(function() {
                            $("#payEnd").ligerDateEditor({
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
                <div id="searchbtn">搜索</div>
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
