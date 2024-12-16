<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 16/12/2024
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Catalog</title>
</head>
<body>
<h3>Input catalog Information</h3>
<form action="<%=request.getContextPath()%>/CategoriesController?action=create" method="post">
    <label for="catalogName">Catalog Name</label>
    <input type="text" id="catalogName" name="catalogName"/><br>
    <label for="description">Description</label>
    <input type="text" id="description" name="description"/><br>
    <label for="active">Status</label>
    <input type="radio" id="active" name="status" value="true" checked><label for="active">Active</label>
    <input type="radio" id="inactive" name="status" value="false"><label for="inactive">Inactive</label><br>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
