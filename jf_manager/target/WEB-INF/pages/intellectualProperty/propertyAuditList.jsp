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
        // 复制
        function view(id, flag) {
        	var dialogTitle = flag == 1?"知识产权审核":"知识产权详情";
           $.ligerDialog.open({
               height: 800,
               width: 1500,
               title: dialogTitle,
               name: "INSERT_WINDOW",
               url: "${pageContext.request.contextPath}/intellectualPropertyRight/auditManageDetail.shtml?id=" + id,
               showMax: true,
               showToggle: false,
               showMin: false,
               isResize: true,
               slide: false,
               data: null
           });
        }

        var listConfig = {
            url: "/intellectualPropertyRight/auditManageData.shtml",
            btnItems: [],
            listGrid: {
                columns: [
                    { display: '提审日期', width: 150, render: function (rowdata, rowindex) {
	                	if (rowdata.createDate!=null){
							var createDate=new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
	                	}
	                }},
                    {display: '产权编号', name: 'id', align: 'center', isSort: false, width: 180},
                    {
                        display: '用户类型',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 180,
                        render: function (rowdata, rowindex) {
                        	var html = [];
                            var identityType = rowdata.identityType;
                            if (identityType != null && identityType != '') {
                                var identityTypeDesc = identityType == '1' ? '个人' : '企业';
                                html.push(identityTypeDesc);
                            }
                            // var propertyRightBelong = rowdata.propertyRightBelong;
                            // if (propertyRightBelong != null && propertyRightBelong != '') {
                            //     var propertyRightBelongDesc = propertyRightBelong == '1' ? '权利人' : '代理人';
                            //     html.push("/");
                            //     html.push(propertyRightBelongDesc);
                            // }
                            return html.join("");
                        }
                    },
                    {display: '产权权利人', name: 'obligeeName', align: 'center', isSort: false, width: 180},
                    {display: '产权类型', name: 'propertyRightTypeDesc', align: 'center', isSort: false, width: 180},
                    {display: '产权名称', name: 'propertyRightName', align: 'center', isSort: false, width: 180},
                    {
                        display: '审核状态',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 100,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            var status = rowdata.status;
                            if (status == '0' && 1 == ${canOperate}) {
                            	html.push("<a href=\"javascript:view(" + rowdata.id + "\," + 1 + ");\">【"+ rowdata.statusDesc +"】</a>");
                            } else {
                            	html.push(rowdata.statusDesc);
                            }
                            return html.join("");
                        }
                    },
                    {display: '备注/驳回原因', name: 'auditRemarks', align: 'center', isSort: false, width: 180},
                    {
                        display: '操作',
                        name: '',
                        align: 'center',
                        isSort: false,
                        width: 100,
                        render: function (rowdata, rowindex) {
                            var html = [];
                            html.push("<a href=\"javascript:view(" + rowdata.id + ");\">【查看】</a>");
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
<!-- <div id="toptoolbar"></div> -->
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label">产权类型：</div>
                <div class="search-td-combobox-condition">
                    <select id="rightType" name="rightType" style="width: 135px;">
                        <option value="">请选择...</option>
                        <c:forEach items="${rightTypeList }" var="rightType">
                            <option value="${rightType.statusValue }">${rightType.statusDesc }</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">产权名称：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="rightName" name="rightName">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">产权人：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="obligeeName" name="obligeeName">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">审核状态：</div>
                <div class="search-td-combobox-condition">
                    <select id="status" name="status" style="width: 135px;">
                        <option value="">请选择...</option>
                        <c:forEach items="${statusList }" var="status">
                            <option value="${status.statusValue }">${status.statusDesc }</option>
                        </c:forEach>
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
			<div class="search-td-label" style="float:left;">提审日期：</div>
			<div class="l-panel-search-item" >
				<input type="text" id="create_date_begin" name="createDateBegin" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_begin").ligerDateEditor( {
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
				<input type="text" id="create_date_end" name="createDateEnd" />
				<script type="text/javascript">
					$(function() {
						$("#create_date_end").ligerDateEditor( {
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

<div id="maingrid" style="margin: 0;"></div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
