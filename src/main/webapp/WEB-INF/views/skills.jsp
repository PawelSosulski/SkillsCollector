<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Skills</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
Your skills:
${user.firstName} ${user.lastName}

<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</body>
</html>