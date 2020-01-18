<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Skill manage</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<h3>Zestawienie umięjętności</h3>
<a href="/admin/skill-addOrEdit">Dodaj umiejętność</a>

<form method="post" action="/admin/skill-manage">
<table>
    <tr>
        <th>Lp.</th>
        <th>Nazwa</th>
        <th></th>
    </tr>
    <c:forEach items="${allSkills}" var="skill" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${skill.name}</td>
            <td>
                <input type="radio" value="${skill.id}" name="skill_id">
            </td>
        </tr>
    </c:forEach>
</table><br>

    <select name="choice">
        <option value="edit">Edytuj</option>
        <option value="delete">Usuń</option>
    </select><br>
<input type="submit" value="Do!">
</form>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>