<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("brandAptitude") != null ? request.getParameter("brandAptitude") : "";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
	<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	 <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
	<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        body{ font-size:12px;padding:10px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
<style type="text/css">
	.img-div {
		display: inline-block;
		vertical-align: middle;
	}
	.img-box {
		position: relative;
		width: 80px;
		height: 80px;
		border: 1px solid #6B6B6B;
		margin-left: 20px;
		display: inline-block;
	}
	.img-pic {
		width: 100%;
		height: 100%;
		display: block;
	}
	.img-box:hover .top-box {
		display: block;
	}
	.top-box {
		display: none;
		position: absolute;
		top: 0;
		left: 0;
		z-index: 1;
		width: 100%;
		height: 20px;
		background: rgba(0, 0, 0, .5);
	}
	.top-box span {
		position: relative;
		float: right;
		width: 20px;
		height: 20px;
		border-radius: 10px;
		background: red;
		cursor: pointer;
	}
	.top-box span:after,
	.top-box span:before {
		position: absolute;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		content: '';
		width: 16px;
		height: 4px;
		margin: auto;
		background: white;
		border-radius: 2px;
		transform: rotate(45deg);
	}
	.top-box span:before {
		transform: rotate(-45deg);
	}
	.add-box {
		position: relative;
		width: 80px;
		height: 80px;
		border: 1px solid #6B6B6B;
		margin-left: 20px;
		display: inline-block;
		vertical-align: middle;
		text-align: center;
	}
	.file-btn {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: 1;
		opacity: 0; 
	}
	.add-span {
		height: 80px;
		line-height: 80px;
	}
</style>
<script type="text/javascript">

		var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
		function closeDialog(){
    		dialog.close();//关闭dialog 
		}
		
		var editor1;
        KindEditor.ready(function(K) {
    		 editor1 = K.create('textarea[name="brandAptitude"]', {
    			width:'85%',
    			height : '650px',
    			cssPath : '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
    			uploadJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
    			fileManagerJson : '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
    			allowFileManager : true,
    			afterCreate : function() {
    			}
    			
    		});
    		prettyPrint();
    	});
		
        function submit_fun(){
			editor1.sync();
			var brandAptitude = document.getElementById("brandAptitude").value;
			if(!brandAptitude){
				commUtil.alertError("品牌资质其他资质要求不能为空");
				return;
			}
			var imgPaths = [];
			$("input[name='picName']").each(function(){
				imgPaths.push($(this).val());
			});
			if(imgPaths.length>0){
				$("#imgPaths").val(imgPaths.join(","));
			}
			$("#form1").submit();
        }
        
        $(function(){
        	
            
        });
 
        function delPicImg(obj) {
        	$(obj).parent().parent().remove();
        }
        
        function addPicImg(pic) {
       		var str = "<div class='img-box' >"
       			+"<img class='img-pic' alt='' src='${pageContext.request.contextPath}/file_servelt"+ pic +"' >"
       			+"<div class='top-box'><span class='top-delete' onclick='delPicImg(this)'></span></div>"
       			+"<input type='hidden' class='picName' name='picName' value='"+ pic +"'>"
       			+"</div>";
       		$(".img-picImg").append(str);
        }
        
      //图片上传
        function ajaxFileUploadPicFile(statusImg) {
            $.ajaxFileUpload({
            	url: '${pageContext.request.contextPath}/service/common/ajax_upload.shtml?fileType=1',
        		secureuri: false,
        		fileElementId: statusImg,
        		dataType: 'json',
        		success: function(result, status) {
        			if(result.RESULT_CODE == 0) {
        				if(statusImg == 'picImg') {
        					addPicImg(result.FILE_PATH);
        				}
        			} else {
        				commUtil.alertError(result.RESULT_MESSAGE);
        			}
        		},
        		error: function(result, status, e) {
        			commUtil.alertError("服务异常！");
        		}
        	});
        }        
</script>
<html>
	<body style="padding:50px">
	<form name="form1" action="${pageContext.request.contextPath}/service/prod/product_type/saveBrandAptitude.shtml" method="post" id="form1" enctype="multipart/form-data">
		<input name="id" type="hidden" id="id" value="${id}" />
		<input name="imgPaths" type="hidden" id="imgPaths"/>
		<table  class="gridtable"  >
			<tr >
               <td  class="title" width="20%">品牌资质其他资质要求<span class="required">*</span></td>
               <c:if test="${isView == 0}">
	               <td align="left" class="l-table-edit-td">
	               		<textarea name="brandAptitude" id="brandAptitude" style="width:150px;height:300px;visibility:hidden;"><%=htmlspecialchars(htmlData)%>${brandAptitude}</textarea>
	               </td>
               </c:if>
               <c:if test="${isView == 1}">
	               <td align="left" class="l-table-edit-td">
	               		<textarea name="brandAptitude" id="brandAptitude" style="width:150px;height:300px;visibility:hidden;"><%=htmlspecialchars(htmlData)%>${brandAptitude}</textarea>
	               </td>
               </c:if>
            </tr>
            <c:if test="${isView == 0}">
         	<tr>
               <td class="title" width="20%">资质示例图片</td>
               <td align="left" class="l-table-edit-td" id="picsTd">
	            	<div class="img-div img-picImg">
	            	<c:forEach items="${aptitudePicList}" var="aptitudePic" >
		            	<div class="img-box">
			            	<img class="img-pic" alt="" src="${pageContext.request.contextPath}/file_servelt${aptitudePic.pic}" onclick="viewerPic(this.src);">
			            	<div class="top-box"><span class="top-delete" onclick="delPicImg(this)"></span></div>
			            	<input type="hidden" class="picName" name="picName" value="${aptitudePic.pic}">
		            	</div>
	            	</c:forEach>
		            </div>
	           		<div id="add-picImg" class="add-box">
	           			<input id="picImg" class="file-btn" type="file" name="file" onchange="ajaxFileUploadPicFile('picImg');" />
						<span class="add-span" >+</span>
					</div>
               </td>
           </tr>
           <tr> 
           		<td class="title" width="20%">操作</td>	           
	           	<td colspan="2"> 
					<input name="btnSubmit" type="button" id="Button1" class="l-button l-button-submit" value="提交" onclick="submit_fun();" /> 
		            <input type="button" value="取消" class="l-button l-button-submit" onclick="frameElement.dialog.close()"/>
	      		</td>
       	   </tr>
       	   </c:if>
		</table> 
		
	</form>
	</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>