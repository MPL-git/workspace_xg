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
<script type="text/javascript">
	var viewerPictures;
    $(function(){
    	viewerPictures = new Viewer(document.getElementById('viewer-pictures'), {hide:function(){
    		$("#viewer-pictures").hide();
    	}});
    	
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
				console.log(data);
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

	//删除
	function delProduct(id) {
		$.ligerDialog.confirm('是否删除？', function(yes) {
			if(yes) {
				$.ajax({
					type : 'POST',
					url : "${pageContext.request.contextPath}/svip/batchDelProduct.shtml",
					data : {ids : id},
					dataType : 'json',
					success : function(data){
						if(data == null || data.returnCode != 0000)
							commUtil.alertError(data.returnMsg);
						else{
							$.ligerDialog.success(data.returnMsg);
							$("#searchbtn").click();
						}
					},
					error : function(e) {
						commUtil.alertError("系统异常请稍后再试");
					}
				});
			}
		});
	}

	function batchDelProduct(ids, num) {
		$.ligerDialog.confirm("选中" + num + "个商品,是否删除", function(yes) {
			if(yes){
				$.ajax({
					url: "${pageContext.request.contextPath}/svip/batchDelProduct.shtml",
					type: 'POST',
					dataType: 'json',
					data: {"ids": ids},
					success: function (data) {
						$.ligerDialog.success("成功删除"+data.idSuccess+ "件商品,失败" + data.idError + "件.");
						listModel.initdataPage();
					},
					error: function () {
						$.ligerDialog.error('操作超时，请稍后再试！');
					}
				});
			}
		})
	}

 var listConfig={
      url:"/svip/getSvipBindProductPageListData.shtml",
	  btnItems:[{ text: '新增', icon: 'add', dtype:'win',  click: itemclick, url:'/svipBindProduct/toAddProduct.shtml', seqId:"", width : 410, height:310},
		        { text: '基础设置', icon: '', dtype:'win',  click: itemclick, url:'/svipBindProduct/svipMarketingSetUpSubmit.shtml', seqId:"", width : 550, height:400},
		        {
					  text: '批量删除',
					  icon: '',
					  id: 'delete',
					  click: function () {
						  var data = listModel.gridManager.getSelectedRows();
						  if (data.length == 0) {
							  $.ligerDialog.alert('请选择行');
						  } else {
							  var str = "";
							  var num = 0;
							  $(data).each(function () {
								  if (str == '') {
									  str = this.id;
									  num++;
								  } else {
									  str += "," + this.id;
									  num++;
								  }
							  });
							  batchDelProduct(str,num);
						  }
						  return;
					  }
				}
	  ],
      listGrid:{ columns: [
			{ display: '主图', name: 'pic', width: 100,render: function(rowdata, rowindex) {
					var h = "";
					  if(rowdata.pic!=null&&(rowdata.pic.indexOf("http") >= 0)){
					   h += "<img src='"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
					  }else{
					   h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='60' height='60' onclick='viewerPic("+rowdata.id+")'>";
					  }
					return h;
			  }},
			  { display: '商品ID（长ID）', name: 'code', width: 100},
			  { display: '分类', name: 'productTypeName', width: 100 },
			  { display: '品牌', name: 'productBrandName', width: 100},
			  { display: '名称', name: 'name', width: 200},
			  { display: '货号', name: 'artNo', width: 100},
			  { display: '吊牌价', name: 'tagPrice', width: 120, render: function(rowdata, rowindex) {
					  var html = [];

					  html.push(rowdata.tagPriceMin);

					  if(rowdata.tagPriceMin!=rowdata.tagPriceMax){
						  html.push("-");
						  html.push(rowdata.tagPriceMax);
					  };
					  return html.join("");
			  }},
			  { display: '商城价',name: 'saleName', width: 120, render: function(rowdata, rowindex) {
					  var html = [];

					  html.push(rowdata.mallPriceMin);

					  if(rowdata.mallPriceMin!=rowdata.mallPriceMax){
						  html.push("-");
						  html.push(rowdata.mallPriceMax);
					  };
					  return html.join("");
			  }},
			  { display: '活动价',name: 'saleName', width: 120, render: function(rowdata, rowindex) {
					  var html = [];

					  html.push(rowdata.salePriceMin);

					  if(rowdata.salePriceMin!=rowdata.salePriceMax){
						  html.push("-");
						  html.push(rowdata.salePriceMax);
					  };
					  return html.join("");
			  }},
			  { display: 'SVIP折扣',name: '', width: 120, render: function(rowdata, rowindex) {
					  var html = [];
					  if (rowdata.svipDiscount!=null) {
						  html.push(rowdata.svipDiscount);
					  }else{
						  html.push("-");

					  }
					  return html.join("");
			  }},
			  { display: '商家', name: 'companyName', width: 150},
			  { display: "操作", name: "OPER", width: 100, align: "center",render: function(rowdata, rowindex) {
					var html = [];
					html.push("<a href=\"javascript:viewProduct(" + rowdata.productId + ");\">查看</a>&nbsp;|&nbsp;");
					html.push("<a href=\"javascript:delProduct(" + rowdata.id + ");\">删除</a>");
					return html.join("");
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
	<div id="toptoolbar"></div>
	<form id="dataForm" runat="server">
		<div id="topmenu"></div>
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商家序号</div>
					<div class="search-td-condition">
						<input name="mchtCode" type="text">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">公司名称</div>
					<div class="search-td-condition">
						<input name="companyName" type="text">
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label">商品关键字</div>
					<div class="search-td-condition">
						<input name="searchFields" type="text">
					</div>
				</div>
				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
			</div>
		</div>
	</form>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
