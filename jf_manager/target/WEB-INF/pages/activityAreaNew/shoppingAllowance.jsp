<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

    <script type="text/javascript">
        $(function () {

        });

        function editShoppingAllowance(id) {
            $.ligerDialog.open({
                height: 600,
                width: 800,
                title: "新增/编辑津贴规则",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/shopping/addAllowanceSetting.shtml?id="+id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        //确认激活或冻结
        function updateStatus(allowanceSettingId,status) {
            var title;
            if (status=='0'){
                title='确认关闭该购物津贴规则'
            }else {
                title='确认启用该购物津贴规则'
            }
            $.ligerDialog.confirm(title, function(yes) {
                if (yes){
                    $.ajax({
                        type : 'POST',
                        url : "${pageContext.request.contextPath}/allowanceSetting/updateStatus.shtml",
                        data : {allowanceSettingId : allowanceSettingId,status:status},
                        dataType : 'json',
                        success : function(data) {
                            if ("200" == data.code) {
                                listModel.gridManager.reload();
                            }else{
                                $.ligerDialog.error(data.msg);
                            }
                        },
                        error : function() {
                            $.ligerDialog.error('操作超时，请稍后再试！');
                        }
                    });
                }})
        }

        function allowanceReset() {

            $.ligerDialog.confirm("是否确认清零", function (yes) {
                if (yes){
                    $.ajax({
                        url : "${pageContext.request.contextPath}/allowanceSetting/allowanceReset.shtml",
                        secureuri : false,
                        dataType : 'json',
                        cache : false,
                        async : false,
                        success : function(data) {
                            if ("200" == data.code) {
                                $.ligerDialog.success("津贴清零成功", function(yes) {
                                    if (yes){
                                        listModel.gridManager.loadData()
                                    }
                                })
                            }else{
                                $.ligerDialog.error(data.msg);
                            }
                        },
                        error : function() {
                            $.ligerDialog.error('操作超时，请稍后再试！');
                        }
                    });
                }

            });
        }

        var listConfig = {

            url: "/shopping/allowanceSettingList.shtml",
            btnItems: [{
                text: '新增购物津贴',
                icon: 'add',
                dtype: 'win',
                click: itemclick,
                url: "/shopping/addAllowanceSetting.shtml",
                seqId: "",
                width: 800,
                height: 600
            },
                {text: '津贴清零', icon: 'add', click: function() {
                        allowanceReset();
                    }
                }],
            listGrid: {
                columns: [
                    {display: '津贴名称', name: 'name'},
                    {
                        display: '兑换规则', render: function (rowdata, rowindex) {
                            console.log(rowdata.exchangeAmountMin)
                            // debugger;
                            var h=[];
                            var integral= rowdata.integral.split(",");
                            var exchangeAmountMin=rowdata.exchangeAmountMin.split(",");
                            var exchangeAmountMax=rowdata.exchangeAmountMax.split(",");
                            console.log(exchangeAmountMin[0]==exchangeAmountMax[0])
                            if (exchangeAmountMin[0]==exchangeAmountMax[0]){
                            h.push(integral[0]+"积分抢"+exchangeAmountMin[0]);
                            }else {
                                h.push(integral[0]+"积分抢"+exchangeAmountMin[0]+"-"+exchangeAmountMax[0]);
                            }
                            h.push('<br>')
                            if (exchangeAmountMin[1]==exchangeAmountMax[1]){
                                h.push(integral[1]+"积分抢"+exchangeAmountMin[1]);
                            }else {
                                h.push(integral[1]+"积分抢"+exchangeAmountMin[1]+"-"+exchangeAmountMax[1]);
                            }
                            return h.join("");

                        }
                    },
                    {
                        display: '兑换弹窗', render: function (rowdata, rowindex) {
                            if (rowdata.popup_count =='1'){
                               return "每日一次"
                            }
                            if (rowdata.popup_count =='2'){
                                return "终生一次"
                            }
                            if (rowdata.popup_count =='3'){
                                return rowdata.day +"天一次"
                            }
                        }
                    },
                    {
                        display: '兑换时间', render: function (rowdata, rowindex) {
                            var h = [];
                            if (rowdata.exchange_begin_date!=null){
                                var exchangeBeginDate=new Date(rowdata.exchange_begin_date);
                                h.push(exchangeBeginDate.format("yyyy-MM-dd hh:mm:ss"))
                                h.push("至")
                            }
                            if (rowdata.exchange_end_date!=null){
                                var exchangeEndDate=new Date(rowdata.exchange_end_date);
                                h.push(exchangeEndDate.format("yyyy-MM-dd hh:mm:ss"))
                            }
                            return h.join("");
                        }
                    },
                    {
                        display: '浮窗图片', render: function (rowdata, rowindex) {
                            var h = "";
                            if (rowdata.pic!=null && rowdata.pic!="") {
                                h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
                            }
                            return h;
                        }
                    },
                    {
                        display: '操作', render: function (rowdata, rowindex) {
                            var html = []
                            html.push("<a href='javascript:;' onclick='editShoppingAllowance(" + rowdata.id + ");'>编辑</a>");
                            html.push('<a href="javascript:;">|</a>');
                            if (rowdata.status =='1'){
                                html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ",'0');\">关闭</a>");
                            }
                            if (rowdata.status =='0'){
                                html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ",'1');\">启用</a>");
                            }
                            return html.join("")
                        }
                    }
                ],
                showCheckbox: false,  //不设置默认为 true
                showRownumber: true //不设置默认为 true
            },
            container: {
                toolBarName: "toptoolbar",
                searchBtnName: "searchbtn",
                fromName: "dataForm",
                listGridName: "maingrid"
            }
        };

    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <div class="search-pannel">



    </div>

    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

</ul>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>