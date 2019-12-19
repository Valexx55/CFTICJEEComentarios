package edu.val.cfticionic.dto;

public class FotoBean {
	
	private int idfoto;
	private String ruta;
	private String titulo;
	
	
	public FotoBean(int idfoto, String ruta, String titulo) {
		super();
		this.idfoto = idfoto;
		this.ruta = ruta;
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public FotoBean() {}
	
	public FotoBean(int idfoto, String ruta) {
		super();
		this.idfoto = idfoto;
		this.ruta = ruta;
	}
	
	
	
	public int getIdfoto() {
		return idfoto;
	}

	public void setIdfoto(int idfoto) {
		this.idfoto = idfoto;
	}

	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	

}
