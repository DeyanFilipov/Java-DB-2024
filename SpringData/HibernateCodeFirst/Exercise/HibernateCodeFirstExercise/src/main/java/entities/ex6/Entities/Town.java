package entities.ex6.Entities;


import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {

    private int id;
    private String name;
    private Country country;
    private Set<Team> localClubs;

    public Town(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public Town() {
    }

    @OneToMany(mappedBy = "town")
    public Set<Team> getLocalClubs() {
        return localClubs;
    }

    public void setLocalClubs(Set<Team> localClubs) {
        this.localClubs = localClubs;
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

    @ManyToOne
    @JoinColumn(name = "country_id",referencedColumnName = "id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
