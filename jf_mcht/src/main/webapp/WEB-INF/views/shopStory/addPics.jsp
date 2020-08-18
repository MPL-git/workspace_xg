<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="readonly" value="true" />
<c:if test="${empty activity || activity.status==1 || activity.status==4}">
    <c:set var="readonly" value="false" />
</c:if>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>图片链接</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css"/>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css"/>
    <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />

    <style type="text/css">
        .hidden{
            display:none;
        }
        .dv1{
            border:1px solid #cccccc;
            border-width:2px 1px 1px 1px;
            padding:10px 10px;
            margin-bottom: 10px;
            line-height: 30px;
        }

        .vm-famate .vm {
            margin: 0 3px 0 0;
        }
        
        img{
		 width:100%;
		 height:100%;
		
		}
    </style>
</head>

<body>
  <div class="modal-dialog" role="document" style="width:700px;hight:300px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">图片链接</span>
      </div>
		<div class="modal-body">
            <form id="dataFrom2">
                <input type="hidden" value="${activityArea.id}" name="activityArea.id">
                <input type="hidden" value="${picPath}" name="picPath" id= "picPath">
                <input type="hidden" name="activity.preSellAuditStatus" id="preSellAuditStatus">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>

  <!--                       <style>
                            .single_pic_picker.x1 {
                                width: 800px;
                                height: 150px;
                                margin: 0;
                            }
                            .single_pic_picker.x1 div,
                            .single_pic_picker.x1 img,
                            .single_pic_picker.x1 input {
                                width: 100% !important;
                                height: 100% !important;
                            }
                            .single_pic_picker.x1 div {
                                line-height: 200px;
                            }
                            
                        </style> -->
           
	                            
                        <tr>
                        <td class="editfield-label-td">图片</td>
                            <td colspan="2" class="text-left padding-10" >
                            <div style="overflow-y:scroll;height:330px; display: none" id="storyDetails">
                            </div>
                               <div class="single_pic_picker x1">
                                    <input type="hidden" name="storyPic" value="${activity.posterPic}"/>
                                    <input id="storyPic" onchange="loadImageFile(this)" type="file">
                                 	<c:if test='${picPath != null && picPath != ""}'>
                                        <img src="${ctx}/file_servelt${picPath}" id="preview_header">
                                    </c:if>
                                    <c:if test='${picPath == null || picPath == ""}'>
                                       <div>+</div>
                                    </c:if>
                                    
                                </div> 
                                 <div style="color: #999;">尺寸：宽度为750像素，高度不超过1500象素，大小：200kb以内</div> 
                            </td>
                        </tr>
						
						 <tr>
                            <td class="editfield-label-td">图片链接</td>
                            <td colspan="2" class="text-left padding-10">
                            <c:if test="${picUrl==null || picUrl=='' }">
                             <input type="text" id="picUrl" name="picUrl" value="http://" style="width:250px;">
                             </c:if>
                             
                              <c:if test="${picUrl!=null && picUrl!=''}">
                             <input type="text" id="picUrl" name="picUrl" value="${picUrl }" style="width:250px;">
                             </c:if>
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </form>

            <div class="modal-footer">
            	<button class="btn btn-info" onclick="picSave();">确认</button>
            	<button class="btn btn-info" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>

<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="1" role="dialog" aria-labelledby="productModalLabel"
     data-backdrop="static">
</div>

<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.serializejson.js" type="text/javascript"></script>

<script type="text/javascript">
    var dataFromValidate;
    var filePath;
    $(function () {
        $.metadata.setType("attr", "validate");
        dataFromValidate = $("#dataFrom").validate({
            highlight: function (element) {
                $(element).addClass('error');
                $(element).closest('tr').find("td").first().addClass('has-error');
            },
            success: function (label) {
                label.closest('tr').find("td").first().removeClass('has-error');
            },
            errorPlacement: function (error, element) {
                error.appendTo($(element).closest('td'));
            }
        });
    });
    

    function picSave() {
        if (dataFromValidate.form()) {
            var storyPic = $("input[name='storyPic']");
            var storyPics = storyPic.parent().children("img");
            if(storyPics.length<=0){
                swal({
                    title: '请上传图片',
                    type: "error",
                    confirmButtonText: "确定"
                });
                return;
            }
            uploadImg("storyPic",storyPic)
            $("#viewModal").modal('hide');
            if(filePath==null||filePath==''){
            	filePath = $("#picPath").val();
            }
		 	 var ff = "${ctx}/file_servelt"+filePath; 
		 	var picIds = "${picId}";
			$("#"+picIds).attr("src",ff); 
			$("#"+picIds).next("input[name='picPath']").first().val(filePath);//文件路径
			var  fileUrl = $("#picUrl").val();
			$("#"+picIds).next().next("input[name='picUrl']").first().val(fileUrl);//文件跳转连接
			$("#"+picIds).parent().children("div").remove();

			
			/* $("#"+picIds).append("<input value='"+filePath+"'>") */
        }
    }



    //上传图片
    function uploadImg($img,$valueFiled) {
		var oFile = document.getElementById($img).files[0];
    	var formData = new FormData();
    	formData.append("file",oFile);
    	$.ajax({ 
    		url : "${ctx}/common/fileUpload?fileType=3", 
    		type : 'POST', 
    		data : formData, 
    		async: false,
    		// 告诉jQuery不要去处理发送的数据
    		processData : false, 
    		// 告诉jQuery不要去设置Content-Type请求头
    		contentType : false,
    		beforeSend:function(){
    			console.log("图片片上传中，请稍候");
    		},
    		success : function(data) {
                if (data.returnCode=="0000") {
                    $valueFiled.val(data.returnData);
                    filePath=data.returnData;
     		        $("#storyPic").attr('type','text'); //解决input文件上传不能传同一文件主要就是这两句操作
      		      	$("#storyPic").attr('type','file');
                } else {
                    swal({
                        title: "图片上传失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
    		}, 
    		error : function(responseStr) { 
    			swal("图片上传失败");"D:/常用工具/有道云笔记/space/sina1796745517/e5a17b59490c456399129a8e7bab11d6/clipboard.png"
    		} 
    		});
    }

    function loadImageFile(obj) {
        if (obj.files.length === 0) {
            return;
        };
        
        var oFile = obj.files[0];
        if(oFile.size>200*1024){
            swal("图片大小不能超过200K");
          ;  return;
        }
        
		var rFilter = ["jpg","png"];
	 	var suffix = oFile.name.substring(oFile.name.lastIndexOf(".")+1);
	 	var fileName = oFile.name;
	 	var fileSize = oFile.size;
		if($.inArray(suffix,rFilter)==-1){
			swal("请上传.jpg 或 .png 的图片！");
			return;
		};
		
		var reader = new FileReader();
		reader.onload = function(e){
			var image = new Image();
			image.onload=function(){
				
		        var imgWidth = '750';
		        var imgHeight= '1500';
		        if(this.width!=imgWidth){
		        	$("#storyPic").val("");
		        	swal("宽度限制为750象素");
		        
		        }
		        if(this.height>imgheight){
		        	$("#storyPic").val("");
		        	swal("高度不能超过1500像素");
		        
		        }
		        
			}
			
		}    

        
        if($(obj).parent().children("img").length<=0){
            $('<img>').appendTo( $(obj).parent() );;
        }

        var oFReader = new FileReader();
        oFReader.onload = function(oFREvent) {
            var img=new Image();
            img.src=oFREvent.target.result;
            img.onload=function(){
            	if(img.width!=750||img.height>1500){
            		if(img.width!=750){
      		        	$("#storyPic").val("");
      		        	swal("宽度限制为750象素");
       		        
       		        }
       		        if(img.height>1500){
       		        	$("#storyPic").val("");
       		        	swal("高度不能超过1500像素");
       		        
       		        }
                    return;
                }else{
		          $(obj).parent().children("img").attr("src",oFREvent.target.result);
		          $(obj).parent().children("div").remove();
                }
            }

        };
        oFReader.readAsDataURL(oFile);
    }


</script>
</body>
</html>
