<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDateEditor.js" ></script>
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/clipboard.min.js"></script>
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
 
<script type="text/javascript">
	 
	 var viewerPictures;
	 
	 $(function() {
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		 
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 135
		});
		
		//复制
		var clipboard = new Clipboard(".copySprUrl");  
		clipboard.on('success', function(e) {  
            console.log(e);
	    });  
		clipboard.on('error', function(e) {  
            console.log(e);
	    });
		
		
	 });
	 
	 function viewerPic(imgPath){
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		imgPath=imgPath.replace('_S', '');
			$("#viewer-pictures").append('<li><img data-original="'+imgPath+'" src="'+imgPath+'" alt=""></li>');
			viewerPictures=new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
			$("#viewer-pictures").hide();
		}});
		viewerPictures.show();
	 }
	 
	 //添加渠道
	 function addWeixinXcxSprChnl() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "添加渠道",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/weixinXcxSprChnl/editWeixinXcxSprChnlManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //添加链接
	 function addWeixinXcxSprDtl() {
		 $.ligerDialog.open({
				height: 500,
				width: 600,
				title: "添加链接",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/weixinXcxSprDtl/addWeixinXcxSprDtlManager.shtml",
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 //复制
	 function copySprUrl(weixinXcxSprDtlId) {
		 $("#copySprUrl_"+weixinXcxSprDtlId).attr("style", "color: red;");
         setTimeout(function(){ $("#copySprUrl_"+weixinXcxSprDtlId).attr("style", ""); }, 3000);
	 }
	 
	 //删除推广计划
	 function delWeixinXcxSprDtl(weixinXcxSprDtlId) {
		 $.ligerDialog.confirm("是否删除？", function(yes) {
			 if(yes) { 
				 $.ajax({
					 type : 'POST',
					 url : "${pageContext.request.contextPath}/weixinXcxSprDtl/delWeixinXcxSprDtl.shtml",
					 data : {weixinXcxSprDtlId : weixinXcxSprDtlId},
					 dataType : 'json',
					 success : function(data){
						 if(data == null || data.code != 200) {
							 commUtil.alertError(data.msg);
						 }else {
							 $("#searchbtn").click();
						 }
					 },
					 error : function(e) {
						 commUtil.alertError("系统异常请稍后再试！");
					 }
				 });
			 }
		 });
	 }
	 
 	 var listConfig={ 
	      url:"/weixinXcxSprDtl/getWeixinXcxSprDtlList.shtml",
	      btnItems:[
		      { text: '添加渠道', icon:'add', id:'add', dtype:'win', click:addWeixinXcxSprChnl },
		      { text: '添加链接', icon:'add', id:'add', dtype:'win', click:addWeixinXcxSprDtl }
	      ],
	      listGrid:{ columns: [
							{display:'ID', name:'id', align:'center', isSort:false, width:100},
							{display:'渠道', name:'spr_chnl_name', align:'center', isSort:false, width:120},
							{display:'投放广告位置', name:'spr_plan_name', align:'center', isSort:false, width:120},
							
							{display:'缩略图', name:'pic', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
			                    var html = [];
			                    if(rowdata.pic != null && rowdata.pic != '') {
			                    	html.push("<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' onclick='viewerPic(this.src)'>");
			                    }
			                    return html.join("");
							}},
							
							{display:'页面类型', name:'spr_type_desc', align:'center', isSort:false, width:120},
							{display:'页面ID', name:'spr_type_value_id', align:'center', isSort:false, width:100},
							{display:'点击打开次数', name:'count_weixin_xcx_spr_log', align:'center', isSort:false, width:100},
							{display:'IP数', name:'count_weixin_xcx_spr_log_ip', align:'center', isSort:false, width:100},
							{display:'用户数', name:'count_weixin_xcx_spr_log_member_id', align:'center', isSort:false, width:100},
							{display:'新客母订单数', name:'new_guest_combine_order_count', align:'center', isSort:false, width:100},
							{display:'新客消费人数', name:'new_guest_consume_count', align:'center', isSort:false, width:100},
							{display:'新客买家实付金额', name:'new_guest_pay_amount', align:'center', isSort:false, width:100},
							{display:'老客母订单数', name:'old_guest_combine_order_count', align:'center', isSort:false, width:100},
							{display:'老客消费人数', name:'old_guest_consume_count', align:'center', isSort:false, width:100},
							{display:'老客买家实付金额', name:'old_guest_pay_amount', align:'center', isSort:false, width:100},
							{display:'总销售额', name:'total_pay_amount', align:'center', isSort:false, width:100},
							{display:'创建时间', name:'', align:'center', isSort:false, width:160, render:function(rowdata, rowindex) {
			                	if(rowdata.create_date == null || rowdata.create_date == '' ) {
									return "";
								}else{
									var createDate = new Date(rowdata.create_date);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								}
			                }},
							{display:'链接', name:'remarks', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='copySprUrl("+ rowdata.id +");' class='copySprUrl' id='copySprUrl_"+ rowdata.id +"' data-clipboard-text='"+ rowdata.spr_url +"' >【复制】</a>");
							    return html.join("");
							}},
							{display:'二维码', name:'createStaffName', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.spr_qr_code != null && rowdata.spr_qr_code != '') {
									html.push("<img alt='' src='${pageContext.request.contextPath}/file_servelt"+ rowdata.spr_qr_code +"' onclick='viewerPic(this.src);' style='width:60px;height:60px;'>");
								}
							    return html.join("");
							}},
			                {display:'操作', name:'', align:'center', isSort:false, width:100, render: function(rowdata, rowindex) {
								var html = [];
								html.push("<a href='javascript:;' onclick='delWeixinXcxSprDtl("+rowdata.id+");'>【删除】</a>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
	                 showRownumber:true //不设置默认为 true
	      }, 							
	      container:{
	        toolBarName:"toptoolbar",
	        searchBtnName:"searchbtn",
	        fromName:"dataForm",
	        listGridName:"maingrid"
	      }        
	  };
 
	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server" >
		<div class="search-pannel" >
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="weixinXcxSprDtlId" name="weixinXcxSprDtlId" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >投放广告位置：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sprPlanName" name="sprPlanName" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">创建日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" style="width: 135px;"  />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" style="width: 135px;" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >渠道：</div>
					<div class="search-td-combobox-condition" >
						<select id="weixinXcxSprChnlId" name="weixinXcxSprChnlId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="weixinXcxSprChnl" items="${weixinXcxSprChnlList }">
								<option value="${weixinXcxSprChnl.id }">${weixinXcxSprChnl.sprChnlName }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >页面类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="sprType" name="sprType" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="sprType" items="${sprTypeList }">
								<option value="${sprType.statusValue }">${sprType.statusDesc }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">统计日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCountDate" name="beginCountDate" class="dateEditor" value="${beginDate }" style="width: 135px;" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCountDate" name="endCountDate" class="dateEditor" value="${endDate }" style="width: 135px;" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >页面ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sprValue" name="sprValue" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >访问类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="accessType" name="accessType" style="width: 135px;" >
							<option value="1">首次访问</option>
							<option value="2">最新访问</option>
						</select>
					</div>
				</div>
			 	<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">

	</ul>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
