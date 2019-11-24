package edu.val.cfticionic.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.binary.Base32;

public class CodeControlUtilCFTIC {
	
	
	private static final int TAMANIO_CODE_CONFIRMACIONC_CLIENTE = 12;
	
	/**
	 * método empleado para obtener 6 posiciones aleatorias basadas en el tiempo del sistema
	 * 
	 * @return un array de números cuyas posiciones se corresponden con los decimales menos signiticativos de la hora del sistema actual
	 */
	private static int [] obtenerArrayIndicesAleatorio ()
	{
		int [] cola_numeros = null;
		
			long ms = System.currentTimeMillis();
			cola_numeros = new int [TAMANIO_CODE_CONFIRMACIONC_CLIENTE];
			String cola_n = ms+"";
			cola_n = cola_n.substring(cola_n.length()-TAMANIO_CODE_CONFIRMACIONC_CLIENTE, cola_n.length());
			for (int index = cola_n.length(), j = 0; index >=1; index--, j++)
			{
				cola_numeros[j]=Integer.parseInt(cola_n.charAt(index-1)+"");
			}
			
		return cola_numeros;
	}

	private static char obtenerLetraAleatoriaAZ ()
	{
		char letra = ' ';
		
			int numAleatorio=(int)Math.floor(Math.random()*(65-90)+90);
			letra = (char)numAleatorio;
			System.out.println("Letra = "+ letra);
		
		return letra;
	}
	
	private static String limparCadenaIguales (String cad_in)
	{
		String cad_fin = null;
		char char_aux, char_en_curso = ' ';
		int longi = 0;
		
			longi = cad_in.length();
			cad_fin = "";
			for (int i = 0; i < longi; i++)
			{
				char_en_curso = cad_in.charAt(i);
				char_aux = (char_en_curso == '=')? obtenerLetraAleatoriaAZ() : char_en_curso;
				cad_fin = cad_fin + char_aux;
			}
		
		return cad_fin;
	}
	
	/**
	 * Partimos de un código en base 32 inicial y seleccionamos de eĺ sólo las posiciones indicadas por un array de índices
	 * de modo que sólo esa selección, constituye una nueva colección devuelta
	 * 
	 * @param cod_parcial el código inicial
	 * @param indices los índices a seleccionar del código inicial
	 * @return el string resultante
	 */
	private static String generarCodigoFromIndicesYCodInicial (String cod_parcial, int [] indices )
	{
		String cod_final = "";
		char charaux = ' ';
		
			for (int i : indices)
			{
				charaux = cod_parcial.charAt(i);
				cod_final = cod_final + charaux;
			}
			
		return cod_final;
	}
	
	private static String crearAnagrama (String entrada) {
		String salida = null;
		
			List<Character> characters = new ArrayList<Character>();
	        for(char c:entrada.toCharArray()){
	            characters.add(c);
	        }
	        StringBuilder output = new StringBuilder(entrada.length());
	        while(characters.size()!=0){
	            int randPicker = (int)(Math.random()*characters.size());
	            output.append(characters.remove(randPicker));
	        }
	        
	        salida = output.toString();
	        
	        
		return salida;
	}
	/**
	 * 
	 * Obtiene un String aleaotrio partiendo del nombre o semilla
	 * El código obtenido se usará como control, para enviarselo al suuario
	 * y que confirme su registro
	 * 
	 * @param semilla String de entrada
	 * @return código de 6 posiciones en base 32 generado de forma aleatoria a partir de la entrada
	 * @throws UnsupportedEncodingException si UTF-8 no se soporta
	 */
	public static String obtenerCodigoAutenticacion (String semilla) throws UnsupportedEncodingException
	{
		String codigo_resultado32_parcial = null;
		String codigo_resultado32_final = null;
		int [] cola_numeros = null;
		
			semilla = crearAnagrama(semilla);
			Base32 base32 = new Base32();
			codigo_resultado32_parcial = base32.encodeAsString(semilla.getBytes("UTF-8"));
			//calculo índices
			cola_numeros = obtenerArrayIndicesAleatorio();
			//selecciono chars
			codigo_resultado32_final = generarCodigoFromIndicesYCodInicial (codigo_resultado32_parcial, cola_numeros);
			//es posible que se cuele algún símbolo de igual se sustitutye por letras A-Z aleatorias
			codigo_resultado32_final = limparCadenaIguales(codigo_resultado32_final);
			
		
		return codigo_resultado32_final;
	}

}
