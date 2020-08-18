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
 
 <script type="text/javascript">
	 
	 $(function() {
		$(".dateEditor").ligerDateEditor({
			showTime : true,
			format : "yyyy-MM-dd hh:mm:ss",
			labelAlign : 'left',
			width : 150
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
	 
 	 var listConfig={
	      url:"/sportGuess/getSportGuessList.shtml?guessType=1",
	      btnItems:[],
	      listGrid:{ columns: [
							{display:'竞猜编号',name:'guessCode', align:'center', isSort:false, width:180},
							{display:'参与时间',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								if (rowdata.createDate != null && rowdata.createDate != '') {
									var createDate = new Date(rowdata.createDate);
									return createDate.format("yyyy-MM-dd hh:mm:ss");
								} else {
									return "";
								}
							}},
							{display:'竞猜会员',name:'nickName', align:'center', isSort:false, width:180},
							{display:'竞猜积分',name:'integral', align:'center', isSort:false, width:120},
							{display:'竞猜赛事',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.sportHomeTeamName != '' && rowdata.sportAwayTeamName != '') {
									html.push(rowdata.sportHomeTeamName);
									html.push(" VS ");
									html.push(rowdata.sportAwayTeamName);
								}
								return html.join("");
			                }},
							{display:'竞猜内容',name:'', align:'center', isSort:false, width:180, render:function(rowdata, rowindex) {
								var html = [];
								if(rowdata.guessWinTeam != null) {
									html.push(rowdata.sportTeamName);
									if(rowdata.guessWinTeam == rowdata.sportHomeTeam) {
										html.push("（"+rowdata.rate+"）");
									}else if(rowdata.guessWinTeam == rowdata.sportAwayTeam) {
										html.push("（"+rowdata.rate+"）");
									}
								}else {
									html.push("平局");
									html.push("（"+rowdata.rate+"）");
								}
								return html.join("");
			                }},
							{display:'竞猜比例',name:'rate', align:'center', isSort:false, width:100},
							{display:'竞猜结果',name:'', align:'center', isSort:false, width:120, render: function(rowdata, rowindex) {
								return "<span style='color:blue;'>"+rowdata.resultDesc+"</span>";
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
		<div class="search-pannel">
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >竞猜编号：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="guessCode" name="guessCode" >
					</div>
				</div>
			 	<div class="search-td">
					<div class="search-td-label" >竞猜结果：</div>
					<div class="search-td-combobox-condition" >
						<select id="result" name="result" style="width: 135px;" >
							<option value="">请选择...</option>
							<c:forEach var="result" items="${resultList }">
								<option value="${result.statusValue }">
									${result.statusDesc }
								</option>
							</c:forEach>
						</select>
				 	 </div>
				</div>
				<div class="search-td" style="width: 40%;">
					<div class="search-td-label" style="float: left;width: 20%;">参与时间：</div>
					<div class="l-panel-search-item" >
						<input type="text" id="startBeginTime" name="startBeginTime" class="dateEditor" />
					</div>
					<div class="l-panel-search-item" style="margin-left: 15px;margin-right: 15px;" >至</div>
					<div class="l-panel-search-item">
						<input type="text" id="endBeginTime" name="endBeginTime" class="dateEditor" />
					</div>
				</div>
			</div>
			<div class="search-tr"  >
				<div class="search-td">
					<div class="search-td-label"  >队伍名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="sportTeamName" name="sportTeamName" >
					</div>
				</div>
				<div class="search-td">
					<div class="search-td-label"  >会员名称：</div>
					<div class="search-td-combobox-condition" >
						<input type="text" id="memberName" name="memberName" >
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
