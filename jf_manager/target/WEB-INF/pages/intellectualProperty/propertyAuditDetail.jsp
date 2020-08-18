<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    /*显示大图*/
	.show-img {
		position: fixed;
		top: 0;
		left: 0;
		z-index: 11;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, .8);
	}
	.show-img img {
		width: auto;
		margin: 50vh auto 0;
		-webkit-transform: translateY(-50%);
		transform: translateY(-50%);
		display:block;
	}
	.blue {
		color: #2476ff;
	}
</style>
<script type="text/javascript">
    var obligeePicViewer;
    var obligeeProofViewer;
    var submitFlag = true;
    $(function(){
        obligeePicViewer = new Viewer(document.getElementById('obligeePicViewer'), {});
        obligeeProofViewer = new Viewer(document.getElementById('obligeeProofViewer'), {});
        
        $("input:radio[name='auditStatus']").change(function (){
            var auditStatus = $("input:radio[name='auditStatus']:checked").val();
            if (auditStatus == '1') {
            	$('#auditRemarksArea').hide();
            	$('#auditRemarks').val('');
            } else {
            	$('#auditRemarksArea').show();
            }
        });
    });
    
 	// 显示大图
    function showFile(ts) {
    	var _src = $(ts).data('src');
    	_src && $('body').append('<div class="show-img" onclick="hideFile()"><img src="' + _src + '"></div>');
    }
    function hideFile() {
    	$('.show-img').remove();
    }

    //提交审核
    function submitAudit() {
        var auditStatus = $('input[name="auditStatus"]:checked').val();
        if (!auditStatus){
            $.ligerDialog.warn("请选择审核状态");
            return false;
        }

        // 驳回原因
        var auditRemarks = '';
        if (auditStatus == '2') {
        	auditRemarks = $('#auditRemarks').val();
        	if (!auditRemarks) {
        		$.ligerDialog.warn("请填写驳回原因");
                return false;
        	}
        }
        
        var propertyRightId = $('#propertyRightId').val();

        if (!submitFlag) {
        	$.ligerDialog.warn("请勿重复提交");
            return false;
        }
        submitFlag = false;
        
        $.ajax({
            url : "${pageContext.request.contextPath}/intellectualPropertyRight/updateStatus.shtml?",
            type : 'POST',
            dataType : 'json',
            data : {
            	id : propertyRightId,
            	status: auditStatus,
            	auditRemarks: auditRemarks
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
    <div><span class="table-title">申请人信息</span>
    </div>
    <br>
    <table class="gridtable">
        <input type="hidden" id="propertyRightId" value="${rightCustom.id}">
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
    <div><span class="table-title">申请信息</span></div>
    <br>
    <table class="gridtable">
        <tr>
            <td colspan="1" class="title">产权类型</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightCustom.propertyRightTypeDesc}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">产权名称</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightCustom.propertyRightName}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">权利人</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                ${rightCustom.obligeeName}
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">权利人证明</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <ul class="docs-pictures clearfix td-pictures" id="obligeeProofViewer">
                    <c:forEach items="${obligeeProofList}" var="obligeeProof">
                        <li><img src="${pageContext.request.contextPath}/file_servelt/${obligeeProof.pic}" alt=""></li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">产权证明</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <ul class="docs-pictures clearfix td-pictures">
                    <c:forEach items="${rightProofList}" var="rightProof">
                        <div>
                        	<span>${rightProof.name}</span>
                        	<c:if test="${rightProof.pdf != null && rightProof.pdf}">
                        		<a class="blue" href="${pageContext.request.contextPath}/file_servelt${rightProof.pic}" target="_blank">【查看】</a>
                        	</c:if>
                        	<c:if test="${rightProof.pdf == null}">
                        		<a href="javascript:void(0);" class="blue" onclick="showFile(this)" data-src="${pageContext.request.contextPath}/file_servelt${rightProof.pic}">【查看】</a>
                        	</c:if>
                        </div>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">代理委托书</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <ul class="docs-pictures clearfix td-pictures">
                    <c:forEach items="${entrustProofList}" var="entrustProof">
                        <div>
                        	<span>${entrustProof.name}</span>
                        	<c:if test="${entrustProof.pdf != null && entrustProof.pdf}">
                        		<a class="blue" href="${pageContext.request.contextPath}/file_servelt${entrustProof.pic}" target="_blank">【查看】</a>
                        	</c:if>
                        	<c:if test="${entrustProof.pdf == null}">
                        		<a href="javascript:void(0);" class="blue" onclick="showFile(this)" data-src="${pageContext.request.contextPath}/file_servelt${entrustProof.pic}">【查看】</a>
                        	</c:if>
                        </div>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </table>


    <!-- 店铺信息 -->
    <c:if test="${rightCustom.status == 0 && 1 == canOperate}">
        <br>
        <br>
        <div><span class="table-title">审核信息</span>
        </div>
        <br>
        <table class="gridtable">
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>审核状态</td>
                <td colspan="3" align="left" class="l-table-edit-td">
                    <input type="radio" name="auditStatus" value="1">通过
                    &nbsp; &nbsp;
                    <input type="radio" name="auditStatus" value="2" checked="checked">驳回
                </td>
            </tr>
            <tr id="auditRemarksArea">
                <td colspan="1" class="title"><span class="required">*</span>驳回原因</td>
                <td colspan="7" align="left" class="l-table-edit-td">
                <textarea rows=2 id="auditRemarks" name="auditRemarks" cols="50"
                          class="text">${rightCustom.auditRemarks}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan="1" class="title"><span class="required">*</span>操作</td>
                <td align="left" class="l-table-edit-td">
                    <button type="button" class="l-button l-button-submit" onclick="submitAudit()">提交</button>
                    <button style="margin-left: 20px;" class="l-button" type="button" onclick="frameElement.dialog.close()">取消</button>
                </td>
            </tr>
        </table>
    </c:if>
</form>
</body>
</html>
