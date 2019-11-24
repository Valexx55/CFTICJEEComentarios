//FICHEROS CON CONSTANTES Y FUNCIONES USADAS POR EL RESTO DE JS
//VAMOS A USAR UN JS POR CADA HTML , COMO MEDIDA PARA ESTRUCTURAR EL CÓDIGO

const EXPRESION_REGULAR_USUARIO_MAIL = /^\w{6, }$/; //El mail y el usuario deben tener al menos 6 caracteres formado por letras, números o guiones bajos
const EXPRESION_REGULAR_EMAIL = /^\w+([\.\+\-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/; //El mail y el usuario deben tener al menos 6 caracteres formado por letras, números o guiones bajos
const EXPRESION_REGULAR_ANUNCIO = /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/; //El mail y el usuario deben tener al menos 6 caracteres formado por letras, números o guiones bajos
const EXPRESION_REGULAR_TELEFONO = /^\+\d{7,15}$/; 
const EXPRESION_REGULAR_PWD = /^\w{6,15}$/; 

//función general que recibirá todas las peticiones de repuesta
function respuesta (response)
{
	var objetoJson = response;
	
	var lista_action = response.accion;
	var funcion_accion = null;
	var i = 0;
	
	for (i=0;i<lista_action.length;i++)
	{
		funcion_accion= window[lista_action[i].accion];//y esto tb
		funcion_accion.call (window, lista_action[i].parametros);//se prefiere por no usar eval, ya que lo de eval se interpreta cada vez
		
    }
}

function urlProperties (url)
{
	var lengua = navigator.language;
	//return url+'?idioma='+lengua;
	return url+'?idioma=en';
}

function informarErroresLogin (mensaje)
{
	
	$("p").remove(); //economía si va en un if y en else, la pongo siempre
	var myNewElement = $("<p>").html (mensaje);
	myNewElement.insertAfter($("#boton_ir"));

}

function informarOkOlvido (mensaje)
{
	
	$("p").remove();
	var myNewElement = $("<p>").html (mensaje);
	myNewElement.css('color', '#6E6E6E');
	myNewElement.insertAfter($("#boton_ir"));

}

function informarErroresOlvido (mensaje)
{
	$("p").remove();
	var myNewElement = $("<p>").html (mensaje);
	myNewElement.insertAfter($("#boton_ir"));

}

function redirigir (pagina)
{
	location='../html/'+pagina;	
}
