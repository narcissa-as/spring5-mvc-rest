package nas.springframework.spring5mvcrest.mapper;

import nas.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import nas.springframework.spring5mvcrest.api.v1.model.mapper.CategoryMapper;
import nas.springframework.spring5mvcrest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//just a pure unit Test, not using spring Context
class CategoryMapperTest {
   public static final String NAME="JOE";
   public static final Long ID=1L;

   CategoryMapper categoryMapper=CategoryMapper.INSTANCE; //just a helper

    @Test
    void categoryToCategoryDto() {
        //given
        Category category= new Category();
        category.setId(ID);
        category.setName(NAME);

        //when
        CategoryDTO categoryDTO =categoryMapper.categoryToCategoryDto(category);
        assertEquals(categoryDTO.getId(),Long.valueOf(ID));
        assertEquals(categoryDTO.getName(),NAME);

    }
}