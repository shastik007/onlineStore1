package online.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import online.db.model.FirstCategory;
import online.db.model.SecondCategory;
import online.db.model.Products;
import online.db.servise.FirstCategoryService;
import online.db.servise.SecondCategoryService;
import online.db.servise.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@AllArgsConstructor
@Tag(name = "Admin", description = "Admin accessible apis")
public class AdminApi {

    private ProductService productService;
    private FirstCategoryService fourCategoryService;
    private SecondCategoryService nextCategoryService;

    @Operation(summary = "Save Category")
    @PostMapping("/first-category")
    public FirstCategory saveCategory(@RequestBody FirstCategory category) {
        return fourCategoryService.save(category);
    }

    @Operation(summary = "Save Next Category")
    @PostMapping("/second-category/{firstCategoryId}")
    public SecondCategory saveNextCategory(@RequestBody SecondCategory category, @PathVariable Long firstCategoryId) {
        return nextCategoryService.save(category,firstCategoryId);
    }

    @Operation(summary = "Save product")
    @PostMapping("/product/{secondId}")
    public Products saveProduct(@RequestBody Products products,@PathVariable Long secondId) {
        return productService.saveProduct(products,secondId);
    }


    @Operation(summary = "Update First Category")
    @PatchMapping("/first-category/{id}")
    public FirstCategory updateFourPr(@RequestBody FirstCategory products, @PathVariable Long id) {
        return fourCategoryService.updateCategory(products,id);
    }

    @Operation(summary = "Delete First Category ")
    @DeleteMapping("/first-category/{id}")
    public String deleteFourPr(@PathVariable Long id) {
        return fourCategoryService.delete(id);
    }

    @Operation(summary = "Update Second Category")
    @PatchMapping("/second-category/{id}")
    public SecondCategory updateNextPr(@RequestBody SecondCategory nextCategory, @PathVariable Long id) {
        return nextCategoryService.updateNextCategory(nextCategory,id);
    }
   

    @Operation(summary = "Delete Second Category ")
    @DeleteMapping("/second-category/{id}")
    public String deleteNextPr(@PathVariable Long id) {
        return nextCategoryService.deleteById(id);
    }

    @Operation(summary = "Update product")
    @PatchMapping("/product/{id}")
    public Products updatePr(@RequestBody Products products,@PathVariable Long id) {
        return productService.updateProduct(products,id);
    }

    @Operation(summary = "Update product")
    @DeleteMapping("/product/{id}")
    public String deletePr(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

}

 // this is govno code
