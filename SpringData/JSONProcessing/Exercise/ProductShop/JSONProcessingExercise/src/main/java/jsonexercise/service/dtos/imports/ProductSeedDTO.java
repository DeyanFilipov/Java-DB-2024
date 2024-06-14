package jsonexercise.service.dtos.imports;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductSeedDTO implements Serializable {

    @Expose
    @NotNull
    @Size(min = 3)
    private String name;

    @Expose
    @NotNull
    private BigDecimal price;

    public @NotNull @Size(min = 3) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 3) String name) {
        this.name = name;
    }

    public @NotNull BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull BigDecimal price) {
        this.price = price;
    }
}
