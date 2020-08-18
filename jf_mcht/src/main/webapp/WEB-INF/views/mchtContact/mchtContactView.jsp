<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>查看联系人</title>
</head>

<body>
<!--查看品牌 -->
  <div class="modal-dialog" role="document">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		<span class="modal-title" id="exampleModalLabel">查看联系人</span>
	  </div>
		<div class="modal-body">
			<div class="table-responsive">
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>姓名</td>
							<td class="text-left">
								${mchtContact.contactName }
							</td>
						</tr>

						<tr>
							<td>职责</td>
							<td class="text-left">
								${mchtContact.contactTypeDesc}
							</td>
						</tr>

						<tr>
							<td>手机号</td>
							<td class="text-left">
								${mchtContact.mobile }
							</td>
						</tr>

						<tr>
							<td>座机号</td>
							<td class="text-left">
								${mchtContact.tel }
							</td>
						</tr>

						<tr>
							<td>QQ号</td>
							<td class="text-left">
								${mchtContact.qq }
							</td>
						</tr>

						<tr>
							<td>邮箱</td>
							<td class="text-left">
								${mchtContact.email }
							</td>
						</tr>

						<tr>
							<td>通讯地址</td>
							<td class="text-left">
								${mchtContact.provinceName }${mchtContact.cityName }${mchtContact.countyName }${mchtContact.address }
							</td>
						</tr>

						<tr>
							<td class="text-left" colspan="2"><a href="javascript:mchtContactEdit(${mchtProductBrand.id})" >【修改联系人】</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
  </div>
  
  <script type="text/javascript">
  
	function mchtContactEdit(){
		
		$.ajax({
			url: "${ctx}/mchtContact/mchtContactEdit?id=${mchtContact.id}", 
			type: "GET", 
			success: function(data){
				$("#viewModal").html(data);
			},
			error:function(){
				swal('页面不存在');
			}
		});
	}
	
	
	
	
	
  </script>
  
  
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
	<!-- Bootstrap -->

</body>
</html>
