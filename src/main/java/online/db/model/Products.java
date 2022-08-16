package online.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq")
    @SequenceGenerator(name = "hibernate_seq", sequenceName = "product_seq", allocationSize = 1)
    @Column(name = "product_id")
    private Long productId;

    private String manufacturer;
    private String model;
    private int weight;
    private Double price;
    private String about;

    private String image;

    @ManyToOne
    @JsonIgnore
    private SecondCategory secondCategory;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "operation_products"
            , joinColumns = @JoinColumn(name = "product_id")
            , inverseJoinColumns = @JoinColumn(name = "operation_id"))
    private List<ClientOperations> operations;

    @JsonIgnore
    public void setOperation(ClientOperations courseId) {
        if (operations == null) {
            operations = new ArrayList<>();
        }
        operations.add(courseId);

    }

}
