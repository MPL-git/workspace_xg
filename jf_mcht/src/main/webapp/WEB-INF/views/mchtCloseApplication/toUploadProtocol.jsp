<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>上传合同扫描件</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/validate.jf.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/viewer.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/webuploader.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/imageUploader.css"/>

    <style type="text/css">
        .td-pictures li{
            display: inline-block;
        }
        .td-pictures li img{
            width: 150px;
            height: 200px;
        }
    </style>
</head>

<body>

<div class="modal-dialog" role="document" style="width:900px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">上传文件</span>
        </div>
        <div class="modal-body">
            <form id="dataFrom">
            	<input type="hidden" name="mchtCloseId" id="mchtCloseId" value="${mchtCloseId}">
            	<input type="hidden" name="needUpload" id="needUpload" value="${mca.isNeedUpload}">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <c:if test="${mchtInfo.isManageSelf == 1}">
                            <tr>
                                <td class="editfield-label-td">是否上传</td>
                                <td>
                                    <span  style="float: left"><input class="radioItem" type="radio" value="1" onclick="selectPropType();" selected name="isNeedUpload" <c:if test="${mca.isNeedUpload eq '1'}">checked="checked"</c:if>>需上传</span>
                                    <span  style="float: left;margin-left: 30px;"><input class="radioItem" type="radio" value="0" onclick="selectPropType();" name="isNeedUpload" <c:if test="${mca.isNeedUpload eq '0'}">checked="checked"</c:if>>无需上传</span>
                                </td>
                            </tr>
                        </c:if>


                        <tr id="uploadFile">
                            <td class="editfield-label-td" style="width:50px">上传文件</td>
                            <td colspan="2" class="text-left"  style="width:300px">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list" id="pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
                                        <c:forEach var="mchtContractPic" items="${mchtCloseApplicationPics}" varStatus="varStatus">
                                            <li id="productPic_li_${varStatus.index}" class="pic_li" draggable="true" ondragstart="drag(event)" pic_path="${mchtContractPic.pic}">
                                                <img  src="${ctx}/file_servelt${mchtContractPic.pic}">
                                                <div class="pic-btn-container" style="height: 0px;">
                                                    <span class="glyphicon glyphicon-remove pic-remove-icon" aria-hidden="true"></span>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <div class="filePicker">选择图片</div>
                                </div>
                            </td>
                        </tr>

                        <tr>
                        	<td class="editfield-label-td" style="width:50px">操作</td>
                        	<td class="editfield-label-td" >
                        	
                        	<c:if test="${mca.contractAuditStatus == 0  || mca.contractAuditStatus ==3 }">
                        	  <a class="btn btn-info" onclick="commitSave();" style="float:left">提交</a>
                        	 </c:if>
                    		<button class="btn btn-info" data-dismiss="modal" style="float:left;margin-left: 35px;">取消</button>
                        	</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript" src="${ctx}/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.metadata.js"></script>
<script type="text/javascript" src="${ctx}/static/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.validate.jf.js"></script>
<script type="text/javascript" src="${ctx}/static/js/webuploader.js"></script>
<script type="text/javascript" src="${ctx}/static/js/viewer.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/imageUpload.js" ></script>

<script type="text/javascript">
function drag(ev)
{   
ev.dataTransfer.setData("Text",$(ev.target).parent().attr("id"));
}
function drop(ev)
{
ev.preventDefault();
var data=ev.dataTransfer.getData("Text");
if($(ev.target).prop("tagName")=="UL"){
	$(ev.target).append(document.getElementById(data));
}
if($(ev.target).prop("tagName")=="IMG"){
 $(ev.target).parent().before(document.getElementById(data));
}

}
function allowDrop(ev)
{
ev.preventDefault();
}
    var dataFromValidate;
    var imageUploader;
    $(function () {
        imageUploader = new ImageUploader();
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

        //判断是否显示图片上传
        var needUpload = $("#needUpload").val();
        if(needUpload == "0"){
            $("#uploadFile").hide();
        }else {
            $("#uploadFile").show();
        }

    });

    function commitSave() {
        if (dataFromValidate.form()) {

            var isNeedUpload= $("input[name='isNeedUpload']:checked").val();
            console.log(isNeedUpload);
            imageUploader.upload(function(){
                // 限制图片数量
                if(isNeedUpload == 1){


                    if($("#pictures-list").children("li").length==0){
                        swal({
                            title: '请上传图片',
                            type: "error",
                            confirmButtonText: "确定"
                        });
                        return;
                    }
                }

                var images=[];//图片
                $("#pictures-list").children("li").each(function(index,element){
                    var pic={};
                    pic.pic=$(element).attr("pic_path");
                    images.push(pic);
                });

                var dataJson = $("#dataFrom").serializeArray();
                dataJson.push({"name":"images","value":JSON.stringify(images)});
                dataJson.push({"name":"isNeedUpload","value":JSON.stringify(isNeedUpload)});

                $.ajax({
                    url: "${ctx}/mchtCloseApplication/toSaveUploadProtocol",
                    type : 'POST',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    data : dataJson,
                    timeout : 30000
                }).done(function (result) {
                	  if (result.code=='0000') {
    		        	submitting = false;
    		           	swal("操作成功");
    		           	$("#viewModal").modal('hide');
    		           	$(".modal-backdrop").hide();
    		           	refreshContract();
    		        }else{
    		        	swal(result.returnMsg);
    		        	submitting = false;
    		        }
    		    });
            });
        }
    }

     function refreshContract() {
         $('#viewModal').on('hidden.bs.modal', function (e) {
            getContent("${ctx}/mchtCloseApplication/index");
        })

        $("#viewModal").modal('hide');
    };


    //选择是否需要上传图片
    function selectPropType() {
        var isNeedUpload = $("input[type=radio][name='isNeedUpload']:checked").val();
        if(isNeedUpload == "0"){
            $("#uploadFile").hide();
        }else {
            $("#uploadFile").show();
        }
    }
</script>
</body>
</html>
