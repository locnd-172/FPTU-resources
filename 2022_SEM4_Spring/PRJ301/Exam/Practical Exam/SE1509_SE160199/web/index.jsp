<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String fullname = (String) session.getAttribute("fullname");
            if (fullname == null) { // not loged in
                response.sendRedirect("login.jsp");
            } else {
        %>        
                <h1>Welcome <%= fullname%> </h1>
                <a href="MainController?action=Logout">Logout</a>
        <%
            }
        %>
    </body>
</html>
