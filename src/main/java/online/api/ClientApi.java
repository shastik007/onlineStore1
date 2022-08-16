package online.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import online.db.model.Basket;
import online.db.model.FirstCategory;
import online.db.model.SecondCategory;
import online.db.model.Products;
import online.db.model.dto.OrderDto;
import online.db.repository.BasketRepository;
import online.db.servise.FirstCategoryService;
import online.db.servise.SecondCategoryService;
import online.db.servise.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*", maxAge = 3600)

@AllArgsConstructor
@Tag(name = "Client", description = "Client accessible apis")
public class ClientApi {

    private ProductService productService;
    private FirstCategoryService fourCategoryService;
    private SecondCategoryService nextCategoryService;
    private BasketRepository basketRepository;

    @Operation(summary = "get all four category")
    @GetMapping("/four-category")
    public List<FirstCategory> getAllFourCategory() {
        return fourCategoryService.getAllFourCategory();
    }

    @Operation(summary = "Get first Category by id")
    @GetMapping("/first-category/{id}")
    public FirstCategory getById(@PathVariable Long id) {
        return fourCategoryService.findById(id);
    }

    @Operation(summary = "get all next category")
    @GetMapping("/next-category/{fourId}")
    public List<SecondCategory> getAllNextCategory(@PathVariable Long fourId) {
        return nextCategoryService.getAllNextCategory(fourId);
    }

    @Operation(summary = "Get Second Category by id")
    @GetMapping("/second-category/{id}")
    public SecondCategory getBySecond(@PathVariable Long id) {
        return nextCategoryService.findById(id);
    }


    @Operation(summary = "getAll product by id")
    @GetMapping("/products/{nextId}")
    public List<Products> getAllProduct(@PathVariable Long nextId) {
        return productService.getAllProducts(nextId);
    }

    @Operation(summary = "get product by id")
    @GetMapping("/product/{id}")
    public Products getProduct(@PathVariable Long id) {
        return productService.getById(id);
    }


    @Operation(summary = "add to basket", description = "add a Order to basket")
//    @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    @PostMapping("/order/basket/{orderId}")
    public ResponseEntity<?> addToBasket(@RequestBody OrderDto order) {
        return productService.addBookToBasket(order);
    }

    @GetMapping("/order/basket/all")
    public List<Basket> getAllBasket() {
        return basketRepository.findAll();
    }


    // To Do
    // plus or minus and Client Operation

}
