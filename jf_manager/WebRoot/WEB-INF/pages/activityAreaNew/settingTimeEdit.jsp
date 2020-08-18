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

        $(function ()  {
            var startHoursDiv=$("#startHoursDiv").text();
            var startMinDiv=$("#startMinDiv").text();
            var continueHoursDiv=$("#continueHoursDiv").text();
            var continueMinDiv=$("#continueMinDiv").text();

            var startHoursHtml = [];
            for(var j=0 ; j<24; j++){
                if(j<10){
                    if(startHoursDiv && j == startHoursDiv ){
                        startHoursHtml.push("<option selected value=0"+j+">0"+j+"</option>");
                    }else{
                        startHoursHtml.push("<option value=0"+j+">0"+j+"</option>");
                    }
                }else {
                    if(startHoursDiv && j == startHoursDiv ){
                        startHoursHtml.push("<option selected value="+j+">"+j+"</option>");
                    }else {
                        startHoursHtml.push("<option value=" + j + ">" + j + "</option>");
                    }
                }
            }
            $("#startHours").html(startHoursHtml.join(""));


            var startMinHtml = [];
            for(var j=0 ; j<60; j++){
                if(j<10){
                    if(startMinDiv && j == startMinDiv ){
                        startMinHtml.push("<option selected value=0"+j+">0"+j+"</option>");
                    }else {
                        startMinHtml.push("<option value=0" + j + ">0" + j + "</option>");
                    }
                }else {
                    if(startMinDiv && j == startMinDiv ){
                        startMinHtml.push("<option selected value="+j+">"+j+"</option>");
                    }else {
                        startMinHtml.push("<option value=" + j + ">" + j + "</option>");
                    }
                }
            }
            $("#startMin").html(startMinHtml.join(""));


        });

        //删除
        function delTime(couponModuleTimeId){
            $.ajax({//检查时间点是否重复
                url : "${pageContext.request.contextPath}/activityAreaNew/delCouponTime.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {couponModuleTimeId:couponModuleTimeId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        location.reload();
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });

        }



        function saveTime(){
          var startHours = $("#startHours").val();
          var startMin = $("#startMin").val();
          var decorateModuleId = $("#decorateModuleId").val();
            console.log(startHours);
            console.log(startMin);
            console.log(decorateModuleId);
            $.ajax({//检查时间点是否重复
                url : "${pageContext.request.contextPath}/activityAreaNew/checkPointOfTime.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {startHours:startHours,startMin:startMin,decorateModuleId:decorateModuleId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        location.reload();
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

    </script>
</head>

<body>

<form method="post" id="form1" name="form1" >
    <input type="hidden" id="decorateModuleId" name="decorateModuleId" value="${decorateModuleId}"/>
    <div style="margin-left: 40px;margin-top: 20px;">
        <div>
            <div>
                    <select id="startHours" name="startHours"  style="width: 80px;">
                        <option value="">请选择</option>
                    </select>
                    &nbsp;:&nbsp;
                    <select id="startMin" name="startMin"  style="width: 80px;">
                        <option value="">请选择</option>
                    </select>

                <input name="saveAndCheck"  id="saveAndCheck" type="button"   style=" margin-left:20px;width: 90px;height: 30px;cursor: default;"  onclick="saveTime();" value="添加秒杀时间点"/>
            </div>

    <%--        <input name=""  id="Button1" class="l-button l-button-submit" onclick="commitSave();" value="保存"/>--%>
        </div>

        <div>
           <%-- <c:forEach items="${decorateAreaCustom}" var="decorateModuleCustom" varStatus="index">--%>
            <c:forEach items="${couponModuleTimeList}" var="couponModuleTime">
                <br><input id="${couponModuleTime.id}" type="button" style="width: 70px;height: 30px;cursor: default;"  value="${couponModuleTime.startHours}:${couponModuleTime.startMin}"/>
                <input name="btnSubmit"  type="button" style="width: 70px;height: 30px;cursor: default;"  value="删除" onclick="delTime(${couponModuleTime.id})"/><br>
            </c:forEach>

        </div>
    </div>

</form>
</body>
</html>
