<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form  action="<c:url value='/save'/>" method='POST'>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <a href="<c:url value='/'/>">На главную</a>
            </div>
            <div class="card-body">
                <table class="table">
                    <tr>
                        <td>Тип:</td>
                        <td>
                            <select name="type.id">
                                <c:forEach var="type" items="${types}" >
                                    <option value="${type.id}">${type.name}</option>
                                </c:forEach>
                            </select>
                    </tr>
                    <tr>
                        <td>Статьи:</td>
                        <td>
                            <select name="rIds" multiple>
                                <c:forEach var="rule" items="${rules}" >
                                    <option value="${rule.id}">${rule.name}</option>
                                </c:forEach>
                            </select>
                    </tr>
                    <tr>
                        <td>Имя:</td>
                        <td><input type='text' name='name' required></td>
                    </tr>
                    <tr>
                        <td>Адресс:</td>
                        <td><input type='text' name='address' required></td>
                    </tr>
                    <tr>
                        <td>Описание:</td>
                        <td><input type='text' name='text' required></td>
                    </tr>
                    <tr>
                        <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
                    </tr>
                </table>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>