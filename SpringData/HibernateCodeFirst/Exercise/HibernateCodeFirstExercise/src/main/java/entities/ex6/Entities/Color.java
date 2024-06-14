package entities.ex6.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "colors")
public class Color {

    private int id;
    private String name;

    public Color(String name) {
        this.name = name;
    }

    public Color() {
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
}
