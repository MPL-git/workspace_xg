<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
    <script src="${pageContext.request.contextPath}/common/js/utils/ajaxfileupload.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(function(){
            $("input[name='editDecorateModule']").live('click',function(){
                var decorateInfoId = $("#decorateInfoId").val();
                var decorateAreaId = $(this).attr("decorateAreaId");
                var moduleType = $(this).attr("moduleType");
                var decorateModuleId = $(this).attr("decorateModuleId");
                var index = $("#decorateModuleDiv div[name='moduleDiv']").index($(this).closest("div[name='moduleDiv']"));
                var seqNo = Number(index)+1;
                var isPreSell = $("#isPreSell").val();
                var title;
                if(moduleType == "1"){
                    title = "编辑图片模块";
                }else if(moduleType == "2"){
                    title = "编辑商品模块";
                }else if(moduleType == "3"){
                    title = "编辑特卖模块";
                }else if(moduleType == "8"){
                    title = "编辑固定底部栏模块";
                }else if(moduleType == "10"){
                    title = "编辑固定顶部栏模块";
                }else if(moduleType == "9"){
                    title = "编辑滑动模块";
                }
                $.ligerDialog.open({
                    height: $(window).height()*0.9,
                    width: $(window).width()*0.9,
                    title: title,
                    name: "INSERT_WINDOW",
                    url: "${pageContext.request.contextPath}/customPage/editDecorateModule.shtml?decorateAreaId=" + decorateAreaId+"&moduleType="+moduleType+"&seqNo="+seqNo+"&decorateModuleId="+decorateModuleId+"&decorateInfoId="+decorateInfoId+"&isPreSell="+isPreSell+"&showType=${showType}",
                    showMax: true,
                    showToggle: false,
                    showMin: false,
                    isResize: true,
                    slide: false,
                    data: null
                });

            });

            $("input[name='areaMoveUp']").bind('click',function(){
                var prevId = $(this).attr("decorateAreaId");
                var nextId = $(this).closest("div[name='eachDecorateAreaDiv']").prev().prev().attr("decorateAreaId");
                var nextIndex = $("div[name='eachDecorateAreaDiv']").index($(this).closest("div[name='eachDecorateAreaDiv']"));
                var nextSeqNo = Number(nextIndex)+1;
                var prevIndex = $("div[name='eachDecorateAreaDiv']").index($(this).closest("div[name='eachDecorateAreaDiv']").prev().prev());
                var prevSeqNo = Number(prevIndex)+1;
                if(prevId && nextId){
                    $.ajax({
                        url : "${pageContext.request.contextPath}/activityAreaNew/moveDecorateArea.shtml?prevId="+prevId+"&nextId="+nextId+"&prevSeqNo="+prevSeqNo+"&nextSeqNo="+nextSeqNo,
                        type : 'POST',
                        dataType : 'json',
                        cache : false,
                        async : false,
                        data : {prevId:prevId,nextId:nextId},
                        timeout : 30000,
                        success : function(data) {
                            if ("0000" == data.returnCode) {
                                commUtil.alertSuccess("操作成功");
                                location.reload(true);
                            }else{
                                $.ligerDialog.error(data.returnMsg);
                            }
                        },
                        error : function() {
                            $.ligerDialog.error('操作超时，请稍后再试！');
                        }
                    });
                }
            });

            $("input[name='areaMoveDown']").bind('click',function(){
                var nextId = $(this).attr("decorateAreaId");
                var prevId = $(this).closest("div[name='eachDecorateAreaDiv']").next().next().attr("decorateAreaId");
                var prevIndex = $("div[name='eachDecorateAreaDiv']").index($(this).closest("div[name='eachDecorateAreaDiv']"));
                var prevSeqNo = Number(prevIndex)+1;
                var nextIndex = $("div[name='eachDecorateAreaDiv']").index($(this).closest("div[name='eachDecorateAreaDiv']").next().next());
                var nextSeqNo = Number(nextIndex)+1;
                if(prevId && nextId){
                    $.ajax({
                        url : "${pageContext.request.contextPath}/activityAreaNew/moveDecorateArea.shtml?prevId="+prevId+"&nextId="+nextId+"&prevSeqNo="+prevSeqNo+"&nextSeqNo="+nextSeqNo,
                        type : 'POST',
                        dataType : 'json',
                        cache : false,
                        async : false,
                        data : {prevId:prevId,nextId:nextId},
                        timeout : 30000,
                        success : function(data) {
                            if ("0000" == data.returnCode) {
                                commUtil.alertSuccess("操作成功");
                                location.reload(true);
                            }else{
                                $.ligerDialog.error(data.returnMsg);
                            }
                        },
                        error : function() {
                            $.ligerDialog.error('操作超时，请稍后再试！');
                        }
                    });
                }
            });

            $("input[name='moveUp']").live('click',function(){
                var decorateAreaId = $(this).attr("decorateAreaId");
                var prevId = $(this).attr("decorateModuleId");
                var nextId = $(this).closest("div[name='moduleDiv"+decorateAreaId+"']").prev().prev().attr("decorateModuleId");
                var nextIndex = $("div[name='moduleDiv"+decorateAreaId+"']").index($(this).closest("div[name='moduleDiv"+decorateAreaId+"']"));
                var nextSeqNo = Number(nextIndex)+1;
                var prevIndex = $("div[name='moduleDiv"+decorateAreaId+"']").index($(this).closest("div[name='moduleDiv"+decorateAreaId+"']").prev().prev());
                var prevSeqNo = Number(prevIndex)+1;
                if(prevId && nextId){
                    $.ajax({
                        url : "${pageContext.request.contextPath}/customPage/moveDecorateModule.shtml?prevId="+prevId+"&nextId="+nextId+"&prevSeqNo="+prevSeqNo+"&nextSeqNo="+nextSeqNo,
                        type : 'POST',
                        dataType : 'json',
                        cache : false,
                        async : false,
                        data : {prevId:prevId,nextId:nextId},
                        timeout : 30000,
                        success : function(data) {
                            if ("0000" == data.returnCode) {
                                commUtil.alertSuccess("操作成功");
                                location.reload(true);
                            }else{
                                $.ligerDialog.error(data.returnMsg);
                            }
                        },
                        error : function() {
                            $.ligerDialog.error('操作超时，请稍后再试！');
                        }
                    });
                }else{

                }
            });

            $("input[name='moveDown']").live('click',function(){
                var decorateAreaId = $(this).attr("decorateAreaId");
                var nextId = $(this).attr("decorateModuleId");
                var prevId = $(this).closest("div[name='moduleDiv"+decorateAreaId+"']").next().next().attr("decorateModuleId");
                var prevIndex = $("div[name='moduleDiv"+decorateAreaId+"']").index($(this).closest("div[name='moduleDiv"+decorateAreaId+"']"));
                var prevSeqNo = Number(prevIndex)+1;
                var nextIndex = $("div[name='moduleDiv"+decorateAreaId+"']").index($(this).closest("div[name='moduleDiv"+decorateAreaId+"']").next().next());
                var nextSeqNo = Number(nextIndex)+1;
                if(prevId && nextId){
                    $.ajax({
                        url : "${pageContext.request.contextPath}/customPage/moveDecorateModule.shtml?prevId="+prevId+"&nextId="+nextId+"&prevSeqNo="+prevSeqNo+"&nextSeqNo="+nextSeqNo,
                        type : 'POST',
                        dataType : 'json',
                        cache : false,
                        async : false,
                        data : {prevId:prevId,nextId:nextId},
                        timeout : 30000,
                        success : function(data) {
                            if ("0000" == data.returnCode) {
                                commUtil.alertSuccess("操作成功");
                                location.reload(true);
                            }else{
                                $.ligerDialog.error(data.returnMsg);
                            }
                        },
                        error : function() {
                            $.ligerDialog.error('操作超时，请稍后再试！');
                        }
                    });
                }else{

                }
            });

            $(".l-dialog-close").live("click", function(){//关闭弹出窗口时刷新父级页面
                location.reload();
            });
        });

        function previewDecorateInfo(decorateInfoId,activityAreaId) {
            $.ligerDialog.open({
                height: $(window).height()*0.9,
                width: $(window).width()*0.9,
                title: "预览",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/activityAreaNew/previewDecorateInfo.shtml?decorateInfoId=" + decorateInfoId+"&activityAreaId="+activityAreaId,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        function delDecorateArea(decorateAreaId,_this){
            if(confirm("确定删除吗？删除分区后，将会把该分区下的所有模块也一并删除！")){
                $.ajax({
                    url : "${pageContext.request.contextPath}/activityAreaNew/deleteDecorateArea.shtml?decorateAreaId="+decorateAreaId,
                    type : 'POST',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    data : $("#form").serialize(),
                    timeout : 30000,
                    success : function(data) {
                        if ("0000" == data.returnCode) {
                            commUtil.alertSuccess("删除成功");
                            location.reload(true);
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

        function insertPicModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            console.log(decorateInfoId)
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/insertPicModule.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        location.reload(true);
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        //插入滑动图片模块
        function insertSlidePicModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/insertSlidePicModule.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        location.reload(true);
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }



        function insertBottomBarModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            var moduleType = "8";
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/getModuleCount.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        if(data.count>0){
                            commUtil.alertError("已经存在固定底部栏模块,不可重复添加");
                        }else{
                            $.ajax({
                                url : "${pageContext.request.contextPath}/activityAreaNew/insertBottomBarModule.shtml",
                                type : 'POST',
                                dataType : 'json',
                                cache : false,
                                async : false,
                                data : {decorateInfoId:decorateInfoId},
                                timeout : 30000,
                                success : function(data) {
                                    if ("0000" == data.returnCode) {
                                        location.reload(true);
                                    }else{
                                        $.ligerDialog.error(data.returnMsg);
                                    }
                                },
                                error : function() {
                                    $.ligerDialog.error('操作超时，请稍后再试！');
                                }
                            });
                        }
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function insertTopFieldModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            var moduleType = "10";
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/getModuleCount.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        if(data.count>0){
                            commUtil.alertError("已经存在固定顶部栏模块,不可重复添加");
                        }else{
                            $.ajax({
                                url : "${pageContext.request.contextPath}/activityAreaNew/insertTopFieldModule.shtml",
                                type : 'POST',
                                dataType : 'json',
                                cache : false,
                                async : false,
                                data : {decorateInfoId:decorateInfoId},
                                timeout : 30000,
                                success : function(data) {
                                    if ("0000" == data.returnCode) {
                                        location.reload(true);
                                    }else{
                                        $.ligerDialog.error(data.returnMsg);
                                    }
                                },
                                error : function() {
                                    $.ligerDialog.error('操作超时，请稍后再试！');
                                }
                            });
                        }
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function insertProductModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/insertProductModule.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        location.reload(true);
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function insertActivityModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            var moduleType = "3";
            var showNum = 1;
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/insertActivityModule.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId,moduleType:moduleType,showNum:showNum},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        location.reload(true);
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function insertSecondKillModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            var moduleType = "6";
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/getModuleCount.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        if(data.count>0){
                            commUtil.alertError("已经存在秒杀模块,不可重复添加");
                        }else{
                            $.ajax({
                                url : "${pageContext.request.contextPath}/activityAreaNew/insertSecondKillModule.shtml",
                                type : 'POST',
                                dataType : 'json',
                                cache : false,
                                async : false,
                                data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
                                timeout : 30000,
                                success : function(data) {
                                    if ("0000" == data.returnCode) {
                                        location.reload(true);
                                    }else{
                                        $.ligerDialog.error(data.returnMsg);
                                    }
                                },
                                error : function() {
                                    $.ligerDialog.error('操作超时，请稍后再试！');
                                }
                            });
                        }
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function insertNeedBuyModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            var moduleType = "7";

           /* $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/getModuleCount.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        if(data.count>0){
                            commUtil.alertError("已经存在TOP榜单模块,不可重复添加");
                        }else{*/
                             //必购TOP榜模块去除限制,原先有做count校验
                            $.ajax({
                                url : "${pageContext.request.contextPath}/activityAreaNew/insertNeedBuyModule.shtml",
                                type : 'POST',
                                dataType : 'json',
                                cache : false,
                                async : false,
                                data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
                                timeout : 30000,
                                success : function(data) {
                                    if ("0000" == data.returnCode) {
                                        location.reload(true);
                                    }else{
                                        $.ligerDialog.error(data.returnMsg);
                                    }
                                },
                                error : function() {
                                    $.ligerDialog.error('操作超时，请稍后再试！');
                                }
                            });
         /*               }
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });*/
        }

        function editDecorateArea(decorateAreaId){
            var title;
            if(decorateAreaId){
                title = "修改分区";
            }else{
                title = "插入分区";
                decorateAreaId = "";
            }
            var decorateInfoId = $("#decorateInfoId").val();
            $.ligerDialog.open({
                height: 250,
                width: 450,
                title: title,
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/activityAreaNew/editDecorateArea.shtml?decorateAreaId=" + decorateAreaId+"&decorateInfoId="+decorateInfoId,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }



        function insertTopPic(_this){
            var length = $("#topPicImg").length;
            if(length > 0){
                commUtil.alertError("只能插入1张海报图");
//		$(_this).attr("disabled", true);
                return false;
            }else{
                var html = '<div class="search-tr" id="topPicDiv">'+
                    '<div style="position: relative;width: 750px;height: 400px;" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">'+
                    '<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
                    '<div style="float: left;padding-top:7px;padding-left:12px;width: 88px;height: 23px;background-color: gray;color: white;">banner海报图</div>'+
                    '<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" onclick="delTopPic(${activityArea.id},this);">'+
                    '<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" onclick="editTopPic(${activityArea.id})">'+
                    '<div style="margin-top: 100px;margin-left: 70px;">图片中需包含专场Logo、利益点、专场主题 <br>尺寸：宽为750像素，高度不限，大小：200Kb以内</div>'+
                    '</div>'+
                    '<img src="${pageContext.request.contextPath}/file_servelt${activityArea.topPic}" id="topPicImg" style="max-width: 750px;max-height: 400px;">'+
                    '</div>'+
                    '</div>';
                $("#activityTimeDiv").before(html);
            }
        }

        function delTopPic(activityAreaId,_this){
            if(confirm("确定删除吗？")){
                var topPic = $("#topPic").val();
                if(topPic){
                    $.ajax({
                        url : "${pageContext.request.contextPath}/activityAreaNew/saveTopPic.shtml?activityAreaId="+activityAreaId,
                        type : 'POST',
                        dataType : 'json',
                        cache : false,
                        async : false,
                        data : $("#form").serialize(),
                        timeout : 30000,
                        success : function(data) {
                            if (data && data.statusCode == 200) {
                                $.ligerDialog.success("删除成功");
                                location.reload(true);
                            }else{
                                $.ligerDialog.error(data.returnMsg);
                            }
                        },
                        error : function() {
                            $.ligerDialog.error('操作超时，请稍后再试！');
                        }
                    });
                }else{
                    $("#topPicDiv").remove();
                }

            }
        }

        function del(id,obj){
            if(confirm("确定删除吗？")){
                if(id && id>0){
                    $.ajax({
                        url : "${pageContext.request.contextPath}/customPage/deleteDecorateModule.shtml?id="+id,
                        type : 'POST',
                        dataType : 'json',
                        cache : false,
                        async : false,
                        data : $("#form").serialize(),
                        timeout : 30000,
                        success : function(data) {
                            if ("0000" == data.returnCode) {
                                commUtil.alertSuccess("删除成功");
                                location.reload(true);
                            }else{
                                $.ligerDialog.error(data.returnMsg);
                            }
                        },
                        error : function() {
                            $.ligerDialog.error('操作超时，请稍后再试！');
                        }
                    });
                }else{
                    var $moduleDiv = $(obj).closest("div[name='moduleDiv']");
                    var $br = $moduleDiv.next();
                    $moduleDiv.remove();
                    $br.remove();
                }
            }
        }

        function editTopPic(activityAreaId){
            $.ligerDialog.open({
                height: $(window).height()*0.9,
                width: $(window).width()*0.8,
                title: "编辑海报图",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/activityAreaNew/editTopPic.shtml?activityAreaId=" + activityAreaId,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        function insertPicModuleInArea(_this,decorateAreaId){
            var moduleType = 1;
            $.ajax({
                url : "${pageContext.request.contextPath}/customPage/insertPicModule.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateAreaId:decorateAreaId,moduleType:moduleType},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        var decorateModuleId = data.decorateModuleId;
                        var picModuleDiv = '<div style="margin-left: 4px;position: relative;width: 750px;height:500px;border:1px solid #000" name="moduleDiv" decorateModuleId = "'+decorateModuleId+'">'+
                            '<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
                            '<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:blue;color:white;">图片模块</div>'+
                            '<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del('+decorateModuleId+',this);">'+
                            '<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="1" decorateModuleId="'+decorateModuleId+'" decorateAreaId="'+decorateAreaId+'">'+
                            '<div style="margin-top: 100px;margin-left: 70px;">'+
                            '自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽为750像素，高度不限，大小：200Kb以内'+
                            '</div>'+
                            '</div>'+
                            '<img src="" style="width: 100%;">'+
                            '</div>'+
                            '<br>';
                        var $eachDecorateAreaDiv = $(_this).closest("div[name='eachDecorateAreaDiv']");
                        var $div = $eachDecorateAreaDiv.find("div[moduleType='8']");
                        if($div.length>0){
                            $div.before(picModuleDiv);
                        }else{
                            $eachDecorateAreaDiv.append(picModuleDiv);
                        }
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });

        }

        function insertProductModuleInArea(_this,decorateAreaId){
            var moduleType = 2;
            var showNum = 2;
            $.ajax({
                url : "${pageContext.request.contextPath}/customPage/insertProductModule.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateAreaId:decorateAreaId,moduleType:moduleType,showNum:showNum},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        var decorateModuleId = data.decorateModuleId;
                        var productModuleDiv = '<div style="margin-left: 4px;position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv" decorateModuleId = "'+decorateModuleId+'">'+
                            '<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
                            '<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:green;color:white;">商品模块</div>'+
                            '<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del('+decorateModuleId+',this);">'+
                            '<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="2" decorateModuleId="'+decorateModuleId+'">'+
                            '<div style="margin-top: 100px;margin-left: 70px;">'+
                            '共选0个商品，要求显示0个商品'+
                            '</div>'+
                            '</div>'+
                            '<img src="" style="width: 100%;">'+
                            '</div>'+
                            '<br>';
                        var $eachDecorateAreaDiv = $(_this).closest("div[name='eachDecorateAreaDiv']");
                        var $div = $eachDecorateAreaDiv.find("div[moduleType='8']");
                        if($div.length>0){
                            $div.before(productModuleDiv);
                        }else{
                            $eachDecorateAreaDiv.append(productModuleDiv);
                        }
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function insertActivityModuleInArea(_this,decorateAreaId){
            var moduleType = 3;
            var showNum = 1;
            $.ajax({
                url : "${pageContext.request.contextPath}/customPage/insertActivityModule.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateAreaId:decorateAreaId,moduleType:moduleType,showNum:showNum},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        var decorateModuleId = data.decorateModuleId;
                        var activityModule = '<div style="position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv" decorateModuleId = "'+decorateModuleId+'">'+
                            '<div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">'+
                            '<div style="float: left;padding-top:7px;padding-left:12px;width:60px;height:23px;background-color:#5500FF;color:white;">特卖模块</div>'+
                            '<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del('+decorateModuleId+',this);">'+
                            '<input type="button" style="margin-left: 20px;float:right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="3" decorateModuleId="'+decorateModuleId+'">'+
                            '<div style="margin-top: 100px;margin-left: 70px;">'+
                            '共选0个特卖，要求显示1个特卖'+
                            '</div>'+
                            '</div>'+
                            '<img src="" style="width: 100%;">'+
                            '</div>'+
                            '<br>';
                        var $eachDecorateAreaDiv = $(_this).closest("div[name='eachDecorateAreaDiv']");
                        var $div = $eachDecorateAreaDiv.find("div[moduleType='8']");
                        if($div.length>0){
                            $div.before(activityModule);
                        }else{
                            $eachDecorateAreaDiv.append(activityModule);
                        }
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        /*  插入领券秒杀模块 */
        function insertCouponSecondKillModule(){
            var decorateInfoId = $("#decorateInfoId").val();
            var moduleType = "15";
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/getModuleCount.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        if(data.count>0){
                            commUtil.alertError("已经存在领券秒杀模块,不可重复添加");
                        }else{
                            $.ajax({
                                url : "${pageContext.request.contextPath}/activityAreaNew/insertCouponSecondKillModule.shtml",
                                type : 'POST',
                                dataType : 'json',
                                cache : false,
                                async : false,
                                data : {decorateInfoId:decorateInfoId,moduleType:moduleType},
                                timeout : 30000,
                                success : function(data) {
                                    if ("0000" == data.returnCode) {
                                        location.reload(true);
                                    }else{
                                        $.ligerDialog.error(data.returnMsg);
                                    }
                                },
                                error : function() {
                                    $.ligerDialog.error('操作超时，请稍后再试！');
                                }
                            });
                        }
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }


        var hasBlur;
        function categoryNameBlur(_this){
            if(hasBlur){
                return false;
            }
            var categoryName = $(_this).val().trim();
            var productCodes;
            var decorateModuleId = $(_this).attr("decorateModuleId");
            var id = $(_this).attr("id");
            if(!categoryName){
                return;
            }else{
                productCodes = $(_this).next().val().trim();
                if(!productCodes){
                    return;
                }
            }
            hasBlur = true;
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/saveBgModuleCategory.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateModuleId:decorateModuleId,id:id,categoryName:categoryName,productCodes:productCodes},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        hasBlur = false;
                        $(_this).attr("id",data.id);
                        $(_this).next().attr("id",data.id);
//				commUtil.alertSuccess("保存成功");
//				location.reload(true);
                    }else{
                        hasBlur = false;
                        $.ligerDialog.error(data.returnMsg);
                        location.reload(true);
                    }
                },
                error : function() {
                    hasBlur = false;
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        var hasFieldBlur;
        function fieldNameBlur(_this){
            var decorateInfoId = $("#decorateInfoId").val();
            if(hasFieldBlur){
                return false;
            }
            var fieldName = $(_this).val().trim();
            var linkDecorateAreaId;
            var decorateModuleId = $(_this).attr("decorateModuleId");
            var id = $(_this).attr("id");
            if(!fieldName){
                return;
            }else{
                console.log(fieldName.length);
                if(fieldName.length>20){
                    commUtil.alertError("最多只能输入20个字");
                    return;
                }

                linkDecorateAreaId = $(_this).next().val().trim();
                if(!linkDecorateAreaId){
                    return;
                }
            }
            hasFieldBlur = true;
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/saveTopFieldModuleField.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId,decorateModuleId:decorateModuleId,id:id,fieldName:fieldName,linkDecorateAreaId:linkDecorateAreaId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        hasFieldBlur = false;
                        $(_this).attr("id",data.id);
                        $(_this).next().attr("id",data.id);
//				commUtil.alertSuccess("保存成功");
//				location.reload(true);
                    }else{
                        hasFieldBlur = false;
                        $.ligerDialog.error(data.returnMsg);
                        location.reload(true);
                    }
                },
                error : function() {
                    hasFieldBlur = false;
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        var hasProductCodeBlur;
        function productCodeBlur(_this){
            if(hasProductCodeBlur){
                return false;
            }
            var productCodes = $(_this).val().trim();
            var categoryName;
            var decorateModuleId = $(_this).attr("decorateModuleId");
            var id = $(_this).attr("id");
            if(!productCodes){
                return;
            }else{
                categoryName = $(_this).prev().val().trim();
                if(!categoryName){
                    return;
                }
            }
            hasProductCodeBlur = true;
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/saveBgModuleCategory.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateModuleId:decorateModuleId,id:id,categoryName:categoryName,productCodes:productCodes},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        hasProductCodeBlur = false;
                        $(_this).attr("id",data.id);
                        $(_this).prev().attr("id",data.id);
//				commUtil.alertSuccess("保存成功");
//				location.reload(true);
                    }else{
                        hasProductCodeBlur = false;
                        $.ligerDialog.error(data.returnMsg);
                        location.reload(true);
                    }
                },
                error : function() {
                    hasBlur = false;
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        var hasLinkDecorateAreaIdBlur;
        function linkDecorateAreaIdBlur(_this){
            var decorateInfoId = $("#decorateInfoId").val();
            if(hasLinkDecorateAreaIdBlur){
                return false;
            }
            var linkDecorateAreaId = $(_this).val().trim();
            var fieldName;
            var decorateModuleId = $(_this).attr("decorateModuleId");
            var id = $(_this).attr("id");
            if(!linkDecorateAreaId){
                return;
            }else{
                fieldName = $(_this).prev().val().trim();
                if(!fieldName){
                    return;
                }
            }
            var re =  /^(0|\+?[1-9][0-9]*)$/;
            if (!re.test(linkDecorateAreaId)) {
                commUtil.alertError("分区ID请输入正整数！");
                return;
            }

            var index = $("#topFieldModuleDiv").find("div").index(_this.closest('div'));
            //console.log(index);

            hasLinkDecorateAreaIdBlur = true;
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/saveTopFieldModuleField.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateInfoId:decorateInfoId,decorateModuleId:decorateModuleId,id:id,fieldName:fieldName,linkDecorateAreaId:linkDecorateAreaId,index:index},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        hasLinkDecorateAreaIdBlur = false;
                        $(_this).attr("id",data.id);
                        $(_this).prev().attr("id",data.id);
//				commUtil.alertSuccess("保存成功");
                        location.reload(true);
                    }else{
                        hasLinkDecorateAreaIdBlur = false;
                        $.ligerDialog.error(data.returnMsg);
                        location.reload(true);
                    }
                },
                error : function() {
                    hasBlur = false;
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });
        }

        function deleteBgModuleCategory(_this){
            var id = $(_this).attr("id");
            $.ligerDialog.confirm("是否删除TOP榜模块，删除后不可恢复", function (yes) {
                if(yes){
                    $(_this).closest("div").remove();
                    if(id){
                        $.ajax({
                            url : "${pageContext.request.contextPath}/activityAreaNew/deleteBgModuleCategory.shtml",
                            type : 'POST',
                            dataType : 'json',
                            cache : false,
                            async : false,
                            data : {id:id},
                            timeout : 30000,
                            success : function(data) {
                                if ("0000" == data.returnCode) {
                                    commUtil.alertSuccess("删除成功");
                                    location.reload(true);
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
            });
        }

        function deleteTopFieldModule(_this){
            var id = $(_this).attr("id");
            $.ligerDialog.confirm("是否删除当前栏目，删除后不可恢复", function (yes) {
                if(yes){
                    $(_this).closest("div").remove();
                    if(id){
                        $.ajax({
                            url : "${pageContext.request.contextPath}/activityAreaNew/deleteTopFieldModuleField.shtml",
                            type : 'POST',
                            dataType : 'json',
                            cache : false,
                            async : false,
                            data : {id:id},
                            timeout : 30000,
                            success : function(data) {
                                if ("0000" == data.returnCode) {
                                    commUtil.alertSuccess("删除成功");
                                    location.reload(true);
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
            });
        }

        function add(decorateModuleId,_this){
           // debugger;
            var html = [];
            html.push('<div style="margin-top: 5px;"><input name="categoryName" style="width: 100px;" id="" onblur="categoryNameBlur(this);" decorateModuleId="'+decorateModuleId+'" value="" placeholder="请输入类目名称">&nbsp;&nbsp;商品ID&nbsp;&nbsp;<input name="productCode" id="" onblur="productCodeBlur(this);" decorateModuleId="'+decorateModuleId+'" value="" placeholder="输入商品ID按,隔开" style="width:450px;">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="deleteBgModuleCategory(this)">删除</a></div>');
            $("#"+decorateModuleId+"bgModuleCategoryDiv").append(html.join(""));
        }

        /* function addTopFieldModuleField(decorateModuleId,_this){
            var html = [];
            html.push('<div style="margin-top: 5px;"><input name="fieldName" style="width: 100px;" onblur="fieldNameBlur(this);" decorateModuleId="'+decorateModuleId+'" value="" placeholder="请输入栏目名称">&nbsp;&nbsp;分区ID&nbsp;&nbsp;<input name="linkDecorateAreaId" id="" onblur="linkDecorateAreaIdBlur(this);" decorateModuleId="'+decorateModuleId+'" value="" placeholder="请输入分区ID" style="width:450px;">&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" onclick="deleteTopFieldModule(this)">删除</a></div>');
            $("#topFieldModuleDiv").append(html.join(""));
        } */

        function addTopFieldModuleField(decorateModuleId){
            $.ajax({
                url : "${pageContext.request.contextPath}/activityAreaNew/countTopFieldModuleField.shtml",
                type : 'POST',
                dataType : 'json',
                cache : false,
                async : false,
                data : {decorateModuleId:decorateModuleId},
                timeout : 30000,
                success : function(data) {
                    if ("0000" == data.returnCode) {
                        var seqNo = data.seqCount;
                        $.ajax({
                            url : "${pageContext.request.contextPath}/activityAreaNew/addTopFieldModuleField.shtml",
                            type : 'POST',
                            dataType : 'json',
                            cache : false,
                            async : false,
                            data : {decorateModuleId:decorateModuleId,seqNo:seqNo},
                            timeout : 30000,
                            success : function(data) {
                                if ("0000" == data.returnCode) {
                                    location.reload(true);
                                }else{
                                    $.ligerDialog.error(data.returnMsg);
                                }
                            },
                            error : function() {
                                $.ligerDialog.error('操作超时，请稍后再试！');
                            }
                        });
                    }else{
                        $.ligerDialog.error(data.returnMsg);
                    }
                },
                error : function() {
                    $.ligerDialog.error('操作超时，请稍后再试！');
                }
            });

        }

        function moveDown(id,_this){
            var nextId = id;
            var prevId = $(_this).closest("div").next().attr("id");
            if(prevId && nextId){
                $.ajax({
                    url : "${pageContext.request.contextPath}/activityAreaNew/changeBgModuleCategorySeqNo.shtml",
                    type : 'POST',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    data : {prevId:prevId,nextId:nextId},
                    timeout : 30000,
                    success : function(data) {
                        if ("0000" == data.returnCode) {
                            commUtil.alertSuccess("操作成功");
                            location.reload(true);
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

        function moveUp(id,_this){
            var prevId = id;
            var nextId = $(_this).closest("div").prev().attr("id");
            if(prevId && nextId){
                $.ajax({
                    url : "${pageContext.request.contextPath}/activityAreaNew/changeBgModuleCategorySeqNo.shtml",
                    type : 'POST',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    data : {prevId:prevId,nextId:nextId},
                    timeout : 30000,
                    success : function(data) {
                        if ("0000" == data.returnCode) {
                            commUtil.alertSuccess("操作成功");
                            //window.location.reload(true);
                            location.reload(true);
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



        //向上移动top
        function moveUpTopFieldModuleField(id){
            var idName = id+"topFieldModuleField";
            console.log(idName);
            var prevId = $("a[id="+idName+"]").parent("div").prev("div").attr("id")
            console.log(prevId)
            if(!prevId){
                $.ligerDialog.error('内容为空不可交换');
                return;
            }
//	var prevId = $("a[id="+procedureNodeId+"]").closest("tr[name='nodeTr']").prev("tr").attr("id");
            if(id && prevId){
                $.ajax({
                    url : "${pageContext.request.contextPath}/activityAreaNew/moveUpTopFieldModuleField.shtml?prevId="+prevId+"&id="+id,
                    type : 'POST',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    data : {prevId:prevId,id:id},
                    timeout : 30000,
                    success : function(data) {
                        if ("0000" == data.returnCode) {
                            location.reload(true);
                            commUtil.alertSuccess("操作成功");
                        }else{
                            $.ligerDialog.error(data.returnMsg);
                        }
                    },
                    error : function() {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });
            }else{

            }
        }

        //向下移动top
        function moveDownTopFieldModuleField(id){
            var idName = id+"topFieldModuleField";
            console.log(idName);
            var nextId = $("a[id="+idName+"]").parent("div").next("div").attr("id")
            console.log(nextId)
            if(!nextId){
                $.ligerDialog.error('内容为空不可交换');
                return;
            }
//	var prevId = $("a[id="+procedureNodeId+"]").closest("tr[name='nodeTr']").prev("tr").attr("id");
            if(id && nextId){
                $.ajax({
                    url : "${pageContext.request.contextPath}/activityAreaNew/moveDownTopFieldModuleField.shtml?nextId="+nextId+"&id="+id,
                    type : 'POST',
                    dataType : 'json',
                    cache : false,
                    async : false,
                    data : {nextId:nextId,id:id},
                    timeout : 30000,
                    success : function(data) {
                        if ("0000" == data.returnCode) {
                            location.reload(true);
                            commUtil.alertSuccess("操作成功");
                        }else{
                            $.ligerDialog.error(data.returnMsg);
                        }
                    },
                    error : function() {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });
            }else{

            }

        }

        //多图上传
        //图片格式验证
        function ajaxFileUploadImg(topPicFile) {
            var files = document.getElementById(topPicFile).files;
            for(var i=0;i<files.length;i++){
                var file = files[i]
                if(!/image\/(PNG|png|JPG|jpg|JPEG|jpeg|gif|GIF)$/.test(file.type)){
                    commUtil.alertError("图片格式不正确！");
                    return;
                }

                var size = file.size;
                if(size > 200*1024 ) {
                    commUtil.alertError("图片过大，图片大小不能超过200KB,请重新上传！");
                    return;
                }
            }
            ajaxFileUpload();
        }

        function ajaxFileUpload() {
            $.ajaxFileUpload({
                url: contextPath + '/service/common/uploadFiles.shtml',
                secureuri: false,
                fileElementId: "topPicFile",
                dataType: 'json',
                success: function(result, status) {
                    if(result.RESULT_CODE == 0) {
                        var activityAreaId = $("#activityAreaId").val();
                        var topPic = result.FILE_PATH;
                        var decorateInfoId = $("#decorateInfoId").val();
                        $.ajax({
                            url : "${pageContext.request.contextPath}/activityAreaNew/insertPicsModule.shtml",
                            type : 'POST',
                            dataType : 'json',
                            cache : false,
                            async : false,
                            data : {decorateInfoId:decorateInfoId,topPic:topPic},
                            timeout : 30000,
                            success : function(data) {
                                if ("0000" == data.returnCode) {
                                    location.reload(true);
                                }else{
                                    $.ligerDialog.error(data.returnMsg);
                                }
                            },
                            error : function() {
                                $.ligerDialog.error('操作超时，请稍后再试！');
                            }
                        });
                    } else {
                        alert(result.RESULT_MESSAGE);
                    }
                },
                error: function(result, status, e) {
                    alert("服务异常");
                }
            });
        }


        /*领券秒杀模块颜色设置*/
        function settingColor(decorateModuleId,moduleType){
            var titleName =""
            if("6" == moduleType){
                titleName="限时秒杀背景色"
            }
            if("15" == moduleType){
                titleName="设置领券秒杀背景色";
            }
            $.ligerDialog.open({
                width: 500,
                height:600,
                title: titleName,
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/activityAreaNew/settingColor.shtml?decorateModuleId=" + decorateModuleId+"&moduleType="+moduleType,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });

        }
        /*设置秒杀时间点*/
        function settingTime(decorateModuleId){
            $.ligerDialog.open({
                width: 500,
                height:600,
                title: "设置秒杀时间点",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/activityAreaNew/settingTime.shtml?decorateModuleId=" + decorateModuleId,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });

        }

        function selectModuleItem() {
                var decorateInfoId = $("#decorateInfoId").val();
                var decorateAreaId = $("#selectCateModuleItem").attr("decorateAreaId");
                var moduleType = $("#selectCateModuleItem").attr("moduleType");
                var decorateModuleId = $("#selectCateModuleItem").attr("decorateModuleId");
                var category = $("#selectCateModuleItem").val();
                var index = $("#decorateModuleDiv div[name='moduleDiv']").index($(this).closest("div[name='moduleDiv']"));
                var seqNo = Number(index)+1;
                var isPreSell = $("#isPreSell").val();
                var title = "编辑商品模块";
                $.ligerDialog.open({
                    height: $(window).height()*0.9,
                    width: $(window).width()*0.9,
                    title: title,
                    name: "INSERT_WINDOW",
                    url: "${pageContext.request.contextPath}/customPage/editDecorateModule.shtml?decorateAreaId=" + decorateAreaId+"&moduleType="+moduleType+"&seqNo="+seqNo+"&decorateModuleId="+decorateModuleId+"&decorateInfoId="+decorateInfoId+"&isPreSell="+isPreSell+"&category="+category+"&showType=${showType}",
                    <%--url: "${pageContext.request.contextPath}/customPage/editDecorateModule.shtml?moduleType="+moduleType+"&decorateModuleId="+decorateModuleId+"&isPreSell="+isPreSell+"&category="+category+"&showType=${showType}",--%>
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
<body style="padding: 0px;">
<form id="dataForm" runat="server" enctype="multipart/form-data" method="post" >
    <input type="hidden" id="decorateInfoId" name="decorateInfoId" value="${decorateInfoId}">
    <input type="hidden" id="topPic" name="topPic" value="${activityArea.topPic}">
    <input type="hidden" id="isPreSell" name="isPreSell" value="${isPreSell}">
    <div class="search-pannel" style="overflow-y: auto;">
        <div class="search-tr"  >
            <div style="display: inline-flex;">
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入分区" onclick="editDecorateArea();">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入图片模块" onclick="insertPicModule();">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入商品模块" onclick="insertProductModule();">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 80px;height: 30px;cursor: pointer;" value="插入特卖模块" onclick="insertActivityModule();">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 120px;height: 30px;cursor: pointer;" value="插入banner海报图" onclick="insertTopPic(this);">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 120px;height: 30px;cursor: pointer;" value="插入秒杀模块" onclick="insertSecondKillModule(this);">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 120px;height: 30px;cursor: pointer;" value="插入必购TOP榜模块" onclick="insertNeedBuyModule(this);">
                </div>
            </div>
        </div>

        <div class="search-tr"  >
            <div style="display: inline-flex;">
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 120px;height: 30px;cursor: pointer;" value="插入固定底部栏模块" onclick="insertBottomBarModule(this);">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 120px;height: 30px;cursor: pointer;" value="插入固定顶部栏模块" onclick="insertTopFieldModule(this);">
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 120px;height: 30px;cursor: pointer;" value="插入滑动图片模块" onclick="insertSlidePicModule(this);">
                </div>
                <div style="padding-left: 10px;">
                    <input style="position:absolute; opacity:0;width: 110px;" type="file" id="topPicFile" name="topPicFile"  multiple  onchange="ajaxFileUploadImg('topPicFile');" />
                    <input type="button" style="width: 120px;height: 30px;cursor: pointer;" value="批量上传图片" >
                </div>
                <div style="padding-left: 10px;">
                    <input type="button" style="width: 120px;height: 30px;cursor: pointer;" value="插入领券秒杀模块" onclick="insertCouponSecondKillModule(this);">
                </div>
                <div style="padding-left: 10px;">
                    <a href="${mUrl}${templetSuffix}?activityAreaId=${activityArea.id}" style="font-size: 20px;" target="_blank">预览</a>
                </div>
            </div>
        </div>
        <c:if test="${not empty activityArea.topPic}">
            <div class="search-tr" id="topPicDiv">
                <div style="position: relative;width: 750px;height: 400px;border:1px solid #000" name="moduleDiv" decorateModuleId = "${decorateModuleCustom.id}">
                    <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                        <div style="float: left;padding-top:7px;padding-left:12px;width: 88px;height: 23px;background-color: gray;color: white;">
                            banner海报图
                        </div>
                        <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" onclick="delTopPic(${activityArea.id},this);">
                        <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" onclick="editTopPic(${activityArea.id})" >
                        <div style="margin-top: 100px;margin-left: 70px;">
                            图片中需包含专场Logo、利益点、专场主题 <br>尺寸：宽为750像素，高度不限，大小：200Kb以内
                        </div>
                    </div>
                    <img src="${pageContext.request.contextPath}/file_servelt${activityArea.topPic}" id="topPicImg" style="max-width: 750px;max-height: 400px;">
                </div>
            </div>
        </c:if>
        <div class="search-tr" id="activityTimeDiv">
            <div style="padding: 15px;width:720px;border:1px solid #000">
                活动时间：
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${activityArea.activityBeginTime}"/>  ~  <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${activityArea.activityEndTime}"/>
            </div>
        </div>
        <div class="search-tr">
            <c:forEach items="${decorateAreaCustoms}" var="decorateAreaCustom" varStatus="status">
                <div name="eachDecorateAreaDiv" decorateAreaId="${decorateAreaCustom.id}" style="width: 760px;border: 1px solid #000;">
                    <div style="margin-left: 4px;margin-top: 4px;position: relative;width: 750px;height: 100px;" name="decorateAreaDiv" decorateAreaId = "${decorateAreaCustom.id}">
                        <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                            <div style="float: left;padding-top:7px;padding-left:12px;width: 40px;height: 23px;background-color: #8B1C62;color: white;">
                                分区
                            </div>
                            <c:if test="${status.index!=0}">
                                <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="areaMoveUp" decorateAreaId="${decorateAreaCustom.id}">
                            </c:if>
                            <c:if test="${!status.last}">
                                <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="areaMoveDown" decorateAreaId="${decorateAreaCustom.id}">
                            </c:if>
                            <input type="button" style="margin-left: 20px;width: 90px;height: 30px;cursor: pointer;" value="插入图片模块" onclick="insertPicModuleInArea(this,${decorateAreaCustom.id})" >
                            <input type="button" style="margin-left: 20px;width: 90px;height: 30px;cursor: pointer;" value="插入商品模块" onclick="insertProductModuleInArea(this,${decorateAreaCustom.id})" >
                            <input type="button" style="margin-left: 20px;width: 90px;height: 30px;cursor: pointer;" value="插入特卖模块" onclick="insertActivityModuleInArea(this,${decorateAreaCustom.id})" >
                            <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="delDecorateArea(${decorateAreaCustom.id},this);">
                            <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" onclick="editDecorateArea(${decorateAreaCustom.id})">
                            <div style="margin-top: 20px;margin-left: 40%;">分区（${decorateAreaCustom.id}）${decorateAreaCustom.areaName}</div>
                        </div>
                    </div>
                    <br>
                    <c:forEach items="${decorateAreaCustom.decorateModuleCustoms}" var="decorateModuleCustom" varStatus="index">
                        <c:if test="${decorateModuleCustom.moduleType == 1}">
                            <div style="margin-left: 4px;position: relative;width: 750px;height: 400px;border:1px solid #000" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: blue;color: white;">
                                        图片模块
                                    </div>
                                    <c:if test="${index.index!=0}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <c:if test="${!index.last}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="1" decorateAreaId="${decorateAreaCustom.id}" decorateModuleId="${decorateModuleCustom.id}">
                                    <div style="margin-top: 100px;margin-left: 70px;">自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽为750像素，高度不限，大小：200Kb以内</div>
                                </div>
                                <img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="max-width: 750px;max-height: 400px;">
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${decorateModuleCustom.moduleType == 2}">
                            <div style="margin-left: 4px;position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: green;color: white;">商品模块</div>
                                    <c:if test="${index.index!=0}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <c:if test="${!index.last}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="2" decorateAreaId="${decorateAreaCustom.id}" decorateModuleId="${decorateModuleCustom.id}">
                                    <input type="hidden" id="selectCateModuleItem" onclick="selectModuleItem()" value="" moduleType="2" decorateAreaId="${decorateAreaCustom.id}" decorateModuleId="${decorateModuleCustom.id}">
                                    <div style="margin-top: 100px;margin-left: 70px;">
                                        共选${decorateModuleCustom.count}个商品，要求显示${decorateModuleCustom.showNum}个商品
                                    </div>
                                </div>
                                <img src="" style="width: 100%;">
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${decorateModuleCustom.moduleType == 3}">
                            <div style="margin-left: 4px;position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: #5500FF;color: white;">特卖模块</div>
                                    <c:if test="${index.index!=0}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <c:if test="${!index.last}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="3" decorateAreaId="${decorateAreaCustom.id}" decorateModuleId="${decorateModuleCustom.id}">
                                    <div style="margin-top: 100px;margin-left: 70px;">
                                        共选${decorateModuleCustom.count}个特卖，要求显示${decorateModuleCustom.showNum}个特卖
                                    </div>
                                </div>
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${decorateModuleCustom.moduleType == 6}">
                            <div style="margin-left: 4px;position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: #5500FF;color: white;">秒杀模块</div>
                                    <c:if test="${dataSource == 1}"><div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: #5500FF;color: white;">限时秒杀</div></c:if>
                                    <c:if test="${dataSource == 2}"><div style="float: left;padding-top:7px;padding-left:12px;width: 60px;height: 23px;background-color: #5500FF;color: white;">会场秒杀</div></c:if>
                                    <c:if test="${index.index!=0}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <c:if test="${!index.last}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="颜色设置" name="colorSetting"  onclick="settingColor(${decorateModuleCustom.id},6);">
                                    <div style="margin-top: 50px;margin-left: 20px;">
                                        <c:forEach items="${decorateModuleCustom.seckillTimes}" var="seckillTime">
                                            <input type="button" style="width: 70px;height: 30px;cursor: default;" value="${seckillTime.startHours}:${seckillTime.startMin}">&nbsp;&nbsp;
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${decorateModuleCustom.moduleType == 7}">
                            <div style="margin-left: 4px;position: relative;width: 750px;min-height: 200px;border:1px solid #000;overflow-y: auto;" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 65px;height: 23px;background-color: #5500FF;color: white;">TOP榜模块</div>
                                    <c:if test="${index.index!=0}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <c:if test="${!index.last}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="新增" onclick="add(${decorateModuleCustom.id},this);">

                                    <div style="margin-top: 50px;margin-left: 20px;" id="${decorateModuleCustom.id}bgModuleCategoryDiv">
                                        <c:if test="${empty decorateModuleCustom.bgModuleCategoryCustoms}">
                                            <div style="margin-top: 5px;" id=""><input name="categoryName" placeholder="请输入类目名称" style="width: 100px;" id="" decorateModuleId="${decorateModuleCustom.id}" onblur="categoryNameBlur(this);" value="">&nbsp;&nbsp;商品ID&nbsp;&nbsp;<input name="productCode" placeholder="输入商品ID按,隔开" id="" decorateModuleId="${decorateModuleCustom.id}" onblur="productCodeBlur(this);" value="" style="width: 450px;">&nbsp;&nbsp;<a href="javascript:;" id="" onclick="deleteBgModuleCategory(this);">删除</a></div>
                                        </c:if>
                                        <c:forEach items="${decorateModuleCustom.bgModuleCategoryCustoms}" var="bgModuleCategoryCustom" varStatus="idx">
                                            <div style="margin-top: 5px;" id="${bgModuleCategoryCustom.id}"><input name="categoryName" placeholder="请输入类目名称" style="width: 100px;" id="${bgModuleCategoryCustom.id}" decorateModuleId="${decorateModuleCustom.id}" onblur="categoryNameBlur(this);" value="${bgModuleCategoryCustom.categoryName}">&nbsp;&nbsp;商品ID&nbsp;&nbsp;<input name="productCode" placeholder="输入商品ID按,隔开" id="${bgModuleCategoryCustom.id}" decorateModuleId="${decorateModuleCustom.id}" onblur="productCodeBlur(this);" value="${bgModuleCategoryCustom.productCodes}" style="width: 450px;">&nbsp;&nbsp;<c:if test="${idx.index!=0}"><a href="javascript:;" onclick="moveUp(${bgModuleCategoryCustom.id},this);">上移</a></c:if>&nbsp;&nbsp;<c:if test="${!idx.last}"><a href="javascript:;" onclick="moveDown(${bgModuleCategoryCustom.id},this);">下移</a></c:if>&nbsp;&nbsp;<a href="javascript:;" id="${bgModuleCategoryCustom.id}" onclick="deleteBgModuleCategory(this);">删除</a></div>
                                        </c:forEach>
                                    </div>

                                </div>
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${decorateModuleCustom.moduleType == 8}">
                            <div moduleType="8" style="margin-left: 4px;position: relative;width: 750px;height: 400px;border:1px solid #000" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 90px;height: 23px;background-color: blue;color: white;">
                                        固定底部栏模块
                                    </div>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="8" decorateAreaId="${decorateAreaCustom.id}" decorateModuleId="${decorateModuleCustom.id}">
                                </div>
                                <img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="max-width: 750px;max-height: 400px;">
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${decorateModuleCustom.moduleType == 9}">
                            <div style="margin-left: 4px;position: relative;width: 750px;height: 400px;border:1px solid #000" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 90px;height: 23px;background-color: blue;color: white;">
                                        滑动图片模块
                                    </div>
                                    <c:if test="${index.index!=0}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <c:if test="${!index.last}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="9" decorateAreaId="${decorateAreaCustom.id}" decorateModuleId="${decorateModuleCustom.id}">
                                    <div style="margin-top: 100px;margin-left: 70px;">自定义图片，可以加超链接。&nbsp;&nbsp;尺寸：宽大于1242像素，高度不限，大小：500Kb以内</div>
                                </div>
                                <img src="${pageContext.request.contextPath}/file_servelt${decorateModuleCustom.pic}" style="max-width: 750px;max-height: 400px;">
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${decorateModuleCustom.moduleType == 10}">
                            <div moduleType="10" style="overflow-y: auto;margin-left: 4px;position: relative;width: 750px;height: 400px;border:1px solid #000" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 90px;height: 23px;background-color: blue;color: white;">
                                        顶部栏模块
                                    </div>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="新增" onclick="addTopFieldModuleField(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="编辑" name="editDecorateModule" moduleType="10" decorateAreaId="${decorateAreaCustom.id}" decorateModuleId="${decorateModuleCustom.id}">
                                </div>
                                <div style="margin-top: 40px;margin-left: 20px;" >
                                    字体颜色：${decorateModuleCustom.fieldFontColor} &nbsp;&nbsp;选中字体颜色：${decorateModuleCustom.fieldSelectFontColor}&nbsp;&nbsp; 栏位背景颜色：${decorateModuleCustom.fieldBgColor}&nbsp;&nbsp;展开字体颜色：${decorateModuleCustom.openFontColor}<br>
                                    展开栏位背景颜色：${decorateModuleCustom.openFieldBgColor}&nbsp;&nbsp;展开按钮背景颜色:${decorateModuleCustom.openBtnBgColor}&nbsp;&nbsp;展开按钮箭头颜色：<c:if test="${decorateModuleCustom.openBtnArrowColor == 1}">黑色</c:if><c:if test="${decorateModuleCustom.openBtnArrowColor == 2}">白色</c:if>
                                </div>
                                <div style="margin-top: 50px;margin-left: 20px;" id="topFieldModuleDiv">
                                        <%-- 							<c:if test="${empty decorateModuleCustom.topFieldModuleFields}">
                                                                        <div style="margin-top: 5px;" id=""><input name="fieldName" id="" placeholder="请输入栏目名称" style="width: 100px;"  decorateModuleId="${decorateModuleCustom.id}" onblur="fieldNameBlur(this);" value="">&nbsp;&nbsp;分区ID&nbsp;&nbsp;<input name="linkDecorateAreaId" id="" placeholder="请输入分区ID" id="" decorateModuleId="${decorateModuleCustom.id}" onblur="linkDecorateAreaIdBlur(this);" value="" style="width: 450px;">&nbsp;&nbsp;<a href="javascript:;" onclick="deleteTopFieldModule(this);">删除</a></div>
                                                                    </c:if> --%>
                                    <c:forEach items="${decorateModuleCustom.topFieldModuleFields}" var="topFieldModuleField" varStatus="idx">
                                        <div style="margin-top: 5px;" id="${topFieldModuleField.id}"><input name="fieldName" placeholder="请输入栏目名称" style="width: 100px;" id="${topFieldModuleField.id}" decorateModuleId="${decorateModuleCustom.id}" onblur="fieldNameBlur(this);" value="${topFieldModuleField.fieldName}">&nbsp;&nbsp;分区ID&nbsp;&nbsp;<input name="linkDecorateAreaId" placeholder="请输入分区ID" id="${topFieldModuleField.id}" decorateModuleId="${decorateModuleCustom.id}" onblur="linkDecorateAreaIdBlur(this);"<c:if test="${topFieldModuleField.linkDecorateAreaId != -1}"> value="${topFieldModuleField.linkDecorateAreaId}" </c:if>  style="width: 450px;">&nbsp;&nbsp;
                                            <a href="javascript:;" id="${topFieldModuleField.id}" onclick="deleteTopFieldModule(this);">删除</a>
                                            <c:if test="${idx.index!=0}">
                                                <a href="javascript:moveUpTopFieldModuleField(${topFieldModuleField.id});" name="moveUp"  id="${topFieldModuleField.id}topFieldModuleField">上移</a>
                                            </c:if>
                                            <c:if test="${!idx.last}">
                                                <a href="javascript:moveDownTopFieldModuleField(${topFieldModuleField.id});" name="moveDown"  id="${topFieldModuleField.id}topFieldModuleField">下移</a>
                                            </c:if>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <br>
                        </c:if>

                        <c:if test="${decorateModuleCustom.moduleType == 15}">
                            <div style="margin-left: 4px;position: relative;width: 750px;height: 200px;border:1px solid #000" name="moduleDiv${decorateAreaCustom.id}" decorateModuleId = "${decorateModuleCustom.id}">
                                <div style="position: absolute; top:0; left: 0;width: 100%; height: 30px;">
                                    <div style="float: left;padding-top:7px;padding-left:12px;width: 75px;height: 23px;background-color: #5500FF;color: white;">领券秒杀模块</div>
                                    <c:if test="${index.index!=0}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="上移" name="moveUp" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                    <c:if test="${!index.last}">
                                        <input type="button" style="margin-left: 20px;float: left;width: 70px;height: 30px;cursor: pointer;" value="下移" name="moveDown" decorateModuleId="${decorateModuleCustom.id}" decorateAreaId="${decorateAreaCustom.id}">
                                    </c:if>
                                        <%--<input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="颜色设置" onclick="add(${decorateModuleCustom.id},this);">--%>
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="删除" name="delete" onclick="del(${decorateModuleCustom.id},this);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="颜色设置" name="colorSetting"  onclick="settingColor(${decorateModuleCustom.id},15);">
                                    <input type="button" style="margin-left: 20px;float: right;width: 70px;height: 30px;cursor: pointer;" value="时间" name="timeSetting"  onclick="settingTime(${decorateModuleCustom.id});" >
                                    <div style="margin-top: 50px;margin-left: 20px;">
                                        <c:forEach items="${decorateModuleCustom.couponModuleTimeList}" var="couponModuleTime">
                                            <input type="button" style="width: 70px;height: 30px;cursor: default;" value="${couponModuleTime.startHours}:${couponModuleTime.startMin}">&nbsp;&nbsp;
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <br>
                        </c:if>

                    </c:forEach>
                </div>
                <br>
            </c:forEach>
        </div>
    </div>
    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

</ul>
</body>