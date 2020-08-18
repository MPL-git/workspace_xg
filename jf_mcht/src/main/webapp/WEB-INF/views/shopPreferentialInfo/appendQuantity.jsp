<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>增加优惠券发行量</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" />
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/validate.jf.css" />
    <style type="text/css">
        .vm {
            margin: 0 3px 0 0;
        }
    </style>
</head>

<body>

<div class="modal-dialog" role="document" style="width:500px;height:500px">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

            <span class="modal-title" id="exampleModalLabel">增加优惠券发行量</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
                <input type="hidden" id="id" name="id" value="${coupon.id}">
                <input type="hidden" id="coupon" name="coupon" value="${coupon}">
                <input type="hidden" id="preferentialType" name="preferentialType" value="${preferentialType}">
                <input type="hidden" id="type" name="type" value="${type}">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>



                        <tr>
                            <td class="editfield-label-td">当前发行量<span class="required">*</span></td>
                            <td colspan="2" class="text-left">
                                <span>${coupon.grantQuantity }张</span>
                            </td>
                        </tr>



                        <tr>
                            <td class="editfield-label-td">新增发行量<span class="required">*</span></td>
                            <td colspan="2" class="text-left">
                                <input type="text" id="grantQuantity" onchange="addGrantQuantity()" validate="{required:true,number:true,min:1,max:999999999}" placeholder="请输入正整数" validate="{required:true,digits:true}"  name="grantQuantity"  style="width: 200px;">张
                            </td>
                        </tr>

                        <tr>
                            <td class="editfield-label-td">增加后发行量</td>
                            <td colspan="2" class="text-left" id="newAdd">

                            </td>
                        </tr>




                        </tbody>
                    </table>
                </div>
            </form>

            <div class="modal-footer">
                <button class="btn btn-info" onclick="commitSave();" >提交</button>
                <button class="btn btn-info" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>

    <!-- 	查看信息框 -->
    <div class="modal fade" id="myViewModal" tabindex="-1" role="dialogA" aria-labelledby="viewModalLabelB" data-backdrop="static">
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/utils.js"></script>
<script type="text/javascript">

    var addId = [];
    var added = [];
    var notAdd = [];


    var dataFromValidate
    $(document).ready(function () {


        if("${productList}"!=null){
            $('#content').show();
        }

        $.metadata.setType("attr", "validate");

        dataFromValidate =$("#dataFrom").validate({
            highlight : function(element) {
                $(element).addClass('error');
                $(element).closest('tr').find("td").first().addClass('has-error');
            },
            success : function(label) {
                label.closest('tr').find("td").first().removeClass('has-error');
            }
        });


    });

    function addGrantQuantity(){
        var old = "${coupon.grantQuantity }"
        var add = $("#grantQuantity").val();
        var num = old*1+add*1
        $("#newAdd").html("<span>"+num+"</span>张")
    }







    function commitSave(){

        if(dataFromValidate.form()){
            var list="";
            var paramList = [];
            $('[id=del]').each(function(){
                var productId = $(this).attr('data_value');
                /* var upDate = $(this).attr('upDate');
                var item = {productId: ,upDate:upDate,remarks:$(this).parent().parent().prev().prev().prev().children('textarea').val()}; */
                paramList.push(productId);
            });


            var dataJson = $("#dataFrom").serializeArray();
            dataJson.push({"name":"paramList","value":JSON.stringify(paramList)});

            $.ajax({
                method: 'POST',
                url: '${ctx}/shopPreferentialInfo/addGrantQuantitys',
                data: dataJson,
                dataType: 'json',
                cache : false,
                async : false,
                timeout : 30000,
                success:function(data){
                    if(data.returnCode == '0000'){
                        swal("提交成功");
                        $("#viewModal").modal('hide');
                        var type = $("#type").val();
                        if(type=="2"){
                            table2.ajax.reload();
                        }
                        if(type=="3"){
                            table3.ajax.reload();
                        }
                    }else{
                        swal({
                            title: data.returnMsg,
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }
                },
                error:function(){
                    swal('请求失败');
                }
            });




        }


    }






</script>
</body>
</html>
