<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 16/12/2024
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Catalog</title>
</head>
<body>
<h3>Update Catalog Information</h3>
<form action="<%=request.getContextPath()%>/CategoriesController?action=update" method="post">
    <label for="catalogId">Catalog Id</label>
    <input type="text" id="catalogId" name="catalogId" value="${catalog.catalogId}" readonly/><br>
    <label for="catalogName">Catalog Name</label>
    <input type="text" id="catalogName" name="catalogName" value="${catalog.catalogName}"/><br>
    <label for="description">Description</label>
    <input type="text" id="description" name="description" value="${catalog.description}"/><br>
    <label for="active">Status</label>
    <input type="radio" id="active" name="status" value="true" ${catalog.status?'checked':''}><label
        for="active">Active</label>
    <input type="radio" id="inactive" name="status" value="false" ${catalog.status?'':'checked'}><label for="inactive">Inactive</label><br>
    <input type="submit" value="Update"/>
</form>
</body>
</html>
