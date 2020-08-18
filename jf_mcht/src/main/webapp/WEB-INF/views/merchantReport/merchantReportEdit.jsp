<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>编辑举报模板</title>
	<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/webuploader.css" />
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
            <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.td-pictures li{
display: inline-block;
}
.td-pictures li img{
width: 100px;
height: 100px;
}

  /*样式2*/  
        .file {  
            position: relative;  
            display: inline-block;  
            background: #3c9eff;  
            border-radius: 4px;  
            padding: 0px 12px;  
            overflow: hidden;  
            color: #fff;  
            text-decoration: none;  
            text-indent: 0;  
            line-height: 20px;  
        }  
        .file input {  
            position: absolute;  
            font-size: 100px;  
            right: 0;  
            top: 0;  
            opacity: 0;  
        }  
        .file:hover {  
            background: #AADFFD;  
            border-color: #78C3F3;  
            color: #004974;  
            text-decoration: none;  
        }  
</style>
</head>

<body>

  <div class="modal-dialog" role="document" style="width:700px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <c:if test="${empty subOrderId}">
			  <span class="modal-title" id="exampleModalLabel">填写信息</span>
		  </c:if>
		  <c:if test="${not empty subOrderId}">
			  <span class="modal-title" id="exampleModalLabel">填写信息<font color="red">(温馨提示:创建后请前往"交易管理-举报管理"查看进度。)</font></span>
		  </c:if>
      </div>
		<div class="modal-body">
		
		<div>
		<c:if test="${rejectReason!=null}">
		<span style="color:red;" >驳回原因 :</span>
		<span style="color:red;" >${rejectReason}</span>
		</c:if>
		</div>
		
		<form id="dataFrom">
		<input type="hidden" id="impeachMemberId" value="${impeachMember.id}" name="id">
		<input type="hidden" id="defaultScene" value="${scene}" >
		<input type="hidden" id="filesSize" value=" ${filesSize}" >	  
		<input type="hidden" id="fileMap" name ="fileMap" value=" ${fileMap}" >
		<input type="hidden" id="commentId" name ="commentId" value=" ${commentId}" >
	<%-- 	<input type="hidden" value="${productAfterTemplet.mchtId}" name="mchtId"> --%>
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
				<c:if test="${empty subOrderId}">
					<tr>
						<td class="editfield-label-td">举报类型<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<div class="col-md-2 search-condition">
						  <select class="form-control"  style="width:250px;" name="type" id="type" onchange="onchangeType()"  validate="{required:true}">
						   <option value="">--请选择--</option>
							<c:forEach var="reportingType" items="${reportingTypeList}">
						   		<option value="${reportingType.statusValue}"  <c:if test="${reportingType.statusValue==type}">selected</c:if>>${reportingType.statusDesc}</option>
						   </c:forEach>
                          </select>
						</div>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">举报场景<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<select class="form-control"  style="width:250px;" class="scene-select"  name="scene" id="scene" validate="{required:true}">
                         	</select>
                        </td>
					</tr>
					<tr>
						<td class="editfield-label-td">相关订单<span class="required">*</span></td>
						<td colspan="2" class="text-left" id="relatedOrders">
						<div id="opts" type="2">
						
						<c:if test='${not empty impeachMember.subOrderIds}'>
							<c:forEach var="subOrderCode" items="${subOrderCodesList}"   varStatus="s">
							<%-- <c:if test="${s.first}">   --%>
							<c:if test="${s.index==0}">
							<div>
							<span name="soi"><input type="text" style="width:250px;" name="subOrderId" value="${subOrderCode}"  ><span style="color:#CC5233"></span>
						    <span class="btn btn-info" id="opbtn">+</span> 
						 	<span class="btn btn-info" id="delbtn">-</span>
							</div>	
                           </c:if>  
                           <c:if test="${s.index>0}">
						   <span name="soi"><input type="text" style="width:250px;" name="subOrderId" value="${subOrderCode}"  ><span style="color:#CC5233"></span>
						   	</c:if> 
						   </c:forEach>
						   <span id="addSpan"></span>
						</c:if>
						
						
						<c:if test='${empty impeachMember.subOrderIds}'>
						<div>
						<span name="soi"><input type="text" style="width:250px;" id="subOrder" name="subOrderId" ><span style="color:#CC5233"></span>
						 <span class="btn btn-info" id="opbtn">+</span> 
						 <span class="btn btn-info" id="delbtn">-</span>
						 </div>			
						<span id="addSpan"></span>
						</c:if>
						<span id="optips"></span>
						
						</div>
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty subOrderId}">
					<tr>
						<td class="editfield-label-td">举报类型<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div class="col-md-2 search-condition">
								<select class="form-control" disabled selected style="width:250px;">
									<option value="1">评论不当</option>
								</select>
								<input type="hidden" id="type" name ="type" value="1" >
							</div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">举报场景<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<select class="form-control"  style="width:250px;" class="scene-select"  name="scene" id="scene" validate="{required:true}">
							</select>
						</td>
					</tr>
					<tr style="height: 58px">
						<td class="editfield-label-td">相关订单<span class="required">*</span></td>
						<td colspan="2" class="text-left" id="relatedOrders">
						<span name="soi"><input type="text" style="width:250px;" id="subOrder" name="subOrderId" value="${subOrderId}" disabled><span style="color:#CC5233"></span>
						</td>
					</tr>
				</c:if>
					<tr>
						<td class="editfield-label-td">举报说明<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<textarea id="remarks" style="width:250px;" name="description" rows="6" cols="40" value="${impeachMember.description}" validate="{required:true}" maxlength="200"> ${impeachMember.description}</textarea>
						<div style="display: inline-block;"><a  href="javascript:void(0);" id="viewProductDescExample" class="glyphicon glyphicon-question-sign"></a>
						<script type="text/javascript">
							$(function(){
							var popoverContent='<textarea rows="5" style="width:100%;">情况说明\n1.12.24消费者申请退货售后，商家12.25同意了消费者的退货申请。\n2.01.11商家拒绝给消费者退款，原因是消费者提供的寄回单号是虚拟单号，快递官网客服告知此单号是虚假单号，根本不存在，有联系快递的录音进行证实。</textarea>';
							$('#viewProductDescExample').popover({"html" : true,"content":popoverContent,"trigger":"focus"});
								})
						</script>
		</div>
					
				
						<div><span style="color:#999;">对接举报场景的补充说明(200字内)</span></div>
						</td>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">举报凭证<span class="required">*</span></td>
						<td colspan="2" class="text-left">
							<div class="form-group">
							<a href="javascript:;" class="file">上传凭证<input type="file" id="file" name="file" onchange="uploadFile(this)"><input type="hidden" id="filePath"></a><span id="fileMes"></span>
							</div>	
						<!-- 	class="table table-bordered dataTable no-footer"  -->
						<table id="datatable" role="grid" aria-describedby="datatable_info"  border="0px solid gray;" width="90%" height="40"  bordercolor="black">
						<!--修改时遍历出来的上传图片  -->
						<tbody id="updatementTbody">
						<c:forEach var="impeachMemberProof" items="${impeachMemberProofs}">
							<tr id="">
							<td ><a href="javascript:;" >${impeachMemberProof.fileName}</a></td>
							<td ><a href="javascript:;" >已上传</a></td>
							<td ><a href="javascript:;" id="${impeachMemberProof.id}" name="downLoadimpeachMemberProof"  data-filename="${impeachMemberProof.fileName }" data-filepath="${impeachMemberProof.filePath }">[下载]</a></td>
							<td><a href="javascript:;" class="btnDelete" onclick="deleteUpdate(${impeachMemberProof.id})">[删除]</a></td>
							</tr>
						</c:forEach>
						</tbody>

						<!--填写举报时的上传图片  -->
						<tbody id="attachmentTbody">
					<%-- 	<c:forEach var="impeachMemberProof" items="${impeachMemberProofs}">
							<tr>
							<td ><a href="javascript:;" >${impeachMemberProof.fileName}</a></td>
							<td ><a href="javascript:;" >已上传</a></td>
							<td class="w_h_100_36"><a href="javascript:;" id="${impeachMemberProof.id}" name="downLoadimpeachMemberProof"  data-filename="${impeachMemberProof.fileName }" data-filepath="${impeachMemberProof.filePath }">[下载]</a></td>
							<td><a href="javascript:;" class="btnDelete">[删除]</a></td>
							</tr>
						</c:forEach> --%>
						</tbody>
						</table>
							<div><span style="color:#999;">请上传举报凭证，可上传图片、压缩包、视频文件。视频大小不超过100M</span></div>
						</td>
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">您的姓名<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" id="phone" value="${impeachMember.name}" name="name" validate="{required:true}" maxlength="6">
						<div><span style="color:#999;">请填写举报人的姓名</span></div>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">您的手机号码<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<input type="text" id="mobile" value="${impeachMember.mobile}" name="mobile" validate="{required:true,mobile:true}">
						<div><span style="color:#999;">请填写正确手机号码方便与您沟通处理</span></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>

	<div class="modal-footer">

			<button class="btn btn-info" onclick="commitSave();" style="margin-right: 17px;" <c:if test="${mchtInfo.zsTotalAuditStatus == 2 && mchtInfo.status == 0}">disabled="disabled"</c:if>>提交</button>
			<button class="btn btn-info" data-dismiss="modal">取消</button>
      </div>

			</div>


<!--弹出文件上传中Div-->
<div class="video_box" style="position:fixed; width:320px; height:170px; top:55%; left:35%; display: none;" id="upFileDiv">
   <%--  <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a> --%>
    	<div class="panel panel-default" style="text-align:center;padding-top:15px;" >

		   <p style="text-align:center ; color:#123456;font-size:15px"  >文件上传中,请稍等...</p>
		 </div>
</div>



	<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
	type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webuploader.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/imageUpload.js" type="text/javascript"></script>


<script type="text/javascript">

/* 动态二级下拉框 */
 var typeCode
 function onchangeType(){

 	typeCode=$("#type option:selected").val();
	  getSceneList(typeCode);
 }

 function getSceneList(typeCode)
  {

	 var path = '${pageContext.request.contextPath}/merchantReport/getReportingScenarioList';
		jQuery.ajax( {
	  		url : path,
	  		type : 'POST',
	  		dataType : 'json',
	  		data: {"type":typeCode},
	  		cache : false,
	  		async : false,
	  		timeout : 30000,
	  		success : function(json)
	  		{  var sel = $("#scene");
					sel.empty();
				   sel.append("<option value=\"" + "\">--请选择--</option>");
				var defaultScene = $("#defaultScene").val();
					$.each(json, function(index, item) {
						  	if(item.statusValue==defaultScene) {
								sel.append("<option  value=\""+item.statusValue+"\" selected >"+ item.statusDesc +"</option>");
							}else{
								  sel.append("<option  value=\""+item.statusValue+"\" >"+ item.statusDesc +"</option>");
							}

						});
	  		},
			error : function()
			{
				swal({
					  title: "提交失败！",
					  type: "error",
					  timer: 1500,
					  confirmButtonText: "确定"
					});
			}
	  	});
  }

 var dataFromValidate
 $(function(){

	 $.metadata.setType("attr", "validate");

	 dataFromValidate =$("#dataFrom").validate({
	        highlight : function(element) {
	        	$(element).addClass('error');
	            $(element).closest('tr').find("td").first().addClass('has-error');
	        },
	        success : function(label) {
	            label.closest('tr').find("td").first().removeClass('has-error');
	        }
		});
	 getSceneList($("#type").val());



	 /* 动态添加input框 */
		// 添加选项
		$("#opbtn").click(function(){
			if($("span[name='soi']").size() < 50){// 最多添加50个选项
			var str="<span name=\"soi\"><input  name=\"subOrderId\"  style=\"width: 250px;\"  ><span style=\"color:#CC5233\"></span>";
			/* $(str).insertBefore("#addSpan");  */
			$("span[name='soi']").last().append(str);
			}else{// 提示选项个数已经达到最大
				swal("选项个数已经达到最大,不能再添加!")
			}

		});

		// 删除选项
		$("#delbtn").click(function(){
			if($("span[name='soi']").size() <= 1){
				swal("不可以再删除了")
			} else{
				// 删除选项,每次删除最后一个
				$("span[name='soi']").last().remove();
			}

		});


		//文件删除,第一次填写举报时候的删除
		$("#attachmentTbody").on('click', '.btnDelete', function () {
		/* 	console.log(fileS);
			console.log($("#attachmentTbody").find("tr")); */
			var index = $("#attachmentTbody").find("tr").index($(this).closest('tr'));
			 $(this).closest('tr').remove();
			   fileS.splice(index,1);
			 /*    console.log(fileS);  */

			});

		//文件下载
		 $("a[name='downLoadimpeachMemberProof']").on('click',function(){
				var fileName = $(this).data("filename");
				var filePath = $(this).data("filepath");
				location.href="${ctx}/merchantReport/downLoadImpeachMemberProof?fileName="+fileName+"&filePath="+filePath;
	          });

	});

 var deleteIds=[]
 var uploadSize=$("#filesSize").val()*1;
//修改的时候的,已上传文件的文件删除
 function deleteUpdate(fileId){
	var fileMaps = ${fileMap};
	var temp = fileMaps[0];
	if(temp!="undefined"&&temp!=0){
		/* var aaa = ${fileMap}; */
		var del = fileMaps[fileId];
		uploadSize=uploadSize*1-del*1;

	}
	deleteIds.push(fileId);

   $("#updatementTbody").on('click', '.btnDelete', function () {
			 $(this).closest('tr').remove();
	});

}








var timer = 0;
 function commitSave(){
	 /*举报单验证  */
	 var sflag=false;
 	 var subs= $("input[name='subOrderId']");
 	 var phonereg = /^[A-Z]{3,5}\d{18}$/;
	 $("input[name='subOrderId']").each(function(){
		 if($(this).val()==""){
		 $(this).parent().find("span[style]").text("不可为空");
		 sflag=true;
		  }else{
			  if(!phonereg.test($(this).val()))
			    {
				  $(this).parent().find("span[style]").text("订单号格式不正确");
			        sflag=true;
			    } else{
			    	 $(this).parent().find("span[style]").text("");
			    }

		  };
	 });

	 if(sflag){
		 return;
	 }

	 /*举报凭证验证  */
	var fs=[];
 	$("#datatable a").each(function(){
 		var picName={};
 		picName=$(this).attr("data-filename");
 		if(picName!=null){
 		fs.push(picName);
 		}
		});
	if(fs.length<=0){
		swal("请上传举报凭证");
	 	return;
	 }


 /*表单提交  */
	if(dataFromValidate.form()){

	var arr=[];
	$("#relatedOrders input").each(function(){
		var sOrder={};
		sOrder=$(this).val()
		if(sOrder!=null){
		arr.push(sOrder);
		}
		});

	var imgSrc=$("#img").src;

	var filePaths=[];
 	$("#datatable a").each(function(){
 		var picPath={};
 		picPath=$(this).attr("data-filepath")
 		if(picPath!=null){
 			filePaths.push(picPath);
 		}
		});

	var fileNames=[];
 	$("#datatable a").each(function(){
 		var picName={};
 		picName=$(this).attr("data-filename");
 		if(picName!=null){
 		fileNames.push(picName);
 		}
		});


			var dataJson = $("#dataFrom").serializeArray();
			dataJson.push({"name":"subOrderCodes","value":JSON.stringify(arr)});
			dataJson.push({"name":"imgSrc","value":JSON.stringify(imgSrc)});
			dataJson.push({"name":"filePaths","value":JSON.stringify(filePaths)});
			dataJson.push({"name":"fileNames","value":JSON.stringify(fileNames)});
			dataJson.push({"name":"deleteIds","value":JSON.stringify(deleteIds)});

			$.ajax({
				url : "${ctx}/merchantReport/saveMerchantReport",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : dataJson,
				timeout : 30000,
				traditional:true,
				success : function(data) {
					if (data.returnCode=="0000") {
						table.ajax.reload();
						/* isReload=true;  */
						$("#viewModal").modal('hide');
						swal({
							  title: "保存成功!",
							  type: "success",
							  confirmButtonText: "确定"

							});
					}else{
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





 /*文件上传  */




var fileS=[];
function uploadFile(obj) {
/* 	console.log("uploadSize"+uploadSize) */
	 	var totalSize=0*1+uploadSize*1;
		if (obj.files.length === 0) {
			return;
		}
		var oFile = obj.files[0];
		var rFilter = ["jpg","bmp","png","gif","JPG","BMP","PNG","GIF","mp3","wav","wma","ogg","ape","acc","MP3","WAV","WMA","OGG","APE","ACC","avi","mp4","mov","rm","wma","mkv","rmvb","AVI","MP4","MOV","RM","WMA","MKV","RMVB","doc","docx","xls","xlsx","ppt","pptx","pdf","rar","zip","DOC","DOCX","XLS","XLSX","PPT","PPTX","PDF","RAR","ZIP"];
	 	var suffix = oFile.name.substring(oFile.name.lastIndexOf(".")+1);
	 	var fileName = oFile.name;
	 	var fileSize = oFile.size;
		if($.inArray(suffix,rFilter)==-1){
			swal("文件格式不正确！");
			return;
		}
		if(suffix=="MP4"||suffix=="mp4"||suffix=="avi"||suffix=="AVI"){
			if(oFile.size>100*1024*1024){
		        swal("视频大小不能超过100M");
		        return;
		    }
		}

		/* var totalSize=$("#filesSize").val()*1; */

		for(var i=0;i<fileS.length;i++){
			totalSize += fileS[i].size*1;
		}

		if(Number(totalSize)+Number(oFile.size)>400*1024*1024){
	        $("#file").attr('type','text'); //解决input文件上传不能传同一文件主要就是这两句操作
		      	$("#file").attr('type','file');
			 swal("文件总大小不能超过400M");
		       return;
		};
		fileS.push(oFile);

		$("#upFileDiv").show()
		var reader = new FileReader();
	    reader.onload = function(e) {
	    	var formData = new FormData();
	    	formData.append("file",oFile);
	    	formData.append("fileType",5);
	    	$.ajax({
	    		url : "${ctx}/common/fileUpload",
	    		type : 'POST',
	    		data : formData,
	    		async: false,
	    		// 告诉jQuery不要去处理发送的数据
	    		processData : false,
	    		// 告诉jQuery不要去设置Content-Type请求头
	    		contentType : false,
	    		beforeSend:function(){
	    			//alert("文件片上传中，请稍候");

	    		},
	    		success : function(data) {
	                if (data.returnCode=="0000") {
	                	var successName="已上传";
	                	var deleteName="[删除]";
	                	var downLoad="[下载]"
	                    var filePath = data.returnData;
	                	$("#upFileDiv").hide();

          		           	var html = [];
          		           	html.push('<tr>');
          		           	html.push('<td ><a href="javascript:;">'+fileName+'</a></td>');
          		        	html.push('<td class="w_h_100_36"><a href="javascript:;">'+successName+'</a></td>');
          		           	html.push('<td class="w_h_100_36"><a href="javascript:;" name="downLoadimpeachMemberProof" date-filesize="'+fileSize+'"  data-filename="'+fileName+'" data-filepath="'+filePath+'">'+downLoad+'</a></td>');
          		           	html.push('<td><a href="javascript:;" class="btnDelete">'+deleteName+'</a></td>');
          		           	html.push('</tr>');
          		           	$("#attachmentTbody").append(html.join(""));   
          		           	
          		         $("a[name='downLoadimpeachMemberProof']").on('click',function(){ 
          					var fileName = $(this).data("filename");
          					var filePath = $(this).data("filepath");
          					location.href="${ctx}/merchantReport/downLoadImpeachMemberProof?fileName="+fileName+"&filePath="+filePath;
          		          }); 
          		       
          		        /*  $("#file").val(""); */
          		        $("#file").attr('type','text'); //解决input文件上传不能传同一文件主要就是这两句操作
          		      	$("#file").attr('type','file');
    		        }else{
    		        	swal("上传失败，请稍后重试");
    		        }
	    		}, 
	    		error : function(responseStr) { 
	    			swal("文件上传失败");
	    		} 
	    		});
	    }
	    reader.readAsDataURL(oFile); 
	}

 
 
 
 


</script>
</body>
</html>
