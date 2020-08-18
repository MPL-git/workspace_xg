<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>查看举报</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/imageUploader.css"/>

<style type="text/css">
    .td-pictures li{
        display: inline-block;
    }
    .td-pictures li img{
        width: 40px;
        height: 40px;
    }

    .td_title{
        font-weight: normal;
        background-color: #cccccc;
        height:22px;
        border-bottom: 0 !important;
    }

    .text-right{
        width:150px;
    }
</style>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 910px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">查看举报</span>
      </div>
      <div class="modal-body">
      
      <div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					
					<tr>
					<span>举报信息</span>
					</tr>
					<tr>
						<td class="editfield-label-td">项目</td>
						<td colspan="2" class="text-left">
						<span>内容</span>
						</td>
					</tr>
				
					<tr>
						<td class="editfield-label-td">举报类型</td>
						<td colspan="2" class="text-left">
						<span>${type}</span>
						</td>
					</tr>

					<tr>
						<td class="editfield-label-td">举报场景</td>
						<td colspan="2" class="text-left">
						<span>${scene}</span>
						</td>
					</tr>
					
					
				    <tr>
						<td class="editfield-label-td">相关订单</td>
						<td colspan="2" class="text-left">
						<c:forEach var="subOrderCode" items="${merchantReport.subOrderCodes}">
						<span>${subOrderCode}</span><br>	                          
	                    </c:forEach>
						</td>
					</tr>
					
					
					
					<tr>
						<td class="editfield-label-td">联系人</td>
						<td colspan="2" class="text-left">
						<span>${merchantReport.name}</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">联系电话</td>
						<td colspan="2" class="text-left">
	   					<span>${merchantReport.mobile}</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">举报说明</td>
						<td colspan="2" class="text-left">
							<span>${merchantReport.description}</span>
						</td>
					</tr>
					
					
					<tr>
						<td class="editfield-label-td">举报凭证</td>
						<td colspan="2" class="text-left">
                          <table id="attachmentTbody">
						<c:forEach var="impeachMemberProof" items="${impeachMemberProofs}">
							<tr>
							<td class="text-left"><a href="javascript:;" >${impeachMemberProof.fileName }</a></td>						
							<td class="text-left"><a href="javascript:;" id="${impeachMemberProof.id}" name="downLoadimpeachMemberProof"  data-filename="${impeachMemberProof.fileName }" data-filepath="${impeachMemberProof.filePath }">[下载]</a></td>							
							</tr>
						</c:forEach>	   
						</table>		
						</td>
					</tr>
			</table>
				
				
			<c:if test="${merchantReport.status==3 }">	
			<span>平台回复</span>	
			<table class="table table-bordered ">	
					<tr>
						<td class="editfield-label-td">状态<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<span>驳回</span>
							
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">平台说明</td>
						<td colspan="2" class="text-left">
						<span>${rejectReason}</span>	
						</td>
					</tr>
			</table> 
			</c:if>
			
			<c:if test="${merchantReport.status==7}">
			<span>平台回复</span>
			<table class="table table-bordered ">	
					<tr>
						<td class="editfield-label-td">状态<span class="required">*</span></td>
						<td colspan="2" class="text-left">
						<span>通过</span>
						</td>
					</tr>
					
					<tr>
						<td class="editfield-label-td">平台说明</td>
						<td colspan="2" class="text-left">
						<span>${caseCloseDesc}</span>	
						</td>
					</tr>
			</table>
		</div>
		</c:if>
      
      
    </div>
    </div>
  </div>
  
  <script type="text/javascript">
  
	function brandPerfect(){
		
		$.ajax({
	        url: "${ctx}/mcht/mchtBrandPerfect?mchtBrandId=${mchtProductBrand.id}", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}
	function brandChgApply(){
		
		$.ajax({
	        url: "${ctx}/mcht/mchtBrandChgApply?mchtBrandId=${mchtProductBrand.id}", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}
	
	$(function(){
		
		 $("a[name='downLoadimpeachMemberProof']").on('click',function(){ 
				var fileName = $(this).data("filename");
				var filePath = $(this).data("filepath");
				location.href="${ctx}/merchantReport/downLoadImpeachMemberProof?fileName="+fileName+"&filePath="+filePath;
	          });
		
		
	});

  
  </script>
  <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/imageUpload.js" ></script>
  
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
