package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import nas.springframework.spring5mvcrest.domain.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    //update
    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

}
