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
function Base64() {
	 
    // private property
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
 
    // public method for encoding
    this.encode = function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
            _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
            _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }
 
    // public method for decoding
    this.decode = function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    }
 
    // private method for UTF-8 encoding
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }
 
        }
        return utftext;
    }
 
    // private method for UTF-8 decoding
    _utf8_decode = function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while ( i < utftext.length ) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
}


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
			}else{
				sort = -1;
			}
			var activityId = $(this).attr("activityId");
			var sortId = activityId+","+sort;
			sortIds+=sortId+"|";
		});
		sortIds = sortIds.substring(0, sortIds.length-1);
		if(error){
			return false;
		}
		$.ajax({ //ajax提交
			type:'POST',
			url:"${pageContext.request.contextPath}/activityArea/saveSort.shtml",
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
	
	$("#toEdit").bind('click',function(){
		var activityAreaId = $("#activityAreaId").val()
		$.ligerDialog.open({
			height: $(window).height()*0.3,
			width: $(window).width()*0.8,
			title: "修改会场类型",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/activityArea/toEdit.shtml?id=" + activityAreaId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
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
function toView(activityAreaId) {
	var mUrl = $("#mUrl").val();
	var templetSuffix = $("#templetSuffix").val();
	var tmp3 = templetSuffix.split("?redirect_url=")[0];
	var tmp2 = "?redirect_url=";
	var tmp4 = templetSuffix.split("?redirect_url=")[1];
	var base64 = new Base64();
	var tmp = base64.encode(tmp4+"?activityAreaId="+activityAreaId+"&currentPage=0&pageSize=10&memberId=&isPreview=1");
	$.ligerDialog.open({
		height: $(window).height(),
		width: $(window).width()*0.8,
		title: "预览会场",
		name: "INSERT_WINDOW",
		url: mUrl+tmp3+tmp2+tmp,
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
		<input type="hidden" id="templetSuffix" value="${templetSuffix}">
		<input type="hidden" id="mUrl" value="${mUrl}">
		<div><span>选择类型：<c:if test="${isDefault == 1}">默认模板</c:if><c:if test="${isDefault == 0}">自定义页面</c:if></span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" id="toEdit">我要修改</a></div>
		<br>
		<div>
	    	<t:imageList name="pictures" list="${topPic}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
	    	<div style="float: left;height: 105px;margin: 10px;">
	    	<input style="position:absolute; opacity:0;" type="file" id="topPicFile" name="file" onchange="ajaxFileUpload();" />
				<div class="l-icon l-icon-up" style="display:inline-block;"></div>
				<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    	</div>
	    	<input id="mchtSettledApplyPics" name="mchtSettledApplyPics" type="hidden" value="${topPic}">
		</div>
	
		<table class="gridtable">
		<c:forEach var="activityCustom" items="${activityCustoms}">
			<tr>				
				<td class="l-grid-row-cell ">
					<div class="l-grid-row-cell-inner">
						<img style="float:left;margin:10px;" src="${pageContext.request.contextPath}/file_servelt${activityCustom.entryPic}" width="300" height="150" onclick="viewerPic(this.src)">
						<span style="display:block;text-align:left;margin-top:8px;">
							排序值：<input type="text" name="sort" style="width:80px;" activityId="${activityCustom.id}" value="${activityCustom.seqNo}"><br>
							活动名称：${activityCustom.name}<br>
							本场商品款数：${activityCustom.productNum}款，库存总数：${activityCustom.activityStock}件<br>
							商家简称：${activityCustom.shortName}<br>
							店铺名称：${activityCustom.shopName}<br>
							<%-- 30天店销量：${activityCustom.thirtyQuantity}件，销售额：${activityCustom.thirtySalePrice}元--%>
						</span>
					</div>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" align="left" class="l-table-edit-td">
				<div id="btnDiv">
					<input name="btnSubmit" type="button" id="saveSort" style="float:left;" class="l-button l-button-submit" value="提交排序"/>
					<input onclick="toView(${activityAreaId});" type="button" value="预览会场" class="l-button l-button-test" style="float:left;" />
				</div>
			</td>
		</tr>
		</table>
		
		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	</ul>
	</form>
</body>
</html>
