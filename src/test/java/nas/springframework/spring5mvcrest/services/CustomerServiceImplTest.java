/*
package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTOList;
import nas.springframework.spring5mvcrest.controllers.v1.CustomerController;
import nas.springframework.spring5mvcrest.domain.Customer;
import nas.springframework.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {


    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    public void getAllCustomers() throws Exception {

        //given
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Michael");
        customer1.setLastname("Weston");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Sam");
        customer2.setLastname("Axe");

        customers.add(customer1);
        customers.add(customer2);

        //when
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        //then
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(customerDTOS.size(), 2);

    }

    @Test
    void getCustomerById() throws Exception {
        //given
        //setting a CustomerDTO
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstname("Sara");
        customer.setLastname("Poly");


        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));
        //when
        CustomerDTO resultCustomerDTO = customerService.getCustomerById(1L);

        //then
        assertEquals(resultCustomerDTO.getFirstname(), "Sara");
        //I added this test by myself too
        assertNotNull(resultCustomerDTO);

    }

    @Test
    void createNewCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("John");
        customerDTO.setLastname("Thompson");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        //I added this line, not tutor
        assertNotNull(savedDto);
        //here we are checking from //when
        assertEquals(savedDto.getFirstname(), customerDTO.getFirstname());
        assertEquals(savedDto.getCustomerUrl(),  CustomerController.BASE_URL + "/1");
    }
    @Test
    public void saveCustomerByDTO() throws Exception{
        //given
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setFirstname("Sara");
        customerDTO.setLastname("Poly");

        //Customer
        Customer savedCustomer=new Customer();
        savedCustomer.setId(1l);
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO returnedCustomerDTO =customerService.saveCustomerByDTO(1l,customerDTO);
        assertEquals(returnedCustomerDTO.getLastname(),"Poly");
        assertEquals(returnedCustomerDTO.getCustomerUrl(), CustomerController.BASE_URL +"/1");
    }
    @Test
    void deleteCustomer(){
        Long id =1l;

        customerRepository.deleteById(id);

        verify(customerRepository,times(1)).deleteById(anyLong()) ;
    }
}*/