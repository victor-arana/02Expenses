package net.nodata.expenses.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.nodata.expenses.DTO.DTOGasto;

public class DAOGasto {
	public static void add(DTOGasto gasto, String filepath) throws IOException {
		File file = new File(filepath);
		PrintWriter out = new PrintWriter(new FileWriter(file, true));
		out.println(gasto.getTipo() + "|" + gasto.getFecha() + "|"
				+ gasto.getCosto() + "|" + gasto.getDescripcion());
		out.close();
	}
	
	public static List<String> getGastos(String filepath) throws IOException{
		File file = new File(filepath);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		
		String line = null;
		List<String> gastos = new ArrayList<String>();
		
		while( (line = reader.readLine()) != null){
			gastos.add(line);
		}
		
		reader.close();
		return gastos;
	}

}
