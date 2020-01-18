<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>SKill Manage</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<c:choose>
    <c:when test="${'add'.equals(job)}">
        <h2>Dodaj umiejętność</h2>
    </c:when>
    <c:otherwise>
        <h2>Edytuj umiejętność</h2>
    </c:otherwise>
</c:choose>

<form method="post" action="/admin/skill-addOrEdit">
    <fieldset>
        <legend>Umiejętność</legend>
        <label for="name">Nazwa umiejętności: </label>
        <input type="text" name="skillName" id="name" value="${skill.name}" required><br>

    </fieldset>
    <fieldset>
    <input type="submit" value="Zatwierdz">
    <input type="reset" value="Resetuj">
    <input type="hidden" name="skill_id" value="${skill.id}">
    </fieldset>
</form>
<a href="/admin/skill-manage">Anuluj</a><br>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>