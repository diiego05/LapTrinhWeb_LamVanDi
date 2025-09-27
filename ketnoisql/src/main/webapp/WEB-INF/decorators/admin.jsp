<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>
<title><decorator:title default="Trang Quản Trị" /></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
	<!-- Header -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/admin/category/list">Admin</a>
			<div class="d-flex">
				<a href="${pageContext.request.contextPath}/logout"
					class="btn btn-danger">Đăng Xuất</a>
			</div>
		</div>
	</nav>

	<!-- Nội dung trang con -->
	<div class="container mt-4">
		<decorator:body />
	</div>

	<!-- Footer -->
	<footer class="bg-dark text-white text-center p-3 mt-4"> ©
		2025 - Admin Page </footer>
</body>
</html>
