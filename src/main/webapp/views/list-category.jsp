<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách danh mục</title>
</head>
<body>
<h1>Danh sách danh mục</h1>

<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên danh mục</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="category" items="${categories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.status ? "Hoạt động" : "Không hoạt động"}</td>
            <td>
                <a href="categories?action=details&id=${category.id}">Chi tiết</a> |
                <a href="categories?action=update&id=${category.id}">Chỉnh sửa</a> |
                <a href="categories?action=delete&id=${category.id}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="categories?action=create">Thêm mới danh mục</a>
<a href="/">Quay trở lại trang chủ</a>
</body>
</html>
