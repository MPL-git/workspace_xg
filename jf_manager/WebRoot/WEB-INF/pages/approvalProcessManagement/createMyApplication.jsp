<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String htmlData = request.getParameter("NOTICE_CONTENT") != null ? request.getParameter("NOTICE_CONTENT") : "";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerRadioList.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/themes/default/default.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css"/>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/kindeditor.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.base64.js" type="text/javascript"></script>


    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/upload_image_list.css" rel="stylesheet" type="text/css"/>

    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">


    <style>
        .upload_image_list {
            font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '宋体', sans-serif;
        }
    </style>
    <script type="text/javascript">
        var logoPic_viewer;
        var viewerPictures;
        var logoPic_viewer;
        var viewerPictures;
        var dataFromValidate;
        var mchtBrandInvoicePic_viewer;


        var editor1;
        KindEditor.ready(function (K) {
            editor1 = K.create('textarea[name="content"]', {
                cssPath: '${pageContext.request.contextPath}/kindeditor-4.1.7/plugins/code/prettify.css',
                uploadJson: '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/upload_json.jsp',
                fileManagerJson: '${pageContext.request.contextPath}/kindeditor-4.1.7/jsp/file_manager_json.jsp',
                allowFileManager: true,
                afterCreate: function () {
                }

            });
            prettyPrint();
        });


        $(function () {
            mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});

            $.metadata.setType("attr", "validate");
            dataFromValidate = $("#form1").validate({
                rules: {
                    name: {
                        required: true,
                        maxlength: 30,
                    },
                    procedureId: {
                        required: true,
                    },
                },
                messages: {
                    name: {
                        required: "不可为空",
                        maxlength: "最多输入30个字",
                    },
                    procedureId: {
                        required: "不可为空",
                    },
                },
                errorPlacement: function (lable, element) {
                    if ($(element).attr('name') == 'name') {
                        $("#name").ligerTip({
                            content: lable.html()
                        });
                    } else {
                        lable.appendTo(element.parent());
                    }
                    ;
                    if ($(element).attr('name') == 'procedureId') {
                        $("#procedureId").ligerTip({
                            content: lable.html()
                        });
                    } else {
                        lable.appendTo(element.parent());
                    }

                },
                success: function (lable) {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function (form) {
                    parent.location.reload();
                    form.submit();
                }
            });

        })


        //限制上传附件大小
        /* 		function ajaxFileUploadattachment(obj) {
                    var file = obj.files[0];
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        var filesize = file.size;
                            if(filesize>1024*1024*10) {
                                commUtil.alertError("附件总大小不超过10M!");
                            }else {
                                ajaxFileUploads(obj);
                            }
                    },
                    reader.readAsDataURL(file);
                } */


        function ajaxFileUploads(obj) {
            var file = obj.files[0];
            var fileName = file.name;
            $("#attachmentName").val(fileName);
            $("#attachmentNameDesc").text(fileName);
            $("#fileDown").text("下载");
            var reader = new FileReader();
            $("#uploading").val(1);
            reader.onload = function (e) {
                $.ajaxFileUpload({
                    url: '${pageContext.request.contextPath}/service/common/fileUpload.shtml?fileType=8',
                    secureuri: false,
                    fileElementId: "uploadFile",
                    dataType: 'json',
                    success: function (result, status) {
                        if (result.RESULT_CODE == 0) {
                            var filePath = result.FILE_PATH;
                            $("#attachmentPath").val(filePath);
                            $("#uploading").val(0);
                            commUtil.alertSuccess("文件上传成功");
                        } else {
                            alert(result.RESULT_MESSAGE);
                        }
                    },
                    error: function (e) {
                        alert("服务异常");
                    }
                });
            };
            reader.readAsDataURL(file);
        }


        //获取节点和抄送人
        function getNodeAndCopies() {
            var procedureId = $("#procedureId").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/approvalProcessManagement/getNodeAndCopies.shtml",
                type: 'POST',
                dataType: 'json',
                cache: false,
                async: false,
                data: {procedureId: procedureId},
                timeout: 30000,
                success: function (data) {
                    if ("0000" == data.returnCode) {
                        $("#copies").html("");
                        $("#applicationNode").html("");
                        var staffNameList = data.staffNameList;//抄送人
                        var procedureNodeList = data.procedureNodeList;//节点
                        /*审批人  */
                        for (var i = 0; i < staffNameList.length; i++) {
                            var staffName = staffNameList[i].staffName;
                            $("#copies").append("&nbsp;<span>" + staffName + ",</span> ");
                        }
                        /*节点  */
                        for (var i = 0; i < procedureNodeList.length; i++) {
                            var procedureNodeName = procedureNodeList[i].name;
                            var procedureNodeType = procedureNodeList[i].type;
                            if (procedureNodeType == 0 || procedureNodeType == 1) {
                                $("#applicationNode").append("&nbsp;<input type='checkbox' checked='checked' disabled>" + procedureNodeName + "");
                            } else {
                                $("#applicationNode").append("&nbsp;<input type='checkbox'  disabled>" + procedureNodeName + "");
                            }
                            ;
                        }
                    } else {
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error: function () {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        //图片格式验证
        function ajaxFileUploadImg(_this) {
            var file = document.getElementById(_this.id).files[0];
            if (!/image\/(PNG|png|JPG|jpg|JPEG|jpeg)$/.test(file.type)) {
                commUtil.alertError("图片格式不正确！");
                return;
            }

            var count = 1;
            $("#aaa img").each(function () {
                count += 1;
            });

            if (count > 9) {
                commUtil.alertError("最多只能上传9张图片");
                return
            }

            if (file.size > 10 * 1024 * 1024) {
                commUtil.alertError("图片大小不能超过10M");
                return;
            }

            var reader = new FileReader();
            reader.onload = function (e) {
                var image = new Image();
                image.onload = function () {
                    ajaxFileUpload(_this);
                };
                image.src = e.target.result;
            }
            reader.readAsDataURL(file);
        }

        function ajaxFileUpload(_this) {
            var id = $(_this).attr("id");
            var data_value = $(_this).attr("data_value");
            $.ajaxFileUpload({
                url: "${pageContext.request.contextPath}/service/common/ajax_upload.shtml",
                secureuri: false,
                fileElementId: id,
                dataType: 'json',
                success: function (result, status) {
                    if (result.RESULT_CODE == 0) {
                        $("#aaa").append('<li><p><img src="${pageContext.request.contextPath}/file_servelt' + result.FILE_PATH + '" path="' + result.FILE_PATH + '"></p><a href="javascript:void(0);" class="del">删除</a></li>');
                        $(".del").live('click', function () {
                            $(this).closest("li").remove();
                        });

                        //对viewer进行重新赋值
                        mchtBrandInvoicePic_viewer.destroy();
                        mchtBrandInvoicePic_viewer = new Viewer(document.getElementById('mchtBrandInvoicePic_viewer'), {});

                    } else {
                        alert(result.RESULT_MESSAGE);
                    }
                },
                error: function (result, status, e) {
                    alert("服务异常");
                }
            });
        }


        //保存
        function save() {
            //图片地址
            var picPaths = [];
            $("#aaa img").each(function () {
                picPath = $(this).attr("path");
                if (picPath != null) {
                    picPaths.push(picPath);
                }
            });

            editor1.sync();

            var NOTICE_CONTENT = $("#NOTICE_CONTENT").val()
            if (NOTICE_CONTENT == '' || NOTICE_CONTENT == null) {
                commUtil.alertError("正文内容不能为空");
                return
            }

            var dataJson = $("#form1").serializeArray();
            dataJson.push({"name": "picPaths", "value": JSON.stringify(picPaths)});

            if (dataFromValidate.form()) {

                $.ajax({
                    url: "${pageContext.request.contextPath}/approvalProcessManagement/saveMyApplication.shtml",
                    type: 'POST',
                    dataType: 'json',
                    cache: false,
                    async: false,
                    data: dataJson,
                    timeout: 30000,
                    success: function (data) {
                        if ("0000" == data.returnCode) {
                            commUtil.alertSuccess("提交成功");
                            parent.location.reload();
                            frameElement.dialog.close();
                        } else {
                            $.ligerDialog.error(data.returnMsg);
                        }

                    },
                    error: function () {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });
            }
        }


        //附加下载
        function downLoadFile() {
            var attachmentPath = $("#fileDown").attr("attachmentpath");
            var attachmentName = $("#fileDown").attr("attachmentname");
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/imPeach/checkFileExists.shtml',
                data: {attachmentPath: attachmentPath},
                dataType: 'json',
                success: function (data) {
                    if (data == null || data.code != 200) {
                        commUtil.alertError(data.msg);
                    } else {
                        location.href = "${pageContext.request.contextPath}/imPeach/downLoadAttachment.shtml?fileName=" + attachmentName + "&filePath=" + attachmentPath;
                    }
                },
                error: function (e) {
                    commUtil.alertError("系统异常请稍后再试！");
                }
            });
        }

        function auditView(LogId) {
            $.ligerDialog.open({
                height: 800,
                width: 900,
                title: "查看",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/approvalProcessManagement/auditView.shtml?LogId=" + LogId,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }


    </script>

</head>
<body style="margin: 10px;">
<form method="post" id="form1" name="form1">
    <input type="hidden" id="attachmentName" name="attachmentName" value=""/>
    <input type="hidden" id="attachmentPath" name="attachmentPath" value=""/>
    <input type="hidden" id="approvalApplicationId" name="approvalApplicationId" value="${approvalApplication.id}"/>
    <input type="hidden" id="uploading" value="0"/>
    <input type="hidden" id="id" name="id" value="${customerServiceOrderCustom.id}"/>
    <table class="gridtable">
        <tr>
            <td class="title" width="20%">流程名称<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <select id="procedureId" name="procedureId" onchange="getNodeAndCopies()" style="width:360px;"
                        validate="{required:true}">
                    <option value="">请选择</option>
                    <c:forEach var="procedure" items="${procedureList}">
                        <option value="${procedure.id}"
                                <c:if test="${procedure.id==approvalApplication.procedureId}">selected="selected"</c:if> >${procedure.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td class="title" width="20%" style="margin-left: 20px;">标题名称<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input style="width:360px;" type="text" id="name" name="name" value="${approvalApplication.name }"
                       validate="{required:true,maxlength:30}"/>
            </td>
        </tr>


        <tr>
            <td class="title">正文<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <textarea name="content" id="NOTICE_CONTENT"
                          style="width:150px;height:300px;visibility:hidden;">${approvalApplication.content}</textarea>
            </td>
        </tr>


        <tr>
            <td colspan="1" class="title">图片</td>
            <td colspan="3" align="left" class="l-table-edit-td" id="mchtBrandInvoicePic_viewer">
                <t:imageList name="mchtBrandInvoicePictures" list="${mchtBrandInvoicePics}" def="1"
                             prefixPath="${pageContext.request.contextPath}/file_servelt"/>

                <ul id="aaa" name="mchtPlatformAuthPictures" class="upload_image_list">
                    <c:forEach var="approvalApplication" items="${approvalApplicationPicList}">
                        <li><p><img src="${pageContext.request.contextPath}/file_servelt${approvalApplication.pic}"
                                    path="${approvalApplication.pic }"></p><a href="javascript:void(0);"
                                                                              class="del">删除</a></li>
                    </c:forEach>
                </ul>
                <div style="float: left;height: 105px;margin: 10px;">
                    <input style="position:absolute; opacity:0;width: 110px;" type="file" id="BrandInvoicePictures"
                           data_value="mchtBrandInvoicePic_viewer" name="file" onchange="ajaxFileUploadImg(this);"/>
                    <input type="button" style="width: 70px;" value="上传图片"/>
                    <br><span style="color:#999;">（只能上传JPG/PNG格式图片，图片不能超过10Mb)</span>
                </div>
            </td>
        </tr>


        <tr>
            <td class="title" width="20%">链接</td>
            <td align="left" class="l-table-edit-td">
                <input style="width:360px;" type="text" placeholder=" http:// " id="linkUrl" name="linkUrl"
                       value="${approvalApplication.linkUrl }"/>
            </td>
        </tr>


        <tr>
            <td class="title">附件</td>
            <td align="left" class="l-table-edit-td">
                <span id="attachmentNameDesc">${approvalApplication.enclosureName }</span> &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:downLoadFile();" id="fileDown" attachmentname="${approvalApplication.enclosureName}"
                   attachmentpath="${approvalApplication.filePath}"></a>

                <br><input style="position:absolute; opacity:0;" type="file" id="uploadFile" name="uploadFile"
                           onchange="ajaxFileUploads(this);"/>
                <input type="button" style="width: 45px;" value="上传"><br>

            </td>
        </tr>


        <tr>
            <td class="title">审批节点</td>
            <td align="left" class="l-table-edit-td" id="applicationNode">
                <c:forEach var="procedureNode" items="${procedureNodeList }">
                    &nbsp;<input type='checkbox' <c:if
                        test="${procedureNode.type==0 || procedureNode.type==1 }">checked</c:if> disabled>${procedureNode.name }
                </c:forEach>
            </td>
        </tr>


        <tr>
            <td class="title">抄送人</td>
            <td align="left" class="l-table-edit-td" id="copies">
                <c:forEach var="staff" items="${staffNameList }">
                    &nbsp;<span>${staff.staffName},</span>
                </c:forEach>
            </td>
        </tr>


        <tr>
            <td class="title" width="20%">操作</td>
            <td align="left" class="l-table-edit-td">
                <input class="l-button l-button-submit" value="提交" onclick="save()"/>
                <input style="margin-left: 20px;" class="l-button" type="button" value="关闭"
                       onclick="frameElement.dialog.close()"/>
            </td>
        </tr>
    </table>

<%--
    <c:forEach items="${approvalApplicationLogs}" var="approvalApplicationLog">
--%>
        <c:if test="${recode}">
            <br/>
            <div>
                <span class="table-title">审核记录</span>
            </div>
            <table class="gridtable">
                <tr align="center">
                    <td class="title">操作人</td>
                    <td class="title">所属部门</td>
                    <td class="title">操作时间</td>
                    <td class="title">操作</td>
                    <td class="title">审批节点</td>
                    <td class="title" width="20%">审批备注</td>
                    <td class="title">审批图片</td>
                </tr>
                <c:forEach items="${approvalApplicationLogs}" var="approvalApplicationLog">
                    <tr align="center">
                        <td>${approvalApplicationLog.createName }</td>
                        <td>${approvalApplicationLog.department }</td>
                        <td><fmt:formatDate value="${approvalApplicationLog.createDate}"
                                            pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>${approvalApplicationLog.operation }</td>
                        <td>${approvalApplicationLog.approvalNode }</td>
                        <td width="20%">${approvalApplicationLog.approvalRemarks }</td>
                        <td>
                            <c:if test="${not  empty approvalApplicationLog.approvalPic && tag !=''}">
                                <a name='${approvalApplicationLog.operation }'
                                   href="javascript:auditView(${approvalApplicationLog.id});">查看</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
<%--
    </c:forEach>
--%>
</form>
</body>
</html>
<%!
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
%>