<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

<style type="text/css">
  /*  body {font-size: 13px;padding: 10px;}
    td input,td select{border:1px solid #AECAF0;}
    .table-title{font-size: 14px;color: #333333;font-weight: 600;}
    .title-top{padding-top:20px;}
    .marR10{margin-right:10px;}
    .marT10{margin-top:10px;}
    .marB05{margin-bottom:5px;}
    .table-title-link{color: #1B17EE;}
    .baseInfo tr td span,.baseInfo tr td img{margin-right:20px;}
    tr.title-first td{background-color:#DCF9FF;}
    tr.title-second td{background-color:#FFFB94;font-size:15px;}
    tr.title-second td span{margin-right:0px;}
    tr.title-third td{font-size:15px;}
    .baseInfo tr td span.amtSpan{ margin-right:0;color: #FF0000;}
    tr.title-fourth td{background-color:#DCF9FF;text-align:center;}
    .LogInfo tr td img{margin:10px 20px 0 0;}
    .color01{color: #FF0000;}
    .color02{color: #797979;}*/
</style>

<script>
    $(function(){
        $("#confirm").bind('click',function(){
            var mobile = $("#mobile").val();
            var myreg = /^[1][3,4,5,7,8,9][0-9]{9}$/;
            if(!myreg.test(mobile)) {
                commUtil.alertError("您填写的手机号不正确");
                    return false;
            }
            $.ajax({
                url : "${pageContext.request.contextPath}/member/sms/saveWhiteMobile.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {"mobile":mobile},
                timeout : 30000,
                success : function(data) {
                    if(data.code == '200'){
                        parent.location.reload();
                        frameElement.dialog.close();

                    }else{
                        $.ligerDialog.error(data.returnMessage);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        });
    });
</script>
<head>
    <title>添加白名单</title>
</head>
<body>
<form method="post" id="form1" name="form1">
    <input id="activityGroupId" name="activityGroupId" type="hidden" value="${activityGroup.id}"/>
    <table class="gridtable">
        <tr>
            <td class="title">电话号码 <span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input id="mobile" name="mobile" type="text" style="float:left;width: 200px;" />
        </tr>

        <tr>
            <td class="title">操作</td>
            <td align="left" class="l-table-edit-td">
                <div id="btnDiv">
                    <div><input name="btnSubmit" type="submit" id="confirm" style="float:left;" class="l-button"
                                value="提交"/></div>
                    <div><input name="btnCancle" type="button" id="Button2" style="float:left; margin-left: 40px"
                                class="l-button" value="取消" onclick="frameElement.dialog.close();"/></div>
                </div>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
