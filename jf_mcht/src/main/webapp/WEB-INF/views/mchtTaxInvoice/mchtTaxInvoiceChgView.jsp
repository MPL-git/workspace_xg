<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>申请更新税票信息</title>

</head>

<body>

  <div class="modal-dialog" role="document" style="width:700px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <span class="modal-title" id="exampleModalLabel">申请更新税票信息</span>
      </div>
			<div class="modal-body">
		<div class="table-responsive">
			<table class="table table-bordered ">
				<tbody>
					<tr>
						<td class="editfield-label-td">公司名称</td>
						<td colspan="2" class="text-left">
						<span>${sessionScope.mchtInfo.companyName}</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">纳税类型</td>
						<td colspan="2" class="text-left">
						<span>${taxTypeDesc }</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">纳税人识别号</td>
						<td colspan="2" class="text-left">
						<span>${mchtTaxInvoiceInfoChg.taxNumber }</span>
						</td>
					</tr>
										<tr>
						<td class="editfield-label-td">地址</td>
						<td colspan="2" class="text-left">
						<span>${mchtTaxInvoiceInfoChg.address }</span>
						</td>
					</tr>
										<tr>
						<td class="editfield-label-td">电话</td>
						<td colspan="2" class="text-left">
						<span>${mchtTaxInvoiceInfoChg.tel }</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">开户行</td>
						<td colspan="2" class="text-left">
						<span>${mchtTaxInvoiceInfoChg.depositBank }</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">帐号</td>
						<td colspan="2" class="text-left">
						<span>${mchtTaxInvoiceInfoChg.accountNumber }</span>
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">确认函</td>
						<td colspan="2" class="text-left">
						<img style="width:60px;height:40px;"  src="${ctx}/file_servelt${mchtTaxInvoiceInfoChg.confirmFile}">
						</td>
					</tr>
					<tr>
						<td class="editfield-label-td">状态</td>
						<td colspan="2" class="text-left">
						<span>${auditStatusDesc }</span>
						</td>
					</tr>
					<c:if test="${mchtTaxInvoiceInfoChg.id!=null&&mchtTaxInvoiceInfoChg.auditStatus=='4' }">
					<tr>
						<td class="editfield-label-td">驳回原因</td>
						<td colspan="2" class="text-left">
						<span>${mchtTaxInvoiceInfoChg.auditRemarks }</span>
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
			</div>
	</div>
  </div>

</body>
</html>
