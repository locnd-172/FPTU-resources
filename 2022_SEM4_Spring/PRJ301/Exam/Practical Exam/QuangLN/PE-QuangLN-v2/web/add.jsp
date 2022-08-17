<%-- 
    Document   : add
    Created on : Mar 18, 2022, 8:45:48 PM
    Author     : Admin
--%>

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
                        <input type="text" name="txtrollno" required="">
                        <%= request.getAttribute("ERROR") == null ? "" : (String) request.getAttribute("ERROR")%>
                    </td>
                </tr>
                <tr>
                    <td>Major</td>
                    <td>
                        <select name="major">
                            <option value="Engineering" selected="">Engineering</option>
                            <option value="Business">Business</option>
                            <option value="Others">Others</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Full name</td>
                    <td><input type="text" name="txtfullname" required=""></td>
                </tr>
                <tr>
                    <td>City </td>
                    <td><input type="text" name="txtcity" required=""></td>
                </tr>
                <tr>
                    <td>Gender </td>
                    <td>
                        <input type="radio" name="gender" value="1" required="" />
                        <label for="gender" >Male</label>
                        <input type="radio" name="gender" value="0" />
                        <label for="gender" >Female</label>
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
