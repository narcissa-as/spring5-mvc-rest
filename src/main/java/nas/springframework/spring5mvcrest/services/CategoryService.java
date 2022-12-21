package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import nas.springframework.spring5mvcrest.domain.Category;

import java.util.List;

public interface CategoryService {

   List<CategoryDTO> getAllCategories ();
   CategoryDTO getCategoryByName(String name);
}
