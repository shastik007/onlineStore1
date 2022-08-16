package online.db.servise;

import lombok.AllArgsConstructor;
import online.db.model.FirstCategory;
import online.db.model.SecondCategory;
import online.db.repository.FirstCategoryRepository;
import online.db.repository.SecondCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SecondCategoryService {

    private final SecondCategoryRepository nextCategoryRepository;
    private final FirstCategoryRepository fourCategoryRepository;

    public SecondCategory save(SecondCategory category, Long id) {

        FirstCategory fourCategory = fourCategoryRepository.findById(id).get();

        category.setFirstCategory(fourCategory);

        return nextCategoryRepository.save(category);
    }

    public List<SecondCategory> getAllNextCategory(Long id) {
        return nextCategoryRepository.findAllNext(id);
    }

    @Transactional
    public SecondCategory updateNextCategory(SecondCategory nextCategory, Long id) {
        SecondCategory nextCategory1 = nextCategoryRepository.findById(id).get();
        String oldName = nextCategory1.getName();
        String newName = nextCategory.getName();
        if (!oldName.equals(newName)){
            nextCategory1.setName(newName);
        }

        return nextCategory1;
    }

    public String deleteById(Long id) {
         nextCategoryRepository.deleteById(id);
         return "Delete Category successfully";
    }

    public SecondCategory findById(Long id) {
        return nextCategoryRepository.findById(id).get();
    }
}
