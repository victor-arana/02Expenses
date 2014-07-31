package net.nodata.expenses.DTO;

public class DTOGasto {
	
	private String tipo;
	private String fecha;
	private String costo;
	private String descripcion;
	
	public DTOGasto(String tipo, String fecha, String costo2, String descripcion) {
		this.tipo = tipo;
		this.fecha = fecha;
		this.costo = costo2;
		this.descripcion = descripcion;
	}
	
	public DTOGasto() {
		// TODO Auto-generated constructor stub
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCosto() {
		return costo;
	}
	public void setCosto(String costo) {
		this.costo = costo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
