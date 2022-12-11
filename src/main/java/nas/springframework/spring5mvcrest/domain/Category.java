package nas.springframework.spring5mvcrest.domain;


import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity  // if we do not include this annotation the context building will be failed
public class Category {
    @Id
    @GeneratedValue
    Long id;
    String name;

}
