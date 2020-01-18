<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Source Manage</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<h3>Source remove: ${source.name}</h3>
<form action="/admin/source-remove" method="post">
    <legend>Source remove</legend>
    <label for="name">Name: </label>
    <input type="text" value="${source.name}" id="name"><br>
    <label for="description">Description:</label><br>
    <textarea id="description">${source.description}</textarea><br>
    <input type="submit" value="Send">
    <input type="hidden" name="source_id" value="${source.id}">
</form>
<a href="/admin/source-manage">Cancel</a>
<br>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>