package edu.val.cfticionic.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.val.cfticionic.dto.Comentario;
import edu.val.cfticionic.dto.UsuarioCFTIC;

public class ListaComentarios {

	private static List<Comentario> lista_comentarios;
	private static String RUTA_FICHERO_LISTA = "lista_comentarios.json";
	private static String RUTA_FICHERO_ID = "id.txt";
	private static int semilla;
	private static AtomicInteger generador_ids;// = new AtomicInteger(0);

	private final static Logger log = Logger.getLogger("mylog");

	public static int nextId() {
		int siguiente_id = 0;

		siguiente_id = generador_ids.incrementAndGet();

		return siguiente_id;
	}

	public static void setSemilla(int semilla) {
		ListaComentarios.semilla = semilla;
		generador_ids = new AtomicInteger(semilla);
	}

	public static void guardarID(int id) {
		File f = null;
		FileWriter fw = null;

		f = new File(RUTA_FICHERO_ID);
		if (!f.exists()) {

			log.debug("El fichero de IDs NO existe");
			try {
				if (f.createNewFile()) {
					log.debug("fichero ids creado existosamente");
				}
			} catch (Exception e) {
				log.error("Error al crear el fichero de ids", e);
			}

		}
		try {
			fw = new FileWriter(f);
			fw.write(id + "");
			fw.close();
		} catch (Exception e) {
			log.error("Error guaradando el id " + id, e);
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception e) {
					log.error("Error liberando recursos con id " + id, e);
				}
			}
		}

	}

	public static int obtenerIdDisponible() {
		int id = 1;
		File f = null;
		FileReader fr = null;
		String linea = null;
		BufferedReader br = null;
		FileWriter fw = null;

		f = new File(RUTA_FICHERO_ID);
		if (f.exists()) {
			log.debug("Fichero ID's existe");
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);
				linea = br.readLine();
				id = Integer.parseInt(linea);
			} catch (Exception e) {
				log.error("Error al leer el id ", e);

			} finally {
				if (fr != null) {
					try {
						fr.close();
					} catch (Exception e) {
						// TODO: handle exception
						log.error("Error al liberar recursos de lectura ", e);
					}
				}

			}
		} else {
			log.debug("Fichero ID NO existe");
			guardarID(0);
		}

		return id;
	}

	public static void insertarNuevoComentario(Comentario comentario_nuevo) {
		lista_comentarios.add(comentario_nuevo);
		log.debug("comentario aniadido");
	}
	
	public static void borrarComentario(int posicion) {
		lista_comentarios.remove(posicion);
		log.debug("comentario eliminado");
	}

	public static List<Comentario> cargarListaComentarios() {

		FileReader fr = null;
		File fichero = null;
		BufferedReader br = null;
		String json_comentarios = null;
		Gson gson = null;
		Type type = null;

		try {

			fichero = new File(RUTA_FICHERO_LISTA);
			if (!fichero.exists()) {
				log.debug("Fichero comentarios no existe");
				if (fichero.createNewFile()) {
					log.debug("Fichero creado");
				}
				lista_comentarios = new ArrayList<Comentario>();
			} else {
				log.debug("Fichero comentarios ya existe");
				log.debug("Ruta fichero comentarios = " + fichero.getAbsolutePath());
				fr = new FileReader(fichero);
				br = new BufferedReader(fr);
				json_comentarios = br.readLine();

				if (json_comentarios == null || (json_comentarios.length() == 0)) {
					lista_comentarios = new ArrayList<Comentario>();
				} else {
					gson = new Gson();
					type = new TypeToken<List<Comentario>>() {
					}.getType();
					lista_comentarios = gson.fromJson(json_comentarios, type);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al cargar la lista de comentarios", e);

		} finally {

			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return lista_comentarios;

	}

	public static void guardarListaComentarios(List<Comentario> lista_comentarios) {
		Gson gson = null;
		String lista_string = null;
		FileWriter fw = null;

		if (lista_comentarios.size() > 0) {
			gson = new Gson();
			Type tipo = new TypeToken<List<Comentario>>() {
			}.getType();
			lista_string = gson.toJson(lista_comentarios, tipo);

			try {
				fw = new FileWriter(new File(RUTA_FICHERO_LISTA), false);
				fw.write(lista_string);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static List<Comentario> obtenerListaComentarios() {
		return lista_comentarios;
	}
	
	
	public static List<Comentario> obtenerListaComentarios(int idfoto) {
		
		List<Comentario> lista_comentarios_foto = null;
		
			lista_comentarios_foto = new ArrayList<Comentario>();
			for (Comentario comentario : lista_comentarios)
			{
				if (comentario.getIdfoto() == idfoto)
				{	
					lista_comentarios_foto.add(comentario);
				}
			}
		
		
		return lista_comentarios_foto;
	}
	
	public static int esPropietario (int idcomentario, String nombre)
	{
		int pos_actual = -1;
		boolean encontrado = false;
		Comentario comentario = null;
		int longuitud_lista = lista_comentarios.size();
		
		if (longuitud_lista > 0)
		{
			pos_actual = 0;
			while (!encontrado && pos_actual<longuitud_lista)
			{
				encontrado = (lista_comentarios.get(pos_actual).getId() == idcomentario);
				pos_actual++;
			}
			
			if (encontrado)
			{
				log.debug("Hemos encontrado al comentario con id " + idcomentario);
				pos_actual = pos_actual-1;
				comentario = lista_comentarios.get(pos_actual);
				if (!comentario.getAutor().equals(nombre))
				{
					pos_actual = -1;
				}
				
			} else {
				log.debug("Comentario NO ENCONTRADO id " + idcomentario);
				pos_actual= -1;
				}
		}
			
		return pos_actual;
		
	}

}
