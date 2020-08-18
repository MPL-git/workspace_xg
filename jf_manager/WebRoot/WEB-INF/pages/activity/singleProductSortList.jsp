<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link	href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
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
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
	  <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBoxList.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerListBox.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/css/form.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script src="../../lib/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript">
</script>

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
	rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
	type="text/javascript"></script>	
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<style type="text/css">
body {
	font-size: 12px;
	padding: 10px;
}

.radioClass{
	margin-right: 20px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-test {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}

.auditFix{position:fixed; bottom:0; left:0; width:100%; _position:absolute;}
</style>
<style type="text/css">
.middle input {
	display: block;
	width: 30px;
	margin: 2px;
}
table.l-checkboxlist-table td{
	border-style: none;
}
table.l-listbox-table td{
	border-style: none;
}

.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}


.table-title{
font-size: 17px;font-weight: 600;
}
.center-align{
	text-align: center;
}
</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("#saveSort").bind('click',function(){
		var sortIds="";
		var error = false;
		$("input[name='sort']").each(function(i){
			var sort = $(this).val();
			if($.trim(sort)!=""){
				if(!testNumber(sort)){
					var num = i+1;
					commUtil.alertError("排序值只能是正整数,请修改第"+num+"活动的排序值");
					error = true;
					return false;
				}
			}
			var activityProductId = $(this).attr("activityProductId");
			var sortId = activityProductId+","+sort;
			sortIds+=sortId+"|";
		});
		sortIds = sortIds.substring(0, sortIds.length-1);
		if(error){
			return false;
		}
		$.ajax({ //ajax提交
			type:'POST',
			url:"${pageContext.request.contextPath}/activityArea/saveActivityProductSort.shtml",
			data:{
				sortIds:sortIds
			},
			dataType:'json',
			cache: false,
			success: function(json){
			   if(json==null || json.statusCode!=200){
			     	commUtil.alertError("排序失败，请稍后重试");
			  	}else{
			  		commUtil.alertSuccess("排序成功");
			  		location.reload();
			  	}
			},
			error: function(e){
			 commUtil.alertError("系统异常请稍后再试");
			}
		});
	});
});

function viewerPic(imgPath){
	
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	$("#viewer-pictures").show();
	viewerPictures.show();
	
}

function ajaxFileUpload() {
	var length = $("#pictures").find("img").length;
	if(length>0){
		commUtil.alertError("只允许上传一张图片，请先删除原图片再重新上传");
		return false;
	}
	$.ajaxFileUpload({
		url: contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "topPicFile",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				uploadImageList.addImage(contextPath + "/file_servelt", result.FILE_PATH);
				var activityAreaId = $("#activityAreaId").val();
				var topPic = result.FILE_PATH;
				$.ajax({ //ajax提交
					type:'POST',
					url:"${pageContext.request.contextPath}/activityArea/saveTopPic.shtml",
					data:{
						activityAreaId:activityAreaId,
						topPic:topPic
					},
					dataType:'json',
					cache: false,
					success: function(json){
					   if(json==null || json.statusCode!=200){
					     commUtil.alertError("上传失败，请稍后重试");
					  }
					},
					error: function(e){
					 commUtil.alertError("系统异常请稍后再试");
					}
				});
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});	
}

/*预览会场 */
function toView(activityAreaId,activityAreaType) {
	var mUrl = $("#mUrl").val();
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()*0.8,
		title: "预览会场",
		name: "INSERT_WINDOW",
		url: mUrl+"/xgbuy/views/#activity/templet/single_def.html?activityAreaId="+activityAreaId+"&pageSize=10&currentPage=0&isPreview=1&activityAreaType="+activityAreaType,
		showMax: true,
		showToggle: false,
		showMin: false,
		isResize: true,
		slide: false,
		data: null
	});
}

function submit_fun(){
	var activityProductId="${activityProductId}";
	var type="${type}";
	var remarks=$("#remarks").val();
	var status = $("input[name='status']:checked").val();
	if (status==3){
		if($("#remarks").val().length==0){
			commUtil.alertError("请输入驳回内容");
			return;
		}
	}else if (status!=2){
		commUtil.alertError("请选择状态");
		return;
	}
	
	$.ajax({ //ajax提交
		type:'POST',
		url:"${pageContext.request.contextPath}/activity/activityProductAudit.shtml",
		data:{
			activityProductId:activityProductId,
			status:status,
			remarks:remarks,
			type:type
		},
		dataType:'json',
		cache: false,
		success: function(json){
		   if(json==null || json.statusCode!=200){
		     commUtil.alertError(json.message);
		  }else{
             $.ligerDialog.success("操作成功",function(yes) {
            	 	parent.$("#searchbtn").click();
 					frameElement.dialog.close();
				});
		  }
		},
		error: function(e){
		 commUtil.alertError("系统异常请稍后再试");
		}
	});
}
</script>
<html>
<body>
	<form method="post" id="form1" name="form1">
		<input type="hidden" id="activityAreaId" value="${activityAreaId}">
		<input type="hidden" id="mUrl" value="${mUrl}">
		<input type="hidden" id="templetSuffix" value="${templetSuffix}">
		<table class="gridtable">
		<c:forEach var="activityProductCustom" items="${activityProductCustoms}" varStatus="status">
		<c:if test="${(status.index+1)%2==1}">
			<tr>				
		</c:if>
				<td class="l-grid-row-cell ">
					<div class="l-grid-row-cell-inner">
						<span style="display:block;text-align:left;margin-top:8px;">
							<img style="margin:10px;" src="${pageContext.request.contextPath}/file_servelt${activityProductCustom.productPic}" width="100" height="100" onclick="viewerPic(this.src)"><br>
							${activityProductCustom.productName}<br>
							价格：${activityProductCustom.salePriceMin}-${activityProductCustom.salePriceMax}元，库存总数：${activityProductCustom.productStock}件<br>
							30天本款销量：${activityProductCustom.dfgNum}件，销售额：${activityProductCustom.dfgPrice}元<br>
							排序值：<input type="text" name="sort" activityProductId="${activityProductCustom.id}" value="${activityProductCustom.seqNo}"><br>
						</span>
					</div>
				</td>
		<c:if test="${(status.index+1)%2==0}">
			</tr>
		</c:if>
		</c:forEach>
		<tr>
			<td colspan="7" align="left" class="l-table-edit-td">
				<div id="btnDiv">
					<input name="btnSubmit" type="button" id="saveSort" style="float:left;" class="l-button l-button-submit" value="提交排序"/>
					<input onclick="toView(${activityAreaId},${activityAreaType});" type="button" value="预览会场" class="l-button l-button-test" style="float:left;" />
				</div>
			</td>
		</tr>
		</table>
		
		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
	</form>
</body>
</html>
