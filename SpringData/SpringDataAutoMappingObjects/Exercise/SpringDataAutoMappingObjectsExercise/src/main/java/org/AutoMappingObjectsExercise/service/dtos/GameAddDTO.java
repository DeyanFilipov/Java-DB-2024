package org.AutoMappingObjectsExercise.service.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class GameAddDTO {

    @Size(min = 3, max = 100, message = "Length should be between 3 and 100 letters")
    @Pattern(regexp = "[A-Z]+\\w+", message = "Title should start with a capital letter")
    private String title;

    @Min(value = 0)
    private double price;

    @Min(value = 0)
    private double size;

    @Size(min = 11, max = 11)
    private String trailer;

    @Pattern(regexp = "^(?:http://)*(?:https://)*.+", message = "Thumbnail doesn't start with the correct protocol")
    private String thumbnail;

    @Size(min = 20)
    private String description;

    private LocalDate releaseDate;

    public GameAddDTO(String title, double price, double size, String trailer, String thumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnail = thumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public double getSize() {
        return size;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
