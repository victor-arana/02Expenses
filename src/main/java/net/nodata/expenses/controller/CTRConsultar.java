package net.nodata.expenses.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.nodata.expenses.DAO.DAOGasto;
import net.nodata.expenses.DTO.DTOGasto;

/**
 * Servlet implementation class CTRConsultar
 */
public class CTRConsultar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. Variables to be collected
		String tipo = null;
		Date fechaInicio = null;
		Date fechaFin = null;
		
		// 1. Get parameters from the request
		tipo = request.getParameter("tipo");
		try {
			fechaInicio = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechaInicio"));
			fechaFin =  new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fechaFin"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch(NumberFormatException e2){
			e2.printStackTrace();
		}
		
		// 2. Get a connection to Database
		ServletContext sc = getServletContext();
		Connection con = (Connection) sc.getAttribute("DBConnection");
		
		// 3. Retrieve the the information from the database
		List<DTOGasto> result = null;
		try {
			result = DAOGasto.getGastos(con,fechaInicio, fechaFin, tipo);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 5. Store the result object in the request object
		request.setAttribute("result", result);		
		
		// 6. Forward request and response objects to JSP page
		String url = "/displayExpenses.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
					
		}

}
