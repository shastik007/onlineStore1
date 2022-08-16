package online.db.servise;

import lombok.AllArgsConstructor;
import online.db.model.Basket;
import online.db.model.SecondCategory;
import online.db.model.Products;
import online.db.model.User;
import online.db.model.dto.OrderDto;
import online.db.model.dto.ProductCard;
import online.db.repository.BasketRepository;
import online.db.repository.SecondCategoryRepository;
import online.db.repository.ProductRepository;
import online.db.repository.UserRepository;
import online.exceptions.BadRequestException;
import online.db.model.dto.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private SecondCategoryRepository nextCategoryRepository;
    private UserRepository userRepository;
    private BasketRepository basketRepository;

    /**
     * Admin
     */

    public Products saveProduct(Products products, Long id) {

        SecondCategory nextCategory = nextCategoryRepository.findById(id).get();

        products.setSecondCategory(nextCategory);

        return productRepository.save(products);
    }

    @Transactional
    public Products updateProduct(Products products, Long id) {
        Products oldProduct = productRepository.findById(id).get();

        String oldName = oldProduct.getAbout();
        String newName = products.getAbout();
        if (!oldName.equals(newName)) {
            oldProduct.setAbout(newName);
        }

        String old = oldProduct.getManufacturer();
        String news = products.getManufacturer();
        if (!old.equals(news)) {
            oldProduct.setManufacturer(news);
        }

        Double oldPrice = oldProduct.getPrice();
        Double newPrice = products.getPrice();
        if (!oldPrice.equals(newPrice)) {
            oldProduct.setPrice(newPrice);
        }

        String oldModel = oldProduct.getModel();
        String newModel = products.getModel();
        if (!oldModel.equals(newModel)) {
            oldProduct.setModel(newModel);
        }

        int oldW = oldProduct.getWeight();
        int newW = products.getWeight();
        if (!Objects.equals(oldW, newW)) {
            oldProduct.setWeight(newW);
        }

        return oldProduct;
    }

    public String deleteProductById(Long id) {
        productRepository.deleteById(id);
        return "Delete Product Successfully";
    }

    /**
     * Client
     */

    public List<Products> getAllProducts(Long nextId) {
        return productRepository.getAllByNextCategory(nextId);
    }

    public Products getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("Product with id %s doesn't exist!", id)
                    );
                });
    }

    @Transactional
    public ResponseEntity<?> addBookToBasket(OrderDto order) {

//        if (basketRepository.checkIfAlreadyClientPutInBasket(
//                getUsersBasketId(username), orderId) > 0) {
//            throw new BadRequestException("You already put this book in your basket");
//        }

//        User user = userRepository.getUser(username).orElseThrow(() ->
//                new NotFoundException(String.format("User with username %s not found", username)));


        List<ProductCard> productCards = new ArrayList<>();
        order.getOrders().forEach(el -> {
            ProductCard productCard = new ProductCard();
            productCard.setProductId(productRepository.findById(el.getProductId()).orElse(null));
            productCard.setCount(el.getCount());
            productCards.add(productCard);
        });


        Basket basket = new Basket();
        basket.setFullName(order.getFullName());
        basket.setProductCards(productCards);
        basket.setNumber(order.getPhoneNumber());
        Basket save = basketRepository.save(basket);

        return ResponseEntity.ok(new MessageResponse(String.format("Order with id %s has been added to basket of user",
                save.getBasketId())));
    }


    public Long getUsersBasketId(String username) {
        return basketRepository.getUsersBasketId(username);
    }


}
