<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<%-- Liger  --%>
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${pageContext.request.contextPath}/liger/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>    
<script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script> 
 
<%-- 自定义JS --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
<html>
 <script type="text/javascript">
 var listConfig={
      url:"/member/group/dataList.shtml",
      
      listGrid:{ columns: [{ display: '会员组ID', name: 'id' },
                { display: '会员组名称', name: 'name' },
                { display: '会员组级别', name: 'level' },
                { display: '成长值范围', name: 'bgn_g_value', render: function (rowdata, rowindex){
                	return rowdata.bgnGValue + "-" + rowdata.endGValue;
                }},
		        { display: '会员等级头像', render: function (rowdata, rowindex){
                    var h = "";
                    h += "<img src='${pageContext.request.contextPath}/file_servelt/"+rowdata.pic+"' width='50' height='50' onclick='pic_show(this.src)'>";
                    return h;
                }
                }],   
                 showCheckbox : false,  //不设置默认为 true
                 showRownumber:true //不设置默认为 true
      } , 							
     container:{
        toolBarName:"toptoolbar",
        searchBtnName:"searchbtn",
        fromName:"dataForm",
        listGridName:"maingrid"
      }        
       
  }   
 </script> 
  
<body style="padding: 0px; overflow: hidden;">
	<div style="height: 100%; width: 100%; overflow: scroll; overflow-x: scroll; padding: 4px;position: absolute;">
			<div class="l-loading" style="display: block" id="pageloading"></div>
	<form id="dataForm" runat="server">
			<div id="topmenu"></div>
			<div id="toptoolbar"></div>
			<div class="l-panel-search"  style="display:none;">
				 
				<div class="l-panel-search-item">
					<input type="hidden" id="INFO_TYPE" name="INFO_TYPE" value="${INFO_TYPE}"/> 
				</div>
				<div class="l-panel-search-item">
					<div id="searchbtn" style="width: 80px;" >
					
					<label type="hidden" > 搜索</label>
					</div>
				</div>
			</div>
			<div id="maingrid" style="margin: 0; padding: 0"></div>
		</form>
		
<div style="display: none;">
		</div>
		</div>
				<div id="pic_Div" style="z-index: 10000;display: none;width: 100%;height: 100%;position: absolute; !important;">
		
		<table
			style="background-color: transparent; z-index: 2001; position: absolute; width: 100%; height: 100% !important">
			<tr>
				<td valign="middle" align="center">
				<div onclick="pic_hide()" title="关闭图片">
						<img style="width: 100%;height: 100%;" id="pic_img" alt="自动关闭" />
					</div>
				</td>
			</tr>
		</table>
		<div
			style="position: fixed; filter: alpha(Opacity = 50); -moz-opacity: 0.5; opacity: 0.5; z-index: 2000; background-color: #000000; height: 100%; width: 100%; text-align: center; vertical-align: middle;">
		</div>
		</div>
	</body>
<script type="text/javascript">
			function pic_show(url){
				$("#pic_img").attr("src",url);
				$("#pic_Div").show(100);
			}
			
			function pic_hide(){
				$("#pic_Div").hide(100);
			}
		</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
	
</html>
