<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- Liger  --%>
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>

<script type="text/javascript">
    var listConfig={
        url:'/member/loginLogInfo.shtml?memberId=${memberId}',

        listGrid:{ columns: [
                { display: '会员ID', name: 'memberId'},
                { display: '操作系统', name: 'system'},
                { display: '手机品牌', name: 'mobileBrand'},
                { display: '手机型号', name: 'mobileModel'},
                { display: '版本', name: 'version'},
                { display: '版本号', name: 'versionNum'},
                { display: 'ip地址', name: 'ip'},
                { display: '设备序列', name: 'imei' },
                { display: '创建日期', name: '' , render: function(rowdata, rowindex) {
                        if (rowdata.createDate != null){
                            var createDate = new Date(rowdata.createDate);
                            return createDate.format("yyyy-MM-dd hh:mm:ss");
                        }
                    }},
                { display: '更新日期', name: '' , render: function(rowdata, rowindex) {
                        if (rowdata.updateDate != null){
                            var updateDate = new Date(rowdata.updateDate);
                            return updateDate.format("yyyy-MM-dd hh:mm:ss");
                        }
                    }}
                ],
            showCheckbox : false,  //不设置默认为 true
            showRownumber: true //不设置默认为 true
        } ,
        container:{
            toolBarName:"toptoolbar",
            searchBtnName:"searchbtn",
            fromName:"dataForm",
            listGridName:"maingrid"
        }

    }
</script>
<body style="padding: 0px; overflow: hidden;">
<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
    <div class="l-loading" style="display: block" id="pageloading"></div>
    <form id="dataForm" runat="server">
        <div id="topmenu"></div>
        <div id="toptoolbar"></div>
        <div class="l-panel-search"  style="display:none;">

            <div class="l-panel-search-item">
                <input type="hidden" id="INFO_TYPE" name="INFO_TYPE" value="${INFO_TYPE}"/>
            </div>
            <div class="l-panel-search-item">
                <div id="searchbtn" style="width: 80px;" >

                    <label type="hidden" > 搜索</label>
                </div>
            </div>
        </div>
        <div id="maingrid" style="margin: 0; padding: 0"></div>
    </form>

    <div style="display: none;">
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
