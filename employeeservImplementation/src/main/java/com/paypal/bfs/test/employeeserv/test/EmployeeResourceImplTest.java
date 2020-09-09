package com.paypal.bfs.test.employeeserv.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmployeeservApplication.class)
@SpringBootTest
public class EmployeeResourceImplTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyEmployeeGetById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.first_name").exists())
				.andExpect(jsonPath("$.last_name").exists())
				.andExpect(jsonPath("$.date_of_birth").exists())
				.andExpect(jsonPath("$.address.line_1").exists())
				.andExpect(jsonPath("$.address.city").exists())
				.andExpect(jsonPath("$.address.state").exists())
				.andExpect(jsonPath("$.address.country").exists())
				.andExpect(jsonPath("$.address.zip_code").exists())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.first_name").value("BFS"))
				.andExpect(jsonPath("$.last_name").value("Developer"))
				.andExpect(jsonPath("$.date_of_birth").value("01/01/2020"))
				.andExpect(jsonPath("$.address.line_1").value("line1"))
				.andExpect(jsonPath("$.address.city").value("City"))
				.andExpect(jsonPath("$.address.state").value("State"))
				.andExpect(jsonPath("$.address.country").value("Country"))
				.andExpect(jsonPath("$.address.zip_code").value("12345"))
				.andDo(print());
	}
	
	@Test
	public void verifyEmployeeCreate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/create").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\" : \"1\",\"first_name\":\"Sushmitha\",\"last_name\":\"Reddy\",\"date_of_birth\":\"21/01/2020\",\"address\":{\"line_1\" : \"line1\",\"city\":\"City\",\"state\":\"State\",\"country\":\"Country\",\"zip_code\":\"12345\"}}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.first_name").exists())
				.andExpect(jsonPath("$.last_name").exists())
				.andExpect(jsonPath("$.date_of_birth").exists())
				.andExpect(jsonPath("$.address.line_1").exists())
				.andExpect(jsonPath("$.address.city").exists())
				.andExpect(jsonPath("$.address.state").exists())
				.andExpect(jsonPath("$.address.country").exists())
				.andExpect(jsonPath("$.address.zip_code").exists())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.first_name").value("Sushmitha"))
				.andExpect(jsonPath("$.last_name").value("Reddy"))
				.andExpect(jsonPath("$.date_of_birth").value("21/01/2020"))
				.andExpect(jsonPath("$.address.line_1").value("line1"))
				.andExpect(jsonPath("$.address.city").value("City"))
				.andExpect(jsonPath("$.address.state").value("State"))
				.andExpect(jsonPath("$.address.country").value("Country"))
				.andExpect(jsonPath("$.address.zip_code").value("12345"))
				.andDo(print());
	}
	
	@Test
	public void verifyEmployeeRetrieve() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/create").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\" : \"1\",\"first_name\":\"Sushmitha\",\"last_name\":\"Reddy\",\"date_of_birth\":\"21/01/2020\",\"address\":{\"line_1\" : \"line1\",\"city\":\"City\",\"state\":\"State\",\"country\":\"Country\",\"zip_code\":\"12345\"}}")
				.accept(MediaType.APPLICATION_JSON));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/create").contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\" : \"2\",\"first_name\":\"Sushmitha\",\"last_name\":\"Reddy\",\"date_of_birth\":\"21/01/2020\",\"address\":{\"line_1\" : \"line1\",\"city\":\"City\",\"state\":\"State\",\"country\":\"Country\",\"zip_code\":\"12345\"}}")
				.accept(MediaType.APPLICATION_JSON));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/retrieve").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$", hasSize(2))).andDo(print())
				.andDo(print());
	}

	@Test
	public void verifyControllerHealthCheck() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/healthCheck/Sushmitha").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").value("Hi Sushmitha, Service is up!"))
				.andDo(print());
	}

}
