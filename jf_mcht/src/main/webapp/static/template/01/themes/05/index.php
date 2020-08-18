<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Genanx</title>
    <link rel="stylesheet" href="../../../../css/style.css">
    <link rel="stylesheet" href="../../../../css/module.css">
    <link rel="stylesheet" href="../../css/tmpl.css">
    <link rel="stylesheet" href="css/theme.css">
    <script src="../../../../js/lib/jquery-1.8.3.min.js"></script>
    <script src="../../../../js/jquery.switchable.min.js"></script>
    <script src="../../../../js/modules.js"></script>
</head>
<style>
.zmws-block img{position: relative; left: 50%; margin-left: -960px; width: 1920px;}
.zmws-honor{ background: #d0a972 url("images/pic/zmws-honor-bg.jpg") no-repeat bottom; padding-bottom: 106px; position: relative; left: 50%; width: 1920px; margin-left: -960px; text-align: center;}
</style>
<body class="page-index">
<?php $_GET['logo'] = 'images/pic/logo.jpg' ?>
<?php include '../../../../modules/include/temp1-header.php' ?>
<div id="page">
    <div class="layout">
        <?php $_GET["size"] = "large"; include '../../../../modules/slides-1.php' ?>
        <?php include '../../../../modules/img-grid-2.php' ?>
        <?php include '../../../../modules/banner-1.php' ?>
        <?php $_GET["num"] = 2; include '../../../../modules/img-grid-2.php' ?>
        <?php $_GET["num"] = null; include '../../../../modules/banner-1.php' ?>
        <?php $_GET["num"] = 3; include '../../../../modules/img-grid-2.php' ?>

        <?php $_GET["num"] = 2; include '../../../../modules/banner-1.php' ?>
        <?php $_GET["num"] = null; $_GET["img_grid_large"] = 1; include '../../../../modules/img-grid-1.php' ?>
        <?php $_GET["num"] = 4; include '../../../../modules/img-grid-2.php' ?>

        <div class="zmws-block"><img src="images/pic/zmws_01.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_02.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_03.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_04.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_05.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_06.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_07.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_08.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_09.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_10.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_11.jpg" alt=""/></div>
        <div class="zmws-block"><img src="images/pic/zmws_12.jpg" alt=""/></div>

        <div class="zmws-honor">
            <p><img src="images/pic/zmws_honor_01.jpg" alt=""/></p>
            <p><img src="images/pic/zmws_honor_02.png" alt=""/></p>
            <p><img src="images/pic/zmws_honor_03.jpg" alt=""/></p>
            <p><img src="images/pic/zmws_honor_04.jpg" alt=""/></p>
            <p><img src="images/pic/zmws_honor_05.jpg" alt=""/></p>
        </div>
    </div>
</div>
<?php include '../../../../modules/include/footer-1.php' ?>
</body>
</html>