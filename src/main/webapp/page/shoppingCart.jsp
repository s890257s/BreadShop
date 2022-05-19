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

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>

<body>
	<header class="p-3 mb-3 border-bottom">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="/"
					class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
					<svg class="bi me-2" width="40" height="32" role="img"
						aria-label="Bootstrap">
						<use xlink:href="#bootstrap"></use></svg>
				</a>

				<ul
					class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="/BreadShop/" class="nav-link px-2 link-secondary">線上麵包商店</a></li>
					<li><a href="/BreadShop/GetAllProducts.do"
						class="nav-link px-2 link-dark">商品頁面</a></li>
					<li><a href="/BreadShop/GetUserShoppingCart.do"
						class="nav-link px-2 link-dark">購物車</a></li>
					<li><a href="/BreadShop/page/admin.jsp" class="nav-link px-2 link-dark">後台管理</a></li>
				</ul>


				<div class="dropdown text-end">

					<c:if test="${user==null }">
						<a href="login.jsp">
							<button class="btn btn-primary">會員登入</button>
						</a>
					</c:if>

					<c:if test="${user!=null }">
						<a href="#"
							class="d-block link-dark text-decoration-none dropdown-toggle"
							id="dropdownUser1" data-bs-toggle="dropdown"
							aria-expanded="false"> <img src="${user.photo }" width="45"
							height="45" class="rounded-circle"> <span>${user.account }</span>

						</a>
						<ul class="dropdown-menu text-small"
							aria-labelledby="dropdownUser1">
							<li><a class="dropdown-item" href="#">會員資訊(建置中)</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/BreadShop/UserLogout.do">登出</a></li>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</header>


	<main>
		<div class="container">

			<div class="py-5 text-center">
				<div class="d-block mx-auto mb-4">
					<i class="fas fa-shopping-cart fa-2x"></i>
				</div>
				<h2>購物車</h2>
			</div>

			<div class="row g-5">
				<div class="col-md-6 col-lg-6 order-md-last">
					<h4 class="d-flex justify-content-between align-items-center mb-3">
						<span class="text-primary">商品清單</span> <span
							class="badge bg-primary rounded-pill">${shoppingCart.size()}
						</span>
					</h4>
					<ul class="list-group mb-3">
						<c:forEach var="s" items="${shoppingCart}">
							<li class="list-group-item d-flex justify-content-between lh-sm">
								<div>
									<h6 class="my-0 ">
										<a class="deleteProduct" deleteProductId="${s.products.id }"
											href="#">${s.products.name } </a>
									</h6>
								</div> <span class="text-muted subtotal"
								subtotal="${s.quantity * s.products.price}">${s.quantity}個
									× $${s.products.price } = ${s.quantity * s.products.price}</span>
							</li>
						</c:forEach>
						<li class="list-group-item d-flex justify-content-between"><span>總金額
								(TW)</span> <strong id="total"></strong></li>
					</ul>


				</div>
				<div class="col-md-6 col-lg-6">
					<h4 class="mb-3">帳單地址</h4>
					<form class="needs-validation" novalidate>
						<div class="row g-3">
							<div class="col-sm">
								<label for="firstName" class="form-label">姓名</label> <input
									type="text" class="form-control" id="firstName" placeholder=""
									value="" required>
							</div>


							<div class="col-12">
								<label for="email" class="form-label">Email </label> <input
									type="email" class="form-control" id="email"
									placeholder="you@example.com">

							</div>

							<div class="col-12">
								<label for="address" class="form-label">地址</label> <input
									type="text" class="form-control" id="address"
									placeholder="台北市大安區..." required>
							</div>
						</div>

						<hr class="my-4">

						<div class="form-check">
							<input type="checkbox" class="form-check-input" id="save-info">
							<label class="form-check-label" for="save-info">將帳單資訊加入快速結帳</label>
						</div>

						<hr class="my-4">

						<h4 class="mb-3">付款方式</h4>

						<div class="my-3">
							<div class="form-check">
								<input id="credit" name="paymentMethod" type="radio"
									class="form-check-input" checked required> <label
									class="form-check-label" for="credit">信用卡</label>
							</div>
							<div class="form-check">
								<input id="debit" name="paymentMethod" type="radio"
									class="form-check-input" required> <label
									class="form-check-label" for="debit">網路轉帳</label>
							</div>
							<div class="form-check">
								<input id="paypal" name="paymentMethod" type="radio"
									class="form-check-input" required> <label
									class="form-check-label" for="paypal">LinePay</label>
							</div>
						</div>


						<hr class="my-4">

						<button class="w-100 btn btn-primary btn-lg" type="submit">前往付款(未實作)</button>
					</form>
				</div>
			</div>
		</div>
	</main>

	<footer class="footer mt-5 py-3 bg-black ">
		<div class="container text-center">
			<span class=" text-light">Copyright © 2022 iSpan. All rights
				reserved.</span>
		</div>
	</footer>

</body>

<script type="text/javascript">
	$(document).ready(function(){
		let total=0;
		for(let item of $(".subtotal")){
			total += +item.getAttribute("subtotal");
		}
		$("#total").html('$'+total);

		$(".deleteProduct").click(function(event){
			event.preventDefault();
			aaa=event
			let pId = event.currentTarget.getAttribute("deleteProductId");
			if(confirm("刪除此項商品?")){
			location.replace("/BreadShop/DeleteShoppingCartProduct.do?deleteProductId="+pId);
			}
		})
	})

	
</script>
</html>