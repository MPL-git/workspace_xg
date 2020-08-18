<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>

<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
    <script type="text/javascript">


        //修改排序值
        function saveSeqNo(_this,id){
            /*var testNumber = /^[0-9]*[1-9][0-9]*$/;*/
            var testNumber =  /^([0]|[1-9][0-9]*)$/;
            var seqNo = $(_this).val().trim();
            if(!seqNo){
                seqNo = -1
            }else if(!testNumber.test(seqNo)){
                commUtil.alertError("排序请输入正整数");
                return false;

            }
            console.log(seqNo);
                $.ajax({
                    url : "${pageContext.request.contextPath}/activityAreaNew/saveSeqNo.shtml?id="+id+"&seqNo="+seqNo,
                    secureuri : false,
                    dataType : 'json',
                    cache : false,
                    async : false,
                    success : function(data) {
                        if ("0000" == data.returnCode) {
                            listModel.gridManager.reload();
                        }else{
                            $.ligerDialog.error(data.returnMsg);
                        }
                    },
                    error : function() {
                        $.ligerDialog.error('操作超时，请稍后再试！');
                    }
                });
        }


        //修改上下架状态
        function changeModuleStatus(id,status) {
                    $.ajax({
                        type: 'post',
                        url: '${pageContext.request.contextPath}/activityAreaNew/changeModuleStatus.shtml?id='+id+'&status='+status,
                        data: {id : id},
                        dataType: 'json',
                        success: function(data) {
                            if( data.returnCode == 0000) {
                                window.location.reload();;
                            }else {
                                commUtil.alertError(data.returnMsg);
                            }
                        },
                        error: function(e) {
                            commUtil.alertError("系统异常请稍后再试");
                        }
                    });
        }



        //编辑模块
        function editModule(id) {
            $.ligerDialog.open({
                height: 500,
                width: 650,
                title: "编辑模块",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/activityAreaNew/editModule.shtml?id="+id,
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }

        //设置会场
        function settingUpVenue() {
            $.ligerDialog.open({
                height: 400,
                width: 550,
                title: "设置会场",
                name: "INSERT_WINDOW",
                url: "${pageContext.request.contextPath}/activityAreaNew/settingUpVenue.shtml?id=1",
                showMax: true,
                showToggle: false,
                showMin: false,
                isResize: true,
                slide: false,
                data: null
            });
        }


        var listConfig = {
            url:"/activityAreaNew/mainVenueModuleData.shtml",
            btnItems:[{ text: '设置会场', icon:'add', id:'add', dtype:'win', click:settingUpVenue }],
            listGrid:{ columns: [
                    { display: '排序', name: 'seqNo', align:'center', width: 100, render: function(rowdata, rowindex) {
                            if(rowdata.seqNo){
                                return '<input type="text" name="seqNo" style="width: 80px" value="'+rowdata.seqNo+'" onblur="saveSeqNo(this,'+rowdata.id+');">';
                            }else{
                                return '<input type="text" name="seqNo" style="width: 80px"  value="" onblur="saveSeqNo(this,'+rowdata.id+');">';
                            }
                        }},
                    { display: '模块名称', name:'name',width: 120},
                    { display: '默认模块图片', width: 120,render: function (rowdata, rowindex){
                            var h = "";
                            if(rowdata.defaultModulePic){
                                h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.defaultModulePic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
                            }
                            return h;
                        }},
                    { display: '选中模块图片', width: 120,render: function (rowdata, rowindex){
                            var h = "";
                            if(rowdata.selectedModulePic){
                                h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.selectedModulePic+"' width='100' height='50' onclick='viewerPic(this.src)'>";
                            }
                            return h;
                        }},
                    { display: '连接类型', width: 120, name: 'linkTyepe',render:function (rowdata,rowindex) {
                            if("1" == rowdata.linkType){
                                return "会场";
                            }else if("4" == rowdata.linkType){
                                return  "自建页面ID";
                            }else if("35" == rowdata.linkType){
                                return  "我的优惠券";
                            }else if("36" == rowdata.linkType){
                                return  "购物津贴";
                            }
                        }},
                    { display: '连接详情', name:'linkValue',width: 120},
                    { display: '修改时间', width: 120, name: 'updateDate',render: function(rowdata, rowindex) {
                            if (rowdata.updateDate!=null){
                                var updateDate=new Date(rowdata.updateDate);
                                return updateDate.format("yyyy-MM-dd hh:mm:ss");
                            }
                        }},
                    { display: '状态', width: 80, name: 'status',render:function(rowdata, rowindex){
                            if('0' == rowdata.status){
                                return "下架";
                            }else if ('1' == rowdata.status){
                                return "上架";
                            }

                        }
                        },
                    { display: '操作', width: 150,render: function(rowdata, rowindex) {
                            var html = [];
                            if (rowdata.status=='0'){//下架
                                html.push("<a href=\"javascript:editModule(" + rowdata.id + ");\">编辑</a>");
                                html.push("/")
                                html.push("<a href=\"javascript:changeModuleStatus(" + rowdata.id + ",'1');\">上架</a>");
                            }else{//上架
                                html.push("<a href=\"javascript:changeModuleStatus(" + rowdata.id + ",'0');\">下架</a>");
                            }
                            return html.join("&nbsp;&nbsp;");
                        }},
                ],
                showCheckbox : false,  //不设置默认为 true
                showRownumber:true//不设置默认为 true
            } ,
            container:{
                toolBarName:"toptoolbar",
                searchBtnName:"searchbtn",
                fromName:"dataForm",
                listGridName:"maingrid"
            }
        }

        function chaneGoods(){
            var temp = $("#whichProduct").val();
            if(temp==1){
                $("#artnos").hide();
                $("#goodIds").show();
            }
            if(temp==2){
                $("#artnos").show();
                $("#goodIds").hide();
            }

        }

    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">

</form>
<div id="maingrid" style="margin: 0; padding: 0"></div>
<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
