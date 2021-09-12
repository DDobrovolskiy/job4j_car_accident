<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.job4j.accident.models.Accident" %>
<html>
<head>
    <title>Accident</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <a href="<c:url value='/create'/>">Добавить инцидент</a>
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Номер</th>
                            <th scope="col">Тип происшествия</th>
                            <th scope="col">Нарушения</th>
                            <th scope="col">Имя</th>
                            <th scope="col">Адресс</th>
                            <th scope="col">Описание</th>
                            <th scope="col">Изменить</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${accidents}" var="accident">
                             <tr>
                                <td>
                                    <c:out value="${accident.getId()}"/>
                                </td>
                                <td>
                                    <c:out value="${accident.getType().getName()}"/>
                                </td>
                                <td>
                                    <c:forEach var="rule" items="${accident.getRules()}" >
                                        <p><c:out value="${rule.getName()}"/></p>
                                    </c:forEach>
                                </td>
                                <td>
                                     <c:out value="${accident.getName()}"/>
                                </td>
                                <td>
                                     <c:out value="${accident.getAddress()}"/>
                                </td>
                                <td>
                                     <c:out value="${accident.getText()}"/>
                                </td>
                                <td>
                                     <a href="<c:url value='/edit?id=${accident.getId()}'/>">Редактировать инцидент</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>