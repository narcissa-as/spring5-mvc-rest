package nas.springframework.spring5mvcrest.domain;


import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Category {
    @Id
    @GeneratedValue
    Long id;
    String name;

}
