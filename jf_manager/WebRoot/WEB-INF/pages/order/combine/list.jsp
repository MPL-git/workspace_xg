<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<style type="text/css">
	.color-s0,.color-fs1{color: #0000FF;}
	.color-s1,.color-fs2{color: #008000;}
	.color-s4{color: #333333;}
	.color-fs0{color: #000000;}
</style>
<script type="text/javascript">

	$(function(){
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left',
			labelwidth: 120,
			width:120
		});

	});

	function viewDetail(id) {
		$.ligerDialog.open({
			height: $(window).height(),
			width: $(window).width()-50,
			title: "母订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/combine/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}

	var listConfig={
		url:"/order/combine/data.shtml",
		listGrid:{ columns: [
				{ display: "母订单编号", width: 160, render: function(rowdata, rowindex) {
						var html = [];
						html.push("<a href=\"javascript:viewDetail(" + rowdata.id + ");\">"+rowdata.combineOrderCode+"</a>");
						return html.join("");
					}},
				{ display: '品牌/货号', width: 180, render: function (rowdata, rowindex) {
						var artBrandGroup=rowdata.artBrandGroup;
						if (artBrandGroup!=null){
							return artBrandGroup.replace(/,/g,"<br>");
						}
					}},
				{ display: '付款渠道', width: 80, name: 'paymentName',render: function (rowdata, rowindex) {
						if(rowdata.orderType == 7){
							return "积分转盘";
						}else {
							return rowdata.paymentName;
						}
					}},
				{ display: '实付金额', width: 80, name: 'totalPayAmount'},
				{ display: '母订单状态', width: 80, render: function (rowdata, rowindex) {
						return "<span class='color-s"+rowdata.status+"'>"+rowdata.statusDesc+"</span>";
					}},
				{ display: '下单时间', width: 150, render: function (rowdata, rowindex) {
						if (rowdata.createDate!=null){
							var createDate=new Date(rowdata.createDate);
							return createDate.format("yyyy-MM-dd hh:mm:ss");
						}
					}},
				{ display: '付款时间', width: 150, render: function (rowdata, rowindex) {
						if (rowdata.payDate!=null){
							var payDate=new Date(rowdata.payDate);
							return payDate.format("yyyy-MM-dd hh:mm:ss");
						}
					}},
				{ display: '支付交易号', width: 220,  name: 'paymentNo',render:function(rowdata,rowindex){
						if(rowdata.orderType == 7){
							return rowdata.combineOrderCode;
						}else {
							return rowdata.paymentNo;
						}
					}},
				{ display: '来源渠道', width: 200, name: 'sprChnlDesc'},
				{ display: '推广渠道', width: 100, name: 'channel', hide:${type==1} },
				{ display: '活动名称', width: 200, name: 'spreadname', hide:${type==1} },
				{ display: '活动组', width: 100, name: 'activityname', hide:${type==1} },
				{ display: '首次活动名称', width: 200, name: 'firstSpreadname', hide:${type==1} }
			],
			showCheckbox : false,  //不设置默认为 true
			showRownumber:true //不设置默认为 true
		} ,
		container:{
			toolBarName:"toptoolbar",
			searchBtnName:"searchbtn",
			fromName:"dataForm",
			listGridName:"maingrid"
		}
	}
</script>

<body style="padding: 0px; overflow: hidden;">
<!-- <div id="toptoolbar"></div> -->
<form id="dataForm" runat="server">
	<div class="search-pannel">
		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" >母订单状态：</div>
				<div class="search-td-combobox-condition" >
					<select id="status" name="status" style="width: 135px;" >
						<option value="">请选择</option>
						<c:forEach var="list" items="${statusList}">
							<option value="${list.statusValue}">${list.statusDesc}
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >付款渠道：</div>
				<div class="search-td-combobox-condition" >
					<select id="paymentId" name="paymentId" style="width: 135px;" >
						<option value="">请选择</option>
						<c:forEach var="list" items="${sysPayments}">
							<option value="${list.id}">${list.remarks}
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >收款状态：</div>
				<div class="search-td-combobox-condition" >
					<select id="financialStatus" name="financialStatus" style="width: 135px;" >
						<option value="">请选择</option>
						<c:forEach var="list" items="${financialStatus}">
							<option value="${list.statusValue}">${list.statusDesc}
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >母订单号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id="combineOrderCode" name="combineOrderCode" >
				</div>
			</div>
		</div>

		<div class="search-tr"  >
			<div class="search-td" style="width: 40%;">
				<div class="search-td-label" style="float: left;width: 20%;">下单日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="create_date_begin" name="create_date_begin" class="dateEditor" value="${today}" style="width: 135px;" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="create_date_end" name="create_date_end" class="dateEditor" value="${today}" style="width: 135px;" />
				</div>
			</div>
			<div class="search-td" style="width: 40%;">
				<div class="search-td-label" style="float: left;width: 20%;">付款日期：</div>
				<div class="l-panel-search-item" >
					<input type="text" id="pay_date_begin" name="pay_date_begin" class="dateEditor" value="" style="width: 135px;" />
				</div>
				<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
				<div class="l-panel-search-item">
					<input type="text" id="pay_date_end" name="pay_date_end" class="dateEditor" value="" style="width: 135px;" />
				</div>
			</div>
		</div>

		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" >支付交易号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "paymentNo" name="paymentNo" >
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >收款编号：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id = "financialNo" name="financialNo" >
				</div>
			</div>
		</div>

		<div class="search-tr"  >
			<div class="search-td">
				<div class="search-td-label" >用户ID：</div>
				<div class="search-td-combobox-condition" >
					<input type="text" id="memberId" name="memberId" >
				</div>
			</div>
			<div class="search-td">
				<div class="search-td-label" >订单类型：</div>
				<div class="search-td-combobox-condition" >
					<select id="orderType" name="orderType" style="width: 135px;" >
						<option value="">请选择</option>
						<c:forEach items="${orderTypes}" var="orderType">
							<option value="${orderType.statusValue}">${orderType.statusDesc}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<c:if test="${type==2}">
				<div class="search-td">
					<div class="search-td-label" >来源渠道：</div>
					<div class="search-td-combobox-condition" >
						<select id="sprChnl" name="sprChnl" style="width: 135px;" >
							<option value="">请选择</option>
							<option value="20001">抖音</option>
							<option value="20000">微信小程序</option>
							<c:forEach var="list" items="${sprChnls}">
								<option value="${list.statusValue}">${list.statusDesc}</option>
							</c:forEach>
							<option value="10000">XG_GDT_YK(集合)</option>
							<option value="10001">XG_GDT_LS(集合)</option>
							<option value="10002">XG_GDT_YC(集合)</option>
							<option value="10003">XG_GDT_YR(集合)</option>
							<option value="10004">XG_GDT_WS(集合)</option>
							<option value="10005">XG_momo_LS(集合)</option>
							<option value="10006">XG_toutiao_JX(集合)</option>
							<option value="10007">XG_toutiao_DMZY(集合)</option>
							<option value="10008">XG_toutiao_DMZY_A002(集合)</option>
							<option value="10009">XG_toutiao_DMZY_A004(集合)</option>
							<option value="10010">XG_toutiao_DMZY_A006(集合)</option>
							<option value="10011">XG_weixin_YK(集合)</option>
							<option value="10012">XG_weixin_LD(集合)</option>
							<option value="10013">XG_GDT_DX(集合)</option>
							<option value="10014">XG_toutiao_DMZY_A008(集合)</option>
							<option value="10015">XG_toutiao_DMZY_A0010(集合)</option>
							<option value="10016">XG_toutiao_DMZY_A012(集合)</option>
							<option value="10017">XG_bilibili(集合)</option>
							<option value="10018">XG_iqiyi_MZ(集合)</option>
							<option value="10019">XG_toutiao-PZZY(集合)</option>
							<option value="10020">XG_weixin_LS(集合)</option>
							<option value="10021">XG_kuobi(集合)</option>
							<option value="10022">XG_toutiao_WSZY_A016(集合)</option>
							<option value="10023">XG_toutiao_WSZY_A020(集合)</option>
							<option value="10024">XG_toutiao_WSZY_A022(集合)</option>
							<option value="10025">XG_toutiao_WSZY(集合)</option>
							<option value="10026">XG_SX_sougou(集合)</option>
							<option value="10027">XG_LD_baidu(集合)</option>
							<option value="10028">XG_toutiao_DXZY_B011(集合)</option>
							<option value="10029">XG_toutiao_DXZY_B012(集合)</option>
							<option value="10030">XG_toutiao_DXZY_B013(集合)</option>
							<option value="10031">XG_toutiao_DXZY_B014(集合)</option>
							<option value="10032">XG_toutiao_DXZY_B015(集合)</option>
							<option value="10033">XG_toutiao_ZSZY_C011(集合)</option>
							<option value="10034">XG_toutiao_ZSZY_C015(集合)</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >推广渠道：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="channel" name="channel" >
					</div>
				</div>
			</c:if>
			<c:if test="${type!=2}">
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</c:if>
		</div>
		<%-- <c:if test="${type==2}">
            <div class="search-tr">
                <div class="search-td">
                    <div class="search-td-label" >活动名称：</div>
                    <div class="search-td-combobox-condition" >
                        <input type="text" id="spreadname" name="spreadname" >
                    </div>
                </div>
                <div class="search-td">
                    <div class="search-td-label"  >活动组：</div>
                    <div class="search-td-combobox-condition" >
                        <input type="text" id="activityname" name="activityname" >
                    </div>
                </div>
                <div class="search-td">
                    <div class="search-td-label"  >推广设备类型：</div>
                    <div class="search-td-combobox-condition" >
                        <select  id="regClient" name="regClient" style="width: 135px;" >
                            <option value="1">IOS</option>
                            <option value="2" selected >Android</option>
                        </select>
                    </div>
                </div>
                <div class="search-td-search-btn">
                    <div id="searchbtn" >搜索</div>
                </div>
            </div>
        </c:if> --%>
		<div class="search-tr">
			<div class="search-td">
				<div class="search-td-label"  >推广类型：</div>
				<div class="search-td-combobox-condition" >
					<select  id="promotionTypes" name="promotionTypes" style="width: 135px;" >
						<option value="">请选择</option>
						<option value="1">推广分润</option>
						<option value="0">无</option>
					</select>
				</div>
			</div>
			<c:if test="${type==2}">
				<div class="search-td">
					<div class="search-td-label" >活动名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="spreadname" name="spreadname" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >活动组：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="activityname" name="activityname" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >推广设备类型：</div>
					<div class="search-td-combobox-condition" >
						<select  id="regClient" name="regClient" style="width: 135px;" >
							<option value="1">IOS</option>
							<option value="2" selected >Android</option>
						</select>
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn" >搜索</div>
				</div>
			</c:if>
		</div>

		<div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>