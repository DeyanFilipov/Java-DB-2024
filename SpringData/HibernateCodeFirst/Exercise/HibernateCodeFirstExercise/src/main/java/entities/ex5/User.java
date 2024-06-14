package entities.ex5;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<BillingDetails> billingDetails;
}