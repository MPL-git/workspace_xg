<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/common/common-tag.jsp" %>

<html>
<head>
    <link href="${pageContext.request.contextPath}/css/search_form.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/utils/json2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/toolBar.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/dateUtil.js"></script>

    <script type="text/javascript">
        $(function () {
            // 禁止分页
            $("#maingrid").ligerGrid({
                usePager: false
            });
            
            // 默认时间
            //getTime();
            
            $("#search").bind('click',function(){
            	var begin = $("#begin_month").val();
    			var end = $("#end_month").val();
				if (!begin || !end) {
					commUtil.alertError("搜索时间不能为空"); 
    				return;
    			}
    			
    			var beginArr = begin.split("-");
    			var endArr = end.split("-");
    			var beginYear = parseInt(beginArr[0]);
    			var beginMonth = parseInt(beginArr[1]);
    			var endYear = parseInt(endArr[0]);
    			var endMonth = parseInt(endArr[1]);
    			
    			if (beginYear > endYear) {
    				commUtil.alertError("开始时间不能大于结束时间"); 
    				return;
    			}
    			
    			// 同年 比较月份 如果超过3个月则不予请求
    			if (beginYear === endYear && (endMonth - beginMonth) > 2) {
    				commUtil.alertError("搜索时间跨度不能超过3个月"); 
    				return;
    			}
    			
    			// 不同年 比较月份 如果超过3个月则不予请求
    			if (beginYear < endYear) {
    				var result = (endYear - beginYear) * 12 + (endMonth - beginMonth);
    				if (result > 2) {
    					commUtil.alertError("搜索时间跨度不能超过3个月"); 
	    				return;
    				}
    				
    			}
       			$("#searchbtn").click();
        	});
            
        });
        
        function getTime() {
        	var date = new Date;
			var year = date.getFullYear(); 
			var month = date.getMonth() + 1;
			month = (month < 10 ? "0" + month : month);
			var end = (year + "-" + month);
			$("#end_month").val(end);
			var beginMonth = 1;
			var beginYear = 2019;
			if (parseInt(month) > 2) {
				beginMonth = month - 2;
			} else if (parseInt(month) === 2) {
				beginMonth = 12;
				beginYear = year - 1;
			} else if (parseInt(month) === 1) {
				beginMonth = 11;
				beginYear = year - 1;
			}
			beginMonth = (beginMonth < 10 ? "0" + beginMonth : beginMonth);
			var end = (beginYear + "-" + beginMonth);
			$("#begin_month").val(end);
        }

        var listConfig = {
            url: "/operateData/monthReport/data.shtml",
            listGrid: {
                columns: [
                    {display: '月份', name: 'date', width: 100},
                    {display: '栏目', name: 'programa', width: 120},
                    <c:forEach items="${productTypeList}" var="productType">
                    {
                        display: '${productType.name}',
                        name: 'column_${productType.id}',
                        width: 100,
                        render: function (rowdata, rowindex) {
                            return !rowdata.column_${productType.id} ? "0" : rowdata.column_${productType.id};
                        }
                    },
                    </c:forEach>
                    {display: '全部栏目', name: 'totalPrograma', width: 100}
                ],
                showCheckbox: false,  //不设置默认为 true
                showRownumber: true //不设置默认为 true
            },
            container: {
                toolBarName: "toptoolbar",
                searchBtnName: "searchbtn",
                fromName: "dataForm",
                listGridName: "maingrid"
            }
        };

    </script>
</head>
<body style="padding: 0px; overflow: hidden;">
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toptoolbar"></div>
<form id="dataForm" runat="server">
    <div class="search-pannel">
        <div class="search-tr">
            <div class="search-td">
                <div class="search-td-label" style="float:left;">付款月份：</div>
                <div class="l-panel-search-item">
                    <input type="text" id="begin_month" name="begin_month" value="${startTime}"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#begin_month").ligerDateEditor({
                                showTime: false,
                                labelWidth: 150,
                                labelAlign: 'left',
                                format: "yyyy-MM"
                            });
                        });
                    </script>
                </div>
                <div class="l-panel-search-item">&nbsp;&nbsp;至：</div>
            </div>

            <div class="search-td">
                <div class="l-panel-search-item">
                    <input type="text" id="end_month" name="end_month" value="${endTime}"/>
                    <script type="text/javascript">
                        $(function () {
                            $("#end_month").ligerDateEditor({
                                showTime: false,
                                labelWidth: 150,
                                labelAlign: 'left',
                                format: "yyyy-MM"
                            });
                        });
                    </script>
                </div>
            </div>
            <div class="search-td-search-btn">
                <div style="padding-left: 10px;">
					<input type="button" style="width: 50px;height: 30px;cursor: pointer;" value="查看" id="search">
				</div>
				<div id="searchbtn" style="display: none;">查看</div>
            </div>
        </div>
    </div>

    <div id="maingrid" style="margin: 0; padding: 0"></div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/listModel/listModel.js"></script>
</body>