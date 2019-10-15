package api.meli.core;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import api.meli.core.entity.AdnEntity;

public class AdnEntityTest {

	@Test
	public void crearEntidadTest() {
		
		JSONArray jsonArr = new JSONArray();
		jsonArr.add("ATGCGA");
		jsonArr.add("CAGTCC");
		jsonArr.add("AGCAGG");
		jsonArr.add("TTATGT");
		jsonArr.add("CTCCTA");
		jsonArr.add("TCACTG");
		
		JSONObject jsonOb = new JSONObject();
		jsonOb.put("dna", jsonArr);
		
		AdnEntity entidad = new AdnEntity();
		
		entidad.setId(1L);
		entidad.setCadena(jsonArr.toJSONString());
		entidad.setHashCode(jsonArr.toJSONString().hashCode());
		entidad.setMutant(false);
		
		assertEquals(1L,entidad.getId());
		assertEquals(String.class,entidad.getCadena().getClass());
		assertEquals(jsonArr.toJSONString().hashCode(), entidad.getHashCode());
		assertEquals(false,entidad.isMutant());
		
	}

}
