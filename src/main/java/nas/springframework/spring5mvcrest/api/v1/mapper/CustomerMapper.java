package nas.springframework.spring5mvcrest.api.v1.mapper;

import nas.springframework.spring5mvcrest.api.v1.model.CustomerDTO;
import nas.springframework.spring5mvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}
