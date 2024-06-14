package entities.ex2;

import entities.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "sales_store_locations",
    joinColumns = @JoinColumn(name = "sale_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "store_location_id", referencedColumnName = "id"))
    private Set<StoreLocation> storeLocation;

    @Column
    private LocalDateTime date;
}
