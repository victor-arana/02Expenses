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
	
	public static void add(DTOGasto gasto,Connection con) throws IOException{
		PreparedStatement statement = null;
		
		try{
			Date fecha = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(gasto.getFecha());
			String insertTableSQL = "INSERT INTO gastos" + " (tipo, costo, descripcion, fecha) VALUES " + "(?,?,?,?)";
			statement = con.prepareStatement(insertTableSQL);
			statement.setString(1, gasto.getTipo());				
			statement.setString(2, gasto.getCosto());
			statement.setString(3, gasto.getDescripcion());
			statement.setDate(4, new java.sql.Date(fecha.getTime()));
			statement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
			System.out.println("Error de parseo");
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
				dtoGasto.setFecha(resultSet.getDate("fecha").toString());
				dtoGasto.setCosto(resultSet.getString("costo"));
				dtoGasto.setDescripcion(resultSet.getString("descripcion"));
				System.out.println(dtoGasto.toString()); 
				gastos.add(dtoGasto);
			}
			
		} finally{
			
		}
		
		return gastos;

	}

}
