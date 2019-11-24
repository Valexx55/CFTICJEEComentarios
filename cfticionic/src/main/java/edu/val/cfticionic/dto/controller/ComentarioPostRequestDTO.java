package edu.val.cfticionic.dto.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ComentarioPostRequestDTO {
	
	//NO ES LO MISMO LOS DATOS QUE RECIBO RELATIVOS A UN COMENTARIO
	//QUE LOS QUE ALMACENO Y QUEDAN RELACIONADOS QUE LOS QUE LOS QUE SON DEVUELTOS
	//TODO
		/**
		 * 
		 * PORBAR PUT DELETE Y TODO UN POCO
		 * 
		 */

	@NotNull
	@Pattern(regexp=".{2,400}")
	private String texto;
	@NotNull
	@Pattern(regexp="alumno\\d{1,2}")
	private String nombre;
	@NotNull
	@Pattern(regexp="\\w{12}")
	private String token;
	@Min(1)
	@Max(10)
	private int idfoto;
	private int idcomentario;
	
	
	
	
	public int getIdcomentario() {
		return idcomentario;
	}
	public void setIdcomentario(int idcomentario) {
		this.idcomentario = idcomentario;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getIdfoto() {
		return idfoto;
	}
	public void setIdfoto(int idfoto) {
		this.idfoto = idfoto;
	}
	@Override
	public String toString() {
		return "ComentarioPostRequestDTO [texto=" + texto + ", nombre=" + nombre + ", token=" + token + ", idfoto="
				+ idfoto + "]";
	}
	
	
	
	
	
	
	
}
