<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
    <%@ page import="net.nodata.expenses.DTO.DTOGasto" %>
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
<%
	List<DTOGasto> gastos = (List<DTOGasto>)request.getAttribute("result");
	Iterator<DTOGasto> it = gastos.iterator();
	DTOGasto elemento = null; 
	
	while(it.hasNext()){
		elemento = it.next();
		out.println("<tr> <td>" + elemento.getTipo() + " </td>" + "<td>" +  elemento.getFecha()  + "</td>"+ 
				         "<td>" +  elemento.getCosto()  + "</td>"+  
				         "<td>" +  elemento.getDescripcion()  + "</td>"+"</tr");
	}
	
%>
</table>

</body>
</html>