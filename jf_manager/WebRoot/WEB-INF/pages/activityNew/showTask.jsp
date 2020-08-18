<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
      rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerTip.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/jquery.metadata.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/liger/lib/jquery-validation/messages_cn.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js"
        type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/form.css"
      rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/css/viewer.min.css"
      rel="stylesheet" type="text/css"/>

<script src="${pageContext.request.contextPath}/js/viewer.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<style type="text/css">
    body {
        font-size: 12px;
        padding: 10px;
    }

    td input, td select {
        border: 1px solid #AECAF0;
    }

    .radioClass {
        margin-right: 20px;
    }

    .l-table-edit {

    }

    .l-table-edit-td {
        padding: 4px;
    }

    .l-button-submit, .l-button-test {
        width: 80px;
        float: left;
        margin-left: 10px;
        padding-bottom: 2px;
    }

    .l-verify-tip {
        left: 230px;
        top: 120px;
    }

    .table-title-link {
        color: #1B17EE;
        font-size: 15px;
        text-decoration: none;
    }

    .table-title {
        font-size: 17px;
        font-weight: 600;
    }
</style>
<style type="text/css">
    .middle input {
        display: block;
        width: 30px;
        margin: 2px;
    }

    table.l-checkboxlist-table td {
        border-style: none;
    }

    table.l-listbox-table td {
        border-style: none;
    }

    .td-pictures li {
        display: inline-block;
    }

    td img {
        width: 60px;
        height: 40px;
    }
    /*显示大图*/
	.show-img {
		position: fixed;
		top: 0;
		left: 0;
		z-index: 11;
		width: 100%;
		height: 100%;
		background: rgba(0, 0, 0, .8);
	}
	.show-img img {
		width: auto;
		margin: 50vh auto 0;
		-webkit-transform: translateY(-50%);
		transform: translateY(-50%);
		display:block;
	}
	.blue {
		color: #2476ff;
	}
</style>
<script type="text/javascript">
    $(function(){
    	$("#download").bind('click',function(){
   		 var attachmentPath = $(this).attr("attachmentpath");
   		 var attachmentName = $(this).attr("attachmentname");
   		 $.ajax({
   				type: 'post',
   				url: '${pageContext.request.contextPath}/designTaskOrder/checkFileExists.shtml',
   				data: {"attachmentPath":attachmentPath},
   				dataType: 'json',
   				success: function(data){
   					if(data == null || data.code != 200){
   				    	commUtil.alertError(data.msg);
   				  	}else{
   				  		location.href="${pageContext.request.contextPath}/designTaskOrder/downLoadAttachment.shtml?fileName="+attachmentName+"&filePath="+attachmentPath;
   				  	}
   				},
   				error: function(e){
   				 	commUtil.alertError("系统异常请稍后再试！");
   				}
   			});
   		 
   	 });
    	
    	 /* var designTaskOrderPicDetailCustoms = ${designTaskOrderPicDetailList};
		 var html = [];
		 for(var i=0;i<designTaskOrderPicDetailCustoms.length;i++){
			html.push("<tr height='220px;'>");
			html.push("<td class='title' width='20%;'>图片"+[i+1]+"</td>");
			html.push("<td align='left' class='l-table-edit-td'>");
 			html.push("<div><img id='logoPic' style='width: 300px;height: 150px;' alt='' src='${pageContext.request.contextPath}/file_servelt"+designTaskOrderPicDetailCustoms[i].pic+"'></div>");
 			html.push("<div style='float: left;height: 30px;'>");
			html.push("<input style='position:absolute; opacity:0;' type='file' id='logoPicFile' name='file' onchange='ajaxPicFileUploadImg('logoPicFile')'/>");
			html.push("<a href='javascript:void(0);' style='width:30%;'>上传图片</a>");
			html.push("<span style='color: gray;'>尺寸:"+designTaskOrderPicDetailCustoms[i].width+"*"+designTaskOrderPicDetailCustoms[i].height+",图片说明："+designTaskOrderPicDetailCustoms[i].picDesc+"</span>");
			html.push("</div>");
			html.push("<input id='pic' name='pic' type='hidden' value="+designTaskOrderPicDetailCustoms[i].pic+">");
			html.push("</td>");
			html.push("</tr>");
		} 
		 $("#gridtable-pic").html(html.join(""));
         */
        
    });
    
    var tasktype=${designTaskOrder.taskType};
     //图片格式验证
    function ajaxPicFileUploadImg(statusImg,str) {
        var file = document.getElementById(statusImg).files[0];
        if (!/image\/(PNG|png|JPG|jpg|JPEG|jpeg|gif|GIF)$/.test(file.type)) {
            commUtil.alertError("图片格式不正确！");
            return;
        } 
       var size = file.size;
       if (tasktype=='1') {
          if (size > 100 * 1024) {
            commUtil.alertError("图片过大，请重新上传！");
            return;
         } 	
	   }else if (tasktype=='2') {
		     if (str=='1') {
		    	 if (size > 500 * 1024) {
		             commUtil.alertError("图片过大，请重新上传！");
		             return;
		         } 
			}else {
				if (size > 800 * 1024) {
		             commUtil.alertError("图片过大，请重新上传！");
		             return;
		         }
			}
	   }
        var reader = new FileReader();
        reader.onload = function (e) {
            var image = new Image();
            image.onload = function () {
            	if (tasktype=='1') {
            		if (str=='1') {
            			if (this.width == '1080' && this.height == '335') {
                          if (str=='1') {
    		                    ajaxFileUploadLogo(statusImg);	
    						  }
                        }else {	 
                        	 commUtil.alertError("图片像素不是1080*335px");
  					   }
				   }else if (str=='2' || str=='3') {
						  if (this.width == '800' && this.height == '400') {
							  if (str=='2') {
									ajaxFileUploadLogo2(statusImg);
								}else if (str=='3') {
									ajaxFileUploadLogo3(statusImg);
								}
						  }else {
							  commUtil.alertError("图片像素不是800*400px");
							}
					  }           		
            		
				}           	            									
				if (tasktype=='2') {
					if (str=='1') {
            		 if (this.width== '750' && this.height == '400') {
            			 if (str=='1') {
            			   ajaxFileUploadLogo(statusImg);				
						 }
                      }else {
                    	  commUtil.alertError("图片像素不是750*400px！");
					  }
					}else if (str=='2' || str=='3') {
						if (this.width == '750' && this.height < '1500') {
							if (str=='2') {
								ajaxFileUploadLogo2(statusImg);								
							}else if (str=='3') {
								ajaxFileUploadLogo3(statusImg);
							}
						  }else {
							commUtil.alertError("图片像素不是750*<1500px！");
						 }
					}
				}

            };
            image.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }

    function ajaxFileUploadLogo() {
        $.ajaxFileUpload({
            url: contextPath + '/service/common/ajax_upload.shtml',
            secureuri: false,
            fileElementId: "logoPicFile",
            dataType: 'json',
            success: function (result, status) {
                if (result.RESULT_CODE == 0) {
                    $("#logoPic").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
                    $("#pic").val(result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error: function (result, status, e) {
                alert("服务异常");
            }
        });

    }
   
    function ajaxFileUploadLogo2() {
        $.ajaxFileUpload({
            url: contextPath + '/service/common/ajax_upload.shtml',
            secureuri: false,
            fileElementId: "logoPicFile2",
            dataType: 'json',
            success: function (result, status) {
                if (result.RESULT_CODE == 0) {
                    $("#logoPic2").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
                    $("#pic2").val(result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error: function (result, status, e) {
                alert("服务异常");
            }
        });

    }
    
    function ajaxFileUploadLogo3() {
        $.ajaxFileUpload({
            url: contextPath + '/service/common/ajax_upload.shtml',
            secureuri: false,
            fileElementId: "logoPicFile3",
            dataType: 'json',
            success: function (result, status) {
                if (result.RESULT_CODE == 0) {
                    $("#logoPic3").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
                    $("#pic3").val(result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error: function (result, status, e) {
                alert("服务异常");
            }
        });

    }
    
    
    
    function commitSave() {
        var pic = document.getElementById("pic");
        var pic2=document.getElementById("pic2");
        var pic3=document.getElementById("pic3");
        if ($.trim(pic.value) == "") {
            commUtil.alertError("请上传图片。");
            return;
        }
         if (tasktype=='2') {
        	 if ($.trim(pic2.value) == "" && $.trim(pic3.value) == "") {
                 commUtil.alertError("请上传图片。");
                 return;
             }
		}else {
			if ($.trim(pic2.value) == "") {
	            commUtil.alertError("请上传图片。");
	            return;
	        }
			if ($.trim(pic3.value) == "") {
	            commUtil.alertError("请上传图片。");
	            return;
	        }
		}          
         
            var dataJson = $("#form1").serializeArray();
            $.ajax({
                method: 'POST',
                url: '${pageContext.request.contextPath}/activityNew/saveDesignTaskOrderPic.shtml',
                data: dataJson,
                dataType: 'json',
                cache: false,
                async: false,
                timeout: 30000,
                success: function (data) {
                    if (data.returnCode == '0000') {
                        commUtil.alertSuccess("保存成功");
                        setTimeout(function () {
                            parent.location.reload();                         
                        }, 1000);
                    } else {
                        commUtil.alertError(data.returnMsg);
                    }
                },
                error: function () {
                    commUtil.alertError("请求失败");
                }
            });
    };
</script>

<html>
<body>
<form method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/activityNew/saveDesignTaskOrderPic.shtml">
  <input type="hidden" id="designTaskOrderId" name="designTaskOrderId" value="${designTaskOrder.id}">
  <input type="hidden" id="getWidth" name="getWidth" value="${getWidth}">
  <input type="hidden" id="getHeight" name="getHeight" value="${getHeight}">
  <input type="hidden" id="getPicDesc" name="getPicDesc" value="${getPicDesc}">
  <input type="hidden" id="getWidth1" name="getWidth1" value="${getWidth1}">
  <input type="hidden" id="getHeight1" name="getHeight1" value="${getHeight1}">
   <input type="hidden" id="getPicDesc1" name="getPicDesc1" value="${getPicDesc1}">
  <input type="hidden" id="getWidth2" name="getWidth2" value="${getWidth2}">
  <input type="hidden" id="getHeight2" name="getHeight2" value="${getHeight2}">
  <input type="hidden" id="getPicDesc2" name="getPicDesc2" value="${getPicDesc2}">
    <br>
    <table class="gridtable marT10 status-table">
        <tr>
            <td class="title title-width">订单编号</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                ${designTaskOrder.orderCode}
            </td>
        </tr>
        <tr>
            <td class="title title-width">任务分类</td>
            <td colspan="7" align="left" class="l-table-edit-td"> 
                 <c:if test="${designTaskOrder.taskType=='1'}">
                                                                     品牌特卖
                 </c:if>
                 <c:if test="${designTaskOrder.taskType=='2'}">
                                                                    店铺装修
                 </c:if>        
            </td>
        </tr>
        <tr>
            <td class="title title-width">任务名称</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                ${designTaskOrder.taskName}
            </td>
        </tr>
        <tr>
                <td class="title title-width" style="border-bottom-width: 0px;">需求详情</td>
                <td colspan="2" class="text-left" style="border-bottom-width: 0px;">
                     <textarea rows="5" class="txt-area" style="width:600px;height:150px;" 
                                      maxlength="1000">${designTaskOrder.requirement}
                                      
                      </textarea>
                 <c:if test="${not empty designTaskOrder.filePath}">
                    <br>
                   <a href="javascript:;" id="download" attachmentname="${designTaskOrder.filePath}" attachmentpath="${designTaskOrder.filePath}">【附件下载】</a>
                </c:if>
                </td>
         </tr>
        <tr>
            <td class="title title-width">支付金额</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                <div style="color: red;">￥${designTaskOrder.payAmount}</div>   
            </td>
        </tr>
        <tr>
            <td class="title title-width">联系方式</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                ${designTaskOrder.contactWay}
            </td>
        </tr>
        <tr>
            <td class="title title-width">联系QQ</td>
            <td colspan="7" align="left" class="l-table-edit-td">
                 ${designTaskOrder.qq}
            </td>
        </tr>
    </table>
     

    <br>
    <div class="title-top">
		<div class="table-title">
			<span>图片详情：</span>
		</div>
		<br>
	<table class="gridtable" id="gridtable-pic">
        <tr height="220px;">
		      <td class="title" width="20%">图片1</td> 
              <td align="left" class="l-table-edit-td">
                <div><img id="logoPic" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${designTaskOrderPic}"></div>
                <div style="float: left;height: 30px;">
                    <input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file" onchange="ajaxPicFileUploadImg('logoPicFile','1');"/>
                    <c:if test="${statusPage ne '1'}">
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                    </c:if>
                    <span style="color: gray;">尺寸:${getWidth}*${getHeight}(px),图片说明：${getPicDesc}</span>
                </div>
                <input id="pic" name="pic" type="hidden" value="${designTaskOrderPic}">
            </td>
        </tr>
        
        <tr height="220px;">
		      <td class="title" width="20%">图片2</td> 
              <td align="left" class="l-table-edit-td">
                <div><img id="logoPic2" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${designTaskOrderPic1}"></div>
                <div style="float: left;height: 30px;">
                    <input style="position:absolute; opacity:0;" type="file" id="logoPicFile2" name="file" onchange="ajaxPicFileUploadImg('logoPicFile2','2');"/>
                    <c:if test="${statusPage ne '1'}">
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                    </c:if>
                    <span style="color: gray;">尺寸:${getWidth1}*${getHeight1}(px),图片说明：${getPicDesc1}</span>
                </div>
                <input id="pic2" name="pic2" type="hidden" value="${designTaskOrderPic1}">
            </td>
        </tr>
        
        <tr height="220px;">
		      <td class="title" width="20%">图片3</td> 
              <td align="left" class="l-table-edit-td">
                <div><img id="logoPic3" style="width: 300px;height: 150px" alt="" src="${pageContext.request.contextPath}/file_servelt${designTaskOrderPic2}"></div>
                <div style="float: left;height: 30px;">
                    <input style="position:absolute; opacity:0;" type="file" id="logoPicFile3" name="file" onchange="ajaxPicFileUploadImg('logoPicFile3','3');"/>
                    <c:if test="${statusPage ne '1'}">
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                    </c:if>
                    <span style="color: gray;">尺寸:${getWidth2}*${getHeight2}(px),图片说明：${getPicDesc2}</span>
                </div>
                <input id="pic3" name="pic3" type="hidden" value="${designTaskOrderPic2}">
            </td>
        </tr> 
	  </table>
	</div>
	 <c:if test="${statusPage=='2' || statusPage=='3'}">
      <input name="btnSubmit" id="Button1" style="margin-left: 600px;" class="l-button l-button-submit" value="提交" onclick="commitSave();"/>
     </c:if>      
</form>
</body>
</html>
