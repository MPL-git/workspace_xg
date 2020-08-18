<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>信息中心</title>
</head>
<body>
<div class="container body-container">
    <div class="content-left">
        <div class="panel panel-default">
            <div id="sidebar-menu2" class="main_menu_side hidden-print main_menu">
                <div class="menu_section">
                    <ul class="nav side-menu">
                        <c:forEach var="menu" items="${catalogList}">
                            <c:if test="${fun:length(menu.get('infoList')) > 0}">
                                <li><a>${menu.frontName}<span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <c:forEach var="subMenu" items="${menu.get('infoList')}">
                                            <li onclick="getContent2('${subMenu.id}')"><a>${subMenu.title }</a></li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:if>

                            <c:if test="${fun:length(menu.get('infoList')) <= 0}">
                                <li><a>${menu.frontName}</a></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>

            </div>
        </div>
    </div>
    <div class="main-content" id="main-content2">
    </div>
</div>


<script type="text/javascript">

    function getContent2(id){
        var url = "${ctx}/info/content?id="+id;
        $.ajax({
            url: url,
            type: "GET",
            success: function(data){
                $("#main-content2").html(data);
            },
            error:function(){
                swal('页面不存在');
            }
        });

    }

    $(function () {
        $("#sidebar-menu2").find('a').on('click', function(ev) {
            var $li = $(this).parent();

            if ($li.is('.active')) {
                $li.removeClass('active active-sm');
                $('ul:first', $li).slideUp(function() {
                    //setContentHeight();
                });
            } else {
                // prevent closing menu if we are on child menu
                if (!$li.parent().is('.child_menu')) {
                    $SIDEBAR_MENU.find('li').removeClass('active active-sm');
                    $SIDEBAR_MENU.find('li ul').slideUp();
                }else{
                    $li.siblings().removeClass('active');
                }

                $li.addClass('active');

                $('ul:first', $li).slideDown(function() {
                    //setContentHeight();
                });
            }
        });

        // check active menu
        $SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass('current-page');

        $SIDEBAR_MENU.find('a').filter(function () {
            return this.href == CURRENT_URL;
        }).parent('li').addClass('current-page').parents('ul').slideDown(function() {
            setContentHeight();
        }).parent().addClass('active');

        //setContentHeight();

        // fixed sidebar
        if ($.fn.mCustomScrollbar) {
            $('.menu_fixed').mCustomScrollbar({
                autoHideScrollbar: true,
                theme: 'minimal',
                mouseWheel:{ preventDefault: true }
            });
        }
    })
</script>
</body>
</html>
