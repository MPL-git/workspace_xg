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
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <span class="modal-title" id="exampleModalLabel">上传合同</span>
        </div>
        <div class="modal-body">
            <c:if test="${contract.auditStatus eq 4}">
      			<font color="red">驳回原因：${contract.auditRemarks}</font>
      			<br>
      			<br>
      		</c:if>  
            <form id="dataFrom">
            	<input type="hidden" name="id" id="id" value="${contract.id}">
                <div class="table-responsive">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <td class="editfield-label-td">合同</td>
                            <td colspan="2" class="text-left">
                                <div class="pic-container">
                                    <ul class="docs-pictures clearfix td-pictures pictures-list" id="pictures-list" ondrop="drop(event)" ondragover="allowDrop(event)">
                                        <c:forEach var="mchtContractPic" items="${mchtContractPicList}" varStatus="varStatus">
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
                        </tbody>
                    </table>
                </div>
            </form>
			<div>备注：合同盖章要求：请将合同打印下来，用黑色签字笔在首页和尾页按要求填写相关信息后加盖公章，并盖骑缝章（保证章印清楚）；合同上传要求：扫描后按照页码顺序上传，保证图片清晰，内容不模糊<a href="javascript:;" onclick="toForExample();">【盖章示范】</a></div>
            <div class="modal-footer">
                <c:if test="${contract.auditStatus==1 || contract.auditStatus==4}">
                    <button class="btn btn-info" onclick="commitSave();">提交</button>
                </c:if>
                <button class="btn btn-info" data-dismiss="modal">取消</button>
            </div>

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

    });

	function toForExample(){
		$.ajax({
	        url: "${ctx}/mcht/contract/toForExample", 
	        type: "GET", 
	        success: function(data){
	            $("#viewModal").html(data);
	            $("#viewModal").modal();
	        },
		    error:function(){
		    	swal('页面不存在');
		    }
		});
	}

    function commitSave() {
        if (dataFromValidate.form()) {

            imageUploader.upload(function(){
                // 限制图片数量
                if($("#pictures-list").children("li").length==0){
                    swal({
                        title: '请上传图片',
                        type: "error",
                        confirmButtonText: "确定"
                    });
                    return;
                }

                var images=[];//图片
                $("#pictures-list").children("li").each(function(index,element){
                    var pic={};
                    pic.pic=$(element).attr("pic_path");
                    images.push(pic);
                });

                var dataJson = $("#dataFrom").serializeArray();
                dataJson.push({"name":"images","value":JSON.stringify(images)});

                $.ajax({
                    url: "${ctx}/mcht/contract/contractPicUpload",
                    type : 'POST',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    data : dataJson,
                    timeout : 30000,
                    success: function (result) {
                        if (result.success) {
                            //$("#viewModal").modal('hide');
                            refreshContract();
                            swal({
                                title: "提交成功!",
                                type: "success",
                                confirmButtonText: "确定"

                            });

                            //getContent("${ctx}/mcht/contract");
                        } else {
                            swal({
                                title: result.returnMsg,
                                type: "error",
                                confirmButtonText: "确定"
                            });
                        }

                    },
                    error : function() {
                        swal({
                            title: "提交失败！",
                            type: "error",
                            confirmButtonText: "确定"
                        });
                    }
                });
            });


        }

    }



    function refreshContract() {
         $('#viewModal').on('hidden.bs.modal', function (e) {
            getContent("${ctx}/mcht/contract/renewIndex");
        })

        $("#viewModal").modal('hide');
    }
</script>
</body>
</html>
