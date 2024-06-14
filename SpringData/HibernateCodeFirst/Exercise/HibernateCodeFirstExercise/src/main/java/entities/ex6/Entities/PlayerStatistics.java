package entities.ex6.Entities;

import entities.ex6.CompositeKeys.PlayerStatsCK;
import jakarta.persistence.*;

@Entity
@IdClass(PlayerStatsCK.class)
@Table(name = "statistics")
public class PlayerStatistics {


    Game game;
    Player player;
    int scoredGoals;
    int playerAssists;
    int playedMinutesDuringGame;


    public PlayerStatistics(Game game, Player player, int scoredGoals, int playerAssists, int playedMinutesDuringGame) {
        this.game = game;
        this.player = player;
        this.scoredGoals = scoredGoals;
        this.playerAssists = playerAssists;
        this.playedMinutesDuringGame = playedMinutesDuringGame;
    }

    public PlayerStatistics() {
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
    @JoinColumn(name = "player_id",referencedColumnName = "id")
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Column(name = "scored_goals")
    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    @Column(name = "player_assists")
    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    @Column(name = "minutes_during_game")
    public int getPlayedMinutesDuringGame() {
        return playedMinutesDuringGame;
    }

    public void setPlayedMinutesDuringGame(int playedMinutesDuringGame) {
        this.playedMinutesDuringGame = playedMinutesDuringGame;
    }
}
