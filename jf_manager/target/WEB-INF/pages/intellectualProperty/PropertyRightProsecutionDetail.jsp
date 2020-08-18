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
	var complainPicViewer;
	var prosecutionPicViewer;
	var submitFlag = true;
	var submitResultFlag = true;
    $(function(){
    	appealPicViewer = new Viewer(document.getElementById('appealPicViewer'), {});
    	obligeePicViewer = new Viewer(document.getElementById('obligeePicViewer'), {});
    	complainPicViewer = new Viewer(document.getElementById('complainPicViewer'), {});
    	prosecutionPicViewer = new Viewer(document.getElementById('prosecutionPicViewer'), {});
    });
  	
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
    
    // 提交审核
    function submitProsecutionAudit() {
        var status = $('input[name="status"]:checked').val();
        if (!status){
            $.ligerDialog.warn("请选择起诉跟进状态");
            return false;
        }

        // 备注（给权利人）
        var remarksToObligee = $('#remarksToObligee').val();
     	// 备注（给商家）
        var remarksToMcht = $('#remarksToMcht').val();
     	// 备注（给内部）
        var remarksToInner = $('#remarksToInner').val();
        
        var propertyRightProsecutionId = $('#propertyRightProsecutionId').val();

        if (!submitFlag) {
        	$.ligerDialog.warn("请勿重复提交");
            return false;
        }
        submitFlag = false;
        
        $.ajax({
            url : "${pageContext.request.contextPath}/propertyRightProsecution/updateStatus.shtml?",
            type : 'POST',
            dataType : 'json',
            data : {
            	id : propertyRightProsecutionId,
            	status: status,
            	remarksToObligee: remarksToObligee,
            	remarksToMcht: remarksToMcht,
            	remarksToInner: remarksToInner
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
    
 	// 提交结果
    function submitProsecutionResult() {
        var winType = $('input[name="winType"]:checked').val();
        if (!winType){
            $.ligerDialog.warn("请选择起诉结果");
            return false;
        }

        // 备注（给权利人）
        var resultToObligee = $('#resultToObligee').val();
     	// 备注（给商家）
        var resultToMcht = $('#resultToMcht').val();
     	// 内部备注
        var innerRemarks = $('#innerRemarks').val();
        
        var propertyRightProsecutionId = $('#propertyRightProsecutionId').val();

        if (!submitResultFlag) {
        	$.ligerDialog.warn("请勿重复提交");
            return false;
        }
        submitResultFlag = false;
        
        $.ajax({
            url : "${pageContext.request.contextPath}/propertyRightProsecution/submitResult.shtml?",
            type : 'POST',
            dataType : 'json',
            data : {
            	id : propertyRightProsecutionId,
            	winType: winType,
            	resultToObligee: resultToObligee,
            	resultToMcht: resultToMcht,
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
                submitResultFlag = true;
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
        <input type="hidden" id="propertyRightProsecutionId" value="${rightProsecutionCustom.id}">
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
    
    <br>
    <br>
    <div><span class="table-title">平台受理</span></div>
    <br>
    <table class="gridtable">
        <tr>
            <td colspan="1" class="title">受理状态</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.acceptStatusDesc}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">商家序号</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.mchtCode}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">备注（给权利人）</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.remarksToObligee}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">备注（给商家）</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.remarksToMcht}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">内部备注</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightAppealCustom.innerRemarks}
            </td>
        </tr>
    </table>
    
    <br>
    <br>
    <div><span class="table-title">商家声明</span></div>
    <br>
    <table class="gridtable">
        <tr>
            <td colspan="1" class="title">声明理由</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightComplainCustom.complainReason}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">声明材料</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <ul class="docs-pictures clearfix td-pictures" id="appealPicViewer">
                    <c:forEach items="${complainPicList}" var="complainPic">
                        <li><img src="${pageContext.request.contextPath}/file_servelt${complainPic.pic}" alt=""></li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </table>
    
    <br>
    <br>
    <div><span class="table-title">起诉/投诉信息</span></div>
    <br>
    <table class="gridtable">
        <tr>
            <td colspan="1" class="title">起诉证明</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <ul class="docs-pictures clearfix td-pictures" id="prosecutionPicViewer">
                    <c:forEach items="${prosecutionPics}" var="prosecutionPic">
                        <li><img src="${pageContext.request.contextPath}/file_servelt${prosecutionPic.pic}" alt=""></li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </table>

    <c:if test="${rightProsecutionCustom.acceptStatus == 1 && rightProsecutionCustom.status == 1 && rightProsecutionCustom.staffId == sessionScope.USER_SESSION.staffID}">
        <br>
        <br>
        <div>
        	<span class="table-title">平台起诉跟进</span>
        </div>
        <br>
        <table class="gridtable">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>起诉跟进</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="status" value="2" checked="checked">起诉成立
                    &nbsp; &nbsp;
                    <input type="radio" name="status" value="3">起诉无效
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">备注（给权利人）</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="remarksToObligee" name="remarksToObligee" cols="50"
                          class="text"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">备注（给商家）</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="remarksToMcht" name="remarksToMcht" cols="50"
                          class="text"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">内部备注</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="remarksToInner" name="remarksToInner" cols="50"
                          class="text"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">操作</td>
                <td align="left" class="l-table-edit-td">
                    <button type="button" class="l-button l-button-submit" onclick="submitProsecutionAudit()">提交</button>
                    <button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
                </td>
            </tr>
        </table>
    </c:if>
    
    <c:if test="${rightProsecutionCustom.acceptStatus == 1 && rightProsecutionCustom.status == 2 || rightProsecutionCustom.status == 3}">
        <br>
	    <br>
	    <div><span class="table-title">平台起诉跟进</span></div>
	    <br>
	    <table class="gridtable">
	        <tr>
	            <td colspan="1" class="title">起诉跟进</td>
	            <td colspan="3" align="left" class="l-table-edit-td">
	            <c:if test="${rightProsecutionCustom.status == 2}">
              		起诉成立
           		</c:if>
           		<c:if test="${rightProsecutionCustom.status == 3}">
              		起诉无效
           		</c:if>
	            </td>
	        </tr>
	        
	        <tr>
	            <td colspan="1" class="title">备注（权利人）</td>
	            <td colspan="3" align="left" class="l-table-edit-td">
              		${rightProsecutionCustom.remarksToObligee}
	            </td>
	        </tr>
	        
         	<tr>
	            <td colspan="1" class="title">备注（商家）</td>
	            <td colspan="3" align="left" class="l-table-edit-td">
              		${rightProsecutionCustom.remarksToMcht}
	            </td>
	        </tr>
	        
	        <tr>
	            <td colspan="1" class="title">内部备注</td>
	            <td colspan="3" align="left" class="l-table-edit-td">
              		${rightProsecutionCustom.remarkToInner}
	            </td>
	        </tr>
	        
	    </table>
    </c:if>
    
    <c:if test="${rightProsecutionCustom.acceptStatus == 1 && rightProsecutionCustom.status == 2 && rightProsecutionCustom.staffId == sessionScope.USER_SESSION.staffID}">
        <br>
        <br>
        <div>
        	<span class="table-title">平台提交结果</span>
        </div>
        <br>
        <table class="gridtable">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>起诉结果</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="winType" value="2" checked="checked">权利人败诉
                    &nbsp; &nbsp;
                    <input type="radio" name="winType" value="1">权利人胜诉
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">备注（给权利人）</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="resultToObligee" name="resultToObligee" cols="50"
                          class="text"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title">备注（给商家）</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="resultToMcht" name="resultToMcht" cols="50"
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
                    <button type="button" class="l-button l-button-submit" onclick="submitProsecutionResult()">提交</button>
                    <button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
                </td>
            </tr>
        </table>
    </c:if>
    
    <c:if test="${complainLogList!=null && fn:length(complainLogList) > 0}">
        <br>
        <br>
        <div><span class="table-title">声明日志</span>
        </div>
        <br>
        <table class="gridtable">
        	<tr>
                <td colspan="1" class="title" style="text-align: center;">提交时间</td>
                <td colspan="1" class="title" style="text-align: center;">操作者</td>
                <td colspan="1" class="title" style="text-align: center;">类型</td>
                <td colspan="2" class="title" style="text-align: center;">内容</td>
            </tr>
            <c:forEach items="${complainLogList}" var="complainLog">
            <tr>
                <td colspan="1">
                	<fmt:formatDate value="${complainLog.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
               	</td>
                <td colspan="1">${complainLog.operatorTypeDesc}</td>
                <td colspan="1">${complainLog.operateTypeDesc}</td>
                <td colspan="2">${complainLog.content}</td>
            </tr>
            </c:forEach>
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
