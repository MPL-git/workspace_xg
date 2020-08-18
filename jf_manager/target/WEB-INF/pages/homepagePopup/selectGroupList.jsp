<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title></title>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#update_begin").ligerDateEditor({
                showTime: false,
                labelWidth: 150,
                labelAlign: 'left'
            });
        });

        $(function () {
            $("#update_end").ligerDateEditor({
                showTime: false,
                labelWidth: 150,
                labelAlign: 'left'
            });
        });


        function add(id) {
            var a = "#A" + id;
            var b = "#B" + id;
            var selectGroup = $("#selectGroup").val();
            $.ajax({
                method: 'POST',
                url: "${pageContext.request.contextPath}/homepagePopup/addGroup.shtml",
                secureuri: false,
                dataType: 'json',
                data: {"id": id, "selectGroup": selectGroup},
                success: function (data) {
                    if ("0000" == data.returnCode) {
                        $(a).hide();
                        $(b).show();
                        $("#selectGroup").attr("value", data.selectGroup)
                        $.ligerDialog.success('添加成功');
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error: function () {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            })
        }

        var listConfig = {
            url: "/memberLabel/groupListdata.shtml",
            listGrid: {
                columns: [
                    {display: '分组名称', name: 'labelGroupName', width: 230},
                    {display: '分组包含用户数', name: 'statNum', width: 130},
                    {display: '分组条件', name: 'labelTypeName', width: 230},
                    {display: '备注', name: 'remarks', width: 230},
                    {display: '创建人', name: 'staffName', width: 100},
                    {
                        display: '更新时间', name: '', width: 200, render: function (rowdata, rowindex) {
                            if (rowdata.updateDate != null && rowdata.updateDate != "") {
                                var updateDate = new Date(rowdata.updateDate);
                                return updateDate.format("yyyy-MM-dd hh:mm:ss");
                            }
                        }
                    },
                    {
                        display: '操作',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.status == '0') {
                                html.push("<a href=\"javascript:value(0);\">已作废</a>&nbsp&nbsp");
                            } else {
                                var selectGroup = $("#selectGroup").val();
                                var strings = selectGroup.split(",");
                                var flag = true;
                                for (var i = 0; i < strings.length; i++) {
                                    if (rowdata.id == strings[i]) {
                                        html.push("<a id=" + "B" + rowdata.id + " >已添加</a>&nbsp&nbsp");
                                        flag = false;
                                        break;
                                    }
                                }
                                if (flag){
                                        html.push("<a id=" + "A" + rowdata.id + " href=\"javascript:add(" + rowdata.id + ");\" >添加</a>&nbsp&nbsp");
                                        html.push("<a id=" + "B" + rowdata.id + " style=\"display:none;\" >已添加</a>&nbsp&nbsp");
                                }
                            }
                            return html.join("");
                        }
                    },
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
        var dialog = frameElement.dialog;
        var dialogData = dialog.get('data');//获取data参数
        dialog.set('title','选择分组'); //设置标题


        /*function commit(){
            debugger;
            var dataJson = $("#tranForm").serializeArray();
            <%--post("${pageContext.request.contextPath}/homepagePopup/addIndexPopupData.shtml",dataJson);--%>
        };

        function post(url, params) {
            // 创建form元素
            var temp_form = document.createElement("form");
            // 设置form属性
            temp_form .action = url;
            temp_form .target = "_self";
            temp_form .method = "post";
            temp_form .style.display = "none";
            // 处理需要传递的参数
            for (var x in params) {
                var opt = document.createElement("textarea");
                opt.name = params[x].name;
                opt.value = params[x].value;
                temp_form .appendChild(opt);
            }
            document.body.appendChild(temp_form);
            // 提交表单
            temp_form .submit();
        }*/

    </script>

</head>

<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar">
<%--    <input type="button" style="float:right" class="l-button l-button-submit" value="提交" onclick="commit()">--%>
<%--        <span style="float:right" >(添加完想要添加的分组后点击提交)</span>--%>
<%--    <form id="tranForm" method="post" action="" >--%>
    <form id="tranForm" method="post" action="${pageContext.request.contextPath}/homepagePopup/addIndexPopupData.shtml" >
        <input type="hidden" id="selectGroup" name="selectGroup" value="${indexPopupAdCustom.selectGroup}">
        <input type="hidden" id="id" name="id" value="${indexPopupAdCustom.id}"/>
        <input type="hidden" id="status" name="status" value="${indexPopupAdCustom.status}"/>
        <input type="hidden" id="indexPopupAdCustom" name="indexPopupAdCustom" value="${indexPopupAdCustom}"/>

        <input type="hidden" id="popupDesc" name="popupDesc" value="${indexPopupAdCustom.popupDesc}">
        <input type="hidden" id="pic" name="pic" value="${indexPopupAdCustom.pic}">
        <input type="hidden" id="linkType" name="linkType" value="${indexPopupAdCustom.linkType}">
        <input type="hidden" id="linkContent" name="linkContent" value="${indexPopupAdCustom.linkContent }">
        <input type="hidden" id="popupCount" name="popupCount" value="${indexPopupAdCustom.popupCount}">
<%--        <input type="hidden" id="upDate" name="upDate" value="${indexPopupAdCustom.upDate}">--%>
<%--        <input type="hidden" id="downDate" name="downDate" value="${indexPopupAdCustom.downDate}">--%>
        <input type="submit" name="submit" style="float: right" class="l-button l-button-submit" value="提交"/>
        <span style="float:right" >(添加完想要添加的分组后点击提交)</span>
    </form>
</div>
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label" style="float:left;display: inline-block;">分组名称：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="labelGroupName" name="labelGroupName">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label" style="float:left;">创建日期：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="update_begin" name="update_begin" value=""/>
                </div>
            </div>
            <%--			<div class="l-panel-search-item" >&nbsp;至：</div>--%>
            <span>&nbsp;至：</span>
            <div class="search-td">
                <div class="l-panel-search-item">
                    <input type="text" id="update_end" name="update_end" value=""/>
                </div>
            </div>
            <div class="search-td-search-btn">
                <div id="searchbtn">搜索</div>
            </div>
        </div>
    </div>
    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>
</html>
