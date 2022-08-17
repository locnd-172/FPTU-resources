<%@page import="locnd.DAO.FoodDAO"%>
<%@page import="locnd.DTO.Food"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
                border-collapse: collapse;
            }
        </style>
    </head>
    <body>
        <%
            String fullname = (String) session.getAttribute("fullname");
            if (fullname == null) { // not loged in
                response.sendRedirect("login.jsp");
            } else {
                String keyword = (String) request.getAttribute("keyword");
        %>        
        <h1> Welcome admin <%= fullname%> </h1>
        <a href="MainController?action=Logout">Logout</a>
        <br/>

        <form action="MainController" method="POST">
            <input type="text" name="txtSearch" value="<%=keyword == null ? "" : keyword%>"/>
            <input type="submit" name="action" value="Search"/>
        </form>

        <br/>
        <%
            ArrayList<Food> searchList = (ArrayList<Food>) request.getAttribute("foodList");
            ArrayList<Food> fullList = (ArrayList<Food>) FoodDAO.getFoods();
            ArrayList<Food> list = null;
            if (searchList == null) {
                list = fullList;
            } else {
                list = searchList;
            }

            if (list != null && !list.isEmpty()) {
        %>


        <table border="1">
            <tr>
                <td>No</td>
                <td>ID</td>
                <td>Name</td>
                <td>Price</td>
                <td>Cooking time</td>
                <td>Description</td>
                <td>Status</td>
                <td>Delete</td>
            </tr>
            <%  int cnt = 0;
                for (Food food : list) {
                    ++cnt;
            %>
            <tr>
                <td><%=cnt%></td>
                <td><%=food.getFoodID()%></td>
                <td><%=food.getFoodName()%></td>
                <td><%=food.getPrice()%></td>
                <td><%=food.getCookingTime()%></td>
                <td><%=food.getDescription()%></td>
                <td><%=food.getStatus() == 1 ? "Available" : "Unavailable"%>
                </td>
                <td>
                    <a href="MainController?action=Delete&id=<%=food.getFoodID()%>&keyword=<%=keyword%>">Delete</a>
                </td>
            </tr>
            <%    }
            %>

        </table>
        <%  } %>
        <br/>


        <%
            }
        %>

    </body>
</html>
