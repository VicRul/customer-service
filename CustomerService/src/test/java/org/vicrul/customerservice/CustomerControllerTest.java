package org.vicrul.customerservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.vicrul.customerservice.model.entity.Address;
import org.vicrul.customerservice.model.entity.Customer;

import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = "/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CustomerControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	void shouldCreateNewCustomers() throws Exception {
		Customer customer = new Customer();
        customer.setSex("male");
        customer.setActualAddress(new Address());
        customer.setRegistredAddress(new Address());
        
        String customerInJson = new Gson().toJson(customer);
        mockMvc
        	.perform(post("http://localhost:8080/api/customers/new")
        		.param("isSameAddresses", "false")
        		.contentType(MediaType.APPLICATION_JSON).content(customerInJson))
        	.andDo(print())
        	.andExpect(status().isCreated());
	}
	
	@Test
	void shouldNotCreateNewCustomers() throws Exception {
		Customer customer = new Customer();
        customer.setSex("maldsadas");
        customer.setActualAddress(new Address());
        customer.setRegistredAddress(new Address());
        
        String customerInJson = new Gson().toJson(customer);
        mockMvc
        	.perform(post("http://localhost:8080/api/customers/new")
        			.param("isSameAddresses", "false")
        			.contentType(MediaType.APPLICATION_JSON).content(customerInJson))
        	.andDo(print())
        	.andExpect(status().isBadRequest());
	}
	
	@Test
	void shouldFindCustomer() throws Exception {
		mockMvc
	    	.perform(get("http://localhost:8080/api/customers/find")
	    			.param("firstName", "Ivan")
	    			.param("lastName", "Ivanov"))
	    	.andDo(print())
	    	.andExpect(status().isOk());
	}
	
	@Test
	void shouldNotFindCustomer() throws Exception {
		mockMvc
    		.perform(get("http://localhost:8080/api/customers/find")
    				.param("firstName", "Ivanscs"))
    		.andDo(print())
    		.andExpect(status().isBadRequest());
	}
	
	@Test
	void shouldUpdateActualAddres() throws Exception {
		Address address = new Address();
		String addressInJson = new Gson().toJson(address);
		
		mockMvc
			.perform(put("http://localhost:8080/api/customers/update")
					.param("customerId", "95")
					.contentType(MediaType.APPLICATION_JSON).content(addressInJson))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	@Test
	void shouldNotUpdateActualAddres() throws Exception {
		
		mockMvc
			.perform(put("http://localhost:8080/api/customers/update")
					.param("customerId", "95"))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
}
