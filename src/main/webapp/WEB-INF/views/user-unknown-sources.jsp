<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Unknown sources</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<h3>Źródła wiedzy do nauki dla ${user.firstName} ${user.lastName}</h3>

<table>
    <th>Lp.</th>
    <th>Nazwa źródła</th>
    <th>Opis źródła</th>
    <th>Umiejętności z źródła</th>
    <th>Potwierdz umiejętność</th>
    <c:forEach items="${userUnknownSource}" var="source" varStatus="stat">
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
            <td>
                <c:url value="/sources/confirm" var="confirmUrl">
                <c:param name="sourceId" value="${source.id}"/>
                </c:url>
                <a href="${confirmUrl}">Accept</a>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</body>
</html>