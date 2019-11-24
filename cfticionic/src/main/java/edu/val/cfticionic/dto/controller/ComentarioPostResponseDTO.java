package edu.val.cfticionic.dto.controller;

public class ComentarioPostResponseDTO {
	
	private String nombre;
	private String texto;
	private int idcomentario;
	private long fecha;
	
	
	
	public ComentarioPostResponseDTO(String nombre, String texto, int idcomentario, long fecha) {
		super();
		this.nombre = nombre;
		this.texto = texto;
		this.idcomentario = idcomentario;
		this.fecha = fecha;
	}
	
	
	
	public int getIdcomentario() {
		return idcomentario;
	}

	public void setIdcomentario(int idcomentario) {
		this.idcomentario = idcomentario;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public long getFecha() {
		return fecha;
	}
	public void setFecha(long fecha) {
		this.fecha = fecha;
	}
	
	

}
