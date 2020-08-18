<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
	<style type="text/css">
		.turntable {
			border-width: 1px;
			border-color: #DDDDDD;
			width: 600px;
			height: 600px;
		}
		.turntable_td {
			width: 200px;
			height: 200px;
			border-width: 1px;
			border-style: solid;
			border-color: #DDDDDD;
		}
		.updatebtn{
			height: 23px;
			overflow: hidden;
			width: 60px;
			line-height: 23px;
			cursor: pointer;
			position: relative;
			text-align: center;
			border: solid 1px #A3C0E8;
			color: #333333;
			background: #E0EDFF;
		}
	</style>
<script type="text/javascript">
	var viewerPictures;

	$(function(){
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
				$("#viewer-pictures").hide();
			}});

		$(".l-dialog-close").live("click", function(){
			$("#searchbtn").click();
		});

		loadData();
	});

	function viewProduct(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1200,
			title: "商品信息",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProduct.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	function viewerPic(productId){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : "${pageContext.request.contextPath}/product/getPoductPic.shtml",
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {productId:productId},
			timeout : 30000,
			success : function(data) {

				if(data&&data.length>0){
					for (var i=0;i<data.length;i++)
					{	if(data[i].pic.indexOf("http") >= 0){
						$("#viewer-pictures").append('<li><img data-original="'+data[i].pic+'" src="'+data[i].pic+'" alt=""></li>');
					}else{
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
					}
					}
					viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
							$("#viewer-pictures").hide();
						}});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}


			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});

	}

	function loadData() {
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/resourceLocationManagement/memberLotteryData.shtml',
			data: {},
			dataType: 'json',
			success: function (data) {
				if(data.statusCode != null && data.statusCode == "0000") {
					$("#perIntegralSpend").val(data.PER_INTEGRAL_SPEND);
					$("#shareForFreeTimes").val(data.SHARE_FOR_FREE_TIMES);
					var lotterySettings = data.lotterySettings;
					var sourceNicheCustoms = data.sourceNicheCustoms;
					for (let i = 0; i < lotterySettings.length; i++) {
						let type = lotterySettings[i].type;
						if(type == '1'){
							let content = '<table style="width: 200px;height: 150px">'
									+'<tr style="height: 120px"><td style="text-align: center">'+lotterySettings[i].integral+'积分</td></tr>'
									+'<tr style="height: 30px"><td style="text-align: center">中奖概率:'+parseFloat(lotterySettings[i].winningProbability)*1000+'/1000</td></tr>'
									+'</table>';
							$("#turntable_content_"+lotterySettings[i].seqNo).html(content);
						}else if(type == '2'){
							let content = '<div style="margin: 0 auto;text-align:center;">谢谢惠顾</div>';
							if(lotterySettings[i].remarks != 0){
								content = '<table style="width: 200px;height: 150px">'
										+'<tr style="height: 30px"><td style="text-align: center"></td></tr>'
										+'<tr style="height: 30px"><td style="text-align: center">优惠券</td></tr>'
										+'<tr style="height: 30px"><td style="text-align: center">'+lotterySettings[i].minAmount+'元 <= A <'+lotterySettings[i].maxAmount+'元</td></tr>'
										+'<tr style="height: 30px"><td style="text-align: center"><a href="javascript:showCoupons('+lotterySettings[i].minAmount+','+lotterySettings[i].maxAmount+');">查看优惠券</a></td></tr>'
										+'<tr style="height: 30px"><td style="text-align: center">中奖概率:'+parseFloat(lotterySettings[i].winningProbability)*1000+'/1000</td></tr>'
										+'</table>';
							}
							$("#turntable_content_"+lotterySettings[i].seqNo).html(content);
						}else if(type == '3'){
							if(sourceNicheCustoms.length == 0){
								$("#turntable_content_"+lotterySettings[i].seqNo).html('<div style="margin: 0 auto;text-align:center;">谢谢惠顾</div>');
							}else {
								let picPath;
								if(sourceNicheCustoms[0].pic!=null&&(sourceNicheCustoms[0].pic.indexOf("http") >= 0)){
									picPath = "<div class='no-check' style='display:  inline-flex;'><img src='"+sourceNicheCustoms[0].pic+"' width='80' height='80' onclick='viewerPic("+sourceNicheCustoms[0].linkId+")'>";
								}else{
									picPath = "<div class='no-check' style='display:  inline-flex;'><img src='${pageContext.request.contextPath}/file_servelt/"+sourceNicheCustoms[0].pic+"' width='80' height='80' onclick='viewerPic("+sourceNicheCustoms[0].linkId+")'>";
								}
								let content = '<table style="width: 200px;height: 150px">'
										+'<tr style="height: 80px"><td style="text-align: center">'+picPath+'</td></tr>'
										+'<tr style="height: 40px"><td style="text-align: center">'+sourceNicheCustoms[0].productName+'</td></tr>'
										+'<tr style="height: 30px"><td style="text-align: center">活动价:'+sourceNicheCustoms[0].salePriceMax+'</td></tr>'
										+'</table>';
								sourceNicheCustoms.splice(0,1);
								$("#turntable_content_"+lotterySettings[i].seqNo).html(content);
							}
						}
					}
				}else {
					commUtil.alertError(data.message);
				}
			},
			error: function (e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}

	function updateTurntableTd(id) {
		$.ligerDialog.open({
			height: 260,
			width: 1200,
			title: "修改",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/editLotterySettingsView.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	function showCoupons(minAmount, maxAmount) {
		$.ligerDialog.open({
			height: $(window).height() - 100,
			width: 1300,
			title: "查看优惠券",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/integralLotteryRoute.shtml?pagetype=10005&moneyMin=" + minAmount + "&moneyMax=" + maxAmount,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	function recoverTurntableSettings() {
		$("#perIntegralSpend").attr('disabled','disabled');
		$("#shareForFreeTimes").attr('disabled','disabled');
		$("#saveTurntableSettings").attr('style','margin:0 auto;display:none');
		$("#editTurntableSettings").attr('style','margin:0 auto');
	}

	function editTurntableSettings() {
		$("#perIntegralSpend").removeAttr('disabled');
		$("#shareForFreeTimes").removeAttr('disabled');
		$("#editTurntableSettings").attr('style','margin:0 auto;display:none');
		$("#saveTurntableSettings").attr('style','margin:0 auto');
	}

	function saveTurntableSettings() {
		let perIntegralSpend = $("#perIntegralSpend").val();
		let shareForFreeTimes = $("#shareForFreeTimes").val();
		let reg = /^[1-9]\d*$/;
		if(!reg.test(perIntegralSpend) || !reg.test(shareForFreeTimes)){
			commUtil.alertError("填写格式有误!");
			return false;
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/resourceLocationManagement/saveTurntableSettings.shtml',
			data: {perIntegralSpend : perIntegralSpend, shareForFreeTimes : shareForFreeTimes},
			dataType: 'json',
			success: function (data) {
				if(data.statusCode != null && data.statusCode == "0000") {
					recoverTurntableSettings();
					loadData();
				}else {
					commUtil.alertError(data.message);
				}
			},
			error: function (e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div>
		<table class="turntable">
			<tr>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 20px">
							<td>位置①</td>
						</tr>
						<tr style="height: 150px">
							<td id="turntable_content_1"><div style="margin: 0 auto;text-align:center;">谢谢惠顾</div></td>
						</tr>
						<tr style="height: 30px">
							<td ><div style="margin:0 auto" class="updatebtn" onclick="updateTurntableTd(1)">修改</div></td>
						</tr>
					</table>
				</td>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 20px">
							<td>位置②</td>
						</tr>
						<tr style="height: 150px">
							<td id="turntable_content_2"><div style="margin: 0 auto;text-align:center;">谢谢惠顾</div></td>
						</tr>
						<tr style="height: 30px">
							<td ><div style="margin:0 auto" class="updatebtn" onclick="updateTurntableTd(2)">修改</div></td>
						</tr>
					</table>
				</td>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 20px">
							<td>位置③</td>
						</tr>
						<tr style="height: 150px">
							<td id="turntable_content_3"><div style="margin: 0 auto;text-align:center;">谢谢惠顾</div></td>
						</tr>
						<tr style="height: 30px">
							<td ><div style="margin:0 auto" class="updatebtn" onclick="updateTurntableTd(3)">修改</div></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 20px">
							<td>位置⑧</td>
						</tr>
						<tr style="height: 150px">
							<td id="turntable_content_8"><div style="margin: 0 auto;text-align:center;">谢谢惠顾</div></td>
						</tr>
						<tr style="height: 30px">
							<td ><div style="margin:0 auto" class="updatebtn" onclick="updateTurntableTd(8)">修改</div></td>
						</tr>
					</table>
				</td>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 80px">
							<td>
								&nbsp;&nbsp;抽奖每次消耗积分:
								<br>
								&nbsp;&nbsp;<input id="perIntegralSpend" style="width: 80px" disabled value="">积分
							</td>
						</tr>
						<tr style="height: 80px">
							<td>
								&nbsp;&nbsp;分享后可免费次数:
								<br>
								&nbsp;&nbsp;<input id="shareForFreeTimes" style="width: 80px" disabled value="">次
							</td>
						</tr>
						<tr style="height: 40px">
							<td>
								<div style="margin:0 auto" id="editTurntableSettings" class="updatebtn" onclick="editTurntableSettings()">编辑</div>
								<div style="margin:0 auto;display:none" id="saveTurntableSettings" class="updatebtn" disabled onclick="saveTurntableSettings()">确认</div>
							</td>
						</tr>
					</table>
				</td>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 20px">
							<td>位置④</td>
						</tr>
						<tr style="height: 150px">
							<td id="turntable_content_4"><div style="margin: 0 auto;text-align:center;">谢谢惠顾</div></td>
						</tr>
						<tr style="height: 30px">
							<td ><div style="margin:0 auto" class="updatebtn" onclick="updateTurntableTd(4)">修改</div></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 20px">
							<td>位置⑦</td>
						</tr>
						<tr style="height: 150px">
							<td id="turntable_content_7"><div style="margin: 0 auto;text-align:center;">谢谢惠顾</div></td>
						</tr>
						<tr style="height: 30px">
							<td ><div style="margin:0 auto" class="updatebtn" onclick="updateTurntableTd(7)">修改</div></td>
						</tr>
					</table>
				</td>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 20px">
							<td>位置⑥</td>
						</tr>
						<tr style="height: 150px">
							<td id="turntable_content_6"><div style="margin: 0 auto;text-align:center;">谢谢惠顾</div></td>
						</tr>
						<tr style="height: 30px">
							<td ><div style="margin:0 auto" class="updatebtn" onclick="updateTurntableTd(6)">修改</div></td>
						</tr>
					</table>
				</td>
				<td class="turntable_td">
					<table style="width: 200px;height: 200px">
						<tr style="height: 20px">
							<td>位置⑤</td>
						</tr>
						<tr style="height: 150px">
							<td id="turntable_content_5"><div style="margin: 0 auto;text-align:center;">谢谢惠顾</div></td>
						</tr>
						<tr style="height: 30px">
							<td ><div style="margin:0 auto" class="updatebtn" onclick="updateTurntableTd(5)">修改</div></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
</html>
