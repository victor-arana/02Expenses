package net.nodata.expenses.DAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.nodata.expenses.DTO.DTOGasto;

public class DAOGasto {
	
	/**
	 * Insert a new register into the table Gastos
	 * @param gasto 
	 * @param con Database connection
	 * @throws IOException
	 * 
	 * @author Victor José Arana Rodríguez
	 * @since 19/09/2014
	 */
	public static void add(DTOGasto gasto,Connection con) throws IOException{
		
		PreparedStatement statement = null;
		
		try{
			Date fecha = gasto.getFecha();
			String insertTableSQL = "INSERT INTO gastos" + " (tipo, costo, descripcion, fecha) VALUES " + "(?,?,?,?)";
			statement = con.prepareStatement(insertTableSQL);
			statement.setString(1, gasto.getTipo());
			statement.setDouble(2, gasto.getCosto());			
			statement.setString(3, gasto.getDescripcion());
			statement.setDate(4, new java.sql.Date(fecha.getTime()));
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static List<DTOGasto> getGastos(Connection con) throws IOException, SQLException{
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<DTOGasto> gastos = new ArrayList<DTOGasto>();
		
		try{
			statement = con.prepareStatement("SELECT * FROM gastos ORDER BY fecha DESC");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				DTOGasto dtoGasto = new DTOGasto();
				dtoGasto.setTipo(resultSet.getString("tipo"));
				dtoGasto.setFecha(resultSet.getDate("fecha"));
				dtoGasto.setCosto(resultSet.getDouble("costo"));				
				dtoGasto.setDescripcion(resultSet.getString("descripcion"));
				//System.out.println(dtoGasto.toString()); 
				gastos.add(dtoGasto);
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally{
			// Close connection?
		}
		
		return gastos;

	}
	
	public static List<DTOGasto> getGastos(Connection con, Date fechaInicio, Date fechaFin, String tipo) throws IOException, SQLException{
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<DTOGasto> gastos = new ArrayList<DTOGasto>();
		
		try{
			String queryString = "SELECT * FROM gastos WHERE tipo = ? AND fecha >= ? AND fecha <= ? ORDER BY fecha DESC";
			statement = con.prepareStatement(queryString);
			statement.setString(1, tipo);
			statement.setDate(2, new java.sql.Date(fechaInicio.getTime()));
			statement.setDate(3, new java.sql.Date(fechaFin.getTime()));
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				DTOGasto dtoGasto = new DTOGasto();
				dtoGasto.setTipo(resultSet.getString("tipo"));
				dtoGasto.setFecha(resultSet.getDate("fecha"));
				dtoGasto.setCosto(resultSet.getDouble("costo"));				
				dtoGasto.setDescripcion(resultSet.getString("descripcion"));
				System.out.println(dtoGasto.toString()); 
				gastos.add(dtoGasto);
			}
			
		} catch(SQLException e){
			
		} finally{
			
		}
		return gastos;
	}

}
