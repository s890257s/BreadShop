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
					<li><a href="/BreadShop/GetUserShoppingCart.do" class="nav-link px-2 link-dark">購物車</a></li>
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
		<section class="py-5 text-center container">
			<div id="carouselExampleIndicators" class="carousel slide"
				data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="../img/a.jpg" class="d-block w-100 ">
					</div>
					<div class="carousel-item">
						<img src="../img/b.jpg" class="d-block w-100 ">
					</div>
					<div class="carousel-item">
						<img src="../img/c.jpg" class="d-block w-100 ">
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</section>

		<div class="album py-5 bg-light">
			<div class="container">

				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

					<c:forEach var="p" items="${products}">
						<div class="col">
							<div class="card">
								<img src="${p.photo }" class="card-img-top" alt="">
								<div class="card-body text-center fs-4">${p.name }</div>
								<div class="card-footer text-end">
									<span class="me-5">$${p.price }</span>
									<button class="btn btn-primary add" productId="${p.id} ">加入購物車</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>

	<footer class="footer mt-auto py-3 bg-black">
		<div class="container text-center">
			<span class=" text-light">Copyright © 2022 iSpan. All rights
				reserved.</span>
		</div>
	</footer>

</body>

<script type="text/javascript">
	$(".add").click(function(event) {
		let pId = event.currentTarget.getAttribute("productId");
		location.replace("/BreadShop/AddProductToCart.do?productId="+pId);
	})
</script>
</html>