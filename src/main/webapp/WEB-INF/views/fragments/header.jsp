<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <a href="/">Strona główna</a>
    <c:choose>
        <c:when test="${user != null}">
            <a href="/user/skills">Twoje umiejętności</a>
            <a href="/user/sources">Źródła wiedzy</a>
            <a href="/user/unknown-sources">Nieznane źródła wiedzy</a>
            <c:if test="${'admin'.equalsIgnoreCase(user.role)}">
                <a href="/admin/source-manage">Zarządzanie źródłami</a>
                <a href="/admin/skill-manage">Zarządzanie umiejętnościami</a>
            </c:if>
            <a href="/logout">logout</a><br>
            <span>  -- Witaj ${user.firstName}! --</span>
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
