package entities.ex6.Entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent {

    private int id;
    private String name;
    private Set<Country> countries;

    public Continent(String name) {
        this.name = name;
    }

    public Continent() {
    }

    @ManyToMany
    @JoinTable(name = "continents_countries",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "continent_id", referencedColumnName = "id"))
    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
