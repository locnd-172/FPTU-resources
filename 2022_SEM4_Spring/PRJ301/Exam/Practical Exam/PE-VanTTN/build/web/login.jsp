<%-- 
    Document   : login
    Created on : Mar 18, 2022, 11:53:29 PM
    Author     : Admin
--%>

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
                    <td>Fullname</td>
                    <td>
                        <input type="text" name="txtfullname" required="" 
                               value="<%= request.getAttribute("fullname") == null ? "" : (String) request.getAttribute("fullname")%>">
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
