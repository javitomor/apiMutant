package api.meli.core;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import api.meli.core.controller.MutantController;
import api.meli.core.service.AdnService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/source/application_test.properties")
public class MutantControllerTest {

	@Autowired
	AdnService service;

	MutantController controller = new MutantController();
	
	private char[] lineaHumano = {'C','C','C','G','T','A'};
	private char[] lineaMutante = {'C','C','C','C','T','A'};
	
	private char[][] matrizTodo={
			{'A','T','G','C','G','A'},
			{'C','A','G','T','G','C'},
			{'T','T','A','T','G','T'},
			{'A','G','A','A','G','G'},
			{'C','C','C','C','T','A'},
			{'T','C','A','C','T','G'}
			};
	private char[][] matrizOblicuaDerechaIzquierda =
		{
				{'A','T','G','C','G','A'},
				{'C','G','T','G','A','C'},
				{'T','G','T','A','T','T'},
				{'G','G','A','A','G','A'},
				{'A','T','C','C','T','A'},
				{'G','T','C','A','C','T'}
				};
	
	@Test
	public void verificarHorizontalTrueTest() {
		
		boolean res = controller.verificarHorizontal(lineaMutante);
		assertEquals(true, res);
	}
	
	@Test
	public void verificarHorizontalFalseTest() { 
		boolean res = controller.verificarHorizontal(lineaHumano);
		assertEquals(false, res);	
	}
	
	@Test
	public void verificarOblicuaTrueTest() {
		
		boolean resTodo = controller.verificarOblicua(matrizTodo);
		boolean resDerIz = controller.verificarOblicuaDerechaIzquierda(matrizOblicuaDerechaIzquierda);
		boolean resIzDer = controller.verificarOblicuaIzquierdaDerecha(matrizTodo);
		assertEquals(true, resTodo);
		assertEquals(true, resDerIz);
		assertEquals(true, resIzDer);
	}
	
	@Test
	public void verificarOblicuoFalseTest() {
		boolean resDerIz = controller.verificarOblicuaDerechaIzquierda(matrizTodo);
		boolean resIzDer = controller.verificarOblicuaIzquierdaDerecha(matrizOblicuaDerechaIzquierda);
		assertEquals(false, resDerIz);
		assertEquals(false, resIzDer);	
	}

	@Test
	public void agregarFilaMatrizTest() {
		String linea = "CAGTGC";
		char matriz[][]=new char[linea.length()][linea.length()];
		int fila = 0;
		matriz = controller.agregarFilaMatriz(matriz, linea, fila);
		assertEquals(linea,new String(matriz[fila]));
	}
	
	@Test
	public void getEstadisticasTest() {
		
		int countMutant = service.getCountPerson(true);
		int countHuman = service.getCountPerson(false);
		float ratio = countHuman == 0 ? 1 : countMutant / countHuman;

		JSONObject json = new JSONObject();

		json.put("count_mutant_dna", countMutant);
		json.put("count_human_dna", countHuman);
		json.put("ratio", ratio);

		assertTrue(json.toJSONString()==controller.getEstadisticas());
	}
	

}
