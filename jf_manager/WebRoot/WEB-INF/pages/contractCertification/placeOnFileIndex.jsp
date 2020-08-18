<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
  <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript">
 	$(function() {
 		
 		$("#closeReload .l-dialog-close").live("click", function () {
            $("#searchbtn").click();
        });
 		
 		$("#export").bind('click',function(){
 			var mchtCode = $("#mchtCode").val();
 			var companyOrShop = $("#companyOrShop").val();
 			var companyInfoArchiveStatus = $("#companyInfoArchiveStatus").val();
 		    var productTypeId = $("#productTypeId").val();
 			var archiveDealStatus = $("#archiveDealStatus").val();
 			var platContactStaffId = $("#platContactStaffId").val();
 			location.href="${pageContext.request.contextPath}/contractCertification/exports.shtml?mchtCode="+mchtCode+"&companyOrShop="+companyOrShop+"&companyInfoArchiveStatus="+companyInfoArchiveStatus+"&productTypeId="+productTypeId+"&archiveDealStatus="+archiveDealStatus+"&platContactStaffId="+platContactStaffId;
 		});
	});
    // 查看公司/经营信息
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

    // 查看财务信息
    function viewFinanceInfo(id) {
        $.ligerDialog.open({
            height: $(window).height(),
            width: $(window).width() - 200,
            title: "商家财务信息",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/mcht/mchtFinanceInfoEdit.shtml?mchtId=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }

    // 查看商家合同详情
    function viewMchtContract(mchtContractId) {
        $.ligerDialog.open({
            height: $(window).height() - 100,
            width: $(window).width() - 250,
            title: "商家合同详情",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/mchtContract/viewMchtContract.shtml?id=" + mchtContractId,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null
        });
    }

    // 公司资质归档状态查看
    function viewCompanyInfoArchiveChg(chgId) {
        $.ligerDialog.open({
            height: $(window).height() * 0.9,
            width: $(window).width() * 0.9,
            title: "公司资质归档",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/mchtContract/viewCompanyInfoArchiveChg.shtml?chgId=" + chgId,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null,
            id: "closeReload"
        });
    }
    
    //公司归档处理
     function filingProcessing(id) {
        $.ligerDialog.open({
            height: 500,
            width: 600,
            title: "处理归档",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/contractCertification/filingProcessing.shtml?id=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null,
            id: "closeReload"
        });
    }
    
    //修改备注
    function modifyArchiveDealInnerRemarks(id) {
        $.ligerDialog.open({
            height: 500,
            width: 600,
            title: "修改备注",
            name: "INSERT_WINDOW",
            url: "${pageContext.request.contextPath}/contractCertification/modifyArchiveDealInnerRemarks.shtml?id=" + id,
            showMax: true,
            showToggle: false,
            showMin: false,
            isResize: true,
            slide: false,
            data: null,
            id: "closeReload"
        });
    }
    
    //查看物流
	function viewWuliu(expressId, expressNos) {
		$.ligerDialog.open({
			height : $(window).height(),
			width : $(window).width() * 0.35,
			title : "查看物流动态",
			name : "INSERT_WINDOW",
			url : "${pageContext.request.contextPath}/resource/viewWuliu.shtml?expressId="+ expressId + "&expressNo=" + expressNos,
			showMax : true,
			showToggle : false,
			showMin : false,
			isResize : true,
			slide : false,
			data : null
		});
	}
	  
	var listConfig={
		url:"/contractCertification/placeOnFileList.shtml",
      	listGrid:{ columns: [ 
						{ display: '开店日期', name: 'cooperateBeginDate', width: 120 ,render: function(rowdata, rowindex) {
   		                	if(rowdata.cooperateBeginDate != null && rowdata.cooperateBeginDate != ""){
   	   		                   var cooperateBeginDate = new Date(rowdata.cooperateBeginDate);
   	   		          	       return cooperateBeginDate.format("yyyy-MM-dd");	
   		                	}
		                }},
		                { display: '招商对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	return rowdata.zsContactName;
		                }},
						{ display: '商家序号', name: 'mchtCode',width: 100 }, 
		                { display: '公司名称', width: 200, name:'companyName'},
		                { display: '店铺名称', width: 200, name:'shopName'},
		                {
	                        display: '公司/经营信息',
	                        name: 'OPER1',
	                        width: 100,
	                        align: 'center',
	                        render: function (rowdata, rowindex) {
	                            var html = [];
	                            html.push("<a href=\"javascript:viewMchtInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
	                            return html.join("");
	                        }
	                    },
	                    {
	                        display: '财务信息',
	                        name: 'OPER2',
	                        width: 100,
	                        align: 'center',
	                        render: function (rowdata, rowindex) {
	                            var html = [];
	                            html.push("<a href=\"javascript:viewFinanceInfo(" + rowdata.mchtId + ");\">查看</a>&nbsp;&nbsp;");
	                            return html.join("");
	                        }
	                    },
	                    {
	                        display: '合同详情',
	                        name: 'OPER4',
	                        width: 100,
	                        align: 'center',
	                        render: function (rowdata, rowindex) {
	                            var html = [];
	                            html.push("<a href=\"javascript:viewMchtContract(" + rowdata.coontractId + ");\">查看</a>&nbsp;&nbsp;");
	                            return html.join("");
	                        }
	                    },
	                    {
	                        display: '公司资质归档情况',
	                        name: 'OPER4',
	                        width: 100,
	                        align: 'center',
	                        render: function (rowdata, rowindex) {
	                            var html = [];
	                            var companyInfoArchiveStatus = rowdata.companyInfoArchiveStatus;
	                            var archiveStatusDesc;
	                            if (!companyInfoArchiveStatus || companyInfoArchiveStatus == 0) {
	                                archiveStatusDesc = "<span style='color:red;'>【未齐全】</span>";
	                            } else {
	                                archiveStatusDesc = "<span style='color:red;'>【已齐全】</span>";
	                            }
	                            html.push(archiveStatusDesc);
	                            html.push("<a href=\"javascript:viewCompanyInfoArchiveChg(" + rowdata.id + ");\">查看</a>&nbsp;&nbsp;");
	                            return html.join("");
	                        }
	                    },
	                    {
	                        display: '商家寄件状态',
	                        name: 'isMchtSend',
	                        width: 200,
	                        align: 'center',
	                        render: function (rowdata, rowindex) {
	                        	var html = [];
	                            if (!rowdata.expressId && !rowdata.expressNo) {
	                                html.push("未寄出");
	                            } else {
	                            	 html.push("已寄出");
	           					  	 html.push("<br/><a href=\"javascript:viewWuliu(" + rowdata.expressId + ", '" + rowdata.expressNo + "');\">单号:" + rowdata.expressNo + "</a>");
	                            }  
	                            return html.join("");
	                        }
	                   },
	                   {
	                        display: '公司归档处理',
	                        name: 'archiveDealStatus',
	                        width: 100,
	                        align: 'center',
	                        render: function (rowdata, rowindex) {
	                        	var html = [];
	                            if((rowdata.fwStaffId == ${sessionScope.USER_SESSION.staffID} && (rowdata.archiveDealStatus == 0 || rowdata.archiveDealStatus == null)) ||(rowdata.assistantId == ${sessionScope.USER_SESSION.staffID} && (rowdata.archiveDealStatus == 0 || rowdata.archiveDealStatus == null))){
	                            	html.push("<a href=\"javascript:filingProcessing(" + rowdata.id + ");\">未处理</a>");
	                            }else if(rowdata.archiveDealStatus == 0 || rowdata.archiveDealStatus == null){
	                            	return "未处理";
	                            }else if (rowdata.archiveDealStatus == 1 ) {
	                            	return "通过";
	                            }else if((rowdata.fwStaffId == ${sessionScope.USER_SESSION.staffID} && rowdata.archiveDealStatus == 2) || (rowdata.assistantId == ${sessionScope.USER_SESSION.staffID} && rowdata.archiveDealStatus == 2)){
	                            		 html.push("<a href=\"javascript:filingProcessing(" + rowdata.id + ");\">驳回</a>");
	                            }else{
	                            	return "驳回";
	                            }
	                            return html.join("");
	                        }                		
	                   },
	                   {
	                        display: '内部备注',
	                        name: 'archiveDealInnerRemarks',
	                        width: 100,
	                        align: 'center',
	                        render: function (rowdata, rowindex) {
	                        	var html = [];
	                        	if(rowdata.archiveDealStatus == 1 && rowdata.archiveDealInnerRemarks != null && (rowdata.fwStaffId == ${sessionScope.USER_SESSION.staffID} || rowdata.assistantId == ${sessionScope.USER_SESSION.staffID})){
		                        	html.push(rowdata.archiveDealInnerRemarks+"<a href=\"javascript:modifyArchiveDealInnerRemarks(" + rowdata.id + ");\">【修改】</a>");
		                        }else if(rowdata.archiveDealStatus == 1 && rowdata.archiveDealInnerRemarks == null && (rowdata.fwStaffId == ${sessionScope.USER_SESSION.staffID} || rowdata.assistantId == ${sessionScope.USER_SESSION.staffID})){
		                        	html.push("<a href=\"javascript:modifyArchiveDealInnerRemarks(" + rowdata.id + ");\">【修改】</a>");
		                        }else{
		                        	html.push(rowdata.archiveDealInnerRemarks);
		                        }
	                        	return html.join("");
	                        }
	                   },
	                   { display: '驳回原因', width: 100, name:'archiveDealRemarks'},
		                { display: '法务对接人', name: '', width:100 ,render: function(rowdata, rowindex) {
		                	var html = [];
		                	if(rowdata.fwContactName) {
		                		html.push(rowdata.fwContactName);
		                	}
		                	return html.join("");
		                }}
		         ],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
      container:{
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
  }
  
</script>
</head>
<body style="padding: 0px; overflow: hidden;">
	<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
		<div class="search-pannel">
			<div class="search-tr">
				<div class="search-td" style="width:240px;">
					<div class="search-td-label">商家序号</div>
					<div class="search-td-condition"> 
						<input type="text" id="mchtCode" name="mchtCode">
					</div>
				</div>
				<div class="search-td" style="width:240px;">
					<div class="search-td-label">名称</div>
					<div class="search-td-condition">
						<input type="text" id="companyOrShop" name="companyOrShop">
					</div>
				</div>
				<div class="search-td" style="width:240px;">
					<div class="search-td-label">公司资质归档状态</div>
					<div class="search-td-condition">
						<select id="companyInfoArchiveStatus" name="companyInfoArchiveStatus">
							<option value="">请选择</option>
							<option value="0">未齐全</option>
							<option value="1">已齐全</option>
						</select>
					</div>
				</div>
				<div class="search-td" style="width:240px;">
					<div class="search-td-label">类目:</div>
					<div class="search-td-condition" >
					<select id="productTypeId" name="productTypeId">
						<option value="">请选择</option>
						<c:forEach items="${sysStaffProductTypeCustomList}" var="sysStaffProductTypeCustom">
							<option value="${sysStaffProductTypeCustom.productTypeId}">${sysStaffProductTypeCustom.staffName}</option>
						</c:forEach>
					</select>
		 			</div>
		 		</div>
				<div class="search-td" style="width:240px;">
					<div class="search-td-label">归档处理状态</div>
					<div class="search-td-condition">
						<select id="archiveDealStatus" name="archiveDealStatus">
							<option value="">请选择</option>
							<option value="0" selected="selected">未处理</option>
							<option value="1">通过</option>
							<option value="2">驳回</option>
						</select>
					</div>
				</div>
		 		<div class="search-td" style="width:240px;">
						<div class="search-td-label">对接人：</div>
						<div class="search-td-condition">
							<select id="platContactStaffId" name="platContactStaffId">
								<c:if test="${isManagement == 1}">
									<option value="" selected="selected">全部商家</option>
								</c:if>
								<option value="${staffID}" selected="selected" >我自己的商家</option>
								<c:forEach items="${sysStaffInfoCustomList }" var="sysStaffInfoCustom">
									<option value="${sysStaffInfoCustom.staffId }">${sysStaffInfoCustom.staffName }</option>
								</c:forEach>
							</select>
						</div>
				</div>					
					<div style="display: inline-flex;margin-bottom:5px;">
					<div id="searchbtn" class="l-button">搜索</div>
					<c:if test="${role431 eq true}">
						<div style="display: inline-flex;width: 62px;margin-left:15px;">
						<div id="export" class="l-button">导出</div>		
						</div>	
					</c:if>		
					</div>					
			</div>
		</div>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</html>
