<%-- 
    Document   : index
    Created on : Mar 18, 2022, 4:24:34 PM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="pe.DTO.Student"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <form action="MainController" method="POST">
            <p>Select student's major 
                <select name="major">
                    <option value="All" selected="">All</option>
                    <option value="Engineering">Engineering</option>
                    <option value="Business">Business</option>
                    <option value="Others">Others</option>
                </select>
                <input type="submit" name="action" value="search" />
            </p>
        </form> 
        <table border="1">
            <tr>
                <td>RollNo</td>
                <td>Major</td>
                <td>FullName</td>
                <td>City</td>
                <td>Gender</td>
            </tr>
            <%--<c:set var="stList" value="${studentList}" scope="request"></c:set>--%>

            <c:forEach var="st" items="${requestScope.studentList}">
                <tr>
                    <td>${st.rollNo}</td>
                    <td>${st.major}</td>
                    <td>${st.fullName}</td>
                    <td>${st.city}</td>
                    <td>${st.gender == 1 ? "Male" : "Female"}</td>
                </tr>
            </c:forEach>
        </table>
        <form action="add.jsp">
            <input type="submit" value="Add new" />
        </form>
    </body>
</html>
