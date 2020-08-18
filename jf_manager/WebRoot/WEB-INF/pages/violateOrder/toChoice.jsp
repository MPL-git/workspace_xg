<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
<style type="text/css">
    body {font-size: 12px;padding: 10px;}
    td input,td select{border:1px solid #AECAF0;}
    .table-title{font-size: 14px;color: #333333;font-weight: 600;}
    .title-top{padding-top:10px;padding-bottom:10px;}
    .marR10{margin-right:10px;}
    .marT10{margin-top:10px;}
    .marB05{margin-bottom:5px;}
</style>
<script type="text/javascript">

    $(function(){

        $("#confirm").bind('click',function(){
            var violateComplainOrderId = $("#violateComplainOrderId").val();
            var suspendedReason = $("#suspendedReason").val();
            if(!suspendedReason){
                commUtil.alertError("请填写挂起原因");
                return false;
            }

            var param = $("#form").serialize();
            $.ajax({
                url : "${pageContext.request.contextPath}/violateOrder/checkChoice.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : param,
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        commUtil.alertSuccess("操作成功");
                        setTimeout(function(){
                            parent.location.reload();
                            frameElement.dialog.close();
                        },1000);
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        });
    });


</script>
<html>
<body>
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/violateOrder/checkChoice.shtml">
    <input type="hidden" id="violateComplainOrderId" name="violateComplainOrderId" value="${violateComplainOrderId}">
    <table class="gridtable">

        <tr id="rejectReasonTr">
            <td class="title">挂起原因<span style="color: red;">*</span></td>
            <td colspan="2" align="left" class="l-table-edit-td">
                <textarea id="suspendedReason" name="suspendedReason" rows="5" class="textarea" cols="50"></textarea>
            </td>
        </tr>

        <tr>
            <td class="title">操作</td>
            <td>
                <input id="confirm" type="button" style="float:left;width: 70px;" class="l-button l-button-test" value="确认挂起"/>
                <input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 10px;" onclick="frameElement.dialog.close();" />
            </td>
        </tr>
    </table>
</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
