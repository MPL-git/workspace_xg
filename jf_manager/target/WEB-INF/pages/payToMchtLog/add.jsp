<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
</style>

<script type="text/javascript">
var mchtIdText;
$(function ()  {
	
	function getGridOptions(checkbox){
	     var options = {
	         columns: [
	         { display: '商家序号', name: 'mchtCode', minWidth: 120, width: 100 },
	         { display: '商家简称', name: 'shortName', minWidth: 120, width: 100 },
	         { display: '公司名称', name: 'companyName', minWidth: 120, width: 100 },
	         { display: '店铺名称', name: 'shopName', minWidth: 120, width: 100 }
	         ], 
	         switchPageSizeApplyComboBox: false,
	         url: '${pageContext.request.contextPath}/payToMchtLog/mchtInfoData.shtml',
	         pageSize: 10, 
	         checkbox: checkbox
	     };
	     return options;
	 }
	
	mchtIdText = $("#mchtInfo").ligerComboBox({
     	width: 162,
         slide: false,
         selectBoxWidth: 512,
         selectBoxHeight: 412,
         valueField: 'id',
         textField: 'shortName',
         valueFieldID:'id',
         grid: getGridOptions(false),
         value: '${id}',
         condition:{ fields: [{ name: 'shortName', label: '商家简称',width:180,type:'text' },{ name: 'mchtCode', label: '商家序号',width:180,type:'text'}] }
     });
	
	$("input[name='btnSubmit']").bind('click',function(){
		var mchtId = mchtIdText.getValue();
		var type = $("#type").val();
		var payAmount = $.trim($("#payAmount").val());
		var payCode = $.trim($("#payCode").val());
		var payDate = $.trim($("#payDate").val());
		if(mchtId == ""){
			commUtil.alertError("请选择供应商");
			return false;
		}
		if(payAmount == ""){
			commUtil.alertError("付款金额不能为空");
			return false;
		}
		if(payCode == ""){
			commUtil.alertError("付款编号不能为空");
			return false;
		}
		if(payDate == ""){
			commUtil.alertError("请选择付款日期");
			return false;
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/payToMchtLog/save.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {"mchtId":mchtId,"type":type,"payAmount":payAmount,"payCode":payCode,"payDate":payDate},
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("提交成功");
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
	<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/payToMchtLog/save.shtml">
		<table class="gridtable">
			<tr>
  				<td class="title">供应商</td>
  				<td align="left" class="l-table-edit-td">
  				<div>
  					<div  style="float: left; ">
  						<input id="mchtInfo" name="mchtInfo" type="text" />
  					</div>
				</div>
				</td> 
  			</tr>
  			
			<tr>
				<td colspan="1" class="title">付款类型<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<select style="width: 100px;" id="type" name="type">
 					<c:forEach var="type" items="${typeList}">
 						<c:if test="${type.statusValue ne '1'}">
							<option value="${type.statusValue}">${type.statusDesc}</option>
 						</c:if>
					</c:forEach>
					</select>
	    		</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">付款金额<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="payAmount" name="payAmount" validate="{required:true,number:true}" type="text" style="width:100px;" value=""/>&nbsp;元&nbsp;
				</td>
			</tr>

			<tr>
				<td colspan="1" class="title">付款编号<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="payCode" name="payCode" validate="{required:true}" type="text" style="width:400px;" value=""/>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">付款日期<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input id="payDate" name="payDate" validate="{required:true}" type="text" style="width:400px;"/>
					<script type="text/javascript">
						$(function() {
							$("#payDate").ligerDateEditor({
								showTime : false,
								labelWidth : 150,
								width:150,
								labelAlign : 'left'
							});
						});
					</script>
				</td>
			</tr>
			
			<tr>
				<td colspan="1" class="title">操作</td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input name="btnSubmit" type="button" style="float:left;" class="l-button l-button-submit" value="提交"/>
						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
					</div>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
