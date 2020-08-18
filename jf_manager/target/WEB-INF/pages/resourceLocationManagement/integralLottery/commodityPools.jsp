<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script	src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>

<link href="${pageContext.request.contextPath}/css/form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>	
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
<html>
<head>
<script type="text/javascript">
	var viewerPictures;
	var seqNoFlag = false;
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

	//积分商品回收和移除
	function updateStatusProduct(id,status) {
		var title;
		if(status == 1){
			title = "移除";
		}else{
			title = "恢复";
		}
		$.ligerDialog.confirm("是否要" + title + "？", function (yes) {
			if (yes) {
				$.ajax({
					type: 'post',
					url: '${pageContext.request.contextPath}/resourceLocationManagement/updateStatusIntegralProduct.shtml',
					data: {id: id, status: status},
					dataType: 'json',
					async: false,
					success: function (data) {
						if(data.statusCode != null && data.statusCode == "0000") {
							$("#searchbtn").click();
							$.ligerDialog.hide();
						}else {
							commUtil.alertError(data.message);
						}
					},
					error: function (e) {
						commUtil.alertError("系统异常请稍后再试");
					}
				});
			}
		});
	}

  	//中奖记录
 	function memberLotteryView(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: 1200,
			title: "抽中商品明细",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/resourceLocationManagement/memberLotteryView.shtml?type=3&integralProduct="+id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }

  	//修改积分
	function updateSourceNicheIntegralCount() {
		let finalPrice = $("#finalPrice").text();
		if(!finalPrice){
			commUtil.alertError("请填写积分数量!");
			return false;
		}
		if(!/^[1-9]\d*$/.test(finalPrice)){
			commUtil.alertError("填写格式错误!");
			return false;
		}
		if(Number($("#adjustPrice").val().substring(1)) > 50000){
			commUtil.alertError("最大调整区间为50000!");
			return false;
		}
		$.ajax({
			type: 'post',
			url: '${pageContext.request.contextPath}/resourceLocationManagement/updateSourceNicheIntegralCount.shtml',
			data: {id: $("#id").val(), integralCount: finalPrice},
			dataType: 'json',
			async: false,
			success: function (data) {
				if(data.statusCode != null && data.statusCode == "0000") {
					$("#searchbtn").click();
					$.ligerDialog.hide();
				}else {
					commUtil.alertError(data.message);
					$.ligerDialog.hide();
				}
			},
			error: function (e) {
				commUtil.alertError("系统异常请稍后再试");
				$.ligerDialog.hide();
			}
		});
	}
 	function updateIntegralCount(id,integralCount) {
		$("#id").val(id);
		$("#originalPrice").text(integralCount);
		$("#adjustPrice").val("");
		$("#finalPrice").text("");
		$.ligerDialog.open({
			target: $("#updateIntegralCountModel"),
			title: '修改',
			width: 750,
			height: 200,
			isResize: true,
			modal: true
		});
	 }
 	function getFinalPrice() {
		var adjustPrice = $("#adjustPrice").val();
		if(!adjustPrice){
			commUtil.alertError("请填写积分数量!");
			return false;
		}
		if(adjustPrice.charAt(0) != '+' && adjustPrice.charAt(0) != '-'){
			commUtil.alertError("填写格式错误!");
			return false;
		}
		if(!/^[1-9]\d*$/.test(adjustPrice.substring(1))){
			commUtil.alertError("填写格式错误!");
			return false;
		}
		if(Number(adjustPrice.substring(1)) > 50000){
			commUtil.alertError("最大调整区间为50000!");
			return false;
		}
		var originalPrice = $("#originalPrice").text();
		if(adjustPrice.charAt(0) == '+'){
			$("#finalPrice").text(Number(originalPrice) + Number(adjustPrice.substring(1)));
		}else if(adjustPrice.charAt(0) == '-'){
			$("#finalPrice").text(Number(originalPrice) + Number(adjustPrice));
		}

	 }

	 //修改排序
	function updateArtNo(id,seqNoOld) {
		$("#seqNo" + id).parent().find("span").remove();
		var seqNoNew = $("#seqNo" + id).val().trim();
		if(Number(seqNoNew) < 0){
			commUtil.alertError("请输入正整数");
			return;
		}
		if(Number(seqNoNew) == Number(seqNoOld)){
			return;
		}
		var flag = seqNoNew.match(/^[1-9]\d*$/);
		if(seqNoNew && flag && seqNoFlag) {
			seqNoFlag = false;
			$.ajax({
				type : 'POST',
				url : "${pageContext.request.contextPath}/resourceLocationManagement/updateIntegralLotterySeqNo.shtml",
				data : {id : id, seqNoOld : seqNoOld, seqNoNew : seqNoNew},
				dataType : 'json',
				async: false,
				success : function(data){
					if(data != null && data.statusCode == "0000")
						$("#searchbtn").click();
					else{
						commUtil.alertError(data.message);
					}
				},
				error : function(e) {
					commUtil.alertError("系统异常请稍后再试");
				}
			});
		}else{
			$("#seqNo" + id).val(seqNoOld);
			$("#seqNo" + id).parent().append("<span style='color:red;'>请输入正整数</span>");
		}
	}



    //格式化排序
    function formattingSort() {
        $.ajax({
            type : 'POST',
            url : "${pageContext.request.contextPath}/resourceLocationManagement/integralProductFormattingSeqNo.shtml",
            data : {},
            dataType : 'json',
            async: false,
            success : function(data){
                if(data != null && data.statusCode == "0000")
                    $("#searchbtn").click();
                else{
                    commUtil.alertError(data.message);
                }
            },
            error : function(e) {
                commUtil.alertError("系统异常请稍后再试");
            }
        });
    }

 var listConfig={
      url:"/resourceLocationManagement/integralLotteryCommodityPoolsList.shtml",
      btnItems:[{text: '格式化排序', icon: 'modify', click: function(yes) {
			  $.ligerDialog.confirm('确定格式化排序?', function (yes) {
				  if (yes == true) {
					  formattingSort();
				  }
			  });
          }},
      ],
		listGrid:{ columns: [
						{ display: '排序', name: 'seqNo', align:'center', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							var seqNo = rowdata.seqNo==null?'':rowdata.seqNo;
							if($("#recycleStatus").val() == 0){
								html.push("<input type='text' style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id +","+seqNo+")' value='" + seqNo + "' >");
							}else{
								html.push("<input type='text' disabled style='width:70px;margin-top:5px;' id='seqNo" + rowdata.id + "' name='seqNo' seqNo='"+seqNo+"' onChange='updateArtNo(" + rowdata.id +","+seqNo+")' value='" + seqNo + "' >");
							}
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
						{ display: '积分数量',  name: 'integralCount', width: 180, render:function(rowdata, rowindex) {
							var html=[];
							html.push(rowdata.integralCount);
							html.push("<br><a href=\"javascript:updateIntegralCount(" + rowdata.id + "," + rowdata.integralCount + ");\">修改</a>");
							return html.join("");
						}},
						{ display:'商品', name:'seqNo', align:'center', isSort:false, width:330, render:function(rowdata, rowindex) {
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
	                	{ display: '参与抽奖库存', name: 'stock', width: 80, render: function(rowdata, rowindex) {
								let lotteryCount = 0;
								let stock = 0;
								if(rowdata.lotteryCount != null){
									lotteryCount = rowdata.lotteryCount;
								}
								if(rowdata.stock != null){
									stock = rowdata.stock;
								}
								return lotteryCount + stock;
							}},
						{ display: '抽中数量', width: 150, render: function(rowdata, rowindex) {
						  	var html = [];
						  	if(rowdata.lotteryCount != null){
								html.push(rowdata.lotteryCount + " <br>");
								html.push("<a href=\"javascript:memberLotteryView(" + rowdata.linkId + ");\">查看</a>");
							}else {
								html.push("0<br><a href=\"javascript:memberLotteryView(" + rowdata.linkId + ");\">查看</a>");
							}
							return html.join("");
						}},
						{ display: '操作', width: 150, render: function(rowdata, rowindex) {
						  	var html = [];
						  	if(rowdata.status == 0){
								html.push("<a href=\"javascript:updateStatusProduct(" + rowdata.id +",1);\">移除</a>");
							}else if(rowdata.status == 1){
								html.push("<a href=\"javascript:updateStatusProduct(" + rowdata.id +",0);\">恢复</a>");
							}
							return html.join("");
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
		          ],
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true, //不设置默认为 true,
				 onAfterShowData: function() {
					$(".l-grid-row-cell-inner").css("height", "auto");
					var i = 0;
					$("tr",".l-grid2","#"+listModel.container.listGridName+"").each(function() {
						$($("tr",".l-grid1","#"+listModel.container.listGridName+"")[i]).css("height",$(this).height());
						i++;
					});
					seqNoFlag = true;
				 }
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
					<div class="search-td-label">店铺名：</div>
					<div class="search-td-condition">
						<input name="mchtName" value="${mchtName }">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtCode" value="${mchtCode }">
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
			</div>
			
			<div class="search-tr">
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
				<div class="search-td">
					<div class="search-td-label" >回收站：</div>
					<div class="search-td-condition" >
						<select id="recycleStatus" name="recycleStatus">
							<option value="0" selected="selected">未回收</option>
							<option value="1">已回收</option>
						</select>
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">抽中数量：</div>
					<div class="search-td-condition" style="display: inline-flex;">
						<div style="float: left;"><input type="text" id="lotteryCountMin" name="lotteryCountMin"></div>
						<div style="width: 25px;">--</div>
						<div style="float: left;"><input type="text" id="lotteryCountMax" name="lotteryCountMax"></div>
					</div>
				</div>

				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>

		</div>
	</form>

	<div id="updateIntegralCountModel" style="text-align:center;display: none">
		<input type="hidden" id="id" name="id" value="">
		<table class="gridtable">
			<tr>
				<td colspan="1" class="title">积分数量调整<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<div>原价: <span id="originalPrice"></span></div><br>
					<input type="text" name="adjustPrice" id="adjustPrice" value="" onblur="getFinalPrice()"/>格式:+1000/-2000<br>
					<div>调整后: <span id="finalPrice"></span></div>
				</td>
			</tr>
			<tr>
				<td colspan="1" class="title">操作<span class="required">*</span></td>
				<td colspan="5" align="left" class="l-table-edit-td">
					<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="提交" onclick="updateSourceNicheIntegralCount()">
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
