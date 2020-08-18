<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
	
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
<style type="text/css">
	table.gridtable {
		font-family: verdana,arial,sans-serif;
		border-width: 1px;
		border-color: #DDDDDD;
		border-collapse: collapse;
		color:#333333;
		font-size: 12px;
		width: 700px;
	}
	table.gridtable td {
		border-width: 1px;
		padding: 7px;
		border-style: solid;
		border-color: #DDDDDD;
		background-color: #ffffff;
		text-align: center;
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

	function chaneGoods(){
		var temp = $("#whichProduct").val();
		if(temp==1){
			$("#artnos").hide();
			$("#goodIds").show();
		}
		if(temp==2){
			$("#artnos").show();
			$("#goodIds").hide();
		}

	}

  	//修改
 	function updateupdate(id) {
	 $.ligerDialog.open({
			height: 400,
			width: 500,
			title: "修改",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/toEditDate.shtml?id="+id+"&setType=1",
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
	});
 }

    //批量通过
    function batchPass(ids){
        $("#ids").val(ids);
        $("#stock").val("");
		$("#artificialAuditStatus_1").attr('checked','checked');
		artificialAuditStatusOnclick(1);
        $("#artificialAuditStatus_2").attr('disabled','disabled');
        $("#checkAuditStatusdiv").attr('style','display:none');
        $("#showAuditStatusSpan").removeAttr('style');
        $.ligerDialog.open({
            target: $("#auditProductModel"),
            title: '批量通过',
            width: 730,
            height: 200,
            isResize: true,
            modal: true
        });
    }

	//批量驳回
	function batchRejection(ids){
		$.ligerDialog.confirm("是否要驳回？", function (yes){
			if (yes) {
				$.ajax({
					type: 'post',
					url: "${pageContext.request.contextPath}/resourceLocationManagement/batchAuditIntegralProduct.shtml",
					data: {ids : ids, artificialAuditStatus : 2, stock : ""},
					dataType: 'json',
					success: function(data) {
						if(data.statusCode != null && data.statusCode == "0000") {
							$("#searchbtn").click();
						}else {
							commUtil.alertError(data.message);
						}
					},
					error: function(e) {
						commUtil.alertError("系统异常请稍后再试");
					}
				});
			}
		});
	}

	//审核商品
	function auditProduct(ids, auditStatus, stock) {
		$("#ids").val(ids);
		$("#stock").val(stock);
		if(auditStatus == 2){
			$("#artificialAuditStatus_1").removeAttr('checked');
			$("#artificialAuditStatus_2").attr('checked', 'checked');
			artificialAuditStatusOnclick(2);
		}else {
			$("#artificialAuditStatus_1").attr('checked', 'checked');
			$("#artificialAuditStatus_2").removeAttr('checked');
			artificialAuditStatusOnclick(1);
		}
		$("#artificialAuditStatus_1").removeAttr('disabled');
		$("#artificialAuditStatus_2").removeAttr('disabled');
		$("#showAuditStatusSpan").attr('style','display:none');
		$("#checkAuditStatusdiv").removeAttr('style');
		$.ligerDialog.open({
			target: $("#auditProductModel"),
			title: '审核',
			width: 730,
			height: 200,
			isResize: true,
			modal: true
		});
	}
	function batchAuditIntegralProduct(){
		var artificialAuditStatus=$("input[name='artificialAuditStatus']:checked").val();
		var stock = $("#stock").val();
		if(artificialAuditStatus == 1){
			if(!stock){
				commUtil.alertError("请填写参与抽奖库存!");
				return;
			}
			if(!/^[1-9]\d*$/.test(stock)){
				commUtil.alertError("库存输入异常!");
				return;
			}
		}
		$.ajax({
			type: 'post',
			url: "${pageContext.request.contextPath}/resourceLocationManagement/batchAuditIntegralProduct.shtml",
			data: {ids : $("#ids").val(), artificialAuditStatus : artificialAuditStatus, stock : stock},
			dataType: 'json',
			success: function(data) {
				if(data.statusCode != null && data.statusCode == "0000") {
					$("#searchbtn").click();
					$.ligerDialog.hide();
				}else {
					$("#searchbtn").click();
					$.ligerDialog.hide();
					commUtil.alertError(data.message);
				}
			},
			error: function(e) {
				commUtil.alertError("系统异常请稍后再试");
			}
		});
	}
	function artificialAuditStatusOnclick(status) {
		if(status == 1){
			$("#stockTr").attr("style","");
		}else if(status == 2){
			$("#stockTr").attr("style","display:none;");
		}
	}
	
 var listConfig={
      url:"/resourceLocationManagement/commodityAuditData.shtml?pagetype=${pagetype}",
      btnItems:[{text: '批量通过', icon: 'add', click: function(yes) {
			  var data = listModel.gridManager.getSelectedRows();
              if (data.length == 0){
            	  $.ligerDialog.alert('请选择行');
              }else{
                 var str = "";
                 var params={};
                 
                  $(data).each(function ()
                  {    
                	  if(str==''){
                		  str = this.id ;
                	  }else{
                		  str += ","+ this.id ;
                	  }
                  });
                  
                  batchPass(str);
           /*    $.ligerDialog.confirm('确定通过?', function (yes)
                  {
                	  if(yes==true){
                      batchPass(str);
                	  }
                  });  */ 
              }
              return;
		  }},
		  {text: '批量驳回', icon: 'add', click: function(yes) {
			  var data = listModel.gridManager.getSelectedRows();
              if (data.length == 0){
            	  $.ligerDialog.alert('请选择行');
              }else{
                 var str = "";
                 var params={};
                 
                  $(data).each(function ()
                  {    
                	  if(str==''){
                		  str = this.id ;
                	  }else{
                		  str += ","+ this.id ;
                	  }
                  });
                  
                  batchRejection(str);
        /*       $.ligerDialog.confirm('确定驳回?', function (yes)
                  {
                	  if(yes==true){
                      batchRejection(str);
                	  }
                  });  */ 
              }
              return;
		  }},
	  ],
		listGrid:{ columns: [
						{display:'商品', name:'seqNo', align:'center', isSort:false, width:330, render:function(rowdata, rowindex) {
							var html=[];
							 var h = "";
							 if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
			                       h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='"+rowdata.pic+"' width='100' height='100' onclick='viewerPic("+rowdata.linkId+")'>";
			                      }else{
			                       h += "<div class='no-check' style='display:  inline-flex; margin:  20px;'><img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='100' height='100' onclick='viewerPic("+rowdata.linkId+")'>";
			                      }
							html.push(h);
							html.push("<div class='width-226'><p class='ellipsis'>"+rowdata.productName+"</p><p>商品ID:"+rowdata.productCode+"</p><p>"+rowdata.artbrandGroupEasy+"&nbsp|&nbsp;"+rowdata.producttype1Name+"&nbsp;&nbsp;</p></div>")
							html.push("<div>")
							
							return html.join("");
	                    }},
						{ display: '活动价', width: 180, render: function (rowdata, rowindex) {
							var html = [];
							//活动价
							html.push(rowdata.salePriceMin);
							if(rowdata.salePriceMin!=rowdata.salePriceMax){
								html.push("-");
								html.push(rowdata.salePriceMax);
							};
							return html.join("");
						}},
						{ display: '店铺名称',  name: 'pShopName', width: 180},
			            { display: '吊牌价', width: 180, render: function (rowdata, rowindex) {
			                var html = [];
							//吊牌价
							html.push(rowdata.tagPriceMin);
							if(rowdata.tagPriceMin!=rowdata.tagPriceMax){
								html.push("-");
								html.push(rowdata.tagPriceMax);
							};
						    return html.join("");
		                }},
		                { display: 'SVIP折扣',name: '', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							if (rowdata.svipDiscount!=null) {
								html.push(rowdata.svipDiscount);
							}else{
								html.push("-");
							}
						    return html.join("");
					 	}},
	                	{ display: '最新库存数', name: 'stockSum', width: 80, },
						{ display: '操作', width: 150, render: function(rowdata, rowindex) {
						  	var html = [];
							html.push("<a href=\"javascript:auditProduct(" + rowdata.id + ","+ rowdata.auditStatus +","+ rowdata.stock +");\">审核</a>");
							return html.join("");
						}},
			         { display: '审核状态', name: "auditStatus", width: 100, align: "center", render: function(rowdata, rowindex) {
			        		var name = "无"

			        	 	var auditDate;
	            	 	    if (rowdata.createDate!=null){
	            	 	    	auditDate=new Date(rowdata.auditDate); 
	            	 	    }
							
							if(rowdata.auditStatus=='0'){
								return "待审核"
							}
							if(rowdata.auditStatus=='1'){
								if(rowdata.auditName!=null&&rowdata.auditName!=""){
									return "<span >审核通过</span><br><span>"+rowdata.auditName+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
								}else{
									return "<span >审核通过</span><br><span>"+name+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
								}

							}
							if(rowdata.auditStatus=='2'){
								if(rowdata.auditName!=null&&rowdata.auditName!=""){
									return "<span >驳回</span><br><span>"+rowdata.auditName+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
								}else{
									return "<span >驳回</span><br><span>"+name+"</span><br><span>"+auditDate.format("yyyy-MM-dd")+"</span>";
								}
							}
						 }},
					 { display: '上线日期', name: "upDate", width: 160, align: "center", render: function(rowdata, rowindex) {
							var html = []; 
						 	var upDate;
	            	 	    if (rowdata.upDate!=null){
	            	 	    	upDate=new Date(rowdata.upDate);
	            	 	    }
							html.push("<span>"+upDate.format("yyyy-MM-dd hh:mm")+"</span><br><a href=\"javascript:updateupdate(" + rowdata.id + ");\">修改</a>");
							return html.join("");
						 }},
					 { display: '报名日期', name: "createDate", width: 100, align: "center", render: function(rowdata, rowindex) {
						 var createDate;
	            	 	    if (rowdata.createDate!=null){
	            	 	    	createDate=new Date(rowdata.createDate);
		            	        return createDate.format("yyyy-MM-dd");
	            	 	    }
						 }},
		          ],
                 showCheckbox : true,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
	<input type="hidden" id="pagetype" name="pagetype" value="${pagetype}">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">
						<select id="whichProduct" name="whichProduct" class="querysel" onchange="chaneGoods()">
							<option value="1">商品ID</option>
							<option value="2">商品货号</option>
						</select>
					</div>

					<div class="search-td-condition">
						<textarea class="goodIds" id="goodIds"  name="goodIds" rows="1" cols="4" style="resize:none;" placeholder="多个商品ID查询请换行" ></textarea>
						<textarea class="goodIds" id="artnos"  name="artnos" rows="1" cols="4" style="resize:none;display:none;" placeholder="多个商品货号查询请换行" ></textarea>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">品牌：</div>
					<div class="search-td-condition">
						<input name="productBrandName"
							<c:if test="${not empty productBrandName }">readonly</c:if>
							value="${productBrandName }">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商品名称：</div>
					<div class="search-td-condition">
						<input name="name">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label" >类目：</div>
					<div class="search-td-condition" >
						<select id="productTypeId" name="productTypeId">
							<option value="">请选择</option>
							<c:forEach var="productType" items="${productTypes}">
								<option value="${productType.id}">${productType.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			
			<div class="search-tr">

				<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">上线日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="sign_begin" name="sign_begin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#sign_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >~</div>
					<div class="l-panel-search-item">
						<input type="text" id="sign_end" name="sign_end" value=""  />
						<script type="text/javascript">
							$(function() {
								$("#sign_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>

				<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">报名日期：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="create_begin" name="create_begin" value="" />
						<script type="text/javascript">
							$(function() {
								$("#create_begin").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
					<div class="l-panel-search-item" style="margin-left: 17px;margin-right: 15px;" >~</div>
					<div class="l-panel-search-item">
						<input type="text" id="create_end" name="create_end"  value=""  />
						<script type="text/javascript">
							$(function() {
								$("#create_end").ligerDateEditor({
									showTime : false,
									labelWidth : 150,
									width : 150,
									labelAlign : 'left'
								});
							});
						</script>
					</div>
				</div>
			</div>

			<div class="search-tr">

				<div class="search-td" id="period" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">审核状态：</div>
						<div class="l-panel-search-item">
						<select id="auditStatus" name="auditStatus" class="querysel" style="width:135px;">
							<option value="" selected>请选择</option>
							<option value="0">待审核</option>
							<option value="1">通过</option>
							<option value="2">驳回</option>
						</select>
					</div>
				</div>

				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>
		</div>
	</form>

	<div id="auditProductModel" style="text-align:center;display: none">
		<input type="hidden" id="ids" name="ids" value="">
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">审核结果<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div id="checkAuditStatusdiv">
						<input type="radio" name="artificialAuditStatus" id="artificialAuditStatus_1" value="1" onclick="artificialAuditStatusOnclick(1);" checked="checked"/>&nbsp;审核通过&nbsp;
						<input type="radio" name="artificialAuditStatus" id="artificialAuditStatus_2" value="2" onclick="artificialAuditStatusOnclick(2);" />&nbsp;审核驳回
					</div>
					<span id="showAuditStatusSpan">审核通过</span>
				</td>
			</tr>
			<tr id="stockTr">
				<td colspan="1" class="title">参与抽奖库存<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="text" style="width: 90%;height: 25px" id="stock" name="stock" value=""/>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">操作<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="提交" onclick="batchAuditIntegralProduct()">
				</td>
			</tr>
		</table>
	</div>

	   <div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;"></ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
