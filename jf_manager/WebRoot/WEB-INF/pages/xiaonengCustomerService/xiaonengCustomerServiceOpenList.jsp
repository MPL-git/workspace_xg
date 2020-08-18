<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

    <script type="text/javascript">
        $(function () {
            $(".dateEditor").ligerDateEditor({
                showTime: true,
                format: "yyyy-MM-dd",
                labelAlign: 'left',
                width: 150
            });

        });


        //修改
        function updateXiaoneng(xiaonengId, mchtId) {
            $.ligerDialog.open({
                height: $(window).height() - 100,
                width: $(window).width() - 400,
                title: "修改",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/xiaonengCustomerService/updateXiaoneng.shtml?xiaonengId=" + xiaonengId + "&mchtId=" + mchtId,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        //开通
        function openXiaoneng(mchtId) {
            $.ligerDialog.open({
                height: $(window).height() - 100,
                width: $(window).width() - 400,
                title: "开通",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/xiaonengCustomerService/openXiaoneng.shtml?mchtId=" + mchtId,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        var listConfig = {
            url: "/xiaonengCustomerService/xiaonengCustomerServiceList.shtml",
            btnItems: [],
            listGrid: {
                columns: [{display: '合作模式', name: 'mchtTypeDesc', align: 'center', isSort: false, width: 100},
                    {display: '商家序号', name: 'mchtCode', align: 'center', isSort: false, width: 120},
                    {display: '公司名称', name: 'companyName', align: 'center', isSort: false, width: 200},
                    {display: '店铺名称', name: 'shopName', align: 'center', isSort: false, width: 200},
                    {display: '主营类目', name: 'productTypeName', align: 'center', isSort: false, width: 200},
                    {
                        display: '开通状态',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 100,
                        render: function (rowdata, rowindex) {
                            if (rowdata.xiaonengId != null && rowdata.xiaonengId != '') {
                                return "已开通";
                            } else {
                                return "未开通";
                            }
                        }
                    },
                    {display: '客服序号', name: 'xiaonengId', align: 'center', isSort: false, width: 100},
                    {display: '企业ID', name: 'xiaonengBusId', align: 'center', isSort: false, width: 100},
                    {display: '客服代码', name: 'xiaonengCode', align: 'center', isSort: false, width: 200},
                    {
                        display: '客服启用状态',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 100,
                        render: function (rowdata, rowindex) {
                            if (rowdata.xiaonengId != null && rowdata.xiaonengId != '') {
                                if (rowdata.xiaonengStatus == '1') {
                                    return "启用";
                                }
                                if (rowdata.xiaonengStatus == '2') {
                                    return "未启用";
                                }
                                if (rowdata.xiaonengStatus == '0') {
                                    return "停用";
                                }
                            }
                        }
                    },
                    {
                        display: '操作',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 100,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.xiaonengId != null && rowdata.xiaonengId != '') {
                                html.push("<a href=\"javascript:updateXiaoneng(" + rowdata.xiaonengId + ", " + rowdata.id + ");\">【修改】</a>");
                            } else {
                                // html.push("<a href=\"javascript:openXiaoneng(" + rowdata.id + ");\">【开通】</a>");
                                html.push("<a href=\"javascript:compoundXiaoneng(" + rowdata.id + ");\">【合成】</a>");
                                // html.push("<a href=\"javascript:compoundXiaoneng(&quot" + rowdata.mchtCode + "&quot" + rowdata.companyName + "&quot" + rowdata.productTypeName + ");\">【合成】</a>");
                            }
                            return html.join("");
                        }
                    }
                ],
                showCheckbox: true, //不设置默认为 true
                showRownumber: true
                //不设置默认为 true
            },
            container: {
                toolBarName: "toptoolbar",
                searchBtnName: "searchbtn",
                fromName: "dataForm",
                listGridName: "maingrid"
            }
        };

        function compoundXiaoneng(mchtId) {
            $.ajax({
                url: "${pageContext.request.contextPath}/xiaonengCustomerService/compoundXiaoneng.shtml",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: {"mchtId": mchtId},
                timeout: 30000,
                success: function (data) {
                    if ("0000" == data.returnCode) {
                        // $.ligerDialog.alert("成功合成","提示");
                        // commUtil.alertSuccess("成功合成");
                        p = {
                            type: 'question',
                            content: "商家小能信息成功合成",
                            buttons: []
                        };
                        $.ligerDialog(p)
                        setTimeout(function () {
                            listModel.ligerGrid.url = '${pageContext.request.contextPath}/xiaonengCustomerService/xiaonengCustomerServiceList.shtml';
                            listModel.initdataPage();
                            $.ligerDialog.hide();
                        }, 3000);
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error: function () {
                    $.ligerDialog.alert("系统繁忙,请联系管理员")
                }
            });
        }

        function xiaoNengExport() {
            //导出
            var data = listModel.gridManager.getSelectedRows();
            if (data.length == 0) {
                // $.ligerDialog.alert("请选择导出数据");
                // $.ligerDialog.confirm("请选择导出数据","导出提示");
                var btnclick = function (item, Dialog) {
                    Dialog.close();
                };
                p = {
                    type: 'question',
                    content: "请选择导出数据",
                    buttons: [{text: "确定", onclick: btnclick, type: 'yes'}]
                };
                $.ligerDialog(p)
            } else {
                var str = "";
                $(data).each(function () {
                    if (str == '') {
                        str = this.id;
                    } else {
                        str += "," + this.id;
                    }
                    $("#ids").val(str);
                });
                //执行导出
                $("#dataForm").attr("action", "${pageContext.request.contextPath}/xiaonengCustomerService/ExportXiaonengMcht.shtml");
                $("#dataForm").submit();

            }
        }


    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<!-- <div id="toptoolbar"></div> -->
<form id="dataForm" runat="server">
    <input type="hidden" name="flagStatus" value="1"/>
    <input type="hidden" id="ids" name="ids" value=""/>
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">商家序号：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="mchtCode" name="mchtCode">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="mchtName" name="mchtName">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">开通状态：</div>
                <div class="search-td-combobox-condition">
                    <select id="xiaonengFlag" name="xiaonengFlag" style="width: 135px;">
                        <option value="">请选择...</option>
                        <option value="0" selected="selected">未开通</option>
                        <option value="1">已开通</option>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">客服启用状态：</div>
                <div class="search-td-combobox-condition">
                    <select id="xiaonengStatus" name="xiaonengStatus" style="width: 135px;">
                        <option value="">请选择...</option>
                        <option value="0">停用</option>
                        <option value="1">启用</option>
                        <option value="2">未启用</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">客服序号：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="xiaonengId" name="xiaonengId">
                </div>
            </div>

<%--            <div class="search-td-export-btn">--%>
<%--            </div>--%>

            <div class="search-td-search-btn" style="display: inline-flex;">
                <div id="searchbtn">搜索</div>
                <div style="padding-left: 10px;">
                    <input type="button"
                           class="l-button"
                           style="width: 110px"
                           value="导出商家小能信息"
                           onclick="xiaoNengExport()" ; id="export">
                </div>
            </div>
        </div>
    </div>


</form>

<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
