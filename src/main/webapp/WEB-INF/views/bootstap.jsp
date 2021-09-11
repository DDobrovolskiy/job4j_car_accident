<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>Наименование</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="element">
                 <tr>
                    <td>
                        <c:out value="${element}"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>