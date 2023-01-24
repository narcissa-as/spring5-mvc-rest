package nas.springframework.spring5mvcrest.api.v1.mapper;

import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import nas.springframework.spring5mvcrest.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerMapperTest {

    static final String FIRST_NAME = "JOE";
    static final String LAST_NAME = "HARRY";
    static final Long ID=1L;

    CustomerMapper customerMapper=CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() throws Exception {
        //given
        Customer customer=new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRST_NAME);
        customer.setLastname(LAST_NAME);

        CustomerDTO customerDTO =customerMapper.customerToCustomerDTO(customer);
        assertEquals(customerDTO.getLastname(),LAST_NAME);
        assertEquals(customerDTO.getFirstname(),FIRST_NAME);

    }
}