package entities.ex6.Entities;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "bets")
public class Bet {

    private int id;
    private Double betMoney;
    private Date temporalInfo;
    private User user;
    private Set<BetGame> betGames;




    public Bet(Double betMoney, Date temporalInfo, User user) {
        this.betMoney = betMoney;
        this.temporalInfo = temporalInfo;
        this.user = user;
    }

    public Bet() {
    }

    @OneToMany(mappedBy = "bet")
    public Set<BetGame> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGame> betGames) {
        this.betGames = betGames;
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

    @Column(name = "bet_money")
    public Double getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(Double betMoney) {
        this.betMoney = betMoney;
    }

    @Column(name = "date")
    public Date getTemporalInfo() {
        return temporalInfo;
    }

    public void setTemporalInfo(Date temporalInfo) {
        this.temporalInfo = temporalInfo;
    }

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }
}
