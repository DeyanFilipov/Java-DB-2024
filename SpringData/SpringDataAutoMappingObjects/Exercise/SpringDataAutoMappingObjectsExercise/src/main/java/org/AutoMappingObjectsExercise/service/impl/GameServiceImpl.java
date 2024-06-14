package org.AutoMappingObjectsExercise.service.impl;

import jakarta.validation.ConstraintViolation;
import org.AutoMappingObjectsExercise.data.entities.Game;
import org.AutoMappingObjectsExercise.data.repositories.GameRepository;
import org.AutoMappingObjectsExercise.service.GameService;
import org.AutoMappingObjectsExercise.service.UserService;
import org.AutoMappingObjectsExercise.service.dtos.GameAddDTO;
import org.AutoMappingObjectsExercise.service.dtos.GamesAllDTO;
import org.AutoMappingObjectsExercise.util.ValidatorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ValidatorService validatorService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ValidatorService validatorService, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.validatorService = validatorService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String addGame(GameAddDTO gameAddDTO) {
        if (this.userService.getLoggedIn() != null && this.userService.getLoggedIn().isAdmin()) {
            if (!this.validatorService.isValid(gameAddDTO)) {
                return this.validatorService.validate(gameAddDTO)
                        .stream().map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining("\n"));
            }

            Game game = this.modelMapper.map(gameAddDTO, Game.class);
            this.gameRepository.saveAndFlush(game);

            return String.format("Added %s", game.getTitle());
        }
        return "Logged in User is not admin";
    }

    @Override
    public String editGame(long id, Map<String, String> map) {
        Optional<Game> optionalGame = this.gameRepository.findById(id);
        if (optionalGame.isEmpty()) {
            return "No such game exists with given id";
        }

        Game game = optionalGame.get();
        String output = String.format("Edited %s", game.getTitle());

        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case "title":
                    game.setTitle(entry.getValue());
                    break;
                case "price":
                    game.setPrice(Double.parseDouble(entry.getValue()));
                    break;
                case "size":
                    game.setSize(Double.parseDouble(entry.getValue()));
                    break;
                case "trailer":
                    game.setTrailer(entry.getValue());
                    break;
                case "thumbnail":
                    game.setThumbnail(entry.getValue());
                    break;
                case "description":
                    game.setDescription(entry.getValue());
                    break;
                case "releaseDate":
                    game.setReleaseDate(LocalDate.parse(entry.getValue()));
                    break;
            }
        }

        this.gameRepository.saveAndFlush(game);
        return output;
    }

    @Override
    public String deleteGame(long id) {
        Optional<Game> optionalGame = this.gameRepository.findById(id);
        if (optionalGame.isEmpty()) {
            return "No such game with given id";
        }
        String output = String.format("Deleted %s", optionalGame.get().getTitle());
        this.gameRepository.delete(optionalGame.get());
        return output;
    }

    @Override
    public Set<GamesAllDTO> getAllGames() {
        return this.gameRepository.findAll()
                .stream()
                .map(g -> this.modelMapper.map(g, GamesAllDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public String allGamesReadyForPrint() {
        return this.getAllGames()
                .stream()
                .map(GamesAllDTO::toString)
                .collect(Collectors.joining("\n"));
    }
}
