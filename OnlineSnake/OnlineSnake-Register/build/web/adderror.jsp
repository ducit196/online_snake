<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Error</title>
    </head>
    <body>
        <h3>T�i kho?n: <core:out value="${username}" /> ?� t?n t?i!!! T?o l?i t�i kho?n m?i</h3>
        <table>
            <tr>
                <td><a href="add">Retry</a></td>
            </tr>
        </table>
    </body>
</html>