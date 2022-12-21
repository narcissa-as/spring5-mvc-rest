package nas.springframework.spring5mvcrest.repositories;

import nas.springframework.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long>{
    Category findByName(String name);
}
