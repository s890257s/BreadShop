<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<style>
.backgroundImg {
	background-image:url('/BreadShop/assets/Bread.png');
	background-color: #DEFFFF;
}

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
					<li><a href="/BreadShop/GetAllProducts.do" class="nav-link px-2 link-dark" >商品頁面</a></li>
					<li><a href="/BreadShop/GetUserShoppingCart.do" class="nav-link px-2 link-dark">購物車</a></li>
				</ul>


			</div>
		</div>
	</header>
	<main>
		<div
			class=" position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center backgroundImg ">
			<div class="col-md-5 p-lg-5 mx-auto my-5">
				<h1 class="display-4 fw-normal text-light">麵包商店</h1>
				<p class="lead fw-normal text-light">好吃的麵包在哪裡</p>
				<a class="btn btn-warning" href="/BreadShop/GetAllProducts.do">在這裡</a>
			</div>
			<div class="product-device shadow-sm d-none d-md-block"></div>
			<div
				class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
		</div>
	</main>
	<footer class="footer mt-auto py-3 bg-black">
		<div class="container text-center">
			<span class=" text-light">Copyright © 2022 iSpan. All rights
				reserved.</span>
		</div>
	</footer>




</body>

</html>