<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>User ID</td>
                    <td>
                        <input type="text" name="txtuserid" required="" 
                               value="<%= request.getAttribute("userID") == null ? "" : (String) request.getAttribute("userID")%>">
                        <%= request.getAttribute("ERROR") == null ? "" : (String) request.getAttribute("ERROR")%>
                    </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="txtpassword" required=""></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Login">
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
