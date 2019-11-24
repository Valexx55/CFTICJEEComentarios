package edu.val.cfticionic.dto;

import java.util.Date;

import edu.val.cfticionic.dao.ListaComentarios;

public class Comentario {
	
	private int id;
	private String autor;
	private int idfoto;
	private long momento;
	private String texto;
	
	
	public Comentario ()
	{
		
	}

	
	

	public Comentario(int id, String autor, int idfoto, long momento, String texto) {
		super();
		this.id = id;
		this.autor = autor;
		this.idfoto = idfoto;
		this.momento = momento;
		this.texto = texto;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public int getIdfoto() {
		return idfoto;
	}


	public void setIdfoto(int idfoto) {
		this.idfoto = idfoto;
	}


	public long getMomento() {
		return momento;
	}


	public void setMomento(long momento) {
		this.momento = momento;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}




	@Override
	public String toString() {
		return "Comentario [id=" + id + ", autor=" + autor + ", idfoto=" + idfoto + ", momento=" + momento + ", texto="
				+ texto + "]";
	}
	
	
	

}
