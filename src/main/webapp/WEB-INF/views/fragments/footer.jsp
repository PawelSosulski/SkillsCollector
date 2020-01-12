<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="javafx.scene.input.DataFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <p>Autor: Paweł Sosulski, <%=LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)%></p>
</div>