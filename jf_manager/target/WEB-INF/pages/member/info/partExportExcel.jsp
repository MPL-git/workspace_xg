<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script	src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>

<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<style type="text/css">
    body {
        font-size: 12px;
        padding: 10px;
    }

    td input, td select {
        border: 1px solid #AECAF0;
    }

    .l-button-submit, .l-button-test {
        width: 80px;
        float: left;
        margin-left: 10px;
        padding-bottom: 2px;
    }

    td img {
        width: 60px;
        height: 40px;
    }

    td ul li {
        display: inline-block;
    }

    .radioClass {
        margin-right: 20px;
    }
</style>
<script type="text/javascript">
    $(function(){
        $("#comPayDateBegin").val($(window.parent.document).find("#comPayDateBegin").val());
        $("#comPayDateEnd").val($(window.parent.document).find("#comPayDateEnd").val());
        $("#partPayOrderAmountMin").val($(window.parent.document).find("#partPayOrderAmountMin").val());
        $("#partPayOrderAmountMax").val($(window.parent.document).find("#partPayOrderAmountMax").val());
        $("#partPayOrderCountMin").val($(window.parent.document).find("#partPayOrderCountMin").val());
        $("#partPayOrderCountMax").val($(window.parent.document).find("#partPayOrderCountMax").val());
    });


    function formSubmit() {
        var comPayDateBegin = $(window.parent.document).find("#comPayDateBegin").val();
        var comPayDateEnd = $(window.parent.document).find("#comPayDateEnd").val();
        var partPayOrderAmountMin = $(window.parent.document).find("#partPayOrderAmountMin").val();
        var partPayOrderAmountMax = $(window.parent.document).find("#partPayOrderAmountMax").val();
        var partPayOrderCountMin = $(window.parent.document).find("#partPayOrderCountMin").val();
        var partPayOrderCountMax = $(window.parent.document).find("#partPayOrderCountMax").val();

        if(!isEmpty(partPayOrderAmountMin) || !isEmpty(partPayOrderAmountMax) || !isEmpty(partPayOrderCountMin) || !isEmpty(partPayOrderCountMax)){
            if(isEmpty(comPayDateBegin) || isEmpty(comPayDateEnd)){
                commUtil.alertSuccess("消费时间不能为空！");
                return;
            }
        }
        if(!isEmpty(comPayDateBegin) && !isEmpty(comPayDateEnd)){
            var days = new Date(comPayDateEnd).getTime() - new Date(comPayDateBegin).getTime();
            var day = parseInt(days / (1000 * 60 * 60 * 24));
            if(day>100){
                commUtil.alertSuccess("消费时间查询跨度不能大于100天！");
                return;
            }
        }
        if(!isEmpty(comPayDateBegin) && isEmpty(comPayDateEnd)){
            commUtil.alertSuccess("消费时间需输入范围！");
            return;
        }
        if(isEmpty(comPayDateBegin) && !isEmpty(comPayDateEnd)){
            commUtil.alertSuccess("消费时间需输入范围！");
            return;
        }
        $(window.parent.document).find("#section").val($('input[name="section"]:checked').val());
        $(window.parent.document).find("#exportExcel").click();
        frameElement.dialog.close();

    }

    //判断字符是否为空
    function isEmpty(obj){
        return (typeof obj === 'undefined' || obj === null || obj === "");
    }
    


</script>
<html>
<body>
<form method="post" id="form1" name="form1">
	<input type="hidden" id="comPayDateBegin" name="comPayDateBegin">
	<input type="hidden" id="comPayDateEnd" name="comPayDateEnd">
	<input type="hidden" id="partPayOrderAmountMin" name="partPayOrderAmountMin">
	<input type="hidden" id="partPayOrderAmountMax" name="partPayOrderAmountMax">
	<input type="hidden" id="partPayOrderCountMin" name="partPayOrderCountMin">
	<input type="hidden" id="partPayOrderCountMax" name="partPayOrderCountMax">
    <table class="gridtable">
        <tr>
            <td colspan="1" class="title">导出数据:<span class="required"></span></td>
            <td colspan="3" align="left" class="l-table-edit-td" >

				<c:forEach var="countList" items="${countArrayList}">
					<c:forEach var="List" items="${countList}">
			     	<label><input name="section" type="radio" value="${List.key}" checked/>${List.value} </label><br>
				</c:forEach>
				</c:forEach>
			</td>
        </tr>
		<tr>
			<td  class="title">操作</td>
			<td colspan="7" align="left" class="l-table-edit-td">
				<div id="btnDiv">
					<input name="btnSubmit" type="button" id="Button1"
						   style="float:left;" class="l-button l-button-submit" value="保存" onclick="formSubmit();"/>
					<input type="button" value="取消" class="l-button l-button-test"
						   style="float:left;" onclick="frameElement.dialog.close();" />

				</div>
			</td>
		</tr>
    </table>
</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
