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
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>

<link href="${pageContext.request.contextPath}/css/glyphicon.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/star.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.star-div{
		font: normal 12px/2em '微软雅黑';
		padding: 0;
		margin:	0
	}
	ul,li{
		list-style: none
	}
	a{
		color: #09f;
	}
</style>

 <script type="text/javascript">
 function saveCommentWeight(_this,id){
	 var commentWeight = $.trim($(_this).val());
	 if(commentWeight){
		if(!testNumber(commentWeight)){
			commUtil.alertError("权重只能是正整数");
			return false;
		}else{
			$.ajax({
				url : "${pageContext.request.contextPath}/comment/saveCommentWeight.shtml",
				type : 'POST',
				dataType : 'json',
				cache : false,
				async : false,
				data : {id : id,commentWeight:commentWeight},
				timeout : 30000,
				success : function(data) {
					if(data && data.code == 200){
						
					}else{
						$.ligerDialog.error('修改失败，请稍后再试！');
					}
				},
				error : function() {
					$.ligerDialog.error('操作超时，请稍后再试！');
				}
			});
		}
	}else{
		commUtil.alertError("权重只能是正整数");
		return false;
	}
 }
 	var viewerPictures;
 	var viewerFlag = true;
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd",
			labelAlign : 'left',
			width : 150
		});
		
		viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
		
	 });
 
	 function formatMoney(s, n) {
	    n = n > 0 && n <= 20 ? n : 2;
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(),
	    r = s.split(".")[1];
	    t = "";
	    for(i = 0; i < l.length; i ++ )
	    {
	       t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "" : "");
	    }
	    return t.split("").reverse().join("") + "." + r;
	 }
	 
	 //放大图片
	 function viewerPic(commentId, src, flag) {
		var url = "${pageContext.request.contextPath}/comment/getCommentPicList.shtml";
		if(flag == 'mcht') {
			url = "${pageContext.request.contextPath}/comment/getCommentMchtPicList.shtml";
		}
		viewerFlag = true;
		$("#viewer-pictures").empty();
		viewerPictures.destroy();
		$.ajax({
			url : url,
			type : 'POST',
			dataType : 'json',
			cache : false,
			async : false,
			data : {commentId : commentId},
			timeout : 30000,
			success : function(data) {
				if(data && data.length > 0){
					var ind = 0;
					for (var i=0;i<data.length;i++) {
						if(data[i].pic == src) {
							ind = i;
						}
						$("#viewer-pictures").append('<li><img data-original="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" src="${pageContext.request.contextPath}/file_servelt'+data[i].pic+'" alt=""></li>');
					}
					viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {
						url: 'data-original',
						hide: function(){
							$("#viewer-pictures").hide();
						},
						viewed: function() {
							if(viewerFlag) {
								viewerPictures.view(ind);
								viewerFlag = false;
							}
						}
					});
					$("#viewer-pictures").show();
					viewerPictures.show();
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
		});
		
	 }
	 
	 //订单详情
	 function viewDetail(id) {
		$.ligerDialog.open({
	 		height: $(window).height(),
			width: $(window).width()-50,
			title: "子订单详情",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/order/sub/detail.shtml?id=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //评价详情
	 function viewComment(commentId) {
		 $.ligerDialog.open({
				height: 600,
				width: 470,
				title: "评价详情",
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/comment/viewComment.shtml?commentId="+commentId,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
		});
	 }
	 
	 // 是否显示
	 function updateIsShow(commentId, isShow) {
		 $.ajax({
			url : "${pageContext.request.contextPath}/comment/updateIsShow.shtml",
			type : 'POST',
			dataType : 'json',
			data : {commentId : commentId, isShow : isShow},
			success : function(data) {
				if(data.code == '200') {
					$("#searchbtn").click();
				}else {
					$.ligerDialog.error(data.msg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
	 	 });
	 }
	 //审核，查看
	 function toZsCheck(id,isView){
		 var title="";
		 if(isView == 0){
			 title="招商审核变更信息";
		 }else{
			 title="查看";
		 }
		 $.ligerDialog.open({
				height: $(window).height()*0.9,
				width: $(window).width()*0.9,
				title: title,
				name: "INSERT_WINDOW",
				url: "${pageContext.request.contextPath}/cooperationChangeApply/toZsCheck.shtml?id="+id+"&isView="+isView,
				showMax: true,
				showToggle: false,
				showMin: false,
				isResize: true,
				slide: false,
				data: null
			});
	 }
	 
 	 var listConfig={ 
	      url:"/cooperationChangeApply/zsCheckData.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'商家提审时间',name:'createDate', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	var html = [];
								if(rowdata.createDate) {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
			                }},   
							{display:'商家序号',name:'mchtCode', align:'center', isSort:false, width:100}, 
							{display:'公司名称',name:'companyName', align:'center', isSort:false, width:180},
			                {display:'主营类目',name:'productTypeName', align:'center', isSort:false, width:80},
							{display:'变更类型',name:'changeApplyType', align:'center', isSort:false, width:150, render: function(rowdata, rowindex) {
								var html=[];
									if(rowdata.changeApplyType == null) {
										return html.join("");
									}
								if(rowdata.changeApplyType.indexOf("1")>=0){
									html.push("店铺名称变更<br>");
								}
								if(rowdata.changeApplyType.indexOf("2")>=0){
									html.push("店铺主营类目变更<br>");
								}
								if(rowdata.changeApplyType.indexOf("3")>=0){
									html.push("品牌技术服务费变更<br>");
								}
								if(rowdata.changeApplyType.indexOf("4")>=0){
									html.push("保证金变更");
								}
								return html.join("");
							}},
							{display:'审核状态',name:'auditStatus', align:'center', isSort:false, width:80, render: function(rowdata, rowindex) {
								if(rowdata.zsAuditStatus == 0){
									return "待审";
								}else if(rowdata.zsAuditStatus == 1){
									return "通过";
								}else if(rowdata.zsAuditStatus == 2 || rowdata.fwAuditStatus == 2){
									return "驳回";
								}
							}}, 
							{display:'备注/驳回原因',name:'remarks', align:'center', isSort:false, width:300, render: function(rowdata, rowindex) {
								if(rowdata.zsAuditRemarks){
									return rowdata.zsAuditRemarks;
								}
							}}, 
							{display:'操作',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								if(rowdata.zsAuditStatus == 0){
									var canCheck = "${canCheck}";
									if(canCheck>0){
										return "<a href='javascript:;' onclick='toZsCheck(" + rowdata.id + ",0);'>审核</a>";
									}else{
										return "<a href='javascript:;' onclick='toZsCheck(" + rowdata.id + ",1);'>查看</a>";
									}
								}else{
									return "<a href='javascript:;' onclick='toZsCheck(" + rowdata.id + ",1);'>查看</a>";
								}
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
 
    $(function(){
    	
    });
	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">商家提审时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" value="${beginCreateDate}" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" value="${endCreateDate}" />
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
			
				<div class="search-td">
					<div class="search-td-label" >变更类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="changeApplyType" name="changeApplyType" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">店铺名称变更</option>
							<option value="2">店铺主营类目变更</option>
							<option value="3">品牌技术服务费变更</option>
							<option value="4">保证金变更</option>
						</select>
				 	 </div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >审核状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="auditStatus" name="auditStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="0">待审</option>
							<option value="1">通过</option>
							<option value="2">驳回</option>
						</select>
				 	 </div>
				</div>
			
				<div class="search-td">
					<div class="search-td-label" >主营类目：</div>
					<div class="search-td-combobox-condition" >
						<select id="productTypeId" name="productTypeId" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${sysStaffProductTypeCustomList}" var="sysStaffProductTypeCustom">
								<option value="${sysStaffProductTypeCustom.productTypeId}">${sysStaffProductTypeCustom.staffName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">对接人：</div>
					<div class="search-td-condition">
						<select id="platContactStaffId" name="platContactStaffId">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID }" selected="selected" >我自己</option>
							<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
							</c:forEach>
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
