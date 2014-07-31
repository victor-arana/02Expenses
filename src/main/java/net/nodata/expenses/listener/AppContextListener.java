package net.nodata.expenses.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class AppContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		String driver = ctx.getInitParameter("DBDRIVER");
		String url = ctx.getInitParameter("DBURL");
		String user = ctx.getInitParameter("DBUSER");
		String pwd = ctx.getInitParameter("DBPWD");
		
		
		try{
			// load the driver
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,pwd);
			
			// Storing connection object as an attribute in ServletContext
			ctx.setAttribute("DBConnection",con);

		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}

	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
		try{
			con.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		

	}

}
