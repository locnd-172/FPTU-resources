<%-- 
    Document   : searchPage
    Created on : Mar 10, 2022, 7:38:45 AM
    Author     : Loc NgD <locndse160199@fpt.edu.vn>
--%>

<%@page import="pe.DTO.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<Plant> list = (ArrayList<Plant>) request.getAttribute("plantList");
            String keyword = (String) request.getAttribute("keyword");
        %>
        <form action="MainController" method="POST">
            <input type="text" name="txtSearch" value="<%=keyword == null ? "" : keyword%>"/>
            <input type="submit" name="action" value="search"/>
        </form>
        <%
            if (list != null && !list.isEmpty()) { %>
        <table>
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Price</td>
                <td>Image</td>
                <td>Category's Name</td>
                <td>Update</td>
            </tr>
            <% for (Plant plant : list) {%>

            <tr>
                <td><%=plant.getPID()%></td>
                <td><%=plant.getPName()%></td>
                <td><%=plant.getPrice()%></td>
                <td><img src="<%=plant.getImgPath()%>" style="width: 60px; height: 60px;"></td>
                <td><%=plant.getCateName()%></td>
                <td>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="id" value="<%=plant.getPID()%>"/>
                        <input type="hidden" name="stt" value="<%=plant.getStatus()%>"/>
                        <input type="hidden" name="keyword" value="<%=keyword%>"/>
                        <input type="submit" name="action" value="update"/>
                    </form>
                </td>
            </tr>

            <%  }
            %>
        </table>
        <%    }
        %>


    </body>
</html>
