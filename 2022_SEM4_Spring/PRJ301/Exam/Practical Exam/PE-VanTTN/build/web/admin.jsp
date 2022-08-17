<%-- 
    Document   : admin
    Created on : Mar 18, 2022, 11:54:16 PM
    Author     : Admin
--%>

<%@page import="pe.DAO.FoodDAO"%>
<%@page import="pe.DTO.Food"%>
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
            String username = (String) session.getAttribute("fullname");
            if (username == null) { // not loged in
                response.sendRedirect("login.jsp");
            } else {
                String keyword = (String) request.getAttribute("keyword");
        %>        
            <h1>Welcome Ad <%= username%> </h1>
            <form action="MainController" method="POST">
                <input type="text" name="txtSearch" value="<%=keyword == null ? "" : keyword%>"/>
                <input type="submit" name="action" value="Search"/>
            </form>
            <br/>
            <%
            ArrayList<Food> searchList = (ArrayList<Food>) request.getAttribute("foodList");
            ArrayList<Food> fullList = (ArrayList<Food>) FoodDAO.getFoods();
            ArrayList<Food> list = null;
            if (searchList == null) list = fullList;
            else list = searchList;
            
            if (list != null && !list.isEmpty()) {
        %>
        
    
        <table border="1">
            <tr>
                <td>Food ID</td>
                <td>Food Name</td>
                <td>Cooking time</td>
                <td>Status</td>
                <td>Action</td>
            </tr>
            <% for (Food food : list) { %>
            <form action="MainController" method="POST">
                <input type="hidden" name="foodid" value="<%=food.getFoodID()%>" />
                <tr>
                    <td><%=food.getFoodID()%></td>
                    <td><%=food.getFoodName()%></td>
                    <td><%=food.getCookingTime()%></td>
                    <td>
                        <select name="status">
                            <option value="1" <%=food.getStatus() == 1 ? "selected" : ""%> >Available</option>
                            <option value="0" <%=food.getStatus() == 0 ? "selected" : ""%> >Unavailable</option>
                        </select>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <!--<a href="update.jsp?foodid=>">Update</a>-->
                        <a href="MainController?action=Delete&foodid=<%=food.getFoodID()%>">Delete</a>
                    </td>
                </tr>
            </form>
            <%    }
            %>
            
        </table>
        <%  } %>
        <br/>
        <form action="add-food.jsp" method="POST">
            <input type="submit" name="add food" value="Add new" />
        </form>
        
        
        
        
        <%
            }
        %>
        
    </body>
</html>
