<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
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
var viewerPictures;
$(function(){
		$("#save").bind('click',function(){
			var year = $("#year").val();
			var month = $("#month").val();
			var date = new Date(year+"-"+month);
			var now = new Date();
			var nowMonthStr = now.format("yyyy-MM");
			var nowMonth = new Date(nowMonthStr);
			if(date<nowMonth){
				commUtil.alertError("不能新建小于当前月份的日期");
				return false;
			}
			$.ajax({
				url : "${pageContext.request.contextPath}/marketing/signInManage/save.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {"year":year,month:month},
				timeout : 30000,
				success : function(data) {
					if ("0000" == data.returnCode) {
						commUtil.alertSuccess("保存成功");
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
<form method="post" id="form" name="form" action="${pageContext.request.contextPath}/marketing/signInManage/save.shtml">
			<table class="gridtable">
           	<tr>
               <td class="title">年份</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="year">
               		<option value="2018" <c:if test="${year == 2018}">selected="selected"</c:if>>2018</option>
               		<option value="2019" <c:if test="${year == 2019}">selected="selected"</c:if>>2019</option>
               		<option value="2020" <c:if test="${year == 2020}">selected="selected"</c:if>>2020</option>
               		<option value="2021" <c:if test="${year == 2021}">selected="selected"</c:if>>2021</option>
               		<option value="2022" <c:if test="${year == 2022}">selected="selected"</c:if>>2022</option>
               		<option value="2023" <c:if test="${year == 2023}">selected="selected"</c:if>>2023</option>
               	</select>
               </td>
			</tr>
	        <tr>
               <td class="title">月份</td>
               <td colspan="2" align="left" class="l-table-edit-td">
               	<select id="month">
               		<option value="1" <c:if test="${month == 1}">selected="selected"</c:if>>1</option>
               		<option value="2" <c:if test="${month == 2}">selected="selected"</c:if>>2</option>
               		<option value="3" <c:if test="${month == 3}">selected="selected"</c:if>>3</option>
               		<option value="4" <c:if test="${month == 4}">selected="selected"</c:if>>4</option>
               		<option value="5" <c:if test="${month == 5}">selected="selected"</c:if>>5</option>
               		<option value="6" <c:if test="${month == 6}">selected="selected"</c:if>>6</option>
               		<option value="7" <c:if test="${month == 7}">selected="selected"</c:if>>7</option>
               		<option value="8" <c:if test="${month == 8}">selected="selected"</c:if>>8</option>
               		<option value="9" <c:if test="${month == 9}">selected="selected"</c:if>>9</option>
               		<option value="10" <c:if test="${month == 10}">selected="selected"</c:if>>10</option>
               		<option value="11" <c:if test="${month == 11}">selected="selected"</c:if>>11</option>
               		<option value="12" <c:if test="${month == 12}">selected="selected"</c:if>>12</option>
               	</select>
               </td>
	        </tr>
	        <tr>
               <td class="title">操作</td>
               <td colspan="2" align="left" class="l-table-edit-td">
					<div id="btnDiv">
						<input id="save" type="button" style="float:left;" class="l-button l-button-submit" value="保存"/>
					</div>
				</td>
	        </tr>
	        </table>
</form>	        
</body>
</html>
