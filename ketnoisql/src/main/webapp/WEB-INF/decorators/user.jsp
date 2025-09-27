<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
    <title><decorator:title default="Website"/></title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">User</a>
            <div>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">Đăng Nhập</a>
                <a href="${pageContext.request.contextPath}/register" class="btn btn-success">Đăng Ký</a>
            </div>
        </div>
    </nav>

    <!-- Nội dung -->
    <div class="container mt-4">
        <decorator:body/>
    </div>

    <!-- Footer -->
    <footer class="bg-light text-center p-3 mt-4">
        © 2025 - User Page
    </footer>
</body>
</html>
