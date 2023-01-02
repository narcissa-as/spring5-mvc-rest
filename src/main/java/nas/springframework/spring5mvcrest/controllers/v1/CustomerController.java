package nas.springframework.spring5mvcrest.controllers.v1;

import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTOList;
import nas.springframework.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    ResponseEntity<CustomerDTOList> getListOfCustomers() {
        return new ResponseEntity<CustomerDTOList>(
                new
                        CustomerDTOList(customerService.getAllCustomers()), HttpStatus.OK);

    }

    @GetMapping({"{id}"})
    ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id) {
        return new ResponseEntity<CustomerDTO>
                (customerService.getCustomerById(Long.valueOf(id)), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        // we want the object bound automatically,so we use @RequestBody which tells Spring to check and parse
        // the body of the request and try to create a customerDTO out of that
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);
        return new ResponseEntity<CustomerDTO>
                (customerService.createNewCustomer(customerDTO)
                        , HttpStatus.CREATED);
    }
}
