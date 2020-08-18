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
    <style>
        .in-showmore a{display: block; text-align: center; line-height: 36px; background: #e9e8e8; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; font-size: 14px; margin-bottom: 15px;}
        .in-showmore a span{ display: inline-block; color: #3d3d3d; padding-right: 18px; background: url("img/showarrow.gif") no-repeat right;}
    </style>
</head>

<body class="page-index">
<?php $_GET['logo'] = 'img/logo.png' ?>
<?php include '../../../../../modules/include/temp1-header.php' ?>
<div id="page">
    <div class="layout">
        <?php include '../../../../../modules/slides-1.php' ?>

        <?php $_GET['color'] = '#ff6073'; ?>
        <?php include '../../../../../modules/itemshow-5.php' ?>
        <div class="in-showmore"><a href="#"><span>更多美食优惠，请点击查看</span></a></div>

        <?php $_GET['color'] = '#3d98ff'; ?>
        <?php include '../../../../../modules/itemshow-5.php' ?>
        <div class="in-showmore"><a href="#"><span>更多美食优惠，请点击查看</span></a></div>

        <?php $_GET['color'] = '#8a95ff'; ?>
        <?php include '../../../../../modules/itemshow-5.php' ?>
        <div class="in-showmore"><a href="#"><span>更多美食优惠，请点击查看</span></a></div>

        <?php $_GET['color'] = '#ff828c'; ?>
        <?php include '../../../../../modules/itemshow-5.php' ?>
        <div class="in-showmore"><a href="#"><span>更多美食优惠，请点击查看</span></a></div>

        <?php $_GET['color'] = '#01bce4'; ?>
        <?php include '../../../../../modules/itemshow-5.php' ?>
        <div class="in-showmore"><a href="#"><span>更多美食优惠，请点击查看</span></a></div>

        <?php $_GET['color'] = '#bc0000'; ?>
        <?php include '../../../../../modules/itemshow-5.php' ?>
        <div class="in-showmore"><a href="#"><span>更多美食优惠，请点击查看</span></a></div>
    </div>
</div>
<?php include '../../../../../modules/include/footer-1.php' ?>
</body>
</html>