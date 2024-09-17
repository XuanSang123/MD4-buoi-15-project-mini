<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm mới danh mục</title>
</head>
<body>
<h1>Thêm mới danh mục</h1>
<form action="categories?action=create" method="post">
    <label for="name">Tên danh mục:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="status">Trạng thái:</label>
    <select id="status" name="status" required>
        <option value="true">Hoạt động</option>
        <option value="false">Không hoạt động</option>
    </select><br>

    <input type="submit" value="Thêm mới">
</form>
</body>
</html>
