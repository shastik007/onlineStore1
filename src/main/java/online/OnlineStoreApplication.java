package online;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.AllArgsConstructor;
import online.db.model.Basket;
import online.db.model.ERole;
import online.db.model.Role;
import online.db.model.User;
import online.db.repository.RoleRepository;
import online.db.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@SpringBootApplication
@OpenAPIDefinition
@AllArgsConstructor
public class OnlineStoreApplication {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
        System.out.println("Welcome to application online store");
    }

    @GetMapping("/")
    public String getAllBookRequest() {
        return " Hello Barsbek2";
    }

//    @PostConstruct
    public void init() {
        Role client = new Role();
        client.setId(1L);
        client.setName(ERole.ROLE_ADMIN);
        Role admin = new Role();
        admin.setId(2L);
        admin.setName(ERole.ROLE_CLIENT);
        roleRepository.save(client);
        roleRepository.save(admin);

        User admins = new User();
        admins.setEmail("admin@gmail.com");
        admins.setFullName("Admin");
        admins.setPassword(encoder.encode("admin"));
        admins.setRole(roleRepository.getByIdRole(1L));
        userRepository.save(admins);

        User clients = new User();
        clients.setEmail("client@gmail.com");
        clients.setFullName("Client");
        clients.setPassword(encoder.encode("client"));
        clients.setRole(roleRepository.getByIdRole(2L));
        Basket basket1 = new Basket();
        basket1.setUser(clients);
        clients.setBasket(basket1);
        userRepository.save(clients);
    }
}
