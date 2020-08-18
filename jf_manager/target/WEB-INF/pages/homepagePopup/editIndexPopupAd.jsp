<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/common/common-tag.jsp" %>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/common/js/jquery/verify.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/js/uploadImageList.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

<style type="text/css">
    body {
        font-size: 12px;
        padding: 10px;
    }

    td input, td select {
        border: 1px solid #AECAF0;
    }

    .radioClass {
        margin: 0 10px 0 10px;
    }

    .l-table-edit-td {
        padding: 4px;
    }

    .l-button-submit, .l-button-test {
        width: 80px;
        float: left;
        margin-left: 10px;
        padding-bottom: 2px;
    }

    .l-text-wrapper {
        display: inline-block;
    }
</style>

<script type="text/javascript">

    var v;
    $(function () {
        var selectGroup = $("#selectGroup").val();
        var linkType = '${indexPopupAdCustom.linkType }';
        var linkValue = '${indexPopupAdCustom.linkContent }';

        selectGroups(selectGroup);

        $.ajax({
            url: "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType=${showType}",
            type: 'POST',
            dataType: 'json',
            cache: false,
            async: false,
            data: "",
            timeout: 30000,
            success: function (data) {
                var linkTypeLists = data.linkTypeList
                for (var i = 0; i < linkTypeLists.length; i++) {
                    var id = linkTypeLists[i].linkType
                    var name = linkTypeLists[i].linkTypeName
                    if (id == linkType) {
                        $("#linkType").append('<option value="' + id + '" selected >' + name + '</option>')
                    } else {
                        $("#linkType").append('<option value="' + id + '" >' + name + '</option>')
                    }

                }
            },
            error: function () {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        });

        $("#linkType").change(function () {
            var linkType = $("#linkType").val();
            radioItem(linkType, "");
        })

        if (linkType != '') {
            radioItem(linkType, linkValue);
        } else {
            radioItem('1', '');

        }


        $("#upDate").ligerDateEditor({
            showTime: true,
            width: 158,
            format: "yyyy-MM-dd hh:mm:ss"
        });
        $("#downDate").ligerDateEditor({
            showTime: true,
            width: 158,
            format: "yyyy-MM-dd hh:mm:ss"
        });


        <c:if test="${indexPopupAdCustom.linkType=='5'}">
        /* document.getElementById("linkContent3").style.display="";
        document.getElementById("linkContent1").style.display="none";
        document.getElementById("linkContent2").style.display="none"; */
        $("#linkContent3").show();
        $("#linkContent1").hide();
        /* $("#linkContent2").hide(); */

        </c:if>

        <%--/* <c:if test="${indexPopupAdCustom.linkType=='6'}">--%>
        <%--   document.getElementById("linkContent2").style.display="";--%>
        <%--   document.getElementById("linkContent3").style.display="none";--%>
        <%--   document.getElementById("linkContent1").style.display="none";--%>
        <%--   $("#linkContent2").show();--%>
        <%--   $("#linkContent3").hide();--%>
        <%--   $("#linkContent1").hide();--%>

        <%--</c:if> */--%>


        $.metadata.setType("attr", "validate");
        v = $("#form1").validate({
            errorPlacement: function (lable, element) {
                var elementType = $(element).attr("type");
                if ($(element).hasClass("l-text-field")) {
                    $(element).parent().ligerTip({
                        content: lable.html(), width: 100
                    });
                } else if ('radio' == elementType) {
                    var radioName = $(element).attr("name");
                    $("input[type=radio][name=" + radioName + "]:last").ligerTip({
                        content: lable.html(), width: 100
                    });
                } else {
                    $(element).ligerTip({
                        content: lable.html(), width: 100
                    });
                }
            },
            success: function (lable, element) {
                lable.ligerHideTip();
                lable.remove();
            }

        });
    });

    function radioItem(linkType, linkValue) {
        var html = [];
        html.push('<input type="radio"  name="popupCount" value="1" onchange="days(1);" />终生一次&nbsp;');
        html.push('<input type="radio"  name="popupCount" value="2" onchange="days(2);" />启动即弹&nbsp;');
        html.push('<input type="radio"  name="popupCount" value="3" onchange="days(3);" />每天一次&nbsp;');
        html.push('<input type="radio"  name="popupCount" value="4" onchange="days(4);" />自定义&nbsp;<input type="text" id="day" name="day" style="width: 36px;" />&nbsp;天一次');
        if (linkType != 29 && !linkValue) {
            $("#popupCountTd").html(html.join(""));
        }
        switch (linkType) {
            case '1':
                $("#show1-1").html('会场ID<span class="required">*</span>');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent" placeholder="请输入会场ID"  value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
                break;
            case '2':
                $("#show1-1").html('活动ID<span class="required">*</span>');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent" placeholder="请输入活动ID"  value="' + linkValue + '" validate="{digits:true,maxlength:9}"/>');
                break;
            case '3':
                $("#show1-1").html('商品ID<span class="required">*</span>');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent" placeholder="请输入商品ID"  value="' + linkValue + '" validate="{digits:true,maxlength:12}"/>');
                break;
            case '4':
                $("#show1-1").html('URL<span class="required">*</span>');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent" placeholder="请输入URL连接"  value="' + linkValue + '" validate="{maxlength:120}"/>');
                break;
            case '5':
                $("#show1-1").html('URL');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent" placeholder="无连接"    value="无链接" />');
                break;
            case '6':
                if (linkValue == '')
                    linkValue = 1;
                $("#show1-1").html('<span>频道<span class="required">*</span></span>');
                var linkIStr = '<select id="linkContent" name="linkContent" style="width: 160px;">';
                $.ajax({
                    url: "${pageContext.request.contextPath}/linkType/adInfo/getLinkTypeList.shtml?showType=" + 14,
                    type: 'POST',
                    dataType: 'json',
                    cache: false,
                    async: false,
                    data: "",
                    timeout: 30000,
                    success: function (data) {
                        var linkValueList = data.linkValueList
                        for (var i = 0; i < linkValueList.length; i++) {
                            var id = linkValueList[i].linkValue
                            var name = linkValueList[i].linkValueName
                            if (id == linkValue) {
                                linkIStr += '<option  value="' + id + '"   selected>' + name + '</option>'
                            } else {
                                linkIStr += '<option  value="' + id + '"  >' + name + '</option>'
                            }

                        }
                    },
                    error: function () {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });

                linkIStr += '</select>'
                $("#show1-2").html(linkIStr);
                break;
            case '7':
                $("#show1-1").html('自建页面ID<span class="required">*</span>');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent" value="' + linkValue + '" placeholder="请输入自建页面ID" validate="{digits:true,maxlength:9}"/>');
                break;
            case '8':
                $("#show1-1").html('淘宝优选关键字<span class="required">*</span>');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent"  value="' + linkValue + '"   placeholder="请输入淘宝优选关键字" validate="{digits:true,maxlength:9}"/>');
                break;
            case '9':
                $("#show1-1").html('<span>新品牌团<span class="required">*</span></span>');
                linkIStr = '<select name="linkContent" id="linkContent" style="width: 160px;"><option value="">请选择</option>';
            <c:forEach items="${brandteamTypes}" var="brandteamType">
                var _selected = "";
                var brandteamTypeId = ${brandteamType.id};
                if (linkValue == brandteamTypeId) {
                    _selected = "selected";
                }
                linkIStr += '<option value="${brandteamType.id}"  ' + _selected + '>${brandteamType.name}</option>';
            </c:forEach>
                linkIStr += '</select>';
                $("#show1-2").html(linkIStr);
                break;
            case '10':
                $("#show1-1").html('商家店铺<span class="required">*</span>');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent"  placeholder="请输入商家序号" value="' + linkValue + '" validate="{maxlength:12}" />');
                break;
            case '11':

                $("#show1-1").html('<span>一级分类<span class="required">*</span></span>');
                linkIStr = '<select name="linkContent" id="linkContent" style="width: 160px;"><option value="">请选择</option>';
            <c:forEach items="${productTypes}" var="productType">
                var _selected = "";
                var productTypeid = ${productType.id};
                if (linkValue == productTypeid) {
                    _selected = "selected";
                }
                linkIStr += '<option value="${productType.id}" ' + _selected + '>${productType.name}</option>';
            </c:forEach>
                linkIStr += '</select>';
                $("#show1-2").html(linkIStr);
                break;
            case '28':
                $("#show1-1").html('<span>商城一级分类<span class="required">*</span></span>');
                linkIStr = '<select name="linkContent" id="linkContent" style="width: 160px;"><option value="">请选择</option>';
            <c:forEach items="${mallCategorys}" var="mallCategory">
                var _selected = "";
                var productTypeid = ${mallCategory.id};
                if (linkValue == productTypeid) {
                    _selected = "selected";
                }
                linkIStr += '<option value="${mallCategory.id}" style="width:" 160px" ' + _selected + '>${mallCategory.categoryName}</option>';
            </c:forEach>
                linkIStr += '</select>';
                $("#show1-2").html(linkIStr);
                break;
            case '29':
                $("#show1-1").html('优惠券ID<span class="required">*</span>');
                $("#show1-2").html('<input type="text" id="linkContent" name="linkContent" value="' + linkValue + '" placeholder="请输入优惠券ID" validate="{maxlength:120}" style="width:300px;"/>');
                $("#popupCountTd").html('<input type="radio" name="popupCount" checked value="5"/>领取停止（启用即弹）');
                break;
        }
    }

    //图片格式验证
    function ajaxPicFileUploadImg(statusImg) {
        var file = document.getElementById(statusImg).files[0];
        if (!/image\/(PNG|png)$/.test(file.type)) {
            commUtil.alertError("图片格式不正确！");
            return;
        }
        var size = file.size;
        if (size > 200 * 1024) {
            commUtil.alertError("图片过大，请重新上传！");
            return;
        }
        var reader = new FileReader();
        reader.onload = function (e) {
            var image = new Image();
            image.onload = function () {
                if (this.width != '660' || this.height != '660') {
                    commUtil.alertError("图片像素不是660x660像素！");
                } else {
                    ajaxFileUploadLogo(statusImg);
                }
            };
            image.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }

    function ajaxFileUploadLogo() {
        $.ajaxFileUpload({
            url: contextPath + '/service/common/ajax_upload.shtml',
            secureuri: false,
            fileElementId: "logoPicFile",
            dataType: 'json',
            success: function (result, status) {
                if (result.RESULT_CODE == 0) {
                    $("#logoPic").attr("src", contextPath + "/file_servelt" + result.FILE_PATH);
                    $("#pic").val(result.FILE_PATH);
                } else {
                    alert(result.RESULT_MESSAGE);
                }
            },
            error: function (result, status, e) {
                alert("服务异常");
            }
        });

    }

    function days(str) {
        if (str == '4') {
            document.getElementById("day").disabled = "";

        } else {
            $("#day").val("");
            document.getElementById("day").disabled = "disabled";
        }
    }

    function linkTypes() {
        var linktype = $('select option:selected').val();
        if (linktype == '0') {
            /* document.getElementById("linkContent3").style.display="";
            document.getElementById("linkContent1").style.display="none";
            document.getElementById("linkContent2").style.display="none";  */
            $("#linkContent3").show();
            /* $("#linkContent1").hide(); */
            /* $("#linkContent2").hide(); */
            $("[name='linkContent']").val("");
            /* $("[name='linkContent4']").val(""); */
            /* $("[name='linkContent5']").val(""); */

        }/* else if (linktype=='6') {
		   document.getElementById("linkContent2").style.display="";
		   document.getElementById("linkContent3").style.display="none";
		   document.getElementById("linkContent1").style.display="none";
		   $("#linkContent2").show();
		   $("#linkContent3").hide();
		   $("#linkContent1").hide();
		   $("[name='linkContent']").val("");
		   $("[name='linkContent4']").val("");
		   $("[name='linkContent5']").val("");
	 
	 } */ else if (linktype != '0') {
            /* document.getElementById("linkContent1").style.display="";
            document.getElementById("linkContent2").style.display="none";
            document.getElementById("linkContent3").style.display="none";  */
            $("#linkContent1").show();
            /* $("#linkContent2").hide(); */
            $("#linkContent3").hide();
            $("[name='linkContent']").val("");
            /* $("[name='linkContent4']").val(""); */
            $("[name='linkContent5']").val("");

        }

    }

    function Linkcontent5() {
        var linkContent5 = $("#linkContent5").val();
        if (linkContent5 == '') {
            $("[name='linkContent']").val("");
            /* $("[name='linkContent4']").val(""); */
        }

    }

    function commitSave() {

        var pic = document.getElementById("pic");
        var popupDesc = document.getElementById("popupDesc");
        var linkType = document.getElementById("linkType");
        var linkContent = document.getElementById("linkContent");
        /* var linkContent4=document.getElementById("linkContent4"); */
        /* var linkContent5=document.getElementById("linkContent5"); */
        var popupCount = $('input:radio[name="popupCount"]:checked').val();
        var day = document.getElementById("day");
        var upDate = document.getElementById("upDate");
        var downDate = document.getElementById("downDate");

        if ($.trim(popupDesc.value) == "") {
            commUtil.alertError("弹窗说明不能为空！");
            return;
        }
        if ($.trim(pic.value) == "") {
            commUtil.alertError("请上传图片。");
            return;
        }
        if ($.trim(linkType.value) == "") {
            commUtil.alertError("请选择链接类型。");
            return;
        }
        switch (linkType.value) {
            case '1':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入会场ID");
                    return;
                }
                break;
            case '2':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入活动ID");
                    return;
                }
                break;
            case '3':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入商品ID");
                    return;
                }
                break;
            case '4':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入URL");
                    return;
                }
                break;
            case '5':
                break;
            case '6':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入频道");
                    return;
                }
                break;
            case '7':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入自建页面ID");
                    return;
                }
                break;
            case '8':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入淘宝优选关键字");
                    return;
                }
                break;
            case '9':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入新品牌团");
                    return;
                }
                break;
            case '10':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入商家店铺");
                    return;
                }
                break;
            case '11':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请选择一级分类");
                    return;
                }
                break;
            case '28':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请选择商城一级分类");
                    return;
                }
                break;
            case '29':
                if ($.trim(linkContent.value) == "") {
                    commUtil.alertError("请输入优惠券ID");
                    return;
                }
                break;
        }
        // if($.trim(linkContent.value)==""  ){
        // 	commUtil.alertError("详情不能为空。");
        // 	return;
        // }
        if ($.trim(popupCount) == "") {
            commUtil.alertError("弹窗次数不能为空！");
            return;
        }
        if ($.trim(popupCount) == "4" && $.trim(day.value) == "") {
            commUtil.alertError("自定义天数不能为空！！");
            return;
        }
        if ($.trim(popupCount) == "4" && !/^[1-9]\d*$/.test($.trim(day.value))) {
            commUtil.alertError("自定义天数只能输入整数！");
            return;
        }
        if ($.trim(upDate.value) == "") {
            commUtil.alertError("自动上架时间不能为空。");
            return;
        }
        if ($.trim(downDate.value) == "") {
            commUtil.alertError("自动下架时间不能为空。");
            return;
        }
        if ($.trim(upDate.value) >= $.trim(downDate.value)) {
            commUtil.alertError("自动上架时间必须小于自动下架时间");
            return;
        }
        var downDate = new Date(downDate.value);
        var nowDate = new Date();
        var dateDiff = downDate.getTime() - nowDate.getTime();
        if (dateDiff <= 0) {
            commUtil.alertError("自动下架时间必须大于当前时间");
            return;
        }


        if (v.form()) {
            var dataJson = $("#form1").serializeArray();

            $.ajax({
                method: 'POST',
                url: '${pageContext.request.contextPath}/homepagePopup/saveIndexPopupAd.shtml',
                data: dataJson,
                dataType: 'json',
                cache: false,
                async: false,
                timeout: 30000,
                success: function (data) {
                    if (data.returnCode == '0000') {
                        commUtil.alertSuccess("保存成功");
                        setTimeout(function () {
                            parent.location.reload();
                            frameElement.dialog.close();
                        }, 1000);
                    } else {
                        commUtil.alertError(data.returnMsg);
                    }
                },
                error: function () {
                    commUtil.alertError("请求失败");
                }
            });

        }


    };

    function commit() {
        var dataJson = $("#form1").serializeArray();
        post("${pageContext.request.contextPath}/homepagePopup/selectGroupList.shtml", dataJson);
    };

    function post(url, params) {
        // 创建form元素
        var temp_form = document.createElement("form");
        // 设置form属性
        temp_form.action = url;
        temp_form.target = "_self";
        temp_form.method = "post";
        temp_form.style.display = "none";
        // 处理需要传递的参数
        for (var x in params) {
            var opt = document.createElement("textarea");
            opt.name = params[x].name;
            opt.value = params[x].value;
            temp_form.appendChild(opt);
        }
        document.body.appendChild(temp_form);
        // 提交表单
        temp_form.submit();
    }

    function deleteGroup(id) {
        // console.log(id);
        var selectGroup = $("#selectGroup").val();
        var split = selectGroup.split(",");
        var ids = "";
        for (var i = 0; i < split.length; i++) {
            if (split[i] != id) {
                if (ids === "") {
                    ids += split[i];
                } else {
                    ids += "," + split[i];
                }
            }
        }
        $("#selectGroup").empty()
        $("#selectGroup").attr("value", ids)
        selectGroups(ids);
    }

    function selectGroups(selectGroup) {
        $.ajax({
            url: "${pageContext.request.contextPath}/homepagePopup/setGroups.shtml",
            type: 'POST',
            dataType: 'json',
            cache: false,
            async: false,
            data: {"selectGroup": selectGroup},
            timeout: 30000,
            success: function (data) {
                $("#groups").empty();
                var selectGroup = $("#selectGroup").val();
                if (data.groups != null && data.groups != "") {
                    for (var i = 0; i < data.groups.length; i++) {
                        var updateDate = new Date(data.groups[i].updateDate);
                        var formatDate1 = updateDate.format("yyyy-MM-dd hh:mm");
                        var deleteStr = "[删除]";
                        var id = data.groups[i].id;

                        var html = "";
                        html += '<tr>';
                        html += '<td ><a href="javascript:;" style="color: red">' + data.groups[i].labelGroupName + '</a></td>';
                        html += '<td class="w_h_100_36"><a href="javascript:;" style="color: red">' + formatDate1 + '</a></td>';
                        html += '<td><span class="btnDelete" style="color: red" onclick="deleteGroup(' + id + ')" >' + deleteStr + '</span></td>';
                        html += '</tr>';
                        $("#groups").append(html)
                    }
                }
            },
            error: function () {
                $.ligerDialog.error('操作超时，请稍后再试！');
            }
        })
    }

    // $("#groups").click( '.btnDelete', function () {
    // 	var index = $("#groups").find("tr").index($(this).closest('tr'));
    // 	$(this).closest('tr').remove();
    // 	fileS.splice(index,1);
    // });

    /* function Linkcontent4(){
           var linkContent4 = $("#linkContent4").val();
           if (linkContent4=='') {
               $("[name='linkContent']").val("");
               $("[name='linkContent5']").val("");
          }

    } */
</script>

<html>
<body>
<span></span>
<form method="post" id="form1" name="form1"
      action="${pageContext.request.contextPath}/homepagePopup/saveIndexPopupAd.shtml">
    <input type="hidden" id="id" name="id" value="${indexPopupAdCustom.id}"/>
    <input type="hidden" id="status" name="status" value="${indexPopupAdCustom.status}"/>
    <input type="hidden" id="indexPopupAdCustom" name="indexPopupAdCustom" value="${indexPopupAdCustom}"/>
    <table class="gridtable">
        <tr>
            <td colspan="1" class="title">弹窗说明<span class="required">*</span></td>
            <td colspan="5" align="left" class="l-table-edit-td">
                <input type="text" id="popupDesc" name="popupDesc" value="${indexPopupAdCustom.popupDesc}"
                       style="width: 59%;"/>
            </td>
        </tr>
        <tr>
            <td class="title" width="20%">展示图片<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <div><img id="logoPic" style="width: 300px;height: 150px" alt=""
                          src="${pageContext.request.contextPath}/file_servelt${indexPopupAdCustom.pic}"></div>
                <div style="float: left;height: 30px;">
                    <input style="position:absolute; opacity:0;" type="file" id="logoPicFile" name="file"
                           onchange="ajaxPicFileUploadImg('logoPicFile');"/>
                    <a href="javascript:void(0);" style="width:30%;">上传图片</a>
                    <span style="color: gray;">(图片尺寸不超过660*660px，大小不超过200kb)</span>
                </div>
                <input id="pic" name="pic" type="hidden" value="${indexPopupAdCustom.pic}">
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">链接类型<span class="required">*</span></td>
            <td colspan="5" align="left" class="l-table-edit-td">
                <select id="linkType" name="linkType" style="width: 150px;" onchange="linkTypes()">
                    <option value="">请选择</option>
                    <%--   	<option value="1" <c:if test="${indexPopupAdCustom.linkType=='1'}">selected</c:if>>会场ID</option>
                       <option value="2" <c:if test="${indexPopupAdCustom.linkType=='2'}">selected</c:if>>特卖ID</option>
                       <option value="3" <c:if test="${indexPopupAdCustom.linkType=='3'}">selected</c:if>>商品ID</option>
                       <option value="4" <c:if test="${indexPopupAdCustom.linkType=='4'}">selected</c:if>>自建页面ID</option>
                       <option value="5" <c:if test="${indexPopupAdCustom.linkType=='5'}">selected</c:if>>栏目</option>
                       <option value="6" <c:if test="${indexPopupAdCustom.linkType=='6'}">selected</c:if>>一级分类</option>
                       <option value="7" <c:if test="${indexPopupAdCustom.linkType=='7'}">selected</c:if>>URL链接</option>
               </select> --%>
            </td>
        </tr>

        <tr id="show1">
            <td id="show1-1" colspan="1" class="title"></td>
            <td id="show1-2" colspan="5" align="left" class="l-table-edit-td"></td>
        </tr>

        <%-- <tr id="linkContent1">
           <td colspan="1" class="title">详情<span class="required">*</span></td>
           <td colspan="5"  align="left" class="l-table-edit-td">
               <input type="text" id="linkContent" name="linkContent" value="${indexPopupAdCustom.linkContent}"/>
           </td>
       </tr> --%>
        <%-- <tr id="linkContent2" style="display:none">
            <td colspan="1" class="title">详情<span class="required">*</span></td>
            <td colspan="5" align="left" class="l-table-edit-td">
            <select id="linkContent4" name="linkContent4" style="width: 135px;" onchange="Linkcontent4()">
                <option value="" >请选择</option>
                <c:forEach var="productTypeList" items="${productTypes}">
                  <c:if test="${indexPopupAdCustom.linkType ne '7'}">
                  <option value="${productTypeList.id}" <c:if test="${indexPopupAdCustom.linkContent==productTypeList.id}">selected</c:if>>${productTypeList.name}</option>
                  </c:if>
                </c:forEach>
             </select>
            </td>
        </tr> --%>
        <%-- 			<tr id="linkContent3" style="display:none">
            <td colspan="1" id="show1-1" class="title">详情<span class="required">*</span></td>
            <td colspan="5"  id="show1-2"  align="left" class="l-table-edit-td">
             <select id="linkContent5" name="linkContent5" style="width: 150px;" onchange="Linkcontent5()">
                       <option value="">请选择</option>
                       <option value="1" <c:if test="${indexPopupAdCustom.linkContent=='1'}">selected</c:if>>新用户专享</option>
                       <option value="2" <c:if test="${indexPopupAdCustom.linkContent=='2'}">selected</c:if>>单品爆款</option>
                       <option value="3" <c:if test="${indexPopupAdCustom.linkContent=='3'}">selected</c:if>>限时抢购</option>
                       <option value="4" <c:if test="${indexPopupAdCustom.linkContent=='4'}">selected</c:if>>新用户秒杀</option>
                       <option value="5" <c:if test="${indexPopupAdCustom.linkContent=='5'}">selected</c:if>>积分商城</option>
                       <option value="6" <c:if test="${indexPopupAdCustom.linkContent=='6'}">selected</c:if>>断码清仓</option>
                       <option value="7" <c:if test="${indexPopupAdCustom.linkContent=='7'}">selected</c:if>>签到</option>
                       <option value="8" <c:if test="${indexPopupAdCustom.linkContent=='8'}">selected</c:if>>砍价</option>
                       <option value="9" <c:if test="${indexPopupAdCustom.linkContent=='9'}">selected</c:if>>邀请免单</option>
                       <option value="10" <c:if test="${indexPopupAdCustom.linkContent=='10'}">selected</c:if>>有好货</option>
                       <option value="11" <c:if test="${indexPopupAdCustom.linkContent=='11'}">selected</c:if>>每日好店</option>
                       <option value="12" <c:if test="${indexPopupAdCustom.linkContent=='12'}">selected</c:if>>新品牌团</option>
               </select>
            </td>
        </tr> --%>
        <tr>
            <td colspan="1" class="title">弹窗次数<span class="required">*</span></td>
            <td colspan="5" align="left" class="l-table-edit-td" id="popupCountTd">
                <c:if test="${indexPopupAdCustom.linkType ne 29}">
                    <input type="radio" name="popupCount" value="1" onchange="days(1);"
                           <c:if test="${indexPopupAdCustom.popupCount=='1'}">checked</c:if>/>终生一次&nbsp;
                    <input type="radio" name="popupCount" value="2" onchange="days(2);"
                           <c:if test="${indexPopupAdCustom.popupCount=='2'}">checked</c:if>/>启动即弹&nbsp;
                    <input type="radio" name="popupCount" value="3" onchange="days(3);"
                           <c:if test="${indexPopupAdCustom.popupCount=='3'}">checked</c:if>/>每天一次&nbsp;
                    <input type="radio" name="popupCount" value="4" onchange="days(4);"
                           <c:if test="${indexPopupAdCustom.popupCount=='4'}">checked</c:if>/>自定义&nbsp;<input type="text" id="day" name="day" value="<c:if
                        test="${indexPopupAdCustom.popupCount=='4'}">${indexPopupAdCustom.day}</c:if>" style="width: 36px;" disabled="disabled"/>&nbsp;天一次
                </c:if>
                <c:if test="${indexPopupAdCustom.linkType eq 29}">
                    <input type="radio" name="popupCount" value="5" onchange="days(5);"
                           <c:if test="${indexPopupAdCustom.popupCount=='5'}">checked</c:if>/>领取停止（启用即弹）&nbsp;
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="title" width="20%">选择分组</td>
            <td align="left" class="l-table-edit-td">
                <input style="width: 40px" type="button" id="addGroup" name="addGroup" value="添加" onclick="commit();"/>
                <input type="hidden" id="selectGroup" name="selectGroup" value="${indexPopupAdCustom.selectGroup}"/>

                <table>
                    <!--选择的分组-->
                    <tbody id="groups">

                    </tbody>
                </table>
            </td>
        </tr>
        <tr>
            <td colspan="1" class="title">自动上架时间<span class="required">*</span></td>
            <td colspan="5" align="left" class="l-table-edit-td">
                <input type="text" id="upDate" name="upDate"
                       value="<fmt:formatDate value="${indexPopupAdCustom.upDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </td>
        </tr>

        <tr>
            <td colspan="1" class="title">自动下架时间<span class="required">*</span></td>
            <td colspan="5" align="left" class="l-table-edit-td">
                <input type="text" id="downDate" name="downDate"
                       value="<fmt:formatDate value="${indexPopupAdCustom.downDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </td>
        </tr>

        <tr>
            <td colspan="1" class="title">操作</td>
            <td colspan="5" align="left" class="l-table-edit-td">
                <div id="btnDiv">
                    <input name="btnSubmit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"
                           onclick="commitSave();"/>
                    <!-- <input name="btnSubmit" type="submit" id="Button1" style="float:left;" class="l-button l-button-submit" value="提交"/> -->
                    <input type="button" value="取消" class="l-button l-button-test" style="float:left;"
                           onclick="frameElement.dialog.close();"/>
                    <%--						<input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="parent.location.reload();" />--%>
                </div>
            </td>
        </tr>

    </table>
</form>
</body>
</html>
