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
	 function updateIsShow(commentId, isShow,isCopy) {
		 $.ajax({
			url : "${pageContext.request.contextPath}/comment/updateIsShow.shtml",
			type : 'POST',
			dataType : 'json',
			data : {commentId : commentId, isShow : isShow},
			success : function(data) {
				if(data.code == '200') {
					/* $("#searchbtn").click(); */
					if(data.isShow == '1') {
					     var operatioHtml=[];
					     if(isCopy != 0){
							 operatioHtml.push("<a id='"+ commentId +"-a' href=\"javascript:viewDetail("+data.subOrderId+");\">【查看订单】</a><br>");
						 }
					     operatioHtml.push("<a id='"+ commentId +"-a' href=\"javascript:updateIsShow("+ commentId +",0);\">【隐藏】</a>");
					     $("#"+commentId+"-a").parent("div").html(operatioHtml.join(""));

						 $("#"+commentId+"-label").parent("div").html("<label id='"+ commentId +"-label'>显示</label>");

					 }else {
						 var strHtml = [];
						 if(isCopy != 0){
							strHtml.push("<a id='"+ commentId +"-a' href=\"javascript:viewDetail("+data.subOrderId+");\">【查看订单】</a><br>");
						 }
						 strHtml.push("<a id='"+ commentId +"-a' href=\"javascript:updateIsShow("+ commentId +",1);\">【显示】</a>");
						 $("#"+commentId+"-a").parent("div").html(strHtml.join(""));

					     $("#"+commentId+"-label").parent("div").html("<label id='"+ commentId +"-label'>隐藏</label>");

					 }

				}else {
					$.ligerDialog.error(data.msg);
				}
			},
			error : function() {
				$.ligerDialog.error('操作超时，请稍后再试！');
			}
	 	 });
	 }

 	 var listConfig={
	      url:"/comment/getCommentList.shtml",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'订单编号',name:'subOrderCode', align:'center', isSort:false, width:180},
							{display:'收货人',name:'receiverName', align:'center', isSort:false, width:100},
							{display:'评价时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
			                	var html = [];
								if(rowdata.createDate != null && rowdata.createDate != '' ) {
									var createDate = new Date(rowdata.createDate);
									html.push(createDate.format("yyyy-MM-dd hh:mm:ss"));
								}
								return html.join("");
			                }},
			                {display:'商品',name:'', align:'left', isSort:false, width:400, render:function(rowdata, rowindex) {
								var html = [];
								html.push("<div style='overflow:hidden;'><img style='float:left;margin: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+rowdata.skuPic+"' width='80' height='80' >");
								html.push("<p style='padding-top: 5px;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;width: calc(100%-100px);' >"+rowdata.brandName+"&nbsp;&nbsp;"+rowdata.productName+"&nbsp;&nbsp;"+rowdata.artNo+"</p>");
								html.push("<p>商品ID:"+rowdata.productCode+"</p>");
								html.push("<div style='margin-top: 15px;'><p style='color: #21a5fa;'>规格："+rowdata.productPropDesc+"</p>");
								html.push("<p style='color: gray;'>SKU码："+rowdata.sku+"</sapn></div></div>");

								html.push("<div class='star-div' style='overflow:hidden;'><div style='float: left;margin: 0px 15px 0px 5px; '>商品描述</div><div class='starBox' >");
								html.push("<ul class='star' >");
								html.push("<li><a href='javascript:void(0)'  class='one-star' style='cursor: default;'>1</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='two-stars' style='cursor: default;'>2</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='three-stars' style='cursor: default;'>3</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='four-stars' style='cursor: default;'>4</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='five-stars' style='cursor: default;'>5</a></li>");
								html.push("</ul>");
								html.push("<div class='current-rating' style='width: "+rowdata.productScore*24+"px;'></div></div></div>");

								html.push("<div class='star-div' style='overflow:hidden;'><div style='float: left;margin: 0px 15px 0px 5px; '>卖家服务</div><div class='starBox' >");
								html.push("<ul class='star' >");
								html.push("<li><a href='javascript:void(0)'  class='one-star' style='cursor: default;'>1</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='two-stars' style='cursor: default;'>2</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='three-stars' style='cursor: default;'>3</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='four-stars' style='cursor: default;'>4</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='five-stars' style='cursor: default;'>5</a></li>");
								html.push("</ul>");
								html.push("<div class='current-rating' style='width: "+rowdata.mchtScore*24+"px;'></div></div></div>");

								html.push("<div class='star-div' style='overflow:hidden;'><div style='float: left;margin: 0px 15px 0px 5px; '>物流服务</div><div class='starBox' >");
								html.push("<ul class='star' >");
								html.push("<li><a href='javascript:void(0)'  class='one-star' style='cursor: default;'>1</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='two-stars' style='cursor: default;'>2</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='three-stars' style='cursor: default;'>3</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='four-stars' style='cursor: default;'>4</a></li>");
								html.push("<li><a href='javascript:void(0)'  class='five-stars' style='cursor: default;'>5</a></li>");
								html.push("</ul>");
								html.push("<div class='current-rating' style='width: "+rowdata.wuliuScore*24+"px;'></div></div></div>");

								return html.join("");
							}},
			                {display:'评价内容',name:'', align:'left', isSort:false, width:400, render:function(rowdata, rowindex) {
								var html = [];
								var str = rowdata.content;
								if(str) {
									/* if(str.length > 64) {
										html.push("<div>"+str.substring(0, 62)+"...</div>");
									}else {
										html.push("<div>"+str+"</div>");
									} */

									html.push("<div style='margin-left: 5px;'>"+str+"</div>");
								}else {
									html.push("<div style='margin-left: 5px;'>此用户没有发布评价！</div>");
								}
								if(rowdata.commentPic) {
									var commentPics = rowdata.commentPic.split(",");
									for(var i=0;i<commentPics.length;i++) {
										html.push("<img style='margin-left: 5px;margin-bottom: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+commentPics[i]+"' width='60' height='60' onclick='viewerPic("+rowdata.id+", \""+commentPics[i]+"\")'>");
									}
								}

								if(rowdata.appendCommentId) {
									if(rowdata.appendReplyDate == '0') {
										html.push("<div style='color: red; margin-left: 5px;'>当天追评</div>");
									}else {
										html.push("<div style='color: red; margin-left: 5px;'>"+rowdata.appendReplyDate+"天后追评</div>");
									}
									html.push("<div style='margin-left: 5px;'>"+rowdata.appendCommentContent+"</div>");
									if(rowdata.appendCommentPic) {
										var appendCommentPics = rowdata.appendCommentPic.split(",");
										for(var i=0;i<appendCommentPics.length;i++) {
											html.push("<img style='margin-left: 5px;margin-bottom: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+appendCommentPics[i]+"' width='60' height='60' onclick='viewerPic("+rowdata.appendCommentId+", \""+appendCommentPics[i]+"\")'>");
										}
									}
								}

								if(rowdata.mchtReplyDate) {
									if(rowdata.mchtReplyDay == '0') {
										html.push("<div style='color: red; margin-left: 5px;'>当天回复</div>");
									}else {
										html.push("<div style='color: red; margin-left: 5px;'>"+rowdata.mchtReplyDay+"天后回复</div>");
									}
									html.push("<div style='margin-left: 5px;color: gray;'>"+rowdata.mchtReply+"</div>");
									if(rowdata.mchtCommentPic) {
										var mchtCommentPics = rowdata.mchtCommentPic.split(",");
										for(var i=0;i<mchtCommentPics.length;i++) {
											html.push("<img style='margin-left: 5px;margin-bottom: 5px;' src='${pageContext.request.contextPath}/file_servelt/"+mchtCommentPics[i]+"' width='60' height='60' onclick='viewerPic("+rowdata.id+", \""+mchtCommentPics[i]+"\", \"mcht\")'>");
										}
									}
								}

								return html.join("");
							}},
							{display:'权重',name:'commentWeight', align:'left', isSort:false, width:100, render: function(rowdata, rowindex) {
								return '<input value="'+rowdata.commentWeight+'" style="width:90px;" onblur="saveCommentWeight(this,'+rowdata.id+');">';
							}},
							{display:'隐藏状态',name:'', align:'center', isSort:false, width:80,render:function(rowdata, rowindex){
								  var html=[];
								 if (rowdata.isShow=='1') {
									 html.push("<label id='"+ rowdata.id+"-label'>显示</label>");
								 }else {
									 html.push("<label id='"+ rowdata.id +"-label'>隐藏</label>");
								}
								 return html.join("");
							}},
							{display:'操作',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.subOrderId !=0){
									html.push("<a id='"+ rowdata.id +"-a' href='javascript:;' onclick='viewDetail(" + rowdata.subOrderId + ");'>【查看订单】</a></br>");
								}
									/* html.push("<a href='javascript:;' onclick='viewComment("+rowdata.id+")'>【评价详情】</a>"); */
								if(rowdata.isShow == '1') {
									html.push("<a id='"+ rowdata.id +"-a' href='javascript:;' onclick='updateIsShow("+rowdata.id+", 0,"+rowdata.subOrderId+")'>【隐藏】</a>");
								}else {
									html.push("<a id='"+ rowdata.id +"-a' href='javascript:;' onclick='updateIsShow("+rowdata.id+", 1,"+rowdata.subOrderId+")'>【显示】</a>");
								}
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
 
 	var productTypeCombo;
    $(function(){
    	productTypeCombo = $("#productTypeName").ligerComboBox({
    		  width: 135,
              selectBoxWidth: 135,
              selectBoxHeight: 200,  
              valueField: 'id',
              textField: 'name',
              valueFieldID: 'productType',
              treeLeafOnly: false,
              valueField: 'id',
              resize: true,
              tree: {
            	  onClick: function(note){
            		  if(!productTypeCombo.treeManager.hasChildren(note.data)) {
	            		  productTypeCombo.treeManager.loadData(note.target, '${pageContext.request.contextPath}/service/prod/product_type/getProductTypeTree.shtml?parentId='+note.data.id);
					  }
            	  },
            	  url: '${pageContext.request.contextPath}/service/prod/product_type/getProductTypeTree.shtml', 
            	  checkbox: false, 
            	  ajaxType: 'get', 
            	  idFieldName: 'id',
            	  textFieldName: 'name',
            	  parentIDFieldName: 'parentId',
            	  isExpand: false
              }
          });
    	
    });
	  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<!-- <div id="toptoolbar"></div> -->
	<form id="dataForm" runat="server" >
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >评价类型：</div>
					<div class="search-td-combobox-condition" >
						<select id="commentStatus" name="commentStatus" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1" selected="selected">有效评价</option>
							<option value="2">有图</option>
							<option value="3">有追评</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家序号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtCode" name="mchtCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >商家名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="mchtName" name="mchtName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >品牌名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="brandName" name="brandName" >
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >商品ID：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="productCode" name="productCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >订单编号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="subOrderCode" name="subOrderCode" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >隐藏状态：</div>
					<div class="search-td-combobox-condition" >
						<select id="isShow" name="isShow" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach items="${isShowList }" var="isShow">
								<option value="${isShow.statusValue }">${isShow.statusDesc }</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >分类：</div>
					<div  class="search-td-combobox-condition">
						<input id="productTypeName" type="text" >
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label" >评价等级：</div>
					<div class="search-td-combobox-condition" >
						<select id="commentGrade" name="commentGrade" style="width: 135px;" >
							<option value="">请选择...</option>
							<option value="1">好评</option>
							<option value="2">中评</option>
							<option value="3">差评</option>
						</select>
				 	 </div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >评价内容：</div>
					<div  class="search-td-combobox-condition">
						<input id="content" name="content" type="text" >
					</div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">评价日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="beginCreateDate" name="beginCreateDate" class="dateEditor" value="${beginCreateDate }" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endCreateDate" name="endCreateDate" class="dateEditor" value="${endCreateDate }" />
					</div>
				</div>
				<!-- <div class="search-td">
					<div class="search-td-label" >
						<label>
							<input type="checkbox" id="contentFlag" name="contentFlag" value="1" checked="checked" />不展示默认好评
						</label>
					</div>
				</div> -->
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
