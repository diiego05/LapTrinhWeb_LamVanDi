<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/category/list">Quản trị</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/category/list">Danh mục</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/profile">Profile</a></li>
                    <li class="nav-item"><a class="nav-link text-danger" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Nội dung -->
    <div class="container mt-4">
        <sitemesh:write property="body"/>
    </div>

    <!-- Footer -->
    <footer class="bg-light text-center p-3 mt-4">
        <p>© 2025 - Quản lý website | MSSV: ...</p>
    </footer>
</body>
</html>
