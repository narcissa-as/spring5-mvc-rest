package nas.springframework.spring5mvcrest.repositories;

import nas.springframework.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Long> {

}
