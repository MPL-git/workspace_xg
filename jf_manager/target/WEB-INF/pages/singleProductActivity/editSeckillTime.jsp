<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/common-tag.jsp"%>

<html>
<head>
    <title></title>
    <style type="text/css">
    	body{ font-size:12px;padding:10px;}
    	.table-1{padding: 30px 0px; border-collapse: separate;}
    </style>
    <script src="${pageContext.request.contextPath}/liger/lib/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>  
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/liger/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function (){
        	var startHoursVal = "00";
        	var startMinVal = "00";
        	var continueHoursVal = "0";
        	var continueMinVal = "0";
        	if('${seckillTime }' != '') {
        		startHoursVal = '${seckillTime.startHours }';
        		startMinVal = '${seckillTime.startMin }';
        		continueHoursVal = '${seckillTime.continueHours }';
        		continueMinVal = '${seckillTime.continueMin }';
        	}
            var startHours = $("#startHours").ligerComboBox({  
				data: dataStr(24, "00"),
            	value: startHoursVal,
            	width: 80,
                selectBoxWidth: 80,
                selectBoxHeight: 150,
				valueFieldID: 'sHours',
				emptyText: '',
				cancelable: false,
				onBeforeOpen: function(){
					$(".l-box-select").eq(1).css("display", "none");
 					$(".l-box-select").eq(2).css("display", "none");
 					$(".l-box-select").eq(3).css("display", "none");
				}
            }); 
            var startMin = $("#startMin").ligerComboBox({  
            	data: dataStr(60, "00"),
            	value: startMinVal,
            	width: 80,
                selectBoxWidth: 80,
                selectBoxHeight: 150,
				valueFieldID: 'sMin',
				emptyText: '',
				cancelable: false,
				onBeforeOpen: function(){
					$(".l-box-select").eq(0).css("display", "none");
 					$(".l-box-select").eq(2).css("display", "none");
 					$(".l-box-select").eq(3).css("display", "none");
				}
            });
	        var continueHours = $("#continueHours").ligerComboBox({  
	        	data: dataStr(24, "0"),
            	value: continueHoursVal,
            	width: 80,
                selectBoxWidth: 80,
                selectBoxHeight: 150,
				valueFieldID: 'cHours',
				emptyText: '',
				cancelable: false,
				onBeforeOpen: function(){
 					$(".l-box-select").eq(0).css("display", "none");
 					$(".l-box-select").eq(1).css("display", "none");
 					$(".l-box-select").eq(3).css("display", "none");
				}
            });
	        var continueMin = $("#continueMin").ligerComboBox({
	        	data: dataStr(60, "0"),
            	value: continueMinVal,
            	width: 80,
                selectBoxWidth: 80,
                selectBoxHeight: 150,
				valueFieldID: 'cMin',
				emptyText: '',
				cancelable: false,
				onBeforeOpen: function(){
 					$(".l-box-select").eq(0).css("display", "none");
 					$(".l-box-select").eq(1).css("display", "none");
 					$(".l-box-select").eq(2).css("display", "none");
				}
            });
			
        });
        
        function dataStr(num, flag) {
        	var data = new Array();
			if(flag == "00") {
	        	for(var i=0;i<num;i++) {
	        		if(i < 10)
	        			data[i] = { "text": "0"+i , "id": "0"+i};
	        		else 
	        			data[i] = { "text": i , "id": i};
	        	}
			}else{
				for(var i=0;i<num;i++) {
					data[i] = { "text": i , "id": i};
				}
			}
        	return data;
        }





		function save() {
			let continueHours = parseInt($("#continueHours").val());
			let continueMin = parseInt($("#continueMin").val());
			let startHours = parseInt($("#startHours").val());
			let startMin =parseInt($("#startMin").val());
			endMin = parseInt(continueMin + startMin);
			endHours = parseInt(continueHours + startHours);
			if (continueMin + startMin >= 60) {
				endMin = endMin - 60;
				endHours = endHours + 1;
			}
			console.log(endHours)
			if (endHours>24){
				$.ligerDialog.warn("结束时间不能超过<span style='color:red;'>24:00</span>！");
				return false;
			}
			if (endHours == 24 && endMin>0){
				$.ligerDialog.warn("结束时间不能超过<span style='color:red;'>24:00</span>！");
				return false;
			}

    		if($("#continueHours").val() == "0" && $("#continueMin").val() == "0") {
    			$.ligerDialog.warn("活动时长不能为<span style='color:red;'>【0】</span>！");
    			return false;
    		} else{
    			$.ajax({
        			url : "${pageContext.request.contextPath}/singleProductActivity/getSeckillTime.shtml",
        			data : {startHours : $("#startHours").val(), startMin : $("#startMin").val(), id : '${id }',seckillType:$("#seckillType").val()},
        			type : 'POST',
        			dataType : 'json',
        			success : function(data) {
        				if(data.returnCode != "200") {
							$.ligerDialog.warn(data.returnMsg);
        				}else {
        					$("#form-1").submit();
        				}
        			},
        			error : function() {
        				$.ligerDialog.error('操作超时，请稍后再试！');
        			}
        		});
    		}
    	}
    	
    </script>
</head>
<body> 
	<form method="post" id="form-1" action="${pageContext.request.contextPath}/singleProductActivity/updateOrAddSeckillTime.shtml">
		<input type="hidden" name="id" value="${id }">
		<div align="center">
			<table class="table-1">
				<tr>
					<td>秒杀类型：</td>
					<td>
						<select id="seckillType" name="seckillType" >
							<option value="1" <c:if test="${seckillTime.seckillType == '1'}">selected="selected"</c:if>>限时秒杀</option>
							<option value="2" <c:if test="${seckillTime.seckillType == '2'}">selected="selected"</c:if>>会场秒杀</option>
						</select>
					</td>
				</tr>
				<tr style="height: 40px;">
					<td>开始时间：</td>
					<td style="width: 80px;">
						<input type="text" id="startHours" name="startHours" />
					</td>
					<td align="center" style="width: 40px;">:</td>
					<td style="width: 80px;">
						<input type="text" id="startMin" name="startMin" />
					</td>
				</tr>
				<tr style="height: 40px;">
					<td>活动时长：</td>
					<td style="width: 80px;">
						<input type="text" id="continueHours" name="continueHours" />
					</td>
					<td align="center">小时</td>
					<td style="width: 80px;">
						<input type="text" id="continueMin" name="continueMin" />
					</td>
					<td align="center" style="width: 40px;">分钟</td>
				</tr>
			</table>
		</div>
        <div align="center">
			<input type="button" class="l-button l-button-submit" value="提交" onClick="save();"/>
		</div>
	</form>
</body>
</html>
