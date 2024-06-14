package lab.inheritance;

import jakarta.persistence.*;
import lab.composition.Company;
import org.hibernate.query.sqm.tree.from.DowncastLocation;

import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle{

    private static final String PLANE_TYPE = "PLANE";

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @ManyToOne
    private Company owner;

    private String airline;

    public Plane() {}

    public Plane(String model, BigDecimal price, String fuelType, int passengerCapacity, String airline, Company owner) {
        super(PLANE_TYPE, model, price, fuelType);

        this.passengerCapacity = passengerCapacity;
        this.airline = airline;
        this.owner = owner;
    }
}
