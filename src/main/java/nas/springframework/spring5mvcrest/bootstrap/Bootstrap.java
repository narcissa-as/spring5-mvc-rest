package nas.springframework.spring5mvcrest.bootstrap;


import nas.springframework.spring5mvcrest.domain.Category;
import nas.springframework.spring5mvcrest.domain.Customer;
import nas.springframework.spring5mvcrest.repositories.CategoryRepository;
import nas.springframework.spring5mvcrest.repositories.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
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

        System.out.println("Category Data Loaded: " + categoryRepository.count());
    }

    private void loadCustomers() {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Freddy");
        customer1.setLastname("Meyers");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Michale");
        customer2.setLastname("Weston");

        //save
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        System.out.println("Customer Data Loaded: " + customerRepository.count());
    }
}

