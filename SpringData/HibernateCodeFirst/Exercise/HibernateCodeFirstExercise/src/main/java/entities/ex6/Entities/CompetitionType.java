package entities.ex6.Entities;

import entities.ex6.Enums.CompetitionScale;
import jakarta.persistence.*;

@Entity
@Table(name = "competition_types")
public class CompetitionType {

    private int id;
    private CompetitionScale scale;

    public CompetitionType(CompetitionScale scale) {
        this.scale = scale;
    }

    public CompetitionType() {
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

    @Column(name = "scale")
    public CompetitionScale getScale() {
        return scale;
    }

    public void setScale(CompetitionScale scale) {
        this.scale = scale;
    }
}
