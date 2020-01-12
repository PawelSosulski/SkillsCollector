<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Skills</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<h3>Your skills: ${user.firstName} ${user.lastName}</h3>
<table>
    <tr>
        <th>Lp.</th>
        <th>Umiejętność</th>
        <th>Poziom</th>
    </tr>
    <c:forEach items="${userSkills.keySet()}" var="skill" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${skill}</td>
            <td>${userSkills.get(skill)}</td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</body>
</html>