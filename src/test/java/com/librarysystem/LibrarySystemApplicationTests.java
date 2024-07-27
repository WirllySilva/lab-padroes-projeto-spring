package com.librarysystem;

import com.librarysystem.model.Address;
import com.librarysystem.model.Customer;
import com.librarysystem.repository.CustomerRepository;
import com.librarysystem.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class LibrarySystemApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	private Customer customer;
	private Address address;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		address = new Address();
		address.setId(1L);
		address.setStreet("123 Main St");
		address.setCity("Anytown");
		address.setState("Anystate");
		address.setZipCode("12345");

		customer = new Customer();
		customer.setId(1L);
		customer.setName("John Doe");
		customer.setEmail("john.doe@example.com");
		customer.setAddress(address);
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customer));

		mockMvc.perform(get("/api/customers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("John Doe"))
				.andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
				.andExpect(jsonPath("$[0].address.street").value("123 Main St"));
	}

	@Test
	public void testGetCustomerById() throws Exception {
		when(customerService.getCustomerById(anyLong())).thenReturn(customer);

		mockMvc.perform(get("/api/customers/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("John Doe"))
				.andExpect(jsonPath("$.email").value("john.doe@example.com"))
				.andExpect(jsonPath("$.address.street").value("123 Main St"));
	}

	@Test
	public void testCreateCustomer() throws Exception {
		when(customerService.saveCustomer(any(Customer.class))).thenReturn(customer);

		String customerJson = "{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\", \"address\": {\"street\": \"123 Main St\", \"city\": \"Anytown\", \"state\": \"Anystate\", \"zipCode\": \"12345\"}}";

		mockMvc.perform(post("/api/customers")
						.contentType(MediaType.APPLICATION_JSON)
						.content(customerJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("John Doe"))
				.andExpect(jsonPath("$.email").value("john.doe@example.com"))
				.andExpect(jsonPath("$.address.street").value("123 Main St"));
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		// Configurar o mock para atualizar o objeto Customer
		Customer updatedCustomer = new Customer();
		updatedCustomer.setId(1L);
		updatedCustomer.setName("John Doe Updated");
		updatedCustomer.setEmail("john.doe.updated@example.com");
		Address updatedAddress = new Address();
		updatedAddress.setId(1L);
		updatedAddress.setStreet("123 Main St Updated");
		updatedAddress.setCity("Anytown");
		updatedAddress.setState("Anystate");
		updatedAddress.setZipCode("54321");
		updatedCustomer.setAddress(updatedAddress);

		when(customerService.saveCustomer(any(Customer.class))).thenReturn(updatedCustomer);

		String customerJson = "{\"name\": \"John Doe Updated\", \"email\": \"john.doe.updated@example.com\", \"address\": {\"street\": \"123 Main St Updated\", \"city\": \"Anytown\", \"state\": \"Anystate\", \"zipCode\": \"54321\"}}";

		mockMvc.perform(put("/api/customers/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(customerJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("John Doe Updated"))
				.andExpect(jsonPath("$.email").value("john.doe.updated@example.com"))
				.andExpect(jsonPath("$.address.street").value("123 Main St Updated"));
	}


	@Test
	public void testDeleteCustomer() throws Exception {
		doNothing().when(customerService).deleteCustomer(anyLong());

		mockMvc.perform(delete("/api/customers/1"))
				.andExpect(status().isOk());

		verify(customerService, times(1)).deleteCustomer(anyLong());
	}
}
