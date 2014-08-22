package net.nodata.expenses.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import net.nodata.expenses.DTO.DTOGasto;

public class DAOGasto {
	
	public static void add(DTOGasto gasto,Connection con) throws IOException {
		PreparedStatement statement = null;

		
		try{
			statement = con.prepareStatement("INSERT INTO gastos VALUES(?,?,?,?)");
			statement.setString(1, gasto.getTipo());
			statement.setDate(2, gasto.getFecha());			
			statement.setString(3, gasto.getCosto());
			statement.setString(4, gasto.getDescripcion());
			statement.executeUpdate();
		} catch(SQLException e){
			
		}

	}
	
	public static List<DTOGasto> getGastos(Connection con) throws IOException, SQLException{
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<DTOGasto> gastos = new ArrayList<DTOGasto>();
		
		try{
			statement = con.prepareStatement("SELECT * FROM gastos");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				DTOGasto dtoGasto = new DTOGasto();
				dtoGasto.setTipo(resultSet.getString("tipo"));
				dtoGasto.setFecha(resultSet.getDate("fecha"));
				dtoGasto.setCosto(resultSet.getString("costo"));
				dtoGasto.setDescripcion(resultSet.getString("descripcion"));
				gastos.add(dtoGasto);
			}
			
		} finally{
			
		}
		
		return gastos;

	}

}
