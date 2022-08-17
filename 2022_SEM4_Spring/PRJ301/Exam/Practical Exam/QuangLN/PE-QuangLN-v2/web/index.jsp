<%-- 
    Document   : index
    Created on : Mar 18, 2022, 8:18:19 PM
    Author     : Admin
--%>

<%@page import="pe.DAO.StudentDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pe.DTO.Student"%>
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
            String selectedMajor = request.getAttribute("selectedMajor") == null ? "" : (String) request.getAttribute("selectedMajor");
        %> 
        <form action="MainController" method="POST">
            <span>Select student's major 
                <select name="major">
                    <option value="All" <%= (selectedMajor.isEmpty() || selectedMajor.length() == 0 || selectedMajor.equals("All")) ? "selected=''" : ""%> >All</option>
                    <option value="Engineering" <%= (selectedMajor.equals("Engineering") == true) ? "selected" : ""%> >Engineering</option>
                    <option value="Business" <%= (selectedMajor.equals("Business")) == true ? "selected" : ""%> >Business</option>
                    <option value="Others" <%= (selectedMajor.equals("Others")) == true ? "selected" : ""%> >Others</option>
                </select>
                <input type="submit" name="action" value="Search" />
            </span>
        </form>
        <br/>
        <%
            ArrayList<Student> fullList = StudentDAO.getStudents();
            ArrayList<Student> searchList = (ArrayList<Student>) request.getAttribute("studentList");
            ArrayList<Student> list = null;
            if (searchList == null) list = fullList;
            else list = searchList;
            if (list != null && !list.isEmpty()) {
        %>
        <table border="1">
            <tr>
                <td>RollNo</td>
                <td>Major</td>
                <td>FullName</td>
                <td>City</td>
                <td>Gender</td>
                <td>Action</td>
            </tr>
            <% for (Student student : list) { %>
            <form action="MainController" method="POST">
                <input type="hidden" name="rollNo" value="<%=student.getRollNo()%>" />
                <tr>
                    <td><%=student.getRollNo()%></td>
                    <td><%=student.getMajor()%></td>
                    <td><%=student.getFullName()%></td>
                    <td><%=student.getCity()%></td>
                    <td><%=student.getMale() == 1 ? "Male" : "Female" %></td>
                    <td>
                        <a href="update.jsp?rollNo=<%=student.getRollNo()%>">Update</a>
                        <a href="MainController?action=Delete&rollNo=<%=student.getRollNo()%>">Delete</a>
                    </td>
                </tr>
            </form>
            <%    }
            %>
            
        </table>
        <%  } %>
        <br/>
        <form action="add.jsp" method="POST">
            <input type="submit" name="add student" value="Add new" />
        </form>
    </body>
</html>
