package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsons.StarSeedDTO;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {

    private static final String FILE_PATH = "src/main/resources/files/json/stars.json";

    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();
        StarSeedDTO[] starSeedDTOS = this.gson.fromJson(readStarsFileContent(), StarSeedDTO[].class);

        for (StarSeedDTO starSeedDTO : starSeedDTOS) {
            Optional<Star> optional = this.starRepository.findByName(starSeedDTO.getName());
            if (!this.validationUtil.IsValid(starSeedDTO) || optional.isPresent()) {
                sb.append("Invalid star\n");
                continue;
            }
            Star star = this.modelMapper.map(starSeedDTO, Star.class);
            star.setStarType(StarType.valueOf(starSeedDTO.getStarType()));
            star.setConstellation(this.constellationRepository.findById(starSeedDTO.getConstellation()).get());
            this.starRepository.saveAndFlush(star);
            sb.append(String.format("Successfully imported star %s - %.2f light years\n", star.getName(), star.getLightYears()));
        }
        return sb.toString();
    }

    @Override
    public String exportStars() {
        return this.starRepository
                .findAllByStarTypeOrderByLightYears()
                .stream()
                .map(s -> String.format("Star: %s\n" +
                        "   *Distance: %.2f light years\n" +
                        "   **Description: %s\n" +
                        "   ***Constellation: %s\n",
                        s.getName(), s.getLightYears(), s.getDescription(), s.getConstellation().getName()))
                .collect(Collectors.joining());
    }
}
