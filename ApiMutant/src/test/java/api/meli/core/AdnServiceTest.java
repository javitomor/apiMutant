package api.meli.core;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import api.meli.core.entity.AdnEntity;
import api.meli.core.service.AdnService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/source/application_test.properties")
public class AdnServiceTest {

	@Autowired
	private AdnService service;

	public String getCadenaMutante() {
		JSONArray cadenaM1 = new JSONArray();
		cadenaM1.add("ATGCGA");
		cadenaM1.add("CAGTGC");
		cadenaM1.add("TTATGT");
		cadenaM1.add("AGAAGG");
		cadenaM1.add("CCCCTA");
		cadenaM1.add("TCACTG");
		JSONObject objM1 = new JSONObject();
		objM1.put("dna", cadenaM1);
		return objM1.toJSONString();
	}

	public String getCadenaHumano() {
		JSONArray cadenaH1 = new JSONArray();
		cadenaH1.add("TTGCCA");
		cadenaH1.add("CAGTGC");
		cadenaH1.add("TTATGT");
		cadenaH1.add("AGAAGG");
		cadenaH1.add("CACCTA");
		cadenaH1.add("TCACTG");
		JSONObject objH1 = new JSONObject();
		objH1.put("dna", cadenaH1);
		return objH1.toJSONString();
	}

	@Test
	public void existeAdnTest() {
		assertEquals(true, service.existeAdn(863293009));
		assertEquals(false, service.existeAdn(863293000));
	}

	@Test
	public void guardarTest() {
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj;
			jsonObj = (JSONObject) parser.parse(getCadenaMutante());
			JSONArray jsonAr = (JSONArray) jsonObj.get("dna");
			AdnEntity entidad = new AdnEntity(jsonAr.toJSONString(), true, jsonAr.toJSONString().hashCode());
			assertEquals(true, service.guardar(entidad));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj;
			jsonObj = (JSONObject) parser.parse(getCadenaHumano());
			JSONArray jsonAr = (JSONArray) jsonObj.get("dna");
			AdnEntity entidad = new AdnEntity(jsonAr.toJSONString(), false, jsonAr.toJSONString().hashCode());
			assertEquals(true, service.guardar(entidad));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void getCountMutantTest() {
		assertTrue(service.getCountPerson(true)>=0);
	}
	
	@Test
	public void getCountHumanTest() {
		assertTrue(service.getCountPerson(false)>=0);
	}

	public AdnServiceTest() {
	}

}
