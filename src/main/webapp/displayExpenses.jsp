<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Gastos</h1>

<table border = "1">
<tr><td>Tipo</td><td>Fecha</td><td>Costo</td><td>Descripción</td></tr>
  <c:forEach items="${result}" var="gasto">
    <tr>
      <td><c:out value="${gasto.tipo}" /></td>	
      <td><c:out value="${gasto.fecha}" /></td>
      <td><c:out value="${gasto.costo}" /></td>
      <td><c:out value="${gasto.descripcion}" /></td>
    </tr>
  </c:forEach>
</table>

</body>
</html>