package entities.ex6.Entities;

import entities.ex6.Enums.RoundPhase;
import jakarta.persistence.*;

@Entity
@Table(name = "rounds")
public class Round {

    private int id;
    private RoundPhase phase;

    public Round(RoundPhase phase) {
        this.phase = phase;
    }

    public Round() {
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

    @Column(name = "phase")
    public RoundPhase getPhase() {
        return phase;
    }

    public void setPhase(RoundPhase phase) {
        this.phase = phase;
    }
}
