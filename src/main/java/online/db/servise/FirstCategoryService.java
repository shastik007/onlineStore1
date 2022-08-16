package online.db.servise;

import lombok.AllArgsConstructor;
import online.db.model.FirstCategory;
import online.db.repository.FirstCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FirstCategoryService {
    private FirstCategoryRepository repository;

    public FirstCategory save(FirstCategory category) {
        return repository.save(category);
    }

    public List<FirstCategory> getAllFourCategory() {
        return repository.findAll();
    }

    @Transactional
    public FirstCategory updateCategory(FirstCategory products, Long id) {
        FirstCategory fourCategory = repository.findById(id).get();
        String oldName = fourCategory.getNameCategory();
        String newName = products.getNameCategory();
        if (!oldName.equals(newName)){
            fourCategory.setNameCategory(newName);
        }
        return fourCategory;
    }

    public String delete(Long id) {
        repository.deleteById(id);
        return "Successfully delete Category";
    }

    public FirstCategory findById(Long id) {
        return repository.findById(id).get();
    }
}
