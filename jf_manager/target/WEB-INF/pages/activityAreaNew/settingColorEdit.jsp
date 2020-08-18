<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="t" uri="/gzs_tag_lib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/common/js/jquery.validate.jf.js" type="text/javascript"></script>
    <title>My JSP 'addmchtplatconindex.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">


    <style type="text/css">
        .title{height: 30px;width: 60px;}
        .l-table-edit-td{height:30px; width: 120px;}
    </style>

    <script type="text/javascript">
        var dataFromValidate;
        $(function(){
            $.metadata.setType("attr", "validate");
            dataFromValidate =   $("#form1").validate({
                errorPlacement : function(lable, element) {
                    if ($(element).attr('name') == 'name') {
                        $("#name").ligerTip({
                            content : lable.html()
                        });
                    } else{
                        lable.appendTo(element.parent());
                    }
                },
                success : function(lable) {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler : function(form) {
                    parent.location.reload();
                    form.submit();
                }
            });
        })

        //鼠标离开改变input框颜色
        function changeColor(_this){
           var colorCode =  $(_this).val().trim();
           var reg = /^#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})$/;
          if( !reg.test(colorCode)){
               $.ligerDialog.error('请输入正确颜色格式');
           }
            /*$(_this).css("background-color",colorCode)*/
           $(_this).prev().css("background-color",colorCode);
        }

        //保存
        function save(){
            var dataJson = $("#form1").serializeArray();
            if(dataFromValidate.form()){
                $.ajax({
                    url: "${pageContext.request.contextPath}/activityAreaNew/saveSettingColor.shtml",
                    type : 'POST',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    data : dataJson,
                    timeout : 30000,
                    success : function(data) {
                        if ("0000" == data.returnCode) {
                            commUtil.alertSuccess("提交成功");
                            parent.location.reload();
                            frameElement.dialog.close();
                        }else{
                            $.ligerDialog.error(data.returnMsg);
                        }

                    },
                    error : function() {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });
            }
        }


    </script>
</head>

<body>

<form method="post" id="form1" name="form1"  style="margin: 10px"  action="">
    <input type="hidden" id="id" name="id" value="${seckillModuleColorSetting.id}"  />
    <input type="hidden" id="decorateModuleId" name="decorateModuleId" value="${seckillModuleColorSetting.decorateModuleId}"  />
    <table class="gridtable">
        <c:if test="${moduleType == 6}">
        <tr>
            <td class ="title" >数据来源<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <select id="dataSource"  name="dataSource"  style="width: 160px;" validate="{required:true}">
                    <option value="" >请选择</option>
                    <option value="1" <c:if test="${seckillModuleColorSetting.dataSource==1}">selected</c:if>>限时秒杀</option>
                    <option value="2" <c:if test="${seckillModuleColorSetting.dataSource==2}">selected</c:if>>会场秒杀</option>
                </select>
            </td>
        </tr>
        </c:if>

        <tr>
            <td class ="title" >整体背景色<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input  type="button" style="width: 10px;background-color:${seckillModuleColorSetting.totalBgColor}" >
                <input id="totalBgColor"   type="text" name="totalBgColor" onblur="changeColor(this)"   value="${seckillModuleColorSetting.totalBgColor }"  validate="{required:true,maxlength:8}"/>
            </td>
        </tr>

        <tr >
            <td class ="title" >时间栏背景色<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input  type="button" style="width: 10px;background-color:${seckillModuleColorSetting.timeColBgColor}" >
                <input id="timeColBgColor"   type="text" name="timeColBgColor"  onblur="changeColor(this)"  value="${seckillModuleColorSetting.timeColBgColor}" validate="{required:true,maxlength:8}"  />
            </td>
        </tr>

        <tr>
            <td class ="title" >选中背景色<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input  type="button" style="width: 10px;background-color:${seckillModuleColorSetting.selectedBgColor}" >
                <input id="selectedBgColor"   type="text" name="selectedBgColor"  onblur="changeColor(this)"   value="${seckillModuleColorSetting.selectedBgColor }"  validate="{required:true,maxlength:8}"/>
            </td>
        </tr>

        <tr>
            <td class ="title" >按钮默认背景色<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input  type="button" style="width: 10px;background-color:${seckillModuleColorSetting.btnDefaultBgColor}" >
                <input id="btnDefaultBgColor"  type="text" name="btnDefaultBgColor"  onblur="changeColor(this)"   value="${seckillModuleColorSetting.btnDefaultBgColor }"  validate="{required:true,maxlength:8}"/>
            </td>
        </tr>

        <tr>
            <td class ="title" >按钮选中背景色<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input  type="button" style="width: 10px;background-color:${seckillModuleColorSetting.btnSelectedBgColor}" >
                <input id="btnSelectedBgColor" type="text" name="btnSelectedBgColor"   onblur="changeColor(this)" value="${seckillModuleColorSetting.btnSelectedBgColor }"  validate="{required:true,maxlength:8}"/>
            </td>
        </tr>

        <c:if test="${moduleType==15}">
        <tr>
            <td class ="title" >优惠券背景色<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input  type="button" style="width: 10px;background-color:${seckillModuleColorSetting.couponBgColor}" >
                <input id="couponBgColor"  type="text" name="couponBgColor"   onblur="changeColor(this)"  value="${seckillModuleColorSetting.couponBgColor }"  validate="{required:true,maxlength:8}"/>
            </td>
        </tr>
        </c:if>

        <tr>
            <td class ="title" >默认字体颜色<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input  type="button" style="width: 10px;background-color:${seckillModuleColorSetting.defaultFontColor}" >
                <input id="defaultFontColor"   type="text" name="defaultFontColor"  onblur="changeColor(this)"   value="${seckillModuleColorSetting.defaultFontColor }"  validate="{required:true,maxlength:8}"/>
            </td>
        </tr>

        <tr>
            <td class ="title" >选中字体颜色<span class="required">*</span></td>
            <td align="left" class="l-table-edit-td">
                <input  type="button" style="width: 10px;background-color:${seckillModuleColorSetting.selectedFontColor}" >
                <input id="selectedFontColor"   type="text" name="selectedFontColor"   onblur="changeColor(this)"  value="${seckillModuleColorSetting.selectedFontColor }"  validate="{required:true,maxlength:8}"/>
            </td>
        </tr>



        <tr>
            <td class="title">操作</td>
            <td align="center" class="l-table-edit-td">
                <div id="btnDiv">
                    <input name="btnSubmit"   style="float:left;margin-right:30px;" class="l-button l-button-submit" value="提交" onclick="save();"/>
                   <%-- <input type="button" value="取消" class="l-button l-button-test" style="float:left;" onclick="frameElement.dialog.close();" />--%>
                </div>
           </td>
        </tr>
    </table>
</form>
</body>
</html>
