<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Spring Registration Example</title>
    </head>
    <body><h3>Registration Form</h3>
        <form:form method="POST" action="/OnlineSnake-Register/addUser">
            <table>
                <tr><td>User Name:</td><td><form:input path="username" /></td></tr>
                <tr><td>Password:</td><td><form:password path="password" /></td></tr>
                <tr><td>Address:</td><td><form:input path="address" /></td></tr>
                <tr><td>Phone</td><td><form:input path="phone" /></td></tr>
                <tr><td><input type="submit" value="Submit" /></td></tr>
            </table>
        </form:form>
    </body>
</html>