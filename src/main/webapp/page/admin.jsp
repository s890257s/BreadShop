<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺管理</title>
</head>
<body>
	<div style="font-size: 100px;text-align: center;margin-top: 50px">建置中</div>

	<div style="text-align: center;margin-top: 50px">
		<button id="back" style="font-size: 200px">回上一頁</button>
	</div>
</body>
<script type="text/javascript">
	document.getElementById("back").addEventListener("click", function() {
		window.history.back();
	})
</script>
</html>