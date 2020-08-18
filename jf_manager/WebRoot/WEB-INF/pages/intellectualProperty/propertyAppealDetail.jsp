<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
      rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
        type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
      rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
      rel="stylesheet" type="text/css"/>

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
        type="text/javascript"></script>

<style type="text/css">
    body {
        font-size: 12px;
        padding: 10px;
    }

    td input, td select {
        border: 1px solid #AECAF0;
    }

    .radioClass {
        margin-right: 20px;
    }

    .l-table-edit {

    }

    .l-table-edit-td {
        padding: 4px;
    }

    .l-button-submit, .l-button-test {
        width: 80px;
        float: left;
        margin-left: 10px;
        padding-bottom: 2px;
    }

    .l-verify-tip {
        left: 230px;
        top: 120px;
    }

    .table-title-link {
        color: #1B17EE;
        font-size: 15px;
        text-decoration: none;
    }

    .table-title {
        font-size: 17px;
        font-weight: 600;
    }
</style>
<style type="text/css">
    .middle input {
        display: block;
        width: 30px;
        margin: 2px;
    }

    table.l-checkboxlist-table td {
        border-style: none;
    }

    table.l-listbox-table td {
        border-style: none;
    }

    .td-pictures li {
        display: inline-block;
    }

    td img {
        width: 60px;
        height: 40px;
    }
</style>
<script type="text/javascript">
    var appealPicViewer;
    var obligeePicViewer;
    var mchtCode;// 商家序号
    var mchtCheckFlag;// 商家序号检测flag
    var submitFlag = true;// 提交审核flag
    $(function(){
    	mchtCheckFlag = false;
    	appealPicViewer = new Viewer(document.getElementById('appealPicViewer'), {});
    	obligeePicViewer = new Viewer(document.getElementById('obligeePicViewer'), {});
    	
    	$("input:radio[name='acceptStatus']").change(function (){
            var acceptStatus = $("input:radio[name='acceptStatus']:checked").val();
            if (acceptStatus == '2') {
            	$('#remarksToMchtArea').hide();
            	$('#checkInfoArea').hide();
            	$('#remarksToMcht').val('');
            	$('#mchtCode').val('');
            } else {
            	$('#remarksToMchtArea').show();
            	$('#checkInfoArea').show();
            }
        });
    });
    
  	//检测
    function checkMchtInfo() {
        mchtCode = $('#mchtCode').val();
        mchtCheckFlag = false;
        if (!mchtCode){
            $.ligerDialog.warn("请填写商家序号再点击检测");
            return false;
        }
        
        $.ligerDialog.open({
            height: 200,
            width: 600,
            title: "商家信息",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/propertyRightAppeal/checkMchtInfo.shtml?mchtCode=" + mchtCode,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }
  	
    // 查看知识产权详情
    function viewRight(id) {
       $.ligerDialog.open({
           height: 600,
           width: 1200,
           title: "知识产权详情",
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
    
    // 创建工单
    function addWork(id) {
       $.ligerDialog.open({
           height: 600,
           width: 1200,
           title: "新增工单",
           name: "INSERT_WINDOW",
           url: "${pageContext.request.contextPath}/work/addwork.shtml?propertyRightId=" + id,
           showMax: true,
           showToggle: false,
           showMin: false,
           isResize: true,
           slide: false,
           data: null
       });
    }
    
    //提交审核
    function submitAppealAudit() {
        var acceptStatus = $('input[name="acceptStatus"]:checked').val();
        if (!acceptStatus){
            $.ligerDialog.warn("请选择审核状态");
            return false;
        }
       	// 判断商家序号是否检测，以及检测后的商家序号是否更改过
        if (acceptStatus != '2' && (!mchtCheckFlag || mchtCode != $('#mchtCode').val())) {
       	 	$.ligerDialog.warn("请检测商家序号");
            return false;
        }

        // 备注（给权利人）
        var remarksToObligee = $('#remarksToObligee').val();
     	// 备注（给商家）
        var remarksToMcht = $('#remarksToMcht').val();
     	// 备注（内部备注）
        var innerRemarks = $('#innerRemarks').val();
     	
     	// 如果是不符合 则备注给权利人必填
        if (acceptStatus == '2' && (remarksToObligee == null || remarksToObligee == '')) {
       	 	$.ligerDialog.warn("请备注给权利人");
            return false;
        }
        
        var propertyRightAppealId = $('#propertyRightAppealId').val();
        
        if (!submitFlag) {
        	$.ligerDialog.warn("请勿重复提交");
            return false;
        }
        submitFlag = false;
        
        $.ajax({
            url : "${pageContext.request.contextPath}/propertyRightAppeal/updateAcceptStatus.shtml?",
            type : 'POST',
            dataType : 'json',
            data : {
            	id : propertyRightAppealId,
            	mchtCode: mchtCode,
            	acceptStatus: acceptStatus,
            	remarksToObligee: remarksToObligee,
            	remarksToMcht: remarksToMcht,
            	innerRemarks: innerRemarks
           	},
            cache : false,
            async : false,
            timeout : 30000,
            success : function(data) {
                if (data.code == '200') {
                    $.ligerDialog.success("操作成功",function() {
                		parent.$("#searchbtn").click();
    					frameElement.dialog.close();
					});
                } else {
                    $.ligerDialog.error(data.message || '系统错误！');
                }
                submitFlag = true;
            },
            error : function() {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });
    }

</script>

<html>
<body>
<form method="post" id="form1" name="form1">
    <div><span class="table-title">投诉人信息</span>
    </div>
    <br>
    <table class="gridtable">
        <input type="hidden" id="propertyRightAppealId" value="${rightAppealCustom.id}">
        <tr>
            <td colspan="1" class="title">用户类型</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${obligee.identityTypeDesc}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">权利人姓名</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${obligee.name}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">权利人身份证明</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <ul class="docs-pictures clearfix td-pictures" id="obligeePicViewer">
                    <c:forEach items="${obligeePicList}" var="obligeePic">
                        <li><img src="${pageContext.request.contextPath}/file_servelt${obligeePic.pic}" alt=""></li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </table>

    <br>
    <br>
    <div><span class="table-title">投诉信息</span></div>
    <br>
    <table class="gridtable">
        <tr>
            <td colspan="1" class="title">知识产权</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightCustom.propertyRightName}
              	<a href="javascript:viewRight(${rightAppealCustom.intellectualPropertyRightId});">【查看产权】</a>
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">投诉理由</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.appealReasonDesc}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">投诉类型</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.appealTypeDesc}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">相关商品ID/店铺名称</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.relevantValue}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">理由说明</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.reasonDesc}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">举证材料</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <ul class="docs-pictures clearfix td-pictures" id="appealPicViewer">
                    <c:forEach items="${appealPicList}" var="appealPic">
                        <li><img src="${pageContext.request.contextPath}/file_servelt${appealPic.pic}" alt=""></li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </table>

    <c:if test="${rightAppealCustom.acceptStatus == 0 && rightAppealCustom.staffId == sessionScope.USER_SESSION.staffID}">
        <br>
        <br>
        <div>
        	<span class="table-title">受理审核
        		<a href="javascript:addWork(${rightAppealCustom.id});">【创建工单】</a>
        	</span>
        </div>
        <br>
        <table class="gridtable">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>受理状态</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="acceptStatus" value="1" checked="checked">受理
                    &nbsp; &nbsp;
                    <input type="radio" name="acceptStatus" value="2">不符合
                </td>
            </tr>
            <tr id="checkInfoArea">
                <td colspan="1" class="title"><span class="required">*</span>商家序号</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                <div>
               		<input id="mchtCode" name="mchtCode" type="text" style="margin-left: 5px;height: 20px;">
               		<button type="button" class="l-button l-button-submit" onclick="checkMchtInfo()">检测</button>
               	</div>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">备注（给权利人）</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="remarksToObligee" name="remarksToObligee" cols="50"
                          class="text"></textarea>
                </td>
            </tr>
            <tr id="remarksToMchtArea">
                <td colspan="1" class="title">备注（给商家）</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="remarksToMcht" name="remarksToMcht" cols="50"
                          class="text"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">内部备注</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="innerRemarks" name="innerRemarks" cols="50"
                          class="text"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">操作</td>
                <td align="left" class="l-table-edit-td">
                    <button type="button" class="l-button l-button-submit" onclick="submitAppealAudit()">提交</button>
                    <button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
                </td>
            </tr>
        </table>
    </c:if>
    
    <c:if test="${appealLogList!=null && fn:length(appealLogList) > 0}">
        <br>
        <br>
        <div><span class="table-title">投诉日志</span>
        </div>
        <br>
        <table class="gridtable">
        	<tr>
                <td colspan="1" class="title" style="text-align: center;">时间</td>
                <td colspan="1" class="title" style="text-align: center;">操作者</td>
                <td colspan="2" class="title" style="text-align: center;">内容</td>
            </tr>
            <c:forEach items="${appealLogList}" var="appealLog">
            <tr>
                <td colspan="1">
                	<fmt:formatDate value="${appealLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
               	</td>
                <td colspan="1">${appealLog.operatorTypeDesc}</td>
                <td colspan="2">${appealLog.content}</td>
            </tr>
            </c:forEach>
        </table>
    </c:if>
</form>
</body>
</html>
