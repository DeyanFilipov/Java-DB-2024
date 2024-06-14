package jsonexercise.service.dtos.imports;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class UserSeedDTO implements Serializable {

    @Expose
    @NotNull
    private String firstName;

    @Expose
    @NotNull
    private String lastName;

    @Expose
    @Min(18)
    private int age;

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    @Min(18)
    public int getAge() {
        return age;
    }

    public void setAge(@Min(18) int age) {
        this.age = age;
    }
}
