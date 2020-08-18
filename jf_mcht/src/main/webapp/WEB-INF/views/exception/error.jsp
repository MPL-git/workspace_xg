<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务异常</title>
    <style>
    	.error h2,
    	.error p {
    		text-align: center;
    		color: #333;
    	}
    	.error img {
    		display: block;
    		margin: 0 auto;
    	}
    	.error h2 {
    		width: 160px;
    		margin: 0 auto;
    		padding: 16px 0 12px;
    		border-bottom: 1px solid #ddd;
    		font-size: 20px;
    		margin: 0;
    	}
    	.error p {
    		margin: 12px 0 0;
    	}
    </style>
</head>
<body>
    <div class="x_panel container-fluid error">
	    <div class="row content-title">
	        <div class="t-title">
	            业务异常
	        </div>
	    </div>

	    <div style="padding: 94px 0 0;">
	    	<img src="${pageContext.request.contextPath}/static/images/error.png">

	    	<h2>业务错误</h2>
			<p>错误代码：${code}</p>
			<p>错误信息：</p>
			<p>${message}</p>
	    </div>
	</div>
</body>
</html>