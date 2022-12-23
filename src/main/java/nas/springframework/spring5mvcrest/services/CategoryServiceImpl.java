package nas.springframework.spring5mvcrest.services;

import nas.springframework.spring5mvcrest.api.v1.model.CategoryDTO;
import nas.springframework.spring5mvcrest.domain.Category;
import nas.springframework.spring5mvcrest.api.v1.model.mapper.CategoryMapper;
import nas.springframework.spring5mvcrest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDto)//map method is used for mapping each category object from findAll()
                .collect(Collectors.toList());//collect  is mostly used to collect the stream elements to a collection.
        // It's a terminal operation. It takes care of synchronization ...
        // and collect the objects in a list
        //The toList() method of Collectors Class is a static (class) method. It returns a Collector Interface
        // that gathers the input data onto a new list. we don't need to define a new collection to save data
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDto(categoryRepository.findByName(name));
    }
}
