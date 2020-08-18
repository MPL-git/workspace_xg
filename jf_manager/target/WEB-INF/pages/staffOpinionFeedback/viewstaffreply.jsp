<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

<style type="text/css">
body {font-size: 12px;padding: 10px;}
td input,td select{border:1px solid #AECAF0;}
.radioClass{margin: 0 10px 0 10px;}
.l-table-edit-td {padding: 4px;}
.l-button-submit,.l-button-test {width: 80px;float: left;margin-left: 10px;padding-bottom: 2px;}
.l-text-wrapper {display: inline-block;}
.upload_image_list li a{display:none !important}
</style>
<script type="text/javascript">
var viewerPictures;
var pics="${replyPicList}".replace('[','').replace(']','').replace(/, /g,',').split(',');

function viewerPic(imagePath){
  	$("#viewer-pictures").empty();
	viewerPictures.destroy();
 	$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+imagePath+'" src="${pageContext.request.contextPath}/file_servelt'+imagePath+'" alt=""></li>');
 	viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	}});
 	$("#viewer-pictures").show();
	viewerPictures.show();
}

  $(function() {
	 viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	  }});
	
	$("img").bind('click',function(){
		var imagePath = $(this).attr("path");
		viewerPic(imagePath);
		
	 });
	 
});


     //获取放大图片
	 function staffreplyPic(staffrePlyid, src) {
		var url = "${pageContext.request.contextPath}/staffreply/staffreplyPicList.shtml";
		viewerFlag = true;
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {staffrePlyid : staffrePlyid},
			timeout : 30000,
			success : function(data) {
				if(data && data.length > 0){
					var ind = 0;
					for (var i=0;i<data.length;i++) {
						if(data[i].replyPic == src) {
							ind = i;
						}
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].replyPic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].replyPic+'" alt=""></li>');
					}
					viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {
						url: 'data-original',
						hide: function(){
							$("#viewer-pictures").hide();
						},
						viewed: function() {
							if(viewerFlag) {
								viewerPictures.view(ind);
								viewerFlag = false;
							}
						}
					});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
	 }


 <c:if test="${not empty staffReplycustomlist}">
 $(function(){
	 var listConfig = $("#maingrid").ligerGrid({
	     columns: [
	     {display:'回复内容', align:'center', width:210,render:function(rowdata, rowindex){
	      var html = [];
	      var str = rowdata.replyContent;
	      if (str) {
	      html.push("<div style='margin-left: 5px;'>"+str+"</div>");		
		 }
	       return html.join("");
	     }},
	     {display: '回复图片',width:410,render:function(rowdata, rowindex){
	     var html = [];
	     if(rowdata.staffReplyPic) {
			var staffReplyPic = rowdata.staffReplyPic.split(",");
				for(var i=0;i<staffReplyPic.length;i++) {
					html.push("<img style='margin-left: 5px;margin-bottom: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+staffReplyPic[i]+"' width='60' height='60' onclick='staffreplyPic("+rowdata.id+", \""+staffReplyPic[i]+"\")'>");
			}
		  }else{
		    html.push("<div style='margin-left: 5px;'>暂无回复图片~~~</div>");
		  }
	      return html.join("");
	     }},
	    {display: '回复人', name: 'staffName', align:'center'},
	    {display:'回复时间',name:'', align:'center',render:function(rowdata, rowindex) {
			var html = [];
			if(rowdata.createDate != null && rowdata.createDate != '' ) {
			var createDate = new Date(rowdata.createDate);
			html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
			}
			return html.join("");
		}},	
        ],pageSize:5,pageSizeOptions:[5,10,20,50,100],
         url:'${pageContext.request.contextPath}/staffreply/viewstaffreplydata.shtml?replyid=${replyid}',
         width: '100%',
         onAfterShowData: function() { 
				$(".l-grid-row-cell-inner").css("height", "auto");
					var i = 0;
	               $("tr",".l-grid2","#"+listModel.container.listGridName+"").each(function() {
                      $($("tr",".l-grid1","#"+listModel.container.listGridName+"")[i]).css("height",$(this).height());
 						i++;
 				});												
		    } 
								
     });
	 
  }); 
 </c:if>


</script>
<html>
<body>
	<form method="post" id="form1" name="form1" action="">
	<input id="id" name="id" type="hidden" value="${staffOpinionFeedback.id}"/>
	<table class="gridtable">
	       <tr>
				<td colspan="1" class="title">反馈内容<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="10" cols="100" disabled="disabled" name="feedbackContent" placeholder="请输入1-500个字~" validate="{required:true,maxlength:500}">${staffOpinionFeedback.feedbackContent}</textarea>
				</td>
		   </tr>			
		
		<c:if test="${not empty staffOpinionFeedbackPicList}">
		 <tr>
			<td colspan="1" class="title">反馈图片</td>
			<td colspan="5" align="left" class="l-table-edit-td">
	    	    <t:imageList name="feedbackPic" list="${staffOpinionFeedbackPicList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt"/>  		    	    	  	   
	        </td>
		 </tr>
       </c:if>
       
       <c:if test="${empty staffOpinionFeedbackPicList}">
		 <tr>
			<td colspan="1" class="title">反馈图片</td>
			<td colspan="5" align="left" class="l-table-edit-td">
	    	   <span>暂无反馈图片~~~</span>  		    	    	  	   
	        </td>
		 </tr>
       </c:if>
         
         
		    <tr>
           	    <td class="title" width="20%">发送给谁<span class="required">*</span></td>
				<td align="left" class="l-table-edit-td" >
              	<select id="organizationId" name="organizationId" style="width: 135px;" disabled="disabled">
						<option value=''>请选择...</option>
						<option value='1' <c:if test="${staffOpinionFeedback.organizationId=='1'}">selected="selected"</c:if>>总经办</option>
						<option value='2' <c:if test="${staffOpinionFeedback.organizationId=='2'}">selected="selected"</c:if>>人事部</option>
						<option value='3' <c:if test="${staffOpinionFeedback.organizationId=='3'}">selected="selected"</c:if>>财务部</option>
						<option value='4' <c:if test="${staffOpinionFeedback.organizationId=='4'}">selected="selected"</c:if>>法务部</option>
						<option value='5' <c:if test="${staffOpinionFeedback.organizationId=='5'}">selected="selected"</c:if>>产品部</option>
						<option value='6' <c:if test="${staffOpinionFeedback.organizationId=='6'}">selected="selected"</c:if>>技术部</option>
						<option value='7' <c:if test="${staffOpinionFeedback.organizationId=='7'}">selected="selected"</c:if>>商品部</option>
						<option value='8' <c:if test="${staffOpinionFeedback.organizationId=='8'}">selected="selected"</c:if>>招商部</option>
						<option value='9' <c:if test="${staffOpinionFeedback.organizationId=='9'}">selected="selected"</c:if>>客服部</option>
						<option value='10' <c:if test="${staffOpinionFeedback.organizationId=='10'}">selected="selected"</c:if>>策划部</option>
						<option value='11' <c:if test="${staffOpinionFeedback.organizationId=='11'}">selected="selected"</c:if>>设计部</option>
						<option value='12' <c:if test="${staffOpinionFeedback.organizationId=='12'}">selected="selected"</c:if>>文案部</option>
						<option value='13' <c:if test="${staffOpinionFeedback.organizationId=='13'}">selected="selected"</c:if>>推广部</option>
						<option value='14' <c:if test="${staffOpinionFeedback.organizationId=='14'}">selected="selected"</c:if>>其他</option>
						<%-- <c:forEach var="list" items="${sysOrganizationList}">
							<option value="${list.orgId}" <c:if test="${list.orgId==staffOpinionFeedback.organizationId}">selected="selected"</c:if>>${list.orgName}</option>
						</c:forEach> --%>					
				</select>
                </td>
           	</tr>
           	
           	 <tr>
           	     <td class="title" width="20%">创建时间</td> 
				 <td align="left" class="l-table-edit-td" >
                <div class="l-panel-search-item" >
					 <fmt:formatDate value="${staffOpinionFeedback.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				 </div>
                </td>             
           	</tr>
			
		  <c:if test="${not empty staffReplycustomlist}">
			 <tr>
				<td  colspan="1" class="title">回复&nbsp;&nbsp;</td>
				<td  colspan="5" align="left" class="l-table-edit-td">
                    <span>详见以下回复内容~~~</span> 
			    </td>
			</tr> 
          </c:if>
           
           <c:if test="${empty staffReplycustomlist}">
			 <tr>
				<td  colspan="1" class="title">回复&nbsp;&nbsp;</td>
				<td  colspan="5" align="left" class="l-table-edit-td">
                    <span>暂无回复内容~~~</span>
			    </td>
			 </tr> 
           </c:if>
             
		</table>
		<div id="maingrid" style="margin: 0; padding: 0;"></div>
		</form>	
		<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
