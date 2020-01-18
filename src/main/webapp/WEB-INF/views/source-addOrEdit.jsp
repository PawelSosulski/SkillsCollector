<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Source Manage</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<c:choose>
<c:when test="${'add'.equals(job)}">
    <h2>Dodaj źródło</h2>
</c:when>
    <c:otherwise>
        <h2>Edytuj źródło</h2>
    </c:otherwise>
</c:choose>

<form  method="post"
        <c:if test="${'edit'.equals(job)}">action="/admin/source-edit"</c:if>
        <c:if test="${'add'.equals(job)}">action="/admin/source-add"</c:if>>
    <fieldset>
        <legend>Dane źródła</legend>
        <label for="name">Nazwa źródła: </label>
        <input type="text" name="sourceName" id="name" value="${source.name}" required><br>
        <label for="description">Opis źródła: </label>
        <textarea required name="description" rows="8" cols="30"
                  id="description">${source.description}</textarea>
    </fieldset>
    <fieldset>
        <legend>Umiejętności:</legend>
        <table>
            <c:forEach items="${skills}" var="skill">
                <tr>
                    <td>
                        <input type="checkbox" value="${skill.id}" name="skills"
                        <c:if test="${source.attachedSkills.contains(skill)}">
                                checked
                        </c:if>
                        >
                    </td>
                    <td>${skill.name}</td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
    <fieldset>
        <input type="submit" value="Zatwierdz">
        <input type="reset" value="Resetuj">
        <input type="hidden" name="sourceId" value="${source.id}">
    </fieldset>
</form>
<a href="/admin/source-manage">Anuluj</a><br>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>