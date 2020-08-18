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

    <script>
        $(function(){
            var globalNav = $("#nav .navwrap");
            var navheight = globalNav.height(),navtop = globalNav.offset().top;
            $(window).on("scroll",function(){
                var scrolltop = $(this).scrollTop();
                globalNav.removeAttr("style");
                if(scrolltop > navtop){
                    globalNav.addClass("global-nav-fix");
                }else{
                    globalNav.removeClass("global-nav-fix");
                }
            });

            $(".global-catalog").hover(function(){
                showGlobalCat();
            }, function(){
                hideGlobalCat();
            });
            var globalCatalog = $('.global-catalog'),
                globalCatCon = $('.gbc-blockcon'),
                gbcDuration = 200;
            function showGlobalCat(){
                globalCatCon.stop().slideDown(gbcDuration);
                globalCatalog.addClass('global-catalog-open');
            }
            function hideGlobalCat(){
                globalCatCon.stop().slideUp(gbcDuration);
                globalCatalog.removeClass('global-catalog-open');
            }
            $(".gbc-treeli").hover(function(){
                var self = $(this);
                self.addClass("tree-hover");
                self.find(".sublayer").show();
            }, function(){
                var self = $(this);
                self.removeClass("tree-hover");
                self.find(".sublayer").hide();
            });

            var sideitems = $("#inslides .slide-side-item");
            sideitems.eq(0).show();
            $('#inslides .switchable-slides').switchable({
                triggers: '&bull;',
                putTriggers: 'insertAfter',
                effect: 'fade',
                easing: 'ease-in-out',
                loop: true,
                autoplay:true,
                panels:'li',
                prev: '#inslide_prev',
                next: '#inslide_next',
                onSwitch: function(event, currentIndex) {
                    sideitems.hide().eq(currentIndex).fadeIn();
                }
            });


            $('#intabs').switchable({
                triggers: $("#intabs .switchable-tab-title li"),
                effect: 'fade',
                easing: 'ease-in-out',
                panels:'.switchable-tab-item',
                onSwitch: function(event, currentIndex) {

                }
            });
        });
    </script>
</head>
<body class="page-index">
<div id="topbar">
	<div class="topbar">
        <div class="layout">
	        <div class="toptools"></div>
			<div class="toplinks">
                <p><a href="#"><img src="images/ico-account.png" alt=""/>我的帐号</a></p>
                <p><a href="#"><img src="images/ico-mobile.png" alt=""/>手机商城</a></p>
			</div>
	        <div class="toplogin">
                <p class="topwelcome">您好！</p>
                <p><a href="login.html" class="loginlink">登录</a></p>
                <p><a href="register.html" class="loginlink">注册</a></p>
                <p class="topnotify">
                    <a href="###"><span>通知<i>3</i></span></a>
                </p>
	        </div>
		</div>
    </div>
</div>
<div id="header">
	<div class="header">
        <div class="layout">
            <table class="headertb" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td id="siteLogo">
                        <a href="#"><img src="images/logo.png" alt=""></a>
                    </td>
                    <td id="logoExtra">
                        <a href="#"><img src="images/logo-extra.png" alt=""/></a>
                    </td>
                </tr>
            </table>
		</div>
    </div>
</div>
<div id="headerRight" class="layout">
    <div id="headerSearchRight">
        <div class="hdsearch-right">
            <div style="margin-top: -10px;"><img src="http://txnz.ecmaster.cn/cmsstatic/1399955025897926.png" usemap="#Map">
                <map name="Map">
                    <area shape="rect" coords="158,3,228,51" href="/view/1102.html" target="_blank">
                    <area shape="rect" coords="81,2,157,49" href="/products/1150.html" target="_blank">
                    <area shape="rect" coords="5,3,81,51" href="http://ogasearch.food.cnca.cn/oga/query/index.jsp " target="_blank">
                </map>
            </div>
        </div>
    </div>
    <div id="headerCart" class="cart-hover">
        <div class="cartbtn"><i></i><span><a href="#">我的购物车</a></span><b class="cartcount"><em>8</em>件</b></div>
        <div class="mini-cart-menu">
            <div class="mini-cart-panel">
                <ul class="mini-cart-bd">
                    <li>
                        <div class="mini-cart-img"><a href="#"><img src="../../images/goodspic.jpg" alt=""/></a></div>
                        <div class="mini-cart-count">¥<strong>219.00</strong></div>
                        <div class="mini-cart-title"><a href="#">千纸鹤男装 2013秋装上新千纸鹤男装 2013秋装上新</a></div>
                        <div class="mini-cart-del"><a href="#">&times;</a></div>
                        <div class="mini-cart-info"><span>颜色：黑色</span><span>尺码：M</span></div>
                    </li>
                    <li>
                        <div class="mini-cart-img"><a href="#"><img src="../../images/goodspic.jpg" alt=""/></a></div>
                        <div class="mini-cart-count">¥<strong>219.00</strong></div>
                        <div class="mini-cart-title"><a href="#">千纸鹤男装 2013秋装上新</a></div>
                        <div class="mini-cart-del"><a href="#">&times;</a></div>
                        <div class="mini-cart-info"><span>颜色：黑色</span><span>尺码：M</span></div>
                    </li>
                </ul>
                <div class="mini-cart-ft">
                    <div class="mini-cart-total">
                        <p>总计: <strong>¥ 219.00</strong></p>
                    </div>
                    <div class="mini-cart-view">
                        <a href="#">查看更多商品</a>
                        <a class="mini-cart-btn" href="#"><span>结算</span></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="headerSearch">
        <form target="_top" action="" name="search">
            <div class="search-panel-fields">
                <label for="q">搜索商城商品!</label>
                <input type="text" name="q" id="q" accesskey="s" autocomplete="off" autofocus x-webkit-speech="" x-webkit-grammar="builtin:translate" lang="zh-CN">
                <button type="submit">搜索</button>
            </div>
        </form>
        <div class="hotsearch">
            <a href="#">初夏T恤</a>
            <a href="#">新款凉鞋</a>
            <a href="#">夏季防晒衫</a>
            <a href="#">开衫</a>
            <a href="#">男T恤</a>
        </div>
    </div>
</div>
<div id="nav">
    <div class="navwrap">
        <div class="global-catalog">
            <div class="gbc-wrap">
                <h2 class="gbc-title"><span>全部商品分类</span></h2>
                <div class="gbc-blockcon">
                    <ul class="gbc-tree">
                        <li class="gbc-treeli tree-hover">
                            <div class="gbc-treelitem">
                                <a class="gbc-treetitle" href="#"><span class="c">一级分类</span></a>
                                <div class="gbc-subtree">
                                    <ul></ul>
                                </div>
                            </div>
                            <div class="sublayer">
                                <dl class="sublayer-item">
                                    <dt><a href="#">休闲裤</a></dt>
                                    <dd>
                                        <ul>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                        </ul>
                                    </dd>
                                </dl>
                                <dl class="sublayer-item">
                                    <dt><a href="#">休闲裤</a></dt>
                                    <dd>
                                        <ul>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                        </ul>
                                    </dd>
                                </dl>
                                <dl class="sublayer-item">
                                    <dt><a href="#">休闲裤</a></dt>
                                    <dd>
                                        <ul>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                        </ul>
                                    </dd>
                                </dl>
                                <dl class="sublayer-item">
                                    <dt><a href="#">休闲裤</a></dt>
                                    <dd>
                                        <ul>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                        </ul>
                                    </dd>
                                </dl>
                                <dl class="sublayer-item">
                                    <dt><a href="#">休闲裤</a></dt>
                                    <dd>
                                        <ul>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                        </ul>
                                    </dd>
                                </dl>
                                <dl class="sublayer-item">
                                    <dt><a href="#">休闲裤</a></dt>
                                    <dd>
                                        <ul>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                        </ul>
                                    </dd>
                                </dl>
                                <dl class="sublayer-item">
                                    <dt><a href="#">休闲裤</a></dt>
                                    <dd>
                                        <ul>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                        </ul>
                                    </dd>
                                </dl>
                                <dl class="sublayer-item">
                                    <dt><a href="#">休闲裤</a></dt>
                                    <dd>
                                        <ul>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                            <li><a href="#">直筒休闲裤</a></li>
                                        </ul>
                                    </dd>
                                </dl>
                            </div>
                        </li>
                        <li class="gbc-treeli">
                            <div class="gbc-treelitem">
                                <a class="gbc-treetitle" href="#"><span class="c">一级分类</span></a>
                                <div class="gbc-subtree">
                                    <ul>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li class="gbc-treeli">
                            <div class="gbc-treelitem">
                                <a class="gbc-treetitle" href="#"><span class="c">一级分类</span></a>
                                <div class="gbc-subtree">
                                    <ul>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li class="gbc-treeli">
                            <div class="gbc-treelitem">
                                <a class="gbc-treetitle" href="#"><span class="c">一级分类</span></a>
                                <div class="gbc-subtree">
                                    <ul>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                        <li><a href="#" class="gbc-treeitem"><span class="c">二级分类</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="nav">
            <div class="layout">
                <div class="site-nav">
                    <ul>
                        <li data-navmenu="snav_item1" class="site-nav-item"><a href="javascript:void(0)">首页</a></li>
                        <li data-navmenu="snav_item2" class="site-nav-item"><a href="javascript:void(0)">新品上架</a></li>
                        <li data-navmenu="snav_item3" class="site-nav-item"><a href="javascript:void(0)">T恤</a></li>
                        <li data-navmenu="snav_item4" class="site-nav-item"><a href="javascript:void(0)">POLO衫</a></li>
                        <li data-navmenu="snav_item5" class="site-nav-item"><a href="javascript:void(0)">衬衫</a></li>
                        <li data-navmenu="snav_item6" class="site-nav-item"><a href="javascript:void(0)">夹克</a></li>
                        <li data-navmenu="snav_item7" class="site-nav-item"><a href="javascript:void(0)">皮衣</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="page">
    <div class="layout">
        <?php include '../../../../modules/slides-1.php' ?>
        <?php include '../../../../modules/tab-carousel-1.php' ?>
        <?php include '../../../../modules/itemshow-1.php' ?>
        <?php include '../../../../modules/itemshow-1.php' ?>
        <?php include '../../../../modules/itemshow-1.php' ?>
        <?php include '../../../../modules/itemshow-1.php' ?>
        <?php include '../../../../modules/itemshow-1.php' ?>
    </div>
</div>

<div id="footer">
<style>
.txnz_ft_tb{line-height: 20px;color: #a0a0a0; text-align: left;}
.txnz_ft_tb td{ vertical-align: top;}
.txnz_ft_tb a{color: #a0a0a0;}
.txnz_ft_info{ padding-top: 10px; text-align: center; line-height: 22px;}
</style>
    <div class="footer">
        <table class="txnz_ft_tb" width="990" align="center" border="0" cellpadding="0" cellspacing="0" bgcolor="#f7f7f7">
            <tr>
                <td colspan="10">
                    <img src="images/pic/txnz_ft_01.png" width="990" height="43" alt=""></td>
            </tr>
            <tr>
                <td width="95">
                    <img src="images/pic/txnz_ft_02.png" width="95" height="79" alt=""></td>
                <td width="99">
                    <p>关于我们</p>

                    <p>购物流程</p>

                    <p>会员政策</p>
                </td>
                <td width="89">
                    <img src="images/pic/txnz_ft_04.png" width="89" height="79" alt=""></td>
                <td width="100">
                    <p>配送时间</p>

                    <p>配送范围</p>

                    <p>资费说明</p>
                </td>
                <td width="88">
                    <img src="images/pic/txnz_ft_06.png" width="88" height="79" alt=""></td>
                <td width="100">
                    <p>货到付款</p>

                    <p>在线支付</p>

                    <p>账户信息</p>
                </td>
                <td width="87">
                    <img src="images/pic/txnz_ft_08.png" width="87" height="79" alt=""></td>
                <td width="100">
                    <p>验证与拒签</p>

                    <p>退换货流程</p>

                    <p>服务协议</p>
                </td>
                <td width="90">
                    <img src="images/pic/txnz_ft_10.png" width="90" height="79" alt=""></td>
                <td width="142">
                    <p>企业团购</p>

                    <p>私宴定制</p>

                    <p>听溪公益</p>
                </td>
            </tr>
        </table>
        <div class="txnz_ft_info">
            <p><b style="color: #2f2f2f;">客服热线：0592-5180001（8:30AM-5:30PM）</b></p>
            <p><a href="#">关于我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">淘宝入口</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
                            href="#">帮助中心</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">服务协议</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
                            href="#">诚征英才</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#">网站地图</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
                            href="#">友情链接</a></p>
            <p>厦门土芭芭农业科技有限公司. © 2005-2014 厦门有机蔬菜领导者</p>
            <p>听溪农庄 版权所有 并保留所有权利</p>
        </div>
    </div>
</div>

</body>
</html>