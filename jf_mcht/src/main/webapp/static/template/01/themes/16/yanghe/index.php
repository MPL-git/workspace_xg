<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Genanx</title>
    <link rel="stylesheet" href="../../../../../css/style.css">
    <link rel="stylesheet" href="../../../../../css/module.css">
    <link rel="stylesheet" href="../../../css/tmpl.css">
    <link rel="stylesheet" href="../css/theme.css">
    <script src="../../../../../js/lib/jquery-1.8.3.min.js"></script>
    <script src="../../../../../js/jquery.switchable.min.js"></script>
    <script src="../../../../../js/modules.js"></script>
</head>
<style>
.yjh-tab{ height: 35px; border-bottom: 1px solid #d2d2d2;}
.yjh-tab h3{ float: left; font-size: 16px; line-height: 35px; padding-right: 30px;}
.yjh-tab h3 span{ margin-left: 10px; font-size: 12px; font-weight: normal;}
.yjh-tab li{ float: left; line-height: 35px; font-size: 14px;}
.yjh-tab li a{ display: block; padding: 0 20px;}
.yjh-tab li.current{ border:1px solid #d2d2d2; border-bottom: 0; background: #fff; color: #008cd6;}
.yjh-tab li.current a{ color: #008cd6;}
.yjh-tabcon{ height: 281px; overflow: hidden; margin-bottom: 15px; background: #fff;}
.yjh-tb,.yjh-thead table{ width: 100%; background: #fff; line-height: 18px;}
.yjh-tb td,.yjh-tb th,.yjh-thead td{border: solid #eee; border-width: 1px 0 0 1px; padding: 8px; text-align: center; font-size: 14px; width: 140px;}
.yjh-thead thead tr{ background: #008cd6; color: #fff;}
.yjh-tb a{ color: #008cd6; text-decoration: underline;}
</style>
<script>
    $(function () {
        var tabItems = $('.yjh-tab li'),
                tabCons = $('.yjh-tabcon'),
                curTabcon;
        tabItems.on('mouseenter', function () {
            tabItems.removeClass('current');
            $(this).addClass('current');
            tabCons.hide();
            if(curTabcon){
                curTabcon.stopScroll && curTabcon.stopScroll();
            }
            curTabcon = $('#'+this.getAttribute('data-tabcon'))[0];
            curTabcon.style.display = "block";
            curTabcon.doScroll && curTabcon.doScroll();
        });


        tabCons.each(function () {
            var tabcon = this,timer,
                    originHtml = tabcon.innerHTML,
                    scrollHeight = tabcon.scrollHeight,
                    boxHeight = tabcon.offsetHeight;

            if(scrollHeight > boxHeight) {
                tabcon.innerHTML = originHtml + originHtml;
                tabcon.doScroll = function () {
                    timer = setInterval(function () {
                        $(tabcon).animate({scrollTop: "+=35"}, 200, function () {
                            if(Math.abs(tabcon.scrollTop - scrollHeight) < 5){
                                tabcon.scrollTop = 0;
                            }
                        });
                    }, 2000);
                }
                tabcon.stopScroll = function () {
                    clearInterval(timer);
                }
            }
        });

        tabItems.eq(0).trigger('mouseenter');
    });
</script>
<body class="page-index">
<?php include '../../../../../modules/include/temp1-header.php' ?>
<div id="page">
    <div class="layout">
        <?php include '../../../../../modules/slides-1.php' ?>

        <div class="yjh-tab">
            <h3>今日牌价 <span class="text-red">每天十点准时更新</span></h3>
            <ul>
                <li data-tabcon="Tab1">
                    <a href="javascript:void(0)">现货</a>
                </li>
                <li data-tabcon="Tab2">
                    <a href="javascript:void(0)">期货</a>
                </li>
                <li data-tabcon="Tab3">
                    <a href="javascript:void(0)">美金盘</a>
                </li>
            </ul>
        </div>

        <div class="yjh-thead">
            <table border="0" cellpadding="0" cellspacing="0">
                <thead>
                <tr>
                    <td>产品类别</td>
                    <td>生产企业</td>
                    <td>产品牌号</td>
                    <td>库存类型</td>
                    <td>交货仓库</td>
                    <td>单价</td>
                    <td>操作</td>
                </tr>
                </thead>
            </table>
        </div>

        <div class="yjh-tabcon" id="Tab1">
            <table class="yjh-tb" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td>A</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
            </table>
        </div>
        <div class="yjh-tabcon" id="Tab2">
            <table class="yjh-tb" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td>B</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
            </table>
        </div>
        <div class="yjh-tabcon" id="Tab3">
            <table class="yjh-tb" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td>C</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
                <tr>
                    <td>LLDPE</td>
                    <td>福建联合石化</td>
                    <td>DFDA7042</td>
                    <td>现货</td>
                    <td>汕头溢诚仓</td>
                    <td>¥9,100</td>
                    <td><a href="#">立即定购</a>&nbsp;&nbsp;<a href="#">关注</a></td>
                </tr>
            </table>
        </div>


        <?php include '../../../../../modules/tab-cat-1.php' ?>

        <?php include '../../../../../modules/mixed-1.php' ?>
        <?php include '../../../../../modules/mixed-1.php' ?>
        <?php include '../../../../../modules/mixed-1.php' ?>
        <?php include '../../../../../modules/item-carousel-1.php' ?>
    </div>
</div>
<?php include '../../../../../modules/include/footer-1.php' ?>
</body>
</html>