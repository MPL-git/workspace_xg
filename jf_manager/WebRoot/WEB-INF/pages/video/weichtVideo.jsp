<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/common-tag.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
	
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
	
<style type="text/css">
 	.radioClass{
		margin-right: 20px;
	}
 
	.table td {
    border-width: 0px;
    padding: 8px;
    border-style: solid;
    border-color: #666666;
    background-color: #ffffff;
}
 </style>
<script type="text/javascript">

//修改人工值
function manualWeichtVideo(id) {
	var manualWeicht = $("#manualWeicht").val();
	var flag = manualWeicht.match(/^[1-9]\d*$/);
	if(manualWeicht != '' && flag != null && flag<=50) {
		$.ajax({
			 type : 'POST',
			 url : "${pageContext.request.contextPath}/video/updatemanualWeichtVideo.shtml",
			 data : {id : id, manualWeicht : manualWeicht},
			 dataType : 'json',
			 success : function(data){
				 if(data == null || data.statusCode != 200){
					 
					 commUtil.alertError(json.message);
				 }
				 else{
					 commUtil.alertSuccess("保存成功");
				 }
			 },
			 error : function(e) {
				 commUtil.alertError("系统异常请稍后再试");
			 }
		 });
	}else{
		commUtil.alertError("<span style='color:red;'>请输入正确数值且数值不能大于50</span>");
		
	}
}

</script>

</head>
	<body style="margin: 10px;background:#F0F8FF;">
		   	<br>			
				<br>
			    <div class="x_content container-fluid" id="content">
			        <div class="row">
			            <div class="col-md-12 datatable-container">
			                <table border="1" bordercolor=#ddd style="width:90%;margin-left: 5%;height: 100px;" id="trendsTable" >
			                    <thead>
			                    <tr role="row">
			                        <th width="88" style="text-align:center">涉及商品的季节</th>
			                        <th width="88" style="text-align:center">播放量</th>
			                        <th width="68" style="text-align:center">评论量</th>
			                        <th width="68" style="text-align:center">收藏量</th>
			                        <th width="68" style="text-align:center">点赞量</th>
			                        <th width="38" style="text-align:center">人工值</th>
			                    </tr>
                    </thead>
                    <tbody id="tableBody">
                    
      					<c:forEach var="videoCustoms" items="${videoCustoms}">
							<tr>
								<td style="text-align:center">
									${videoCustoms.seasonWeight}
								</td>
								
								<td style="text-align:center">
								   ${videoCustoms.playCount}
								</td>
								
								<td style="text-align:center">
								   ${videoCustoms.commentCount}
								</td>
								
								<td style="text-align:center">
								   ${videoCustoms.collectCount}
								</td>
								   
								<td style="text-align:center">
								   ${videoCustoms.likeCount}
								</td>
								
								<td style="text-align:center">
									<input type="text" id="manualWeicht" name="manualWeicht" value="${videoCustoms.manualWeicht}" maxlength="2" onchange="manualWeichtVideo(${videoCustoms.id})">
								</td>					
							</tr>
						</c:forEach>          	 
		            </tbody>
		                </table>           
		            </div>
		        </div>
		    </div>
		   	<br>
	</body>
</html>