<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa sách</title>
</head>
<body>
<h1>Chỉnh sửa sách</h1>

<c:if test="${not empty book}">
    <form action="books" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${book.id}">

        <label for="name">Tên sách:</label>
        <input type="text" id="name" name="name" value="${book.name}" required><br>

        <label for="categoryId">Thể loại:</label>
        <select id="categoryId" name="categoryId" required>
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}" ${category.id == book.categoryId.id ? 'selected' : ''}>
                        ${category.name}
                </option>
            </c:forEach>
        </select><br>

        <label for="price">Giá:</label>
        <input type="number" id="price" name="price" value="${book.price}" step="0.01" required><br>

        <label for="stock">Số lượng:</label>
        <input type="number" id="stock" name="stock" value="${book.stock}" required><br>

        <label for="totalPages">Số trang:</label>
        <input type="number" id="totalPages" name="totalPages" value="${book.totalPages}" required><br>

        <label for="yearCreated">Năm xuất bản:</label>
        <input type="number" id="yearCreated" name="yearCreated" value="${book.yearCreated}" required><br>

        <label for="author">Tác giả:</label>
        <input type="text" id="author" name="author" value="${book.author}" required><br>

        <label for="status">Trạng thái:</label>
        <select id="status" name="status" required>
            <option value="true" ${book.status ? 'selected' : ''}>Đang bán</option>
            <option value="false" ${!book.status ? 'selected' : ''}>Ngừng bán</option>
        </select><br>

        <input type="submit" value="Cập nhật">
        <a href="books?action=view">Trở về danh sách sách</a>
    </form>
</c:if>

<c:if test="${empty book}">
    <p>Không tìm thấy thông tin sách để chỉnh sửa.</p>
    <a href="books?action=view">Trở về danh sách sách</a>
</c:if>
</body>
</html>
