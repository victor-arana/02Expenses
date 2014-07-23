package net.nodata.expenses.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.nodata.expenses.DAO.DAOGasto;
import net.nodata.expenses.DTO.DTOGasto;

public class CTRGastos extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// Get parameters from the request
		String tipo = request.getParameter("tipo");
		String fecha = request.getParameter("fecha");
		String costo = request.getParameter("costo");
		String descripcion = request.getParameter("descripcion");
		
		// Get a relative file name
		ServletContext sc = getServletContext();
		String path = sc.getRealPath("/WEB-INF/ExpensesList.txt");
		
		// Use regular Java objects to write the data to a file
		DTOGasto gasto = new DTOGasto(tipo, fecha, costo, descripcion);
		DAOGasto.add(gasto, path);
		
		// Send response to browser
		List<String> result = DAOGasto.getGastos(path);
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Iterator<String> it = result.iterator();
		while(it.hasNext()){
			out.print("<br>Gasto: " + it.next());
		}
		
		out.close();
		
	}
}
