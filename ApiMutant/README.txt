Documentación de Uso de API para detectar cadena de ADN Mutante.

Los endpoint disponibles son los siguiente:
*******************************************************************************************************************************
| Determina si una cadena de adn corresponde a un mutante o a un humano y devuelve los estados ok y forbidden
| endpoint: "/mutant/"
| method: POST
| tipo parametros entrada:  json
|       formato json: { “dna”:["XXXXXX","XXXXXX","XXXXXX","XXXXXX","XXXXXX","XXXXXX"] } los caracteres "X" solo pueden 
|        ser (A,T,C,G) y cada fila puede ser de N caracteres de longitud y el objeto "dna" debe contener N filas en total.
| parametros de salida: HTTP 200-OK si detecto que la cadena es de un Mutante.
|                       HTTP 403-FORBIDDEN si detecto que la cadena es de un Humano.
*******************************************************************************************************************************

*******************************************************************************************************************************
| Devuelve las estadisticas de las lecturas de las cadenas de adn que se le han enviado para procesar
| endopoint:"/mutant/stats"
| method: GET
| tipo parametros entrada:  (ninguno)
| parametros de salida: json
|      formato json:   {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
|                      count_mutant_dna: cantidad de Mutantes detectados.
|                      count_human_dna: cantidad de Humanos detectados.
|                      ratio: ratio de Mutantes detectados respecto de Humanos.
*******************************************************************************************************************************

*******************************************************************************************************************************
| Muestra el estado de salud del servidor
| endpoint: "/mutant/status"
| method: GET
| tipo paramentros entrada: (ninguno)
| parametros de salida: HTTP 200-OK indicando si el servidor esta en servicio o no
*******************************************************************************************************************************

*******************************************************************************************************************************
| Muestra información de contacto del postulante
| endpoint: "/mutant/info"
| endpoint: "/mutant/info"
| method: GET
| tipo parametros entrada: (ninguno)
| paramentros de salida: json
          formato json: {"senioriry":"Jr","lenguaje":"Java","apellido":"Moreno","correo":"javiermoreno1986@gmail.com",
                        "telefono":"2616631427","nombre":"Javier"}
                        senioriry: nivel de seniority del postulante.
                        lenguaje: lenguaje de programación para el que se postulo.
                        apellido: apellido del postulante.
                        correo: correo electronico del postulante.
                        telefono: telefono de contacto del postulante.
                        nombre: nombre del postulante.
*******************************************************************************************************************************
