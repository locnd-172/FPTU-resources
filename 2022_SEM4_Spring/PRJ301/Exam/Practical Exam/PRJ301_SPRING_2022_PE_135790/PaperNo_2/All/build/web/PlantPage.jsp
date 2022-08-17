<%-- 
    Document   : PlantPage
    Created on : Mar 10, 2022, 7:52:50 AM
    Author     : Loc NgD <locndse160199@fpt.edu.vn>
--%>

<%@page import="pe.DAO.PlantDAO"%>
<%@page import="pe.DTO.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            td:nth-child(2) {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <h1>Information of the plant</h1>
        <p></p>
        <%
            int pid = 0;
            Plant plant = null;
            if (request.getParameter("id") != null) {
                pid = Integer.parseInt((String) request.getParameter("id"));
                plant = PlantDAO.getPlantById(pid);
        %>
        <table style="width: 50%;">
            <tr>
                <td>ID</td>
                <td><%=plant.getPID()%></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><%=plant.getPName()%></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><%=plant.getPrice()%></td>
            </tr>
            <tr>
                <td>Image path</td>
                <td><%=plant.getImgPath()%></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><p><%=plant.getDescription()%></p></td>
            </tr>
            <tr>
                <td>Category name</td>
                <td><%=plant.getCateName()%></td>
            </tr>
            <form action="MainController" method="POST">
                <input type="hidden" name="id" value="<%=plant.getPID()%>"/>
                <input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>"/>
                <tr>
                    <td>Status</td>
                    <%
                        int status = plant.getStatus();
                    %>

                    <td style="border: none;">
                        <select name="status">
                            <% if (status == 1) { %>
                            <option value="active" selected="">Active</option>
                            <option value="inactive">Inactive</option>
                            <% } else { %>
                            <option value="active">Active</option>
                            <option value="inactive" selected="">Inactive</option>
                            <% }%>
                        </select>
                        <p><%=status%></p>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Update">
                    </td>
                </tr>
            </form>
        </table>
        <%            }
        %>

    </body>
</html>
