package net.nodata.expenses.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
		
		// Get a connection to Database
		ServletContext sc = getServletContext();
		Connection con = (Connection) sc.getAttribute("DBConnection"); 
		
		// Use a DTO to register the collected info and save it to the db
		DTOGasto gasto = new DTOGasto(tipo, fecha, costo, descripcion);
		DAOGasto.add(gasto, con);
		
		// Retrieve all the information from the database
		List<DTOGasto> result = null;
		try {
			result = DAOGasto.getGastos(con);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		// Store the result object in the request object
		request.setAttribute("result", result);		
		request.setAttribute("gasto", gasto);
		
		
		// forward request and response objects to JSP page
		String url = "/displayExpenses.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
