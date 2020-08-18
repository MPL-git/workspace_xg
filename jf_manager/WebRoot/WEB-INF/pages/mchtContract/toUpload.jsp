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
<script src="${pageContext.request.contextPath}/common/js/utils/util.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/utils/move.js" type="text/javascript"></script>
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

*
        {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        
        #pictures
        {
            width: 570px;
            position: relative;
            margin: 10px auto;
        }
        #pictures li
        {
            width: 90px;
            height: 90px;
            float: left;
            list-style: none;
            margin: 10px;
        }
        #pictures li:hover
        {
            border-color: #9a9fa4;
            box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.85);
        }
        #pictures .active
        {
            border: 1px dashed red;
        }

</style>
<script type="text/javascript">
var viewerPictures;
$(function(){
	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	
	$("#confirm").bind('click',function(){
		var pictures = [];
		var lis = $(".upload_image_list").find("li");
		lis.each(function(index, item) {
			var path = $("img", item).attr("path");
			var def = ($(".def", item).length == 1 ? "1" : "0");
			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
			pictures.push(pic);
		});
		if(pictures.length > 0){
			$("#contractPics").val(JSON.stringify(pictures));
		}
		var isManageSelf= $("#isManageSelf").val();
		var isNeedUpload = $("input[name='isNeedUpload']:checked").val();
		console.log(isManageSelf !="1" && isNeedUpload !="0")
		if (isNeedUpload !="0"){  //自营的无需校验
			if(pictures.length == 0){
				commUtil.alertError("请上传图片");
				return;
			}
		}

		var param = $("#picForm").serialize();
		$.ajax({
			url : "${pageContext.request.contextPath}/mchtContract/contractPicUpload.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : param,
			timeout : 30000,
			success : function(data) {
				if ("0000" == data.returnCode) {
					commUtil.alertSuccess("上传成功");
					parent.location.reload();
					frameElement.dialog.close();
				}else{
					$.ligerDialog.error(result.message);
				}
				
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
	});


	showContract();
	
});

function showContract(){

	var isNeedUpload=$("input[name='isNeedUpload']:checked").val();
	if(isNeedUpload == '1'){
		$("#contract").show();
	}
	if (isNeedUpload=='0') {
		$("#contract").hide();
	}
}

function viewerPic(imgPath){
	$("#viewer-pictures").empty();
	viewerPictures.destroy();
		$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
		viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
	viewerPictures.show();
}

function ajaxFileUpload() {
	$.ajaxFileUpload({
		url:"${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
		secureuri: false,
		fileElementId: "contractPic",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
				uploadImageList.addImage("${pageContext.request.contextPath}/file_servelt", result.FILE_PATH);
				var aLi = $("#pictures")[0].getElementsByTagName("li");
			    var disX = 0;
			    var disY = 0;
			    var minZindex = 1;
			    var aPos = [];
			    for (var i = 0; i < aLi.length; i++) {
			        var t = 10;
			        var l = 10+i*110;
			        console.log(i);
			        console.log(t);
			        console.log(l);
			        aLi[i].style.top = t + "px";
			        aLi[i].style.left = l + "px";
			        aPos[i] = { left: l, top: t };
			        aLi[i].index = i;
			    }
			    for (var i = 0; i < aLi.length; i++) {
			        aLi[i].style.position = "absolute";
			        aLi[i].style.margin = 0;
			        setDrag(aLi[i]);
			    }
			    //拖拽
			    function setDrag(obj) {
			        obj.onmouseover = function () {
			            obj.style.cursor = "move";
			        }
			        obj.onmousedown = function (event) {
			            var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
			            var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
			            obj.style.zIndex = minZindex++;
			            //当鼠标按下时计算鼠标与拖拽对象的距离
			            disX = event.clientX + scrollLeft - obj.offsetLeft;
			            disY = event.clientY + scrollTop - obj.offsetTop;
			            document.onmousemove = function (event) {
			                //当鼠标拖动时计算div的位置
			                var l = event.clientX - disX + scrollLeft;
			                var t = event.clientY - disY + scrollTop;
			                obj.style.left = l + "px";
			                obj.style.top = t + "px";
			                /*for(var i=0;i<aLi.length;i++){
			                aLi[i].className = "";
			                if(obj==aLi[i])continue;//如果是自己则跳过自己不加红色虚线
			                if(colTest(obj,aLi[i])){
			                aLi[i].className = "active";
			                }
			                }*/
			                for (var i = 0; i < aLi.length; i++) {
			                    aLi[i].className = "";
			                }
			                var oNear = findMin(obj);
			                if (oNear) {
			                    oNear.className = "active";
			                }
			            }
			            document.onmouseup = function () {
			                document.onmousemove = null; //当鼠标弹起时移出移动事件
			                document.onmouseup = null; //移出up事件，清空内存
			                //检测是否普碰上，在交换位置
			                var oNear = findMin(obj);
			                if (oNear) {
			                    oNear.className = "";
			                    oNear.style.zIndex = minZindex++;
			                    obj.style.zIndex = minZindex++;
			                    startMove(oNear, aPos[obj.index]);
			                    startMove(obj, aPos[oNear.index]);
			                    //交换index
			                    oNear.index += obj.index;
			                    obj.index = oNear.index - obj.index;
			                    oNear.index = oNear.index - obj.index;
			                } else {

			                    startMove(obj, aPos[obj.index]);
			                }
			            };
			            clearInterval(obj.timer);
			            return false; //低版本出现禁止符号
			        }
			    }
			    //碰撞检测
			    function colTest(obj1, obj2) {
			        var t1 = obj1.offsetTop;
			        var r1 = obj1.offsetWidth + obj1.offsetLeft;
			        var b1 = obj1.offsetHeight + obj1.offsetTop;
			        var l1 = obj1.offsetLeft;

			        var t2 = obj2.offsetTop;
			        var r2 = obj2.offsetWidth + obj2.offsetLeft;
			        var b2 = obj2.offsetHeight + obj2.offsetTop;
			        var l2 = obj2.offsetLeft;

			        if (t1 > b2 || r1 < l2 || b1 < t2 || l1 > r2) {
			            return false;
			        } else {
			            return true;
			        }
			    }
			    //勾股定理求距离
			    function getDis(obj1, obj2) {
			        var a = obj1.offsetLeft - obj2.offsetLeft;
			        var b = obj1.offsetTop - obj2.offsetTop;
			        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
			    }
			    //找到距离最近的
			    function findMin(obj) {
			        var minDis = 999999999;
			        var minIndex = -1;
			        for (var i = 0; i < aLi.length; i++) {
			            if (obj == aLi[i]) continue;
			            if (colTest(obj, aLi[i])) {
			                var dis = getDis(obj, aLi[i]);
			                if (dis < minDis) {
			                    minDis = dis;
			                    minIndex = i;
			                }
			            }
			        }
			        if (minIndex == -1) {
			            return null;
			        } else {
			            return aLi[minIndex];
			        }
			    }
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});
	
}



window.onload = function () {
    var oUl = document.getElementById("pictures");
    
}
</script>

<html>
<body>
<form id="picForm" method="post" action="${pageContext.request.contextPath}/mchtContract/contractPicUpload.shtml">
	<input type="hidden" name="id" id="mchtContractId" value="${id}">
	<input type="hidden" name="isManageSelf" id="isManageSelf" value="${isManageSelf}">
	<table class="gridtable">
		<c:if test="${isManageSelf  eq 1}">
			<tr>
				<td class="title">是否上传</td>
				<td colspan="3">
					<input  name="isNeedUpload" type="radio" value="1" <c:if test="${isNeedUpload eq '1'}">checked="checked"</c:if> onclick="showContract()" >需上传
					<input  name="isNeedUpload" type="radio" value="0" <c:if test="${isNeedUpload eq '0'}">checked="checked"</c:if> onclick="showContract()" >无需上传
				</td>
			</tr>
		</c:if>

		<tr id="contract"  style="display:none;">
			<td class="title" >合同</td>
			<td colspan="3">
				<t:imageList name="pictures" list="${contractPics}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
				<div style="float: left;height: 105px;margin: 10px;">
	    			<input style="position:absolute; opacity:0;" type="file" id="contractPic" name="file" onchange="ajaxFileUpload();" />
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>
	    		</div>
	    		<input id="contractPics" name="contractPics" type="hidden" value="${contractPics}">
			</td>
		</tr>



		<tr>
			<td class="title" style="text-align: center;">操作</td>
			<td colspan="3" align="left" class="l-table-edit-td">
				<div id="btnDiv">
					<input name="btnSubmit" type="button" id="confirm" style="float:left;" class="l-button l-button-submit" value="提交" />
					<input type="button" value="取消" class="l-button l-button-test" style="float:left;margin-left: 20px;" onclick="frameElement.dialog.close();" />
				</div>
			</td>
	    </tr>
	</table>	
</form>		
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
