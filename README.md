CAPA DE SERVICIOS PARA EJERICIO PRÁCTICO CURSO JS AVANZADO E IONIC
"COMENTARIOS DE CINE"

----------------------------------------------------------
SERVICIO 1 LOGIN
----------------------------------------------------------

POST http://10.1.2.10:8081/cfticionic/usuariocftic
CUERPO {"nombre":"alumno10", "pwd":"alumno10"}

RESPUESTA
200

{"nombre":"alumno16","pwd":"alumno16","token":"CQOYQYTQ3CP3"}

Otros códigos de respuesta:

400 Bad Request (errores de validación, petición incorrecta)
500 Internal Server Error (algún problema en la generación del usuario)

----------------------------------------------------------
SERVICIO 2 OBTENER TODAS LAS FOTOS
----------------------------------------------------------

GET http://10.1.2.10:8081/cfticionic/fotos?key=CQOYQYTQ3CP3

RESPUESTA
200 OK

[{"idfoto":0,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/adios.jpg"},{"idfoto":1,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/dinero.jpg"},{"idfoto":2,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/frozen.jpg"},{"idfoto":3,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/gigolo.jpg"},{"idfoto":4,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/joker.jpg"},{"idfoto":5,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/ladronas.jpg"},{"idfoto":6,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/lluvia.jpg"},{"idfoto":7,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/malefica.jpg"},{"idfoto":8,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/rico.jpg"},{"idfoto":9,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/terminator.jpg"}]

Otros códigos de respuesta:
403 Prohibido (clave ausente o incorrecta)


----------------------------------------------------------
SERVICIO 3 OBTENER INFORMACIÓN DE UNA FOTO
----------------------------------------------------------

GET http://10.1.2.10:8081/cfticionic/foto?key=CQOYQYTQ3CP3&idfoto=3

RESPUESTA
200 OK
{"idfoto":3,"ruta":"http://10.1.2.10:8081/cfticionic/fotos/frozen.jpg"}

Otros códigos de respuesta:

400 BAD Request (el id de la foto está fuera de rango)
403 Prohibido (La credencial key no es válida)


----------------------------------------------------------
SERVICIO 4 PUBLICAR UN COMENTARIO 
(comentarios entre 2 y 400 caracteres)
----------------------------------------------------------

POST http://10.1.2.10:8081/cfticionic/comentario
BODY { "nombre": "alumno10", "texto": "la verdad, me ha gustado aunque el final un poco feo", "token": "CQOYQYTQ3CP3", "idfoto": 5}

RESPUESTA
201 CREADO

{"nombre":"alumno10","texto":"la verdad, me ha gustado aunque el final un poco feo","idcomentario":66,"fecha":1574598838203}


Otros códigos de respuesta:

400 BAD Request (falta algún parámetro o es incorrecto/fuera de rango)
403 Prohibido (credenciales incorrectas)

----------------------------------------------------------
SERVICIO 5 OBTENER LOS COMENTARIOS DE UNA FOTO
----------------------------------------------------------

GET http://10.1.2.10:8081/cfticionic/comentarios/foto?key=CQOYQYTQ3CP3&idfoto=5

RESPUESTA
200 OK

[{"id":66,"autor":"alumno10","idfoto":5,"momento":1574598838203,"texto":"la verdad, me ha gustado aunque el final un poco feo"},{"id":67,"autor":"alumno10","idfoto":5,"momento":1574596744100,"texto":"a la dirección artística, pá matarla"}]

Otros códigos de respuesta:

204 Sin Contenido (La foto solicitada no tiene ningún comentario asociado)
400 BAD Request (falta algún parámetro)
403 Prohibido (La credencial key no es válida)

----------------------------------------------------------
SERVICIO 6 MODIFICAR UN COMENTARIO (propio)
----------------------------------------------------------

Modificar un comentario (sólo pueden modicarse comentarios del propio autor)

PUT http://10.1.2.10:8081/cfticionic/comentario
BODY { "nombre": "alumno10", "texto": "Si lo sé no vengo. Dinero mal invertido", "token": "CQOYQYTQ3CP3", "idfoto": 5,"idcomentario": 66}

RESPUESTA
201 CREADO
{"nombre":"alumno10","texto":"Si lo sé no vengo. Dinero mal invertido","idcomentario":66,"fecha":1574597928396}

Otros códigos de respuesta:

403 Prohibido (La credencial key no es válida)

----------------------------------------------------------
SERVICIO 7 ELIMINAR UN COMENTARIO (propio)
----------------------------------------------------------

DELETE http://10.1.2.10:8081/cfticionic/comentario?key=CQOYQYTQ3CP3&idcomentario=66&nombre=alumno10

RESPUESTA
200 OK (Se ha eliminado el contenido)

Otros códigos de respuesta:

400 Petición incorrecta (fallo en la llamada)
403 Prohibido (La credencial key no es válida o el nombre no es el autor)





