package entities.ex6.Entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {


    private String init;
    private String name;
    private Set<Continent> continents;
    private Set<Town> towns;

    public Country(String init, String name) {
        this.init = init;
        this.name = name;

    }

    public Country() {
    }

    @OneToMany(mappedBy = "country")
    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }

    @Id
    @Column(name = "id")
    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "countries")
    public Set<Continent> getContinents() {
        return continents;
    }

    public void setContinents(Set<Continent> continents) {
        this.continents = continents;
    }
}
