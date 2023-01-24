package nas.springframework.spring5mvcrest.controllers.v1;

import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import nas.springframework.spring5mvcrest.domain.Customer;
import nas.springframework.spring5mvcrest.services.CustomerService;
import nas.springframework.spring5mvcrest.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//we can use below @ or "MockitoAnnotations.openMocks(this)" in SetUp, they are equal ,so I use this ann
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest extends AbstractRestControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
        //we have a class as a ControllerAdvice that we used it in CostumerServiceImpl, since here we just have a
        //CustomerService mock (and not a real Bean), we need to new ControllerAdvice and inject it in our ServiceImpl
        // class to be able to have it
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)

                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getListOfCustomers() throws Exception {
        //given
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstname("Michael");
        customerDTO1.setLastname("Weston");
        customerDTO1.setCustomerUrl(CustomerController.BASE_URL + "/1");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstname("Sam");
        customerDTO2.setLastname("Axe");

        customerDTOS.add(customerDTO1);
        customerDTOS.add(customerDTO2);
        customerDTO2.setCustomerUrl(CustomerController.BASE_URL + "/2");

        //when
        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        //then
        mockMvc.perform(get(CustomerController.BASE_URL + "/").contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void getCustomerById() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Sara");
        customerDTO.setLastname("Poly");
        customerDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        //when
        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Sara")));
    }

    @Test
    void createNewCustomer() throws Exception {
        //setting a customerDto as a parameter of method
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Sara");
        customerDTO.setLastname("Poly");

        //setting a customer as return of method
        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customerDTO.getFirstname());
        returnedDTO.setLastname(customerDTO.getLastname());
        returnedDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnedDTO);

        //then
        mockMvc.perform(post(CustomerController.BASE_URL + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))//we defined this method in AbstractRestControllerTest
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")))
                .andExpect(jsonPath("$.firstname", equalTo("Sara")));

    }

    @Test
    public void updateCustomer() throws Exception {

        //CustomerDTO to update
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Sara");
        customerDTO.setLastname("Poly");

        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customerDTO.getFirstname());
        returnedDTO.setLastname(customerDTO.getLastname());
        returnedDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnedDTO);

        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Sara")))
                .andExpect(jsonPath("$.lastname", equalTo("Poly")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    public void PatchCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Sara");


        CustomerDTO returnedDTO = new CustomerDTO();
        returnedDTO.setFirstname(customerDTO.getFirstname());
        returnedDTO.setLastname("Poly");
        returnedDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnedDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Sara")))
                .andExpect(jsonPath("$.lastname", equalTo("Poly")))
                .andExpect(jsonPath("$.customer_url", equalTo(CustomerController.BASE_URL + "/1")));

    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());

    }

    @Test
    public void ExceptionNotFound() throws Exception {
        //we defined 2 customers so far, so we don't have any customer with id:3, and we know we'll get "not found exception" in this way

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/222")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }


}