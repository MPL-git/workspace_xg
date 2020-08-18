<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/ligerLinkageComboBox.js"></script>
 <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css" />
 <link href="${pageContext.request.contextPath}/css/viewer.min.css" rel="stylesheet" type="text/css" />
 <script src="${pageContext.request.contextPath}/js/viewer.min.js" type="text/javascript"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>
 
 <script type="text/javascript">
	 $(function() {
		$(".dateEditor").ligerDateEditor( {
			showTime : false,
			labelAlign : 'left'
		});
		
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
	 
	 //启用
	 function updateIsEffect(signInCnfId, signInCnfName) {
		 $.ligerDialog.confirm("是否确认启用<span style='color: red;'>"+signInCnfName+"</span>方案？", function(yes) {
			 if(yes) {
				 $.ajax({
					 type: 'post',
					 url: '${pageContext.request.contextPath }/signInCnf/updateIsEffect.shtml',
					 data: {signInCnfId : signInCnfId},
					 dataType: 'json',
					 success: function(data) {
						 if(data == null || data.code != 200){
						     commUtil.alertError(data.msg);
						 }else {
							 listModel.gridManager.reload();
						 }
					 },
					 error: function(e) {
						 commUtil.alertError("系统异常请稍后再试！");
					 }
				 });
			 }
		 });
	 }
	 
	 //编辑
	 function updateSignInCnf(signInCnfId) {
		 $.ligerDialog.open({
			height: 800,
			width: 1200,
			title: "修改签到方案",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/signInCnf/updateSignInCnfManager.shtml?signInCnfId="+signInCnfId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 //查看计算结果
	 function getShowResult(signInCnfId) {
		 $.ligerDialog.open({
			height: 400,
			width: 500,
			title: "计算结果",
			name: "INSERT_WINDOW",
			url: "${pageContext.request.contextPath}/signInCnf/getShowResult.shtml?signInCnfId="+signInCnfId,
			showMax: true,
			showToggle: false,
			showMin: false,
			isResize: true,
			slide: false,
			data: null
		});
	 }
	 
	 function strPush() {
		 var html = [];
		 html.push("<span style='margin-left: 50px;color: red;'><span>已提现总额：${amountSum }");
		 html.push("（直减券 ${amountSumThree }，微信红包 ${amountSumTwo }）</span>");
		 html.push("<span style='margin-left: 20px;'>总提现人数：${memberCount }</span>");
		 html.push("<span style='margin-left: 20px;'>总提现次数：${withdrawCount }</span></span>");
		 return html.join("");
	 }
	 
 	 var listConfig={
	      url:"/signInCnf/getSignInCnfList.shtml",
	      btnItems:[{ text:'新增签到方案', icon:'add', dtype:'win', click:itemclick, url:'/signInCnf/addSignInCnfManager.shtml', seqId:'', width:1200, height:800},
	                {text:strPush()}
	                ],
	      listGrid:{ columns: [
							{display:'方案名称',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								html.push("<span class='"+rowdata.id+"-name'>"+rowdata.name+"</span>");
								if(rowdata.isEffect == '1') {
									html.push("<span style='color: red;'>（已启用）</span>");
								}
							    return html.join("");
	                        }},
							{display:'最低提现金额',name:'', align:'center', isSort:false, width:100, render:function(rowdata, rowindex) {
	 		                	if(rowdata.baseAmount){
		 		                	return formatMoney(rowdata.baseAmount, 2);
	 		                	}else{
	 		                		return "0.00";
	 		                	}
			                }},
							{display:'首次签到获得金额比例（%）',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	 		                	var sqBeginRate = "0.00";
	 		                	var sqEndRate = "0.00";
								if(rowdata.sqBeginRate){
									sqBeginRate = formatMoney(rowdata.sqBeginRate, 2);
	 		                	}
								if(rowdata.sqEndRate){
									sqEndRate = formatMoney(rowdata.sqEndRate, 2);
	 		                	}
								return sqBeginRate+"-"+sqEndRate;
			                }},
							{display:'累计签到7天获得金额比例（%）',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	 		                	var ljBeginRate = "0.00";
	 		                	var ljEndRate = "0.00";
								if(rowdata.ljBeginRate){
									ljBeginRate = formatMoney(rowdata.ljBeginRate, 2);
	 		                	}
								if(rowdata.ljEndRate){
									ljEndRate = formatMoney(rowdata.ljEndRate, 2);
	 		                	}
								return ljBeginRate+"-"+ljEndRate;
			                }},
	                        {display:'每日邀请好友上限人数',name:'inviteLimit', align:'center', isSort:false, width:180},
	                        {display:'首次好友签到获得金额比例（%）',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	 		                	var syqBeginRate = "0.00";
	 		                	var syqEndRate = "0.00";
								if(rowdata.syqBeginRate){
									syqBeginRate = formatMoney(rowdata.syqBeginRate, 2);
	 		                	}
								if(rowdata.syqEndRate){
									syqEndRate = formatMoney(rowdata.syqEndRate, 2);
	 		                	}
								return syqBeginRate+"-"+syqEndRate;
			                }},
	                        {display:'邀请新用户签到获金额比例（%）',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	 		                	var xyqBeginRate = "0.00";
	 		                	var xyqEndRate = "0.00";
								if(rowdata.xyqBeginRate){
									xyqBeginRate = formatMoney(rowdata.xyqBeginRate, 2);
	 		                	}
								if(rowdata.xyqEndRate){
									xyqEndRate = formatMoney(rowdata.xyqEndRate, 2);
	 		                	}
								return xyqBeginRate+"-"+xyqEndRate;
			                }},
	                        {display:'邀请老用户签到获金额比例（%）',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
	 		                	var lyqBeginRate = "0.00";
	 		                	var lyqEndRate = "0.00";
								if(rowdata.lyqBeginRate){
									lyqBeginRate = formatMoney(rowdata.lyqBeginRate, 2);
	 		                	}
								if(rowdata.lyqEndRate){
									lyqEndRate = formatMoney(rowdata.lyqEndRate, 2);
	 		                	}
								return lyqBeginRate+"-"+lyqEndRate;
			                }},
	                        {display:'弹窗提示次数',name:'popupLimit', align:'center', isSort:false, width:100},
							{display:'操作',name:'', align:'center', isSort:false, width:160, render: function(rowdata, rowindex) {
								var html = [];
								if(rowdata.isEffect == '0') {
									html.push("<a href='javascript:;' onclick='updateIsEffect("+rowdata.id+", \""+rowdata.name+"\");'>【启用】</a>");
								}
								html.push("<a href='javascript:;' onclick='updateSignInCnf("+rowdata.id+");'>【编辑】</a></br>");
								html.push("<a href='javascript:;' onclick='getShowResult("+rowdata.id+");'>【查看计算结果】</a></br>");
							    return html.join("");
							}}
			         ], 
	                 showCheckbox :false,  //不设置默认为 true
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
	<form id="dataForm" runat="server" style="display: none;">
		<div class="search-pannel">
			<div class="search-tr"  > 
				<div class="search-td-search-btn" >
					<div id="searchbtn" >搜索</div>
				</div>
			</div>
		</div>
	</form>
	<div id="maingrid" style="margin: 0;"></div>
	
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>

</html>
