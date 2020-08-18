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
	// 查看商家信息
	function viewMchtInfo(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 40,
			title: "商家基础资料",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/mchtBaseInfoEdit.shtml?mchtId=" + id,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	// FW审核对接人资质
	function LegalReview(id) {
		$.ligerDialog.open({
			height: $(window).height() - 40,
			width: $(window).width() - 850,
			title: "对接人审核",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/mcht/legalReview.shtml?id=" + id +"&staffID="+$("#staffID").val(),
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	}
	function viewProductMchtInfo(mchtId,productId,mchtName) {
		$.ligerDialog.open({
			height: 400,
			width: 500,
			title: mchtName,
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/product/viewProductMchtInfo.shtml?mchtId=" + mchtId+"&productId="+productId,
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
	
	
 var listConfig={
      url:"/mcht/mchtDockingPersonAuditList.shtml",
      listGrid:{ columns: [
						{ display: '商家序号', name: 'mchtCode', align:'center', width: 100}, 
			            { display: '入住类型', width: 80,render: function(rowdata, rowindex) {
			                    var h = "";
			                      if(rowdata.settledType == 1){
			                       h += "企业公司";
			                      }
			                      if(rowdata.settledType == 2){
			                       h += "个体工商";
			                      }
			                    return h;
						}},
			            { display: '对接人类型', width: 100,render: function(rowdata, rowindex) {
			                    var h = "";
			                      if(rowdata.contactType == 1){
			                       h += "电商负责人";
			                      }
			                      else if(rowdata.contactType == 2){
			                       h += "运营对接人";
			                      }
			                      else if(rowdata.contactType == 3){
			                       h += "订单对接人";
			                      }
			                      else if(rowdata.contactType == 4){
			                       h += "售后对接人";
			                      }
			                      else if(rowdata.contactType == 5){
			                       h += "财务对接人";
			                      }
			                      else if(rowdata.contactType == 6){
			                       h += "客服对接人";
			                      }
			                    return h;
						}},
			            { display: '公司名称', name: 'companyName', width: 100}, 
			            { display: '店铺名称', name: 'shopName', width: 200}, 
		                { display: '公司/经营信息', width: 100, render: function(rowdata, rowindex) {
							var html = [];
							html.push("");
							if(rowdata.mchtId!=0){
								html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">查看</a>");
							};
						    return html.join("");
					 	}},
		                { display: '审核状态', width: 100, render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.auditStatus==0){
		                		 if(rowdata.fwStaffId==$("#staffID").val() || rowdata.fwAssistantStaffId==$("#staffID").val()){
		                			html.push("<a href=\"javascript:LegalReview(" + rowdata.id + ");\">待审</a>");
		                		}else{
		                			html.push("<div>待审</div>");
		                		}
		                	}
		                	if(rowdata.auditStatus==1){
		                		html.push("通过");
		                	}
		                	if(rowdata.auditStatus==2){
		                		html.push("驳回");
		                	}
							return html.join("");
					 }},
					 { display: '驳回理由', name: 'rejectReasons', width: 200}, 
					 { display: '法务对接人', name: 'platformContactName', width: 200}	 
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
 

    var productTypeCombo;
    $(function(){
    	productTypeCombo = $("#productTypeName").ligerComboBox({
              selectBoxWidth: 200,
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
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<input type="hidden" value="${mchtType}" name="mchtType"> 
		<input type="hidden" value="${staffID}" id="staffID"> 
		<div class="search-pannel">
			
			<div class="search-tr">
				<div class="search-td">
					<div class="search-td-label">商家序号：</div>
					<div class="search-td-condition">
						<input name="mchtId">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">名称：</div>
					<div class="search-td-condition">
						<input name="name">
					</div>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">审核状态：</div>
					<select id="suitGroup" name="auditStatus" class="auditStatus" style="width: 150px;">
							<option value="">请选择</option>
							<option value="0" selected="selected">待审</option>
							<option value="1">通过</option>
							<option value="2">驳回</option>
						</select>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">类目：</div>
					<select id="suitGroup" name="productType" class="productType" style="width: 150px;">
								<option value="">请选择</option>
							<c:forEach  var="product" items="${Rows}">
								<option value="${product.id}">${product.name}</option>
							</c:forEach>
						</select>
				</div>
				
			</div>

			<div class="search-tr">

				<div class="search-td">
					<div class="search-td-label">平台对接人：</div>
					<select id="suitGroup" name="platformContact" class="platformContact" style="width: 150px;">
							<c:if test="${isManagement == 1}">
								<option value="" selected="selected">全部商家</option>
							</c:if>
							<option value="${staffID}" >我自己的商家</option>
							<c:forEach items="${sysStaffInfoCustomList}" var="sysStaffInfoCustom">
								<option value="${sysStaffInfoCustom.staffId}">${sysStaffInfoCustom.staffName}的商家</option>
							</c:forEach>
						</select>
				</div>
				
				<div class="search-td">
					<div class="search-td-label">对接人类型：</div>
					<div class="search-td-condition">
						<select id="suitGroup" name="contactType" class="contactType" style="width: 150px;">
							<option value="">请选择</option>
							<option value="1">店铺总负责人</option>
							<option value="3">订单对接人</option>
							<option value="6">客服对接人</option>
							<option value="4">售后对接人</option>
							<option value="2">运营对接人</option>
						</select>
					</div>
				</div>

				<div class="search-td-search-btn">
					<div id="searchbtn">搜索</div>
				</div>
				
				<!-- <div class="search-td-search-btn" style="display: inline-flex; ">
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="搜索" id="searchbtn">
					</div>
					<div style="padding-left: 10px;">
						<input type="button" style="width: 60px;height: 25px;cursor: pointer;" value="导出" id="export" onclick="excel();" >
					</div>
				</div> -->
			</div>
		</div>
	</form>
	<div id="toptoolbar"></div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	<ul  class="docs-pictures clearfix td-pictures" id="viewer-pictures" style="display: none;">
	
	</ul>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
