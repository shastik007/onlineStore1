package online.db.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class FirstCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq")
    @SequenceGenerator(name = "hibernate_seq", sequenceName = "four_category_seq", allocationSize = 1)
    private Long id;

    private String nameCategory;

    private String image;

    @OneToMany(mappedBy = "firstCategory")
    private List<SecondCategory> nextCategory;
}
