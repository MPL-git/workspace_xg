<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>店铺装修</title>
    <link href="${pageContext.request.contextPath}/static/css/shopDecorate.css" rel="stylesheet" type="text/css"/>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        li {
            list-style: none;
        }

        .list li {
            float: left;
            min-height: 10px;
            margin: 0 0 0 20px;
        }

        .list img {
            display: block;
            width: 100%;
        }

        .btn-icon {
            margin: 10px auto;
            width: 84px;
            display: block;
        }

        html {
            width: auto !important;
        }

        .right-img {
            width: 36px;
            height: 36px;
            position: relative;
            top: 10px;
            left: 10px;
        }

        .title-h1 {
            font-size: 36px;
            display: flex;
        }


    </style>
</head>
<body>
<div class="x_panel container-fluid">
    <div class="row content-title">
        <div class="t-title">
            店铺装修
        </div>
    </div>
    <div class="" style="display: flex;">
        <div class="btn-total">
            <input type="hidden" id="decoratePageId" value="${data.decoratePageId}">
            <input type="hidden" id="decorateAreaId" value="${data.decorateAreaId}">
            <input type="hidden" id="mchtId" value="${mchtId}">
            <button type="button" class="btn btn-info btn-icon" id="changeBanner" onclick="bannerDialog();">头部背景
            </button>
            <button type="button" class="btn btn-info btn-icon" id="changeShopLogo" onclick="shopLogoDialog();">头像图片
            </button> 
            <button type="button" class="btn btn-info btn-icon" id="addModule" onclick="addShopModule();">新增模块</button>
        </div>
        <div class="containers">
            <main class="naiKe">
                <header id="header"
                        <c:if test="${data.banner != null && data.banner != ''}">
                            style="background-image:url(${ctx}/file_servelt${data.banner});height: 258px"
                        </c:if>
                        <c:if test="${data.banner == null || data.banner == ''}">
                            style="background-image:url(${ctx}/static/images/shopDecorate/bgimg.png); height: 258px"
                        </c:if>
                >
                    <nav>
                        <img  style="height: 258px" src="${pageContext.request.contextPath}/static/images/shopDecorate/bannerBoss.png" alt="">
                    </nav>

                    <%--<div class="headImg" id="headImg" style="background:url(${pageContext.request.contextPath}/static/images/shopDecorate/shangpin.png)">--%>
                    <div class="headImg" style="position: relative; z-index: 55;top: -263px;left: 20px">
                   <%--       <div class="shopImg">
                            <c:if test='${data.shopLogo != null && data.shopLogo != ""}'>
                                <img name="shopLogoImg" src="${ctx}/file_servelt${data.shopLogo}" alt="">
                            </c:if>
                            <c:if test='${data.shopLogo==null||data.shopLogo==""}'>
                                <img name="shopLogoImg"
                                     src="${pageContext.request.contextPath}/static/images/shopDecorate/header.png"
                                     alt="">
                            </c:if>
                        </div>  --%>

                        <div class="title">
                            <div class="title-h1">
                                <p>${data.shopName}</p>
                                <img class="right-img"
                                     src="${pageContext.request.contextPath}/static/images/shopDecorate/click.png"
                                     alt="">
                            </div>
                           <div><p style="margin: 0;color:gray;font-size:20px"" >商品描述:<span style="color:red">${productScore}</span>&nbsp;&nbsp;
                            														卖家服务:<span style="color:red">${mchtScore}</span>&nbsp;&nbsp;
                            														物流服务:<span style="color:red">${wuliuScore}</span></p></div>

                        </div>
                     
                    </div>
                </header>


                <div class="main">
                    <c:forEach var="module" items="${data.moduleList}" varStatus="status">
                    <div class="module-header for-search"
                         data-moduleId="${module.id}">
                        <img src="${ctx}/file_servelt${module.pic}" class="bg-img">
                        <div class="btns-img flex ac jc">
                            <c:if test="${status.index != null && status.index != 0}">
                                <button onclick="changeSeqNo(this,${module.id},0);">上移</button>
                            </c:if>
                            <c:if test="${status.index != null && (status.index + 1) != fn:length(data.moduleList)}">
                                <button onclick="changeSeqNo(this,${module.id},1);">下移</button>
                            </c:if>
                            <button onclick="moduleDialog(${module.id}); " class="btn-1">编辑</button>
                            <button onclick="deleteModule(this, ${module.id});" class="btn-2">删除</button>
                        </div>
                    </div>
                    </c:forEach>

                    <div id="moduleHeader" class="module-header" style="display: none">
                        <button onclick="moduleDialog();">编辑</button>
                        <button onclick="deleteModule(this);">删除</button>
                        <div class="textSpan">新增模块请编辑上传</div>
                    </div>

                    <div class="content" id="content">
                        <c:if test="${data != null && data.productList != null}">
                            <c:forEach var="product" items="${data.productList}">
                                <div class="shangpin item">
                                    <div class="shangpinImg">
                                        <c:if test="${product.pic.indexOf('http') >= 0}">
                                            <img src="${product.pic}" alt="">
                                        </c:if>
                                        <c:if test="${product.pic.indexOf('http') < 0}">
                                            <img src="${ctx}/file_servelt${product.pic}" alt="">
                                        </c:if>
                                    </div>
                                    <div class="shangpinName">
                                        <div class="biaoqian">${product.productBrandName}</div>
                                        <span>${product.name}</span>
                                    </div>
                                    <div class="price">
                                        <span class="red-color shopPrice">￥${product.mallPrice}</span>
                                        <del class="c9">${product.tagPrice}</del>
                                        <c:if test="${data.rule != null && data.rule != ''}">
                                            <div class="shopActive red-color">店铺活动</div>
                                        </c:if>
                                    </div>
                                    <c:if test="${product.totalCommentCount > 0}">
                                        <div class="shopPingjia">
                                            <span class="pr20">${product.totalCommentCount}条评价</span>
                                            <span>${product.goodCommentRate}好评</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${data.rule != null && data.rule != ''}">
                                        <div class="youhui">
                                            <span class="red-color">${data.rule}</span>
                                        </div>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="list" id="list"></div>

                    <div class="foot">
                        <div class="shopimg">
                            <c:if test='${data.shopLogo != null && data.shopLogo != ""}'>
                                <img name="shopLogoImg" src="${ctx}/file_servelt${data.shopLogo}" alt="">
                            </c:if>
                            <c:if test='${data.shopLogo==null||data.shopLogo==""}'>
                                <img name="shopLogoImg"
                                     src="${pageContext.request.contextPath}/static/images/shopDecorate/header.png"
                                     alt="">
                            </c:if>
                        </div>
                        <div class="shopimg">
                            <div class="gouwu">
                                <img src="${pageContext.request.contextPath}/static/images/shopDecorate/gouwu.png"
                                     alt="">
                            </div>
                            <span>全部商品</span>
                        </div>
                        <div class="shopimg">
                            <div class="gouwu">
                                <img src="${pageContext.request.contextPath}/static/images/shopDecorate/liebiao.png"
                                     alt="">
                            </div>
                            <span>宝贝分类</span>
                        </div>
                        <div class="shopimg">
                            <div class="gouwu">
                                <img src="${pageContext.request.contextPath}/static/images/shopDecorate/liaotian.png"
                                     alt="">
                            </div>
                            <span>联系客服</span>
                        </div>
                    </div>
            </main>
        </div>
        <div>
            <button type="button" class="btn btn-info btn-icon" id="preview" onclick="preview();">预览</button>
            <button type="button" class="btn btn-info btn-icon" id="announce" onclick="announceDecorate();">发布</button>
            <button type="button" class="btn btn-info btn-icon" id="restore" onclick="restoreDecorate();">还原</button>
        </div>
    </div>

</div>

<!-- 查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static"></div>

<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/viewer.min.js" type="text/javascript"></script>

<script type="text/javascript">
    var waterFall = {
        content: document.getElementById('content'),    //存放内容的容器
        list: document.getElementById('list'), //将要展示的列表容器

        setOptions: function (options) {
            options = options || {};
            this.colNum = options.num || 2;   //显示的列数，默认显示3列
            this.colWidth = options.width || 330;   //每列的宽度
        },

        //构建列数
        setColumn: function () {
            var self = this;
            var html = '';
            for (var i = 0, l = self.colNum; i < l; i++) {
                html += '<li style="width:' + self.colWidth + 'px;"></li>';
            }
            self.list.innerHTML = html;

            self.column = self.list.getElementsByTagName('li');
        },

        //计算最小高度的列
        setMinHeightCol: function () {
            var self = this;
            var heiArray = [];
            var minIndex = 0, index = 1;
            for (var i = 0, l = self.colNum; i < l; i++) {
                heiArray[i] = self.column[i].offsetHeight;
            }
            while (heiArray[index]) {
                if (heiArray[index] < heiArray[minIndex]) {
                    minIndex = index;
                }
                index++;
            }
            return self.column[minIndex];
        },
        //填充内容
        setCont: function (cnt) {
            var self = this;
            self.setMinHeightCol().appendChild(cnt);
            if (!!self.content.children[0]) {
                self.setCont(self.content.children[0]);
            }
        },

        initFunc: function (options) {
            var self = this;
            self.setOptions(options);
            self.setColumn();
            self.setCont(self.content.children[0]);
        }
    };
    $(function () {
        <c:if test="${data != null && data.productList != null && fn:length(data.productList) > 0}">
            waterFall.initFunc();
        </c:if>
    })
</script>
<script type="text/javascript">
    // 编辑头部背景
    function bannerDialog() {
        var decoratePageId = $("#decoratePageId").val();
        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/bannerEdit?decoratePageId=" + decoratePageId,
            type: 'GET',
            success: function (data) {
                $("#viewModal").html(data);
                $("#viewModal").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    // 编辑头部图片
    function shopLogoDialog() {
        var decoratePageId = $("#decoratePageId").val();
        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/shopLogoEdit?decoratePageId=" + decoratePageId,
            type: 'GET',
            success: function (data) {
                $("#viewModal").html(data);
                $("#viewModal").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    // 发布
    function announceDecorate() {
        var decoratePageId = $("#decoratePageId").val();
        var submitFlag = true;
        if (!submitFlag) {
            swal("请不要重复提交");
            submitFlag = false;
            return false;
        }
        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/announceDecorate",
            type: 'POST',
            dataType: 'json',
            data: {
                'decoratePageId': decoratePageId
            },
            timeout: 30000,
            success: function (data) {
                if (data.returnCode === "0000") {
                    swal({
                        title: "发布成功！",
                        type: "success",
                        confirmButtonText: "确定"
                    });
                } else {
                    swal({
                        title: "发布失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
                submitFlag = true;

            },
            error: function () {
                swal({
                    title: "发布失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
        });
    }

    // 还原
    function restoreDecorate() {
        var decoratePageId = $("#decoratePageId").val();
        var submitFlag = true;
        if (!submitFlag) {
            swal("请不要重复提交");
            submitFlag = false;
            return false;
        }
        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/restoreDecorate",
            type: 'POST',
            dataType: 'json',
            data: {
                'decoratePageId': decoratePageId
            },
            timeout: 30000,
            success: function (data) {
                if (data.returnCode === "0000") {
                    submitFlag = true;
                    swal({
                        title: "还原成功！",
                        type: "success",
                        confirmButtonText: "确定"
                    }, function (e) {
                        getContent("${ctx}/shopDecoratePageDraft/decorateIndex")
                    });
                } else {
                    swal({
                        title: "还原失败！",
                        type: "error",
                        confirmButtonText: "确定"
                    });
                }
            },
            error: function () {
                swal({
                    title: "还原失败！",
                    type: "error",
                    confirmButtonText: "确定"
                });
            }
        });
    }

    // 编辑模块
    function moduleDialog(moduleId) {
        var decorateAreaId = $("#decorateAreaId").val();
        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/moduleEdit",
            data: {
                'shopModuleId': moduleId,
                'shopDecorateAreaId': decorateAreaId
            },
            type: 'GET',
            success: function (data) {
                $("#viewModal").html(data);
                $("#viewModal").modal();
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    // 删除模块
    function deleteModule(_this, moduleId) {
        swal({
            title: "确认删除该模块？",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: false
        }, function (isConfirm) {
            if (isConfirm) {
                confirmDelete(_this, moduleId);
            }
        });
    }

    // 确认删除
    function confirmDelete(_this, moduleId) {
        if (!moduleId) {
            $(_this).parent().remove();
            swal("删除成功");
            return
        }
        var submitFlag = true;
        if (!submitFlag) {
            swal("请不要重复提交");
            submitFlag = false;
            return false;
        }
        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/deleteModule",
            data: {
                'moduleId': moduleId
            },
            type: 'GET',
            success: function (data) {
                if (data.returnCode === "0000") {
                    $(_this).parent().parent().remove();
                    swal("删除成功");
                } else {
                    swal("删除失败");
                }
                submitFlag = true;
            },
            error: function () {
                swal('页面不存在');
            }
        });
    }

    // 新增模块
    function addShopModule() {
        var moduleHeaderDiv = $("#moduleHeader");
        var cloneModule = moduleHeaderDiv.clone(true);
        cloneModule.removeAttr("id");
        cloneModule.removeAttr("style");
        cloneModule.addClass("for-search");
        if ($("div.for-search").length > 0) {
            $("div.for-search:last").after(cloneModule);
        } else {
            $("#moduleHeader").after(cloneModule);
        }
    }

    // 上下移 flag 0:上移，1:下移
    function changeSeqNo(_this, moduleId, flag) {
        var beChangeModuleId;
        if (flag === 0) {
            //上移
            beChangeModuleId = $(_this).parent().parent().prev().attr("data-moduleId");
        } else if (flag === 1) {
            //下移
            beChangeModuleId = $(_this).parent().parent().next().attr("data-moduleId");
        }

        if (!beChangeModuleId) {
            swal("操作错误,请刷新页面重新操作");
            return false;
        }

        var submitFlag = true;
        if (!submitFlag) {
            swal("请不要重复提交");
            submitFlag = false;
            return false;
        }
        $.ajax({
            url: "${ctx}/shopDecoratePageDraft/changeSeqNo",
            data: {
                'firstModuleId': moduleId,
                'secondModuleId': beChangeModuleId
            },
            type: 'GET',
            success: function (data) {
                if (data.returnCode === "0000") {
                    swal({
                        title: "操作成功！",
                        type: "success",
                        confirmButtonText: "确定"
                    }, function (e) {
                        getContent("${ctx}/shopDecoratePageDraft/decorateIndex")
                    });
                } else {
                    swal("操作失败");
                }
                submitFlag = true;
            },
            error: function () {
                swal('操作失败');
            }
        });
    }

    // 预览
    function preview() {
        var mchtId = $("#mchtId").val();
        window.open("${previewServerUrl}/webApp/xgbuy/views/index.html?redirect_url=seller/index.html?mchtId=" + mchtId + "&preview=1", "_blank");
    }
</script>
</body>
</html>
