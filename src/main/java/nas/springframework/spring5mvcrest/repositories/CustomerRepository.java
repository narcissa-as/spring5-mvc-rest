package nas.springframework.spring5mvcrest.repositories;

import nas.springframework.spring5mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long > {

}
