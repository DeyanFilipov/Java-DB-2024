package entities.ex6.Entities;


import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "competitions")
public class Competition {

    private int id;
    private String name;
    private CompetitionType type;

    private Set<Game> games;

    public Competition(String name, CompetitionType type) {
        this.name = name;
        this.type = type;
    }

    public Competition() {
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
    @JoinColumn(name = "competition_scale",referencedColumnName = "scale")
    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }

    @OneToMany(mappedBy = "competition")
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}

