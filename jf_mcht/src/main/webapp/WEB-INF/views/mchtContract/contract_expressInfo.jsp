<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>合同寄件</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />

</head>

<body>

<div class="modal-dialog" role="document" style="width:600px;">
    <input  type="hidden" id="isManageSelf" value="${mchtInfo.isManageSelf}">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">合同寄件</span>
        </div>

        <div class="modal-body" style="padding:30px;">
            <p style="line-height: 30px;">
               请按《寄件内容》的要求，请将合同原件及所有资质（已盖贵公司公章）寄往：
                <br/>收件人：招商
                <br/>联系电话：4008088227
                <br/>收件地址：厦门市思明区塔埔东路171号1104-A单元
                <br/>寄件之后，请及时提交快递信息
            </p>
            <form id="dataFrom">
                快递
                <select style="display:inline-block; width:120px;" class="form-control" name="expressId" validate="{required:true}">
                    <option value="">--请选择--</option>
                    <c:forEach var="express" items="${expressList}">
                    <option value="${express.id}" <c:if test="${contract.mchtExpressId==express.id}">selected="selected"</c:if>>${express.name}</option>
                    </c:forEach>
                </select>
                &nbsp;&nbsp;&nbsp;
                快递单号
                <input type="text" name="expressNo" value="${contract.mchtExpressNo}" validate="{required:true}">
                	
            </form>
            <br>
            <br>	
             <p style="line-height: 30px;" id="change">
              	您的收件地址<span style="float:right; magin-right:25px;"><a href="javascript:edit('${ctx}/mchtContact/mchtContactEdit?contactType=1&id=${mchtContact.id}&Mailing=1')">【修改】</a></span>
				收 &nbsp;件&nbsp;人：<span id ="contactName">${mchtContact.contactName}</span><br>
                                               联系电话：<span id ="mobile" >${mchtContact.mobile}</span><br>
                                               收件地址：<span id="address">${address}</span><br>
            </p>	
		</div>
        <div class="modal-footer">
            <c:if test="${contract.archiveStatus!=1}">
                <button class="btn btn-info" onclick="commitSave();">提交</button>
            </c:if>
            <button class="btn btn-info" data-dismiss="modal">取消</button>
        </div>

    </div>

</div>
<!-- 	查看信息框 -->
<div class="modal fade" id="myViewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
	 data-backdrop="static">
</div>

<script src="${ctx}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery.validate.jf.js" type="text/javascript"></script>

<script type="text/javascript">

    var dataFromValidate;
    $(function () {
        $.metadata.setType("attr", "validate");
        dataFromValidate = $("#dataFrom").validate({
            highlight: function (element) {
                $(element).addClass('error');
                $(element).closest('tr').find("td").first().addClass('has-error');
            },
            success: function (label) {
                label.closest('tr').find("td").first().removeClass('has-error');
            },
            errorPlacement: function (error, element) {
                error.appendTo($(element).closest('td'));
            }
        });


    });


    function commitSave() {
        if (dataFromValidate.form()) {
            var dataJson = $("#dataFrom").serializeArray();
    		var address = $("#contactName").text();
    		var mobile = $("#mobile").text();
    		var contactName = $("#contactName").text();
            var isManageSelf = $("#isManageSelf").val();

            if(isManageSelf != 1){//不是自营需要检验
                if(address==null || address =='' || mobile==null || mobile =='' || contactName==null || contactName ==''){
                    swal("请完善您的收件信息");
                    return false;
                }
            }

            
            $.ajax({
                url: "${ctx}/mcht/contract/contractExpressInfoCommit",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.returnCode=='0000') {
                        swal({
                            title: "提交成功!",
                            type: "success",
                            confirmButtonText: "确定"
                        });

                        $('#viewModal').on('hidden.bs.modal', function (e) {
                            getContent("${ctx}/mcht/contract")
                        })

                        $("#viewModal").modal('hide');
                    } else {
                        swal({
                            title: result.returnMsg,
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }

                },
                error: function () {
                    swal({
                        title: "提交失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            });
        }

    }

    function edit(url){
		if(url == "")	return;
		$.ajax({
			url: url, 
			type: "GET", 
			success: function(data){
				$("#myViewModal").html(data);
				$("#myViewModal").modal();
			},
			error:function(){
				swal('页面不存在');
			}
		});
	}
    
 </script>
</body>
</html>
