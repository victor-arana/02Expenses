package net.nodata.expenses.DTO;

import java.util.Date;


public class DTOGasto {
	
	private String tipo;
	private Date fecha;
	private Double costo;
	private String descripcion;
	
	@Override
	public String toString() {
		return "[" + tipo + ", " + fecha + ", "
				+ costo + ", " + descripcion + "]";
	}

	public DTOGasto(String tipo, Date fecha, Double costo, String descripcion) {
		this.tipo = tipo;
		this.fecha = fecha;
		this.costo = costo;
		this.descripcion = descripcion;
	}
	
	public DTOGasto() {
		
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date date) {
		this.fecha = date;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
