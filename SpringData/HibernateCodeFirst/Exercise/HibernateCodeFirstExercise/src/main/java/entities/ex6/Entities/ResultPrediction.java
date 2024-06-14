package entities.ex6.Entities;

import entities.ex6.Enums.Prediction;
import jakarta.persistence.*;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction {

    private int id;
    private Prediction prediction;

    public ResultPrediction(Prediction prediction) {
        this.prediction = prediction;
    }

    public ResultPrediction() {
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

    @Column(name = "prediction")
    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
