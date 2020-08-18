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
        // 审核/查看
        function view(id) {
           $.ligerDialog.open({
               height: 800,
               width: 1500,
               title: "投诉信息",
               name: "INSERT_WINDOW",
               url: "${pageContext.request.contextPath}/propertyRightAppeal/appealManageDetail.shtml?id=" + id,
               showMax: true,
               showToggle: false,
               showMin: false,
               isResize: true,
               slide: false,
               data: null
           });
        }
        
     	// 创建工单
        function addWork(id) {
           $.ligerDialog.open({
               height: 800,
               width: 1500,
               title: "新增工单",
               name: "INSERT_WINDOW",
               url: "${pageContext.request.contextPath}/work/addwork.shtml?rightAppealId=" + id,
               showMax: true,
               showToggle: false,
               showMin: false,
               isResize: true,
               slide: false,
               data: null
           });
        }
        
      	// 领取
        function updateStaffId(id) {
            if (!id){
                $.ligerDialog.warn("数据错误，请刷新页面");
                return false;
            }

            $.ajax({
                url : "${pageContext.request.contextPath}/propertyRightAppeal/updateStaffId.shtml?",
                type : 'POST',
                dataType : 'json',
                data : {
                	id : id
                	},
                cache : false,
                async : false,
                timeout : 30000,
                success : function(data) {
                    if (data.code == '200') {
                        $.ligerDialog.success("操作成功",function() {
                    		$("#searchbtn").click();
    					});
                    } else {
                        $.ligerDialog.error(data.message || '系统错误！');
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        var listConfig = {
            url: "/propertyRightAppeal/appealManageData.shtml",
            btnItems: [],
            listGrid: {
                columns: [
					{ display: '提审时间', width: 150, render: function (rowdata, rowindex) {
						if (rowdata.createDate!=null){
							var createDate=new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}
					}},
					{display: '投诉ID', name: 'id', align: 'center', isSort: false, width: 140},
					{
					    display: '申请人类型',
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
					{display: '申请人手机号码', name: 'mobile', align: 'center', isSort: false, width: 180},
					{display: '投诉类型', name: 'appealTypeDesc', align: 'center', isSort: false, width: 180},
					{display: '商品ID/店铺名称', name: 'relevantValue', align: 'center', isSort: false, width: 140},
					{display: '相关产权ID', name: 'intellectualPropertyRightId', align: 'center', isSort: false, width: 180},
					{display: '受理状态', name: 'acceptStatusDesc', align: 'center', isSort: false, width: 180},
					{display: '内部备注', name: 'innerRemarks', align: 'center', isSort: false, width: 180},
					{
					    display: '领取人',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					        var html = [];
					        var staffId = rowdata.staffId;
					        var acceptStatus = rowdata.acceptStatus;
					        if (acceptStatus == '0' && !staffId && 1 == ${canOperate}) {
					        	html.push("<a href=\"javascript:updateStaffId(" + rowdata.id + ");\">领取</a>");
					        } else {
					        	html.push(rowdata.staffName);
					        }
					        return html.join("");
					    }
					},
					{
					    display: '操作',
					    name: '',
					    align: 'center',
					    isSort: false,
					    width: 150,
					    render: function (rowdata, rowindex) {
					        var html = [];
					        var acceptStatus = rowdata.acceptStatus;
					        if (acceptStatus == '0' && rowdata.staffId == ${sessionScope.USER_SESSION.staffID}) {
					        	html.push("<a href=\"javascript:view(" + rowdata.id + ");\">受理</a>");
					        	html.push("&nbsp;&nbsp;");
					        	html.push("<a href=\"javascript:addWork(" + rowdata.id + ");\">创建工单</a>");
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
<!-- <div id="toptoolbar"></div> -->
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
			<div class="search-td">
                <div class="search-td-label">投诉ID：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="rightAppealId" name="rightAppealId">
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">产权ID：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="propertyRightId" name="propertyRightId">
                </div>
            </div>

            <div class="search-td">
                <div class="search-td-label">投诉类型：</div>
                <div class="search-td-combobox-condition">
                    <select id="appealType" name="appealType" style="width: 135px;">
                        <option value="">请选择...</option>
                        <c:forEach items="${appealTypeList}" var="appealType">
                            <option value="${appealType.statusValue}">${appealType.statusDesc}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="search-td">
                <div class="search-td-label">申请人手机号码：</div>
                <div class="search-td-combobox-condition">
                    <input type="text" id="mobile" name="mobile">
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
