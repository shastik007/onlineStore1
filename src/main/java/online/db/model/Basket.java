package online.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import online.db.model.dto.ProductCard;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq")
    @SequenceGenerator(name = "hibernate_seq", sequenceName = "basket_seq", allocationSize = 1)
    @Column(name = "basket_id")
    private Long basketId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductCard> productCards;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String fullName;

    private String number;

    public void clear() {
        productCards.removeIf(books -> true);
    }
}
