package edu.val.cfticionic.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UsuarioCFTIC {
	
	@NotNull
	@Pattern(regexp="alumno\\d{1,2}")
	private String nombre;
	@NotNull
	@Pattern(regexp="alumno\\d{1,2}")
	private String pwd;
	private String token;
	
	
	
	
	public UsuarioCFTIC ()
	{
		//xdefecto
	}
	
	public UsuarioCFTIC(String nombre, String pwd, String token) {
		super();
		this.nombre = nombre;
		this.pwd = pwd;
		this.token = token;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UsuarioCFTIC [nombre=" + nombre + ", pwd=" + pwd + ", token=" + token + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		UsuarioCFTIC usuarioCFTIC = null;
		
			usuarioCFTIC = (UsuarioCFTIC) obj;
			iguales = usuarioCFTIC.getNombre().equals(this.getNombre());
			
			
		return iguales;
	}
	

}
