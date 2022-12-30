package nas.springframework.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nas.springframework.spring5mvcrest.domain.Customer;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTOList {
    private List<CustomerDTO> customers;
}
