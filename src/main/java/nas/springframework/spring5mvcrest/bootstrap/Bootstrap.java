package nas.springframework.spring5mvcrest.bootstrap;


import nas.springframework.spring5mvcrest.domain.Category;
import nas.springframework.spring5mvcrest.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*Loading data using Spring Events (A context initialization event)
and CommandRunner is a Spring Boot specific class and  used to indicate
that a bean should run when it is contained within a SpringApplication
so this class gets called on startup and any arguments that have been
passed into the JVM also get picked up, but here we only to load some classes*/
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded: " + categoryRepository.count());
    }
}
