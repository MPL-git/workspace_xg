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
body {font-size: 13px;padding: 10px;}
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
.color02{color: #797979;}
</style>
<script type="text/javascript">
var submitting;
$(function(){
	$("#confirm").bind('click',function(){
		if(submitting){
			return;
		}
		var id = $("#mchtChgId").val();
		if($("#brandType").val() == "" || $("#brandTypeDesc").val() == ""){
			$.ligerDialog.warn('主要经营品牌类型不可为空');
			return;
		}
		var brandType = $("#brandType").val();
		var brandTypeDesc = $("#brandTypeDesc").val();
		submitting = true;
		$.ajax({
			url : "${pageContext.request.contextPath}/mcht/mchtInfoChg/saveChgBrandType.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"id":id,"brandType":brandType,"brandTypeDesc":brandTypeDesc},
			timeout : 30000,
			success : function(data) {
				if(data.returnCode == '0000'){
					submitting = false;
					commUtil.alertSuccess("提交成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},1000);
				}else{
					submitting = false;
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
<body>
	<div class="title-top">
	<input type="hidden" id="mchtChgId" value="${mchtInfoChg.id}">
		<table class="gridtable">
          	<tr>
				<td colspan="1" class="title">主要经营品牌类型<span class="required">*</span></td>
				<td colspan="1" class="l-table-edit-td">
                    <select style="width:210px;" name="brandType" id="brandType" validate="{required:true}">
                    <option value="">请选择</option>
                    <option value="0"<c:if test="${mchtInfoChg.brandType eq 0}">selected</c:if>>品牌官方</option>
                    <option value="1"<c:if test="${mchtInfoChg.brandType eq 1}">selected</c:if>>品牌总代</option>
                    <option value="2"<c:if test="${mchtInfoChg.brandType eq 2}">selected</c:if>>品牌代理商</option>
                    </select>
                    <br><br>
                    <textarea class="form-control" rows="5" style="width: 100%;resize: vertical;" id="brandTypeDesc" name="brandTypeDesc" maxlength="256" validate="{required:true}">${mchtInfoChg.brandTypeDesc}</textarea>
				</td>
			</tr>
	        <tr>
	          	<td class="title">操作</td>
	          	<td>
					<input id="confirm" type="button" style="float:left;width: 70px;" class="l-button l-button-test" value="提交"/>
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 10px;" onclick="frameElement.dialog.close();" />
				</td>
	        </tr>
        </table>
	</div>
	
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
