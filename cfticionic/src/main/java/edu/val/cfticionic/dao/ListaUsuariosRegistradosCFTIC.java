package edu.val.cfticionic.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.val.cfticionic.dto.UsuarioCFTIC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class ListaUsuariosRegistradosCFTIC {

	private static List<UsuarioCFTIC> lista_usuarios;
	private static String RUTA_FICHERO_LISTA = "lista_usuarios.json";

	private final static Logger log = Logger.getLogger("mylog");

	public static void insertarUsuario(UsuarioCFTIC usuarioCFTIC) 
	{
		lista_usuarios.add(usuarioCFTIC);
		log.debug("usuario aniadido");
	}
	
	/**
	 * Método que comprueba que la credencial token sea del usuario nombre
	 * 
	 * @param nombre
	 * @param token
	 * @return true si sí, false en caso contrario
	 */
	public static boolean credencialesCorrectas(String nombre, String token) {

		boolean correctas = false;
		UsuarioCFTIC usuarioCFTICaux = null;
		int pos = 0;
		int tamanio_max = 0;
		
		tamanio_max = lista_usuarios.size();

		while (!correctas && (pos < tamanio_max)) {
			usuarioCFTICaux = lista_usuarios.get(pos);
			correctas = usuarioCFTICaux.getNombre().equals(nombre) && usuarioCFTICaux.getToken().equals(token); 
			pos = pos +1;
		}


		return correctas;
	}
	

	public static UsuarioCFTIC existeUsuario(UsuarioCFTIC usuarioCFTIC) {

		boolean existe = false;
		UsuarioCFTIC usuarioCFTICaux = null;
		int pos = 0;
		int tamanio_max = 0;
		
		tamanio_max = lista_usuarios.size();

		while (!existe && (pos < tamanio_max)) {
			usuarioCFTICaux = lista_usuarios.get(pos);
			existe = usuarioCFTICaux.equals(usuarioCFTIC);
			pos = pos +1;
		}

		if (!existe) {
			usuarioCFTICaux = null;
		}

		return usuarioCFTICaux;
	}
	
	public static boolean tokenValido(String token) {

		boolean valido = false;
		UsuarioCFTIC usuarioCFTICaux = null;
		int pos = 0;
		int tamanio_max = 0;
		
		tamanio_max = lista_usuarios.size();

		while (!valido && (pos < tamanio_max)) {
			usuarioCFTICaux = lista_usuarios.get(pos);
			valido = usuarioCFTICaux.getToken().equals(token);
			pos = pos +1;
		}



		return valido;
	}

	public static void guardarListaUsuarios(List<UsuarioCFTIC> lista_usuarios) {
		Gson gson = null;
		String lista_string = null;
		FileWriter fw = null;

		if (lista_usuarios.size() > 0) {
			gson = new Gson();
			Type tipo = new TypeToken<List<UsuarioCFTIC>>() {
			}.getType();
			lista_string = gson.toJson(lista_usuarios, tipo);

			try {
				fw = new FileWriter(new File(RUTA_FICHERO_LISTA));
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
	
	public static List<UsuarioCFTIC> obtenerListaUsuariosRegisgtrados()
	{
		return lista_usuarios;
	}

	public static List<UsuarioCFTIC> cargarListaUsuariosRegisgtrados() {

		FileReader fr = null;
		File fichero = null;
		BufferedReader br = null;
		String json_usuarios = null;
		Gson gson = null;
		Type type = null;

		try {

			fichero = new File(RUTA_FICHERO_LISTA);
			if (!fichero.exists()) {
				log.debug("Fichero no existe");
				if (fichero.createNewFile()) {
					log.debug("Fichero creado");
				}
				lista_usuarios = new ArrayList<UsuarioCFTIC>();
			} else {
				log.debug("Fichero ya existe");
				fr = new FileReader(fichero);
				br = new BufferedReader(fr);
				json_usuarios = br.readLine();

				if (json_usuarios == null || (json_usuarios.length() == 0)) {
					lista_usuarios = new ArrayList<UsuarioCFTIC>();
				} else {
					gson = new Gson();
					type = new TypeToken<List<UsuarioCFTIC>>() {
					}.getType();
					lista_usuarios = gson.fromJson(json_usuarios, type);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error al cargar la lista", e);
		
		} finally {
			
				if (br != null) 
					{
					try {
						br.close();
							
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		}

		return lista_usuarios;

	}

}
