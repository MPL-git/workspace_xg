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
/* .upload_image_list li a{display:none !important} */
</style>
<script type="text/javascript">
var viewerPictures;
var pics="${replyPics}".replace('[','').replace(']','').replace(/, /g,',').split(',');


  $(function() {
	 viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
		$("#viewer-pictures").hide();
	  }});
	  		   
});



     //获取放大回复图片
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



    //删除回复数据
	function viewDetail(id) {
	    var title="删除";
	    $.ligerDialog.confirm("是否要"+title+"？", function (yes){
	    if (yes) {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/staffreply/updatestaffreplydata.shtml',
			cache : false,
			async : false,
			data: {id : id},
			dataType: 'json',
			timeout : 30000,
			success: function(data) {
				if("0000" == data.returnCode) {
				  $("#maingrid").ligerGetGridManager().reload();
				}else {
					$.ligerDialog.error(data.returnMsg);
				}
			},
			error: function(e) {
				$.ligerDialog.error('系统异常，请稍后再试！');;
			}
		});
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
	    {display:'操作',name:'', align:'center',render: function(rowdata, rowindex) {
		  var html = [];
		  if (rowdata.createBy==${sessionScope.USER_SESSION.staffID}) {
		      html.push("<a href='javascript:;' onclick='viewDetail(" + rowdata.id + ");'>删除</a></br>");														
		  }
		  return html.join("");
		}},
        ],pageSize:5,pageSizeOptions:[5,10,20,50,100],
         url:'${pageContext.request.contextPath}/staffreply/staffreplydata.shtml?replyid=${replyid}',
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


    //图片格式验证
	function ajaxFileUploadImg(statusImg) {
		var file = document.getElementById(statusImg).files[0];
        if(!/image\/(JPG|jpg|JPEG|jpeg|png)$/.test(file.type)) {  
        	commUtil.alertError("图片格式不正确！");
            return;
        }
        var reader = new FileReader();  
        reader.onload = function(e) { 
        	var image = new Image();
        	image.onload = function() {
        	var filesize = file.size;
        		if(filesize>1024*1024*10) {
        			commUtil.alertError("单张大小不超过10M");
            	}else {
        			ajaxFileUpload(statusImg);
        		}
            };
            image.src = e.target.result;
        },
        reader.readAsDataURL(file);
	}

 function ajaxFileUpload(statusImg) {
	$.ajaxFileUpload({
		url:contextPath + '/service/common/ajax_upload.shtml',
		secureuri: false,
		fileElementId: "replyPic",
		dataType: 'json',
		success: function(result, status) {
			if(result.RESULT_CODE == 0) {
			   uploadImageList.addImage(contextPath + "/file_servelt", result.FILE_PATH);
			   		     
			} else {
				alert(result.RESULT_MESSAGE);
			}
		},
		error: function(result, status, e) {
			alert("服务异常");
		}
	});	
}

  $(function(){		
	$.metadata.setType("attr", "validate");
		$("#form1").validate({//表单验证
	        errorPlacement: function (lable, element) {
	         var name =  $("#replyContent").val();
	         if ($.trim(name)=='') {
				element.ligerTip({ content: lable.html("回复内容不能为空!")}); 
			  }else{
	        		$(element).ligerTip({
						content : lable.html("回复内容不能超过500个字!"),width: 100
					});	        	
			    }  
	        },
	        submitHandler: function(form) {        	   
	        	var pictures = [];
    		    var lis = $(".upload_image_list").find("li");
    		    lis.each(function(index, item) {
    			var path = $("img", item).attr("path");
    			var def = ($(".def", item).length == 1 ? "1" : "0");
    			var pic = {picPath: path, isPrimary: def, picOrder: index + 1};
    			pictures.push(pic);
    		});
    		if(pictures.length >0 && pictures.length<=5){
    			$("#replyPics").val(JSON.stringify(pictures));  		   
    		}else if(pictures.length >5){
    			commUtil.alertError("最多上传5张图片！");
	   		    return;
    		}
	          form.submit();
	        }
	    }); 
		
	});
     
     //获取放大反馈图片   
	 function vieWerPic(feedbackcontentid, src) {
		var url = "${pageContext.request.contextPath}/staffOpinionFeedback/staffopinionfeedbackpicPicList.shtml";
		viewerFlag = true;
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {feedbackcontentid : feedbackcontentid},
			timeout : 30000,
			success : function(data) {
				if(data && data.length > 0){
					var ind = 0;
					for (var i=0;i<data.length;i++) {
						if(data[i].feedbackContentPic == src) {

							ind = i;
						}
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].feedbackContentPic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].feedbackContentPic+'" alt=""></li>');
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
</script>
<html>
<body>
	<form  method="post" id="form1" name="form1" action="${pageContext.request.contextPath}/staffreply/addstaffreply.shtml">
	<input id="id" name="id" type="hidden" value="${staffOpinionFeedback.id}"/>
	<table class="gridtable">
	       <tr>
				<td colspan="1" class="title">反馈内容<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="10" cols="100" name="feedbackContent" disabled="disabled">${staffOpinionFeedback.feedbackContent}</textarea>
				</td>
		   </tr>			
		
		<c:if test="${not empty staffOpinionFeedbackPicList}">
		 <tr>
			<td colspan="1" class="title">反馈图片</td>
			<td colspan="5" align="left" class="l-table-edit-td">
	    	   <c:forEach var="staffOpinionFeedbackPicList" items="${staffOpinionFeedbackPicList}">
	    	         <div style="position: relative;display:inline-block;margin-left: 5px;width: 100px;height: 80px;"><img style="width: 100%;height: 100%;display: block;" alt="" src="${pageContext.request.contextPath}/file_servelt${staffOpinionFeedbackPicList.feedbackContentPic}" onclick="vieWerPic('${staffOpinionFeedbackPicList.feedbackContentId}','${staffOpinionFeedbackPicList.feedbackContentPic}')"></div>
	    	   </c:forEach> 	    		    	    	  	   
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
           	
           	<tr>
				<td colspan="1" class="title">回复内容<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<textarea style="background:transparent;" rows="10" cols="100" id="replyContent" name="replyContent" placeholder="请输入1-500个字~" validate="{required:true,maxlength:500}"></textarea>
				</td>
		   </tr>
		   
		  <tr>
			<td colspan="1" class="title">回复图片</td>
			<td colspan="5" align="left" class="l-table-edit-td">
	    		<t:imageList name="pictures" list="${replyPicList}" def="1" prefixPath="${pageContext.request.contextPath}/file_servelt" />
	    		<div style="float: left;height: 105px;margin: 10px;">
	    		<input style="position:absolute; opacity:0;" type="file" id="replyPic" name="file" onchange="ajaxFileUploadImg('replyPic');" />
					<div class="l-icon l-icon-up" style="display:inline-block;"></div>
					<a href="javascript:void(0);" style="width:30%;">上传图片</a>
					<span style="color:#CC0000;">(提示：最多上传5张,单张大小不超过10M)</span>	               
	    		</div>
	    		<input id="replyPics" name="replyPics" type="hidden" value="">	    	   
	        </td>
		  </tr>
				
			<tr>
				<td colspan="1" class="title">操作<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="btnDiv">					
						<input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="回复"/>				
						<input type="button" value="关闭" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />
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
