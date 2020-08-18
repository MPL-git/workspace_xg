<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/MD5.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/clipboard.min.js"></script>
 <script type="text/javascript">
 	$(function(){
 		//加密
 		$("#md5Btn").bind("click", function(){
 			var str = $("#md5get").val();
			var strs = str.split("\n");
 			if($("#addMd5").val() == '' && $.trim(str) != '') {
	 			$.each(strs, function(){
	 				if(this != '') {
			 			if($("#addMd5").val() != ''){
			 				$("#addMd5").val($("#addMd5").val()+"\n"+md5(this));
			 			}else{
			 				$("#addMd5").val(md5(this));
			 			}
	 				}
	 			});
	 			$("#md5Btn").val("加密成功");
 			}
 		});
 		
 		//复制
 		$("#md5Copy").bind("click", function(){
 			if($("#addMd5").val() != '') {
	 			var clipboard = new Clipboard("#md5Copy");   
	 			clipboard.on('success', function(e) {  
	 		        console.log(e); 
	 		        $("#md5Copy").val("复制成功");
	 		    });  
	 			clipboard.on('error', function(e) {  
			        console.log(e); 
			        $("#md5Copy").val("复制失败");
			    }); 
 			}
 		});
 		
 		//清除
 		$("#md5Remove").bind("click", function(){
 			$("#md5get").val("");
 			$("#addMd5").val("");
 			$("#md5Btn").val("MD5加密>>");
 			$("#md5Copy").val("复制加密");
 		});
 	})
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div style="margin: 10px;">
		<h1>MD5 32位小写加密</h1>
		<div style="color: red;margin: 5px;">注意：文本框中是以行对应加密，每加密一次需按【清除所有】按钮才可以再次加密。</div>
		<div  style="display: inline-flex; ">
			<div style="border: solid 2px #eee;width: 700px;height: 700px; overflow: auto;" >
				<textarea id="md5get" rows="5000" cols="5000"></textarea>
			</div>
			<div style="margin: 20px;">
				<input type="button" style="width: 100px;height: 25px;cursor: pointer;margin-top: 270px;" value="MD5加密>>" id="md5Btn"><br/>
				<input type="button" style="width: 100px;height: 25px;cursor: pointer;margin-top: 10px;" value="复制加密" id="md5Copy" data-clipboard-action="copy" data-clipboard-target="#addMd5"><br/>
				<input type="button" style="width: 100px;height: 25px;cursor: pointer;margin-top: 10px;" value="清除所有" id="md5Remove">
			</div>
			<div style="border: solid 2px #eee;width: 700px;height: 700px; overflow: auto;">
				<textarea id="addMd5" rows="5000" cols="5000"></textarea>
			</div>
		</div>
	</div>
	
</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>