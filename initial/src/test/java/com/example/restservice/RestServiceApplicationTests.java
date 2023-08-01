package com.example.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
class RestServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeManager employeeManager;
	@Autowired
	private ObjectMapper objectMapper;

	private final String uri = "http://localhost:8080/employees";

	@Test
	void canGetAllEmployees() throws Exception {

		Employee employee1 = new Employee(
				1L,
				"Robert",
				"Downey Jr",
				"robertdowneyjr@hpe.com",
				"Software Engineer"
		);
		Employee employee2 = new Employee(
				2L,
				"Emily",
				"Blunt",
				"emilyblunt@hpe.com",
				"Software Engineer"
		);

		List<Employee> employeeList = Arrays.asList(employee1, employee2);

		Mockito.when(employeeManager.getEmployeeData()).thenReturn(employeeList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri);

		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].email", is("robertdowneyjr@hpe.com")))
				.andExpect(jsonPath("$[1].firstName", is("Emily")));

	}

	@Test
	void canAddEmployee() throws Exception {

		Employee robert = new Employee(
				1L,
				"Robert",
				"Downey Jr",
				"robertdowneyjr@hpe.com",
				"Software Engineer"
		);

		Mockito.when(employeeManager.addEmployee(robert)).thenReturn("success");

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(robert));

		mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());

	}

}
