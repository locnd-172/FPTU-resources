<%-- 
    Document   : update
    Created on : Mar 18, 2022, 9:59:29 PM
    Author     : Admin
--%>

<%@page import="pe.DTO.Student"%>
<%@page import="pe.DAO.StudentDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String rollNo = request.getParameter("rollNo") == null ? "" : (String) request.getParameter("rollNo");
            Student st = StudentDAO.getStudentByID(rollNo);
        %>
        <form action="MainController" method="POST"> 
            <table>
                <tr>
                    <td>Roll no</td>
                    <td>
                        <input type="text" name="txtrollno" required="" readonly="" value="<%= rollNo%>" />
                        <%= request.getAttribute("ERROR") == null ? "" : (String) request.getAttribute("ERROR")%>
                    </td>
                </tr>
                <tr>
                    <td>Major</td>
                    <td>
                        <select name="major">
                            <option value="Engineering" <%=st.getMajor().equals("Engineering") ? "selected" : ""%> >Engineering</option>
                            <option value="Business" <%=st.getMajor().equals("Business") ? "selected" : ""%>>Business</option>
                            <option value="Others" <%=st.getMajor().equals("Others") ? "selected" : ""%>>Others</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Full name</td>
                    <td>
                        <input type="text" name="txtfullname" required="" value="<%= st.getFullName()%>">
                    </td>
                </tr>
                <tr>
                    <td>City </td>
                    <td>
                        <input type="text" name="txtcity" required="" value="<%= st.getCity()%>">
                    </td>
                </tr>
                <tr>
                    <td>Gender </td>
                    <td>
                        <input type="radio" id="male" name="gender" value="1" required="" <%=st.getMale() == 1 ? "checked" : "" %> />
                        <label for="male" >Male</label>
                        <input type="radio" id="female" name="gender" value="0" <%=st.getMale() == 0 ? "checked" : "" %> />
                        <label for="female" >Female</label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Update">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
