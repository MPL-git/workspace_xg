<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
      <link href="${pageContext.request.contextPath}/static/css/viewer.min.css" rel="stylesheet" type="text/css" />
      <link href="${pageContext.request.contextPath}/static/css/imageUploader.css" rel="stylesheet" type="text/css" />
          <script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>	

<title>查看寄件内容</title>
<style type="text/css">
td img{
display: inline-block;
width: 60px;
height: 40px;
}
</style>

</head>

<body>

  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 910px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">查看寄件内容</span>
      </div>
		<div class="modal-body">
	      <div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>				
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfoChg.settledType eq 1}">法人身份证</c:if><c:if test="${mchtInfoChg.settledType eq 2}">经营者身份证</c:if></td>
						<td colspan="2" class="text-left">
							<img  src="${ctx}/file_servelt${mchtInfoChg.corporationIdcardImg1}">
							<img  src="${ctx}/file_servelt${mchtInfoChg.corporationIdcardImg2}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfoChg.settledType eq 1}">营业执照副本</c:if><c:if test="${mchtInfoChg.settledType eq 2}">营业执照</c:if></td>
						<td colspan="2" class="text-left">
							<img  src="${ctx}/file_servelt${mchtInfoChg.blPic}">
						</td>
					</tr>
					<c:if test="${mchtInfoChg.settledType eq 1}">
					<tr>
						<td class="editfield-label-td">组织机构代码证</td>
						<td colspan="2" class="text-left">
							<img  src="${ctx}/file_servelt${mchtInfoChg.occPic}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">税务登记证</td>
						<td colspan="2" class="text-left">
							<img  src="${ctx}/file_servelt${mchtInfoChg.trcPic}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">一般纳税人资格证</td>
						<td colspan="2" class="text-left">
							<img  src="${ctx}/file_servelt${mchtInfoChg.atqPic}">
						</td>
					</tr>
					</c:if>
					<tr>
						<td class="editfield-label-td"><c:if test="${mchtInfoChg.settledType eq 1}">银行开户许可证</c:if><c:if test="${mchtInfoChg.settledType eq 2}">经营者银行卡信息</c:if></td>
						<td colspan="2" class="text-left">
							<img  src="${ctx}/file_servelt${mchtInfoChg.boaalPic}">
						</td>
					</tr>				
				</tbody>
			</table>
		</div>
		
		
			</div>
	</div>
  </div>



</body>
</html>
