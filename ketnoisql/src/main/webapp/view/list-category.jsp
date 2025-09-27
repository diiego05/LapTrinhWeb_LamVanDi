<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh mục</title>
</head>
<body>
	<h2>Danh sách danh mục</h2>

	<!-- Button thêm mới -->
	<a href="<c:url value='/admin/category/add'/>">
		<button type="button">+ Thêm danh mục</button>
	</a>
	<!-- Nút logout -->
	<a href="<c:url value='/logout'/>" style="margin-left: 20px;">
		<button type="button">Đăng xuất</button>
	</a>
	<a href="<c:url value='/member/profile'/>">
		<button type="button">Hồ sơ cá nhân</button>
	</a>
	<br>
	<br>


	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<th>#</th>
			<th>Ảnh</th>
			<th>Tên danh mục</th>
			<th>Hành động</th>
		</tr>
		<c:forEach items="${cateList}" var="cate" varStatus="STT">
			<tr>
				<td>${STT.index+1 }</td>
				<td><img height="80" width="100"
					src="<c:url value='/image?fname=${cate.icon}'/>"
					alt="Category Image" /></td>
				<td>${cate.catename }</td>
				<td><a
					href="<c:url value='/admin/category/edit?id=${cate.cateid }'/>">Sửa</a>
					| <a
					href="<c:url value='/admin/category/delete?id=${cate.cateid }'/>">Xóa</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
