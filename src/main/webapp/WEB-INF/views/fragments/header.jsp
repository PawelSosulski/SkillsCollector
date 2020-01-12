<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <c:choose>
        <c:when test="${user != null}">
            <a href="/user/skills">Strona główna</a>
            <a href="/user/sources">Źródła wiedzy</a>
            <a href="/user/unknown-sources">Nieznane źródła wiedzy</a>
            <a href="/logout">logout</a>
        </c:when>
        <c:otherwise>
            <c:if test="${!hiddenLinks.contains('/register')}">
                <a href="/register">Rejestracja</a>
            </c:if>
            <c:if test="${!hiddenLinks.contains('/login')}">
                <a href="/login">Login</a>
            </c:if>

        </c:otherwise>
    </c:choose>


</div>
<div>
    <c:if test="${user != null}">
        <br><span>Witaj ${user.firstName}!</span><br><br>
    </c:if>
</div>