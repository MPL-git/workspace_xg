<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>官方活动管理</title>
 <style type="text/css">
	.good {
		width:42px;
		height:14px;
		font-size:15px;
		font-family:SourceHanSansCN-Bold;
		font-weight:bold;
		margin-top:15px;
		color:rgba(51,51,51,1);
	}	
	.jinxing {
		width:69px;
		height:14px;
		font-size:14px;
		font-family:SourceHanSansCN-Bold;
		font-weight:bold;
		color:rgba(48,206,126,1);
		margin-left:32px;
	}
	.marginTop {
		margin-top: 15px;
	}
	.shijian {
		width:55px;
		height:13px;
		font-size:14px;
		font-family:SourceHanSansCN-Regular;
		font-weight:400;
		color:rgba(153,153,153,1);
	}	
	
	.shenhe {
		width:55px;
		height:13px;
		font-size:14px;
		font-family:SourceHanSansCN-Regular;
		font-weight:400;
		color:rgba(153,153,153,1);
	}	
	.youxiao {
		width:84px;
		height:14px;
		font-size:14px;
		font-family:SourceHanSansCN-Bold;
		font-weight:bold;
		color:rgba(51,51,51,1);
		margin-left:20px;
	}	
	.submit{
		width:129px;
		height:40px;
		background:rgba(0,153,255,1);
		border-radius:2px;
	}	
	.fuhe{
		width:28px;
		height:13px;
		font-size:14px;
		font-family:SourceHanSansCN-Regular;
		font-weight:400;
		color:rgba(51,51,51,1);
		margin-left:20px;
	}	
	.buttonText{
		 width:129px;
		 height:40px;
		 line-height: 28px;
		 margin: 0;
		 padding: 0 18px;
		 background: #3c9eff;
		 border: none;
		 color: #fff;
		 cursor: pointer;
		 border-radius: 1px;
		 font-size: 12px;
		 margin-top:20px;
	 }
	.buttonText1{
		width:129px;
		height:40px;
		line-height: 28px;
		margin: 0;
		padding: 0 18px;
		background: #3c9eff;
		border: none;
		color: #fff;
		cursor: pointer;
		border-radius: 1px;
		font-size: 12px;
		margin-top:20px;
	}
		.buttonShopText{
		width:129px;
		height:40px;
		line-height: 28px;
   	 	margin: 0;
   	 	padding: 0 18px;
    	background: #3c9eff;
   		border: none;
    	color: #fff;
    	cursor: pointer;
    	border-radius: 1px;
    	font-size: 12px;
    	margin-top:20px;
	}	
	
	
	.qiehuan{
		margin-top:43px;
	}	
	.weiqiehuanText{
		width:71px;
		height:18px;
		font-size:15px;
		font-family:SourceHanSansCN-Regular;
		font-weight:400;
		color:rgba(51,51,51,1);
	}
	.qiehuanText{
		width:72px;
		height:18px;
		font-size:15px;
		font-family:SourceHanSansCN-Bold;
		font-weight:bold;
		color:rgba(255,80,80,1);
	}
	.lines{
		width:980px;
		height:1px;
		background:rgba(221,221,221,1);
		width:102%;
	}
	.introduceContent{
		margin-top:10px;
		font-size:14px;
		font-family:SourceHanSansCN-Regular;
		font-weight:400;
		color:rgba(51,51,51,1);
	}
	.introduceImg{
		width:375px;
		height:407px;
		background:rgba(221,221,221,1);
	}
    .change {
        background: white;
        border-bottom: 2px solid rgba(255,80,80,1);
        color:rgba(255,80,80,1);
    }
    .nav.q>li>a {
    width: 120px;
    height: 36px;
    line-height: 36px;
    margin: 0 0 -1px;
    padding: 0;
    border: 1px solid #ddd;
    text-align: center;
    border-radius: 0;
    border-left: none;
    border-bottom: none;
    }
    .biaoti{
    background-color: gainsboro;
    height: 34px;
    line-height: 34px;
    padding: 0;
    border-bottom: 1px solid #ddd;
    }     
</style>
</head>
<body>
<div class="x_panel container-fluid gf-hd">
	<input type="hidden" id="mchtInfoStatus" value="${mchtInfoStatus}"/>
	<input type="hidden" id="TAG" value="${TAG}"/>
	<input type="hidden" id="auditStatus" value="${sourceNiche.auditStatus}"/>
	<input type="hidden" id="pic" value="${information.pic}"/>
    <div class="row content-title">
        <div class="t-title">
        <c:if test="${activityType == 1}">
   		有好货
   		</c:if>
   		<c:if test="${activityType == 2}">
   		潮鞋上新
   		</c:if>
   		<c:if test="${activityType == 3}">
   		潮流男装
   		</c:if>
   		<c:if test="${activityType == 4}">
   		断码特惠
   		</c:if>
   		<c:if test="${activityType == 5}">
   		运动鞋服
   		</c:if>
   		<c:if test="${activityType == 6}">
   		潮流美妆
   		</c:if>
   		<c:if test="${activityType == 7}">
   		食品超市
   		</c:if>
   		<c:if test="${activityType == 8}">
   		每日好店
   		</c:if>
   		<c:if test="${activityType == 9}">
   		大学生创业
   		</c:if>
		<c:if test="${activityType == 10}">
		领券中心
		</c:if>
		<c:if test="${activityType == 11}">
		积分抽奖
		</c:if>
        <a href="JavaScript:returnBase();">返回</a></div>
    </div>
   	<div class="marginTop">
   		<span class="good">
   		<c:if test="${activityType == 1}">
   		有好货
   		</c:if>
   		<c:if test="${activityType == 2}">
   		潮鞋上新
   		</c:if>
   		<c:if test="${activityType == 3}">
   		潮流男装
   		</c:if>
   		<c:if test="${activityType == 4}">
   		断码特惠
   		</c:if>
   		<c:if test="${activityType == 5}">
   		运动鞋服
   		</c:if>
   		<c:if test="${activityType == 6}">
   		潮流美妆
   		</c:if>
   		<c:if test="${activityType == 7}">
   		食品超市
   		</c:if>
   		<c:if test="${activityType == 8}">
   		每日好店
   		</c:if>
   		<c:if test="${activityType == 9}">
   		大学生创业
   		</c:if>
		<c:if test="${activityType == 10}">
		领券中心
		</c:if>
		<c:if test="${activityType == 11}">
		积分抽奖
		</c:if>
   		</span>
   		<span class="jinxing">报名进行中</span>
   	</div>
   	<div class="marginTop">
   		<span class="shenhe">报名时间</span>
   		<span class="youxiao">报名长期有效</span>
   	</div>
   	<div class="marginTop">
   		<span class="shenhe">活动时间</span>
   		<span class="youxiao">活动长期有效</span>
   	</div>
   	<div class="marginTop">
   		<span class="shenhe">报名资质</span>
   		<span class="fuhe"><c:if test="${TAG eq 0}"><img alt="" src="${ctx}/static/images/success.png" style="margin-bottom:3px;">符合 </c:if><c:if test="${TAG eq 1}"><img alt="" src="${ctx}/static/images/fullWarn.png" style="margin-bottom:3px;">不符合</c:if></span>
   	</div> 
   	
   	<!--当活动为1234567时  -->
   	 <c:if test="${activityType == 1 || activityType == 2  || activityType == 3 || activityType == 4 || activityType == 5 || activityType == 6 || activityType == 7 || activityType == 11}">
   	<button class="buttonText" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>立即报名</button>
	<c:if test="${TAG eq 1 && activityRuleConfiguration.shopComment ne null}">&nbsp;&nbsp;<img alt="" src="${ctx}/static/images/warn.png" style="margin-bottom:3px;">店铺DSR评分三项指标平均值不得低于${activityRuleConfiguration.shopComment}</c:if>
   	 </c:if>
	<!--当活动为10时   领券中心  -->
	<c:if test="${activityType == 10 }">
        <!--没报名过  -->
        <c:if test="${isExistence==null || isExistence==1 }">
            <button class="buttonText1" onclick="toSignUp();" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>立即报名</button>
            <c:if test="${TAG eq 1 && activityRuleConfiguration.shopComment ne null}">&nbsp;&nbsp;<img alt="" src="${ctx}/static/images/warn.png" style="margin-bottom:3px;">店铺DSR评分三项指标平均值不得低于${activityRuleConfiguration.shopComment}</c:if>
        </c:if>
        <!--报名过  -->
        <c:if test="${isExistence==0 }">
            <button class="buttonText1" onclick="toAddproductCertificates(1);" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>创建商品券</button>
            <button class="buttonText1" onclick="getContent('${ctx}/mcht/activity')" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>创建品牌特卖</button>
            <c:if test="${TAG eq 1 && activityRuleConfiguration.shopComment ne null}">&nbsp;&nbsp;<img alt="" src="${ctx}/static/images/warn.png" style="margin-bottom:3px;">店铺DSR评分三项指标平均值不得低于${activityRuleConfiguration.shopComment}</c:if>
        </c:if>
	</c:if>

   	<!--当活动为89时  -->
	<c:if test="${activityType == 8 || activityType == 9 }">

		<!--没报名过  -->
		<c:if test="${isExistence==1 }">
			<button class="buttonShopText" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>立即报名</button>
			<c:if test="${TAG eq 1 && activityRuleConfiguration.shopComment ne null}">&nbsp;&nbsp;<img alt="" src="${ctx}/static/images/warn.png" style="margin-bottom:3px;">店铺DSR评分三项指标平均值不得低于${activityRuleConfiguration.shopComment}</c:if>
		</c:if>

		<!--报名过  -->
		<c:if test="${isExistence==0 }">

			<c:if test="${sourceNiche.auditStatus==-1}">
				<button class="buttonShopText" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>立即报名</button>
				<c:if test="${TAG eq 1 && activityRuleConfiguration.shopComment ne null}">&nbsp;&nbsp;<img alt="" src="${ctx}/static/images/warn.png" style="margin-bottom:3px;">店铺DSR评分三项指标平均值不得低于${activityRuleConfiguration.shopComment}</c:if>
			</c:if>

			<!-- 待审核 -->
			<c:if test="${sourceNiche.auditStatus==0 }">
				<button class="buttonShopText" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>店铺管理</button>
				<span class="shenhe">审核状态:</span>
				<span class="fuhe">待审核</span>
			</c:if>

			<!-- 审核通过 -->
			<c:if test="${sourceNiche.auditStatus==1 }">
				<button class="buttonShopText" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>店铺管理</button>
				<span class="shenhe">审核状态:</span>
				<span class="fuhe"><img alt="" src="${ctx}/static/images/success.png" style="margin-bottom:3px;">审核通过</span>
			</c:if>

			<!-- 审核驳回-->
			<c:if test="${sourceNiche.auditStatus==2  }">

				<!-- 驳回的旧数据 -->
				<c:if test="${ sourceNiche.canApply == null }">
					<button class="buttonShopText" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>立即报名</button>
					<c:if test="${TAG eq 1 && activityRuleConfiguration.shopComment ne null}">&nbsp;&nbsp;<img alt="" src="${ctx}/static/images/warn.png" style="margin-bottom:3px;">店铺DSR评分三项指标平均值不得低于${activityRuleConfiguration.shopComment}</c:if>
				</c:if>

				<!-- 0.七天内不可报名 -->
				<c:if test="${ sourceNiche.canApply == 0 }">
					<button class="buttonShopText" id="sevenDay" >立即报名</button>
					<span class="shenhe">审核状态 :</span>
					<span class="fuhe"><img alt="" src="${ctx}/static/images/fullWarn.png" style="margin-bottom:3px;">驳回</span>
					<span class="shijian" style="display:none"> 距重新报名还剩: </span>  <span class="shijian" id="spanTime" style="display:none"></span>
					<a href="javascript:;" onclick="viewSubOrder()">查看原因</a>
				</c:if>

				<!-- 1.可立即报名 -->
				<c:if test="${ sourceNiche.canApply == 1 }">
					<button class="buttonShopText" <c:if test="${TAG eq 1}">style="background: rgba(153,153,153,1);" disabled</c:if>>立即报名</button>
					<span class="shenhe">审核状态 :</span>
					<span class="fuhe"><img alt="" src="${ctx}/static/images/fullWarn.png" style="margin-bottom:3px;">驳回</span>
					<a href="javascript:;" onclick="viewSubOrder()">查看原因</a>
				</c:if>

				<!-- 1.不可再次报名 -->
				<c:if test="${ sourceNiche.canApply == 2 }">
					<button class="buttonShopText" style="background: rgba(153,153,153,1);" disabled >立即报名</button>
					<span class="shenhe">审核状态 :</span>
					<span class="fuhe"><img alt="" src="${ctx}/static/images/fullWarn.png" style="margin-bottom:3px;">驳回</span>
					<a href="javascript:;" onclick="viewSubOrder()">查看原因</a>
				</c:if>

			</c:if>

		</c:if>

	</c:if>


   	
   	<ul class="nav nav-tabs q" role="tablist" style="margin-top:20px;">
        <li <c:if test="${tabType eq 0}">class="change"</c:if>> <a href="#" role="tab" data-toggle="tab" <c:if test="${tabType eq 0}">class="qiehuanText"</c:if>>活动介绍</a></li>
        <li> <a href="#" role="tab" data-toggle="tab" class="weiqiehuanText">活动要求</a></li>
    </ul>
    <div class="lines"></div>
	<div class="introduceContent">
		${information.content} 
	</div>
	<img  class="introduceImg" style="margin-bottom:20px;display:none;" src="" />
</div>
  
<!-- 	查看信息框 -->
<div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="viewModalLabel"
     data-backdrop="static">
</div>


			
<!--弹出驳回原因div-->
<div class="video_box" style="position:fixed; width:500px; height:350px; top:35%; left:35%; display: none;" id="reasonsDiv">
    <a href="javascript:;" class="video_close"><img src="${ctx}/static/images/close.png"></a> 
    	<div class="panel panel-default" style="text-align:left" >
		  	<div class="biaoti">
		        <h3 class="modal-title">
		        	驳回原因
				</h3>
		    </div>
		  	
		   <p style="text-align:-webkit-auto; margin:15px ; color:#123456;font-size:14px;height:80px"  >${sourceNiche.auditRemarks }</p>
		   
				<div class="panel panel-default"  style="float:right;width:100%;">
				<button style="float:right;margin:10px ;" onclick="closeModel();">我知道了</button>
			 	</div>
		 </div>
		 
</div>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/static/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dataTables.bootstrap.min.js"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/jquery.metadata.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/static/js/messages_cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.jf.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dateUtil.js"></script>
<div class="modal fade" id="viewModal" tabindex="-1" role="dialogA" aria-labelledby="viewModalLabelA" data-backdrop="static" ></div>

<script>
    var table;
    var table3;


        table3 = $('#datatable3').dataTable({
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm").serializeArray();

                param.push({"name":"pagesize","value":data.length});
                if (data.start > 0) {
                    param.push({"name":"page","value":data.start/data.length+1});
                } else {
                    param.push({"name":"page","value":1});
                }


                $.ajax({
                    method: 'POST',
                    url: '${ctx}/shopPreferentialInfo/getShopPreferentialInfoList?searchOrderType=3',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode =='0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);
                        $("span[name='timeStatus3']").each(function(){
                            var $this = $(this);
                            var id = $this.data("id");
                            var timer = "timer"+id;
                            var status = $this.data("status");
                            var beginDate = $this.data("begindate");
                            var endDate = $this.data("enddate");
                            var preferentialType = $this.data("preferentialtype");
                            var now = new Date();
                            var beginTime = new Date(beginDate);
                            var endTime = new Date(endDate);

                            if(now>endTime){
                                $this.text("已结束");
                            }
                            if(now>=beginTime && now<=endTime){
                                $this.text("活动中");
                                $this.closest("td").next().html('<a href="javascript:;" onclick="endInAdvanceCoupon('+id+',3);">【提前结束】</a>');
                            }
                            if(now<beginTime){
                                $this.text("未开始");
                                var html = [];
                                html.push('<a href="javascript:;" onclick="toEdit(3,'+id+',);">编辑</a><br>');
                                html.push('<a href="javascript:;" onclick="remove('+id+',3);">删除</a><br>');
                                $this.closest("td").next().html(html.join(""));
                            }


                        });
                    }
                });
            },
            "language": {"url": '${ctx}/static/cn.json'},
            "autoWidth": true,   // 禁用自动调整列宽
            "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
            "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
            "processing": true,  // 隐藏加载提示,自行处理
            "serverSide": true,   // 启用服务器端分页
            "searching": false,   // 禁用原生搜索
            "bSort": false,
            "bServerSide": true,
            "columns": [

                {"data": "","width":"180", "render": function (data, type, row, meta) {
                        var tempNo = row.productArtNo
                        var tempName = row.productName;
                        var ellipsisNo="";
                        var ellipsisName = "";
                        if(tempName&&tempName.length >10){
                            ellipsisName = tempName.substring(0,10)+"..."
                        }else{
                            ellipsisName = tempName
                        }

                        if(tempNo&&tempNo.length >10){
                            ellipsisNo = tempNo.substring(0,10)+"..."
                        }else{
                            ellipsisNo = tempNo
                        }
                        var h=[];
                        var html = "";
                        html+='<div class="no-check">';
                        if(row.pic){
                            var pic=row.pic.substring(0, row.pic.lastIndexOf("."))+"_M"+row.pic.substring(row.pic.lastIndexOf("."));
                            html+='<div class="width-60"><img src="${ctx}/file_servelt'+pic+'"></div>';
                        }else{
                            html+='<div class="width-60"><img src="${ctx}/file_servelt"></div>';
                        }
                        html+='<div class="width-180"><p  style="color: #999;margin: 5px 0 0;">'+ellipsisName+'</p><br><p  style="color: #999;margin: 5px 0 0;">货号：'+ellipsisNo +'&nbsp;&nbsp;&nbsp;&nbsp;</p><p style="color: #999;margin: 0;">ID：'+row.productCode+'</p></div>';
                        html+='</div>';
                        h.push(html);
                        return h.join("");

                    }},

                {"data": "name","width":"80","render": function (data, type, row, meta) {
                        var html=[];
                        html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='watchView("+row.id+")' >"+row.name+"</a>");
                        if(row.couponType==1&&row.preferentialType==1){
                            html.push("<p>店铺满减券</p>")
                        }
                        if(row.couponType==1&&row.preferentialType==2){
                            html.push("<p>店铺折扣券</p>")
                        }
                        if(row.couponType==4){
                            html.push("<p>商品券</p>")
                        }
                        return html.join("");

                    }},
                {"data": "money","width":"50","render": function (data, type, row, meta) {
                        return row.money;
                    }},
                {"data": "date","width":"120","render": function (data, type, row, meta) {//使用时间
                        return "起:"+ new Date(row.expiryBeginDate).format("yyyy-MM-dd hh:mm")+"<br>止:"+new Date(row.expiryEndDate).format("yyyy-MM-dd hh:mm");
                    }},
                {"data": "grantQuantity","width":"80","render": function (data, type, row, meta) {//发行量
                        var html =[];
                        var now = new Date();
                        var endTime = row.recEndDate;
                        html.push(row.grantQuantity+"<br>")
                        if(now<endTime){
                            html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='editMchtContact("+row.id+",3)' >追加</a>")
                        }
                        return html.join("")
                    }},
                {"data": "recQuantity","width":"80","render": function (data, type, row, meta) {//领取量
                        var html= [];
                        var temDate = new Date();
                        if(row.recQuantity==""||row.recQuantity==null||row.recQuantity==0){
                            html.push("0");
                        }else{
                            html.push(row.recQuantity);
                        }

                        if(row.recQuantity>=row.grantQuantity){
                            html.push("<br>已领光")
                        }
                        if(row.recEndDate<=temDate){
                            html.push("<br>领取结束")
                        }
                        return html.join("");
                    }},
                {"data": "useNum","width":"80","render": function (data, type, row, meta) {
                        return row.useNum;
                    }},

                {"data": "status","width":"80","render": function (data, type, row, meta) {
                        /*    var statusDesc="";
                           if(row.status==0){
                               statusDesc="未启用"
                           }
                           if(row.status==1){
                               statusDesc="启用"
                           }
                           if(row.status==2){
                               statusDesc="停用"
                           } */

                        return '<span name="timeStatus3" data-preferentialtype="'+row.preferentialType+'"  data-id="'+row.id+'" data-status="'+row.status+'" data-begindate="'+row.recBeginDate+'" data-enddate="'+row.recEndDate+'"></span>';
                    }},
                {"data": "op","width":"100","render": function (data, type, row, meta) {
                        return "";
                    }}
            ]
        }).api();
        $('#btn-query3').on('click', function (event) {
            table3.ajax.reload();
        });




        table = $('#datatable').dataTable({
            "ajax": function (data, callback, settings) {
                var param = $("#searchForm").serializeArray();

                param.push({"name":"pagesize","value":data.length});
                if (data.start > 0) {
                    param.push({"name":"page","value":data.start/data.length+1});
                } else {
                    param.push({"name":"page","value":1});
                }


                $.ajax({
                    method: 'POST',
                    url: '${ctx}/shopPreferentialInfo/getShopPreferentialInfoList?searchOrderType=1',
                    data: param,
                    dataType: 'json'
                }).done(function (result) {
                    if (result.returnCode =='0000') {
                        var returnData = {};
                        returnData.recordsTotal = result.returnData.Total;
                        returnData.recordsFiltered = result.returnData.Total; // 后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.returnData.Rows;
                        callback(returnData);
                        $("span[name='timeStatus1']").each(function(){
                            var $this = $(this);
                            var id = $this.data("id");
                            var timer = "timer"+id;
                            var status = $this.data("status");
                            var beginDate = $this.data("begindate");
                            var endDate = $this.data("enddate");
                            var now = new Date();
                            var beginTime = new Date(beginDate);
                            var endTime = new Date(endDate);

                            if(now>endTime){
                                $this.text("已结束");
                            }
                            if(now>=beginTime && now<=endTime){

                                $this.text("活动中");
                                $this.closest("td").next().html('<a href="javascript:;" onclick="endInAdvance('+id+');">【提前结束】</a>');
                            }
                            if(now<beginTime){
                                $this.text("未开始");
                                var html = [];
                                html.push('<a href="javascript:;" onclick="toEdit(1,'+id+',-1);">编辑</a><br>');
                                html.push('<a href="javascript:;" onclick="del('+id+');">删除</a><br>');
                                $this.closest("td").next().html(html.join(""));
                            }
                        });
                    }
                });
            },
            "language": {"url": '${ctx}/static/cn.json'},
            "autoWidth": true,   // 禁用自动调整列宽
            "stripeClasses": ['odd', 'even'], // 为奇偶行加上样式，兼容不支持CSS伪类的场合
            "order": [[1, 'asc']],          // 取消默认排序查询,否则复选框一列会出现小箭头
            "processing": true,  // 隐藏加载提示,自行处理
            "serverSide": true,   // 启用服务器端分页
            "searching": false,   // 禁用原生搜索
            "bSort": false,
            "bServerSide": true,
            "columns": [
                {"data": "name","width":"150","render": function (data, type, row, meta) {
                        var html=[];
                        html.push("<a class='table-opr-btn' href='javascript:void(0);' onclick='watchShopView("+row.id+")' >"+row.name+"</a>");
                        html.push("<p>店铺满减活动</p>")

                        return html.join("");
                    }},
                {"data": "rules","width":"150","render": function (data, type, row, meta) {
                        if(row.rules){
                            var rulesArray = row.rules.split("|");
                            var html = [];
                            for(var i=0;i<rulesArray.length;i++){
                                var eachRuleArray = rulesArray[i].split(",");
                                if(row.sumFlag == "0"){
                                    html.push("满"+eachRuleArray[0]+"减"+eachRuleArray[1]+"<br>");
                                }else{
                                    html.push("满"+eachRuleArray[0]+"减"+eachRuleArray[1]+"【上不封顶】");
                                }
                            }
                            return html.join("");
                        }else{
                            return "";
                        }
                    }},
                {"data": "date","width":"180","render": function (data, type, row, meta) {
                        return "起:"+ new Date(row.beginDate).format("yyyy-MM-dd hh:mm")+"<br>止:"+new Date(row.endDate).format("yyyy-MM-dd hh:mm");
                    }},
                {"data": "statusDesc","width":"80","render": function (data, type, row, meta) {
                        return '<span name="timeStatus1" data-id="'+row.id+'" data-status="'+row.status+'" data-begindate="'+row.beginDate+'" data-enddate="'+row.endDate+'"></span>';
                    }},
                {"data": "op","width":"100","render": function (data, type, row, meta) {
                        return "";
                    }}
            ]
        }).api();

        $('#btn-query').on('click', function (event) {
            table.ajax.reload();
        });

        /*     $("#searchKeyWord").keydown(function(e){
                if(e.keyCode==13){
                    table.ajax.reload();
                    return false;
                }
            }); */


    $(function(){
		
 		if($('#pic').val() != "" && $('#pic').val() !=null){
			$('.introduceImg').show();
			$('.introduceImg').attr("src","${ctx}/file_servelt/${information.pic}");
		} 

		//倒计时
		timer = setInterval(show,1000);
		function show(){
			var d1 = new Date();//获取到当前的时间
			var d1Ms = d1.getTime();
			var dd = "${sourceNiche.auditDate}"
			//var d2 = "${sourceNiche.auditDate}"
			var d2 = new Date("${auditDate}");
			var d2Ms = d2.getTime();
			var differMs = d2Ms-d1Ms;//相差的毫秒数
			if(differMs<=60000){
				$(".shijian").hide();
				$("#sevenDay").removeAttr("disabled");
				$("#sevenDay").attr("style","");
				clearInterval(timer);
			}else{
				$(".shijian").show();
				$("#sevenDay").attr("disabled");
				$("#sevenDay").attr("style","background: rgba(153,153,153,1);");
			}
			
			var date = parseInt(differMs/(3600*24*1000));//天
			var hours = parseInt((differMs%(3600*24*1000))/(3600*1000));//1小时=3600s
			var minutes =parseInt((differMs%(3600*1000))/(60*1000));//分钟
			var seconds = parseInt((differMs%(60*1000))/1000);//秒
			//var ms = differMs%1000;//毫秒
			
			//当前分秒为个位数字时，对其进行的处理
			hours = hours<10?"0"+hours:hours;
			minutes = minutes<10?"0"+minutes:minutes;
			seconds = seconds<10?"0"+seconds:seconds;
			$("#spanTime").html(date+"天"+hours+"小时"+ minutes+"分");
			//document.getElementById("spanTime").innerHTML = date+"天"+hours+"小时"+ minutes+"分"+ seconds+"秒"+ ms;
			
		}
		
	});
	
	$('.weiqiehuanText').click(function(){
		getContent("${ctx}/market/activityRequirements?activityType="+${activityType}+"&TAG="+$('#TAG').val());
	});
	
	$('.buttonText').click(function(){
		getContent("${ctx}/market/productRegistration?activityType="+${activityType});
	});
	/*
	//跳转品牌特卖页面
	$('.buttonText1').click(function(){
		getContent("${ctx}/mcht/activity");
	});
	*/

	$('.buttonShopText').click(function(){
		getContent("${ctx}/market/shopRegistration?activityType="+${activityType});
	});
	
	function returnBase(){
		getContent("${ctx}/market/signUpActivity");
	};
	
	function viewSubOrder(_this){
	  $("#reasonsDiv").show();
	};
	
	function closeModel(){
		  $("#reasonsDiv").hide();	
	};
		
	$(".video_close").on('click',function(){
		 $("#reasonsDiv").hide();
	});



	//领券中心中创建商品券
	function toAddproductCertificates(){

		$.ajax({
			url: "${ctx}/shopPreferentialInfo/checkCouponNum?preferentialType="+3,
			type: "GET",
			success: function(data){
				if(data.returnDate=="4004"){
					swal("对不起,最多只能创建20个同类型活动");
				}else{
					$.ajax({
						url: "${ctx}/shopPreferentialInfo/productCertificates",
						type: "GET",
						success: function(data){
							$("#viewModal").html(data);
							$("#viewModal").modal();

							table3.ajax.reload();

						},
						error:function(){
							swal('页面不存在');
						}
					});
				}
			},
			error:function(){
				swal('页面不存在');
			}
		});

	}


    //领券中心中报名
    function toSignUp(){
        $.ajax({
            url: "${ctx}/market/couponSignUp?activityType="+10,
            type: "GET",
            success: function(data){
                getContent("${ctx}/market/signUpImmediately?activityType="+${activityType}+"&TAG="+$('#TAG').val());
            },
            error:function(){
                swal('页面不存在');
            }
        });
    }

</script>
</body>
</html>
