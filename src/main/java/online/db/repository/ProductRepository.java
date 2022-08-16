package online.db.repository;

import online.db.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {


    @Query("select b from Products b where b.secondCategory.id = ?1")
    List<Products> getAllByNextCategory(Long id);
}
