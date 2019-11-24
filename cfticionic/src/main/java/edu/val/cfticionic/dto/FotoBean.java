package edu.val.cfticionic.dto;

public class FotoBean {
	
	private int idfoto;
	private String ruta;
	
	
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
