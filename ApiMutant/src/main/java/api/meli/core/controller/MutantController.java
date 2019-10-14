package api.meli.core.controller;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.meli.core.entity.AdnEntity;
import api.meli.core.service.AdnService;

@RestController
@RequestMapping("/mutant")
public class MutantController {

	AdnService service = new AdnService();

	@GetMapping("/status")
	public HttpStatus checkStatus() {
		return HttpStatus.OK;
	 }
	
	@GetMapping("/info")
	public String info() {
		JSONObject contacto = new JSONObject();
		contacto.put("nombre", "Javier");
		contacto.put("apellido", "Moreno");
		contacto.put("telefono", "2616631427");
		contacto.put("correo", "javiermoreno1986@gmail.com");
		contacto.put("lenguaje", "Java");
		contacto.put("senioriry","Jr");
		return contacto.toJSONString();
	}

	@PostMapping("/")
	public HttpStatus isMutant(@RequestBody String dna) {

		JSONParser parser = new JSONParser();
		char[][] matrizADN = null;
		boolean mutante = false;
		String cadena = "";
		int hashCode = 0;

		try {
			JSONObject jsonObj = (JSONObject) parser.parse(dna);
			JSONArray jsonAr = (JSONArray) jsonObj.get("dna");
			Iterator<String> iterator = jsonAr.iterator();
			hashCode = jsonAr.toJSONString().hashCode();
			int fila = 0;

			String linea = "";
			boolean matrizInicializada = false;
			while (iterator.hasNext()) {

				linea = iterator.next();

				if (!matrizInicializada) {
					matrizADN = new char[linea.length()][linea.length()];
					matrizInicializada = true;
				}

				matrizADN = agregarFilaMatriz(matrizADN, linea, fila);

				// verifica linea horizontal
				if (verificarHorizontal(matrizADN[fila])) {
					mutante = true;
				}
				fila++;
			}
			// Verifica vertical
			if (verificarVertical(matrizADN)) {
				mutante = true;
			}

			// Verifica oblicua para ambos lados
			if (verificarOblicua(matrizADN)) {
				mutante = true;
			}

			try {
				if (service.guardar(new AdnEntity(jsonAr.toString(), mutante, hashCode))) {
					System.out.println("La cadena no estaba en la base de datos");
				} else {
					System.out.println("La cadena estaba en la base de datos");
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}

		} catch (ParseException e) {
			System.out.println("Error en el JSON recibido");
			e.printStackTrace();
		}
		return mutante ? HttpStatus.OK : HttpStatus.FORBIDDEN;

	}

	@GetMapping("/stats")
	public String getEstadisticas() {
		int countMutant = 0;
		int countHuman = 0;

		try {
			countMutant = service.getCountPerson(true);
		} catch (NullPointerException e) {
			countMutant = 0;
		}
		try {
			countHuman = service.getCountPerson(false);
		} catch (NullPointerException e) {
			countHuman = 0;
		}
		float ratio = countHuman == 0 ? 1 : countMutant / countHuman;

		JSONObject json = new JSONObject();

		json.put("count_mutant_dna", countMutant);
		json.put("count_human_dna", countHuman);
		json.put("ratio", ratio);

		return json.toJSONString();
	}

	public boolean verificarHorizontal(char[] linea) {
		for (int i = 0; (linea.length - i) >= 4; i++) {
			if (linea[i] == linea[i + 1]) {
				if (linea[i + 1] == linea[i + 2]) {
					if (linea[i + 2] == linea[i + 3]) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean verificarOblicua(char[][] matriz) {

		if (verificarOblicuaIzquierdaDerecha(matriz) || verificarOblicuaDerechaIzquierda(matriz))
			return true;
		return false;

	}

	public boolean verificarOblicuaIzquierdaDerecha(char[][] matriz) {
		for (int i = 0; (matriz.length - i) >= 4; i++) {
			for (int j = 0; (matriz.length - j) >= 4; j++) {
				if (matriz[i][j] == matriz[i + 1][j + 1]) {
					if (matriz[i + 1][j + 1] == matriz[i + 2][j + 2]) {
						if (matriz[i + 2][j + 2] == matriz[i + 3][j + 3]) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean verificarOblicuaDerechaIzquierda(char[][] matriz) {
		for (int j = 0; (matriz.length - j) >= 4; j++) {
			for (int i = (matriz.length - 1); i >= 4; i--) {
				if (matriz[j][i] == matriz[j + 1][i - 1]) {
					if (matriz[j + 1][i - 1] == matriz[j + 2][i - 2]) {
						if (matriz[j + 2][i - 2] == matriz[j + 3][i - 3]) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean verificarVertical(char[][] matriz) {
		for (int i = 0; (matriz.length) > i; i++) {
			for (int j = 0; (matriz.length - j) >= 4; j++) {
				if (matriz[j][i] == matriz[j + 1][i]) {
					if (matriz[j + 1][i] == matriz[j + 2][i]) {
						if (matriz[j + 2][i] == matriz[j + 3][i]) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public char[][] agregarFilaMatriz(char[][] matriz, String linea, int fila) {

		for (int columna = 0; columna < linea.length(); columna++) {
			char caracter = linea.charAt(columna);
			matriz[fila][columna] = caracter;
		}
		return matriz;
	}

}
