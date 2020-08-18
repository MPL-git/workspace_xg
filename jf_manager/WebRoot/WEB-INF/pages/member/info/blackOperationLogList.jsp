<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/search_form.css"
          rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

    <script type="text/javascript">
        var listConfig = {
            url: "/member/blackOperationData.shtml",
            listGrid: {
                columns: [
                    {display: '会员ID', name: 'memberId', width: 140},
                    {display: '会员昵称', name: 'memberNick', width: 140},
                    {
                        display: '操作类型', name: 'operateType', width: 140, render: function (rowdata, rowindex) {
                            var str = '加入黑名单';
                            if (rowdata.operateType === '2') {
                                str = '编辑黑名单';
                            } else if (rowdata.operateType === '3') {
                                str = '移出黑名单';
                            }
                            return str;
                        }
                    },
                    {
                        display: '限制功能', name: 'limitFunction', width: 160, render: function (rowdata, rowindex) {
                            if (!rowdata.limitFunction) {
                                return '——';
                            }
                            var str = '';
                            var limitFunction = rowdata.limitFunction;
                            if (limitFunction.indexOf('1') != -1) {
                                str += '评价';
                            }
                            if (limitFunction.indexOf('2') != -1) {
                                str += str === ''?'购买':'、购买';
                            }
                            if (limitFunction.indexOf('3') != -1) {
                                str += str === ''?'登录':'、登录';
                            }
                            return str;
                        }
                    },
                    {display: '理由', name: 'blackReason', width: 140, render: function (rowdata, rowindex) {
                        if (!rowdata.blackReason) {
                            return '——';
                        }
                        return rowdata.blackReason;
                    }},
                    {display: '内部备注', name: 'blackInnerRemarks', width: 140, render: function (rowdata, rowindex) {
                        if (!rowdata.blackInnerRemarks) {
                            return '——';
                        }
                        return rowdata.blackInnerRemarks;
                    }},
                    {display: '操作人', name: 'operator', width: 140},
                    { display: '操作时间', render: function (rowdata, rowindex) {
                        if (rowdata.createDate!=null){
                            var createDate = new Date(rowdata.createDate);
                            return createDate.format("yyyy-MM-dd hh:mm:ss");
                        }
                    }}
                    ],
                showCheckbox: false, //不设置默认为 true
                showRownumber: true //不设置默认为 true
            },
            container: {
                searchBtnName: "searchbtn",
                fromName: "dataForm",
                listGridName: "maingrid"
            }
        };
    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">会员id：</div>
                <div class="search-td-condition">
                    <input type="text" id="memberId" name="memberId">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">会员昵称：</div>
                <div class="search-td-condition">
                    <input type="text" id="memberNick" name="memberNick">
                </div>
            </div>
            
            <div class="search-td">
                <div class="search-td-label">限制功能：</div>
                <div class="search-td-combobox-condition">
                    <select id="limitType" name="limitType">
                        <option value="">请选择</option>
                        <option value="1">评价</option>
                        <option value="2">购买</option>
                        <option value="3">登录</option>
                    </select>
                </div>
            </div>

            <div class="search-td-search-btn">
                <div id="searchbtn">搜索</div>
            </div>
        </div>
    </div>
    
    <div class="search-pannel">
        <div class="search-tr">
        
        <div class="search-td">
			<div class="search-td-label" style="float:left;">操作时间：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="createDate_begin" name="createDate_begin" />
				<script type="text/javascript">
					$(function() {
						$("#createDate_begin").ligerDateEditor( {
							showTime : false,
							labelWidth : 150,
							width:150,
							labelAlign : 'left'
						});
					});
				</script>
			</div>
		<div class="l-panel-search-item" >&nbsp;&nbsp;至：</div>
		</div>
		
		<div class="search-td">
		<div class="l-panel-search-item">
			<input type="text" id="createDate_end" name="createDate_end" />
			<script type="text/javascript">
				$(function() {
					$("#createDate_end").ligerDateEditor( {
						showTime : false,
						labelWidth : 150,
						width:150,
						labelAlign : 'left'
					});
				});
			</script>	
		</div>
		</div>
    </div>
    </div>
</form>
<div id="maingrid" style="margin: 0; padding: 0"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>
</html>