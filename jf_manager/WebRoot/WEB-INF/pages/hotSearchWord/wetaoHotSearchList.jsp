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
                showTime: false,
                labelAlign: 'left'
            });
        });

        //排序
        function updateArtNo(hotSearchWordId) {
            $("#seqNo" + hotSearchWordId).parent().find("span").remove();
            var seqNo = $("#seqNo" + hotSearchWordId).val();
            var flag = seqNo.match(/^[1-9]\d*$/);
            if (seqNo != '' && flag != null) {
                $.ajax({
                    type: 'POST',
                    url: "${pageContext.request.contextPath}/wetaoHotSearchWord/updateSeqNo.shtml",
                    data: {hotSearchWordId: hotSearchWordId, seqNo: seqNo},
                    dataType: 'json',
                    success: function (data) {
                        if (data == null || data.code != 200)
                            commUtil.alertError(data.msg);
                        else {
                            listModel.gridManager.reload();
                            $("#seqNo" + hotSearchWordId).attr("seqNo", seqNo);
                        }
                    },
                    error: function (e) {
                        commUtil.alertError("系统异常请稍后再试");
                    }
                });
            } else {
                $("#seqNo" + hotSearchWordId).val($("#seqNo" + hotSearchWordId).attr("seqNo"));
                $("#seqNo" + hotSearchWordId).parent().append("<span style='color:red;'>请输入正整数</span>");
            }
        }

        //上架或下架
        function updateStatus(hotSearchWordId, status) {
            var titleStr = '是否上架？';
            if (status == '0') {
                titleStr = '是否下架？';
            }
            $.ligerDialog.confirm(titleStr, function (yes) {
                if (yes) {
                    $.ajax({
                        type: 'POST',
                        url: "${pageContext.request.contextPath}/wetaoHotSearchWord/updateStatus.shtml",
                        data: {hotSearchWordId: hotSearchWordId, status: status},
                        dataType: 'json',
                        success: function (data) {
                            if (data == null || data.code != 200)
                                commUtil.alertError(data.msg);
                            else {
                                $("#searchbtn").click();
                            }
                        },
                        error: function (e) {
                            commUtil.alertError("系统异常请稍后再试");
                        }
                    });
                }
            });
        }

        //删除
        function delHotSearchWord(hotSearchWordId) {
            $.ligerDialog.confirm('是否删除？', function (yes) {
                if (yes) {
                    $.ajax({
                        type: 'POST',
                        url: "${pageContext.request.contextPath}/wetaoHotSearchWord/delHotSearchWord.shtml",
                        data: {hotSearchWordId: hotSearchWordId},
                        dataType: 'json',
                        success: function (data) {
                            if (data == null || data.code != 200)
                                commUtil.alertError(data.msg);
                            else {
                                $("#searchbtn").click();
                            }
                        },
                        error: function (e) {
                            commUtil.alertError("系统异常请稍后再试");
                        }
                    });
                }
            });
        }

        //添加
        function addHotSearchWord() {
            $.ligerDialog.open({
                height: 400,
                width: 500,
                title: "添加",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/wetaoHotSearchWord/addHotSearchWordManager.shtml",
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        var listConfig = {
            url: "/wetaoHotSearchWord/getHotSearchWordList.shtml",
            btnItems: [
                {text: '添加', icon: 'add', id: 'add', dtype: 'win', click: addHotSearchWord}
            ],
            listGrid: {
                columns: [
                    {
                        display: '排序值',
                        name: 'seqNo',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            var seqNo = rowdata.seqNo == null ? '' : rowdata.seqNo;
                            html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='" + seqNo + "' onChange='updateArtNo(" + rowdata.id + ")' value='" + seqNo + "' >");
                            return html.join("");
                        }
                    },
                    {display: '关键词', name: 'word', align: 'center', isSort: false, width: 180},
                    {
                        display: '标签',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            console.log(rowdata.tag);
                            if (rowdata.tag == "1") {
                                html.push("热")
                            } else if (rowdata.tag == "2") {
                                html.push("荐")
                            } else if (rowdata.tag == null) {
                                html.push("/")
                            }
                            return html.join("");
                        }
                    },
                    {display: '热搜状态', name: 'statusDesc', align: 'center', isSort: false, width: 180},
                    {
                        display: '创建时间',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            if (rowdata.createDate != null && rowdata.createDate != '') {
                                var createDate = new Date(rowdata.createDate);
                                html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
                            }
                            return html.join("");

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
                                html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '1');\">【上架】</a>");
                                html.push("<a href=\"javascript:delHotSearchWord(" + rowdata.id + ");\">【删除】</a>");
                            } else {
                                html.push("<a href=\"javascript:updateStatus(" + rowdata.id + ", '0');\">【下架】</a>");
                            }
                            return html.join("");
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
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">关键词：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="word" name="word">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">状态：</div>
                <div class="search-td-combobox-condition">
                    <select id="status" name="status" style="width: 135px;">
                        <option value="">请选择...</option>
                        <c:forEach var="status" items="${statusList }">
                            <option value="${status.statusValue }">${status.statusDesc }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="search-td-search-btn" style="display: inline-flex;">
                <div id="searchbtn" class="l-button">搜索</div>
            </div>
        </div>
    </div>
</form>

<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
