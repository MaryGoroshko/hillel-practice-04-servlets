<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        div {
            text-align: center;
        }
    </style>
    <title>Login page</title>
</head>
<body class="w3-light-grey">
<form action="login" method="post">
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Login page</h2>
        </div>
        <div>
            <label>Login</label>
            <div>
                <input type="text" name="login" placeholder="Enter login" class="w3-border"/>
            </div>
        </div>

        <div>
            <label>Password</label>
            <div>
                <input type="password" name="password" placeholder="Enter password" class="w3-border"/>
            </div>
        </div>

        <div>
            <div>
                <button type="submit" value="Login" class="w3-btn w3-green w3-round-large w3-margin-bottom">Login
                </button>
            </div>
        </div>
    </div>
</form>
</body>
</html>