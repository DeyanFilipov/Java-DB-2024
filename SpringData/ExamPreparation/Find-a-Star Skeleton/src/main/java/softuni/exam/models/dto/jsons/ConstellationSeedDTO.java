package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class ConstellationSeedDTO implements Serializable {

    @Expose
    @Size(min = 3, max = 20)
    private String name;

    @Expose
    @Size(min = 5)
    private String description;

    public @Size(min = 3, max = 20) String getName() {
        return name;
    }

    public void setName(@Size(min = 3, max = 20) String name) {
        this.name = name;
    }

    public @Size(min = 5) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 5) String description) {
        this.description = description;
    }
}
