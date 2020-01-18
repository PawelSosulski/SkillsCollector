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
<h2>Zarządzanie źródłami</h2><br>
<c:if test="${errorMsg!=null}">
    <div>
        <hr>
            ${errorMsg}
        <hr>
    </div>
</c:if>

<a href="/admin/source-add">Dodaj nowe źródło</a><br>
<h3>Zestawienie źródeł</h3>

<table>
    <tr>
        <td>Lp.</td>
        <td>Nazwa</td>
        <td>Opis</td>
        <td>Umiejności</td>
        <td></td>
        <td></td>
    </tr>
    <c:forEach items="${allSource}" var="source" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${source.name}</td>
            <td>${source.description}</td>
            <td>
                <ul>
                    <c:forEach items="${source.attachedSkills}" var="skill">
                        <li>${skill}</li>
                    </c:forEach>
                </ul>
            </td>
            <c:url value="/admin/source-edit" var="editUrl">
                <c:param name="sourceId" value="${source.id}"/>
            </c:url>
            <td><a href="${editUrl}">Edytuj</a></td>
            <c:url value="/admin/source-remove" var="removeUrl">
                <c:param name="source_id" value="${source.id}"/>
            </c:url>
            <td><a href="${removeUrl}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>