<%-- 
    Document   : add-food
    Created on : Mar 19, 2022, 12:44:19 AM
    Author     : Admin
--%>

<%@page import="pe.Utils.FoodError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            FoodError foodError = (FoodError) request.getAttribute("FOOD_ERROR");
            if (foodError == null) {
                foodError = new FoodError();
            }
        %>
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>Food ID</td>
                    <td>
                        <input type="text" name="txtfoodid" required="">
                        <%= foodError.getFoodIDError()%>
                    </td>
                </tr>
                <tr>
                    <td>Food name</td>
                    <td>
                        <input type="text" name="txtfoodname" required="">
                        <%= foodError.getFoodNameError()%>
                    </td>
                </tr>
                <tr>
                    <td>Cooking time</td>
                    <td><input type="number" name="txtctime" required=""></td>
                    <%= foodError.getcTimeError()%>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>
                        <select name="status">
                            <option value="1" selected="">Available</option>
                            <option value="0">Unavailable</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add">
                        <input type="submit" name="action" value="Cancel">
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
