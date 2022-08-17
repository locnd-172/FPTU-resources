<%-- 
    Document   : add
    Created on : Mar 18, 2022, 5:09:35 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>Roll no</td>
                    <td>
                        <input type="text" name="txtrollno" value="" required="" >
                        <span>${requestScope.Error}</span>
                    </td>
                </tr>
                <tr>
                    <td>Major</td>
                    <td>
                        <select name="major">
                            <option value="Engineering" ${requestScope.majorTest.equals("Engineering")?"selected":""} >Engineering</option>
                            <option value="Business" ${requestScope.majorTest.equals("Business")?"selected":""}>Business</option>
                            <option value="Others" ${requestScope.majorTest.equals("Others")?"selected":""}>Others</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Full name</td>
                    <td><input type="text" name="txtfullname" value="" required=""></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><input type="text" name="txtcity" value="" required=""></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td>
                        <input type="radio" name="gender" value="1" required=""/>
                        <label for="gender">Male</label>
                        <input type="radio" name="gender" value="0" />
                        <label for="gender">Female</label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Save">
                        <input type="submit" name="action" value="Cancel">
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>
