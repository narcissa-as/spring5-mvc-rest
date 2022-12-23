package nas.springframework.spring5mvcrest.controllers.v1;

import nas.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import nas.springframework.spring5mvcrest.api.v1.model.CategoryListDTO;
import nas.springframework.spring5mvcrest.repositories.CategoryRepository;
import nas.springframework.spring5mvcrest.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
    public static final Long ID = 1L;
    public static final String NAME = "NAME";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategories() throws Exception {
        //new Category
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        //list DTO creation
        /*List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryDTOS.add(categoryDTO);
*/
        //CategoryListDTO categoryListDTO = new CategoryListDTO(categoryDTOS);
        // CategoryListDTO categoryListDTO = Arrays.asList(categoryDTO);
        //3th way
        List<CategoryDTO> categories = Arrays.asList(categoryDTO);

        //when
        when(categoryService.getAllCategories()).thenReturn(categories);

        //then
        mockMvc.perform(get("/api/v1/categories/")
                        // mockMvc.perform(get("/api/v1/categories/")
                        .contentType(MediaType.APPLICATION_JSON))
                //JSONPath is a query language for JSON, similar to XPath for XML. It allows you to select and extract data from a JSON
                // document. You use a JSONPath expression to traverse the path to an element in the JSON structure.
                //here we use jsonPath for examining the json object, $ sign is the root
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(1)));

    }

    @Test
    void getCategoryByName() throws Exception {
        //creating CategoryDTO
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        //when
        when(categoryService.getCategoryByName(any())).thenReturn(categoryDTO);

        //then
        mockMvc.perform(get("/api/v1/categories/jim").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }
}