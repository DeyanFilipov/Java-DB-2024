package entities.ex6.Entities;


import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private Date temporalInfo;
    private double homeTeamWinRate;
    private double awayTeamWinRate;
    private double drawRate;
    private Round round;
    private Competition competition;
    private Set<BetGame> betGames;

    private Set<PlayerStatistics> playersStats;

    public Game(Team homeTeam, Team awayTeam, int homeGoals,
                int awayGoals, Date temporalInfo, double homeTeamWinRate,
                double awayTeamWinRate, double drawRate,
                Round round, Competition competition, Set<Player> players) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.temporalInfo = temporalInfo;
        this.homeTeamWinRate = homeTeamWinRate;
        this.awayTeamWinRate = awayTeamWinRate;
        this.drawRate = drawRate;
        this.round = round;
        this.competition = competition;

    }

    public Game() {
    }

    @OneToMany(mappedBy = "game")
    public Set<BetGame> getBetGames() {
        return betGames;
    }

    public void setBetGames(Set<BetGame> betGames) {
        this.betGames = betGames;
    }

    @OneToMany(mappedBy = "game")
    public Set<PlayerStatistics> getPlayersStats() {
        return playersStats;
    }

    public void setPlayersStats(Set<PlayerStatistics> playersStats) {
        this.playersStats = playersStats;
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

    @ManyToOne
    @JoinColumn(name = "home_team_id",referencedColumnName = "id")
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @ManyToOne
    @JoinColumn(name = "away_team_id",referencedColumnName = "id")
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Column(name = "home_goals")
    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    @Column(name = "away_goals")
    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    @Column(name = "date")
    public Date getTemporalInfo() {
        return temporalInfo;
    }

    public void setTemporalInfo(Date temporalInfo) {
        this.temporalInfo = temporalInfo;
    }

    @Column(name = "home_team_win_rate")
    public double getHomeTeamWinRate() {
        return homeTeamWinRate;
    }

    public void setHomeTeamWinRate(double homeTeamWinRate) {
        this.homeTeamWinRate = homeTeamWinRate;
    }

    @Column(name = "away_team_win_rate")
    public double getAwayTeamWinRate() {
        return awayTeamWinRate;
    }

    public void setAwayTeamWinRate(double awayTeamWinRate) {
        this.awayTeamWinRate = awayTeamWinRate;
    }

    @Column(name = "draw_rate")
    public double getDrawRate() {
        return drawRate;
    }

    public void setDrawRate(double drawRate) {
        this.drawRate = drawRate;
    }

    @ManyToOne
    @JoinColumn(name = "round_phase",referencedColumnName = "phase")
    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @ManyToOne
    @JoinColumn(name = "competition_id",referencedColumnName = "id")
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

}
