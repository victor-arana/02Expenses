package net.nodata.expenses.CTR;

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
 * @author Victor José Arana Rodríguez
 * @since 19/09/2014
 *
 */
public class CTRGastos extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				
		String tipo = null;
		Date fecha = null;
		Double costo = null;
		String descripcion = null;
		
		// 1. Get parameters from the request
		tipo = request.getParameter("tipo");
		descripcion = request.getParameter("descripcion");
		try {
			fecha = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("fecha"));
			costo = Double.parseDouble(request.getParameter("costo"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch(NumberFormatException e2){
			e2.printStackTrace();
		}
		
		// 2. Get a connection to Database
		ServletContext sc = getServletContext();
		Connection con = (Connection) sc.getAttribute("DBConnection"); 
		
		// 3. Use a DTO to register the collected info and save it to the db
		DTOGasto gasto = new DTOGasto(tipo, fecha, costo, descripcion);
		DAOGasto.add(gasto, con);
		
		// 4. Retrieve all the information from the database
		List<DTOGasto> result = null;
		try {
			result = DAOGasto.getGastos(con);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		// 5. Store the result object in the request object
		request.setAttribute("result", result);		
		request.setAttribute("gasto", gasto);
		
		// 6. Forward request and response objects to JSP page
		String url = "/displayExpenses.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
