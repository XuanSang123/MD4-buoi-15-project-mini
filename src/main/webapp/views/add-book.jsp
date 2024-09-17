<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Thêm mới sách</title>
</head>
<body>
<h1>Thêm mới sách</h1>
<form action="/books" method="post">
    <label for="categoryId">Thể loại:</label>
    <select id="categoryId" name="categoryId" required>
        <c:forEach var="category" items="${categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select><br>

    <label for="name">Tên sách:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="price">Giá:</label>
    <input type="number" id="price" name="price" required><br>

    <label for="stock">Số lượng:</label>
    <input type="number" id="stock" name="stock" required><br>

    <label for="totalPages">Số trang:</label>
    <input type="number" id="totalPages" name="totalPages" required><br>

    <label for="yearCreated">Năm xuất bản:</label>
    <input type="number" id="yearCreated" name="yearCreated" required><br>

    <label for="author">Tác giả:</label>
    <input type="text" id="author" name="author" required><br>

    <label for="status">Trạng thái:</label>
    <select id="status" name="status" required>
        <option value="true">Đang bán</option>
        <option value="false">Ngừng bán</option>
    </select><br>

    <input type="submit" value="Thêm mới">
    <a href="/books?action=view">Quay lại trang chủ</a>
</form>
</body>
</html>
