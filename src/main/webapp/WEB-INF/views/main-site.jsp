<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Skills collector</title>
</head>
<body>
<c:choose>
    <c:when test="${user==null}">
        <h1>Welcome at Skill collector</h1>
        <table>
            <tr>
                <td>
                    <form action="/login" method="post">
                        <fieldset>

                            <div>
                                <label for="login">Login</label>
                                <input type="text" required id="login" name="login"/>
                            </div>
                            <div>
                                <label for="password">Password</label>
                                <input type="password" required id="password" name="password"/>
                            </div>
                        </fieldset>
                        <fieldset>
                            <input type="submit" value="Login"/><input type="reset" value="Reset">
                        </fieldset>
                    </form>
                </td>
                <td>
                    <a href="/register">Zarejestruj się !</a>
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <jsp:include page="fragments/header.jsp"/>
        <h1>Welcome at Skill collector</h1><br>
        <div>What`s up ${user.firstName}?</div>
    </c:otherwise>
</c:choose>
<br>
<h2>Top of the top</h2>
<div>
    <h3>Top najpopularniejszych źródeł wiedzy</h3>
    <table style="font-size: 10px;">
        <tr>
            <th>Lp.</th>
            <th>Nazwa</th>
            <th>Wynik</th>
        </tr>
        <c:forEach items="${sourceMap.keySet()}" var="source" varStatus="stat">
            <tr>
                <td>${stat.count}.</td>
                <td>${source}</td>
                <td>${sourceMap.get(source)}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <h3>Top użytkowników</h3>
    <table style="font-size: 10px;">
        <tr>
            <th>Lp.</th>
            <th>Imię</th>
            <th>Nazwisko</th>
            <th>Wynik</th>
        </tr>
        <c:forEach items="${userMap.keySet()}" var="user" varStatus="stat">
            <tr>
                <td>${stat.count}.</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${userMap.get(user)}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <h3>Top najpopularniejszych umiejętności</h3>
    <table style="font-size: 10px;">
        <tr>
            <th>Lp.</th>
            <th>Nazwa</th>
            <th>Wynik</th>
        </tr>
        <c:forEach items="${skillMap.keySet()}" var="skill" varStatus="stat">
            <tr>
                <td>${stat.count}.</td>
                <td>${skill}</td>
                <td>${skillMap.get(skill)}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<br><br>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>