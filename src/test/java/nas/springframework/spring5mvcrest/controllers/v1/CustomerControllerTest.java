package nas.springframework.spring5mvcrest.controllers.v1;

import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import nas.springframework.spring5mvcrest.domain.Customer;
import nas.springframework.spring5mvcrest.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//we can use below @ or "MockitoAnnotations.openMocks(this)" in SetUp, they are equal ,so I use this ann
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getListOfCustomers() throws Exception {
        //given
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setId(1L);
        customerDTO1.setFirstname("Michael");
        customerDTO1.setLastname("Weston");
        customerDTO1.setCustomerUrl("/api/v1/customers/1");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(2L);
        customerDTO2.setFirstname("Sam");
        customerDTO2.setLastname("Axe");

        customerDTOS.add(customerDTO1);
        customerDTOS.add(customerDTO2);
        customerDTO2.setCustomerUrl("/api/v1/customers/2");

        //when
        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        //then
        mockMvc.perform(get("/api/v1/customers/").contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerById() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstname("Sara");
        customerDTO.setLastname("Poly");
        customerDTO.setCustomerUrl("/api/v1/customers/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        //when
        mockMvc.perform(get("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Sara")));
    }
}