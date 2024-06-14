package entities.ex6.Entities;


import entities.ex6.CompositeKeys.BetGameCK;
import jakarta.persistence.*;

@Entity
@IdClass(BetGameCK.class)
@Table(name = "bet_games")
public class BetGame {

    private Game game;
    private Bet bet;
    private ResultPrediction prediction;


    public BetGame(Game game, Bet bet, ResultPrediction prediction) {
        this.game = game;
        this.bet = bet;
        this.prediction = prediction;
    }

    public BetGame() {
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id",referencedColumnName = "id")
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    @Id
    @ManyToOne
    @JoinColumn(name = "bet_id", referencedColumnName = "id")
    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @ManyToOne
    @JoinColumn(name = "prediction_id", referencedColumnName = "id")
    public ResultPrediction getPrediction() {
        return prediction;
    }

    public void setPrediction(ResultPrediction prediction) {
        this.prediction = prediction;
    }
}
