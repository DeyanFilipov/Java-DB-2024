package entities.ex3;

import entities.BaseEntity;
import jakarta.persistence.*;

@MappedSuperclass
public abstract class Information extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    private String phone;
}
