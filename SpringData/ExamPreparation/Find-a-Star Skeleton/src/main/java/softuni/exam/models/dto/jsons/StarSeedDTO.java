package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class StarSeedDTO implements Serializable {

    @Expose
    @Size(min = 6)
    private String description;

    @Expose
    @Min(0)
    private double lightYears;

    @Expose
    @Size(min = 2, max = 30)
    private String name;

    @Expose
    private String starType;

    @Expose
    private long constellation;

    public @Size(min = 6) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 6) String description) {
        this.description = description;
    }

    @Min(0)
    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(@Min(0) double lightYears) {
        this.lightYears = lightYears;
    }

    public @Size(min = 2, max = 30) String getName() {
        return name;
    }

    public void setName(@Size(min = 2, max = 30) String name) {
        this.name = name;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public long getConstellation() {
        return constellation;
    }

    public void setConstellation(long constellation) {
        this.constellation = constellation;
    }
}
