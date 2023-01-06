package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.mapper.CustomerMapper;
import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import nas.springframework.spring5mvcrest.controllers.v1.CustomerController;
import nas.springframework.spring5mvcrest.domain.Customer;
import nas.springframework.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    //this part of code is interesting for me, we want to have a property of customerUrl, we define it in CustomerDTO
    //not Customer domain. and then since we don't want to save it in DB and repository because this is not a domain
    //property need to be saved but a property that just need in web application to generate a URL related to this
    // specific Customer. so we generate it in service layer, and here at 34 line,and in the {} part we define the URL
    //property and return it from this part of code out as one CustomerDTO , and collect it in a collector and return it out.
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.
                findAll()
                .stream()
                .map(customer -> {//map each customer with what is in {}s
                    CustomerDTO customerDTO = new CustomerDTO();//first, we should create a new object to be able to modify it then
                    customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        //I used this code before, and it got result too, but the better way from tutor is on next line
        // return customerMapper.customerToCustomerDTO((customerRepository.findById(id)).get());
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO).orElseThrow(RuntimeException::new);
        //todo implement better exception handling
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer =
                customerMapper.customerDTOToCustomer(customerDTO);

        return saveAndReturnDTO(customer);
    }

    //save pure Customer in interact with Repository, this part is in 2
    private CustomerDTO saveAndReturnDTO(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        customerDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));
        return customerDTO;

    }

    //update anything
    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

        Customer customer =
                customerMapper.customerDTOToCustomer(customerDTO);

        customer.setId(Long.valueOf(id));

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if (customerDTO.getFirstname() != null) {
                customer.setFirstname(customerDTO.getFirstname());
            }
            if (customerDTO.getLastname() != null) {
                customer.setLastname(customerDTO.getLastname());
            }

            CustomerDTO returnedDTO =
                    customerMapper.customerToCustomerDTO(customerRepository.save(customer));

            returnedDTO.setCustomerUrl(getCustomerUrl(id));

            return returnedDTO;

        }).orElseThrow(RuntimeException::new);//todo better Exception handling
    }

    @Override
    public void deleteCustomerById(Long id) {

        customerRepository.deleteById(id);
    }

    private String getCustomerUrl(Long id) {

        return CustomerController.BASE_URL + "/" + id;

    }
}
