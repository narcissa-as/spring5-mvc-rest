package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import nas.springframework.spring5mvcrest.domain.Category;
import nas.springframework.spring5mvcrest.mapper.CategoryMapper;
import nas.springframework.spring5mvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {
    static final String NAME = "CATEGORY_1";
    static final Long ID = 1L;
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;
    //instead of mocking Mapper we do it like setUp
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);//I assume this is equal to @init mock
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);// interface= getting new
        //from impl class, with getting the class Mapper and Repository Bean
    }

    @Test
    void getAllCategories() {

        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryResult = new ArrayList<>();
        categoryResult = categoryService.getAllCategories();

        //then
        assertEquals(categoryResult.size(), 3);
        assertEquals(categoryResult.isEmpty(), false);

    }

    @Test
    void getCategoryByName() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO=categoryService.getCategoryByName(NAME);

        //then
        assertNotNull(categoryDTO);
        assertEquals(categoryDTO.getName(), NAME);
        assertEquals(categoryDTO.getId(),ID);
    }
}