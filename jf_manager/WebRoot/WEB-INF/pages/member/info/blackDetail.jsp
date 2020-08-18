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
	var picViewer;
	$(function(){
		if (${fn:length(blackInfo.memberBlackPictures) > 4}) {
			$("#submitPic").hide();
		}
		picViewer = new Viewer(document.getElementById('memberBlackPictures'), {});
	})
	
    //图片格式验证
    function ajaxFileUploadImg(_this) {
        var file = document.getElementById(_this.id).files[0];
        var reader = new FileReader();
        reader.onload = function (e) {
            var image = new Image();
            image.onload = function () {
                ajaxFileUpload(_this);
            };
            image.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }

    function ajaxFileUpload(_this) {
        var id = $(_this).attr("id");
        $.ajaxFileUpload({
            url: "${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
            secureuri: false,
            fileElementId: id,
            dataType: 'json',
            success: function (result, status) {
                if (result.RESULT_CODE == 0) {
                    $("#member" + id).append('<li><p><img src="${pageContext.request.contextPath}/file_servelt' + result.FILE_PATH + '" path="' + result.FILE_PATH + '" onclick="viewerPic(this.src)"></p><a href="javascript:void(0);" class="del">删除</a></li>');
                    $(".del").live('click', function () {
                        $(this).closest("li").remove();
                        $("#submitPic").show();
                    });
                    if ($("#memberBlackPictures").find("li") != null && $("#memberBlackPictures").find("li").length > 4) {
                		$("#submitPic").hide();
                	}
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error: function (result, status, e) {
                alert("服务异常");
            }
        });
    }

  	// 提交
    function formSubmit() {
        var memberId = $("#memberId").val();
        var status = $("#status").val();
        var operateType = $("#operateType").val();
        if (!status) {
        	commUtil.alertError("数据错误，请刷新页面重新操作！");
			return;
        }
        var limitFunctionArr = [];
        var blackReason = '';
        // 拉黑操作 获取限制类型以及拉黑理由
		if (status === 'P') {
	        $(":checkbox[name='limitFunction']:checked").each(function(){
	        	limitFunctionArr.push(this.value)
	        });
	        blackReason = $("#blackReason").val();
			if (!blackReason || blackReason === '') {
				commUtil.alertError("加入黑名单理由必填！");
				return;
			}
			if (blackReason.length > 200) {
				commUtil.alertError("理由最多只能输入200个字符！");
				return;
			}
		}
		var blackInnerRemarks = $("#blackInnerRemarks").val();
		if (!blackInnerRemarks && blackInnerRemarks.length > 200) {
			commUtil.alertError("内部备注最多只能输入200个字符！");
			return;
		}

        var blackPictures = [];
        var list = $("#memberBlackPictures").find("li");
        list.each(function (index, item) {
            var path = $("img", item).attr("path");
            blackPictures.push(path);
        });

        $.ajax({
            url: "${pageContext.request.contextPath}/member/saveLimitPermission.shtml",
            type: 'POST',
            dataType: 'json',
            cache: false,
            async: false,
            data: {
                'id': memberId,
                'status': status,
                'limitFunction': limitFunctionArr.toString(),
                'blackReason': blackReason,
                'blackInnerRemarks': blackInnerRemarks,
                'blackPictures': blackPictures.toString(),
                'logOperateType': operateType
            },
            timeout: 30000,
            success: function (data) {
                if ("200" == data.statusCode) {
					commUtil.alertSuccess("提交成功");
					setTimeout(function(){
						parent.location.reload();
						frameElement.dialog.close();
					},2000);
                } else {
                	commUtil.alertError(data.message);
                }
            },
            error: function () {
            	commUtil.alertError('操作超时，请稍后再试！');
            }
        });
    }
  	
  	// 删除图片时显示上传图片按钮
  	function deletePic() {
  		$("#submitPic").show();
  	}

</script>
<html>
<body>
<form method="post" id="form1" name="form1">
    <input type="hidden" name="memberId" id="memberId" value="${memberId}">
    <input type="hidden" name="status" id="status" value="${status}">
    <input type="hidden" name="operateType" id="operateType" value="${operateType}">
    <table class="gridtable">
    	<c:if test="${status != null && status == 'P' }">
	        <tr>
	            <td colspan="1" class="title">限制</td>
	            <td colspan="3" align="left" class="l-table-edit-td">
	                <span class="radioClass">
	                    <input type="checkbox" name="limitFunction" value="1"
	                    <c:if test="${blackInfo.limitEvaluate == 1 }">checked</c:if>
	                    <c:if test="${sessionScope.USER_SESSION.staffID != 1 and canOperate != 1 }">disabled</c:if>/>
	                   		限制评价
	                </span>
	                <span class="radioClass">
	                    <input type="checkbox" name="limitFunction" value="2"
	                    <c:if test="${blackInfo.limitBuy == 1 }">checked</c:if>
	                    <c:if test="${sessionScope.USER_SESSION.staffID != 1 and canOperate != 1 }">disabled</c:if>/>
	                   		 限制购买
	                </span>
	                <span class="radioClass">
	                    <input type="checkbox" name="limitFunction" value="3"
	                    <c:if test="${blackInfo.limitLogin == 1 }">checked</c:if>
	                    <c:if test="${sessionScope.USER_SESSION.staffID != 1 and canOperate != 1 }">disabled</c:if>/>
	                   		限制登录
	                </span>
	            </td>
	        </tr>
	
	        <tr>
	            <td colspan="1" class="title">理由<span class="required">*</span></td>
	            <td colspan="3" align="left" class="l-table-edit-td">
	                <textarea rows="3" style="width:90%;" id="blackReason" name="blackReason" maxlength="200"
	                <c:if test="${sessionScope.USER_SESSION.staffID != 1 and canOperate != 1 }">disabled</c:if>
	                          validate="{required:true}" placeholder="请输入理由，1-200个字符~">${blackInfo.blackReason}</textarea>
	            </td>
	        </tr>
        </c:if>

        <tr>
            <td colspan="1" class="title">内部备注<span class="required"></span></td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <textarea rows="3" style="width:90%;" id="blackInnerRemarks" name="blackInnerRemarks" maxlength="200"
                <c:if test="${sessionScope.USER_SESSION.staffID != 1 and canOperate != 1 }">disabled</c:if>
                          placeholder="请输入备注，0-200个字符~">${blackInfo.blackInnerRemarks}</textarea>
            </td>
        </tr>

        <tr>
            <td colspan="1" class="title">图片</td>
            <td colspan="3" align="left" class="l-table-edit-td">
                <ul id="memberBlackPictures" class="upload_image_list">
            	<c:forEach items="${blackInfo.memberBlackPictures}" var="list">
            		<li>
           				<p>
           				<img src="${pageContext.request.contextPath}/file_servelt${list.pic}" path="${list.pic}">
           				</p>
           				<c:if test="${sessionScope.USER_SESSION.staffID == 1 or canOperate == 1 }"> 
           					<a href="javascript:void(0);" onclick="deletePic()" class="del">删除</a>
           				</c:if>
       				</li>
            	</c:forEach>
            	</ul>
                <c:if test="${sessionScope.USER_SESSION.staffID == 1 or canOperate == 1 }">             
                <div style="float: left;height: 105px;margin: 10px;">
                    <input style="position:absolute; opacity:0;width: 110px;" type="file" id="BlackPictures"
                           name="file" onchange="ajaxFileUploadImg(this);"/>
                   	<input type="button" id="submitPic" style="width: 70px;" value="上传图片"/>
                </div>
                </c:if>
            </td>
        </tr>
		<c:if test="${sessionScope.USER_SESSION.staffID == 1 or canOperate == 1 }"> 
        <tr>
            <td  class="title">操作</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                <div id="btnDiv">
                    <input name="btnSubmit" type="button" id="Button1"
                           style="float:left;" class="l-button l-button-submit" value="提交" onclick="formSubmit();"/>
                    <input type="button" value="取消" class="l-button l-button-test"
                           style="float:left;" onclick="frameElement.dialog.close();" />

                </div>
            </td>
        </tr>
        </c:if>
    </table>
</form>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
