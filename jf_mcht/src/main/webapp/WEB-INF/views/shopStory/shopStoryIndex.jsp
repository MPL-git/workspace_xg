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
    <title>店铺故事</title>
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
    </style>
</head>

<body>
    <div class="x_panel container-fluid py-tm">
        <div class="row content-title">
    	<input type="hidden" id="basePath" value="${ctx}">
            <div class="t-title">
			故事简介
            </div>
        </div>

        <div class="search-container container-fluid vm-famate">
            <form id="dataFrom">
            <input type="hidden" id="shopStoryId" name="shopStoryId" value="${shopStoryId}">
            <input type="hidden" id="shopStory" name="shopStory" value="${shopStory}">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>

                        <style>
                            .single_pic_picker.x1 {
                                width: 600px;
                                height: 100px;
                                margin: 0;
                            }
                            .single_pic_picker.x1 div,
                            .single_pic_picker.x1 img,
                            .single_pic_picker.x1 input {
                                width: 100% !important;
                                height: 100% !important;
                            }
                           .single_pic_picker.x1 div {
                                line-height: 100px;
                            }
                        </style>
<%--                         <tr>
                            <td class="editfield-label-td">故事简介</td>
                            <td colspan="2" class="text-left padding-10">
                            <div>
                              <textarea rows="10" cols="30" id="storyBrief" name="storyBrief" style="resize:none;width:600px;" onkeyup="$('#product_name_length').text(($('#storyBrief').val().length));" >${shopStory.storyIntroduction }</textarea>
                            </div>
                              <span style="color:red;margin: 0 3px;" id="product_name_length">0</span>
                              <span style="color:red;margin: 0 3px;">/200</span>
                            </td>
                        </tr> --%>
                        
                        <tr>
                          <td class="editfield-label-td">故事简介</td>
                        	<td colspan="2" class="text-left padding-10" >
								<c:if test="${not empty shopStoryList}" >
									<c:forEach var="shopStory" items="${shopStoryList}">
										<div >
											<textarea id="storyBrief" name="storyBrief"  rows="5" cols="30" style="resize:none;width:600px;" placeholder=" 请输入文字" onkeyup="$('#product_name_length').text(($('#storyBrief').val().length));">${shopStory.storyIntroduction }</textarea>
										</div>
										<span></span>
										<span style="color:red;margin: 0 3px;" id="product_name_length">0</span>
										<span style="color:red;margin: 0 3px;">/200</span>
									</c:forEach>
								</c:if>
								<c:if test="${empty shopStoryList}" >
									<div >
										<textarea id="storyBrief" name="storyBrief"  rows="5" cols="30" style="resize:none;width:600px;" placeholder=" 请输入文字" onkeyup="$('#product_name_length').text(($('#storyBrief').val().length));">${shopStory.storyIntroduction }</textarea>
									</div>
									<span></span>
									<span style="color:red;margin: 0 3px;" id="product_name_length">0</span>
									<span style="color:red;margin: 0 3px;">/200</span>
								</c:if>
                        	</td>
                        </tr>
                        
                        <tr >
							<td class="editfield-label-td" rowspan="2">故事详情</td>
                           	<td colspan="2" class="text-left padding-10">
                           <!--  <div align="center" > -->
                           <div>
                          	<span class="btn btn-info" onclick="addWords();">添加文字</span>
                            <span class="btn btn-info" onclick="addPics();">添加图片</span>
                            </div>

                            </td>

                        </tr>
						
						
							                            
                        <tr>
                            <td colspan="2" class="text-left padding-10" >
                            <div style="overflow-y:scroll;height:600px;" id="storyDetails">
                            <c:if test="${shopStoryDetailList != null}">
                            
                           		<c:forEach var="shopStoryDetail" items="${shopStoryDetailList}">
                           			<!--文字  -->
                           			 <c:if test="${shopStoryDetail.type==0 }">
                           			<div> 
                           			<span class="btn btn-info" style="float:right;margin-right:85px" onclick="del(this);">删除</span>
                           			</div>
                           			 <div name="wordAndPic" >
								     	<textarea name="word" rows="5" cols="30" style="resize:none;width:600px;" placeholder=" 请输入文字" >${shopStoryDetail.content }</textarea>
								     </div>
                           			 </c:if>
                            
                            		<!--图片  -->
                            		<c:if test="${shopStoryDetail.type==1 }">
                            		<div> <span class="btn btn-info" style="float:right;margin-right:85px" onclick="del(this);">删除</span></div>
                            			<div name="wordAndPic" class="single_pic_picker x1" onclick="addStoryPic(this)" >';
									          <img id="${shopStoryDetail.id }" name="picImg" src="${ctx}/file_servelt${shopStoryDetail.pic}">
									          <input type="hidden"  name="picPath" value="${shopStoryDetail.pic }"> 
									          <input type="hidden"  name="picUrl" value="${shopStoryDetail.picUrl }">
									       <!--   <input type="hidden" name="story" value=""/>
									         <input id="story" onchange="loadImageFile(this)" > -->
									          
								         </div>
								       
                            		</c:if>
                            
                            	</c:forEach>
                            
                            </c:if>

                            </div>
                            </td>
                        </tr>
						

                        </tbody>
                    </table>
                </div>
            </form>

            <div class="modal-footer">
            	 <button class="btn btn-info" onclick="commitSave();">提交</button> 
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
    $(function () {
    	 var shopStoryList = "${shopStoryList}";
    	 var wordNum = $("#storyBrief").val();
    	if(wordNum!=null&&wordNum!=""){
    		$("#product_name_length").html(wordNum.length);
    	}  

    	$("input[name='fullCut[sumFlag]']").on('click',function(){
    		var sumFlag = $(this).val();
    		if(sumFlag == "0"){
    			$("input[name='fullCut[ladderFlag]']").val(1);
    		}else if(sumFlag == "1"){
    			$("input[name='fullCut[ladderFlag]']").val(0);
    		}
    	});

        $.metadata.setType("attr", "validate");
        dataFromValidate = $("#dataFrom").validate({
        	
   		 rules: {
   			storyBrief: {
				 required:true,
				 rangelength:[0,200],
		      }
		 },
		 messages: {
			 storyBrief: {
			        required: "不可为空",
			        rangelength:"最多输入200个字",
			      }
		 },
        	
        	
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
    
    
    //添加文字
    function addWords(){
    	
    	$("#storyDetails").show();
    	var html='';
    	html+='<div name="wordAndPic" >';
    	html+='<div> <span class="btn btn-info" style="float:right;margin-right:85px" onclick="del(this);">删除</span></div>';
    	html+='<textarea name="word" rows="5" cols="30" style="resize:none;width:600px;" placeholder=" 请输入文字" ></textarea>';
    	html+='</div>';
    	$("#storyDetails").append(html);
    	
    }

    //添加图片
    var time ;
    var mm;
    var picTemp="picTemp";
    function addPics(){ 
    	var picCount = 0;//图片数量
    	$("input[name='picPath']").each(function(){
    		picCount++;
    	});
    	
    	if(picCount>50){
    		swal("最多插入50张图片");
    		return;
    	};
    	
    	var now = new Date();
    	 time = now.getTime();
    	 mm = picTemp+time;
    	$("#storyDetails").show();
       		var html='';
       		html+='<div> <span class="btn btn-info" style="float:right;margin-right:85px" onclick="del(this);">删除</span></div>';
        	html+='<div name="wordAndPic" class="single_pic_picker x1" onclick="addStoryPic(this)">';
         	html+=' <img id="'+mm+'" name="picImg">'; 
         	html+='  <input type="hidden"  name="picPath" value="">'; 
         	html+='  <input type="hidden"  name="picUrl" value="">';
       
        	html+='<div>+</div>';
        	html+='	</div>';
			$("#storyDetails").append(html); 
    };
    
    //跳转选择图片和填写连接
    function addStoryPic(_this){
    var picId= 	$(_this).children("img").attr("id");
    var picPath= $(_this).children("input[name='picPath']").val();
    var picUrl= $(_this).children("input[name='picUrl']").val();
		$.ajax({
	        url: "${ctx}/shopStory/addPics?picId="+picId+"&picPath="+picPath+"&picUrl="+picUrl, 
	        type: "GET", 
	        success: function(data){
	           $("#viewModal").html(data);
	           $("#viewModal").modal();  
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
    };
    
    //删除
    function del(_this){
    	$(_this).parent().next().remove();
    	$(_this).parent().remove();

    }


    function commitSave() {

        var picFlage = false;//图片非空
    	var wordFlage = false;//文字非空
    	var wordCount = 0;//文字数量<1500
    	$("input[name='picPath']").each(function(){
    		if($(this).val()==""||$(this).val()==null){
    			picFlage=true;
    		}
    		
    	});
    	
    	$("textarea[name='word']").each(function(){
    		var temp = $(this).val().length;
    		wordCount+=temp;
    		if($(this).val()==""||$(this).val()==null){
    			wordFlage=true;
    		}
    	});
    	
    	if(picFlage){
    		swal("有模块没有上传图片");
    		return;
    	};
    	
    	if(wordFlage){
    		swal("有模块没有填写文字");
    		return;
    	};
    	
    	if(wordCount>1500){
    		swal("一共最多输入1500字");
    		return;
    	}
    	

    	var detailsCount = $("#storyDetails").find("div[name='wordAndPic']");
    	if(detailsCount.length<=0){
    		swal("故事详情不能为空");
    		return;
    	}
    	
    
    
    	 
    	 var picArray=[];
   		  $("div[name='wordAndPic']").each(function(){ 
   			var word =  $(this).find("textarea[name='word']").first().val();
   			var picPath =  $(this).find("input[name='picPath']").first().val();
   			var picUrl =  $(this).find("input[name='picUrl']").first().val();
   			
   			
			if(word==undefined){
				word="";
			};
			if(picPath==undefined){
				picPath="";
			};
			if(picUrl==undefined){
				picUrl="";
			};
   			var obj={
   			"word":word,
   			"picPath":picPath,
   			"picUrl":picUrl
   			} 
   		 	picArray.push(obj); 
   		  });
   		  
   		  
   		if(dataFromValidate.form()){
   		var dataJson = $("#dataFrom").serializeArray();
   		dataJson.push({"name":"picArrayJsonStr","value":JSON.stringify(picArray)});
   	
   		
		$.ajax({
			url : "${ctx}/shopStory/shopStoryDetailSave",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : dataJson,
			timeout : 30000,
			success : function(data) {
				if (data.returnCode=="0000") {
					$("#viewModal").modal('hide');
					swal({
						  title: "提交成功!",
						  type: "success",
						  confirmButtonText: "确定"
						  
						});
					window.parent.location.reload();
				} else {
					swal({
						  title: data.returnMsg,
						  type: "error",
						  confirmButtonText: "确定"
						});
				}
			},
			error : function() {
				swal({
					  title: "提交失败！",
					  type: "error",
					  timer: 1500,
					  confirmButtonText: "确定"
					});
			}
		});
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
                } else {
                    swal({
                        title: "图片上传失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
    		}, 
    		error : function(responseStr) { 
    			swal("图片上传失败");
    		} 
    		});


    }

    function loadImageFile(obj) {
        if (obj.files.length === 0) {
            return;
        }
        var oFile = obj.files[0];
        if(oFile.size>100*1024){
            swal("图片大小不能超过100K");
            return;
        }
        var rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
        if (!rFilter.test(oFile.type)) {
            swal("请选择图片文件");
            return;
        }
        if($(obj).parent().children("img").length<=0){
            $('<img>').appendTo( $(obj).parent() );;
        }

        var oFReader = new FileReader();
        oFReader.onload = function(oFREvent) {
            var img=new Image();
            img.src=oFREvent.target.result;
            img.onload=function(){
	          $(obj).parent().children("img").attr("src",oFREvent.target.result);
	          $(obj).parent().children("div").remove();
                
            }

        };
        oFReader.readAsDataURL(oFile);
    }


</script>
</body>
</html>
