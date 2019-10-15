package api.meli.core;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import api.meli.core.controller.MutantController;
import api.meli.core.service.AdnService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/source/application_test.properties")
//@Sql("/source/test.sql")
public class ApiMutantApplicationTests {

	@Test
	@Ignore
	public void failTest() {
		fail("No se implemento");
	}

}
