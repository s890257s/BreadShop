<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>線上麵包商店</title>

<!-- web icon -->
<link rel="icon" href="/BreadShop/assets/favicon.ico">

<!-- bootstrap 5.1.3 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<!-- bootstrap 5.1.3 JS -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<!-- jQuery 3.6.0 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>


<!-- font awesome 5.10.0 -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />


<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

#checkEye {
	position: absolute;
	top: 50%;
	right: 10px;
	transform: translateY(-50%);
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body class="vh-100">
	<div class="container text-center ">

		<main class="form-signin m-5">
			<form action="/BreadShop/UserLogin.do" method="post">
				<h1 class="h3 mb-3 fw-normal">會員登入</h1>

				<div class="form-floating">
					<input type="text" class="form-control" id="floatingInput"
						placeholder="aaa" name="account" required> <label
						for="floatingInput">帳號</label>
				</div>
				<div class="form-floating">
					<input type="text" class="form-control" id="floatingPassword"
						placeholder="Password" name="password" required> <label
						for="floatingPassword">密碼</label> <i id="checkEye"
						class="fas fa-eye"></i>

				</div>

				<div class="checkbox my-3">
					<label> <input type="checkbox" name="remeberMe" > 
						記住我
					</label>
				</div>
				<div class=" mb-2">
					<button class="w-25 btn btn-lg btn-primary">登入</button>
				</div>
				<div>
					<button class="w-25 btn btn-lg btn-outline-dark" id="cancel">取消</button>
				</div>

				<div class="mt-5" style="color:red"> ${message }</div>
				<p class="mt-5 mb-3 text-muted">Copyright © 2022 iSpan. All
					rights reserved.</p>
			</form>
		</main>


	</div>


	<div class="text-center">
		快速登入:
		<button class="btn btn-outline-info fastLogin">Amy</button>
		<button class="btn btn-outline-info fastLogin">Bob</button>
		<button class="btn btn-outline-info fastLogin">Emma</button>
		<button class="btn btn-outline-info fastLogin">Jim</button>
	</div>
</body>

<script type="text/javascript">
	//快速登入
	$(".fastLogin").click(function() {
		$("#floatingInput").val(this.innerHTML);
		$("#floatingPassword").val(this.innerHTML + "001");
	});

	//密碼可視切換
	$("#checkEye").click(function() {
		if ($(this).hasClass('fa-eye')) {
			$("#floatingPassword").attr('type', 'password');
		} else {
			$("#floatingPassword").attr('type', 'text');
		}
		$(this).toggleClass('fa-eye').toggleClass('fa-eye-slash');
	});

	//取消按鈕返回上頁
	$("#cancel").click(function(event) {
		event.preventDefault(); //取消預設行為
		window.history.back(); //返回上一頁
	})
</script>

</html>