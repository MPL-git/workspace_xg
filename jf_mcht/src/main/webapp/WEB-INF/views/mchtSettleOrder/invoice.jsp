<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>结算单开票</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />

</head>

<body>

<div class="modal-dialog" role="document" style="width:600px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">结算单开票</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
                <input type="hidden" value="${model.id}" name="id">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">开票金额</td>
                            <td colspan="2" class="text-left">
                                ${model.needPayAmount}元
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">开票类型</td>
                            <td colspan="2" class="text-left">
                                ${ptkpMap.get("PTKP_LX")}
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">平台税票信息</td>
                            <td colspan="2" class="text-left">
                                公司名称：${ptkpMap.get("PTKP_MC")}
                                <br/>纳税人识别号：${ptkpMap.get("PTKP_SBH")}
                                <br/>开户行及账号：${ptkpMap.get("PTKP_KHH")}      ${ptkpMap.get("PTKP_ZH")}
                                <br/>地址、电话：${ptkpMap.get("PTKP_DZ")}  ${ptkpMap.get("PTKP_DH")}
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">平台收件地址</td>
                            <td colspan="2" class="text-left">
                                地址：${ptkpMap.get("PTKP_SJDZ")}
                                <br/>电话：${ptkpMap.get("PTKP_LX")}   收件人：${ptkpMap.get("PTKP_SJR")}
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">发票寄出快递运单号</td>
                            <td colspan="2" class="text-left">
                                <select style="width:120px; float:left;" class="form-control" name="expressId" validate="{required:true}">
                                    <option value="">--请选择--</option>
                                    <c:forEach var="express" items="${expressList}">
                                        <option value="${express.id}"
                                                <c:if test="${model.mchtInvoiceExpressId==express.id}">selected="selected"</c:if>>${express.name}</option>
                                    </c:forEach>
                                </select>
                                &nbsp;&nbsp;&nbsp;
                                <input type="text" name="expressNo" value="${model.mchtInvoiceExpressNo}" validate="{required:true}">
                            </td>
                        </tr>


                        </tbody>
                    </table>
                </div>
            </form>

            <div class="modal-footer">
                <button class="btn btn-info mr17" onclick="commitSave();">确认</button>
                <button class="btn btn-info" data-dismiss="modal">取消</button>
            </div>

        </div>
    </div>
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

            $.ajax({
                url: "${ctx}/mchtSettleOrder/invoiceCommit",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: dataJson,
                timeout: 30000,
                success: function (result) {
                    if (result.success) {
                        swal({
                            title: "提交成功!",
                            type: "success",
                            confirmButtonText: "确定"
                        });

                        $('#viewModal').on('hidden.bs.modal', function (e) {
                            getContent("${ctx}/mchtSettleOrder/detail?id=${model.id}")
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

</script>
</body>
</html>
