package entities.ex6.Entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Position {

    private String idLetterPosition;
    private String description;

    private Set<Player> players;

    public Position(String idLetterPosition, String description) {
        this.idLetterPosition = idLetterPosition;
        this.description = description;
    }

    public Position() {
    }

    @OneToMany(mappedBy = "position")
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(2)")
    public String getIdLetterPosition() {
        return idLetterPosition;
    }

    public void setIdLetterPosition(String idLetterPosition) {
        this.idLetterPosition = idLetterPosition;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
