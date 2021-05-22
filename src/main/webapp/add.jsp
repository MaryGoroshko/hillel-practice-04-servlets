<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        div {
            text-align: center;
        }
    </style>
    <title>Add user</title>
</head>
<body class="w3-light-grey">
<form action="add" method="post">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Add user</h2>
        </div>
        <label>Name:
            <input type="text" name="login" placeholder="Enter login" class="w3-border"/>
        </label>
        <label>Password:
            <input type="password" name="password" placeholder="Enter password" class="w3-border"/>
        </label>
        Role:
        <select name="role">
            <c:forEach var="roleItem" items='<%= request.getSession().getServletContext().getAttribute("roleList") %>'>
                <option value='<c:out value="${roleItem}" />'><c:out value="${roleItem}"/></option>
            </c:forEach>
        </select>
        <button type="submit">Submit</button>

        <div>
            <div>
                <button class="w3-btn w3-green w3-round-large w3-margin-bottom"
                        onclick="location.href='/users'">Users list</button>
            </div>
        </div>

        <div>
            <div>
                <button class="w3-btn w3-green w3-round-large w3-margin-bottom"
                        onclick="location.href='/logout'">Logout</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>
