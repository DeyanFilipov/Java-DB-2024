package org.AutoMappingObjectsExercise.service;

import org.AutoMappingObjectsExercise.service.dtos.GameAddDTO;
import org.AutoMappingObjectsExercise.service.dtos.GamesAllDTO;

import java.util.Map;
import java.util.Set;

public interface GameService {
    String addGame(GameAddDTO gameAddDTO);

    String editGame(long id, Map<String, String> map);

    String deleteGame(long id);

    Set<GamesAllDTO> getAllGames();

    String allGamesReadyForPrint();
}
