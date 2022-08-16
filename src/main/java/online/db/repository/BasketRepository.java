package online.db.repository;


import online.db.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    @Query("select b.basketId from Basket b where b.user.email = ?1")
    Long getUsersBasketId(String username);

    @Query(value = "select case when count(*) > 0 then 1 else 0 end " +
            "from products_basket where basket_id = ?1 and product_id = ?2", nativeQuery = true)
    Integer checkIfAlreadyClientPutInBasket(Long basketId, Long bookId);

}