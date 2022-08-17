<%-- 
    Document   : index
    Created on : Mar 19, 2022, 12:21:02 AM
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
        <%
            String username = (String) session.getAttribute("fullname");
            if (username == null) { // not loged in
                response.sendRedirect("login.jsp");
            } else {
        %>        
                <h1>Welcome <%= username%> </h1>
                <a href="MainController?action=logout">Logout</a>
        <%
            }
        %>
    </body>
</html>
