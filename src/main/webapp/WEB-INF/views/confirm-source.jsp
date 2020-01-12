<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm source</title>
</head>
<body>
<h3>Potwierdz źródło</h3>
<form method="post" action="/sources/confirm">
    <fieldset>
        <legend>Czy chcesz potwierdzić to źródło ?</legend>
        <label for="sourceName">Nazwa źródła</label>
        <input type="text" value="${source.name}" id="sourceName" readonly><br>
        <label for="sourceDesc">Nazwa źródła</label>
        <input type="text" value="${source.description}"id="sourceDesc" readonly><br>
        <input type="hidden" name ="sourceId" value="${source.id}">
        <input type="submit" value="Potwierdz">
    </fieldset>
</form>
<a href="user-unknown-sources.jsp">Powrót</a>


</body>
</html>