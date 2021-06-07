<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        div {
            text-align: center;
        }
    </style>
    <title>Users</title>
</head>
<body class="w3-light-grey">
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <h2>
        <div style="text-align: center;" class="w3-container w3-green">Users List</div>
    </h2>

    <table border="1" width="50%" style="border:3px solid green;margin-left:auto;margin-right:auto;">
        <tr>
            <th>Login</th>
            <th>Role</th>
            <c:if test="${access_admin == user_role or access_support == user_role}">
                <th>Action</th>
            </c:if>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.key}"/></td>
                <td><c:out value="${user.value.role}"/></td>
                <c:if test="${access_admin == user_role or access_support == user_role}">
                    <td>
                        <form action="${pageContext.request.contextPath}/users" method="post">
                            <input type="hidden" name="delete-user" value="${user.key}"/>
                            <input type="submit" value="Delete"/>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${access_admin == user_role or access_support == user_role}">
    <div>
        <div>
            <button class="w3-btn w3-green w3-round-large w3-margin-bottom"
                    onclick="location.href='${pageContext.request.contextPath}/add'">Add New User
            </button>
        </div>
    </div>
    </c:if>

    <div>
        <div>
            <button class="w3-btn w3-green w3-round-large w3-margin-bottom"
                    onclick="location.href='${pageContext.request.contextPath}/logout'">Logout
            </button>
        </div>
    </div>
</body>
</html>
