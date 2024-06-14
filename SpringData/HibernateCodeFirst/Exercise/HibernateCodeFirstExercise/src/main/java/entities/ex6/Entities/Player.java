package entities.ex6.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "players")
public class Player {

    private int id;
    private String name;
    private int squadNumber;
    private Team team;
    private Position position;
    private boolean isCurrentlyInjured;
    private Set<PlayerStatistics> stats;


    public Player(String name, int squadNumber, Team team, Position position, boolean isCurrentlyInjured) {
        this.name = name;
        this.squadNumber = squadNumber;
        this.team = team;
        this.position = position;
        this.isCurrentlyInjured = isCurrentlyInjured;
    }

    public Player() {
    }

    @OneToMany(mappedBy = "player")
    public Set<PlayerStatistics> getStats() {
        return stats;
    }

    public void setStats(Set<PlayerStatistics> stats) {
        this.stats = stats;
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

    @Column(name = "squad_number")
    public int getSquadNumber() {
        return squadNumber;
    }

    public void setSquadNumber(int squadNumber) {
        this.squadNumber = squadNumber;
    }

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Column(name = "is_currently_injured")
    public boolean isCurrentlyInjured() {
        return isCurrentlyInjured;
    }

    public void setCurrentlyInjured(boolean currentlyInjured) {
        isCurrentlyInjured = currentlyInjured;
    }
}
