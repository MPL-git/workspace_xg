<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
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
$(function(){
	$("#productType1Id").bind('change',function(){
		var productType1Id = $(this).val();
		if(productType1Id){
			$("#productType2Id").empty();
			$("#productTypeId").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/taobaoke/getProductTypesByParentId.shtml?parentId="+productType1Id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var productTypeList = data.productTypeList;
						var html=[];
						html.push('<option value="">请选择</option>');
						for(var i=0;i<productTypeList.length;i++){
							html.push('<option value="'+productTypeList[i].id+'">'+productTypeList[i].name+'</option>');
						}
						$("#productType2Id").append(html.join(""));
						$("#productTypeId").append('<option value="">请选择</option>');
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			$("#productType2Id").empty();
			$("#productType2Id").append('<option value="">请选择</option>');
			$("#productTypeId").empty();
			$("#productTypeId").append('<option value="">请选择</option>');
		}
	});
	
	$("#productType2Id").bind('change',function(){
		var productType2Id = $(this).val();
		if(productType2Id){
			$("#productTypeId").empty();
			$.ajax({
				url : "${pageContext.request.contextPath}/taobaoke/getProductTypesByParentId.shtml?parentId="+productType2Id,
				secureuri : false,
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if ("0000" == data.returnCode) {
						var productTypeList = data.productTypeList;
						var html=[];
						html.push('<option value="">请选择</option>');
						for(var i=0;i<productTypeList.length;i++){
							html.push('<option value="'+productTypeList[i].id+'">'+productTypeList[i].name+'</option>');
						}
						$("#productTypeId").append(html.join(""));
					}else{
						$.ligerDialog.error(data.returnMsg);
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}else{
			$("#productTypeId").empty();
			$("#productTypeId").append('<option value="">请选择</option>');
		}
	});
	
});

function save(){
	var favoritesId = $("#favoritesId").val();
	var productTypeId = $("#productTypeId").val();
	var wetaoChannel = $("#wetaoChannel").val();
	alert("抓取存到本地库表中需要一定时间，请耐心等待。");
	$.ajax({
		url : "${pageContext.request.contextPath}/taobaoke/batchCatch.shtml",
		type : 'POST',
		dataType : 'json',
		cache : false,
		async : false,
		data : {favoritesId:favoritesId,productTypeId:productTypeId,wetaoChannel:wetaoChannel},
		timeout : 30000,
		success : function(data) {
			if ("0000" == data.returnCode) {
				commUtil.alertSuccess("操作成功");
				parent.location.reload();
				frameElement.dialog.close();
			}else{
				$.ligerDialog.error(data.returnMsg);
			}
		},
		error : function() {
			$.ligerDialog.error('操作超时，请稍后再试！');
		}
	});
}
</script>
<html>
<body>
<form id="form" method="post" action="${pageContext.request.contextPath}/taobaoke/batchCatch.shtml">
	<input type="hidden" id="favoritesId" name="favoritesId" value="${favoritesId}">
	<table class="gridtable">
		<tr>
			<td class="title">分类</td>
			<td align="left" class="l-table-edit-td">
				<select id="productType1Id" name="productType1Id" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach items="${productTypes}" var="productType">
						<option value="${productType.id}">${productType.name}</option>
					</c:forEach>
				</select>
				<select id="productType2Id" name="productType2Id" style="width: 150px;">
					<option value="">请选择</option>
				</select>
				<select id="productTypeId" name="productTypeId" style="width: 150px;">
					<option value="">请选择</option>
				</select>
			</td>
		</tr>
		
			
			<tr>
				<td class="title">所属频道</td>
				<td align="left" class="l-table-edit-td">
				<select id="wetaoChannel" name="wetaoChannel" style="width:150px;" >
					<option value="">请选择</option>
					<c:forEach var="wetaoChannel" items="${wetaoChannels}">
						<option value="${wetaoChannel.id}">${wetaoChannel.channelName}</option>
					</c:forEach> 
				</select>
				</td>
			</tr>
		
		
		<tr>
			<td class="title">操作</td>
			<td>
				<input type="button" class="l-button l-button-submit" value="提交" onclick="save();"/>&nbsp;&nbsp;
				<input type="button" value="取消" class="l-button l-button-test" onclick="frameElement.dialog.close();" />
			</td>
		</tr>
	</table>	
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
