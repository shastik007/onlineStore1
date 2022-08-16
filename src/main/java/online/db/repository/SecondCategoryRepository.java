package online.db.repository;

import online.db.model.SecondCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecondCategoryRepository extends JpaRepository<SecondCategory,Long> {

    @Query("select b from SecondCategory b where b.firstCategory.id = ?1")
    List<SecondCategory> findAllNext(Long id);
}
